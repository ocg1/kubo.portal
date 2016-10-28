package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="in_investment_progress")
public class InvestmentProgress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer investment_progress_id;
	@Column
	private Integer automatic_investment_id; //` INT NOT NULL,
	@Column
	private Date investment_date; //` DATE NOT NULL,
	@Column
	private Date execute_date; 				//` DATETIME NULL,
	@Column
	private Date end_execute_date; 				//` DATETIME NULL,
	@Column
	private Integer status_progress; 		//` INT NULL,
	@Column
	private String message; 				//` VARCHAR(500) NULL,
	@Column
	private Double total_amount_invested; //` DOUBLE NULL,
	@Column
	private String filter;//` TEXT NULL,
	@Column
	private Integer num_projects;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "automatic_investment_id", referencedColumnName = "automatic_investment_id", insertable = false, updatable = false)
	}) private AutomaticInvestment automaticInvestment;
	
	
	public Date getExecute_date() {
		return execute_date;
	}
	public void setExecute_date(Date execute_date) {
		this.execute_date = execute_date;
	}
	public Integer getStatus_progress() {
		return status_progress;
	}
	public void setStatus_progress(Integer status_progress) {
		this.status_progress = status_progress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Double getTotal_amount_invested() {
		return total_amount_invested;
	}
	public void setTotal_amount_invested(Double total_amount_invested) {
		this.total_amount_invested = total_amount_invested;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public Integer getNum_projects() {
		return num_projects;
	}
	public void setNum_projects(Integer num_projects) {
		this.num_projects = num_projects;
	}
	public Date getEnd_execute_date() {
		return end_execute_date;
	}
	public void setEnd_execute_date(Date end_execute_date) {
		this.end_execute_date = end_execute_date;
	}
	public AutomaticInvestment getAutomaticInvestment() {
		return automaticInvestment;
	}
	public void setAutomaticInvestment(AutomaticInvestment automaticInvestment) {
		this.automaticInvestment = automaticInvestment;
	}
	public Integer getInvestment_progress_id() {
		return investment_progress_id;
	}
	public void setInvestment_progress_id(Integer investment_progress_id) {
		this.investment_progress_id = investment_progress_id;
	}
	public Integer getAutomatic_investment_id() {
		return automatic_investment_id;
	}
	public void setAutomatic_investment_id(Integer automatic_investment_id) {
		this.automatic_investment_id = automatic_investment_id;
	}
	public Date getInvestment_date() {
		return investment_date;
	}
	public void setInvestment_date(Date investment_date) {
		this.investment_date = investment_date;
	}
	
}
