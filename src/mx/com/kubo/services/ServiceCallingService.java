package mx.com.kubo.services;

import mx.com.kubo.model.ServiceCalling;

public interface ServiceCallingService 
{	
	ServiceCalling loadServiceCallByProspectus(Integer prospectus_id,Integer company_id);
	ServiceCalling loadSelectedServiceCall(int scoring_result_id);
	
	boolean updateServiceCall(ServiceCalling newServiceCall);
	
	void saveServiceCall(ServiceCalling newServiceCall);
	
	void registrar(int status, int prospectus_id, int company_id, String service_call_INFO);
	void registrar(int status, int prospectus_id, int company_id, String service_call_INFO, String exception_INFO);
}
