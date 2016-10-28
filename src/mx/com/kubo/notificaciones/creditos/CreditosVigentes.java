package mx.com.kubo.notificaciones.creditos;

import java.io.Serializable;

import javax.xml.rpc.ServiceException;

import mx.com.kubo.constantes.RiskSGBWebService;

import org.springframework.stereotype.Service;

import com.soa.webServices.WsSgbRiskServiceLocator;

@Service(value="creditosVigentesNotificacionImpl") @SuppressWarnings("serial") 
public class CreditosVigentes extends CreditosVigentesAMO 
implements Serializable
{
	public CreditosVigentes()
	{				
		try {			
			locator       = new WsSgbRiskServiceLocator();
			webService    = locator.getWsSgbRisk();
		} catch (ServiceException e) { 
			bitacora.registrar(3, RiskSGBWebService.CREDITOS_VIGENTES, e.getMessage());
		}		
	}
	
	public String getINFO(String solicitudBuroId)
	{
		return super.asignarCreditoVigente(solicitudBuroId);
	}	
}
