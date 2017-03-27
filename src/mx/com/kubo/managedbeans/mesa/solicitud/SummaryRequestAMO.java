package mx.com.kubo.managedbeans.mesa.solicitud;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.primefaces.json.JSONObject;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.bean.PersonOtherFamily;
import mx.com.kubo.bean.ProgressEFL;
import mx.com.kubo.controller.efl_connect.EflConnect;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.SMSRequestService;
import mx.com.kubo.managedbeans.CheckScoreProcessed;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.investor.InvestmentList;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMP;
import mx.com.kubo.managedbeans.mesa.solicitud.notas.NotasDelCasoIMP;
import mx.com.kubo.managedbeans.mesa.solicitud.perfil.FondeadorIMP;
import mx.com.kubo.mesa.solicitud.ReporteInusualIMP;
import mx.com.kubo.mesa.solicitud.documentacion.DocumentacionIMP;
import mx.com.kubo.mesa.solicitud.perfil.ActividadEconomicaIMP;
import mx.com.kubo.mesa.solicitud.perfil.EditorIdentificationIMP;
import mx.com.kubo.mesa.solicitud.promo.PromocionIMP;
import mx.com.kubo.mesa.solicitud.telefonos.TelefonosIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.model.EflScore;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesHistory;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ExpensesTypePK;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeDetailHistory;
import mx.com.kubo.model.IncomeDetailPK;
import mx.com.kubo.model.IncomeHistory;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.IncomeTypePK;
import mx.com.kubo.model.LegalLimit;
import mx.com.kubo.model.LegalLimitPK;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.OfferRejectionMotive;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_costPK;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.Operating_cost_typePK;
import mx.com.kubo.model.OperationCostHistory;
import mx.com.kubo.model.PhoneType;
import mx.com.kubo.model.PrecioUdi;
import mx.com.kubo.model.ProfileFormValue;
import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.ProfileInvPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.References;
import mx.com.kubo.model.RelatedPersonLoan;
import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.RoleAccess;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingDetailHistory;
import mx.com.kubo.model.SellingDetailPK;
import mx.com.kubo.model.SellingType;
import mx.com.kubo.model.SellingTypePK;
import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.model.Stackholder_relationshipPK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMO;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.DomicilioIMP;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMP;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;
import mx.com.kubo.tools.Utilities;

public abstract class SummaryRequestAMO extends SummaryRequestDMO
{	
	protected void init_variables()
	{
		validTransUnion = false;
		nameVisible     = false;
		displayNotes    = false;
		pais_origen_ENABLED = false;
		editor_domicilio_ENABLED    = false;
		domicilio_fiscal_ENABLED    = false;
		editor_nombre_ENABLED       = false;
		editor_tipo_credito_ENABLED = false;
		reporte_inusual_ENABLED     = false;
		flagPromo = false;
		copiar_documentos_ENABLED = false;
		
		changeBankData      = null;		
		changeConsolidate   = null;
		changeReasons       = null;
		changeStatusProyect = null;		
		negotiation         = null;
		investor            = null;		
		transErrorMsg       = null;		
		actualProyect       = null;
		editor_notas        = null;
		isYears             = null;
		imTutor             = null;
		thisTutor           = null;
		refered_search      = null;		
		lstApproval			= null;
		img_status_inv 		= "";
		str_status_inv 		= "";
		safi_account_id 	= "";
		offer				= null;
		hasOffer 			= 0;
		offer_why_not		= null;
		
		hasEflTest 			= false;
		efl_OK 				= false;
		efl_ERROR			= false;
		autorizar_personas_relacionadas_ENABLED = false;
		ren4c				= false;
		
		editor_domicilio 	= null;
		domicilio_actividad = null;
		
		promotionTitle = "";
	
		pagePromo = "../../../secciones/registro/pre_aprobacion/tmp1.xhtml" ;
		
		editor_domicilio = null;
		domicilio_actividad = null;
		editor_nombre = null;
		editor_identification = null;
	}
	
	protected void init_persona() 
	{					
		if( actualProyect != null)
		{
			persona = actualProyect.getPerson();
			
			estado_cuenta_ENABLED = sesion.getArea().equals('I') && actualProyect.getSafi_credit_id() != null;
			acreditado_sin_publicar_ENABLED = actualProyect == null && !session_sumary.isPerson();
			
		} else {
			
			String a = cadenaProyecto.split("::")[0];
			String b = cadenaProyecto.split("::")[1];
			
			key = new gnNaturalPersonPK(Integer.parseInt(a),Integer.parseInt(b)); 
			persona = service_natural_person.getNaturalPersonById(key);
			
		}
		
		if(persona != null && persona.getGender_id() != null && persona.getGender_id() == 2)
		{			
			female = true;
			
		} else {
			
			female = false;
		}
		
		if(persona.getEdad() != null )
		{
			is_menor = (persona.getEdad() < 18);
			
		}else{
			
			is_menor = false;
		}
		
		prospecto  = persona.getProspectus();		
		numCliente = persona.getNatPerPK().getProspectus_id();
	}
	
	protected void init_related_person(){
		
		if( member.getIs_stackholder() != null && member.getIs_stackholder().equals("S") ){
			
			if( member.getStackholder_relationship_id() != null ){
			
				Stackholder_relationshipPK stkPK = new Stackholder_relationshipPK();
				
				stkPK.setCompany_id(member.getMembershipPK().getCompany_id());
				stkPK.setStackholder_relationship_id(member.getStackholder_relationship_id());
				
				
				Stackholder_relationship stkHld = service_accionistas.getStackholderByPK(stkPK);
				
				stackholder_description = stkHld.getRelationship_type();
				
				if( stkHld.getRelated_to() != null ){
					
					stkPK.setCompany_id(member.getMembershipPK().getCompany_id());
					stkPK.setStackholder_relationship_id(stkHld.getRelated_to());
					
					stkHld = service_accionistas.getStackholderByPK(stkPK);
					
					stackholder_description += " de " + stkHld.NombreCompletoNPM() + " ( " + stkHld.getRelationship_type() + " )";
					
				}
				
				
			
			}
			
		}
		
		LegalLimitPK lpk = new LegalLimitPK();
		
		lpk.setCompany_id(member.getMembershipPK().getCompany_id());
		lpk.setLegal_limit_id(1);
		
		LegalLimit legallimit = legallimitservice.getLegalLimitByPK(lpk);
		
		PrecioUdi pu =capitalnetoservice.getMaxPrecioUdi();
		
		Double dlimiteUDIS = (Double.parseDouble(legallimit.getValue()) * pu.getMx_precio_udi() );
		
		limiteUDIS = dec.format( dlimiteUDIS );
		
		lpk = new LegalLimitPK();
		
		lpk.setCompany_id(member.getMembershipPK().getCompany_id());
		lpk.setLegal_limit_id(2);
		
		legallimit = legallimitservice.getLegalLimitByPK(lpk);
		
		CapitalNeto capitalNeto = capitalnetoservice.getMaxCapitalNeto();
		
		Double dlimiteCapitalNeto =  (capitalNeto.getMx_capital_neto()* (Double.parseDouble(legallimit.getValue()))) / 100 ;
		
		limiteCapitalNeto = dec.format( dlimiteCapitalNeto );
		
		System.out.println( "descripcion persona relacionada : " + stackholder_description );
		
		requireAutorizacionPersonaRelacionada = member.getIs_stackholder() != null && member.getIs_stackholder().equals("S")  ;
		
		superaPorcCapitalNeto = actualProyect.getAmmount() > dlimiteCapitalNeto;
		superaUDIS = actualProyect.getAmmount() > dlimiteUDIS;
		
		requireAutorizacionConsejoAdmin = superaPorcCapitalNeto || superaUDIS ;
		
		if( requireAutorizacionConsejoAdmin ){
			requireAutorizacionPersonaRelacionada = false;
		}
		
		initRelatedPerson();
		
	}
	
	protected void initDetalleTableroNormativo(){
		
		tableronormativodetallado = tableronormativoservice.getDetalleTableroNormativo( actualProyect.getProyectloanPk().getProyect_loan_id() );
		
	}
	
	protected void initRelatedPerson(){
		
		relatedProyect = relatedpersonloanservice.getRelatedPersonLoanByProyectLoanProspectus(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getProyect_loan_id());
		
		if( relatedProyect == null ){
			relatedProyect = new RelatedPersonLoan();
		}
		
	}
	
	protected void init_telefonos()
	{
		telefono = new TelefonosIMP();

		telefono.setService_change_control(service_change_control);
		telefono.setService_natural_person(service_natural_person);
		telefono.setService_proyect_loan  (service_proyect_loan);
		telefono.setService_telefono      (service_telefono);
		telefono.setService_references(referencesService);
		telefono.setListBusiness(listBusiness);
		telefono.setListEmployment(listEmployment);
		telefono.setSesion(sesion);
		telefono.setPerson(persona);
		telefono.init();
	}

	protected void init_phone_type() 
	{
		listTempPhoneType = service_telefono.loadAllPhoneType();
		
		listPhoneType = new ArrayList<PhoneType>();
		
		for (PhoneType phoneType : listTempPhoneType) 
		{
			int phone_type_id = phoneType.getPhoneTypePk().getPhone_type_id();
			
			if((phone_type_id == 1 || phone_type_id==2) && listEmployment != null && listEmployment.size() > 0)
			{
				listPhoneType.add(phoneType);
				
			} else if((phone_type_id==3 || phone_type_id==4) && listBusiness!=null && listBusiness.size()>0) {
				
				listPhoneType.add(phoneType);
				
			} else if(phone_type_id==5 || phone_type_id==6) {
				
				listPhoneType.add(phoneType);
			}
		}
	}
	
	protected void init_name_visible() 
	{
		if(sesion.getArea() == 'I')
		{
			nameVisible = (false);
			
		} else if(sesion.getArea() == 'L'){
			
			if(sesion.getProspectus_id() == prospecto.getProspectusPK().getProspectus_id())
			{
				nameVisible = (true);
				
			} else {
				
				nameVisible = (false);
			}
			
		} else if(sesion.getArea() =='M') {
			
			nameVisible = (true);
		}
	}
	
	protected void init_proyect_loan() 
	{
		if(cadenaProyecto.split("::").length > 2)
		{						
			int proyect_loan_id = Integer.parseInt(cadenaProyecto.split("::")[0]);
			int proyect_id      = Integer.parseInt(cadenaProyecto.split("::")[1]);
			int prospectus_id   = Integer.parseInt(cadenaProyecto.split("::")[2]);
			int company_id      = Integer.parseInt(cadenaProyecto.split("::")[3]);					
			
			proyect_loan_PK = new ProyectLoanPK(proyect_loan_id, proyect_id, prospectus_id, company_id);
			
			actualProyect = service_proyect_loan.findProyect(proyect_loan_PK);
			
			if(actualProyect != null)
			{								
				if(actualProyect.getTransunion() == null)
				{
					//System.out.println( " Sin calificacion de transunion " );
					validTransUnion = false;
					
				} else {
					
					if(
						(actualProyect.getTransunion().getMx_rechazofpd()!=null && actualProyect.getTransunion().getMx_rechazofpd().equals("1")) ||
						(actualProyect.getTransunion().getMx_rechazomop()!=null && actualProyect.getTransunion().getMx_rechazomop().equals("1")) ||
						(actualProyect.getTransunion().getMx_rechazomop24()!=null && actualProyect.getTransunion().getMx_rechazomop24().equals("1")) ||
						(actualProyect.getTransunion().getMx_rechazoobs()!=null && actualProyect.getTransunion().getMx_rechazoobs().equals("1")) 
					  )
					{
						validTransUnion = true;
						
						if(actualProyect.getTransunion().getMx_rechazomop()!=null && actualProyect.getTransunion().getMx_rechazomop().equals("1")){
							
							transErrorMsg = "MOP de créditos abiertos mayor a 3 y saldo vencido mayor a 100";
							
						}else if(actualProyect.getTransunion().getMx_rechazoobs()!=null && actualProyect.getTransunion().getMx_rechazoobs().equals("1")){
							
							transErrorMsg = "Con clave de observación negativa en créditos de menos de 12 meses";

						}else if (actualProyect.getTransunion().getMx_rechazomop24()!=null && actualProyect.getTransunion().getMx_rechazomop24().equals("1")){
						
							transErrorMsg = "MOP máximo en últimos 24 meses mayor a 5 en cuentas abiertas";

						}else if(actualProyect.getTransunion().getMx_rechazofpd()!=null && actualProyect.getTransunion().getMx_rechazofpd().equals("1")){
							
							transErrorMsg = "Rechazado por la variable RECHAZOFPD";
							
						} else {
							
							transErrorMsg = null;
							
						}						
					}					
				}				
			}
		}
	}
	
	protected void init_frecuencia_pagos() 
	{
		if(actualProyect != null)
		{			
			switch(actualProyect.getFrequency_id())
			{
				case 1:
					frequency = "Semanal";
					frequencyStr = "Semanas";
					frequencyStr02 = "semanales";
					break;
				case 2:
					frequency = "Catorcenal";
					frequencyStr = "Catorcenas";
					frequencyStr02 = "catorcenales";
					break;
				case 3:
					frequency = "Quincenal";
					frequencyStr = "Quincenas";
					frequencyStr02 = "quincenales";
					break;
				case 4:
					frequency = "Mensual";
					frequencyStr= "Meses";
					frequencyStr02 = "mensuales";
					break;
			}
		}
	}
	
	protected void init_imagen_prospecto() 
	{
		if(prospecto.getImage()!= null && prospecto.getImage().length() > 0	) 
		{
			imagenProspecto = "/documents/cia_"
							+ prospecto.getProspectusPK().getCompany_id()
							+ "/pros_"
							+ prospecto.getProspectusPK().getProspectus_id()
							+ "/photo/" + prospecto.getImage().split("\\.")[0]
							+ "_thump_150" + "." + prospecto.getImage().split("\\.")[1];
			
			if(!isDirectory(imagenProspecto))
			{
				if(female)
				{
				
					imagenProspecto = "/img/sinimagenM.jpg";
					
				} else {
					
					imagenProspecto = "/img/sinimagen.jpg";
					
				}
				
				imgWPr = "150";
				imgHPr = "150";
				
			} else {
				
				String destination = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
				String path        = destination + imagenProspecto;
				
				newFile = new File(path);
				
				if(newFile.exists())
				{
					Hashtable<String, Integer> ht = getDimImg(newFile);
					imgWPr = (Integer)ht.get("Width") +"";
					imgHPr = (Integer)ht.get("Height") +"";
				}
			}
		} else if(sesion.getUsrImg()!= null && sesion.getUsrImg().length() > 0) {
			
			imagenProspecto = "/documents/cia_"
							+ sesion.getCompany_id()
							+ "/pros_"
							+ sesion.getProspectus_id()
							+ "/photo/" + sesion.getUsrImg();
			
			if(!isDirectory(imageSesion))
			{
				if(female)
				{
					imagenProspecto = "/img/sinimagenM.jpg";
				} else {
					imagenProspecto = "/img/sinimagen.jpg";
				}
				
				imgWPr = "150";
				imgHPr = "150";			
			}
		} else {
			if(female)
			{
				imagenProspecto = "/img/sinimagenM.jpg";
			} else {
				imagenProspecto = "/img/sinimagen.jpg";
			}
			
			imgWPr = "150";
			imgHPr = "150";			
		}
	}
	
