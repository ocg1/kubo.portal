package mx.com.kubo.notificaciones.notificacion;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.tools.Utilities;

public class NotificacionPMO extends NotificacionDMO
{
	protected final MimeMessage asignarMIME_message() throws MessagingException
	{		      
    	mimeMultipart = new MimeMultipart();        
    	body          = new MimeBodyPart();
    	
    	MimeMessage message = conexion_SMTP.getMessage();
    	
        if(isForwad())
        {          
        	/*
        	message.setSubject(message, "Confirmación kubo.financiero");
    		body_text = NotificacionHTML.CUERPO_FW;
        	body_text = body_text.replaceAll("##name##", getNameTo());
        	body_text = body_text.replaceAll("##mailTo##", getStrTo());
            body_text = body_text.replaceAll("###ACTIVATION###", getClaveActivacion());	
            */
            
        	message.setSubject("Confirmación kubo.financiero");
        	body_text = NotificacionHTML.CUERPO;
        	
        	SimpleDateFormat fm = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy"); 
        	
            body_text = body_text.replaceAll( "##name##" , getNameTo().toUpperCase() );
            body_text = body_text.replaceAll("##mailTo##", getStrTo());
            body_text = body_text.replaceAll("###ACTIVATION###", getClaveActivacion());
            body_text = body_text.replaceAll("###Fecha###",fm.format(new Date()));
            body_text = body_text.replaceAll("###prospectus_id###", getProspectus_id());
            body_text = body_text.replaceAll("###codigo_activacion###", getClaveActivacion());
            body_text = body_text.replaceAll("###fecha_activacion###", date_format_activacion.format(new Date()));
            
        } else if (publish) {
        	
        	message.setSubject("Publicación de Proyecto - " + getProspectName());
        	
        	body_text = NotificacionHTML.CUERPO_PUBLICACION;
        	
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
            body_text = body_text.replaceAll("###prospectName###", getProspectName());
            body_text = body_text.replaceAll("###Folio###", getFolio());
            body_text = body_text.replaceAll("###Monto###", getMonto());
            body_text = body_text.replaceAll("###Plazo###", getPlazo());
            body_text = body_text.replaceAll("###Calif###", getKuboScore());
            body_text = body_text.replaceAll("###e-mailAcred###", getEmailAcred());
            body_text = body_text.replaceAll("###REGISTRATION_REASON###", getRegistrationReason());
            body_text = body_text.replaceAll("###BCSCORE###", getBcScore());
            body_text = body_text.replaceAll("###LOANTYPE###", getLoanType());
            body_text = body_text.replaceAll("###Para_que_lo_utizara###", getProposito_del_credito());
            body_text = body_text.replaceAll("###BC-DESC###", Utilities.getBCDesc(getBcScore()));
            body_text = body_text.replaceAll("###URL_COMPORTAMIENTO_PAGO###", comportamiento_pago_URL);
            
        } else if(isTemppass()){
        	message.setSubject("Petición de cambio de contraseña para kubo.financiero.");
        	body_text = NotificacionHTML.CUERPO_TEMP_PASS;
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
            body_text = body_text.replaceAll("###TEMPASS###", getEncriptempass());
            
        } else if(isConsultaBC()){
        	
        	message.setSubject("Consulta a Buró de Crédito Realizada - " + getProspectName().toUpperCase());
        	body_text = NotificacionHTML.CUERPO_BC;
        	
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("###prospectName###", getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###BCScore###", getBcScore());
        	body_text = body_text.replaceAll("###KuboScore###", getKuboScore());
        	body_text = body_text.replaceAll("###Tasa###", getTasa());
        	body_text = body_text.replaceAll("###Comision###", getComision());
        	body_text = body_text.replaceAll("###prospectNumber###", getProspectus_id());
        	body_text = body_text.replaceAll("###SOLNUM###", getBurSolNum());
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###", getRegistrationReason());
        	body_text = body_text.replaceAll("###e-mailAcred###", getEmailAcred());
        	body_text = body_text.replaceAll("###LOANTYPE###", getLoanType());
        	body_text = body_text.replaceAll("###BC-DESC###", Utilities.getBCDesc(getBcScore()));
        	
        	if(getProspectusID() == null){
        		body_text = body_text.replaceAll("###promotorID###", "");
        	}else{
        		body_text = body_text.replaceAll("###promotorID###", ""+getProspectusID());
        	}
        	
        	if(getDescNegativeBCScore() == null)
        		body_text = body_text.replaceAll("###DescNegativeBCScore###", "");
        	else
        		body_text = body_text.replaceAll("###DescNegativeBCScore###", getDescNegativeBCScore());
        	
        } else if(isError()){
        	message.setSubject("Error en Consulta a Buró de Crédito - "+getProspectName().toUpperCase());
        	body_text = NotificacionHTML.CUERPO_BC_ERROR;
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("###prospectName###", getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###prospectNumber###", getProspectus_id());
        	body_text +=  getErrormsg();
        	body_text += NotificacionHTML.CUERPO_BC_ERROR_2;
        } else if(isConfmail()){
        	
        	message.setSubject("Petición de cambio de correo electrónico para kubo.financiero.");
        	body_text = NotificacionHTML.CUERPO_MAIL_CONFIRMACION;
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("##name##", getNameTo());
        	body_text = body_text.replaceAll("##mailTo##", getStrTo());
            body_text = body_text.replaceAll("###ACTIVATION###", getClaveActivacion().trim());
            
        } else if(isBcFirma()) {
        	
        	body_text = NotificacionHTML.CUERPO_BC_FIRMA;
            	
        	if(isBcSeguimiento())
        	{
        		message.setSubject("Consulta a Buró de Crédito mediante Firma Realizada para Seguimiento- "+getProspectName().toUpperCase());
        		body_text = body_text.replaceAll("###LOANTYPE###", "");
        		
        	} else {
        		message.setSubject("Consulta a Buró de Crédito mediante Firma Realizada - "+getProspectName().toUpperCase());
        		
        		if(getLoanType() == null || getLoanType().trim().length() == 0)
        		{
        			tmp = tmp.replaceAll("###LOANTYPE###", "NO ASIGNADO");
        		} else {
        			tmp = tmp.replaceAll("###LOANTYPE###", getLoanType());
        		}
        		                		
        		body_text = body_text.replaceAll("###LOANTYPE###", tmp);
        	}
        	
        	body_text = body_text.replaceAll("###userControlName###", getUserControlName());
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("###prospectName###", getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###BCScore###", getBcScore());
        	body_text = body_text.replaceAll("###KuboScore###", getKuboScore());
        	body_text = body_text.replaceAll("###Tasa###", getTasa());
        	body_text = body_text.replaceAll("###Comision###", getComision());
        	body_text = body_text.replaceAll("###prospectNumber###", getProspectus_id());
        	body_text = body_text.replaceAll("###SOLNUM###", getBurSolNum());
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###", getRegistrationReason());
        	body_text = body_text.replaceAll("###e-mailAcred###", getEmailAcred());                	                	                	
        	body_text = body_text.replaceAll("###BC-DESC###", Utilities.getBCDesc(getBcScore()));
        	
        	if(getProspectusID() == null){
        		body_text = body_text.replaceAll("###promotorID###", "");
        	}else{
        		body_text = body_text.replaceAll("###promotorID###", ""+getProspectusID());
        	}
        	
        	if(getDescNegativeBCScore() == null)
        	{
        		body_text = body_text.replaceAll("###DescNegativeBCScore###", "");
        	} else {
        		body_text = body_text.replaceAll("###DescNegativeBCScore###", getDescNegativeBCScore());
        	}
        	
        } else if(isPartner01) {
            	
        	message.setSubject("Registro: "+getProspectus_id()+"  Socio: "+getPartner_number()+" - "+getProspectName().toUpperCase());
        	body_text = NotificacionHTML.CUERPO_PARTNER;
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("###NombreSocio###", getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###NumeroSocio###", getPartner_number());
        	body_text = body_text.replaceAll("###ToPartnerName###", getToPartnerName());
        	body_text = body_text.replaceAll("###forwardUser###", getForwardUser());
        	body_text = body_text.replaceAll("###NombrePromotor###", getPromotorName());
            	
        } else if(isAvisoFinSolicitudInv) {
        	
        	message.setSubject("Gracias por registrarte como Inversionista en Kubo.financiero");
        	body_text = NotificacionHTML.Notifica_Fin_Sol_Inver;
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("###prospectNumber###",getProspectus_id());
            	
        } else if(isRechazado) {
        	message.setSubject(titulo_estatus_credito + ": " + getProspectus_id()+" - "+getProspectName().toUpperCase() +" " +getLoanType() );
        	
        	body_text = NotificacionHTML.CUERPO_RECHAZADO;
        	
        	body_text = body_text.replaceAll("###titulo_estatus_credito###", titulo_estatus_credito);
        	
        	body_text = body_text.replaceAll("###ESTATUS_ORIGINAL###", estatus_original.toUpperCase());
        	body_text = body_text.replaceAll("###ESTATUS_CREDITO###", estatus_credito.toUpperCase());
        	
        	if(fecha_pospuesta != null)
        	{
        		body_text = body_text.replaceAll("###FECHA_POSPUESTA###", "para la fecha <span style='font-weight: bold;' >" + fecha_pospuesta + "</span>");
        		
        	} else {
        		
        		body_text = body_text.replaceAll("###FECHA_POSPUESTA###", "");
        	}
        	
        	
        	body_text = body_text.replaceAll("###Fecha###", getStrFecha());
        	body_text = body_text.replaceAll("###prospectNumber###",getProspectus_id());
        	body_text = body_text.replaceAll("###prospectName###",getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###e-mailAcred###",getEmailAcred());
        	body_text = body_text.replaceAll("###BCScore###",getBcScore());
        	body_text = body_text.replaceAll("###KuboScore###",getKuboScore());
        	body_text = body_text.replaceAll("###Tasa###",getTasa());
        	body_text = body_text.replaceAll("###Comision###",getComision());
        	body_text = body_text.replaceAll("###Monto###",getMonto());
        	body_text = body_text.replaceAll("###Plazo###",getPlazo());
        	body_text = body_text.replaceAll("###LOANTYPE###",getLoanType());
        	body_text = body_text.replaceAll("###IMGURL###",getUrlImg());        	
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###",getRegistrationReason());
        	body_text = body_text.replaceAll("###BC-DESC###", Utilities.getBCDesc(getBcScore()));
        	body_text = body_text.replaceAll("###motive_catalog_description###", motive_catalog_description);
        	        	
        	if(lista_notas != null)
        	{
	        	StringBuilder sb = new StringBuilder();
	        	sb.append("<ul>");
	        	
	        	for(Notes nota: lista_notas)
	        	{
	        		sb.append("<li style='font-weight: bold;'><b>").append(nota.getNote()).append("</b></li>");
	        	}
	        	
	        	sb.append("</ul>");
	        	
	        	body_text = body_text.replaceAll("###NOTAS_DE_RECHAZO###", sb.toString());
	        	
        	} else {
        		
        		body_text = body_text.replaceAll("###NOTAS_DE_RECHAZO###", "");
        	}
        	
        } else if (isAutorizado) {
        	
        	message.setSubject("Crédito Autorizado: "+getProspectus_id()+" - "+getProspectName().toUpperCase() +" " +getLoanType() );
        	body_text = NotificacionHTML.CUERPO_AUTORIZADO;
        	body_text = body_text.replaceAll("###Fecha###",getStrFecha());
        	body_text = body_text.replaceAll("###prospectNumber###",getProspectus_id());
        	body_text = body_text.replaceAll("###prospectName###",getProspectName().toUpperCase()); 
        	body_text = body_text.replaceAll("###e-mailAcred###",getEmailAcred());
        	body_text = body_text.replaceAll("###BCScore###",getBcScore()); 
        	body_text = body_text.replaceAll("###KuboScore###",getKuboScore());
        	body_text = body_text.replaceAll("###Tasa###",getTasa());
        	body_text = body_text.replaceAll("###Comision###",getComision());
        	body_text = body_text.replaceAll("###Monto###",getMonto());
        	body_text = body_text.replaceAll("###Plazo###",getPlazo());
        	body_text = body_text.replaceAll("###FechaDesembolso###",getFechaDesembolso());
        	body_text = body_text.replaceAll("###LOANTYPE###",getLoanType());
        	body_text = body_text.replaceAll("###FormaDesembolso###",getFormaDesembolso());
        	body_text = body_text.replaceAll("###CuentaDesembolso###",getCuentaDesembolso());
        	body_text = body_text.replaceAll("###OBSERVACION###",getObservacion()); 
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###",getRegistrationReason());
        	body_text = body_text.replaceAll("###Clave_elector###",getClaveElector());
        	body_text = body_text.replaceAll("###IMGURL###",getUrlImg());
        	body_text = body_text.replaceAll("###BC-DESC###", Utilities.getBCDesc(getBcScore()));
            	
        } else if( isPreautorizado ) {
        	
        	message.setSubject("Pre - Autorización de Crédito: "+getProspectus_id()+" - "+getProspectName().toUpperCase() +" " +getLoanType() );
        	body_text = NotificacionHTML.CUERPO_PRE_AUTORIZADO;
        	body_text = body_text.replaceAll("###Fecha###",getStrFecha());
        	body_text = body_text.replaceAll("###prospectNumber###",getProspectus_id());
        	body_text = body_text.replaceAll("###prospectName###",getProspectName().toUpperCase()); 
        	body_text = body_text.replaceAll("###e-mailAcred###",getEmailAcred());
        	body_text = body_text.replaceAll("###BCScore###",getBcScore()); 
        	body_text = body_text.replaceAll("###KuboScore###",getKuboScore());
        	body_text = body_text.replaceAll("###Tasa###",getTasa());
        	body_text = body_text.replaceAll("###Comision###",getComision());
        	body_text = body_text.replaceAll("###Monto###",getMonto());
        	body_text = body_text.replaceAll("###Plazo###",getPlazo());
        	body_text = body_text.replaceAll("###FechaDesembolso###",getFechaDesembolso());
        	body_text = body_text.replaceAll("###LOANTYPE###",getLoanType());
        	body_text = body_text.replaceAll("###FormaDesembolso###",getFormaDesembolso());
        	body_text = body_text.replaceAll("###CuentaDesembolso###",getCuentaDesembolso());
        	//thisCuerpo = thisCuerpo.replaceAll("###OBSERVACION###",getObservacion()); 
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###",getRegistrationReason());
        	body_text = body_text.replaceAll("###IMGURL###",getUrlImg());
        	body_text = body_text.replaceAll("###BC-DESC###", Utilities.getBCDesc(getBcScore()));
        	
        } 
/*        
        else if( isPubliInversion ) {
            	
        	SimpleDateFormat fm = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy"); 
        	
        	message.setSubject("Solicitud de Inversión: "+getProspectus_id()+" - "+getProspectName().toUpperCase() );
        	body_text = NotificacionHTML.CUERPO_INV_PUB;
        	
        	body_text = body_text.replaceAll("###Fecha###",fm.format(new Date()));
        	body_text = body_text.replaceAll("###prospectNumber###",getProspectus_id());
        	body_text = body_text.replaceAll("###prospectName###",getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###e-mailAcred###",getEmailAcred());
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###",getRegistrationReason());
        	body_text = body_text.replaceAll("###creation-date###", creation_DATE);
        	body_text = body_text.replaceAll("###finish-date###",finish_DATE);

        	
        }
        */
        else if( altaInversion ) {
        	
        	SimpleDateFormat fm = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy"); 
        	
        	message.setSubject("Activación de cuenta de inversionista: "+getProspectus_id()+" - "+getProspectName().toUpperCase() );
        	body_text = NotificacionHTML.CUERPO_INV_ALTA;
        	
        	body_text = body_text.replaceAll("###Fecha###",fm.format(new Date()));
        	body_text = body_text.replaceAll("###prospectNumber###",getProspectus_id());
        	body_text = body_text.replaceAll("###prospectName###",getProspectName().toUpperCase());
        	body_text = body_text.replaceAll("###e-mailAcred###",getEmailAcred());
        	body_text = body_text.replaceAll("###safiClientId###",getSafiClientID());
        	body_text = body_text.replaceAll("###REGISTRATION_REASON###",getRegistrationReason());

        } else if( pruebaCorreo ) {
        	
        	SimpleDateFormat fm = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy"); 
        	
        	message.setSubject("PRUEBA DE ENVIO DE CORREO" );
        	body_text = NotificacionHTML.PRUEBA;
        	
        	body_text = body_text.replaceAll("###Fecha###",fm.format(new Date()));
        	
        } else {
        	
        	message.setSubject("Confirmación kubo.financiero");
        	body_text = NotificacionHTML.CUERPO;
        	
        	SimpleDateFormat fm = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy"); 
        	
            body_text = body_text.replaceAll( "##name##" , getNameTo().toUpperCase() );
            body_text = body_text.replaceAll("##mailTo##", getStrTo());
            body_text = body_text.replaceAll("###ACTIVATION###", getClaveActivacion());
            body_text = body_text.replaceAll("###Fecha###",fm.format(new Date()));
            body_text = body_text.replaceAll("###prospectus_id###", getProspectus_id());
            body_text = body_text.replaceAll("###codigo_activacion###", getClaveActivacion());
            body_text = body_text.replaceAll("###fecha_activacion###", date_format_activacion.format(new Date()));
                
        }
        
        body.setText( body_text, "ISO-8859-1", "html" );
        
        mimeMultipart.addBodyPart( body );	   
        
        message.setContent( mimeMultipart );
        
        return message;
	}

}
