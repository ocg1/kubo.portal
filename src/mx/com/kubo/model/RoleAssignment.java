package mx.com.kubo.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gn_role_assignment")
public class RoleAssignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RoleAssignmentPK pk;
	@Column
	private Date date_created;
	@Column
	private Date date_application;
	@Column
	private String status_id;
	@Column
	private Integer created_by;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
	    })	
	private Role role;
	
	public RoleAssignmentPK getPk() {
		return pk;
	}
	public void setPk(RoleAssignmentPK pk) {
		this.pk = pk;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public Date getDate_application() {
		return date_application;
	}
	public void setDate_application(Date date_application) {
		this.date_application = date_application;
	}
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getDate_createdStr(){
		
		SimpleDateFormat formateador =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","ES"));
		
		if(date_created != null){
			
			return formateador.format(date_created);
					
		}else{
			
			return "No asignada";
			
		}
		
	}
	
	public String getDate_applicationStr(){
		
		SimpleDateFormat formateador =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","ES"));
		
		if(date_application != null){
			
			return formateador.format(date_application);
					
		}else{
			
			return "No asignada";
			
		}
		
	}

}
