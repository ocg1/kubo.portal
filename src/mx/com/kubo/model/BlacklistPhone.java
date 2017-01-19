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
@Table(name="ln_blacklist_phone")
public class BlacklistPhone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 	blacklist_phone_id; //` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private String 	phone_number; //` VARCHAR(50) NULL,
	@Column
	private Date	added_date;//` DATETIME NULL,
	@Column
	private String is_enabled;
	
	public int getBlacklist_phone_id() {
		return blacklist_phone_id;
	}
	public void setBlacklist_phone_id(int blacklist_phone_id) {
		this.blacklist_phone_id = blacklist_phone_id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
