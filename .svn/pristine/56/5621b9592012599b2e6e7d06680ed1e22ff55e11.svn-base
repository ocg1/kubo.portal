package mx.com.kubo.services.mesa.solicitud.notas;

import java.util.ArrayList;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

import mx.com.kubo.model.mesa.solicitud.notas.MetaInfoToken;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfo;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfoPK;

public abstract class ServiceNotasDelCasoAMO extends ServiceNotasDelCasoDMO
implements ServiceNotasDelCasoIMO
{
	public void init_meta_info(Notes nota) 
	{
		prospectus_id = nota.getNotesPk().getProspectus_id();
		company_id    = nota.getNotesPk().getCompany_id();
		note_id       = nota.getNotesPk().getNote_id();
		
		meta_info_PK = new NotesMetaInfoPK();
		meta_info_PK.setCompany_id(company_id);
		meta_info_PK.setProspectus_id(prospectus_id);
		meta_info_PK.setNote_id(note_id);
		
		meta_info = new NotesMetaInfo();
		meta_info.setMeta_info_PK(meta_info_PK);
		meta_info.setDatos_documentos_OK(datos_documentos_OK);
		meta_info.setDomicilio_OK(domicilio_OK);		
	}
	
	protected void init_meta_info_TOKEN() 
	{
		sb = new StringBuilder();
						
		if(meta_info != null)
		{
			datos_documentos_OK = meta_info.getDatos_documentos_OK();
			domicilio_OK        = meta_info.getDomicilio_OK();

			sb.append(" - ");
			
			if(datos_documentos_OK != null && datos_documentos_OK.equals("S"))
			{
				sb.append("Los datos y los documentos son correctos. ");
				
			} else {
				
				sb.append("Los datos y los documentos NO son correctos. ");
			}
			
			if(domicilio_OK != null && domicilio_OK.equals("S"))
			{
				sb.append("El domicilio es correcto. ");
				
			} else {
				
				sb.append("El domicilio NO es correcto. ");
			}
			
		} else {
			
			sb.append("");		
		}
		
		meta_info_TOKEN = sb.toString();
	}
	
	protected void init_meta_info_JSON() 
	{
		meta_info_JSON = null;
		
		if(meta_info != null)
		{
			lista_meta_info_TOKEN = new ArrayList<MetaInfoToken>();
			
			datos_documentos_OK = meta_info.getDatos_documentos_OK();
			domicilio_OK        = meta_info.getDomicilio_OK();
			
			lista_meta_info_TOKEN.add(new MetaInfoToken("datos_documentos_OK", datos_documentos_OK));
			lista_meta_info_TOKEN.add(new MetaInfoToken("domicilio_OK",        domicilio_OK));
			
			try 
			{
				meta_info_JSON = new JSONArray(lista_meta_info_TOKEN.toArray(), true).toString();
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
	}
}
