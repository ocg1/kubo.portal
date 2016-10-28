package mx.com.kubo.managedbeans.mesa;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.managedbeans.mesa.solicitud.adicional.*;
import mx.com.kubo.model.ProyectLoan;

import org.primefaces.context.RequestContext;

public abstract class MenuControlTableBeanPMO extends MenuControlTableBeanAMO
{	
	private ProyectLoan         proyecto_actual;
	private AdditionalCreditIMO additional;
	private RequestContext      requestContext;	
	
	private Map<String, Object> viewMap;
	
	private int acreditado_ID;
	
	public void toAdditionalCredit(ActionEvent evento)
	{					
		proyecto_actual = (ProyectLoan) evento.getComponent().getAttributes().get("proyect");
		
		acreditado_ID = proyecto_actual.getProyectloanPk().getProspectus_id();
		
		requestContext = RequestContext.getCurrentInstance();
		
		faces     = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();		
		viewMap   = faces.getViewRoot().getViewMap();
		
		viewMap.clear();											
		
		if(proyecto_actual != null)
		{
			additional = (AdditionalCreditIMO) resolver.getValue(elContext, null, "additionalCredit");
			additional.init(proyecto_actual);
		}
			
		setPaginaActual("controlTable/additionalCredit");
		
		registrar_bitacora_accesso(24, 0, sesion, acreditado_ID,true);						
		
		requestContext.addPartialUpdateTarget(":actualPage");	
	}
}
