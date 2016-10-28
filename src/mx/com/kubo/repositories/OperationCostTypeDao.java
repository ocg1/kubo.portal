package mx.com.kubo.repositories;

import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.Operating_cost_typePK;


public interface OperationCostTypeDao   {
	
    public Operating_cost_type getOperatingCostTypeById(Operating_cost_typePK pk);
    
}
