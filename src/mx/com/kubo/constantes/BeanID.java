package mx.com.kubo.constantes;

public enum BeanID 
{
	NOTIFICADOR("notificadorImp");
	
	private final String id;
	
	private BeanID(String id)
	{
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
}
