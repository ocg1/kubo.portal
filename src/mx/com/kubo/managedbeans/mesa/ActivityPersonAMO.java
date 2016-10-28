package mx.com.kubo.managedbeans.mesa;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.ScreenPK;

public abstract class ActivityPersonAMO extends ActivityPersonDMO
{
	protected void asignar_menu_item_selected()
	{		
		asignar_pagina_actual();
		
		for(MenuRegBean menu_item : menus)
		{
			if(menu_item.getTargetItem().equals(paginaActual))
			{
				menu_item.setClassItem("menuItem");
				menu_item.setPorcClassItem("porcent");
				menu_item.setNumItem("menuItemNum");
				
//				setMenuSel(menu_item.getIdItem());
				
				break;
			}
		}
		
//		menuSel      = menu_item_selected.split("::")[2];		
//		paginaActual = menu_item_selected.split("::")[0];
		
		for(MenuRegBean menu_item : menus)
		{
			if(menu_item.getTargetItem().equals(paginaActual))
			{
				menu_item.setClassItem("menuItemSel");
				menu_item.setPorcClassItem("porcentSel");
				menu_item.setNumItem("menuItemNumSel");
				
//				setMenuSel(menu_item.getIdItem());
//				
//				porc = menu_item.getPorcItem();
				
				break;
			}
		}				
	}
	
	private final void asignar_pagina_actual()
	{
		
		recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		access =  accessService.getMaxAccess(prospectus_id, company_id);
				
		if(access != null)
		{
			screen_PK  = new ScreenPK();
			screen_PK.setCompany_id(company_id);
			screen_PK.setScreen_id(access.getScreen_id());
			
			screen = screenService.getScreenById(screen_PK);
			
			paginaActual = screen.getName();
			
			fecUltimoAccesoStr = frm.format( access.getAccess_datetime() );
			
			//ultimaPantallaStr = recurso.getString(screen.getResource_name());
			ultimaPantallaStr = screen.getResource_name();
			
		} else { 
			
			paginaActual = null;
		}		
	}
	
	protected String getDiferencia_fecha_pospuesta( Date date )
	{
		pospuesta = Calendar.getInstance();
	    today     = Calendar.getInstance();
	    	    	    
	    decimal_format = new DecimalFormat("##");
	    
	    pospuesta.setTime( date );
	    
	    decimal_format.setRoundingMode(RoundingMode.DOWN);
	    
	    long pospuesta_milis = pospuesta.getTimeInMillis();
	    long today_milis     = today.getTimeInMillis();	    
	    long diff            = today_milis - pospuesta_milis ;
/*	    
	    long diffSeconds = diff / 1000;
	    long diffMinutes = diff / (60 * 1000);
	    long diffHours   = diff / (60 * 60 * 1000);
*/	    
	    long diffDays    = diff / (24 * 60 * 60 * 1000);	  
	    
	    if(diffDays > 1)
	    {	    	   		    		    
		    if(diffDays / 30 > 0)
		    {
		    	if(diffDays / 30 > 1)
		    	{
		    		diferencia_fecha_pospuesta = decimal_format.format(diffDays / 30) + " meses";
		    		
		    	} else {
		    		
		    		diferencia_fecha_pospuesta = "1 mes";
		    	}
		    		    	
		    } else {
		    	
		    	diferencia_fecha_pospuesta = decimal_format.format(diffDays) + " días";
		    	
		    }
		    
	    } else {
	    	
	    	diferencia_fecha_pospuesta = "1 día";
	    }
	    
	    return diferencia_fecha_pospuesta;
	}
	
	protected boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{
		Change_controlPK changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		Change_control changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(emisor_prospectus_id);
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(ipAddress);			
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
