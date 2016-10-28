package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InfoScreenPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private int company_id; //` INT UNSIGNED NOT NULL,
	@Column
	private int info_screen_id; //
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getInfo_screen_id() {
		return info_screen_id;
	}
	public void setInfo_screen_id(int info_screen_id) {
		this.info_screen_id = info_screen_id;
	}
	
	
	
}
