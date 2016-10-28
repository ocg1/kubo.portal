package mx.com.kubo.registro.datos.pais;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

public final class PaisOrigenIMP extends PaisOrigenAMO
implements PaisOrigenIMO
{		
	public final void init()
	{
		init_address();
	}
	
	public final void listener_codigo_postal(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		codigo_postal = (String) input_text.getValue();
		
		System.out.println("PaisOrigenIMP.listener_codigo_postal(): " + codigo_postal);
		
		save_address();
	}
	
	public final void listener_ciudad(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		ciudad = (String) input_text.getValue();
		
		System.out.println("PaisOrigenIMP.listener_ciudad(): " + ciudad);
		
		save_address();
	}
	
	public final void listener_address_text(AjaxBehaviorEvent evento)
	{
		input_text_area = (HtmlInputTextarea) evento.getComponent();
		
		address_text = (String) input_text_area.getValue();
				
		System.out.println("PaisOrigenIMP.listener_address_text(): " + address_text);
		
		save_address();
	}
	
	public final void listener_country(AjaxBehaviorEvent evento)
	{
		input_select_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		country_id = (Integer) input_select_menu.getValue();
		
		System.out.println("PaisOrigenIMP.listener_country(): " + country_id + " - " + input_select_menu.getLabel() );
		
		save_address();
	}
}
