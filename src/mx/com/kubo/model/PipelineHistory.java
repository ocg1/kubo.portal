package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dw_pipeline_history")
public class PipelineHistory implements Serializable{

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private Integer prospectus_id; 		//	prospectus_id	int(10) unsigned
	@Column
	private Integer proyect_loan_id;	//	prospectus_id	int(10) unsigned
	
	@Column
	private java.sql.Date periodo;				//  periodo date
	@Column
	private String nombre;				//	nombre	varchar(183)
	@Column
	private Double edad;				//	edad	decimal(6,0)
	@Column
	private String sexo;				//	sexo	varchar(50)
	@Column
	private String medio_contacto;		//	medio_contacto	varchar(60)
	@Column
	private String otro_medio_contacto;	//	otro_medio_contacto	varchar(60)
	@Column
	private String promotor;			//	promotor	varchar(150)
	@Column
	private String recomendado_por;		//	recomendado_por	varchar(200)
	@Column
	private String nivel_estudios;		//	nivel_estudios	varchar(45)
	@Column
	private String estado_civil;		//	estado_civil	varchar(50)
	@Column
	private String regimen_matrimonial;	//	regimen_matrimonial	varchar(50)
	@Column
	private String es_comerciante;		//	es_comerciante	varchar(2)
	@Column
	private String es_empleado;			//	es_empleado	varchar(2)
	@Column
	private String actividad_economica;	//	actividad_economica	varchar(200)
	@Column
	private String Colonia;				//	Colonia	varchar(45)
	@Column
	private Date fecha_registro;		//	fecha_registro	datetime
	@Column
	private Integer activo;				//	activo	int(11)
	@Column
	private Date fecha_activacion;		//	fecha_activacion	datetime
	@Column
	private Integer intentos;			//	intentos	bigint(21)
	@Column
	private String ultimo_intento;		//	ultimo_intento	varchar(250)
	@Column
	private Date fecha_consulta;		//	fecha_consulta	datetime
	@Column
	private Integer bc_score;			//	bc_score	int(11)
	@Column
	private String kubo_score;			//	kubo_score	varchar(2)
	@Column
	private String folio_consulta;		//	folio_consulta	varchar(10)
	@Column
	private String icc;					//	icc	varchar(4)
	@Column
	private String estatus;				//	estatus	varchar(20)
	@Column
	private String tipo_credito;		//	tipo_credito	varchar(3)
	@Column
	private java.sql.Date dia_publicacion;	//	dia_publicacion	date
	@Column
	private Double monto_actual;		//	monto_actual	decimal(12,2)
	@Column
	private Double monto_ultima_simulacion;	//	monto_ultima_simulacion	decimal(12,2)
	@Column
	private String producto;			//	producto	varchar(40)
	@Column
	private String destino;				//	destino	varchar(40)
	@Column
	private String safi_credit_id;		//	safi_credit_id	varchar(25)
	@Column
	private Double monto_credito;		//	monto_credito	decimal(12,2)
	@Column
	private java.sql.Date fecha_inicio;		//	fecha_inicio	date
	@Column
	private java.sql.Date fecha_vencimiento;	//	fecha_vencimiento	date
	@Column
	private Double tasa;				//	tasa	decimal(12,4)
	@Column
	private Character frecuencia;			//	frecuencia	char(1)
	@Column
	private Integer no_cuotas;			//	no_cuotas	int(11)
	@Column
	private Double monto_cuota;			//	monto_cuota	decimal(12,2)
	@Column
	private Character estatus_credito;		//	estatus_credito	char(1)
	@Column
	private Double saldo_cap_vigente;	//	saldo_cap_vigente	decimal(14,2)
	@Column
	private Double saldo_cap_atrasado;	//	saldo_cap_atrasado	decimal(14,2)
	@Column
	private Double saldo_cap_vencido; 	//	saldo_cap_vencido	decimal(14,2)
	@Column
	private Double cat;					//	cat	decimal(12,4)
	@Column
	private Integer dias_atraso;		//	dias_atraso	bigint(11)
	
	
	
	public PipelineHistory() {
		super();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getEdad() {
		return edad;
	}
	
	public void setEdad(Double edad) {
		this.edad = edad;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getMedio_contacto() {
		return medio_contacto;
	}
	
	public void setMedio_contacto(String medio_contacto) {
		this.medio_contacto = medio_contacto;
	}
	
	public String getOtro_medio_contacto() {
		return otro_medio_contacto;
	}
	
	public void setOtro_medio_contacto(String otro_medio_contacto) {
		this.otro_medio_contacto = otro_medio_contacto;
	}
	
	public String getPromotor() {
		return promotor;
	}
	
	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}
	
	public String getRecomendado_por() {
		return recomendado_por;
	}
	
	public void setRecomendado_por(String recomendado_por) {
		this.recomendado_por = recomendado_por;
	}
	
	public String getNivel_estudios() {
		return nivel_estudios;
	}
	
	public void setNivel_estudios(String nivel_estudios) {
		this.nivel_estudios = nivel_estudios;
	}
	
	public String getEstado_civil() {
		return estado_civil;
	}
	
	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}
	
	public String getRegimen_matrimonial() {
		return regimen_matrimonial;
	}
	
