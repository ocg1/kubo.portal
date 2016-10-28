package mx.com.kubo.notificaciones.mailsender;

import java.util.TimerTask;

import javax.mail.MessagingException;

import mx.com.kubo.notificaciones.notificador.NotificacionException;

public abstract class MailSenderEMO extends MailSenderDMO
{		
	public final void connect() throws NotificacionException
	{		
		intentos_conexion = 0;
		
		do
		{
			if(conexion_SMTP.connect())
			{
				break;
			} else {
				intentos_conexion += 1;
				system_delay(2000);
			}
						
		} while(intentos_conexion < MAX_INTENTOS_CONEXION);		
		
		if(intentos_conexion == MAX_INTENTOS_CONEXION)
		{
			throw new NotificacionException("Imposible conectar a SMTP");
		}
	}
	
	public final void disconnect() throws NotificacionException
	{
		try 
		{
			conexion_SMTP.close();
			
		} catch (MessagingException e) {
		
			throw new NotificacionException(e.getMessage());
		}
	}
	
	private void system_delay(long delay_in_milis)
	{						
		DelayTask task   = new DelayTask();	     
		//Timer timer      = new Timer();
		
	    //timer.schedule(task, delay_in_milis);  
	    task.system_delay(delay_in_milis);
	}
	
	private class DelayTask extends TimerTask
	{
        public void run()  
        {     		
    		System.out.println("waiting for delay...");;   		
        } 
        
        public void system_delay(long delay_in_milis)
        {    	
    	    for(int i = 0; i < delay_in_milis * 1000; i++);
        }
	}
}
