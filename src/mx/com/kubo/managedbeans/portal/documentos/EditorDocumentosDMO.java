package mx.com.kubo.managedbeans.portal.documentos;

import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.solicitud.documentacion.DocumentacionIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.RoleFunctionService;

public abstract class EditorDocumentosDMO 
{
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access; 
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{roleFunctionServiceImp}")
	protected RoleFunctionService service_role_function;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	protected FacesContext faces;
	
	protected SessionBean sesion;	
	protected Membership  mesa;
	protected Membership  cliente;
	protected MembershipPK  membership_PK;
	protected ProyectLoan proyect_loan;
	
	protected          AccessIMO auditor;
	protected ParameterReaderIMO reader;
	protected   DocumentacionIMO gestor;
	
	protected List<RoleFunction> lista_role_function;
	
	protected String page_title;
	protected String access_from;
	protected String real_path;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_viewed;
	protected Integer proyect_loan_id;
	protected Integer role_id;
	protected Integer file_type_id;
	
	protected boolean autorizar_contratos_ENABLED;
	protected boolean editar_file_ENABLED;
	
	protected final int SCREEN_EDITAR_DOCUMENTOS = 71;
	protected final int EDITAR_DOCUMENTOS   = 8;
	protected final int AUTORIZAR_CONTRATOS = 29;
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
	
	public void setService_role_function(RoleFunctionService service) 
	{
		service_role_function = service;
	}
	
	public void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}
	
	public final AccessIMO getAuditor()
	{
		return auditor;
	}
	
	public final DocumentacionIMO getGestor()
	{
		return gestor;
	}
	
	public final String getPage_title()
	{
		return page_title;
	}
	
	public boolean isEditar_file_ENABLED() 
	{
		return editar_file_ENABLED;
	}
	
	public final boolean isAutorizar_contratos_ENABLED() 
	{
		return autorizar_contratos_ENABLED;
	}
}
