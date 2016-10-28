package mx.com.kubo.test.notificaciones;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;

public abstract class NotificacionesTestDMO 
{
	@ManagedProperty("#{notificadorImp}")
	protected NotificadorIMO notificador;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membership_service;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyect_loan_service;
	
	protected FacesContext faces;
	protected FacesMessage faces_msg;
	
	protected Membership    emisor, acreditado;
	protected MembershipPK  membership_PK;
	protected ProyectLoanPK proyect_loan_PK;
	protected ProyectLoan   proyect_loan;
	
	protected MovimientosIMO param_values_bean;
	
	protected StringBuilder sb;
	protected Integer prospectus_id, company_id;
	
	protected String receptor_email;
	
	public final void setNotificador(NotificadorIMO notificador) 
	{
		this.notificador = notificador;
	}

	public final void setMembership_service(MembershipService service) 
	{
		membership_service = service;
	}

	public final void setProyect_loan_service(ProyectLoanService service) 
	{
		proyect_loan_service = service;
	}
	
	public final void setProspectus_id(Integer id)
	{
		prospectus_id = id;
	}
	
	public final Integer getProspectus_id()
	{
		return prospectus_id;
	}

	public String getReceptor_email() 
	{
		return receptor_email;
	}

	public void setReceptor_email(String email) 
	{
		receptor_email = email;
	}
	
	
}
