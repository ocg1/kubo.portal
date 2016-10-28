package mx.com.kubo.mesa.solicitud.promo;

public class PromocionIMP extends PromocionDMO
implements PromocionIMO
{
	public final void init()
	{
		init_promo();
	}
	
	private void init_promo() 
	{
		String code;
		
		String r_data_split [];
		
		if(r_data != null)
		{
			r_data = r_data.trim();
			
			r_data_split = r_data.split(" ", 3);
			
			if(r_data_split.length > 2)
			{
				code = r_data_split[2];
				
				promo = service_catalogos.getPromo(code);
			}
		}						
		
		if(promo != null)
		{						
			promo_ENABLED = true;
		}
	}
}
