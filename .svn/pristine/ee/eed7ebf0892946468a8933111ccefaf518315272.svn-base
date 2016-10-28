package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	
	@NamedNativeQuery(
			resultClass = ExistPhone.class,
			name = "existePhoneSP",    
			query = "call EXISTE_TELEFONO( :phone_str , :prospectus_id )",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class ExistPhone {
	
	@Id
	@Column
	private int persona;
	@Column
	private int referencia;
	
	public int getPersona() {
		return persona;
	}
	
	public void setPersona(int persona) {
		this.persona = persona;
	}
	
	public int getReferencia() {
		return referencia;
	}
	
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

}
