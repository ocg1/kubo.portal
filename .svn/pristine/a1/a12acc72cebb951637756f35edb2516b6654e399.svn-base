package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ln_purpose")
@NamedQuery(name = "Purpose.findAll", query = "SELECT p FROM Purpose p")
public class Purpose implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PurposePK purposePK;
	
	@Column
	private String	name;
	@Column
	private String description;
	@Column
	private Integer type_id;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PurposePK getPurposePK() {
		return purposePK;
	}
	public void setPurposePK(PurposePK purposePK) {
		this.purposePK = purposePK;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	
}
