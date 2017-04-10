package mx.com.kubo.managedbeans.navigation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONObject;

import mx.com.kubo.bean.HS_OBJ;
import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.controller.efl_connect.EflConnect;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.managedbeans.registro.documentacion.AddPldDocument;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.EflScore;
import mx.com.kubo.model.Fields;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.segment.SegmentProspectus;

import mx.com.kubo.tools.Utilities;

public abstract class NavigationBeanAMO extends NavigationBeanDMO
{
	private ResourceBundle recurso;
	private MenuRegBean    menu;
	private Access         access;
	private AddPldDocument addPldDocument;
	
	private MembershipPK   membership_PK;
	protected Membership     membership;
	
	private List<AccessCollector> lista_menu_access;
	
	private String score_A;		
	
	protected final void asignar_loaner()
	{
		membership_PK = new MembershipPK();
		
		membership_PK.setCompany_id(sesion.getCompany_id());
		membership_PK.setProspectus_id(sesion.getProspectus_id());
		
		membership = membershipService.getMembershipById(membership_PK);
				
		if(isPantalla_estado_cuenta_ENABLED(sesion.getCompany_id()))
		{		
			String safi_client_id = membership.getPerson().getSafi_client_id();
			
			if(safi_client_id != null && safi_client_id.trim().length() > 0)
			{
				setLoaner(true);
			}
		}	
	}
	
	protected final void asignar_BC_score()
	{
		flagBCScore = false;
		
		score = null;
		
		area = sesion.getArea() + "";
		
		if(sesion.getArea() == null || area.equals("L"))
		{		
			 score = scoringService.loadMaxScoringByProspectus(getProspectus(), getCompany());			
			
			if(score == null)
			{
				flagBCScore = false;
				
			} else {
				
				flagBCScore = true;
			}
				
		} else if(area.equals("I")) {
			
			flagBCScore = true;
		}	
		
		if(area.equals("I") || area.equals("M"))
		{
			setDisplaySim(false);
		}
	}
	
