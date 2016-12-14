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
			resultClass = SP_UpdateProspectoCallector.class,
			name = "collectorUpdateProspecto",    
			 
			// query = "	call microfin.CONTRATOSREP(100005328,86.1645,1,1,1,'2016-02-08 19:11:00','192.0.0.0','kubofinanciero.generaContratos',1,0)
			
			// (  _prospectus_id int (11) , primer_nombre varchar(100),segundo_nombre varchar(100), ap_paterno varchar(100), ap_materno varchar(100), hs_vid int(11) , hs_email varchar(45), url_origin varchar(100), phone_mobile varchar(45)   )
			
			query = "call sp_update_prospecto( :param_prospectus_id , :param_primer_nombre , :param_segundo_nombre , :param_ap_paterno , :param_ap_materno, :param_hs_vid , :param_s_email , :param_url_origin , :param_phone_mobile )",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class SP_UpdateProspectoCallector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	String respuesta;

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
}
