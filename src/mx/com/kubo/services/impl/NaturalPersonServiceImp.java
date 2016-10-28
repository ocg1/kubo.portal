package mx.com.kubo.services.impl;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.NaturalPersonService;

import org.springframework.stereotype.Service;

@Service
public class NaturalPersonServiceImp extends ServiceNaturalPersonDMO
implements NaturalPersonService 
{	
	public void add(NaturalPerson newNaturalPerson) 
	{
		log.info("add.CompanyServiceImp");
		
		dao_natural_person.saveNaturalPerson(newNaturalPerson);		
	}
		
	public NaturalPerson update(NaturalPerson newNaturalPerson) 
	{
		log.info("update.CompanyServiceImp");
		
		return dao_natural_person.update(newNaturalPerson);		
	}
}
