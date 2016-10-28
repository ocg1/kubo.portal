package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQuery(name="findBank",
			query="SELECT a FROM Bank a  WHERE a.short_name LIKE :description"
			)

@Entity
@Table(name = "gn_bank")
public class Bank implements Serializable 
{
	private static final long serialVersionUID = -6245629666571387279L;
	
	@EmbeddedId
	private BankPK bankpk;
	@Column
	private String short_name;//	varchar(100)
	@Column
	private String name;//	varchar(180)
	@Column
	private String type;//	varchar(60)
	
	
	public BankPK getBankpk() {
		return bankpk;
	}
	public void setBankpk(BankPK bankpk) {
		this.bankpk = bankpk;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
