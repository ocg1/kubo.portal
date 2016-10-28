package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ContratoRepCapCollector.class,
			name = "collectorContratoCaptacion",    
			// query = "call CLIENTERFCCAL( :par_EmpresaID (int 11),aud_Usuario(int),aud_FechaActual(DateTime),aud_DireccionIP(varchar(15)),aud_ProgramaID(varchar(50)),aud_Sucursal(int),aud_NumTransaccion(bigint)", 
			query = "call microfin.CONTRATOSREPCAPT(:par_cuentaSafi, :par_tipoContrato)",
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})


@Entity
public class ContratoRepCapCollector {
  //Field Type
  @Column(name = "KboReca")
  private String  kboReca; // varchar(29)
  
  
  @Id
  @Column(name = "CliNumeroCliente")
  private Integer cliNumeroCliente;
  
  @Column(name = "CliCuentaAhoID")
  private Integer cliCuentaAhoID;
 
  @Column(name = "KboNumeroContrato")
  private String  kboNumeroContrato;  // varchar(1)
 
  @Column(name = "KboFechaFirma")
  private  String  kboFechaFirma; // varchar(25)
  
  @Column(name = "CliNombre")
  private String cliNombre; //varchar(91)
  
  @Column(name = "CliApellidoPaterno")
  private String cliApellidoPaterno;  //varchar(45)
  
  @Column(name = "CliApellidoMaterno")
  private String cliApellidoMaterno;  //varchar(45)
  
  @Column(name = "CliNombreCompleto")
  private String cliNombreCompleto; //varchar(183)
  
  @Column(name = "CliNacionalidad")
  private String cliNacionalidad; //varchar(10)
  
  @Column(name = "CliLugarNacimiento")
  private String cliLugarNacimiento;  //varchar(67)
 
  
  @Column(name = "CliFechaNacimiento")
  private String cliFechaNacimiento;  //varchar(25)
  
  @Column(name = "CliRFC")
  private String cliRFC;  
  //varchar(13)
  @Column(name = "CliCurp")
  private String cliCurp;     // varchar(18)
  
  @Column(name = "CliMorDenRazonSocial")
  private String cliMorDenRazonSocial;  //  varchar(1)
  
  @Column(name = "CliMorNombre")
  private String cliMorNombre;   // varchar(1)
  
  @Column(name = "CliMorApoderadoLegal")
  private String cliMorApoderadoLegal;  //  varchar(1)
  
  @Column(name = "CliMorRFC")
  private String cliMorRFC;  // varchar(1)
  
  @Column(name = "CliMorEscrituraApoderadoFirma")
  private String cliMorEscrituraApoderadoFirma; // varchar(1)
  
  @Column(name = "CliMorNombreCompletoFedPub")
  private String cliMorNombreCompletoFedPub;  //varchar(1)
  
  @Column(name = "CliMorTipoFederatarioPub")
  private String cliMorTipoFederatarioPub;  //varchar(1)
 
  @Column(name = "CliMorNumero")
  private String cliMorNumero;  // varchar(1)
  
  @Column(name = "CliMorEntidadFederativa")
  private String cliMorEntidadFederativa; // varchar(1)
  
  @Column(name = "CliMorNumeroInstPublico")
  private String cliMorNumeroInstPublico; //varchar(1)
  
  @Column(name = "CliMorFechaOtorgamiento")
  private String cliMorFechaOtorgamiento;   //varchar(1)
  
  
  @Column(name = "CliMorDatoInscripcionRPC")
  private String cliMorDatoInscripcionRPC; //varchar(1)
  
  @Column(name = "CliMorFechaInscripcion")
  private String cliMorFechaInscripcion;  //varchar(1)
  
  @Column(name = "CliMorLugarInscripcion")
  private String cliMorLugarInscripcion;  //varchar(1)
  
  @Column(name = "CliCalle")
  private String cliCalle;  //varchar(60)
  
  @Column(name = "CliNumeroExterior")
  private String cliNumeroExterior; // varchar(10)
  
  @Column(name = "CliNumeroInterior")
  private String cliNumeroInterior; // varchar(20)
 
  @Column(name = "CliManzana")
  private String cliManzana; // varchar(5)
  
