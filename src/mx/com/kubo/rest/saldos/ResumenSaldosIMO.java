package mx.com.kubo.rest.saldos;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.rest.ResumenSaldos;
import mx.com.kubo.services.ProyectLoanService;

public interface ResumenSaldosIMO 
{
	void setServicioProyecto(ProyectLoanService service);
	void setPerson(NaturalPerson person);
	
	void init();
	void init_resumen_saldos();
	
	ResumenSaldos getResumenSaldos();
}
