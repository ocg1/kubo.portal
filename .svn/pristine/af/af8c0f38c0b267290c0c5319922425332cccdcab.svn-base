package mx.com.kubo.registro;

public class RegistrationReasonAMO extends RegistrationReasonDMO
{
	protected void asignar_reason_TOKEN() 
	{
		if(reason != null && reason_id != null)
		{			
			switch(reason_id)
			{				
				case 3:
					if(who_recommends != null)
					{						
						reason_TOKEN = " Recomendado por " + who_recommends;
						
					} else {
						
						reason_TOKEN = " " + reason.getName() + origen_campaign;
					}
				break;
				
				case 7:					
					if(other_reason != null)
					{
						reason_TOKEN = " " + other_reason + origen_campaign;
					}
				break;
				
				case 8:
					reason_TOKEN = " " + reason.getName() + origen_campaign;
					
					if(price_shoes_number != null && price_shoes_number.trim().length() > 0)
					{						
						reason_TOKEN += " con numero de socio " + price_shoes_number;						
					}
				break;
				
				default:
					reason_TOKEN = " " + reason.getName() + origen_campaign;
				break;
			}
			
			
			if(promotor != null && promotor_id != null && promotor_id > 0)
			{ 																	
				reason_TOKEN += " asignado al Promotor "+ promotor.getName();				
							
			} else {		
				
				reason_TOKEN += " sin promotor asignado ";				
			}			
		}
	}
}
