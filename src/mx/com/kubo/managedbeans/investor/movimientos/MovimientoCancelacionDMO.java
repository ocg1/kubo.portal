package mx.com.kubo.managedbeans.investor.movimientos;

public final class MovimientoCancelacionDMO extends MovimientosIMO
{
	private String motivo_id;
	private String motivo;
	private String folio;
		
	public MovimientoCancelacionDMO(String cuenta, String motivo_id, String motivo, String folio, String descripcion)
	{
		this.cuenta      = cuenta;
		this.motivo_id   = motivo_id;
		this.motivo      = motivo;
		this.folio       = folio;
		this.descripcion = descripcion;
	}

	public String getMonto() 
	{
		return "";
	}

	public String getCuenta() 
	{
		return cuenta;
	}
	
	public final String getMotivo() 
	{
		return motivo;
	}
	
	public final String getMotivo_id() 
	{
		return motivo_id;
	}

	public final String getFolio() 
	{
		return folio;
	}

	public Integer getClabe_account_id() 
	{
		return null;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public String getFecha_deposito() 
	{
		return "";
	}
}
