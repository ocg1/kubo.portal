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

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ContratoCreditoCollector.class,
			name = "collectorContratoCredito",    
			 
			// query = "	call microfin.CONTRATOSREP(100005328,86.1645,1,1,1,'2016-02-08 19:11:00','192.0.0.0','kubofinanciero.generaContratos',1,0)
			
			query = "call microfin.CONTRATOSREP(:par_CreditoID,:par_CAT,:par_TipoContrat,:par_EmpresaID,:aud_Usuario,:aud_FechaActual,:aud_DireccionIP,:aud_ProgramaID,:aud_Sucursal,:aud_NumTransaccion)",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class ContratoCreditoCollector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CreditoID")
	private BigInteger creditoID; //			int(11);
	@Column(name="VigenciaContrato")
	private String  vigenciaContrato	; //	varchar(30);
	@Column(name="PlazoCredito")
	private String  plazoCredito	; //		varchar(30);
	@Column(name="FechaCorte")
	private String  fechaCorte	; //			varchar(20);
	@Column(name="RFCOficial")
	private String  rFCOficial	; //			varchar(30);
	@Column(name="Nacionalidad")
	private String  nacionalidad	; //		varchar(20);
	@Column(name="Correo")
	private String  correo		; //			varchar(100);
	@Column(name="Telefono")
	private String  telefono 	; //			varchar(20);
	@Column(name="TelefonoCelular")
	private String  telefonoCelular	; // 	varchar(20);
	@Column(name="NombreCorto")
	private String  nombreCorto	; // 		varchar(100);
	@Column(name="CuentaCLABE")
	private String  cuentaCLABE 	; //		varchar(20);
	@Column(name="DireccionCompleta")
	private String direccionCompleta	; // 	varchar(200);
	@Column(name="FolioConBuro")
	private String  folioConBuro	; // 		varchar(15);
	@Column(name="FechaConBuro")
	private String  fechaConBuro	; //		varchar(15);
	@Column(name="NombreRepresentante")
	private String  nombreRepresentante	; // varchar(100);
	@Column(name="DirecCompleta")
	private String  direcCompleta	; // 		varchar(200);
	@Column(name="RegistroRECA")
	private String  registroRECA 	; //		varchar(100);
	@Column(name="NombreComercial")
	private String  nombreComercial	; // 	varchar(100);
	@Column(name="TipoCredito")
	private String  tipoCredito 		; //	varchar(20);
	@Column(name="CompaniaAsegura")
	private String  companiaAsegura 	; //	varchar(100);
	@Column(name="PolizaSeguro")
	private String  polizaSeguro 		; //	varchar(20);
	@Column(name="Cobertura")
	private String  cobertura  			; //	varchar(200); 
	@Column(name="MontoPrima")
	private Double  montoPrima	; // 		decimal(12,2);
	@Column(name="FechaVigencia")
	private Date   fechaVigencia	; // 		date;
	@Column(name="FactorMora")
	private Double  factorMora	; //  		decimal(12,2);
	@Column(name="MontoTotCredito")
	private Double  montoTotCredito		; //decimal(14,2);
	@Column(name="TasaFija")
	private Double  tasaFija	; // 			decimal(12,2);
	@Column(name="Var_Dia")
	private Integer dia		; //				int(2);
	@Column(name="Var_Anio")
	private Integer anio	; //				int(4);
	@Column(name="Var_MesDescrip")
	private String  mesDescrip			; //	varchar(12);
	@Column(name="Var_NombreEstado")
	private String 	 nombreEstado	; //		varchar(200);
	@Column(name="Var_NombreMunicipio")
	private String  nombreMunicipio		; //	varchar(300);
	@Column(name="NombreCliente")
	private String  nombreCliente	; //		varchar(200);
	@Column(name="CAT")
	private Double  cat	; //				float;
	@Column(name="FechaFirmaContrato")
	private String  fechaFirmaContrato	; //	varchar(10);
	@Column(name="FechaVigenciaSeguro")
	private String  vigenciaSeguro	; //		varchar(10);
	@Column(name="TasaIntMorFija")
	private String  tasaIntMorFija		; //	decimal(12,2);
	@Column(name="NombreEstadoFirma")
	private String  nombreEstadoFirma	; //	varchar(200);
	@Column(name="NombreMunicipioFirma")
	private String  nombreMunicipioFirma	; // varchar(200);
	@Column(name="Nombre")
	private String  nombre			; //		varchar(200);
	@Column(name="ApellidoPaterno")
	private String  apPaterno			; //	varchar(200);
	@Column(name="ApellidoMaterno")
	private String  apMaterno			; //	varchar(200);
	@Column(name="FechaNacimiento")
	private String  fechaNacimiento	 	; //	varchar(200);
	@Column(name="Calle")
	private String  calle				; //	varchar(200);
	@Column(name="NumeroExterior")
	private String  numeroExterior		; //	varchar(200);
	@Column(name="NumeroInterior")
	private String  numeroInterior		; //	varchar(200);
	@Column(name="Manzana")
	private String  manzana				; //	varchar(200);
	@Column(name="Lote")
	private String  lote				; //	varchar(200);
	@Column(name="Colonia")
	private String  colonia				; //	varchar(200);
	@Column(name="CP")
	private String  cP					; //	varchar(200);
	@Column(name="Ocupacion")
	private String  ocupacion			; //	varchar(200);
	@Column(name="KuboTelefonoFijo")
	private String  kuboTelefonoFijo 	; //	varchar(200);
	@Column(name="KuboTelefonoMovil")
	private String  kuboTelefonoMovil 	; //	varchar(200);
	@Column(name="KuboTelefonoTrabajo")
	private String  kuboTelefonoTrabajo; //		varchar(200);
	@Column(name="LugarNacimiento")
	private String  lugarNacimiento		; //	varchar(200);
	
	public BigInteger getCreditoID() {
		return creditoID;
	}
	public void setCreditoID(BigInteger creditoID) {
		this.creditoID = creditoID;
	}
	public String getVigenciaContrato() {
		return vigenciaContrato;
	}
	public void setVigenciaContrato(String vigenciaContrato) {
		this.vigenciaContrato = vigenciaContrato;
	}
	public String getPlazoCredito() {
		return plazoCredito;
	}
	public void setPlazoCredito(String plazoCredito) {
		this.plazoCredito = plazoCredito;
	}
	public String getFechaCorte() {
		return fechaCorte;
	}
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}
	public String getrFCOficial() {
		return rFCOficial;
	}
	public void setrFCOficial(String rFCOficial) {
		this.rFCOficial = rFCOficial;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getCuentaCLABE() {
		return cuentaCLABE;
	}
	public void setCuentaCLABE(String cuentaCLABE) {
		this.cuentaCLABE = cuentaCLABE;
	}
	public String getDireccionCompleta() {
		return direccionCompleta;
	}
	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}
	public String getFolioConBuro() {
		return folioConBuro;
	}
	public void setFolioConBuro(String folioConBuro) {
		this.folioConBuro = folioConBuro;
	}
	public String getFechaConBuro() {
		return fechaConBuro;
	}
	public void setFechaConBuro(String fechaConBuro) {
		this.fechaConBuro = fechaConBuro;
	}
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}
	public String getDirecCompleta() {
		return direcCompleta;
	}
	public void setDirecCompleta(String direcCompleta) {
		this.direcCompleta = direcCompleta;
	}
	public String getRegistroRECA() {
		return registroRECA;
	}
	public void setRegistroRECA(String registroRECA) {
		this.registroRECA = registroRECA;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getCompaniaAsegura() {
		return companiaAsegura;
	}
	public void setCompaniaAsegura(String companiaAsegura) {
		this.companiaAsegura = companiaAsegura;
	}
	public String getPolizaSeguro() {
		return polizaSeguro;
	}
	public void setPolizaSeguro(String polizaSeguro) {
		this.polizaSeguro = polizaSeguro;
	}
	public String getCobertura() {
		return cobertura;
	}
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}
	public Double getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(Double montoPrima) {
		this.montoPrima = montoPrima;
	}
	public Date getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	public Double getFactorMora() {
		return factorMora;
	}
	public void setFactorMora(Double factorMora) {
		this.factorMora = factorMora;
	}
	public Double getMontoTotCredito() {
		return montoTotCredito;
	}
	public void setMontoTotCredito(Double montoTotCredito) {
		this.montoTotCredito = montoTotCredito;
	}
	public Double getTasaFija() {
		return tasaFija;
	}
	public void setTasaFija(Double tasaFija) {
		this.tasaFija = tasaFija;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getMesDescrip() {
		return mesDescrip;
	}
	public void setMesDescrip(String mesDescrip) {
		this.mesDescrip = mesDescrip;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	public String getFechaFirmaContrato() {
		return fechaFirmaContrato;
	}
	public void setFechaFirmaContrato(String fechaFirmaContrato) {
		this.fechaFirmaContrato = fechaFirmaContrato;
	}
	public String getVigenciaSeguro() {
		return vigenciaSeguro;
	}
	public void setVigenciaSeguro(String vigenciaSeguro) {
		this.vigenciaSeguro = vigenciaSeguro;
	}
	public String getTasaIntMorFija() {
		return tasaIntMorFija;
	}
	public void setTasaIntMorFija(String tasaIntMorFija) {
		this.tasaIntMorFija = tasaIntMorFija;
	}
	public String getNombreEstadoFirma() {
		return nombreEstadoFirma;
	}
	public void setNombreEstadoFirma(String nombreEstadoFirma) {
		this.nombreEstadoFirma = nombreEstadoFirma;
	}
	public String getNombreMunicipioFirma() {
		return nombreMunicipioFirma;
	}
	public void setNombreMunicipioFirma(String nombreMunicipioFirma) {
		this.nombreMunicipioFirma = nombreMunicipioFirma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public String getManzana() {
		return manzana;
	}
	public void setManzana(String manzana) {
		this.manzana = manzana;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getcP() {
		return cP;
	}
	public void setcP(String cP) {
		this.cP = cP;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getKuboTelefonoFijo() {
		return kuboTelefonoFijo;
	}
	public void setKuboTelefonoFijo(String kuboTelefonoFijo) {
		this.kuboTelefonoFijo = kuboTelefonoFijo;
	}
	public String getKuboTelefonoMovil() {
		return kuboTelefonoMovil;
	}
	public void setKuboTelefonoMovil(String kuboTelefonoMovil) {
		this.kuboTelefonoMovil = kuboTelefonoMovil;
	}
	public String getKuboTelefonoTrabajo() {
		return kuboTelefonoTrabajo;
	}
	public void setKuboTelefonoTrabajo(String kuboTelefonoTrabajo) {
		this.kuboTelefonoTrabajo = kuboTelefonoTrabajo;
	}
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	public Double getCat() {
		return cat;
	}
	public void setCat(Double cat) {
		this.cat = cat;
	}
	
}
