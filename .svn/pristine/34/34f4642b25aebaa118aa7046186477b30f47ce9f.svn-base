package mx.com.kubo.repositories.impl;

import java.util.Arrays;
import java.util.List;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.repositories.Change_controlDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Change_controlDAOImp extends DAOChangeControlAMO
implements Change_controlDAO
{    
	public Change_control getChangeControlByID(Change_controlPK changeControlPK) 
	{	
		return em.find(Change_control.class, changeControlPK);
	}
	
	@Transactional
	public boolean persist(Change_control change_control) 
	{
		int prospectus_id = change_control.getChange_controlPK().getProspectus_id();
		int company_id    = change_control.getChange_controlPK().getCompany_id();
		
		boolean change_control_OK = false;
		
		change_control_OK = persist_change_control(change_control, prospectus_id, company_id);
		
		return change_control_OK;		
	}	

	@Transactional
	public boolean addChangeControl(Change_control change_control, int prospectusID, int companyID) 
	{
		return persist_change_control(change_control, prospectusID, companyID);
	}

	public List<Change_control> getListByChangeByProspectus(int change_prospectus_id) 
	{
		List<Change_control> lisChangByP=em.createQuery("from Change_control c where c.change_prospectus_id = ?",Change_control.class)
				.setParameter(1, change_prospectus_id).getResultList();
		return lisChangByP;
	}

	public List<Change_control> getAllListChangeByProspectId(int prospectus_id,int company_id) 
	{
		List<Change_control> lstByProspect=em.createQuery("from Change_control c where c.change_controlPK.prospectus_id = ? and c.change_controlPK.company_id=?",Change_control.class)
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.getResultList();
		return lstByProspect;
	}

	public List<Change_control> getListByProspectByAfectedTablesFields(int prospectus_id, int company_id, String[] tables, String[] fields) 
	{			
		String query  = "from Change_control c "
			   + " where c.change_controlPK.prospectus_id = ? "
			   + " and   c.change_controlPK.company_id    = ? "
			   + " and   c.afected_table IN (:lstTables) "
			   + " and   c.field         IN (:lstFields) ";
		
		List<Change_control> lstByProspect = em.createQuery(query, Change_control.class)
						  .setParameter(1, prospectus_id)
						  .setParameter(2, company_id)
						  .setParameter("lstTables", Arrays.asList(tables))				
						  .setParameter("lstFields", Arrays.asList(fields))
						  .getResultList();
		
		return lstByProspect;
	}

	public List<Change_control> getListByProspectByAfectedByTable(int prospectus_id, int company_id, String table) 
	{
		List<Change_control> lstByProspect=em.createQuery("from Change_control c where c.change_controlPK.prospectus_id = ? and c.change_controlPK.company_id=? and c.afected_table=?",Change_control.class)
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.setParameter(3, table)
				.getResultList();
		return lstByProspect;
	}
	
	public Change_control getChangeControlByProspectByAfectedTablesFields(int prospectus_id, int company_id, String[] tables, String[] fields) 
	{			
		Integer changeIDMax = null;
		Change_control changeControl = null;
		
		String query = "select max(c.change_controlPK.change_id) "
			  + "from Change_control c "
			  + "where c.change_controlPK.prospectus_id = ? "
			  + "and   c.change_controlPK.company_id    = ? "
			  + "and   c.afected_table IN (:lstTables) "
			  + "and   c.field         IN (:lstFields) ";
		
		try
		{
			changeIDMax = (Integer) em.createQuery(query)
									  .setParameter(1, prospectus_id)
									  .setParameter(2, company_id)
									  .setParameter("lstTables", Arrays.asList(tables))				
									  .setParameter("lstFields", Arrays.asList(fields))
									  .getSingleResult();
			
			if(changeIDMax != null)
			{
				query = "from Change_control c "
					  + "where c.change_controlPK.prospectus_id = ? "
					  + "and   c.change_controlPK.company_id    = ? "
					  + "and   c.afected_table IN (:lstTables) "
					  + "and   c.field         IN (:lstFields) "
				      + "and   c.change_controlPK.change_id = (:changeIDMax)";
				
				changeControl = em.createQuery(query, Change_control.class)
							      .setParameter(1, prospectus_id)
								  .setParameter(2, company_id)
								  .setParameter("lstTables", Arrays.asList(tables))				
								  .setParameter("lstFields", Arrays.asList(fields))
								  .setParameter("changeIDMax", changeIDMax)
								  .getSingleResult();
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			changeControl = null;
		}
			
		return changeControl;		
	}
}
