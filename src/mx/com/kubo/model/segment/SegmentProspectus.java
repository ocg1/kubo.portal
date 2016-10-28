package mx.com.kubo.model.segment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.kubo.model.NaturalPerson;

@Entity
@Table(name="gn_segment_prospectus")
public class SegmentProspectus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SegmentProspectusPK pk;
	@Column
	private Date assign_date; //, datetime, YES; //, , 
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	    })	
	private NaturalPerson person;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "segment_id", referencedColumnName = "segment_id", insertable = false, updatable = false)
	    })	
	private Segment segment;
	
	
	public SegmentProspectusPK getPk() {
		return pk;
	}
	public void setPk(SegmentProspectusPK pk) {
		this.pk = pk;
	}
	public Date getAssign_date() {
		return assign_date;
	}
	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public Segment getSegment() {
		return segment;
	}
	public void setSegment(Segment segment) {
		this.segment = segment;
	}
	
}

