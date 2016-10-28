package mx.com.kubo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pr_qr_access")
public class QrAccess {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int qr_id;
	
	@Column
	private Date qr_date;
	@Column
	private String qr_param;
	
	public int getQr_id() {
		return qr_id;
	}
	public void setQr_id(int qr_id) {
		this.qr_id = qr_id;
	}
	public Date getQr_date() {
		return qr_date;
	}
	public void setQr_date(Date qr_date) {
		this.qr_date = qr_date;
	}
	public String getQr_param() {
		return qr_param;
	}
	public void setQr_param(String qr_param) {
		this.qr_param = qr_param;
	}
	
}
