package mx.com.kubo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pr_service_calling")
public class ServiceCalling {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_calling_id;//	"int(10) unsigned"
	@Column
	private Integer company_id;//	"tinyint(3) unsigned"
	@Column
	private Integer prospectus_id;//	"int(10) unsigned"
	@Column
	private Integer proyect_loan_id;//	"int(10) unsigned"
	@Column
	private Integer proyect_id;//	"int(10) unsigned"
	@Column
	private Date acces_datetime;//	timestamp
	@Column
	private Integer status;//	"tinyint(1) unsigned"
	@Column
	private String info;//	varchar(250)
	@Column
	private String exception;//	varchar(2000)
	
	public int getService_calling_id() {
		return service_calling_id;
	}
	public void setService_calling_id(int service_calling_id) {
		this.service_calling_id = service_calling_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
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
	public Integer getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(Integer proyect_id) {
		this.proyect_id = proyect_id;
	}
	public Date getAcces_datetime() {
		return acces_datetime;
	}
	public void setAcces_datetime(Date acces_datetime) {
		this.acces_datetime = acces_datetime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
}
