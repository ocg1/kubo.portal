package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gn_neighborhood")
public class NeighborhoodCat implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "town_id",    referencedColumnName = "town_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private TownCat delegMunicipio;
	
	@EmbeddedId private NeighborhoodCatPK neighborhoodCatPK;
	
	@Column private String neigh_type_id;
	@Column private String name;
	@Column private String zip_code;
	
	@Column private int town_id;	
	@Column private int neigh_zone_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZip_code() {
		return zip_code;
	}


	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}


	public int getTown_id() {
		return town_id;
	}

	public void setTown_id(int town_id) {
		this.town_id = town_id;
	}

	public String getNeigh_type_id() {
		return neigh_type_id;
	}

	public void setNeigh_type_id(String neigh_type_id) {
		this.neigh_type_id = neigh_type_id;
	}

	public int getNeigh_zone_id() {
		return neigh_zone_id;
	}

	public void setNeigh_zone_id(int neigh_zone_id) {
		this.neigh_zone_id = neigh_zone_id;
	}


	public NeighborhoodCatPK getNeighborhoodCatPK() {
		return neighborhoodCatPK;
	}

	public void setNeighborhoodCatPK(NeighborhoodCatPK neighborhoodCatPK) {
		this.neighborhoodCatPK = neighborhoodCatPK;
	}

	public TownCat getDelegMunicipio() {
		return delegMunicipio;
	}

	public void setDelegMunicipio(TownCat delegMunicipio) {
		this.delegMunicipio = delegMunicipio;
	}

}
