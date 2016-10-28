package mx.com.kubo.repositories.catalogos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.AuthenticationPool;
import mx.com.kubo.model.Promo;
import mx.com.kubo.model.PromoPK;
import mx.com.kubo.model.SocietyType;
import mx.com.kubo.model.catalogos.Channel;
import mx.com.kubo.model.catalogos.IdentificationType;
import mx.com.kubo.model.catalogos.NotificationPreference;
import mx.com.kubo.model.catalogos.UnusualBehavior;

@Repository("dao_catalogos")
public class DAOSCatalogosIMP 
implements DAOCatalogosIMO
{
	protected EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	public final List<IdentificationType> getLista_tipo_credencial() 
	{
		List<IdentificationType> tipo_credencial;
		
		tipo_credencial = em.createQuery("from IdentificationType", IdentificationType.class).getResultList();
		
		return tipo_credencial;
	}
	
	public final List<Channel> getLista_canales() 
	{
		List<Channel> lista_canales;
		
		lista_canales = em.createQuery("from Channel", Channel.class).getResultList();
		
		return lista_canales;
	}
	
	public final List<AuthenticationPool> getAuthentication_pool() 
	{
		List<AuthenticationPool> pool;
		
		pool = em.createQuery("from AuthenticationPool", AuthenticationPool.class).getResultList();
		
		return pool;
	}
	
	public final List<NotificationPreference> getLista_notification_preference()
	{
		List<NotificationPreference> lista_notification_preference;
		
		lista_notification_preference = em.createQuery("from NotificationPreference", NotificationPreference.class).getResultList();
		
		return lista_notification_preference;
	}

	public final List<SocietyType> getLista_society_type() 
	{
		List<SocietyType> lista_society_type;
		
		lista_society_type = em.createQuery("from SocietyType as s order by s.name asc", SocietyType.class).getResultList();
		
		return lista_society_type;
	}
	
	public final List<UnusualBehavior> getLista_unusual_behavior()
	{
		List<UnusualBehavior> lista_unusual_behavior;
		
		lista_unusual_behavior = em.createQuery("from UnusualBehavior as b order by b.description asc", UnusualBehavior.class).getResultList();
		
		return lista_unusual_behavior;
	}
	
	public final Promo getPromo(String code) 
	{		
		Promo promo = null;
		
		TypedQuery<Promo> typed;
		
		try
		{
			typed = em.createQuery("from Promo where code = ? ", Promo.class);
			typed.setParameter(1, code);
			
			promo = typed.getSingleResult();
			
		} catch (Exception e) {
			
			//e.printStackTrace();
		}
		
		return promo;
	}
	
	
	
	public final Promo getPromoByPK( PromoPK pk ) {
		
		try{
			
			return em.find(Promo.class, pk);
			
			
		}catch( Exception e ){
			return null;
		}
		
	}
}
