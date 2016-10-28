package mx.com.kubo.managedbeans.preregistro;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.com.kubo.constantes.NavigationRule;

public abstract class PreregistroPMO extends PreregistroAMO
{
	protected void procesar_email() 
	{
		if(email_OK)
		{			
			init_membership();
				
		} else {
			
			displayValMail     = "block";
			displayWarningMail = "block";
			
			imgValMail = "<img src='../resources/img/warning.png' width='20px' height='20px' />";			
			//warningMail = "<script>varEmail=false;</script>";
			request.addCallbackParam("warningMail", EMAIL_ERROR);
		}	
	}
	
	protected void procesar_pre_registro() 
	{
		pre_registro_OK = false;
		
		if(! validaNombre())
		{	
			//System.out.println( "validaNombre pre_registro_OK = true" );
			
			pre_registro_OK = true;
		}
				
		validar_email();
		
		if(!email_OK)
		{
			//System.out.println( "validar_email() email_OK = false" );
			
			log.info("validaMail");			
			pre_registro_OK = true;
		}
		
//		if(! email.equals(confMail))
//		{
//			log.info("!getEmail().equals(getConfMail())");	
//			
//			pre_registro_OK = true;
//		}
		
		if(area == null || area != 'I')
		{
//			if(!password.equals(confPass))
//			{
				log.info("!password.equals(confPass)");	
				
				//pre_registro_OK = true;
//			}
		}
		
		if(loginService.verifyEmail(email))
		{
			log.info("loginService.verifyEmail(getEmail())");	
			//System.out.println( "loginService.verifyEmail(getEmail())" );
			pre_registro_OK = true;
		}
				
/*
		if(loginreturnService.verifyAlias(getNickname()))
		{
			log.info("loginService.verifyAlias(getNickname())");
			return "preregistro";
		}
*/
	}
	
	public boolean validaNombre_portal()
	{
		if(natural_person.getFirst_name().length() > 0)
		{
			String s = natural_person.getFirst_name();
			int x = 0;
			int conV = 0;
			int conC = 0;
			int conN = 0;
			boolean b = false;
			
			for(int i = 0; i<natural_person.getFirst_name().length();i++)
			{
				if(i <= natural_person.getFirst_name().length()-1)
				{
					 if(i > 0)
					 {
						  if((s.toLowerCase().charAt(i)+"").equals((s.toLowerCase().charAt(i-1)+"")))
						  {
							  x++;
							  if(x>1){
								  b=true;
								  break;
							  }
						  }
					 }
					 
					  if ((s.toLowerCase().charAt(i)=='a') || (s.toLowerCase().charAt(i)=='e') || (s.toLowerCase().charAt(i)=='i') || (s.toLowerCase().charAt(i)=='o') || (s.toLowerCase().charAt(i)=='u'))
					  {
						    conV++;
					  }
					  
					  if ((s.toLowerCase().charAt(i)!='a') && (s.toLowerCase().charAt(i)!='e') && (s.toLowerCase().charAt(i)!='i') && (s.toLowerCase().charAt(i)!='o') && (s.toLowerCase().charAt(i)!='u'))
					  {
						    conC++;
					  }
					  
					  if ((s.charAt(i)=='1') || (s.charAt(i)=='2') || (s.charAt(i)=='3') || (s.charAt(i)=='4') || (s.charAt(i)=='5') ||
					  (s.charAt(i)=='6') || (s.charAt(i)=='7') || (s.charAt(i)=='8') || (s.charAt(i)=='9') || (s.charAt(i)=='0'))
					  {
						    conN++;
					  }
				}
			}
			
			if(b||conV==0||conC==0||conN>0)
			{
				setDisplayWarningName("block");
				setWarningName("<script>$('#name').addClass('requiredClass');varName=false;  alerta ('Formato de nombre incorrecto', '#name');</script>");
				return false;
			} else {
				setDisplayWarningName("none");
				setWarningName("<script>$('#name').removeClass('requiredClass');varName=true; alertaQuitar ('#name'); </script>");
				return true;
			}
			
		} else {
			setDisplayWarningName("none");
			setWarningName("<script>$('#name').removeClass('requiredClass'); varName=false; alertaQuitar ('#name');</script>");
			return true;
		}
	}
	
