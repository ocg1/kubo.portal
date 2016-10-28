package mx.com.kubo.portal.efectivo;

import java.util.ArrayList;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_costPK;
import mx.com.kubo.model.Operating_cost_typePK;
import mx.com.kubo.model.OperationCostHistory;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingDetailHistory;
import mx.com.kubo.model.SellingDetailPK;
import mx.com.kubo.model.SellingTypePK;

public abstract class IncomeDetailAMO extends IncomeDetailDMO
{
	protected void existlistSellingDetailHistory()
	{		
		if(proyect_loan != null)
		{			
			lstSellingDetail = null;
		
			proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
			lstSellHis = service_selling_detail_history.getSellingDetailHistoryByProyectLoan(proyect_loan_id);
		
			if( lstSellHis != null &&  lstSellHis.size()>0 )
			{		
				lstSellingDetail = new ArrayList<SellingDetail>();
			
				for( SellingDetailHistory inH : lstSellHis )
				{			
					in = new SellingDetail();			
					in.setAmmount(inH.getAmmount());
					in.setAmmount_modified(inH.getAmmount_modified());
					in.setDatetime_modified(inH.getDatetime_modified());
					
					sellHPk = new SellingDetailPK();				
					sellHPk.setCompany_id(inH.getPk().getCompany_id());
					sellHPk.setIncome_id(inH.getPk().getIncome_id());
					sellHPk.setProspectus_id(inH.getPk().getProspectus_id());				
					sellHPk.setIncome_detail_id(inH.getPk().getIncome_detail_id());
					sellHPk.setSelling_type_id(inH.getPk().getSelling_type_id());
					
					in.setSellingDetailPK(sellHPk);
					
					id = new SellingTypePK();				
					id.setCompany_id(inH.getPk().getCompany_id());
					id.setSelling_type_id(inH.getPk().getSelling_type_id());
				
					type =  service_selling_type.getSellingTypeByPK(id);
					
					in.setSellingType(type);
					
					in.setProspectus_id_modified(inH.getProspectus_id_modified());
					
					lstSellingDetail.add(in);			
				}							
			} 
		}
	}
		
	protected void existlistOperationCostHistory()
	{		
		if(proyect_loan != null)
		{					
			lstInHis = service_opertaion_cost_history.getOperationCostHistoryByProyectLoan(proyect_loan_id);
			
			listOperationCost = null;
			
			if(lstInHis != null && lstInHis.size() > 0)
			{			
				listOperationCost = new ArrayList<Operating_cost>();
				
				for(OperationCostHistory inH : lstInHis)
				{				
					op = new Operating_cost();
					
					op.setAmmount(inH.getAmmount());
					op.setAmmount_modified(inH.getAmmount_modified());
					op.setDatetime_modified(inH.getDatetime_modified());
					
					opCostHPk = new Operating_costPK();				
					opCostHPk.setCompany_id(inH.getPk().getCompany_id());
					opCostHPk.setIncome_id(inH.getPk().getIncome_id());
					opCostHPk.setProspectus_id(inH.getPk().getProspectus_id());
					opCostHPk.setOperating_cost_type_id(inH.getPk().getOperating_cost_type_id());
					opCostHPk.setIncome_detail_id(inH.getPk().getIncome_detail_id());
					
					op.setOperatingCostPK(opCostHPk);
					
					op_PK = new Operating_cost_typePK();				
					op_PK.setCompany_id(inH.getPk().getCompany_id());
					op_PK.setOperating_cost_type_id(inH.getPk().getOperating_cost_type_id());
					
					op_type = service_opertaion_cost_type.getOperatingCostTypeById(op_PK);
					
					op.setOperaCostType( op_type );
					op.setProspectus_id_modified(inH.getProspectus_id_modified());
					
					listOperationCost.add(op);				
				}									
			} 
		}
	}
}
