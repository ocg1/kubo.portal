package mx.com.kubo.managedbeans.portal.ofertas;

import static mx.com.kubo.notificaciones.notificables.Evento.PUBLICACION;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMP;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;
import mx.com.kubo.portal.ofertas.ParserRenovacionAutomaticaIMP;
import mx.com.kubo.portal.ofertas.SimuladorIMP;

@ViewScoped
@ManagedBean(name = "ofertaRenovacion") 
public class OfertaRenovacionIMP extends OfertaRenovacionAMO 
implements Serializable 
{
	private static final long serialVersionUID = 6275801449204523658L;
	
	@PostConstruct
	public void init()
	{		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");		
		
		if(sesion != null & sesion.getProspectus_id() != null)
		{
			lista_loan_type = service_proyect_loan.getLista_loan_type();			
			listPurpose     = service_purpose.getPurposeList();		
			
			prospectus_id = sesion.getProspectus_id();
			company_id    = sesion.getCompany_id();
			
			score = service_scoring.loadMaxScoringByProspectus(prospectus_id, company_id);
			
			if(score != null)
			{
				rate = score.getRate();
				opening_commission = score.getOpening_commission();
				
				proyect_loan = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
				
				Integer status_id = proyect_loan.getStatus_id();
				String is_automatic_aproved = proyect_loan.getIs_automatic_aproved();	
				
				publicacion_ENABLED = status_id < PUBLICADO ? true : false;
								
				if(publicacion_ENABLED)
				{				
					sesion.setOpeningCommission(opening_commission);				
					sesion.setRate(rate);
					
					parser = new ParserRenovacionAutomaticaIMP();		
					parser.setSesion(sesion);
					parser.setScore(score);				
					parser.init();
					
					simulador = new SimuladorIMP();
					simulador.setSesion(sesion);
					simulador.init();	
					
					reasignador = new ReasignadorIMP();
					reasignador.setSesionBean(sesion);
					
				} else {
					
					is_automatic_aproved = is_automatic_aproved != null ? is_automatic_aproved : "N";
					
					automatic_aproved_ENABLED = is_automatic_aproved.equals("S") ? true : false;
				}
			}	
			
			initAccess( sesion );
			
		}
	}
	
	public void check_automatic_aproved()
	{
		request = RequestContext.getCurrentInstance();
		
		request.addCallbackParam("automatic_aproved_ENABLED", automatic_aproved_ENABLED);
	}
	
	public void init_SAFI_credit_id(AjaxBehaviorEvent event)
	{		
		request = RequestContext.getCurrentInstance();
		
		select_one = (HtmlSelectOneMenu) event.getComponent();
		
		SAFI_credit_id = select_one.getValue().toString();		
		
		if(SAFI_credit_id.length() > 2)
		{		
			renovacion = parser.getRenovacion(SAFI_credit_id);
			
			proyect_loan = service_proyect_loan.getProyectLoanListBySafiCreditID(SAFI_credit_id);			
				
			actual_rate = proyect_loan.getRate();
			
			same_rate_ENABLED = actual_rate.toString().equals(rate.toString());
			
			init_ofert_rate_TOKEN();					
						
			reasignador.setProyect_loan_reasignable(proyect_loan);	
							
			request.addCallbackParam("SAFI_credit_id", SAFI_credit_id);
			request.addCallbackParam("same_rate_ENABLED", same_rate_ENABLED);
			request.addCallbackParam("ofert_TOKEN", ofert_TOKEN);
			request.addCallbackParam("ofert_rate_TOKEN", ofert_rate_TOKEN);							
		}			
	}
	
	public void init_panel_simulador()
	{
		request = RequestContext.getCurrentInstance();		
		
		panel_simulador_ENABLED = true;
		
		simulador.setRenovacion(renovacion);
		
		request.addCallbackParam("panel_simulador_ENABLED", panel_simulador_ENABLED);
		request.addCallbackParam("max_ammount", renovacion.getMax_ammount());
		request.addCallbackParam("max_payment", renovacion.getMax_payment());
	}
	
	
	public void init_preaprobador(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		int where_to_recieve_data = Integer.parseInt(input_text.getValue().toString());
		
		switch(where_to_recieve_data)
		{
			case FROM_TABLA_OFERTAS:
				
				init_request_oferta();
				
			break;
			
			case FROM_SIMULATOR:
				
				max_payment_ENABLED = simulador.isMax_payment_ENABLED();								
				
				reasignador.setMax_payment_ENABLED(max_payment_ENABLED);
				
				String total_payment = format.format(simulador.getSimulation().getTotal_payment());
				String ammount = format.format(simulador.getSimulation().getAmmount());							
				String payment = format.format(simulador.getSimulation().getPayment());				
				String mx_cat  = simulador.getSimulation().getMx_cat() + "";
				String term_id = simulador.getSimulation().getTerm_id() + "";
				
				int frequency_id = simulador.getSimulation().getFrequency_id();
				
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
				
				request.addCallbackParam("tabla_amoritizacion_URL", tabla_amoritizacion_URL);
				request.addCallbackParam("max_payment_ENABLED", max_payment_ENABLED);
				
				request.addCallbackParam("interes",            sesion.getRate());
				request.addCallbackParam("opening_commission", sesion.getOpeningCommission());
				
				request.addCallbackParam("plazo",  simulador.getTerm_frequency_TOKEN());
				
				request.addCallbackParam("ammount",  ammount);																			
				request.addCallbackParam("mx_cat", mx_cat);
				request.addCallbackParam("pago_mensual", payment);
				request.addCallbackParam("total_payment", total_payment);
				request.addCallbackParam("ammount", ammount);
				request.addCallbackParam("ammount_commission", format.format(simulador.getSimulation().getOpening_commission_amount()));												
				request.addCallbackParam("total_recibir",      format.format(simulador.getSimulation().getTotal_to_receive()));	
			break;
		}				
		
		panel_simulador_ENABLED   = false;
		panel_preaprobado_ENABLED = true;
		
		request.addCallbackParam(  "panel_simulador_ENABLED", panel_simulador_ENABLED);
		request.addCallbackParam("panel_preaprobado_ENABLED", panel_preaprobado_ENABLED);
	}		

	public void obtener_prestamo()
	{							
		request = RequestContext.getCurrentInstance();
		
		loan_type_id = parser.getLoan_type_id();
			
		reasignador.setMax_payment_ENABLED(max_payment_ENABLED);
		reasignador.init_renovacion_aprobacion_automatica(loan_type_id);
		
		boolean new_proyect_OK = reasignador.isNew_proyect_OK();
		
		if(new_proyect_OK)
		{		
			membership_PK = new MembershipPK(prospectus_id, company_id);
			
			membership = service_membership.getMembershipById(membership_PK);		
			
			ProyectLoan proyect_loan = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id);
	
			try 
			{
				notificador = new NotificadorIMP();
			 	notificador.setEmisor(membership);
				notificador.notificar(PUBLICACION, score, proyect_loan, "");
				
			} catch (NotificacionException e) {
				
				e.printStackTrace();
			}
		}
		
		request.addCallbackParam("new_proyect_OK", new_proyect_OK);
		request.addCallbackParam("max_payment_ENABLED", max_payment_ENABLED);
		request.addCallbackParam("loan_type_id", loan_type_id);
	}
}
