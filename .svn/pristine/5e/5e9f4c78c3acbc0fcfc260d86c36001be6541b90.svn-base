package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.Gender;
import mx.com.kubo.repositories.DAOGenderIMO;

@Repository("dao_gender")
public class DAOGenderIMP 
implements DAOGenderIMO 
{
	private EntityManager em = null;
	
	private List<Gender> lista_de_generos;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	public List<Gender> getLista_de_generos() 
	{
		lista_de_generos = em.createQuery("from Gender", Gender.class).getResultList();
		
		return lista_de_generos;
	}

}
