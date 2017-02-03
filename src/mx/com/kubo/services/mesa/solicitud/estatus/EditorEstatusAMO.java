package mx.com.kubo.services.mesa.solicitud.estatus;

import static mx.com.kubo.notificaciones.notificables.Evento.REGISTRO_BITACORA_CHANGE_CONTROL;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.change_control.ChangeControlIMO;
import mx.com.kubo.kubows.EditorEstatusRequest;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.model.EstatusChangeLog;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.StatusProyectCatPK;

public abstract class EditorEstatusAMO extends EditorEstatusDMO
implements ChangeControlIMO
{	
	protected void asignar_change_control() 
	{
		change_control = new ChangeBean();
		
		estatus_PK = new StatusProyectCatPK(estatus_NEW.getId(), COMPANY);
		
		estatus_name_NEW = service_estatus.getStatusProyectCatByPK(estatus_PK);
		
		if( estatus_ORIGINAL != null ){
		
			estatus_PK = new StatusProyectCatPK(estatus_ORIGINAL.getId(), COMPANY);
			
			estatus_name_ORIGINAL = service_estatus.getStatusProyectCatByPK(estatus_PK);
		
		}
		
		change_control.setNewValue(estatus_name_NEW.getName());
		change_control.setWhyChangeData(descripcion_del_cambio);
		
		change_control.setNameTable("ln_status");
		change_control.setNameField("status_id");					
		
		afected_table = new String[]{"ln_proyect_loan"};
		afected_field = new String[]{"status_id"};
					
		lista_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id , afected_table, afected_field);

		has_change              = lista_change_control != null && lista_change_control.size() > 0 ? true: false;
		bitacora_change_control = lista_change_control != null && lista_change_control.size() > 0 ? lista_change_control : null;
		
		change_control.setHasChange(has_change);
		change_control.setLstChanges(bitacora_change_control);
		
		change_control_LAST = service_change_control.get_last_change(prospectus_id, company_id, afected_table, afected_field);
		
		if(change_control_LAST != null)
		{
			change_control.setOrigValue(change_control_LAST.getNew_value());	
			
		} else {
			
			change_control.setOrigValue(estatus_name_ORIGINAL.getName());
		}
	}
	
	protected void asignar_motive_catalog_description() 
	{
		if(motivo_id > -1)
		{
			motive_PK = new  MotivePK();
			motive_PK.setCompany_id(company_id);
			motive_PK.setMotive_id(motivo_id);
			
			motive = service_motive.getMotiveByPK(motive_PK);		
			
			motive_catalog_description = motive.getDescription(); 
		}
	}
	
	protected void asignar_motivo_del_cambio() 
	{
		motivo_del_cambio = "Motivo:  " + motive_catalog_description + ". " + descripcion_del_cambio;
	}
	
	protected void asignar_motivo_del_cambio_POSPUESTO() 
	{		
		fecha_pospuesta = caso_pospuesto.getFecha_NEW_pospuesta();
		
		motivo_del_cambio = "Fecha pospuesta al " 
						  + formatter_date.format(fecha_pospuesta) + "\n"
			              + "Motivo: " + motive_catalog_description + ". " + descripcion_del_cambio;
	}
	
	protected void asignar_motivo_del_cambio_PRE_AUTORIZADO() 
	{	
		fecha_pre_autorizacion = caso_pospuesto.getFecha_NEW_pre_autorizacion();
		
		motivo_del_cambio = "Fecha pre-autorizada al " + formatter_date.format(fecha_pre_autorizacion) + "\n";
		
		if(motive_catalog_description != "")
		{
			motivo_del_cambio += "Motivo: " + motive_catalog_description + ". " + descripcion_del_cambio;
		} else {
			
			motivo_del_cambio += "Motivo: " + descripcion_del_cambio;
		}
	}
	
	protected void asignar_motivo_del_cambio_DUPLICADO()
	{
		motivo_del_cambio = descripcion_del_cambio;
	}
	
	protected void cambiar_estatus_solicitud() 
	{
		loc = new PublicProyectServiceLocator();
		cambiar_estatus_OK = false;
		
		try 
		{			
			System.out.printf("\nEditorEstatusPMO.notificar_cambio_de_estatus()\n");
			
			publicproyect =  loc.getPublicProyect();
			
			request = new EditorEstatusRequest();
			request.setCompany_id      (Integer.toString(company_id));
			request.setProspectus_id   (Integer.toString(prospectus_id));
			request.setEmisor_id       (Integer.toString(emisor_prospectus_id));
			request.setProyect_loan_id (Integer.toString(proyect_loan_id));
			request.setEstatus_id      (Integer.toString(estatus_NEW.getId()));
						
			publicproyect.publishProyect(request);
			
			cambiar_estatus_OK = true;
			
		} catch (ServiceException e) {			
			
			e.printStackTrace();
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	protected void registrar_estatus_change_log() 
	{
		estatus_change_log = new EstatusChangeLog();	
		estatus_change_log.setCompany_id          (company_id);
		estatus_change_log.setProspectus_id       (prospectus_id);
		estatus_change_log.setProyect_id          (proyect_id);
		estatus_change_log.setProyect_loan_id     (proyect_loan_id);
		estatus_change_log.setEvent_id            (REGISTRO_BITACORA_CHANGE_CONTROL.getId());
		estatus_change_log.setStatus_id           (estatus_ORIGINAL.getId());
		estatus_change_log.setChange_status_id    (estatus_NEW.getId());
		estatus_change_log.setChange_prospectus_id(emisor_prospectus_id);
		estatus_change_log.setChange_id           (change_control_LAST.getChange_controlPK().getChange_id());
		estatus_change_log.setChange_date         (change_control_LAST.getChange_date());
				
		change_control_OK = service_estatus_change_log.registrar(estatus_change_log);
	}
			
	protected void actualizar_proyect_loan() 
	{				
		actualProyect = service_proyect_loan.getProyectLoanById(actualProyect.getProyectloanPk());
		
		if(motivo_id > -1)
		{			
			actualProyect.setMotive_id(motivo_id);
			
		} else {
			
			actualProyect.setMotive_id(null);			
		}
		
		actualProyect.setPosposed_date(fecha_pospuesta);
		actualProyect.setPreapproved_date(fecha_pre_autorizacion);
		
		service_proyect_loan.update(actualProyect);				
	}
}
