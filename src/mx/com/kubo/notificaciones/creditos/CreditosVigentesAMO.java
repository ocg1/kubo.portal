package mx.com.kubo.notificaciones.creditos;

import java.rmi.RemoteException;

import mx.com.kubo.constantes.RiskSGBWebService;

public abstract class CreditosVigentesAMO extends CreditosVigentesDMO
{
	protected String asignarCreditoVigente(String solicitudBuroId)
	{					
		try 
		{
			bitacora.registrar(1, RiskSGBWebService.CREDITOS_VIGENTES);		
			
			asignarCreditosVigentes(solicitudBuroId);
			bitacora.registrar(2, RiskSGBWebService.CREDITOS_VIGENTES);
//TODO Desacoplado			
//			procesarCreditosVigentes(solicitudBuroId);
			creditos_vigentes_INFO = getCreditosVigentes(solicitudBuroId);
			
		} catch (RemoteException e) {
			bitacora.registrar(3, RiskSGBWebService.CREDITOS_VIGENTES, e.getMessage());
		}		
		
		return creditos_vigentes_INFO;
	}
	
	private void asignarCreditosVigentes(String solicitudBuroId) throws RemoteException
	{
		creditosVigentes = webService.getVtbur_infocredcte_vig(solicitudBuroId);
	}
	
//TODO Desacoplado
/*	
	private void procesarCreditosVigentes(String solicitudBuroId) throws ParametrosNotificacionException
	{
		if(existenCreditosVigentes())
		{
			notificacion.setDescNegativeBCScore("Con créditos vigentes en circulo");
		} else if(exitenCreditosCerradosSemestreAnterior(solicitudBuroId)) {
			notificacion.setDescNegativeBCScore("Con créditos cerrados en circulo en los últimos 6 meses");
		} else if(existenCreditosCerradosSemestrePosterior(solicitudBuroId)) {
			notificacion.setDescNegativeBCScore("Con créditos cerrados en circulo hace más de 6 meses");
		} else {		
			notificacion.setDescNegativeBCScore("Sin créditos vigentes en circulo");				
		}	
	}
*/
	
	private String getCreditosVigentes(String buro_solicitud_numero)
	{
		if(existenCreditosVigentes())
		{
			return "Con créditos vigentes en circulo";
		} else if(exitenCreditosCerradosSemestreAnterior(buro_solicitud_numero)) {
			return "Con créditos cerrados en circulo en los últimos 6 meses";
		} else if(existenCreditosCerradosSemestrePosterior(buro_solicitud_numero)) {
			return "Con créditos cerrados en circulo hace más de 6 meses";
		} else {		
			return "Sin créditos vigentes en circulo";				
		}
	}
	
	private boolean existenCreditosVigentes()
	{
		return creditosVigentes != null && creditosVigentes.length > 0;
	}
	
	private boolean exitenCreditosCerradosSemestreAnterior(String solicitudBuroId)
	{		
		boolean bandera = false;
		
		try 
		{
			bitacora.registrar(1, RiskSGBWebService.CERRADOS_SEMESTRE_ANTERIOR);
			asignarCreditosCerradosSemestreAnterior(solicitudBuroId);
			bitacora.registrar(2, RiskSGBWebService.CERRADOS_SEMESTRE_ANTERIOR);
			
			bandera = cerradosAnterior != null && cerradosAnterior.length > 0;
			
		} catch (RemoteException e) {
			bitacora.registrar(3, RiskSGBWebService.CERRADOS_SEMESTRE_ANTERIOR, e.getMessage());
		}
		
		return bandera;
	}
	
	private void asignarCreditosCerradosSemestreAnterior(String solicitudBuroId) throws RemoteException
	{
		cerradosAnterior = webService.getVtbur_infocredcte_c(solicitudBuroId);
	}
	
	private boolean existenCreditosCerradosSemestrePosterior(String solicitudBuroId)
	{
		boolean bandera = false;
		
		try 
		{
			bitacora.registrar(1, RiskSGBWebService.CERRADOS_SEMESTRE_POSTERIOR);
			asignarCreditosCerradosSemestrePosterior(solicitudBuroId);
			bitacora.registrar(2, RiskSGBWebService.CERRADOS_SEMESTRE_POSTERIOR);
			
			bandera = cerradosPosterior != null && cerradosPosterior.length > 0;
			
		} catch (RemoteException e) {
			bitacora.registrar(3, RiskSGBWebService.CERRADOS_SEMESTRE_POSTERIOR, e.getMessage());
		}
		
		return bandera;		
	}
	
	private void asignarCreditosCerradosSemestrePosterior(String solicitudBuroId) throws RemoteException
	{
		cerradosPosterior = webService.getVtbur_infocredcte_m(solicitudBuroId);
	}
}
