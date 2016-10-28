package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.kubo.managedbeans.util.ConvertCalendar;

/*
@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ViewForTiendaExec.class,
			name = "queryTiendaInversionista",    
			query = "call instructions_execute(:query)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})
*/

// Par_CuentaAhoID 	int(12),
// Par_ClienteID		int(12),
// Par_TipoFondeo		char(1),	-- T todos. S sin fondeo previo, C con fondeo previo
// Par_LoanType		varchar(250),
// Par_KuboScore		varchar(250),
// Par_GenderID		varchar(10),
// Par_Ammount			varchar(100),
// Par_DiasPorTrans	int

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ViewForTiendaExec.class,
			name = "queryTiendaInversionista",    
			query = "call FILTROSTIENDACON(:Par_CuentaAhoID, :Par_ClienteID,:Par_TipoFondeo, :Par_LoanType, :Par_KuboScore, :Par_GenderID, :Par_Ammount, :Par_DiasPorTrans, :Par_purpose_id)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class ViewForTiendaExec implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId private ProyectLoanPK proyectloanPk;
	
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	}) private NaturalPerson person;
	
	@ManyToOne
	@JoinColumns(value = {
		@JoinColumn(name = "proyect_id",    referencedColumnName = "proyect_id",    insertable = false, updatable = false),
		@JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
		@JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	}) private Proyect proyect;
	
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "status_id",  referencedColumnName = "status_id",  insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private StatusProyectCat statusProyect;
	
	
	@ManyToOne
	@JoinColumns(value = {
		@JoinColumn(name = "term_id",    referencedColumnName = "term_id",    insertable = false, updatable = false),
		@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
    }) private Term term;
	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column private Date signature_date;
	@Column private Date day_published;	
	@Column private Date approval_date;	
	@Column private Date funding_date;	
	@Column private Date consulting_date;	
	@Column private Date posposed_date;	
	@Column private Date preapproved_date;
		
	@Column private String bc_score_range;	
	@Column private String mx_solicitud_buro;
	@Column private String kubo_score_a;
	@Column private String kubo_score_b;	
	@Column private String approval_ticket;	
	@Column private String safi_credit_id;	
	@Column private String	safi_mx_seguro_id;
	@Column private String	opening_payment;							
	@Column private String loan_type;		
	@Column private String transunion_mod1;	
	@Column private String transunion_mod2;
	@Column private String cci_score;
	@Column private String is_collection_solution;
	@Column private String is_published;
	
	@Column private Double rate;		
	@Column private Double investment_bite = 250D;	
	@Column private Double ammount;	
	@Column private Double min_ammount;	
	@Column private Double payment;
	@Column private Double mx_cat ;	
	@Column private Double opening_commission_amount;//	DECIMAL	
	@Column private Double opening_commission;	
	@Column private Double rate_with_opening;
	@Column private Double rate_investor;//	"decimal(12	
	@Column private Double liquidity ;	
		
	@Column private Integer bc_score;
	@Column private Integer safi_mx_solicitud_id;	
	@Column private Integer status_id;	
	@Column private Integer motive_id;	
	@Column private Integer is_kubo_property=0;	
	@Column private Integer deposit_method_id;				
	@Column private Integer previous_proyect_loan_id;
	@Column private Integer transunion_bc_score;
	
	@Column private double amount_founded = 0D;	
	
	@Column private int verification_score;
	@Column private int investors_number=0;
	@Column private int days_online;	
	@Column private int term_id;	
	@Column private int frequency_id;	
	
	@Column private char funding_type;	
	
	@Column(name="CantInvCred")
	private BigInteger cantInvCred;//	int(11)
	@Column(name="CantInvSol")
	private BigInteger cantInvSol;//	int(11)
	public ProyectLoanPK getProyectloanPk() {
		return proyectloanPk;
	}
	public void setProyectloanPk(ProyectLoanPK proyectloanPk) {
		this.proyectloanPk = proyectloanPk;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public Proyect getProyect() {
		return proyect;
	}
	public void setProyect(Proyect proyect) {
		this.proyect = proyect;
	}
	public Date getSignature_date() {
		return signature_date;
	}
	public void setSignature_date(Date signature_date) {
		this.signature_date = signature_date;
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
	public Date getPosposed_date() {
		return posposed_date;
	}
	public void setPosposed_date(Date posposed_date) {
		this.posposed_date = posposed_date;
	}
	public Date getPreapproved_date() {
		return preapproved_date;
	}
	public void setPreapproved_date(Date preapproved_date) {
		this.preapproved_date = preapproved_date;
	}
	public String getBc_score_range() {
		return bc_score_range;
	}
	public void setBc_score_range(String bc_score_range) {
		this.bc_score_range = bc_score_range;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public String getKubo_score_a() {
		return kubo_score_a;
	}
	public void setKubo_score_a(String kubo_score_a) {
		this.kubo_score_a = kubo_score_a;
	}
	public String getKubo_score_b() {
		return kubo_score_b;
	}
	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
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
	public String getSafi_mx_seguro_id() {
		return safi_mx_seguro_id;
	}
	public void setSafi_mx_seguro_id(String safi_mx_seguro_id) {
		this.safi_mx_seguro_id = safi_mx_seguro_id;
	}
	public String getOpening_payment() {
		return opening_payment;
	}
	public void setOpening_payment(String opening_payment) {
		this.opening_payment = opening_payment;
	}
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
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
	public String getCci_score() {
		return cci_score;
	}
	public void setCci_score(String cci_score) {
		this.cci_score = cci_score;
	}
	public String getIs_collection_solution() {
		return is_collection_solution;
	}
	public void setIs_collection_solution(String is_collection_solution) {
		this.is_collection_solution = is_collection_solution;
	}
	public String getIs_published() {
		return is_published;
	}
	public void setIs_published(String is_published) {
		this.is_published = is_published;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getInvestment_bite() {
		return investment_bite;
	}
	public void setInvestment_bite(Double investment_bite) {
		this.investment_bite = investment_bite;
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
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
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
	public Double getOpening_commission() {
		return opening_commission;
	}
	public void setOpening_commission(Double opening_commission) {
		this.opening_commission = opening_commission;
	}
	public Double getRate_with_opening() {
		return rate_with_opening;
	}
	public void setRate_with_opening(Double rate_with_opening) {
		this.rate_with_opening = rate_with_opening;
	}
	public Double getRate_investor() {
		return rate_investor;
	}
	public void setRate_investor(Double rate_investor) {
		this.rate_investor = rate_investor;
	}
	public Double getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(Double liquidity) {
		this.liquidity = liquidity;
	}
	public Integer getBc_score() {
		return bc_score;
	}
	public void setBc_score(Integer bc_score) {
		this.bc_score = bc_score;
	}
	public Integer getSafi_mx_solicitud_id() {
		return safi_mx_solicitud_id;
	}
	public void setSafi_mx_solicitud_id(Integer safi_mx_solicitud_id) {
		this.safi_mx_solicitud_id = safi_mx_solicitud_id;
	}
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	public Integer getMotive_id() {
		return motive_id;
	}
	public void setMotive_id(Integer motive_id) {
		this.motive_id = motive_id;
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
	public Integer getPrevious_proyect_loan_id() {
		return previous_proyect_loan_id;
	}
	public void setPrevious_proyect_loan_id(Integer previous_proyect_loan_id) {
		this.previous_proyect_loan_id = previous_proyect_loan_id;
	}
	public Integer getTransunion_bc_score() {
		return transunion_bc_score;
	}
	public void setTransunion_bc_score(Integer transunion_bc_score) {
		this.transunion_bc_score = transunion_bc_score;
	}
	public double getAmount_founded() {
		return amount_founded;
	}
	public void setAmount_founded(double amount_founded) {
		this.amount_founded = amount_founded;
	}
	public int getVerification_score() {
		return verification_score;
	}
	public void setVerification_score(int verification_score) {
		this.verification_score = verification_score;
	}
	public int getInvestors_number() {
		return investors_number;
	}
	public void setInvestors_number(int investors_number) {
		this.investors_number = investors_number;
	}
	public int getDays_online() {
		return days_online;
	}
	public void setDays_online(int days_online) {
		this.days_online = days_online;
	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	public int getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
	}
	public char getFunding_type() {
		return funding_type;
	}
	public void setFunding_type(char funding_type) {
		this.funding_type = funding_type;
	}
	public BigInteger getCantInvCred() {
		return cantInvCred;
	}
	public void setCantInvCred(BigInteger cantInvCred) {
		this.cantInvCred = cantInvCred;
	}
	public BigInteger getCantInvSol() {
		return cantInvSol;
	}
	public void setCantInvSol(BigInteger cantInvSol) {
		this.cantInvSol = cantInvSol;
	}
	
	 @Override
		public boolean equals(Object obj)
		 {
			
			if((obj == null) || (obj.getClass() != this.getClass()))
			{
		        return false;
			}
			
			ProyectLoan guest = (ProyectLoan) obj;
			
			if(proyectloanPk == null || guest.getProyectloanPk() == null)
			{
				return false;
			}
			
	        return proyectloanPk.getCompany_id()      == guest.getProyectloanPk().getCompany_id()
	             && proyectloanPk.getProspectus_id()   == guest.getProyectloanPk().getProspectus_id()
	             && proyectloanPk.getProyect_id()      == guest.getProyectloanPk().getProyect_id()
	             && proyectloanPk.getProyect_loan_id() == guest.getProyectloanPk().getProyect_loan_id();
		}
	 
	 public Long getDaysLeft() 
		{
			if(approval_date != null)
			{
				ConvertCalendar cv      = new ConvertCalendar(this.approval_date);
				ConvertCalendar convert = new ConvertCalendar(new Date());
				
				Long fechaDeAlta = cv.toBvLong();
			    Long limitDate = fechaDeAlta + days_online;
			    
			    Long dateNow = convert.toBvLong();
			    Long daysLeft = limitDate - dateNow;
			    
			    if(daysLeft<=0) 
			    {
			    	return (long) 0;
			    } else {
			    	return daysLeft;
			    }
			    
			} else {
				
				return 0L;
			}
		}
	 
	 
	 public Double getAmmountLeft()
		{
			if((this.ammount-this.amount_founded)<0.00) return 0.00;
			return (this.ammount-this.amount_founded);
		}
	 
	 
	 public Double getBottomPorcent()
		{
			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
			simbolo.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
			Double valor = (((100*this.ammount)-100*(this.ammount-this.amount_founded))/this.ammount);
			
			if(valor > 100.00) 
			{
				return 100.00;
			}
			
			return Double.valueOf(formateador.format(valor)); 
		}
		
		public Double getBottomPorcentParametrized(String IFValueFunding)
		{
			Double ammountFoundedByI = Double.valueOf(IFValueFunding);
			DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
			simbolo.setDecimalSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
			Double valor = (((100*this.ammount)-100*(this.ammount-ammountFoundedByI))/this.ammount);
			
			if(valor > 100.00) 
			{
				return 100.00;
			}
			
			return Double.valueOf(formateador.format(valor)); 
		}
		
		public String getBarPorcentTotal()
		{
			return "width: " + getBottomPorcent().toString() + "%;";
		}

		public String checkAmountAndDaysOnline()
		{
			if(getDays_online() <= 0)
			{
				if(this.getBottomPorcent() >= 80.0)
				{
					return "1";
					
				} else {
					
					return "0";
				}
			}
			
			if(getAmount_founded() >= getAmmount())
			{
				return "1";
				
			} else {
				
				return "0";
			}
		}
		
		public boolean getEnabledBottomDetail() 
		{
			//Aqui hay que checar que el usuario si a fondeado en el proyecto en contexto para habilitar o deshabilitar el boton
			
			if(amount_founded > 0.00)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public Double getInvestment_bite2(String saldoActual) 
		{
			if(getAmmountLeft() < investment_bite)
			{
				return this.getAmmountLeft();
			}
			
			if(Double.parseDouble(saldoActual) < this.investment_bite)
			{
				return Double.parseDouble(saldoActual);
				
			} else {
				
				return investment_bite;
			}
		}
		
		public char getVerificationClass()
		{
			return (char)(verification_score+65);
		}
		
		public String getVerificationRange()
		{
			switch(this.getVerificationClass())
			{
				case 'A': return "Muy Baja";
				case 'B': return "Baja"; 
				case 'C': return "Media"; 
				case 'D': return "Alta";
				
				default:return "unknown";
			}
		}
		public StatusProyectCat getStatusProyect() {
			return statusProyect;
		}
		public void setStatusProyect(StatusProyectCat statusProyect) {
			this.statusProyect = statusProyect;
		}
		public Term getTerm() {
			return term;
		}
		public void setTerm(Term term) {
			this.term = term;
		}
	
}
