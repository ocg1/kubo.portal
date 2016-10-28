package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_frequency")
public class Frequency implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private FrequencyPK frequencyPK;
	@Column
	private String name;
	@Column
	private int days;
	
	public Frequency(){
		
	}

	public FrequencyPK getFrequencyPK() {
		return frequencyPK;
	}

	public void setFrecuencyPK(FrequencyPK frequencyPK) {
		this.frequencyPK = frequencyPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}	

}
