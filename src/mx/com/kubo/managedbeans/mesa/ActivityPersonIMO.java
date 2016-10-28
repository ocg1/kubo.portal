package mx.com.kubo.managedbeans.mesa;

import mx.com.kubo.bean.ChangeBean;

public interface ActivityPersonIMO 
{
	void listener_init_actividad();
	void listener_borrar_consultas();
	void cargaInfo();
	void setListChanges(ChangeBean change);
	void generar_password_activacion();
	void updateConsulting();
	
	void setProspectus_id(Integer prospectus_id);
	void setCompany_id   (Integer company_id);
	
	boolean isPanel_consulta_ENABLED();
	boolean isPanel_inversionista_ENABLED();
	boolean isBorrar_intentos_ENABLED();
		
	ChangeBean getChange_control_bean();
}
