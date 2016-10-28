package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="gn_note_type")
public class NoteType implements Serializable {
	private static final long serialVersionUID = 1L;
			
	@EmbeddedId
	private NoteTypePK noteTypePk;
	@Column
	private String name;
	
	public NoteTypePK getNoteTypePk() {
		return noteTypePk;
	}
	public void setNoteTypePk(NoteTypePK noteTypePk) {
		this.noteTypePk = noteTypePk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
