package mx.com.kubo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.json.JSONObject;

import mx.com.kubo.bean.InteractorBean;
import mx.com.kubo.model.FormAnalytics;
import mx.com.kubo.tools.Utilities;

public class SrvInteractor extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	 
	
	
	//private String message;

	  public void init() throws ServletException
	  {
	      // Do required initialization
	     // message = "Hello World";
		  
		  System.out.println( "INIT SRVINTERACTOR" );
		   
	  }

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      // Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>DO GET</h1>");
	  }
	  
	  public void doPost(HttpServletRequest request,
              HttpServletResponse response)
      throws ServletException, IOException
		{
		
//		  request.
//		  System.out.println( "" );
		  
		  
		  StringBuilder jb = new StringBuilder();
		  String line = null;
		  
		  try {
		  
			  BufferedReader reader = request.getReader();
		    
			  while ((line = reader.readLine()) != null){
		      
				  jb.append(line);
				  
			  }
		  
		  } catch (Exception e) { 
			  
			  e.printStackTrace();
			  
		  }

		  try {
		   /*
			  System.out.println( "\n\n");
			  System.out.println( "**************************************************************"  );
			  System.out.println( "*******************RESPUESTA*JSON*****************************"  );
			  System.out.println( "\n\n");
			  System.out.println( jb.toString()  );
			  System.out.println( "\n\n");
			  System.out.println( "**************************************************************"  );
			  System.out.println( "**************************************************************"  );
			  System.out.println( "\n\n");
			  */
			  JSONObject jsonObject = new JSONObject(jb.toString());
		    
			  FormAnalytics analytic = new FormAnalytics();
			  
			  String 	google_id		=  (String) jsonObject.get("cliente") ;
			  String 	prospectus_id	=  (String) jsonObject.get("prospecto") ;
			  
			  analytic.setGoogle_id(	google_id	);
			  
			  if( prospectus_id != null && Utilities.isNumeric(prospectus_id) ){
				  
				  analytic.setProspectus_id( Integer.parseInt( prospectus_id ) );
				  
			  }
			  
			  analytic.setAnalytics_str( jb.toString() );
			  
			  InteractorBean interactor =  new InteractorBean(  );
			  
			  interactor.saveFormAnalytics(analytic);
			  
			  analytic =  null;
			  jsonObject = null;
			  google_id		= null;
			  prospectus_id	= null;
			  jb = null;
			  
		  } catch (Exception e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		    
		  }
		  
		}
	  
	  
	  public void destroy()
	  {
	      // do nothing.
	  }
	
}
