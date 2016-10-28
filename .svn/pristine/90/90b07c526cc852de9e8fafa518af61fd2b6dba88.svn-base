package mx.com.kubo.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = "searchSummaySession")
@SessionScoped 
public class SearchSummaySession 
implements Serializable 
{
	private static final long serialVersionUID = -697207858501138394L;
	
	private String  searchSummary;
	private String  typeLog;
	
	private boolean  person = false;
	private boolean showInvestPnl=false;	
	private boolean actividad_ENABLED;
	private boolean investor_ENABLED;

	public String getSearchSummary() {
		return searchSummary;
	}

	public void setSearchSummary(String searchSummary) {
		this.searchSummary = searchSummary;
	}
	
	public String getTypeLog() {
		return typeLog;
	}
	
	public void setTypeLog(String typeLog) {
		this.typeLog = typeLog;
	}
	
	public boolean isPerson() {
		return person;
	}
	
	public void setPerson(boolean person) {
		this.person = person;
	}

	public boolean isShowInvestPnl() {
		return showInvestPnl;
	}

	public final boolean isActividad_ENABLED() {
		return actividad_ENABLED;
	}
	
	public final boolean isInvestor_ENABLED() {
		return investor_ENABLED;
	}

	public final void setInvestor_ENABLED(boolean investor_ENABLED) {
		this.investor_ENABLED = investor_ENABLED;
	}

	public final void setActividad_ENABLED(boolean actividad_ENABLED) {
		this.actividad_ENABLED = actividad_ENABLED;
	}

	public void setShowInvestPnl(boolean showInvestPnl) {
		this.showInvestPnl = showInvestPnl;
	}
}
