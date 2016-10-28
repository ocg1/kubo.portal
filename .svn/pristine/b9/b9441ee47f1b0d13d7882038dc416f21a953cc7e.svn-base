package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.com.kubo.model.NaturalPerson;

@Entity @Table(name = "gn_note")
public class Notes extends ModelNotesDMO
implements ModelNotesIMO, Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId private NotesPK notesPk;
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "note_type_id", referencedColumnName = "note_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",   referencedColumnName = "company_id",   insertable = false, updatable = false)
	}) private NoteType noteType;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "change_prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",           referencedColumnName = "company_id",    insertable = false, updatable = false)
	}) private NaturalPerson person;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "priority_type_id", referencedColumnName = "priority_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",       referencedColumnName = "company_id",       insertable = false, updatable = false)
	}) private PriorityType priorityType;
	
	@Column private Date change_date;
	
	@Column private String note;	
	
	@Column	private Integer change_prospectus_id;		
	@Column private Integer proyect_id;
	@Column private Integer note_type_id;	
	@Column private Integer priority_type_id;
	@Column private Integer motive_id; 
	
	public Notes()
	{ 
		super();
	}

	public NotesPK getNotesPk() 
	{
		return notesPk;
	}

	public String getNote() {
		return note;
	}
	
	public void setNotesPk(NotesPK notesPk) {
		this.notesPk = notesPk;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public void setChange_prospectus_id(Integer change_prospectus_id) {
		this.change_prospectus_id = change_prospectus_id;
	}

	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}
	
	public void setProyect_id(Integer proyect_id) {
		this.proyect_id = proyect_id;
	}
	
	public void setNote_type_id(Integer note_type_id) {
		this.note_type_id = note_type_id;
	}
	
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	
	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}
	
	public void setPriority_type_id(Integer priority_type_id) {
		this.priority_type_id = priority_type_id;
	}
	
	public void setPriorityType(PriorityType priorityType) {
		this.priorityType = priorityType;
	}

	public void setMotive_id(Integer motive_id) {
		this.motive_id = motive_id;
	}

	public Integer getChange_prospectus_id() {
		return change_prospectus_id;
	}

	public Date getChange_date() {
		return change_date;
	}

	public Integer getProyect_id() {
		return proyect_id;
	}

	public Integer getNote_type_id() {
		return note_type_id;
	}

	public NoteType getNoteType() {
		return noteType;
	}

	public NaturalPerson getPerson() {
		return person;
	}

	public Integer getPriority_type_id() {
		return priority_type_id;
	}

	public PriorityType getPriorityType() {
		return priorityType;
	}

	public Integer getMotive_id() {
		return motive_id;
	}
}
