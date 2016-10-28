package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_offer_rejection_motive")
public class OfferRejectionMotive implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OfferRejectionMotivePK pk;
	@Column
	private String description; //` VARCHAR(300) NULL,
	@Column
	private Integer order_id; //` VARCHAR(45) NULL,
	@Column
	private String is_enabled; //
	
	public OfferRejectionMotivePK getPk() {
		return pk;
	}
	public void setPk(OfferRejectionMotivePK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
	
}
