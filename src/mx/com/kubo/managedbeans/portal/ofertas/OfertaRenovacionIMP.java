package mx.com.kubo.managedbeans.portal.ofertas;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.portal.ofertas.ParserRenovacionAutomaticaIMP;
import mx.com.kubo.portal.ofertas.PreaprobadorIMP;
import mx.com.kubo.portal.ofertas.SimuladorIMP;

@ViewScoped
@ManagedBean(name = "ofertaRenovacion") 
public class OfertaRenovacionIMP extends OfertaRenovacionDMO 
implements Serializable 
{
	private static final long serialVersionUID = 6275801449204523658L;
	
	@PostConstruct
	public void init()
	{		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");		
		
		if(sesion != null & sesion.getProspectus_id() != null)
		{
			lista_loan_type = service_proyect_loan.getLista_loan_type();			
			listPurpose     = service_purpose.getPurposeList();
			
			proyect_loan = service_proyect_loan.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			parser = new ParserRenovacionAutomaticaIMP();		
			parser.setSesion(sesion);
			parser.setProyect_loan(proyect_loan);
			parser.init();
			
			simulador = new SimuladorIMP();
			simulador.setSesion(sesion);
			simulador.init();	
			
			reasignador.setSesionBean(sesion);
			reasignador.setProyect_loan_reasignable(proyect_loan);
			
			preaprobador = new PreaprobadorIMP();
			preaprobador.setReasignador(reasignador);	
		}
	}
	
	public void init_panel_simulador()
	{
		request = RequestContext.getCurrentInstance();		
		
		panel_simulador_ENABLED = true;
		
		request.addCallbackParam("panel_simulador_ENABLED", panel_simulador_ENABLED);
	}
	
	public void init_preaprobador(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		int where_to_recieve_data = Integer.parseInt(input_text.getValue().toString());
		
		switch(where_to_recieve_data)
		{
			case FROM_TABLA_OFERTAS:
				request.addCallbackParam("ammount",      format.format(parser.getAmmount()));
				request.addCallbackParam("plazo",        parser.getTerm_frequency_TOKEN());
				request.addCallbackParam("interes",      parser.getRate());
				request.addCallbackParam("mx_cat",       parser.getCat());
				request.addCallbackParam("pago_mensual", format.format(parser.getPayment()));
			break;
			
			case FROM_SIMULATOR:
				request.addCallbackParam("ammount",      format.format(simulador.getAmmount()));
				request.addCallbackParam("plazo",        simulador.getTerm_frequency_TOKEN());
				request.addCallbackParam("interes",      simulador.getRate());
				request.addCallbackParam("mx_cat",       simulador.getCat());
				request.addCallbackParam("pago_mensual", format.format(simulador.getPayment()));
			break;
		}				
		
		panel_simulador_ENABLED   = false;
		panel_preaprobado_ENABLED = true;
		
		request.addCallbackParam(  "panel_simulador_ENABLED", panel_simulador_ENABLED);
		request.addCallbackParam("panel_preaprobado_ENABLED", panel_preaprobado_ENABLED);
	}	
}
