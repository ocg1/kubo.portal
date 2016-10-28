package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_selling_type")
public class SellingType implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private SellingTypePK sellingTypePk;
	@Column
	private String name;
	@Column
	private String hs_name;
	
	public SellingType(){
		
	}

	public SellingTypePK getSellingTypePk() {
		return sellingTypePk;
	}

	public void setSellingTypePk(SellingTypePK sellingTypePk) {
		this.sellingTypePk = sellingTypePk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHs_name() {
		return hs_name;
	}

	public void setHs_name(String hs_name) {
		this.hs_name = hs_name;
	}
	
	
}
