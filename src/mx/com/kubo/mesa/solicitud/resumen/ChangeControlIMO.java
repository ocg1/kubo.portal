package mx.com.kubo.mesa.solicitud.resumen;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProyectLoan;

public interface ChangeControlIMO 
{
	void setSesion(SessionBean sesion);
	
	boolean isUpdate_OK();
	
	ProyectLoan getProyect_loan();
	
	ChangeBean getChange_control_bean();
	
	void init_change_control();
	void save_change_control();
}
