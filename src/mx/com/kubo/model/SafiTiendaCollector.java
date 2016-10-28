package mx.com.kubo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = SafiTiendaCollector.class,
			name = "safiTiendaSP",    
			// query = "call CLIENTERFCCAL( :par_EmpresaID (int 11),aud_Usuario(int),aud_FechaActual(DateTime),aud_DireccionIP(varchar(15)),aud_ProgramaID(varchar(50)),aud_Sucursal(int),aud_NumTransaccion(bigint)", 
			query = "call microfin.CREMASCARFONCON(:par_EmpresaID, :aud_Usuario, :aud_FechaActual, :aud_DireccionIP, :aud_ProgramaID,:aud_Sucursal,:aud_NumTransaccion )",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})
@Entity
public class SafiTiendaCollector {
	
	@Id
	@Column
	private Integer SolicitudCreditoID; //		bigint(20)	YES		NULL
	@Column
	private Integer numTransaccion; //	bigint(20)	NO	MUL	NULL
	@Column
	private Integer CreditoID; //		bigint(20)	YES		NULL
	@Column
	private Double MontoCredito	; //	decimal(12,2)	YES		NULL
	@Column
	private Double SaldoCredito	; //	decimal(12,2)	YES		NULL
	@Column
	private Double MontoFonPropio; //		decimal(12,2)	YES		NULL
	@Column
	private Double PorcFonPropio; //		decimal(12,6)	YES		NULL
	@Column
	private Double DisponibleFondeo	; //	varchar(45)	YES		NULL
	@Column
	private Integer PlazoEnDias	; //	int(11)	YES		NULL
	@Column
	private String Frecuencia; //		char(1)	YES		NULL
	@Column
	private Date FechaInicio; //		date	YES		NULL
	@Column
	private Date FechaVencimien	; //	date	YES		NULL
	@Column
	private Integer NumeroCuotas; //		int(11)	YES		NULL
	@Column
	private Double MontoCuota; //		decimal(12,2)	YES		NULL
	@Column
	private Integer DiasPorTrans; //		int(11)	YES		NULL
	
	
	public Integer getNumTransaccion() {
		return numTransaccion;
	}
	public void setNumTransaccion(Integer numTransaccion) {
		this.numTransaccion = numTransaccion;
	}
	public Integer getSolicitudCreditoID() {
		return SolicitudCreditoID;
	}
	public void setSolicitudCreditoID(Integer solicitudCreditoID) {
		SolicitudCreditoID = solicitudCreditoID;
	}
	public Integer getCreditoID() {
		return CreditoID;
	}
	public void setCreditoID(Integer creditoID) {
		CreditoID = creditoID;
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
	public Double getDisponibleFondeo() {
		return DisponibleFondeo;
	}
	public void setDisponibleFondeo(Double disponibleFondeo) {
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
	

	
}
