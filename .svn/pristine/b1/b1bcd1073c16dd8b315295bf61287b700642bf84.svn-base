package mx.com.kubo.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import mx.com.kubo.tools.Utilities;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ProyectLoanActiveInSafi.class,
			name = "getProyectActiveList",    
			query = "call KUBOINVERSIONIMPLIS(:cuentaAho_ID,:diaAtrasoID,:fondeoKuboID,:proyect_loan_id,:tipoConsulta,:tipoVistaInv)",    
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}
	
	)
})

@Entity
//@Table(name="view_proyect_loan_active")
public class ProyectLoanActiveInSafi {

	@Id
	@Column 
	private Integer fondeokuboid; //	bigint(20)
	@Column 
	private Integer proyect_loan_id; // ,"int(10)
	@Column 
	private String safi_credit_id; // ,varchar(25),YES,,NULL,
	@Column 
	private Integer proyect_id; // ,"int(10)
	@Column 
	private Integer prospectus_id; // ,"int(10)
	@Column 
	private Integer company_id; // ,"tinyint(3)
	@Column 
	private String loan_type; // ,varchar(3),YES,,NULL,
	@Column 
	private String funding_type; // ,char(1),NO,,NULL,
	@Column 
	private Double ammount; // ,"decimal(12,2)",NO,,NULL,
	@Column 
	private Double min_ammount; // ,"decimal(12,2)",NO,,0.00,
	@Column 
	private Integer days_online; // ,"tinyint(3)
	@Column 
	private Integer term_id; // ,"tinyint(3)
	@Column 
	private Integer frequency_id; // ,"tinyint(3)
	@Column 
	private Double payment; // ,"decimal(12,2)",NO,,0.00,
	@Column 
	private Date day_published; // ,date,YES,,NULL,
	@Column 
	private Date approval_date; // ,datetime,YES,,NULL,
	@Column 
	private Date funding_date; // ,datetime,YES,,NULL,
	@Column 
	private Date consulting_date; // ,datetime,YES,,NULL,
	@Column 
	private Integer status_id; // ,"tinyint(3)
	@Column 
	private Double rate; // ,"decimal(12,2)",NO,,0.00,
	@Column 
	private String mx_solicitud_buro; // ,varchar(10),YES,,NULL,
	@Column 
	private Integer bc_score; // ,int(11),NO,,0,
	@Column 
	private String bc_score_range; // ,varchar(20),YES,,NULL,
	@Column 
	private String kubo_score_a; // ,varchar(1),NO,,,
	@Column 
	private Double amount_founded; // ,"decimal(12,2)
	@Column 
	private Integer verification_score; // ,"tinyint(3)
	@Column 
	private Double investment_bite; // ,"decimal(12,2)",NO,,0.00,
	@Column 
	private Integer investors_number; // ,"int(10)
	@Column 
	private String kubo_score_b; // ,varchar(1),NO,,,
	@Column 
	private Integer safi_mx_solicitud_id; // ,"int(10)
	@Column 
	private String approval_ticket; // ,varchar(20),YES,,NULL,
	@Column 
	private Double mx_cat; // ,"decimal(12,2)",YES,,NULL,
	@Column 
	private Double opening_commission_amount; // ,"decimal(12,2)",YES,,NULL,
	@Column 
	private String opening_payment; // ,varchar(1),YES,,NULL,
	@Column 
	private Double rate_with_opening; // ,"decimal(12,2)",YES,,NULL,
	@Column 
	private Integer is_kubo_property; // ,"tinyint(1)
	@Column 
	private Integer deposit_method_id; // ,tinyint(3),YES,,NULL,
	@Column 
	private Date signature_date; // ,datetime,YES,,NULL,
	@Column 
	private Double rate_investor; // ,"decimal(12,2)",YES,,NULL,
	@Column 
	private Double opening_commission; // ,"decimal(12,2)",YES,,NULL,
	@Column 
	private Double liquidity; // ,"decimal(12,2)",YES,,NULL,
	@Column 
	private String safi_mx_seguro_id; // ,varchar(45),YES,,NULL,
	@Column 
	private Integer previous_proyect_loan_id; // ,int(10),YES,,NULL,
	@Column 
	private String transunion_mod1; // ,varchar(10),YES,,NULL,
	@Column 
	private String transunion_mod2; // ,varchar(10),YES,,NULL,
	@Column 
	private Integer transunion_bc_score; // ,int(11),YES,,NULL,
	@Column
	private Integer cuentaAhoID;
	@Column 
	private Integer creditoid; //	int(11)
	@Column 
	private Integer solicitudCreditoID;//	bigint(20)
	@Column 
	private Double montocredito; // 	decimal(12,2)
	@Column 
	private Double montofondeo; //	 decimal(12,2)
	@Column 
	private Double montofondeoinv; //	decimal(12,2)
	@Column 
	private Double porcentajefondeo	; //decimal(10,6)
	@Column 
	private Date fechainicio; //	 date
	@Column 
	private Date fechavencimien; //	date
	@Column 
	private Double tasafija; // decimal(12,4)
	@Column 
	private Integer periodicidadcap; // int(11)
	@Column 
	private Integer numamortizacion	; // int(11)
	@Column 
	private String frecuenciaint; //	char(1)
	@Column 
	private String estatus; // 	char(1)
	@Column 
	private Double saldocapvigent; //	decimal(14,2)
	@Column 
	private Double saldocapatrasad; // decimal(14,2)
	@Column 
	private Double saldoCapVencido; // 	decimal(14,2)
	@Column 
	private Double saldCapVenNoExi; //	 decimal(14,2)
	@Column 
	private Double saldoInterOrdin; //	 decimal(14,4)
	@Column 
	private Double saldoInterAtras; //	decimal(14,4)
	@Column 
	private Double saldoInterVenc; //	decimal(14,4)
	@Column 
	private Double saldoInterProvi; // 	decimal(14,4)
	@Column 
	private Double saldoIntNoConta; // 	decimal(14,4)
	@Column 
	private Double saldoIVAInteres; // 	decimal(12,2)
	@Column 
	private Double saldoMoratorios; // 	decimal(14,2)
	@Column 
	private Double saldoIVAMorator; //	 decimal(12,2)
	@Column 
	private Double saldComFaltPago; // 	decimal(12,2)
	@Column 
	private Double salIVAComFalPag; // 	decimal(12,2)
	@Column 
	private Double saldoOtrasComis; // 	decimal(12,2)
	@Column 
	private Double saldoIVAComisi; //	decimal(12,2)
	@Column 
	private Double provisionAcum; //	decimal(14,4)
	@Column 
	private Double saldoCapVigenteInv; //	decimal(14,2)
	@Column 
	private Double saldoCapExigibleInv; //	decimal(14,2)
	@Column 
	private Double saldoCapInv; //	decimal(14,2)
	@Column
	private Double montoCtaOrden; 
	@Column
	private Double saldoIntCtaOrden;
	@Column 
	private Date fechaInicioInv; //	date
	@Column 
	private Date fechaVencimientoInv; //	 date
	@Column 
	private Integer diasAtraso; //	bigint(11)	
	@Column
	private Integer numCuotas;
	@Column 
	private Integer noCuotasAtraso;
	@Column
	private Integer diaAtrasoID;
	@Column
	private String consecutivo;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "proyect_id", referencedColumnName = "proyect_id", insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private Proyect proyect;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "frequency_id", referencedColumnName = "frequency_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private Frequency frequency;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	    })	
	private NaturalPerson person;
	
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public Integer getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(Integer proyect_id) {
		this.proyect_id = proyect_id;
	}
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
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public String getFunding_type() {
		return funding_type;
	}
	public void setFunding_type(String funding_type) {
		this.funding_type = funding_type;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Double getMin_ammount() {
		return min_ammount;
	}
	public void setMin_ammount(Double min_ammount) {
		this.min_ammount = min_ammount;
	}
	public Integer getDays_online() {
		return days_online;
	}
	public void setDays_online(Integer days_online) {
		this.days_online = days_online;
	}
	public Integer getTerm_id() {
		return term_id;
	}
	public void setTerm_id(Integer term_id) {
		this.term_id = term_id;
	}
	public Integer getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(Integer frequency_id) {
		this.frequency_id = frequency_id;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Date getDay_published() {
		return day_published;
	}
	public void setDay_published(Date day_published) {
		this.day_published = day_published;
	}
	public Date getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}
	public Date getFunding_date() {
		return funding_date;
	}
	public void setFunding_date(Date funding_date) {
		this.funding_date = funding_date;
	}
	public Date getConsulting_date() {
		return consulting_date;
	}
	public void setConsulting_date(Date consulting_date) {
		this.consulting_date = consulting_date;
	}
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public Integer getBc_score() {
		return bc_score;
	}
	public void setBc_score(Integer bc_score) {
		this.bc_score = bc_score;
	}
	public String getBc_score_range() {
		return bc_score_range;
	}
	public void setBc_score_range(String bc_score_range) {
		this.bc_score_range = bc_score_range;
	}
	public String getKubo_score_a() {
		return kubo_score_a;
	}
	public void setKubo_score_a(String kubo_score_a) {
		this.kubo_score_a = kubo_score_a;
	}
	public Double getAmount_founded() {
		return amount_founded;
	}
	public void setAmount_founded(Double amount_founded) {
		this.amount_founded = amount_founded;
	}
	public Integer getVerification_score() {
		return verification_score;
	}
	public void setVerification_score(Integer verification_score) {
		this.verification_score = verification_score;
	}
	public Double getInvestment_bite() {
		return investment_bite;
	}
	public void setInvestment_bite(Double investment_bite) {
		this.investment_bite = investment_bite;
	}
	public Integer getInvestors_number() {
		return investors_number;
	}
	public void setInvestors_number(Integer investors_number) {
		this.investors_number = investors_number;
	}
	public String getKubo_score_b() {
		return kubo_score_b;
	}
	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
	}
	public Integer getSafi_mx_solicitud_id() {
		return safi_mx_solicitud_id;
	}
	public void setSafi_mx_solicitud_id(Integer safi_mx_solicitud_id) {
		this.safi_mx_solicitud_id = safi_mx_solicitud_id;
	}
	public String getApproval_ticket() {
		return approval_ticket;
	}
	public void setApproval_ticket(String approval_ticket) {
		this.approval_ticket = approval_ticket;
	}
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Double getMx_cat() {
		return mx_cat;
	}
	public void setMx_cat(Double mx_cat) {
		this.mx_cat = mx_cat;
	}
	public Double getOpening_commission_amount() {
		return opening_commission_amount;
	}
	public void setOpening_commission_amount(Double opening_commission_amount) {
		this.opening_commission_amount = opening_commission_amount;
	}
	public String getOpening_payment() {
		return opening_payment;
	}
	public void setOpening_payment(String opening_payment) {
		this.opening_payment = opening_payment;
	}
	public Double getRate_with_opening() {
		return rate_with_opening;
	}
	public void setRate_with_opening(Double rate_with_opening) {
		this.rate_with_opening = rate_with_opening;
	}
	public Integer getIs_kubo_property() {
		return is_kubo_property;
	}
	public void setIs_kubo_property(Integer is_kubo_property) {
		this.is_kubo_property = is_kubo_property;
	}
	public Integer getDeposit_method_id() {
		return deposit_method_id;
	}
	public void setDeposit_method_id(Integer deposit_method_id) {
		this.deposit_method_id = deposit_method_id;
	}
	public Date getSignature_date() {
		return signature_date;
	}
	public void setSignature_date(Date signature_date) {
		this.signature_date = signature_date;
	}
	public Double getRate_investor() {
		return rate_investor;
	}
	public void setRate_investor(Double rate_investor) {
		this.rate_investor = rate_investor;
	}
	public Double getOpening_commission() {
		return opening_commission;
	}
	public void setOpening_commission(Double opening_commission) {
		this.opening_commission = opening_commission;
	}
	public Double getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(Double liquidity) {
		this.liquidity = liquidity;
	}
	public String getSafi_mx_seguro_id() {
		return safi_mx_seguro_id;
	}
	public void setSafi_mx_seguro_id(String safi_mx_seguro_id) {
		this.safi_mx_seguro_id = safi_mx_seguro_id;
	}
	public Integer getPrevious_proyect_loan_id() {
		return previous_proyect_loan_id;
	}
	public void setPrevious_proyect_loan_id(Integer previous_proyect_loan_id) {
		this.previous_proyect_loan_id = previous_proyect_loan_id;
	}
	public String getTransunion_mod1() {
		return transunion_mod1;
	}
	public void setTransunion_mod1(String transunion_mod1) {
		this.transunion_mod1 = transunion_mod1;
	}
	public String getTransunion_mod2() {
		return transunion_mod2;
	}
	public void setTransunion_mod2(String transunion_mod2) {
		this.transunion_mod2 = transunion_mod2;
	}
	public Integer getTransunion_bc_score() {
		return transunion_bc_score;
	}
	public void setTransunion_bc_score(Integer transunion_bc_score) {
		this.transunion_bc_score = transunion_bc_score;
	}
	
