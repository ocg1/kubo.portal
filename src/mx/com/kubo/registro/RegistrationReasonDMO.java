package mx.com.kubo.registro;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Promotor;
import mx.com.kubo.model.RegistrationReason;

public abstract class RegistrationReasonDMO 
{
	protected RegistrationReason reason;
	protected Promotor promotor;
	
	protected String reason_TOKEN;
	protected String origen_campaign;
	protected String other_reason;
	protected String who_recommends;
	protected String price_shoes_number;
	
	protected Integer reason_id;
	protected Integer promotor_id;
	
	protected void init(Membership membership)
	{
		reason_TOKEN = "";
						
		reason             = membership.getRegistration_reason();
		reason_id          = membership.getRegistration_reason_id();
		other_reason       = membership.getOther_registration_reason();
		who_recommends     = membership.getWho_recommends();
		promotor_id        = membership.getPromotor_id();
		promotor           = membership.getPromotor();
		price_shoes_number = membership.getPriceshoes_number();
		origen_campaign    = membership.getOrigin();
		origen_campaign    = origen_campaign != null ? "(" + origen_campaign + ")" : "";
	}
}
