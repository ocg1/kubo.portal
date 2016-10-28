package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "ln_loan_type")
public class LoanType 
implements Serializable 
{
	private static final long serialVersionUID = -4681844025522851996L;
	
	@EmbeddedId
	private LoanTypePK pk;
	
	@Column private String loan_type_name;
	@Column private String loan_type_desc;
		
	public LoanTypePK getPk() 
	{
		return pk;
	}
	
	public void setPk(LoanTypePK pk) 
	{
		this.pk = pk;
	}
	
	public String getLoan_type_name() 
	{
		return loan_type_name;
	}
	
	public void setLoan_type_name(String loan_type_name) 
	{
		this.loan_type_name = loan_type_name;
	}
	
	public String getLoan_type_desc() 
	{
		return loan_type_desc;
	}
	
	public void setLoan_type_desc(String loan_type_desc) 
	{
		this.loan_type_desc = loan_type_desc;
	}
}
