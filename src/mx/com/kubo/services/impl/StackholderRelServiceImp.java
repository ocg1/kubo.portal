package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.repositories.StackholderRelDao;
import mx.com.kubo.services.StackholderRelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StackholderRelServiceImp 
implements StackholderRelService
{
	
	@Autowired
	private StackholderRelDao stackholderrelrepository;
	
	@Override
	public List<Stackholder_relationship> getStackholderRelLst(){
		
		return stackholderrelrepository.getStackholderRelLst();
		
	}
	
}
