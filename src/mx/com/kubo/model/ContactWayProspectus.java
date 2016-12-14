package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_prospectus_contact_way")
public class ContactWayProspectus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ContactWayProspectusPK pk;
	@Column
	private String descripton;
	@Column
	private Date selection_date;
	
	public ContactWayProspectusPK getPk() {
		return pk;
	}
	public void setPk(ContactWayProspectusPK pk) {
		this.pk = pk;
	}
	public String getDescripton() {
		return descripton;
	}
	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}
	public Date getSelection_date() {
		return selection_date;
	}
	public void setSelection_date(Date selection_date) {
		this.selection_date = selection_date;
	} 
	
}
