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
			resultClass = DetalleMovsInvestments.class,
			name = "getDetalleMovimientos",
			query = "call KUBODETALLEMOVIMIENTOS(:clienteID,:cuentaAhoID,:tipoConsulta,:fechaInicio,:fechaFin,:mes,:anio)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	)
})

@Entity
public class DetalleMovsInvestments implements Serializable{
		private static final long serialVersionUID = 1;
		
		@Id @Column (name="Consecutivo")
		private Integer	consecutivo;
		
		@Column (name="Fecha")
		private String fecha;
		
		@Column (name="Descripcion1")
		private String descripcion1;
		
		@Column (name="Descripcion2")
		private String descripcion2;
		
		@Column (name="Descripcion3")
		private String descripcion3;
		
		@Column (name="AbonosMov")
		private Double AbonosMov;
		
		@Column (name="CargosMov")
		private Double CargosMov;
		
		@Column (name="BancoReceptor")
		private String 	bancoReceptor;
		
		public Integer getConsecutivo(){
			return consecutivo;
		}
		public void setConsecutivo(Integer consecutivo){
			this.consecutivo = consecutivo;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getDescripcion1() {
			return descripcion1;
		}
		public void setDescripcion1(String descripcion1) {
			this.descripcion1 = descripcion1;
		}
		public String getDescripcion2() {
			return descripcion2;
		}
		public void setDescripcion2(String descripcion2) {
			this.descripcion2 = descripcion2;
		}
		public String getDescripcion3() {
			return descripcion3;
		}
		public void setDescripcion3(String descripcion3) {
			this.descripcion3 = descripcion3;
		}
		public Double getAbonosMov() {
			return AbonosMov;
		}
		public void setAbonosMov(Double abonosMov) {
			AbonosMov = abonosMov;
		}
		public Double getCargosMov() {
			return CargosMov;
		}
		public void setCargosMov(Double cargosMov) {
			CargosMov = cargosMov;
		}
		public String getBancoReceptor() {
			return bancoReceptor;
		}
		public void setBancoReceptor(String bancoReceptor) {
			this.bancoReceptor = bancoReceptor;
		}
		

}		
