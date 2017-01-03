package mx.com.kubo.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.Country;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.CountryService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.StateService;

public abstract class GeneraCurpRfcDMO 
{
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{stateServiceImp}")
	protected StateService stateService;
	
	@ManagedProperty("#{countryServiceImp}")
	protected CountryService countryService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
		
	protected NotificadorIMO notificador;
	
	protected Membership membership;
	protected MembershipPK membership_PK;
	protected NaturalPerson naturalPerson;
	
	protected ProyectLoan proyect_loan;
		
	protected List<ProyectLoan> lista_proyect_loan;
	protected List<StateCat> stateList;
	protected List<Country> countryList;
	
	protected ArrayList<String> days = new ArrayList<String>();
	protected ArrayList<String> months = new ArrayList<String>();
	protected ArrayList<String> years = new ArrayList<String>();
	
	private String day;
	private String month;
	private String year;
	private String dateStr;
	
	protected String bursolnum;
	
	private String extDis = "none";
	private String mexDis = "block";
	
	protected String monthStr[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
			"Diciembre" };
	
	protected int kubo_kubofinanciero_com;
	
	public void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}
	
	public void setStateService(StateService service) 
	{
		stateService = service;
	}

	public NaturalPerson getNaturalPerson() {
		return naturalPerson;
	}

	public void setNaturalPerson(NaturalPerson naturalPerson) {
		this.naturalPerson = naturalPerson;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public ArrayList<String> getDays() {
		return days;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public ArrayList<String> getMonths() {
		return months;
	}

	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}

	public String[] getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String[] monthStr) {
		this.monthStr = monthStr;
	}

	public String getExtDis() 
	{
		if (getNaturalPerson().getCitizenship() == null || getNaturalPerson().getCitizenship() == 1)
		{
			extDis = "none";
			
		} else {
			
			extDis = "block";
		}
		
		return extDis;
	}

	public void setExtDis(String extDis) {
		this.extDis = extDis;
	}

	public MembershipService getMembershipservice() {
		return membershipservice;
	}

	public void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}

	public String getBursolnum() {
		return bursolnum;
	}

	public ProyectLoanService getProyectloanService() {
		return proyectloanService;
	}

	public void setProyectloanService(ProyectLoanService service) 
	{
		proyectloanService = service;
	}

	public void setBursolnum(String bursolnum) {
		this.bursolnum = bursolnum;
	}  
	
	public String getMexDis() {
		if (getNaturalPerson().getCitizenship() == null
				|| getNaturalPerson().getCitizenship() == 1)
			mexDis = "block";
		else
			mexDis = "none";
		return mexDis;
	}

	public void setMexDis(String mexDis) {
		this.mexDis = mexDis;
	}

	public List<StateCat> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateCat> stateList) {
		this.stateList = stateList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

}
