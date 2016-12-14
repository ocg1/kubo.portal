package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="historicos.TSAFI_CREDITOSMOVS")
public class TSafiCreditosMovs implements Serializable {
		
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CREDITOSMOVS_ID")
	private BigInteger creditosmovs_id;

	@Column(name="CREDITOID")
	private Integer creditoId; 
	@Column(name="AMORTICREID")
	private Integer amorticreId; 
	@Column(name="TRANSACCION")
	private Integer transaccion;
	@Column(name="TIPOMOVCREID")
	private Integer tipoMovCreId;
	@Column(name="FECHAAPLICACION")
	private Date fechaAplicacion;
	 
	 @Column(name="FECHAOPERACION")
	 private Date fechaOperacion;   
	 @Column(name="TIPO_SALDO")
	 private String tipoSaldo;   
	 @Column(name="NATMOVIMIENTO")
	 private String natMovimiento;  
	 @Column(name="DESCRIPCION")
	 private String descripcion;  
	 @Column(name="CANTIDAD")
	 private Double cantidad;  
	 @Column(name="DESCRIPCION_OPERACION")
	 private String decripcionOperacion;
	 @Column(name="REFERENCIA")
	 private String referencia;  
	 @Column(name="FECHAACTUAL")
	 private Date fechaActual; 
	 @Column(name="PROSPECTOIDEXT")
	 private Integer prospectoIdExt;
	 

	 
		public Integer getCreditoId() {
			return creditoId;
		}
		public void setCreditoId(Integer creditoId) {
			this.creditoId = creditoId;
		}
		public Integer getAmorticreId() {
			return amorticreId;
		}
		public void setAmorticreId(Integer amorticreId) {
			this.amorticreId = amorticreId;
		}
		public Integer getTransaccion() {
			return transaccion;
		}
		public void setTransaccion(Integer transaccion) {
			this.transaccion = transaccion;
		}
		public Integer getTipoMovCreId() {
			return tipoMovCreId;
		}
		public void setTipoMovCreId(Integer tipoMovCreId) {
			this.tipoMovCreId = tipoMovCreId;
		}
		public Date getFechaAplicacion() {
			return fechaAplicacion;
		}
		public void setFechaAplicacion(Date fechaAplicacion) {
			this.fechaAplicacion = fechaAplicacion;
		}  
		public Date getFechaOperacion() {
			return fechaOperacion;
		}
		public void setFechaOperacion(Date fechaOperacion) {
			this.fechaOperacion = fechaOperacion;
		}
		public String getTipoSaldo() {
			return tipoSaldo;
		}
		public void setTipoSaldo(String tipoSaldo) {
			this.tipoSaldo = tipoSaldo;
		}
		public String getNatMovimiento() {
			return natMovimiento;
		}
		public void setNatMovimiento(String natMovimiento) {
			this.natMovimiento = natMovimiento;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Double getCantidad() {
			return cantidad;
		}
		public void setCantidad(Double cantidad) {
			this.cantidad = cantidad;
		}
		public String getDecripcionOperacion() {
			return decripcionOperacion;
		}
		public void setDecripcionOperacion(String decripcionOperacion) {
			this.decripcionOperacion = decripcionOperacion;
		}
		public String getReferencia() {
			return referencia;
		}
		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}
		public Date getFechaActual() {
			return fechaActual;
		}
		public void setFechaActual(Date fechaActual) {
			this.fechaActual = fechaActual;
		}
		public Integer getProspectoIdExt() {
			return prospectoIdExt;
		}
		public void setProspectoIdExt(Integer prospectoIdExt) {
			this.prospectoIdExt = prospectoIdExt;
		} 

}
