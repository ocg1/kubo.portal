package mx.com.kubo.services.cliente.cuenta;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.referencia_pago_panel.PanelEMO;
import mx.com.kubo.tools.Utilities;

public final class ServiceReferenciaPagoIMP extends ServiceReferenciaPagoAMO
{	
	
	public ServiceReferenciaPagoIMP(){
		
		dao = Utilities.findBean("panelDAO");
		prospecto_service = Utilities.findBean("prospectusServiceImp");
		persona_service = Utilities.findBean("naturalPersonServiceImp");
		service_access = Utilities.findBean("accessServiceImp");
		
	}
	
	public final PanelEMO getReferencia_pago() 
	{				
		init_referencia_pago();
		
		return panel;		
	}

	public final void registrar_consulta(boolean panel_OPENED)
	{
		if(panel_OPENED)
		{
			asignarProspecto();
			asignarLog_message();
			asignarRegistroAcceso(REFERENCIA_PAGO);	
		}
	}
	
	public final void registrar_consulta_liquidacion()
	{
		asignarProspecto();
		
		if(sesion != null)
		{
			sesion.setCredito_SAFI(credito_id);
			sesion.setSaldo_liquidacion(saldo_liquidacion);
		}else{
			
			FacesContext faces      = FacesContext.getCurrentInstance();
			ELResolver resolver   = faces.getApplication().getELResolver();
			ELContext context_EL = faces.getELContext();
			
			sesion     = (SessionBean) resolver.getValue(context_EL, null, "sessionBean");
			
			if(sesion != null)
			{
				sesion.setCredito_SAFI(credito_id);
				sesion.setSaldo_liquidacion(saldo_liquidacion);
			}
			
		}
		
		asignarLog_message();
		asignarRegistroAcceso(REFERENCIA_PAGO_LIQUIDACION);	
	}
	
}
