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
@Table(name="ln_blacklist_ip")
public class BlacklistIp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 	blacklist_ip_id; //` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private String ipaddress;//` VARCHAR(100) NULL,
	@Column
	private Date added_date;//` DATETIME NULL,
	@Column
	private String is_enabled;//` VARCHAR(1) NULL,
	
	
	public int getBlacklist_ip_id() {
		return blacklist_ip_id;
	}
	public void setBlacklist_ip_id(int blacklist_ip_id) {
		this.blacklist_ip_id = blacklist_ip_id;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public Date getAdded_date() {
		return added_date;
	}
	public void setAdded_date(Date added_date) {
		this.added_date = added_date;
	}
	public String getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
	
}
