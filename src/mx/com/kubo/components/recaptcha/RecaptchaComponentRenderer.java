package mx.com.kubo.components.recaptcha;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;



public class RecaptchaComponentRenderer extends Renderer {

	  static final String RENDERERTYPE = "RecaptchaComponentRenderer";

	  @Override
	  public void decode(FacesContext context,
	    UIComponent component) {
	    if (component instanceof UIInput) {
	      UIInput input = (UIInput) component;
	      String clientId = input.getClientId(context);

	      Map requestMap =
	        context.getExternalContext().getRequestParameterMap();
	      String newValue = (String) requestMap.get(clientId);
	      if (null != newValue) {
	        input.setSubmittedValue(newValue);
	      }
	    }
	  }

	  @Override
	  public void encodeBegin(FacesContext ctx,
	    UIComponent component) throws IOException {
	  }

	  @Override
	  public void encodeEnd(FacesContext ctx,
	    UIComponent component)
	    throws IOException {
	   
	    if (component instanceof RecaptchaComponent) {       
	      RecaptchaComponent rc = (RecaptchaComponent) component;
	      String publicKey = rc.getPublicKey();
	      String privateKey = rc.getPrivateKey();
	      if (publicKey == null || privateKey == null) {
	        throw new IllegalArgumentException("reCaptcha keys cannot be null. This is probably a component bug.");
	      }

	      ReCaptcha c = ReCaptchaFactory.newSecureReCaptcha(publicKey, privateKey, false);
	      
	      ((ReCaptchaImpl) c).setRecaptchaServer("https://www.google.com/recaptcha/api");
	      
	      String createRecaptchaHtml = c.createRecaptchaHtml(null, null);
	      ResponseWriter writer = ctx.getResponseWriter();
	      writer.write(createRecaptchaHtml);
	    }
	  }
	}