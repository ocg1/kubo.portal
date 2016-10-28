package mx.com.kubo.managedbeans.investor;

import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.SimulationBaseService;
import mx.com.kubo.services.SystemParamService;

public class InvestmentSimulatorDMO {
	
	@ManagedProperty("#{simulationBaseServiceImp}")
	protected SimulationBaseService simulationBaseServiceImp;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	protected SessionBean       sesion;
	
	protected Double AMMOUNT_INIT = 100000D;

	protected String ammountStr = "100,000";
	
	protected Double ammount;
	
	protected String numprojects;
	
	protected String riskElements;
	
	protected String termElements;
	
	protected String genderElements;
	
	protected String destinoElements;
	
	protected String riskElementsTmp;
	
	protected String termElementsTmp;
	
	protected String genderElementsTmp;
	
	protected String destinoElementsTmp;
	
	protected String ammountStrTmp;
	
	protected String scriptGraphicPie;
	
	protected String scriptGraphicBar;
	
	protected String scriptGraphicArea;
	
	protected String scriptGraphicLine;
	
	protected String scriptGraphicAreaVig;
	
	protected String minAmmount;
	
	protected String maxAmmount;
	
	protected List<String> riskArray;
	protected List<String> genderArray;
	protected List<String> termArray;
	protected List<String> purposeArray;
	
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);

	

	public String getNumprojects() {
		return numprojects;
	}

	public void setNumprojects(String numprojects) {
		this.numprojects = numprojects;
	}

	public String getRiskElements() {
		return riskElements;
	}

	public void setRiskElements(String riskElements) {
		this.riskElements = riskElements;
	}

	public String getTermElements() {
		return termElements;
	}

	public void setTermElements(String termElements) {
		this.termElements = termElements;
	}

	public String getGenderElements() {
		return genderElements;
	}

	public void setGenderElements(String genderElements) {
		this.genderElements = genderElements;
	}

	public String getDestinoElements() {
		return destinoElements;
	}

	public void setDestinoElements(String destinoElements) {
		this.destinoElements = destinoElements;
	}

	public String getAmmountStr() {
		return ammountStr;
	}

	public void setAmmountStr(String ammountStr) {
		this.ammountStr = ammountStr;
	}

	public SimulationBaseService getSimulationBaseServiceImp() {
		return simulationBaseServiceImp;
	}

	public void setSimulationBaseServiceImp(
			SimulationBaseService simulationBaseServiceImp) {
		this.simulationBaseServiceImp = simulationBaseServiceImp;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public String getScriptGraphicPie() {
		return scriptGraphicPie;
	}

	public void setScriptGraphicPie(String scriptGraphicPie) {
		this.scriptGraphicPie = scriptGraphicPie;
	}

	public String getScriptGraphicBar() {
		return scriptGraphicBar;
	}

	public void setScriptGraphicBar(String scriptGraphicBar) {
		this.scriptGraphicBar = scriptGraphicBar;
	}

	public String getScriptGraphicArea() {
		return scriptGraphicArea;
	}

	public void setScriptGraphicArea(String scriptGraphicArea) {
		this.scriptGraphicArea = scriptGraphicArea;
	}

	public String getScriptGraphicLine() {
		return scriptGraphicLine;
	}

	public void setScriptGraphicLine(String scriptGraphicLine) {
		this.scriptGraphicLine = scriptGraphicLine;
	}

	public String getMinAmmount() {
		return minAmmount;
	}

	public void setMinAmmount(String minAmmount) {
		this.minAmmount = minAmmount;
	}

	public String getMaxAmmount() {
		return maxAmmount;
	}

	public void setMaxAmmount(String maxAmmount) {
		this.maxAmmount = maxAmmount;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public String getScriptGraphicAreaVig() {
		return scriptGraphicAreaVig;
	}

	public void setScriptGraphicAreaVig(String scriptGraphicAreaVig) {
		this.scriptGraphicAreaVig = scriptGraphicAreaVig;
	}
	
	
}
