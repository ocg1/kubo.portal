package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_note_meta_info")
public class NotesMetaInfo 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId private NotesMetaInfoPK meta_info_PK;
	
	@Column(name = "is_data_and_files_OK")
	public String datos_documentos_OK;
	
	@Column(name = "is_address_OK")
	public String domicilio_OK;

	public final NotesMetaInfoPK getMeta_info_PK() 
	{
		return meta_info_PK;
	}
	
	public final String getDatos_documentos_OK() 
	{
		return datos_documentos_OK;
	}
	
	public final String getDomicilio_OK() {
		return domicilio_OK;
	}
	
	public final void setMeta_info_PK(NotesMetaInfoPK meta_info_PK) 
	{
		this.meta_info_PK = meta_info_PK;
	}

	public final void setDomicilio_OK(String domicilio_OK) {
		this.domicilio_OK = domicilio_OK;
	}

	public final void setDatos_documentos_OK(String datos_documentos_OK) 
	{
		this.datos_documentos_OK = datos_documentos_OK;
	}
}
