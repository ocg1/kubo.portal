package mx.com.kubo.managedbeans.mesa.cosultaMasivaIDProvider;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.services.IdProviderMassiveService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ScoringService;

public class ConsultaMasivaIProviderDMO {
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{idProviderMassiveServiceImp}")
	protected IdProviderMassiveService  idprovidermassiveservice;

	protected final String separador = "," ;
	
	protected final int COMPANY_ID = 1;
	
	protected final int min = 99999;
	protected final int max = 9999999;

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}

	public ProyectLoanService getProyectloanService() {
		return proyectloanService;
	}

	public void setProyectloanService(ProyectLoanService proyectloanService) {
		this.proyectloanService = proyectloanService;
	}

	public IdProviderMassiveService getIdprovidermassiveservice() {
		return idprovidermassiveservice;
	}

	public void setIdprovidermassiveservice(IdProviderMassiveService idprovidermassiveservice) {
		this.idprovidermassiveservice = idprovidermassiveservice;
	}
	
}
