package mx.com.kubo.controller.sgbRest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SgbHelp  {
	
	private String url_str;
	private String input;
	private String response;
	private int status;
	

	public void connect(){
		
		HttpURLConnection conn = null;
		
	try {
		
		
		
		//url_str = "http://localhost:8080/TEST_RPC/rest/Connector/addpersonas";
		
		
		URL url = new URL( url_str );
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        
		//input = "{ \"apellidoPaterno\": \"Martinez\", \"apellidoMaterno\":\"Balderas\",\"primerNombre\": \"Rodrigo\"}";

		//input = quitaAcentos( input );
		
		System.out.println(input);
		writer.write(input);
        writer.close();
        
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			
			response = " CONNECT EXCEPTION:  Failed : HTTP error code : "+ conn.getResponseCode();
			status = -99;
			//throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
		}

		if( status != (-99) ){
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		
		StringBuilder outputB = new StringBuilder();
		
		String linea=br.readLine();
        
		while(linea!=null){
            System.out.println(linea);
            outputB.append(linea);
            linea=br.readLine();
        }
		
        
        String output = outputB.toString();
		
		System.out.println("Output from Server .... \n");
		
//		if ((output = br.readLine()) != null) {
//			
//			try{
				
				System.out.println(output);
				
				response = output;
				status = 1;
				
			/*	JSONObject jsonObj = new JSONObject( output );
				// {"score":"0687","burSolNum":"0000014333","valido":true,"clientId":13203,"servicioActivo":true}
				
				String 	score=(String) jsonObj.get("score");
				String 	burSolNum=(String) jsonObj.get("burSolNum");
				Boolean valido=(Boolean) jsonObj.get("valido");
				Integer clientId=(Integer) jsonObj.get("clientId");
				Boolean servicioActivo=(Boolean) jsonObj.get("servicioActivo");
				
				System.out.println( score +" - " + burSolNum +" - " + valido +" - "+ clientId +" - " + servicioActivo +" - " );
				
				ResponseShortScore res = new ResponseShortScore();
				 
				res.setBurSolNum(burSolNum);
				res.setClientId(clientId);
				res.setScore(score);
				res.setServicioActivo(servicioActivo);
				res.setValido(valido);
				
				SAFI_SOLICITUD_CREDITO_INIT = "Respuesta PROSPECTOR: PrimerNombre: score: "+ score +" - burSolNum: " + burSolNum +" - valido: " + valido +" - clientId: "+ clientId +" - servicioActivo: " + servicioActivo ;
				
				service_calling.registrar(RESPONSE, Integer.parseInt( request.getClientId() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
				
				return res; */
				
//			}catch(Exception e){
//				
//				e.printStackTrace();
//				
//				
//			}
//			
//		}else{
//			
//		}

		}

	  } catch (MalformedURLException e1) {
		  status = -98;
		  response = e1.getMessage();
		e1.printStackTrace();
		
	  } catch (IOException e2) {
		  status = -97;
		  response = e2.getMessage();
		e2.printStackTrace();
		
		
	  }finally{
		  
		  if(conn != null)
			  conn.disconnect();
	  }
}


	public String getUrl_str() {
		return url_str;
	}


	public void setUrl_str(String url_str) {
		this.url_str = url_str;
	}


	public String getInput() {
		return input;
	}


	public void setInput(String input) {
		this.input = input;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
}
