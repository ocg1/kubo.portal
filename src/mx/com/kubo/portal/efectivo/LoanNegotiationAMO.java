package mx.com.kubo.portal.efectivo;

import java.util.Date;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;

import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;

public abstract class LoanNegotiationAMO extends LoanNegotiationDMO
{
	protected void inicializaSimulador()
	{				
		simulator = (Simulator) resolver.getValue(elContext, null, "simulator");		
		
		SimulatorBean sim = simulatorService.getMaxSimulationProspect(prospectus_id_viewed, company_id);
		
		simulator.simulaCred( false );
		
		if(sim != null)
		{
			simulator.setAmmount(sim.getAmmount());
			simulator.setFrequency_id(sim.getFrequency_id());
			
			if(sesion.getRate()!= null)
			{
				simulator.setTasaTotal(sesion.getRate());
				
			} else {
				
				simulator.setTasaTotal(61.50D);
			}
			
			if( sim.getTerm_id() != null )
			{
				simulator.setTerm_id(sim.getTerm_id());
			}
			
			if( sim.getPurpose_id() != null )
			{
				simulator.setPurpose_id(sim.getPurpose_id());
			}
		}					
	}
	
	protected void init_loan_negotiation() 
	{
		boolean isNegotiation;
		negotiation = null;
		
		if(proyect_loan != null)
		{
			negotiation = service_negotiation.loadMaxLoanNegotiation(prospectus_id_viewed, company_id, proyect_loan_id, proyect_id);
		}
		
		if(negotiation == null && proyect_loan != null)
		{			
			isNegotiation = false;
			
			negotiation = new LoanNegotiation();
			
			negotiation_PK =  new LoanNegotiationPK();			
			negotiation_PK.setCompany_id(company_id);
			negotiation_PK.setProspectus_id(prospectus_id_viewed);
			negotiation_PK.setProyect_id(proyect_id);
			negotiation_PK.setProyect_loan_id(proyect_loan_id);
			
			negotiation.setPk(negotiation_PK);
			
			negotiation.setStatus(null);
			negotiation.setProspectus_id_proposed(prospectus_id);
			negotiation.setAmmount     (proyect_loan.getAmmount());
			negotiation.setDays_online (proyect_loan.getDays_online());
			negotiation.setFrequency_id(proyect_loan.getFrequency_id());
			negotiation.setFunding_type(proyect_loan.getFunding_type());
			negotiation.setKubo_score_a(proyect_loan.getKubo_score_a());
			negotiation.setKubo_score_b(proyect_loan.getKubo_score_b());
			negotiation.setMin_ammount (proyect_loan.getMin_ammount());
			negotiation.setMx_cat      (proyect_loan.getMx_cat());
			negotiation.setPayment     (proyect_loan.getPayment());
			negotiation.setRate        (proyect_loan.getRate());
			negotiation.setTerm_id     (proyect_loan.getTerm_id());
			negotiation.setOpening_commission_amount(proyect_loan.getOpening_commission_amount());
			negotiation.setOpening_payment  (proyect_loan.getOpening_payment());						
			negotiation.setRate_with_opening(proyect_loan.getRate_with_opening());												
			hasNegotiation =false;
			dispBotCondiciones = false;
			
		} else {
			
			isNegotiation = true;
			hasNegotiation = true;
			dispBotCondiciones = true;
			
		}
		
		if(negotiation!=null)
		{		
			if(isNegotiation )
			{
				if(negotiation.getStatus() == null|| !negotiation.getStatus().equals("A"))
				{
					dispSendNegotiation = true;
				}
				
			} else {
				
				dispSendNegotiation = false;
			}			
			
			
			pagoMenControl  = negotiation.getPayment();
			
			termInt = negotiation.getTerm_id();
			
			switch (negotiation.getFrequency_id())
			{
					case 1://semanal
						pagoMenControl = pagoMenControl*4;
						break;
					case 2://catorcenal
						pagoMenControl = pagoMenControl*2;
						break;
					case 3: //quincenal
						pagoMenControl = pagoMenControl*2;
						break;
					case 4://mensual
						break;
				}
			
			montoNegotiation = num.format(negotiation.getAmmount());

			pagoMenIni = pagoMenControl;
			   pagoIni = negotiation.getPayment();
			  montoIni = negotiation.getAmmount();			
			   freqIni = negotiation.getFrequency_id();		

		}
	}

