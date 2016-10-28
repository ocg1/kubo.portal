package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Function;
import mx.com.kubo.repositories.FunctionDao;
import mx.com.kubo.services.FunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImp implements FunctionService {
	
	@Autowired
	private FunctionDao functionreporsitory;
	
	@Override
	public List<Function> getLstFunction(){
		
		return functionreporsitory.getLstFunction();
		
	}
		
}