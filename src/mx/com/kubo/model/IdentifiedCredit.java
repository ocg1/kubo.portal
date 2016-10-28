package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="gn_identified_credit")
public class IdentifiedCredit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IdentifiedCreditPK pk;
	
	@Column
	private String mx_solicitud_buro;
	@Column
	private String credit_num;
	@Column
	private String original_total_ammount;
	@Column
	private String original_payment_ammount;
	@Column
	private String actual_entity;
	@Column
	private String actual_frequency;
	@Column
	private String actual_total_ammount;
	@Column
	private String actual_total_payment;
	@Column
	private String posible_entity;
	
	public IdentifiedCreditPK getPk() {
		return pk;
	}
	public void setPk(IdentifiedCreditPK pk) {
		this.pk = pk;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public String getCredit_num() {
		return credit_num;
	}
	public void setCredit_num(String credit_num) {
		this.credit_num = credit_num;
	}
	public String getOriginal_total_ammount() {
		return original_total_ammount;
	}
	public void setOriginal_total_ammount(String original_total_ammount) {
		this.original_total_ammount = original_total_ammount;
	}
	public String getOriginal_payment_ammount() {
		return original_payment_ammount;
	}
	public void setOriginal_payment_ammount(String original_payment_ammount) {
		this.original_payment_ammount = original_payment_ammount;
	}
	public String getActual_entity() {
		return actual_entity;
	}
	public void setActual_entity(String actual_entity) {
		this.actual_entity = actual_entity;
	}
	public String getActual_frequency() {
		return actual_frequency;
	}
	public void setActual_frequency(String actual_frequency) {
		this.actual_frequency = actual_frequency;
	}
	public String getActual_total_ammount() {
		return actual_total_ammount;
	}
	public void setActual_total_ammount(String actual_total_ammount) {
		this.actual_total_ammount = actual_total_ammount;
	}
	public String getActual_total_payment() {
		return actual_total_payment;
	}
	public void setActual_total_payment(String actual_total_payment) {
		this.actual_total_payment = actual_total_payment;
	}
	public String getPosible_entity() {
		return posible_entity;
	}
	public void setPosible_entity(String posible_entity) {
		this.posible_entity = posible_entity;
	}
	

}
