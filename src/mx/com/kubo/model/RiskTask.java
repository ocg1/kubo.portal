package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ln_risk_task")
public class RiskTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int risk_task_id; //` INT NOT NULL AUTO_INCREMENT,
	@Column
	private int company_id; // ` TINYINT(3) NULL,
	@Column
	private int prospectus_id; //` INT(11) NULL,
	@Column
	private String mx_solicitud_buro; // ` VARCHAR(45) NULL,
	@Column
	private int task_id; // ` INT(10) NULL,
	@Column
	private String task_value; // ` VARCHAR(45) NULL,
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "task_id", referencedColumnName = "task_id", insertable = false, updatable = false)
	})
	private Task_Cat task;
	
	public int getRisk_task_id() {
		return risk_task_id;
	}
	public void setRisk_task_id(int risk_task_id) {
		this.risk_task_id = risk_task_id;
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
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_value() {
		return task_value;
	}
	public void setTask_value(String task_value) {
		this.task_value = task_value;
	}
	public Task_Cat getTask() {
		return task;
	}
	public void setTask(Task_Cat task) {
		this.task = task;
	}
	
}
