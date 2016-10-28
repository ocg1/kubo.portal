package mx.com.kubo.mesa.autenticacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.AuthenticationPool;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.TownCat;

public abstract class AutenticacionDMO
implements AutenticacionIMO
{
	protected HtmlSelectOneMenu select_one_menu;
	protected HtmlInputText input_text;
	protected RequestContext request;
	
	protected Membership membership;
	protected NaturalPerson natural_person;
	
	protected AuthenticationPool authentication;
	
	protected String value_SELECTED;
	protected String email;
	
	private String description;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	
	protected boolean email_ENABLED;
	
	public final void setMembership(Membership membership) 
	{
		this.membership = membership;
		
		email = membership.getEmail();
	}
	
	public void setNatural_person(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;		
	}

	public final void setAuthentication(AuthenticationPool authentication)
	{
		this.authentication = authentication;
		description   = authentication.getDescription();
	}
	
	public final AuthenticationPool getAuthentication() 
	{
		return authentication;
	}

	public final String getDescription() 
	{
		return description;
	}
	
	public String getValue_NEW() 
	{		
		if(value_SELECTED != null)
		{
			return value_SELECTED.toString();
		}
		
		return "";
	}
	
	public void init_state_id(AjaxBehaviorEvent evento)
	{
		
	}

	public void init_town_id(AjaxBehaviorEvent evento) 
	{
		
	}
	
	public void init_zipcode(AjaxBehaviorEvent evento)
	{
	
	}
	
	public void init_celular(AjaxBehaviorEvent evento)
	{
	
	}
	
	public void init_email(AjaxBehaviorEvent evento)
	{
	
	}
		
	public void listener_dia_SELECTED(AjaxBehaviorEvent evento) 
	{
				
	}

	public void listener_mes_SELECTED(AjaxBehaviorEvent evento) 
	{		
		
	}

	public void listener_year_SELECTED(AjaxBehaviorEvent evento) 
	{		
		
	}
	
	public List<TownCat> getLista_delegaciones() 
	{
		return null;
	}

	public List<StateCat> getLista_estados() 
	{
		return null;
	}

	public ArrayList<String> getLista_dias() 
	{	
		return null;
	}

	public ArrayList<String> getLista_meses() 
	{		
		return null;
	}

	public ArrayList<String> getLista_years() 
	{	
		return null;
	}
}
