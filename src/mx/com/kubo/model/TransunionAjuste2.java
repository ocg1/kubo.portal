package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ln_transunion_ajuste2")
public class TransunionAjuste2 {

	@Id
	private String mx_motivo_ajuste_2;
	@Column
	private String description;
	
	public String getMx_motivo_ajuste_2() {
		return mx_motivo_ajuste_2;
	}
	public void setMx_motivo_ajuste_2(String mx_motivo_ajuste_2) {
		this.mx_motivo_ajuste_2 = mx_motivo_ajuste_2;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}