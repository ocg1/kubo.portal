package mx.com.kubo.managedbeans.registro.beneficiarios;

import java.util.ArrayList;

import mx.com.kubo.bean.AddressBean;
import mx.com.kubo.bean.Benefi_ciaries;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;
import mx.com.kubo.model.SavingAccount;

public abstract class IbeneficiariesAMO extends IbeneficiariesDMO
{
	protected void init_cuenta() 
	{
		listAccount = accountService.getListAccountByProspect(prospectus_id, company_id);
		
		listBenefic = new ArrayList<Beneficiaries>();
		
		if(listAccount.size() != 0)
		{	
			for(SavingAccount element: listAccount)
			{
				actualAccountID   = element.getSaving_accountPk().getSaving_account_id();
				descriptioAccount = element.getDescription();
				
				break;
			}			
			
			listBenefic = service_beneficiarios.getListBeneficByProspectByAccount(prospectus_id, company_id, actualAccountID);		
		}	
	}

	protected void init_beneficiario_NEW() 
	{
		beneficiario_PK = new BeneficiariesPK(prospectus_id, company_id);
		beneficiario_PK.setSaving_account_id(actualAccountID);
		
		beneficiarie = new Beneficiaries();
		beneficiarie.setBeneficPk(beneficiario_PK);	
		
		is_save_OK = service_beneficiarios.addBeneficiaries(beneficiarie, prospectus_id, company_id);
		
		if(is_save_OK)
		{			
			log.info("Nuevo Beneficiario agregado ");
			
			listBenefic = service_beneficiarios.getListBeneficByProspectByAccount(prospectus_id, company_id, actualAccountID);
			
			listBenefi_ciaries = new ArrayList<Benefi_ciaries>();
			
			int i = 0;
			
			for(Beneficiaries elementBen: listBenefic)
			{
				i++;
				
				beneficiario_bean = new Benefi_ciaries();				
				beneficiario_bean.setVarStatus(i);
				beneficiario_bean.setProspectus_id    (elementBen.getBeneficPk().getProspectus_id());
				beneficiario_bean.setCompany_id       (elementBen.getBeneficPk().getCompany_id());
				beneficiario_bean.setSaving_account_id(elementBen.getBeneficPk().getSaving_account_id());
				beneficiario_bean.setBeneficiarie_id  (elementBen.getBeneficPk().getBeneficiarie_id());
				
				beneficiario_id = beneficiario_bean.getBeneficiarie_id();
				
				address = service_address.getMaxAddressByBeneficiario(prospectus_id, company_id, beneficiario_id);
				
				//TODO
				//beneficiario_bean.setEditor_vivienda(init_editor_vivienda());
				
				listBenefi_ciaries.add(beneficiario_bean);
			}
			
		} else {
			
			log.info("Error al agregar el beneficiario");
		}
	}

