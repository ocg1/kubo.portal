package mx.com.kubo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;


@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = CAT_Collector.class,
			name = "catQuery",
			query = "call microfin.CALCULARCAT(" +
					"	:Par_MontoCredito, " +
					"	:Par_ValorCuotas, " +
					"	:Par_FrecuPago, " +
					" 	:Par_Salida, " +
					"	:Var_CAT, " +
					"	:NumTransaccion)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})

@Entity
public class CAT_Collector {

	@Id
	private Double Var_Cat;

	public Double getVar_Cat() {
		return Var_Cat;
	}

	public void setVar_Cat(Double var_Cat) {
		Var_Cat = var_Cat;
	}
	
}
