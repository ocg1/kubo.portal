package mx.com.kubo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="gn_town")
public class TownCat implements Serializable
{
	private static final long serialVersionUID = 1854802842183622530L;

	@EmbeddedId private TownCatPK townCatPK;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "state_id",   referencedColumnName = "state_id",   insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private StateCat estados;

	@Column private String name;
	@Column private String type;
	
	@Column private int state_id;
	@Column private int safi_town_id;
	
	@OneToMany(mappedBy="delegMunicipio")
	private List<NeighborhoodCat> asentamiento;
		
	public TownCat(){
		
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getState_id() {
		return state_id;
	}


	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	


	public TownCatPK getTownCatPK() {
		return townCatPK;
	}


	public void setTownCatPK(TownCatPK townCatPK) {
		this.townCatPK = townCatPK;
	}


	public List<NeighborhoodCat> getAsentamiento() {
		return asentamiento;
	}

	public void setAsentamiento(List<NeighborhoodCat> asentamiento) {
		this.asentamiento = asentamiento;
	}

	public StateCat getEstados() {
		return estados;
	}

	public void setEstados(StateCat estados) {
		this.estados = estados;
	}


	public int getSafi_town_id() {
		return safi_town_id;
	}


	public void setSafi_town_id(int safi_town_id) {
		this.safi_town_id = safi_town_id;
	}

}
