package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gn_account_type")
public class AccountType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private AccountTypePK accountTypePK;
	@Column
	private String account_code;
	@Column
	private String description;
	
	public AccountType() {
	}

	public AccountType(mx.com.kubo.model.AccountTypePK accountTypePK,
			String account_code, String description) {
		this.accountTypePK = accountTypePK;
		this.account_code = account_code;
		this.description = description;
	}

	public AccountTypePK getAccountTypePK() {
		return accountTypePK;
	}

	public void setAccountTypePK(AccountTypePK accountTypePK) {
		this.accountTypePK = accountTypePK;
	}

	public String getAccount_code() {
		return account_code;
	}

	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
