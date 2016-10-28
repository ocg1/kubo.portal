package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = SPDeleteCreditsByClientsCollector.class,
			name = "deleteCreditsByClient",    
			query = "call delete_credits_byClient(:safi_client_id)",    
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity
public class SPDeleteCreditsByClientsCollector {

	@Id
	@Column(name="NumErr")
	private int numErr;
	@Column(name="ErrMen")
	private String ErrMen;
	
	public int getNumErr() {
		return numErr;
	}
	public void setNumErr(int numErr) {
		this.numErr = numErr;
	}
	public String getErrMen() {
		return ErrMen;
	}
	public void setErrMen(String errMen) {
		ErrMen = errMen;
	}
	
	

	
}
