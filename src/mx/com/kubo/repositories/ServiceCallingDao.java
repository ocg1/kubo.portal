package mx.com.kubo.repositories;

import mx.com.kubo.model.ServiceCalling;

public interface ServiceCallingDao {

		public abstract ServiceCalling loadSelectedServiceCall(int scoring_result_id);
		public abstract void saveServiceCall(ServiceCalling newServiceCall) ;
		public abstract ServiceCalling loadServiceCallByProspectus(Integer prospectus_id,Integer company_id);
		public abstract boolean updateServiceCall(ServiceCalling newServiceCall);
			
}
