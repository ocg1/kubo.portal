package mx.com.kubo.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ln_proyect_funding")
public class ProyectFunding  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProyectFundingPK proyectloanfundingPk;
	
	
	@Column
	private Double amount;
	@Column
	private Date funding_date;
	@Column
	private Time funding_time;
	@Column
	private int status;
	@Column
	private String kubo_score_a;
	@Column
	private Double amount_founded;
	@Column 
	private int verification_score;
	@Column
	private String solicitudFondeo;

	
	@ManyToOne(fetch=FetchType.EAGER)  
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_investor_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	    })
	private Membership member_invest;
	
	@Column
	private String kubo_score_b;
	
	
	public ProyectFunding(){
		
	}
	
	public ProyectFundingPK getProyectloanfundingPk() {
		return proyectloanfundingPk;
	}
	public void setProyectloanfundingPk(ProyectFundingPK proyectloanfundingPk) {
		this.proyectloanfundingPk = proyectloanfundingPk;
	}
	
	
	public Date getFunding_date() {
		return funding_date;
	}

	public void setFunding_date(Date funding_date) {
		this.funding_date = funding_date;
	}

	public Time getFunding_time() {
		return funding_time;
	}
	public void setFunding_time(Time funding_time) {
		this.funding_time = funding_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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



	public int getVerification_score() {
		return verification_score;
	}
	public void setVerification_score(int verification_score) {
		this.verification_score = verification_score;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount_founded() {
		return amount_founded;
	}

	public void setAmount_founded(Double amount_founded) {
		this.amount_founded = amount_founded;
	}

	public Membership getMember_invest() {
		return member_invest;
	}

	public void setMember_invest(Membership member_invest) {
		this.member_invest = member_invest;
	}
	
	

	public String getSolicitudFondeo() {
		return solicitudFondeo;
	}

	public void setSolicitudFondeo(String solicitudFondeo) {
		this.solicitudFondeo = solicitudFondeo;
	}

	
}