  @Column(name = "CliLote")
  private String cliLote;       // varchar(5)
  
  @Column(name = "CliColonia")
  private String cliColonia;  //varchar(100)
  
  @Column(name = "CliDelegacionMun")
  private String cliDelegacionMun;  //varchar(45)
 
  @Column(name = "CliCP")
  private String cliCP; //varchar(10)
  
  @Column(name = "CliEstado")
  private String cliEstado; //varchar(25)
 
  @Column(name = "CliTelefonofijo")
  private String cliTelefonofijo;  //varchar(45)
 
  @Column(name = "CliTelefonofijo2")
  private String cliTelefonofijo2;  //varchar(1)
  
  @Column(name = "CliTelefonoMovil")
  private String cliTelefonoMovil;  //varchar(45)
  
  @Column(name = "CliTelefonoMovil2")
  private String cliTelefonoMovil2; //varchar(1)
  
  @Column(name = "CliTelefonoTrabajo")
  private String cliTelefonoTrabajo;  //varchar(45)
  
  @Column(name = "CliTelefonoTrabajo2")
  private String cliTelefonoTrabajo2;    // varchar(1)
  
  @Column(name = "CliCorreo")
  private String cliCorreo; // varchar(50)
  
  @Column(name = "CliCorreo2")
  private String cliCorreo2;  //varchar(1)
  
  @Column(name = "CliFacebook")
  private String cliFacebook;   // varchar(1)
  
  @Column(name = "CliTwitter")
  private String cliTwitter;   // varchar(1)
  
  @Column(name = "CliLinkedIn")
  private String cliLinkedIn;  //varchar(1)
  
  @Column(name = "CliNombreBanco")
  private String cliNombreBanco;  //varchar(100)
  
  @Column(name = "CliNumeroCuenta")
  private String cliNumeroCuenta; //varchar(11)
  
  @Column(name = "CliCuentaClabe")
  private String cliCuentaClabe;  //varchar(18)
  
  
  @Column(name = "KuboFechaFirma")
  private String kuboFechaFirma;  //varchar(1)
  
  @Column(name = "KuboNombre")
  private String kuboNombre;  //varchar(1)
  
  @Column(name = "kuboRazonSocial")
  private String kuboRazonSocial;   //varchar(1)
  
  @Column(name = "kuboApoderadoLegal")
  private String kuboApoderadoLegal;  //varchar(1)
  
  @Column(name = "KuboGlobalTasaInteresAnualFija")
  private String kuboGlobalTasaInteresAnualFija;  //varchar(15)

  @Column(name = "KuboGlobalGATNominal")
  private String kuboGlobalGATNominal;  //varchar(16)
  
  @Column(name = "KuboGlobalGATReal")
  private String kuboGlobalGATReal; //varchar(15)
  
  @Column(name = "PendPConsultaSaldoViaInternet")
  private String pendPConsultaSaldoViaInternet; //varchar(1)
  
  @Column(name = "PendPEnvioSaldoPorCorreoElectronico")
  private String pendPEnvioSaldoPorCorreoElectronico;   //varchar(1)
  
  @Column(name = "CliTipoPersona")
  private String cliTipoPersona;  //char(1)
 
