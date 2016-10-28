package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.AlertEvent;
import mx.com.kubo.model.ProyectLoanPK;

public interface AlertEventDao {
	
	public boolean saveAlertEvent( AlertEvent alertevent);
	
    public boolean haveAlertsEventPerson(int prospectus_id, int company_id);
    
    public boolean haveAlertsEventProyectLoan(ProyectLoanPK pylnPk);
    
    public List<AlertEvent> getAlertsEventPersonList(int prospectus_id, int company_id);
    
    public List<AlertEvent> getAlertsEventProyectLoanList(ProyectLoanPK pylnPk);
	
}
