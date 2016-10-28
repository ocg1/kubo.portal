package mx.com.kubo.constantes;

public enum EstatusResponse 
{
	EXITO ("0"),
	ERROR ("-1");
	
	private String id;
	
	private EstatusResponse(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
}
