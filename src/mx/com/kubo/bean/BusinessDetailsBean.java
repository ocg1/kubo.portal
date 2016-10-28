package mx.com.kubo.bean;

import java.util.List;

public class BusinessDetailsBean {
	
	private int index;
	private String description;
	private List<IncomeDetailsBean> lstIncomeDetaBean;
		
	public BusinessDetailsBean(){
		
	}
	public BusinessDetailsBean(int index,String description){
		this.index=index;
		this.description=description;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<IncomeDetailsBean> getLstIncomeDetaBean() {
		return lstIncomeDetaBean;
	}
	public void setLstIncomeDetaBean(List<IncomeDetailsBean> lstIncomeDetaBean) {
		this.lstIncomeDetaBean = lstIncomeDetaBean;
	}
	
	

}
