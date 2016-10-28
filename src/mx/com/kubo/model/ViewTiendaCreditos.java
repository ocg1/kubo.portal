package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="view_mx_tiendacreditos")
public class ViewTiendaCreditos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Integer safi_mx_solicitud_id;//	bigint(20)
	@Column
	private BigInteger NumTransaccion;//	bigint(20)
	@Column
	private String safi_credit_id;//	bigint(20)
	@Column
	private Double MontoCredito;//	"decimal(12
	@Column
	private Double SaldoCredito;//	"decimal(12
	@Column
	private Double MontoFonPropio;//	"decimal(12
	@Column
	private Double PorcFonPropio;//	"decimal(12
	@Column
	private String DisponibleFondeo;//	varchar(45)
	@Column
	private Integer PlazoEnDias;//	int(11)
	@Column
	private String Frecuencia;//	char(1)
	@Column
	private Date FechaInicio;//	date
	@Column
	private Date FechaVencimien;//	date
	@Column
	private Integer NumeroCuotas;//	int(11)
	@Column
	private Double MontoCuota;//	"decimal(12" +
	@Column
	private Integer DiasPorTrans;//	int(11)
	@Column(name="CantInvCred")
	private BigInteger cantInvCred;//	int(11)
	@Column(name="CantInvSol")
	private BigInteger cantInvSol;//	int(11)
	
	
	public BigInteger getNumTransaccion() {
		return NumTransaccion;
	}
	public void setNumTransaccion(BigInteger numTransaccion) {
		NumTransaccion = numTransaccion;
	}
	public Integer getSafi_mx_solicitud_id() {
		return safi_mx_solicitud_id;
	}
	public void setSafi_mx_solicitud_id(Integer safi_mx_solicitud_id) {
		this.safi_mx_solicitud_id = safi_mx_solicitud_id;
	}
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Double getMontoCredito() {
		return MontoCredito;
	}
	public void setMontoCredito(Double montoCredito) {
		MontoCredito = montoCredito;
	}
	public Double getSaldoCredito() {
		return SaldoCredito;
	}
	public void setSaldoCredito(Double saldoCredito) {
		SaldoCredito = saldoCredito;
	}
	public Double getMontoFonPropio() {
		return MontoFonPropio;
	}
	public void setMontoFonPropio(Double montoFonPropio) {
		MontoFonPropio = montoFonPropio;
	}
	public Double getPorcFonPropio() {
		return PorcFonPropio;
	}
	public void setPorcFonPropio(Double porcFonPropio) {
		PorcFonPropio = porcFonPropio;
	}
	public String getDisponibleFondeo() {
		return DisponibleFondeo;
	}
	public void setDisponibleFondeo(String disponibleFondeo) {
		DisponibleFondeo = disponibleFondeo;
	}
	public Integer getPlazoEnDias() {
		return PlazoEnDias;
	}
	public void setPlazoEnDias(Integer plazoEnDias) {
		PlazoEnDias = plazoEnDias;
	}
	public String getFrecuencia() {
		return Frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		Frecuencia = frecuencia;
	}
	public Date getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public Date getFechaVencimien() {
		return FechaVencimien;
	}
	public void setFechaVencimien(Date fechaVencimien) {
		FechaVencimien = fechaVencimien;
	}
	public Integer getNumeroCuotas() {
		return NumeroCuotas;
	}
	public void setNumeroCuotas(Integer numeroCuotas) {
		NumeroCuotas = numeroCuotas;
	}
	public Double getMontoCuota() {
		return MontoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		MontoCuota = montoCuota;
	}
	public Integer getDiasPorTrans() {
		return DiasPorTrans;
	}
	public void setDiasPorTrans(Integer diasPorTrans) {
		DiasPorTrans = diasPorTrans;
	}
	public BigInteger getCantInvCred() {
		return cantInvCred;
	}
	public void setCantInvCred(BigInteger cantInvCred) {
		this.cantInvCred = cantInvCred;
	}
	public BigInteger getCantInvSol() {
		return cantInvSol;
	}
	public void setCantInvSol(BigInteger cantInvSol) {
		this.cantInvSol = cantInvSol;
	}

	
}
