package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery (
		
	name  = "queryAddChangeControl",
	query = "SELECT MAX(c.change_controlPK.change_id) FROM Change_control c "
		  + "WHERE c.change_controlPK.prospectus_id = ? "
		  + "AND   c.change_controlPK.company_id    = ?"
)

@Entity @Table(name = "gn_change_control")
public class Change_control 
implements Serializable 
{
	private static final long serialVersionUID = 3094615865173144347L;

	@EmbeddedId private Change_controlPK change_controlPK;
	
	@OneToOne
	@JoinColumns (
		value = {
	        @JoinColumn(name = "change_prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",           referencedColumnName = "company_id",    insertable = false, updatable = false)
	    }
	) private NaturalPerson person;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column private Date change_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column private Date focus_date;
	
	@Column private Integer change_prospectus_id;
	@Column private Integer field_type_id;
	
	@Column private String original_value;
	@Column private String new_value;
	@Column private String afected_table;
	@Column private String field;
	@Column private String afected_table_type;	
	@Column private String comments;
	@Column private String ip;
	
	public Change_controlPK getChange_controlPK() 
	{
		return change_controlPK;
	}
	
	public void setChange_controlPK(Change_controlPK change_controlPK) 
	{
		this.change_controlPK = change_controlPK;
	} 
	
	public Date getChange_date() {
		return change_date;
	}
	
	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}
	
	public Date getFocus_date() {
		return focus_date;
	}

	public void setFocus_date(Date focus_date) {
		this.focus_date = focus_date;
	}

	public Integer getChange_prospectus_id() {
		return change_prospectus_id;
	}
	
	public void setChange_prospectus_id(Integer change_prospectus_id) {
		this.change_prospectus_id = change_prospectus_id;
	}
	
	public String getOriginal_value() {
		return original_value;
	}
	
	public void setOriginal_value(String original_value) {
		this.original_value = original_value;
	}
	
	public String getNew_value() {
		return new_value;
	}
	
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
	
	public String getAfected_table() {
		return afected_table;
	}
	
	public void setAfected_table(String afected_table) {
		this.afected_table = afected_table;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getAfected_table_type() {
		return afected_table_type;
	}

	public void setAfected_table_type(String afected_table_type) {
		this.afected_table_type = afected_table_type;
	}

	public Integer getField_type_id() {
		return field_type_id;
	}

	public void setField_type_id(Integer field_type_id) {
		this.field_type_id = field_type_id;
	}

	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public NaturalPerson getPerson() {
		return person;
	}
	
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	
	public String getDateChage()
	{
		String dateString;
		String nowDate;
		
		dateString = "";		
		
		if(getChange_date() != null)
		{
			nowDate = getChange_date().toString();
			
			dateString = nowDate.substring(0, nowDate.length() - 2);
		}
		
		return dateString;
	}
}
