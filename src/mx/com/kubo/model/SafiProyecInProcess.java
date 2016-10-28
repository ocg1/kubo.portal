package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.kubo.managedbeans.util.ConvertCalendar;

@Entity
@Table(name="view_safi_proyect_in_process")
public class SafiProyecInProcess implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private Integer safi_mx_solicitud_id; //,"int(10)
	
	@Column
	private Integer proyect_loan_id; //,"int(10)
	@Column
	private Integer proyect_id; //,"int(10)
	@Column
	private Integer prospectus_id; //,"int(10)
	@Column
	private Integer company_id; //,"tinyint(3)
	@Column
	private String loan_type; //,varchar(3),YES,,NULL,
	@Column
	private Character funding_type; //,char(1),NO,,NULL,
	@Column
	private Double ammount; //,"decimal(12,2)",NO,,NULL,
	@Column
	private Double min_ammount; //,"decimal(12,2)",NO,,0.00,
	@Column
	private Integer days_online; //,"tinyint(3)
	@Column
	private Integer term_id; //,"tinyint(3)
	@Column
	private Integer frequency_id; //,"tinyint(3)
	@Column
	private Double payment; //,"decimal(12,2)",NO,,0.00,
	@Column
	private Date day_published; //,date,YES,,NULL,
	@Column
	private Date approval_date; //,datetime,YES,,NULL,
	@Column
	private Date funding_date; //,datetime,YES,,NULL,
	@Column
	private Date consulting_date; //,datetime,YES,,NULL,
	@Column
	private Integer status_id; //,"tinyint(3)
	@Column
	private Double rate; //,"decimal(12,2)",NO,,0.00,
	@Column
	private String mx_solicitud_buro; //,varchar(10),YES,,NULL,
	@Column
	private Integer bc_score; //,int(11),NO,,0,
	@Column
	private String bc_score_range; //,varchar(20),YES,,NULL,
	@Column
	private String kubo_score_a; //,varchar(1),NO,,,
	@Column
	private Double amount_founded; //,"decimal(12,2)
	@Column
	private Integer verification_score; //,"tinyint(3)
	@Column
	private Double investment_bite; //,"decimal(12,2)",NO,,0.00,
	@Column
	private Integer investors_number; //,"int(10)
	@Column
	private String kubo_score_b; //,varchar(1),NO,,,
	@Column
	private String safi_credit_id; //,varchar(25),YES,,NULL,
	@Column
	private String approval_ticket; //,varchar(20),YES,,NULL,
	@Column
	private Double mx_cat; //,"decimal(12,2)",YES,,NULL,
	@Column
	private Double opening_commission_amount; //,"decimal(12,2)",YES,,NULL,
	@Column
	private String opening_payment; //,varchar(1),YES,,NULL,
	@Column
	private Double rate_with_opening; //,"decimal(12,2)",YES,,NULL,
	@Column
	private Integer is_kubo_property; //,"tinyint(1)
	@Column
	private Integer deposit_method_id; //,tinyint(3),YES,,NULL,
	@Column
	private Date signature_date; //,datetime,YES,,NULL,
	@Column
	private Double rate_investor; //,"decimal(12,2)",YES,,NULL,
	@Column
	private Double opening_commission; //,"decimal(12,2)",YES,,NULL,
	@Column
	private Double liquidity; //,"decimal(12,2)",YES,,NULL,
	@Column
	private String safi_mx_seguro_id; //,varchar(45),YES,,NULL,
	@Column
	private Integer previous_proyect_loan_id; //,int(10),YES,,NULL,
	@Column
	private String transunion_mod1; //,varchar(10),YES,,NULL,
	@Column
	private String transunion_mod2; //,varchar(10),YES,,NULL,
	@Column
	private Integer transunion_bc_score; //,int(11),YES,,NULL,
	@Column
	private Integer solicitudCreditoID; //,bigint(20),NO,,NULL,
	@Column
	private Date fechaRegistro; //,date,YES,,NULL,
	@Column
	private Double montoFondeo; //,"decimal(12,2)",YES,,NULL,
	@Column
	private Double porcentajeFondeo; //,"decimal(10,6)",YES,,NULL,
	@Column
	private Double tasaPasiva; //,"decimal(8,4)",NO,,NULL,
	@Column
	private Integer cuentaAhoID; //,int(11),YES,,NULL
	@Column
	private Integer plazoEnDias; //,int(11),YES,,NULL
	@Column
	private Integer diasPorTrans; //,int(11),YES,,NULL
	@Column
	private Double disponibleFondeo; //,int(11),YES,,NULL
	
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "proyect_id", referencedColumnName = "proyect_id", insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private Proyect proyect;
	
	
	public Integer getSafi_mx_solicitud_id() {
		return safi_mx_solicitud_id;
	}
	public void setSafi_mx_solicitud_id(Integer safi_mx_solicitud_id) {
		this.safi_mx_solicitud_id = safi_mx_solicitud_id;
	}
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
	public Character getFunding_type() {
		return funding_type;
	}
	public void setFunding_type(Character funding_type) {
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
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public String getApproval_ticket() {
		return approval_ticket;
	}
	public void setApproval_ticket(String approval_ticket) {
		this.approval_ticket = approval_ticket;
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
	public Integer getSolicitudCreditoID() {
		return solicitudCreditoID;
	}
	public void setSolicitudCreditoID(Integer solicitudCreditoID) {
		this.solicitudCreditoID = solicitudCreditoID;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Double getMontoFondeo() {
		return montoFondeo;
	}
	public void setMontoFondeo(Double montoFondeo) {
		this.montoFondeo = montoFondeo;
	}
	public Double getPorcentajeFondeo() {
		return porcentajeFondeo;
	}
	public void setPorcentajeFondeo(Double porcentajeFondeo) {
		this.porcentajeFondeo = porcentajeFondeo;
	}
	public Double getTasaPasiva() {
		return tasaPasiva;
	}
	public void setTasaPasiva(Double tasaPasiva) {
		this.tasaPasiva = tasaPasiva;
	}
	public Integer getCuentaAhoID() {
		return cuentaAhoID;
	}
	public void setCuentaAhoID(Integer cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
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
		if(tasaPasiva==null)
			tasaPasiva=52.6D;
		//tasaPasiva = tasaPasiva/100;
		Double tasaAnual=(tasaPasiva/100);
		Double tasaEnPeriodo = tasaAnual/pagos;	
		Double rendimiento = generaMontoCuota(tasaEnPeriodo, Double.parseDouble(term_id+""), montoFondeo);
		rendimiento = (rendimiento*term_id)-montoFondeo;
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
	
	public Long getDaysLeft() {
		if( this.day_published != null ){
			ConvertCalendar cv = new ConvertCalendar(this.day_published);
			Long fechaDeAlta = cv.toBvLong();
		    Long limitDate = fechaDeAlta +this.days_online;
		    ConvertCalendar convert = new ConvertCalendar(new Date());
		    Long dateNow = convert.toBvLong();
		    Long daysLeft = limitDate-dateNow;
		    if(daysLeft<=0) return (long) 0;
		    else return daysLeft;
		}else
			return 0L;
	}
	public Integer getPlazoEnDias() {
		return plazoEnDias;
	}
	public void setPlazoEnDias(Integer plazoEnDias) {
		this.plazoEnDias = plazoEnDias;
	}
	public Integer getDiasPorTrans() {
		return diasPorTrans;
	}
	public void setDiasPorTrans(Integer diasPorTrans) {
		this.diasPorTrans = diasPorTrans;
	}
	public Double getDisponibleFondeo() {
		return disponibleFondeo;
	}
	public void setDisponibleFondeo(Double disponibleFondeo) {
		this.disponibleFondeo = disponibleFondeo;
	}
	
}
