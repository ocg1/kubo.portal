package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_fromwhere")
public class FromWhereCat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FromWhereCatPK fromWherePk;
	@Column
	private String name;
	
	public FromWhereCat() {
		
	}
	
	public FromWhereCatPK getFromWherePk() {
		return fromWherePk;
	}
	public void setFromWherePk(FromWhereCatPK fromWherePk) {
		this.fromWherePk = fromWherePk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
