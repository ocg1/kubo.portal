package mx.com.kubo.notificaciones.bitacora;

import java.util.Date;

import mx.com.kubo.notificaciones.notificables.Evento;

public abstract class BitacoraMailLogAMO extends BitacoraMailLogDMO
{
	protected final void asignarEvento()
	{								
		model.setEvent_id(getEvento().getId());				
	}
	
	protected final void asignarEstatus() 
	{							
		model.setStatus   (getEstatus().ordinal());
		model.setException(getEstatus().toString());	
		
		if(getEvento().equals(Evento.ERROR_DESARROLLO))
		{
			model.setException(getEvento().getError());	
		}
	}
	
	protected final void asignarEmisor()	
	{
		model.setProspectus_id_sender(getEmisor().getMembershipPK().getProspectus_id());
	}
	
	protected final void asignarReceptor()
	{
		model.setCompany_id   (getReceptor().getMembershipPK().getCompany_id());
		model.setProspectus_id(getReceptor().getMembershipPK().getProspectus_id());	
		
		model.setAccount_type_id(getSMTPServer().getAccount_code_id());
		model.setSend_type(getSendType().toString());
		
		model.setEmail_to (getReceptor().getEmail());				
		model.setEmail_date(new Date());
	}
	
	protected final void asignarAcreditado()
	{
		model.setProspectus_id_viewed(getAcreditado().getMembershipPK().getProspectus_id());
	}
}
