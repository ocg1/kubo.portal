package mx.com.kubo.components.recaptcha;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.com.kubo.managedbeans.SessionBean;

import org.primefaces.context.RequestContext;

public class RecaptchaComponent extends UIInput {

  static final String RecaptchaComponent_FAMILY = "RecaptchaComponentFamily";
  private String publicKey;
  private String privateKey;
  
  public RecaptchaComponent() {
	    super();
	    addValidator(new RecaptchaValidator());
	}

  @Override
  public final String getFamily() {
    return RecaptchaComponent_FAMILY;
  }

  public void setPublicKey(String s) {
    publicKey = s;
  }

  public void setPrivateKey(String s) {
    privateKey = s;
  }

  public String getPublicKey() {
    if (publicKey != null)
      return publicKey;
       
    ValueExpression ve = this.getValueExpression("publicKey");
    if (ve != null) {
      return (String)ve.getValue(getFacesContext().getELContext());
    } else {
      return publicKey;
    }
  }

  public String getPrivateKey() {
    if (privateKey != null)
      return privateKey;

    ValueExpression ve = this.getValueExpression("privateKey");
    if (ve != null) {
      return (String)ve.getValue(getFacesContext().getELContext());
    } else {
      return privateKey;
    }
  }
  
  public void refreshComponent(FacesContext ctx){
	  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Guardando Informacion espere un momento.!", "¡Guardando Informacion espere un momento.!");
		ctx.addMessage(getClientId(ctx), message);
		
      RequestContext requestContext = RequestContext.getCurrentInstance();
      requestContext.addPartialUpdateTarget("pnlCaptchaMsg");
  }
  
  @Override
  public void validate(FacesContext ctx) {
    Validator[] validators = getValidators();
    for (Validator v : validators) {
      try {
        v.validate(ctx, this, null);
        

       

		SessionBean session 
		    = (SessionBean) ctx.getApplication()
		        .getELResolver().getValue(ctx.getELContext(), null, "sessionBean");
		session.setDisplayScript(true);
        
		
        
        
      } catch (ValidatorException ex) {
        setValid(false);
        FacesMessage message = ex.getFacesMessage();
        if (message != null) {
          message.setSeverity(FacesMessage.SEVERITY_ERROR);
          ctx.addMessage(getClientId(ctx), message);
          
          RequestContext requestContext = RequestContext.getCurrentInstance();
          requestContext.addPartialUpdateTarget("pnlCaptchaMsg");
          
        }
      }catch(Exception e){
	      	setValid(false);
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
	        if (message != null) {
	          message.setSeverity(FacesMessage.SEVERITY_ERROR);
	          ctx.addMessage(getClientId(ctx), message);
	          
	          RequestContext requestContext = RequestContext.getCurrentInstance();
	          requestContext.addPartialUpdateTarget("pnlCaptchaMsg");
        }
    }

      super.validate(ctx);
    }
  }
  
}