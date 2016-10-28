package mx.com.kubo.constantes;

public enum RiskSGBWebService 
{
	CREDITOS_VIGENTES("WsSgbRiskServiceLocator.getWsSgbRisk.getVtbur_infocredcte_vig()"),
	CERRADOS_SEMESTRE_ANTERIOR("WsSgbRiskServiceLocator.getWsSgbRisk.getVtbur_infocredcte_c()"),
	CERRADOS_SEMESTRE_POSTERIOR("WsSgbRiskServiceLocator.getWsSgbRisk.getVtbur_infocredcte_m()");
	
	private String descripcion;
	
	private RiskSGBWebService(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString()
	{
		return descripcion;
	}
}
