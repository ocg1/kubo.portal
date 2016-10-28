package mx.com.kubo.referencia_pago_panel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class PanelDAO_DMO 
{
	protected EntityManager em;
	
	protected final String        
		query_lista_ref_pago,
		query_lista_ref_pago_SPEI;	
	
	protected PanelDAO_DMO()
	{
		query_lista_ref_pago      = "FROM ReferenciaPagoEntity r WHERE r.deposit_type = 'D' ORDER BY r.no_secuencia ASC";
		query_lista_ref_pago_SPEI = "FROM ReferenciaPagoEntity r WHERE r.deposit_type = 'S' ORDER BY r.no_secuencia ASC";
	}
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
}
