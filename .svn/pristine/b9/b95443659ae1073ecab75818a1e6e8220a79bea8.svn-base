package mx.com.kubo.managedbeans.mesa.solicitud.perfil;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.InstitutionalInvestor;
import mx.com.kubo.model.InvestorCategory;

public abstract class FondeadorAMO extends FondeadorDMO
{	
	protected void init_investor_category_ENABLED() 
	{
		lista_investor_category = service_fondeador.getLista_investor_category(kubo_property);
		
		category_TOKEN = "";
		SEPARADOR = "::";
		
		if(lista_investor_category != null && lista_investor_category.size() > 0)
		{
			investor_category_ENABLED = true;						
			
			for(InvestorCategory investor_category : lista_investor_category)
			{
				category_id_ONLY_READ   = investor_category.getPk().getInvestor_category_id();				
				category_name_ONLY_READ = investor_category.getName();
				
				category_TOKEN += SEPARADOR + category_id_ONLY_READ + ";" + category_name_ONLY_READ;								
			}
			
		} else {
			
			investor_category_ENABLED = false;
		}
	}
	
	public void init_validar_condiciones()
	{		
		InstitutionalInvestor temp = null;
		
		condiciones_ENABLED = false;
		
		for(InstitutionalInvestor institucionalInv : lista_fondeadores)
		{
		
			if( kubo_property == institucionalInv.getInstitutionalInvestorPK().getInstitutional_investor_id() )
			{				
				temp = institucionalInv;
				
				break;				
			}		
		}
		
		if(temp != null)
		{		
			if(temp.getCredit_limit() != null)
			{				
				if(temp.getCredit_limit() >= ammount)
				{					
					condiciones_ENABLED = true;
					
					if( temp.getTerm_limit() != null )
					{						
						condiciones_ENABLED = validaTerm( temp.getTerm_limit() , term_id, frequency_id);						
					}
					
				}
				
			} else {
				
				condiciones_ENABLED = true;
				
				if(  temp.getTerm_limit() != null )
				{					
					condiciones_ENABLED = validaTerm( temp.getTerm_limit() , term_id, frequency_id);					
				}				
			}			
		}				
	}
	
	public boolean validaTerm( Integer termInst , Integer creditTerm , Integer freq )
	{
		
		boolean flagT = false;
		
		factor = 0f;
		
		switch (freq)
		{			
			case SEMANAL:					
				factor = 52f/12f;
			break;
				
			case CATORCENAL:					
				factor = 26f/12f;					
			break;
				
			case QUINCENAL:						
				factor = 24f/12f;					
			break;
				
			case MENSUAL:					
				factor = 12f/12f;				
			break;			
		}
		
		Float term = (factor * creditTerm);
			
		if(termInst < term)
		{				
			flagT = false;
			
		} else {
			
			flagT = true;
		}
					
		return flagT;		
	}
	
	protected void init_change_control()
	{
		changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(change_prospectus_id);
		changeCtrl.setAfected_table("ln_proyect_loan");
		changeCtrl.setField("is_kubo_property");
		changeCtrl.setIp(IP_address_client);			
		changeCtrl.setOriginal_value(institutional_investor_ORIGINAL);
		changeCtrl.setNew_value     (institutional_investor_NEW);		
		changeCtrl.setComments(comment);
		
		change_control_OK = service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id);
		
		if(change_control_OK)
		{										
			bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, new String[]{"ln_proyect_loan"},new String[]{"is_kubo_property"});
			
			change_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
			change_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);					
		}
	}
}
