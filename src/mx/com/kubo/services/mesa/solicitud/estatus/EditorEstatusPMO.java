package mx.com.kubo.services.mesa.solicitud.estatus;

import static mx.com.kubo.change_control.ChangeControlEMO.CAMBIO_ESTATUS;
import static mx.com.kubo.change_control.ChangeControlEMO.CAMBIO_ESTATUS_FECHA_POSPUESTA;
import static mx.com.kubo.change_control.ChangeControlEMO.CAMBIO_ESTATUS_FECHA_PRE_AUTORIZACION;
import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.change_control.ChangeControlEMO;

public abstract class EditorEstatusPMO extends EditorEstatusAMO
{					
	protected void procesar_estatus_solicitud() 
	{														
		switch (estatus_NEW) 
		{			
			case BORRADOR: 						
			case AUTORIZADO:
			case DESEMBOLSADO:
			case LIQUIDADO:
			case EN_PROCESO_AUTORIZACION: 
			case CANCELADO_AUTOMATICO: break;
								
			case CANCELADO:		
			case DESISTIDO:							
				asignar_motive_catalog_description();					
				asignar_motivo_del_cambio();
				
				procesar_evento_change_control(CAMBIO_ESTATUS);
				registrar_estatus_change_log();
			break;
			
			case POSPUESTO:				
				caso_pospuesto.setEstatus_SELECTED(estatus_NEW);
				caso_pospuesto.setFecha_ORIGINAL(actualProyect.getPosposed_date());
				
				asignar_motive_catalog_description();						
				asignar_motivo_del_cambio_POSPUESTO();
				
				procesar_evento_change_control(CAMBIO_ESTATUS_FECHA_POSPUESTA);											
				procesar_evento_change_control(CAMBIO_ESTATUS);
				registrar_estatus_change_log();
			break;
			
			case COMITE:
			case DUPLICADO: 
				asignar_motivo_del_cambio_DUPLICADO();		
				
				procesar_evento_change_control(CAMBIO_ESTATUS);
				registrar_estatus_change_log();
			break;		
			
			case PRE_AUTORIZADO:	
				caso_pospuesto.setEstatus_SELECTED(estatus_NEW);
				caso_pospuesto.setFecha_ORIGINAL(actualProyect.getPreapproved_date());
				
				asignar_motive_catalog_description();						
				asignar_motivo_del_cambio_PRE_AUTORIZADO();
				
				procesar_evento_change_control(CAMBIO_ESTATUS_FECHA_PRE_AUTORIZACION);											
				procesar_evento_change_control(CAMBIO_ESTATUS);
				registrar_estatus_change_log();
			break;
		}					
	}
	
	private void procesar_evento_change_control(ChangeControlEMO change_control_EMO)
	{
		switch(change_control_EMO)
		{
			case CAMBIO_ESTATUS:			
				
				change_control_OK = service_change_control.registrar_change_control(new ChangeBean(CAMBIO_ESTATUS), estatus_name_ORIGINAL.getName(), estatus_name_NEW.getName(), motivo_del_cambio, company_id, emisor_prospectus_id, acreditado_prospectus_id);

				if (change_control_OK)
				{					
					lista_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id , afected_table, afected_field);
					
					bitacora_change_control = lista_change_control != null && lista_change_control.size() > 0 ? lista_change_control : null;
					
					change_control_LAST = service_change_control.get_last_change(prospectus_id, company_id, afected_table, afected_field);
					
					change_control.setHasChange(true);
					change_control.setWhyChangeData(null);
					change_control.setLstChanges(bitacora_change_control);					
					change_control.setOrigValue(estatus_name_NEW.getName());
					
					System.out.printf("\nEditorEstatusPMO.registrar_change_control():\n");
					
					System.out.printf(change_control_EMO.getAfected_table() + "."+ change_control_EMO.getField());										
				}								
			break;
			
			case CAMBIO_ESTATUS_FECHA_POSPUESTA:			
			case CAMBIO_ESTATUS_FECHA_PRE_AUTORIZACION:
				
				change_control_OK = service_change_control.registrar_change_control(new ChangeBean(change_control_EMO), caso_pospuesto, company_id, emisor_prospectus_id, acreditado_prospectus_id);
				
				if(change_control_OK)
				{					
					System.out.printf("\nEditorEstatusMO.registrar_change_control():\n");	
					
					System.out.printf(change_control_EMO.getAfected_table() + "."+ change_control_EMO.getField());		
				}
			break;
			
			default: break;
		}
	}
}
