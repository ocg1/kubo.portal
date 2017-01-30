package mx.com.kubo.controller.efl_connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.primefaces.json.JSONObject;

import mx.com.kubo.model.EflScore;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.services.EflScoreService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.tools.Utilities;

public class EflConnect {
	
	private String estatus;
	
	private String eflRes; 
	
	private final int COMPANY_ID = 1;

	
	public void EFLResult( String prospectus_id, String mx_solicitud_buro  ){
	
		HttpURLConnection conn = null;
		
		try {
			
			
			URL url = new URL("http://info.kubofinanciero.com/eflws/index.php/scores/getDataByExternalKey/"+prospectus_id);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			/*
	        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	        
			String input = "{ "
					
								+ "\"externalkey\": "	+  prospectus_id		+" " // 3 - 26
								
						
						+ "}";

			//input = quitaAcentos( input );
			
			System.out.println(input);
			
			writer.write(input);
	        writer.close();*/
			
			int code = 0;
			
			try{
				
				code = conn.getResponseCode();
				
			}catch( ConnectException ne ){
				
				System.out.println( "ConnectException: " + ne );
				ne.printStackTrace();
				
				code = 0;
			}
	        
			if ( code != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "+ code );
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			
			if ((output = br.readLine()) != null) {
				
				try{
					
					System.out.println(output);
					
					eflRes = output;
					
					JSONObject jsonObj = new JSONObject( output );
					
					Integer estado	=	(Integer) jsonObj.get("estado");
					
					estatus = estado+"";
					
					System.out.println( "estado: " + estado );
					
					guardaReturn( prospectus_id, mx_solicitud_buro, estado+"" , output );
					
				}catch(Exception e){
					
					e.printStackTrace();
					
					
				}
				
			}

			

		  } catch (MalformedURLException e) {
			  
			e.printStackTrace();
			
			
			
		  } catch (IOException e) {

			e.printStackTrace();
			
		  }finally{
			  
			  if(conn != null)
				  conn.disconnect();
			  
		  }
	
	}
	
	private void guardaReturn( String prospectus_id, String  mx_solicitud_buro, String  estado , String output ){
		
		EflScoreService service = Utilities.findBean( "eflScoreServiceImp" );
		
		EflScore eflscr  = new EflScore();
		
		eflscr.setCompany_id(COMPANY_ID);
		eflscr.setProspectus_id( Integer.parseInt(prospectus_id) );
		eflscr.setConsulting_date(new Date());
		eflscr.setConsulting_status(estado);
		eflscr.setEfl_return(output);
		eflscr.setMx_solicitud_buro(mx_solicitud_buro);
		
		service.saveEflScore(eflscr);
		
		// --
		
		ScoringService scoringservice =  Utilities.findBean( "scoringServiceImp" );
		
		Scoring score = scoringservice.loadScoringByBurSolNum(mx_solicitud_buro);
		
		score.setEfl_test(estado);
		
		scoringservice.updateScoring(score);
		
	}


	public String getEstatus() {
		return estatus;
	}


	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}


	public String getEflRes() {
		return eflRes;
	}


	public void setEflRes(String eflRes) {
		this.eflRes = eflRes;
	}
	
}
