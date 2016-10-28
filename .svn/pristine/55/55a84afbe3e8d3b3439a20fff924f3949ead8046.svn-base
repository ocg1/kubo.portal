package mx.com.kubo.mesa.solicitud.telefonos;

import java.util.ArrayList;

import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.model.Phone;

public class PhoneReviewIMP extends PhoneReviewAMO 
implements PhoneReviewIMO
{		
	public final void init() 
	{
		index = 0;
		
		lista_phone_view = new ArrayList<PhoneReview>();
		
		for(Phone phone : lista_phone_prospectus)
		{				
			init_phone_type_name(phone);		
			init_phone_view(phone);
			init_phone_number(phone);																													
			init_has_references(phone);						
			
			lista_phone_view.add(view);																			
		}		
	}
}
