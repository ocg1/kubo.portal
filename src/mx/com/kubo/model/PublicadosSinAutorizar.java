package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			
			resultClass = PublicadosSinAutorizar.class,
			
			name = "collectorPublicadosSinAutorizar",    
			 
			//  Par_Inserta: (I) inserta, (C) Consulta      Par_TipoNotifica: (S) SMS , (C) Correo
			query = "call SP_PUBLICADOS_SIN_AUTORIZAR( :Par_Inserta , :Par_TipoNotifica )",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class PublicadosSinAutorizar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private Integer client_notification_id;
	@Column
	private Integer notification_type_id;
	@Column
	private Integer prospectus_id;
	@Column
	private Integer proyect_loan_id;
	
	public Integer getClient_notification_id() {
		return client_notification_id;
	}
	public void setClient_notification_id(Integer client_notification_id) {
		this.client_notification_id = client_notification_id;
	}
	public Integer getNotification_type_id() {
		return notification_type_id;
	}
	public void setNotification_type_id(Integer notification_type_id) {
		this.notification_type_id = notification_type_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	
}
