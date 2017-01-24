package mx.com.kubo.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseProyectLoanActiveInSafi
implements Serializable
{
	private static final long serialVersionUID = -8005117699033153645L;
	
	private String consecutivo;
	private String proyect_purpose_name;
	private String proyect_name;
	private String proyect_loan_id;
	private String proyect_id;
	private String prospectus_id;
	private String company_id;
	private String fondeokuboid;
	private String kubo_score_a;
	private String kubo_score_b;
	private String ammount;
	private String plazoStr;
	private String fechaInicioInv;
	private String rate_investor;
	private String period_rate;
	private String rate_moratoria;
	private String montofondeoinv;
	private String porcentajefondeo;
	private String saldo_vigente;
	private String saldo_atrasado;
	private String estatus;
	private String dias_atraso;
	private String cuotas_atraso;
	
	public String getConsecutivo() {
		return consecutivo;
	}
	
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	public String getProyect_purpose_name() {
		return proyect_purpose_name;
	}

	public void setProyect_purpose_name(String proyect_purpose_name) {
		this.proyect_purpose_name = proyect_purpose_name;
	}

	public String getProyect_name() {
		return proyect_name;
	}

	public void setProyect_name(String proyect_name) {
		this.proyect_name = proyect_name;
	}

	public String getProyect_loan_id() {
		return proyect_loan_id;
	}

	public void setProyect_loan_id(String proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}

	public String getProyect_id() {
		return proyect_id;
	}

	public void setProyect_id(String proyect_id) {
		this.proyect_id = proyect_id;
	}

	public String getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(String prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFondeokuboid() {
		return fondeokuboid;
	}

	public void setFondeokuboid(String fondeokuboid) {
		this.fondeokuboid = fondeokuboid;
	}

	public String getKubo_score_a() {
		return kubo_score_a;
	}

	public void setKubo_score_a(String kubo_score_a) {
		this.kubo_score_a = kubo_score_a;
	}

	public String getKubo_score_b() {
		return kubo_score_b;
	}

	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getPlazoStr() {
		return plazoStr;
	}

	public void setPlazoStr(String plazoStr) {
		this.plazoStr = plazoStr;
	}

	public String getFechaInicioInv() {
		return fechaInicioInv;
	}

	public void setFechaInicioInv(String fechaInicioInv) {
		this.fechaInicioInv = fechaInicioInv;
	}

	public String getRate_investor() {
		return rate_investor;
	}

	public void setRate_investor(String rate_investor) {
		this.rate_investor = rate_investor;
	}

	public String getPeriod_rate() {
		return period_rate;
	}

	public void setPeriod_rate(String period_rate) {
		this.period_rate = period_rate;
	}

	public String getRate_moratoria() {
		return rate_moratoria;
	}

	public void setRate_moratoria(String rate_moratoria) {
		this.rate_moratoria = rate_moratoria;
	}

	public String getMontofondeoinv() {
		return montofondeoinv;
	}

	public void setMontofondeoinv(String montofondeoinv) {
		this.montofondeoinv = montofondeoinv;
	}

	public String getPorcentajefondeo() {
		return porcentajefondeo;
	}

	public void setPorcentajefondeo(String porcentajefondeo) {
		this.porcentajefondeo = porcentajefondeo;
	}

	public String getSaldo_vigente() {
		return saldo_vigente;
	}

	public void setSaldo_vigente(String saldo_vigente) {
		this.saldo_vigente = saldo_vigente;
	}

	public String getSaldo_atrasado() {
		return saldo_atrasado;
	}

	public void setSaldo_atrasado(String saldo_atrasado) {
		this.saldo_atrasado = saldo_atrasado;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDias_atraso() {
		return dias_atraso;
	}

	public void setDias_atraso(String dias_atraso) {
		this.dias_atraso = dias_atraso;
	}

	public String getCuotas_atraso() {
		return cuotas_atraso;
	}

	public void setCuotas_atraso(String cuotas_atraso) {
		this.cuotas_atraso = cuotas_atraso;
	}
}
