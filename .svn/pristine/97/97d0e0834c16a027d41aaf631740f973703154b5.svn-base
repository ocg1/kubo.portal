package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.DetSaldosKuboGlobal;
import mx.com.kubo.services.DetSaldosKuboGlobalService;
import mx.com.kubo.services.NaturalPersonService;

public class DetSaldosKuboGlobalDMO {

	@ManagedProperty("#{detSaldosKuboGlobalServiceImp}")
	protected DetSaldosKuboGlobalService detSaldosKuboGlobalService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService personaNaturalService;
	
	protected DetSaldosKuboGlobal detSaldosKubo;
	protected List<String> numCuentas;
	protected String selectedAccount;
	

	public DetSaldosKuboGlobalService getDetSaldosKuboGlobalService() {
		return detSaldosKuboGlobalService;
	}
	public void setDetSaldosKuboGlobalService(
			DetSaldosKuboGlobalService detSaldosKuboGlobalService) {
		this.detSaldosKuboGlobalService = detSaldosKuboGlobalService;
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
	public DetSaldosKuboGlobal getDetSaldosKubo() {
		return detSaldosKubo;
	}
	public void setDetSaldosKubo(DetSaldosKuboGlobal detSaldosKubo) {
		this.detSaldosKubo = detSaldosKubo;
	}
}
