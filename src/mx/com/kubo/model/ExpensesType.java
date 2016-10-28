package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="gn_expense_type")
public class ExpensesType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ExpensesTypePK pk;
	@Column
	private String name;
	
	public ExpensesType(){
		
	}

	public ExpensesTypePK getPk() {
		return pk;
	}

	public void setPk(ExpensesTypePK pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
