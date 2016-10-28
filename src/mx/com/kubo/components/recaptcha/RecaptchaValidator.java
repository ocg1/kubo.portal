package mx.com.kubo.components.recaptcha;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class RecaptchaValidator implements Validator {

	  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

	    //request.getAsyncContext();
	    if (component instanceof RecaptchaComponent) {
	      RecaptchaComponent c = (RecaptchaComponent)component;
	      String remoteAddr = request.getRemoteAddr();
	      ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	      reCaptcha.setPrivateKey(c.getPrivateKey());
	      
			      String challenge =
			        request.getParameter("recaptcha_challenge_field");
			      String uresponse =
			        request.getParameter("recaptcha_response_field");
			      
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*******************************************CAPTCHA_VALIDATOR*****************************************************");
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*****************************************************************************************************************");
			      System.out.println("*****************************************************************************************************************");
			      Date d1 = new Date();
			      ReCaptchaResponse reCaptchaResponse =
			        reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
			      Date d2 = new Date();
			      
			      Long t = d2.getTime() - d1.getTime();
			      
			      
			      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			      System.out.println("++++++++++++++++++++++++++++++++++++++++++REGRESANDO+DESPUES+DE++"+t+"+milisegundos++++++++++++++++++++++++++++++++++");
			      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			      if (!reCaptchaResponse.isValid()) {
			        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Captcha incorrecto, vuelva a intentarlo!", "¡Captcha incorrecto vuelva a intentarlo!"));
			      }
	    }
	  }
	  
	  
	  
	}