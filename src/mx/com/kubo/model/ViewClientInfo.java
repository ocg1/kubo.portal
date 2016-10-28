package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;




@NamedNativeQuery(name = "findViewClientInfo", query = "select * from view_client_info where prospectus_id = :prospect and company_id = :company", resultClass = ViewClientInfo.class)

@Entity
public class ViewClientInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column
	private Integer prospectus_id;//	"int(10) unsigned"
	@Column
	private Integer company_id;//	"tinyint(3) unsigned"
	@Column
	private Integer sucursalOrigen;//	int(1)
	@Column
	private String titulo;//	varchar(1)
	@Column
	private Date fecha_nacimiento;//	date
	@Column
	private String lugar_nacimiento;//	varchar(25)
	@Column
	private Integer estadoID;//	"smallint(5) unsigned"
	@Column
	private String nacion;//	varchar(1)
	@Column
	private Integer pais_de_residencia;//	"smallint(5) unsigned"
	@Column
	private String sexo;//	varchar(1)
	@Column
	private String curp;//	varchar(18)
	@Column
	private String RFC;//	varchar(13)
	@Column
	private String estado_civil;//	varchar(1)
	@Column
	private String telefono_celular;//	varchar(45)
	@Column
	private String telefono_casa;//	varchar(45)
	@Column
	private String correo;//	varchar(45)
	@Column
	private String razon_social;//	varchar(1)
	@Column
	private Integer tipoSociedadID;//	"tinyint(2) unsigned"
	@Column
	private String RFCpm;//	varchar(13)
	@Column
	private String grupo_empresarial;//	varchar(1)
	@Column
	private String fax;//	varchar(1)
	@Column
	private Integer ocupacionID;//	"int(10) unsigned"
	@Column
	private String puesto;//	varchar(45)
	@Column
	private String lugar_trabajo;//	varchar(1)
	@Column
	private Integer antiguedad_trabajo;//	"int(10) unsigned"
	@Column
	private String telefono_trabajo;//	varchar(45)
	@Column
	private String clasificacion;//	varchar(1)
	@Column
	private String motivo_apertura;//	varchar(1)
	@Column
	private String pagaISR;//	varchar(1)
	@Column
	private String pagaIVA;//	varchar(1)
	@Column
	private String pagaIDE;//	varchar(1)
	@Column
	private String nivelRiesgo;//	varchar(1)
	@Column
	private Integer sector_general;//	"smallint(5) unsigned"
	@Column
	private String actividadBancoMX;//	varchar(15)
	@Column
	private Integer actividadINEGI;//	"int(10) unsigned"
	@Column
	private Integer sectorEconomico;//	"int(10) unsigned"
	@Column
	private String promotor_inicial;//	varchar(1)
	@Column
	private String promotor_actual;//	varchar(1)
	@Column
	private String nombre_conyuge;//	varchar(1)
	@Column
	private String rfc_conyuge;//	varchar(1)
	@Column
	private Integer prospecto_id;//	"int(10) unsigned"
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getSucursalOrigen() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(Integer sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getLugar_nacimiento() {
		return lugar_nacimiento;
	}
	public void setLugar_nacimiento(String lugar_nacimiento) {
		this.lugar_nacimiento = lugar_nacimiento;
	}
	public Integer getEstadoID() {
		return estadoID;
	}
	public void setEstadoID(Integer estadoID) {
		this.estadoID = estadoID;
	}
	public String getNacion() {
		return nacion;
	}
	public void setNacion(String nacion) {
		this.nacion = nacion;
	}
	public Integer getPais_de_residencia() {
		return pais_de_residencia;
	}
	public void setPais_de_residencia(Integer pais_de_residencia) {
		this.pais_de_residencia = pais_de_residencia;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public String getEstado_civil() {
		return estado_civil;
	}
	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}
	public String getTelefono_celular() {
		return telefono_celular;
	}
	public void setTelefono_celular(String telefono_celular) {
		this.telefono_celular = telefono_celular;
	}
	public String getTelefono_casa() {
		return telefono_casa;
	}
	public void setTelefono_casa(String telefono_casa) {
		this.telefono_casa = telefono_casa;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public Integer getTipoSociedadID() {
		return tipoSociedadID;
	}
	public void setTipoSociedadID(Integer tipoSociedadID) {
		this.tipoSociedadID = tipoSociedadID;
	}
	public String getRFCpm() {
		return RFCpm;
	}
	public void setRFCpm(String rFCpm) {
		RFCpm = rFCpm;
	}
	public String getGrupo_empresarial() {
		return grupo_empresarial;
	}
	public void setGrupo_empresarial(String grupo_empresarial) {
		this.grupo_empresarial = grupo_empresarial;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Integer getOcupacionID() {
		return ocupacionID;
	}
	public void setOcupacionID(Integer ocupacionID) {
		this.ocupacionID = ocupacionID;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getLugar_trabajo() {
		return lugar_trabajo;
	}
	public void setLugar_trabajo(String lugar_trabajo) {
		this.lugar_trabajo = lugar_trabajo;
	}
	public Integer getAntiguedad_trabajo() {
		return antiguedad_trabajo;
	}
	public void setAntiguedad_trabajo(Integer antiguedad_trabajo) {
		this.antiguedad_trabajo = antiguedad_trabajo;
	}
	public String getTelefono_trabajo() {
		return telefono_trabajo;
	}
	public void setTelefono_trabajo(String telefono_trabajo) {
		this.telefono_trabajo = telefono_trabajo;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getMotivo_apertura() {
		return motivo_apertura;
	}
	public void setMotivo_apertura(String motivo_apertura) {
		this.motivo_apertura = motivo_apertura;
	}
	public String getPagaISR() {
		return pagaISR;
	}
	public void setPagaISR(String pagaISR) {
		this.pagaISR = pagaISR;
	}
	public String getPagaIVA() {
		return pagaIVA;
	}
	public void setPagaIVA(String pagaIVA) {
		this.pagaIVA = pagaIVA;
	}
	public String getPagaIDE() {
		return pagaIDE;
	}
	public void setPagaIDE(String pagaIDE) {
		this.pagaIDE = pagaIDE;
	}
	public String getNivelRiesgo() {
		return nivelRiesgo;
	}
	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}
	public Integer getSector_general() {
		return sector_general;
	}
	public void setSector_general(Integer sector_general) {
		this.sector_general = sector_general;
	}
	public String getActividadBancoMX() {
		return actividadBancoMX;
	}
	public void setActividadBancoMX(String actividadBancoMX) {
		this.actividadBancoMX = actividadBancoMX;
	}
	public Integer getActividadINEGI() {
		return actividadINEGI;
	}
	public void setActividadINEGI(Integer actividadINEGI) {
		this.actividadINEGI = actividadINEGI;
	}
	public Integer getSectorEconomico() {
		return sectorEconomico;
	}
	public void setSectorEconomico(Integer sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}
	public String getPromotor_inicial() {
		return promotor_inicial;
	}
	public void setPromotor_inicial(String promotor_inicial) {
		this.promotor_inicial = promotor_inicial;
	}
	public String getPromotor_actual() {
		return promotor_actual;
	}
	public void setPromotor_actual(String promotor_actual) {
		this.promotor_actual = promotor_actual;
	}
	public String getNombre_conyuge() {
		return nombre_conyuge;
	}
	public void setNombre_conyuge(String nombre_conyuge) {
		this.nombre_conyuge = nombre_conyuge;
	}
	public String getRfc_conyuge() {
		return rfc_conyuge;
	}
	public void setRfc_conyuge(String rfc_conyuge) {
		this.rfc_conyuge = rfc_conyuge;
	}
	public Integer getProspecto_id() {
		return prospecto_id;
	}
	public void setProspecto_id(Integer prospecto_id) {
		this.prospecto_id = prospecto_id;
	}

	
}
