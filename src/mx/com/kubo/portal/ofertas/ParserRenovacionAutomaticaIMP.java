package mx.com.kubo.portal.ofertas;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class ParserRenovacionAutomaticaIMP extends ParserRenovacionAutomaticaAMO 
implements ParserRenovacionAutomaticaIMO
{
	public void init()
	{						
		init_ofert_ENABLED();
		
		if(ofert_ENABLED)
		{
			init_R_data();
		}
	}
	
	public boolean is_ofert_ENABLED()
	{
		init_ofert_ENABLED();
		
		return ofert_ENABLED;
	}
	
	public void init_oferta(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		oferta_TOKEN = input_text.getValue().toString();
		
		oferta_TOKEN_split = oferta_TOKEN.split("::", 4);
		
		term_frequency_TOKEN = oferta_TOKEN_split[0];
		
		term_id = Integer.parseInt(term_frequency_TOKEN.split(" ", 3)[1]);
		
		String frequency_TOKEN = term_frequency_TOKEN.split(" ", 3)[2];				
		
		if(frequency_TOKEN.equals("semanas"))
		{
			frequency_id = 1;
			
		} else if(frequency_TOKEN.equals("catorcenas")) {
			
			frequency_id = 2;
			
		} else if(frequency_TOKEN.equals("quincenas")) {
			
			frequency_id = 3;
			
		} else if(frequency_TOKEN.equals("meses")) {
			
			frequency_id = 4;
		}
		
		ammount = Double.parseDouble(oferta_TOKEN_split[1].replace(",", "").replace("$",""));
		payment = Double.parseDouble(oferta_TOKEN_split[2].replace(",", "").replace("$",""));
		
		String oferta_SELECTED = oferta_TOKEN_split[3];
		
		request.addCallbackParam("frequency", term_frequency_TOKEN);		
		request.addCallbackParam("term_id", term_id);
		request.addCallbackParam("frequency_id", frequency_id);		
		request.addCallbackParam("ammount", ammount);
		request.addCallbackParam("payment", payment);
		request.addCallbackParam("oferta_SELECTED", oferta_SELECTED);
	}
	
	public final void init_simulator()
	{				
		request = RequestContext.getCurrentInstance();
				
		init_CAT_simulation();
		
		request.addCallbackParam("cat", simulation.getMx_cat());
	}
}
