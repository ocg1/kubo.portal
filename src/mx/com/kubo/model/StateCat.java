package mx.com.kubo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="gn_state")
public class StateCat implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private StateCatPK stateCatPK;
	
	@Column
	private String name;
	@Column
	private String bc_key;
	@Column
	private String is_enabled;
	
	@OneToMany(mappedBy="estados")
	private List<TownCat> delegMunicipios;
	
	public StateCat(){
		
	}

	public StateCatPK getStateCatPK() {
		return stateCatPK;
	}

	public void setStateCatPK(StateCatPK stateCatPK) {
		this.stateCatPK = stateCatPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBc_key() {
		return bc_key;
	}

	public void setBc_key(String bc_key) {
		this.bc_key = bc_key;
	}
	
	public List<TownCat> getDelegMunicipios() {
		return delegMunicipios;
	}

	public void setDelegMunicipios(List<TownCat> delegMunicipios) {
		this.delegMunicipios = delegMunicipios;
	}

	public String getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
	
}
