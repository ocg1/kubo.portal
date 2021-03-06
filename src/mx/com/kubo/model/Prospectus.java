package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "gn_prospectus")
@NamedQuery(name = "Prospectus.findAll", query = "SELECT p FROM Prospectus p") 
public final class Prospectus 
implements Serializable
{
	private static final long serialVersionUID = -1476022266075588805L;

	@EmbeddedId @AttributeOverride(name = "prospectus_id", column = @Column(name = "prospectus_id"))
	private ProspectusPK prospectusPK;
				
	@Column private String tagline;	
	@Column private String image;	
	@Column private String description;		
	@Column private String same_address;
	@Column private String tracking_id;
	@Column private String metadata;	
	
	@Column(name = "person_type_id")
	private Character 	person_type;  //Fisica  Mora  (A) persona fisica con actividad empresarial
	
	@Column private Character	area; // L  credito   I inversion
	
	@Column private Integer  safi_prospectus_id;
	@Column private Integer electronic_statement;
	@Column private Integer role_id;
	
	@Column private Integer infusion_id;
	
	@Column private Integer hs_vid;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "role_id",    referencedColumnName = "role_id",    insertable = false, updatable = false)
	}) private Role role;

	public Character getPerson_type() {
		return person_type;
	}
	public void setPerson_type(Character person_type) {
		this.person_type = person_type;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProspectusPK getProspectusPK() {
		return prospectusPK;
	}
	public void setProspectusPK(ProspectusPK prospectusPK) {
		this.prospectusPK = prospectusPK;
	}

	public Integer getSafi_prospectus_id() {
		return safi_prospectus_id;
	}

	public void setSafi_prospectus_id(Integer safi_prospectus_id) {
		this.safi_prospectus_id = safi_prospectus_id;
	}

	public String getSame_address() {
		return same_address;
	}

	public void setSame_address(String same_address) {
		this.same_address = same_address;
	}

	public Integer getElectronic_statement() {
		return electronic_statement;
	}

	public void setElectronic_statement(Integer electronic_statement) {
		this.electronic_statement = electronic_statement;
	}
	public Character getArea() {
		return area;
	}
	public void setArea(Character area) {
		this.area = area;
	}
	public String getTracking_id() {
		return tracking_id;
	}
	
	public void setTracking_id(String tracking_id) 
	{
		this.tracking_id = tracking_id;
	}

	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Integer getRole_id() {
		return role_id;
	}



	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getInfusion_id() {
		return infusion_id;
	}
	public void setInfusion_id(Integer infusion_id) {
		this.infusion_id = infusion_id;
	}
	public Integer getHs_vid() {
		return hs_vid;
	}
	public void setHs_vid(Integer hs_vid) {
		this.hs_vid = hs_vid;
	}	

}
