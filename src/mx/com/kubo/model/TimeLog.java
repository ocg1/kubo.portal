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
@Table(name="pr_time_log")
public class TimeLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long		time_log_id; //` INT(10) UNSIGNED NOT NULL,
	@Column
	private Integer		prospectus_id; //` INT(10) UNSIGNED NULL,
	@Column
	private Integer		prospectus_id_viewed; //` INT(10) UNSIGNED NULL,
	@Column
	private String 		identify_str; //` VARCHAR(500) NULL,
	@Column
	private String		description; //` VARCHAR(500) NULL,
	@Column
	private Date		init_time; //` DATETIME NULL,
	@Column
	private Date		final_time; //` DATETIME NULL,
	@Column
	private String total_lapse; //` TIME NULL,
	@Column
	private String	ip_address; //` VARCHAR(45) NULL,
	
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getProspectus_id_viewed() {
		return prospectus_id_viewed;
	}
	public void setProspectus_id_viewed(Integer prospectus_id_viewed) {
		this.prospectus_id_viewed = prospectus_id_viewed;
	}
	public String getIdentify_str() {
		return identify_str;
	}
	public void setIdentify_str(String identify_str) {
		this.identify_str = identify_str;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getInit_time() {
		return init_time;
	}
	public void setInit_time(Date init_time) {
		this.init_time = init_time;
	}
	public Date getFinal_time() {
		return final_time;
	}
	public void setFinal_time(Date final_time) {
		this.final_time = final_time;
	}
	
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getTotal_lapse() {
		return total_lapse;
	}
	public void setTotal_lapse(String total_lapse) {
		this.total_lapse = total_lapse;
	}
	public void setTime_log_id(long time_log_id) {
		this.time_log_id = time_log_id;
	}
	
}
