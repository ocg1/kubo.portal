package mx.com.kubo.controller.hs_connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.TimeZone;

import org.primefaces.json.JSONObject;

import mx.com.kubo.tools.Utilities;

public class HubSpotController {
	
	 
	private final String[] meses_c ={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"}; 
		
	
	public Integer saveProspectus( String properties ){
		
		String url="https://api.hubapi.com/contacts/v1/contact/?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		
		return executeCallBack( url , properties );
		
	}
	
	public Integer updateProspectus( Integer vid , String properties ){
		
		String url="https://api.hubapi.com/contacts/v1/contact/vid/"+vid+"/profile?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		
		return executeCallBack( url , properties );
	}
	
	public  Integer createField( String properties ){
		
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
				int HttpResult = con.getResponseCode();
				
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
		int HttpResult = con.getResponseCode();
		
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
	
	
	private  Integer executeCallBack( String url , String properties ){
		
			properties = validaStr( properties );
			
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
			int HttpResult = con.getResponseCode();
			
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
			    
				System.out.println(con.getResponseMessage());
			    
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
	
	private String validaStr( String properties ){
		
		String values = "";
		
		String year = null;
		String day = null;
		String month = null; 
		
		String[] val = properties.split("&");
		
		if( val != null && val.length > 1 ){
			
			for ( int y = 0 ; y < val.length ; y++ ){
			
			//	for ( int y = 0 ; y < 20 ; y++ ){
				
				if( val[ y ] != null && val[ y ].trim().length() > 0 & (val[ y ].split("=")).length == 2){
				
						String property = val[ y ].split("=")[0] ;
						String value = val[ y ].split("=")[1] ;
						
						if(property.equals("day") || property.equals("month") || property.equals("year") ||  property.equals("selling_") ){
							
							if(property.equals("day"))
								day = value;
							if(property.equals("month"))
								month = value;
							if(property.equals("year"))
								year = value;
							
							continue;
							
						}
						
						if(y != 0){
							values += ",";
						}
						
						String n =  validaNombre( property.trim().toLowerCase() );
						
						if( !isNumericField(  n ) ){
						
							values += " { \"property\" : \""+n+"\" , \"value\" : \""+Utilities.quitaAcentos(value)+"\" }" ;
						
						}else{
							
							value = value.replace("$", "").replaceAll(",", "");
							values += " { \"property\" : \""+n+"\" , \"value\" : "+value+" }" ;
						}
			
				}
			
			}
			
			if( day != null && month != null && year != null ){
				
				//SimpleDateFormat  fr = new SimpleDateFormat("dd/MMMM/yyyy");
				
				SimpleDateFormat fr = new SimpleDateFormat("dd/MM/yyyy");
				
				//fr.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

				String fecha = converterDate( day , month , year );
				
				try{
					
					Date d = fr.parse(fecha);
					
					TimeZone tz = TimeZone.getTimeZone("UTC");
			        
					Calendar c = Calendar.getInstance();
					
					c.setTimeZone(tz);
					
					c.setTime(d);
					
					
					c.set(Calendar.HOUR, 0);
					
					c.set(Calendar.MINUTE, 0);
					
					c.set(Calendar.SECOND, 0);
					
					c.set(Calendar.MILLISECOND, 0);
					
					values += " ,{ \"property\" : \"birthday\" , \"value\" : \""+(c.getTimeInMillis() )+"\" }" ;
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}else{
			values = properties;
		}
		
		return values;
		
	}
	
	private String validaNombre( String property ){
		
		String res = "";
		
		
		
		if( property.equals("name") ){
			res = "firstname";
		}else if( property.equals("apaterno") ){
			res = "lastname";
		}else if( property.equals("amaterno") ){
			res = "last_name2";
		}else if( property.equals("second_name") ){
			res = "second_name";
		}else if( property.equals("zip_code") ){
			res = "zip";
		}else if( property.equals("street") ){
			res = "address";
		}else if( property.equals("delegmun") ){
			res = "city";
		}else if( property.equals("ammount_input") ){
			res = "ammount";
		}else if( property.equals("phonecel_employ") ){
			res = "mobilephone";
		}else if( property.equals("phonefixed_employ") ){
			res = "phone";
		}else if( property.equals("kuboscore") ){
			res = "inputkuboscore";
		}else if( property.equals("tipo-cliente") ){
			res = "tipo_cliente";
		}else if( property.equals("kuboscore_int") ){
			res = "bc_score_int";
		}
		

		
		
		else {
			res = property;
		}
		
		return res;
		
	}
	
	private boolean isNumericField( String  n ){
		
		boolean res = false;
		
		if( n.equals("ammount") ){
			
			res = true;
		}if( n.equals("inp_food") ){
			
			res = true;
		
		}if( n.equals("gastototal") ){
			
			res = true;
		
		}if( n.equals("inp_gas") ){
			
			res = true;
			
		}if( n.equals("inp_phone") ){
			
			res = true;
			
		}if( n.equals("inp_income") ){
			
			res = true;
		
	    }if( n.equals("inp_maintenance_house_or_apartment") ){
			
	    	res = true;
			
	    }if( n.equals("inp_credits") ){
			
	    	res = true;
			
	    }if( n.equals("inp_series") ){
			
	    	res = true;
			
	    }if( n.equals("inp_entertainment") ){
			
	    	res = true;
			
	    }if( n.equals("inp_medical") ){
			
	    	res = true;
			
	    }if( n.equals("inp_school") ){
			
	    	res = true;
			
	    }if( n.equals("inp_gasoline_transport") ){
			
	    	res = true;
			
	    }if( n.equals("inp_food_at_work") ){
			
	    	res = true;
	    }if(  n.equals("inp_wages_salary") ){
	    	
	    	res = true;
	    }
	    
	    if(  n.equals("selling_monday") ){
			res = true;
	    }
	    if(  n.equals("selling_tuesday") ){
			res = true;
	    }
		if(  n.equals("selling_wednesday") ){
			res = true;
	    }
		if(  n.equals("selling_thursday") ){
			res = true;
	    }
		if(  n.equals("selling_friday") ){
			res = true;
	    }
		if(  n.equals("selling_saturday") ){
			res = true;
	    }
		if(  n.equals("selling_sunday") ){
			res = true;
	    }
		if(  n.equals("selling_mounthly_sales") ){
			res = true;
	    }
		if(  n.equals("selling_fortnight_1") ){
			res = true;
	    }
		if(  n.equals("selling_fortnight_2") ){
			res = true;
	    }
		if(  n.equals("operating_rent") ){
			res = true;
	    }
		if(  n.equals("operating_serv") ){
			res = true;
	    }
		if(  n.equals("operating_phone") ){
			res = true;
	    }
		if(  n.equals("operating_taxes") ){
			res = true;
	    }
		if(  n.equals("operating_gas") ){
			res = true;
	    }
		if(  n.equals("operating_maintenance") ){
			res = true;
	    }
		if(  n.equals("operating_accountant") ){
			res = true;
	    }
		if(  n.equals("operating_employment") ){
			res = true;
	    }
		if(  n.equals("operating_other") ){
			res = true;
	    }
		if(  n.equals("inp_other_income") ){
			res = true;
	    }
		if(  n.equals("gastosNegocio") ){
			res = true;
	    }
		
		if(  n.equals("frm_questPLD_momentmaxdep") ){
			res = true;
	    }
		if(  n.equals("frm_questPLD_momentmaxret") ){
			res = true;
	    }
		if(  n.equals("frm_questPLD_montomaxsal") ){
			res = true;
	    }
		
		if(  n.equals("operating_cost_rent") ){
			res = true;
	    }
		if(  n.equals("operating_cost_serv") ){
			res = true;
		}
		if(  n.equals("operating_cost_phone") ){
			res = true;
		}
		if(  n.equals("operating_cost_taxes") ){
			res = true;
		}
		if(  n.equals("operating_cost_gas") ){
			res = true;
		}
		if(  n.equals("operating_cost_maintenance") ){
			res = true;
		}
		if(  n.equals("operating_cost_accountant") ){
			res = true;
		}
		if(  n.equals("operating_cost_employment") ){
			res = true;
		}
		if(  n.equals("operating_cost_other") ){
			res = true;
		}
		if(  n.equals("inp_light") ){
			res = true;
		}
		if(  n.equals("inp_value_opexpenses") ){
			res = true;
		}
		
		if(  n.equals("inp_how_often") ){
			res = true;
		}
		
		if(  n.equals("inp_income_other_family") ){
			res = true;
		}
		if(  n.equals("inp_income_otherfamilye") ){
			res = true;
		}
		if(  n.equals("gastosnegocio") ){
			res = true;
		}
		if(  n.equals("inp_business_company") ){
			res = true;
		}if(n.equals("bc_score_int")){
			res = true;
		}

		
		return res;
		
	}

	private String converterDate( String day , String month , String year ){
		
		String fecha = "";
		
		if( Integer.parseInt( day ) < 10 ){
			day = "0"+day;
		}
		
		fecha += day+"/";
		
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
	
}