	protected void init_resumen_solicitud() 
	{
		if(actualProyect != null)
		{
			
			bCDesc = Utilities.getBCDesc(String.valueOf(actualProyect.getBc_score()));
			
			burSolNum           = actualProyect.getMx_solicitud_buro();
			ammount	            = num.format(actualProyect.getAmmount());
			
			amount_founded      = actualProyect.getAmount_founded();
			bc_score_range      = actualProyect.getBc_score_range();
			bottomPorcent       = actualProyect.getBottomPorcent();						
			days_online         = actualProyect.getDays_online();
			daysLeft            = actualProyect.getDaysLeft();
			enabledBottomDetail = "" + actualProyect.getEnabledBottomDetail();
			enabledFlag         = actualProyect.getEnabledFlag();
			frequency_id        = actualProyect.getFrequency_id();
			funding_type        = actualProyect.getFunding_type();
			investment_bite     = actualProyect.getInvestment_bite();
//			investors_number    = actualProyect.getInvestors_number();
//			investorsInt        = actualProyect.getInvestorsInt();
			kubo_score_a        = actualProyect.getKubo_score_a();
			kubo_score_b        = actualProyect.getKubo_score_b();
			kuboScoreLetter     = actualProyect.getkuboScoreLetter();
			kuboScoreNumber     = actualProyect.getkuboScoreNumber();
			min_ammount	        = actualProyect.getMin_ammount();
			payment             = num.format(actualProyect.getPayment());
			rate_investor       = actualProyect.getRate_investor(); 
			term_id             = actualProyect.getTerm_id();
			verification_score  = actualProyect.getVerification_score();
			verificationClass   = actualProyect.getVerificationClass();
			verificationRange   = actualProyect.getVerificationRange();
			months              = actualProyect.getTerm_id();
			nameProyect         = actualProyect.getProyect().getName();
			liquidezReq         = (actualProyect.getLiquidity() == null ? 2.5D : actualProyect.getLiquidity());
			
			scoreClass = "dvRisk" + kuboScoreLetter;			
			
			i1 = Calendar.getInstance();
			i1.setTime( new Date() );
			
			if( sesion.getArea() != null && !sesion.getArea().toString().trim().equals("M") ){
			
				initInvestorList();
				
			}
				
			i2 = Calendar.getInstance();
			i2.setTime( new Date() );
			
			li1 = i2.getTimeInMillis() - i1.getTimeInMillis();
			
			System.out.println( "Tardó en cargar la lista de inversionista: "+li1+" milisegundos" );
			
			flagSolicitud = false;
			flagInv = false;
			
			if(listInvestors != null && listInvestors.size() > 0 )
			{				
				//investors_number    = actualProyect.getInvestors_number();
				investorsInt = listInvestors.size();
				ammountLeft	 = num.format( listInvestors.get(0).getDisponibleFondeo() );
				daysLeft	 = Long.parseLong( listInvestors.get(0).getDiasPoTrans() + "" );
				flagInv = true;
				
			}
			
			if( actualProyect.getSafi_credit_id() == null && actualProyect.getMx_solicitud_buro() == null )
			{
				investorsInt = 0;
				daysLeft = 0L;
				ammountLeft = num.format(actualProyect.getAmmount());
				flagSolicitud = true;
			
			}else if( actualProyect.getMx_solicitud_buro() != null ){
											
				if(!flagInv)
				{
					daysLeft = actualProyect.getDaysLeft()<0?0L:actualProyect.getDaysLeft() ;
					ammountLeft = num.format(actualProyect.getAmmount());
					investorsInt = 0;
				}
			}
		}
	}
	
	private void initInvestorList()
	{
		listInvestors = null;
		
		if(actualProyect != null)
		{			
			if(actualProyect.getSafi_credit_id() != null)
			{
				listInvestors = (List<ViewInvestmetInProyect>)financiamientoService.getListInvestorbyProyectId( Integer.parseInt( actualProyect.getSafi_credit_id() ), null);
			
			} else if( actualProyect.getSafi_mx_solicitud_id() != null ) {
				
				listInvestors = (List<ViewInvestmetInProyect>)financiamientoService.getListInvestorbyProyectId( null ,  actualProyect.getSafi_mx_solicitud_id() );
			}
		}
	}
	
	protected void init_casos_pospuestos() 
	{
		if(actualProyect != null && actualProyect.getStatus_id() != null)
		{						
			estatus_ORIGINAL = EstatusProyectLoan.getInstance(actualProyect.getStatusProyect().getStatusPK().getStatus_id());
			
			c1 = Calendar.getInstance();
			c1.setTime(new Date());
			
			lista_cambio_estatus = service_estatus.getListaEstatus_by_EstatusConfig(actualProyect.getStatus_id());		
			
			c2 = Calendar.getInstance();
			c2.setTime(new Date());
			
			asignar_change_control();
			
			c3 = Calendar.getInstance();
			c3.setTime(new Date());
			
			System.out.println("Tardo "+(c2.getTimeInMillis() - c1.getTimeInMillis())+" en cargar asignar_lista_cambio_estatus");
			
			System.out.println("Tardo "+(c3.getTimeInMillis() - c3.getTimeInMillis())+" en cargar asignar_change_control(); ");
			
			
			today       = Calendar.getInstance();
			fecha_leida = Calendar.getInstance();
						
			c4 = Calendar.getInstance();
			
			c4.setTime( new Date() );
			
			if(actualProyect.getPosposed_date() != null)
			{				
				fecha_leida.setTime(actualProyect.getPosposed_date());
				
				setFechaPospuestaValida(fecha_leida.after(today));								
				
				casosPospuestos = new CasosPospuestosIMP(estatus_ORIGINAL, actualProyect.getPosposed_date());
				
			} else if(actualProyect.getPreapproved_date() != null) {	
				
				fecha_leida.setTime(actualProyect.getPreapproved_date());
				
				fechaPospuestaValida = fecha_leida.after(today);								
				
				casosPospuestos = new CasosPospuestosIMP(estatus_ORIGINAL, actualProyect.getPreapproved_date());
				
			} else {
				
				casosPospuestos = new CasosPospuestosIMP();
			}
			
			c5 = Calendar.getInstance();
			
			c5.setTime( new Date() );
			
			System.out.println( "tardó "+(c5.getTimeInMillis() - c4.getTimeInMillis())+" milisegundos en casos pospuestos " );
			
		} 	
	}
	
	protected void init_image_logo() 
	{
		if(actualProyect != null)
		{			
			try {
				
				loadImgProyect();
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				
			}
			
			
		} else {
			imageLogo1 = new ImagesBean();
			imageLogo1.setUrlImgThumb("/img/sinimagenM.jpg");
			imageLogo1.setUrlImg("/img/sinimagenM.jpg");
		}	
	}
	
	protected void init_direccion() 
	{
		c6 = Calendar.getInstance();
		c6.setTime( new Date() );
		
		direccion = service_address.getMaxAddressByType(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(), 1);
		
		if(direccion != null)
		{		
			if(direccion.getTown_id() != null)
			{
			
				ciudadId = new TownCatPK(direccion.getTown_id(),persona.getNatPerPK().getCompany_id());
				ciudad = ciudadService.getTownById(ciudadId);
				
				townType = ciudad.getType();
				townName = ciudad.getName();
			}
			
			if(direccion.getState_id() != null)
			{
				StateCatPK estadoId2 = new StateCatPK(direccion.getState_id(), persona.getNatPerPK().getCompany_id());
				estadoDireccion = estadoService.getStateById(estadoId2);
				
				stateAddress = estadoDireccion.getName();
			}
		
		} else {
			
			direccion =  new Address();
		}		
		
		c7 = Calendar.getInstance();
		c7.setTime( new Date() );
		
		System.out.println("SummaryRequest.mapeoDatos(): tardó "+(c7.getTimeInMillis() - c6.getTimeInMillis())+" milisegundos en casos pospuestos " );
		
	}
	
	protected void init_membership() 
	{
		c8 = Calendar.getInstance();
		c8.setTime( new Date() );
		
		mpk = new  MembershipPK();
		mpk.setCompany_id(persona.getNatPerPK().getCompany_id());
		mpk.setProspectus_id(persona.getNatPerPK().getProspectus_id());
		
		member = service_membership.getMembershipById(mpk);
		
		alias = member.getNickname();
		
		c9 = Calendar.getInstance();
		c9.setTime( new Date() );
		
		System.out.println("SummaryRequest.init_membership(): tardó "+(c9.getTimeInMillis() - c8.getTimeInMillis())+" milisegundos en cargar membership para el alias " );		
	}
	
	protected void init_reporte_inusual() 
	{
		reporte_inusual_ENABLED = persona.getSafi_client_id() != null ? true : false;
		
		if(reporte_inusual_ENABLED)
		{		
			inusual = new ReporteInusualIMP();
			inusual.setService_catalogos(service_catalogos);
			inusual.setService_PLD(prevencionldservice);
			inusual.setService_access(accessService);
			inusual.setSesion(sesion);
			inusual.setPerson(persona);
			inusual.init();
		}
	}
	
	protected void init_notas_del_caso() 
	{
		if( persona.getProspectus().getArea().toString().equals("L") ){
		
			if(actualProyect != null && displayNotes)
			{
				proyect_id = actualProyect.getProyectloanPk().getProyect_id();
				
				editor_notas = new NotasDelCasoIMP();
				editor_notas.setPersona(persona);
				editor_notas.setProyect_loan(actualProyect);
				editor_notas.setProyect_id(proyect_id);
				editor_notas.setCasosPospuestos(casosPospuestos);
				editor_notas.init();
				editor_notas.init_nota();
				editor_notas.init_lista_notas();
			}
		
		}else if( persona.getProspectus().getArea().toString().equals("I") && displayNotes ){
			
			editor_notas = new NotasDelCasoIMP();
			editor_notas.setPersona(persona);
//			editor_notas.setProyect_loan(actualProyect);
//			editor_notas.setProyect_id(proyect_id);
//			editor_notas.setCasosPospuestos(casosPospuestos);
			editor_notas.init();
			editor_notas.init_nota();
			editor_notas.init_lista_notas();
			
		}
		
		lstNoteType       = noteService.getListNoteTypes();
		lstNotePrioriType = noteService.getListPrioriType();
	}
	
	protected void init_documentacion()
	{
		if(nameVisible)
		{
			String real_path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
			documentacion = new DocumentacionIMP();
			documentacion.setSesion(sesion);
			documentacion.setReal_path(real_path);
			documentacion.setProyect_loan  (actualProyect);	
			documentacion.setPersona(persona);						
			documentacion.init();
		}
	}
	
	protected void init_fondeador() 
	{
		fondeador = new FondeadorIMP();
		
		if(!persona.getProspectus().getArea().equals('I') && actualProyect != null)
		{			
			fondeador.setService_fondeador     (service_fondeador);
			fondeador.setService_proyect_loan  (service_proyect_loan);		
			fondeador.setService_change_control(service_change_control);
			fondeador.setProyect_loan(actualProyect);
			fondeador.setPersona(persona);
			fondeador.setSesion(sesion);
			fondeador.init();		
		} 
	}
	
	protected void init_domicilio(boolean address_type_ENABLED) 
	{					
		editor_domicilio = new EditorViviendaIMP();
		editor_domicilio.setSesion(sesion);
		editor_domicilio.setPerson(persona);
		editor_domicilio.init();

		
		if(request != null)
		{
			request.addCallbackParam("codigo_postal", editor_domicilio.getCodigo_postal());
		}
		
		Address address = null;		
		
		if(address_type_ENABLED)
		{
			int prospectus_id = persona.getNatPerPK().getProspectus_id();
			int    company_id = persona.getNatPerPK().getCompany_id();
			      activity_id = persona.getEconomic_activity_id();
			      
			switch(activity_id)
			{
				case COMERCIANTE:
					address_type_id = NEGOCIO;
				break;
				
				case EMPLEO:
					address_type_id = EMPRESA;
				break;
			}
			
			address = service_address.getMaxAddressByType(prospectus_id, company_id, address_type_id);
			
			domicilio_actividad = new DomicilioIMP();
			domicilio_actividad.setSesion(sesion);
			domicilio_actividad.setAddress(address);	
			domicilio_actividad.setLista_employment(listEmployment);
			domicilio_actividad.setLista_business(listBusiness);
			domicilio_actividad.init();
			
		} else {
			
			domicilio_actividad = new DomicilioIMP();
			domicilio_actividad.setSesion(sesion);
			domicilio_actividad.setLista_employment(listEmployment);
			domicilio_actividad.setLista_business(listBusiness);
			domicilio_actividad.init();
		}			
	}
	
	protected void init_domicilio_fiscal() 
	{

		domicilio_fiscal = new EditorViviendaIMP();
		domicilio_fiscal.setSesion(sesion);
		domicilio_fiscal.setPerson(persona);
		domicilio_fiscal.setAddress_type(FISCAL);
		domicilio_fiscal.init();
		
		domicilio_fiscal_ENABLED = domicilio_fiscal.isFiscal_ENABLED();
	}
	
	protected void init_domicilio_pais_origen() 
	{
		prospectus_id = persona.getNatPerPK().getProspectus_id();
		company_id    = persona.getNatPerPK().getCompany_id();
		
		Address address = service_address.getMaxAddressByType(prospectus_id, company_id, 7);
		
		if(address != null)
		{
			AddressTokenIMO token  = new AddressTokenIMP();			
			token.setAddress(address);
			
			domicilio_pais_origen_TOKEN = token.getAddressTOKEN();
			
			pais_origen_ENABLED = true;
		}
		
	}
	
	protected void init_editor_identification() 
	{
		editor_identification = new EditorIdentificationIMP();
		editor_identification.setSesion(sesion);
		editor_identification.setPerson(persona);
		editor_identification.init();
	}
	
	protected void init_inversion_interna() 
	{
		if( sesion.getArea() != null && sesion.getArea().toString().equals("I")  )
		{			
			investmentList = ( InvestmentList ) resolver.getValue(context, null, "investmentList");
			
			saldoActualInv = investmentList.getSaldoActual();
			
			itemSel = investmentList.getProyectSeleccionado( actualProyect.getProyectloanPk().getProyect_loan_id(), actualProyect.getProyectloanPk().getProyect_id(), actualProyect.getProyectloanPk().getProspectus_id() , actualProyect.getProyectloanPk().getCompany_id() );
			
			if(itemSel != null)
			{			
				ammounttoInv = itemSel.getInvestment_bite();			
			}			
		}
	}
	
	protected void init_access(int screen_id) 
	{
		Access access = new Access();
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		access.setCompany_id          (sesion.getCompany_id());
		access.setProspectus_id       (sesion.getProspectus_id());
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setOp_system           (sesion.getOsbrawser());
		access.setHorizontal_size     (sesion.getBrowser_width());
		access.setVertical_size       (sesion.getBrowser_height());
		access.setUser_agent          (sesion.getUser_agent());
		access.setDevice_info         (sesion.getDevice_info());
		access.setIpaddress           (sesion.getIP_address_client());
		access.setProspectus_id_viewed(persona.getNatPerPK().getProspectus_id());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		access.setUrl_access		  (sesion.getUrl_access());
		
		accessService.add(access, true);
	}
	