	public void realizaSimulacion()
	{		
		simulator.setFrequency_id(negotiation.getFrequency_id());	
		
		simulator.generaNumCuotas();
		
		negotiation.setAmmount(Double.parseDouble(montoNegotiation.replace(",", "").replace("$","")));
		
		simulator.setAmmount  (negotiation.getAmmount());
		simulator.setTerm_id  (negotiation.getTerm_id());		
		simulator.setTasaTotal(negotiation.getRate());		
		
		simulator.generaMontoCuota2();
		
		negotiation.setMx_cat       (simulator.getCat());
		negotiation.setPayment      (simulator.getMontoCuota());
		negotiation.setTotal_payment(simulator.getTotalPagar());
		
		negotiation.setMin_ammount(negotiation.getAmmount());
		
		Double c = (proyect_loan.getOpening_commission()==null?5:proyect_loan.getOpening_commission())/100;
		Double comision = c * negotiation.getAmmount();
		comision = ((double)Math.round((comision)*100)/100);
		
		negotiation.setOpening_commission_amount(comision);
		
				
		pagoMenControl = negotiation.getPayment();
		
		
		switch (negotiation.getFrequency_id()){
			case 1://semanal
				pagoMenControl = pagoMenControl*4;
				break;
			case 2://catorcenal
				pagoMenControl = pagoMenControl*2;
				break;
			case 3: //quincenal
				pagoMenControl = pagoMenControl*2;
				break;
			case 4://mensual
				break;
		}
		
/*		
		if(ammountConsolidate.getAmmount()!=null && ammountConsolidate.getAmmount()>0)
		{
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
		
			liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenControl;
		}else{//Si no cuenta con mont de consolidacion
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = excedenteControl/pagoMenControl;
		}		
		
		liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
		

		if(liquidezReq!=null&&liquidezReq<liquidezCliControl){
			dispOKControl=true;
			dispWarnControl=false;
		}else{
			dispOKControl=false;
			dispWarnControl=true;
		}
*/		
	}
	
	public void callSGB(ProyectLoan pl)
	{
		try
		{
			
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			ServiceCalling srvCall = new ServiceCalling();
			WsSgbResponse res1 =  new WsSgbResponse();
			WsSgbResponse res2 =  new WsSgbResponse();
			WsSgbResponse res3 =  new WsSgbResponse();
			WsSgbResponse res4 =  new WsSgbResponse();
			
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit");
			srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(1);
			
			servicecallingService.saveServiceCall(srvCall);
			
			//service.updateCredit(userId, projectLoanId, prospectId, companyId, varId, newValue);
			
			res1 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "1" , pl.getAmmount()+"");
			res2 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "2" , pl.getFrequency().getName()+"");
			res3 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "3" , pl.getTerm_id()+"");
			res4 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "4" , pl.getOpening_commission()+"");
			
			if(res1.getStatus().equals("0") && res2.getStatus().equals("0") && res3.getStatus().equals("0") && res4.getStatus().equals("0") ){
				srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
				srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit");
				srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
				srvCall.setStatus(2);
				
				servicecallingService.saveServiceCall(srvCall);
				
			}else{
				
				if(res1.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (monto) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar el MONTO EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				if(res2.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (FRECUENCIA) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					srvCall.setException(res1.getMessage());
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar FRECUENCIA EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(3);
					srvCall.setException(res2.getMessage());
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				if(res3.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (PLAZO) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar PLAZO EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setException(res3.getMessage());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				if(res4.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (ComisionApertura) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar ComisionApertura EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setException(res4.getMessage());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				
			}
			
			
		}catch(Exception e){
			
			ServiceCalling srvCall = new ServiceCalling();
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Error al invocar WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit");
			srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(3);
			srvCall.setException(e.getMessage());
			servicecallingService.saveServiceCall(srvCall);
			
		}		
	}
}
