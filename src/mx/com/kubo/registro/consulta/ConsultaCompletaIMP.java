package mx.com.kubo.registro.consulta;

import java.util.ResourceBundle;

import org.primefaces.context.RequestContext;

import com.soa.webServices.WsSgbRiskServiceLocator;

public final class ConsultaCompletaIMP extends ConsultaCompletaAMO
implements ConsultaCompletaIMO
{
	public final void init()
	{
		init_credit_history();
	}
	
	public void callWSSGB()
	{			
		try
		{
			System.out.println("Preaprobacion.callWSSGB()");
			
			recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");				
			
			request = RequestContext.getCurrentInstance();
			request.addCallbackParam("blocked_person_ENABLED", blocked_person_ENABLED);
			
			if(!blocked_person_ENABLED)
			{			
				if(has_credito_ENABLED)
				{			
					
					locator = new WsSgbRiskServiceLocator();
					service = locator.getWsSgbRisk();
					
					user     = "";
					password = "";

					if(score == null)
					{		
						is_prospect_SGB_OK = creaProspectSGB();
						
						if(is_prospect_SGB_OK)
						{		
							asignar_credit_history_attempt();
							
						} else {
							
							success = false;
							wait = false;
						    fail = false;
						    errorMsg = "Error en el proceso de alta en Mesa de control comuniquese a kubo.financiero. Disculpe las molestias.";
							error = true;
							noHit = false;
						}
					}	
					
					asignar_score();
					
				} 
			}
			
		} catch(Exception e) {
			
			asignar_error_consulta(e);
		}		
	}
}
