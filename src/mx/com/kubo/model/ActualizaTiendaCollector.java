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
			resultClass = ActualizaTiendaCollector.class,
			name = "actualizaTienda",    
			query = "call ACTUALIZA_TIENDA( :solicitudes_a_eliminar )",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	),
	@NamedNativeQuery(
			resultClass = ActualizaTiendaCollector.class,
			name = "actualizaEstatusProyectLoan",    
			query = "call ACTUALIZA_ESTATUS_PROYECT_LOAN( :my_proyect_loan_id , :my_company_id , :my_proyect_id, :my_prospectus_id , :new_status , :new_date )",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class ActualizaTiendaCollector implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Integer res;

	public Integer getRes() {
		return res;
	}

	public void setRes(Integer res) {
		this.res = res;
	}
	
}
