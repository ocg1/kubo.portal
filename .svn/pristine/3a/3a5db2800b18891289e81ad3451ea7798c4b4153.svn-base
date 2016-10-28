package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_term")
public class Term implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TermPK termPK;
	@Column
	String name;
	@Column
	int months;
	
	public Term(){
		
	}

	public TermPK getTermPK() {
		return termPK;
	}

	public void setTermPK(TermPK termPK) {
		this.termPK = termPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}
	
	
}
