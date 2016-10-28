package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "gn_blocked_person")
public class BlockedPerson 
implements Serializable 
{
	private static final long serialVersionUID = 3888357313794545452L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blocked_id;
	
	@Column private String full_name;
	@Column private String mx_rfc;
	@Column private String mx_oficio;
	@Column private String mx_expediente;
	@Column private String additional_data;
	
	@Column private Date blocked_date;

	public Integer getBlocked_id() {
		return blocked_id;
	}

	public void setBlocked_id(Integer blocked_id) {
		this.blocked_id = blocked_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getMx_rfc() {
		return mx_rfc;
	}

	public void setMx_rfc(String mx_rfc) {
		this.mx_rfc = mx_rfc;
	}

	public String getMx_oficio() {
		return mx_oficio;
	}

	public void setMx_oficio(String mx_oficio) {
		this.mx_oficio = mx_oficio;
	}

	public String getMx_expediente() {
		return mx_expediente;
	}

	public void setMx_expediente(String mx_expediente) {
		this.mx_expediente = mx_expediente;
	}

	public String getAdditional_data() {
		return additional_data;
	}

	public void setAdditional_data(String additional_data) {
		this.additional_data = additional_data;
	}

	public Date getBlocked_date() {
		return blocked_date;
	}

	public void setBlocked_date(Date blocked_date) {
		this.blocked_date = blocked_date;
	}
}
