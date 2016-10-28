package mx.com.kubo.managedbeans.mesa.solicitud.perfil;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.InvestorCategory;

import org.primefaces.context.RequestContext;

public class FondeadorIMP extends FondeadorAMO
implements FondeadorIMO 
{
	public final void init()
	{
		bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, new String[]{"ln_proyect_loan"},new String[]{"is_kubo_property"});				
		
		change_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
		change_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);
		
		lista_fondeadores = service_fondeador.getListInstInvestor();					
	}
	
	public final void init_investor_category(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		init_investor_category_ENABLED();
		
		request.addCallbackParam("kubo_property", kubo_property);
		request.addCallbackParam("category_id", investor_category_id);
		request.addCallbackParam("investor_category_ENABLED", investor_category_ENABLED);
		request.addCallbackParam("category_TOKEN", category_TOKEN);
	}

	public final void init_kubo_property(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		kubo_property = Integer.parseInt(select_one_menu.getValue().toString());				
		
		investor_category_id = NOT_SELECTED;
		
		init_investor_category_ENABLED();
		
		request.addCallbackParam("kubo_property", kubo_property);
		request.addCallbackParam("category_id", investor_category_id);
		request.addCallbackParam("investor_category_ENABLED", investor_category_ENABLED);
		request.addCallbackParam("category_TOKEN", category_TOKEN);
	}
	
	public final void init_investor_category_id(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		investor_category_id = Integer.parseInt(input_text.getValue().toString());		
		
		request.addCallbackParam("investor_category_id", investor_category_id);
	}
			
	public void init_crear_cartera()
	{				 				
		request = RequestContext.getCurrentInstance();
		
		init_validar_condiciones();
		
		if(condiciones_ENABLED)
		{										
			proyect_loan.setIs_kubo_property(kubo_property);
			
			if(investor_category_id > NOT_SELECTED)
			{
				proyect_loan.setInvestor_category_id(investor_category_id);
				
			} else {
				
				proyect_loan.setInvestor_category_id(null);
			}
			
			update_OK = service_proyect_loan.update(proyect_loan);
		
			if(update_OK)
			{
				proyect_loan = service_proyect_loan.findProyect(proyect_loan.getProyectloanPk());
				
				institutional_investor_NEW = proyect_loan.getInstitutionalInvestor().getName();
				
				for(InvestorCategory investor_category : lista_investor_category)
				{
					investor_category_name = "";
					
					if(investor_category_id == investor_category.getPk().getInvestor_category_id())
					{
						investor_category_name = investor_category.getName();
						
						break;
					}
				}								
				
				if(investor_category_ENABLED && investor_category_id > NOT_SELECTED)
				{
					sb = new StringBuilder();
					sb.append(change_bean.getWhyChangeData());
					sb.append(" :: Categoría :: ");
					sb.append(investor_category_name);
					
					comment = sb.toString();
					
				} else {
				
					comment = change_bean.getWhyChangeData();
				}
				
				init_change_control();				
				
				request.addCallbackParam( "isValidInvestor" , true);
								
			} else {
			
				request.addCallbackParam( "isValidInvestor" , false);
				request.addCallbackParam("investorMessage", "Ocurrio un error al actualizar el proyecto");			
			}
		
		} else {
			
			request.addCallbackParam( "isValidInvestor" , false);
			request.addCallbackParam("investorMessage", "No se actualizó el fondeador ya que los parametros de plazo o monto no cumplen con los máximos especificados por el fondeador.");			
		}		
	}
}
