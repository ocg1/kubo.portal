package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.AlertEvent;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.repositories.AlertEventDao;
import mx.com.kubo.services.AlertEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertEventServiceImp implements Serializable,AlertEventService  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlertEventDao alerteventdao;
	
	@Override
	public boolean saveAlertEvent( AlertEvent alertevent){
		return alerteventdao.saveAlertEvent(alertevent);
	}
	
	@Override
    public boolean haveAlertsEventPerson(int prospectus_id, int company_id){
		return alerteventdao.haveAlertsEventPerson(prospectus_id, company_id);
	}
    
	@Override
    public boolean haveAlertsEventProyectLoan(ProyectLoanPK pylnPk){
		return alerteventdao.haveAlertsEventProyectLoan(pylnPk);
	}
    
	@Override
    public List<AlertEvent> getAlertsEventPersonList(int prospectus_id, int company_id){
		return alerteventdao.getAlertsEventPersonList(prospectus_id, company_id);
	}
    
	@Override
    public List<AlertEvent> getAlertsEventProyectLoanList(ProyectLoanPK pylnPk){
		return alerteventdao.getAlertsEventProyectLoanList(pylnPk);
	}

}