	protected void asignar_vivienda_TOKEN()
	{
		if(direccion.getStreet() != null && direccion.getStreet().length() > 0)
		{
			addressStr += " " + direccion.getStreet();
		}
		
		if(direccion.getAddress_number() != null && direccion.getAddress_number().length() > 0)
		{
			addressStr += " número " + direccion.getAddress_number();
		}
		
		if(direccion.getApt_number()!=null && direccion.getApt_number().length() > 0)
		{
			addressStr += " número interior " + direccion.getApt_number();
		}
		
		if(direccion.getMx_manzana()!=null&&direccion.getMx_manzana().length()>0)
		{
			addressStr += " manzana " + direccion.getMx_manzana();
		}
		
		if(direccion.getMx_lote() != null && direccion.getMx_lote().length() > 0)
		{
			addressStr += " lote "+direccion.getMx_lote();
		}
		
		if(direccion.getNeighborhood()!=null)
		{
			addressStr += ", colonia "+direccion.getNeighborhood().getName();
		}
		
		if(direccion.getTownCat()!=null)
		{
			if(direccion.getStateCat()!=null&&direccion.getStateCat().getStateCatPK().getState_id()==9)
			{
				addressStr += ", delegación " + direccion.getTownCat().getName();
				
			} else {
				
				addressStr += ", municipio " + direccion.getTownCat().getName();
			}
		}
		
		if(direccion.getStateCat() != null)
		{
			addressStr += ", " + direccion.getStateCat().getName();
		}
		
		if(direccion.getZip_code()!=null && direccion.getZip_code().length() > 0)
		{
			addressStr += ", Código Postal " + direccion.getZip_code()+"";
		}
		
		addressStr += ", entre " + direccion.getFirst_street_reference() +  " y "  + direccion.getSecond_street_reference();
		
		if(direccion.getDescription()!=null)
		{					
			addressStr += " , "+direccion.getDescription();					
		}
		
		addressStr = Utilities.capilizeString(addressStr);
	}
	
	public void asignarFecha()
	{        
// Dias
		for (int i = 1; i <= 31; i++) 
		{
			if(i < 10)
			{
				days.add("0" + i);
			}else{
				days.add("" + i);
			}
		}
		// Meses
		for (int i = 0; i < monthStr.length; i++) 
		{
			meses.add("" + monthStr[i]);
		}

		// Años
		
		SimpleDateFormat frm;
		Calendar         fecha;
		Date     	     d1, d2;
		Integer          year1, year2;
		
		frm = new SimpleDateFormat("dd/MM/yyyy");
		
		fecha = Calendar.getInstance();
		d1    = fecha.getTime();
		year1 = Integer.parseInt(frm.format(d1).split("/")[2]);
		
		fecha = Calendar.getInstance();
		fecha.add(Calendar.DATE, 14);
		
		d2    = fecha.getTime();
		year2 = Integer.parseInt(frm.format(d2).split("/")[2]);
		
		for (int i = year2; i >= year1; i--) 
		{
			years.add("" + i);
		}
		
		for (int i = 1; i < 13; i++) 
		{
			if(i < 10)
			{     			
				hrs.add("0" + i);
			} else {
				hrs.add("" + i);
			} 
		}
		     		     		
		hh   = "09";
		ampm = "PM";
		mm   = "00";     
	}
	
	protected void inicializafechasPLD(){
		
		if( member.getFile_creation_date() != null)
		{
			file_creation_date = formatter_date_complete.format( member.getFile_creation_date() );
			
		}else{
			
			file_creation_date = "El cliente aún no finaliza su primer solicitud ";
			
		}
		
		Date datePublish = service_proyect_loan.getLastPublishProyectDate( actualProyect );
		
		if( datePublish != null ){
			
			last_update_date = formatter_date_complete.format( datePublish );
			
		}else{
			
			last_update_date = "El cliente no cuenta con actualizaciones de expediente ";
			
		}
		
		Date first_credit = service_proyect_loan.getDateFirstCreditFromSAFI( actualProyect );
		
		if( first_credit == null )
		{
			first_credit_date = "El cliente aún no cuenta con créditos desembolsados";
			
		}else{
			
			first_credit_date = formatter_date_complete.format( first_credit );
			
		}
		
		
	}
	
	protected void asignar_change_control() 
	{
		changeStatusProyect = new ChangeBean();
		changeStatusProyect.setOrigValue(actualProyect.getStatusProyect().getName());
		changeStatusProyect.setNameTable("ln_status");
		changeStatusProyect.setNameField("status_id");
		
		List<Change_control> listChangeStatusProyectTemp, lista_cambios;
		
		int prospectus_id       = actualProyect.getProyectloanPk().getProspectus_id();
		int company_id          = actualProyect.getProyectloanPk().getCompany_id();
		String[] afected_tables = new String[]{"ln_proyect_loan"};
		String[] afected_fields = new String[]{"status_id"};
					
		listChangeStatusProyectTemp = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id , afected_tables, afected_fields);

		boolean has_change  = listChangeStatusProyectTemp != null && listChangeStatusProyectTemp.size() > 0 ? true: false;
		lista_cambios        = listChangeStatusProyectTemp != null && listChangeStatusProyectTemp.size() > 0 ? listChangeStatusProyectTemp : null;
		
