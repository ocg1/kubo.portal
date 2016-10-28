package mx.com.kubo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ServiceCallingService;

import org.apache.log4j.Logger;

import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;
import safisrv.ws.InvKuboServicios.SolicitudInversionRequest;
import safisrv.ws.InvKuboServicios.SolicitudInversionResponse;

@ManagedBean
@SessionScoped
public class InstitutionalFunding implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	private ServiceCallingService servicecallingService;
	
	String ExceptionOnFunding = new String("");
	
	public InstitutionalFunding(){
		
	}
	
	private String funding(ProyectLoanPK key){
		
			Integer InvestorID = 104884;
			Integer Account = 104878;
			
			Integer proyect_id = key.getProyect_id();
			Integer proyect_loan_id = key.getProyect_loan_id(); 
			Integer prospectus_id = key.getProspectus_id(); 
			Integer company_id = key.getCompany_id();
			
		    
		    
		    ProyectLoan proyectInContext = proyectLoanService.findProyect(key);
			
		    
		    Double investment_Bite = proyectInContext.getAmmount()*0.01;
		    
		    ServiceCalling srvCall = new ServiceCalling();
		    srvCall.setAcces_datetime(new Date());
		    srvCall.setCompany_id(company_id);
		    srvCall.setStatus(1);
		    srvCall.setProspectus_id(prospectus_id);
		    srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudInversion");
		    servicecallingService.saveServiceCall(srvCall);
		    
		   
		    
		    String response = this.storeInvestmentInWS(proyectInContext.getSafi_mx_solicitud_id(), Account, InvestorID, 0.0, investment_Bite);
		    if(!response.equals("0")){
		    	srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(this.ExceptionOnFunding);
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(3);
				servicecallingService.saveServiceCall(srvCall);
				return "1";
		    }
		    else{
		    	srvCall = new ServiceCalling();
			    srvCall.setAcces_datetime(new Date());
			    srvCall.setCompany_id(company_id);
			    srvCall.setStatus(2);
			    srvCall.setProspectus_id(prospectus_id);
			    srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudInversion");
			    servicecallingService.saveServiceCall(srvCall);
			    
			    proyectLoanService.spSetOnProyectFunding(proyect_loan_id, proyect_id, company_id, prospectus_id, InvestorID, investment_Bite,"");
			    
			    proyectInContext.setAmount_founded(proyectInContext.getAmount_founded()+investment_Bite);
			    proyectInContext.setInvestors_number(proyectInContext.getInvestors_number()+1);
			    return "0";
		    }
		    
		    
		    
	}
	
	public String storeInvestmentInWS(Integer solicitudCreditoId,Integer cuentaAhorroId,Integer prospectus_investor_id, Double rate, Double investmentBite){
    	try{
    		SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
			SAFIServicios servicio = locator.getSAFIServiciosSoap11();
			//SolicitudInversionResponse res1 = servicio.solicitudInversion(new SolicitudInversionRequest(solicitudCreditoId.toString(),prospectus_investor_id.toString(),cuentaAhorroId.toString(),investmentBite.toString(),rate.toString()));
			SolicitudInversionRequest solicitudInversionRequest = new SolicitudInversionRequest();
			//solicitudInversionRequest.set
			// TODO: Llenar datos
			SolicitudInversionResponse res1 = servicio.solicitudInversion(solicitudInversionRequest);
			this.ExceptionOnFunding = res1.getMensajeRespuesta(); 
			return res1.getCodigoRespuesta();
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		return "-1";
		}
    }
}