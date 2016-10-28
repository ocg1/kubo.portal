package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="lisContractType",
query=" FROM Contract_typeCat a"
)
@Entity
@Table(name="gn_contract_type")
public class Contract_typeCat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Contract_typeCatPK contract_typeCatPK; 
	
	@Column
	private String name;
	
	public Contract_typeCat(){
		
	}

	public Contract_typeCatPK getContract_typeCatPK() {
		return contract_typeCatPK;
	}

	public void setContract_typeCatPK(Contract_typeCatPK contract_typeCatPK) {
		this.contract_typeCatPK = contract_typeCatPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
