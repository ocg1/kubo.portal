package mx.com.kubo.services.impl;

import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.Operating_cost_typePK;
import mx.com.kubo.repositories.OperationCostTypeDao;
import mx.com.kubo.services.OperationCostTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationCostTypeServiceImp implements OperationCostTypeService {
	
	@Autowired
	private OperationCostTypeDao operationcosttyperepository;
	
	@Override
	public Operating_cost_type getOperatingCostTypeById(Operating_cost_typePK pk){
		
		return operationcosttyperepository.getOperatingCostTypeById(pk);
		
	}
	
}
