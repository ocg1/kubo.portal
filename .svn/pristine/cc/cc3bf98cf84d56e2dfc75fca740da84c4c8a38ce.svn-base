package mx.com.kubo.services.mesa.solicitud.busqueda;

import java.util.LinkedList;
import java.util.List;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.repositories.mesa.solicitud.busqueda.ClientViewFullNameDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClientViewFullNameServiceImp 
implements ClientViewFullNameService
{
	@Autowired @Qualifier("dao_view_full_name")
	ClientViewFullNameDao clientViewFullNameDao;
	
	
	public List<ClientViewFullName> getListClientByName(String strName) 
	{		
		return clientViewFullNameDao.getListClientByName(strName);
	}

	public List<ClientViewFullName> getListClientByEmail(String strEmail) 
	{
		return clientViewFullNameDao.getListClientByEmail(strEmail);
	}
	
	public List<ClientViewFullName> getListAllUserByName(String strName)
	{
		return clientViewFullNameDao.getListAllUserByName(strName);
	}
	
	public LinkedList<ClientViewFullName> getListAllUserForReferred(String strName)
	{
		return clientViewFullNameDao.getListAllUserForReferred( strName );
	}
	
}
