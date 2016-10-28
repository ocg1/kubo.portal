package mx.com.kubo.managedbeans.registro.publicacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.xml.rpc.ServiceException;

import mx.com.kubo.bean.DealBean;
import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;

public abstract class DealAMO extends DealDMO  
{		
	protected boolean asignarMenu()
	{
		flag             = false;
		listRequiredMenu = new ArrayList<MenuRegBean>();
		recurso          = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		for(AccessCollector access : menuIncomplete)
		{				
			if(access != null 
			&& access.getPercentage() != null 
			&& access.getPercentage() < 100 
			&& access.getScreen_id()  != 6)
			{						
				menureq = new MenuRegBean();
				
				menureq.setTargetItem(access.getName());
				menureq.setScreenid(access.getScreen_id());
				menureq.setIdItem("menu"+access.getMenu_order());
				menureq.setNameItem(recurso.getString(access.getResource_name()));
				
				listRequiredMenu.add(menureq);
				
				flag = true;
				
			} else if(access.getPercentage() == null && access.getScreen_id() != 6) {
				menureq = new MenuRegBean();
				
				menureq.setTargetItem(access.getName());
				menureq.setScreenid(access.getScreen_id());
				menureq.setIdItem("menu" + access.getMenu_order());
				menureq.setNameItem(recurso.getString(access.getResource_name()));
				
				flag = true;
				
				listRequiredMenu.add(menureq);
			}
		}
		
		return flag;
	}
	
	protected void asignarClabeAccount(int prospectus_id, int company_id)
	{
		if(accountList != null && accountList.size() > 0)
		{
			
			setHasAccount(true);
			setAccountDisp("block");
			setSelAccount("1");
			thisClabe = accountList.get(0);
			
		} else {
			
			setHasAccount(false);
			setAccountDisp("none");
			setSelAccount("0");
			
			thisClabe = new ClabeAccount();
			
			ClabeAccountPK clabepk = new ClabeAccountPK();
			clabepk.setCompany_id(company_id);
			clabepk.setProspectus_id(prospectus_id);
			
			thisClabe.setClabepk(clabepk);					
		}
	}
	
