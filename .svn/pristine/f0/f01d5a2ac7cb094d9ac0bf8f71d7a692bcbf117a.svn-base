package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_temp_password")
public class TempPassword implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TempPasswordPK tempPassPK;
	@Column
	private String password;
	@Column
	private Date creation_date;
	@Column
	private Date valid_date;
	@Column
	private Integer prospectus_id;
	@Column
	private Character is_used;
	@Column
	private Date date_used;
	
	
	public TempPasswordPK getTempPassPK() {
		return tempPassPK;
	}
	public void setTempPassPK(TempPasswordPK tempPassPK) {
		this.tempPassPK = tempPassPK;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Date getValid_date() {
		return valid_date;
	}
	public void setValid_date(Date valid_date) {
		this.valid_date = valid_date;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Character getIs_used() {
		return is_used;
	}
	public void setIs_used(Character is_used) {
		this.is_used = is_used;
	}
	public Date getDate_used() {
		return date_used;
	}
	public void setDate_used(Date date_used) {
		this.date_used = date_used;
	}
	
	

}
