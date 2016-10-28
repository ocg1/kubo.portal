package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.ProyectFunding;

@ManagedBean
@SessionScoped
public class InvestorSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProyectFunding> investmentList;
	
	private List<ItemLoanList> listToInvestment;
	
	@PostConstruct
	public void init(){
		
		investmentList = new ArrayList<ProyectFunding>();
		
	}

	public List<ProyectFunding> getInvestmentList() {
		return investmentList;
	}

	public void setInvestmentList(List<ProyectFunding> investmentList) {
		this.investmentList = investmentList;
	}

	public List<ItemLoanList> getListToInvestment() {
		return listToInvestment;
	}

	public void setListToInvestment(List<ItemLoanList> listToInvestment) {
		this.listToInvestment = new ArrayList<ItemLoanList>();
		
		for( ItemLoanList item : listToInvestment ){
			
			ItemLoanList i = new ItemLoanList();
			
			i.setItemLoanList(item);
			
			this.listToInvestment.add(i);
		}
		
		// this.listToInvestment = listToInvestment;
	}
	
	
}
