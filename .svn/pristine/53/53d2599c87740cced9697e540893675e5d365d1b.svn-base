package mx.com.kubo.services.mesa.solicitud.notas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.com.kubo.model.mesa.solicitud.notas.MetaInfoToken;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfo;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfoPK;
import mx.com.kubo.repositories.mesa.solicitud.notas.DAONotesMetaInfoIMO;

public abstract class ServiceNotasDelCasoDMO 
implements ServiceNotasDelCasoIMO
{
	@Autowired @Qualifier("dao_notes_meta_info")
	protected DAONotesMetaInfoIMO dao_notes_meta_info;
	
	protected NotesMetaInfo   meta_info;
	protected NotesMetaInfoPK meta_info_PK;	
	
	protected List<MetaInfoToken> lista_meta_info_TOKEN;
	
	protected StringBuilder sb;
	
	protected String meta_info_TOKEN;
	protected String meta_info_JSON;
	protected String datos_documentos_OK;
	protected String domicilio_OK;
	
	protected Integer note_id;
	
	protected int prospectus_id;
	protected int company_id;
	
	public void setDatos_documentos_OK(boolean bandera) 
	{
		if(bandera)
		{
			datos_documentos_OK = "S";
			
		} else {
			
			datos_documentos_OK = "N";
		}
	}

	public void setDomicilio_OK(boolean bandera) 
	{
		if(bandera)
		{
			domicilio_OK = "S";
			
		} else {
			
			domicilio_OK = "N";
		}
	}

	public final boolean getDatos_documentos_OK() 
	{
		if(datos_documentos_OK.equals("S"))
		{
			return true;
			
		} else {
			
			return false;
		}
	}

	public final boolean getDomicilio_OK() 
	{
		if(domicilio_OK.equals("S"))
		{
			return true;
			
		} else {
			
			return false;
		}
	}
}
