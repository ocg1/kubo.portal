package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_pademobile_payment")
public class PademobilePayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PademobilePaymentPK pk;
	@Column
	private Integer prospectus_id;
	@Column
	private Integer proyect_loan_id;
	@Column
	private String safi_credit_id;
	@Column
	private Date payment_date;
	@Column
	private Double ammount;
	@Column
	private String status;
	@Column
	private String message;
	@Column
	private String cod_tran;
	@Column
	private Integer proyect_id;
	@Column
	private String accounted;
	
	public PademobilePaymentPK getPk() {
		return pk;
	}
	public void setPk(PademobilePaymentPK pk) {
		this.pk = pk;
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
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCod_tran() {
		return cod_tran;
	}
	public void setCod_tran(String cod_tran) {
		this.cod_tran = cod_tran;
	}
	public Integer getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(Integer proyect_id) {
		this.proyect_id = proyect_id;
	}
	public String getAccounted() {
		return accounted;
	}
	public void setAccounted(String accounted) {
		this.accounted = accounted;
	}

}
