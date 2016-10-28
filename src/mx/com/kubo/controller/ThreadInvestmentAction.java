package mx.com.kubo.controller;

import java.util.List;

import mx.com.kubo.controller.threads.PostInvestmentAction;
import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.model.ServiceCalling;

public class ThreadInvestmentAction {
	
	private List<ServiceCalling> 				lstService;
	private List<String>						strStored;
	private List<InvestmentProgressDet>			lstProgressDet;
	
	public boolean excecuteAction(){
		
		try{
			
			PostInvestmentAction post = new PostInvestmentAction();
			post.setLstService(lstService);
			post.setStrStored(strStored);
			post.setLstProgressDet(lstProgressDet);
			post.start();
			
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}

	public List<ServiceCalling> getLstService() {
		return lstService;
	}

	public void setLstService(List<ServiceCalling> lstService) {
		this.lstService = lstService;
	}

	public List<String> getStrStored() {
		return strStored;
	}

	public void setStrStored(List<String> strStored) {
		this.strStored = strStored;
	}

	public List<InvestmentProgressDet> getLstProgressDet() {
		return lstProgressDet;
	}

	public void setLstProgressDet(List<InvestmentProgressDet> lstProgressDet) {
		this.lstProgressDet = lstProgressDet;
	}
	
}
