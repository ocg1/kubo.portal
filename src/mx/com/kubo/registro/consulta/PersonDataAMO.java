package mx.com.kubo.registro.consulta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import mx.com.kubo.model.Access;

public abstract class PersonDataAMO extends PersonDataDMO
{	
	protected void init_credit_history() 
	{
		if(flag)
		{						
			if(credit_history != null)
			{
				i = 2;
				flag = true;
				
			} else {
				
				flag = false;
			}
		}
	}

	protected void init_person() 
	{
		if(flag)
		{					
			if(person != null)
			{
				i = 3;
				flag = true;
				
			} else {
				
				flag = false;
			}
		}
	}

	protected void init_address() 
	{
		if(flag)
		{					
			if(address != null)
			{
				i = 4;
				flag = true;
				
			} else {
				flag = false;
			}
		}
	}

	protected void init_phone() 
	{
		if(flag)
		{					
			if(phone !=  null)
			{
				i = 5;
				flag = true;
				
			} else {
				
				flag = false;
			}
		}	
	}
	
	protected void init_message_ERROR() 
	{
		switch (i)
		{
			case 0:
				messageInit = "Error al cargar los datos del prospecto.";
				displayAction = false;
			break;
			
			case 1:
				messageInit = "Error al cargar los datos del historial crediticio.";
				displayAction = false;
			break;
			
			case 2:
				messageInit = "Error al cargar los datos de la persona.";
				displayAction = false;
			break;
				
			case 3:
				messageInit = "Error al cargar los datos de la direccion de la persona. Regrese al paso 1 para proporcionar datos faltantes. ";
				displayAction = false;
			break;
			
			case 4:
				messageInit = "Error al cargar los datos telefonicos de la persona. Regrese al paso 1 para proporcionar datos faltantes.";
				displayAction = false;
			break;
			
			case 5:
				messageInit = "";			
			break;
			
			default: break;
		}
	}
	
	protected void init_access()
	{		
		if (i==5)
		{	
			access = new Access();
			
			access.setCompany_id( sesion.getCompany_id() );
			access.setProspectus_id(sesion.getProspectus_id());
			access.setScreen_id(SCREEN_PERSON_DATA);
			access.setPercentage(100);
			access.setWeb_browser(sesion.getNamebrawser());
			access.setWeb_browser_version(sesion.getVersionbrawser());
			access.setOp_system(sesion.getOsbrawser());
			access.setHorizontal_size(sesion.getBrowser_width());
			access.setVertical_size(sesion.getBrowser_height());
			access.setIpaddress(sesion.getIP_address_client());
			access.setUser_agent(sesion.getUser_agent());
			access.setDevice_info(sesion.getDevice_info());
			access.setProspectus_id_coach( sesion.getCoachProspectus_id() );		
			access.setAccess_from		  (sesion.getAccess_from());
			access.setVersion_description (sesion.getVersion_description());
			
			access.setUrl_access		  (sesion.getUrl_access());
			
			service_access.add(access, true);	
		}
	}
		
	protected void init_address_TOKEN() 
	{
		if (i==5)
		{			
			addressStr = "";
			
			if(address.getStreet() != null && address.getStreet().length() > 0)
			{
				addressStr +=  " " + address.getStreet();
				
			} else {
				
				messageInit +=" <br /> Falta proporcionar Calle. ";
				displayAction = false;
			}
			
			boolean flagNumber = false;
			
			if(address.getAddress_number() != null && address.getAddress_number().length() > 0)
			{
				addressStr += " Número " +  address.getAddress_number();
				flagNumber = true;
			}
			
			if(address.getApt_number() != null && address.getApt_number().length() > 0)
			{
				addressStr += " Número interior " + address.getApt_number();
				flagNumber = true;
			}
			
			if(address.getMx_manzana() != null && address.getMx_manzana().length() > 0)
			{
				addressStr += " Manzana "+ address.getMx_manzana();
				flagNumber = true;
			}
			
			if(address.getMx_lote() != null && address.getMx_lote().length() > 0)
			{
				addressStr += " lote " + address.getMx_lote();
				flagNumber = true;
			}
			
			if(!flagNumber)
			{
				addressStr += " SIN NÚMERO ";
			}
			
			if(address.getNeighborhood() != null)
			{				
				addressStr += ", Colonia " + address.getNeighborhood().getName();
				
			} else if(address.getNeighborhood_text() != null ) {
				
				addressStr += ", Colonia " + address.getNeighborhood_text();
				
			} else {
				
				messageInit +=" <br /> Falta seleccionar tu Colonia. ";
				displayAction = false;
			}
			
			if(address.getTownCat() != null)
			{
				if(address.getStateCat() != null && address.getStateCat().getStateCatPK().getState_id() == 9)
				{
					addressStr += ", Delegación " + address.getTownCat().getName();
					
				} else {
					
					addressStr += ", Municipio " + address.getTownCat().getName();
				}
				
			} else {
				
				messageInit +=" <br /> La Delegación o Municipio no está definido. ";
				displayAction = false;
			}
			
			if(address.getStateCat() != null)
			{
				addressStr += ", " + address.getStateCat().getName();
				
			} else {
				
				messageInit += " <br /> El estado no está definido. ";
				displayAction = false;
				
			}
			
			if(address.getZip_code() != null && address.getZip_code().length() > 0)
			{
				addressStr += ", Código Postal "+address.getZip_code() + "";
				
			} else {
				
				messageInit += " <br /> El código postal no está definido. ";
				displayAction = false;
			}
		}
	}
	
