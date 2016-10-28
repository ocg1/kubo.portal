package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.model.Company;
import mx.com.kubo.services.CompanyService;

import org.apache.log4j.Logger;

@ManagedBean
@SessionScoped
public class AddCompany implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{companyServiceImp}")
	private CompanyService companyService;
	
	private int company_id;
	private String name;
	
	
	
	public String addNewCompany(){
		log.info("Inicio add new company");
		Company newCompany=new Company();
		newCompany.setName(getName());		
		//Guarda la nueva compania
		companyService.add(newCompany);
		log.info("Fin add Company");
		return "/jsf/Result.xhtml";
	}
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
}
