package mx.com.kubo.mesa.solicitud.perfil;

import java.util.ArrayList;

import mx.com.kubo.bean.EconActivityBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Employment;

public abstract class ActividadEconomicaAMO extends ActividadEconomicaDMO
{
	protected void init_employment()
	{		
		index = 0;		
		lista_actividad_economica = new ArrayList<EconActivityBean>();				
		
		for(Employment employment : lista_employment)
		{
			this.employment = employment;						
			
			init_actividad_economica();
			//init_employ_address_TOKEN();	
			//init_employ_permanencia();
		}
	}
	
	private void init_actividad_economica() 
	{				
		actividad_economica = new EconActivityBean();
		
		//address_empleo  = employment.getAddressEmploy();
		bmx_activity_id = employment.getBmx_econ_activity_id();
		
		if(bmx_activity_id != null)
		{
			bmx = service_view_client_info.getEconomicById(bmx_activity_id);
			
			actividad_economica.setActivityDesc(bmx.getDescription());
			actividad_economica.setBmxActivity(bmx);
			actividad_economica.setHasEconActivity(true);
			actividad_economica.setIcon_ENABLED(false);
			
		} else {
						
			actividad_economica.setActivityDesc("no definido");
			actividad_economica.setHasEconActivity(false);
			actividad_economica.setIcon_ENABLED(true);
		}
		
		actividad_economica.setLabelCompletDesc(female ? "Empleada del giro ":"Empleado del giro ");
		actividad_economica.setTypeEconActivity(employment);
		actividad_economica.setHasEconActivity(hasEconActivity);
		actividad_economica.setIndex(index);
		
		index++;
		
		lista_actividad_economica.add(actividad_economica);
	}	
	
	/*
	private void init_employment_TOKEN() 
	{
		if(address != null)
		{
			String latitude       = address.getLatitude();
			String longiud        = address.getLongitude();
			String street         = address.getStreet();
			String number         = address.getAddress_number();
			String number_apt     = address.getApt_number();
			String manzana        = address.getMx_manzana();
			String lote           = address.getMx_lote();
			String zip_code       = address.getZip_code();
			String referencia_UNO = address.getFirst_street_reference();
			String referencia_DOS = address.getSecond_street_reference();
			String descripcion    = address.getDescription();
			
			NeighborhoodCat colonia = address.getNeighborhood();
			TownCat town_cat        = address.getTownCat();
			StateCat state_cat      = address.getStateCat();
			
			if(nameVisible)
			{
				//latlongMap += "" + latitude + "," + longiud + ",Empleo::";
				
				//employAddressStr = "";
				sb = new StringBuilder();
				
				if(street != null && street.length() > 0)
				{
					employAddressStr += " " + street;
				}
				
				if(number != null && number.length() > 0 && !number.trim().equals("0") && !number.trim().equals("00"))
				{
					employAddressStr += " número " + number;
				}
				
				if(number_apt != null && number_apt.length() > 0 && !number_apt.trim().equals("0") && !number_apt.trim().equals("00"))
				{
					employAddressStr += " número interior " + number_apt;
				}
				
				if(manzana != null && manzana.length() > 0 && !manzana.trim().equals("0") && !manzana.trim().equals("00"))
				{
					employAddressStr += " manzana " + manzana;
				}
				
				if(lote != null && lote.length() > 0 && !lote.trim().equals("0") && !lote.trim().equals("00"))
				{
					employAddressStr += " lote " + lote;
				}
				
				if(colonia != null)
				{
					employAddressStr += ", colonia " + colonia.getName();
			    }
			
				if(town_cat != null)
				{
					if(state_cat != null && state_cat.getStateCatPK().getState_id()==9)
					{
						employAddressStr += ", delegación " + town_cat.getName();
						
					} else {
						
						employAddressStr += ", municipio " + town_cat.getName();
					}
				}
				
				if(state_cat != null)
				{
					employAddressStr += ", " + state_cat.getName();
				}
				
				if(zip_code != null && zip_code.length() > 0)
				{
					employAddressStr += ", Código Postal " + zip_code;
				}
									
				employAddressStr += ", entre "+  referencia_UNO + " y " + referencia_DOS;
				
				if(descripcion != null)
				{					
					employAddressStr += " , " + descripcion;					
				}
				
				employAddressStr = Utilities.capilizeString(employAddressStr);															
			}
		}
	}
*/
	
/*	
	private void init_employ_permanencia() 
	{
		if(employment.getTenure_id() != null)
		{
			auxTenure = service_employment.getTenure(employment.getTenure_id()); 
			
		} else if(employment.getTenure() != null && employment.getTenure_month() != null) {
			
			if(employment.getTenure() == 0 && employment.getTenure_month() == 0)
			{
				auxTenure = "0 Meses.";
				
			} else {
				
				if(employment.getTenure() > 1)
				{
					auxTenure = employment.getTenure() + " años ";
					
				} else if(employment.getTenure() == 0)
				{
					auxTenure = " ";
					
				} else {
					
					auxTenure = employment.getTenure() + " año ";
				}
				
				if(employment.getTenure_month() > 1)
				{
					auxTenure = auxTenure + employment.getTenure_month() + " meses.";
					
				} else if(employment.getTenure_month() == 0) {
					
					auxTenure = auxTenure + employment.getTenure_month() + " meses.";
					
				} else {
					
					auxTenure = auxTenure + employment.getTenure_month() + " mes.";
				}
			}
			
		} else if(employment.getTenure() != null) {
			
			auxTenure = employment.getTenure() > 1 ? employment.getTenure() + " años.":employment.getTenure()+" año.";
			
		} else if(employment.getTenure_month() != null) { 
			
			auxTenure = employment.getTenure_month() > 1 ? employment.getTenure_month() + " meses.":employment.getTenure_month() + " mes.";
			
		} else {					
			
			auxTenure = "0 Meses.";
		}
	}
*/	
	
