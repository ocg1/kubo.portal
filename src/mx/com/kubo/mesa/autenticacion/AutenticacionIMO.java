package mx.com.kubo.mesa.autenticacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.AuthenticationPool;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.TownCat;

public interface AutenticacionIMO 
{		
	void setMembership(Membership membership);
	void setNatural_person(NaturalPerson natural_person);
	void setAuthentication(AuthenticationPool authentication);
	
	void init_state_id         (AjaxBehaviorEvent evento);
	void init_town_id          (AjaxBehaviorEvent evento);
	void init_zipcode          (AjaxBehaviorEvent evento);
	void init_celular          (AjaxBehaviorEvent evento);
	void init_email            (AjaxBehaviorEvent evento);
	void listener_dia_SELECTED (AjaxBehaviorEvent evento);
	void listener_mes_SELECTED (AjaxBehaviorEvent evento);
	void listener_year_SELECTED(AjaxBehaviorEvent evento);
	
	boolean isValue_ENABLED();
	
	String getValue_ORIGINAL();
	String getValue_NEW();
	String getDescription();
	
	AuthenticationPool getAuthentication();
	
	List<TownCat>  getLista_delegaciones();
	List<StateCat> getLista_estados();
	
	ArrayList<String> getLista_dias();
	ArrayList<String> getLista_meses();
	ArrayList<String> getLista_years();
}