	public Proyect getProyect() {
		return proyect;
	}
	public void setProyect(Proyect proyect) {
		this.proyect = proyect;
	}

	
	
	public String getBarPorcentTotal(){
		 
		return "width: "+this.getBottomPorcent().toString()+"%;";
	}
	public Double getBottomPorcent(){
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
		Double valor = (((100*this.ammount)-100*(this.ammount-this.amount_founded))/this.ammount);
		if(valor>100.00) return 100.00;
		return Double.valueOf(formateador.format(valor)); 
	}
	
	public String getKuboBarPorcent(){
		try {
			
			BigDecimal amount = BigDecimal.valueOf(amount_founded);
			//ProyectLoan proyect = this.proyectLoanService.findProyect(key);
			
			if(amount != null  ){
				BigDecimal kuboAmmount=amount;
				
				String KFAmmount = kuboAmmount.toString();
				Double montoDeProyecto = getBottomPorcentParametrized(KFAmmount);
				
				if(montoDeProyecto<=0.0){
					return "width: 0.0%;";
				}
				else{
					return "width: "+montoDeProyecto.toString()+"%;";
				}
			}else{
				return "width: 0.0%;";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "width: 0.0%;"; 
		}
		
	}
	
	public String getPlazoStr(){
		String plazo ="";
		
		plazo = ""+term_id;
		
		switch(frequency_id){
			case 1:
				plazo += " Semanas";
				break;
			case 2:
				plazo += " Catorcenas";
				break;
			case 3:
				plazo += " Quincenas";
				break;
			case 4:
				plazo += " Meses";
				break;
		}
		return plazo;
	}
	
	public Double getBottomPorcentParametrized(String IFValueFunding){
		Double ammountFoundedByI = Double.valueOf(IFValueFunding);
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
		Double valor = (((100*this.ammount)-100*(this.ammount-ammountFoundedByI))/this.ammount);
		if(valor>100.00) return 100.00;
		return Double.valueOf(formateador.format(valor)); 
	}
	
	public Double getExpectedPerformanceForProyect(){
		Integer pagos = 0;
		if(frequency_id==1){ 
			pagos = 52;
		}
		else if (frequency_id==2){ 
			pagos = 26;
		}
		else if (frequency_id==3){ 
			pagos = 24;
		}
		else{
			pagos = 14;
		}
		if(rate==null)
			rate=52.6D;
		//tasaPasiva = tasaPasiva/100;
		Double tasaAnual=(rate/100);
		Double tasaEnPeriodo = tasaAnual/pagos;	
		Double rendimiento = generaMontoCuota(tasaEnPeriodo, Double.parseDouble(term_id+""), ammount);
		rendimiento = (rendimiento*term_id)-ammount;
		if (rendimiento < 0.00) rendimiento = 0.00;
		return (double)Math.round(rendimiento*100)/100;
	}
	
	private Double generaMontoCuota(Double tasaPeriodo,Double numCuota,Double cachito){
		Double intper = tasaPeriodo;
		Double num = (Math.pow((1+intper), numCuota))*intper;
		Double den = (Math.pow((1+intper), numCuota))-1;
		Double montoAPagar = cachito*(num/den);
		return (double)Math.round(montoAPagar*100)/100;
	}
	
	public String getEstatusStr(){
		String res = "";
		
		if(diasAtraso>0 && estatus.equals("V")){
			res = "ATRASADO";
		}else if(diasAtraso==0 && estatus.equals("V")){
			res = "VIGENTE";
		}else if(estatus.equals("P")){
			res = "PAGADO";
		}else if(estatus.equals("B")){
			res = "VENCIDO";
		}else if(estatus.equals("K")){
			res = "QUEBRANTADO";
		}
		return res;
	}
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	public Integer getFondeokuboid() {
		return fondeokuboid;
	}
	public void setFondeokuboid(Integer fondeokuboid) {
		this.fondeokuboid = fondeokuboid;
	}
	public Integer getCreditoid() {
		return creditoid;
	}
	public void setCreditoid(Integer creditoid) {
		this.creditoid = creditoid;
	}
	public Integer getSolicitudCreditoID() {
		return solicitudCreditoID;
	}
	public void setSolicitudCreditoID(Integer solicitudCreditoID) {
		this.solicitudCreditoID = solicitudCreditoID;
	}
	public Double getMontocredito() {
		return montocredito;
	}
	public void setMontocredito(Double montocredito) {
		this.montocredito = montocredito;
	}
	public Double getMontofondeo() {
		return montofondeo;
	}
	public void setMontofondeo(Double montofondeo) {
		this.montofondeo = montofondeo;
	}
	public Double getMontofondeoinv() {
		return montofondeoinv;
	}
	public void setMontofondeoinv(Double montofondeoinv) {
		this.montofondeoinv = montofondeoinv;
	}
	public Double getPorcentajefondeo() {
		return porcentajefondeo;
	}
	public void setPorcentajefondeo(Double porcentajefondeo) {
		this.porcentajefondeo = porcentajefondeo;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechavencimien() {
		return fechavencimien;
	}
	public void setFechavencimien(Date fechavencimien) {
		this.fechavencimien = fechavencimien;
	}
	public Double getTasafija() {
		return tasafija;
	}
	public void setTasafija(Double tasafija) {
		this.tasafija = tasafija;
	}
	public Integer getPeriodicidadcap() {
		return periodicidadcap;
	}
	public void setPeriodicidadcap(Integer periodicidadcap) {
		this.periodicidadcap = periodicidadcap;
	}
	public Integer getNumamortizacion() {
		return numamortizacion;
	}
	public void setNumamortizacion(Integer numamortizacion) {
		this.numamortizacion = numamortizacion;
	}
	public String getFrecuenciaint() {
		return frecuenciaint;
	}
	public void setFrecuenciaint(String frecuenciaint) {
		this.frecuenciaint = frecuenciaint;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Double getSaldocapvigent() {
		return saldocapvigent;
	}
	public void setSaldocapvigent(Double saldocapvigent) {
		this.saldocapvigent = saldocapvigent;
	}
	public Double getSaldocapatrasad() {
		return saldocapatrasad;
	}
	public void setSaldocapatrasad(Double saldocapatrasad) {
		this.saldocapatrasad = saldocapatrasad;
	}
	public Double getSaldoCapVencido() {
		return saldoCapVencido;
	}
	public void setSaldoCapVencido(Double saldoCapVencido) {
		this.saldoCapVencido = saldoCapVencido;
	}
	public Double getSaldCapVenNoExi() {
		return saldCapVenNoExi;
	}
	public void setSaldCapVenNoExi(Double saldCapVenNoExi) {
		this.saldCapVenNoExi = saldCapVenNoExi;
	}
	public Double getSaldoInterOrdin() {
		return saldoInterOrdin;
	}
	public void setSaldoInterOrdin(Double saldoInterOrdin) {
		this.saldoInterOrdin = saldoInterOrdin;
	}
	public Double getSaldoInterAtras() {
		return saldoInterAtras;
	}
	public void setSaldoInterAtras(Double saldoInterAtras) {
		this.saldoInterAtras = saldoInterAtras;
	}
	public Double getSaldoInterVenc() {
		return saldoInterVenc;
	}
	public void setSaldoInterVenc(Double saldoInterVenc) {
		this.saldoInterVenc = saldoInterVenc;
	}
	public Double getSaldoInterProvi() {
		return saldoInterProvi;
	}
	public void setSaldoInterProvi(Double saldoInterProvi) {
		this.saldoInterProvi = saldoInterProvi;
	}
	public Double getSaldoIntNoConta() {
		return saldoIntNoConta;
	}
	public void setSaldoIntNoConta(Double saldoIntNoConta) {
		this.saldoIntNoConta = saldoIntNoConta;
	}
	public Double getSaldoIVAInteres() {
		return saldoIVAInteres;
	}
	public void setSaldoIVAInteres(Double saldoIVAInteres) {
		this.saldoIVAInteres = saldoIVAInteres;
	}
	public Double getSaldoMoratorios() {
		return saldoMoratorios;
	}
	public void setSaldoMoratorios(Double saldoMoratorios) {
		this.saldoMoratorios = saldoMoratorios;
	}
	public Double getSaldoIVAMorator() {
		return saldoIVAMorator;
	}
	public void setSaldoIVAMorator(Double saldoIVAMorator) {
		this.saldoIVAMorator = saldoIVAMorator;
	}
	public Double getSaldComFaltPago() {
		return saldComFaltPago;
	}
	public void setSaldComFaltPago(Double saldComFaltPago) {
		this.saldComFaltPago = saldComFaltPago;
	}
	public Double getSalIVAComFalPag() {
		return salIVAComFalPag;
	}
	public void setSalIVAComFalPag(Double salIVAComFalPag) {
		this.salIVAComFalPag = salIVAComFalPag;
	}
	public Double getSaldoOtrasComis() {
		return saldoOtrasComis;
	}
	public void setSaldoOtrasComis(Double saldoOtrasComis) {
		this.saldoOtrasComis = saldoOtrasComis;
	}
	public Double getSaldoIVAComisi() {
		return saldoIVAComisi;
	}
	public void setSaldoIVAComisi(Double saldoIVAComisi) {
		this.saldoIVAComisi = saldoIVAComisi;
	}
	public Double getProvisionAcum() {
		return provisionAcum;
	}
	public void setProvisionAcum(Double provisionAcum) {
		this.provisionAcum = provisionAcum;
	}
	public Double getSaldoCapVigenteInv() {
		return saldoCapVigenteInv;
	}
	public void setSaldoCapVigenteInv(Double saldoCapVigenteInv) {
		this.saldoCapVigenteInv = saldoCapVigenteInv;
	}
	public Date getFechaInicioInv() {
		return fechaInicioInv;
	}
	public void setFechaInicioInv(Date fechaInicioInv) {
		this.fechaInicioInv = fechaInicioInv;
	}
	public Date getFechaVencimientoInv() {
		return fechaVencimientoInv;
	}
	public void setFechaVencimientoInv(Date fechaVencimientoInv) {
		this.fechaVencimientoInv = fechaVencimientoInv;
	}
	public Integer getDiasAtraso() {
		return diasAtraso;
	}
	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}
	public Integer getCuentaAhoID() {
		return cuentaAhoID;
	}
	public void setCuentaAhoID(Integer cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
	}
	public Integer getNoCuotasAtraso() {
		return noCuotasAtraso;
	}
	public void setNoCuotasAtraso(Integer noCuotasAtraso) {
		this.noCuotasAtraso = noCuotasAtraso;
	}
	public Integer getNumCuotas() {
		return numCuotas;
	}
	public void setNumCuotas(Integer numCuotas) {
		this.numCuotas = numCuotas;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public Double getPeriodRate(){
		
		Calendar cFF = Calendar.getInstance();
		cFF.setTime(fechaVencimientoInv);
		
		Calendar cFI = Calendar.getInstance();
		
		Integer dias = 0;
		
		cFI.setTime(fechaInicioInv);
		
		Calendar cFA = Calendar.getInstance();
		
		cFA.setTime( new Date() );
		
		GregorianCalendar gcal = new GregorianCalendar();
		
		Integer maxDiaMesActual = gcal.getActualMaximum( Calendar.DAY_OF_MONTH );
		
		//Integer m = gcal.get(  );
		
		Integer mesActual 			= cFA.get(Calendar.MONTH);
		Integer diaActual			= cFA.get(Calendar.DATE );
		Integer yearActual			= cFA.get(Calendar.YEAR );
		
		Integer mesIniInversion		= cFI.get(Calendar.MONTH);
		Integer diaIniInversion		= cFI.get(Calendar.DATE );
		Integer yearIniInversion	= cFI.get(Calendar.YEAR );
		
		Integer mesFinInversion		= cFF.get(Calendar.MONTH);
		Integer diaFinInversion		= cFF.get(Calendar.DATE );
		Integer yearFinInversion	= cFF.get(Calendar.YEAR );
//	
//		System.out.println("ACTUAL dia:  " + diaActual + "mes: "+ mesActual +" año: " + yearActual + " de dias : "+maxDiaMesActual );
//		System.out.println("INICIO dia:  " + diaIniInversion + "mes: "+ mesIniInversion +" año: " + yearIniInversion );
//		System.out.println("FIN    dia:  " + diaFinInversion + "mes: "+ mesFinInversion +" año: " + yearFinInversion );
//		
		if( mesActual == mesFinInversion && yearActual == yearFinInversion ) 
		{
			dias = diaFinInversion;
			
		}else if( mesActual == mesIniInversion && yearActual == yearIniInversion ) 
		{
			dias = maxDiaMesActual - diaIniInversion;
			
		} else {
			
			dias = maxDiaMesActual;
				
		}
		
//		System.out.println("dias: "+dias);
//		System.out.println("");
		
		Double d = ((getRate_investor())/360) * dias;
		return Math.round(d * 100.0) / 100.0;
	}
	
	public String getTipoCrédito(){
		
		return Utilities.getTipoCredito( getLoan_type() );
		
	}
	public Integer getDiaAtrasoID() {
		return diaAtrasoID;
	}
	public void setDiaAtrasoID(Integer diaAtrasoID) {
		this.diaAtrasoID = diaAtrasoID;
	}
	public Double getSaldoCapExigibleInv() {
		return saldoCapExigibleInv;
	}
	public void setSaldoCapExigibleInv(Double saldoCapExigibleInv) {
		this.saldoCapExigibleInv = saldoCapExigibleInv;
	}
	public Double getSaldoCapInv() {
		return saldoCapInv;
	}
	public void setSaldoCapInv(Double saldoCapInv) {
		this.saldoCapInv = saldoCapInv;
	}
	public String getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	public Double getMontoCtaOrden() {
		return montoCtaOrden;
	}
	public void setMontoCtaOrden(Double montoCtaOrden) {
		this.montoCtaOrden = montoCtaOrden;
	}
	public Double getSaldoIntCtaOrden() {
		return saldoIntCtaOrden;
	}
	public void setSaldoIntCtaOrden(Double saldoIntCtaOrden) {
		this.saldoIntCtaOrden = saldoIntCtaOrden;
	}
	public Double getSaldosVigAtrGar(){
		if(montoCtaOrden > 0 ){
			this.saldoCapVigenteInv = montoCtaOrden;
		}
		return this.saldoCapVigenteInv; 
	}
	public Double getSaldosVigIntGar(){
		if(montoCtaOrden > 0 ){
			this.saldoCapExigibleInv = saldoIntCtaOrden;
		}
		return this.saldoCapExigibleInv; 
	}
}