	public final boolean validaNombre()
	{
		if( natural_person.getFirst_name() != null && natural_person.getFirst_name().length() > 0)
		{
			String s = natural_person.getFirst_name();
			int x = 0;
			int conV = 0;
			int conC = 0;
			int conN = 0;
			boolean b = false;
			
			for(int i = 0; i<natural_person.getFirst_name().length();i++)
			{
				if(i <= natural_person.getFirst_name().length()-1)
				{
					 if(i > 0)
					 {
						  if((s.toLowerCase().charAt(i)+"").equals((s.toLowerCase().charAt(i-1)+"")))
						  {
							  x++;
							  if(x>1){
								  b=true;
								  break;
							  }
						  }else{
							  x=0;
						  }
					 }
					 
					  if ((s.toLowerCase().charAt(i)=='a') || (s.toLowerCase().charAt(i)=='e') || (s.toLowerCase().charAt(i)=='i') || (s.toLowerCase().charAt(i)=='o') || (s.toLowerCase().charAt(i)=='u'))
					  {
						    conV++;
					  }
					  
					  if ((s.toLowerCase().charAt(i)!='a') && (s.toLowerCase().charAt(i)!='e') && (s.toLowerCase().charAt(i)!='i') && (s.toLowerCase().charAt(i)!='o') && (s.toLowerCase().charAt(i)!='u'))
					  {
						    conC++;
					  }
					  
					  if ((s.charAt(i)=='1') || (s.charAt(i)=='2') || (s.charAt(i)=='3') || (s.charAt(i)=='4') || (s.charAt(i)=='5') ||
					  (s.charAt(i)=='6') || (s.charAt(i)=='7') || (s.charAt(i)=='8') || (s.charAt(i)=='9') || (s.charAt(i)=='0'))
					  {
						    conN++;
					  }
				}
			}
			
			if(b||conV==0||conC==0||conN>0)
			{
				setDisplayWarningName("block");
				setWarningName("<script>$('#name').addClass('requiredClass');varName=false; alerta ('Formato de nombre incorrecto', '#name'); </script>");
				return false;
			} else {
				setDisplayWarningName("none");
				setWarningName("<script>$('#name').removeClass('requiredClass');varName=true; alertaQuitar ('#name');</script>");
				return true;
			}
			
		} else {
			setDisplayWarningName("none");
			setWarningName("<script>$('#name').removeClass('requiredClass');varName=false; alertaQuitar ('#name'); </script>");
			return true;
		}
	}
	
	public void validaAlias()
	{
		log.info("Validando Alias ");
		if(getNickname()!=null){
			if(validaAliasFormat(getNickname())){
				log.info("Alias Valido; "+getNickname());
				if(loginService.verifyAlias(getNickname())){
					log.info("El Alias ya existe");
							/*setDisplayValAlias("block");
							setImgValAlias("<img src='../resources/img/warning.png' width='20px' height='20px' />");
							setDisplayWarningAlias("block");
							setWarningAlias("El alias : "+getNickname()+" no se encuentra disponible");*/
					//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Vailda Alias", "El alias : "+getNickname()+" no se encuentra disponible"));
					addMessage("Nombre de usuario o contraseña incorrecta, verifica tambien que tu cuenta se encuentra activa.");
				}else{
					log.info("El Alias es nuevo");
							setDisplayValAlias("block");
							setImgValAlias("<img src='../resources/img/check.png' />");
							setDisplayWarningAlias("none");
							setWarningAlias("");
				}
			}else{
				log.info("Alias No Valido: "+getNickname());
				setDisplayValAlias("block");
				setImgValAlias("<img src='../resources/img/warning.png' width='20px' height='20px' />");
				setDisplayWarningAlias("block");
				setWarningAlias("El alias : "+getNickname()+" no es válido");
			}
		}else{
			log.info("Alias vacio");
			displayValMail = ("none");
			setDisplayWarningAlias("none");
		}
		
	}
	
	public void validaPass_portal()
	{
		log.info("Validando Password ");
		if(password!=null&&confPass!=null&&password.length()>0&&confPass.length()>0)
		{
			if(password.equals(confPass))
			{
				setDisplayValPass("block");
				setImgValPass("<img src='../resources/img/check.png' />");
				setDisplayWarningConfPass("none");
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=true; alertaQuitar ('#confpass');</script>");
			} else {
				log.info("Confirmacion No Valida: "+password+"  -  "+confPass);
				setDisplayValPass("block");
				setImgValPass("<img src='../resources/img/warning.png' width='20px' height='20px' />");
				setDisplayWarningConfPass("block");
				setWarningConfPass("<script>$('#confpass').addClass('requiredClass');varPass=false;  alerta ('Error al confirmar la contraseña', '#confpass');</script>");
			
			}
		} else {
				log.info("Password vacio");
				setDisplayWarningConfPass("none");
				setDisplayValPass("none");
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=false; alertaQuitar ('#confpass');</script>");
		}		
	}
	
