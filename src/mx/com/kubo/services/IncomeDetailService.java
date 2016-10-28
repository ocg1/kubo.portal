package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeDetailPK;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingType;

public interface IncomeDetailService {

	public abstract IncomeDetail loadSelectedIncome(IncomeDetailPK pk);
	public abstract IncomeDetail loadMaxIncomeByProspectus(int prospectus_id,int company_id);
	public abstract boolean  saveIncomeDeatil(IncomeDetail incomedetail);
	public abstract boolean  updateMaxIncomeDeatil(IncomeDetail incomedetail);
	public abstract boolean  deleteMaxIncomeDeatil(IncomeDetail incomedetail);
	public abstract List<SellingDetail> getSellingDetailByDetailId(int prospectus_id,int company_id,Integer detail_id);
	public abstract List<Operating_cost> getOperatingCostByDetailId(int prospectus_id,int company_id,Integer detail_id);
	public abstract boolean saveSelligOrOperatingDetail(Object sellingOrOperatingCostDetail);
	public abstract boolean updateSelligOrOperatingDetail(Object sellingOrOperatingCostDetail);
	public abstract boolean deleteSellingByDetailId(int prospectus_id,int company_id,Integer detail_id);
	public abstract boolean deleteOperatingByDetailId(int prospectus_id,int company_id,Integer detail_id);
	public abstract List<SellingType> getListSellingType(Integer company_id);
	public abstract List<Operating_cost_type> getListOperation_costType(Integer company_id);
}
