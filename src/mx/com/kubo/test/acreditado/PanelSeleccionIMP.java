package mx.com.kubo.test.acreditado;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.test.login.LoginTestIMO;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "panel_seleccion") @ViewScoped
public final class PanelSeleccionIMP extends PanelSeleccionAMO
implements Serializable, PanelSeleccionIMO
{			
	private static final long serialVersionUID = -4692779364782484081L;

	@PostConstruct
	public final void init()
	{
		faces    = FacesContext.getCurrentInstance();			
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		login_sesion = (LoginTestIMO) resolver.getValue(context, null, "login_test");
		
		if(!login_sesion.isSesion_ENABLED())
		{
			lista_membership  = service_membership.getMembershipByArea('M');
			lista_acreditados = service_membership.getMembershipByArea('L', 0, 100);		
			lista_status      = service_status.getListStatusProyectCat();	
		}
		
		asignar_default_selected(GUZMARO_HDEZ_ZEPEDA);
	}
	
	public final void listener_usuario_selected(SelectEvent event)
	{
		usuario_selected = (Membership) event.getObject(); 
		
		System.out.printf("\nPanelSeleccionIMP.listener_usuario_selected(): " + usuario_selected.getEmail());
	}
    
	public final void listener_acreditado_selected(SelectEvent evento) 
	{
		acreditado_selected = (Membership) evento.getObject();
		
		prospectus_id = acreditado_selected.getMembershipPK().getProspectus_id();
		company_id    = acreditado_selected.getMembershipPK().getCompany_id();
		
		lista_proyectos = service_proyect.loadProyectListByProspect(acreditado_selected.getPerson().getProspectus());
		
		System.out.println("PanelSeleccionIMP.listener_acreditado_selected(): " + acreditado_selected.getEmail());
	}       
	
	public final void listener_proyecto_selected(SelectEvent evento) 
	{
		proyecto_selected = (Proyect) evento.getObject();
		
		lista_proyect_loan = service_proyect_loan.getProyectLoanListByProspectus(prospectus_id, company_id);
		
		System.out.println("PanelSeleccionIMP.listener_proyecto_selected(): " + proyecto_selected.getName());
	}   
	
	public final void listener_proyect_loan_edited(RowEditEvent evento)
	{
		proyect_loan_selected = (ProyectLoan) evento.getObject();
		
		System.out.println("PanelSeleccionIMP.listener_proyect_loan_edited(): " + proyect_loan_selected.getProyectloanPk().getProyect_loan_id() + " - " + status_id_selected);
	}
	
	public final void listener_proyect_loan_selected(SelectEvent evento) 
	{
		proyect_loan_selected = (ProyectLoan) evento.getObject();				
		
		System.out.println("PanelSeleccionIMP.listener_proyect_loan_selected(): " + proyect_loan_selected.getProyectloanPk().getProyect_loan_id());		
	} 
	
	public final void listener_update_panel_cambio_de_estatus()
	{
		status_id_selected = proyect_loan_selected.getStatus_id().toString();
		
		lista_motivos = service_motivos.getMotiveStatusListByStatus(proyect_loan_selected.getStatus_id());
		
		asignar_service_casos_pospuestos(proyect_loan_selected); 
		
		System.out.println("PanelSeleccionIMP.listener_proyect_loan_selected(): OK \n");
	}
		
	public final void listener_status_id_selected(AjaxBehaviorEvent evento)
	{
		ajax_inputText = (HtmlInputText) evento.getComponent();
		
		status_id_selected = (String) ajax_inputText.getValue();
		
		lista_motivos = service_motivos.getMotiveStatusListByStatus(Integer.parseInt(status_id_selected));
		
		System.out.println("PanelSeleccionIMP.listener_status_id_selected(): " + status_id_selected);
	}
	
	public final void guardar_cambio_de_estatus()
	{		
		/*
		String fecha = formatter_date.format(caso_pospuesto.getFecha_pospuesta());
		
		faces = FacesContext.getCurrentInstance();
		
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		sesion   = (SessionBean) resolver.getValue(context, null, "sessionBean");
				
		editor = new EditorEstatusIMP(sesion.getProspectus_id(), caso_pospuesto, proyect_loan_selected);
		
		editor.guardar_cambio_de_estatus(status_id_selected, motivo_id_selected, descripcion_motivo);
		
		caso_pospuesto.setSelected_day_ORIGINAL(caso_pospuesto.getSelected_day());
		
		System.out.println("PanelSeleccionIMP.guardar_cambio_estado_proyecto(): " + status_id_selected + " - " + motivo_id_selected + " - "  + fecha+ " - " + descripcion_motivo);
		*/
	}
}
