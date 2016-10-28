package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.model.AlertEvent;

@ManagedBean
@SessionScoped
public class AlertsManaged extends AlertsManagedDMO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	@PostConstruct
	public void init(){
	
		lstAlerts = alertservice.getAlertListSelectable();
		
		alertEventSel = new AlertEvent();

	}
	
	
	
	public void saveAlertsEvents(){
		
		System.out.println("init saveAlertsEvents");
		
		alertEventSel.setCompany_id(company_id);
		alertEventSel.setProspectus_id_viewed(prospectus_id);
		alertEventSel.setProspectus_id_sender(prospectus_id_sender);
		
		if(alertType == 1){
		
			alertEventSel.setProyect_id(pylnPk.getProyect_id());
			alertEventSel.setProyect_loan_id(pylnPk.getProyect_loan_id());
			
		}
		
		if(alerteventservice.saveAlertEvent(alertEventSel)){
			alertEventSel = new AlertEvent();
			
			if(alertType == 0){
				
				lstAlertEventsPerson = alerteventservice.getAlertsEventPersonList(prospectus_id, company_id);
				haveAlertPerson = alerteventservice.haveAlertsEventPerson(prospectus_id, company_id);
				
			}else if(alertType == 1){
			
				lstAlertEventsProyect = alerteventservice.getAlertsEventProyectLoanList(pylnPk);
				haveAlertProyect = alerteventservice.haveAlertsEventProyectLoan(pylnPk);
				
			}
		}
		
	}
	
	public void getAlertMotiveLst(){
		
		if( alertEventSel.getAlert_id() != null && alertEventSel.getAlert_id() != 0 ){
			lstAlertMotive = alertmotiveservice.getAlertMotiveListByAlert(alertEventSel.getAlert_id(), company_id );
		}else{
			lstAlertMotive = null;
		}
		
	}
	
	public boolean haveAlertsProyect(){
		return alerteventservice.haveAlertsEventProyectLoan(pylnPk);
	}
	
	public boolean haveAlertsPerson(){
		return alerteventservice.haveAlertsEventPerson(prospectus_id, company_id);
	}
	
	public void setListType(String type){
		
		if(type != null && type.equals("proyect")){
			
			typelistproyect=true;
			typelistperson=false;
			lstAlertEventsProyect = alerteventservice.getAlertsEventProyectLoanList(pylnPk);
			haveAlertProyect = alerteventservice.haveAlertsEventProyectLoan(pylnPk);
			alertType = 1;
			
		}else if(type != null && type.equals("person")){
			
			typelistproyect=false;
			typelistperson=true;
			lstAlertEventsPerson = alerteventservice.getAlertsEventPersonList(prospectus_id, company_id);
			haveAlertPerson = alerteventservice.haveAlertsEventPerson(prospectus_id, company_id);
			alertType = 0;
			
		}
		
	}

	
	
}
