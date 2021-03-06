package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotesPK 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column private int prospectus_id;	
	@Column private int company_id;	
	@Column private int note_id;
	
	public NotesPK(int prospectus_id, int company_id)
	{	
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}
	
	public NotesPK(int prospectus_id, int company_id, int note_id)
	{
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.note_id=note_id;
	}
	
	public NotesPK()
	{
	
	}
	
	public int getProspectus_id() {
		return prospectus_id;
	}
	
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
	public int getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public int getNote_id() {
		return note_id;
	}
	
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
}
