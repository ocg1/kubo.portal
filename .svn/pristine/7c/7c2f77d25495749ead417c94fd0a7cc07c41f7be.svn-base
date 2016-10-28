package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_econ_sector_cat")
public class EconSectorCat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EconSectorCatPK econSectorCatPK;
	@Column
	private String description;
	@Column
	private char score;
	
	public EconSectorCat(){
		
	}

	public EconSectorCatPK getEconSectorCatPK() {
		return econSectorCatPK;
	}

	public void setEconSectorCatPK(EconSectorCatPK econSectorCatPK) {
		this.econSectorCatPK = econSectorCatPK;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getScore() {
		return score;
	}

	public void setScore(char score) {
		this.score = score;
	}
	
}
