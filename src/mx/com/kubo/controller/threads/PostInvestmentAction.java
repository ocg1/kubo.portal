package mx.com.kubo.controller.threads;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.InvestmentProgressDetService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.TiendaCreditosService;
import mx.com.kubo.tools.Utilities;

public class PostInvestmentAction extends Thread {

	private List<ServiceCalling> 			lstService;
	private List<String>					strStored;
	private List<InvestmentProgressDet>		lstProgressDet;
	private ServiceCallingService 			service;
	private ProyectLoanService				proyectloanservice;
	private TiendaCreditosService 			tiendacreditosservice;
	private InvestmentProgressDetService 	investmentprogressdetservice;
	
	public PostInvestmentAction(){
		service 						= Utilities.findBean("serviceCallingServiceImp");
		proyectloanservice				= Utilities.findBean("proyectLoanServiceImp");
		tiendacreditosservice			= Utilities.findBean("tiendaCreditosServiceImp");
		investmentprogressdetservice	= Utilities.findBean("investmentProgressDetServiceImp");
	}
	
	@Override
	public void run() {
		
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		
		Calendar c1 = Calendar.getInstance();
		Calendar c05 = Calendar.getInstance();
		c1.setTime(new Date());
		
		System.out.println("ejecutando Run");
		
		try{
			
			
			ejecutaStored();
			
			c2 = Calendar.getInstance();
			c2.setTime(new Date());
			
			long l1 = c2.getTimeInMillis() - c1.getTimeInMillis();
			System.out.println( "Tiempo en ejecutar los stored: "+l1+" milisegundos" );
			
			insertaService();
			
			c3 = Calendar.getInstance();
			c3.setTime(new Date());
			
			long l2 = c3.getTimeInMillis() - c2.getTimeInMillis();
			
			System.out.println( "Tiempo en ejecutar los inserts en service_calling: "+l2+" milisegundos" );
			
			tiendacreditosservice.actualizaTienda();
			
			Calendar c04 = Calendar.getInstance();
			c04.setTime(new Date());
			
			long l02 = c04.getTimeInMillis() - c3.getTimeInMillis();
			
			System.out.println( "Tiempo en actualizar la tienda: "+l02+" milisegundos" );
			
			insertaProgressDet();
			
			c05 = Calendar.getInstance();
			c05.setTime(new Date());
			
			long l05 = c05.getTimeInMillis() - c04.getTimeInMillis();
			
			System.out.println( "Tiempo en ejecutar insertaProgressDet : "+l05+" milisegundos" );
			
			System.out.println("Finaliza Thread");
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		Calendar c4 = Calendar.getInstance();
		c4.setTime(new Date());
		
		long l3 = c4.getTimeInMillis() - c05.getTimeInMillis();
		
		System.out.println( "Tiempo en actualizar la tienda: "+l3+" milisegundos" );
		
		long l4 = c4.getTimeInMillis() - c1.getTimeInMillis();
		System.out.println( "Tiempo TOTAL en ejecutar EL THREAD: "+l4+" milisegundos" );
		
		System.out.println("Finaliza Run");
		
	}
	
	private void  ejecutaStored(){
		
		if(strStored!=null){
			
			for( String  storedItem :  strStored  ){
				try{
				
					proyectloanservice.spSetOnProyectFunding( Integer.parseInt( storedItem.split("::")[0] ) , Integer.parseInt( storedItem.split("::")[1] ) , Integer.parseInt( storedItem.split("::")[2] ) , Integer.parseInt( storedItem.split("::")[3] ) , Integer.parseInt( storedItem.split("::")[4] ) , Double.parseDouble( storedItem.split("::")[5] ), storedItem.split("::")[6] );
				
				}catch( Exception e ){
					e.printStackTrace();
				}
			}
		}
	}
	
	private void insertaService(){
		
		if(lstService!=null){
			
			for( ServiceCalling serviceItem :  lstService  ){
				try{
				
					service.saveServiceCall(serviceItem);
				
				}catch( Exception e ){
					e.printStackTrace();
				}
			}
		}
	}
	
	private void insertaProgressDet(){
		
		if( lstProgressDet != null ){
		
			for( InvestmentProgressDet progressdet : lstProgressDet ){
				try{
				
					investmentprogressdetservice.saveInvestmentProgressDet( progressdet );
					
				}catch( Exception e ){
					e.printStackTrace();
				}
			}
		
		}
	}
	
	public List<ServiceCalling> getLstService() {
		return lstService;
	}

	public void setLstService(List<ServiceCalling> lstService) {
		this.lstService = lstService;
	}

	public List<String> getStrStored() {
		return strStored;
	}

	public void setStrStored(List<String> strStored) {
		this.strStored = strStored;
	}

	public ServiceCallingService getService() {
		return service;
	}

	public void setService(ServiceCallingService service) {
		this.service = service;
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}

	public List<InvestmentProgressDet> getLstProgressDet() {
		return lstProgressDet;
	}

	public void setLstProgressDet(List<InvestmentProgressDet> lstProgressDet) {
		this.lstProgressDet = lstProgressDet;
	}

	public InvestmentProgressDetService getInvestmentprogressdetservice() {
		return investmentprogressdetservice;
	}

	public void setInvestmentprogressdetservice(InvestmentProgressDetService investmentprogressdetservice) {
		this.investmentprogressdetservice = investmentprogressdetservice;
	}
	
}
