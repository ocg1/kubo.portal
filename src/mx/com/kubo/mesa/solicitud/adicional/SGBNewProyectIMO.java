package mx.com.kubo.mesa.solicitud.adicional;

import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;

public interface SGBNewProyectIMO 
{
	void setProyect(Proyect proyect);
	
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void setIs_automatic_aproved(String is_automatic_aproved);
	
	void init();
	
	boolean isChange_status_ENABLED();

}
