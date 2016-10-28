package mx.com.kubo.managedbeans.mesa.solicitud.notas;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.MotivePK;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;

public abstract class NotasDelCasoAMO extends NotasDelCasoDMO
{
	protected void asignar_nota_NEW() 
	{
		prospectus_id = persona.getNatPerPK().getProspectus_id();
		company_id    = persona.getNatPerPK().getCompany_id();	
		area          = persona.getProspectus().getArea().toString();
		
		note_PK = new NotesPK(prospectus_id, company_id);
		
		addNote.setNotesPk(note_PK);
		addNote.setChange_date(new Date());
		addNote.setChange_prospectus_id(change_prospectus_id);
		
		if(!area.equals("I"))
		{
			addNote.setProyect_id(proyect_id);
		}
	}
	
	protected void asignar_cambio_estatus_TOKEN() 
	{
		String note_description;
		
		int motive_estatus;
		
		motive_id        = -1;
		motive_estatus   = -1;
		note_description = "null";
		
		if(lastNote != null)
		{
			company_id       = lastNote.getNotesPk().getCompany_id();
			note_description = lastNote.getNote();
		}
		
		if(lastNote != null && lastNote.getMotive_id() != null)
		{						
			motive_PK = new MotivePK();			
			
			motive_id  = lastNote.getMotive_id();
			
			motive_PK.setCompany_id(company_id);
			motive_PK.setMotive_id(motive_id);
			
			motive = service_motive.getMotiveByPK( motive_PK );
			
			motive_selected = motive.getPk().getMotive_id();			
			motive_estatus  = motive.getStatus_id();								
			
		} 
		
		String fecha_pospuesta       = "0/0/0";
		String fecha_pre_autorizada  = "0/0/0";
		
		if(casosPospuestos.getFecha_ORIGINAL_pospuesta() != null)
		{
			fecha_pospuesta = formatter_date.format(casosPospuestos.getFecha_ORIGINAL_pospuesta());
		} 
		
		if(casosPospuestos.getFecha_ORIGINAL_pre_autorizacion() != null)
		{
			fecha_pre_autorizada = formatter_date.format(casosPospuestos.getFecha_ORIGINAL_pre_autorizacion());
		} 
		
		cambio_estatus_TOKEN = motive_estatus + "::" + note_description + "::" +  motive_id
							 + "::" + fecha_pospuesta + "::" + fecha_pre_autorizada;	
			
		//System.out.println( "SummaryRequestPMO.asignar_cambio_estatus_TOKEN(): " + cambio_estatus_TOKEN );		
	}
	
	protected final boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{
		Change_controlPK changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(persona.getNatPerPK().getProspectus_id());
		changeCtrlPK.setCompany_id(persona.getNatPerPK().getCompany_id());
		
		Change_control changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(getIpAddressClient());			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		
		int prospectus_id = persona.getNatPerPK().getProspectus_id();
		int company_id    = persona.getNatPerPK().getCompany_id();
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
		} else {
			return false;
		}
	}

	private String getIpAddressClient() 
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		
		ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    
		if(ipAddressClient == null)  
		{
	    	ipAddressClient = httpServletRequest.getRemoteAddr();  	 
		}
		
		return ipAddressClient;
	}
	
	protected String transaction_message_edicion()
	{
		sb = new StringBuilder();
		
		sb.append("SummaryRequestDMO.editarNotaDelCaso():").append("\n");
		sb.append("Prioridad:").append("\n");
		sb.append(old_priority_type_id).append(" >>> ");
		sb.append(new_priority_type_id).append("\n");		
		
		return sb.toString();	
	}
}
