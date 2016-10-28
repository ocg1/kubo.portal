package mx.com.kubo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = RFCCollector.class,
			name = "rfcQuery",    
			query = "call CLIENTERFCCAL(:name,:fatherlast,:motherlast,:birthday)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})
@Entity
public class RFCCollector implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String rfc;

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	} 
}
