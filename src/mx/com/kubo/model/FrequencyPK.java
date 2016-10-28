package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FrequencyPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	private int frequency_id;
	@Column
	private int company_id;
	
	public FrequencyPK(){
		
	}

	public int getFrequency_id() {
		return frequency_id;
	}

	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	

}
