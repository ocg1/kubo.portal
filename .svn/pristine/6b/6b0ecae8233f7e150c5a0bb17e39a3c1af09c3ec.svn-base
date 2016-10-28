package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MovementNotificationPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; //` tinyint(3) NOT NULL,
	@Column
	private int movement_id; // ` int(11) NOT NULL,
	@Column
	private BigInteger folioCargaID; //` bigint(17) NOT NULL,
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getMovement_id() {
		return movement_id;
	}
	public void setMovement_id(int movement_id) {
		this.movement_id = movement_id;
	}
	public BigInteger getFolioCargaID() {
		return folioCargaID;
	}
	public void setFolioCargaID(BigInteger folioCargaID) {
		this.folioCargaID = folioCargaID;
	}
	
}
