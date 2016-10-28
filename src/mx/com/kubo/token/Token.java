package mx.com.kubo.token;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.SMSRequestService;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TokenDB;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TokenDBService;
import mx.com.kubo.tools.Utilities;

public class Token {
	
	protected TokenDBService tokenservice;
	protected MembershipService membershipservice;
	
	protected SystemParamService systemservice;

	private final int min = 99999;
	private final int max = 9999999;
	private final int maximo_consecutivos = 2;
	private final int maximo_repetido = 3;
	private final int TIEMPO_EN_MINUTOS_DE_VALIDEZ_DEL_TOKEN = 2;
	private final int MAX_ATTEMPTS;
	
	public Token(){
		
		tokenservice = Utilities.findBean("tokenDBServiceImp");
		membershipservice = Utilities.findBean("membershipServiceImp");
		
		systemservice = Utilities.findBean("systemParamServiceImp");
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(1);
		spk.setSystem_param_id(64);
		
		SystemParam sys = systemservice.loadSelectedSystemParam(spk);
		
		MAX_ATTEMPTS = Integer.parseInt( sys.getValue() );
		
	}
	
	public boolean creaToken( Integer prospectus_id , Integer company_id, int evento_id){
		
		try{
		
			boolean flag = false;
			
			String tokenGen = "";
			String fechaMilis = "";
			
			while( !flag ){
				
				tokenGen = generaToken();
				
				if ( validaTokenUsed( Utilities.encrypt(""+tokenGen) , 1 ) ){
					
					TokenDB token = new TokenDB();
					
					token.setCompany_id(company_id);
					
					token.setIs_used("N");
					token.setProspectus_id(prospectus_id);
					token.setToken_id(Utilities.encrypt(""+company_id+prospectus_id+tokenGen+""));
					token.setToken_gen(Utilities.encrypt(""+tokenGen));
					
					UUID key_T =  UUID.randomUUID();
					
					String s = key_T.toString();
					UUID key = UUID.fromString(s);
					
					token.setMsb_key( key.getMostSignificantBits() +"");
					token.setLsb_key( key.getLeastSignificantBits() + "");
					
					Calendar cal = Calendar.getInstance();
					
					cal.setTime( new Date() );
					
					token.setCreation_date( cal.getTime() );
					
					String st = "" + cal.getTimeInMillis();
					
					fechaMilis = st.substring(0,(st.length()-3) )+"000";
					
					token.setToken_result( Utilities.encrypt(""+company_id+prospectus_id+evento_id+tokenGen+key.toString()+""+fechaMilis) );
					
					flag = tokenservice.insertToken( token );
					
				}
				
			}
			
			enviaToken( tokenGen,  prospectus_id, fechaMilis  );
			
			return flag;
		
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public TokenVerification verificaToken( Integer prospectus_id , Integer company_id, int evento_id, String token ){
		
		TokenVerification verification = new TokenVerification();
		
		try{
		
			
			TokenDB tokenbyID = getTokenDBByID (  Utilities.encrypt(""+company_id+prospectus_id+token+"") );
			
			MembershipPK mpk = new MembershipPK();
			
			mpk.setCompany_id(company_id);
			mpk.setProspectus_id(prospectus_id);
			
			Membership member = membershipservice.getMembershipById( mpk ); 
			
			if( tokenbyID != null ){
				
				if( tokenbyID.getIs_used() == null || tokenbyID.getIs_used().equals("N") ){
				
					UUID tmp_UUID = new UUID( Long.parseLong( tokenbyID.getMsb_key() ) , Long.parseLong( tokenbyID.getLsb_key() ) );
					
					Calendar cal = Calendar.getInstance();
					
					cal.setTime(tokenbyID.getCreation_date());
					
						if ( tokenbyID.getToken_result().equals( Utilities.encrypt(""+company_id+prospectus_id+evento_id+token+tmp_UUID.toString()+""+cal.getTimeInMillis()) ) ){
							
								Calendar cal1 = Calendar.getInstance();
								cal1.setTime(new Date());
								
								long l = cal1.getTimeInMillis() - cal.getTimeInMillis();
								
								long s = l/1000;
								Long m = s/60;
								
								if( m < TIEMPO_EN_MINUTOS_DE_VALIDEZ_DEL_TOKEN ){
									
									tokenbyID.setIs_used("S");
									tokenservice.updateTokenUsed(tokenbyID);
									
									verification.setValid(true);
									verification.setMessage("El código ha sido validado satisfactoriamente");
									
									member.setFailed_token_attempts(0);
									
									membershipservice.update(member);
									
								}else{
									// EL TOKEN HA EXPIRADO
									verification.setValid(false);
									verification.setMessage("El código ha expirado");
									
								}
								
						}else{
							// FALLÓ EL METODO DE VALIDACION, INFORMACIÓN INCONGRUENTE PARA LA GENERACIÓN DEL TOKEN
							verification.setValid(false);
							verification.setMessage("Falló el método de validación, información incongruente para la generación del código");
						}
					
				}else{
					//EL TOKEN YA HA SIDO UTILIZADO
					verification.setValid(false);
					verification.setMessage("El código ya ha sido utilizado");
				}
				
			}else{
				// NO EXISTE TOKEN 
				verification.setValid(false);
				verification.setMessage("No existe el código");
			}
			
			if( !verification.isValid() ){
			
				Integer failed = member.getFailed_token_attempts() == null ? 0 :  member.getFailed_token_attempts();
				
				failed = failed +1;
				
				if( failed >= MAX_ATTEMPTS ){
					
					member.setIs_blocked("S");
					verification.setBloqued(true);
					
				}
				
				member.setFailed_token_attempts(failed);
				
				membershipservice.update(member);
			
			}
		
		}catch( Exception e ){
			
			verification.setValid(false);
			verification.setMessage("Exception: "+e.getMessage() );
			
		}
		
		return verification;
		
	}
	
	private TokenDB getTokenDBByID ( String token_id ){
		return tokenservice.getTokenDBById(token_id);
	}
	
	private boolean  validaTokenUsed( String token, Integer prospectus_id ){
		
		boolean valido = false;
		List<TokenDB> lst = tokenservice.getTokenDBListByTokenId(token, prospectus_id);
		
		if( lst != null && lst.size() > 0 ){
			valido = false;
		}else{
			valido = true;
		}
		
		return valido;
		
	}
	
	private String generaToken(){
		
		String token = "";
		
		String code = "";
		
		while( !isValidFormatCode(code) ){
			
			code = generaCodigo();
			
		}
		
		token = code + Utilities.digitoVerificador(code);
		
		return token;
		
	}
	
	private String generaCodigo(){
		
		 Random rand = new Random();

		 int randomNum = rand.nextInt((max - min) + 1) + min;

		 String randomStr =  randomNum+"";
		    
		 while(randomStr.length()<7){
			  
			 randomStr = "0"+randomStr;
			 
		 }
		
		 return randomStr;
		  
	}
	
	private boolean isValidFormatCode( String codigo ){
		
		if( codigo == null || codigo.length() != 7 )
			return false;
		
		int repetido = 0;
		int consecutivo = 0;
		
		boolean flagrepetido = true;
		boolean flagconsecutivo = true;
		
		for( int i = 0 ; i < codigo.length() ; i++ ){
		
			char c = codigo.charAt(i);
			
			for( int x = 0 ; x < codigo.length() ; x++ ){
			
				if(x != i){
					
					char c2 = codigo.charAt(x); 
					
					if( c == c2  ){
						
						consecutivo ++;
						repetido++;
						
						if( consecutivo == maximo_consecutivos ){
							
							flagconsecutivo = false;
							
						}
						
					}else{
						consecutivo = 0;
					}
					
					
				}
				
			}
			
			if( repetido >= maximo_repetido ){
				flagrepetido = false;
			}
		
		}
		
		return flagrepetido && flagconsecutivo;
		
	}
	
	
	private boolean enviaToken(String tokenGen, Integer prospectus_id, String fechaMilis){
	
		//System.out.println( tokenGen );
		boolean flag = false; 
		try{
			PublicProyectServiceLocator kubolocator = new  PublicProyectServiceLocator();
			
			PublicProyect kuboservices =  kubolocator.getPublicProyect();
			
			SMSRequestService request =  new SMSRequestService() ;
			
			request.setBursolnum(null);
			request.setCampaign(null);
			request.setEmisor_id("0");
			request.setEvent_id("35");
			
			SimpleDateFormat frm = new SimpleDateFormat("hh:mm:ss a 'del dia' dd-MM-yy ");
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTimeInMillis( Long.parseLong( fechaMilis ) );
			
			cal.add(Calendar.MINUTE, TIEMPO_EN_MINUTOS_DE_VALIDEZ_DEL_TOKEN);
			
			String msg = "Tu codigo de seguridad de Kubo es "+tokenGen+" valido hasta  "+frm.format(cal.getTime());
			
			request.setMessage(msg);
			
			String[] str = {""+prospectus_id+""};
			
			request.setProspectus_id(str);
			
			kuboservices.enviaSMS(request);
			
			flag = true;
		
		}catch(Exception e){
			
			e.printStackTrace();
			flag = false;
		}
	
		return flag ;
		
	}
	
}
