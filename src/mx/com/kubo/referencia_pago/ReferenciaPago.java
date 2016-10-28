package mx.com.kubo.referencia_pago;

public enum ReferenciaPago 
{
	BANORTE(0),	
	BANAMEX(1),
	SEVEN_ELEVEN(2),
	BANORTE_SPEI(3),
	TELECOMM(4);
	
	private Integer orden_ASC;
	
	private ReferenciaPago(Integer indice)
	{
		orden_ASC = indice;
	}
	
	public Integer getOrden_ASC()
	{
		return orden_ASC;
	}
}
