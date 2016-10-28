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
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import mx.com.kubo.bean.Amortization;
import mx.com.kubo.bean.SimBeanForList;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.registro.documentacion.AddPldDocument;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulationConfig;
import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.model.Simulation_Cache_PK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SlideEndEvent;

import com.sun.faces.mgbean.ManagedBeanCreationException;

import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;

@ManagedBean @SessionScoped
public class Simulator extends SimulatorDMO
implements Serializable
{	
	private FacesContext faces;
	private ELContext    elContext;
	private ELResolver   resolver;
	
	private ProyectLoan      proyectLoan;
	private MembershipPK     membershipPK;
	private Membership       membership;
	private SimulationConfig simulationConfig;
	
	private List<Frequency> listFrequencyTmp;
	
	private String ammountStr   = "50,000";
	private String totalRecibir = "0";	
	private String purposeName;
	private String frequencyName;
	private String guardarAction;
	private String mailAction;
	
	private SimBeanForList simulador_A;
	private SimBeanForList simulador_C;
	private SimBeanForList simulador_E;
	
	private Double comisionApertura = 5D;
	
	private int frequency_id = 4;
	private int purpose_id;
	
	public boolean flagSaveSimulationCache = false;
		
	private static final long serialVersionUID = 1L;
	
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
		   
		   if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null)
		   {			  
			   if(sesion.getArea() != null && (sesion.getArea()=='M' || sesion.getArea()=='I'))
				{
					
				   area = sesion.getArea().toString();
				   
				} else {
					
					if(sesion.getArea() != null)
					{
						area = sesion.getArea().toString();
					}
					
					proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
					
					if(proyectLoan != null)
					{						
						setLoanTypeID(proyectLoan.getLoan_type());
						
						if(proyectLoan.getProyect().getPartner_id() != null && partnerID == null)
						{
							partnerID =	proyectLoan.getProyect().getPartner_id().toString();
						}
							
					} else {
						
						membershipPK = new MembershipPK(sesion.getProspectus_id(), sesion.getCompany_id());
						
						membership = membershipservice.getMembershipById(membershipPK);
						
						if(membership.getRegistration_reason() != null)
						{
							partnerID = membership.getRegistration_reason().getPartner_id();
						}					
					}
					
					if(sesion != null && sesion.getPartner() != null && partnerID == null)
					{
						partnerID = "BRD";
					}						
				}
			   
		   	} else if(sesion != null && sesion.getPartner() != null) {
				
		   		
		   		if(sesion.getPartner().equals("10000034") || sesion.getPartner().equals("ALIANZA BARARED")){
		   		
		   			partnerID = "BRD";
		   		
		   		}else{
		   			partnerID = sesion.getPartner();
		   		}
			   
			}
		   
		   System.out.println( "\n\n******* SimulationConfig Partner: "+sesion.getPartner()+" *******\n\n" );

			simulationConfig = new SimulationConfig();
			
			if(area == null)
			{
				area = "L";
				
				if(partnerID == null)
				{
					if( sesion == null || sesion.getCompany_id() == null)
					{
						simulationConfig = simulationConfigService.getSimulationByArea(area, 1);
					} else {
						simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
					}
					
				} else {
					
					if( sesion == null || sesion.getCompany_id() == null)
					{
						simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID,area, 1);
					} else {
						simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID,area, sesion.getCompany_id());
					}
				}
				
				area = null;
				
			} else if(getLoanTypeID() != null && partnerID != null && area != null){
					
				simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandPartnerIDandArea(getLoanTypeID(), partnerID, area, sesion.getCompany_id());
				
				if( simulationConfig == null )
				{				
					simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID, area, sesion.getCompany_id());					
				}
				
				if( simulationConfig == null )
				{				
					simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandArea(getLoanTypeID(), area, sesion.getCompany_id());					
				}
				
			} else if(getLoanTypeID() != null && area != null){
					
				simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandArea(getLoanTypeID(), area, sesion.getCompany_id());
					
			} else if(partnerID != null && area != null){
					
				simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID, area, sesion.getCompany_id());
				
			} else if(area != null){
					
				simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
				
			} else {
				
				simulationConfig = simulationConfigService.getSimulationByArea("L", 1);
			}
			
			if(simulationConfig == null)
			{			
				area = "L";
				
				if( sesion == null || sesion.getCompany_id() == null)
				{
					simulationConfig = simulationConfigService.getSimulationByArea(area, 1);
					
				} else {
					
					simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
				}
				
				area = null;				
			}
		   			
			if(simulationConfig != null)
			{
				setMontoMax(""+simulationConfig.getMax_amount());
				setMontoMin(""+simulationConfig.getMin_amount());
				setTermMax(""+simulationConfig.getMax_term());
				
			} else {
				
				setMontoMax("50000");
				setMontoMin("5000");
				setTermMax("18");
			}
		
		
			listPurpose = purposeService.getPurposeList();
			
			listFrequencyTmp = frequencyService.getFrequencyList();
			
			listFrequency = new ArrayList<Frequency>();
			for(Frequency ls :listFrequencyTmp ){
				switch (ls.getFrequencyPK().getFrequency_id()){
					
					case 1://Semanal
						ls.setName("Semanas");
						break;
						
					case 2: //Catorcenal
						ls.setName("Catorcenas");
						break;
						
					case 3: //Quincenal
						ls.setName("Quincenas");
						break;
						
					case 4: //Mensual
						ls.setName("Meses");
						break;
						
				}
				listFrequency.add(ls);
			}
		//simulaCred();
		
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
			
		
	}

	public int getPurpose_id() 
	{
		return purpose_id;
	}

	public void setPurpose_id(int purpose_id) 
	{
		this.purpose_id = purpose_id;
		
		if(purpose_id != 0)
		{
			initPurpose(purpose_id);
		}else{
			setPurpose( new Purpose() );
		}
		
		setPurposeName("");
	}

	public int getFrequency_id() {
		return frequency_id;
	}

	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
		if(frequency_id!=0)
			initFrequency(frequency_id);
		setFrequencyName("");
	}

	private void initFrequency(int frequency_id)
	{
		setFrequency(frequencyService.getFrequencyById(frequency_id));
	}
	
	private void initPurpose(int purpose_id){
		
		setPurpose(purposeService.getPurposeById(new PurposePK(purpose_id, 1)));
	}

	public String getFrequencyName() {
		
		int freq = getFrequency_id();
		switch (freq){
			case 1://Semanal
				frequencyName="Semanal";
				break;
			case 2: //Catorcenal
				frequencyName="Catorcenal";
				break;
			case 3: //Quincenal
				frequencyName = "Quincenal";
				break;
			case 4: //Mensual
				frequencyName = "Mensual";
				break;
		}
		
		
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = getFrequency().getName();
	}
	
	public String getPurposeName() {
		return purposeName;
	}

	public String getInteresStr() 
	{
		return formatDec(dec.format(getInteres()));
	}
	
	public void setPurposeName(String purposeName) {
		this.purposeName = getPurpose().getName();
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
	
	public void onFreqChange(ValueChangeEvent ce) {  
		
		if(ce.getNewValue()!=null){
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

	public String getAmmountStr() {
		
		ammountStr = formatDec(dec.format(this.ammount));
		ammountStr = ammountStr.split("\\.")[0];
		return ammountStr;
	}

	public void setAmmountStr(String ammountStr) {
		this.ammountStr = ammountStr;
		setAmmount(Float.parseFloat(ammountStr.replaceAll(",", "")));
	}
	
	public String getTotalPagarStr() 
	{
		if(getTotalPagar() != null && getTotalPagar() > 0)
		{
			return formatDec(dec.format(getTotalPagar()));
			
		} else {
			
			return "No disponible";
		}
	}
	
	public String getMontoCuotaStr() 
	{
		if(getMontoCuota() != null && getMontoCuota() > 0)
		{
			return formatDec(dec.format(getMontoCuota()));
		} else {
			return "No disponible";
		}
	}

	public void simulaCred(boolean isSafi)
	{
		if( sesion.getArea() != null && sesion.getArea().toString().equals("L"))
			proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		
		setActualRate();
		setNumCuota(generaNumCuotas());
		setTasaPeriodo(generaTasaPeriodo(true));
		if(isSafi){
			generaMontoCuota2();
		}else{
			getCuotaByFormula(true,isSafi);
		}
		setInteres(generaInteres());
		/*setIvaInteres(generaIva(getInteres(),getIva()));*/
		//setTotalPagar(generaTotal());
		if(flagSaveSimulationCache){
			saveSimulatorCache();
		}
		
		if(isConnected() && !getTotalPagarStr().equals("No disponible"))
		{
			saveSimulator();
						
			SimulatorPK pk    = new SimulatorPK(0, sesion.getProspectus_id(), sesion.getCompany_id());			
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
			
			if(isSafi){
				sim.setOrigin("S");
			}else{
				sim.setOrigin("K");
			}
			
			try{
				simulatorService.add(sim);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if (proyectLoan != null && proyectLoan.getStatus_id() == 0)
			{
				if(proyectLoan.getStatus_id() != null && proyectLoan.getStatus_id() == 0)
				{
					if(proyectLoan.getOpening_commission() != null)
					{
						double cuota_simulador    = getMontoCuota();
						double cuota_proyect_loan = proyectLoan.getPayment();
						double cuota_apertura     = getAmmount() * proyectLoan.getOpening_commission() / 100;
					
						if(cuota_simulador != cuota_proyect_loan)
						{
							proyectLoan.setAmmount(getAmmount());
							proyectLoan.setMin_ammount(getAmmount());
							proyectLoan.setMx_cat(getCat());
							proyectLoan.setTerm_id(getTerm_id());
							proyectLoan.setFrequency_id(getFrequency_id());
							proyectLoan.setPayment(getMontoCuota());
							
							proyectLoan.setOpening_commission_amount(cuota_apertura);
							
							if( proyectLoanService.update(proyectLoan) )
							{
								System.out.println("Simulator.simulaCred(): OK");
							} else {
								
								System.out.println("Simulator.simulaCred(): ERROR al actualizar proyect_loan");
							}
							
							if(purpose_id != 0){
								
								Proyect proyect = proyectLoan.getProyect();
								
								proyect.setPurpose_id(purpose_id);
								
								proyectService.update(proyect);
								
							}
							
							proyectLoan = proyectLoanService.getProyectLoanByProyectLoanID( proyectLoan.getProyectloanPk().getProyect_loan_id(), proyectLoan.getProyectloanPk().getProspectus_id(), proyectLoan.getProyectloanPk().getCompany_id());
							
						}
					}
				
				}
				
			}
			
		} else {//Guarda en la tabla de comparaciones a los status anonimos. Prospectus_id = 0;
			SimulatorPK pk    = new SimulatorPK(0, 0, 1);			
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
			
			if(isSafi){
				sim.setOrigin("S");
			}else{
				sim.setOrigin("K");
			}
			try{
					simulatorService.add(sim);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		if(requestContext != null)
		{
			requestContext.addCallbackParam("valor", true);
		}
	}
	
	private Double generaInteres(){
		Double i = ((getMontoCuota()*getNumCuota())-getAmmount());
		double interes = i/(1+getIva());
		double iva = (double)Math.round((i - interes)*100)/100;
		setIvaInteres(iva);
		return (double)Math.round(interes*100)/100;
	}
	
	
//	private Double generaTotal(){
//		return (double)Math.round((getAmmount()+getInteres()+getIvaInteres())*100)/100;
//	}
	
	public Integer generaNumCuotas(){
		int freq = getFrequency_id();
		int num = getTerm_id();
		switch (freq){
			case 1://Semanal
				//num= (int)Double.parseDouble(Math.rint((getTerm_id()*52)/12)+"");
				setFrequencyAnual(52);
				setFrequencyStr("semanales");
				setFrequencyStr2("semanas");
				setFrequencyName("Semanal");
				setDiasFreq(7);
				setFreqStr("S");
				break;
			case 2: //Catorcenal
				//num= (int)Double.parseDouble(Math.rint((getTerm_id()*26)/12)+"");
				setFrequencyAnual(26);
				setFrequencyStr("catorcenales");
				setFrequencyStr2("catorcenas");
				setFrequencyName("Catorcenal");
				setDiasFreq(14);
				setFreqStr("C");
				break;
			case 3: //Quincenal
				//num= getTerm_id()*2;
				setFrequencyAnual(24);
				setFrequencyStr("quincenales");
				setFrequencyStr2("quincenas");
				setFrequencyName("Quincenal");
				setDiasFreq(15);
				setFreqStr("Q");
				break;
			case 4: //Mensual
				//num= getTerm_id();
				setFrequencyAnual(12);
				setFrequencyStr("mensuales");
				setFrequencyStr2("meses");
				setFrequencyName("Mensual");
				setDiasFreq(30);
				setFreqStr("M");
				break;
		}
		return num;

	}
	
	public void generaMontoCuota2(){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		
		String thisFechaInicio = format.format(new Date());
		setFechaInicio(formatStr.format(new Date()));
		
		Simulation_Cache simulationCache = null;
		//Consulta el servicio solo si es Semanal o Catorcenal
		if(getFrequency_id() ==1 || getFrequency_id() == 2)
			simulationCache = simulationCacheService.getMaxByAmmountRateTermFrequency(getAmmount(), getTasaPeriodo(), getTerm_id(), getFrequency_id(), 1);
		
		
		if(simulationCache == null){
		
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
			try{
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
	
	private void saveSimulatorCache(){
		try{
			
			Simulation_Cache_PK simulationCachePK = new Simulation_Cache_PK();
			Simulation_Cache simulationCache = new Simulation_Cache();
			
			simulationCachePK.setCompany_id(1);
			
			simulationCache.setPk(simulationCachePK);
			simulationCache.setAmmount(getAmmount());
			simulationCache.setTerm_id(getTerm_id());
			simulationCache.setPayment(getMontoCuota());
			simulationCache.setFrequency_id(getFrequency_id());
			simulationCache.setNum_payments(getNumCuota());
			simulationCache.setTotal_payment(getTotalPagar());
			simulationCache.setPeriod_rate(getTasaPeriodo());
			simulationCache.setYearly_rate(getTasaTotal());
			simulationCache.setMx_cat(getCat());
			simulationCache.setCreation_date(new Date());
			
			simulationCacheService.add(simulationCache);
			
		}catch(Exception e){
			e.printStackTrace();
		}
				
		
	}

	private Double generaTasaPeriodo(boolean flagIva){
		/*
		double ta = getTasaTotal();
		double n = Double.parseDouble(getFrequencyAnual()+"");
		//return (double)Math.round((ta/n)*100)/100;
		double tp = 0D;
		if(flagIva){
			tp = (double)Math.round((((ta/100)/n)*(1+getIva()))*10000)/10000;
		}else{
			tp = (double)Math.round((((ta/100)/n))*10000)/10000;
		}
		setTasaPeriodoPorc((double)Math.round((tp*100)*100)/100);
		return tp;
		*/
		
		double taP = getTasaTotal()/100;
		double tp = 0D;
		
		if(flagIva){
			
			tp = (double)Math.round(((taP/360)*(1+getIva()))*100000000)/100000000;
			
		}else{
			
			tp = (double)Math.round((taP/360)*100000000)/100000000;
			
		}
		
		return tp*getDiasFreq();
		
	}

	
	/*private Double generaTEA(){
		Double t = getTasaTotal();
		double n = Double.parseDouble(getFrequencyAnual()+"");
		Double t1 = (double)Math.round((t/(n))*100)/100;
		Double t2 = Math.pow((1+t1), (1/n));
		
		Double t3 = (t2-1)*100;
		
		Double t4 = (double)Math.round(t3*100)/100;
		return t4;
	}*/
	
	public void saveSimulator(){
		
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
	
	public void creaTablaAmort(){
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
		
		for(int i = 1; i<=getNumCuota(); i++){
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

	public void clearSim(){
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
	
	public void sendMail()
	{
		
	}

	public Double getCuotaByFormula(boolean flagIva, boolean isSafiSimulation){
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
	
	public String getGuardarAction() {
		
		if(isConnected())
			guardarAction = "<a id='save' href='#'>Guarda tu simulación para próximas visitas (reg).</a>";
		else{
			guardarAction = "<a id='inlineSave' href='#formRegistro'>Guarda tu simulación para próximas visitas.</a>";
		}
		return guardarAction;
	}

	public void setGuardarAction(String guardarAction) {
		this.guardarAction = guardarAction;
	}

	public String getMailAction() {
		if(isConnected())
			mailAction = "<a id='send' href='#formRegistro'>Enviamos tu simulación por mail (reg).</a>";
		else{
			mailAction = "<a id='inlineMail' href='#formRegistro'>Enviamos tu simulación por mail.</a>";
		}
		return mailAction;
	}

	public void setMailAction(String mailAction) {
		this.mailAction = mailAction;
	}
	
	private boolean isConnected(){
		
		if(sesion.getProspectus_id()!=null&&sesion.getProspectus_id()>0)
			return true;
		else
			return false;
		
	}

	private String formatDec(String valor)
	{
		String res;
		valor = valor.replace("$", "");
		
		String[] arrayValor = valor.split("\\.");
		
		if(arrayValor.length < 2)
		{
			//res=valor+".00";
			res = valor;
			
		} else {
			
			if(arrayValor[1].length() < 2)
			{
				arrayValor[1]= arrayValor[1] + "0";
			}
			
			res = arrayValor[0]+"."+arrayValor[1];
		}
		
		return res;
	}
	
	public void simulaLst(){

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

	public String getIvaInteresStr() 
	{
		return formatDec(dec.format(getIvaInteres()));
	}
	
	public String getTotalRecibir() {
		
		Double d =  getAmmount() - (getAmmount() * (getComisionApertura()/100));
		
		d = (double) Math.round(d*100)/100;
		
		totalRecibir = dec.format(d);
		
		return totalRecibir;
	}

	public void setTotalRecibir(String totalRecibir) {
		this.totalRecibir = totalRecibir;
	}

	public Double getComisionApertura() {
		
		if(sesion.getOpeningCommission()!=null){
			comisionApertura = sesion.getOpeningCommission();
		}
		
		return comisionApertura;
	}

	public void setComisionApertura(Double comisionApertura) {
		this.comisionApertura = comisionApertura;
	}
	
	public boolean isClientePriceShoes(){
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id(sesion.getCompany_id());
		mpk.setProspectus_id(sesion.getProspectus_id());
		
		Membership member =  membershipservice.getMembershipById(mpk);
		
		if( member.getRegistration_reason_id() != null && member.getRegistration_reason_id() == 8 ){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	private void setActualRate(){
		if(isConnected() && sesion.getArea().toString().equals("L"))
		{
			ProyectLoan proyectLoan=proyectLoanService.getMaxProyectLoanByProspect(sesion.getProspectus_id(),sesion.getCompany_id());
			
			if(proyectLoan!=null && proyectLoan.getScoring()!=null && proyectLoan.getScoring().getRate()!=null && proyectLoan.getScoring().getStatus()!=null && proyectLoan.getScoring().getStatus()==1  )
			{
				sesion.setRate(proyectLoan.getScoring().getRate());
			}
		}
	}

	public void setSimulador_A(SimBeanForList simulador_A) {
		this.simulador_A = simulador_A;
	}

	public void setSimulador_C(SimBeanForList simulador_C) {
		this.simulador_C = simulador_C;
	}

	public void setSimulador_E(SimBeanForList simulador_E) {
		this.simulador_E = simulador_E;
	}
	
	public SimBeanForList getSimulador_A() {
		return simulador_A;
	}

	public SimBeanForList getSimulador_C() {
		return simulador_C;
	}

	public SimBeanForList getSimulador_E() {
		return simulador_E;
	}
	
}