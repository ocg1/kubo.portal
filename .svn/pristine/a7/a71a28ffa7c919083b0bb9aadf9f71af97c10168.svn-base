package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity @Table(name = "gn_employment")
public class Employment 
implements Serializable 
{	
	@EmbeddedId private EmploymentPK employmentPK;	
	
	@OneToOne
	@JoinColumns(value = {
		@JoinColumn(name = "inegi_econ_activity_id", referencedColumnName = "inegi_econ_activity_id", insertable = false, updatable = false),	       
		@JoinColumn(name = "company_id",             referencedColumnName = "company_id",             insertable = false, updatable = false)
	}) private InegiEconActivityCat inegiEconActivityCat;
	
	@OneToOne (mappedBy = "employment")
	private Address addressEmploy;
	
	@Temporal(TemporalType.DATE)
	@Column private Date capture_date;
	
	@Column private String employer;
	@Column private String position;
	@Column private String nss;
	@Column private String check_in; 
	@Column private String check_out;
	@Column private String about_my_job;
	@Column private String who_pays;
	@Column private String bmx_econ_activity_id;
	
	@Column private Double total_income;
	
	@Column private Integer company_type_employee_id;//	"tinyint(3) unsigned"
	@Column private Integer company_type_retired_id;//	"tinyint(3) unsigned"
	@Column private Integer inegi_econ_activity_id;
	@Column private Integer econ_sector_id;
	@Column private Integer contract_type_id;
	@Column private Integer other_income_id;
	@Column private Integer sector_bussines_id;
	@Column private Integer tenure;
	@Column private Integer tenure_month;
	@Column private Integer previous_job_tenure;
	@Column private Integer previous_job_tenure_month;
	@Column private Integer not_outsourcing;	
	@Column private Integer tenure_id;	

	public EmploymentPK getEmploymentPK() 
	{
		return employmentPK;
	}
	
	public final Integer getTenure_id()
	{
		return tenure_id;
	}

	public final void setTenure_id(Integer tenure_id)
	{
		if(tenure_id > 0)
		{
			this.tenure_id = tenure_id;
			
		} else {
			
			this.tenure_id = null;
		}
	}
	
	public void setEmploymentPK(EmploymentPK employmentPK) {
		this.employmentPK = employmentPK;
	}

	public Date getCapture_date() {
		return capture_date;
	}

	public void setCapture_date(Date capture_date) {
		this.capture_date = capture_date;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public Integer getInegi_econ_activity_id() {
		return inegi_econ_activity_id;
	}

	public void setInegi_econ_activity_id(Integer inegi_econ_activity_id) {
		this.inegi_econ_activity_id = inegi_econ_activity_id;
	}

	public Integer getEcon_sector_id() {
		return econ_sector_id;
	}

	public void setEcon_sector_id(Integer econ_sector_id) {
		this.econ_sector_id = econ_sector_id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Integer getPrevious_job_tenure() {
		return previous_job_tenure;
	}

	public void setPrevious_job_tenure(Integer previous_job_tenure) {
		this.previous_job_tenure = previous_job_tenure;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}


	public Integer getContract_type_id() {
		return contract_type_id;
	}

	public void setContract_type_id(Integer contract_type_id) {
		this.contract_type_id = contract_type_id;
	}

	public String getCheck_in() {
		return check_in;
	}

	public void setCheck_in(String check_in) {
		this.check_in = check_in;
	}

	public String getCheck_out() {
		return check_out;
	}

	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}

	public Double getTotal_income() {
		return total_income;
	}

	public void setTotal_income(Double total_income) {
		this.total_income = total_income;
	}

	public Integer getOther_income_id() {
		return other_income_id;
	}

	public void setOther_income_id(Integer other_income_id) {
		this.other_income_id = other_income_id;
	}

	public Integer getSector_bussines_id() {
		return sector_bussines_id;
	}

	public void setSector_bussines_id(Integer sector_bussines_id) {
		this.sector_bussines_id = sector_bussines_id;
	}

	public InegiEconActivityCat getInegiEconActivityCat() {
		return inegiEconActivityCat;
	}

	public void setInegiEconActivityCat(InegiEconActivityCat inegiEconActivityCat) {
		this.inegiEconActivityCat = inegiEconActivityCat;
	}

	public String getAbout_my_job() {
		return about_my_job;
	}

	public void setAbout_my_job(String about_my_job) {
		this.about_my_job = about_my_job;
	}

	public Integer getNot_outsourcing() {
		return not_outsourcing;
	}

	public void setNot_outsourcing(Integer not_outsourcing) {
		this.not_outsourcing = not_outsourcing;
	}

	public String getWho_pays() {
		return who_pays;
	}

	public void setWho_pays(String who_pays) {
		this.who_pays = who_pays;
	}
	
	public Integer getCompany_type_employee_id() {
		return company_type_employee_id;
	}
	
	public void setCompany_type_employee_id(Integer company_type_employee_id) {
		this.company_type_employee_id = company_type_employee_id;
	}
	
	public Integer getCompany_type_retired_id() {
		return company_type_retired_id;
	}
	
	public void setCompany_type_retired_id(Integer company_type_retired_id) {
		this.company_type_retired_id = company_type_retired_id;
	}

	public Address getAddressEmploy() {
		return addressEmploy;
	}

	public void setAddressEmploy(Address addressEmploy) {
		this.addressEmploy = addressEmploy;
	}

	public Integer getTenure_month() {
		return tenure_month;
	}

	public Integer getPrevious_job_tenure_month() {
		return previous_job_tenure_month;
	}

	public void setTenure_month(Integer tenure_month) {
		this.tenure_month = tenure_month;
	}

	public void setPrevious_job_tenure_month(Integer previous_job_tenure_month) {
		this.previous_job_tenure_month = previous_job_tenure_month;
	}

	public String getBmx_econ_activity_id() {
		return bmx_econ_activity_id;
	}

	public void setBmx_econ_activity_id(String bmx_econ_activity_id) {
		this.bmx_econ_activity_id = bmx_econ_activity_id;
	}
	
}
