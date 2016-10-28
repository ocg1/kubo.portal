package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

// Oferta Kubo te premia


@NamedNativeQueries({
	
	@NamedNativeQuery(
			resultClass = SaldoInversionista.class,
			name = "getSaldoInversionista",    
			query = "call getSaldoInversionista( :safi_account_id )",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})


@Entity
public class SaldoInversionista implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name="CuentaAhoID")
		private String cuentaAhoID;
		
		@Column(name="SaldoDispon")
		private Double saldoDispon;
		
		@Column(name="SaldoTotal")
		private Double saldoTotal;
		
		public String getCuentaAhoID() {
			return cuentaAhoID;
		}
		
		public void setCuentaAhoID(String cuentaAhoID) {
			this.cuentaAhoID = cuentaAhoID;
		}
		
		public Double getSaldoDispon() {
			return saldoDispon;
		}
		
		public void setSaldoDispon(Double saldoDispon) {
			this.saldoDispon = saldoDispon;
		}

		public Double getSaldoTotal() {
			return saldoTotal;
		}

		public void setSaldoTotal(Double saldoTotal) {
			this.saldoTotal = saldoTotal;
		}
}