	protected void init_lista_beneficiarios() 
	{
		listBenefi_ciaries = new ArrayList<Benefi_ciaries>();
		
		int i = 0;
		
		for(Beneficiaries elementBen: listBenefic)
		{							
			i++;
			
			beneficiario_bean = new Benefi_ciaries();				
			beneficiario_bean.setVarStatus(i);
			beneficiario_bean.setProspectus_id    (elementBen.getBeneficPk().getProspectus_id());
			beneficiario_bean.setCompany_id       (elementBen.getBeneficPk().getCompany_id());
			beneficiario_bean.setSaving_account_id(elementBen.getBeneficPk().getSaving_account_id());
			beneficiario_bean.setBeneficiarie_id  (elementBen.getBeneficPk().getBeneficiarie_id());
			beneficiario_bean.setFirst_name       (elementBen.getFirst_name());
			beneficiario_bean.setMiddle_name      (elementBen.getMiddle_name());
			beneficiario_bean.setFather_last_name (elementBen.getFather_last_name());
			beneficiario_bean.setMother_last_name (elementBen.getMother_last_name());
			beneficiario_bean.setGender_id        (elementBen.getGender_id());
			
			if(elementBen.getPercentage() != null)
			{
				beneficiario_bean.setPercentage(Double.valueOf(elementBen.getPercentage() +"" ));
			}
			
			beneficiario_bean.setRelationship     (elementBen.getRelationship());
			beneficiario_bean.setSame_address     (elementBen.getSame_address());
			
			beneficiario_id = beneficiario_bean.getBeneficiarie_id();
			
			address = service_address.getMaxAddressByBeneficiario(prospectus_id, company_id, beneficiario_id);
			
			addressbean = new AddressBean();
			
			if(address == null)
			{
				address    = new Address();	
				address_PK = new AddressPK();
				
				address_id = service_address.getMaxAddressId(prospectus_id, company_id);
				
				address_PK.setAddress_id(address_id);		
				address_PK.setProspectus_id(prospectus_id);
				address_PK.setCompany_id(company_id);
				
				address.setAddressPK(address_PK);
				address.setBeneficiarie_id(beneficiario_id);
				address.setCountry_id(700);
				address.setAddress_type_id(6);
				
				is_save_OK = service_address.add(address);
				
			} else {								
				
				zipcode         = address.getZip_code();
				gn_state        = address.getStateCat();
				gn_town         = address.getTownCat();
				
				addressbean.setStreet(address.getStreet());								
				addressbean.setAddress_number   (address.getAddress_number());
				addressbean.setApt_number (address.getApt_number());
				addressbean.setMx_manzana (address.getMx_manzana());
				addressbean.setMx_lote    (address.getMx_lote());
				addressbean.setDescription(address.getDescription());				
				addressbean.setFirst_street_reference (address.getFirst_street_reference());
				addressbean.setSecond_street_reference(address.getSecond_street_reference());				
				addressbean.setBeneficiario_id(beneficiario_id);
				
				neighborhood_id   = address.getNeighborhood_id();
				neighborhood_text = address.getNeighborhood_text();								
				
				if(neighborhood_id == null)
				{
					neighborhood_text_ENABLED = neighborhood_text != null ? "block" : "none";
					
					neighborhood_id_ENABLED = "none";
				}	
				
				if(neighborhood_text == null)
				{
					neighborhood_text_ENABLED = "none";
					
					neighborhood_id_ENABLED   = "block";
				}
								
				addressbean.setNeighborhood_id  (neighborhood_id);
				addressbean.setNeighborhood_text(neighborhood_text);
				addressbean.setNeighborhood_id_ENABLED(neighborhood_id_ENABLED);
				addressbean.setNeighborhood_text_ENABLED(neighborhood_text_ENABLED);
				
			   if(zipcode != null)
			   {
				   lista_neighborhood = service_employment.getAsentamientosByCP(zipcode);
				   
				   addressbean.setListNeighborhood(lista_neighborhood);
				   addressbean.setZip_code(zipcode);				
			   }				   			   
					   
			   if(gn_town != null && gn_state != null)
			   {
				   addressbean.setTown_id  (gn_town.getTownCatPK().getTown_id());
				   addressbean.setTownName (gn_town.getName());			   
				   addressbean.setStateName(gn_state.getName());				   
				   addressbean.setState_id (gn_state.getStateCatPK().getState_id());
			   }				   					  
			} 
			
			addressbean.setAddress(address);		
			   
			beneficiario_bean.setAddressbean(addressbean);
						
			//TODO
			//beneficiario_bean.setEditor_vivienda(init_editor_vivienda());
			
			init_fecha_nacimiento(elementBen);
			
			listBenefi_ciaries.add(beneficiario_bean);
		}
	}
	
	private void init_fecha_nacimiento(Beneficiaries elementBen) 
	{
		if(elementBen.getDate_of_birth() != null)
		{
			String dStr = date_format.format(elementBen.getDate_of_birth());
			
			String dd = dStr.split("/")[0];
			
			dd = "" + Integer.parseInt(dStr.split("/")[0]);
			
			beneficiario_bean.setDay(dd);
			beneficiario_bean.setMonth(monthStr[Integer.parseInt(dStr.split("/")[1]) - 1]);				
			beneficiario_bean.setYear(dStr.split("/")[2]);
		}
	}
	
	protected void init_porcentaje() 
	{
		for (int i = 0; i <= 100; i++) 
		{
			listPercentage.add(""+i);
		}	
	}
}
