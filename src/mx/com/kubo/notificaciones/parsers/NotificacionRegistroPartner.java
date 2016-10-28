package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;

public class NotificacionRegistroPartner extends NotificacionIMP 
{
	public NotificacionRegistroPartner(ConexionIMO conexion_SMTP, Membership emisor, ReceptoresIMO receptores)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));
		
		setProspectName(emisor.getPerson().NombreCompletoNPM());
		setProspectus_id(emisor.getMembershipPK().getProspectus_id() + "");						
		setEmailAcred(emisor.getEmail());
		
		if(emisor.getPerson().getMx_ife_cveelector() != null && emisor.getPerson().getMx_ife_cveelector().trim().length() > 0 )
		{
			setClaveElector(emisor.getPerson().getMx_ife_cveelector());
		} else { 
			setClaveElector("No proporcionada");
		}
		
		if(emisor.getPromotor() != null && emisor.getPromotor().getMembership() != null)
		{
			setPromotorName(emisor.getPromotor().getName());
		} else {
			setPromotorName("Sin Promotor");
		}
		
		setToPartnerName(receptores.getPartnerName());
		setForwardUser(receptores.getPartnerForwardMail());
		
		setPartner01(true);
		setPartner_number(emisor.getPriceshoes_number());
	}
}
