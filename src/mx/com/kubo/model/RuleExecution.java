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
@Table(name="gn_rule_execution")
public class RuleExecution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rule_execution_id; // ` INT NOT NULL AUTO_INCREMENT,
	@Column
	private Date creation_date; // ` DATETIME NULL,
	@Column
	private Integer prospectus_id; // ` INT NULL,
	@Column
	private Integer rule_id; // ` INT NULL,
	@Column
	private String result_value; // ` VARCHAR(500) NULL,
	@Column
	private String result_text; //` VARCHAR(500) NULL,
	
	
	public Integer getRule_execution_id() {
		return rule_execution_id;
	}
	public void setRule_execution_id(Integer rule_execution_id) {
		this.rule_execution_id = rule_execution_id;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getRule_id() {
		return rule_id;
	}
	public void setRule_id(Integer rule_id) {
		this.rule_id = rule_id;
	}
	public String getResult_value() {
		return result_value;
	}
	public void setResult_value(String result_value) {
		this.result_value = result_value;
	}
	public String getResult_text() {
		return result_text;
	}
	public void setResult_text(String result_text) {
		this.result_text = result_text;
	}
	
	
	
}
