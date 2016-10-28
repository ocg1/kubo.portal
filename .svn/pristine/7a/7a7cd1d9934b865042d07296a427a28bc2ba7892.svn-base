package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_segment_action")
public class SegmentAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SegmentActionPK pk;
	@Column
	private String segment_action; //` VARCHAR(45) NULL,
	@Column
	private int enabled; //` INT(1) NULL,

	public SegmentActionPK getPk() {
		return pk;
	}
	public void setPk(SegmentActionPK pk) {
		this.pk = pk;
	}
	public String getSegment_action() {
		return segment_action;
	}
	public void setSegment_action(String segment_action) {
		this.segment_action = segment_action;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
}
