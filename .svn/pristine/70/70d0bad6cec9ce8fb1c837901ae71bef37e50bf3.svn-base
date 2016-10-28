package mx.com.kubo.registro;

import mx.com.kubo.model.Membership;

public final class RegistrationReasonIMP extends RegistrationReasonAMO
implements RegistrationReasonIMO
{	
	public final String get_reason_TOKEN(Membership acreditado)
	{
		init(acreditado);
		
		asignar_reason_TOKEN();
		
		return reason_TOKEN;
	}
	
	public final String validaRegistrationReason(Membership membership)
	{
		boolean flag = false;
		String registrationReason ="";
		String origen_campaign = membership.getOrigin() != null ? "(" + membership.getOrigin() + ")" : "";
		
		if(membership != null && membership.getRegistration_reason() != null)
		{
			//Otro
			if(membership.getRegistration_reason_id() != null && membership.getRegistration_reason_id() == 7)
			{ 
				//registrationReason = membershipship.getRegistration_reason().getName() ;
				if(membership.getOther_registration_reason() != null)
				{
					flag = true;
					registrationReason = " " + membership.getOther_registration_reason() + origen_campaign;
				}
				
			} else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 3) {
				
				if(membership.getWho_recommends() != null)
				{
					flag = true;
					registrationReason = " Recomendado por " + membership.getWho_recommends();
					
				} else {
					
					flag = true;
					registrationReason = " " + membership.getRegistration_reason().getName() + origen_campaign;
				}
				
			} else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 8) { //PriceShoes
				
					flag = true;
					registrationReason = " " + membership.getRegistration_reason().getName() + origen_campaign;
					
					if(membership.getPriceshoes_number()!=null && membership.getPriceshoes_number().trim().length() >0 )
					{						
						registrationReason = " con numero de socio " + membership.getPriceshoes_number();						
					}
					
			} else if(membership.getRegistration_reason() != null && membership.getRegistration_reason_id() != 6 ) {
				
				flag = true;
				registrationReason = " " + membership.getRegistration_reason().getName() + origen_campaign;
			}
			
			//Promotor
			if(membership.getPromotor_id()!=null && membership.getPromotor_id()>0 )
			{ 				
				if(membership.getPromotor()!=null)
				{					
					if(flag )
					{					
						registrationReason += " asignado al Promotor "+membership.getPromotor().getName();						
					} else {						
						registrationReason += " Promotor " + membership.getPromotor().getName();						
					}					
				}
				
			} else {				
				registrationReason += " sin promotor asignado ";				
			}			
		}
		
		return registrationReason;
	}
}
