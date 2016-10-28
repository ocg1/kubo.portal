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
@Table(name="gn_signature")
public class Signature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int signature_id;
	@Column
	private int company_id;
	@Column
	private int prospectus_id;
	
	@Column
	private Integer proyect_loan_id;
	
	@Column
	private String serialize_str;
	@Column
	private String data_url_str;
	@Column
	private int file_id;
	@Column
	private Date signature_date;
	@Column
	private String user_agent;
	@Column
	private String ipaddress;
	@Column
	private String location;
	
	
	public int getSignature_id() {
		return signature_id;
	}
	public void setSignature_id(int signature_id) {
		this.signature_id = signature_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getSerialize_str() {
		return serialize_str;
	}
	public void setSerialize_str(String serialize_str) {
		this.serialize_str = serialize_str;
	}
	public String getData_url_str() {
		return data_url_str;
	}
	public void setData_url_str(String data_url_str) {
		this.data_url_str = data_url_str;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public Date getSignature_date() {
		return signature_date;
	}
	public void setSignature_date(Date signature_date) {
		this.signature_date = signature_date;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
  

	
}
