package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "pr_system_param")
public class SystemParam  
implements Serializable 
{
	private static final long serialVersionUID = 1737305475384735949L;

	@EmbeddedId 
	private SystemParamPK pk;
	
	@Column private String description;
	@Column private String value;
	
	public SystemParam()
	{
		super();
	}
	
	public SystemParamPK getPk() {
		return pk;
	}
	public void setPk(SystemParamPK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
