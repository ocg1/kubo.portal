package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gn_identification_type")
public class IdentificationType 
implements Serializable 
{
	private static final long serialVersionUID = -1491932184075668827L;
	
	@EmbeddedId
	private IdentificationTypePK pk;
	
	private String description;
	private String is_enabled;
	
	private Integer safi_tipo_identi_id;
	private Integer safi_numero_caracteres;

	public IdentificationTypePK getPk() 
	{
		return pk;
	}

	public void setPk(IdentificationTypePK pk) 
	{
		this.pk = pk;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Integer getSafi_tipo_identi_id() {
		return safi_tipo_identi_id;
	}

	public void setSafi_tipo_identi_id(Integer safi_tipo_identi_id) {
		this.safi_tipo_identi_id = safi_tipo_identi_id;
	}

	public String getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}

	public Integer getSafi_numero_caracteres() {
		return safi_numero_caracteres;
	}

	public void setSafi_numero_caracteres(Integer safi_numero_caracteres) {
		this.safi_numero_caracteres = safi_numero_caracteres;
	}
	
	
}
