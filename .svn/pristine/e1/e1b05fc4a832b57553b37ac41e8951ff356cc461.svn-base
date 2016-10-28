package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = RelacionMenorTutorCollector.class,
			name = "relaciona_menor_tutor_safi",    
			// query = "call CLIENTERFCCAL( :par_EmpresaID (int 11),aud_Usuario(int),aud_FechaActual(DateTime),aud_DireccionIP(varchar(15)),aud_ProgramaID(varchar(50)),aud_Sucursal(int),aud_NumTransaccion(bigint)", 
			query = "call  RELACIONA_MENOR_CON_TUTOR_EN_SAFI(:safi_client_id_menor,:safi_client_id_tutor,:fecha);",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})
@Entity
public class RelacionMenorTutorCollector {
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
