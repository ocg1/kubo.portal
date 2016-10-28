package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="gn_relationship")
public class RelationShip implements Serializable{

	private static final long serialVersionUID = 1L;	
	@EmbeddedId
	private RelationShipPK relationShipPk;	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_destiny_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private Prospectus prospectus;
	@Column
	private String relationship;
	
	public RelationShip(){
		
	}

	public RelationShipPK getRelationShipPk() {
		return relationShipPk;
	}

	public Prospectus getProspectus() {
		return prospectus;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationShipPk(RelationShipPK relationShipPk) {
		this.relationShipPk = relationShipPk;
	}

	public void setProspectus(Prospectus prospectus) {
		this.prospectus = prospectus;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}	
	

}
