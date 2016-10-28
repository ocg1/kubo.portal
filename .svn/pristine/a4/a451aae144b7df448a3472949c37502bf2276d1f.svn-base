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
@Table(name="gn_role_function")
public class RoleFunction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleFunctionPK pk;
	
	@Column
	private String comments;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "function_id", referencedColumnName = "function_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private Function function;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private Role role;
	
	public RoleFunctionPK getPk() {
		return pk;
	}
	
	public void setPk(RoleFunctionPK pk) {
		this.pk = pk;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
