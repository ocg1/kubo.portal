package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery (
		name  = "queryAddAccount",
		query = "select MAX(e.saving_accountPk.saving_account_id) from SavingAccount e where e.saving_accountPk.prospectus_id=? and e.saving_accountPk.company_id=?"
)
@Entity @Table(name = "gn_saving_account")
public class SavingAccount 
implements Serializable 
{
	private static final long serialVersionUID = -9212556761052555826L;
	
	@EmbeddedId
	private SavingAccountPK saving_accountPk;
	
	@Column private String safi_account_id;
	@Column private String description;
	@Column private String has_related_person;
	
	@Column private Integer status = 0;

	public SavingAccountPK getSaving_accountPk() 
	{
		return saving_accountPk;
	}

	public String getSafi_account_id() 
	{
		return safi_account_id;
	}

	public void setSaving_accountPk(SavingAccountPK saving_accountPk) {
		this.saving_accountPk = saving_accountPk;
	}

	public void setSafi_account_id(String safi_account_id) {
		this.safi_account_id = safi_account_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public final String getHas_related_person() {
		return has_related_person;
	}

	public final void setHas_related_person(String has_related_person) {
		this.has_related_person = has_related_person;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
