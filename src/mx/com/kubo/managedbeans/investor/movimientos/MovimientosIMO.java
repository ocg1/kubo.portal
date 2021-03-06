package mx.com.kubo.managedbeans.investor.movimientos;

public abstract class MovimientosIMO 
{
	protected String monto;
	protected String cuenta;
	protected String descripcion;
	protected String motivo_disposicion;
	
	protected Integer clabe_account_id;
	
	public abstract void setMotivo_disposicion(String motivo);
	
	public abstract String getMonto();	
	public abstract String getCuenta();
	public abstract String getMotivo(); 
	public abstract String getMotivo_id();
	public abstract String getMotivo_disposicion();
	public abstract String getFolio();
	public abstract String getDescripcion();
	public abstract String getFecha_deposito();
	
	public abstract Integer getClabe_account_id();
}