	protected void init_business() 
	{				
		for(Business business : lista_business)
		{			
			this.business = business;
			
			actividad_economica = new EconActivityBean();
			
			bmx_activity_id = business.getBmx_econ_activity_id();
			
			if(bmx_activity_id != null)
			{
				bmx = service_view_client_info.getEconomicById(bmx_activity_id);
			
				actividad_economica.setActivityDesc(bmx.getDescription());
				actividad_economica.setBmxActivity(bmx);
				actividad_economica.setHasEconActivity(true);
				actividad_economica.setIcon_ENABLED(false);
				
			} else {
								
				actividad_economica.setActivityDesc("no definido");
				actividad_economica.setHasEconActivity(false);
				actividad_economica.setIcon_ENABLED(true);
			}
			
			actividad_economica.setLabelCompletDesc(female ? "Empresaria del giro " : "Empresario del giro ");
			actividad_economica.setTypeEconActivity(business);			
			actividad_economica.setIndex(index);
			
			index++;
			
			lista_actividad_economica.add(actividad_economica);
						
/*			
			addressB          = business.getAddressBusiness();				
			years_since_when  = business.getYears_since_when();
			months_since_when = business.getMonths_since_when();
			
			init_business_address_TOKEN();
			init_business_antiguedad();
*/			
		}
	}
	
/*	
	private void init_business_antiguedad() 
	{			
		if(business.getTenure_id() != null)
		{
			auxYears = service_employment.getTenure(business.getTenure_id()); 
			
		} else if(years_since_when != null && months_since_when != null) {
			
			if(years_since_when == 0 && months_since_when == 0)
			{
				auxYears = "0 Meses.";
				
			} else {
				
				if(years_since_when > 1)
				{
					auxYears = years_since_when + " años ";
					
				} else if(years_since_when == 0) {
					
					auxYears = " ";
					
				} else {
					
					auxYears = years_since_when + " año ";
				}
				
				if(months_since_when > 1)
				{
					auxYears = auxYears + months_since_when + " meses.";
					
				} else if(months_since_when == 0) {
					
					auxYears = auxYears + months_since_when + " meses.";
					
				} else {
					
					auxYears = auxYears + months_since_when + " mes.";
				}
			}
			
		} else if(years_since_when != null) {
			
			auxYears = years_since_when > 1 ? years_since_when + " años." : years_since_when + " año.";
			
		} else if(months_since_when != null) {
			
			auxYears = months_since_when > 1 ? months_since_when+" meses." : months_since_when + " mes.";
			
		} else {
			
			auxYears = "0 Meses.";
		}
	}
*/	

/*	
	private void init_business_address_TOKEN() 
	{
		if(addressB != null)
		{
			if(nameVisible)
			{
				businessAddressStr ="";
				
				if(addressB.getStreet()!=null&&addressB.getStreet().length()>0)
				{
					businessAddressStr += " "+addressB.getStreet();
				}
				
				if(addressB.getAddress_number()!=null&&addressB.getAddress_number().length()>0)
				{
					businessAddressStr += " número "+addressB.getAddress_number();
				}
				
				if(addressB.getApt_number()!=null&&addressB.getApt_number().length()>0)
				{
					businessAddressStr += " número interior "+addressB.getApt_number();
				}
				
				if(addressB.getMx_manzana()!=null&&addressB.getMx_manzana().length()>0)
				{
					businessAddressStr += " manzana "+addressB.getMx_manzana();
				}
				
				if(addressB.getMx_lote()!=null&&addressB.getMx_lote().length()>0)
				{
					businessAddressStr += " lote "+addressB.getMx_lote();
				}
				
				if(addressB.getNeighborhood()!=null)
				{
					businessAddressStr += ", colonia "+addressB.getNeighborhood().getName();
				}
				
				if(addressB.getTownCat()!=null)
				{
					if(addressB.getStateCat()!=null&&addressB.getStateCat().getStateCatPK().getState_id()==9)
					{
						businessAddressStr += ", delegación "+addressB.getTownCat().getName();
					} else  {
						businessAddressStr += ", municipio "+addressB.getTownCat().getName();
					}
				}
				
				if(addressB.getStateCat()!=null)
				{
					businessAddressStr += ", "+addressB.getStateCat().getName();
				}
				
				if(addressB.getZip_code()!=null&&addressB.getZip_code().length()>0)
				{
					businessAddressStr += ", Código Postal "+addressB.getZip_code()+"";
				}
									
				businessAddressStr += ", entre "+  addressB.getFirst_street_reference() + " y "+addressB.getSecond_street_reference();
				
				if(addressB.getDescription()!=null)
				{
					
					businessAddressStr += " , "+addressB.getDescription();
					
				}												
				
				businessAddressStr = Utilities.capilizeString(businessAddressStr);
				
				latlongMap += ""+addressB.getLatitude()+","+addressB.getLongitude()+",Negocio::";
				
			}
		}
	}
*/	
		
