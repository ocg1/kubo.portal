package mx.com.kubo.repositories.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Contract_typeCat;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.InegiEconActivityCat;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Other_IncomeCat;
import mx.com.kubo.model.TenureCat;
import mx.com.kubo.repositories.EmploymentDao;

import org.apache.log4j.Logger;

public abstract class DAOEmploymentDMO 
implements EmploymentDao 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;
	
	protected TenureCat tenure;
	
	protected InegiEconActivityCat catag;
	
	protected List<NeighborhoodCat> lista_asentamientos;	
	protected List<TenureCat> lista_tenure;
	protected List<Employment> employment;
	protected List<BmxEconActivityCat> bmxEconAct;
	protected List<Other_IncomeCat> otherIncome;
	protected List<Contract_typeCat> contractType;
	
	protected  TypedQuery<TenureCat> typed;
	
	protected  String query;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }

	public Employment loadSelectedEmployment(EmploymentPK pk) 
	{
		return em.find(Employment.class,pk);
	}
	
	public List<Employment> loadEmploymentList() 
	{
		employment = em.createQuery("from Employment ", Employment.class).getResultList();
		
		 return employment;
	}
	
	public List<Employment> getListEmployByProspect(int prospectID,int companyID) 
	{		
		employment = em.createQuery("from Employment e where e.employmentPK.prospectus_id = ? and e.employmentPK.company_id = ?", Employment.class).setParameter(1, prospectID).setParameter(2, companyID).getResultList();
		
		return employment;
	}
	
	public List<BmxEconActivityCat> searchActivityList(String descripString) 
	{
		log.info("DESCRIPCION ES= "+ descripString);
		
		bmxEconAct =  em.createNamedQuery("findBmxActivity",BmxEconActivityCat.class)
				        .setParameter("description", "%"+descripString+"%")
				        .setMaxResults(5)
				        .getResultList();
		
		return bmxEconAct;
	}
	
	public List<NeighborhoodCat> getAsentamientoByCP(String cp) 
	{	
		
		Calendar cd_1 = Calendar.getInstance();
		cd_1.setTime(new Date());
		lista_asentamientos = em.createQuery("from NeighborhoodCat where zip_code = ?", NeighborhoodCat.class).setParameter(1,cp).getResultList();	
		
		Calendar cd_2 = Calendar.getInstance();
		cd_2.setTime(new Date());
		
		long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
		
		//System.out.println( "Ejecuta busqueda zip-code =  " + cp + " tiempo: " + dif_1 );
		
		return lista_asentamientos;
	}

	
	public List<Contract_typeCat> loadContracTypeList() 
	{
		contractType = em.createNamedQuery( "lisContractType", Contract_typeCat.class).getResultList();
		
		 return contractType;
	}

	public List<TenureCat> loadTenureList() 
	{
		lista_tenure = em.createNamedQuery("lisTenureCat",TenureCat.class).getResultList();
		
		return lista_tenure;
	}	
	
	public List<Other_IncomeCat> loadOtherIncomeList() 
	{
		otherIncome = em.createNamedQuery("lisOther_IncomeCat",Other_IncomeCat.class).getResultList();
		
		return otherIncome;
	}
}
