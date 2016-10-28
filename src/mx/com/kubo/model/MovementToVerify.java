package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pr_movements_to_verify")
public class MovementToVerify implements Serializable  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovementToVerifyPK pk;
	
	@Column
	private String movement_name; //', 'varchar(45)', 'YES', '', NULL, ''
	@Column
	private String movement_description; //', 'varchar(45)', 'YES', '', NULL, ''
	@Column
	private String movement_status; //', 'varchar(45)', 'YES', '', NULL, ''
	@Column
	private String movement_order; //', 'int(11)', 'YES', '', NULL, ''
	@Column
	private Integer minutes_to_reply_event;
	@Column
	private Date next_day_to_apply;
	
	
	public MovementToVerifyPK getPk() {
		return pk;
	}
	public void setPk(MovementToVerifyPK pk) {
		this.pk = pk;
	}
	public String getMovement_name() {
		return movement_name;
	}
	public void setMovement_name(String movement_name) {
		this.movement_name = movement_name;
	}
	public String getMovement_description() {
		return movement_description;
	}
	public void setMovement_description(String movement_description) {
		this.movement_description = movement_description;
	}
	public String getMovement_status() {
		return movement_status;
	}
	public void setMovement_status(String movement_status) {
		this.movement_status = movement_status;
	}
	public String getMovement_order() {
		return movement_order;
	}
	public void setMovement_order(String movement_order) {
		this.movement_order = movement_order;
	}
	public Integer getMinutes_to_reply_event() {
		return minutes_to_reply_event;
	}
	public void setMinutes_to_reply_event(Integer minutes_to_reply_event) {
		this.minutes_to_reply_event = minutes_to_reply_event;
	}
	public Date getNext_day_to_apply() {
		return next_day_to_apply;
	}
	public void setNext_day_to_apply(Date next_day_to_apply) {
		this.next_day_to_apply = next_day_to_apply;
	}
	
}
