package mx.com.kubo.managedbeans.registro.publicacion;

import static mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional.*;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.xml.rpc.ServiceException;

import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMP;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;

public abstract class DealPMO extends DealAMO
implements DealIMO
{					
	protected final void procesarEstatus(double rate, Simulator simulator) throws ServiceException
	{		
		proyect_loan_reasignable = proyectloanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
		
		if(	proyect_loan_reasignable != null)
		{					
			proyect_id      = proyect_loan_reasignable.getProyect().getProyectoPk().getProyect_id();
			proyect_loan_id	= proyect_loan_reasignable.getProyectloanPk().getProyect_loan_id();
			
			estatus  = EstatusProyectLoan.getInstance(proyect_loan_reasignable.getStatus_id());
			
			switch(estatus)
			{				
				case BORRADOR:								
					asignarClabeAccount(prospectus_id, company_id);
					asignarDatosSimulacion(prospectus_id, company_id, rate, simulator);											
					//asignar_selected_deal(prospectus_id, company_id);
					
					mostrar_propuestas();									
				break;		
				
				case COMITE:				
				case AUTORIZADO:			
				case DESEMBOLSADO:
				case LIQUIDADO:											
				case DUPLICADO:	
				case DESISTIDO:
				case EN_PROCESO_AUTORIZACION:		
					//asignar_selected_deal(prospectus_id, company_id, proyect_loan_id, proyect_id);
					
					mostrar_solicitud_completa();																										
				break;
				
				case CANCELADO:
					asignarReanudar_solicitud_ENABLED(getFecha_en_cancelacion());
					
					mostrar_proyecto_cancelado();
				break;
									
				case POSPUESTO:
					asignarReanudar_solicitud_ENABLED(getFecha_pospuesta());										
					
					mostrar_proyecto_pospuesto();					
				break;
				
				case PRE_AUTORIZADO:
					asignarReanudar_solicitud_ENABLED(getFecha_preaprobacion());										
					
					mostrar_proyecto_pospuesto();
				break;
				
				case CANCELADO_AUTOMATICO:
					asignarReanudar_solicitud_ENABLED(getFecha_consulta());
					
					mostrar_proyecto_rechazado_automaticamente( );
				break;
				
			}
		}
	}
	
	public final void listener_reanudar_solicitud_consulta(AjaxBehaviorEvent evento)
	{
		System.out.println("\n > DealPMO.listener_reanudar_solicitud()");
		
		reasignar_proyecto_consulta();
		reasignar_documentos();
		reasignar_menu_registro();
	}
	
	public final void listener_reanudar_solicitud(AjaxBehaviorEvent evento)
	{
		System.out.println("\n > DealPMO.listener_reanudar_solicitud()");
		
		reasignar_proyecto();
		reasignar_documentos();
		reasignar_menu_registro();
	}
	
	private void reasignar_proyecto_consulta() 
	{
		System.out.println(" > DealPMO.reasignar_proyecto()");
		
		reasignador = new ReasignadorIMP();		
		reasignador.setSesionBean(sesion);
		reasignador.setProyect_loan_reasignable(proyect_loan_reasignable);
		reasignador.crear_nuevo_proyecto(NUEVA_CONSULTA_ENABLED, 4);		
	}

	private void reasignar_proyecto() 
	{
		System.out.println(" > DealPMO.reasignar_proyecto()");
		
		reasignador = new ReasignadorIMP();	
		reasignador.setSesionBean(sesion);
		reasignador.setProyect_loan_reasignable(proyect_loan_reasignable);
		reasignador.crear_nuevo_proyecto(NUEVA_CONSULTA_DISABLED, 4);		
	}
	
	private void reasignar_documentos() 
	{
		System.out.println(" > DealPMO.reasignar_documentos()");
		
		reasignador.crear_lista_documentos(VALIDACION_VIGENCIA_ENABLED);
		reasignador.copiar_documentos();	
	}
	
	private void reasignar_menu_registro() 
	{
		System.out.println(" > DealPMO.reasignar_menu_registro()");
		
		faces     = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		menu_registro_service = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
		
		menu_registro_service.init();
		menu_registro_service.asignar_menu_item_selected(getMenu_item_selected());
	}
}
