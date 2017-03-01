package mx.com.kubo.registro.verificacion;

import java.util.List;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Clients;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.BlockedPersonService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ClientsService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.tools.Utilities;

public abstract class PersonaBloqueadaDMO
implements PersonaBloqueadaIMO
{
	protected NaturalPersonService  service_natural_person;
	protected MembershipService     service_membership;	
	protected Change_controlService service_change_control;
	protected AccessService         service_access;
	protected ClientsService        service_clients;
	protected BlockedPersonService  service_blocked_person;
	
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected Membership   membership;
	protected MembershipPK membership_PK;
	protected NaturalPerson natural_person;
	
	protected Access access;
	protected Change_control   change_control;
	protected Change_controlPK change_control_PK;
	
	protected BlockedPerson person;
	
	protected List<BlockedPerson> lista_blocked_person;
	protected List<Clients> lista_clientes;
	
	protected String SAFI_client_id;
	protected String full_name;
	protected String mx_rfc;
	protected String pld_blocked;
	protected String version_description;
	protected String access_from;
	protected String user_agent;
	protected String device_info;
	protected String IP_address_client;
	
	protected Integer prospectus_id_coach;
	
	protected int prospectus_id;
	protected int company_id;
	protected int blocked_person_TOTAL;
	protected int tipo_operacion;
	
	protected boolean persist_OK;
	protected boolean change_control_OK;
	protected boolean full_name_ENABLED;
	protected boolean blocked_person_ENABLED;
	
	protected final String PERSONA_BLOQUEADA_REGISTRO     = "38";
	protected final String PERSONA_BLOQUEADA_OPERACION    = "39";
	protected final String PERSONA_BLOQUEADA_COINCIDENCIA = "40";
	
	protected static final int OPERACION_PERSONA_BLOQUEADA = 1;
	protected static final int REGISTRO_PERSONA_BLOQUEADA  = 2;
	protected static final int BUSQUEDA_PERSONA_BLOQUEADA  = 3;
	
	protected PersonaBloqueadaDMO()
	{
		service_membership     = Utilities.findBean("membershipServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		service_access         = Utilities.findBean("accessServiceImp");
		service_clients        = Utilities.findBean("clientsServiceImp");
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		service_blocked_person = Utilities.findBean("blockedPersonServiceImp");
		
		blocked_person_TOTAL = 0;
		
		tipo_operacion = REGISTRO_PERSONA_BLOQUEADA;
	}
	
	public final void setTipo_de_Operacion(int tipo_operacion) 
	{
		this.tipo_operacion = tipo_operacion;
	}
	
	public final void setSesion(SessionBean sesion)
	{
		if(sesion != null)
		{
			prospectus_id_coach = sesion.getCoachProspectus_id();
			device_info         = sesion.getDevice_info();
			user_agent          = sesion.getUser_agent();
			access_from         = sesion.getAccess_from();
			IP_address_client   = sesion.getIP_address_client();
		}
	}
		
	public final void setPerson(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;
		
		if(natural_person != null)
		{
			company_id    = natural_person.getNatPerPK().getCompany_id();
			prospectus_id = natural_person.getNatPerPK().getProspectus_id();
					
			mx_rfc = natural_person.getMx_rfc();
			
			boolean first_name_ENABLED       = natural_person.getFirst_name()       != null && natural_person.getFirst_name().trim().length() > 0;			
			boolean father_last_name_ENABLED = natural_person.getFather_last_name() != null && natural_person.getFather_last_name().trim().length() > 0;
			boolean mother_last_name_ENABLED = natural_person.getMother_last_name() != null && natural_person.getMother_last_name().trim().length() > 0;
			
			if(first_name_ENABLED && father_last_name_ENABLED && mother_last_name_ENABLED)
			{			
				full_name = natural_person.NombreCompletoNPM();
				
				full_name_ENABLED = true;
				
			} else {
				
				full_name = null;
				
				full_name_ENABLED = false;
			}						
			
			membership_PK = new MembershipPK(); 
			membership_PK.setCompany_id(company_id);
			membership_PK.setProspectus_id(prospectus_id);
			
			membership = service_membership.getMembershipById(membership_PK);
			
			pld_blocked = membership.getIs_pld_blocked();
		}
	}
	
	public final BlockedPerson getPerson() 
	{
		return person;
	}

	public final boolean isBlocked_person_ENABLED() 
	{
		return blocked_person_ENABLED;
	}

	public final int getBlocked_person_TOTAL() 
	{
		return blocked_person_TOTAL;
	}
}
