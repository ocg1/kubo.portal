package mx.com.kubo.managedbeans.mesa.solicitud;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.bean.ListInvestor;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectFundingService;
import mx.com.kubo.services.ProyectLoanService;

public class InvestorListByProyectDMO {

	@ManagedProperty("#{proyectFundingServiceImp}")
	protected ProyectFundingService fundingService;
	
	protected List<ViewInvestmetInProyect>listInvestors;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService servicioProyecto;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	protected Double ammountFounded=0D;
	
	protected Double ammountAvailable=0D;
	
	protected ProyectLoan actualProyect;
	
	protected List<ListInvestor> listInvestor_lst;
	
	protected Membership member_Solicitud;
	
	protected boolean flagAutorizado;
	
	public ProyectFundingService getFundingService() {
		return fundingService;
	}

	public void setFundingService(ProyectFundingService fundingService) {
		this.fundingService = fundingService;
	}

	public List<ViewInvestmetInProyect> getListInvestors() {
		return listInvestors;
	}

	public void setListInvestors(List<ViewInvestmetInProyect> listInvestors) {
		this.listInvestors = listInvestors;
	}

	public ProyectLoanService getServicioProyecto() {
		return servicioProyecto;
	}

	public void setServicioProyecto(ProyectLoanService servicioProyecto) {
		this.servicioProyecto = servicioProyecto;
	}

	public ProyectLoan getActualProyect() {
		return actualProyect;
	}

	public void setActualProyect(ProyectLoan actualProyect) {
		this.actualProyect = actualProyect;
	}

	public Double getAmmountFounded() {
		return ammountFounded;
	}

	public void setAmmountFounded(Double ammountFounded) {
		this.ammountFounded = ammountFounded;
	}

	public Double getAmmountAvailable() {
		return ammountAvailable;
	}

	public void setAmmountAvailable(Double ammountAvailable) {
		this.ammountAvailable = ammountAvailable;
	}

	public MembershipService getService_membership() {
		return service_membership;
	}

	public void setService_membership(MembershipService service_membership) {
		this.service_membership = service_membership;
	}

	public List<ListInvestor> getListInvestor_lst() {
		return listInvestor_lst;
	}

	public void setListInvestor_lst(List<ListInvestor> listInvestor_lst) {
		this.listInvestor_lst = listInvestor_lst;
	}

	public Membership getMember_Solicitud() {
		return member_Solicitud;
	}

	public void setMember_Solicitud(Membership member_Solicitud) {
		this.member_Solicitud = member_Solicitud;
	}

	public boolean isFlagAutorizado() {
		return flagAutorizado;
	}

	public void setFlagAutorizado(boolean flagAutorizado) {
		this.flagAutorizado = flagAutorizado;
	}
	
}
