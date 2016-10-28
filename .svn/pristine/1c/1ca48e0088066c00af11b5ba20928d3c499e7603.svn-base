package mx.com.kubo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = spSetOnProyectFunding.class,
			name = "loanQuery",    
			query = "call PROYECT_FUNDING_INS(:par_proyect_loan_id,:par_proyect_id,:par_company_id,:par_prospectus_id,:par_prospectus_investor_id,:par_amount,:par_solicitudFondeo)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})
@Entity
public class spSetOnProyectFunding implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int resultado;

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	
}