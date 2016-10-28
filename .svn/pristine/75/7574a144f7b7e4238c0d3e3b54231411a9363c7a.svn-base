package mx.com.kubo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_password_history")
public class PasswordHistory {
	
	@EmbeddedId
	private PasswordHistoryPK pwdhpk;
	@Column
	private String password;
	@Column
	private Date date_changed;
	
	
	public PasswordHistoryPK getPwdhpk() {
		return pwdhpk;
	}
	public void setPwdhpk(PasswordHistoryPK pwdhpk) {
		this.pwdhpk = pwdhpk;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate_changed() {
		return date_changed;
	}
	public void setDate_changed(Date date_changed) {
		this.date_changed = date_changed;
	}

}
