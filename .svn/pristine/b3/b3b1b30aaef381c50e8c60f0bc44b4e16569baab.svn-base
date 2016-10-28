package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import mx.com.kubo.model.Address;
import mx.com.kubo.tools.Utilities;

public final class AddressTokenIMP extends AddressTokenAMO
implements AddressTokenIMO
{	
	public final String getInvestor_TOKEN()
	{
		sb = new StringBuilder();
		
		sb.append(antiguedad);
		sb.append("En una vivienda ");
		sb.append(residence_TOKEN);
		sb.append(" ubicada en");
		sb.append(town);
		sb.append(estado);
		
		address_TOKEN = Utilities.capilizeString(sb.toString());
		
		return address_TOKEN;
	}
	
	public final String getAddress_activity_TOKEN()
	{						
		init_activity_TOKEN();	
		
		return address_TOKEN;
	}	

	public final String getAddress_beneficiario_TOKEN()
	{		
		sb = new StringBuilder();
		
		sb.append(street);
		sb.append(address_number);
		sb.append(apt_number);
		sb.append(mx_manzana);
		sb.append(mx_lote);
		sb.append(colonia);
		sb.append(town);
		sb.append(estado);
		sb.append(zip_code);
		
		address_TOKEN = sb.toString();
		
		if(address_TOKEN.length() > 0)
		{
			address_TOKEN = Utilities.capilizeString(address_TOKEN);
		}				
		
		return address_TOKEN;
	}
	
	public final String getAddress_TOKEN()
	{		
		sb = new StringBuilder();
		
		sb.append(antiguedad);
		sb.append("En una vivienda ");
		
		if( residence_TOKEN != null ){
			sb.append(residence_TOKEN);
		}
		
		sb.append(" ubicada en");
		sb.append(street);
		sb.append(address_number);
		sb.append(apt_number);
		sb.append(mx_manzana);
		sb.append(mx_lote);
		sb.append(colonia);
		sb.append(town);
		sb.append(estado);
		sb.append(zip_code);
		sb.append(first_street_reference);
		sb.append(second_street_reference);
		sb.append(description);
		
		address_TOKEN = Utilities.capilizeString(sb.toString());
		
		return address_TOKEN;
	}
}
