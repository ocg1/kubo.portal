package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_reca")
public class Reca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RecaPK pk ;
	@Column
	private String reca_number;
	@Column
	private Date valid_from;
	@Column
	private Date valid_to;
	@Column
	private Integer status;

	public String getReca_number() {
		return reca_number;
	}

	public void setReca_number(String reca_number) {
		this.reca_number = reca_number;
	}

	public Date getValid_from() {
		return valid_from;
	}

	public void setValid_from(Date valid_from) {
		this.valid_from = valid_from;
	}

	public Date getValid_to() {
		return valid_to;
	}

	public void setValid_to(Date valid_to) {
		this.valid_to = valid_to;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public RecaPK getPk() {
		return pk;
	}

	public void setPk(RecaPK pk) {
		this.pk = pk;
	}

	
	
}
