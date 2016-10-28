package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_residence")
public class Residence 
implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResidencePK residencePK;
	
	@Column
	private String description;
	
	public ResidencePK getResidencePK() {
		return residencePK;
	}
	public void setResidencePK(ResidencePK residencePK) {
		this.residencePK = residencePK;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}