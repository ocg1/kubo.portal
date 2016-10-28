package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_clabe_account")
public class ClabeAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ClabeAccountPK clabepk;
	@Column
	private String mx_clabe;//	varchar(18)
	@Column
	private Integer bank_id;//	"int(10) unsigned"
	@Column
	private String bank_description;//	varchar(100)
	public ClabeAccountPK getClabepk() {
		return clabepk;
	}
	public void setClabepk(ClabeAccountPK clabepk) {
		this.clabepk = clabepk;
	}
	public String getMx_clabe() {
		return mx_clabe;
	}
	public void setMx_clabe(String mx_clabe) {
		this.mx_clabe = mx_clabe;
	}
	public Integer getBank_id() {
		return bank_id;
	}
	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}
	public String getBank_description() {
		return bank_description;
	}
	public void setBank_description(String bank_description) {
		this.bank_description = bank_description;
	}

	
}
