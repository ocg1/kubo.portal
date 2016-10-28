package mx.com.kubo.managedbeans.mesa.solicitud.adicional;

public enum TipoCreditoAdicional 
{
	NUEVA_CONSULTA_ENABLED  (true),
	NUEVA_CONSULTA_DISABLED (false),
	RENOVACION              (false),
	RENOVACION_APROBACION_AUTOMATICA(true);
	
	private boolean consulta_buro_enabled;
	
	private TipoCreditoAdicional(boolean is_enabled)
	{
		consulta_buro_enabled = is_enabled;
	}
	
	public boolean isConsulta_buro_enabled()
	{
		return consulta_buro_enabled;
	}
}
