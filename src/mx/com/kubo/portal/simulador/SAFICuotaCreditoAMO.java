package mx.com.kubo.portal.simulador;

import java.util.Date;

import mx.com.kubo.model.ServiceCalling;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;

public class SAFICuotaCreditoAMO extends SAFICuotaCreditoDMO 
{
	protected void init_request() 
	{
		request = new SimuladorCuotaCreditoRequest();
		
		request.setFechaInicio(thisFechaInicio);
		request.setFrecuencia(freqStr);
		request.setMontoSolici(ammount+"");
		request.setPlazo(term_id+"");
		
		request.setTasaAnualizada(tasaTotal + "");
		request.setAjustarFecVen("N");
		montoComisionApert = ((ammount * comisionApertura) / 100);
		request.setComisionApertura(montoComisionApert + "");
		request.setFormaCobroComAp("D");
		
		srvCall = new ServiceCalling();
		
		if( prospectus_id !=0 )
		{
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id( company_id );
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito");
			srvCall.setProspectus_id( prospectus_id );
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
		}
	}
	
	protected void init_response( ) 
	{
		try
		{
			locator = new SAFIServiciosServiceLocator();
			service = locator.getSAFIServiciosSoap11();
			
			response =  service.simuladorCuotaCredito(request);
										
				if(response.getCodigoRespuesta().equals("0"))
				{
					if( prospectus_id !=0 )
					{
						srvCall = new ServiceCalling();
			
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id( company_id );
						srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito: "+response.getMensajeRespuesta());
						srvCall.setProspectus_id( prospectus_id);
						srvCall.setStatus(2);
						
						servicecallingRepository.saveServiceCall(srvCall);
					}
					
					cat = (Double.parseDouble(response.getCat().replace(",", "")));
					//System.out.println("Cat Antes:  -->"+getCat());
					cat = ((double) Math.ceil(cat*10)/10);
					//System.out.println("Cat Después:  -->"+getCat());
					//setCatStr(response.getCat());
					montoCuota = Double.parseDouble(response.getMontoCuota().replace(",", ""));
					numCuota   = Integer.parseInt(response.getNumeroCuotas().replace(",", ""));
					totalPagar = Double.parseDouble(response.getTotalPagar().replace(",", ""));
					
					//Guarda solo si la frecuencia es Semanal o Catorcenal
					if(frequency_id == 1 || frequency_id == 2)
					{
						flagSaveSimulationCache = true;
						
					} else {
						
						flagSaveSimulationCache = false;							
					}
					
					
				} else {
					
					if( prospectus_id !=0 )
					{
						srvCall = new ServiceCalling();
						
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id( company_id );
						srvCall.setException(response.getMensajeRespuesta());
						srvCall.setProspectus_id( prospectus_id );
						srvCall.setStatus(3);
						servicecallingRepository.saveServiceCall(srvCall);
						
						//setCat(Double.parseDouble(response.getCat().replace(",", "")));
						catStr = "No disponible";
						montoCuota = 0D;
						numCuota   = 0;
						totalPagar = 0D;
						
					}
						flagSaveSimulationCache = false;
					
				}
				
		} catch(Exception e){
			e.printStackTrace();
			catStr = "No disponible";
			montoCuotaStr = "No disponible";
			
			totalPagar = 0D;
			flagSaveSimulationCache = false;
		}
	}
	
	protected void init_simulation_cache() 
	{
		cat = (Math.ceil(simulationCache.getMx_cat()*10) / 10);
		
		montoCuota = simulationCache.getPayment();
		numCuota   = simulationCache.getNum_payments();
		totalPagar = simulationCache.getTotal_payment();
		
		flagSaveSimulationCache = false;
		
		simulationCache.setConsultation_last(new Date());
		
		Integer count = simulationCache.getConsultation_count() == null?1:(simulationCache.getConsultation_count()+1);
		
		simulationCache.setConsultation_count(count);
		
		simulationCacheService.update(simulationCache);
	}
}
