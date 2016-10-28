package mx.com.kubo.repositories.impl;

import mx.com.kubo.model.Address;

public abstract class AddressDaoAMO extends  AddressDaoDMO
{
	protected void init_filtro_NIVEL_2(int nivel_filtro, Address address, StringBuilder sb) 
	{		
		if(nivel_filtro == NIVEL_2)
		{
			if(address.getAddress_number() != null &&  !address.getAddress_number().trim().equals("") )
			{
				sb.append("and address_number = '").append(address.getAddress_number()).append("' ");
				
			} else {
				
				sb.append("and (address_number is null or address_number = '')");
			}
			
			if(address.getApt_number() != null &&  !address.getApt_number().trim().equals("") )
			{
				sb.append("and apt_number = '").append(address.getApt_number()).append("' ");
				
			} else {
				
				sb.append("and (apt_number is null or apt_number = '')");
			}
			
			if(address.getMx_manzana() != null &&  !address.getMx_manzana().trim().equals("") )
			{
				sb.append("and mx_manzana = '").append(address.getMx_manzana()).append("' ");
				
			} else {
				
				sb.append("and (mx_manzana is null or apt_number = '')");
			}
			
			if(address.getMx_lote() != null  &&  !address.getMx_lote().trim().equals("") )
			{
				sb.append("and mx_lote = '").append(address.getMx_lote()).append("' ");
				
			} else {
				
				sb.append("and (mx_lote is null or mx_lote = '') ");
			}
		}	
	}
	
	protected void init_address_type_id(int nivel_filtro, Address address, StringBuilder sb) 
	{
		if(nivel_filtro == NIVEL_1)
		{
			switch(address.getAddress_type_id())
			{
				case OFICINA:
				case NEGOCIO:
				case EMPLEO:
					sb.append("and address_type_id in (2, 3, 4) ");
				break;
					
				default:
					sb.append("and address_type_id not in (2, 3, 4) ");
				break;
			}
		}
		
		sb.append("order by address_type_id ");
	}
}