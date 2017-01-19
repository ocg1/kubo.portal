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
@Table(name="ln_blacklist_password")
public class BlacklistPassword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 	blacklist_password_id; //` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private String password_str; //` VARCHAR(500) NULL,
	@Column
	private Date 	added_date; //` DATETIME NULL,
	@Column
	private String 	is_enabled;
	
	
	public int getBlacklist_password_id() {
		return blacklist_password_id;
	}
	public void setBlacklist_password_id(int blacklist_password_id) {
		this.blacklist_password_id = blacklist_password_id;
	}
	public String getPassword_str() {
		return password_str;
	}
	public void setPassword_str(String password_str) {
		this.password_str = password_str;
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
