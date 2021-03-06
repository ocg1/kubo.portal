package mx.com.kubo.services.mesa.solicitud.busqueda;

import java.util.LinkedList;
import java.util.List;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;

public interface ClientViewFullNameService 
{
	List<ClientViewFullName> getListClientByName(String strName);
	List<ClientViewFullName> getListClientByEmail(String strEmail);
	List<ClientViewFullName> getListAllUserByName(String strName);
	LinkedList<ClientViewFullName> getListAllUserForReferred(String strName);
}
