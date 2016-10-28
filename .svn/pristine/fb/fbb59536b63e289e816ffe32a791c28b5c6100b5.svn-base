package mx.com.kubo.controller.shortURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.primefaces.json.JSONObject;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public class GeneraURLCorta {

	protected SystemParamService systemparamservice;
	protected ServiceCallingService service_calling;
	private final int INIT;
	private final int RESPONSE;	
	private final int ERROR;
	private final int COMPANY_ID;
	
//	public static void main(String[] args) {
//		
//		GeneraURLCorta shortURl = new GeneraURLCorta();
//		
//		RequestShortURL request = new RequestShortURL();
//		
//		request.setCompany_id(1+"");
//		request.setProspectus_id(686+"");
//		//request.setLongURL("http://www.kubofinanciero.com/Kubo/Portal/acreditado/preregistro/comenzar-registro.xhtml?selectedReg=s");
//		
//		request.setLongURL("https://www.kubofinanciero.com/Kubo/Portal/aviso-legal/aviso-privacidad.xhtml");
//		
//		shortURl.generaConsultaCorta( request );
//		
//	}
	
	public GeneraURLCorta(){
		
		systemparamservice = Utilities.findBean("systemParamServiceImp");
		service_calling = Utilities.findBean("serviceCallingServiceImp");
		
		INIT 		= 1;
		RESPONSE 	= 2;	
		ERROR 		= 3;
		COMPANY_ID 	= 1;
		
	}
	
	public ResponseShortURL generaConsultaCorta( RequestShortURL request ){
		
		HttpURLConnection conn = null;
		
		try {
			
			
			
			String SAFI_SOLICITUD_CREDITO_INIT = "Genera URLShortener - Google: " + request.getLongURL() ;
			
			service_calling.registrar(INIT, Integer.parseInt( request.getProspectus_id() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
			
//			SystemParamPK pk = new SystemParamPK(79, 1);
//
//			SystemParam sys = systemparamservice.loadSelectedSystemParam(pk);
			
			
			
			URL url = new URL("https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyBULitAYMaTTaM8efu0QFX8NY2kLNrKURY");
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			
	        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	        
			String input = "{ \"longUrl\": \""+request.getLongURL()+"\"}";

			input = quitaAcentos( input );
			
			System.out.println(input);
			writer.write(input);
	        writer.close();
	        
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			StringBuilder outputB = new StringBuilder();
			System.out.println("Output from Server .... \n"+br.toString());
			
			String linea=br.readLine();
            while(linea!=null){
                System.out.println(linea);
                outputB.append(linea);
                linea=br.readLine();
            }
			
            
            String output = outputB.toString();
            
			if (output != null && output.length() > 0 ) {
				
				try{
					
					System.out.println(output);
					JSONObject jsonObj = new JSONObject( output );
					// {"score":"0687","burSolNum":"0000014333","valido":true,"clientId":13203,"servicioActivo":true}
					
					String 	shortURL=(String) jsonObj.get("id");
					
					
					System.out.println( "shortURL: " + shortURL  );
					
					ResponseShortURL res = new ResponseShortURL();
					 
					res.setShortURL(shortURL);
					
					
					SAFI_SOLICITUD_CREDITO_INIT = "Respuesta URLShortener - Google: PrimerNombre: shortURL: "+ shortURL ;
					
					service_calling.registrar(RESPONSE, Integer.parseInt( request.getProspectus_id() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
					
					res.setStatus("0");
					
					return res;
					
				}catch(Exception e){
					e.printStackTrace();
					
					SAFI_SOLICITUD_CREDITO_INIT = "Respuesta ERROR URLShortener - Google: " + e.getMessage();
					
					service_calling.registrar(ERROR, Integer.parseInt( request.getProspectus_id() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
					
					ResponseShortURL res = new ResponseShortURL();
					
					res.setStatus("90");
					res.setError("SAFI_SOLICITUD_CREDITO_INIT");
					
					return res;
				}
				
			}else{
				
				SAFI_SOLICITUD_CREDITO_INIT = "Respuesta ERROR URLShortener - Google: BufferedReader br.readLine es null" ;
				
				service_calling.registrar(ERROR, Integer.parseInt( request.getProspectus_id() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
				
				ResponseShortURL res = new ResponseShortURL();
				
				res.setStatus("90");
				res.setError(SAFI_SOLICITUD_CREDITO_INIT);
				
				return res;
				
			}

			

		  } catch (MalformedURLException e) {
			  
			  e.printStackTrace();
				
				String SAFI_SOLICITUD_CREDITO_INIT = "Respuesta ERROR URLShortener - Google: " + e.getMessage();
				
				service_calling.registrar(ERROR, Integer.parseInt( request.getProspectus_id() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
				
				ResponseShortURL res = new ResponseShortURL();
				
				res.setStatus("90");
				res.setError("SAFI_SOLICITUD_CREDITO_INIT");
				
				return res;
		  } catch (IOException e) {

			  e.printStackTrace();
				
				String SAFI_SOLICITUD_CREDITO_INIT = "Respuesta ERROR URLShortener - Google: " + e.getMessage();
				
				service_calling.registrar(ERROR, Integer.parseInt( request.getProspectus_id() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
				
				ResponseShortURL res = new ResponseShortURL();
				
				res.setStatus("90");
				res.setError("SAFI_SOLICITUD_CREDITO_INIT");
				
				return res;
			  
			
		  }finally{
			  
			  if(conn != null)
				  conn.disconnect();
		  }
	}
	
	private String quitaAcentos(String str){
		
		if( str != null ) {
			
			String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    // Cadena de caracteres ASCII que reemplazarán los originales.
		    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		    
		    for (int i=0; i<original.length(); i++) {
		        // Reemplazamos los caracteres especiales.
		    	str = str.replace(original.charAt(i), ascii.charAt(i));
		    }//for i

			
		}
		
		return str;
	}
	
}
