package mx.com.kubo.test.notificaciones;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

public abstract class NotificacionesTestAMO extends NotificacionesTestDMO
{
	protected final boolean asignar_registro_acreditado()
	{
		boolean bandera = false;
		
		if(receptor_email.equalsIgnoreCase("prospectus_id"))
		{
			membership_PK = new MembershipPK(getProspectus_id(), 1);
			acreditado    = membership_service.getMembershipById(membership_PK);
		
		} else {
			
			acreditado = membership_service.getMembershipByEmail(getReceptor_email());
		}
			
		if(acreditado != null)
		{
			faces     = FacesContext.getCurrentInstance();
			faces_msg = new FacesMessage("Emisor = Receptor",  acreditado.getEmail()); 	         
			faces.addMessage(null, faces_msg);	
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(acreditado);	
			
			bandera = true;
		}				
		
		return bandera;
	}
	
	protected final void asignar_credito_rechazado()
	{
		membership_PK = new MembershipPK(1, 1);
		emisor        = membership_service.getMembershipById(membership_PK);
		
		membership_PK = new MembershipPK(4880, 1);
		acreditado    = membership_service.getMembershipById(membership_PK);
		
		prospectus_id = acreditado.getMembershipPK().getProspectus_id();
		company_id    = acreditado.getMembershipPK().getCompany_id();
		
		proyect_loan_PK = new ProyectLoanPK(2591, 2625, prospectus_id, company_id);
		proyect_loan    = proyect_loan_service.getProyectLoanById(proyect_loan_PK);
		
		notificador = new NotificadorIMP();
		notificador.setEmisor(emisor);
		notificador.setAcreditado(acreditado);
	}
}
