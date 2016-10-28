package mx.com.kubo.services.mesa.solicitud;

import mx.com.kubo.model.EstatusChangeLog;
import mx.com.kubo.repositories.mesa.solicitud.DAOEstatusChangeLogIMO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("service_estatus_change_log")
public final class ServiceEstatusChangeLogIMP 
implements ServiceEstatusChangeLogIMO
{
	@Autowired @Qualifier("dao_estatus_change_log")
	private DAOEstatusChangeLogIMO dao;
	
	public final boolean registrar(EstatusChangeLog estatus_change_log)
	{		
		return dao.registrar(estatus_change_log);
	}
	
}