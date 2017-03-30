package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientoDepositoDMO;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientoDisposicionDMO;
import mx.com.kubo.services.investor.ServiceMovimientosIMP;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

import static mx.com.kubo.notificaciones.notificables.Evento.DEPOSITO_DE_EFECTIVO;
import static mx.com.kubo.notificaciones.notificables.Evento.DISPOSICION_DE_EFECTIVO;

@ManagedBean @ViewScoped
public class DisposicionEfectivo extends DisposicionEfectivoAMO 
implements Serializable 
{
	private static final long serialVersionUID = 6442848920271115214L;

	@PostConstruct
	public void init()
	{		
		faces = FacesContext.getCurrentInstance();
		
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		membership_PK = new MembershipPK(prospectus_id, company_id);

		membership = membership_service.getMembershipById(membership_PK);
		
		SAFI_client_id = membership.getPerson().getSafi_client_id();
		
		//SavingAccount saving = savingaccountservice.getSavingAccountByProspectus(naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id());
		 
		//if(saving != null){
			SAFI_cuenta = new ArrayList<String>();
			SAFI_cuenta.add(SAFI_client_id);
			
			service_movimientos = new ServiceMovimientosIMP();
			
			service_movimientos.setSAFI_client_id(SAFI_client_id);
			service_movimientos.setupClientSAFIData(SAFI_cuenta);
			
			listInvAccounts = service_movimientos.getLista_cuentas();
		//}
		 	
		format = new SimpleDateFormat("dd/MM/yyyy");
		
		fechaDep = format.format(new Date());
		
		// saldoActual = getTotalCreditOnAccounts();
		
		init_lista_cuentas();	
		
		capital_neto = capitalnetoservice.getMaxCapitalNeto();
		 
		saldotope = capital_neto.getMx_capital_neto();
		
		init_cuenta_CLABE();	
	}

	public final void notifica()
	{		
		requestContext = RequestContext.getCurrentInstance();
		
		System.out.println("DisposicionEfectivo.notifica() : "+monto_SELECTED+"  ---");
		
		 try 
		 {			 
			 disposicion_param_values = new MovimientoDisposicionDMO( monto_SELECTED, cuentaActual , "1");
			 disposicion_param_values.setMotivo_disposicion(motivo_disposicion);
			 
			 Membership emisor     = membership;
			 Membership acreditado = membership;
			 
			 notificador = new NotificadorIMP();
			 notificador.setEmisor(emisor);
			 notificador.setAcreditado(acreditado);
			 
			 notificador.notificar(DISPOSICION_DE_EFECTIVO, disposicion_param_values);
			 
			 System.out.println("NotificacionesTestPMO.notificar_disposicion_efectivo(): OK");
			 			 
			 requestContext.addCallbackParam("success", true);			 
			 
		 } catch (NotificacionException e) {
			 
			 e.printStackTrace();
			 
			 requestContext.addCallbackParam("success", false);			
		 }		
	}
	
	public void notificaDeposito()
	{		
		requestContext = RequestContext.getCurrentInstance();
		
		System.out.println(" ---  notificaDeposito  ---");
		
		 try 
		 {			 
			 deposito_param_values = new MovimientoDepositoDMO( monto_SELECTED_dep, cuentaActual , "1", fechaDep, descDep );
			 
			 Membership emisor     = membership;
			 Membership acreditado = membership;
			 
			 notificador = new NotificadorIMP();
			 notificador.setEmisor(emisor);
			 notificador.setAcreditado(acreditado);
			 
			 notificador.notificar(DEPOSITO_DE_EFECTIVO, deposito_param_values);
			 
			 notificaSGB();
			 
			 System.out.println("NotificacionesTestPMO.notificar_deposito_efectivo(): OK");
			 
			 requestContext.addCallbackParam("success", true);
			 
		 } catch (NotificacionException e) {
			 
			 System.out.println("NotificacionesTestPMO.notificar_deposito_efectivo(): ERROR  inicio -->");
			 
			 e.printStackTrace();
			 
			 System.out.println(" <-- NotificacionesTestPMO.notificar_deposito_efectivo(): ERROR");
			 
			 requestContext.addCallbackParam("success", false);			 
		 }		
	}
	
	private void notificaSGB(){
		try{
			
			WsSgbRiskServiceLocator locatorSGB = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locatorSGB.getWsSgbRisk();// yyyymmdd. 19860131
			
			servicecallingservice.registrar(0, membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id(), 
					"Invocando getWsSgbRisk.depositNotification: ( " +
											membership.getMembershipPK().getProspectus_id() + " ,"  +
											membership.getEmail()	+ " ,"  +
											SAFI_client_id	+ " ,"  +
											clabeSel	+ " ,"  +
											bnkStr	+ " ,"  +
											cuentaActual	+ " ,"  +
											deposito_param_values.getMonto().replace("\\$", "").replaceAll(",", "")	+ " ,"  +
											deposito_param_values.getFecha_deposito()	+ " ,"  +
											deposito_param_values.getDescripcion()	+ " ,"  +
											cuentaActual	+ " ) ");
			
			 WsSgbResponse response = service.depositNotification(
											membership.getMembershipPK().getProspectus_id() + "", 
											membership.getEmail(), 
											SAFI_client_id, 
											clabeSel, 
											bnkStr, 
											cuentaActual, 
											deposito_param_values.getMonto().replace("\\$", "").replaceAll(",", ""), 
											deposito_param_values.getFecha_deposito(), 
											deposito_param_values.getDescripcion(), 
											cuentaActual
										);
			
			 if( response != null && response.getStatus().equals("0") ){
			 
				 servicecallingservice.registrar(1, membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id(), response.getMessage()  );
			
			 }else {
				 
				 if( response != null) {
					 servicecallingservice.registrar(1, membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id(), "" , response.getMessage() );
				 }else{
					 servicecallingservice.registrar(1, membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id(), "" , "SERVICIO REGRESO RESPUESTA NULL" );
				 }
				 
			 }
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public Double getTotalCreditOnAccounts()
	{
		Double suma=0.00;
		
		try {
			
			for (InvestorsAccounts iterElement : getListInvAccounts()) 
			{
				suma=suma+iterElement.getSaldo();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return suma;
	}
		
}
