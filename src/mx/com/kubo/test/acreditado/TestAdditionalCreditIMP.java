package mx.com.kubo.test.acreditado;

import static mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional.*;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.mesa.solicitud.adicional.*;
import mx.com.kubo.portal.ofertas.SimuladorIMP;

@ViewScoped
@ManagedBean(name = "testAdditionalCredit") 
public final class TestAdditionalCreditIMP extends TestAdditionalCreditAMO 
implements Serializable 
{
	private static final long serialVersionUID = 7060307687214047770L;
	
	@PostConstruct
	public void init()
	{
		lista_loan_type = service_proyect_loan.getLista_loan_type();		
		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");		
		sesion.setCompany_id(1);
		sesion.setProspectus_id(12347);
		
		simulator = (Simulator) resolver.getValue(elContext, null, "simulator");		
		simulador = new SimuladorIMP();		
		simulador.setSesion(sesion);
		simulador.init();
	}
		
	public void init_loan_type_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) event.getComponent();
		
		loan_type_id = select_one.getValue().toString();
		
		if(loan_type_id.equals("RDC"))
		{
			loan_type = 1;
			
		} else if(loan_type_id.equals("ADD")) {
			
			loan_type = 2;
			
		} else if(loan_type_id.equals("REN")) {
			
			loan_type = 3;
			
		} else if(loan_type_id.equals("NVO")) {
			
			loan_type = 4;
		} 
		
		request.addCallbackParam("loan_type_id", loan_type_id);
	}
	
	public void init_consulta_ENABLED(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) event.getComponent();
		
		Integer consulta_ENABLED = Integer.parseInt(select_one.getValue().toString());
		
		switch(consulta_ENABLED)
		{
			case 1:
				tipo_credito_adicional = NUEVA_CONSULTA_ENABLED;	
			break;
			
			case 2:
				tipo_credito_adicional = NUEVA_CONSULTA_DISABLED;
			break;
		}
		
		request.addCallbackParam("consulta_ENABLED", consulta_ENABLED);
	}

	public void renovar_solicitud_de_credito()
	{	
		try
		{	
			init_sesion();
					
			reasignador = (ReasignadorIMO) resolver.getValue(elContext, null, "reasignador_service");		
			reasignador.setSesionBean(sesion);
			reasignador.init(proyect_loan);
			reasignador.renovar_solicitud_de_credito(RENOVACION);
			
			request.addCallbackParam("renovacion_OK", true);
			
		} catch(Exception e) {
				
			e.printStackTrace();		
		}	
	}
	
	public void generar_solicitud()
	{										
		try
		{		
			init_sesion();
			
			reasignador = (ReasignadorIMO) resolver.getValue(elContext, null, "reasignador_service");
			reasignador.setSesionBean(sesion);
			reasignador.setProyect_loan_reasignable(proyect_loan);
			reasignador.crear_nuevo_proyecto(tipo_credito_adicional, loan_type);
							
			//copiar_archivos();
			
		} catch(Exception e) {
			
			e.printStackTrace();		
		}		
	}
	
	public void init_renovacion_aprobacion_automatica()
	{
		try
		{		
			init_sesion();			
			
			reasignador = (ReasignadorIMO) resolver.getValue(elContext, null, "reasignador_service");
			reasignador.setSesionBean(sesion);
			reasignador.setProyect_loan_reasignable(proyect_loan);
			
			reasignador.init_renovacion_aprobacion_automatica(tipo_credito_adicional, loan_type);
			
		} catch(Exception e) {
			
			e.printStackTrace();		
		}	
	}
}
