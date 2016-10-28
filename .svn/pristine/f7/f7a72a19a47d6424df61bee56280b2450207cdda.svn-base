package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQuery(name="lisOther_IncomeCat",
query=" FROM Other_IncomeCat o"
)
@Entity
@Table(name="gn_other_income")
public class Other_IncomeCat implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private Other_IncomeCatPK other_IncomeCatPK;
	@Column
	private String name;
	
	public Other_IncomeCat(){
		
	}

	public Other_IncomeCatPK getOther_IncomeCatPK() {
		return other_IncomeCatPK;
	}

	public void setOther_IncomeCatPK(Other_IncomeCatPK other_IncomeCatPK) {
		this.other_IncomeCatPK = other_IncomeCatPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
