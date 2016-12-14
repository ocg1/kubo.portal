package mx.com.kubo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.primefaces.json.JSONObject;

import mx.com.kubo.bean.RequestShortScore;
import mx.com.kubo.bean.ResponseShortScore;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public class ObtieneConsultaCorta {

	protected SystemParamService systemparamservice;
	protected ServiceCallingService service_calling;
	private final int INIT;
	private final int RESPONSE;	
	private final int ERROR;
	private final int COMPANY_ID;
	
	private String strRes;
	private String cadenaEnviada;
	
	
	public ObtieneConsultaCorta(){
		
		systemparamservice = Utilities.findBean("systemParamServiceImp");
		service_calling = Utilities.findBean("serviceCallingServiceImp");
		
		INIT 		= 1;
		RESPONSE 	= 2;	
		ERROR 		= 3;
		COMPANY_ID 	= 1;
		
	}
	
	public ResponseShortScore generaConsultaCorta( RequestShortScore request , boolean regService ){
		
		HttpURLConnection conn = null;
		boolean consultaErronea = false;
		
		try {
			
			String SAFI_SOLICITUD_CREDITO_INIT = "";
			
			if( regService ){
			
				SAFI_SOLICITUD_CREDITO_INIT = "Consulta PROSPECTOR: PrimerNombre: " + request.getPrimerNombre() + " - SegundoNombre: "  +
															request.getSegundoNombre()+ " - ApellidoPaterno: "  + request.getApellidoPaterno()+ " - ApellidoMaterno: "  + request.getApellidoMaterno()+" - FechaNacimineto: "  + 
															request.getFechaNacimineto()+ " -  Rfc: "  + request.getRfc()+ " - Calle: "  + request.getCalle()+ " - NumeroExterior: "  +
															request.getNumeroExterior()+" - NumeroInterior: "  + request.getNumeroInterior()+ " - Colonia: "  + request.getColonia()+  " - Municipio: "  +
															request.getMunicipio()+ " - Ciudad: "  + request.getCiudad()+ " - Estado: "  + request.getEstado()+ " - CodigoPostal: "  + 
															request.getCodigoPostal()+ " - ClientId"  + request.getClientId();
				
				service_calling.registrar(INIT, Integer.parseInt( request.getClientId() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
			
			}
			
			SystemParamPK pk = new SystemParamPK(79, 1);

			SystemParam sys = systemparamservice.loadSelectedSystemParam(pk);
			
			URL url = new URL(sys.getValue());
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			
	        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	        
	        request = validaRequest( request );
	        
			String input = "{ "
					
								+ "\"apellidoPaterno\": \""	+  quitaAcentos( request.getApellidoPaterno() )		+"\", " // 3 - 26
								+ "\"apellidoMaterno\":\""	+  quitaAcentos( request.getApellidoMaterno() )		+"\", " // 3 - 26
								+ "\"primerNombre\": \""	+  quitaAcentos( request.getPrimerNombre() )			+"\", " // 3 - 26
								+ "\"segundoNombre\":\""	+  quitaAcentos( request.getSegundoNombre() )		+"\", " // 3 - 26
								+ "\"fechaNacimineto\": \""	+  quitaAcentos( request.getFechaNacimineto() )		+"\", " // 8
								+ "\"rfc\":\"" 				+  quitaAcentos( request.getRfc() )					+"\", " // 10 - 13
								+ "\"calle\": \""			+  quitaAcentos( request.getCalle() )				+"\", " //  - + 
								+ "\"numeroExterior\": \""	+  quitaAcentos( request.getNumeroExterior() )		+"\", " //     ¨ 40
								+ "\"numeroInterior\": \""	+  quitaAcentos( request.getNumeroInterior() ) 		+"\", " //  - +¨
								+ "\"colonia\":\""			+  quitaAcentos( request.getColonia() )				+"\", " // 40
								+ "\"municipio\": \""		+  quitaAcentos( request.getMunicipio() )			+"\", " // 40
								+ "\"ciudad\": \""			+  quitaAcentos( request.getCiudad() )				+"\", " // 40
								+ "\"estado\": \""			+  quitaAcentos( request.getEstado() )				+"\", " // clave max 4
								+ "\"codigoPostal\":\""		+  quitaAcentos( request.getCodigoPostal() ) 			+"\", " // 5 
								+ "\"clientId\": "			+  quitaAcentos( request.getClientId() )				+""
						
						+ "}";

			//input = quitaAcentos( input );
			
			System.out.println(input);
			
			cadenaEnviada = input;
			
			writer.write(input);
	        writer.close();
	        
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			
			if ((output = br.readLine()) != null) {
				
				try{
					
					System.out.println(output);
					
					strRes = output;
					
					if( output.indexOf("null") != (-1) && output.indexOf("null") < 50  ){
						
						consultaErronea = true;
						
					}
					
					if( !consultaErronea ){
						
						JSONObject jsonObj = new JSONObject( output );
						// {"score":"0687","burSolNum":"0000014333","valido":true,"clientId":13203,"servicioActivo":true}
						
						String 	score=(String) jsonObj.get("score");
						String 	burSolNum=(String) jsonObj.get("burSolNum");
						Boolean valido=(Boolean) jsonObj.get("valido");
						
						Integer clientId = null;
							
						if( request.getClientId() != null ){
						
							clientId=(Integer) jsonObj.get("clientId");
							
						}
						
						
						Boolean servicioActivo=(Boolean) jsonObj.get("servicioActivo");
						
						System.out.println( score +" - " + burSolNum +" - " + valido +" - "+ clientId +" - " + servicioActivo +" - " );
						
						ResponseShortScore res = new ResponseShortScore();
						 
						res.setBurSolNum(burSolNum);
						res.setClientId(clientId);
						res.setScore(score);
						res.setServicioActivo(servicioActivo);
						res.setValido(valido);
						
						if( regService ){
						
							SAFI_SOLICITUD_CREDITO_INIT = "Respuesta PROSPECTOR: PrimerNombre: score: "+ score +" - burSolNum: " + burSolNum +" - valido: " + valido +" - clientId: "+ clientId +" - servicioActivo: " + servicioActivo ;
							
							service_calling.registrar(RESPONSE, Integer.parseInt( request.getClientId() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
						
						}
						return res;
					
					}else{
						return null;
					}
					
				}catch(Exception e){
					
					e.printStackTrace();
					
					SAFI_SOLICITUD_CREDITO_INIT = "Respuesta ERROR CONSULTA PROSPECTOR: " + e.getMessage();
					
					if( regService ){
					
						service_calling.registrar(ERROR, Integer.parseInt( request.getClientId() ), COMPANY_ID, SAFI_SOLICITUD_CREDITO_INIT);
					
					}
					
					strRes = SAFI_SOLICITUD_CREDITO_INIT;
					
					return null;
				}
				
			}else{
				
				strRes = "RESPUESTA NULL";
				
				return null;
			}

			

		  } catch (MalformedURLException e) {
			  
			e.printStackTrace();
			
			strRes = "RESPUESTA NULL " + e.getMessage();
			
			return null;
			
		  } catch (IOException e) {

			e.printStackTrace();
			
			strRes = "RESPUESTA NULL " + e.getMessage();
			
			return null;
			
		  }finally{
			  
			  if(conn != null)
				  conn.disconnect();
		  }
	}
	
	
	private RequestShortScore validaRequest( RequestShortScore request ){
		
		RequestShortScore newrequest = new RequestShortScore();
		
		if(request.getApellidoPaterno()	!= null ){ // 3 - 26
			
			if( request.getApellidoPaterno().length() < 2 ){
				
				newrequest.setApellidoPaterno( "" );
				
			}else if( request.getApellidoPaterno().length() > 26 ){
				
				newrequest.setApellidoPaterno(request.getApellidoPaterno().substring(0, 25));
				
			}else{
				
				newrequest.setApellidoPaterno(request.getApellidoPaterno());
				
			}
			
		}else{
			newrequest.setApellidoPaterno(request.getApellidoPaterno());
		}
		
		
		if(request.getApellidoMaterno()	!= null ){ // 3 - 26
			
			if( request.getApellidoMaterno().length() < 2 ){
				
				newrequest.setApellidoMaterno( "" );
				
			}else if( request.getApellidoMaterno().length() > 26 ){
				
				newrequest.setApellidoMaterno(request.getApellidoMaterno().substring(0, 25));
				
			}else{
				
				newrequest.setApellidoMaterno(request.getApellidoMaterno());
				
			}
			
		}else{
			newrequest.setApellidoMaterno(request.getApellidoMaterno());
		}

		if(request.getPrimerNombre()	!= null ){ // 3 - 26
			
			if( request.getPrimerNombre().length() < 2 ){
				
				newrequest.setPrimerNombre( "" );
				
			}else if( request.getPrimerNombre().length() > 26 ){
				
				newrequest.setPrimerNombre(request.getPrimerNombre().substring(0, 25));
				
			}else{
				
				newrequest.setPrimerNombre(request.getPrimerNombre());
				
			}
			
		}else{
			newrequest.setPrimerNombre(request.getPrimerNombre());
		}

		
		if(request.getSegundoNombre()	!= null ){ // 3 - 26
			
			if( request.getSegundoNombre().length() < 2 ){
				
				newrequest.setSegundoNombre( "" );
				
			}else if( request.getSegundoNombre().length() > 26 ){
				
				newrequest.setSegundoNombre(request.getSegundoNombre().substring(0, 25));
				
			}else{
				
				newrequest.setSegundoNombre(request.getSegundoNombre());
				
			}
			
		}else{
			newrequest.setSegundoNombre(request.getSegundoNombre());
		}

		
		newrequest.setFechaNacimineto( request.getFechaNacimineto()	);	 // 8
		newrequest.setRfc( request.getRfc()	);				 // 10 - 13
		
		String calle = request.getCalle();				 //  - + 
		String numExt = request.getNumeroExterior();		 //     ¨ 40
		String numInt = request.getNumeroInterior();		 //  - +¨
		
		if(calle == null){
			calle = "";
		}
		
		if(numExt == null){
			numExt = "";
		}
		
		if(numInt == null){
			numInt = "";
		}
		
		String calleStr = calle + " " + numExt + " " + numInt;
		
		if(calleStr.length() > 40){
			
			if( numInt.length() > 0 ){
				
				numInt="";
				calleStr = calle + " " + numExt + " " + numInt;
				
				if(calleStr.length() > 40){
					
					int i = calleStr.length() - 40;
					calle = calle.substring(0, (calle.length() - (i+1) ) );
					
				}
			
			}else{
			
				int i = calleStr.length() - 40;
				calle = calle.substring(0, (calle.length() - (i+1) ) );
				
			}
			
		}
		
		newrequest.setCalle( calle );				 //  - + 
		newrequest.setNumeroExterior( numExt );		 //     ¨ 40
		newrequest.setNumeroInterior(numInt);
		
		if(request.getColonia()	!= null ){ 
			
			if( request.getColonia().length() < 3 ){
				
				newrequest.setColonia( "" );
				
			}else if( request.getColonia().length() > 40 ){
				
				newrequest.setColonia(request.getColonia().substring(0, 39));
				
			}else{
				
				newrequest.setColonia(request.getColonia());
				
			}
			
		}else{
			newrequest.setColonia(request.getColonia());
		}
		


		if(request.getMunicipio()	!= null ){ 
					
			if( request.getMunicipio().length() < 3 ){
				
				newrequest.setMunicipio( "" );
				
			}else if( request.getMunicipio().length() > 40 ){
				
				newrequest.setMunicipio(request.getMunicipio().substring(0, 39));
				
			}else{
				
				newrequest.setMunicipio(request.getMunicipio());
				
			}
			
		}else{
			newrequest.setMunicipio(request.getMunicipio());
		}

		
		if(request.getCiudad()	!= null ){ 
			
			if( request.getCiudad().length() < 3 ){
				
				newrequest.setCiudad( "" );
				
			}else if( request.getCiudad().length() > 40 ){
				
				newrequest.setCiudad(request.getCiudad().substring(0, 39));
				
			}else{
				
				newrequest.setCiudad(request.getCiudad());
				
			}
			
		}else{
			newrequest.setCiudad(request.getCiudad());
		}
		
		newrequest.setEstado( request.getEstado()	);			 // clave max 4
		newrequest.setCodigoPostal( request.getCodigoPostal()	);		 // 5 
		newrequest.setClientId( request.getClientId()	);		
		
		return newrequest;
		
	}
	
	
	private String quitaAcentos( String str ){
		
		if( str != null ) {
			
			String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    // Cadena de caracteres ASCII que reemplazarán los originales.
		    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		    
		    for (int i=0; i<original.length(); i++) {
		        // Reemplazamos los caracteres especiales.
		    	str = str.replace(original.charAt(i), ascii.charAt(i));
		    }//for i
		    
			str = str.replaceAll("\"","");
			
			str = str.replaceAll("\\\\","");
			
			str = str.replaceAll("/","");
			
			str = str.replaceAll("'","");
			
			str = str.replaceAll("&","");
		
		}
		
		return str;
	}

	public String getStrRes() {
		return strRes;
	}

	public void setStrRes(String strRes) {
		this.strRes = strRes;
	}

	public String getCadenaEnviada() {
		return cadenaEnviada;
	}

	public void setCadenaEnviada(String cadenaEnviada) {
		this.cadenaEnviada = cadenaEnviada;
	}
	
}