	public void validaPass()
	{
		log.info("Validando Password ");
		if(password!=null&&confPass!=null&&password.length()>0&&confPass.length()>0)
		{
			if(password.equals(confPass))
			{
				setDisplayValPass("block");
				setImgValPass("<img src='../resources/img/check.png' />");
				setDisplayWarningConfPass("none");
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=true; alertaQuitar ('#confpass');</script>");
			} else {
				log.info("Confirmacion No Valida: "+password+"  -  "+confPass);
				setDisplayValPass("block");
				setImgValPass("<img src='../resources/img/warning.png' width='20px' height='20px' />");
				setDisplayWarningConfPass("block");
				setWarningConfPass("<script>$('#confpass').addClass('requiredClass');varPass=false; alerta ('Error al confirmar la contraseña', '#confpass');</script>");
			
			}
		} else {
				log.info("Password vacio");
				setDisplayWarningConfPass("none");
				setDisplayValPass("none");
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=false; alertaQuitar ('#confpass');</script>");
		}		
	}
	
	protected void validar_email()
	{		
		email_OK = false;
		
		
		
		if(email != null && email.length() > 0)
		{
			reg_exp = Pattern.compile("^*\\w+([\\.*\\w([\\-\\w])+])*@\\w([\\-\\w])+\\.\\w{2,4}([\\.*\\w{2,3}])*$");
			
			matcher = reg_exp.matcher(email);
			
			if ( matcher.matches())
			{
				email_OK =  true;				
			} else{
//				warningMail = "<script>alertify.error('Formato de correo incorrecto.');";
//				warningMail += "varEmail=false;</script>";
				warningMail = "";
			}
			
		} else {
			
			log.info("Mail vacio");
			displayValMail     = "none";
			displayWarningMail = "none";
			//warningMail = "<script>$('#email').removeClass('requiredClass');varEmail=false;</script>";
			warningMail = "";
		}
		
		
	}
		
	public boolean validaAliasFormat(String alias)
	{
		String cadena="0123456789,-_+¿?!¡ ";
		int val = 0;
		
		if(natural_person.getFirst_name()!=null){
			log.info("First Name: "+natural_person.getFirst_name());
			if(alias.toLowerCase().equals(natural_person.getFirst_name().toLowerCase()))
				return false;
		}
		if(natural_person.getFirst_name()!=null&&natural_person.getMiddle_name()!=null)
		if(alias.toLowerCase().equals((natural_person.getFirst_name()+natural_person.getMiddle_name()).toLowerCase()))
			return false;
		if(natural_person.getFirst_name()!=null&&natural_person.getMiddle_name()!=null&&natural_person.getFather_last_name()!=null)
		if(getNickname().toLowerCase().equals((natural_person.getFirst_name()+natural_person.getMiddle_name()+natural_person.getFather_last_name()).toLowerCase()))
			return false;
		if(natural_person.getFirst_name()!=null&&natural_person.getMiddle_name()!=null&&natural_person.getFather_last_name()!=null&&natural_person.getMother_last_name()!=null)
		if(getNickname().toLowerCase().equals((natural_person.getFirst_name()+natural_person.getMiddle_name()+natural_person.getFather_last_name()+natural_person.getMother_last_name()).toLowerCase()))
			return false;
		
		for(int i = 0; i<getNickname().length();i++){
			if (cadena.indexOf((getNickname().charAt(i)))!=(-1)){
				  val ++;
				  if(i==1)
					  return false;
			}
			if(val>4)
				return false;
		}
		
		return true;
	}
	
	public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
				
	public void changeReason()
	{
		if(registration_reason_id!=null&&registration_reason_id==7){
			displayOtherReason = true;
		}else{
			displayOtherReason=false;
			other_registration_reason=null;
		}
		if(registration_reason_id!=null&&registration_reason_id!=3){
			who_recommends = null;
		}
		if(registration_reason_id!=null&& registration_reason_id!=6 && registration_reason_id!=8 ){
			who_recommends = null;
		}
		// promotor_id = null;
	}

	public String changeMail()
	{			
		return NavigationRule.CAMBIO_CUENTA_CORREO.toString();		
	}
}
