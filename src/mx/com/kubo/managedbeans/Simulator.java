package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import mx.com.kubo.bean.Amortization;
import mx.com.kubo.bean.SimBeanForList;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.registro.documentacion.AddPldDocument;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.Simulation_Cache;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SlideEndEvent;

import com.sun.faces.mgbean.ManagedBeanCreationException;

import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;

@ManagedBean @SessionScoped
public class Simulator extends SimulatorAMO
implements Serializable
{			
	private static final long serialVersionUID = 2314574719378680076L;

	@PostConstruct
	public void init()
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if(sesion.getRate() != null)
		{
	   		setWsim(370);
	   		setWgral(750);
	   		setWcat(250);
	   		setMsim(0);
	   		
		} else {		 
			
			setWsim(815);
			setWgral(1250);
			setWcat(760);
			setMsim(230);
	   }
			
		init_partner_ID();
		init_simulation_config();
		init_list_frequency();
		init_PLD();
			
		if(sesion.getProspectus_id() != null && simulationConfig != null )
		{
			initSimulador_();
		}
	}

	public void onSlideEnd(SlideEndEvent event) 
	{  
        setTerm_id(event.getValue());  
        simulaCred(false);
    }
	
	public void onSlideMontoEnd(SlideEndEvent event) 
	{ 
        setAmmount(event.getValue());  
        simulaCred(false);
    }
	
	public void onSlidePlazoEnd(SlideEndEvent event) 
	{  
        setTerm_id(event.getValue());  
        simulaCred(false);
    }
	
	public void onFreqChange(ValueChangeEvent ce) 
	{  		
		if(ce.getNewValue()!=null)
		{
			this.frequency_id = Integer.parseInt(ce.getNewValue().toString());  
        	simulaCred(false);
		}
    }
	
	public void ammountAdd()
	{
		if(this.ammount < 50000)
		{
			
		 this.ammount = this.ammount + 100; 
		 simulaCred(false);
		}
	}
	
	public void ammountDel()
	{
		if(this.ammount > 5000)
		{
			this.ammount = this.ammount - 100;
			simulaCred(false);
		}
	}

	public void simulaCred(boolean isSafi)
	{
		if( sesion.getArea() != null && sesion.getArea().toString().equals("L"))
		{
			proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		}
		
		setActualRate();
		setNumCuota(generaNumCuotas());
		setTasaPeriodo(generaTasaPeriodo(true));
		
		if(isSafi)
		{
			generaMontoCuota2();
			
		} else {
			
			getCuotaByFormula(true,isSafi);
		}
		
		setInteres(generaInteres());
		/*setIvaInteres(generaIva(getInteres(),getIva()));*/
		//setTotalPagar(generaTotal());
		
		if(flagSaveSimulationCache)
		{
			saveSimulatorCache();
		}
		
		if(isConnected() && !getTotalPagarStr().equals("No disponible"))
		{
			saveSimulator();
			addSimulator(isSafi);
			updateProyectLoan();			
			
		} else {
			
			addSimulatorAnonimo(isSafi);			
		}
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if(requestContext != null)
		{
			requestContext.addCallbackParam("valor", true);
		}
	}

	public void generaMontoCuota2()
	{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		
		String thisFechaInicio = format.format(new Date());
		setFechaInicio(formatStr.format(new Date()));
		
		Simulation_Cache simulationCache = null;
		//Consulta el servicio solo si es Semanal o Catorcenal
		if(getFrequency_id() ==1 || getFrequency_id() == 2)
			simulationCache = simulationCacheService.getMaxByAmmountRateTermFrequency(getAmmount(), getTasaPeriodo(), getTerm_id(), getFrequency_id(), 1);
		
		
		if(simulationCache == null)
		{		
			SimuladorCuotaCreditoRequest simulador = new SimuladorCuotaCreditoRequest();
			
			simulador.setFechaInicio(thisFechaInicio);
			simulador.setFrecuencia(getFreqStr());
			simulador.setMontoSolici(getAmmount()+"");
			simulador.setPlazo(getTerm_id()+"");
			
			simulador.setTasaAnualizada(getTasaTotal()+"");
			simulador.setAjustarFecVen("N");
			setMontoComisionApert((getAmmount() * getComisionApertura()) / 100);
			simulador.setComisionApertura(getMontoComisionApert() + "");
			simulador.setFormaCobroComAp("D");
			
			ServiceCalling srvCall = new ServiceCalling();
			if(sesion!=null&&sesion.getProspectus_id()!=null&&sesion.getProspectus_id()!=0){
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(sesion.getCompany_id());
				srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito");
				srvCall.setProspectus_id(sesion.getProspectus_id());
				srvCall.setStatus(1);
				servicecallingRepository.saveServiceCall(srvCall);
			}
			
			try
			{
				
				SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
				SAFIServicios service = locator.getSAFIServiciosSoap11();
				SimuladorCuotaCreditoResponse response =  service.simuladorCuotaCredito(simulador);
				
					if(response.getCodigoRespuesta().equals("0")){
						
						if(sesion!=null&&sesion.getProspectus_id()!=null&&sesion.getProspectus_id()!=0){
						
							srvCall = new ServiceCalling();
				
							srvCall.setAcces_datetime(new Date());
							srvCall.setCompany_id(sesion.getCompany_id());
							srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito: "+response.getMensajeRespuesta());
							srvCall.setProspectus_id(sesion.getProspectus_id());
							srvCall.setStatus(2);
							
							servicecallingRepository.saveServiceCall(srvCall);
						}
						
						setCat(Double.parseDouble(response.getCat().replace(",", "")));
						//System.out.println("Cat Antes:  -->"+getCat());
						setCat((double) Math.ceil(getCat()*10)/10);
						//System.out.println("Cat Después:  -->"+getCat());
						//setCatStr(response.getCat());
						setMontoCuota(Double.parseDouble(response.getMontoCuota().replace(",", "")));
						setNumCuota(Integer.parseInt(response.getNumeroCuotas().replace(",", "")));
						setTotalPagar(Double.parseDouble(response.getTotalPagar().replace(",", "")));
						
						//Guarda solo si la frecuencia es Semanal o Catorcenal
						if(getFrequency_id() == 1 || getFrequency_id() == 2){
							flagSaveSimulationCache = true;
							
						}else{
							flagSaveSimulationCache = false;
							
						}
						
						
					}else{
						if(sesion!=null&&sesion.getProspectus_id()!=null&&sesion.getProspectus_id()!=0){
							srvCall = new ServiceCalling();
							
							srvCall.setAcces_datetime(new Date());
							srvCall.setCompany_id(sesion.getCompany_id());
							srvCall.setException(response.getMensajeRespuesta());
							srvCall.setProspectus_id(sesion.getProspectus_id());
							srvCall.setStatus(3);
							servicecallingRepository.saveServiceCall(srvCall);
							
							//setCat(Double.parseDouble(response.getCat().replace(",", "")));
							setCatStr("No disponible");
							setMontoCuota(0D);
							setNumCuota(0);
							setTotalPagar(0D);
							
						}
							flagSaveSimulationCache = false;
						
					}
			}catch(Exception e){
				e.printStackTrace();
				setCatStr("No disponible");
				setMontoCuotaStr("No disponible");
				
				setTotalPagar(0D);
				flagSaveSimulationCache = false;
			}
		}else{
			
			
			setCat(Math.ceil(simulationCache.getMx_cat()*10)/10);
			
			//setCat(Double.parseDouble(response.getCat().replace(",", "")));
			//System.out.println("Cat Antes:  -->"+getCat());
			//setCat((double) Math.ceil(getCat()*10)/10)	;
			//System.out.println("Cat Después:  -->"+getCat());
			//setCatStr(response.getCat());
			setMontoCuota(simulationCache.getPayment());
			//setMontoCuota(Double.parseDouble(response.getMontoCuota().replace(",", "")));
			setNumCuota(simulationCache.getNum_payments());
			//setNumCuota(Integer.parseInt(response.getNumeroCuotas().replace(",", "")));
			setTotalPagar(simulationCache.getTotal_payment());
			//setTotalPagar(Double.parseDouble(response.getTotalPagar().replace(",", "")));
			flagSaveSimulationCache = false;
			
			simulationCache.setConsultation_last(new Date());
			simulationCache.setConsultation_count(simulationCache.getConsultation_count() == null?1:(simulationCache.getConsultation_count()+1));
			
			simulationCacheService.update(simulationCache);
			
		}	
	}
	
	public void saveSimulator()
	{	
		int prospectus_id = sesion.getProspectus_id();
		
		/*
		SimulatorPK pk = new SimulatorPK(0,prospectus_id,company_id);
		pk.setCompany_id(company_id);
		pk.setProspectus_id(prospectus_id);
		SimulatorBean sim = new SimulatorBean();
		sim.setTitle(null);
		sim.setBc_score(null);
		sim.setAmmount(getAmmount());
		sim.setDate(new Date());
		sim.setFrequency_id(getFrequency_id());
		sim.setNum_payments(getNumCuota());
		sim.setPayment(getMontoCuota());
		sim.setPeriod_rate(getTasaPeriodo());
		sim.setPurpose_id(getPurpose_id());
		sim.setSimulatorPK(pk);
		sim.setTerm_id(getTerm_id());
		sim.setTotal_interest(getInteres());
		sim.setTotal_payment(getTotalPagar());
		sim.setType('M');
		sim.setType_id(null);
		sim.setYearly_rate(getTasaTotal());
		sim.setYearly_interest(null);
		sim.setMx_cat(getCat());
		simulatorService.add(sim);		
		*/
		
		try{
			
			prevencionld = null;
			
			if(sesion.getProspectus_id() != null )
				prevencionld = prevencionldservice.getPrevencionLDByProspectus(sesion.getProspectus_id() , sesion.getCompany_id() );
			
			if(prevencionld != null){
				
				setHasPLD(true);
				
			}else{
				
				setHasPLD(false);
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
			setHasPLD(false);
		}
		
		List<Change_control> listChangeByProspect=changeControlService.getListByChangeByProspectus(prospectus_id);
		
		//Para cuestiones de rendimiento me traigo la lista de cambios de ese prospectus.
		// Se determina que campos tuvieron cambios y no se actualizan en la tablade PLD
		
		
		//Consulta el registro PLD actual del prospecto.
		 if(listChangeByProspect!=null && listChangeByProspect.size()>0){
			 
			 if(sesion.getArea()!='I'){
				 
			   	PrevencionLDPK prevPK = new PrevencionLDPK();				
				prevPK.setCompany_id(sesion.getCompany_id());
				prevPK.setProspectus_id(sesion.getProspectus_id());				
				setPrevencionld(prevencionldservice.getSelectedPrevencionLDById(prevPK));
				
			 }
			 
		   }
		
		if(isHasPLD()){
			 if(!sesion.getArea().toString().equals("I")){
				if(listChangeByProspect!=null && listChangeByProspect.size()>0){	
					boolean hasMovementFre=false; // bandera que determina si el campo movement_frequency sufrio cambios por parte del usuario. y esta ya no se actualiza.
					boolean hasMaxDeposit=false;// bandera que determina si el campo maximum_deposit sufrio cambios por parte del usuario. y esta ya no se actualiza.
					boolean hasMaxWithdraw=false;// bandera que determina si el campo maximum_withdraw sufrio cambios por parte del usuario. y esta ya no se actualiza.
					boolean hasMaxBalance=false;// bandera que determina si el campo maximum_balance sufrio cambios por parte del usuario. y esta ya no se actualiza.
					
					for (Change_control change_iter : listChangeByProspect) {
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("movement_frequency"))
							hasMovementFre=true;
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("maximum_deposit"))
							hasMaxDeposit=true;
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("maximum_withdraw"))
							hasMaxWithdraw=true;
						if(change_iter.getAfected_table().trim().equals("gn_mx_pld") && change_iter.getField().trim().equals("maximum_balance"))
							hasMaxBalance=true;
					}
					
					if(!hasMovementFre)
						getPrevencionld().setMovement_frequency(getFrequencyName());
					if(!hasMaxDeposit)
						getPrevencionld().setMaximum_deposit(getMontoCuota());
					if(!hasMaxWithdraw)
						getPrevencionld().setMaximum_withdraw(getAmmount());
					if(!hasMaxBalance)
						getPrevencionld().setMaximum_balance(getMontoCuota());
					
					
				}else{
					
					if(getPrevencionld()==null){
						PrevencionLDPK prevPK = new PrevencionLDPK();				
						prevPK.setCompany_id(sesion.getCompany_id());
						prevPK.setProspectus_id(sesion.getProspectus_id());
						
						prevencionld = new PrevencionLD();
						prevencionld.setPk(prevPK);
					}
					
					getPrevencionld().setMovement_frequency(getFrequencyName());
					getPrevencionld().setMaximum_deposit(getMontoCuota());
					getPrevencionld().setMaximum_withdraw(getAmmount());
					getPrevencionld().setMaximum_balance(getMontoCuota());
				}
				
				getPrevencionld().setResource_origin("Del préstamo de Kubo.Financiero");
				
				prevencionldservice.updatePrevencionLD(getPrevencionld());
				
				//Para determinar si esta en la seccion de documentacion y PLD entonces hace el update sobre la seccion.
				//Si no esta en la sección de documentacion y PLD ho hace nada ya que al llegar a esta sección se actualizaran los datos.
				ELContext elContext = FacesContext.getCurrentInstance().getELContext();			
				NavigationBeanIMP navigation = (NavigationBeanIMP) FacesContext.getCurrentInstance().getApplication()
				        .getELResolver().getValue(elContext, null, "navigationBean");
				 
				 if(navigation!=null && navigation.getPaginaActual().equals("registro/documentationAndPld")){
					 try{
						 
						 AddPldDocument pldAndDoc = (AddPldDocument) FacesContext.getCurrentInstance().getApplication()
							        .getELResolver().getValue(elContext, null, "addPldDocument");
						 pldAndDoc.setPrevencionld(prevencionld);
						 RequestContext requestContext = RequestContext.getCurrentInstance();
						 
						 if( requestContext != null ){
							 requestContext.addPartialUpdateTarget("frm_questPLD");
						 }
					 
					 }catch(ManagedBeanCreationException me){
					
						 System.out.println( "No se pudo crear el managedBean AddPldDocument" );  
					 
					 }
					 
				 }
			 }
			
		}else{
			
			 if(sesion.getArea()!='I')
			 {				 
				if(getPrevencionld()==null)
				{
					
					PrevencionLDPK prevPK = new PrevencionLDPK();				
					prevPK.setCompany_id(sesion.getCompany_id());
					prevPK.setProspectus_id(sesion.getProspectus_id());
					
					prevencionld = new PrevencionLD();
					prevencionld.setPk(prevPK);
					
				}
					
					getPrevencionld().setMovement_frequency(getFrequencyName());
					getPrevencionld().setMaximum_deposit(getMontoCuota());
					getPrevencionld().setMaximum_withdraw(getAmmount());
					getPrevencionld().setMaximum_balance(getMontoCuota());
					
					try
					{
						prevencionldservice.savePrevencionLD(getPrevencionld());
					}catch(Exception e){
						
					}
					setHasPLD(true);
			 }
		}
	}
	
	public void creaTablaAmort()
	{
		Double montoTotal;
		Double interes;
		Double iva;
		Double capital;
		Double tasa;
		Double sumInteres=0d;
		Double sumIva=0d;
		Double sumCapital=0d;
		
		montoTotal = getAmmount();
		tasa = getTasaPeriodo();
		
		String res = "<div class='amort'>";
		res += "<table cellspacing='8px' cellpadding='8px'>";
		
		res += "<tr style='text-align: center'>";
		res += "<td>Número de Pago</td>";
		res += "<td>Monto de Pago</td>";
		res += "<td>Interés</td>";
		res += "<td>I.V.A.</td>";
		res += "<td>Capital</td>";
		//res += "<td>Monto Restante</td></tr>";
		
		for(int i = 1; i<=getNumCuota(); i++)
		{
			interes = (double)Math.round(((tasa)*montoTotal)*100)/100;
			iva = (double)Math.round(((getIva()*interes)/(getIva()+1))*100)/100;
			interes = interes-iva;
			capital = (double)Math.round((getMontoCuota()-(interes+iva))*100)/100;
			res += "<tr class='numero'><td>"+i+"</td>";
			res += "<td>$"+getMontoCuotaStr()+"</td>";
			res += "<td>"+formatDec(dec.format(interes))+"</td>";
			res += "<td>"+formatDec(dec.format(iva))+"</td>";
			res += "<td>"+formatDec(dec.format(capital))+"</td>";
			montoTotal = (double)Math.round((montoTotal-capital)*100)/100;
			//res += "<td>"+dec.format(montoTotal)+"</td>";
			res += "</tr>";
			sumCapital= sumCapital+capital;
			sumInteres = sumInteres+interes;
			sumIva = sumIva+iva;
		}
		
		res += "<td><b>Total</b></td>";
		res += "<td class='numero'><b>"+formatDec(dec.format((double)Math.round((sumInteres+sumIva+sumCapital)*100)/100))+"</b></td>";
		res += "<td class='numero'><b>"+formatDec(dec.format((double)Math.round(sumInteres*100)/100))+"</b></td>";
		res += "<td class='numero'><b>"+formatDec(dec.format((double)Math.round(sumIva*100)/100))+"</b></td>";
		res += "<td class='numero'><b>"+formatDec(dec.format((double)Math.round(sumCapital*100)/100))+"</b></td>";
		//res += "<td></td>";
		res += "</tr>";
		res += "</table>";
		res += "</div>";
		
		setTableAmort(res);
	}

	public void clearSim()
	{
		setAmmount(15000d);
		setAmmountStr("15,000");
		setFrequency_id(4);
		setPurpose_id(1);
		setTerm_id(12);
		setCatStr("0");
		setComisionApertura(5D);
		totalRecibir="0";
		setTasaTotal(52.6D);
	}
	
	public Double getCuotaByFormula(boolean flagIva, boolean isSafiSimulation)
	{
		setNumCuota(generaNumCuotas());
		setTasaPeriodo(generaTasaPeriodo(flagIva));
		Double intper = getTasaPeriodo();
		//System.out.println( getNumCuota()+"  --  "+ intper);
		
		Double num = (Math.pow((1+intper), getNumCuota()))*intper;
		Double den = (Math.pow((1+intper), getNumCuota()))-1;
		Double montoAPagar = getAmmount()*(num/den);
//		System.out.println("Monto: "+getAmmount());
//		System.out.println("Monto Cuota1: "+montoAPagar);
		
		Double payment = Math.ceil(montoAPagar);
		
		setMontoCuota(payment);
//		System.out.println("Monto Cuota2: "+getMontoCuota());
		setTotalPagar((double)Math.round((montoAPagar*getNumCuota())*100)/100);
//		System.out.println("Total a pagar: "+getTotalPagar());
		setInteres(generaInteres());
		
		calculaCat(isSafiSimulation );
		
		return (double)Math.round(montoAPagar*100)/100;
		
	}
	
	public void calculaCat( boolean isSafiSimulation ){
		
		String valorCuotas = "";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		elContext = facesContext.getELContext();
		resolver  = facesContext.getApplication().getELResolver();
		
		TablaAmortizacion amort = (TablaAmortizacion)resolver.getValue(elContext, null, "tablaAmortizacion");;
				
		amort.setAmmount(getAmmount() );
		amort.setTerm_id(	getTerm_id() ) ;
		amort.setRate(getTasaTotal() );
				
		
		if( !getMontoCuotaStr().equals("No disponible") ){
			amort.setdPayment( Double.parseDouble( getMontoCuotaStr().replace("$", "").replace(",", "") ) );
		}else{
			amort.setdPayment( Double.parseDouble( "0" ) );
		}
		if( !getTotalPagarStr().equals("No disponible") ){
			amort.setTotalPayment( Double.parseDouble(  getTotalPagarStr().replace("$", "").replace(",", "") ) );
		}else{
			amort.setTotalPayment( Double.parseDouble( "0" ) );
		}	
		amort.setFreq(	getFrequencyStr() );
		amort.setCat( "" );
		amort.setComision( (getComisionApertura() +"").replace("$", "").replace(",", "")  );
		
		amort.setSafiSimulation( isSafiSimulation ); //*************************************
		
		amort.calculaTabla();
		int x = 0;
		for(Amortization a : amort.getTamort()){
			
			if(x != 0)
				valorCuotas += ",";
			
			x++;
			
			Double interes =  Double.parseDouble( a.getInterest().replace("$", "").replace(",", "") );
			
			Double interesSinIva = interes/(1.16);
			
			interesSinIva = Double.parseDouble( (dec.format(interesSinIva)).replace("$", "").replace(",", "") );
			
			Double capital = Double.parseDouble( a.getCapital().replace("$", "").replace(",", "") );
			
			valorCuotas += (capital+interesSinIva);
			
		}
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		Double catRes =  simulatorService.getCatBySafi( Double.parseDouble(  getTotalRecibir().replace("$", "").replace(",", "") ), valorCuotas, getDiasFreq());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date());
		
		long difCns_R_2 = cal2.getTimeInMillis() - cal1.getTimeInMillis();
		 System.out.println("Tiempo en calcular el  cat: "+difCns_R_2+" milisegundos");
		
		 
		 catRes = (double)Math.round((catRes)*10)/10;
		 
		System.out.println("Resultado Cat: "+catRes);
		
		setCat(catRes);
		
	}
	
	public void simulaLst()
	{
		lstSim = new ArrayList<SimBeanForList>();
		
		lstSim.clear();
		if(sesion.getRate()!=null){
			
			setTasaTotal(sesion.getRate());
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(new Date());
			
			simulaCred(false);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			
			long difCns_R_2 = cal2.getTimeInMillis() - cal1.getTimeInMillis();
			 System.out.println("Tiempo en hacer la simulacion: "+difCns_R_2+" milisegundos");
			
			
			//getCuotaByFormula(true);
			
			SimBeanForList sim = new SimBeanForList(); 
			
			sim.setAmmountStr(getAmmountStr());
			sim.setFrequencyStr(getFrequencyStr());
			sim.setMontoCuotaStr(getMontoCuotaStr());
			sim.setNumCuota(getNumCuota()+"");
			sim.setTasaTotal(getTasaTotal()+"");
			sim.setTotalPagarStr(getTotalPagarStr());
			sim.setCatStr(getCatStr());
			sim.setComisionApertura(getComisionApertura());
			sim.setTotalRecibir(getTotalRecibir());
			lstSim.add(sim);
			
		}else{
			
			for(int x = 0; x<3 ; x++){
				SimBeanForList sim = new SimBeanForList(); 
				switch(x){
					case 0:
//						setTasaTotal(23.9D);
						setTasaTotal(17.9D);
						sim.setKuboScoreLetter("A");
						break;
					case 1:
						setTasaTotal(32.52D);
						sim.setKuboScoreLetter("C");
						break;
					case 2:
						setTasaTotal(52.6D);
						sim.setKuboScoreLetter("E");
						break;
				}
				
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(new Date());
				
				simulaCred(false);
				
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());
				
				long difCns_R_2 = cal2.getTimeInMillis() - cal1.getTimeInMillis();
				 System.out.println("Tiempo en hacer la simulacion: "+difCns_R_2+" milisegundos");
				
				
				
				sim.setAmmountStr(getAmmountStr());
				sim.setFrequencyStr(getFrequencyStr());
				sim.setMontoCuotaStr(getMontoCuotaStr());
				sim.setNumCuota(getNumCuota()+"");
				sim.setTasaTotal(getTasaTotal()+"");
				sim.setTotalPagarStr(getTotalPagarStr());
				sim.setCatStr(getCatStr());
				sim.setComisionApertura(getComisionApertura());
				sim.setTotalRecibir(getTotalRecibir());
				lstSim.add(sim);
				
			}
			
			simulador_A = lstSim.get(0);
			
			simulador_C = lstSim.get(1);
			
			simulador_E = lstSim.get(2);
			
		}				
	}
}