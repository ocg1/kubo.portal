package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gn_prospector_massive")
public class MassiveProspector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer massive_id;
	@Column
	private Date fecha_carga;
	@Column
	private String partner_id;
	@Column
	private String primer_nombre; // VARCHAR(100) NULL,
	@Column
	private String segundo_nombre; // VARCHAR(100) NULL,
	@Column
	private String apellido_paterno; // VARCHAR(100) NULL,
	@Column
	private String apellido_materno; // VARCHAR(100) NULL,
	@Column
	private Date fecha_nacimiento; // DATETIME NULL,
	@Column
	private String rfc; // VARCHAR(13) NULL,
	@Column
	private String calle; // VARCHAR(350) NULL,
	@Column
	private String numero_exterior; // VARCHAR(50) NULL,
	@Column
	private String colonia; // VARCHAR(350) NULL,
	@Column
	private String ciudad; // VARCHAR(350) NULL,
	@Column
	private String municipio; // VARCHAR(350) NULL,
	@Column
	private String estado; // VARCHAR(350) NULL,
	@Column
	private String codigo_postal; // VARCHAR(10) NULL,
	@Column
	private String archivo_carga; // VARCHAR(350) NULL,
	@Column
	private String archivo_exito; // VARCHAR(350) NULL,
	@Column
	private String archivo_error; // VARCHAR(350) NULL,
	@Column
	private String prospector; // VARCHAR(1) NULL,
	@Column
	private String valid_prospector;
	@Column
	private String mensaje; // VARCHAR(500) NULL,
	@Column
	private String bc_score; // VARCHAR(5) NULL);
	@Column
	private String mx_solicitud_buro; // VARCHAR(5) NULL);
	
	
	public Integer getMassive_id() {
		return massive_id;
	}
	public void setMassive_id(Integer massive_id) {
		this.massive_id = massive_id;
	}
	public String getPrimer_nombre() {
		return primer_nombre;
	}
	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}
	public String getSegundo_nombre() {
		return segundo_nombre;
	}
	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero_exterior() {
		return numero_exterior;
	}
	public void setNumero_exterior(String numero_exterior) {
		this.numero_exterior = numero_exterior;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getProspector() {
		return prospector;
	}
	public void setProspector(String prospector) {
		this.prospector = prospector;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getBc_score() {
		return bc_score;
	}
	public void setBc_score(String bc_score) {
		this.bc_score = bc_score;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public Date getFecha_carga() {
		return fecha_carga;
	}
	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getArchivo_carga() {
		return archivo_carga;
	}
	public void setArchivo_carga(String archivo_carga) {
		this.archivo_carga = archivo_carga;
	}
	public String getArchivo_exito() {
		return archivo_exito;
	}
	public void setArchivo_exito(String archivo_exito) {
		this.archivo_exito = archivo_exito;
	}
	public String getArchivo_error() {
		return archivo_error;
	}
	public void setArchivo_error(String archivo_error) {
		this.archivo_error = archivo_error;
	}
	public String getValid_prospector() {
		return valid_prospector;
	}
	public void setValid_prospector(String valid_prospector) {
		this.valid_prospector = valid_prospector;
	}

}
