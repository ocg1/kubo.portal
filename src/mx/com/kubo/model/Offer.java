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
@Table(name="ln_offer")
public class Offer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true,updatable=false,insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int offer_id; // , int(11), NO, PRI, , auto_increment
	@Column
	private int prospectus_id; // , int(10) unsigned, YES, , , 
	@Column
	private String mx_solicitud_buro; // , varchar(10), YES, , , 
	@Column
	private int promo_id; // , int(11), YES, , , 
	@Column
	private String parameter; // , varchar(500), YES, , , 
	@Column
	private Integer offer_status_id; // , varchar(45), YES, , , 
	@Column
	private Date offer_date; // , datetime, YES, , , 
	@Column
	private Date offer_finished_date; // , datetime, YES, , , 
	@Column
	private Date showed1_date; // , datetime, YES, , , 
	@Column
	private Date accepted1_date; // , datetime, YES, , , 
	@Column
	private Date rejection1_date; // , datetime, YES, , , 
	@Column
	private Date showed2_date; // , datetime, YES, , , 
	@Column
	private Date accepted2_date; // , datetime, YES, , , 
	@Column
	private Date rejection2_date; // , datetime, YES, , , 
	@Column
	private Integer offer_rejection_motive_id; // ` INT(10) UNSIGNED NULL AFTER `option_selected`,
	@Column
	private String other_rejection_motive; //
	@Column
	private Integer analyst_prospectus_id; 
	@Column
	private String why_not;
	@Column
	private Date analyzed_date;
	
	@Column
	private String option_selected; // , datetime, YES, , , 
	
	public int getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public int getPromo_id() {
		return promo_id;
	}
	public void setPromo_id(int promo_id) {
		this.promo_id = promo_id;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public Date getOffer_date() {
		return offer_date;
	}
	public void setOffer_date(Date offer_date) {
		this.offer_date = offer_date;
	}
	public Date getOffer_finished_date() {
		return offer_finished_date;
	}
	public void setOffer_finished_date(Date offer_finished_date) {
		this.offer_finished_date = offer_finished_date;
	}
	public Date getShowed1_date() {
		return showed1_date;
	}
	public void setShowed1_date(Date showed1_date) {
		this.showed1_date = showed1_date;
	}
	public Date getAccepted1_date() {
		return accepted1_date;
	}
	public void setAccepted1_date(Date accepted1_date) {
		this.accepted1_date = accepted1_date;
	}
	public Date getRejection1_date() {
		return rejection1_date;
	}
	public void setRejection1_date(Date rejection1_date) {
		this.rejection1_date = rejection1_date;
	}
	public Date getShowed2_date() {
		return showed2_date;
	}
	public void setShowed2_date(Date showed2_date) {
		this.showed2_date = showed2_date;
	}
	public Date getAccepted2_date() {
		return accepted2_date;
	}
	public void setAccepted2_date(Date accepted2_date) {
		this.accepted2_date = accepted2_date;
	}
	public Date getRejection2_date() {
		return rejection2_date;
	}
	public void setRejection2_date(Date rejection2_date) {
		this.rejection2_date = rejection2_date;
	}
	public Integer getOffer_status_id() {
		return offer_status_id;
	}
	public void setOffer_status_id(Integer offer_status_id) {
		this.offer_status_id = offer_status_id;
	}
	public String getOption_selected() {
		return option_selected;
	}
	public void setOption_selected(String option_selected) {
		this.option_selected = option_selected;
	}
	public Integer getOffer_rejection_motive_id() {
		return offer_rejection_motive_id;
	}
	public void setOffer_rejection_motive_id(Integer offer_rejection_motive_id) {
		this.offer_rejection_motive_id = offer_rejection_motive_id;
	}
	public String getOther_rejection_motive() {
		return other_rejection_motive;
	}
	public void setOther_rejection_motive(String other_rejection_motive) {
		this.other_rejection_motive = other_rejection_motive;
	}
	public Integer getAnalyst_prospectus_id() {
		return analyst_prospectus_id;
	}
	public void setAnalyst_prospectus_id(Integer analyst_prospectus_id) {
		this.analyst_prospectus_id = analyst_prospectus_id;
	}
	public String getWhy_not() {
		return why_not;
	}
	public void setWhy_not(String why_not) {
		this.why_not = why_not;
	}
	public Date getAnalyzed_date() {
		return analyzed_date;
	}
	public void setAnalyzed_date(Date analyzed_date) {
		this.analyzed_date = analyzed_date;
	}
	
}
