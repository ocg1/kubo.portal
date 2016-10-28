package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pr_token")
public class TokenDB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private String token_id;
	@Column
	private String token_gen;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	@Column
	private String msb_key;
	@Column
	private String lsb_key;
	@Column
	private String token_result;
	@Column
	private Date creation_date;
	@Column
	private String is_used;
	
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getToken_result() {
		return token_result;
	}
	public void setToken_result(String token_result) {
		this.token_result = token_result;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getIs_used() {
		return is_used;
	}
	public void setIs_used(String is_used) {
		this.is_used = is_used;
	}
	public String getMsb_key() {
		return msb_key;
	}
	public void setMsb_key(String msb_key) {
		this.msb_key = msb_key;
	}
	public String getLsb_key() {
		return lsb_key;
	}
	public void setLsb_key(String lsb_key) {
		this.lsb_key = lsb_key;
	}
	public String getToken_gen() {
		return token_gen;
	}
	public void setToken_gen(String token_gen) {
		this.token_gen = token_gen;
	}
	
}
