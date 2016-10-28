package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_who_answered")
public class WhoAnsweredCat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private WhoAnsweredCatPK whoAnsweredCatPk;
	@Column
	private String name;
	
	public WhoAnsweredCat(){
		
	}

	public WhoAnsweredCatPK getWhoAnsweredCatPk() {
		return whoAnsweredCatPk;
	}


	public void setWhoAnsweredCatPk(WhoAnsweredCatPK whoAnsweredCatPk) {
		this.whoAnsweredCatPk = whoAnsweredCatPk;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