	protected void init_credit_history_DATA() 
	{
		if(person != null )
		{				
			if(credit_history.getCar_is_principal() != null && credit_history.getCar_is_principal() == 1)
			{
				legendCard = "Cuenta con la tarjeta de crédito con terminación " + credit_history.getCreditcard_four_digits();
				
				if(credit_history.getCreditcard_company() != null && credit_history.getCreditcard_company().trim().length()>0)
				{
					legendCard += " otorgada por " + credit_history.getCreditcard_company(); 
				}
				
			} else {
				
				legendCard = "No cuenta con tarjeta de crédito";
			}
			
			if( credit_history.getMortgage_is_principal() != null && credit_history.getMortgage_is_principal() == 1)
			{
				legendMortgage = "Cuenta con crédito hipotecario";
				
				if(credit_history.getMortgage_company() != null && credit_history.getMortgage_company().trim().length() > 0)
				{
					legendMortgage +=" otorgado por " + credit_history.getMortgage_company(); 
				}
				
			} else {
				
				legendMortgage = "No cuenta con crédito hipotecario";
			}
			
			if(credit_history.getCar_is_principal() != null && credit_history.getCar_is_principal() == 1)
			{
				legendCar = "Cuenta con crédito automotriz";
				
				if(credit_history.getCar_company() != null && credit_history.getCar_company().trim().length() > 0)
				{
					legendCar += " otorgado por " + credit_history.getCar_company(); 
				}
				
			} else {
				
				legendCar = "No cuenta con crédito automotriz";
			}			
		}
	}

	protected void init_person_DATA() 
	{
		if(person != null)
		{		
			if(person.getDate_of_birth() != null)
			{
				SimpleDateFormat formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
				birthday = formatStr.format(person.getDate_of_birth());
				
				edad = calcularEdad(person.getDate_of_birth());
				
			} else {
				
				messageInit +=" <br /> La fecha de nacimiento no está definida. ";
				displayAction = false;
			}
			
			if(person.getMx_curp()==null || person.getMx_curp().trim().length()<1)
			{
				messageInit +=" <br /> La CURP no está definida. ";
				displayAction = false;
			}
			
			if(person.getMx_rfc()==null || person.getMx_rfc().trim().length()<1)
			{
				messageInit +=" <br /> La RFC no está definida. ";
				displayAction = false;
			}
			
			if(person.getGender_id()==null || person.getGender_id()==0)
			{
				messageInit +=" <br /> El género no está definido. ";
				displayAction = false;
			}
		}
	}
	
	private int calcularEdad(Date fecha) 
	{
		try 
		{
			Calendar birth = new GregorianCalendar();
			Calendar today = new GregorianCalendar();
			
			int age=0;
			int factor=0;
					
			Date birthDate= fecha;
			Date currentDate=new Date();
			
			birth.setTime(birthDate);
			today.setTime(currentDate);
			
			if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) 
			{
				if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) 
				{
					if (today.get(Calendar.DATE) > birth.get(Calendar.DATE)) 
					{
						factor = -1; //Aun no celebra su cumpleaños
					}
					
				} else {
					
					factor = -1; //Aun no celebra su cumpleaños
				}
			}
			
			age = (today.get(Calendar.YEAR)-birth.get(Calendar.YEAR)) + factor;
			
			return age;
			
		} catch (Exception e) {
			return -1;
		}
	}
	
	public void saveCreditHistory()
	{				
		service_credit_history.update(credit_history);	
	}
}
