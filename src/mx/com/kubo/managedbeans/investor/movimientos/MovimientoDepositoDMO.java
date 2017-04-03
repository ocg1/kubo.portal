package mx.com.kubo.managedbeans.investor.movimientos;

public final class MovimientoDepositoDMO extends MovimientosIMO
{
	private String 	fecha_deposito;
	
	public MovimientoDepositoDMO(String monto, String cuenta, String clabe_account_id, String fecha_deposito, String descripcion)
	{
		this.monto  			= monto;
		this.cuenta 			= cuenta;
		this.clabe_account_id 	= Integer.parseInt(clabe_account_id);
		
		this.fecha_deposito  	= fecha_deposito;
		this.descripcion 		= descripcion;
	}
	
	public final String getMonto() 
	{
		return monto;
	}
	
	public final String getCuenta() 
	{
		return cuenta;
	}
	
	public final Integer getClabe_account_id() 
	{
		return clabe_account_id;
	}

	public final String getDescripcion() 
	{
		return descripcion;
	}

	public final String getFecha_deposito() 
	{
		return fecha_deposito;
	}

	public final String getMotivo() 
	{
		return "";
	}
	
	public final String getMotivo_id() 
	{
		return "";
	}
	
	public final String getFolio() 
	{
		return "";
	}
	
	public void setMotivo_disposicion(String motivo) 
	{
		motivo_disposicion = motivo;
	}
	
	public String getMotivo_disposicion() 
	{
		return "";
	}
}
