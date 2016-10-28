package mx.com.kubo.repositories;

public interface EstadoCuentaDatosDao {

	public Double getMontoExigible( String safi_credit_id );
	
	public Double getMontoParaLiquidar( String safi_credit_id );
	
}