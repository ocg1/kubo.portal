package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_manual_consulting")
public class ConsultingManual implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ConsultingManualPK pk;
	@Column
	private Date consulting_date;//	datetime	YES
	@Column
	private Integer consulting_prospectus_id;//	int(10)	YES
	@Column
	private String mx_solicitud_buro;//	varchar(10)	YES
	
	public ConsultingManualPK getPk() {
		return pk;
	}
	public void setPk(ConsultingManualPK pk) {
		this.pk = pk;
	}
	public Date getConsulting_date() {
		return consulting_date;
	}
	public void setConsulting_date(Date consulting_date) {
		this.consulting_date = consulting_date;
	}
	public Integer getConsulting_prospectus_id() {
		return consulting_prospectus_id;
	}
	public void setConsulting_prospectus_id(Integer consulting_prospectus_id) {
		this.consulting_prospectus_id = consulting_prospectus_id;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}

	
}
