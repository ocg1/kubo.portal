package mx.com.kubo.managedbeans;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.Amortization;

public abstract class TablaAmortizacionDMO 
implements TablaAmortizacionIMO
{
	protected FacesContext faces;
	protected ELContext elContext;
	
	protected Map<String, String> parameters;
	
	protected Amortization amort;
	
	protected Simulator simulador;
	
	protected Double tasaconiva;
	
	protected Double ammount;
	protected Double rate;
	protected Double tax;
	protected Integer totalnumPayment;
	protected Double comission;
	protected String simulationDate;
	protected String zoneType;
	protected String payment;
	protected String frequency;
	protected String term;
	
	protected String parameter_ammount;
	protected String monto;
	protected String montoarecibir;
	protected String comision;
	protected String numPagos;
	protected String fechaSim;
	protected String zone;
	protected String strTasa1;
	protected String strTasa2;
	protected String cat;
	protected String fecCalc;
	
	protected Integer term_id ;
	protected Double dPayment;
	protected Double totalPayment ;
	protected String freq;
	
	protected boolean safiSimulation;
	
	protected ArrayList<Amortization> tamort;
	
	protected  Locale locale = new Locale("es","mx");
	protected  NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	
	protected SimpleDateFormat formatStr = new SimpleDateFormat("EEEE dd' de 'MMMM' de 'yyyy", new Locale("ES"));
	protected SimpleDateFormat formatStr1 = new SimpleDateFormat("dd' de 'MMMM' de 'yyyy", new Locale("ES"));
	
	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Integer getTotalnumPayment() {
		return totalnumPayment;
	}

	public void setTotalnumPayment(Integer totalnumPayment) {
		this.totalnumPayment = totalnumPayment;
	}

	public Double getComission() {
		return comission;
	}

	public void setComission(Double comission) {
		this.comission = comission;
	}

	public String getSimulationDate() {
		return simulationDate;
	}

	public void setSimulationDate(String simulationDate) {
		this.simulationDate = simulationDate;
	}

	public String getZoneType() {
		return zoneType;
	}

	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public ArrayList<Amortization> getTamort() {
		return tamort;
	}

	public void setTamort(ArrayList<Amortization> tamort) {
		this.tamort = tamort;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public String getNumPagos() {
		return numPagos;
	}

	public void setNumPagos(String numPagos) {
		this.numPagos = numPagos;
	}

	public String getFechaSim() {
		return fechaSim;
	}

	public void setFechaSim(String fechaSim) {
		this.fechaSim = fechaSim;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getStrTasa1() {
		return strTasa1;
	}

	public void setStrTasa1(String strTasa1) {
		this.strTasa1 = strTasa1;
	}

	public String getStrTasa2() {
		return strTasa2;
	}

	public void setStrTasa2(String strTasa2) {
		this.strTasa2 = strTasa2;
	}

	public String getMontoarecibir() {
		return montoarecibir;
	}

	public void setMontoarecibir(String montoarecibir) {
		this.montoarecibir = montoarecibir;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getFecCalc() {
		return fecCalc;
	}

	public void setFecCalc(String fecCalc) {
		this.fecCalc = fecCalc;
	}

	public boolean isSafiSimulation() {
		return safiSimulation;
	}

	public void setSafiSimulation(boolean safiSimulation) {
		this.safiSimulation = safiSimulation;
	}

	public Integer getTerm_id() {
		return term_id;
	}

	public void setTerm_id(Integer term_id) {
		this.term_id = term_id;
	}

	public Double getdPayment() {
		return dPayment;
	}

	public void setdPayment(Double dPayment) {
		this.dPayment = dPayment;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}
}
