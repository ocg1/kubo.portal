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
@Table(name="gn_inegi_econ_activity_cat")
public class InegiEconActivityCat implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private InegiEconActivityCatPK inegiEconActivityCatPK;
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "econ_sector_id", referencedColumnName = "econ_sector_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private EconSectorCat sector_econ;
	
	
	@Column	
	private String description;
	@Column
	private int econ_sector_id;
	@Column
	private int activity_code;
	
	public InegiEconActivityCat(){
		
	}

public InegiEconActivityCatPK getInegiEconActivityCatPK() {
		return inegiEconActivityCatPK;

	}
public void setInegiEconActivityCatPK(
			InegiEconActivityCatPK inegiEconActivityCatPK) {
		this.inegiEconActivityCatPK = inegiEconActivityCatPK;
	}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getEcon_sector_id() {
	return econ_sector_id;
}

public void setEcon_sector_id(int econ_sector_id) {
	this.econ_sector_id = econ_sector_id;
}

public int getActivity_code() {
	return activity_code;
}

public void setActivity_code(int activity_code) {
	this.activity_code = activity_code;
}

public EconSectorCat getSector_econ() {
	return sector_econ;
}

public void setSector_econ(EconSectorCat sector_econ) {
	this.sector_econ = sector_econ;
}

}
