package mx.com.kubo.test.acreditado;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.StatusProyectCatService;
import mx.com.kubo.services.mesa.solicitud.estatus.EditorEstatusIMO;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;
import mx.com.kubo.test.login.LoginTestIMO;

@SuppressWarnings("serial")
public abstract class PanelSeleccionDMO 
implements Serializable, PanelSeleccionIMO
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService service_proyect;

	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{statusProyectCatServiceImp}")
	protected StatusProyectCatService service_status;
	
	@ManagedProperty("#{motiveServiceImp}")
	protected MotiveService service_motivos;			
	
	protected LoginTestIMO       login_sesion;
	protected EditorEstatusIMO   editor;
	protected CasosPospuestosIMO caso_pospuesto;
	
	protected FacesContext       faces;
	protected ELContext          context;
	protected ELResolver         resolver;
	
	protected SimpleDateFormat formatter_date;
	protected HtmlInputText    ajax_inputText;
	
	protected SessionBean sesion;
	protected Membership  usuario_selected;
	protected Membership  acreditado_selected;
	protected Proyect     proyecto_selected;
	protected ProyectLoan proyect_loan_selected;
	
	protected EstatusProyectLoan  estatus_ORIGINAL;
	
	protected List<Membership>        lista_membership;	
	protected List<Membership>        lista_filtrada;
	protected List<Membership>        lista_acreditados;
	protected List<Proyect>           lista_proyectos;
	protected List<ProyectLoan>       lista_proyect_loan;	
	protected List<StatusProyectCat>  lista_status;
	protected List<Motive>            lista_motivos;
	
	protected String status_id_selected;
	protected String motivo_id_selected;
	protected String descripcion_motivo;
	
	protected final String DEFAULT_DESCRIPCION;
	
	protected int ultimo_registro;
	protected int no_acreditados;
	protected int prospectus_id;
	protected int company_id;
	
	protected final int GUZMARO_HDEZ_ZEPEDA = 1;
	
	protected PanelSeleccionDMO()
	{
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
		
		DEFAULT_DESCRIPCION = "Descripción de PRUEBA";
	}
	
	public final void setService_membership(MembershipService service)
	{
		service_membership = service;
	}
	
	public final void setService_proyect(ProyectService service) 
	{
		service_proyect = service;
	}

	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
	
	public final void setService_status(StatusProyectCatService service) 
	{
		service_status = service;
	}

	public final void setService_motivos(MotiveService service) 
	{
		service_motivos = service;
	}
	
	public void setCaso_pospuesto(CasosPospuestosIMO caso_pospuesto) 
	{
		this.caso_pospuesto = caso_pospuesto;
	}

	public void setService_editor_estatus(EditorEstatusIMO service) 
	{
		editor = service;
	}
	
	public void setUsuario_selected(Membership selected) 
	{
		usuario_selected = selected;
	}

	public final void setAcreditado_selected(Membership selected)
	{
		acreditado_selected = selected;
	}

	public final void setProyecto_selected(Proyect selected) 
	{
		proyecto_selected = selected;
	}
	
	public final void setProyect_loan_selected(ProyectLoan selected) 
	{
		proyect_loan_selected = selected;
	}
	
	public final void setStatus_id_selected(String selected)
	{
		status_id_selected = selected;
	}

	public final void setMotivo_id_selected(String selected) 
	{
		motivo_id_selected = selected;
	}

	public final void setDescripcion_motivo(String descripcion) 
	{
		descripcion_motivo = descripcion;
	}

	public final CasosPospuestosIMO getCaso_pospuesto() 
	{
		return caso_pospuesto;
	}
	
	public Membership getUsuario_selected() 
	{
		return usuario_selected;
	}

	public final Membership getAcreditado_selected() 
	{		
		return acreditado_selected;
	}

	public final Proyect getProyecto_selected() 
	{
		return proyecto_selected;
	}
	
	public final ProyectLoan getProyect_loan_selected() 
	{
		return proyect_loan_selected;		
	}
	
	public final String getStatus_id_selected()
	{
		return status_id_selected;
	}
	
	public final String getMotivo_id_selected() 
	{
		return motivo_id_selected;
	}
	
	public final String getDescripcion_motivo() 
	{
		return descripcion_motivo;
	}
	
	public List<Membership> getLista_membership()
	{
		return lista_membership;
	}
	
	public List<Membership> getLista_filtrada()
	{
		return lista_filtrada;
	}
	
	public final List<Membership> getLista_acreditados() 
	{
		return lista_acreditados;
	}

	public final List<Proyect> getLista_proyectos() 
	{
		return lista_proyectos;
	}

	public final List<ProyectLoan> getLista_proyect_loan() 
	{
		return lista_proyect_loan;
	}

	public final List<StatusProyectCat> getLista_status() 
	{
		return lista_status;
	}

	public final List<Motive> getLista_motivos() 
	{
		return lista_motivos;
	}
}
