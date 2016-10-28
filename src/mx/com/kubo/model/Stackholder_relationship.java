package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="gn_stackholder_relationship")
public class Stackholder_relationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Stackholder_relationshipPK pk;
	@Column
	private String first_name;	//varchar(45)	NO
	@Column
	private String middle_name;	//varchar(45)	YES
	@Column
	private String father_last_name;//	varchar(45)	NO
	@Column
	private String mother_last_name;//	varchar(45)	YES
	@Column
	private Date date_of_birth;//	date	NO
	@Column
	private String mx_rfc;//	varchar(13)	YES
	@Column
	private Integer related_to;//	"int(10) unsigned"	YES
	@Column
	private String relationship_type;//	varchar(100)	YES
	@Column
	private Character is_employee;//	char(1)	NO
	@Column
	private Character is_enabled;//	char(1)	NO
	@Column
	private String mx_curp;//	varchar(18)	YES	
	
	public Stackholder_relationshipPK getPk() {
		return pk;
	}
	public void setPk(Stackholder_relationshipPK pk) {
		this.pk = pk;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getFather_last_name() {
		return father_last_name;
	}
	public void setFather_last_name(String father_last_name) {
		this.father_last_name = father_last_name;
	}
	public String getMother_last_name() {
		return mother_last_name;
	}
	public void setMother_last_name(String mother_last_name) {
		this.mother_last_name = mother_last_name;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getMx_rfc() {
		return mx_rfc;
	}
	public void setMx_rfc(String mx_rfc) {
		this.mx_rfc = mx_rfc;
	}
	public Integer getRelated_to() {
		return related_to;
	}
	public void setRelated_to(Integer related_to) {
		this.related_to = related_to;
	}
	public String getRelationship_type() {
		return relationship_type;
	}
	public void setRelationship_type(String relationship_type) {
		this.relationship_type = relationship_type;
	}
	public Character getIs_employee() {
		return is_employee;
	}
	public void setIs_employee(Character is_employee) {
		this.is_employee = is_employee;
	}
	public Character getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(Character is_enabled) {
		this.is_enabled = is_enabled;
	}
	public String getMx_curp() {
		return mx_curp;
	}
	public void setMx_curp(String mx_curp) {
		this.mx_curp = mx_curp;
	}
	
	public String NombreCompletoNPM(){
		String nombre ="";
		
		if(first_name!=null && first_name.trim().length()>0 )
			nombre += first_name;
		
		if(middle_name!=null && middle_name.trim().length()>0 )
			nombre += " "+middle_name;
		if(father_last_name!=null && father_last_name.trim().length()>0 )
			nombre += " "+father_last_name;
		if(mother_last_name!=null && mother_last_name.trim().length()>0 )
			nombre += " "+mother_last_name;
		
		return nombre;
	}
	
}
