package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = InvestmentsAtrasadasEdoCta.class,
			name		= "getInvestmentsAtraEdoCta",
			query 		= "call KUBOINVERSIONIMPCON(:cuentaAhoID)",
			hints		= {
							@QueryHint(name="org.hibernate.callable", value="true")
						  }
	)
})

@Entity
public class InvestmentsAtrasadasEdoCta implements Serializable{
	private static final long serialVersionUID = 1;
	
	@Id @Column (name = "CuentaAhoID")
	private Integer cuentaAhoID;
	
	@Column (name = "SaldoVigente0")
	private Double saldoVigente0;
	
	@Column (name = "SaldoVigente1a15")
	private Double	saldoVigente1a15;
	
	@Column (name = "SaldoVigente16a30")
	private Double	saldoVigente16a30;
	
	@Column (name = "SaldoVigente31a90")
	private Double saldoVigente31a90;
	
	@Column (name = "SaldoVigente91a120")
	private Double	saldoVigente91a120;
	
	@Column (name = "SaldoVigente120mas")
	private Double	saldoVigente120mas;
	
	@Column (name = "SaldoAtrasado0")
	private Double saldoAtrasado0;
	
	@Column (name = "SaldoAtrasado1a15")
	private Double	saldoAtrasado1a15;
	
	@Column (name = "SaldoAtrasado16a30")
	private Double	saldoAtrasado16a30;
	
	@Column (name = "SaldoAtrasado31a90")
	private Double saldoAtrasado31a90;
	
	@Column (name = "SaldoAtrasado91a120")
	private Double	saldoAtrasado91a120;
	
	@Column (name = "SaldoAtrasado120mas")
	private Double	saldoAtrasado120mas;
	
	@Column (name = "SaldoCapVigTotal")
	private Double	saldoCapVigTotal;

	@Column (name = "SaldoCapAtrTotal")
	private Double saldoCapAtrTotal;
		
	@Column (name = "SaldoIntVigente")
	private Double  saldoIntVigente;
	
	@Column (name = "SaldoIntAtrasado")
	private  Double saldoIntAtrasado;

	public Integer getCuentaAhoID() {
		return cuentaAhoID;
	}
	public void setCuentaAhoID(Integer cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
	}
	public Double getSaldoVigente0() {
		return saldoVigente0;
	}
	public void setSaldoVigente0(Double saldoVigente0) {
		this.saldoVigente0 = saldoVigente0;
	}
	public Double getSaldoVigente1a15() {
		return saldoVigente1a15;
	}
	public void setSaldoVigente1a15(Double saldoVigente1a15) {
		this.saldoVigente1a15 = saldoVigente1a15;
	}
	public Double getSaldoVigente16a30() {
		return saldoVigente16a30;
	}
	public void setSaldoVigente16a30(Double saldoVigente16a30) {
		this.saldoVigente16a30 = saldoVigente16a30;
	}
	public Double getSaldoVigente31a90() {
		return saldoVigente31a90;
	}
	public void setSaldoVigente31a90(Double saldoVigente31a90) {
		this.saldoVigente31a90 = saldoVigente31a90;
	}
	public Double getSaldoVigente91a120() {
		return saldoVigente91a120;
	}
	public void setSaldoVigente91a120(Double saldoVigente91a120) {
		this.saldoVigente91a120 = saldoVigente91a120;
	}
	public Double getSaldoVigente120mas() {
		return saldoVigente120mas;
	}
	public void setSaldoVigente120mas(Double saldoVigente120mas) {
		this.saldoVigente120mas = saldoVigente120mas;
	}
	public Double getSaldoAtrasado0() {
		return saldoAtrasado0;
	}
	public void setSaldoAtrasado0(Double saldoAtrasado0) {
		this.saldoAtrasado0 = saldoAtrasado0;
	}
	public Double getSaldoAtrasado1a15() {
		return saldoAtrasado1a15;
	}
	public void setSaldoAtrasado1a15(Double saldoAtrasado1a15) {
		this.saldoAtrasado1a15 = saldoAtrasado1a15;
	}
	public Double getSaldoAtrasado16a30() {
		return saldoAtrasado16a30;
	}
	public void setSaldoAtrasado16a30(Double saldoAtrasado16a30) {
		this.saldoAtrasado16a30 = saldoAtrasado16a30;
	}
	public Double getSaldoAtrasado31a90() {
		return saldoAtrasado31a90;
	}
	public void setSaldoAtrasado31a90(Double saldoAtrasado31a90) {
		this.saldoAtrasado31a90 = saldoAtrasado31a90	;
	}
	public Double getSaldoAtrasado91a120() {
		return saldoAtrasado91a120;
	}
	public void setSaldoAtrasado91a120(Double saldoAtrasado91a120) {
		this.saldoAtrasado91a120 = saldoAtrasado91a120;
	}
	public Double getSaldoAtrasado120mas() {
		return saldoAtrasado120mas;
	}
	public void setSaldoAtrasado120mas(Double saldoAtrasado120mas) {
		this.saldoAtrasado120mas = saldoAtrasado120mas;
	}
	public Double getSaldoIntVigente() {
		return saldoIntVigente;
	}
	public void setSaldoIntVigente(Double saldoIntVigente) {
		this.saldoIntVigente = saldoIntVigente;
	}
	public Double getSaldoCapVigTotal() {
		return saldoCapVigTotal;
	}
	public void setSaldoCapVigTotal(Double saldoCapVigTotal) {
		this.saldoCapVigTotal = saldoCapVigTotal;
	}
	public Double getSaldoCapAtrTotal() {
		return saldoCapAtrTotal;
	}
	public void setSaldoCapAtrTotal(Double saldoCapAtrTotal) {
		this.saldoCapAtrTotal = saldoCapAtrTotal;
	}
	public Double getSaldoIntAtrasado() {
		return saldoIntAtrasado;
	}
	public void setSaldoIntAtrasado(Double saldoIntAtrasado) {
		this.saldoIntAtrasado = saldoIntAtrasado;
	}
	
}
