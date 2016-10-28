package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeDetailPK;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingType;
import mx.com.kubo.repositories.IncomeDetailDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IncomeDetailDaoImp implements Serializable, IncomeDetailDao {

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
			
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public IncomeDetail loadSelectedIncome(IncomeDetailPK pk){
		try{
			return em.find(IncomeDetail.class,pk);
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public  IncomeDetail loadMaxIncomeByProspectus(int prospectus_id, int company_id){
		try{
			String query="from IncomeDetail income where income.incomDetailPk.prospectus_id = ? and income.incomDetailPk.company_id = ?  ";
			return (IncomeDetail) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2, company_id).getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	@Transactional
	@Override
	public  boolean  saveIncomeDeatil(IncomeDetail incomedetail){
		
		boolean flag;
		
		String query="select MAX(income.incomDetailPk.income_detail_id) from IncomeDetail income where income.incomDetailPk.prospectus_id = ? ";
		Integer id=0;
		id=(Integer) em.createQuery(query).setParameter(1, incomedetail.getIncomDetailPk().getProspectus_id()).getSingleResult();
		id= (id==null)?0:id;
		
		incomedetail.getIncomDetailPk().setIncome_detail_id(id);
		try{
			em.persist(incomedetail);
			flag = true;
		}catch(Exception ex){
			flag = false;
			ex.printStackTrace();
		}
		
		return flag;
	}
	
	@Transactional
	@Override
	public boolean  updateMaxIncomeDeatil(IncomeDetail incomedetail){
		boolean flag;
		try{
			em.merge(incomedetail);
			flag = true;
		}catch(Exception ex){
			flag = false;
			ex.printStackTrace();
		}
		
		return flag;
	}
	
	@Transactional
	@Override
	public boolean  deleteMaxIncomeDeatil(IncomeDetail incomedetail){
		boolean flag;
		try{
			em.remove(incomedetail);
			flag = true;
		}catch(Exception ex){
			flag = false;
			ex.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public List<SellingDetail> getSellingDetailByDetailId(int prospectus_id,int company_id,Integer detail_id) {
			String query="from SellingDetail sellDet where sellDet.sellingDetailPK.prospectus_id=? and sellDet.sellingDetailPK.company_id=? and sellDet.sellingDetailPK.income_detail_id = ? ";
			return  em.createQuery(query,SellingDetail.class)
					.setParameter(1, prospectus_id)
					.setParameter(2, company_id)
					.setParameter(3, detail_id)
					.getResultList();		
	}

	@Override
	public List<Operating_cost> getOperatingCostByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		String query="from Operating_cost opCost where opCost.operatingCostPK.prospectus_id=? and opCost.operatingCostPK.company_id=? and opCost.operatingCostPK.income_detail_id = ? ";
		return  em.createQuery(query,Operating_cost.class)
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.setParameter(3, detail_id)
				.getResultList();	
	}

	@Transactional
	@Override
	public boolean saveSelligOrOperatingDetail(Object sellingOrOperatingCostDetail) {
		boolean flag=false;
		try{
			if(sellingOrOperatingCostDetail instanceof SellingDetail){
				em.persist((SellingDetail)sellingOrOperatingCostDetail);				
				flag = true;
			}else if(sellingOrOperatingCostDetail instanceof Operating_cost){
				em.persist((Operating_cost)sellingOrOperatingCostDetail);					
				flag = true;
			}
		}catch(Exception ex){
			flag = false;
			ex.printStackTrace();
		}
		
		return flag;
	}

	@Transactional
	@Override
	public boolean updateSelligOrOperatingDetail(Object sellingOrOperatingCostDetail) {
		boolean flag=false;
		try{
			if(sellingOrOperatingCostDetail instanceof SellingDetail){
				em.merge((SellingDetail)sellingOrOperatingCostDetail);				
				flag = true;
			}else if(sellingOrOperatingCostDetail instanceof Operating_cost){
				em.merge((Operating_cost)sellingOrOperatingCostDetail);					
				flag = true;
			}else if(sellingOrOperatingCostDetail instanceof IncomeDetail){
				em.merge((IncomeDetail)sellingOrOperatingCostDetail);					
				flag = true;
			}
		}catch(Exception ex){
			flag = false;
			ex.printStackTrace();
		}
		
		return flag;
	}

	@Transactional
	@Override
	public boolean deleteSellingByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		int result=em.createNativeQuery("delete from gn_selling_detail where prospectus_id=? and company_id=? and income_detail_id=?;")
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.setParameter(3, detail_id).executeUpdate();
		return result>1?true:false;
	}

	@Transactional
	@Override
	public boolean deleteOperatingByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		int result=em.createNativeQuery("delete from gn_operating_cost where prospectus_id=? and company_id=? and income_detail_id=?;")
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.setParameter(3, detail_id).executeUpdate();
		return result>1?true:false;
	}

	@Override
	public List<SellingType> getListSellingType(Integer company_id) {
		return em.createQuery("from SellingType",SellingType.class).getResultList();
	}

	@Override
	public List<Operating_cost_type> getListOperation_costType(Integer company_id) {
		return em.createQuery("from Operating_cost_type",Operating_cost_type.class).getResultList();
	}
	
	
}
