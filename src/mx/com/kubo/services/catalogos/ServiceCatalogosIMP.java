package mx.com.kubo.services.catalogos;

import java.util.List;

import mx.com.kubo.model.AuthenticationPool;
import mx.com.kubo.model.Promo;
import mx.com.kubo.model.PromoPK;
import mx.com.kubo.model.SocietyType;
import mx.com.kubo.model.catalogos.Channel;
import mx.com.kubo.model.catalogos.IdentificationType;
import mx.com.kubo.model.catalogos.NotificationPreference;
import mx.com.kubo.model.catalogos.UnusualBehavior;
import mx.com.kubo.repositories.catalogos.DAOCatalogosIMO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("service_catalogos")
public class ServiceCatalogosIMP 
implements ServiceCatalogosIMO
{
	@Autowired @Qualifier("dao_catalogos")
	private DAOCatalogosIMO dao;
	
	public List<IdentificationType> getLista_tipo_credencial() 
	{
		return dao.getLista_tipo_credencial();
	}
	
	public List<Channel> getLista_canales()
	{
		return dao.getLista_canales();
	}
	
	public List<AuthenticationPool> getAuthentication_pool()
	{
		return dao.getAuthentication_pool();
	}
	
	public List<NotificationPreference> getLista_notification_preference()
	{
		return dao.getLista_notification_preference();
	}
	
	public List<SocietyType> getLista_society_type()
	{
		return dao.getLista_society_type();
	}		
	
	public List<UnusualBehavior> getLista_unusual_behavior()
	{
		return dao.getLista_unusual_behavior();
	}
	
	public final Promo getPromo(String code) 
	{
		return dao.getPromo(code);
	}
	
	public final Promo getPromoByPK(PromoPK pk)
	{
		return dao.getPromoByPK(pk);
	}
}
