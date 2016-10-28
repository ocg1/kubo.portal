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
@Table(name="ln_proyect")
public class Proyect implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "purpose_id", referencedColumnName = "purpose_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	})
	private Purpose purpose;
	
	
	
	@EmbeddedId
	private ProyectPK proyectoPk;
	@Column
	String	name;
	@Column
	Integer	type_id;
	@Column
	Integer	purpose_id;
	@Column
	String	partner_id;
	@Column
	String	tagline;
	@Column
	String	logo;
	@Column
	String	image;
	@Column
	String	description;
	@Column
	String	goal;
	@Column
	String	benefits;
	@Column
	String	consecuences;
	@Column
	String	reason;
	@Column
	String logo2;
	@Column
	String logo3;
	@Column
	String metadata1;
	@Column
	String metadata2;
	@Column
	String metadata3;
	@Column
	String why_us;
	@Column
	String owe_family;
	@Column
	String owe_family_text;
	@Column
	String other_debts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getPurpose_id() {
		return purpose_id;
	}
	public void setPurpose_id(Integer purpose_id) {
		this.purpose_id = purpose_id;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getLogo() {
		/*System.out.println("CADENA DE RUTA: "+this.logo);
		String sfichero = getRealPath+this.logo;
		File fichero = new File(sfichero);
		if(!fichero.exists()){
			return "/img/sinimagen.jpg";
		}
		else*/ return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
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
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getConsecuences() {
		return consecuences;
	}
	public void setConsecuences(String consecuences) {
		this.consecuences = consecuences;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public ProyectPK getProyectoPk() {
		return proyectoPk;
	}
	public void setProyectoPk(ProyectPK proyectoPk) {
		this.proyectoPk = proyectoPk;
	}
	public Purpose getPurpose() {
		return purpose;
	}
	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}
	public String getLogo2() {
		return logo2;
	}
	public void setLogo2(String logo2) {
		this.logo2 = logo2;
	}
	public String getLogo3() {
		return logo3;
	}
	public void setLogo3(String logo3) {
		this.logo3 = logo3;
	}
	public String getMetadata1() {
		return metadata1;
	}
	public void setMetadata1(String metadata1) {
		this.metadata1 = metadata1;
	}
	public String getMetadata2() {
		return metadata2;
	}
	public void setMetadata2(String metadata2) {
		this.metadata2 = metadata2;
	}
	public String getMetadata3() {
		return metadata3;
	}
	public void setMetadata3(String metadata3) {
		this.metadata3 = metadata3;
	}
	public String getWhy_us() {
		return why_us;
	}
	public void setWhy_us(String why_us) {
		this.why_us = why_us;
	}
	public String getOwe_family() {
		return owe_family;
	}
	public void setOwe_family(String owe_family) {
		this.owe_family = owe_family;
	}
	public String getOwe_family_text() {
		return owe_family_text;
	}
	public void setOwe_family_text(String owe_family_text) {
		this.owe_family_text = owe_family_text;
	}
	public String getOther_debts() {
		return other_debts;
	}
	public void setOther_debts(String other_debts) {
		this.other_debts = other_debts;
	}
	
	
	
}
