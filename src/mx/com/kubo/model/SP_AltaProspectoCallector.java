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
			resultClass = SP_AltaProspectoCallector.class,
			name = "collectorAltaProspecto",    
			 
			// query = "	call microfin.CONTRATOSREP(100005328,86.1645,1,1,1,'2016-02-08 19:11:00','192.0.0.0','kubofinanciero.generaContratos',1,0)
			
			// ( primer_nombre varchar(100),segundo_nombre varchar(100), ap_paterno varchar(100), ap_materno varchar(100), hs_vid int(11) , hs_email varchar(45), url_origin varchar(100), phone_mobile varchar(45)  )
			
			query = "call sp_alta_prospecto( :par_primer_nombre, :par_segundo_nombre , :par_ap_paterno , :par_ap_materno, :par_hs_vid , :par_hs_email , :par_url_origin , :par_phone_mobile, :par_area, :par_registration_reason  )",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class SP_AltaProspectoCallector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	int respuesta;

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	
}
