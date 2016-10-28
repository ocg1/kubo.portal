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
			resultClass = DetSaldosKuboGlobal.class,
			name = "getDetSaldoskuboGlo",
			query = "CALL DETSALDOSKUBOGLOBAL(:clienteID, :cuentaAhoID, :tipoConsulta, :fechaInicio, :fechaFin, :mes, :anio)",
			hints = {
				@QueryHint(name = "org.hibernate.callable", value = "true")
					}
		)
})

@Entity
public class DetSaldosKuboGlobal implements Serializable{
		private static final long serialVersionUID = 1;
		
		@Id @Column (name="Consecutivo")
		private Integer consecutivo;
		
		@Column (name="Descripcion")
		private String Descripcion;
		
		@Column (name="CuentaAhoID")
		private Integer cuentaAhoID;
		
		@Column (name="DesMoneda")
		private String desMoneda;
		
		@Column (name="Sucursal")
		private String sucursal;
		
		@Column (name="SaldoInicial")
		private Double saldoInicial;
		
		@Column (name="SaldoActual")
		private Double saldoActual;
		
		@Column (name="SaldoDisponible")
		private Double saldoDisponible;
		
		@Column (name="SaldoBloqueado")
		private Double saldoBloqueado;
		
		@Column (name="SaldoPromedio")
		private Double saldoPromedio;
		
		@Column (name="SaldoSBC")
		private Double saldoSBC;
		
		@Column (name="TasaInteres")
		private Double tasaInteres;
		
		@Column (name="TasaPeriodo")
		private Double tasaPeriodo;
		
		@Column (name="Gat")
		private Double gat;
		
		@Column (name="GatReal")
		private Double gatReal;

		public Integer getConsecutivo() {
			return consecutivo;
		}
		public void setConsecutivo(Integer consecutivo) {
			this.consecutivo = consecutivo;
		}
		public String getDescripcion() {
			return Descripcion;
		}
		public void setDescripcion(String descripcion) {
			Descripcion = descripcion;
		}
		public Integer getCuentaAhoID() {
			return cuentaAhoID;
		}
		public void setCuentaAhoID(Integer cuentaAhoID) {
			this.cuentaAhoID = cuentaAhoID;
		}
		public String getDesMoneda() {
			return desMoneda;
		}
		public void setDesMoneda(String desMoneda) {
			this.desMoneda = desMoneda;
		}
		public String getSucursal() {
			return sucursal;
		}
		public void setSucursal(String sucursal) {
			this.sucursal = sucursal;
		}
		public Double getSaldoInicial() {
			return saldoInicial;
		}
		public void setSaldoInicial(Double saldoInicial) {
			this.saldoInicial = saldoInicial;
		}
		public Double getSaldoActual() {
			return saldoActual;
		}
		public void setSaldoActual(Double saldoActual) {
			this.saldoActual = saldoActual;
		}
		public Double getSaldoDisponible() {
			return saldoDisponible;
		}
		public void setSaldoDisponible(Double saldoDisponible) {
			this.saldoDisponible = saldoDisponible;
		}
		public Double getSaldoBloqueado() {
			return saldoBloqueado;
		}
		public void setSaldoBloqueado(Double saldoBloqueado) {
			this.saldoBloqueado = saldoBloqueado;
		}
		public Double getSaldoPromedio() {
			return saldoPromedio;
		}
		public void setSaldoPromedio(Double saldoPromedio) {
			this.saldoPromedio = saldoPromedio;
		}
		public Double getSaldoSBC() {
			return saldoSBC;
		}
		public void setSaldoSBC(Double saldoSBC) {
			this.saldoSBC = saldoSBC;
		}
		public Double getTasaInteres() {
			return tasaInteres;
		}
		public void setTasaInteres(Double tasaInteres) {
			this.tasaInteres = tasaInteres;
		}
		public Double getTasaPeriodo() {
			return tasaPeriodo;
		}
		public void setTasaPeriodo(Double tasaPeriodo) {
			this.tasaPeriodo = tasaPeriodo;
		}
		public Double getGat() {
			return gat;
		}
		public void setGat(Double gat) {
			this.gat = gat;
		}
		public Double getGatReal() {
			return gatReal;
		}
		public void setGatReal(Double gatReal) {
			this.gatReal = gatReal;
		}
}
