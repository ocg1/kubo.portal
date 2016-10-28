package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.DetMovsInvestmentsBean;
import mx.com.kubo.services.DetMovsInvestmentsServicio;
import mx.com.kubo.services.NaturalPersonService;

public class DetMovsInvestmentsDMO {
	
	@ManagedProperty("#{detMovsInvestmentsServicioImp}")
	protected DetMovsInvestmentsServicio detMovsInvestmentsServicio; 
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService personaNaturalService;
	
	protected List<DetMovsInvestmentsBean> 	detMovsInvestmentsList;
	protected String periodoAconsultar;
	protected List<String> numCuentas;
	protected String selectedAccount;
	
	
	public DetMovsInvestmentsServicio getDetMovsInvestmentsServicio() {
		return detMovsInvestmentsServicio;
	}

	public void setDetMovsInvestmentsServicio(
			DetMovsInvestmentsServicio detMovsInvestmentsServicio) {
		this.detMovsInvestmentsServicio = detMovsInvestmentsServicio;
	}


	public List<DetMovsInvestmentsBean> getDetMovsInvestmentsList() {
		return detMovsInvestmentsList;
	}


	public void setDetMovsInvestmentsList(
			List<DetMovsInvestmentsBean> detMovsInvestmentsList) {
		this.detMovsInvestmentsList = detMovsInvestmentsList;
	}

	public String getPeriodoAconsultar() {
		return periodoAconsultar;
	}

	public void setPeriodoAconsultar(String periodoAconsultar) {
		this.periodoAconsultar = periodoAconsultar;
	}

	public NaturalPersonService getPersonaNaturalService() {
		return personaNaturalService;
	}

	public void setPersonaNaturalService(NaturalPersonService personaNaturalService) {
		this.personaNaturalService = personaNaturalService;
	}

	public List<String> getNumCuentas() {
		return numCuentas;
	}

	public void setNumCuentas(List<String> numCuentas) {
		this.numCuentas = numCuentas;
	}

	public String getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}
}
