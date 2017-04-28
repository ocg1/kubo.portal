package mx.com.kubo.mesa.solicitud.permisos;

import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.RoleFunction;

public abstract class RoleFunctionAMO extends RoleFunctionDMO
{
	protected void init_role_function() 
	{
		
		if( lista_funciones != null && lista_funciones.size() > 0 ){
		
			for(RoleFunction role_function : lista_funciones)
			{
				function_id = role_function.getPk().getFunction_id();
				
				switch(function_id)
				{
					case MODIFICAR_CONDICIONES_CREDITO:
						changeCreditConditionsFunction = true;
					break;
					
					case EDICION_CURP_TEL_DOCS:
						changeActions = true;
					break;
					
					case RENOVAR_CREDITOS:
						renovacionAct = true;
					break;
					
					case ASIGNAR_CARTERA:
						asignaCartera = true;
					break;
					
					case CREAR_CREDITO_ADICIONAL:
						additionalCredit = true;
					break;
					
					case EDITAR_DOCUMENTOS:
						editDocument = true;
					break;
					
					case ADJUNTAR_DOCUMENTOS:
						addDocument = true;
					break;
					
					case CAMBIAR_ESTATUS:
						changeStatus = true;
					break;
					
					case CAMBIAR_CONDICIONES_DISPERSION:
						changeDispersion = true;
					break;
					
					case MODIFICAR_TELEFONO:
						changeTelephone = true;
					break;
					
					case CONSULTA_MANUAL:
						makeNewConsultation = true;
					break;
					
					case VER_CALIFICACION_TRANSUINION:
						displayTransUnion = true;
					break;
					
					case ACTIVAR_SOLICITUDES_INVERSION:
						changeInstitutionalInvestor = true;
					break;
					
					case AGREGAR_NOTAS:
						displayNotes = true;
					break;
					
					case CANCELAR_CUENTA:
						cancel_prospectus = true;
					break;
					
					case AGREGAR_ALARMAS:
						displayAlerts = true;
					break;
					
					case ASIGNAR_TUTOR:
						displayAddTutor = true;
					break;
					
					case EDICION_VIVIENDA:
						editor_domicilio_ENABLED = true;
					break;
					
					case ASIGNAR_RECOMENDADOS:
						displayReferredChange = true;
					break;
					
					case EDITAR_FORMULARIO:
						edit_Form_help_Coach = true;
					break;
					
					case EDICION_NOMBRE:
						editor_nombre_ENABLED = true;
					break;
					
					case EDICION_TIPO_CREDITO:
						editor_tipo_credito_ENABLED = true;
					break;
					
					case AUTORIZAR_CONTRATOS:
						autorizar_contratos_ENABLED = true;
					break;
					
					case COPIAR_DOCUMENTOS:
						copiar_documentos_ENABLED = true;
					break;
					
					case AUTORIZAR_PERSONAS_RELACIONADAS:
						autorizar_personas_relacionadas_ENABLED = true;
					break;
					
					case VER_PESTANA_TABLERO_NORMATIVO:
						ver_pestana_tablero_normativo_ENABLED = true;
					break;
					
					case VER_NOTAS_COMPORTAMIENTO_INUSUAL:
						ver_notas_comportamiento_inusual_ENABLED = true;
					break;
					
					case REN_4_C:
						ren4c = true;
					break;
					
					case MODIFICAR_TASA_ACREDITADO:
						modificar_tasa_acreditado = true;
					break;
					
					case MODIFICAR_TASA_INVERSIONISTA:
						modificar_tasa_inversionista = true;
					break;
					
					case MODIFICAR_COMISION_POR_APERTURA:
						modificar_comision_apertura = true;
					break;
					
					case MODIFICAR_DESTINO_CREDITO:
						modificar_destino_credito = true;
					break;
					
					case REPROCESAR_BURO_CREDITO:
						reprocesar_buro_credito = true;
					break;
					
					case MODIFICAR_CALIFICACION:
						modificar_calificacion = true;
					break;
					
					default: break;
				}						
			}
		}
	}
	
	protected void init_access_collector() 
	{
		if(menuAccess != null)
		{		
			for(AccessCollector collector : menuAccess)
			{			
				screen_id = collector.getScreen_id();
				
				switch(screen_id)
				{
					case SCREEN_CONTROLTABLE_LOGS:
						displayLogCob = true;
					break;
					
					default: break;
				}			
			}			
		}	
	}
	
	protected void init_constraints() 
	{
		if(status_id != null && status_id != EN_COMITE)
		{
			changeActions = false;
		}											
				
		if(ren4c && safi_client_id != null && safi_client_id.trim().length() > 0)
		{
			ren4c = true;
			
		} else {
			
			ren4c = false;
		}
		
		if(score != null && additionalCredit && !area.equals("I"))
		{
			additionalCredit = true;
			
		} else {
			
			additionalCredit = false;
		}
		
		if(reprocesar_buro_credito && proyect_loan != null && score != null && score.getRisk_processed() == 0)
		{
			reprocesar_buro_credito = true;
			
		} else {
			
			reprocesar_buro_credito = false;
		}
	}
}
