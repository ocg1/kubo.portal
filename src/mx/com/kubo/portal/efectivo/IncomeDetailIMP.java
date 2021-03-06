package mx.com.kubo.portal.efectivo;

import java.util.ArrayList;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_costPK;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingDetailPK;

public class IncomeDetailIMP extends IncomeDetailAMO
implements IncomeDetailIMO
{
	public double init()
	{
		listBusinessDetails=new ArrayList<IncomeDetailsBean>();
		incomeDetaBean=null;
		
		int index=0;
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Sus ventas son");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(false);
		incomeDetaBean.setAmmount_modified(incomedetail.getSales_type()==null?"---":incomedetail.getSales_type().equals("M")?"Mixto":incomedetail.getSales_type().equals("C")?"Contado":"Abonos");
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Sus compras son");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(false);
		incomeDetaBean.setAmmount_modified(incomedetail.getProvider_type()==null?"---":incomedetail.getProvider_type().equals("M")?"Mixto":incomedetail.getProvider_type().equals("C")?"Contado":"Abonos");
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setTypeComponent("S");
		incomeDetaBean.setName("Compras");	
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Veces que se surte al mes");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("I");
		
		incomeDetaBean.setAmmount( Double.parseDouble( (incomedetail.getTimes_refill()==null?0:incomedetail.getTimes_refill()) +"" ) );
		
		times_refill_init = ( incomedetail.getTimes_refill_modified()==null?(incomedetail.getTimes_refill()==null?0:incomedetail.getTimes_refill()):incomedetail.getTimes_refill_modified());
		
		incomeDetaBean.setAmmount_modified	( times_refill_init +""  );
		
		//incomeDetaBean.setAmmount_modified(incomedetail.getTimes_refill()==null?"0 veces":incomedetail.getTimes_refill()>1?incomedetail.getTimes_refill()+" veces":incomedetail.getTimes_refill()+" vez");
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Total de compras mensuales");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("I");
		incomeDetaBean.setPutBoldStyle(true);
		incomeDetaBean.setAmmount			(incomedetail.getProvider_total()==null?0.0:incomedetail.getProvider_total());
		
		provider_total_init = (incomedetail.getProvider_total_modified()==null?(incomedetail.getProvider_total()==null?0D:incomedetail.getProvider_total()):incomedetail.getProvider_total_modified());
		
		incomeDetaBean.setAmmount_modified	( num.format( provider_total_init ) );
		
		listBusinessDetails.add(incomeDetaBean);
		index++;
						
		existlistSellingDetailHistory();
		
		if(lstSellingDetail == null)
		{
			lstSellingDetail = service_income_detail.getSellingDetailByDetailId(prospectus_id,  company_id,incomedetail.getIncomDetailPk().getIncome_detail_id());
		}
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setTypeComponent("S");
		incomeDetaBean.setName("Ventas");	
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		double totalCtrlTable=0.0;
		double profitBeforeCostCtrlTable=0;
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Sus ventas son");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setAmmount_modified(incomedetail.getSales_freq().equals("1")?"Mensual":incomedetail.getSales_freq().equals("2")?"Quincenal":incomedetail.getSales_freq().equals("4")?"Semanal":"Diario");
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(false);
		listBusinessDetails.add(incomeDetaBean);
		
		index++;
		
		if(lstSellingDetail != null && lstSellingDetail.size() > 0)
		{					
			for (SellingDetail iterSelling : lstSellingDetail) 
			{
				incomeDetaBean = new IncomeDetailsBean();
				incomeDetaBean.setSave(true);
				incomeDetaBean.setIndex(index);
				incomeDetaBean.setName(iterSelling.getSellingType().getName());
				incomeDetaBean.setHs_name(iterSelling.getSellingType().getHs_name());
				incomeDetaBean.setSellingOrOperCostDetail(iterSelling);
				incomeDetaBean.setAmmount(iterSelling.getAmmount());
				incomeDetaBean.setTypeComponent("I");
				incomeDetaBean.setPutBoldStyle(false);
				incomeDetaBean.setAmmount_modified(iterSelling.getAmmount_modified()!=null?num.format(iterSelling.getAmmount_modified()):num.format(iterSelling.getAmmount()));
				
				totalCtrlTable += iterSelling.getAmmount_modified()==null?iterSelling.getAmmount():iterSelling.getAmmount_modified();
				
				listBusinessDetails.add(incomeDetaBean);
				
				index++;
			}
			
		}else{
			in = new SellingDetail();
			sellHPk = new SellingDetailPK(
					
			incomedetail.getIncomDetailPk().getIncome_id(), 
			incomedetail.getIncomDetailPk().getProspectus_id(), 
			incomedetail.getIncomDetailPk().getCompany_id(), 
			incomedetail.getIncomDetailPk().getIncome_detail_id(),1);
			
			in.setSellingDetailPK(sellHPk);
			in.setAmmount(0.0);
			incomeDetaBean=new IncomeDetailsBean();
			incomeDetaBean.setSave(false);
			incomeDetaBean.setIndex(index);
			incomeDetaBean.setName("Ventas mensuales*");
			incomeDetaBean.setSellingOrOperCostDetail(in);
			incomeDetaBean.setAmmount(0.0);
			incomeDetaBean.setTypeComponent("I");
			incomeDetaBean.setPutBoldStyle(false);
			incomeDetaBean.setAmmount_modified("0");
			listBusinessDetails.add(incomeDetaBean);
			index++;
			
		}
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Total de ventas mensuales");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		if(incomedetail.getSales_freq().equals("30")){
			incomeDetaBean.setAmmount(incomedetail.getSales_total()==null?0.0:incomedetail.getSales_total()*4);
			incomeDetaBean.setAmmount_modified(num.format(totalCtrlTable*4));
		}else{
			incomeDetaBean.setAmmount(incomedetail.getSales_total()==null?0.0:incomedetail.getSales_total());
			incomeDetaBean.setAmmount_modified(num.format(totalCtrlTable));
		}
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setShowProcess(true);
		incomeDetaBean.setPutBoldStyle(true);
		listBusinessDetails.add(incomeDetaBean);		
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Utilidad bruta mensual");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(true);
		incomeDetaBean.setAmmount(incomedetail.getProfit_before_costs()==null?0.0:incomedetail.getProfit_before_costs());
		
		Double d1 = (incomedetail.getProvider_total_modified() == null ) ? ( (incomedetail.getProvider_total()==null?0.0:incomedetail.getProvider_total()) ) : incomedetail.getProvider_total_modified() ;
		
		if(incomedetail.getSales_freq().equals("30") && totalCtrlTable>0)
		{		
			incomeDetaBean.setAmmount_modified(num.format((totalCtrlTable*4)-( d1 )));
			profitBeforeCostCtrlTable=(totalCtrlTable*4)-( d1 );
			
		} else {
			
			incomeDetaBean.setAmmount_modified(num.format((totalCtrlTable)-( d1 )));
			profitBeforeCostCtrlTable=(totalCtrlTable)-( d1 );			
		}					
		
		listBusinessDetails.add(incomeDetaBean);
		
		index++;
		
		lstOperCostType = service_income_detail.getListOperation_costType(1);
		
		existlistOperationCostHistory();
		
		if(listOperationCost == null )
		{
			listOperationCost = service_income_detail.getOperatingCostByDetailId(prospectus_id,  company_id,incomedetail.getIncomDetailPk().getIncome_detail_id());
		}
		
		incomeDetaBean = new IncomeDetailsBean();
		incomeDetaBean.setIndex(1);
		incomeDetaBean.setTypeComponent("S");
		incomeDetaBean.setName("Gastos operativos");			
		listBusinessDetails.add(incomeDetaBean);
		
		totalCtrlTable = 0.0;
		
		for (Operating_cost_type operating_costType : lstOperCostType) 
		{
			incomeDetaBean=new IncomeDetailsBean();
			incomeDetaBean.setSave(false);
			
			for (Operating_cost operating_cost : listOperationCost) 
			{
				if(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id()==operating_cost.getOperatingCostPK().getOperating_cost_type_id())
				{
					incomeDetaBean.setSave(true);
					incomeDetaBean.setIndex(index);
					incomeDetaBean.setName(operating_cost.getOperaCostType().getName());
					incomeDetaBean.setHs_name(operating_cost.getOperaCostType().getHs_name());
					incomeDetaBean.setSellingOrOperCostDetail(operating_cost);
					incomeDetaBean.setAmmount(operating_cost.getAmmount());
					incomeDetaBean.setTypeComponent("I");
					incomeDetaBean.setPutBoldStyle(false);
					incomeDetaBean.setAmmount_modified(operating_cost.getAmmount_modified()!=null?num.format(operating_cost.getAmmount_modified()):num.format(operating_cost.getAmmount()));
					
					totalCtrlTable+=operating_cost.getAmmount_modified()==null?operating_cost.getAmmount():operating_cost.getAmmount_modified();
					
					listBusinessDetails.add(incomeDetaBean);					
					break;
				}
			}
			
			if(!incomeDetaBean.isSave())
			{
				op = new Operating_cost();
				opCostHPk = new Operating_costPK(
				incomedetail.getIncomDetailPk().getIncome_id(), 
				incomedetail.getIncomDetailPk().getProspectus_id(), 
				incomedetail.getIncomDetailPk().getCompany_id(), 
				incomedetail.getIncomDetailPk().getIncome_detail_id(), operating_costType.getOperatingCostTypePk().getOperating_cost_type_id());
				
				op.setOperaCostType(operating_costType);
				op.setOperatingCostPK(opCostHPk);
				incomeDetaBean.setIndex(index);
				incomeDetaBean.setName(operating_costType.getName());
				incomeDetaBean.setHs_name(operating_costType.getHs_name());
				incomeDetaBean.setSellingOrOperCostDetail(op);
				incomeDetaBean.setAmmount(0.0);
				incomeDetaBean.setTypeComponent("I");
				incomeDetaBean.setPutBoldStyle(false);
				incomeDetaBean.setAmmount_modified("0");
				listBusinessDetails.add(incomeDetaBean);
			}
			index++;
			
		}
		
		Double totalOperativeCostCtrlTable = totalCtrlTable;
		incomeDetaBean = new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Total de gastos operativos mensuales");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setAmmount(incomedetail.getOperative_costs()==null?0.0:incomedetail.getOperative_costs());
		incomeDetaBean.setAmmount_modified(num.format( totalCtrlTable ));
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setShowProcess(true);
		incomeDetaBean.setPutBoldStyle(true);
		
		listBusinessDetails.add(incomeDetaBean);
		
		index++;
		
		incomeDetaBean = new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Utilidad neta");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setAmmount(incomedetail.getProfil_after_costs()==null?0.0:incomedetail.getProfil_after_costs());						
		incomeDetaBean.setAmmount_modified(num.format(profitBeforeCostCtrlTable-totalOperativeCostCtrlTable));		
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(true);
		
		listBusinessDetails.add(incomeDetaBean);		
						
		if(incomedetail.getProfil_after_costs() != null && incomeBussinness != null &&  (incomedetail.getProfil_after_costs() != incomeBussinness.getAmmount() ))
		{		
			Income incTmp = service_income.getIncomebyID(incomeBussinness.getIncomePk());
			
			incTmp.setAmmount(incomedetail.getProfil_after_costs());
			
			service_income.updateIncome( incTmp );
			
			for( IncomeBean tmp : listIncomeBean )
			{
				if( tmp.getIncome_type_id() == 2 )
				{
					tmp.setAmmount(incomedetail.getProfil_after_costs());					
				}
			}
			
		}
				
		return profitBeforeCostCtrlTable-totalCtrlTable;
	}
	
	
}
