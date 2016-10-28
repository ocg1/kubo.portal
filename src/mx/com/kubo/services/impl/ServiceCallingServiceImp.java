package mx.com.kubo.services.impl;

import java.util.Date;

import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.ServiceCallingService;

import org.springframework.stereotype.Service;

@Service
public class ServiceCallingServiceImp extends ServiceCallingDMO
implements ServiceCallingService 
{
	public final void saveServiceCall(ServiceCalling newServiceCall)
	{
		dao.saveServiceCall(newServiceCall);
	}
	
	public final void registrar(int status, int prospectus_id, int company_id, String service_call_INFO)
	{
		registrar(status, prospectus_id, company_id, service_call_INFO, null);
	}
	
	public final void registrar(int status, int prospectus_id, int company_id, String service_call_INFO, String exception_INFO) 
	{
		srvCall = new ServiceCalling();
		
		srvCall.setAcces_datetime(new Date());
		srvCall.setStatus(status);		
		srvCall.setCompany_id(company_id);		
		srvCall.setProspectus_id(prospectus_id);
		srvCall.setInfo(service_call_INFO);
		srvCall.setException(exception_INFO);
		
		dao.saveServiceCall(srvCall);
	}

	@Override
	public ServiceCalling loadServiceCallByProspectus(Integer prospectus_id,
			Integer company_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceCalling loadSelectedServiceCall(int scoring_result_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateServiceCall(ServiceCalling newServiceCall) {
		// TODO Auto-generated method stub
		return false;
	}
	
}