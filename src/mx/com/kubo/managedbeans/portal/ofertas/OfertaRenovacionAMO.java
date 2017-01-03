package mx.com.kubo.managedbeans.portal.ofertas;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;

public abstract class OfertaRenovacionAMO extends OfertaRenovacionDMO 
{
	protected void init_ofert_rate_TOKEN() 
	{	
		sb = new StringBuilder();
		sb.append(PRESTAMO_APROBADO);
		
		if(!same_rate_ENABLED)
		{
			sb.append(MEJOR_TASA);
		}
		
		ofert_TOKEN = sb.toString();
		
		
		ofert_rate_TOKEN = "";
		
		if(!same_rate_ENABLED)
		{
			sb = new StringBuilder();
			sb.append("Tasa anterior ");
			sb.append(actual_rate.toString()).append("%");
			sb.append(" Tasa Actual ");
			sb.append(rate.toString()).append("%");
			
			ofert_rate_TOKEN = sb.toString();
		}
	}
	
	protected void init_request_oferta() 
	{
		String total_payment = format.format(parser.getSimulation().getTotal_payment());
		String ammount = format.format(parser.getSimulation().getAmmount());
		String payment = format.format(parser.getSimulation().getPayment());				
		String mx_cat  = parser.getSimulation().getMx_cat() + "";
		String term_id = parser.getSimulation().getTerm_id() + "";
		
		int frequency_id = parser.getSimulation().getFrequency_id();
		
		String frequency = "";
		
		switch(frequency_id)
		{
			case 1:
				frequency = "mensuales";
			break;
			
			case 2:
				frequency = "catorcenales";
			break;
			
			case 3:
				frequency = "quincenales";
			break;
			
			case 4:
				frequency = "semanales";
			break;
		}
		
		sb = new StringBuilder();
		sb.append(TABLA_AMORTIZACION_XHTML).append("?");
		sb.append("monto=").append(ammount).append("::");
		sb.append("term=").append(term_id).append("::");
		sb.append("rate=").append(sesion.getRate()).append("::");
		sb.append("payment=").append(payment).append("::");
		sb.append("frequency=").append(frequency).append("::");
		sb.append("comision=").append(sesion.getOpeningCommission()).append("::");
		sb.append("totalPayment=").append(total_payment).append("::");
		sb.append("cat=").append(mx_cat);
		
		String tabla_amoritizacion_URL = sb.toString(); 
		
		max_payment_ENABLED = false;
		
		request.addCallbackParam("tabla_amoritizacion_URL", tabla_amoritizacion_URL);
		request.addCallbackParam("max_payment_ENABLED", max_payment_ENABLED);
		
		request.addCallbackParam("interes",            sesion.getRate());
		request.addCallbackParam("opening_commission", sesion.getOpeningCommission());
		
		request.addCallbackParam("plazo", parser.getTerm_frequency_TOKEN().substring(2) );	
		
		request.addCallbackParam("mx_cat", mx_cat);
		request.addCallbackParam("pago_mensual", payment);
		request.addCallbackParam("total_payment", total_payment);
		request.addCallbackParam("ammount", ammount);
		request.addCallbackParam("ammount_commission", format.format(parser.getSimulation().getOpening_commission_amount()));												
		request.addCallbackParam("total_recibir",      format.format(parser.getSimulation().getTotal_to_receive()));		
	}
	
	protected void initAccess( SessionBean sesion ){
		
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		
		access.setScreen_id( 81 );
		
		access.setPercentage(0);
		
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUrl_access		  (sesion.getUrl_access());
		
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, false);
		
	}
}
