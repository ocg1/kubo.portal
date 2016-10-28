package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NoteTypePK implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private int note_type_id;
	@Column
	private int company_id;
	
	public NoteTypePK(int company_id){
		this.company_id=company_id;
	}
	public NoteTypePK(int note_type_id,int company_id){
		this.note_type_id=note_type_id;
		this.company_id=company_id;
	}

	public NoteTypePK(){
		
	}
	public int getNote_type_id() {
		return note_type_id;
	}
	public void setNote_type_id(int note_type_id) {
		this.note_type_id = note_type_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
