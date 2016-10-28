package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_legal_status")
public class Legal_Status 
implements Serializable
{
	private static final long serialVersionUID = -3096551330398978884L;
	
	@EmbeddedId
	private LegalStatusPK legalstatusPK;
	
	@Column private String description;
	
	@Column int menu_order;
	
	public LegalStatusPK getLegalstatusPK() 
	{
		return legalstatusPK;
	}
	
	public void setLegalstatusPK(LegalStatusPK legalstatusPK) 
	{
		this.legalstatusPK = legalstatusPK;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getMenu_order() 
	{
		return menu_order;
	}

	public void setMenu_order(int menu_order) 
	{
		this.menu_order = menu_order;
	}
}