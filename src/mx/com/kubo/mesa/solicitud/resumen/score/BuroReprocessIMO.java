package mx.com.kubo.mesa.solicitud.resumen.score;

import mx.com.kubo.model.NaturalPerson;

public interface BuroReprocessIMO 
{
	void setPerson(NaturalPerson person);
	
	void setMxSolicitudBuro(String mxSolicitudBuro);
	
	void init();
	
	String getResponse_msg();
	String getResponse_status();
	
	boolean isReproccess_OK();
}
