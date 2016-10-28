package mx.com.kubo.managedbeans.portal.password;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.model.Membership;

public abstract class DesbloqueoPorCorreoDMO 
{
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected final String DESBLOQUEO_DE_PASSWORD = "36";
	
	protected String [] lista_receptores;
	
	protected Membership membership;
	
	protected String email;
	
	protected int prospectus_id;
	protected int company_id;
	
	public final void setMembership(Membership membership) 
	{
		this.membership = membership;
		
		prospectus_id = membership.getMembershipPK().getProspectus_id();
		company_id    = membership.getMembershipPK().getCompany_id();	
		email         = membership.getEmail();
	}
}
