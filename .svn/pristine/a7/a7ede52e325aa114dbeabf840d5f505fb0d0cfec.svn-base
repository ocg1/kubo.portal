package mx.com.kubo.portal.efectivo;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;

public class LoanNegotiationIMP extends LoanNegotiationAMO 
implements LoanNegotiationIMO
{
	public void init()
	{
		inicializaSimulador();
		init_loan_negotiation();
	}
	
	public void setIniciales()
	{
		if(negotiation == null)
		{			
			negotiation = new LoanNegotiation();
			
			LoanNegotiationPK lnpk =  new LoanNegotiationPK();
			
			lnpk.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
			lnpk.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
			lnpk.setProyect_id(proyect_loan.getProyectloanPk().getProyect_id());
			lnpk.setProyect_loan_id(proyect_loan.getProyectloanPk().getProyect_loan_id());
			
			negotiation.setPk(lnpk);
			
			negotiation.setAmmount(proyect_loan.getAmmount());
			negotiation.setDays_online(proyect_loan.getDays_online());
			negotiation.setFrequency_id(proyect_loan.getFrequency_id());
			negotiation.setFunding_type(proyect_loan.getFunding_type());
			negotiation.setKubo_score_a(proyect_loan.getKubo_score_a());
			negotiation.setKubo_score_b(proyect_loan.getKubo_score_b());
			negotiation.setMin_ammount(proyect_loan.getMin_ammount());
			negotiation.setMx_cat(proyect_loan.getMx_cat());
			negotiation.setOpening_commission_amount(proyect_loan.getOpening_commission_amount());
			negotiation.setOpening_payment(proyect_loan.getOpening_payment());
			negotiation.setPayment(proyect_loan.getPayment());
			negotiation.setProspectus_id_proposed(sesion.getProspectus_id());
			negotiation.setRate(proyect_loan.getRate());
			negotiation.setRate_with_opening(proyect_loan.getRate_with_opening());						
			negotiation.setStatus(null);
			negotiation.setTerm_id(proyect_loan.getTerm_id());			
		}
		
		negotiation.setRate(proyect_loan.getRate());

						
		negotiation.setTerm_id(termInt);
		negotiation.setAmmount(montoIni);		
		negotiation.setFrequency_id(freqIni);
		negotiation.setPayment(pagoIni);				

		montoNegotiation = num.format(montoIni);
		
	    pagoMenControl = pagoMenIni;
	    /*
	    liquidezCliControl = liqIni;
				
		if(ammountConsolidate.getAmmount() != null && ammountConsolidate.getAmmount() > 0)
		{
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenIni;
			
		} else {
			
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = excedenteControl/pagoMenIni;
		}
		
		liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
		

		if(liquidezReq != null && liquidezReq < liquidezCliControl)
		{
			  dispOKControl =true;
			dispWarnControl = false;
			
		} else {
			
			  dispOKControl = false;
			dispWarnControl = true;
		}
*/		
		
		realizaSimulacion();

		request = RequestContext.getCurrentInstance();
				
		request.addCallbackParam("monto_inicial", montoNegotiation);
		request.addCallbackParam("plazo", termInt != null ? termInt : 0);
	}
	
	public void init_monto_negotiation(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		montoNegotiation = input_text.getValue().toString();
		
		request.addCallbackParam("montoNegotiation", montoNegotiation);
	}	
	
	public void modificaCondiciones()
	{				
		realizaSimulacion();
		
		if(hasNegotiation)
		{
			service_negotiation.update(negotiation);
			hasNegotiation = true;    
			dispBotCondiciones = true;
			
		} else {
			
			service_negotiation.saveLoanNegotiation(negotiation);
			hasNegotiation = true;
			dispBotCondiciones = true;
		}
		
		montoIni = negotiation.getAmmount();
		termInt  = negotiation.getTerm_id();
		freqIni  = negotiation.getFrequency_id();
		pagoIni  = negotiation.getPayment();
		
		pagoMenIni = negotiation.getPayment();
		
		
		switch (negotiation.getFrequency_id())
		{
			case 1:
				pagoMenIni = pagoMenIni*4;
			break;
			
			case 2:
				pagoMenIni = pagoMenIni*2;
			break;
			
			case 3: 
				pagoMenIni = pagoMenIni*2;
			break;
			
			case 4: break;
		}
		
		dispSendNegotiation = true;
		
/*		
		if(ammountConsolidate.getAmmount() != null && ammountConsolidate.getAmmount() > 0)
		{
			ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());
			
			liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenIni;
			
		} else {
			
			ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());
			
			liquidezCliControl = excedenteControl/pagoMenIni;
		}		
		
		liquidezCliControl = (double)Math.round((liquidezCliControl)*100) / 100;
		
		liqIni = liquidezCliControl;				

		
		indice = new IndicePagoDeudasIMP();
		indice.init();
*/				
	}
	
	public void aceptaCondiciones()
	{			
		request = RequestContext.getCurrentInstance();
		
		negotiation.setDate_finished(new Date());
		negotiation.setStatus("A");
		
		service_negotiation.update(negotiation);
		
		proyect_loan.setAmmount(negotiation.getAmmount());
		proyect_loan.setFrequency_id(negotiation.getFrequency_id());
		proyect_loan.setFunding_type(negotiation.getFunding_type());
		proyect_loan.setMx_cat(negotiation.getMx_cat());
		proyect_loan.setOpening_payment(negotiation.getOpening_payment());
		proyect_loan.setOpening_commission_amount(negotiation.getOpening_commission_amount());
		proyect_loan.setPayment(negotiation.getPayment());
		proyect_loan.setRate(negotiation.getRate());
		proyect_loan.setRate_with_opening(negotiation.getRate_with_opening());
		proyect_loan.setTerm_id(negotiation.getTerm_id());
			
		service_proyect_loan.update(proyect_loan);
		
		callSGB(proyect_loan);
		
		dispSendNegotiation = false;
		
		dispBotCondiciones = false;
		
		request.addCallbackParam("acepta_condiciones_OK", true);
		
/*		
		ammountLeft	= num.format(proyect_loan.getAmmountLeft());
		ammount     = num.format(proyect_loan.getAmmount());
		
		months = proyect_loan.getTerm_id();
		
		switch(proyect_loan.getFrequency_id())
		{
			case 1:
				frequency = "Semanal";
				frequencyStr = "Semanas";
				frequencyStr02 = "semanales";
			break;
			
			case 2:
				frequency = "Catorcenal";
				frequencyStr = "Catorcenas";
				frequencyStr02 = "catorcenales";
			break;
			
			case 3:
				frequency = "Quincenal";
				frequencyStr = "Quincenas";
				frequencyStr02 = "quincenales";
			break;
			
			case 4:
				frequency = "Mensual";
				frequencyStr= "Meses";
				frequencyStr02 = "mensuales";
			break;
		};
*/		
		
/*		
		payment = num.format(proyect_loan.getPayment());
		
		pagoMen(proyect_loan.getPayment());		
		
		switch (negotiation.getFrequency_id()){
		case 1://semanal
			pagoMen = pagoMen*4;
			break;
		case 2://catorcenal
			pagoMen = pagoMen*2;
			break;
		case 3: //quincenal
			pagoMen = pagoMen*2;
			break;
		case 4://mensual
			break;
		}	
		
		liquidezCli = excedente / pagoMen;
		
		liquidezCli = (double)Math.round((liquidezCli)*100)/100;
		
		if(liquidezReq != null && liquidezReq < liquidezCli)
		{
			dispOKCl = true;
			dispWarnCl = false;
			
		} else {
			dispOKCl=false;
			dispWarnCl=true;
		}
						
		indice = new IndicePagoDeudasIMP();
		indice.init();
*/		
	}
}
