package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="historicos.VTSAFI_POSICIONINT")
public class TSafiPosicionInt implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@EmbeddedId
	private TSafiPosicionIntPK pk;
	
	@Column(name="Nombrecompleto")//
	private String nombreCompleto;
	@Column(name="fechanacimiento")//
	private Date fechaNacimiento ;
	@Column(name="sexo")//
	private String sexo;
	@Column(name="estadocivil")//
	private String estadoCivil;
	@Column(name="estatus")//
	private String estatus;
	@Column(name="montocredito")//
	private Double montoCredito;
	@Column(name="solicitudcreditoid")//
	private Integer solicitudCreditoId ;
	@Column(name="tipofondeo")//
	private String tipoFondeo;
	@Column(name="fechainicio")//
	private Date fechaInicio;
	@Column(name="fechavencimien")//
	private Date fechaVencimien;
	@Column(name="TASAFIJA")//
	private Double tasaFija;
	@Column(name="FACTORMORA")//
	private Double factorMora;
	@Column(name="FRECUENCIACAP")//
	private String frecuenciaCap;
	@Column(name="NUMAMORTIZACION")//
	private Integer numAmortizacion;
	@Column(name="SALDOCAPVIGENT")//
	private Double saldoCapVigent ;
	@Column(name="saldocapatrasad")//
	private Double saldoCapAtrasad ;
	@Column(name="SALDOCAPVENCIDO")
	private Double saldoCapVencido ;
	@Column(name="SALDCAPVENNOEXI")
	private Double saldoCapVenNoExi;
	@Column(name="SALDOINTERORDIN")
	private Double saldoInterOrdin;
	@Column(name="SALDOINTERATRAS")
	private Double saldoInterAtras ;
	@Column(name="SALDOINTERVENC")
	private Double saldoInterVenc ;
	@Column(name="SALDOINTERPROVI")
	private Double saldoInterProvi;
	@Column(name="SALDOIVAINTERES")
	private Double saldoIvaInteres ;
	@Column(name="SALDOMORATORIOS")
	private Double saldoMoratorios ;
	@Column(name="SALDOIVAMORATOR")
	private Double saldoIvaMorator ;
	@Column(name="SALDCOMFALTPAGO")
	private Double saldoComFaltaPago ;
	@Column(name="SALIVACOMFALPAG")
	private Double salIvacomFalPag ;
	@Column(name="SALDOOTRASCOMIS")
	private Double saldoOtrasComis ;
	@Column(name="SALDOIVACOMISI")
	private Double saldoIvaComisi ;
	@Column(name="PROVISIONACUM")
	private Double provisionAcum;
	@Column(name="VALORCAT")
	private Double valorCat ;
	@Column(name="MONTOCOMAPERT")
	private Double  montoComApert;
	@Column(name="IVACOMAPERTURA")
	private Double ivaComApertura ;
	@Column(name="FECPROCVEN")
	private Date fecProcVen;
	@Column(name="PRODUCTOCREDITOID")
	private Integer productoCreditoId;
	@Column(name="PROSPECTOIDEXT")
	private Integer prospectoIdExt;
	@Column(name="CUOTA")
	private Double cuota ;
	@Column(name="CUOTA_MAS_COM")
	private Double cuota_mas_com ;
	@Column(name="FECHAULTIMOPAGO")
	private Date fechaUltimoPago;
	@Column(name="PAGOSVENCIDOS")
	private Integer pagosVencidos;
	@Column(name="TOTALPAGOS")
	private Integer totalPagos;
	@Column(name="SALDOCAPVIGENTE")
	private Double saldoCapVigente ;
	@Column(name="SALDOINTERESPRO")
	private Double saldoInteresPro ;
	@Column(name="SALDOINTNOCONTA")
	private Double saldoIntNoConta ;
	@Column(name="AMORTIZACIONID")
	private Integer amortizacionId;
	@Column(name="fechaalta")//
	private Date fechaAlta;
	
	public TSafiPosicionIntPK getPk() {
		return pk;
	}
	public void setPk(TSafiPosicionIntPK pk) {
		this.pk = pk;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Double getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(Double montoCredito) {
		this.montoCredito = montoCredito;
	}
	public Integer getSolicitudCreditoId() {
		return solicitudCreditoId;
	}
	public void setSolicitudCreditoId(Integer solicitudCreditoId) {
		this.solicitudCreditoId = solicitudCreditoId;
	}
	public String getTipoFondeo() {
		return tipoFondeo;
	}
	public void setTipoFondeo(String tipoFondeo) {
		this.tipoFondeo = tipoFondeo;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaVencimien() {
		return fechaVencimien;
	}
	public void setFechaVencimien(Date fechaVencimien) {
		this.fechaVencimien = fechaVencimien;
	}
	public Double getTasaFija() {
		return tasaFija;
	}
	public void setTasaFija(Double tasaFija) {
		this.tasaFija = tasaFija;
	}
	public Double getFactorMora() {
		return factorMora;
	}
	public void setFactorMora(Double factorMora) {
		this.factorMora = factorMora;
	}
	public String getFrecuenciaCap() {
		return frecuenciaCap;
	}
	public void setFrecuenciaCap(String frecuenciaCap) {
		this.frecuenciaCap = frecuenciaCap;
	}
	public Integer getNumAmortizacion() {
		return numAmortizacion;
	}
	public void setNumAmortizacion(Integer numAmortizacion) {
		this.numAmortizacion = numAmortizacion;
	}
	public Double getSaldoCapVigent() {
		return saldoCapVigent;
	}
	public void setSaldoCapVigent(Double saldoCapVigent) {
		this.saldoCapVigent = saldoCapVigent;
	}
	public Double getSaldoCapAtrasad() {
		return saldoCapAtrasad;
	}
	public void setSaldoCapAtrasad(Double saldoCapAtrasad) {
		this.saldoCapAtrasad = saldoCapAtrasad;
	}
	public Double getSaldoCapVencido() {
		return saldoCapVencido;
	}
	public void setSaldoCapVencido(Double saldoCapVencido) {
		this.saldoCapVencido = saldoCapVencido;
	}
	public Double getSaldoCapVenNoExi() {
		return saldoCapVenNoExi;
	}
	public void setSaldoCapVenNoExi(Double saldoCapVenNoExi) {
		this.saldoCapVenNoExi = saldoCapVenNoExi;
	}
	public Double getSaldoInterOrdin() {
		return saldoInterOrdin;
	}
	public void setSaldoInterOrdin(Double saldoInterOrdin) {
		this.saldoInterOrdin = saldoInterOrdin;
	}
	public Double getSaldoInterAtras() {
		return saldoInterAtras;
	}
	public void setSaldoInterAtras(Double saldoInterAtras) {
		this.saldoInterAtras = saldoInterAtras;
	}
	public Double getSaldoInterVenc() {
		return saldoInterVenc;
	}
	public void setSaldoInterVenc(Double saldoInterVenc) {
		this.saldoInterVenc = saldoInterVenc;
	}
	public Double getSaldoInterProvi() {
		return saldoInterProvi;
	}
	public void setSaldoInterProvi(Double saldoInterProvi) {
		this.saldoInterProvi = saldoInterProvi;
	}
	public Double getSaldoIvaInteres() {
		return saldoIvaInteres;
	}
	public void setSaldoIvaInteres(Double saldoIvaInteres) {
		this.saldoIvaInteres = saldoIvaInteres;
	}
	public Double getSaldoMoratorios() {
		return saldoMoratorios;
	}
	public void setSaldoMoratorios(Double saldoMoratorios) {
		this.saldoMoratorios = saldoMoratorios;
	}
	public Double getSaldoIvaMorator() {
		return saldoIvaMorator;
	}
	public void setSaldoIvaMorator(Double saldoIvaMorator) {
		this.saldoIvaMorator = saldoIvaMorator;
	}
	public Double getSaldoComFaltaPago() {
		return saldoComFaltaPago;
	}
	public void setSaldoComFaltaPago(Double saldoComFaltaPago) {
		this.saldoComFaltaPago = saldoComFaltaPago;
	}
	public Double getSalIvacomFalPag() {
		return salIvacomFalPag;
	}
	public void setSalIvacomFalPag(Double salIvacomFalPag) {
		this.salIvacomFalPag = salIvacomFalPag;
	}
	public Double getSaldoOtrasComis() {
		return saldoOtrasComis;
	}
	public void setSaldoOtrasComis(Double saldoOtrasComis) {
		this.saldoOtrasComis = saldoOtrasComis;
	}
	public Double getSaldoIvaComisi() {
		return saldoIvaComisi;
	}
	public void setSaldoIvaComisi(Double saldoIvaComisi) {
		this.saldoIvaComisi = saldoIvaComisi;
	}
	public Double getProvisionAcum() {
		return provisionAcum;
	}
	public void setProvisionAcum(Double provisionAcum) {
		this.provisionAcum = provisionAcum;
	}
	public Double getValorCat() {
		return valorCat;
	}
	public void setValorCat(Double valorCat) {
		this.valorCat = valorCat;
	}
	public Double getMontoComApert() {
		return montoComApert;
	}
	public void setMontoComApert(Double montoComApert) {
		this.montoComApert = montoComApert;
	}
	public Double getIvaComApertura() {
		return ivaComApertura;
	}
	public void setIvaComApertura(Double ivaComApertura) {
		this.ivaComApertura = ivaComApertura;
	}
	public Date getFecProcVen() {
		return fecProcVen;
	}
	public void setFecProcVen(Date fecProcVen) {
		this.fecProcVen = fecProcVen;
	}
	public Integer getProductoCreditoId() {
		return productoCreditoId;
	}
	public void setProductoCreditoId(Integer productoCreditoId) {
		this.productoCreditoId = productoCreditoId;
	}
	public Integer getProspectoIdExt() {
		return prospectoIdExt;
	}
	public void setProspectoIdExt(Integer prospectoIdExt) {
		this.prospectoIdExt = prospectoIdExt;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	public Double getCuota_mas_com() {
		return cuota_mas_com;
	}
	public void setCuota_mas_com(Double cuota_mas_com) {
		this.cuota_mas_com = cuota_mas_com;
	}
	public Date getFechaUltimoPago() {
		return fechaUltimoPago;
	}
	public void setFechaUltimoPago(Date fechaUltimoPago) {
		this.fechaUltimoPago = fechaUltimoPago;
	}
	public Integer getPagosVencidos() {
		return pagosVencidos;
	}
	public void setPagosVencidos(Integer pagosVencidos) {
		this.pagosVencidos = pagosVencidos;
	}
	public Integer getTotalPagos() {
		return totalPagos;
	}
	public void setTotalPagos(Integer totalPagos) {
		this.totalPagos = totalPagos;
	}
	public Double getSaldoCapVigente() {
		return saldoCapVigente;
	}
	public void setSaldoCapVigente(Double saldoCapVigente) {
		this.saldoCapVigente = saldoCapVigente;
	}
	public Double getSaldoInteresPro() {
		return saldoInteresPro;
	}
	public void setSaldoInteresPro(Double saldoInteresPro) {
		this.saldoInteresPro = saldoInteresPro;
	}
	public Double getSaldoIntNoConta() {
		return saldoIntNoConta;
	}
	public void setSaldoIntNoConta(Double saldoIntNoConta) {
		this.saldoIntNoConta = saldoIntNoConta;
	}
	public Integer getAmortizacionId() {
		return amortizacionId;
	}
	public void setAmortizacionId(Integer amortizacionId) {
		this.amortizacionId = amortizacionId;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
