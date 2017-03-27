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
			resultClass = CoachPublicationsCollector.class,
			name = "collectorClientNotification",    
			 
			// query = "	call microfin.CONTRATOSREP(100005328,86.1645,1,1,1,'2016-02-08 19:11:00','192.0.0.0','kubofinanciero.generaContratos',1,0)
			
			query = "call microfin.COACHPUBLICATIONS(:par_Inserta,:par_TipoOperacion,:par_TipoNotifica)",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

/* 
 * 
 *  Par_Inserta          char(1)     ->    "S", inserta prospectos nuevos
                                            ->    "N". No inserta prospectos nuevos

   Par_TipoOperacion  char(1) ->     "A" Asignacion de couch (los prospectos aun no                                                             tienen asignados a un coach)
                                                    "C" Consulta prospectos con coach asignado

   Par_TipoNotifica 
 * 
 *  */

@Entity
public class CoachPublicationsCollector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column 
	@Id 
	private Integer prospectus_id; //               int(10)
	@Column
	private Integer notification_type_id; //        int(11)
	@Column 
	private Integer prospectus_id_coach; //     int(11)
	
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
	public Integer getProspectus_id_coach() {
		return prospectus_id_coach;
	}
	public void setProspectus_id_coach(Integer prospectus_id_coach) {
		this.prospectus_id_coach = prospectus_id_coach;
	}
	
}