	protected void guardar_business() 
	{
		if(tipo_de_actividad instanceof Business)
		{
			business = (Business) tipo_de_actividad;
			
			business.setBmx_econ_activity_id   (bmx_activity_id);
			business.setInegi_econ_activity_id (bmx_inegi_id);
			business.setEcon_sector_id         (bmx_sector_id);
			
			update_OK = service_business.update(business);
			
			if(update_OK)
			{
				if(saveChangeData("gn_business","bmx_econ_activity_id", actividad_LABEL, bmx_LABEL, motivo_del_cambio))
				{
					actividad_economica = lista_actividad_economica.get(index);	
					
					actividad_economica.setActivityDesc(bmx_LABEL);
					actividad_economica.setWhyChangeData(null);
					
					lista_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, afected_tables, afected_field);
				 
					change_control.setHasChange(true);
					change_control.setLstChanges(lista_change_control!=null && lista_change_control.size()>0?lista_change_control:null);
				}
			}
		} 
	}
	
	protected void guardar_employment() 
	{
		if(tipo_de_actividad instanceof Employment) 
		{
			
			employment = (Employment) tipo_de_actividad;
			
			employment.setBmx_econ_activity_id   (bmx_activity_id);
			employment.setInegi_econ_activity_id (bmx_inegi_id);
			employment.setEcon_sector_id         (bmx_sector_id);
			
			update_OK = service_employment.updateEmployment(employment);
			
			if(update_OK)
			{
				if(saveChangeData("gn_employment","bmx_econ_activity_id",actividad_LABEL,bmx_LABEL, motivo_del_cambio))
				{
					actividad_economica = lista_actividad_economica.get(index);
					actividad_economica.setActivityDesc(bmx_LABEL);
					actividad_economica.setWhyChangeData(null);
					
					lista_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, afected_tables, afected_field);
					
					change_control.setHasChange(true);
					change_control.setLstChanges(lista_change_control!=null && lista_change_control.size()>0?lista_change_control:null);
				}
			}
		}
	}
	
	protected boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{
		changeCtrl   = new Change_control();
		changeCtrlPK = new Change_controlPK();		
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);		
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(mesa_prospectus_id);
		changeCtrl.setIp(ipAddressClient);
		changeCtrl.setAfected_table(table);				
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
		} else {
			return false;
		}
	}
}
