package mx.com.kubo.managedbeans;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ReferencesService;

public abstract class MoreInfoDMO 
{
	@ManagedProperty("#{referencesServiceImp}")
	protected ReferencesService sreferencesService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
				
	protected static final long serialVersionUID = 1L;
	
	protected FacesContext faces;
	protected ELContext context;
	protected ELResolver resolver;
	protected ExternalContext external;
	
	protected SessionBean sesion;
	
	protected NaturalPerson naturalPerson;
	protected gnNaturalPersonPK natural_person_PK;
	
	protected ProyectLoan thisProyectLoan;
	
	protected ReferencesPK rfPK1;
	protected ReferencesPK rfPK2;
	protected ReferencesPK rfPK3;
	protected ReferencesPK rfPK4; 
	
	protected References ref1;
	protected References ref2;
	protected References ref3;
	protected References ref4;
	protected References reference1;
	protected References reference2;
	protected References reference3;
	protected References reference4;	
		
	protected String phone_lada_ref1;
	protected String phone_lada_ref2;
	protected String phone_lada_ref3;
	protected String phone_lada_ref4;	
	protected String phone_ref1;
	protected String phone_ref2;
	protected String phone_ref3;
	protected String phone_ref4;			
	protected String rphone;
	protected String[] arphone;	
	
	protected String kuboscore;
	
	protected boolean hasRef1;
	protected boolean hasRef2;
	protected boolean hasRef3;
	protected boolean hasRef4;
		
	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}
	
	public void setSreferencesService(ReferencesService service)
	{
		sreferencesService = service;
	}
	
	public NaturalPerson getNaturalPerson() 
	{
		return naturalPerson;
	}

	public References getReference1() {
		return reference1;
	}
	
	public References getReference2() {
		return reference2;
	}
	
	public References getReference3() {
		return reference3;
	}
	
	public References getReference4() {
		return reference4;
	}
		
	public boolean isHasRef1() {
		return hasRef1;
	}
	public boolean isHasRef2() {
		return hasRef2;
	}
	
	public boolean isHasRef3() {
		return hasRef3;
	}
	
	public boolean isHasRef4() {
		return hasRef4;
	}
/*	
	public void setHasRef1(boolean hasRef1) {
		this.hasRef1 = hasRef1;
	}
	 
	public void setHasRef2(boolean hasRef2) {
		this.hasRef2 = hasRef2;
	}
	
  	public void setHasRef3(boolean hasRef3) {
		this.hasRef3 = hasRef3;
	}
		
	public void setHasRef4(boolean hasRef4) {
		this.hasRef4 = hasRef4;
	}	
	
	public void setReference1(References reference1) {
		this.reference1 = reference1;
	}
	
	public void setReference2(References reference2) {
		this.reference2 = reference2;
	}
	
	public void setReference3(References reference3) {
		this.reference3 = reference3;
	}	
	
	public void setReference4(References reference4) {
		this.reference4 = reference4;
	}
		
	public void setMaritalstatusdis(String maritalstatusdis) {
		this.maritalstatusdis = maritalstatusdis;
	}
*/	
	public String getPhone_lada_ref2() {
		return phone_lada_ref2;
	}
	public void setPhone_lada_ref2(String phone_lada_ref2) {
		this.phone_lada_ref2 = phone_lada_ref2;
	}
	public String getPhone_lada_ref1() {
		return phone_lada_ref1;
	}
	public void setPhone_lada_ref1(String phone_lada_ref1) {
		this.phone_lada_ref1 = phone_lada_ref1;
	}
	public String getPhone_lada_ref3() {
		return phone_lada_ref3;
	}
	public void setPhone_lada_ref3(String phone_lada_ref3) {
		this.phone_lada_ref3 = phone_lada_ref3;
	}
	public String getPhone_lada_ref4() {
		return phone_lada_ref4;
	}
	public void setPhone_lada_ref4(String phone_lada_ref4) {
		this.phone_lada_ref4 = phone_lada_ref4;
	}
	public String getPhone_ref1() {
		return phone_ref1;
	}
	public void setPhone_ref1(String phone_ref1) {
		this.phone_ref1 = phone_ref1;
	}
	public String getPhone_ref2() {
		return phone_ref2;
	}
	public void setPhone_ref2(String phone_ref2) {
		this.phone_ref2 = phone_ref2;
	}
	public String getPhone_ref3() {
		return phone_ref3;
	}
	public void setPhone_ref3(String phone_ref3) {
		this.phone_ref3 = phone_ref3;
	}
	public String getPhone_ref4() {
		return phone_ref4;
	}
	public void setPhone_ref4(String phone_ref4) {
		this.phone_ref4 = phone_ref4;
	}

	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}

	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}

	public ProyectLoan getThisProyectLoan() {
		return thisProyectLoan;
	}

	public void setThisProyectLoan(ProyectLoan thisProyectLoan) {
		this.thisProyectLoan = thisProyectLoan;
	}

	public String getKuboscore() {
		return kuboscore;
	}

	public void setKuboscore(String kuboscore) {
		this.kuboscore = kuboscore;
	}
}
