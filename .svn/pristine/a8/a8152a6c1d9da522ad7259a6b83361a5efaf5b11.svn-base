package mx.com.kubo.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.com.kubo.model.ClientCarteraKiva;
import mx.com.kubo.repositories.ClientCarteraKivaDao;

import org.springframework.stereotype.Repository;

@Repository
public class ClientCarteraKivaDaoImp implements ClientCarteraKivaDao{
	
	private EntityManager em;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClientCarteraKiva> getClientCarteraKivaListByIsKuboPropertyByIsNotReportLog(Integer isKuboProperty) {
		String query = "select pl.prospectus_id, pl.safi_credit_id, pl.proyect_loan_id, pl.proyect_id, pl.company_id, "
						+"replace(concat(per.first_name,' ',per.middle_name,' ',per.father_last_name,' ',per.mother_last_name),'  ',' ') AS full_name "
						+"from ln_proyect_loan pl "
						+"left outer join gn_natural_person per "
						+"on(per.prospectus_id = pl.prospectus_id and "
						+"per.company_id = pl.company_id) "
						+"left outer join gn_report_log rl "
						+"on(pl.proyect_loan_id = rl.proyect_loan_id " 
						+"and pl.proyect_id = rl.proyect_id "
						+"and pl.prospectus_id = rl.prospectus_id " 
						+"and pl.company_id = rl.company_id) "
						+"where pl.is_kubo_property = :isKuboProperty "
						+"and pl.safi_credit_id is not null and pl.status_id = 3 and " 
						+"(rl.prospectus_id is null and rl.proyect_id is null "
						+"and rl.proyect_loan_id is null and rl.company_id is null)";
		List<ClientCarteraKiva> list = new ArrayList<ClientCarteraKiva>();
		
		
		try {
			Query queryP = em.createNativeQuery(query, ClientCarteraKiva.class)
					.setParameter("isKuboProperty", isKuboProperty);
			
			list = queryP.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();	
			list = null;
		}
		return list;
	}
}