  @Column(name = "CliDireccionCompleta")
  private String cliDireccionCompleta;  //varchar(500)

public String getKboReca() {
	return kboReca;
}

public void setKboReca(String kboReca) {
	this.kboReca = kboReca;
}

public Integer getCliNumeroCliente() {
	return cliNumeroCliente;
}

public void setCliNumeroCliente(Integer cliNumeroCliente) {
	this.cliNumeroCliente = cliNumeroCliente;
}

public String getKboNumeroContrato() {
	return kboNumeroContrato;
}

public void setKboNumeroContrato(String kboNumeroContrato) {
	this.kboNumeroContrato = kboNumeroContrato;
}

public String getKboFechaFirma() {
	return kboFechaFirma;
}

public void setKboFechaFirma(String kboFechaFirma) {
	this.kboFechaFirma = kboFechaFirma;
}

public String getCliNombre() {
	return cliNombre;
}

public void setCliNombre(String cliNombre) {
	this.cliNombre = cliNombre;
}

public String getCliApellidoPaterno() {
	return cliApellidoPaterno;
}

public void setCliApellidoPaterno(String cliApellidoPaterno) {
	this.cliApellidoPaterno = cliApellidoPaterno;
}

public String getCliApellidoMaterno() {
	return cliApellidoMaterno;
}

public void setCliApellidoMaterno(String cliApellidoMaterno) {
	this.cliApellidoMaterno = cliApellidoMaterno;
}

public String getCliNombreCompleto() {
	return cliNombreCompleto;
}

public void setCliNombreCompleto(String cliNombreCompleto) {
	this.cliNombreCompleto = cliNombreCompleto;
}

public String getCliNacionalidad() {
	return cliNacionalidad;
}

public void setCliNacionalidad(String cliNacionalidad) {
	this.cliNacionalidad = cliNacionalidad;
}

public String getCliLugarNacimiento() {
	return cliLugarNacimiento;
}

public void setCliLugarNacimiento(String cliLugarNacimiento) {
	this.cliLugarNacimiento = cliLugarNacimiento;
}

public String getCliFechaNacimiento() {
	return cliFechaNacimiento;
}

public void setCliFechaNacimiento(String cliFechaNacimiento) {
	this.cliFechaNacimiento = cliFechaNacimiento;
}

public String getCliRFC() {
	return cliRFC;
}

public void setCliRFC(String cliRFC) {
	this.cliRFC = cliRFC;
}

public String getCliCurp() {
	return cliCurp;
}

public void setCliCurp(String cliCurp) {
	this.cliCurp = cliCurp;
}

public String getCliMorDenRazonSocial() {
	return cliMorDenRazonSocial;
}

public void setCliMorDenRazonSocial(String cliMorDenRazonSocial) {
	this.cliMorDenRazonSocial = cliMorDenRazonSocial;
}

public String getCliMorNombre() {
	return cliMorNombre;
}

public void setCliMorNombre(String cliMorNombre) {
	this.cliMorNombre = cliMorNombre;
}

public String getCliMorApoderadoLegal() {
	return cliMorApoderadoLegal;
}

public void setCliMorApoderadoLegal(String cliMorApoderadoLegal) {
	this.cliMorApoderadoLegal = cliMorApoderadoLegal;
}

public String getCliMorRFC() {
	return cliMorRFC;
}

public void setCliMorRFC(String cliMorRFC) {
	this.cliMorRFC = cliMorRFC;
}

public String getCliMorEscrituraApoderadoFirma() {
	return cliMorEscrituraApoderadoFirma;
}

public void setCliMorEscrituraApoderadoFirma(
		String cliMorEscrituraApoderadoFirma) {
	this.cliMorEscrituraApoderadoFirma = cliMorEscrituraApoderadoFirma;
}

public String getCliMorNombreCompletoFedPub() {
	return cliMorNombreCompletoFedPub;
}

public void setCliMorNombreCompletoFedPub(String cliMorNombreCompletoFedPub) {
	this.cliMorNombreCompletoFedPub = cliMorNombreCompletoFedPub;
}

public String getCliMorTipoFederatarioPub() {
	return cliMorTipoFederatarioPub;
}

public void setCliMorTipoFederatarioPub(String cliMorTipoFederatarioPub) {
	this.cliMorTipoFederatarioPub = cliMorTipoFederatarioPub;
}

public String getCliMorNumero() {
	return cliMorNumero;
}

public void setCliMorNumero(String cliMorNumero) {
	this.cliMorNumero = cliMorNumero;
}

public String getCliMorEntidadFederativa() {
	return cliMorEntidadFederativa;
}

public void setCliMorEntidadFederativa(String cliMorEntidadFederativa) {
	this.cliMorEntidadFederativa = cliMorEntidadFederativa;
}

public String getCliMorNumeroInstPublico() {
	return cliMorNumeroInstPublico;
}

public void setCliMorNumeroInstPublico(String cliMorNumeroInstPublico) {
	this.cliMorNumeroInstPublico = cliMorNumeroInstPublico;
}

public String getCliMorFechaOtorgamiento() {
	return cliMorFechaOtorgamiento;
}

public void setCliMorFechaOtorgamiento(String cliMorFechaOtorgamiento) {
	this.cliMorFechaOtorgamiento = cliMorFechaOtorgamiento;
}

public String getCliMorDatoInscripcionRPC() {
	return cliMorDatoInscripcionRPC;
}

public void setCliMorDatoInscripcionRPC(String cliMorDatoInscripcionRPC) {
	this.cliMorDatoInscripcionRPC = cliMorDatoInscripcionRPC;
}

public String getCliMorFechaInscripcion() {
	return cliMorFechaInscripcion;
}

public void setCliMorFechaInscripcion(String cliMorFechaInscripcion) {
	this.cliMorFechaInscripcion = cliMorFechaInscripcion;
}

public String getCliMorLugarInscripcion() {
	return cliMorLugarInscripcion;
}

public void setCliMorLugarInscripcion(String cliMorLugarInscripcion) {
	this.cliMorLugarInscripcion = cliMorLugarInscripcion;
}

public String getCliCalle() {
	return cliCalle;
}

public void setCliCalle(String cliCalle) {
	this.cliCalle = cliCalle;
}

public String getCliNumeroExterior() {
	return cliNumeroExterior;
}

public void setCliNumeroExterior(String cliNumeroExterior) {
	this.cliNumeroExterior = cliNumeroExterior;
}

public String getCliNumeroInterior() {
	return cliNumeroInterior;
}

public void setCliNumeroInterior(String cliNumeroInterior) {
	this.cliNumeroInterior = cliNumeroInterior;
}

public String getCliManzana() {
	return cliManzana;
}

public void setCliManzana(String cliManzana) {
	this.cliManzana = cliManzana;
}

public String getCliLote() {
	return cliLote;
}

public void setCliLote(String cliLote) {
	this.cliLote = cliLote;
}

public String getCliColonia() {
	return cliColonia;
}

public void setCliColonia(String cliColonia) {
	this.cliColonia = cliColonia;
}

public String getCliDelegacionMun() {
	return cliDelegacionMun;
}

public void setCliDelegacionMun(String cliDelegacionMun) {
	this.cliDelegacionMun = cliDelegacionMun;
}

public String getCliCP() {
	return cliCP;
}

public void setCliCP(String cliCP) {
	this.cliCP = cliCP;
}

public String getCliEstado() {
	return cliEstado;
}

public void setCliEstado(String cliEstado) {
	this.cliEstado = cliEstado;
}

public String getCliTelefonofijo() {
	return cliTelefonofijo;
}

public void setCliTelefonofijo(String cliTelefonofijo) {
	this.cliTelefonofijo = cliTelefonofijo;
}

public String getCliTelefonofijo2() {
	return cliTelefonofijo2;
}

public void setCliTelefonofijo2(String cliTelefonofijo2) {
	this.cliTelefonofijo2 = cliTelefonofijo2;
}

public String getCliTelefonoMovil() {
	return cliTelefonoMovil;
}

public void setCliTelefonoMovil(String cliTelefonoMovil) {
	this.cliTelefonoMovil = cliTelefonoMovil;
}

public String getCliTelefonoMovil2() {
	return cliTelefonoMovil2;
}

public void setCliTelefonoMovil2(String cliTelefonoMovil2) {
	this.cliTelefonoMovil2 = cliTelefonoMovil2;
}

public String getCliTelefonoTrabajo() {
	return cliTelefonoTrabajo;
}

public void setCliTelefonoTrabajo(String cliTelefonoTrabajo) {
	this.cliTelefonoTrabajo = cliTelefonoTrabajo;
}

public String getCliTelefonoTrabajo2() {
	return cliTelefonoTrabajo2;
}

public void setCliTelefonoTrabajo2(String cliTelefonoTrabajo2) {
	this.cliTelefonoTrabajo2 = cliTelefonoTrabajo2;
}

public String getCliCorreo() {
	return cliCorreo;
}

public void setCliCorreo(String cliCorreo) {
	this.cliCorreo = cliCorreo;
}

public String getCliCorreo2() {
	return cliCorreo2;
}

public void setCliCorreo2(String cliCorreo2) {
	this.cliCorreo2 = cliCorreo2;
}

public String getCliFacebook() {
	return cliFacebook;
}

public void setCliFacebook(String cliFacebook) {
	this.cliFacebook = cliFacebook;
}

public String getCliTwitter() {
	return cliTwitter;
}

public void setCliTwitter(String cliTwitter) {
	this.cliTwitter = cliTwitter;
}

public String getCliLinkedIn() {
	return cliLinkedIn;
}

public void setCliLinkedIn(String cliLinkedIn) {
	this.cliLinkedIn = cliLinkedIn;
}

public String getCliNombreBanco() {
	return cliNombreBanco;
}

public void setCliNombreBanco(String cliNombreBanco) {
	this.cliNombreBanco = cliNombreBanco;
}

public String getCliNumeroCuenta() {
	return cliNumeroCuenta;
}

public void setCliNumeroCuenta(String cliNumeroCuenta) {
	this.cliNumeroCuenta = cliNumeroCuenta;
}

public String getCliCuentaClabe() {
	return cliCuentaClabe;
}

public void setCliCuentaClabe(String cliCuentaClabe) {
	this.cliCuentaClabe = cliCuentaClabe;
}
public String getKuboFechaFirma() {
	return kuboFechaFirma;
}

public void setKuboFechaFirma(String kuboFechaFirma) {
	this.kuboFechaFirma = kuboFechaFirma;
}

public String getKuboNombre() {
	return kuboNombre;
}

public void setKuboNombre(String kuboNombre) {
	this.kuboNombre = kuboNombre;
}

public String getKuboRaz0nSocial() {
	return kuboRazonSocial;
}

public void setKuboRaz0nSocial(String kuboRaz0nSocial) {
	this.kuboRazonSocial = kuboRaz0nSocial;
}

public String getKuboApoderadoLegal() {
	return kuboApoderadoLegal;
}

public void setKuboApoderadoLegal(String kuboApoderadoLegal) {
	this.kuboApoderadoLegal = kuboApoderadoLegal;
}

public String getKuboGlobalTasaInteresAnualFija() {
	return kuboGlobalTasaInteresAnualFija;
}

public void setKuboGlobalTasaInteresAnualFija(
		String kuboGlobalTasaInteresAnualFija) {
	this.kuboGlobalTasaInteresAnualFija = kuboGlobalTasaInteresAnualFija;
}

public String getKuboGlobalGATNominal() {
	return kuboGlobalGATNominal;
}

public void setKuboGlobalGATNominal(String kuboGlobalGATNominal) {
	this.kuboGlobalGATNominal = kuboGlobalGATNominal;
}

public String getKuboGlobalGATReal() {
	return kuboGlobalGATReal;
}

public void setKuboGlobalGATReal(String kuboGlobalGATReal) {
	this.kuboGlobalGATReal = kuboGlobalGATReal;
}

public String getPendPConsultaSaldoViaInternet() {
	return pendPConsultaSaldoViaInternet;
}

public void setPendPConsultaSaldoViaInternet(
		String pendPConsultaSaldoViaInternet) {
	this.pendPConsultaSaldoViaInternet = pendPConsultaSaldoViaInternet;
}

public String getPendPEnvioSaldoPorCorreoElectronico() {
	return pendPEnvioSaldoPorCorreoElectronico;
}

public void setPendPEnvioSaldoPorCorreoElectronico(
		String pendPEnvioSaldoPorCorreoElectronico) {
	this.pendPEnvioSaldoPorCorreoElectronico = pendPEnvioSaldoPorCorreoElectronico;
}

public String getCliTipoPersona() {
	return cliTipoPersona;
}

public void setCliTipoPersona(String cliTipoPersona) {
	this.cliTipoPersona = cliTipoPersona;
}

public String getCliDireccionCompleta() {
	return cliDireccionCompleta;
}

public void setCliDireccionCompleta(String cliDireccionCompleta) {
	this.cliDireccionCompleta = cliDireccionCompleta;
}

public Integer getCliCuentaAhoID() {
	return cliCuentaAhoID;
}

public void setCliCuentaAhoID(Integer cliCuentaAhoID) {
	this.cliCuentaAhoID = cliCuentaAhoID;
}

public String getKuboRazonSocial() {
	return kuboRazonSocial;
}

public void setKuboRazonSocial(String kuboRazonSocial) {
	this.kuboRazonSocial = kuboRazonSocial;
}

}