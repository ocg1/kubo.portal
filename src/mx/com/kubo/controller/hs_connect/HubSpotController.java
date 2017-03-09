package mx.com.kubo.controller.hs_connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import mx.com.kubo.bean.HS_OBJ;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.UtmPartnerConversion;
import mx.com.kubo.services.RegistrationReasonService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

public class HubSpotController {
	
	 
	private final String[] meses_c ={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"}; 
	
	private RegistrationReasonService registrationreasonservice;
	
	
	public Integer saveProspectus( StringBuilder properties ){
		
		String url="https://api.hubapi.com/contacts/v1/contact/?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		
		return executeCallBack( url , properties , null );
		
	}
	
	public Integer updateProspectus( Integer vid , StringBuilder properties ){
		
		String url="https://api.hubapi.com/contacts/v1/contact/vid/"+vid+"/profile?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		
		return executeCallBack( url , properties , vid );
	}
	
	public Integer sendEvent( String event, String email){
		try{
			
			try{
				
				
				String url="http://track.hubspot.com/v1/event?_n="+event+"&_a=2511529&email="+email;
				
				URL object=new URL(url);
				
				String charset = "UTF-8";

				HttpURLConnection con = (HttpURLConnection) object.openConnection();
				con.setDoOutput(true);
				
				con.setRequestProperty("Accept-Charset", charset);
				con.setRequestProperty("Content-Type", "application/json;charset="+charset);
				
				con.setRequestMethod("GET");
				
				
				//display what returns the POST request

				StringBuilder sb = new StringBuilder();  
				
				int HttpResult = 0;
				
				try{
					
					HttpResult = con.getResponseCode();
					
				}catch(Exception e){
					
				}
				
				System.out.println(HttpResult);
				
				if (HttpResult == HttpURLConnection.HTTP_OK ) {
					
				    BufferedReader br = new BufferedReader(
											            	new InputStreamReader(con.getInputStream(), charset)
											            );
				    
				    String line = null;  
				    
				    while ((line = br.readLine()) != null) {  
				        
				    	sb.append(line + "\n");
				    	
				    }
				    
				    br.close();
				    System.out.println("" + sb.toString());
				    
				}
				
				return HttpResult;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public  Integer createField2( String properties ){
		
		String url=" https://api.hubapi.com/properties/v1/contacts/properties?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		
		String[] proper = validaStrForCreate( properties );
		
		HttpURLConnection con = null;
		
		Integer 	vid = null;
		
		try{
		
		
			
		//String url="http://192.168.11.73:5300/";
			
			for( int i = 0; i<proper.length ; i++ ){
			
				URL object=new URL(url);
				
				String charset = "UTF-8";
		
				con = (HttpURLConnection) object.openConnection();
				con.setDoOutput(true);
				
				con.setRequestProperty("Accept-Charset", charset);
				con.setRequestProperty("Content-Type", "application/json;charset="+charset);
				
				con.setRequestMethod("POST");
				
				//String  s  = "{\"properties\": [{\"property\": \"email\",\"value\": \"new-email_331@hubspot.com\"},{\"property\": \"firstname\",\"value\": \"Updated_33\"},{\"property\": \"lastname\",\"value\": \"Lead_33\"}]}";
				
				String  s  = proper[i];
				
				System.out.println(s);
				
				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
				
				wr.write( s );
				wr.close();
				
				//display what returns the POST request
		
				StringBuilder sb = new StringBuilder();  
				
				int HttpResult = 0;
				
				try{
					
					HttpResult = con.getResponseCode();
					
					
				}catch( ConnectException ne ){
					
					System.out.println( "ConnectException: " + ne );
					
					ne.printStackTrace();
					
					vid = null;
				
					SystemParamService systemparamservice = Utilities.findBean( "systemParamServiceImp" ) ;
					
					SystemParamPK sppk =  new SystemParamPK(96, 1);  // Bandera que indica si esta habilitada la conección con HUBSPOT
					
					SystemParam sp = systemparamservice.loadSelectedSystemParam(sppk);
					
					sp.setValue("N");
					
					systemparamservice.updateSelectedSystemParam(sp);
					
					try{
						
						NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();
						request_notificar_config.setCompany_id("1");
						request_notificar_config.setProspectus_id("0");					
						request_notificar_config.setCalled_FROM("PortalKubo.HubspotController()");
						request_notificar_config.setEvento_id("2");
						request_notificar_config.setFecha_deposito("El servicio de HUBSPOT no está disponible");
						request_notificar_config.setMonto_deposito("ERROR COMUNICACIÓN CON HUBSPOT");
						
						PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
						
						PublicProyect kubo_services = locator.getPublicProyect();
						
						WsResponse response = kubo_services.notificar(request_notificar_config);
						
					}catch( Exception e ){
						
						e.printStackTrace();
						
					}
					
				}
				
				System.out.println(HttpResult);
				
				if (HttpResult == HttpURLConnection.HTTP_OK ) {
					
				    BufferedReader br = new BufferedReader(
											            	new InputStreamReader(con.getInputStream(), charset)
											            );
				    
				    String line = null;  
				    
				    while ((line = br.readLine()) != null) {  
				        
				    	sb.append(line + "\n");
				    	
				    }
				    
				    br.close();
				    System.out.println("" + sb.toString());
				    
				    
				    
				} else {
				    
					System.out.println(con.getResponseMessage());
				    
				}  
		
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		  
		  if(con != null)
			  con.disconnect();
	  }
	
	 
	return vid;
	
}
	
	public  String getJSONProperties(Integer vid ){
		
		String url="https://api.hubapi.com/contacts/v1/contact/vid/"+vid+"/profile?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		
		return executeCallBackGetJSON( url  );
		
	}
	
	private  String executeCallBackGetJSON( String url ){
		
		
		HttpURLConnection con = null;
		
		String resJSON = null;
		
		try{
		
		//String url="http://192.168.11.73:5300/";
		URL object=new URL(url);
		
		String charset = "UTF-8";

		con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		
		con.setRequestProperty("Accept-Charset", charset);
		con.setRequestProperty("Content-Type", "application/json;charset="+charset);
		
		con.setRequestMethod("GET");
		
		
		//display what returns the POST request

		StringBuilder sb = new StringBuilder();  
		
		int HttpResult = 0;
		
		try{
			
			HttpResult = con.getResponseCode();
			
			
		}catch( ConnectException ne ){
			
			System.out.println( "ConnectException: " + ne );
			
			ne.printStackTrace();
			
			SystemParamService systemparamservice = Utilities.findBean( "systemParamServiceImp" ) ;
			
			SystemParamPK sppk =  new SystemParamPK(96, 1);  // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			SystemParam sp = systemparamservice.loadSelectedSystemParam(sppk);
			
			sp.setValue("N");
			
			systemparamservice.updateSelectedSystemParam(sp);
			
			try{
				
				NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();
				request_notificar_config.setCompany_id("1");
				request_notificar_config.setProspectus_id("0");					
				request_notificar_config.setCalled_FROM("PortalKubo.HubspotController()");
				request_notificar_config.setEvento_id("2");
				request_notificar_config.setFecha_deposito("El servicio de HUBSPOT no está disponible");
				request_notificar_config.setMonto_deposito("ERROR COMUNICACIÓN CON HUBSPOT");
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect kubo_services = locator.getPublicProyect();
				
				WsResponse response = kubo_services.notificar(request_notificar_config);
				
			}catch( Exception e ){
				
				e.printStackTrace();
				
			}
			
			
		}
		
		System.out.println(HttpResult);
		
		if (HttpResult == HttpURLConnection.HTTP_OK ) {
			
		    BufferedReader br = new BufferedReader(
									            	new InputStreamReader(con.getInputStream(), charset)
									            );
		    
		    String line = null;  
		    
		    while ((line = br.readLine()) != null) {  
		        
		    	sb.append(line + "\n");
		    	
		    }
		    
		    br.close();
		    resJSON = sb.toString();
		    
		    
		    
		} else {
		    
			System.out.println(con.getResponseMessage());
		    
		}  
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		  
		  if(con != null)
			  con.disconnect();
	  }
	
	 
	return resJSON;
	
}
	
	
	private  Integer executeCallBack( String url , StringBuilder properties, Integer hs_vid ){
		
			properties = new StringBuilder( validaStr( properties ) );
			
			HttpURLConnection con = null;
			
			Integer 	vid = null;
			
			try{
			
			//String url="http://192.168.11.73:5300/";
			URL object=new URL(url);
			
			String charset = "UTF-8";

			con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			
			con.setRequestProperty("Accept-Charset", charset);
			con.setRequestProperty("Content-Type", "application/json;charset="+charset);
			
			con.setRequestMethod("POST");
			
			//String  s  = "{\"properties\": [{\"property\": \"email\",\"value\": \"new-email_331@hubspot.com\"},{\"property\": \"firstname\",\"value\": \"Updated_33\"},{\"property\": \"lastname\",\"value\": \"Lead_33\"}]}";
			
			String  s  = "{\"properties\": ["+properties+"]}";
			
			
			
			System.out.println(s);
			
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			
			wr.write( s );
			wr.close();
			
			//display what returns the POST request

			StringBuilder sb = new StringBuilder();  
			
			
			int HttpResult = 0;
			
			try{
				
				HttpResult = con.getResponseCode();
				
				
			}catch( ConnectException ne ){
				
				System.out.println( "ConnectException: " + ne );
				
				ne.printStackTrace();
				
				vid = null;
				
				SystemParamService systemparamservice = Utilities.findBean( "systemParamServiceImp" ) ;
				
				SystemParamPK sppk =  new SystemParamPK(96, 1);  // Bandera que indica si esta habilitada la conección con HUBSPOT
				
				SystemParam sp = systemparamservice.loadSelectedSystemParam(sppk);
				
				sp.setValue("N");
				
				systemparamservice.updateSelectedSystemParam(sp);
				
				try{
					
					NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();
					request_notificar_config.setCompany_id("1");
					request_notificar_config.setProspectus_id("0");					
					request_notificar_config.setCalled_FROM("PortalKubo.HubspotController()");
					request_notificar_config.setEvento_id("2");
					request_notificar_config.setFecha_deposito("El servicio de HUBSPOT no está disponible");
					request_notificar_config.setMonto_deposito("ERROR COMUNICACIÓN CON HUBSPOT");
					
					PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
					
					PublicProyect kubo_services = locator.getPublicProyect();
					
					WsResponse response = kubo_services.notificar(request_notificar_config);
					
				}catch( Exception e ){
					
					e.printStackTrace();
					
				}
				
			}
			
			System.out.println(HttpResult);
			
			if (HttpResult == HttpURLConnection.HTTP_OK ) {
				
			    BufferedReader br = new BufferedReader(
										            	new InputStreamReader(con.getInputStream(), charset)
										            );
			    
			    String line = null;  
			    
			    while ((line = br.readLine()) != null) {  
			        
			    	sb.append(line + "\n");
			    	
			    }
			    
			    br.close();
			    System.out.println("" + sb.toString());
			    
			    JSONObject jsonObj = new JSONObject( sb.toString() );
				
				vid =(Integer) jsonObj.get("vid");
			    
			} else {
				
				if (HttpResult != 204) {
				
					enviaError( con.getResponseMessage() ,  HttpResult, properties.toString(), hs_vid );
				    
					System.out.println(con.getResponseMessage());
				
				}
			    
			}  
			
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
			  
			  if(con != null)
				  con.disconnect();
		  }
		
		 
		return vid;
		
	}
	
	private String[] validaStrForCreate( String properties ){
		
		String[] val = properties.split("&");
		
		
		
		String[] resA = new  String[ val.length ];
		
		int i = 0;
		
		if( val != null && val.length > 1 ){
			
			for ( int y = 0 ; y < val.length ; y++ ){
			
			//	for ( int y = 0 ; y < 20 ; y++ ){
				
				 if( val[ y ] != null && val[ y ].trim().length() > 0 & (val[ y ].split("=")).length == 2){
					String res =
							 "	{  "
							 +"	\"name\": \""+(val[ y ].split("=")[0]).toLowerCase()+"\", "
							 +"	\"label\": \""+(val[ y ].split("=")[0]).toLowerCase()+"\", "
							 +"	\"description\": \"Creado desde Kubo\", "
							 +"	\"groupName\": \"api_inversion\", "
							 +"	\"type\": \"string\", "
							 +"	\"fieldType\": \"text\", "
							 +"	\"formField\": true, "
							 +"	\"displayOrder\": 6, "
							 +"	\"options\": [ "
							 +"	] "
							 +"	}  ";
						
						resA[i] = res;
						
						i++;
							
				}
				
				
				//System.out.println(  val[ y ].split("=")[0] );
				
				
			}
			
		}
		
		return resA;
		
	}
	
	private String validaStr( StringBuilder properties ){
		
		StringBuilder values = new StringBuilder();
		
		String year = null;
		String day = null;
		String month = null; 
		
		String[] val = properties.toString().split("&");
		
		if( val != null && val.length > 1 ){
			
			for ( int y = 0 ; y < val.length ; y++ ){
			
			//	for ( int y = 0 ; y < 20 ; y++ ){
				
				if( val[ y ] != null && val[ y ].trim().length() > 0 & (val[ y ].split("=")).length == 2){
				
						String property =  (val[ y ].split("=")[0]).trim().toLowerCase()  ;
						String value =  val[ y ].split("=")[1] ;
						
						if(property.equals("day") || property.equals("month") || property.equals("year") ||  property.equals("selling_") ){
							
							if(property.toString().equals("day"))
								day = value.toString();
							if(property.toString().equals("month"))
								month = value.toString();
							if(property.toString().equals("year"))
								year = value.toString();
							
							continue;
							
						}
						
						if(y != 0){
							values.append(",");
						}
						
						StringBuilder n = new StringBuilder(   validaNombre( property ) );
						
						if( !isNumericField(  n ) ){
						
							values.append( " { \"property\" : \""+n.toString()+"\" , \"value\" : \""+Utilities.quitaAcentos(value.toString())+"\" }" ) ;
						
						}else{
							
							value =  value.replace("$", "").replaceAll(",", "") ;
							values.append(" { \"property\" : \""+n.toString()+"\" , \"value\" : "+value.toString()+" }") ;
						}
			
						property =  null ;
						value = null;
						
				}
			
			}
			
			if( day != null && month != null && year != null && Utilities.isNumeric(day) && Utilities.isNumeric(year) ){
				
				//SimpleDateFormat  fr = new SimpleDateFormat("dd/MMMM/yyyy");
				
				SimpleDateFormat fr = new SimpleDateFormat("dd/MM/yyyy");
				
				//fr.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

				StringBuilder fecha = new StringBuilder( converterDate( day , month , year ) );
				
				try{
					
					Date d = fr.parse(fecha.toString());
					
					TimeZone tz = TimeZone.getTimeZone("UTC");
			        
					Calendar c = Calendar.getInstance();
					
					c.setTimeZone(tz);
					
					c.setTime(d);
					
					
					c.set(Calendar.HOUR, 0);
					
					c.set(Calendar.MINUTE, 0);
					
					c.set(Calendar.SECOND, 0);
					
					c.set(Calendar.MILLISECOND, 0);
					
					values.append( " ,{ \"property\" : \"birthday\" , \"value\" : \""+(c.getTimeInMillis() )+"\" }") ;
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}else{
			values = properties;
		}
		
		val = null;
		
		return values.toString();
		
	}
	
	private String validaNombre( String property ){
		
		StringBuilder res = new StringBuilder();
		
		if( property.equals("name") ){
			
			res.append("firstname");
			
		}else if( property.equals("apaterno") ){
			
			res.append("lastname");
			
		}else if( property.equals("amaterno") ){
			
			res.append("last_name2");
			
		}else if( property.equals("second_name") ){
			
			res.append("second_name");
			
		}else if( property.equals("zip_code") ){
			
			res.append("zip");
			
		}else if( property.equals("street") ){
			
			res.append("address");
			
		}else if( property.equals("delegmun") ){
			
			res.append("city");
			
		}else if( property.equals("ammount_input") ){
			
			res.append("ammount");
			
		}else if( property.equals("phonecel_employ") ){
			
			res.append("mobilephone");
			
		}else if( property.equals("phonefixed_employ") ){
			
			res.append("phone");
			
		}else if( property.equals("kuboscore") ){
			
			res.append("inputkuboscore");
			
		}else if( property.equals("tipo-cliente") ){
			
			res.append("tipo_cliente");
			
		}else if( property.equals("kuboscore_int") ){
			
			res.append("bc_score_int");
			
		}else if( property.equals("tipoidentificacion") ){
			
			res.append("tipo_de_identificacion");
			
		}else if( property.equals("ingMaxMen")){
			
			res.append("ingresos_aproximados_mes");
			
		}
		

		
		
		else {
			res.append(property.toString());
		}
		
		return res.toString();
		
	}
	
	private boolean isNumericField( StringBuilder  n ){
		
		boolean res = false;
		
		if( n.toString().equals("ammount") ){
			
			res = true;
		}if( n.toString().equals("inp_food") ){
			
			res = true;
		
		}if( n.toString().equals("gastototal") ){
			
			res = true;
		
		}if( n.toString().equals("inp_gas") ){
			
			res = true;
			
		}if( n.toString().equals("inp_phone") ){
			
			res = true;
			
		}if( n.toString().equals("inp_income") ){
			
			res = true;
		
	    }if( n.toString().equals("inp_maintenance_house_or_apartment") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_credits") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_series") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_entertainment") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_medical") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_school") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_gasoline_transport") ){
			
	    	res = true;
			
	    }if( n.toString().equals("inp_food_at_work") ){
			
	    	res = true;
	    }if(  n.toString().equals("inp_wages_salary") ){
	    	
	    	res = true;
	    }
	    
	    if(  n.toString().equals("selling_monday") ){
			res = true;
	    }
	    if(  n.toString().equals("selling_tuesday") ){
			res = true;
	    }
		if(  n.toString().equals("selling_wednesday") ){
			res = true;
	    }
		if(  n.toString().equals("selling_thursday") ){
			res = true;
	    }
		if(  n.toString().equals("selling_friday") ){
			res = true;
	    }
		if(  n.toString().equals("selling_saturday") ){
			res = true;
	    }
		if(  n.toString().equals("selling_sunday") ){
			res = true;
	    }
		if(  n.toString().equals("selling_mounthly_sales") ){
			res = true;
	    }
		if(  n.toString().equals("selling_fortnight_1") ){
			res = true;
	    }
		if(  n.toString().equals("selling_fortnight_2") ){
			res = true;
	    }
		if(  n.toString().equals("operating_rent") ){
			res = true;
	    }
		if(  n.toString().equals("operating_serv") ){
			res = true;
	    }
		if(  n.toString().equals("operating_phone") ){
			res = true;
	    }
		if(  n.toString().equals("operating_taxes") ){
			res = true;
	    }
		if(  n.toString().equals("operating_gas") ){
			res = true;
	    }
		if(  n.toString().equals("operating_maintenance") ){
			res = true;
	    }
		if(  n.toString().equals("operating_accountant") ){
			res = true;
	    }
		if(  n.toString().equals("operating_employment") ){
			res = true;
	    }
		if(  n.toString().equals("operating_other") ){
			res = true;
	    }
		if(  n.toString().equals("inp_other_income") ){
			res = true;
	    }
		if(  n.toString().equals("gastosNegocio") ){
			res = true;
	    }
		
		if(  n.toString().equals("frm_questPLD_momentmaxdep") ){
			res = true;
	    }
		if(  n.toString().equals("frm_questPLD_momentmaxret") ){
			res = true;
	    }
		if(  n.toString().equals("frm_questPLD_montomaxsal") ){
			res = true;
	    }
		
		if(  n.toString().equals("operating_cost_rent") ){
			res = true;
	    }
		if(  n.toString().equals("operating_cost_serv") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_phone") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_taxes") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_gas") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_maintenance") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_accountant") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_employment") ){
			res = true;
		}
		if(  n.toString().equals("operating_cost_other") ){
			res = true;
		}
		if(  n.toString().equals("inp_light") ){
			res = true;
		}
		if(  n.toString().equals("inp_value_opexpenses") ){
			res = true;
		}
		
		if(  n.toString().equals("inp_how_often") ){
			res = true;
		}
		
		if(  n.toString().equals("inp_income_other_family") ){
			res = true;
		}
		if(  n.toString().equals("inp_income_otherfamilye") ){
			res = true;
		}
		if(  n.toString().equals("gastosnegocio") ){
			res = true;
		}
		if(  n.toString().equals("inp_business_company") ){
			res = true;
		}if(n.toString().equals("bc_score_int")){
			res = true;
		}

		
		return res;
		
	}

	private String converterDate( String day , String month , String year ){
		
		String fecha = "";
		
		if( Integer.parseInt( day ) < 10 ){
			day = "0"+day;
		}
		
		//fecha += day+"/";
		
		String month_num = getMonthNum(month);
		
		fecha += day+"/"+ month_num + "/"+year;
		
		return fecha;
		
	}
	
	private String getMonthNum( String month){
		
		String mes = "0";
		
		int i = 0;
		boolean flag = false;
		
		for( String m :  meses_c ){
			
			i++;
			
			if( m.trim().toLowerCase().equals( month.trim().toLowerCase() ) ){
				flag = true;
				break;
			}
			
		}
		
		if( flag ){
			
			if( i < 10 ){
				
				mes = "0"+i;
				
			}else{
				mes = ""+i;
			}
			
		}
		
		return mes;
		
	}
	
	private void enviaError( String error_msj , Integer error_code, String json_send, Integer hs_vid ){
		
		HttpURLConnection con = null;
		
		try{
		
		String url="http://info.kubofinanciero.com:6000/errorLog";

		URL object=new URL(url);
		
		String charset = "UTF-8";

		con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		
		con.setRequestProperty("Accept-Charset", charset);
		con.setRequestProperty("Content-Type", "application/json;charset="+charset);
		
		con.setRequestMethod("POST");
		
		//String  s  = "{\"properties\": [{\"property\": \"email\",\"value\": \"new-email_331@hubspot.com\"},{\"property\": \"firstname\",\"value\": \"Updated_33\"},{\"property\": \"lastname\",\"value\": \"Lead_33\"}]}";
		
		String  s  = "{\"error_msj\": \""+error_msj+"\" , 	\"error_code\": " +error_code+ ", \"json_send\": ["+json_send+"] , \"vid\": "+hs_vid+" }";

		
		
		
		System.out.println(s);
		
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		
		wr.write( s );
		wr.close();
		
		//display what returns the POST request

		int HttpResult = 0;
		
		try{
			
			HttpResult = con.getResponseCode();
			
		}catch( ConnectException ne ){
			
			System.out.println( "ConnectException: " + ne );
			
			ne.printStackTrace();
			
			SystemParamService systemparamservice = Utilities.findBean( "systemParamServiceImp" ) ;
			
			SystemParamPK sppk =  new SystemParamPK(96, 1);  // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			SystemParam sp = systemparamservice.loadSelectedSystemParam(sppk);
			
			sp.setValue("N");
			
			systemparamservice.updateSelectedSystemParam(sp);
			
			try{
				
				NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();
				request_notificar_config.setCompany_id("1");
				request_notificar_config.setProspectus_id("0");					
				request_notificar_config.setCalled_FROM("PortalKubo.HubspotController()");
				request_notificar_config.setEvento_id("2");
				request_notificar_config.setFecha_deposito("El servicio de HUBSPOT no está disponible");
				request_notificar_config.setMonto_deposito("ERROR COMUNICACIÓN CON HUBSPOT");
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect kubo_services = locator.getPublicProyect();
				
				WsResponse response = kubo_services.notificar(request_notificar_config);
				
			}catch( Exception e ){
				
				e.printStackTrace();
				
			}
			
		}
		
		System.out.println(HttpResult);
		
		if (HttpResult == HttpURLConnection.HTTP_OK ) {
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public HS_OBJ createHSOBJ( String json_str ){
		
		try{
		
			HS_OBJ hs_obj = new HS_OBJ();
			
			JSONObject json = (JSONObject) new JSONObject( json_str );
			
			hs_obj.setV_id( Long.parseLong( (Integer)json.get("vid") + "" ) );
			
			System.out.println( "VID: " + hs_obj.getV_id() );
			
			JSONObject prop =  (JSONObject)json.getJSONObject("properties");
			
			String first_name = getValueElementForJSON( prop, "firstname" );
			
			hs_obj.setFirstname_value( ( (first_name == null) ? "" : first_name) );
			
			System.out.println( "firstname: " + hs_obj.getFirstname_value() );
			
			hs_obj.setSecond_name( getValueElementForJSON( prop, "second_name" ) ); 
			
			System.out.println( "second_name: " + hs_obj.getSecond_name() );
			
			hs_obj.setLast_name( getValueElementForJSON( prop, "lastname" ) ); 
			
			System.out.println( "lastname: " + hs_obj.getLast_name() );
			
			hs_obj.setLast_name2( getValueElementForJSON( prop, "last_name2" ) ); 
			
			System.out.println( "last_name2: " + hs_obj.getLast_name2() );
			
			String mobile = getValueElementForJSON( prop, "mobilephone" );
			
			hs_obj.setMobil_value(  mobile == null ? "" : mobile  );
			
			System.out.println( "mobil_value: " +  hs_obj.getMobil_value() );
			
			hs_obj.setEmail_value( getValueElementForJSON( prop, "email" ) );
			
			System.out.println( "email_value: " + hs_obj.getEmail_value() );
			
			JSONArray submit =  (JSONArray)json.getJSONArray("form-submissions");
			
			if( submit  != null ){
			
				try{
					
					hs_obj.setUrl_value( ( (String)(((JSONObject)submit.getJSONObject(0)).get("page-url")) ).trim() );
					
					System.out.println( "url_value: " + hs_obj.getUrl_value() );
					
					Integer registration_reason_id = decodeURL( hs_obj.getUrl_value() );
					
					if( registration_reason_id != null && registration_reason_id.intValue() != 0 ){
						hs_obj.setRegistration_reason_id(registration_reason_id);
					}
					
					hs_obj.setUrl_medium( getUTMValue( hs_obj.getUrl_value()   , "utm_medium" ));
					
					hs_obj.setUrl_campaign( getUTMValue( hs_obj.getUrl_value()   , "utm_campaign" ));
					
				}catch(Exception e){
					hs_obj.setRegistration_reason_id(81);
				}
				
			}else{
				hs_obj.setRegistration_reason_id(81);
			}
			
			return hs_obj;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	protected String getValueElementForJSON( JSONObject json_obj, String element ){
		
		try{
		
			JSONObject prop =  (JSONObject)json_obj.getJSONObject(element);
			
			String res= null;
			
			if( prop != null ){
			
				res = (String)prop.get("value");
				
			}
	
			return res;
		
		}catch( Exception e ){
			
			System.out.println("HS: elemento no encontrado: " + element );
			
			return null;
			
		}
		
	}
	
	private Integer decodeURL( String url_value  ){
		try{
			
			registrationreasonservice = Utilities.findBean( "registrationReasonServiceImp" );
			
			Integer res = 0;
			
			if( url_value != null && url_value.trim().length() > 0 ){
			
				URL url = new URL(url_value) ;
				
				Map<String, List<String>> map = splitQuery( url);
				
				List<String> this_utm_partner_id = map.get("utm_source");
				
				if( this_utm_partner_id != null && this_utm_partner_id.size() > 0){
				
					String utm_partner_id = this_utm_partner_id.get(0);
					
					if ( utm_partner_id != null &&  utm_partner_id.trim().length() > 0 ){
					
						UtmPartnerConversion utmP =registrationreasonservice.existUTM( utm_partner_id );
						
						if( utmP != null ){
							
							RegistrationReason reg = registrationreasonservice.getRegistrationReasonByPartner( utmP.getPartner_id() );
							
							if( reg != null ){
							
								res = reg.getRegPK().getRegistration_reason_id();
								
							}
							
						}else {
							
							RegistrationReason reg = registrationreasonservice.getRegistrationReasonByPartner( utm_partner_id );
							
							if( reg != null ){
							
								res = reg.getRegPK().getRegistration_reason_id();
							
							}
							
						}
					
					}
				
				}
			
			}
		
			return res;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	private String getUTMValue( String url_value, String utm  ){
		
		String utm_medium = null;
		
		try{
			
			if( url_value != null && url_value.trim().length() > 0 ){
			
				URL url = new URL(url_value) ;
				
				Map<String, List<String>> map = splitQuery( url);
				
				List<String> this_utm_partner_id = map.get(utm); // map.get("utm_medium");
				
				if( this_utm_partner_id != null && this_utm_partner_id.size() > 0){
				
					String utm_partner_id = this_utm_partner_id.get(0);
					
					if ( utm_partner_id != null &&  utm_partner_id.trim().length() > 0 ){
					
						utm_medium = utm_partner_id;
						
					}
				
				}
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return utm_medium;
	}
	
	
	private  Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException   {
		  final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		  final String[] pairs = url.getQuery().split("&");
		  for (String pair : pairs) {
		    final int idx = pair.indexOf("=");
		    final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
		    if (!query_pairs.containsKey(key)) {
		      query_pairs.put(key, new LinkedList<String>());
		    }
		    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
		    query_pairs.get(key).add(value);
		  }
		  return query_pairs;
		}
	
}