	public void setRegimen_matrimonial(String regimen_matrimonial) {
		this.regimen_matrimonial = regimen_matrimonial;
	}
	
	public String getEs_comerciante() {
		return es_comerciante;
	}
	
	public void setEs_comerciante(String es_comerciante) {
		this.es_comerciante = es_comerciante;
	}
	
	public String getEs_empleado() {
		return es_empleado;
	}
	
	public void setEs_empleado(String es_empleado) {
		this.es_empleado = es_empleado;
	}
	
	public String getActividad_economica() {
		return actividad_economica;
	}
	
	public void setActividad_economica(String actividad_economica) {
		this.actividad_economica = actividad_economica;
	}
	
	public String getColonia() {
		return Colonia;
	}
	
	public void setColonia(String colonia) {
		Colonia = colonia;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public Integer getActivo() {
		return activo;
	}
	
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
	public Date getFecha_activacion() {
		return fecha_activacion;
	}
	
	public void setFecha_activacion(Date fecha_activacion) {
		this.fecha_activacion = fecha_activacion;
	}
	
	public Integer getIntentos() {
		return intentos;
	}
	
	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}
	
	public String getUltimo_intento() {
		return ultimo_intento;
	}
	
	public void setUltimo_intento(String ultimo_intento) {
		this.ultimo_intento = ultimo_intento;
	}
	
	public Date getFecha_consulta() {
		return fecha_consulta;
	}
	
	public void setFecha_consulta(Date fecha_consulta) {
		this.fecha_consulta = fecha_consulta;
	}
	
	public Integer getBc_score() {
		return bc_score;
	}
	
	public void setBc_score(Integer bc_score) {
		this.bc_score = bc_score;
	}
	
	public String getKubo_score() {
		return kubo_score;
	}
	
	public void setKubo_score(String kubo_score) {
		this.kubo_score = kubo_score;
	}
	
	public String getFolio_consulta() {
		return folio_consulta;
	}
	
	public void setFolio_consulta(String folio_consulta) {
		this.folio_consulta = folio_consulta;
	}
	
	public String getIcc() {
		return icc;
	}
	
	public void setIcc(String icc) {
		this.icc = icc;
	}
	
	public String getEstatus() {
		return estatus;
	}
	
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public String getTipo_credito() {
		return tipo_credito;
	}
	
	public void setTipo_credito(String tipo_credito) {
		this.tipo_credito = tipo_credito;
	}
	
	public java.sql.Date getDia_publicacion() {
		return dia_publicacion;
	}
	
	public void setDia_publicacion(java.sql.Date dia_publicacion) {
		this.dia_publicacion = dia_publicacion;
	}
	
	public Double getMonto_actual() {
		return monto_actual;
	}
	
	public void setMonto_actual(Double monto_actual) {
		this.monto_actual = monto_actual;
	}
	
	public Double getMonto_ultima_simulacion() {
		return monto_ultima_simulacion;
	}
	
	public void setMonto_ultima_simulacion(Double monto_ultima_simulacion) {
		this.monto_ultima_simulacion = monto_ultima_simulacion;
	}
	
	public String getProducto() {
		return producto;
	}
	
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	
	public Double getMonto_credito() {
		return monto_credito;
	}
	
	public void setMonto_credito(Double monto_credito) {
		this.monto_credito = monto_credito;
	}
	
	public java.sql.Date getFecha_inicio() {
		return fecha_inicio;
	}
	
	public void setFecha_inicio(java.sql.Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	
	public java.sql.Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(java.sql.Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public Double getTasa() {
		return tasa;
	}
	
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	
	public Integer getNo_cuotas() {
		return no_cuotas;
	}
	
	public void setNo_cuotas(Integer no_cuotas) {
		this.no_cuotas = no_cuotas;
	}
	
	public Double getMonto_cuota() {
		return monto_cuota;
	}
	
	public void setMonto_cuota(Double monto_cuota) {
		this.monto_cuota = monto_cuota;
	}
	
	public Double getSaldo_cap_vigente() {
		return saldo_cap_vigente;
	}
	
	public void setSaldo_cap_vigente(Double saldo_cap_vigente) {
		this.saldo_cap_vigente = saldo_cap_vigente;
	}
	
	public Double getSaldo_cap_atrasado() {
		return saldo_cap_atrasado;
	}
	
	public void setSaldo_cap_atrasado(Double saldo_cap_atrasado) {
		this.saldo_cap_atrasado = saldo_cap_atrasado;
	}
	
	public Double getSaldo_cap_vencido() {
		return saldo_cap_vencido;
	}
	
	public void setSaldo_cap_vencido(Double saldo_cap_vencido) {
		this.saldo_cap_vencido = saldo_cap_vencido;
	}
	
	public Double getCat() {
		return cat;
	}
	
	public void setCat(Double cat) {
		this.cat = cat;
	}
	
	public Integer getDias_atraso() {
		return dias_atraso;
	}
	
	public void setDias_atraso(Integer dias_atraso) {
		this.dias_atraso = dias_atraso;
	}
	
	public Character getEstatus_credito() {
		return estatus_credito;
	}

	public void setEstatus_credito(Character estatus_credito) {
		this.estatus_credito = estatus_credito;
	}

	public Character getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Character frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}

	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}

	public java.sql.Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(java.sql.Date periodo) {
		this.periodo = periodo;
	}
	
}
