package mx.com.kubo.registro.verificacion;

import java.util.List;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.StackholderRelService;


public abstract class ProspectoDuplicadoDMO
implements ProspectoDuplicadoIMO
{
	protected StackholderRelService service_accionistas;
	
	protected NaturalPersonService service_natural_person;
	
	protected MembershipService service_membership;
	
	protected AccessService service_access;
			
	protected SessionBean sesion;
	
	protected NaturalPerson naturalPerson;
	protected NaturalPerson duplicado;
	protected gnNaturalPersonPK natural_person_PK;
	
	protected Membership   prospecto;
	protected MembershipPK membership_PK;
	
	protected Access access;
	
	protected StringBuilder sb;
	
	protected String prospecto_RFC;
	protected String prospecto_CURP;
	protected String prospecto_nombre;
	protected String accionista_RFC;
	protected String accionista_CURP;
	protected String accionista_nombre;
	protected String duplicado_CURP;
	protected String duplicado_nombre;
	protected String lista_prospectos_duplicados;
	
	protected List<Stackholder_relationship> lista_accionistas;
	protected List<Membership> lista_membership;
	
	protected Stackholder_relationship stackholder_selection;
	
	protected int company_id;
	protected int prospectus_id;
	protected int duplicado_company_id;
	protected int duplicado_prospectus_id;
	
	protected boolean flagRelation;
	protected boolean flagSamePros;
	protected boolean flagEmployee;
	
	protected final int SCREEN_CLIENTE_DUPLICADO = 29;
	
	public final void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}

	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}

	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public final void setService_accionistas(StackholderRelService service) 
	{
		service_accionistas = service;
	}

	public final String getLista_prospectos_duplicados() 
	{
		return lista_prospectos_duplicados;
	}

	public Stackholder_relationship getStackholder_selection() {
		return stackholder_selection;
	}

	public void setStackholder_selection(Stackholder_relationship stackholder_selection) {
		this.stackholder_selection = stackholder_selection;
	}

	public boolean isFlagRelation() {
		return flagRelation;
	}

	public void setFlagRelation(boolean flagRelation) {
		this.flagRelation = flagRelation;
	}

	public boolean isFlagEmployee() {
		return flagEmployee;
	}

	public void setFlagEmployee(boolean flagEmployee) {
		this.flagEmployee = flagEmployee;
	}
}
