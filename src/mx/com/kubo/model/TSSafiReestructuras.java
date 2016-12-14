package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="historicos.TSAFI_REESTRUCTURAS")
public class TSSafiReestructuras {
	
	@EmbeddedId
	private TSSafiReestructurasPK pk ;
	
	@Column(name="SALDOCREDANTERI")
	private Double saldoCredAteri;
	
}
