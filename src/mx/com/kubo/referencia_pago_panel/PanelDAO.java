package mx.com.kubo.referencia_pago_panel;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.referencia_pago.ReferenciaPagoEntity;

import org.springframework.stereotype.Repository;

@Repository @SuppressWarnings("serial")
public final class PanelDAO extends PanelDAO_DMO
implements Serializable
{	        
    public final List<ReferenciaPagoEntity> getLista_referencias_pago(String credito_id)
    {    	
    	List<ReferenciaPagoEntity> lista_referencias_pago = null;
    	
		try
		{									
			
			lista_referencias_pago = em.createNamedQuery("getLista_referencias_pago", ReferenciaPagoEntity.class)
									   .setParameter("safi_credito_id", credito_id)
									   .getResultList();
		
		} catch(Exception e) {
			e.printStackTrace();			
		} 
		
		return lista_referencias_pago;	
	}
}
