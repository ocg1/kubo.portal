package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ViewDepositInfo.class,
			name = "collectorViewDepositInfo",    
			 
			// query = "	call microfin.CONTRATOSREP(100005328,86.1645,1,1,1,'2016-02-08 19:11:00','192.0.0.0','kubofinanciero.generaContratos',1,0)
			
			query = "call microfin.ENTRADASALIDACON(:par_INSERTA,:par_TIPONOTIFICA,:par_TipoOperacion,:par_TipoConsulta,:par_Fecha)",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
@Table( name="view_deposit_info")
public class ViewDepositInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ClaveRastreo")
	@Id 
	private BigInteger claveRastreo;
	
	@Column
	private int prospectus_id;
	
	@Column
	private String safi_account_id; // , varchar(12), YES, , ,
	
	@Column(name="Operacion")
	private String operacion; // , varchar(17), NO, , ,
	
	@Column(name="FechaOperacion")
	private Date fechaOperacion; // , date, YES, , ,
	
	@Column(name="HoraOperacion")
	private String HoraOperacion; //, varchar(8), YES, , ,
	
	@Column(name="CuentaOrigen")
	private String cuentaOrigen; //, char(0), NO, , ,
	
	@Column(name="CuentaDestino")
	private String cuentaDestino; //, varchar(12), YES, , ,
	
	@Column(name="NombreBeneficiario")
	private String nombreBeneficiario; //, varchar(183), YES, , , 
	
	@Column(name="Email")
	private String email; //, varchar(45), NO, , , 
	
	@Column(name="Importa")
	private Double importa; // , decimal(12,2), YES, , , 
	
	@Column(name="FechaAplicacion")
	private Date fechaAplicacion; //, date, YES, , ,
	
	@Column(name="Comision")
	private Integer comision; //, int(1), NO, , 0,
	
	@Column(name="IVAComision")
	private Integer iVAComision; // , int(1), NO, , 0,

	public BigInteger getClaveRastreo() {
		return claveRastreo;
	}

	public void setClaveRastreo(BigInteger claveRastreo) {
		this.claveRastreo = claveRastreo;
	}

	public String getSafi_account_id() {
		return safi_account_id;
	}

	public void setSafi_account_id(String safi_account_id) {
		this.safi_account_id = safi_account_id;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getHoraOperacion() {
		return HoraOperacion;
	}

	public void setHoraOperacion(String horaOperacion) {
		HoraOperacion = horaOperacion;
	}

	public String getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public String getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getImporta() {
		return importa;
	}

	public void setImporta(Double importa) {
		this.importa = importa;
	}

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Integer getComision() {
		return comision;
	}

	public void setComision(Integer comision) {
		this.comision = comision;
	}

	public Integer getiVAComision() {
		return iVAComision;
	}

	public void setiVAComision(Integer iVAComision) {
		this.iVAComision = iVAComision;
	}

	public int getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	


}
