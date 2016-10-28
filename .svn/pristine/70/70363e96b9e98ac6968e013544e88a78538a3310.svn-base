package mx.com.kubo.registro.verificacion;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.NaturalPerson;

public interface PersonaBloqueadaIMO 
{	
	void setTipo_de_Operacion(int tipo_operacion);
	
	void setPerson(NaturalPerson naturalPerson);
	void setSesion(SessionBean sesion);
	
	void init();
	void init_busqueda_clientes();
	
	int getBlocked_person_TOTAL();
	
	boolean isBlocked_person_ENABLED();
	
	BlockedPerson getPerson();	
}
