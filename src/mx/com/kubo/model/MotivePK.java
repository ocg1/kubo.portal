package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MotivePK implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int motive_id;
	@Column
	private Integer company_id;
	
	
	public int getMotive_id() {
		return motive_id;
	}
	public void setMotive_id(int motive_id) {
		this.motive_id = motive_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
	
}
