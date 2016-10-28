package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeDetailPK;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingType;
import mx.com.kubo.repositories.IncomeDetailDao;
import mx.com.kubo.services.IncomeDetailService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeDetailServiceImp implements Serializable, IncomeDetailService {
	

	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IncomeDetailDao incomeDaoRepository;
	
	@Override
	public  IncomeDetail loadSelectedIncome(IncomeDetailPK pk){
		return incomeDaoRepository.loadSelectedIncome(pk);
	}
	@Override
	public  IncomeDetail loadMaxIncomeByProspectus(int prospectus_id,int company_id){
		return incomeDaoRepository.loadMaxIncomeByProspectus(prospectus_id,company_id);
	}
	@Override
	public  boolean  saveIncomeDeatil(IncomeDetail incomedetail){
		return incomeDaoRepository.saveIncomeDeatil(incomedetail);
	}
	@Override
	public  boolean  updateMaxIncomeDeatil(IncomeDetail incomedetail){
		return incomeDaoRepository.updateMaxIncomeDeatil(incomedetail);
	}
	@Override
	public  boolean  deleteMaxIncomeDeatil(IncomeDetail incomedetail){
		return incomeDaoRepository.deleteMaxIncomeDeatil(incomedetail);
	}
	@Override
	public List<SellingDetail> getSellingDetailByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		return incomeDaoRepository.getSellingDetailByDetailId(prospectus_id,company_id,detail_id);
	}
	@Override
	public List<Operating_cost> getOperatingCostByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		return incomeDaoRepository.getOperatingCostByDetailId(prospectus_id,company_id,detail_id);
	}	
	@Override
	public boolean deleteSellingByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		return incomeDaoRepository.deleteSellingByDetailId(prospectus_id,company_id,detail_id);
	}
	@Override
	public boolean deleteOperatingByDetailId(int prospectus_id,int company_id,Integer detail_id) {
		return incomeDaoRepository.deleteOperatingByDetailId(prospectus_id,company_id,detail_id);
	}
	@Override
	public boolean saveSelligOrOperatingDetail(Object sellingOrOperatingCostDetail) {
		return incomeDaoRepository.saveSelligOrOperatingDetail(sellingOrOperatingCostDetail);
	}
	@Override
	public boolean updateSelligOrOperatingDetail(Object sellingOrOperatingCostDetail) {
		return incomeDaoRepository.updateSelligOrOperatingDetail(sellingOrOperatingCostDetail);
	}	
	@Override
	public List<SellingType> getListSellingType(Integer company_id) {
		return incomeDaoRepository.getListSellingType(company_id);
	}
	@Override
	public List<Operating_cost_type> getListOperation_costType(Integer company_id) {
		return incomeDaoRepository.getListOperation_costType(company_id);
	}
	
}
