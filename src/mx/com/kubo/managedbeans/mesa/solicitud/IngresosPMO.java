package mx.com.kubo.managedbeans.mesa.solicitud;

import java.util.Date;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.mesa.solicitud.perfil.IndicePagoDeudasIMP;

public abstract class IngresosPMO extends EgresosPMO
{
	public void changeIncome(IncomeBean income)
	{
		income.getIncome().setAmmount_modified(Double.parseDouble(income.getAmmount_modified().replace(",", "")));
		income.getIncome().setProspectus_id_modified(sesion.getProspectus_id());
		income.getIncome().setDatetime_modified(new Date());
		
		if(income.getIncome().getIncomePk().getIncome_id()!=0)
		{
			ingresosService.updateIncome(income.getIncome());
			
		} else {
			
			ingresosService.addIncome(income.getIncome(), income.getIncome().getIncomePk().getProspectus_id(), income.getIncome().getIncomePk().getCompany_id());
		}
				
		calculaSueldoNeto();
		calculaTotalIncome();
				
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();

		indice = new IndicePagoDeudasIMP();
		indice.init();
	}
	
	public void saveChangeIncomDetails(IncomeDetailsBean incomeDetailBean)
	{
		Double newAmmount= 0D;
		
		if(incomeDetailBean.getAmmount_modified()!= null &&incomeDetailBean.getAmmount_modified().trim().replace(",", "").length()>0 )
			newAmmount=Double.parseDouble(incomeDetailBean.getAmmount_modified().replace(",", ""));
		
		if(incomeDetailBean.getSellingOrOperCostDetail() instanceof SellingDetail)
		{
			//System.out.println( "INGRESOSPMO instanceof SellingDetail " );
			
			SellingDetail updateSellDetail=(SellingDetail) incomeDetailBean.getSellingOrOperCostDetail();
			updateSellDetail.setAmmount_modified(newAmmount);
			
			if(incomeDetailBean.isSave()?incomeDetailService.updateSelligOrOperatingDetail(updateSellDetail):incomeDetailService.saveSelligOrOperatingDetail(updateSellDetail))
			{				 
				Income updateIncome=ingresosService.getIncomebyID(new IncomePK(incomedetail.getIncomDetailPk().getIncome_id(), incomedetail.getIncomDetailPk().getProspectus_id(), incomedetail.getIncomDetailPk().getCompany_id()));
				updateIncome.setAmmount_modified(loadIncomeBussinessDetails());
				
				updateIncome.setDatetime_modified(new Date());
				updateIncome.setProspectus_id_modified( sesion.getProspectus_id() );
				
				if(ingresosService.updateIncome(updateIncome)){
					
					calculaTotalIncome();
					calculaTotalExpenses();
				}
			}				
				
		} else if(incomeDetailBean.getSellingOrOperCostDetail() instanceof Operating_cost){
			
				//System.out.println( "INGRESOSPMO instanceof Operating_cost " );
			
				Operating_cost updateOperaCostDetail=(Operating_cost) incomeDetailBean.getSellingOrOperCostDetail();
				updateOperaCostDetail.setAmmount_modified(newAmmount);
				
				if(incomeDetailBean.isSave()?incomeDetailService.updateSelligOrOperatingDetail(updateOperaCostDetail):incomeDetailService.saveSelligOrOperatingDetail(updateOperaCostDetail))
				{
					Income updateIncome=ingresosService.getIncomebyID(new IncomePK(incomedetail.getIncomDetailPk().getIncome_id(), incomedetail.getIncomDetailPk().getProspectus_id(), incomedetail.getIncomDetailPk().getCompany_id()));
					updateIncome.setAmmount_modified(loadIncomeBussinessDetails());
					updateIncome.setDatetime_modified(new Date());
					updateIncome.setProspectus_id_modified( sesion.getProspectus_id() );
					if(ingresosService.updateIncome(updateIncome))
					{	
						loadIncomeBussinessDetails();
						calculaTotalIncome();
						calculaTotalExpenses();
						
					}
				}					
				
		}else if(incomeDetailBean.getSellingOrOperCostDetail() instanceof IncomeDetail){
			
			//System.out.println( "INGRESOSPMO instanceof IncomeDetail " );
			
			IncomeDetail i  = (IncomeDetail)incomeDetailBean.getSellingOrOperCostDetail();
			
			if( incomeDetailBean.getName().equals("Veces que se surte al mes")  ){
				
				
				
				saveChangeData("gn_income_detail", "times_refill_modified", getTimes_refill_init()+"" , ""+i.getTimes_refill_modified() , "Cambio de Mesa de Control en entrevista" );
				
				i.setTimes_refill_modified ( Integer.parseInt ( incomeDetailBean.getAmmount_modified()  ) );
				
				setTimes_refill_init( i.getTimes_refill_modified() );
				i.setTimes_refill_datetime_modified(new Date());
				i.setTimes_refill_prospectus_id_modified( sesion.getProspectus_id() ); 
				
			}else if ( incomeDetailBean.getName().equals( "Total de compras mensuales" ) ){
				
				String ammountMod =  (incomeDetailBean.getAmmount_modified() != null) ? incomeDetailBean.getAmmount_modified().replaceAll(",", "" ) : "0"  ;
				
				i.setProvider_total_modified(Double.parseDouble( ammountMod  ) );
				
				saveChangeData("gn_income_detail", "provider_total_modified", getProvider_total_init() + "" , ""+ i.getProvider_total_modified() , "Cambio de Mesa de Control en entrevista" );
				
				setProvider_total_init( i.getProvider_total_modified() );
				i.setProvider_total_datetime_modified( new Date());
				i.setProvider_total_prospectus_id_modified( sesion.getProspectus_id() );
			}
			
			// --IncomeDetail updateSellDetail=(IncomeDetail) incomeDetailBean.getSellingOrOperCostDetail();
			// --updateSellDetail.setAmmount_modified(newAmmount);
			
			Income updateIncome=ingresosService.getIncomebyID(new IncomePK(incomedetail.getIncomDetailPk().getIncome_id(), incomedetail.getIncomDetailPk().getProspectus_id(), incomedetail.getIncomDetailPk().getCompany_id()));
			updateIncome.setAmmount_modified(loadIncomeBussinessDetails());
			updateIncome.setDatetime_modified(new Date());
			updateIncome.setProspectus_id_modified( sesion.getProspectus_id() );
			if(ingresosService.updateIncome(updateIncome)){
			
			loadIncomeBussinessDetails();
			calculaTotalIncome();
			calculaTotalExpenses();
			
			}
			
			incomeDetailService.updateSelligOrOperatingDetail( i );
			
		}
		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		        
		indice = new IndicePagoDeudasIMP();
		indice.init();
	}
}
