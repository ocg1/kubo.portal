package mx.com.kubo.notificaciones.parsers;

import java.util.Date;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMP;

public final class NotificacionPublicacion extends NotificacionIMP
{
	public NotificacionPublicacion(ConexionIMO conexion_SMTP, Membership emisor, Scoring score, ProyectLoan proyecto)
	{
		super(conexion_SMTP);
		
		setStrFecha(date_format.format(new Date()));
		
		asignarDatosAcreditado(emisor);
		asignarScore(score);		
		registrationReason = service_reason.validaRegistrationReason(emisor);
		asignarLoanType(proyecto);
		
		publish = true;
		
		promotor_id_ENABLED = false;
		
		asignaDatosPublicacion(proyecto);
		
		sb = new StringBuilder();
		
		sb.append(JSF_CONSULTA_BURO).append("?");
		sb.append("clave"     ).append("=").append("ldsgeorufxhnvcvsbnfbvsxnnvsh").append("&");
		sb.append("prospecto" ).append("=").append("098375372327563").append("&");
		sb.append("registro"  ).append("=").append("8989yh98y72e32eq3et6twgfrt").append("&");
		sb.append("solNum"    ).append("=").append(burSolNum).append("&");
		sb.append("valor"     ).append("=").append("iskqwisdhfzncwertgfdfdsgnxbvc").append("&");
		sb.append("clave1"    ).append("=").append("8765209293847456233486").append("&");
		sb.append("clave2"    ).append("=").append("erdfcvbhjuiy6tfvbvfdr56y7uioijn2w");
		
		if(promotor_id_ENABLED)
		{
			sb.append("&").append("promotorID").append("=").append("###promotorID###");
		}
	
    	comportamiento_pago_URL = sb.toString();
	}
}