	protected final void asignar_menu_items()
	{
		recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		try
		{
			lista_menu_access = accessService.loadMenu(getProspectus(), getCompany(), sesion.getArea());
			
			lista_menu_items = new ArrayList<MenuRegBean>();
			
			int i = 0;
			
			if(sesion.getArea().toString().equals("I")) {
				 i = 1;
			}
				
			for(AccessCollector access_collector: lista_menu_access)
			{
				menu = new MenuRegBean();							
				
				String resource_name = access_collector.getResource_name();
				String target_item   = access_collector.getName();
				String obligatorio   = access_collector.getIs_obligatory();
				
				Integer menu_order   = access_collector.getMenu_order(); 
				Integer screen_id    = access_collector.getScreen_id();
				
				int porcentage = ((access_collector.getPercentage() == null) ? 0 : access_collector.getPercentage());
								
				if(i != 0) {
					menu.setPosition(i);
				}
		
				menu.setIdItem("menu" + menu_order);
				
				menu.setNumItem("menuItemNum");
				menu.setPorcClassItem("porcent");
				
				menu.setScreenid(screen_id);
				menu.setTargetItem(target_item);				
				menu.setPorcItem(porcentage);
				menu.setNameItem(recurso.getString(resource_name));

				if(screen_id == 6 )
				{
					menu.setDisplayBar(false);
				} else {
					
					menu.setDisplayBar(true);
				}
			
				if(obligatorio != null && obligatorio.equals("S") )
				{
					menu.setObligatory("S");
					
				} else {
					
					menu.setObligatory("N");
				}
							
				
				if(!flagBCScore && i > 2 && !(sesion.getArea() + "").equals("I"))
				{
					menu.setIsblocked("block");
					menu.setIsConBlocked("none");
					menu.setClassItem("menuItemUnHover");
					
				} else {
												
					if(i > 2 && !(sesion.getArea() + "").equals("I") && score != null)
					{
						score_A = score.getKubo_score_a();
						
						if(score_A == null || ! score_A.equals("N"))
						{
							menu.setIsblocked("none");
							menu.setIsConBlocked("block");
							menu.setClassItem("menuItem");
							
						} else {
							menu.setIsblocked("block");
							menu.setIsConBlocked("none");
							menu.setClassItem("menuItemUnHover");
						}
						
					} else {
						menu.setIsblocked("none");
						menu.setIsConBlocked("block");
						menu.setClassItem("menuItem");
					}
				}
				
				menu.setHs_name(resource_name);
				
				lista_menu_items.add(menu);
				
				i++;						
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected boolean is_Rechazo(){
		
			boolean flagRechazado = false;
			
			int SEGMENTO_RIESGO_BURO = 1;
			
			int SEGMENTO_RECHAZADO = 3;
			
			int RISK_PROCESSED = 1;
			
			int STATUS_RECHAZADO = 11;
			
			ProyectLoan proyectloan = proyectloanservice.getMaxProyectLoanByProspect(getProspectus(), getCompany());
			
			if( proyectloan != null && proyectloan.getStatus_id() != null && proyectloan.getStatus_id().intValue() == STATUS_RECHAZADO )
			{
//				system_param_PK = new SystemParamPK();
//				system_param_PK.setCompany_id(sesion.getCompany_id());
//				system_param_PK.setSystem_param_id(65);
//				
//				system_param = systemparamservice.loadSelectedSystemParam( system_param_PK );
//				
//				if( system_param != null )
//				{									
//					Calendar c = Calendar.getInstance();
//					c.setTime(proyectloan.getConsulting_date());
//					Calendar c2 = Calendar.getInstance();
//					c2.setTime( new Date() );
//					
//					long l = c2.getTimeInMillis() - c.getTimeInMillis();
//					
//					long dif = l /((24 * 60 * 60 * 1000)); 
//					
//					if( Integer.parseInt(system_param.getValue()) < dif )
//					{
				
				List<SegmentProspectus> lst = segment_prospectus_service.loadSegmentProspectListByType( proyectloan.getProyectloanPk().getProspectus_id() , proyectloan.getProyectloanPk().getCompany_id(), SEGMENTO_RIESGO_BURO);
				
				if( lst != null && lst.size() == 1 && lst.get(0).getPk().getSegment_id() == SEGMENTO_RECHAZADO  ){
					
					Scoring score = scoringService.loadMaxScoringByProspectus(proyectloan.getProyectloanPk().getProspectus_id(), proyectloan.getProyectloanPk().getCompany_id());
					
					if( score != null && score.getRisk_processed() != null && score.getRisk_processed().intValue() == RISK_PROCESSED  ){
						flagRechazado = true;
					}
					
					if(score == null){
						flagRechazado = true;
					}
				
				}else{
					
					if( lst == null || lst.size() == 0){
						flagRechazado = true;
					}
					
				}
//					}									
//				}									
			}
			
//			if( flagRechazado )
//			{									
//				init_access(RECHAZO_AUTOMATICO);
//				
//				screen_PK = new ScreenPK();
//				screen_PK.setCompany_id(sesion.getCompany_id());
//				screen_PK.setScreen_id(RECHAZO_AUTOMATICO);
//				
//				screen =  screenservice.getScreenById(screen_PK);
//				
//				sesion.setLastPage(screen.getName());
//				
//			}
			
			return flagRechazado;
			
		
		
	}
	
	protected boolean is_EFL(){
		
		int SEGMENTO_RIESGO_BURO = 1;
		
		int SEGMENTO_EFL = 8;
		
		int RISK_PROCESSED = 1;
		
		int EFL_TEST_NO_FINISHED = 0;
		
		boolean is_efl = false;
		
		List<SegmentProspectus> lst = segment_prospectus_service.loadSegmentProspectListByType( getProspectus(), getCompany(), SEGMENTO_RIESGO_BURO);
		
		if( lst != null && lst.size() == 1 && lst.get(0).getPk().getSegment_id() == SEGMENTO_EFL  ){
			
			Scoring score = scoringService.loadMaxScoringByProspectus(getProspectus(), getCompany());
		
			if( 
				score != null && score.getRisk_processed() != null && score.getRisk_processed().intValue() == RISK_PROCESSED
				&& score.getEfl_test() != null && Integer.parseInt( score.getEfl_test() ) == EFL_TEST_NO_FINISHED
				
				){
				
				EflScore eflscore =  eflscoreservice.getMaxEflScoreByBurSolNum(score.getMx_solicitud_buro());
				
				if( eflscore != null ){
				
					String res = eflscore.getEfl_return();
					
					if( res != null ){
						try{
							
							if( validaEFLString(res) ){
							
								is_efl =  validaEFL(  score.getProspectus_id()+"" , score.getMx_solicitud_buro() );
							
								if( !is_efl ){
									score.setEfl_test("1");
								}else{
									score.setEfl_test("0");
								}
								
								scoringService.updateScoring(score);
								
							}else{
								
								score.setEfl_test("1");
								
								scoringService.updateScoring(score);
								
								is_efl = false;
							}
						
						}catch(Exception e){
							e.printStackTrace();
							is_efl = true;
						}
						
					}
					
				}else{
					
					is_efl =  validaEFL(  score.getProspectus_id()+"" , score.getMx_solicitud_buro() );
				
					if( !is_efl ){
						score.setEfl_test("1");
					}else{
						score.setEfl_test("0");
					}
					
					scoringService.updateScoring(score);
					
				}
				
			}
			
		}
		
		return is_efl;
		
	}
	
	private boolean validaEFL( String prospectus_id , String mx_solicitud_buro ){
		
		EflConnect eflconnect = new  EflConnect();
		
		eflconnect.EFLResult(prospectus_id, mx_solicitud_buro);
		
		String res = eflconnect.getEflRes();
		
		if( eflconnect.getEstatus() != null && eflconnect.getEstatus().equals("1") ){
		
			return validaEFLString( res );
		
		}else{
			return true;
		}
		
	}
	
	private boolean validaEFLString( String strEfl ){
		
		boolean is_efl = false;
		
		try{
				
			JSONObject jsonObj = new JSONObject( strEfl );
			
			Integer 	estado=(Integer) jsonObj.get("estado");
			
			if( estado == 1 ){
			
				String scr = (String)jsonObj.get("score");
				
				if( Utilities.isNumeric(scr) ){
					is_efl = false;
				}else{
					is_efl = true;
				}
				
			}else{
				is_efl = true;
			}
			
		}catch(Exception e){
			is_efl = true;
		}
		
		return is_efl;
		
	}
	
	protected final void asignar_pagina_actual()
	{
		if(sesion.getLastPage() != null)
		{
			if(sesion.getLastPage().length() > 0)
			{
				setPaginaActual(sesion.getLastPage());
			} else {	
				
				switch (sesion.getArea()) 
				{
					case 'L':
						setPaginaActual(paginaIni);
					break;
					
					case 'I':
						setPaginaActual(paginaIni);
					break;
					
					case 'M':
						setPaginaActual("controlTable/searchRequest");
					break;
					
					default: break;
				}		
			}
			
		} else {			
			
			switch (sesion.getArea()) 
			{
				case 'L':
					setPaginaActual(paginaIni);
				break;
				
				case 'I':
					setPaginaActual(paginaIni);
				break;
				
				case 'M':
					setPaginaActual("controlTable/searchRequest");
				break;
				
				default: break;
			}			
		}
		
		if(paginaActual.equals("registro/cierre"))
		{
			
			setDisplaySim(false);
			
		} else {
			
			setDisplaySim(true);
		}
		
		if(area.equals("I") && paginaActual.equals("controlTable/searchRequest"))
		{
			paginaActual = paginaIni;
		}
	}
	
	public void registrar_bitacora_acceso()
	{
		for(MenuRegBean menu_item : lista_menu_items)
		{			
			if(menu_item.getTargetItem().equals(getPaginaActual()))
			{
				menu_item.setClassItem("menuItemSel");
				menu_item.setPorcClassItem("porcentSel");
				menu_item.setNumItem("menuItemNumSel");
				
				setMenuSel(menu_item.getIdItem());
				
				access = new Access();
				
				System.out.println( "NavigationBeanAMO screen_id 99 = " + screen_id );
				
				access.setCompany_id(getCompany());
				access.setProspectus_id(getProspectus());
				access.setScreen_id(menu_item.getScreenid());
				access.setPercentage(menu_item.getPorcItem());
				access.setWeb_browser        (sesion.getNamebrawser());
				access.setWeb_browser_version(sesion.getVersionbrawser());
				access.setOp_system          (sesion.getOsbrawser());
				access.setHorizontal_size    (sesion.getBrowser_width());
				access.setVertical_size      (sesion.getBrowser_height());
				access.setIpaddress          (sesion.getIP_address_client());
				access.setUser_agent         (sesion.getUser_agent());
				access.setDevice_info        (sesion.getDevice_info());				
				access.setProspectus_id_coach(sesion.getCoachProspectus_id());
				access.setUrl_access		  (sesion.getUrl_access());
				
				//Determinar campos obligatorios de actual menu.
				if(sesion.getArea().toString().equals("L"))
				{
					setHasValidScore(((score != null && score.getScoring_result_id() > 0) ? true : false));	
					
					htDisabledFields = new Hashtable<String, Fields>();
					
					List<Fields> lstFields = fieldsService.getListByScreenId(menu_item.getScreenid(),sesion.getCompany_id());
					
					for (Fields  fieldsIter: lstFields) 
					{
						if(fieldsIter.getBlocked_after_BC().equals("S"))
						{
							if(isHasValidScore())
							{
								fieldsIter.setIs_enabled("N");
								
								htDisabledFields.put(fieldsIter.getName(), fieldsIter );
							}
						}
					}
				}
				
				access.setProspectus_id_coach (sesion.getCoachProspectus_id());
				access.setAccess_from		  (sesion.getAccess_from());
				access.setVersion_description (sesion.getVersion_description());
				
				accessService.add(access, true);
				
				break;
			}
		}
	}
	
	public final void asignar_menu_item_selected(String menu_item_selected)
	{
		for(MenuRegBean menu_item : lista_menu_items)
		{
			if(menu_item.getTargetItem().equals(getPaginaActual()))
			{
				menu_item.setClassItem("menuItem");
				menu_item.setPorcClassItem("porcent");
				menu_item.setNumItem("menuItemNum");
				
				setMenuSel(menu_item.getIdItem());
				
				break;
			}
		}
		
		menuSel      = menu_item_selected.split("::")[2];	
		screen_id    = menu_item_selected.split("::")[1];
		paginaActual = menu_item_selected.split("::")[0];
		
		System.out.println( "NavigationBeanAMO screen_id = " + screen_id );
		
		if( screen_id.equals("3") ){
			screen_id = "2";
			paginaActual="registro/basicosHistorial";
		}
		
		if(paginaActual.equalsIgnoreCase("registro/documentationAndPld"))
		{
			faces     = FacesContext.getCurrentInstance();
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
			
			addPldDocument = (AddPldDocument) resolver.getValue(elContext, null, "addPldDocument");
			addPldDocument.init();
		}
		
		Integer porc = 0;
		
		for(MenuRegBean menu_item : lista_menu_items)
		{
			if(menu_item.getTargetItem().equals(getPaginaActual()))
			{
				menu_item.setClassItem("menuItemSel");
				menu_item.setPorcClassItem("porcentSel");
				menu_item.setNumItem("menuItemNumSel");
				
				setMenuSel(menu_item.getIdItem());
				
				porc = menu_item.getPorcItem();
				
				break;
			}
		}
		
		System.out.println( "NavigationBeanAMO screen_id = " + screen_id );
		
		Access access = new Access();
		access.setCompany_id(getCompany());
		access.setProspectus_id(getProspectus());
		access.setScreen_id(Integer.parseInt(screen_id));
		access.setPercentage(porc);
		access.setWeb_browser        (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system          (sesion.getOsbrawser());
		access.setHorizontal_size    (sesion.getBrowser_height());
		access.setVertical_size      (sesion.getBrowser_width());
		access.setIpaddress          (sesion.getIP_address_client());
		access.setProspectus_id_coach(sesion.getCoachProspectus_id());
		access.setUser_agent         (sesion.getUser_agent());
		access.setDevice_info        (sesion.getDevice_info());
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		//Determinar campos obligatorios por menu
		if(sesion.getArea().equals('L') && isHasValidScore())
		{			
			List<Fields> lstFields=fieldsService.getListByScreenId(access.getScreen_id(),sesion.getCompany_id());
			
			for (Fields  fieldsIter: lstFields) 
			{
				if(fieldsIter.getBlocked_after_BC().equals("S"))
				{
					if(isHasValidScore())
					{
						fieldsIter.setIs_enabled("N");
						
						htDisabledFields.put(fieldsIter.getName(), fieldsIter);
					}
				}
			}
		}
		
		accessService.add(access, true);
		
		if(paginaActual.equals("registro/cierre"))
		{
			setDisplaySim(false);
			//simulator.simulaCred(true);
			
		} else {
			
			if(area.equals("L")) {
				try{
					setDisplaySim(true);
					simulator.simulaCred(false);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			} else {
				
				setDisplaySim(false);
			}									
		}			
	}
	
	
	protected void actualizaHS_VID(){
		
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		String cookie = getCookieRequest( request );
		
		if( cookie != null ){
			
			Integer hs_vid =  getHSVID( cookie );
		
			//TODO TRAE utm_source utm_medium  registration_reason
			
			HubSpotController hs  = new HubSpotController();
			
			String json_str =hs.getJSONProperties( hs_vid );
			
			HS_OBJ hs_obj =hs.createHSOBJ( json_str );
			
			if( hs_obj != null ){
			
				ProspectusPK ppk = new ProspectusPK();
				
				ppk.setCompany_id(getCompany());
				ppk.setProspectus_id( getProspectus() );
				
				Prospectus prospectus = prospectusService.getProspectusById(ppk) ;
				
				prospectus.setHs_vid(hs_vid);
				
				membership = membershipService.getMembershipById(membership.getMembershipPK());
				
				membership.setRegistration_reason_id(hs_obj.getRegistration_reason_id());
				
				membership.setOrigin( hs_obj.getUrl_value() );
				
				StringBuilder properties = new StringBuilder();
				
				properties.append("{ \"property\" : \"prospectoid\" , \"value\" : \""+getProspectus() +"\"},");
				
				properties.append("{ \"property\" : \"alta_kubo\" ,\"value\":\"S\" }");
				
				properties.append(",{\"property\": \"tipo_cliente\" , 		 \"value\" : \""+
						
											( membership.getPerson().getProspectus().getArea().toString().equals("L")?"acreditado":membership.getPerson().getProspectus().getArea().toString().equals("I")?"inversionista":"" )
					
											+"\"}" );
				
				hs.updateProspectus(hs_vid, properties);
			
			}
			
		}
		
	}
	
	
	private String getCookieRequest( HttpServletRequest request ){
		
		Cookie[] cookies = request.getCookies();
		
		String hsutk = null;
		
		  if(cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		         
		    	  Cookie  cookie=cookies[i];
		          
		    	  String cookieName = cookie.getName();
		          String cookieValue = cookie.getValue();
		          String domain = cookie.getDomain();
		          
		          if( domain != null && cookieName != null && cookieValue != null ){
		          
		        	  System.out.println( "*********************************************************");
		        	  System.out.println( "*********************************************************");
		        	  System.out.println( "*********************************************************");
		        	  System.out.println( "cookieName: " +  cookieName);
		        	  System.out.println( "cookieValue: " + cookieValue );
		        	  System.out.println( "domain: " +  domain );
		        	  System.out.println( "*********************************************************");
		        	  System.out.println( "*********************************************************");
		        	  System.out.println( "*********************************************************");
		        	  
			          if( cookieName.equals("hubspotutk") && domain.indexOf("kubofinanciero") != (-1) ){
			        	  
			        	  hsutk = cookieValue;
			        	  break;
			        	  
			          }
		          
		          }
		          
		       }
		      
		   }
		
		
		  return hsutk;
		  
	}
	
	private Integer getHSVID( String cookie ){
		
		HttpURLConnection con = null;
		Integer 	vid = null;
		
		
		try{
		
		String url="http://api.hubapi.com/contacts/v1/contact/utk/"+cookie+"/profile/?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		URL object=new URL(url);
		
		String charset = "UTF-8";

		con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		
		con.setRequestProperty("Accept-Charset", charset);
		con.setRequestProperty("Content-Type", "application/json;charset="+charset);
		
		con.setRequestMethod("GET");
		
		String  s  = "";
		
		System.out.println(s);
		
		/*OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		
		wr.write( s );
		wr.close(); */
		
		//display what returns the POST request

		StringBuilder sb = new StringBuilder();  
		
		int HttpResult = 0;
		
		try{
			
			HttpResult = con.getResponseCode();
			
			
		}catch( ConnectException ne ){
			
			System.out.println( "ConnectException: " + ne );
			
			ne.printStackTrace();
			
			vid = null;
			
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
		    
		    //JSONObject jsonObj = new JSONObject( sb.toString() );
		    
		    JSONObject json = new JSONObject( sb.toString() );
			
		    vid = (Integer) json.get("vid");
			
			//hs_email =getMailFromHS( sb.toString() );
		    
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
	
}
