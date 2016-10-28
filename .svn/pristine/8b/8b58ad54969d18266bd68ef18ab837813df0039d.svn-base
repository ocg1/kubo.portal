package mx.com.kubo.services.mesa.solicitud.notas;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;

import org.springframework.stereotype.Service;

@Service("service_notas_del_caso")
public final class ServiceNotasDelCasoIMP extends ServiceNotasDelCasoAMO
implements ServiceNotasDelCasoIMO
{
	public final boolean persist_meta_info() 
	{
		return dao_notes_meta_info.persist(meta_info);
	}		
	
	public final boolean update_meta_info()
	{
		return dao_notes_meta_info.update(meta_info);
	}
	
	public final String getMeta_info_TOKEN(Notes nota)
	{			
		meta_info = dao_notes_meta_info.getMeta_info(nota);
		
		init_meta_info_TOKEN();
				
		return meta_info_TOKEN;
	}
	
	public final String getMeta_info_JSON(Notes nota)
	{				
		meta_info = dao_notes_meta_info.getMeta_info(nota);
		
		init_meta_info_JSON();
				
		return meta_info_JSON;
	}
}
