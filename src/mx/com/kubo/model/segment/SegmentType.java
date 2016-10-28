package mx.com.kubo.model.segment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="gn_segment_type")
public class SegmentType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SegmentTypePK pk;
	@Column
	private String name; //, varchar(200), YES, , ,
	@Column
	private String description; //, varchar(1000), YES, , , 
	
	public SegmentTypePK getPk() {
		return pk;
	}
	public void setPk(SegmentTypePK pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
