package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pr_utm_partner_conversion")
public class UtmPartnerConversion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column
	private String utm_source_value;
	@Column
	private String partner_id;
	
	public String getUtm_source_value() {
		return utm_source_value;
	}
	public void setUtm_source_value(String utm_source_value) {
		this.utm_source_value = utm_source_value;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
}
