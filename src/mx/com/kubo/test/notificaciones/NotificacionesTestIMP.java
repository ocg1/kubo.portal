package mx.com.kubo.test.notificaciones;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientoCancelacionDMO;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientoDisposicionDMO;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientoDepositoDMO;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

import static mx.com.kubo.notificaciones.notificables.Evento.CANCELACION_CUENTA_INVERSIONES;
import static mx.com.kubo.notificaciones.notificables.Evento.CANCELACION_CUENTA_INVERSIONISTA;

@ManagedBean (name = "notificacionesTest") @ViewScoped 
public class NotificacionesTestIMP extends NotificacionesTestPMO
implements NotificacionesTestIMO, Serializable
{	
	private static final long serialVersionUID = -6842898647951717182L;

	public final void notificar_disposicion_efectivo()
	{
		try 
		{
			param_values_bean = new MovimientoDisposicionDMO("monto_SELECTED", "cuenta_SELECTED", "1");
			
			emisor     = membership_service.getMembershipByEmail("damian@kubofinanciero.com");		
			acreditado = membership_service.getMembershipByEmail("d@test.com");
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(emisor);
			notificador.setAcreditado(acreditado);			
			notificador.notificar(Evento.DISPOSICION_DE_EFECTIVO, param_values_bean);
				
			System.out.println("NotificacionesTestPMO.notificar_disposicion_efectivo(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	public final void notificar_deposito_efectivo()
	{
		try 
		{
			String monto = "$100,000.00";
			String cuenta = "00100007240"; 
			String clabe_account_id = "1"; 
			String fecha_deposito = "19 de Abril del 2017"; 
			String descripcion = "Test notificación movimiento depósito de efectivo";
			
			param_values_bean = new MovimientoDepositoDMO(monto, cuenta, clabe_account_id, fecha_deposito, descripcion);
			
			emisor     = membership_service.getMembershipByEmail("damian@kubofinanciero.com");		
			acreditado = membership_service.getMembershipByEmail("d@test.com");
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(emisor);
			notificador.setAcreditado(acreditado);			
			notificador.notificar(Evento.DEPOSITO_DE_EFECTIVO, param_values_bean);
				
			System.out.println("NotificacionesTestPMO.notificar_deposito_efectivo(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	public final void notificar_cancelacion_cuenta()
	{
		try 
		{
			param_values_bean = new MovimientoCancelacionDMO("00100008874", "motivo_id", "motivo de la cancelación", "100100000211-1", "descripcion de la cancelacion");
				
			membership_PK = new MembershipPK(12311, 1);
			emisor = membership_service.getMembershipById(membership_PK);
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(emisor);	
			notificador.notificar(CANCELACION_CUENTA_INVERSIONES, param_values_bean);
				
			System.out.println("NotificacionesTestPMO.notificar_disposicion_efectivo(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	public final void notificar_cancelacion_cuenta_inversionista()
	{
		try 
		{
			param_values_bean = new MovimientoCancelacionDMO("00100008874",  "motivo_id", "motivo de la cancelación", "100100000211-1", "descripcion de la cancelacion");
				
			membership_PK = new MembershipPK(12311, 1);
			emisor = membership_service.getMembershipById(membership_PK);
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(emisor);	
			notificador.notificar(CANCELACION_CUENTA_INVERSIONISTA, param_values_bean);
				
			System.out.println("NotificacionesTestPMO.notificar_disposicion_efectivo(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	public final void notificar_publicacion()
	{
		try 
		{										
			acreditado = membership_service.getMembershipByEmail("extapia26@gmail.com");
			
			proyect_loan = proyect_loan_service.getMaxProyectLoanByProspect(12347, 1);
			
			notificador = new NotificadorIMP();
			notificador.setEmisor(acreditado);			
			notificador.notificar(Evento.PUBLICACION, proyect_loan.getScoring(), proyect_loan, "");
				
			System.out.println("NotificacionesTestPMO.notificar_publicacion(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
}
