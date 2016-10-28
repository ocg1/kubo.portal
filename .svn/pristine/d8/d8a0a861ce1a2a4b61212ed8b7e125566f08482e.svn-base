package mx.com.kubo.listeners;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class AplicacionListener extends HttpServlet 
implements ServletContextListener 
{

    private ServletContext context = null;

    @SuppressWarnings("rawtypes")
	public void contextInitialized(ServletContextEvent event) 
    {
    	Map usuarios = new Hashtable();
    	
    	context = event.getServletContext();
              
    	context.setAttribute("usuariosFirmados", usuarios);
    }
    
    public void contextDestroyed(ServletContextEvent event) 
    {
    	
    }
}