package mx.com.kubo.managedbeans.investor.movimientos;

public final class MovimientoDisposicionDMO extends MovimientosIMO
{	
	public MovimientoDisposicionDMO(String monto, String cuenta, String clabe_account_id)
	{
		this.monto  = monto;
		this.cuenta = cuenta;
		this.clabe_account_id = Integer.parseInt(clabe_account_id);
	}

	public String getMonto() 
	{
		return monto;
	}

	public String getCuenta() 
	{
		return cuenta;
	}

	public Integer getClabe_account_id() 
	{
		return clabe_account_id;
	}

	public String getDescripcion() 
	{
		return "";
	}

	public String getFecha_deposito() 
	{
		return "";
	}

	public String getMotivo() 
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
}
