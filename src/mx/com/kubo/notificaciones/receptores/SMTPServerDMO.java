package mx.com.kubo.notificaciones.receptores;

public final class SMTPServerDMO
{	
	private int    account_code_id;
	private String account_code;
	
	public SMTPServerDMO(String account_code, int account_code_id)
	{
		this.account_code    = account_code;
		this.account_code_id = account_code_id;
	}
	
	public final int getAccount_code_id() 
	{
		return account_code_id;
	}
	
	public final String getAccount_code() 
	{
        if(account_code == null || account_code.equals(""))
        {
        	return "mail.smtp.";
        } else {
        	return account_code;
        }        	
	}
}
