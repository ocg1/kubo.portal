package mx.com.kubo.managedbeans;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ConsultingErrorBean;
import mx.com.kubo.bean.TemporalBean;
import mx.com.kubo.model.ClientView;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.notificaciones.notificables.Evento;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;

import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.BCRiskRequest;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped 
public class ConsultingController extends ConsultingControllerAMO 
implements Serializable, ConsultingControllerIMO 
{
	@PostConstruct
	public void init() 
	{				
		membership_PK = new MembershipPK();
		elContext = FacesContext.getCurrentInstance().getELContext();
		sesion    = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
				
		membership_PK.setProspectus_id(sesion.getProspectus_id());
		membership_PK.setCompany_id(sesion.getCompany_id());
		
		membership  = membershipService.getMembershipById(membership_PK);	
		
		reasonsLst  = reasonsService.loadRegistrationReasonList();
		
		role_function = (RoleFunctionController) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "roleFunctionController");
		
		simulator = (Simulator) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "simulator");
		
		List<RoleFunction> lista_funciones = role_function.getFunctionByRole(sesion.getRole_id());
		
		listSearchingType = new ArrayList<TemporalBean>();
		
		TemporalBean tmp = new TemporalBean();
		
		tmp.setDescription("Por Nombre Completo");
		tmp.setValue(1);
		
		listSearchingType.add(tmp);
		
		setSearchingType(1) ;
		
		boolean flagAlianzas = false;
		
		for( RoleFunction rf : lista_funciones ){
			
			if( rf.getPk().getFunction_id() == 23 ){ // Consulta por Alianza para consultas en forma manual
				flagAlianzas = true;
				break;
			}
			
		}
		
		if( flagAlianzas ){
			
			tmp = new TemporalBean();
			
			tmp.setDescription("Por Alianza");
			tmp.setValue(2);
			
			listSearchingType.add(tmp);
		}
		
	}
	
	public List<ClientView> completeinfoclient(String name)
	{
		suggestions = new ArrayList<ClientView>();
		
		suggestions = viewclientinfoservice.getListClientViewAllByName(name);
		
		return suggestions;		
	}
	
	public void selectionAction()
	{	
		
		if(getSearchingType() == 1 ){
		
			membership_PK = new MembershipPK(new Integer(search), 1);
			
			memberSel          = membershipService.getMembershipById(membership_PK);
			displaydataSuccess = true;
			
			
			if(memberSel.getPerson().getDate_of_birth() != null)
			{
				birthday = date_format.format(memberSel.getPerson().getDate_of_birth());
			} else {
				messageErrorDatos = " No cuenta con fecha de nacimiento.";
				displaydataSuccess = false;
			}
			
//			if(memberSel.getPerson().getResidence_id()==null)
//			{
//				messageErrorDatos += "\t No cuenta con Tipo de vivienda.";
//				displaydataSuccess = false;
//			}
			
			if(memberSel.getPerson().getGender_id() == null)
			{
				messageErrorDatos += "\t No seleccionó su Sexo.";
				displaydataSuccess = false;
			}
		
		}else if(getSearchingType() == 2) {
			
			try{
				if(origin_value == null)
					origin_value = "";
					
				List<Membership> list =  membershipService.getMembershipListByRegistrationReasonAndOrigin( registration_reason_id , origin_value );
				
				RequestContext requestContext = RequestContext.getCurrentInstance();
				
				if(  list != null && list.size() > 0 ){
				
					requestContext.addCallbackParam("miembrosList", new JSONArray(list.toArray(),true).toString());
					requestContext.addCallbackParam("is_valid", "S" );
				
				}else{
					
					requestContext.addCallbackParam("miembrosList", "" );
					requestContext.addCallbackParam("is_valid", "N" );
					
				}
				
			}catch( Exception e ){
				
				e.printStackTrace();
				
			}
			
		}
	}
	
	public void cargaClientes(){
		
		List<Membership> clientList2 = membershipService.getMemberShipForConsulting();
		
		clientList = new Membership[clientList2.size()];
		
		int i = 0;
		
		for( Membership mem : clientList2){
			clientList[i] = mem;
			i++;
		}
		
	}
	
	public void createConsulting(  )
	{
		createConsultingLst( memberSel  );
	}
	
	public void createConsultingLst( Membership memberSel_tmp  )
	{
		System.out.printf("\nConsultingController.createConsulting(): " + memberSel_tmp.getPerson().NombreCompletoNPM());
		
		consultaBuro         = false;
		displayConsulSuccess = false;				
		
		califKubo = ""; 
		bcscore   = ""; 
		rate      = "" ;		
		messageErrorConsulta = "";
		
		try
		{
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();
			
			srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(memberSel_tmp.getMembershipPK().getCompany_id());
			srvCall.setInfo("Invocando Servicio Web consulta a buró manual getProspectBCRisk SGB");
			srvCall.setProspectus_id(memberSel_tmp.getMembershipPK().getProspectus_id());
			srvCall.setStatus(1);
			
			servicecallingService.saveServiceCall(srvCall);
			
			//consulta a Buro de forma manual
			
			int age = memberSel_tmp.getPerson().getEdad();
			
			request = new BCRiskRequest();
			
			request.setAge(age+"");
			request.setBursolnum(null);
			request.setBusinessType("3227");
			request.setCompanyId(memberSel_tmp.getMembershipPK().getCompany_id()+"");
			request.setEventId(Evento.CONSULTA_BC_MANUAL.getId()+"");
			request.setGender((memberSel_tmp.getPerson().getGender_id()+""));
			if( memberSel_tmp.getPerson().getResidence_id() != null ){
				request.setHomeType(memberSel_tmp.getPerson().getResidence_id()+"");
			}else{
				request.setHomeType("1");
			}
			request.setProspectId(memberSel_tmp.getMembershipPK().getProspectus_id()+"");
			request.setProspectIdTemp(membership.getMembershipPK().getProspectus_id()+"");
			
			System.out.println(service.testConnection()); 
			
			prospect_bc_risk_response = service.getProspectBCRisk( request );
					
			// ProspectBCRiskResponse prospect_bc_risk_response =  service.getProspectBCRisk((memberSel.getMembershipPK().getProspectus_id()+""), (memberSel.getPerson().getResidence_id()+""), (memberSel.getPerson().getGender_id()+""), (age+""), "3227");
			
			System.out.println("Mensaje de Respuesta "+ prospect_bc_risk_response.getStatus());
			
			
//			Cambio que quita el parametro de la validacion
//			if(prospect_bc_risk_response!=null && prospect_bc_risk_response.getStatus().equals("0")){
			
			if( prospect_bc_risk_response != null )
			{
			
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(memberSel_tmp.getMembershipPK().getCompany_id());
				srvCall.setInfo("Regresando Satisfactorioamente de Servicio Web consulta a buró manual getProspectBCRisk SGB");
				srvCall.setProspectus_id(memberSel_tmp.getMembershipPK().getProspectus_id());
				srvCall.setStatus(2);
				
				servicecallingService.saveServiceCall(srvCall);
				
				score = new Scoring();
				
				score.setBc_score(prospect_bc_risk_response.getBurScore());				
				score.setBc_score_date(new Date());				
				score.setCci_score(prospect_bc_risk_response.getIcc());
				score.setCompany_id(memberSel_tmp.getMembershipPK().getCompany_id());
				score.setKubo_rate(prospect_bc_risk_response.getVotasakubo());
				
				if((prospect_bc_risk_response.getVocalkubo1()+prospect_bc_risk_response.getVocalkubo2()).equals("N0"))
				{
					score.setKubo_score_a("G");
					score.setKubo_score_b("1");
				}else if((prospect_bc_risk_response.getVocalkubo1()+prospect_bc_risk_response.getVocalkubo2()).equals("E6"))
				{
					score.setKubo_score_a("F");
					score.setKubo_score_b("1");
				} else{
					score.setKubo_score_a(prospect_bc_risk_response.getVocalkubo1());
					score.setKubo_score_b(prospect_bc_risk_response.getVocalkubo2());
				}
				
				if(prospect_bc_risk_response.getVoliquidez() != null)
				{
					score.setLiquidity(Double.parseDouble(prospect_bc_risk_response.getVoliquidez()));
				}
				
				score.setMx_folio(prospect_bc_risk_response.getBurFol());
				score.setMx_solicitud_buro(prospect_bc_risk_response.getBurSolNum());
				
				if(prospect_bc_risk_response.getVocomision() != null)
				{
					score.setOpening_commission(Double.parseDouble(prospect_bc_risk_response.getVocomision()));
				}
				
				score.setProspectus_id(memberSel_tmp.getMembershipPK().getProspectus_id());
				
				if(prospect_bc_risk_response.getTasaAcre() != null)
				{
					score.setRate(Double.parseDouble(prospect_bc_risk_response.getTasaAcre()));
				}
				
				if(prospect_bc_risk_response.getTasaInv() != null)
				{
					score.setRate_investor(Double.parseDouble(prospect_bc_risk_response.getTasaInv()));
				}
				
				score.setResult_datetime(new Date());
				score.setRisk_level(prospect_bc_risk_response.getVoriesgo());
				
				scoringService.saveScoring(score);
				
				califKubo = score.getKubo_score_a() + score.getKubo_score_b(); 
				bcscore   = score.getBc_score(); 
				rate      = score.getRate() + "%" ;
				
				messageErrorConsulta = "";
				displayConsulSuccess = true;				
				consultaBuro         = true;
				
				intConsultas++;
				
				crear_proyect_loan( memberSel_tmp );
			}
			
		} catch(Exception e) {
			
			displayConsulSuccess = false;
			messageErrorConsulta = e.getMessage();
			
			e.printStackTrace();
			 
			writer      = new StringWriter();
			printWriter = new PrintWriter(writer);
			
			e.printStackTrace(printWriter);
			msg = e.getMessage();
			
			evento = Evento.ERROR_DESARROLLO;
			evento.setError("managedbeans.ConsultingController.createConsulting(213): " + msg);
			
			notificar(evento, null,msg + " <br /> "+writer.toString(),null);
			
			srvCall = new ServiceCalling();

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(memberSel_tmp.getMembershipPK().getCompany_id());
			srvCall.setInfo("Error ConsultingController.createConsulting (220)");
			srvCall.setProspectus_id(memberSel_tmp.getMembershipPK().getProspectus_id());
			srvCall.setStatus(3);
			
			exception =  e.getStackTrace().toString().length() > 990 ? e.getStackTrace().toString().substring(0,990) : e.getStackTrace().toString();
			
			srvCall.setException(exception);
			
			servicecallingService.saveServiceCall(srvCall);
			
			ConsultingErrorBean errorbean = new ConsultingErrorBean();
			
			errorbean.setMember( memberSel_tmp );
			errorbean.setError_descript( msg );
			
			lstError.add( errorbean );
			
		}		
	}
	
	public void getDescritionPerson(){
		
		System.out.println("Persona Seleccionada: " + valPerSelStr );
		
		String strTmp = "persona_";
		
		String nombre 	= "" ;
		String fecnac	= "" ;
		String rfc  	= "" ;
		String curp  	= "" ;
		String str1  	= "";
		
		if( valPerSelStr != null && valPerSelStr.trim().length() > 0 ){
		 
			str1  = valPerSelStr.substring( strTmp.length()  );
			
			String company_tmp = str1.split("::")[0];
			
			String prospectus_id_tmp = str1.split("::")[1];
			
			MembershipPK membership_PK_tmp = new MembershipPK();
			
			membership_PK_tmp.setCompany_id( Integer.parseInt(company_tmp) );
			membership_PK_tmp.setProspectus_id(Integer.parseInt(prospectus_id_tmp));
			
			Membership memberSel_tmp = membershipService.getMembershipById(membership_PK_tmp);
			
			if(memberSel_tmp != null){
				
				nombre  = memberSel_tmp.getPerson().NombreCompletoNPM();
				
				if(memberSel_tmp.getPerson().getDate_of_birth() != null)
				{
					fecnac = date_format.format(memberSel_tmp.getPerson().getDate_of_birth());
				
				}else{
					
					fecnac = "NO PROPORCIONADA";
					
				}
				
				rfc  	= memberSel_tmp.getPerson().getMx_rfc() ;
				curp  	= memberSel_tmp.getPerson().getMx_curp() ;
			}
			
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		
		System.out.println(" ---  " + nombre );
		
		//nombre = nombre.replaceAll("ñ", "n");
		
		context.addCallbackParam("mi_valor", "regresandoExitosamente");
		
		context.addCallbackParam("dv_id", 	"dv_"+str1);
		context.addCallbackParam("nombre", 	nombre);
		context.addCallbackParam("fecnac", 	fecnac);
		context.addCallbackParam("rfc", 	rfc==null?"NO GENERADO":rfc);
		context.addCallbackParam("curp", 	curp==null?"NO GENERADO":curp);
		
	}
	
	public void realizaConsultaLst(){
		
		System.out.println("--- Realizando Consulta ---");
		
		System.out.println(valores_a_consultar);
		
		lstError = new ArrayList<ConsultingErrorBean>();
		
		intConsultas = 0;
		
		int inttotal = 0;
		
		if(valores_a_consultar != null && valores_a_consultar.trim().length() > 0){
		
			String[] arrayClientToConsult = valores_a_consultar.split("&&");
		
			for( String item : arrayClientToConsult ){
			
				
				msg="";
				inttotal++;
				
				String[] peronalData =  item.split("::");
				
				MembershipPK membership_PK_tmp = new MembershipPK();
				
				membership_PK_tmp.setCompany_id( Integer.parseInt( peronalData[0] ) );
				membership_PK_tmp.setProspectus_id( Integer.parseInt( peronalData[1] ) );
				
				Membership memberSel_tmp = membershipService.getMembershipById(membership_PK_tmp);
				
				if ( validaCURP_RFC( memberSel_tmp )){
				
					if( callSGBAltaUsuario( memberSel_tmp ) )
					{
						createConsultingLst( memberSel_tmp  );
					}
				
				}
				
			}
		
		}
		
		try{ 
			
			RequestContext context = RequestContext.getCurrentInstance();
			
			context.addCallbackParam("mi_valor", "regresandoExitosamente");
			
			if( lstError.size() > 0 ){
				
				context.addCallbackParam("haveErrors", true );
			
				context.addCallbackParam("lstErrors", 	new JSONArray(lstError.toArray(),true).toString());
			
			}else{
				
				context.addCallbackParam("haveErrors", false );
				
			}
			
			context.addCallbackParam("totalconsultas", 	inttotal 	);
			context.addCallbackParam("intConsultas", 	intConsultas);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}
		
	protected boolean validaCURP_RFC( Membership memberSel_tmp ){
		
		String RFC = "";
		String CURP = "";
		
		if( memberSel_tmp.getPerson().getDate_of_birth() == null ){
			
			ConsultingErrorBean errorbean = new ConsultingErrorBean();
			
			errorbean.setMember( memberSel_tmp );
			errorbean.setError_descript( "No cuenta con fecha de cumpleaños." );
			
			lstError.add( errorbean );
			return false;
		}
		
		
		try{
		
			if( memberSel_tmp.getPerson().getMx_rfc() == null || memberSel_tmp.getPerson().getMx_rfc().trim().length()>0 ){
				
				String name ="";
				
				if (memberSel_tmp.getPerson().getMiddle_name() != null)
				{
					name = memberSel_tmp.getPerson().getFirst_name().trim() + " " + memberSel_tmp.getPerson().getMiddle_name().trim();
				} else {
					name = memberSel_tmp.getPerson().getFirst_name().trim();
				}
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
				if( memberSel_tmp.getPerson().getDate_of_birth() != null ){
				
					String birthdayStr = formatter.format(memberSel_tmp.getPerson().getDate_of_birth());
					
					RFC         = naturalPersonService.getRFC( name , memberSel_tmp.getPerson().getFather_last_name(), memberSel_tmp.getPerson().getMother_last_name(), birthdayStr );
					memberSel_tmp.getPerson().setMx_rfc(RFC.substring( 0, ( RFC.length()-3 ) ) );
				
					
					
				}
			}
			
			if(memberSel_tmp.getPerson().getMx_curp() == null || memberSel_tmp.getPerson().getMx_curp().trim().length()>0  ){
				
				System.out.println("Obteniendo CURP");
				System.out.println("fecha de nacimiento: " + memberSel_tmp.getPerson().getDate_of_birth());
			
				String estado = "";
				boolean flag = false;
				
				if (memberSel_tmp.getPerson().getCitizenship()!=null&&memberSel_tmp.getPerson().getCitizenship() == 1)
				{
					if (memberSel_tmp.getPerson().getState_id() != null
					&& !memberSel_tmp.getPerson().getState_id().equals("")
					&& !memberSel_tmp.getPerson().getState_id().equals("0")) 
					{
						StateCatPK pk = new StateCatPK();
						
						pk.setCompany_id(memberSel_tmp.getPerson().getNatPerPK().getCompany_id());
						pk.setState_id(memberSel_tmp.getPerson().getState_id());
						
						estado = ((StateCat) stateService.getStateById(pk)).getName();
						flag   = true;
						
					} else {
						estado = "Extranjero";
						flag = true;
					}
					
				} else {
					estado = "Extranjero";
					flag   = true;
				}
				
				if (flag)
				{
					CURP = naturalPersonService.generaCURP(memberSel_tmp.getPerson(), estado.toUpperCase());
				}			
					
			}
			
			if( RFC.trim().length() > 0 || CURP.trim().length() > 0 ){
				
				NaturalPerson person =  naturalPersonService.getNaturalPersonById(memberSel_tmp.getPerson().getNatPerPK());
				
				if(RFC.trim().length() > 0){
					
					person.setMx_rfc( RFC );
					
				}
				if(CURP.trim().length() > 0){
					person.setMx_curp(CURP);
				}
				
				naturalPersonService.update(person);
				
			}
			
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			ConsultingErrorBean errorbean = new ConsultingErrorBean();
			
			errorbean.setMember( memberSel_tmp );
			errorbean.setError_descript( "Exception: "+ e.getMessage() );
			
			lstError.add( errorbean );
			
			return false;
			
		}
		
	}
	
	public void changeReason(){
		System.out.println("ChangeRazon");
	}
		
}