		changeStatusProyect.setHasChange(has_change);
		changeStatusProyect.setLstChanges(lista_cambios);
	}
	
	protected void calculaTotalIncome()
	{
		totalIncome = 0.0;
		IncomeBean income=null;
		listIncomeBean=new ArrayList<IncomeBean>();
		listIncome = new ArrayList<Income>() ; 
		
		totalIncomeControll=0.0;	
		
		incomeBussinness = null;
		
		if( actualProyect !=null )
		{
			if( !existlistIncomeHistory() )
			{				
				listIncome = ingresosService.getListIncomeByProspect(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());
				System.out.println( " --- !existlistIncomeHistory() : entro" );
			}else{
				System.out.println( " --- !existlistIncomeHistory() : else" );
			}
			
			//System.out.println("SummaryRequest.calculaTotalIncome: INIT   --  actualProyect:"+actualProyect.getProyectloanPk().getProspectus_id()+"  person:  "+persona.getNatPerPK().getProspectus_id()+" incomedetail: " +"   listIncome: "+listIncome.get(0).getIncomePk().getProspectus_id() );
			
				for(IncomeType itertype: listIncomeType)
				{
					boolean isEquals=false;
					
						for(Income registro3: listIncome)
						{
							if(registro3.getIncome_type_id().equals(itertype.getPk().getIncome_type_id()))
							{
								if(registro3.getIncome_type_id()==3 && getPersonFamily()!=null)
								{
									if(getPersonFamily().getRelationShip()!=null&&getPersonFamily().getRelationShip().getRelationship()!=null&&getPersonFamily().getRelationShip().getRelationship().length()>0)
									{
										income=new IncomeBean(registro3.getIncome_type_id(),registro3.getAmmount(),"Ingreso de "+getPersonFamily().getRelationShip().getRelationship().toLowerCase());
										
										if(registro3.getAmmount_modified()!=null)
										{
											String tmp = num.format(registro3.getAmmount_modified());
											
											if(tmp.equals("0"))
											{
												tmp = "0";
											}
											
											income.setAmmount_modified(tmp);
										}else{
											String tmp = num.format(registro3.getAmmount());
											if(tmp.equals("0"))
												tmp = "0";
											income.setAmmount_modified(tmp);
										}
										income.setIncome(registro3);
										listIncomeBean.add(income);
										//Suma o resta solo aquellos que sean ingresos de empleado.
										if(itertype.getObs_income_type().equals("IE")){
										
											this.totalIncomeControll +=  (itertype.getOperation_sign() * Double.parseDouble(income.getAmmount_modified().replace(",","").replace("$","")));
											this.totalIncome += (itertype.getOperation_sign() * registro3.getAmmount());
										}
										
										isEquals=true;
									}else{
										income=new IncomeBean(registro3.getIncome_type_id(),registro3.getAmmount(),itertype.getName());
										if(registro3.getAmmount_modified()!=null){
											String tmp = num.format(registro3.getAmmount_modified());
											if(tmp.equals("0"))
												tmp = "0";
											income.setAmmount_modified(tmp);
										}else{
											String tmp = num.format(registro3.getAmmount());
											if(tmp.equals("0"))
												tmp = "0";
											income.setAmmount_modified(tmp);
										}
										income.setIncome(registro3);
										listIncomeBean.add(income);
										//Suma o resta solo aquellos que sean ingresos de empleado.
										if(itertype.getObs_income_type().equals("IE")){
											this.totalIncomeControll +=  (itertype.getOperation_sign() * Double.parseDouble(income.getAmmount_modified().replace(",","").replace("$","")));
											this.totalIncome += (itertype.getOperation_sign() * registro3.getAmmount());
										}
										isEquals=true;
									}
								}
								else if(registro3.getIncome_type_id() != 7 ) {
									
									income=new IncomeBean(registro3.getIncome_type_id(),registro3.getAmmount(),itertype.getName());
									
									if(registro3.getAmmount_modified()!=null){
										String tmp = num.format(registro3.getAmmount_modified());
										if(tmp.equals("0"))
											tmp = "0";
										income.setAmmount_modified(tmp);
									}else{
										String tmp = num.format(registro3.getAmmount());
										if(tmp.equals("0"))
											tmp = "0";
										income.setAmmount_modified(tmp);
									}
									income.setIncome(registro3);
									listIncomeBean.add(income);
									if(itertype.getObs_income_type().equals("IE")){
										this.totalIncomeControll +=  (itertype.getOperation_sign() * Double.parseDouble(income.getAmmount_modified().replace(",","").replace("$","")));
										this.totalIncome += (itertype.getOperation_sign() * registro3.getAmmount());
									}
									isEquals=true;
								
								} else if(registro3.getIncome_type_id() == 7 ) {
									
									setIngresosComprobables(registro3.getAmmount_modified());
									
								}
								
								if( registro3.getIncome_type_id() == 2 ){
									incomeBussinness = registro3;
								}
								
							}
						}
					if(!isEquals){
						//Si es empleado pero no tiene ningun income(Esto se sabe por el isEquals) se crean en 0 para que mesa de control pueda modificar
						//Los income que pueda tener el empleado(el que es sueldo neto y todos aquellos que los calculan "Transacciones de suldo del empleado").
						if((listEmployment.size()!=0 && itertype.getPk().getIncome_type_id()==6) || (listEmployment.size()!=0 && itertype.getObs_income_type().equals("TSE")))
							{
								income=new IncomeBean(itertype.getPk().getIncome_type_id(),0D,itertype.getName());
								
								
								income.setAmmount_modified("0");
								Income ex = new Income();
								ex.setAmmount(0D);
								ex.setAmmount_modified(0D);
								ex.setIncome_type_id(itertype.getPk().getIncome_type_id());
								ex.setProspectus_id_modified(null);
								IncomePK expk = new IncomePK();
								expk.setCompany_id(persona.getNatPerPK().getCompany_id());
								expk.setIncome_id(0);
								expk.setProspectus_id(persona.getNatPerPK().getProspectus_id());
								ex.setIncomePk(expk);
								income.setIncome(ex);
								
								listIncomeBean.add(income);
							}
						else if(listBusiness.size()!=0 && itertype.getPk().getIncome_type_id()==2)
							{
								income=new IncomeBean(itertype.getPk().getIncome_type_id(),0D,itertype.getName());
								
								income.setAmmount_modified("0");
								Income ex = new Income();
								ex.setAmmount(0D);
								ex.setAmmount_modified(0D);
								ex.setIncome_type_id(itertype.getPk().getIncome_type_id());
								ex.setProspectus_id_modified(null);
								IncomePK expk = new IncomePK();
								expk.setCompany_id(persona.getNatPerPK().getCompany_id());
								expk.setIncome_id(0);
								expk.setProspectus_id(persona.getNatPerPK().getProspectus_id());
								ex.setIncomePk(expk);
								income.setIncome(ex);
								
								listIncomeBean.add(income);
							}
						//Linea anterior
						//else if(itertype.getPk().getIncome_type_id()!=1 && itertype.getPk().getIncome_type_id()!=2){
						//Ahora se valida para generar income en 0 que sea diferente de sueldo neto, diferente de 2 y de aquellos que tengan Obs_income_type = TSE
						//para que no se muestren 0 en la sabana en esos incomes_type.
						else if(itertype.getPk().getIncome_type_id()!=6 && itertype.getPk().getIncome_type_id()!=2 && !itertype.getObs_income_type().equals("TSE")){
						
								income=new IncomeBean(itertype.getPk().getIncome_type_id(),0D,itertype.getName());
								
								income.setAmmount_modified("0");
								Income ex = new Income();
								ex.setAmmount(0D);
								ex.setAmmount_modified(0D);
								ex.setIncome_type_id(itertype.getPk().getIncome_type_id());
								ex.setProspectus_id_modified(null);
								IncomePK expk = new IncomePK();
								expk.setCompany_id(persona.getNatPerPK().getCompany_id());
								expk.setIncome_id(0);
								expk.setProspectus_id(persona.getNatPerPK().getProspectus_id());
								ex.setIncomePk(expk);
								income.setIncome(ex);
								
								listIncomeBean.add(income);
							}
//						else if(itertype.getPk().getIncome_type_id() == 7 ) {
//							
//							setIngresosComprobables(registro3.getAmmount_modified());
//							
//						}
						
					}
				}
				
				excedenteControl = totalIncomeControll - totalExpensesControl;
				
				//Si cuenta con monto de consolidacion
				if(getAmmountConsolidate().getAmmount()!=null && getAmmountConsolidate().getAmmount()>0){
					ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
					liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenControl;
				}else{//Si no cuenta con mont de consolidacion
					ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
					liquidezCliControl = excedenteControl/pagoMenControl;
				}
				
				liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
				
				if(liquidezReq!=null&&liquidezReq<liquidezCliControl){
					dispOKControl=true;
					dispWarnControl=false;
				}else{
					dispOKControl=false;
					dispWarnControl=true;
				}
				
				liqIni = liquidezCliControl;
				
		}
	}
	
	protected void calculaSueldoNeto(){
		Double sueldoNeto = 0D;
		
		List<Income> listIncomeTemp = ingresosService.getListIncomeByProspect(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());
		
		if(listIncomeTemp != null){
		
			Income incomeUpdate = null;
			for(Income incomeTemp: listIncomeTemp){
				//se suman todos los montos que permiten calcular el sueldo neto
				if(incomeTemp.getIncomeType().getObs_income_type().equals("TSE")){
					
					if( incomeTemp.getAmmount_modified() != null ){
						sueldoNeto += (incomeTemp.getIncomeType().getOperation_sign() * incomeTemp.getAmmount_modified());
					}
					else{
						sueldoNeto += (incomeTemp.getIncomeType().getOperation_sign() * incomeTemp.getAmmount());
					}
				}
				//Si es sueldo neto guardamos el obj, para actualizarle la suma al terminar de recorrer la lista de income
				if(incomeTemp.getIncome_type_id() == 6){
					incomeUpdate = incomeTemp;
					
				}
			}
			
			if(incomeUpdate != null){
				incomeUpdate.setAmmount_modified(sueldoNeto);
				ingresosService.updateIncome(incomeUpdate);
				
			}
		}
	}
	
	protected void calculaTotalExpenses()
	{		
		
		boolean flagExpenses = false;
		boolean flagTotalExpenses = false;
		Double totalExpenseTemp = 0D;
		Double totalExpenseControlTemp = 0D;
		
		if(actualProyect != null)
		{		
			totalExpenses = 0.0;
			totalExpensesControl = 0.0;
			
			if(!existlistExpenses())
			{
				int prospectus_id = actualProyect.getProyectloanPk().getProspectus_id();
				int company_id    = actualProyect.getProyectloanPk().getCompany_id();
				
				listExpenses = egresosService.getListExpensesByProspect(prospectus_id, company_id) ;
				
			}
			
			SystemParamPK sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(5);//parametro que indica como se hace la suma de los ingresos;
			
			thisSystem = systemparamservice.loadSelectedSystemParam(sysPk);
			
			for(Expenses registro4: listExpenses)
			{
				if( registro4.getExpense_type_id() != 14 ){ //Gastos Totales
					
					if(registro4.getAmmount()!=null)
					{
						
							this.totalExpenses += registro4.getAmmount();
							flagExpenses = true;
					}
					
					
					
					if(thisSystem.getValue().equals("1"))
					{
						//valores de mesa de control en caso de ser null suma el valor del usuario por que el valor es nuevo
						if(registro4.getAmmount_modified()!=null)
						{
							
							this.totalExpensesControl += registro4.getAmmount_modified();
						} else {
							this.totalExpensesControl += registro4.getAmmount();
						}
						
					}else if(thisSystem.getValue().equals("2")){//toma el máximo de las 2 entradas en caso de ser null suma el valor del usuario por que el valor es nuevo
						if(registro4.getAmmount_modified()!=null||registro4.getAmmount()!=null)
						{
							if(registro4.getAmmount_modified()!=null && registro4.getAmmount()!=null)
							{
								if(registro4.getAmmount_modified()>registro4.getAmmount())
								{
									this.totalExpensesControl += registro4.getAmmount_modified();
								} else {
									this.totalExpensesControl += registro4.getAmmount();
								}
							}
							
							if(registro4.getAmmount_modified()!=null)
							{
								this.totalExpensesControl += registro4.getAmmount_modified();
							} else if(registro4.getAmmount()!=null) {
								this.totalExpensesControl += registro4.getAmmount();
							}
						}
						
					}
					
				}else{
					flagTotalExpenses = true;
					totalExpenseTemp = registro4.getAmmount();
					
					if( !flagExpenses ){
					
						if(registro4.getAmmount_modified()!=null)
						{
							totalExpenseControlTemp = registro4.getAmmount_modified();
						} else if(registro4.getAmmount()!=null) {
							totalExpenseControlTemp += registro4.getAmmount();
						}
					}
				}
					
			}
			
			if(!flagExpenses &&  flagTotalExpenses ){
				
				this.totalExpenses = totalExpenseTemp;
				this.totalExpensesControl = totalExpenseControlTemp;
				
//				System.out.println("--------- ");
//				System.out.println("----1----   "+this.totalExpenses + "  -  " + totalExpenseTemp);
//				System.out.println("--------- ");
//				System.out.println("--------- ");
				
				
			}else if(!flagTotalExpenses && flagExpenses  ) {
				
				ExpensesPK expensesPK  = new ExpensesPK();
				expensesPK.setCompany_id( actualProyect.getProyectloanPk().getCompany_id() );
				expensesPK.setExpense_id(14);
				expensesPK.setProspectus_id( actualProyect.getProyectloanPk().getProspectus_id() );
				
				Expenses totalExpensesObj = new Expenses(); 
				totalExpensesObj.setExpensesPk(expensesPK);
				totalExpensesObj.setAmmount(this.totalExpenses);
				totalExpensesObj.setAmmount_modified(this.totalExpensesControl);
				totalExpensesObj.setExpense_type_id(14);
				
				egresosService.addExpenses(totalExpensesObj, actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id() );
				
//				System.out.println("--------- ");
//				System.out.println("----2---- ");
//				System.out.println("--------- ");
//				System.out.println("--------- ");
				
			}else if( flagExpenses &&  flagTotalExpenses ){
				this.totalExpenses = totalExpenseTemp;
				
				if( this.totalExpensesControl == 0D ){
				
					this.totalExpensesControl = totalExpenseControlTemp;
				
				}
				
//				System.out.println("--------- ");
//				System.out.println("----3---- ");
//				System.out.println("--------- ");
//				System.out.println("--------- ");
			}else{
//				System.out.println("--------- ");
//				System.out.println("----4---- ");
//				System.out.println("--------- ");
//				System.out.println("--------- ");
				this.totalExpenses = totalExpenseTemp;
				this.totalExpensesControl = totalExpenseControlTemp;
			}
			
			pagoMen = actualProyect.getPayment();
			
			switch (actualProyect.getFrequency_id())
			{
				case 1://semanal
					pagoMen = pagoMen*4;
				break;
				
				case 2://catorcenal
					pagoMen = pagoMen*2;
				break;
				
				case 3: //quincenal
					pagoMen = pagoMen*2;
				break;
				
				case 4://mensual
				break;
			}
			
			excedenteControl = totalIncomeControll - totalExpensesControl;
			
			//Si cuenta con monto de consolidacion
			if(getAmmountConsolidate().getAmmount()!=null && getAmmountConsolidate().getAmmount()>0)
			{
				ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
				liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenControl;
			}else{//Si no cuenta con mont de consolidacion
				ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
				liquidezCliControl = excedenteControl/pagoMenControl;
			}
			
			liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
			
	
			if(liquidezReq!=null&&liquidezReq<liquidezCliControl){
				dispOKControl=true;
				dispWarnControl=false;
			}else{
				dispOKControl=false;
				dispWarnControl=true;
			}
		}
		
	}
	
	protected Membership getMemberInvNull(){
		
		Membership mem = new Membership();
		NaturalPerson nat = new NaturalPerson();
		Prospectus pro = new Prospectus();
		
		nat.setProspectus(pro);
		mem.setPerson(nat);
		
		mem.setNickname("No  disponible");
		
		return mem;
		
	}
	
	protected boolean existlistIncomeHistory(){
		
		if(actualProyect==null )
			return false;
		
		List<IncomeHistory> lstInHis = incomehistoryService.getIncomeHistoryByProyectLoan(actualProyect.getProyectloanPk().getProyect_loan_id());
		
		if( lstInHis != null && lstInHis.size()>0 ){
			
			listIncome = new ArrayList<Income>();
			
			for( IncomeHistory inH : lstInHis ){
				
				Income in = new Income();
				
				in.setAmmount(inH.getAmmount());
				in.setAmmount_modified(inH.getAmmount_modified());
				in.setDatetime_modified(inH.getDatetime_modified());
				in.setDescription(inH.getDescription());
				in.setIncome_type_id(inH.getIncome_type_id());
				
				IncomePK incomePk = new IncomePK();
				
				incomePk.setCompany_id(inH.getPk().getCompany_id());
				incomePk.setIncome_id(inH.getPk().getIncome_id());
				incomePk.setProspectus_id(inH.getPk().getProspectus_id());
				
				in.setIncomePk(incomePk);
				
				IncomeTypePK id = new IncomeTypePK();
				
				id.setCompany_id(inH.getPk().getCompany_id());
				id.setIncome_type_id(inH.getIncome_type_id());
				
				IncomeType type = service_income_type.getIncomeTypeById(id);
				
				in.setIncomeType( type );
				in.setProspectus_id_modified(inH.getProspectus_id_modified());
				listIncome.add(in);
				
			}
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	protected boolean existlistExpenses()
	{
		
		if(actualProyect==null )
		{
			return false;
		}
		
		int proyect_loan_id = actualProyect.getProyectloanPk().getProyect_loan_id();
		
		List<ExpensesHistory> lstExHis = expenseshistoryService.getExpensesHistoryByProyectLoan(proyect_loan_id);
	
		if( lstExHis != null && lstExHis.size() > 0)
		{
		
			listExpenses = new ArrayList<Expenses>();
		
			for( ExpensesHistory exH : lstExHis )
			{
			
				Expenses in = new Expenses();
				
				in.setAmmount(exH.getAmmount());
				in.setAmmount_modified(exH.getAmmount_modified());
				in.setDatetime_modified(exH.getDatetime_modified());
				in.setDescription(exH.getDescription());
				in.setExpense_type_id(exH.getExpense_type_id());			
				in.setAmmount_minus(exH.getAmmount_minus());
			
				ExpensesPK incomePk = new ExpensesPK();
				
				incomePk.setCompany_id(exH.getPk().getCompany_id());
				incomePk.setExpense_id(exH.getPk().getExpense_id());
				incomePk.setProspectus_id(exH.getPk().getProspectus_id());
			
				in.setExpensesPk(incomePk);
			
				ExpensesTypePK id = new ExpensesTypePK();
			
				id.setCompany_id(exH.getPk().getCompany_id());
				id.setExpenses_type_id(exH.getExpense_type_id());
			
				ExpensesType type = expensesTypeService.getExpensesTypeById(id);
			
				in.setExpensesType( type );
				in.setProspectus_id_modified(exH.getProspectus_id_modified());
				
				listExpenses.add(in);			
			}
		
			return true;
		
		} else {
		
		return false;
		
		}
	}	
	
	protected double  loadIncomeBussinessDetails()
	{
		listBusinessDetails=new ArrayList<IncomeDetailsBean>();
		IncomeDetailsBean incomeDetaBean=null;
		
		int index=0;
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Sus ventas son");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(false);
		incomeDetaBean.setAmmount_modified(incomedetail.getSales_type()==null?"---":incomedetail.getSales_type().equals("M")?"Mixto":incomedetail.getSales_type().equals("C")?"Contado":"Abonos");
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Sus compras son");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(false);
		incomeDetaBean.setAmmount_modified(incomedetail.getProvider_type()==null?"---":incomedetail.getProvider_type().equals("M")?"Mixto":incomedetail.getProvider_type().equals("C")?"Contado":"Abonos");
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setTypeComponent("S");
		incomeDetaBean.setName("Compras");	
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Veces que se surte al mes");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("I");
		
		incomeDetaBean.setAmmount( Double.parseDouble( (incomedetail.getTimes_refill()==null?0:incomedetail.getTimes_refill()) +"" ) );
		
		setTimes_refill_init( incomedetail.getTimes_refill_modified()==null?(incomedetail.getTimes_refill()==null?0:incomedetail.getTimes_refill()):incomedetail.getTimes_refill_modified());
		
		incomeDetaBean.setAmmount_modified	( getTimes_refill_init() +""  );
		
		//incomeDetaBean.setAmmount_modified(incomedetail.getTimes_refill()==null?"0 veces":incomedetail.getTimes_refill()>1?incomedetail.getTimes_refill()+" veces":incomedetail.getTimes_refill()+" vez");
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Total de compras mensuales");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("I");
		incomeDetaBean.setPutBoldStyle(true);
		incomeDetaBean.setAmmount			(incomedetail.getProvider_total()==null?0.0:incomedetail.getProvider_total());
		
		setProvider_total_init(incomedetail.getProvider_total_modified()==null?(incomedetail.getProvider_total()==null?0D:incomedetail.getProvider_total()):incomedetail.getProvider_total_modified());
		
		incomeDetaBean.setAmmount_modified	( num.format( getProvider_total_init() ) );
		
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		
		//----------------Detalle de ventas---------------------------------
		List<SellingDetail> lstSellingDetail = existlistSellingDetailHistory();
		
		if(lstSellingDetail == null){
			lstSellingDetail=incomeDetailService.getSellingDetailByDetailId(persona.getNatPerPK().getProspectus_id(),  persona.getNatPerPK().getCompany_id(),incomedetail.getIncomDetailPk().getIncome_detail_id());
		}
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setTypeComponent("S");
		incomeDetaBean.setName("Ventas");	
		listBusinessDetails.add(incomeDetaBean);
		index++;
		
		double totalCtrlTable=0.0;
		double profitBeforeCostCtrlTable=0;
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Sus ventas son");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setAmmount_modified(incomedetail.getSales_freq().equals("1")?"Mensual":incomedetail.getSales_freq().equals("2")?"Quincenal":incomedetail.getSales_freq().equals("4")?"Semanal":"Diario");
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(false);
		listBusinessDetails.add(incomeDetaBean);
		index++;
		if(lstSellingDetail!=null && lstSellingDetail.size()>0){
			//System.out.println("<<<<<<< lstSellingDetail.size(): "+lstSellingDetail.size()+" >>>>>>>>> ");
			for (SellingDetail iterSelling : lstSellingDetail) {
				incomeDetaBean=new IncomeDetailsBean();
				incomeDetaBean.setSave(true);
				incomeDetaBean.setIndex(index);
				incomeDetaBean.setName(iterSelling.getSellingType().getName());
				incomeDetaBean.setSellingOrOperCostDetail(iterSelling);
				incomeDetaBean.setAmmount(iterSelling.getAmmount());
				incomeDetaBean.setTypeComponent("I");
				incomeDetaBean.setPutBoldStyle(false);
				incomeDetaBean.setAmmount_modified(iterSelling.getAmmount_modified()!=null?num.format(iterSelling.getAmmount_modified()):num.format(iterSelling.getAmmount()));				
				totalCtrlTable+=iterSelling.getAmmount_modified()==null?iterSelling.getAmmount():iterSelling.getAmmount_modified();
				listBusinessDetails.add(incomeDetaBean);
				index++;
			}
			
		}else{
			SellingDetail sellingDetail=new SellingDetail();
			SellingDetailPK sellingPK=new SellingDetailPK(
					incomedetail.getIncomDetailPk().getIncome_id(), 
					incomedetail.getIncomDetailPk().getProspectus_id(), 
					incomedetail.getIncomDetailPk().getCompany_id(), 
					incomedetail.getIncomDetailPk().getIncome_detail_id(),1);		
			sellingDetail.setSellingDetailPK(sellingPK);
			sellingDetail.setAmmount(0.0);
			incomeDetaBean=new IncomeDetailsBean();
			incomeDetaBean.setSave(false);
			incomeDetaBean.setIndex(index);
			incomeDetaBean.setName("Ventas mensuales*");
			incomeDetaBean.setSellingOrOperCostDetail(sellingDetail);
			incomeDetaBean.setAmmount(0.0);
			incomeDetaBean.setTypeComponent("I");
			incomeDetaBean.setPutBoldStyle(false);
			incomeDetaBean.setAmmount_modified("0");
			listBusinessDetails.add(incomeDetaBean);
			index++;
			
		}
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Total de ventas mensuales");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		if(incomedetail.getSales_freq().equals("30")){
			incomeDetaBean.setAmmount(incomedetail.getSales_total()==null?0.0:incomedetail.getSales_total()*4);
			incomeDetaBean.setAmmount_modified(num.format(totalCtrlTable*4));
		}else{
			incomeDetaBean.setAmmount(incomedetail.getSales_total()==null?0.0:incomedetail.getSales_total());
			incomeDetaBean.setAmmount_modified(num.format(totalCtrlTable));
		}
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setShowProcess(true);
		incomeDetaBean.setPutBoldStyle(true);
		listBusinessDetails.add(incomeDetaBean);		
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Utilidad bruta mensual");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(true);
		incomeDetaBean.setAmmount(incomedetail.getProfit_before_costs()==null?0.0:incomedetail.getProfit_before_costs());
		
		Double d1 = (incomedetail.getProvider_total_modified() == null ) ? ( (incomedetail.getProvider_total()==null?0.0:incomedetail.getProvider_total()) ) : incomedetail.getProvider_total_modified() ;
		
		if(incomedetail.getSales_freq().equals("30") && totalCtrlTable>0){
			
			incomeDetaBean.setAmmount_modified(num.format((totalCtrlTable*4)-( d1 )));
			profitBeforeCostCtrlTable=(totalCtrlTable*4)-( d1 );
			
		}else{
			
			incomeDetaBean.setAmmount_modified(num.format((totalCtrlTable)-( d1 )));
			profitBeforeCostCtrlTable=(totalCtrlTable)-( d1 );
			
		}					
		listBusinessDetails.add(incomeDetaBean);
		index++;
		//------------------------------------------------------------------------
		
		//---------------------Detalle gastos operativos----------------------------------
		List<Operating_cost_type> lstOperCostType=incomeDetailService.getListOperation_costType(1);
		List<Operating_cost> lstOperCostDetail= existlistOperationCostHistory();
				if(lstOperCostDetail == null )
					lstOperCostDetail = incomeDetailService.getOperatingCostByDetailId(persona.getNatPerPK().getProspectus_id(),  persona.getNatPerPK().getCompany_id(),incomedetail.getIncomDetailPk().getIncome_detail_id());
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(1);
		incomeDetaBean.setTypeComponent("S");
		incomeDetaBean.setName("Gastos operativos");			
		listBusinessDetails.add(incomeDetaBean);
		
		totalCtrlTable=0.0;
		for (Operating_cost_type operating_costType : lstOperCostType) {
			incomeDetaBean=new IncomeDetailsBean();
			incomeDetaBean.setSave(false);
			for (Operating_cost operating_cost : lstOperCostDetail) {
				if(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id()==operating_cost.getOperatingCostPK().getOperating_cost_type_id()){
					incomeDetaBean.setSave(true);
					incomeDetaBean.setIndex(index);
					incomeDetaBean.setName(operating_cost.getOperaCostType().getName());
					incomeDetaBean.setSellingOrOperCostDetail(operating_cost);
					incomeDetaBean.setAmmount(operating_cost.getAmmount());
					incomeDetaBean.setTypeComponent("I");
					incomeDetaBean.setPutBoldStyle(false);
					incomeDetaBean.setAmmount_modified(operating_cost.getAmmount_modified()!=null?num.format(operating_cost.getAmmount_modified()):num.format(operating_cost.getAmmount()));					
					totalCtrlTable+=operating_cost.getAmmount_modified()==null?operating_cost.getAmmount():operating_cost.getAmmount_modified();
					listBusinessDetails.add(incomeDetaBean);					
					break;
				}
			}
			
			if(!incomeDetaBean.isSave()){
				Operating_cost operaCost=new Operating_cost();
				Operating_costPK operaCostPk=new Operating_costPK(
						incomedetail.getIncomDetailPk().getIncome_id(), 
						incomedetail.getIncomDetailPk().getProspectus_id(), 
						incomedetail.getIncomDetailPk().getCompany_id(), 
						incomedetail.getIncomDetailPk().getIncome_detail_id(),operating_costType.getOperatingCostTypePk().getOperating_cost_type_id());
				operaCost.setOperaCostType(operating_costType);
				operaCost.setOperatingCostPK(operaCostPk);
				incomeDetaBean.setIndex(index);
				incomeDetaBean.setName(operating_costType.getName());
				incomeDetaBean.setSellingOrOperCostDetail(operaCost);
				incomeDetaBean.setAmmount(0.0);
				incomeDetaBean.setTypeComponent("I");
				incomeDetaBean.setPutBoldStyle(false);
				incomeDetaBean.setAmmount_modified("0");
				listBusinessDetails.add(incomeDetaBean);
			}
			index++;
			
		}
		
		Double totalOperativeCostCtrlTable = totalCtrlTable;
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Total de gastos operativos mensuales");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setAmmount(incomedetail.getOperative_costs()==null?0.0:incomedetail.getOperative_costs());
		incomeDetaBean.setAmmount_modified(num.format( totalCtrlTable ));
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setShowProcess(true);
		incomeDetaBean.setPutBoldStyle(true);
		listBusinessDetails.add(incomeDetaBean);		
		index++;
		
		incomeDetaBean=new IncomeDetailsBean();
		incomeDetaBean.setIndex(index);
		incomeDetaBean.setName("Utilidad neta");
		incomeDetaBean.setSellingOrOperCostDetail(incomedetail);
		incomeDetaBean.setAmmount(incomedetail.getProfil_after_costs()==null?0.0:incomedetail.getProfil_after_costs());
		
		//totalCtrlTable = incomedetail.getOperative_costs()==null?0.0:incomedetail.getOperative_costs();
		
		incomeDetaBean.setAmmount_modified(num.format(profitBeforeCostCtrlTable-totalOperativeCostCtrlTable));		
		incomeDetaBean.setTypeComponent("L");
		incomeDetaBean.setPutBoldStyle(true);
		listBusinessDetails.add(incomeDetaBean);		
		
		
		
		if(incomedetail.getProfil_after_costs() != null && incomeBussinness != null &&  (incomedetail.getProfil_after_costs() != incomeBussinness.getAmmount() )){
			
//			System.out.println("SummaryRequest.if( if(incomedetail.getProfil_after_costs() ... ): INIT   --  "+actualProyect.getProyectloanPk().getProspectus_id()+"  person:  "+persona.getNatPerPK().getProspectus_id()+" incomedetail: "+incomedetail.getIncomDetailPk().getProspectus_id() +"   incomeBussinness: "+incomeBussinness.getIncomePk().getProspectus_id() );
//			
//			System.out.println( " getProfil_after_costs() --> " + incomedetail.getProfil_after_costs() );
//			
//			System.out.println( " incomeBussinness.getAmmount():  " + incomeBussinness.getAmmount() );
			
			Income incTmp = ingresosService.getIncomebyID(incomeBussinness.getIncomePk());
			
			incTmp.setAmmount(incomedetail.getProfil_after_costs());
			
			ingresosService.updateIncome( incTmp );
			
			for( IncomeBean tmp : listIncomeBean ){
				if( tmp.getIncome_type_id() == 2 ){
					tmp.setAmmount(incomedetail.getProfil_after_costs());
					
//					String tmp2 = num.format(incomedetail.getProfil_after_costs());
//					
//					if(tmp2.equals("0"))
//					{
//						tmp2 = "0";
//					}
//					
					
					
//					tmp.setAmmount_modified( tmp2+"" );
				}
			}
			
		}
		
		
		return profitBeforeCostCtrlTable-totalCtrlTable;
	}
	
	protected List<SellingDetail> existlistSellingDetailHistory()
	{
		
	if(actualProyect==null )
		return null;
	
	List<SellingDetail> lstSellingDetail = null;
	
	List<SellingDetailHistory> lstSellHis = sellingdetailhistoryService.getSellingDetailHistoryByProyectLoan( actualProyect.getProyectloanPk().getProyect_loan_id());
	
	if( lstSellHis != null &&  lstSellHis.size()>0 ){
		
		lstSellingDetail = new ArrayList<SellingDetail>();
		
		for( SellingDetailHistory inH : lstSellHis ){
			
			SellingDetail in = new SellingDetail();
			
			in.setAmmount(inH.getAmmount());
			in.setAmmount_modified(inH.getAmmount_modified());
			in.setDatetime_modified(inH.getDatetime_modified());
			
			SellingDetailPK sellHPk = new SellingDetailPK();
			
			sellHPk.setCompany_id(inH.getPk().getCompany_id());
			sellHPk.setIncome_id(inH.getPk().getIncome_id());
			sellHPk.setProspectus_id(inH.getPk().getProspectus_id());
			
			sellHPk.setIncome_detail_id(inH.getPk().getIncome_detail_id());
			sellHPk.setSelling_type_id(inH.getPk().getSelling_type_id());
			
			in.setSellingDetailPK(sellHPk);
			
			SellingTypePK id = new SellingTypePK();
			
			id.setCompany_id(inH.getPk().getCompany_id());
			id.setSelling_type_id(inH.getPk().getSelling_type_id());
			
			SellingType type =  sellingtypeservice .getSellingTypeByPK(id);
			
			in.setSellingType( type );
			in.setProspectus_id_modified(inH.getProspectus_id_modified());
			lstSellingDetail.add(in);
			
		}
		
		return lstSellingDetail;
		
	}else{
		
		return null;
		
	}
	}
	
	protected List<Operating_cost> existlistOperationCostHistory()
	{		
		if(actualProyect==null )
			return null;
		
		List<OperationCostHistory> lstInHis = opertaioncosthistoryService.getOperationCostHistoryByProyectLoan(  actualProyect.getProyectloanPk().getProyect_loan_id());
		
		List<Operating_cost> listOperationCost = null;
		
		if( lstInHis != null && lstInHis.size()>0 )
		{
			
			listOperationCost = new ArrayList<Operating_cost>();
			
			for( OperationCostHistory inH : lstInHis )
			{
				
				Operating_cost in = new Operating_cost();
				
				in.setAmmount(inH.getAmmount());
				in.setAmmount_modified(inH.getAmmount_modified());
				in.setDatetime_modified(inH.getDatetime_modified());
				
				Operating_costPK opCostHPk = new Operating_costPK();
				
				opCostHPk.setCompany_id(inH.getPk().getCompany_id());
				opCostHPk.setIncome_id(inH.getPk().getIncome_id());
				opCostHPk.setProspectus_id(inH.getPk().getProspectus_id());
				opCostHPk.setOperating_cost_type_id(inH.getPk().getOperating_cost_type_id());
				opCostHPk.setIncome_detail_id(inH.getPk().getIncome_detail_id());
				
				in.setOperatingCostPK(opCostHPk);
				
				Operating_cost_typePK id = new Operating_cost_typePK();
				
				id.setCompany_id(inH.getPk().getCompany_id());
				id.setOperating_cost_type_id(inH.getPk().getOperating_cost_type_id());
				
				Operating_cost_type type = opertaioncosttypeService.getOperatingCostTypeById(id);
				
				in.setOperaCostType( type );
				in.setProspectus_id_modified(inH.getProspectus_id_modified());
				listOperationCost.add(in);
				
			}
			
			return listOperationCost;
			
		}else{
			
			return null;
			
		}
	
	}
	
	protected void init_loan_negotiation() 
	{
		boolean isNegotiation;
		negotiation = null;
		
		Calendar c31 = Calendar.getInstance();
		c31.setTime(new Date());
		
		if(actualProyect != null)
		{
			negotiation = negotiationservice.loadMaxLoanNegotiation(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id(), actualProyect.getProyectloanPk().getProyect_loan_id(), actualProyect.getProyectloanPk().getProyect_id());
		}
		
		Calendar c32 = Calendar.getInstance();
		c32.setTime(new Date());
						
		//System.out.println("en cargar Negociación: "+dif3+" milisegundos");
		Calendar cOther1 = Calendar.getInstance();
		cOther1.setTime(new Date());
		
		if(negotiation == null && actualProyect != null)
		{			
			isNegotiation = false;
			
			negotiation = new LoanNegotiation();
			
			lnpk =  new LoanNegotiationPK();			
			lnpk.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
			lnpk.setProspectus_id(actualProyect.getProyectloanPk().getProspectus_id());
			lnpk.setProyect_id(actualProyect.getProyectloanPk().getProyect_id());
			lnpk.setProyect_loan_id(actualProyect.getProyectloanPk().getProyect_loan_id());
			
			negotiation.setPk(lnpk);
			
			negotiation.setAmmount(actualProyect.getAmmount());
			negotiation.setDays_online(actualProyect.getDays_online());
			negotiation.setFrequency_id(actualProyect.getFrequency_id());
			negotiation.setFunding_type(actualProyect.getFunding_type());
			negotiation.setKubo_score_a(actualProyect.getKubo_score_a());
			negotiation.setKubo_score_b(actualProyect.getKubo_score_b());
			negotiation.setMin_ammount(actualProyect.getMin_ammount());
			negotiation.setMx_cat(actualProyect.getMx_cat());
			negotiation.setOpening_commission_amount(actualProyect.getOpening_commission_amount());
			negotiation.setOpening_payment(actualProyect.getOpening_payment());
			negotiation.setPayment(actualProyect.getPayment());
			negotiation.setProspectus_id_proposed(sesion.getProspectus_id());
			negotiation.setRate(actualProyect.getRate());
			negotiation.setRate_with_opening(actualProyect.getRate_with_opening());
			
			
			negotiation.setStatus(null);
			negotiation.setTerm_id(actualProyect.getTerm_id());
			hasNegotiation =false;
			dispBotCondiciones = false;
			
		} else {
			
			isNegotiation = true;
			hasNegotiation = true;
			dispBotCondiciones = true;
			
		}
		
		if(negotiation!=null)
		{		
			if(isNegotiation )
			{
				if(negotiation.getStatus() == null|| !negotiation.getStatus().equals("A"))
				{
					dispSendNegotiation = true;
				}
				
			} else {
				
				dispSendNegotiation = false;
			}
			
			pagoIni = negotiation.getPayment();
			
			pagoMenControl  = negotiation.getPayment();
			
			switch (negotiation.getFrequency_id())
			{
					case 1://semanal
						pagoMenControl = pagoMenControl*4;
						break;
					case 2://catorcenal
						pagoMenControl = pagoMenControl*2;
						break;
					case 3: //quincenal
						pagoMenControl = pagoMenControl*2;
						break;
					case 4://mensual
						break;
				}
			
			montoNegotiation = num.format(negotiation.getAmmount());
			
			pagoMenIni = pagoMenControl;
			montoIni = negotiation.getAmmount();
			termInt = negotiation.getTerm_id();
			freqIni = negotiation.getFrequency_id();		
		}
	}
		
/*	
	protected void init_change_control_IFE() 
	{
		changeDataIFE = new ChangeBean();
		
		lstChange_ife = null;
		
		if(actualProyect != null)
		{
			int prospectus_id = actualProyect.getProyectloanPk().getProspectus_id();
			int company_id    = actualProyect.getProyectloanPk().getCompany_id();
			
			String[] tables = new String[]{"gn_natural_person"};
			String[] fields = new String[]{"mx_ife_cveelector","mx_ife_numemision","mx_ife_seccion","mx_ife_numvertical"};
			
			lstChange_ife = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id ,tables, fields);
			
		} else {
			
			int prospectus_id = persona.getNatPerPK().getProspectus_id();
			int company_id   = persona.getNatPerPK().getCompany_id();
			
			String[] tables = new String[]{"gn_natural_person"};
			String[] fields = new String[]{"mx_ife_cveelector","mx_ife_numemision","mx_ife_seccion","mx_ife_numvertical"};
			
			service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
		}
		
		changeDataIFE.setHasChange( lstChange_ife != null && lstChange_ife.size() > 0 ? true : false);
		changeDataIFE.setLstChanges(lstChange_ife != null && lstChange_ife.size() > 0 ? lstChange_ife : null);
		
		ifeChangeTem = new ChangeBean();
		lstChangeIFE = new ArrayList<ChangeBean>();
		
		String  mx_ife_cveelector  = persona.getMx_ife_cveelector();
		String  mx_ife_seccion     = persona.getMx_ife_seccion();
		String  mx_ife_numvertical = persona.getMx_ife_numvertical();
		Integer mx_ife_numemision  = persona.getMx_ife_numemision();
		
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_cveelector");
		ifeChangeTem.setOrigValue(mx_ife_cveelector != null && mx_ife_cveelector != "" ? mx_ife_cveelector : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_cveelector != null && mx_ife_cveelector != "" ? mx_ife_cveelector : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);
		
		ifeChangeTem = new ChangeBean();
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_numemision");
		ifeChangeTem.setOrigValue(mx_ife_numemision != null && mx_ife_numemision.toString() != "" ? mx_ife_numemision.toString() : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_numemision != null && mx_ife_numemision.toString() != "" ? mx_ife_numemision.toString() : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);
		
		ifeChangeTem = new ChangeBean();
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_seccion");
		ifeChangeTem.setOrigValue(mx_ife_seccion != null && mx_ife_seccion != "" ? mx_ife_seccion : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_seccion != null && mx_ife_seccion != "" ? mx_ife_seccion : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);		
		
		ifeChangeTem = new ChangeBean();
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_numvertical");
		ifeChangeTem.setOrigValue(mx_ife_numvertical != null && mx_ife_numvertical != "" ? mx_ife_numvertical : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_numvertical != null && mx_ife_numvertical != "" ? mx_ife_numvertical : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);	
	}
*/	
	
	protected void init_change_control_reasons() 
	{
		changeReasons=new ChangeBean();
		changeReasons.setOrigValue(member.getRegistration_reason_id()+"");
		changeReasons.setNameTable("ln_membership");
		changeReasons.setNameField("registration_resaon_id");
		
		listChangeReason = service_change_control.getListByProspectByAfectedTablesFields(persona.getNatPerPK().getProspectus_id(),persona.getNatPerPK().getCompany_id(),new String[]{"ln_membership"},new String[]{"promotor_id","registration_reason_id","priceshoes_number"});
		
		changeReasons.setHasChange(listChangeReason!=null && listChangeReason.size()>0?true:false);
		changeReasons.setLstChanges(listChangeReason!=null && listChangeReason.size()>0?listChangeReason:null);		
	}
	
	protected void init_membership_TEMP() 
	{
		membershipTemp = new Membership();
		
		membershipTemp.setAccept_subscribe(member.getAccept_subscribe());
		membershipTemp.setActivation_code (member.getActivation_code());
		membershipTemp.setActivation_date (member.getActivation_date());
		membershipTemp.setAnswer          (member.getAnswer());
		membershipTemp.setContract        (member.getContract());
		membershipTemp.setCreation_date   (member.getCreation_date());
		membershipTemp.setEmail           (member.getEmail());
		membershipTemp.setFailed_login_attempts(member.getFailed_login_attempts());
		membershipTemp.setFromwhere_id(member.getFromwhere_id());
		membershipTemp.setFromwhere_other(member.getFromwhere_other());
		membershipTemp.setFromWhereCat(member.getFromWhereCat());
		membershipTemp.setIs_blocked(member.getIs_blocked());
		membershipTemp.setIs_active(member.getIs_active());
		membershipTemp.setLast_login_attempt(member.getLast_login_attempt());
		membershipTemp.setMembershipPK(member.getMembershipPK());
		membershipTemp.setNickname(member.getNickname());
		membershipTemp.setOther_registration_reason(member.getOther_registration_reason());
		membershipTemp.setPassword(member.getPassword());
		membershipTemp.setPerson(member.getPerson());
		membershipTemp.setPriceshoes_number(member.getPriceshoes_number());
		membershipTemp.setPromotor(member.getPromotor());
		membershipTemp.setPromotor_id(member.getPromotor_id());
		membershipTemp.setRegistration_reason(member.getRegistration_reason());
		membershipTemp.setRegistration_reason_id(member.getRegistration_reason_id());
		membershipTemp.setSecurity_question_id(member.getSecurity_question_id());
		membershipTemp.setSecurityQuestion(member.getSecurityQuestion());
		membershipTemp.setWho_answered_id(member.getWho_answered_id());
		membershipTemp.setWho_answered_other(member.getWho_answered_other());
		membershipTemp.setWho_recommends(member.getWho_recommends());
		membershipTemp.setWhoAnswerCat(member.getWhoAnswerCat());
	}
	
	protected void init_relationship() 
	{
		relationShip = relationShipService.getRelationShipByProspectOriginal(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
		
		if(relationShip != null && sesion.getArea().equals('M'))
		{
			checkInOtherFamily = ingresosService.getIncomeByTypeIncomeID(persona.getNatPerPK().getProspectus_id(),  persona.getNatPerPK().getCompany_id(), 3);
			
			if(checkInOtherFamily != null)
			{
				if(checkInOtherFamily.getAmmount() > 0)
				{
					loadInfoPersonOtherFamily(relationShip);
					
				} else {
					
					personFamily=null;
				}
				
			} else {
				
				personFamily=null;
			}
			
		} else {
			
			personFamily=null;
		}
	}
	
	private void loadInfoPersonOtherFamily(RelationShip relationShip)
	{
		SimpleDateFormat formateador =  new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","ES"));
		personFamily=new PersonOtherFamily(relationShip);
		personFamily.setPerson( service_natural_person.getNaturalPersonById(new gnNaturalPersonPK(relationShip.getRelationShipPk().getProspectus_destiny_id(), relationShip.getRelationShipPk().getCompany_id())));
		personFamily.setMembership(service_membership.getMembershipById(new MembershipPK(relationShip.getRelationShipPk().getProspectus_destiny_id(),relationShip.getRelationShipPk().getCompany_id())));
		String fullNames = (personFamily.getPerson().getFirst_name()==null?"":personFamily.getPerson().getFirst_name())+ 
				 (personFamily.getPerson().getMiddle_name()==null?"":(" "+personFamily.getPerson().getMiddle_name()))+
				 (personFamily.getPerson().getFather_last_name()==null?"":(" "+personFamily.getPerson().getFather_last_name())) +
				 (personFamily.getPerson().getMother_last_name()==null?"":(" "+personFamily.getPerson().getMother_last_name()));
		personFamily.setFullName(fullNames);
		personFamily.setBirthday(personFamily.getPerson().getDate_of_birth()!=null?formateador.format(personFamily.getPerson().getDate_of_birth()):"");
		
		if(personFamily.getPerson().getCitizenship()!=null)
		{
			if(personFamily.getPerson().getCitizenship()==1)
			{
				personFamily.setNationality("Mexicana");
				
			} else {
				
				personFamily.setNationality("Extranjero");
			}
			
		} else {
			
			personFamily.setNationality("");
		}
		
		if(personFamily.getPerson().getCountry_id()!=null)
		{
			Country pais = paisService.getCountryById(new CountryPK(personFamily.getPerson().getCountry_id(), relationShip.getRelationShipPk().getCompany_id()));
			
			personFamily.setCountryName(pais!=null?pais.getName():"");
			
		} else {
		
			personFamily.setCountryName("");
		}
		
		if(personFamily.getPerson().getState_id()!=null)
		{
			StateCat estado = estadoService.getStateById(new StateCatPK(personFamily.getPerson().getState_id(), relationShip.getRelationShipPk().getCompany_id()));
			personFamily.setStateName(estado!=null?estado.getName():"");
			
		} else {
			
			personFamily.setStateName("");		
		}
		
		if(relationShip.getProspectus().getSame_address()!=null)
		{
			if(relationShip.getProspectus().getSame_address().equals("S"))
			{
				personFamily.setThisAddress("Vive en el mismo domicilio.");
				
			} else {
				
				Address addres = service_address.getMaxAddressByType(relationShip.getRelationShipPk().getProspectus_destiny_id(), relationShip.getRelationShipPk().getCompany_id(), 1) ;
				
				if(addres!=null)
				{
					String buildAddress="En una vivienda";
					
					if(addres.getStreet()!=null&&addres.getStreet().length()>0)
						buildAddress += " "+addres.getStreet();
					if(addres.getAddress_number()!=null&&addres.getAddress_number().length()>0)
						buildAddress += " número "+addres.getAddress_number();
					if(addres.getApt_number()!=null&&addres.getApt_number().length()>0)
						buildAddress += " número interior "+addres.getApt_number();
					if(addres.getMx_manzana()!=null&&addres.getMx_manzana().length()>0)
						buildAddress += " manzana "+addres.getMx_manzana();
					if(addres.getMx_lote()!=null&&addres.getMx_lote().length()>0)
						buildAddress += " lote "+addres.getMx_lote();
					if(addres.getNeighborhood()!=null)
						buildAddress += ", colonia "+addres.getNeighborhood().getName();
					if(addres.getTownCat()!=null){
						if(addres.getStateCat()!=null&&addres.getStateCat().getStateCatPK().getState_id()==9)
							buildAddress += ", delegación "+addres.getTownCat().getName();
						else
							buildAddress += ", municipio "+addres.getTownCat().getName();
					}
					if(addres.getStateCat()!=null)
						buildAddress += ", "+addres.getStateCat().getName();
					if(addres.getZip_code()!=null&&addres.getZip_code().length()>0)
						buildAddress += ", Código Postal "+addres.getZip_code()+".";
					
					personFamily.setThisAddress(buildAddress);
				}else
					personFamily.setThisAddress("Ninguna dirección por el momento");
			}				
		}else
			personFamily.setThisAddress("Ninguna dirección por el momento");
		
		List<Employment> listEmploy= employmentService.getListEmployByProspect(relationShip.getRelationShipPk().getProspectus_destiny_id(), relationShip.getRelationShipPk().getCompany_id());		
		List<Business> listBus= businessService.getListBusinessByProspect(relationShip.getRelationShipPk().getProspectus_destiny_id(), relationShip.getRelationShipPk().getCompany_id());
		
		personFamily.setListEmployment(listEmploy!=null && listEmploy.size()!=0 ? listEmploy : null);
		personFamily.setListBusiness(listBus!=null && listBus.size()!=0 ? listBus:null);
		
		List<Income> listIncomeProspect = ingresosService.getListIncomeByProspect(relationShip.getRelationShipPk().getProspectus_destiny_id(), relationShip.getRelationShipPk().getCompany_id());
		for(Income iterIncome: listIncomeProspect){
			 switch (iterIncome.getIncome_type_id()) {
			case 1:
				personFamily.setIncomeWagesSalary(iterIncome.getAmmount());
				break;
			case 2:
				personFamily.setIncomeBusinessCompany(iterIncome.getAmmount());			
				break;
			default:
				break;
			}
		}
		
		Income checkInOtherFamily=ingresosService.getIncomeByTypeIncomeID(persona.getNatPerPK().getProspectus_id(),  persona.getNatPerPK().getCompany_id(), 3);
		
		if(personFamily.getListEmployment()!=null && personFamily.getListBusiness()!=null)
		{
			personFamily.setIncomeOtherFamilyE(checkInOtherFamily.getAmmount()/2);
			personFamily.setIncomeOtherFamilyB(checkInOtherFamily.getAmmount()/2);
			
		} else if(personFamily.getListEmployment()!=null)
		{
			personFamily.setIncomeOtherFamilyE(checkInOtherFamily.getAmmount());
		}
		else{
			personFamily.setIncomeOtherFamilyB(checkInOtherFamily.getAmmount());
		}
		
	}
	
	protected void init_referencias() 
	{		
		listReference = referencesService.loadReferencesListByProspect(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
			
		ArrayList<References> array = new ArrayList<References>();
		
		for(References r : listReference)
		{
			if(r.getEmail()==null || r.getEmail().indexOf("null")!=-1||r.getEmail().trim().equals(""))
			{
				r.setEmail("No proporcionó su e-mail");
			}
			
			if(r.getPhone()==null || r.getPhone().indexOf("null")!=-1|| r.getPhone().trim().equals(""))
			{
				r.setPhone("No proporcionó su teléfono");
				array.add(r);
				
				continue;
			}else{
				r.setPhone( "044/045" + r.getPhone() );
			}
			
			if(r.getRelationship()==null || r.getPhone().indexOf("null")!=-1|| r.getPhone().trim().equals(""))
			{
				r.setRelationship("No proporcionó su parentesco");
			}
			
		}
		
		for(References x : array)
		{
			listReference.remove(x);
		}
		
		if(array.size() > 0)
		{			
			if(array.size() > 1)
			{			
				noshowRefStr="(*)Además proporcionó "+array.size()+" referencias sin número telefónico.";
				
			} else {
			
				noshowRefStr="(*)Además proporcionó "+array.size()+" referencia sin número telefónico.";				
			}	
			
		} else {
			
			noshowRefStr="";			
		}
		
		numberReferences = listReference.size();
		
		incomedetail = null;
		
		if( !existlistIncomeDetailHistory() )
		{		
			incomedetail = incomeDetailService.loadMaxIncomeByProspectus(persona.getNatPerPK().getProspectus_id(),persona.getNatPerPK().getCompany_id());
			
			//System.out.println("SummaryRequest.if(!existlistIncomeDetailHistory()) entro: INIT   --  "+actualProyect.getProyectloanPk().getProspectus_id()+"  person:  "+persona.getNatPerPK().getProspectus_id()+" incomedetail: "+incomedetail.getIncomDetailPk().getProspectus_id() );
			
		}else{
			
			//System.out.println("SummaryRequest.if(!existlistIncomeDetailHistory()) else: INIT   --  "+actualProyect.getProyectloanPk().getProspectus_id()+"  person:  "+persona.getNatPerPK().getProspectus_id()+" incomedetail: "+incomedetail.getIncomDetailPk().getProspectus_id() );
		}
		
		if(incomedetail != null)
		{		
			hasIncomeDeatil = true;		
			loadIncomeBussinessDetails();
			
		} else {
			
			hasIncomeDeatil = false;			
		}			
	}
	
	protected void init_CLABE_account() 
	{								
		claveaccountlst = clabeaccountservice.loadClabeAccountListByProspectus(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
		
		changeBankData = new ChangeBean();
		lstTemChangeClabe = service_change_control.getListByProspectByAfectedTablesFields(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(),new String[]{"gn_clabe_account","ln_proyect_loan"}, new String[]{"mx_clabe","signature_date","deposit_method_id"});
		
		changeBankData.setHasChange (lstTemChangeClabe!=null && lstTemChangeClabe.size()>0?true:false);
		changeBankData.setLstChanges(lstTemChangeClabe!=null && lstTemChangeClabe.size()>0?lstTemChangeClabe:null);
		
		if(claveaccountlst!=null && claveaccountlst.size()>0)
		{
			claveaccount = claveaccountlst.get(0);			
			changeBankData.setOrigValue(claveaccount.getMx_clabe() != null ? claveaccount.getMx_clabe() : UNDEFINED);
			changeBankData.setBank_description(claveaccount.getBank_description());
			
		} else {
			
			claveaccount = new ClabeAccount();
			claveaccount.setBank_description(UNDEFINED);
			claveaccount.setMx_clabe(UNDEFINED);
			changeBankData.setOrigValue(claveaccount.getMx_clabe());
			changeBankData.setBank_description(claveaccount.getBank_description());
		}
	}
	
	private boolean existlistIncomeDetailHistory(){
		
		if(actualProyect==null )
			return false;
	
		List<IncomeDetailHistory> lstInHis = incomedetailhistoryService.getIncomeDetailHistoryByProyectLoan(actualProyect.getProyectloanPk().getProyect_loan_id());
		
		if( lstInHis != null && lstInHis.size()>0 ){
			
			for( IncomeDetailHistory inH : lstInHis ){
				
				incomedetail = new IncomeDetail();
				
				IncomeDetailPK incomDetailPk = new IncomeDetailPK();
				
				incomDetailPk.setCompany_id(inH.getPk().getCompany_id());
				incomDetailPk.setIncome_detail_id(inH.getPk().getIncome_detail_id());
				incomDetailPk.setIncome_id(inH.getPk().getIncome_id());
				incomDetailPk.setProspectus_id(inH.getPk().getProspectus_id());
				
				incomedetail.setIncomDetailPk(incomDetailPk);
				
				incomedetail.setOperative_costs(inH.getOperative_costs());
				incomedetail.setProfil_after_costs(inH.getProfil_after_costs());
				incomedetail.setProfit_before_costs(inH.getProfit_before_costs());
				incomedetail.setProvider_amount(inH.getProvider_amount());
				incomedetail.setProvider_total(inH.getProvider_total());
				incomedetail.setProvider_type(inH.getProvider_type());
				incomedetail.setSales_freq(inH.getSales_freq());
				incomedetail.setSales_total(inH.getSales_total());
				incomedetail.setSales_type(inH.getSales_type());
				incomedetail.setTimes_refill(inH.getTimes_refill());
				
				
				//listIncome.add(incomedetail);
				
			}
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	protected void setPermissions(int role_id)
	{
		faces    = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		role_function = (RoleFunctionController) resolver.getValue(context, null, "roleFunctionController");
		
		lista_funciones = role_function.getFunctionByRole(role_id);
		
		displayLogCob = false;
		
		for(RoleFunction rf : lista_funciones)
		{
			int function_id = rf.getPk().getFunction_id();
			
			if(function_id == 2)
			{ //permiso para Fondear
				
				changeCreditConditionsFunction = true;
				continue;
			}
			
			if(function_id == FUNCTION_EDICION_CURP_TEL_DOCS)
			{  				
				changeActions = true;				
			}
			
			if( function_id == 5 ){ // Renovar Creditos
				
				renovacionAct = true;
				
			}
			
			if(function_id == 6 ){ // asignar cartera
				
				asignaCartera = true;
				
			}
			
			if(function_id == 7){ // crear créditos
				
				additionalCredit = true;
				
			}
			
			if(function_id == 8){ // Editar Documentos
				
				editDocument = true;
				
			}
			
			if(function_id == 9){ // adjuntar más documentos
				
				addDocument = true;
				
			}
			
			if(function_id == FUNCTION_CAMBIAR_ESTATUS)
			{			
				changeStatus = true;				
			}
			
			if(function_id == 11){ // adjuntar más documentos
				
				changeDispersion = true;
				
			}
			
			if(function_id == 12){//Modificar telefono
				changeTelephone = true;
			}
			
			if(function_id == 13){//Realizar consultas manuales
				makeNewConsultation = true;
			}
			
			if(function_id == 14){//Ver Calificación Transunion
				displayTransUnion = true;
			}
			
			if(function_id == 15){//Cambiar Institutional Ivestor
				changeInstitutionalInvestor = true;
			}
			if(function_id == 16){ //Agregar notas al caso
				displayNotes = true;
			}
			if(function_id == 17){ //Cancelar Cuentas
				cancel_prospectus = true;
			}
			if(function_id == 18){ //Agregar Alarmas
				displayAlerts = true;
			}
			if(function_id == 20){ //Asignar Tutor
				displayAddTutor = true;
			}						
			
			if(function_id == FUNCTION_EDICION_VIVIENDA)
			{ 
				editor_domicilio_ENABLED = true;
			}
			
			if( function_id == 22 ) //Asignar Recomendados
			{
				displayReferredChange = true;
			}
			
			if( function_id == 25 ) //Editar Formulario de Cliente ( Soporte Digital )
			{
				edit_Form_help_Coach = true;
			}
			
			if(function_id == FUNCTION_EDICION_NOMBRE)
			{
				editor_nombre_ENABLED = true;
			}
			
			if(function_id == FUNCTION_EDICION_TIPO_CREDITO)
			{
				editor_tipo_credito_ENABLED = true;
			}
			
			if(function_id == AUTORIZAR_CONTRATOS)
			{
				autorizar_contratos_ENABLED = true;
			}
			
			if(function_id == COPIAR_DOCUMENTOS)
			{
				copiar_documentos_ENABLED = true;
			}
			
			if(function_id == AUTORIZAR_PERSONAS_RELACIONADAS)
			{
				autorizar_personas_relacionadas_ENABLED = true;
			}
			
			if(function_id == VER_PESTANA_TABLERO_NORMATIVO)
			{
				ver_pestana_tablero_normativo_ENABLED = true;
			}
			if(function_id == VER_NOTAS_COMPORTAMIENTO_INUSUAL)
			{
				ver_notas_comportamiento_inusual_ENABLED = true;
			}
			if(function_id == REN_4_C)
			{
				
				ren4c = true;
				
			}
			
		}
		
		List<AccessCollector> menuAccess = accessService.loadControlMenu(role_id, sesion.getCompany_id());
		
		if( menuAccess != null  ){
		
			for( AccessCollector a : menuAccess ){
				
				if( a.getScreen_id() == 16 ){
				
					displayLogCob = true;
					
				}
				
			}
			
		}
		
		
	}
	
	public boolean inAccessbyRole(List<RoleAccess> lstroleAccess, AccessCollector a)
	{		
		boolean flag = false;
		
		if( lstroleAccess != null){
			for(RoleAccess ra : lstroleAccess )
			{			
				if(ra.getScreen().getScreenPK().getScreen_id() == a.getScreen_id() )
				{
					flag = true;
					break;
				}			
			}
		}
		return flag;
	}
	
	protected void firstParameter()
	{
		try
		{
			latlongMap = "";
						
			//alias = afiliacion.getNickname();
			nicknameSesion = sesion.getNombre();
			imageSesion    = sesion.getUsrImg();
						
			if(actualProyect != null && getReport(actualProyect.getProyectloanPk().getProspectus_id()))
			{
				displayReport = true;
				
			} else {
				
				displayReport = false;
			}

//			numCredit = actualProyect.getProyectloanPk().getProyect_loan_id();
			
			fm = new SimpleDateFormat("dd/MM/yyyy");
									
			if(persona.getDate_of_birth()!=null)
			{
				isYearss = fm.format(persona.getDate_of_birth());						
				isYears  = Utilities.calcularEdad(isYearss);
			}
			
			genderAux = persona.getGender_id();
			
			if(genderAux==null)
			{
				gender = "";
				
			} else if(genderAux==1) {
				
				gender = "Masculino";
				
			} else {
					
				gender = "Femenino";
			}
				
			nationalityAux = persona.getCitizenship();
			
			if(nationalityAux==null)
			{
				nationality = "";
				
			} else if (nationalityAux == 1) {
				
				nationality="Mexicana";
				
			} else {
					
				nationality="Extranjero";
			};
			
			if(pais == null)
			{
				country = "";
				
			} else {
				
				country = pais.getName();
			};
			
			if(estado != null)
			{
				state = estado.getName();
				
			} else {
				
				state = "";
			}
			
			if(estudios != null)
			{
				studyLevel = estudios.getName();
				
			} else {
				
				studyLevel = "No proporcionado";
			}
			
		
			if(estadoLegal!=null)
			{
				statusLegal = estadoLegal.getDescription();
				
			}else{
				
				statusLegal = "No proporcionado";
			}
			
			if(estadoMarital!=null)
			{
				maritalStatus = " por " + estadoMarital.getDescription();
			}
			
			dependantsNumberAux = persona.getDependants_number();
			
			if (dependantsNumberAux == null || dependantsNumberAux == 0)
			{
				dependantsNumber = "";
				
			}else if(dependantsNumberAux == 1) {
				
				dependantsNumber = ", con " + dependantsNumberAux + " dependiente económico";
				
			} else {
				
				dependantsNumber = ", con " + dependantsNumberAux + " dependientes económicos";
			};
						
			editor_actividad_economica = new ActividadEconomicaIMP();						
			editor_actividad_economica.setSesion(sesion);
			editor_actividad_economica.setPerson(persona);
			editor_actividad_economica.setNameVisible(nameVisible);
			editor_actividad_economica.setLista_business  (listBusiness);
			editor_actividad_economica.setLista_employment(listEmployment);			
			editor_actividad_economica.init();
			
			check = new PromocionIMP();
			check.setService_catalogos(service_catalogos);
			check.setProyect_loan(actualProyect);
			check.init();
			 
			 if(persona.getLength_of_residence()==null)
			 {
				 lengthResidence = "";
				 
			 } else if (persona.getLength_of_residence() == 1) {
				 
				 lengthResidence = persona.getLength_of_residence() + " año";
					 
			 } else {
				
				 lengthResidence = persona.getLength_of_residence() + " años";
			 }
			
			if(persona.getResidence_id() != null)
			{
				residentType = residencia.getDescription().toLowerCase();
				
			} else {
				
				residentType = "";
			}
		
			ammountConsolidate = new ExpensesBean();
			ammountConsolidate.setAmmount(0.0);
			ammountConsolidate.setExcedentConsolidate(0.0);
			
			if(changeConsolidate != null && changeConsolidate.isHasChange())
			{
				lastChange = changeConsolidate.getLstChanges().get(changeConsolidate.getLstChanges().size() - 1);
				
				ammountConsolidate.setWhyChangeData(lastChange.getComments());
			}
			
			calculaTotalIncome();			
			calculaTotalExpenses();		
			
			liqIni    = liquidezCliControl;	
			excedente = totalIncome - totalExpenses;
			
			liquidezCli = excedente / pagoMen;
			
			liquidezCli = (double) Math.round((liquidezCli) * 100) / 100;

			if(liquidezReq != null && liquidezReq < liquidezCli)
			{
				dispOKCl   = true;
				dispWarnCl = false;
				
			} else {
				
				dispOKCl   = false;
				dispWarnCl = true;
			}		
			
			addressStr = "";
			
			if(nameVisible)
			{
				asignar_vivienda_TOKEN();
				
				latlongMap += "" + direccion.getLatitude() + "," + direccion.getLongitude() + ",Casa::";
			}
			
			init_expenses();			
			
			if(ammountConsolidate != null && getAmmountConsolidate().getAmmount() > 0)
			{
				liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenControl;				
				liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
			}
			
			if(persona.getHas_computer_home()==null)
			{
				computerHome="";
				
			} else if (persona.getHas_computer_home()==1) {
				
				computerHome ="<li><p>Tiene computadora en casa</p></li>";
			}
			
			if(persona.getHas_computer_home()==null)
			{
				computerHome = "";
				
			} else if (persona.getHas_computer_home() == 1) {
				
				computerHome = "<li><p>Tiene computadora en casa</p></li>";
			}
				
			if(persona.getHas_internet_home()==null)
			{
				internetHome="";
				
			} else if (persona.getHas_internet_home()==1) {
				
				internetHome = "<li><p>Tiene internet en casa</p></li>";
			}
				
			if(persona.getHas_printer()==null){
				printer="";
				}else if(persona.getHas_printer()==1){
					printer = "<li><p>Tiene impresora</p></li>";
				}
			
			if(persona.getHas_computer_employment()==null){
				computerEmployment="";
				}else if(persona.getHas_computer_employment()==1){
					computerEmployment = "<li><p>Tiene computadora en el trabajo</p></li>";
				}
						
			if(persona.getHas_internet_business()==null){
					internetBusiness="";				
				}else if (persona.getHas_internet_business()==1){
					internetBusiness = "<li><p>Tiene internet en el negocio</p></li>";
				}
			
			if(persona.getHas_internet_employment()==null){
					internetEmployment="";
				}else if (persona.getHas_internet_employment()==1){
					internetEmployment = "<li><p>Tiene internet en el trabajo</p></li>";
				}
			
			if(persona.getHas_computer_business()==null){
				computerBusiness="";
				}else if (persona.getHas_computer_business()==1){
					computerBusiness = "<li><p>Tiene computadora en el negocio</p></li>";
				}
			
			
			aboutMe = persona.getAbout_me();
			
			
			
			if(listPublicForum!=null){
				for(PublicForum registro6: listPublicForum){
					registro6.getAnswer();
					registro6.getProyectQuestion().getQuestion();
					registro6.getPublicForumPK().getPublic_forum_id();
					if(registro6.getAnswer()==null){
						registro6.setAnswer("Por favor, escriba su respuesta aquí");
					}
				}
	
				numberQuest = listPublicForum.size();
			}
			
			if( listUnrealizedQuestions!=null ){
				for(ProyectQuestion registro7: listUnrealizedQuestions){
					registro7.getQuestion();
				}
			}
			
			if(sesion.getProspectus_id()!=null){
				enableDisable="true";
				createButtonQuestion=
						"<div id=\"newQuestion\"><a  title=\"Nueva pregunta\" class=\"blueBtn\" id=\"preguntas\" href=\"templates/questions.xhtml\"><strong>Envía una nueva pregunta</strong></a></div>";
			}
			else{
				createButtonQuestion=null;
			}
			
			if(actualProyect!=null && sesion.getProspectus_id()!=null && sesion.getProspectus_id()==actualProyect.getProyectloanPk().getProspectus_id()){
				enableDisable="false";
				createButtonQuestion=null;
				
			}
			else{
				enableDisable="true";
			}
						
			if(listInvestors!= null)
			{				
				anchorages = listInvestors.size();
			
				if (listInvestors.size()==0)
				{
					fondeadorestxt="";
					
				} else {
					fondeadorestxt = "<p id=\"profileFundersTxt\">Todos estos inversionistas han fondeado a "+alias+":</p>";
				}
				
			} else {
				
				anchorages = 0;
				
				fondeadorestxt = "<p id=\"profileFundersTxt\">Este proyecto aún no cuenta con fondeos</p>";
				
			}

			loadProyectList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
	}

	private void init_expenses() 
	{
		
		boolean flagExpenses = false;
		
		boolean flagTotalExpenses = false;
		Double totalExpenseTemp = 0D;
		
		totalExpenses = 0.0;
		expensesBean = null;
		listExpensesBean = new ArrayList<ExpensesBean>();		
		
		if(listExpensesType != null && listExpenses != null)
		{
			for(ExpensesType iterType : listExpensesType)
			{
				boolean isEquals = false;
				
				
				
				for(Expenses registro4: listExpenses)
				{
					
					if( registro4.getExpense_type_id() != 14 ){
					
						if(sesion.getArea()!='I')
						{
							if(registro4.getExpense_type_id().equals(iterType.getPk().getExpenses_type_id()))
							{
								expensesBean=new ExpensesBean(registro4.getExpense_type_id(), registro4.getAmmount(), iterType.getName());
								expensesBean.setExpense(registro4);
								
								if(registro4.getAmmount_modified() != null)
								{
									String tmp = num.format(registro4.getAmmount_modified());
									
									if(tmp.equals("0"))
									{
										tmp = "0";
									}
									
									expensesBean.setAmmount_modified(tmp);		
									
								} else {
									
									String tmp = num.format(registro4.getAmmount());
									
									if(tmp.equals("0"))
									{
										tmp = "0";
									}
									
									expensesBean.setAmmount_modified(tmp);
								}		
								
								//Se anexo esta condicion para determinar si tiene monto en credito(7) ammount_modified para lo de consolidar deudas.
								if(registro4.getExpense_type_id()==7  )
								{
									ammountConsolidate.setExpense(registro4);
									
										if((registro4.getAmmount()!=null && registro4.getAmmount()>0) || (registro4.getAmmount_modified()!=null && registro4.getAmmount_modified()>0))
										{										
											ammountConsolidate.setExpense_type_id(registro4.getExpense_type_id());										
											ammountConsolidate.setAmmount(registro4.getAmmount_minus()!=null?registro4.getAmmount_minus():0.0);
											ammountConsolidate.setAmmount_modified(registro4.getAmmount_minus()!=null?num.format(registro4.getAmmount_minus()):"0.0");
											if(registro4.getAmmount_minus()!=null && registro4.getAmmount_minus()>0)
												consolidate=1;
												else
													consolidate=2;
										}else{
											ammountConsolidate.setAmmount(0.0);
											consolidate=null;
										}
								}
								//---------------
								listExpensesBean.add(expensesBean);
								this.totalExpenses += registro4.getAmmount();
								isEquals=true;
							}
						}else{
							if(registro4.getExpense_type_id().equals(iterType.getPk().getExpenses_type_id()))
							{
								expensesBean=new ExpensesBean(registro4.getExpense_type_id(), 0D, iterType.getName());
								expensesBean.setExpense(registro4);
								
								if(registro4.getAmmount_modified()!=null)
								{
									expensesBean.setAmmount(registro4.getAmmount_modified());
									this.totalExpenses += registro4.getAmmount_modified();
								}else{
									expensesBean.setAmmount(registro4.getAmmount());
									this.totalExpenses += registro4.getAmmount();
								}
								listExpensesBean.add(expensesBean);
								
								isEquals=true;
							}
						}
					
						flagExpenses = true;
						
					}else{
						flagTotalExpenses = true;
						totalExpenseTemp = registro4.getAmmount();
					}
					
				}
			
				if(!isEquals)
				{
					expensesBean=new ExpensesBean(iterType.getPk().getExpenses_type_id(),0D, iterType.getName());
					expensesBean.setAmmount_modified("0");
					Expenses ex = new Expenses();
					ex.setAmmount(0D);
					ex.setAmmount_modified(0D);
					ex.setExpense_type_id(iterType.getPk().getExpenses_type_id());
					ex.setProspectus_id_modified(null);
					ExpensesPK expk = new ExpensesPK();
					expk.setCompany_id(persona.getNatPerPK().getCompany_id());
					expk.setExpense_id(0);
					expk.setProspectus_id(persona.getNatPerPK().getProspectus_id());
					ex.setExpensesPk(expk);
					expensesBean.setExpense(ex);
					listExpensesBean.add(expensesBean);
				}

			}// fin del For
			
			if(!flagExpenses &&  flagTotalExpenses ){
				
				this.totalExpenses = totalExpenseTemp;
				
//				System.out.println("--------- ");
//				System.out.println("----1.3----   "+this.totalExpenses + "  -  " + totalExpenseTemp);
//				System.out.println("--------- ");
//				System.out.println("--------- ");
				
				
			}else if(!flagTotalExpenses && flagExpenses  ) {
				
				ExpensesPK expensesPK  = new ExpensesPK();
				expensesPK.setCompany_id( actualProyect.getProyectloanPk().getCompany_id() );
				expensesPK.setExpense_id(14);
				expensesPK.setProspectus_id( actualProyect.getProyectloanPk().getProspectus_id() );
				
				Expenses totalExpensesObj = new Expenses(); 
				totalExpensesObj.setExpensesPk(expensesPK);
				totalExpensesObj.setAmmount(this.totalExpenses);
				totalExpensesObj.setExpense_type_id(14);
				
				egresosService.addExpenses(totalExpensesObj, actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id() );
				
//				System.out.println("--------- ");
//				System.out.println("----2.3---- ");
//				System.out.println("--------- ");
//				System.out.println("--------- ");
				
			}else if( flagExpenses &&  flagTotalExpenses ){
				this.totalExpenses = totalExpenseTemp;
//				System.out.println("--------- ");
//				System.out.println("----3.3---- ");
//				System.out.println("--------- ");
//				System.out.println("--------- ");
			}else{
//				System.out.println("--------- ");
//				System.out.println("----4.3---- ");
//				System.out.println("--------- ");
//				System.out.println("--------- ");
				this.totalExpenses = totalExpenseTemp;
			}
			
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			
		}
	}
	
	protected void cargaPerfilInversion()
	{		
		profileformvaluelist = profileformvalueservice.getProfileFormValueListByProspectus( persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id() );
		profile =  profileinvservice.getProfileInvByProspectus(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
		
		inPreg1 = inPreg2= inPreg3= inPreg4= inPreg5= "<ul style='padding-left: 10px; color: #333 !important;'>";
		
		if(profile == null){
			
			profile = new ProfileInv();
			ProfileInvPK ppk = new ProfileInvPK();
			
			ppk.setCompany_id(persona.getNatPerPK().getCompany_id());
			ppk.setProspectus_id(persona.getNatPerPK().getProspectus_id());
			
			profile.setPk(ppk);
			
		}
		
		if( profileformvaluelist != null )
		{
		
			for( ProfileFormValue val : profileformvaluelist ){
				
				switch(val.getPk().getProfile_form_id()){
					
						/*Preg 1.1*/
					case 1:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En cuentas de ahorro o chequera en bancos o instituciones financieras por menos de 1 año</li>";
						break;
					case 2:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En cuentas de ahorro o chequera en bancos o instituciones financieras de 1 a 3 años</li>";
						break;
					case 3:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En cuentas de ahorro o chequera en bancos o instituciones financieras por más de 5 años</li>";
						break;
					
						/*Preg 1.2*/	
					case 4:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Sociedades de Inversión de Renta Fija  por Menos de 1 año</li>";
						break;
					case 5:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Sociedades de Inversión de Renta Fija de 1 a 3 años</li>";
						break;
					case 6:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Sociedades de Inversión de Renta Fija por más de 5 años</li>";
						break;
						
						/*Preg 1.3*/
					case 7:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Sociedades de Inversión de Renta Variable  por menos de 1 año</li>";
						break;
					case 8:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Sociedades de Inversión de Renta Variable financieras de 1 a 3 años</li>";
						break;
					case 9:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Sociedades de Inversión de Renta Variable por más de 5 años</li>";
						break;
					
						/*Preg 1.4*/
					case 10:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Instrumentos de deuda  por menos de 1 año</li>";
						break;
					case 11:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Instrumentos de deuda financieras de 1 a 3 años</li>";
						break;
					case 12:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Instrumentos de deuda por más de 5 años</li>";
						break;
					
						/*Preg 1.5*/
					case 13:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Acciones  por menos de 1 año</li>";
						break;
					case 14:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Acciones financieras de 1 a 3 años</li>";
						break;
					case 15:
						if(val.getValue().equals("1"))
							inPreg1+="<li>En Acciones por más de 5 años</li>";
						break;
						
						/*Preg 1.6*/
					case 16:
						if(val.getValue().equals("1"))
							inPreg1="<ul style='padding-left: 10px; color: #333 !important;'><li>Nunca ha invertido</li>";
						break;
						
						/*Preg 2*/
					case 17:
						if(val.getValue().equals("1"))
							inPreg2+="<li>En más de 2 años</li>";
						break;
					case 18:
						if(val.getValue().equals("1"))
							inPreg2+="<li>Entre 1 y 2 años</li>";
						break;
					case 19:
						if(val.getValue().equals("1"))
							inPreg2+="<li>En menos de 1 año</li>";
						break;
						
						/*Preg 3*/
					case 20:
						if(val.getValue().equals("1"))
							inPreg3+="<li>Mantener el valor de la inversión con el mínimo riesgo</li>";
						break;
					case 21:
						if(val.getValue().equals("1"))
							inPreg3+="<li>Hacer crecer su inversión a través del tiempo con un riesgo moderado</li>";
						break;
					case 22:
						if(val.getValue().equals("1"))
							inPreg3+="<li>Hacer crecer su inversión considerablemente a largo plazo, tomando mayores riesgos en su portafolio</li>";
						break;
					case 23:
						if(val.getValue().equals("1"))
							inPreg3+="<li>Fomentar proyectos sociales, el rendimiento de su inversión es secundario</li>";
						break;
						
						
						/*Preg 4*/
					case 24:
						if(val.getValue().equals("1"))
							inPreg4+="<li>Inversión que en el mejor de los casos le da una ganancia de 25% y en el peor de los casos, una pérdida de 10% </li>";
						break;
					case 25:
						if(val.getValue().equals("1"))
							inPreg4+="<li>Inversión que en el mejor de los casos le da una ganancia de 15% y en el peor caso, una pérdida de 5%</li>";
						break;
					case 26:
						if(val.getValue().equals("1"))
							inPreg4+="<li>Inversión que en el mejor de los casos te da una ganancia de 8% y en el peor caso, un rendimiento del 0%</li>";
						break;
					case 27:
						if(val.getValue().equals("1"))
							inPreg4+="<li>Inversión en la que tu rendimiento esperado sería del 4% </li>";
						break;
						
						/*Preg 5*/
					case 28:
						if(val.getValue().equals("1"))
							inPreg5+="<li>Sí estoy dispuesto</li>";
						break;
					case 29:
						if(val.getValue().equals("1"))
							inPreg5+="<li>Algunas veces estaría dispuesto</li>";
						break;
					case 30:
						if(val.getValue().equals("1"))
							inPreg5+="<li>Sólo invierto en instrumentos con el menor riesgo posible</li>";
						break;
									
				}			
			}		
		}
		
		inPreg1 += "</ul>";
		inPreg2 += "</ul>";
		inPreg3 += "</ul>";			
	}
	
	protected boolean enviaSMSContract(String url, Integer prospectus_id){
		
		//System.out.println( tokenGen );
		boolean flag = false; 
		
		try{
			
			PublicProyectServiceLocator kubolocator = new  PublicProyectServiceLocator();
			
			PublicProyect kuboservices =  kubolocator.getPublicProyect();
			
			SMSRequestService request =  new SMSRequestService() ;
			
			request.setEmisor_id( sesion.getProspectus_id() + "" );
			
			request.setBursolnum(null);
			request.setCampaign(null);
			
			if( persona.getProspectus().getArea().toString().equals("I") ){
			
				request.setEvent_id("43");
				
				String msg = "Con KUBO gana dinero ¡YA!. Invierte con nosotros, da clic en: "+url+" y firma tus contratos ahora.";
				
				request.setMessage(msg);
				
			}else if( persona.getProspectus().getArea().toString().equals("L") ) {
				
				request.setEvent_id("43");
				
				String msg = "Hola "+ persona.getFirst_name().toUpperCase() +" ¡ya puedes firmar tus contratos! Da click en: "+url+"";
				
				request.setMessage(msg);
				
			}
			
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
	
	protected void init_approval_users(){
		lstApproval = service_proyect_loan.getApprovalUsers( actualProyect.getSafi_credit_id() );
	}
	
	protected void inicializaPromo( ProyectLoan actualProyect ){
		
		offer = null;
		pagePromo = "../../../secciones/registro/pre_aprobacion/";
		rejectionMotiveStr = "";
		
		CheckScoreProcessed checkScore = (CheckScoreProcessed) resolver.getValue(context, null, "checkScoreProcessed");
		
		if(score != null &&  score.getMx_solicitud_buro() != null){
			
			score.setRisk_processed(1);
			score.setScreen_viewed(1);
			checkScore.setScore(score);
			
			offer = checkScore.getOffer_service().getOfferByBurSolNum( score.getMx_solicitud_buro() );
			
			List<SegmentProspectus> seg = checkScore.getSegmentprospectusservice().loadSegmentProspectListByType( score.getProspectus_id(), score.getCompany_id() ,1);
			
			if (seg != null && seg.size() > 0 && seg.get(0).getPk().getSegment_id() == 8 ){
				
				hasEflTest = true;
				
				if(score.getEfl_test() != null && score.getEfl_test().equals("1") ){
				
					efl_OK =   true;
				
				}
				
				if(score.getEfl_test() != null && score.getEfl_test().equals("3") ){
					
					efl_ERROR =   true;
				
				}
				
			}
			
		}else{
			
			Scoring sc = new Scoring();
			
			if( actualProyect != null && actualProyect.getMx_solicitud_buro() != null ){
				
				sc.setMx_solicitud_buro(actualProyect.getMx_solicitud_buro());
				sc.setRisk_processed(1);
				sc.setScreen_viewed(1);
				sc.setOpening_commission(actualProyect.getOpening_commission());
				
				checkScore.setScore(sc);
				
				offer = checkScore.getOffer_service().getOfferByBurSolNum(actualProyect.getMx_solicitud_buro());
				
			}
			
		}
		
		if( offer != null ){
			
			checkScore.setProspectus_id(actualProyect.getProyectloanPk().getProspectus_id());
			checkScore.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
			checkScore.validaMsg();
			checkScore.setUserName(persona.getFirst_name() + " " + persona.getFather_last_name());
			
			promotionTitle = checkScore.getTitlePromo();
			
			pagePromo += checkScore.getPagePromo();
			
			flagPromo = true;
			
			if( offer.getRejection1_date() != null || offer.getRejection2_date() != null ){
			
				hasOffer = 1;
				
				if( offer.getOffer_status_id() != null &&  offer.getOffer_status_id().toString().equals("1")  ){
					hasOffer = 5;// Aceptado en Mesa
				}
			
			}else if( offer.getAccepted2_date() != null ){
				
				if( offer.getOffer_status_id() != null &&  offer.getOffer_status_id().toString().equals("2")  ){
				
					hasOffer = 4;
				
				}else{
					hasOffer = 2;
				}
				
			}else {
				
				hasOffer = 3;
				
			}
			
			if( offer.getOffer_rejection_motive_id() != null && offer.getOffer_rejection_motive_id().intValue() == 1 ){
				
				rejectionMotiveStr = offer.getOther_rejection_motive();
				hasOffer = 4;// Rechazado en Mesa
				
			}else if( offer.getOffer_rejection_motive_id() != null ){
				
				List<OfferRejectionMotive> lstm =  offerrejectionmotiveservice.getOfferRejectionMotiveList();
				
				for( OfferRejectionMotive mo : lstm ){
					
					if( offer.getOffer_rejection_motive_id().intValue() == mo.getPk().getOffer_rejection_motive_id() ){
						
						rejectionMotiveStr = mo.getDescription();
						break;
					}
					
				}
				
				hasOffer = 4;// Rechazado en Mesa
				
			}
			
			
		}else{
			pagePromo += "tmp1.xhtml";
			flagPromo = false;
		}
		
		
		
	}
	
	
	public void initListEFLProgress(){
		
		List<EflScore> eflscorelist = eflscoreservice.getEflScoreListByBurSolNum( actualProyect.getMx_solicitud_buro() );
		
		List<ProgressEFL> eflprogresslist = new ArrayList<ProgressEFL>();
				
		if( eflscorelist != null && eflscorelist.size() > 0 ){
			
			for( EflScore efl : eflscorelist ){
				
				if( efl.getConsulting_status() != null && efl.getConsulting_status().equals( "1" ) ){
					
					try{
					
						ProgressEFL p = new ProgressEFL();
						
						JSONObject jsonObj = new JSONObject( efl.getEfl_return() );
						
						Integer estado	=	(Integer) jsonObj.get("estado");
						
						p.setAnswer((String) jsonObj.get("answer"));
						p.setDateCreated((String) jsonObj.get("dateCreated"));
						p.setEflId((String) jsonObj.get("eflId"));
						p.setEstado(estado+"");
						p.setExternalkey((String) jsonObj.get("externalkey"));
						p.setFullname((String) jsonObj.get("fullname"));
						p.setIddata((String) jsonObj.get("iddata"));
						p.setMensajeEstado((String) jsonObj.get("mensajeEstado"));
						p.setScore((String) jsonObj.get("score"));
						p.setStatus((String) jsonObj.get("status"));
						p.setStatusScore((String) jsonObj.get("statusScore"));
						
						p.setConsulting_date( efl.getConsulting_date() );
						
						eflprogresslist.add(p);
						
					}catch( Exception e ){
						
						e.printStackTrace();
						
					}
					
				}else{
					
					try{
						
						ProgressEFL p = new ProgressEFL();
						
						JSONObject jsonObj = new JSONObject( efl.getEfl_return() );
						
						Integer estado	=	(Integer) jsonObj.get("estado");
						
						p.setMensajeEstado((String) jsonObj.get("mensajeEstado"));
						p.setEstado(estado+"");
						
						p.setConsulting_date( efl.getConsulting_date() );
						
						eflprogresslist.add(p);
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		EFLProgressSession efl          = (EFLProgressSession)         resolver.getValue(context, null, "eFLProgressSession");
		
		efl.setEflprogresslist(eflprogresslist);
		
	}
	
	public void consultaEFL(){
		
		EflConnect efl = new EflConnect();
		
		efl.EFLResult(actualProyect.getProyectloanPk().getProspectus_id()+"", actualProyect.getMx_solicitud_buro());
		
		initListEFLProgress();
	}
}
