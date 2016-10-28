package mx.com.kubo.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.Alert;
import mx.com.kubo.model.AlertEvent;
import mx.com.kubo.model.AlertMotive;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.services.AlertEventService;
import mx.com.kubo.services.AlertMotiveService;
import mx.com.kubo.services.AlertService;

public class AlertsManagedDMO {
	
	@ManagedProperty("#{alertEventServiceImp}")
	protected AlertEventService alerteventservice;
	
	@ManagedProperty("#{alertServiceImp}")
	protected AlertService alertservice;
	
	@ManagedProperty("#{alertMotiveServiceImp}")
	protected AlertMotiveService alertmotiveservice;
	
	protected List<AlertEvent> lstAlertEventsPerson;
	protected List<AlertEvent> lstAlertEventsProyect;
	
	protected List<AlertMotive> lstAlertMotive;
	
	protected List<Alert> lstAlerts;
	
	protected boolean haveAlertPerson;
	protected boolean haveAlertProyect;
	
	protected boolean typelistproyect=false;
	protected boolean typelistperson=false;
	
	protected Integer prospectus_id =null;
	protected Integer company_id =null;
	protected Integer prospectus_id_sender=null;
	
	protected boolean displayAlert = false;
	
	protected ProyectLoanPK pylnPk = null;
	
	protected Integer alertType = 0;
	
	protected AlertEvent alertEventSel;
	
	public AlertEventService getAlerteventservice() {
		return alerteventservice;
	}

	public void setAlerteventservice(AlertEventService alerteventservice) {
		this.alerteventservice = alerteventservice;
	}

	public List<AlertEvent> getLstAlertEventsPerson() {
		return lstAlertEventsPerson;
	}

	public void setLstAlertEventsPerson(List<AlertEvent> lstAlertEventsPerson) {
		this.lstAlertEventsPerson = lstAlertEventsPerson;
	}

	public List<AlertEvent> getLstAlertEventsProyect() {
		return lstAlertEventsProyect;
	}

	public void setLstAlertEventsProyect(List<AlertEvent> lstAlertEventsProyect) {
		this.lstAlertEventsProyect = lstAlertEventsProyect;
	}

	public boolean isHaveAlertPerson() {
		return haveAlertPerson;
	}

	public void setHaveAlertPerson(boolean haveAlertPerson) {
		this.haveAlertPerson = haveAlertPerson;
	}

	public boolean isHaveAlertProyect() {
		return haveAlertProyect;
	}

	public void setHaveAlertProyect(boolean haveAlertProyect) {
		this.haveAlertProyect = haveAlertProyect;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public ProyectLoanPK getPylnPk() {
		return pylnPk;
	}

	public void setPylnPk(ProyectLoanPK pylnPk) {
		this.pylnPk = pylnPk;
	}

	public AlertEvent getAlertEventSel() {
		return alertEventSel;
	}

	public void setAlertEventSel(AlertEvent alertEventSel) {
		this.alertEventSel = alertEventSel;
	}

	public List<Alert> getLstAlerts() {
		return lstAlerts;
	}

	public void setLstAlerts(List<Alert> lstAlerts) {
		this.lstAlerts = lstAlerts;
	}

	public AlertService getAlertservice() {
		return alertservice;
	}

	public void setAlertservice(AlertService alertservice) {
		this.alertservice = alertservice;
	}

	public Integer getProspectus_id_sender() {
		return prospectus_id_sender;
	}

	public void setProspectus_id_sender(Integer prospectus_id_sender) {
		this.prospectus_id_sender = prospectus_id_sender;
	}

	public Integer getAlertType() {
		return alertType;
	}

	public void setAlertType(Integer alertType) {
		this.alertType = alertType;
	}

	public AlertMotiveService getAlertmotiveservice() {
		return alertmotiveservice;
	}

	public void setAlertmotiveservice(AlertMotiveService alertmotiveservice) {
		this.alertmotiveservice = alertmotiveservice;
	}

	public List<AlertMotive> getLstAlertMotive() {
		return lstAlertMotive;
	}

	public void setLstAlertMotive(List<AlertMotive> lstAlertMotive) {
		this.lstAlertMotive = lstAlertMotive;
	}

	public boolean isTypelistproyect() {
		return typelistproyect;
	}

	public void setTypelistproyect(boolean typelistproyect) {
		this.typelistproyect = typelistproyect;
	}

	public boolean isTypelistperson() {
		return typelistperson;
	}

	public void setTypelistperson(boolean typelistperson) {
		this.typelistperson = typelistperson;
	}

	public boolean isDisplayAlert() {
		return displayAlert;
	}

	public void setDisplayAlert(boolean displayAlert) {
		this.displayAlert = displayAlert;
	}

}
