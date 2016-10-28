package mx.com.kubo.services.cliente.cuenta;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.managedbeans.cliente.CreditoEMO;

public class ServiceEstadoCuentaIMP extends ServiceEstadoCuentaAMO
implements ServiceEstadoCuentaIMO
{	
	public final List<CreditoEMO> getLista_creditos() 
	{		
		try 
		{
			if(posicion_SAFI != null)
			{																
				init_lista_creditos();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		return lista_creditos;
	}

	private void init_lista_creditos() throws ParseException, RemoteException
	{				
		lista_creditos = new ArrayList<CreditoEMO>();
		
		for(int indice_posicion_SAFI = 0 ; indice_posicion_SAFI < posicion_SAFI.length; indice_posicion_SAFI++)
		{					
			init_credito_SAFI(indice_posicion_SAFI);
			
			if(credito_LIQUIDADO_EN_FECHA)
			{
				contador_creditos_liquidados++;
				
			} else {
				
				init_credito(indice_posicion_SAFI);
			}																			
		}	   
	}
}