	protected void asignarDatosSimulacion(int prospectus_id, int company_id, double rate, Simulator simulator)
	{
		sim  = simulatorService.getMaxSimulationProspect(prospectus_id, company_id) ;
		
		if(sim != null)
		{
			setAmmount(sim.getAmmount());
			setFrequency_id(sim.getFrequency_id());
			setTerm_id(sim.getTerm_id());
			setAmountSel( num.format(sim.getAmmount() )); 
			
			setAmountSel( num.format( getAmmount() ));
			setTermSel(sim.getTerm_id());
			setFrequencySel(sim.getFrequency_id());
			
			
			setAmmount(sim.getAmmount());
			setFrequency_id(getFrequencySel());
			setTerm_id(getTermSel());
			
		//**
			try{
				simulaDeal();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void listener_ammount_simulation_deal( AjaxBehaviorEvent e )
	{
		
		String ammountStr = (String)((HtmlInputText) e.getComponent()).getValue();
		
		setAmountSel(ammountStr);
		
		realizaSimulacionDeal();
	}
	
	public void listener_term_simulation_deal( AjaxBehaviorEvent e )
	{
		
		Integer ammountStr = (Integer)((HtmlInputText) e.getComponent()).getValue();
		
		System.out.println("TermSel: "+ammountStr);
		
		setTermSel(  ammountStr );
		
		realizaSimulacionDeal();
	}
	
	public void listener_frequency_simulation_deal( AjaxBehaviorEvent e )
	{
		
		Integer ammountStr = (Integer)((HtmlSelectOneMenu) e.getComponent()).getValue();
		
		System.out.println("frequencySel: "+ammountStr);
		
		setFrequencySel(  ammountStr );
		
		realizaSimulacionDeal();
	}
	
	public void realizaSimulacionDeal( )
	{
		
		System.out.println("Monto Seleccionado: " 		+ getAmountSel());
		System.out.println("Plazo Seleccionado: " 		+ getTermSel());
		System.out.println("Frecuencia Seleccionado: " 	+ getFrequencySel());
		
		Double amm = 0D;
		
		if( getAmountSel() != null && getAmountSel().trim().length()>0 ){
			amm = Double.parseDouble(getAmountSel().replace("$", "").replace(",", "").replace(",", ""));
		}
		
			setAmmount(amm);
			setFrequency_id(getFrequencySel());
			setTerm_id(getTermSel());
			sim.setAmmount(amm);
		//**
			try{
				simulaDeal();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		
	}
	
	private void simulaDeal()  throws ServiceException{
		
		setTasaTotal(rate);			
		setPurpose_id(sim.getPurpose_id());
		
		simulator.simulaCred(true); //true: simulacion mediante Safi;  false: simulacion mediante Fórmula Kubo 
		simulator.setAmmount(getAmmount());
		simulator.setFrequency_id(getFrequency_id());
		simulator.setTerm_id(getTerm_id());
		locator   = new SAFIServiciosServiceLocator();
		service   = locator.getSAFIServiciosSoap11();
		
		simulador = new SimuladorCuotaCreditoRequest();
			
		String frecuencia     = "";
		String montoSolici    = "";
		String plazo          = "";
		String tasaAnualizada = "";
		
		format    = new SimpleDateFormat("yyyy-MM-dd");					
		formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
				
		setFechaInicio(format.format(new Date()));
							
		frecuencia  = getFrecuencia(getFrequency_id());
		montoSolici = getAmmount()+"";
		plazo       = getTerm_id()+"";
							
		simulador.setFechaInicio(getFechaInicio());
		simulador.setFrecuencia(frecuencia);
		simulador.setMontoSolici(montoSolici);
		simulador.setPlazo(plazo);					
		simulador.setAjustarFecVen("N");					
		
		String montoComision = getMontoComision(score,sim.getAmmount());
		
		if(getFormaPagoCom().equals("T"))
		{
			//montoComision = "0";
			tasaAnualizada = generateRateDif(sim.getYearly_rate());
			simulador.setComisionApertura("0");
			simulador.setFormaCobroComAp("D");
		}
		
		if(getFormaPagoCom().equals("D"))
		{						
			tasaAnualizada = sim.getYearly_rate() + "";
			simulador.setComisionApertura(montoComision);
			simulador.setFormaCobroComAp("D");						
		}
							
		simulador.setTasaAnualizada(tasaAnualizada);
		
		setFechaInicio(formatStr.format(new Date()));
				
		System.out.println("*******************************************************************");
		System.out.println("frechaInicio: "+ getFechaInicio());
		System.out.println("frecuencia: "+frecuencia);
		System.out.println("montoSolici: "+montoSolici);
		System.out.println("plazo: "+plazo);
		System.out.println("tasaAnualizada: "+tasaAnualizada);
		System.out.println("*******************************************************************");
		
		srvCall = new ServiceCalling();
		
		srvCall.setAcces_datetime(new Date());
		srvCall.setCompany_id(company_id);
		srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito");
		srvCall.setProspectus_id(prospectus_id);
		srvCall.setStatus(1);
		
		servicecallingRepository.saveServiceCall(srvCall);										
		
		try
		{						
			response =  service.simuladorCuotaCredito(simulador);
			setDisplayBtnDeal(true);
			
		} catch(Exception e) {
			
			response = new SimuladorCuotaCreditoResponse();
			response.setCodigoRespuesta("Servicio no disponible");
			response.setMensajeRespuesta("Servicio no disponible");
			setDisplayBtnDeal(false);					
		}
		
		if(response.getCodigoRespuesta().equals("0"))
		{						
			srvCall = new ServiceCalling();

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito: "+response.getMensajeRespuesta());
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(2);
			
			servicecallingRepository.saveServiceCall(srvCall);
														
		} else {
			
			srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			srvCall.setException(response.getMensajeRespuesta());
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(3);
			
			servicecallingRepository.saveServiceCall(srvCall);						
		}
		
		System.out.println("*******************************************************************");
		System.out.println("CAT: "+response.getCat());
		System.out.println("CodigoRespuesta: "+ response.getCodigoRespuesta());
		System.out.println("MensajeRespuesta: "+response.getMensajeRespuesta());
		System.out.println("MontoCuota: "+response.getMontoCuota());
		System.out.println("NumeroCuotas: "+response.getNumeroCuotas());
		System.out.println("TotalPagar: "+response.getTotalPagar());
		System.out.println("*******************************************************************");
	
		//dealList = new ArrayList<DealBean>();
	
		selectedDeal = new DealBean();
		
		selectedDeal.setTitle("Propuesta ");
		selectedDeal.setAmountStr("$"+simulator.getAmmountStr());
		selectedDeal.setFeeStrVal("$"+response.getMontoCuota());
		selectedDeal.setFee(response.getMontoCuota()==null?"No disponible":response.getMontoCuota());
		selectedDeal.setFrequencyStr(simulator.getFrequencyName());
		selectedDeal.setTermStrVal(simulator.getTerm_id()+"");
		selectedDeal.setTermStrDesc(simulator.getFrequencyStr2());
		selectedDeal.setTotalStr("$"+response.getTotalPagar()==null?"No disponible":response.getTotalPagar());
		selectedDeal.setTotalCuotas(""+response.getNumeroCuotas()==null?"No disponibe":response.getNumeroCuotas());
		selectedDeal.setCat(""+response.getCat()==null?"No disponible":response.getCat());
		selectedDeal.setMontoComision("$"+num.format((Double.parseDouble(montoComision))));
							
		if(response.getCat() != null)
		{
			selectedDeal.setCatStr(((double)Math.round((Double.parseDouble(response.getCat()))*10)/10)+"%");
		} else {
			selectedDeal.setCatStr("No disponible");
		}
		
		selectedDeal.setFrequency_Id(simulator.getFrequency_id());
		selectedDeal.setTerm_Id(simulator.getTerm_id());
		
		if(tasaAnualizada.split("\\.").length > 1)
		{						
			String t =  tasaAnualizada.split("\\.")[1];
			
			if(t.length() < 2)
			{
				tasaAnualizada = tasaAnualizada+"0";
			}
			
		} else {
			tasaAnualizada = tasaAnualizada+".00";
		}
		
		selectedDeal.setRate(tasaAnualizada+"%");
		selectedDeal.setProspectus_id(prospectus_id + "");
		selectedDeal.setCompany_id(company_id + "");
		selectedDeal.setProyect_id("");
		selectedDeal.setProyect_loan_id("");
		
		if(getFormaPagoCom().equals("D"))
		{
			String amount   = selectedDeal.getAmountStr().replace("$", "").replace(",","").replace(" ", "");
			String comision = selectedDeal.getMontoComision().replace("$", "").replace(",","").replace(" ", "");
			
			Double d = Double.parseDouble(amount) - Double.parseDouble(comision);
			d = ((double)Math.round(d*100)/100);
			
			String str = num.format(d);
			
			selectedDeal.setAmountReal("$" + str);
			
		} else {
			selectedDeal.setAmountReal(selectedDeal.getAmountStr());
		}
		
		selectedDeal.setFeeStrDesc(generaFrequenciaStr());
		
		//dealList.add(deal);
	
		
	}
	
	
	protected final void mostrar_solicitud_completa()
	{
		setDisplayBtnDeal(true);						
		setBeginDisp(false);
		setContResp(true);
		setErrorDisp(false);											
		setErrorDesc("");						
		setSuccessDisp(true);
		setProyecto_rechazado_automaticamente(false);
	}
	
	protected final void mostrar_proyecto_desistido()
	{
		setDisplayBtnDeal(true);						
		setBeginDisp(false);
		setContResp(false);
		setErrorDisp(false);											
		setErrorDesc("");						
		setSuccessDisp(false);
		setProyecto_cancelado(false);
		setProyecto_pospuesto(false);
		setProyecto_desistido(true);
		setProyecto_rechazado_automaticamente(false);
	}
	
	protected final void mostrar_proyecto_pospuesto()
	{
		setDisplayBtnDeal(true);						
		setBeginDisp(false);
		setContResp(false);
		setErrorDisp(false);											
		setErrorDesc("");						
		setSuccessDisp(false);
		setProyecto_cancelado(false);
		setProyecto_desistido(false);
		setProyecto_pospuesto(true);
		setProyecto_rechazado_automaticamente(false);
	}
	
	protected final void mostrar_proyecto_rechazado_automaticamente(  )
	{
		Calendar c1 =  Calendar.getInstance();
		c1.setTime(proyect_loan_reasignable.getConsulting_date());
		
		Calendar c2 =  Calendar.getInstance();
		c2.setTime(new Date());
		
		Long l = c2.getTimeInMillis() - c1.getTimeInMillis();
		
		Double diffDays    =(Double) ( l / (double)  (24 * 60 * 60 * 1000) );	
		
		SystemParamPK pk = new SystemParamPK();
		
		pk.setCompany_id(1);
		pk.setSystem_param_id(65);
		
		SystemParam sys =  system_param_service.loadSelectedSystemParam(pk);
		
		if(sys != null && sys.getValue() != null && Integer.parseInt( sys.getValue() ) < diffDays ){
		
			setDisplayBtnDeal(true);						
			setBeginDisp(false);
			setContResp(false);
			setErrorDisp(false);											
			setErrorDesc("");						
			setSuccessDisp(false);
			setProyecto_cancelado(false);
			setProyecto_desistido(false);
			setProyecto_pospuesto(false);
			setProyecto_rechazado_automaticamente(true);
		
		}else{
			
			if( proyect_loan_reasignable.getDay_published() == null )
			
				try{
					asignarClabeAccount(prospectus_id, company_id);
					asignarDatosSimulacion(prospectus_id, company_id, rate, simulator);											
					//asignar_selected_deal(prospectus_id, company_id);
					mostrar_propuestas();
				}catch(Exception e){
					e.printStackTrace();
				}
			else{
				
				mostrar_solicitud_completa();
				
			}
		}
		
	}
	
	protected final void mostrar_proyecto_cancelado()
	{
		setDisplayBtnDeal(true);						
		setBeginDisp(false);
		setContResp(false);
		setErrorDisp(false);											
		setErrorDesc("");						
		setSuccessDisp(false);
		setProyecto_pospuesto(false);
		setProyecto_desistido(false);
		setProyecto_cancelado(true);
	}
	
	protected final void mostrar_propuestas()
	{		
		setBeginDisp(true);
		setContResp(false);
		setErrorDisp(false);											
		setErrorDesc("");
		setSuccessDisp(false);	
		setProyecto_rechazado_automaticamente(false);
	}
		
	protected final void asignar_selected_deal(int prospectus_id, int company_id, int proyect_id, int proyect_loan_id)
	{
		selectedDeal = new DealBean();
		
		selectedDeal.setCompany_id(company_id + "");
		selectedDeal.setProspectus_id(prospectus_id + "");
		selectedDeal.setProyect_loan_id(proyect_loan_id + "");
		selectedDeal.setProyect_id(proyect_id + "");
	}
	
	protected final void asignar_selected_deal(int prospectus_id, int company_id)
	{
		selectedDeal = new DealBean();
		
		selectedDeal.setCompany_id(company_id + "");
		selectedDeal.setProspectus_id(prospectus_id + "");	
	}
	
	protected final void asignarReanudar_solicitud_ENABLED(Date fecha)
	{				
		Calendar today       = Calendar.getInstance();
		today.setTime(new Date());
		
		Calendar fecha_leida = Calendar.getInstance();
		
		fecha_leida.setTime(fecha);
		
		setReanudar_solicitud_ENABLED( !today.before(fecha_leida));		
	}
	
	protected ProyectLoan creaProyectLoan(int prospectus_id, int company_id)
	{		
		try
		{			
			proyect_loan =  proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			asignar_datos_montos();
			asignar_datos_score();
			asignar_datos_otros();
			
			if(proyect_loan == null 
			|| proyect_loan.getStatus_id() == 3
			|| proyect_loan.getStatus_id() == 4)
			{				
				proyect_loan    = new ProyectLoan();
				proyect_loan_PK = new  ProyectLoanPK();
				
				proyect    = proyectService.getMaxProyect(prospectus_id, company_id);				
				proyect_id = proyect.getProyectoPk().getProyect_id();																																																																													

				proyect_loan_PK.setCompany_id(company_id);
				proyect_loan_PK.setProspectus_id(prospectus_id);
				proyect_loan_PK.setProyect_id(proyect_id);
				proyect_loan_PK.setProyect_loan_id(0);
				
				proyect_loan.setProyectloanPk(proyect_loan_PK);
				
				asignar_proyect_loan();
										
//              proyect_loan.setDays_online(getPublishTime());					
				proyect_loan.setDays_online(15);
				proyect_loan.setStatus_id(0);
	
				proyectloanService.add(proyect_loan);
				
				proyect_loan =  proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
				
			} else {
				
				asignar_proyect_loan();		
				
				proyectloanService.update(proyect_loan);
			}
			
			return proyect_loan;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
	}
	
	private void asignar_datos_montos() 
	{
		monto  = selectedDeal.getAmountStr();			
		monto  = monto.replace("$", "");
		monto  = monto.replace(",", "");
		monto  = monto.replace(" ", "");			
		amount = Double.parseDouble(monto);
					
		monto = selectedDeal.getFee();			
		monto = monto.replace("$", "");
		monto = monto.replace(",", "");
		monto = monto.replace(" ", "");			
		pagos = Double.parseDouble(monto);
		
		monto = selectedDeal.getMontoComision();			
		monto = monto.replace("$", "");
		monto = monto.replace(",", "");
		monto = monto.replace(" ", "");			
		comision_apertura_monto = Double.parseDouble(monto);
		
	}
	
	private void asignar_datos_otros() 
	{
		metodo_deposito_id = hasAccount ? 1 : 2;
		tipo_fondeo        = fundingType.charAt(0);
		term_id            = selectedDeal.getTerm_Id();
		frequency_id       = selectedDeal.getFrequency_Id();
		cat                = Double.parseDouble(selectedDeal.getCat());
		tasa_apertura      = Double.parseDouble(selectedDeal.getRate().replace("%", ""));
	}
	
	private void asignar_datos_score() 
	{
		bc_score          = Integer.parseInt(score.getBc_score());
		tasa              = score.getRate();
		tasa_inversion    = score.getRate_investor();
		liquidez          = score.getLiquidity();
		comision_apertura = score.getOpening_commission();
		kubo_score_A      = score.getKubo_score_a();
		kubo_score_B      = score.getKubo_score_b();
		bur_sol_num       = score.getMx_solicitud_buro();
		cci_score         = score.getCci_score();
	}
	
	private void asignar_proyect_loan() 
	{
		proyect_loan.setDay_published(new Date());
		proyect_loan.setIs_published("S");
		proyect_loan.setTerm_id(term_id);
		proyect_loan.setDeposit_method_id(metodo_deposito_id);
		proyect_loan.setFrequency_id(frequency_id);
		proyect_loan.setFunding_type(tipo_fondeo);
		
		proyect_loan.setAmmount(amount);
		proyect_loan.setPayment(pagos);		
		proyect_loan.setMx_cat(cat);				
		proyect_loan.setMin_ammount(monto_minimo);
		proyect_loan.setLiquidity(liquidez);
		
		proyect_loan.setRate(tasa);
		proyect_loan.setRate_investor(tasa_inversion);		
		proyect_loan.setRate_with_opening(tasa_apertura);
		
		proyect_loan.setOpening_payment(formaPagoCom);
		proyect_loan.setOpening_commission       (comision_apertura);
		proyect_loan.setOpening_commission_amount(comision_apertura_monto);	
		
		proyect_loan.setMx_solicitud_buro(bur_sol_num);
		proyect_loan.setKubo_score_a(kubo_score_A);
		proyect_loan.setKubo_score_b(kubo_score_B);	
		proyect_loan.setBc_score(bc_score);
		proyect_loan.setCci_score(cci_score);					
	}
	
	private String generaFrequenciaStr(){
		
		String freq = "";
		
		switch (getFrequencySel()){
			case 1://Semanal
				
				freq = "semanales";
				
				break;
			case 2: //Catorcenal
				
				freq = "catorcenales";
				
				break;
			case 3: //Quincenal
				
				freq = "quincenales";
				
				break;
			case 4: //Mensual
				
				freq = "mensuales";
				
				break;
		}
		
		return freq ;
		
	}
	
}

