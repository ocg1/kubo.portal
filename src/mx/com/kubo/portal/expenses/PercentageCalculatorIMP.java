package mx.com.kubo.portal.expenses;

import java.util.ArrayList;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ListPorc;

public final class PercentageCalculatorIMP extends PercentageCalculatorDMO
implements PercentageCalculatorIMO 
{
	public final void init()
	{
		boolean flagExpenses = false;
		boolean flagTotalExpenses = false;
		Double totalExpenseTemp = 0D;
		
/*		
		if( !existlistExpenses() )
		{		
			listExpenses = egresosService.getListExpensesByProspect(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());		
		}
*/		
		
		listPorcClientBean = new ArrayList<ListPorc>();
		
		ListPorc listporc;
		
		for(ExpensesType iterType : listExpensesType)
		{		
			boolean isEquals = false;
			
			for(Expenses registro4: listExpenses)
			{
				
				if( registro4.getExpense_type_id() != null && registro4.getExpense_type_id() != 14 )
				{ 				
					if(registro4.getExpense_type_id().equals(iterType.getPk().getExpenses_type_id()))
					{
						listporc=new ListPorc();
						listporc.setName(registro4.getExpensesType().getName());
						
							String tmp = num.format(registro4.getAmmount());
							if(tmp.equals("0"))
								tmp = "0";
							if(user.equals("cliente"))
								listporc.setAmmount(registro4.getAmmount());
							if(user.equals("mesacontrol")){
								if(registro4.getAmmount_modified()!=null)
									listporc.setAmmount(registro4.getAmmount_modified());
								else{
									listporc.setAmmount(registro4.getAmmount());
								}
							}
							
							if(listporc.getAmmount()!=null){
								Double d =0.0;
								if(user.equals("cliente"))
									d= (listporc.getAmmount()*100)/totalIncome;
								if(user.equals("mesacontrol"))
									d= (listporc.getAmmount()*100)/totalIncomeControl;
								d = ((double)Math.round((d)*100)/100);
								listporc.setPorcent(d);
							}
						
						isEquals=true;
						listPorcClientBean.add(listporc);
						this.totalExpenses += registro4.getAmmount();
						flagExpenses = true;
					}
					
				}else{
					
					flagTotalExpenses = true;
					totalExpenseTemp = registro4.getAmmount();
					
				}
			}
			
			if(!isEquals){
				listporc=new ListPorc();
				listporc.setName(iterType.getName());
				listporc.setAmmount(0D);
				listporc.setPorcent(0D);
				listPorcClientBean.add(listporc);
			}
			
		}
		
		
		if(!flagExpenses &&  flagTotalExpenses ){
			
			this.totalExpenses = totalExpenseTemp;
			
			System.out.println("--------- ");
			System.out.println("----1.1----   "+this.totalExpenses + "  -  " + totalExpenseTemp);
			System.out.println("--------- ");
			System.out.println("--------- ");
			
			
		}else if(!flagTotalExpenses && flagExpenses  ) {
			
			ExpensesPK expensesPK  = new ExpensesPK();
			expensesPK.setCompany_id(company_id);
			expensesPK.setExpense_id(14);
			expensesPK.setProspectus_id(prospectus_id);
			
			Expenses totalExpensesObj = new Expenses(); 
			totalExpensesObj.setExpensesPk(expensesPK);
			totalExpensesObj.setAmmount(this.totalExpenses);
			totalExpensesObj.setExpense_type_id(14);
			
			egresosService.addExpenses(totalExpensesObj, prospectus_id, company_id);
			
			System.out.println("--------- ");
			System.out.println("----2.1---- ");
			System.out.println("--------- ");
			System.out.println("--------- ");
			
		}else if( flagExpenses &&  flagTotalExpenses ){
			this.totalExpenses = totalExpenseTemp;
			System.out.println("--------- ");
			System.out.println("----3.1---- ");
			System.out.println("--------- ");
			System.out.println("--------- ");
		}else{
			System.out.println("--------- ");
			System.out.println("----4.1---- ");
			System.out.println("--------- ");
			System.out.println("--------- ");
			this.totalExpenses = totalExpenseTemp;
		}
		
		
		if(user.equals("cliente"))
		{
			porcActionUser = "(Cliente)";
		}
		
		if(user.equals("mesacontrol"))
		{
			porcActionUser = "(Mesa de control)";
		}
		
		dispListPorcWait = false;
		dispListPorc = true;	
	}
}
