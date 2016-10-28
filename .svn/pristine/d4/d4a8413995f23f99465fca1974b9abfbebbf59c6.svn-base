package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pr_field")
public class Fields implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FieldsPK fieldsPK;

	@Column
	private String name;
	@Column
	private String is_mandatory;
	@Column
	private String is_visible;
	@Column
	private String is_enabled;
	@Column
	private String blocked_after_BC;
	
	public Fields(){
		
	}

	public FieldsPK getFieldsPK() {
		return fieldsPK;
	}

	public void setFieldsPK(FieldsPK fieldsPK) {
		this.fieldsPK = fieldsPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_mandatory() {
		return is_mandatory;
	}

	public void setIs_mandatory(String is_mandatory) {
		this.is_mandatory = is_mandatory;
	}

	public String getIs_visible() {
		return is_visible;
	}

	public void setIs_visible(String is_visible) {
		this.is_visible = is_visible;
	}

	public String getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}

	public String getBlocked_after_BC() {
		return blocked_after_BC;
	}

	public void setBlocked_after_BC(String blocked_after_BC) {
		this.blocked_after_BC = blocked_after_BC;
	}

	
}
