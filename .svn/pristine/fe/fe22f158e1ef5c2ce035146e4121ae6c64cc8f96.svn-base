package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.References;
import mx.com.kubo.model.Study_Level;
import mx.com.kubo.services.LegalStatusService;
import mx.com.kubo.services.MaritalStatusService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ReferencesService;
import mx.com.kubo.services.StudyLevelService;

public abstract class ImoreInfoDMO 
{
	@ManagedProperty("#{referencesServiceImp}")
	protected ReferencesService sreferencesService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{legalStatusServiceImp}")
	protected LegalStatusService legalstatusService;

	@ManagedProperty("#{maritalStatusServiceImp}")
	protected MaritalStatusService maritalstatusService;
	
	@ManagedProperty("#{studyLevelServiceImp}")
	protected StudyLevelService studyLevelService;
	
	protected FacesContext  faces;
	protected ELContext     elContext;
	protected ELResolver    resolver;
	protected SessionBean   sesion;
	protected NaturalPerson naturalPerson;
	
	protected List<Study_Level>    studylevellist;
	protected List<Legal_Status>   legalstatuslist;
	protected List<Marital_Status> maritalstatuslist;
	
	protected String maritalstatusdis;
	
	private String phone_lada_ref1;
	private String phone_lada_ref2;
	private String phone_ref1;
	private String phone_ref2;	

	protected List<String> selectedComputer;
	protected List<String> selectedInternet;
	
	private References reference1;
	private References reference2;
	
	protected static final int NACIONAL   = 1;
	protected static final int EXTRANJERO = 0;
	
	private boolean hasRef1;
	private boolean hasRef2;
//	private boolean datos_credencial_elector_ENABLED;
	
	protected ImoreInfoDMO()
	{
		maritalstatusdis = "none";
	}
	
	public void setLegalstatusService(LegalStatusService service) 
	{
		legalstatusService = service;
	}

	public void setMaritalstatusService(MaritalStatusService service) 
	{
		maritalstatusService = service;
	}

	public void setSreferencesService(ReferencesService service) 
	{
		sreferencesService = service;
	}

	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}
	
	public void setStudyLevelService(StudyLevelService service) 
	{
		studyLevelService = service;
	}
	
	public LegalStatusService getLegalstatusService() 
	{
		return legalstatusService;
	}

	public MaritalStatusService getMaritalstatusService() 
	{
		return maritalstatusService;
	}
	
	public StudyLevelService getStudyLevelService() 
	{
		return studyLevelService;
	}
	
	public ReferencesService getSreferencesService() 
	{
		return sreferencesService;
	}

	public NaturalPersonService getNaturalPersonService() 
	{
		return naturalPersonService;
	}
	
	public NaturalPerson getNaturalPerson() {
		return naturalPerson;
	}

	public References getReference1() {
		return reference1;
	}

	public References getReference2() {
		return reference2;
	}

	public String getPhone_lada_ref1() {
		return phone_lada_ref1;
	}

	public String getPhone_lada_ref2() {
		return phone_lada_ref2;
	}

	public String getPhone_ref1() {
		return phone_ref1;
	}
	
	public void setMaritalstatusdis(String maritalstatusdis) 
	{
		this.maritalstatusdis = maritalstatusdis;
	}

	public String getPhone_ref2() {
		return phone_ref2;
	}
	
	public boolean isHasRef1() {
		return hasRef1;
	}

	public boolean isHasRef2() {
		return hasRef2;
	}
	
	public void setNaturalPerson(NaturalPerson naturalPerson) {
		this.naturalPerson = naturalPerson;
	}

	public void setReference1(References reference1) {
		this.reference1 = reference1;
	}

	public void setReference2(References reference2) {
		this.reference2 = reference2;
	}

	public void setPhone_lada_ref1(String phone_lada_ref1) {
		this.phone_lada_ref1 = phone_lada_ref1;
	}

	public void setPhone_lada_ref2(String phone_lada_ref2) {
		this.phone_lada_ref2 = phone_lada_ref2;
	}

	public void setPhone_ref1(String phone_ref1) {
		this.phone_ref1 = phone_ref1;
	}

	public void setPhone_ref2(String phone_ref2) {
		this.phone_ref2 = phone_ref2;
	}

	public void setHasRef1(boolean hasRef1) {
		this.hasRef1 = hasRef1;
	}

	public void setHasRef2(boolean hasRef2) {
		this.hasRef2 = hasRef2;
	}

	public List<Study_Level> getStudylevellist() {
		return studylevellist;
	}

	public void setStudylevellist(List<Study_Level> studylevellist) {
		this.studylevellist = studylevellist;
	}

	public List<Legal_Status> getLegalstatuslist() {
		return legalstatuslist;
	}

	public List<Marital_Status> getMaritalstatuslist() {
		return maritalstatuslist;
	}

	public void setLegalstatuslist(List<Legal_Status> legalstatuslist) {
		this.legalstatuslist = legalstatuslist;
	}

	public void setMaritalstatuslist(List<Marital_Status> maritalstatuslist) {
		this.maritalstatuslist = maritalstatuslist;
	}
	
	public List<String> getSelectedComputer() {
		return selectedComputer;
	}

	public void setSelectedComputer(List<String> selectedComputer) {
		this.selectedComputer = selectedComputer;
	}

	public List<String> getSelectedInternet() {
		return selectedInternet;
	}

	public void setSelectedInternet(List<String> selectedInternet) {
		this.selectedInternet = selectedInternet;
	}
}
