package mx.com.kubo.mesa.solicitud.permisos;

import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;

public interface RoleFunctionIMO 
{
	void init();
	void setSesion(SessionBean sesion);
	void setScore(Scoring score);
	void setProyectLoan(ProyectLoan proyect_loan);
	void setRole_function(RoleFunctionController role_function);
	
	void setEditor_nombre_ENABLED(boolean flag);
	
	boolean isReprocesar_buro_credito();
	boolean isDisplayNotes();
	boolean isDisplayAlerts();	
	boolean isChangeActions();
	boolean isEditor_tipo_credito_ENABLED();
	boolean isEditor_nombre_ENABLED();
	boolean isModificar_destino_credito();
	boolean isModificar_tasa_acreditado();
	boolean isModificar_tasa_inversionista();
	boolean isModificar_comision_apertura();
	boolean isModificar_calificacion();
}
