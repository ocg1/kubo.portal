package mx.com.kubo.services.mesa.solicitud.notas;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;

public interface ServiceNotasDelCasoIMO 
{
	void setDatos_documentos_OK(boolean bandera);
	void setDomicilio_OK       (boolean bandera);
	
	void init_meta_info(Notes nota);
	
	String getMeta_info_TOKEN(Notes nota);
	String getMeta_info_JSON (Notes nota);	
	
	boolean persist_meta_info();
	boolean update_meta_info();
	
	boolean getDatos_documentos_OK();
	boolean getDomicilio_OK();
}
