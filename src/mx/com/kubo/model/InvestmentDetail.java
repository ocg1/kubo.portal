package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="view_investment_detail")
public class InvestmentDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FondeoKuboID")
	private BigInteger fondeoKuboID;
	
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	@Column
	private Double rate_investor;
	@Column
	private String kubo_score_a;
	@Column
	private String kubo_score_b;
	
	@Column(name="CuentaAhoID")
	private String cuentaAhoID;
	
	@Column(name="FechaInicio")
	private Date fechaInicio;
	
	@Column(name="FechaVencimiento")
	private Date fechaVencimiento;
	
	@Column(name="MontoFondeo")
	private Double montoFondeo;
	
	@Column
	private int frequency_id;
	@Column
	private Double ammount;
	
	@Column(name="PorcentajeFondeo")
	private Double porcentajeFondeo;
	
	@Column(name="Gat")
	private Double gatNominal;
	
	@Column(name="GatReal")
	private Double gatReal;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	    })	
	private NaturalPerson person;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "frequency_id", referencedColumnName = "frequency_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private Frequency frequency;
	
	
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public Double getRate_investor() {
		return rate_investor;
	}
	public void setRate_investor(Double rate_investor) {
		this.rate_investor = rate_investor;
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
	public int getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	public BigInteger getFondeoKuboID() {
		return fondeoKuboID;
	}
	public void setFondeoKuboID(BigInteger fondeoKuboID) {
		this.fondeoKuboID = fondeoKuboID;
	}
	public String getCuentaAhoID() {
		return cuentaAhoID;
	}
	public void setCuentaAhoID(String cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
	}
	public Double getMontoFondeo() {
		return montoFondeo;
	}
	public void setMontoFondeo(Double montoFondeo) {
		this.montoFondeo = montoFondeo;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Double getPorcentajeFondeo() {
		return porcentajeFondeo;
	}
	public void setPorcentajeFondeo(Double porcentajeFondeo) {
		this.porcentajeFondeo = porcentajeFondeo;
	}
	public Double getGatNominal() {
		return gatNominal;
	}
	public void setGatNominal(Double gatNominal) {
		this.gatNominal = gatNominal;
	}
	public Double getGatReal() {
		return gatReal;
	}
	public void setGatReal(Double gatReal) {
		this.gatReal = gatReal;
	}
	
}
