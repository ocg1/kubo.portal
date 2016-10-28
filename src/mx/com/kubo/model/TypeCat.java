package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_type")
public class TypeCat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TypeCatPK typePk;
	
	@Column
	private String name;
	
	@Column
	private String selectable;
	
	public TypeCat(){
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public TypeCatPK getTypePk() {
		return typePk;
	}

	public void setTypePk(TypeCatPK typePk) {
		this.typePk = typePk;
	}

	public String getSelectable() {
		return selectable;
	}

	public void setSelectable(String selectable) {
		this.selectable = selectable;
	}
	
	
	
}
