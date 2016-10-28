package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_deposit_method")
public class Deposit_method implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Deposit_methodPK deposit_methodPK;
	
	private String name;
	private String safi_code;

	public Deposit_method(){
		
	}

	public Deposit_methodPK getDeposit_methodPK() {
		return deposit_methodPK;
	}

	public void setDeposit_methodPK(Deposit_methodPK deposit_methodPK) {
		this.deposit_methodPK = deposit_methodPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSafi_code() {
		return safi_code;
	}

	public void setSafi_code(String safi_code) {
		this.safi_code = safi_code;
	}
}
