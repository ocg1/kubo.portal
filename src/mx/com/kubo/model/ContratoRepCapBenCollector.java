package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ContratoRepCapBenCollector.class,
			name = "collectorContratoCaptBeneficiaries",    
			query = "call microfin.CONTRATOSREPCAPT(:par_cuentaSafi, :par_tipoContrato)",
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	)
})


@Entity
public class ContratoRepCapBenCollector {

	@Id
	@Column(name="BenNombre")
	private String benNombre;
	
	@Column(name="BenApellidoPaterno")
	private String benApellidoPaterno;
	
	@Column(name="BenApellidoMaterno")
	private String benApellidoMaterno;
	
	@Column(name="BenFechaNacimiento")
	private String benFechaNacimiento;
	
	@Column(name="BenPorcentaje")
	private String benPorcentaje;
	
	@Column(name="BenCalle")
	private String benCalle;
	
	@Column(name="BenNumeroExt")
	private String benNumeroExt;
	
	@Column(name="BenNumeroInt")
	private String benNumeroInt;
	
	@Column(name="BenManzana")
	private String benManzana;
	
	@Column(name="BenLote")
	private String benLote;
	
	@Column(name="BenColonia")
	private String benColonia;
	
	@Column(name="BenDelegacionMunicipio")
	private String benDelegacionMunicipio;
	
	@Column(name="BenCodigoPostal")
	private String benCodigoPostal;
	
	@Column(name="BenEstado")
	private String benEstado;
	
	@Column(name="BenTelefonoFijo")
	private String benTelefonoFijo;
	
	@Column(name="BenTelefonoMovil")
	private String benTelefonoMovil;
	
	@Column(name="BenCorreo")
	private String benCorreo;

	public String getBenNombre() {
		return benNombre;
	}

	public void setBenNombre(String benNombre) {
		this.benNombre = benNombre;
	}

	public String getBenApellidoPaterno() {
		return benApellidoPaterno;
	}

	public void setBenApellidoPaterno(String benApellidoPaterno) {
		this.benApellidoPaterno = benApellidoPaterno;
	}

	public String getBenApellidoMaterno() {
		return benApellidoMaterno;
	}

	public void setBenApellidoMaterno(String benApellidoMaterno) {
		this.benApellidoMaterno = benApellidoMaterno;
	}

	public String getBenFechaNacimiento() {
		return benFechaNacimiento;
	}

	public void setBenFechaNacimiento(String benFechaNacimiento) {
		this.benFechaNacimiento = benFechaNacimiento;
	}

	public String getBenPorcentaje() {
		return benPorcentaje;
	}

	public void setBenPorcentaje(String benPorcentaje) {
		this.benPorcentaje = benPorcentaje;
	}

	public String getBenCalle() {
		return benCalle;
	}

	public void setBenCalle(String benCalle) {
		this.benCalle = benCalle;
	}

	public String getBenNumeroExt() {
		return benNumeroExt;
	}

	public void setBenNumeroExt(String benNumeroExt) {
		this.benNumeroExt = benNumeroExt;
	}

	public String getBenNumeroInt() {
		return benNumeroInt;
	}

	public void setBenNumeroInt(String benNumeroInt) {
		this.benNumeroInt = benNumeroInt;
	}

	public String getBenManzana() {
		return benManzana;
	}

	public void setBenManzana(String benManzana) {
		this.benManzana = benManzana;
	}

	public String getBenLote() {
		return benLote;
	}

	public void setBenLote(String benLote) {
		this.benLote = benLote;
	}

	public String getBenColonia() {
		return benColonia;
	}

	public void setBenColonia(String benColonia) {
		this.benColonia = benColonia;
	}

	public String getBenDelegacionMunicipio() {
		return benDelegacionMunicipio;
	}

	public void setBenDelegacionMunicipio(String benDelegacionMunicipio) {
		this.benDelegacionMunicipio = benDelegacionMunicipio;
	}

	public String getBenCodigoPostal() {
		return benCodigoPostal;
	}

	public void setBenCodigoPostal(String benCodigoPostal) {
		this.benCodigoPostal = benCodigoPostal;
	}

	public String getBenEstado() {
		return benEstado;
	}

	public void setBenEstado(String benEstado) {
		this.benEstado = benEstado;
	}

	public String getBenTelefonoFijo() {
		return benTelefonoFijo;
	}

	public void setBenTelefonoFijo(String benTelefonoFijo) {
		this.benTelefonoFijo = benTelefonoFijo;
	}

	public String getBenTelefonoMovil() {
		return benTelefonoMovil;
	}

	public void setBenTelefonoMovil(String benTelefonoMovil) {
		this.benTelefonoMovil = benTelefonoMovil;
	}

	public String getBenCorreo() {
		return benCorreo;
	}

	public void setBenCorreo(String benCorreo) {
		this.benCorreo = benCorreo;
	}
	
}
