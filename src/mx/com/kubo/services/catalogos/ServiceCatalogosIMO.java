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

public interface ServiceCatalogosIMO 
{
	List<AuthenticationPool> getAuthentication_pool();
	List<IdentificationType> getLista_tipo_credencial();
	List<Channel> getLista_canales();
	List<NotificationPreference> getLista_notification_preference();
	List<SocietyType> getLista_society_type();
	List<UnusualBehavior> getLista_unusual_behavior();
	
	Promo getPromo(String code);
	Promo getPromoByPK( PromoPK pk );	
}
