package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_phone_type")
public class PhoneType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PhoneTypePK phoneTypePk;
	@Column
	private String name;
	
	public PhoneType(){
		
	}

	public PhoneTypePK getPhoneTypePk() {
		return phoneTypePk;
	}

	public void setPhoneTypePk(PhoneTypePK phoneTypePk) {
		this.phoneTypePk = phoneTypePk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
