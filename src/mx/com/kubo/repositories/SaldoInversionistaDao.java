package mx.com.kubo.repositories;


import mx.com.kubo.model.SaldoInversionista;

public interface SaldoInversionistaDao {

	public SaldoInversionista getSaldoByAccount( String account );
	
}
