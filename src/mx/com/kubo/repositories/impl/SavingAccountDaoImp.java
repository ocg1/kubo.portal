package mx.com.kubo.repositories.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.RelacionMenorTutorCollector;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.repositories.SavingAccountDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
public class SavingAccountDaoImp 
implements SavingAccountDao 
{
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public SavingAccount getSavingAccountByID(SavingAccountPK savingAccountPk) {
		return em.find(SavingAccount.class, savingAccountPk);
	}
	
	@Override
	public SavingAccount getSavingAccountByAccount(String savingAccountStr){
		
		String query = "from SavingAccount a"
				  + " where a.safi_account_id = ? ";
		
		SavingAccount account = null;
		
		try
		{
			
			account = em.createQuery(query, SavingAccount.class)
					.setParameter(1, savingAccountStr)
					.getSingleResult();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return account;
		
	}
	
	@Transactional
	@Override
	public boolean addSavingAccount(SavingAccount newSavingAccount,int prospectusID, int companyID) {		
		Integer idAccount=0;
		idAccount=(Integer) em.createNamedQuery("queryAddAccount")
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getSingleResult();
		if(idAccount==null){			
			idAccount=1;
		}
		else{
			idAccount++;
			}
		log.info("Maximo de idAccount= "+idAccount);
		newSavingAccount.getSaving_accountPk().setSaving_account_id(idAccount);
		try {
			em.persist(newSavingAccount);			
			return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	@Transactional
	@Override
	public boolean updateSavingAccount(SavingAccount savingAccount) {
		try{
			em.merge(savingAccount);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SavingAccount> getListAccountByProspect(int prospectID, int companyID) 
	{
		List<SavingAccount> account = null;
		
		String query = "from SavingAccount a"
			  + " where a.saving_accountPk.prospectus_id = ? "
			  + " and a.saving_accountPk.company_id      = ? ";
		
		try
		{
		account = em.createQuery(query, SavingAccount.class)
					.setParameter(1, prospectID)
					.setParameter(2, companyID)
					.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	
	@Override
	public  SavingAccount getSavingAccountByProspectus(int prospectusID,int companyID){
		try{
		return (SavingAccount)em.createQuery(
			    "from SavingAccount a where a.saving_accountPk.prospectus_id = ? and a.saving_accountPk.company_id = ?", SavingAccount.class).setParameter(1, prospectusID).setParameter(2, companyID)
			    .getSingleResult();
		}catch(Exception e){
			System.out.println("No se encntro saving account para el prospecto: "+prospectusID);
			return null;
		}
	}
	
	@Override
	@Transactional
	public boolean verifyAccount(SavingAccount savingAccount){
		
		boolean flag=false;
		String query = "select count(1) from microfin.CUENTASAHO where CuentaAhoID = '"+savingAccount.getSafi_account_id()+"' and Estatus = 'A';";
		try{
			
			BigInteger i = (BigInteger)em.createNativeQuery(query).getSingleResult();
			
			if(i!=null){
				
				if(Integer.parseInt(i+"") > 0){
				
					SavingAccount accountTemp = em.find(SavingAccount.class, savingAccount.getSaving_accountPk());
					accountTemp.setStatus(1);
					em.merge(accountTemp);
					flag=true;
				
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
		}
		
		
		return flag;
		
	}
	
	@Override
	public List<SavingAccount> getListAccountActiveByProspect(int prospectID,
			int companyID) {
		// TODO Auto-generated method stub
		List<SavingAccount> account = null;
		try{
			account = em.createQuery("from SavingAccount a where a.saving_accountPk.prospectus_id = ? "
					+ "and a.saving_accountPk.company_id = ? and a.status = 1", SavingAccount.class)
					.setParameter(1, prospectID)
					.setParameter(2, companyID)
					.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return account;
	}
	
	@Override
	@Transactional
	
	public boolean executeSP_Relaciona_Menor( String safi_client_menor, String safi_client_tutor ){
		try{
			
			RelacionMenorTutorCollector res = em.createNamedQuery("relaciona_menor_tutor_safi",RelacionMenorTutorCollector.class)
										.setParameter("safi_client_id_menor",safi_client_menor)
										.setParameter("safi_client_id_tutor",safi_client_tutor)
										.setParameter("fecha",new Date())
										.getSingleResult();
			
			return true;
		
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
