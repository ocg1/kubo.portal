package mx.com.kubo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.Gender;
import mx.com.kubo.repositories.DAOGenderIMO;
import mx.com.kubo.services.GenderServiceIMO;

@Service("service_gender")
public final class GenderServiceIMP 
implements GenderServiceIMO 
{
	@Autowired @Qualifier("dao_gender")
	private DAOGenderIMO dao;
	
	public final List<Gender> getLista_de_generos() 
	{
		return dao.getLista_de_generos();
	}

}
