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
@Table(name="pr_info_notification")
public class InfoNotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 		info_notification_id; //` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private Integer 	company_id; //` TINYINT(3) UNSIGNED NULL,
	@Column
	private Integer 	prospectus_id; //` INT(10) UNSIGNED NULL,
	@Column
	private Integer 	proyect_loan_id; //` INT(10) UNSIGNED NULL,
	@Column
	private Integer 	info_screen_id; //` INT(10) UNSIGNED NULL,
	@Column
	private Date 		viewed_date; //` DATETIME NULL,
	
	
	public int getInfo_notification_id() {
		return info_notification_id;
	}
	public void setInfo_notification_id(int info_notification_id) {
		this.info_notification_id = info_notification_id;
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
	public Integer getInfo_screen_id() {
		return info_screen_id;
	}
	public void setInfo_screen_id(Integer info_screen_id) {
		this.info_screen_id = info_screen_id;
	}
	public Date getViewed_date() {
		return viewed_date;
	}
	public void setViewed_date(Date viewed_date) {
		this.viewed_date = viewed_date;
	}
	
}
