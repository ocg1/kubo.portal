package mx.com.kubo.managedbeans.mesa;

import java.util.ArrayList;
import java.util.ResourceBundle;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.RoleFunction;

public abstract class ActivityPersonPMO extends ActivityPersonAMO 
{			
	protected void init_permisos() 
	{
		lista_funciones = role_function.getFunctionByRole(sesion.getRole_id());
		
		for(RoleFunction role_function : lista_funciones)
		{
			int function_id = role_function.getPk().getFunction_id();
			
			if(function_id == FUNCTION_BORRAR_INTENTOS_CONSULTA)
			{  				
				borrar_intentos_ENABLED = true;		
				
				break;
			}
		}
	}
	
	protected void init_bitacora_change_control() 
	{
		change_control_bean = new ChangeBean();
		change_control_bean.setOrigValue("");
		change_control_bean.setNewValue("");
		change_control_bean.setNameTable("gn_credit_history_attempt");
		change_control_bean.setNameField("attempt_id");
			
		bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
		change_control_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
		change_control_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);
	}

	protected void init_registration_reason()
	{
		boolean flag = false;
		
		registrationReason = "";
		
		if(membership !=null && membership.getRegistration_reason()!=null)
		{
			if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 7)
			{ //Otro
				if(membership.getOther_registration_reason()!=null)
				{
					flag = true;
					registrationReason = " "+membership.getOther_registration_reason();
				}
				
			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 3){
				
				if(membership.getWho_recommends()!=null)
				{
					flag = true;
					registrationReason = " Recomendado por "+membership.getWho_recommends();
				}else{
					flag = true;
					registrationReason = " "+membership.getRegistration_reason().getName();
				}
				
			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 8){ //PriceShoes
				
					flag = true;
					registrationReason = " "+membership.getRegistration_reason().getName();
					
					if(membership.getPriceshoes_number()!=null && membership.getPriceshoes_number().trim().length() >0 )
					{					
						registrationReason = " con numero de socio "+membership.getPriceshoes_number();						
					}
					
			}else if(membership.getRegistration_reason() != null && membership.getRegistration_reason_id() != 6 ){
				
				flag = true;
				registrationReason = " "+membership.getRegistration_reason().getName();
				
			}
			
			if(membership.getPromotor_id() != null && membership.getPromotor_id() > 0)
			{ //Promotor				
				if(membership.getPromotor()!=null)
				{					
					if(flag )
					{					
						registrationReason += " asignado al Promotor "+membership.getPromotor().getName();
						
					} else {
						
						registrationReason = " Promotor "+membership.getPromotor().getName();
						
					}					
				}
				
			} else {
				
				registrationReason += " sin promotor asignado ";				
			}			
		}	
	}
	
	protected void init_telefonos()
	{		
		listPhone = telefonoService.getPhoneByProspectusList(prospectus_id, company_id);
		
		for(Phone p : listPhone)
		{
		
			switch (p.getPhone_type_id())
			{			
				case 1:
				case 2:
					p.getPhoneType().setName(p.getPhoneType().getName()+"/Trabajo");
					
					break;
				
				case 3:
				case 4:
					p.getPhoneType().setName(p.getPhoneType().getName()+"/Negocio");
					
					break;
				
				case 5:
					p.getPhoneType().setName(p.getPhoneType().getName()+"/Casa");
					
					break;
				
				case 6:
					p.getPhoneType().setName(p.getPhoneType().getName()+"/Propio");
					
					break;					
			}
		}		
	}
	
	protected void init_menu()
	{		
		recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		score_A = null;
		
		try
		{
			lista_menu_access = accessService.loadMenu(prospectus_id, company_id, membership.getPerson().getProspectus().getArea());
			
			menus = new ArrayList<MenuRegBean>();
			
			int i = 1;
			
			for(AccessCollector access_collector: lista_menu_access)
			{
				 menu = new MenuRegBean();							
				
				resource_name = access_collector.getResource_name();
				target_item   = access_collector.getName();
				obligatorio   = access_collector.getIs_obligatory();
				
				menu_order   = access_collector.getMenu_order(); 
				screen_id    = access_collector.getScreen_id();
				
				int porcentage = ((access_collector.getPercentage() == null) ? 0 : access_collector.getPercentage());
								
				menu.setPosition(i);
				menu.setIdItem("menu" + menu_order);
				
				menu.setNumItem("menuItemNum");
				menu.setPorcClassItem("porcent");
				
				menu.setScreenid(screen_id);
				menu.setTargetItem(target_item);				
				menu.setPorcItem(porcentage);
				menu.setNameItem(recurso.getString(resource_name));

				if(screen_id == 6 )
				{
					menu.setDisplayBar(false);
				} else {
					
					menu.setDisplayBar(true);
				}
			
				if(obligatorio != null && obligatorio.equals("S") )
				{
					menu.setObligatory("S");
					
				} else {
					
					menu.setObligatory("N");
				}
							
				
				if(!flagBCScore && i > 2 && !(membership.getPerson().getProspectus().getArea() + "").equals("I"))
				{
					menu.setIsblocked("block");
					menu.setIsConBlocked("none");
					menu.setClassItem("menuItemUnHover");
					
				} else {
												
					if(i > 2 && !(membership.getPerson().getProspectus().getArea() + "").equals("I") && score != null)
					{
						score_A = score.getKubo_score_a();
						
						if(score_A == null || ! score_A.equals("N"))
						{
							menu.setIsblocked("none");
							menu.setIsConBlocked("block");
							menu.setClassItem("menuItem");
							
						} else {
							menu.setIsblocked("block");
							menu.setIsConBlocked("none");
							menu.setClassItem("menuItemUnHover");
						}
						
					} else {
						menu.setIsblocked("none");
						menu.setIsConBlocked("block");
						menu.setClassItem("menuItem");
					}
				}
				
				menus.add(menu);
				
				i++;						
			}
			
			asignar_menu_item_selected();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	protected void init_fecha_consulta()
		{
			flagBCScore = false;
			
			score = null;						
			
			if(area == null || area.equals("L"))
			{		
				 score = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);			
				
				if(score == null)
				{
					flagBCScore = false;
					
					fechaConsulta = "NO HA REALIZADO CONSULTA";
										
				} else {
					
					flagBCScore = true;
					
					fechaConsulta = frm.format( score.getResult_datetime() );
					
				}
					
			} else if(area.equals("I")) {
				
				flagBCScore = true;
			}			
	}
			
	protected void init_fecha_activacion()
	{
		clsActv = "";
		
		if(membership.getActivation_date()!=null)
		{
			activationDateStr = frm.format( membership.getActivation_date() );
			
		}else{
			
			if( membership.getIs_active() != null && membership.getIs_active() == 1)
			{
				activationDateStr = "SIN FECHA DE ACTIVACIÓN";
				
			}else if( membership.getIs_active() != null && membership.getIs_active() == 0){
				
				dias = getDiferencia_fecha_pospuesta( membership.getCreation_date() );
				
				activationDateStr =  dias.toUpperCase() + " SIN ACTIVAR";
				clsActv = "redcls";				
			}			
		}
	}
			
}
