package mx.com.kubo.managedbeans.mesa.reportes.buro;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import mx.com.kubo.bean.ConsultingBean;
import mx.com.kubo.bean.Rate;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.buro.Bur_maxoto;
import mx.com.kubo.managedbeans.buro.Bur_spca;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.BuroCache;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.IdentifiedCredit;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RecommendationType;
import mx.com.kubo.model.SimulationConfig;
import mx.com.kubo.tools.FormatoCadenas;

import com.soa.model.businessobject.BurGraphic;
import com.soa.model.businessobject.BurResponse;
import com.soa.model.businessobject.BurResume;
import com.soa.model.businessobject.Vbur_maxoto;
import com.soa.model.businessobject.Vtbur_infocnsltms;
import com.soa.model.businessobject.Vtbur_infocnsltult;
import com.soa.model.businessobject.Vtbur_infocredcte_c;
import com.soa.model.businessobject.Vtbur_infocredcte_m;
import com.soa.model.businessobject.Vtbur_infocredcte_vig;
import com.soa.model.businessobject.Vtbur_infodircte;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.KuboRulesResponse;

public abstract class ChartBackBeanAMO extends ChartBackBeanDMO
{			
	protected void init_prospecto() 
	{		
		membership_pk = new MembershipPK();					
		membership_pk.setCompany_id(1);
		
		if(promotorIDOfMail != null)
		{					
			membership_pk.setProspectus_id(Integer.parseInt(promotorIDOfMail));
			
			thisMember          = membershipservice.getMembershipById(membership_pk);						
			naturalPersonOfMail = thisMember.getPerson();
			
			if(naturalPersonOfMail != null )
			{							
				memberSelect = naturalPersonOfMail.getNatPerPK().getProspectus_id() + "::" + naturalPersonOfMail.getNatPerPK().getCompany_id();							
			}

			is_prospectus_OK = false;						
			showInputId      = false;
		
		} else {	
			
			showInputId = true;					
		}
	}
	
	protected void init_promotor_ENABLED()
	{		
		is_promotor_ENABLED = false;
		
		if(promotorIDOfMail != null && promotorIDOfMail.trim().length() > 0 )
		{		
			if( (sesion.getProspectus_id()+"").equals((Integer.parseInt(promotorIDOfMail))+"") )
			{
				is_promotor_ENABLED = true;
			}			
		}
					
	}
	
	protected void init_promotor_ID() 
	{
		if(is_promotor_ENABLED)
		{
			membership_pk = new MembershipPK();				
			membership_pk.setCompany_id(sesion.getCompany_id());
			membership_pk.setProspectus_id( sesion.getProspectus_id());
			
			thisMember          = membershipservice.getMembershipById(membership_pk);				
			naturalPersonOfMail = thisMember.getPerson();
			
			if(naturalPersonOfMail != null )
			{				
				memberSelect = naturalPersonOfMail.getNatPerPK().getProspectus_id() + "::" + naturalPersonOfMail.getNatPerPK().getCompany_id();					
			}
			
			is_prospectus_OK = true;
			
			showInputId = false;
			diferentUsr = (false);
			
		} else {				
			if(promotorIDOfMail != null && promotorIDOfMail.trim().length() > 0 )
			{
			
				if( (sesion.getProspectus_id()+"").equals((Integer.parseInt(promotorIDOfMail))+"") )
				{
					
					showInputId = true;
					diferentUsr = (false);
					
				} else {						
					showInputId = false;						
					account1 	= sesion.getEmail();
					
					membership_pk = new MembershipPK();						
					membership_pk.setCompany_id(1);
					membership_pk.setProspectus_id(Integer.parseInt(promotorIDOfMail));
					
					thisMember 			= membershipservice.getMembershipById(membership_pk);
					
					account2 		    = thisMember.getEmail();						
					usr      			= thisMember.getEmail();						
					naturalPersonOfMail = thisMember.getPerson();						
					memberSelect 		= thisMember.getPerson().getNatPerPK().getProspectus_id() + "::" + naturalPersonOfMail.getNatPerPK().getCompany_id();
					
					diferentUsr = (true);						
				}
				
			} else {
				if(sesion.getArea().toString().equals("M") && burSolNum != null)
				{						
					is_prospectus_OK = true;
					
				}
			}
		}	
	}
	
	protected String quitarValorNulo(String cadena)
	{
		if(cadena == null)
		{
			return "";
		} else {		
			return cadena;
		}
	}
	
	protected void init_nota_del_coach() 
	{				
		lista_notas_del_coach = noteService.getLista_notas_por_tipo(NOTA_DEL_COACH, proyecto);
		
		if(lista_notas_del_coach != null && lista_notas_del_coach.size() > 0)
		{
			nota_coach_ENABLED = true;
			
			nota_del_coach = lista_notas_del_coach.get(LAST);
			
			prospectus_id = nota_del_coach.getChange_prospectus_id();
			company_id    = nota_del_coach.getNotesPk().getCompany_id();
			
			membership_pk = new MembershipPK();
			membership_pk.setProspectus_id(prospectus_id);
			membership_pk.setCompany_id(company_id);
			
			coach = membershipservice.getMembershipById(membership_pk);
			
			coach_nombre = coach.getPerson().NombreCompletoNPM();
			
			coach_fecha_nota = date_format.format(nota_del_coach.getChange_date());
			
		} else {
			
			nota_coach_ENABLED = false;
		}
	}

	protected void asignar_proyect_loan_info() 
	{
		//Systemout.println( "Iniciando asignar_proyect_loan_info: "+burSolNum );
		
		lista_proyect_loan = proyectLoanService.getProyectLoansListByBurSolNum(burSolNum);
		
		
		
		if(lista_proyect_loan != null && lista_proyect_loan.size() > 0)
		{			
			//Systemout.println( "proyectos: "+lista_proyect_loan.size() );
			
			proyecto = lista_proyect_loan.get((lista_proyect_loan.size() - 1));
			
			if(proyecto != null)
			{						
				tScore        = proyecto.getTransunion_bc_score() == null ? "No Asignado" : "0" + proyecto.getTransunion_bc_score().toString();
				incremento1   = proyecto.getTransunion_mod1()     == null ? "No Asignado" : proyecto.getTransunion_mod1();
				incremento2   = proyecto.getTransunion_mod2()     == null ? "No Asignado" : proyecto.getTransunion_mod2();						
				prospectus_id = proyecto.getProyectloanPk().getProspectus_id();
				company_id    = proyecto.getProyectloanPk().getCompany_id();
				cci_score     = proyecto.getCci_score();
										
			} else {
				
				tScore        = "No Asignado";
				incremento1   = "No Asignado";
				incremento2   = "No Asignado";						
				prospectus_id = 0;
				company_id    = 1;					
			}
			
		} else {
			
			/*
			if( lista_proyect_loan != null ){
				System.out.println( "proyectos: "+lista_proyect_loan.size() );
			}else{
				System.out.println( "proyectos: null" );
			}*/
			
			tScore      = "No Asignado";
			incremento1 = "No Asignado";
			incremento2 = "No Asignado";
			
			prospectus_id = 0;
			company_id    = 1;				
		}
	}
	
	protected void asignar_transunion_rechazo() 
	{
		transunion = transunionrespservice.getTransunionByBurSolNum(burSolNum);
		
		validTransUnion = false;
		transErrorMsg   = null;
		
		if(transunion !=  null)
		{					
			String rechazo_fpd  = transunion.getMx_rechazofpd();
			String rechazo_mop  = transunion.getMx_rechazomop();
			String rechazomop24 = transunion.getMx_rechazomop24();
			String rechazo_obs  = transunion.getMx_rechazoobs();
			
			boolean is_rechazo_ENABLED = (rechazo_fpd  != null && rechazo_fpd.equals("1")) 
									   || (rechazo_mop  != null && rechazo_mop.equals("1")) 
									   || (rechazomop24 != null && rechazomop24.equals("1")) 
									   || (rechazo_obs  != null && rechazo_obs.equals("1"));
			
			if(is_rechazo_ENABLED)
			{
				validTransUnion = true;
				
				if(rechazo_mop != null && rechazo_mop.equals("1"))
				{								
					transErrorMsg = "MOP de créditos abiertos mayor a 3 y saldo vencido mayor a 100";
					
				} else if(rechazo_obs != null && rechazo_obs.equals("1")) {
					
					transErrorMsg = "Con clave de observación negativa en créditos de menos de 12 meses";

				} else if (rechazomop24 != null && rechazomop24.equals("1")) {
				
					transErrorMsg = "MOP máximo en últimos 24 meses mayor a 5 en cuentas abiertas";

				} else if(rechazo_fpd!=null && rechazo_fpd.equals("1")) {
					
					transErrorMsg = "Rechazado por la variable RECHAZOFPD";
					
				} else {
					
					transErrorMsg = null;
				}							
			}					
		}
	}
	
	protected void asignar_all_data_bur() throws ServiceException, RemoteException 
	{
		locator     = new WsSgbRiskServiceLocator();
		service_SGB_risk     = locator.getWsSgbRisk();	
		
		cWS_1 = Calendar.getInstance();
		cWS_1.setTime(new Date());
		
		 org.apache.axis.client.Stub s = (org.apache.axis.client.Stub) service_SGB_risk;
		 s.setTimeout(300*60*1000);  // 5 hrs, in miliseconds
		
		allDataBur  = service_SGB_risk.getAllDataBur(burSolNum);
		
		cWS_2 = Calendar.getInstance();
		cWS_2.setTime(new Date());
		
		difWS_2 = cWS_2.getTimeInMillis() - cWS_1.getTimeInMillis();
		
		//Systemout.println("Tiempo en consultar datos WS: " + difWS_2 + " milisegundos");		
	}
	
	@SuppressWarnings("deprecation")
	protected BurResume getBuroResume(String burSol) 
	{
		BurResume res = null;
		try {
			
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			
			WsSgbRisk service = locator.getWsSgbRisk();
			
			res = service.getBurResume(null, null,burSol);
			
			this.maxoto = null;
			
			if(res!=null){
				
				if(res.getFec_credantiguo()!=null)
				{
				
					int year, month, day;
					year = new Integer(res.getFec_credantiguo().substring(4,8));
					month = new Integer(res.getFec_credantiguo().substring(2,4));
					day = new Integer(res.getFec_credantiguo().substring(0, 2));
		
					this.setBur_fec_credantiguo(new Date(year-1900,month-1 ,day));
				
				}else{
					this.setBur_fec_credantiguo(null);
				}
				
				if(res.getSpca()!=null && res.getSpca().length>0)
				{
					this.spca = new Bur_spca(res.getSpca());
				}
				
				
				if(res.getMaxoto()!=null && res.getMaxoto().length>0)
				{
					for(int i =0; i<res.getMaxoto().length;i++)
					{
						Vbur_maxoto aux = res.getMaxoto()[i];
						if(aux.getOtorgante().toUpperCase().contains("BANCO"))
						{
							this.maxoto= new Bur_maxoto(aux);
						}
					}
					
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	protected void asignar_calificacion_kubo() 
	{
		if(proyecto != null)
		{
			calificacion_kubo = proyecto.getKubo_score_a() + proyecto.getKubo_score_b();
			
		} else {
			
			calificacion_kubo = infocalkubo.getCalificacion_kubo();
		}
		
		if(calificacion_kubo.equals("N0"))
		{
			calificacion_kubo = "E7";
		}
		
		kubo_score_letter = calificacion_kubo.charAt(0);
		kubo_score_number = calificacion_kubo.charAt(1);
		
		kubo_score_class = "kubo_score_" + kubo_score_letter;
	}
	
	protected void asignarCorreoAcreditado()
	{
		pk = new MembershipPK();
		
		pk.setCompany_id(company_id);
		pk.setProspectus_id( prospectus_id );	
		
		acreditado = membershipservice.getMembershipById(pk);
		
		nombreAcreditado = acreditado.getPerson().NombreCompletoNPM();
		
		
	}
	
	protected boolean isNombreAcreditadoHomogeneo()
	{		
		sb = new StringBuilder();
		
		sb.append(quitarValorNulo(infocte.getNombre1())).append(SEPARADOR);
		sb.append(quitarValorNulo(infocte.getNombre2())).append(SEPARADOR);
		sb.append(quitarValorNulo(infocte.getApellido1())).append(SEPARADOR);
		sb.append(quitarValorNulo(infocte.getApellido2()));		
		
		nombreCliente    = sb.toString();
		nombreCliente    = nombreCliente.replace(" ", "");
		nombreCliente    = nombreCliente.toLowerCase();
		
		nombreAcreditado = quitaAcentos( nombreAcreditado );
		
		String tmp  = nombreAcreditado.replace(" ", "").toLowerCase();
		
		return tmp.equals(nombreCliente);		
	}
	
	protected boolean isRFCAcreditadoHomogeneo()
	{
		rfcCliente    = infocte.getRfc();
		rfcAcreditado = acreditado.getPerson().getMx_rfc();
				
		if(rfcAcreditado.length() < 10)
		{
			rfcAcreditado = rfcAcreditado.substring(0, 10);
		}

		return rfcAcreditado.equalsIgnoreCase(rfcCliente);
	}
	
	protected void asignar_bloqueos_autotizacion() 
	{
		tableRules = new StringBuilder();
		
		flagRules = false;
		
		if(rules != null && rules.length > 0)
		{				
			for(KuboRulesResponse rule : rules)
			{
			 	//System.out.println( " *** "+ rule + " --  " +
//						rule.getTextCSS() + " --  " +
//						
//						rule.getRenderRule()  );
				
//				
//				System.out.println( " *** "+ rule + " --  " );
//				
				if(rule != null 
				&& rule.getTextCSS() != null
				&& !rule.getTextCSS().equals("null") 
				&& rule.getRenderRule() != null 
				&& rule.getRenderRule().equals("1") )
				{
					tableRules.append( rule.getTextCSS() );
					flagRules = true;
				}					
			}				
		} 
		
		if(flagRules && tableRules != null && !tableRules.toString().endsWith("</div>")){
			tableRules.append( "</div>");
		}
		
		tableRulesStr = tableRules.toString();
	}
	
	protected void asignar_datos_alerta_reporte() 
	{
		cAlert1_1 = Calendar.getInstance();
		cAlert1_1.setTime(new Date());
		
		tableAlertas1    = new StringBuilder();
		tableAlertas1Str = null ;
		tableAlertas1Str = "";
			
		if(infoAlertaInc != null )
		{						
			tableAlertas1.append(	"<thead>" +
		 
								 		"<tr>" +
				 
									 		"<th>Clave</th>" +
									 		"<th>Descripcion</th>" +
									 		"<th>Responsabilidad</th>" +
									 		"<th>Reportado</th>" +
									 		"<th>Mensaje</th>" +
									 		"<th>Datos de</th>" +
									 		
									 	"</tr>" +
									 	
					 				"</thead>");
			
			tableAlertas1.append("<tbody>");
			
			for( int i = 0 ; i< infoAlertaInc.length; i++)
			{							
				tableAlertas1.append("<tr>");							 
					tableAlertas1.append("<td>" + infoAlertaInc[i].getClave() + "</td>");
					tableAlertas1.append("<td>" + infoAlertaInc[i].getDescripcion() + "</td>");
					tableAlertas1.append("<td>" + infoAlertaInc[i].getResponsabilidad() + "</td>");
					tableAlertas1.append("<td>" + infoAlertaInc[i].getReportado() + "</td>");
					tableAlertas1.append("<td>" + infoAlertaInc[i].getMensaje() + "</td>");
					tableAlertas1.append("<td>" + infoAlertaInc[i].getRep_credito() + "</td>");							
				tableAlertas1.append("</tr>");								
			}
			
			tableAlertas1.append("</tbody>");
			
			tableAlertas1Str = tableAlertas1.toString();						
		}	
		
		cAlert1_2 = Calendar.getInstance();
		cAlert1_2.setTime(new Date());
		
		difAlert1_2 = cAlert1_2.getTimeInMillis() - cAlert1_1.getTimeInMillis();
		
		//Systemout.println("Tiempo en cargar datos de Alertas 1: " + difAlert1_2 + " milisegundos");		
	}
	
	protected void asignar_datos_alerta_consulta() 
	{
		cAlert_1 = Calendar.getInstance();
		cAlert_1.setTime(new Date());
		
		tableAlertas2 = new StringBuilder();
		tableAlertas2Str = null;
		tableAlertas2Str = "";
			
		if(infoAlertaPrev != null )
		{						
			tableAlertas2.append(	"<thead>" +
					 
								 		"<tr>" +
				 
									 		"<th>Clave</th>" +
									 		"<th>Descripcion</th>" +
									 		"<th>Responsabilidad</th>" +
									 		"<th>Reportado</th>" +
									 		"<th>Mensaje</th>" +
									 		"<th>Datos de</th>" +
									 		
									 	"</tr>" +
									 	
					 				"</thead>");
			
			tableAlertas2.append("<tbody>");
			
			for( int i = 0 ; i< infoAlertaPrev.length; i++)
			{							
				tableAlertas2.append("<tr>");
				 
					tableAlertas2.append("<td>" + infoAlertaPrev[i].getClave() + "</td>");
					tableAlertas2.append("<td>" + infoAlertaPrev[i].getDescripcion() + "</td>");
					tableAlertas2.append("<td>" + infoAlertaPrev[i].getResponsabilidad() + "</td>");
					tableAlertas2.append("<td>" + infoAlertaPrev[i].getReportado() + "</td>");
					tableAlertas2.append("<td>" + infoAlertaPrev[i].getMensaje() + "</td>");
					tableAlertas2.append("<td>" + infoAlertaPrev[i].getConsulta() + "</td>");
				
				tableAlertas2.append("</tr>");								
			}
			
			tableAlertas2.append("</tbody>");
			
			tableAlertas2Str = tableAlertas2.toString();
		}
		
		cAlert_2 = Calendar.getInstance();
		cAlert_2.setTime(new Date());
		
		difAlert_2 = cAlert_2.getTimeInMillis() - cAlert_1.getTimeInMillis();
		
		//Systemout.println("Tiempo en cargar datos de Alesrtas 2 : "+difAlert_2+" milisegundos");		
	}
	
	protected void asignar_consultas_semestre_anterior() throws ParseException 
	{
		
		 cCns_R_1 = Calendar.getInstance();
		 cCns_R_1.setTime(new Date());
		 
		 tableConsultUlt =  new StringBuilder();
		 tableConsultUlt.append(	"<thead>" +
		 
								 		"<tr>" +
				 
									 		"<th>Registro</th>" +
									 		"<th>Fecha de Consulta</th>" +
									 		"<th>Clave Otorgante</th>" +
									 		"<th>Nombre Otorgante</th>" +
									 		"<th>Tipo Contrato</th>" +
									 		"<th>Descripción Tipo Contrato</th>" +
									 		"<th>Monto Solicitado</th>" +
									 		"<th>Indicador Cliente Nuevo</th>" +
									 		
									 	"</tr>" +
									 	
					 				"</thead>");
		 if( cnsltult != null )
		 {						 
			 tableConsultUlt.append("<tbody>");
			 
			 int iUlt = 0;
			 
			 for( Vtbur_infocnsltult cnslt1 : cnsltult )
			 {
				 
				 if( cnslt1 != null)
				 {
				 
					 //if(cnslt1.getNombre_otorgante()!=null && !cnslt1.getNombre_otorgante().trim().equals("BURO DE CREDITO")){
					 iUlt++;
					 
					 String fec = (cnslt1.getFecha_de_consulta()==null?"":(fm.format(fm3.parse(cnslt1.getFecha_de_consulta()))));
					 
					 String d[] = fec.split("/");
					 
					 String key = "";
					 
					 if(d.length == 3 )
					 {
						 
						 key = d[1]+"::"+d[2];
						 
					 }
					 
					 List<Vtbur_infocnsltult> ltConInfoUlt  = htConsultUlt.get(key);
					 
					 if( ltConInfoUlt == null ){
						 
						 ltConInfoUlt = new ArrayList<Vtbur_infocnsltult> ();
						 
					 }
					 
					 if( cnslt1.getNombre_otorgante() != null && cnslt1.getNombre_otorgante().equals("KUBO FINANCIERO") )
					 {
						 
						 consultasKubo = consultasKubo +1;
						 
						 consultasKuboULT = consultasKuboULT +1;
						 
					 } else if (cnslt1.getNombre_otorgante() != null && cnslt1.getNombre_otorgante().equals("CONSUMIDOR FINAL")) {
						 
						 consultas_propias += 1;
						 consultas_propiasULT += 1;
						 
					 } else {
						 consultasOtras = consultasOtras + 1;
						 consultasOtrasULT = consultasOtrasULT + 1;
					 }
					 
					 ltConInfoUlt.add(cnslt1);
					 
					 htConsultUlt.put(key, ltConInfoUlt);
					 
						 tableConsultUlt.append("<tr>");
						 
						 	tableConsultUlt.append("<td>"+ iUlt +"</td>");
						 	tableConsultUlt.append("<td>"+fec +"</td>");
						 	tableConsultUlt.append("<td>"+cnslt1.getClave_otorgante()+"</td>");
						 	tableConsultUlt.append("<td>"+cnslt1.getNombre_otorgante()+"</td>");
						 	tableConsultUlt.append("<td>"+cnslt1.getTipo_contrato()+"</td>");
						 	tableConsultUlt.append("<td>"+cnslt1.getDescripcion_tipo_contrato()+"</td>");
						 	tableConsultUlt.append("<td class='moneyClass' style='text-align: right !important;' >"+(cnslt1.getMonto_solicitado()==null?"":(dec.format(Double.parseDouble(cnslt1.getMonto_solicitado()))))+"</td>");
						 	tableConsultUlt.append("<td>"+cnslt1.getIndicador_cliente_nuevo()+"</td>");
						 
						 tableConsultUlt.append("</tr>");
				 	//	}
				 	} 
				}
		
			 	tableConsultUlt.append("</tbody>");
		 
		 } 
		 
		 tableConsultUltData = tableConsultUlt.toString();
		 
		 cCns_R_2 = Calendar.getInstance();
		 cCns_R_2.setTime(new Date());

		 difCns_R_2 = cCns_R_2.getTimeInMillis() - cCns_R_1.getTimeInMillis();
		 
		 //Systemout.println("Tiempo en cargar datos de Consultas Recientes: "+difCns_R_2+" milisegundos");	 
	}
	
	protected void asignar_consultas_semestre_posterior() throws ParseException 
	{
		 Calendar cCns_M6_1 = Calendar.getInstance();
		 cCns_M6_1.setTime(new Date());
		 
		 
		 StringBuilder tableConsultMS =  new StringBuilder();
		 tableConsultMS.append(		"<thead>" +
		 
								 		"<tr>" +
				 
									 		"<th>Registro</th>" +
									 		"<th>Fecha de Consulta</th>" +
									 		"<th>Clave Otorgante</th>" +
									 		"<th>Nombre Otorgante</th>" +
									 		"<th>Tipo Contrato</th>" +
									 		"<th>Descripción Tipo Contrato</th>" +
									 		"<th>Monto Solicitado</th>" +
									 		"<th>Indicador Cliente Nuevo</th>" +
									 		
									 	"</tr>" +
									 	
					 				"</thead>");
		 if( cnsltms != null ){
			 
			 tableConsultMS.append("<tbody>");
			 
			 int iMs = 0;
			 
			 for( Vtbur_infocnsltms cnslt1 : cnsltms ){
				 
//				 if(!cnslt1.getNombre_otorgante().trim().equals("BURO DE CREDITO")){
				 
				 iMs++;
				 
				 String fec = (cnslt1.getFecha_de_consulta()==null?"":(fm.format(fm3.parse(cnslt1.getFecha_de_consulta()))));
				 
				 String d[] = fec.split("/");
				 
				 String key = "";
				 
				 if(d.length == 3 ){
					 
					 key = d[1]+"::"+d[2];
					 
				 }
				 
				 List<Vtbur_infocnsltms> ltConInfoMs  = htConsultMS.get(key);
				 
				 if( ltConInfoMs == null ){
					 
					 ltConInfoMs = new ArrayList<Vtbur_infocnsltms> ();
					 
				 }
				 
				 ltConInfoMs.add(cnslt1);
				 
				 htConsultMS.put(key, ltConInfoMs);
				 
				 if( cnslt1.getNombre_otorgante() != null && cnslt1.getNombre_otorgante().equals("KUBO FINANCIERO") ){
					 
					 consultasKubo = consultasKubo +1;
					 consultasKuboMS = consultasKuboMS +1;
					 
				 }else if (cnslt1.getNombre_otorgante() != null && cnslt1.getNombre_otorgante().equals("CONSUMIDOR FINAL")) {
					 
					 consultas_propias += 1;
					 consultas_propiasMS += 1;
					 
				 } else{
					 consultasOtras = consultasOtras + 1;
					 consultasOtrasMS = consultasOtrasMS + 1;
				 }
				 
				 tableConsultMS.append("<tr>");
					 tableConsultMS.append("<td>"+iMs+"</td>");
					 tableConsultMS.append("<td>"+(cnslt1.getFecha_de_consulta()==null?"":(fm2.format(fm3.parse(cnslt1.getFecha_de_consulta())))) +"</td>");
					 tableConsultMS.append("<td>"+cnslt1.getClave_otorgante()+"</td>");
					 tableConsultMS.append("<td>"+cnslt1.getNombre_otorgante()+"</td>");
					 tableConsultMS.append("<td>"+cnslt1.getTipo_contrato()+"</td>");
					 tableConsultMS.append("<td>"+cnslt1.getDescripcion_tipo_contrato()+"</td>");
					 tableConsultMS.append("<td class='moneyClass' style='text-align: right !important;' >"+(cnslt1.getMonto_solicitado()==null?"":(dec.format(Double.parseDouble(cnslt1.getMonto_solicitado()))))+"</td>");
					 tableConsultMS.append("<td></td>");
				 tableConsultMS.append("</tr>");
			 	}	
			 }
		
			 tableConsultMS.append("</tbody>");
		 
		 //} 
		 
		 tableConsultMSData = tableConsultMS.toString();
		 
		 cCns_M6_2 = Calendar.getInstance();
		 cCns_M6_2.setTime(new Date());
		
		 difCns_M6_2 = cCns_M6_2.getTimeInMillis() - cCns_M6_1.getTimeInMillis();
		 
		 //Systemout.println("Tiempo en cargar datos de Consulta en más de 6Meses: "+difCns_M6_2+" milisegundos");		 
	}
	
	protected void asignar_dias_transcurridos_consulta() 
	{
		date1 = new GregorianCalendar();
        date1.setTime(dFecConsult);
        
        date2 = new GregorianCalendar();
        date2.setTime(new Date());
        
        rangoAnyos = 0;
        rango = 0;
        /* COMPROBAMOS SI ESTAMOS EN EL MISMO ANYO */
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) 
        {        
        	rango = (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        	
        } else {
            /* SI ESTAMOS EN DISTINTO ANYO COMPROBAMOS QUE EL ANYO DEL DATEINI NO SEA BISIESTO
             * SI ES BISIESTO SON 366 DIAS EL ANYO
             * SINO SON 365
             */
            diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;

            /* CALCULAMOS EL RANGO DE ANYOS */
            rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);

            /* CALCULAMOS EL RANGO DE DIAS QUE HAY */
            rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        }
		
		diasTrans = rango+"";	
	}
	
	protected void asignar_creditos_vigentes() throws ParseException 
	{		
		Calendar cCrd_V_1 = Calendar.getInstance();
			
		cCrd_V_1.setTime(new Date());
			
		 
		 Double subMontPagar = 0D;
		 Double subDeudaInteres = 0D;
		 Double subInteresPaga = 0D;
		 Double subSaldoActual = 0D;
		 Double subLimCred = 0D;
		 
		 Double subMontPagarCom = 0D;
		 Double subDeudaInteresCom = 0D;
		 Double subInteresPagaCom = 0D;
		 Double subSaldoActualCom = 0D;
		 Double subLimCredCom = 0D;
		 
		 Double totalSaldoVencido = 0D;
		 
		 Double totalMontoCred = 0D;
		 
		 if(infocredcte_vig !=null)
		 {
			 
			 StringBuilder tableVig =  new StringBuilder();
			 StringBuilder tableVigCom =  new StringBuilder();
			 tableVig.append(	"<thead>" +
							 		"<tr>" +
								 		"<th>Crédito</th>" +
//												 		"<th>SIC</th>" + 
								 		"<th>Otorgante</th>" +
								 		"<th>Mop Actual</th>" +
								 		"<th>Frecuencia</th>" +
								 		"<th>Pagos</th>" +
								 		"<th>Pago Periódico</th>" +
								 		
								 		"<th>Monto del Crédito</th>" +
								 		
								 		"<th>Tasa</th>" + //campo formula
								 		"<th>Tasa Kubo</th>" + //campo formula
								 		
								 		"<th>Pago Kubo</th>" + //campo formula
								 		"<th>Ahorro</th>" + //campo formula
								 		"<th>Tipo Contrato</th>" +
								 		"<th>Pago Mensual</th>" +
								 		"<th>Interés que Paga</th>" + //campo formula
								 		"<th>Deuda más interes</th>" + //campo formula
								 		"<th>Pago Mínimo</th>" + //campo formula
								 		"<th>Crédito Máximo</th>" +
								 		"<th>Saldo Actual</th>" +
								 		"<th>Límite de Crédito</th>" +
								 		"<th>Saldo Vencido</th>" +
								 		"<th>Fecha de Apertura</th>" +
								 		"<th>Tipo</th>" +
								 		"<th>Tipo Cuenta</th>" +
								 		"<th>Fecha Último Pago</th>" +
								 		"<th>Fecha Última Compra</th>" +
								 		"<th>Fecha de Vencimiento</th>" + // campo formula
								 		"<th>Fecha Cierre</th>" +
								 		"<th>Garantia</th>" +
								 		"<th>Fecha de Reporte</th>" +
								 		"<th>Histórico de Pagos</th>" +
								 		"<th>Histórico de Pagos Kubo</th>" +
								 		"<th>Observación</th>" +
								 		"<th></th>" +
								 	"</tr>" +
				 				"</thead>");
			 
			 tableVig.append("<tbody>");
			 
			 
			 tableVigCom.append(	"<thead>" +
				 		"<tr>" +
				 		"<th>Crédito</th>" +
				 		"<th>SIC</th>" +
				 		"<th>Otorgante</th>" +
				 		"<th>Tipo Contrato</th>" +
				 		"<th>Frecuencia</th>" +
				 		"<th>Pagos</th>" +
				 		"<th>Mop Actual</th>" +
				 		"<th>Monto a Pagar</th>" +
				 		"<th>Monto Mensual</th>" +
				 		"<th>Deuda más interes</th>" + //campo formula
				 		"<th>Tasa</th>" + //campo formula
				 		"<th>Interés que Paga</th>" + //campo formula
				 		"<th>Pago Mínimo</th>" + //campo formula
				 		"<th>Crédito Máximo</th>" +
				 		"<th>Saldo Actual</th>" +
				 		"<th>Límite de Crédito</th>" +
				 		"<th>Saldo Vencido</th>" +
				 		"<th>Fecha de Apertura</th>" +
				 		"<th>Tipo</th>" +
				 		"<th>Tipo Cuenta</th>" +
				 		"<th>Fecha Último Pago</th>" +
				 		"<th>Fecha Última Compra</th>" +
				 		"<th>Fecha de Vencimiento</th>" + // campo formula
				 		"<th>Fecha Cierre</th>" +
				 		"<th>Garantia</th>" +
				 		
				 		"<th>Descipción de Mop Actual</th> " +
				 		"<th>Fecha de Reporte</th>" +
				 		"<th>Histórico de Pagos</th>" +
				 		"<th>Histórico de Pagos Kubo</th>" +
				 		"<th>Observación</th>" +
				 		"<th></th>" +
				 	"</tr>" +
				"</thead>");

			 tableVigCom.append("<tbody>");
			 
			 
			 for( int i = 0; i<infocredcte_vig.length; i++ ){
				
				 Vtbur_infocredcte_vig vig = infocredcte_vig[i];
				 
				 if(!vig.getOtorgante().toUpperCase().equals("COMUNICACIONES")){ 
					 
					 IdentifiedCredit identifiedCredit = null;
						 /*
						 
						 
						 if(vig.getNo_credito()!=null){
						 
							 identifiedCredit = htIdentified.get(Integer.parseInt(vig.getNo_credito()));
						 } 
						 
						 if(identifiedCredit == null){
							 identifiedCredit  = getCreditIdentified(identify,vig);
							 
							 if(identifiedCredit != null){
								 //CREDITO IDENTIFICADO
								
								 if(vig.getNo_credito()!=null)
									 htIdentified.put(Integer.parseInt(vig.getNo_credito()), identifiedCredit);
							 }
							 
						 }
						 
						 if(identifiedCredit != null){
							 
							 if( identifiedCredit!=null && identifiedCredit.getActual_entity()!=null && identifiedCredit.getActual_entity().trim().length()>0 ){
								 vig.setOtorgante(identifiedCredit.getActual_entity().toUpperCase());
							 }
							 
						 }
						 
						*/
						 
						 tableVig.append("<tr");
						 
						 if(sesion != null && sesion.getProspectus_id()!=null && sesion.getProspectus_id() > 0){
							 n++;
							 
							 if( sesion.getArea().toString().equals("M") )
							 {
								 tableVig.append(" class='clRowChng' onclick='changeNameCount(keyStr"+n+",valStr"+n+")' style='cursor: pointer;' ");
							 }
							 
							 //arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
							 tableScriptJSon.append("var keyStr"+n+"='[{");
							 
									 if(identifiedCredit != null){
										 
										 tableScriptJSon.append("\"Company_id"		+"\":\""+		identifiedCredit.getPk().getCompany_id()			+"\",");
										 tableScriptJSon.append("\"Frequency"		+"\":\""+		identifiedCredit.getPk().getFrequency()				+"\",");
										 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		identifiedCredit.getPk().getOriginal_entity()		+"\",");
										 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		identifiedCredit.getPk().getProspectus_id()			+"\",");
										 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		identifiedCredit.getPk().getTotal_payments()		+"\",");
										 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( identifiedCredit.getPk().getStart_date() )+"\"");
									 	
									 }else{
										
										 tableScriptJSon.append("\"Company_id"		+"\":\""+		((proyecto!=null)? proyecto.getProyectloanPk().getCompany_id()	:1)			+"\",");
										 tableScriptJSon.append("\"Frequency"		+"\":\""+		vig.getFrecuencia_pagos()							+"\",");
										 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		vig.getOtorgante()									+"\",");
										 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		prospectus_id										+"\",");
										 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		vig.getNumero_pagos()								+"\",");
										 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( fm3.parse( vig.getFecha_apertura() ) )	+"\"");
										 
									 }
							 
									 tableScriptJSon.append("}]';");
							//FIN arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
							 
							//arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
									 tableScriptJSon.append("var valStr"+n+"='[{");
							 
									 if(identifiedCredit != null){
										 
										 tableScriptJSon.append("\"Entity"		+"\":\""+		identifiedCredit.getActual_entity().toUpperCase()				+"\",");
										 tableScriptJSon.append("\"Frequency"	+"\":\""+		identifiedCredit.getActual_frequency()			+"\",");
										 tableScriptJSon.append("\"Ammount"		+"\":\""+		identifiedCredit.getActual_total_ammount()		+"\",");
										 tableScriptJSon.append("\"Payment"		+"\":\""+		identifiedCredit.getActual_total_payment()		+"\"");
									 	
									 }else{
										
										 tableScriptJSon.append("\"Entity"		+"\":\""+		vig.getOtorgante().toUpperCase()								+"\",");
										 tableScriptJSon.append("\"Frequency"	+"\":\""+		vig.getFrecuencia_pagos()						+"\",");
										 tableScriptJSon.append("\"Ammount"		+"\":\""+		vig.getLimite_credito()							+"\",");
										 tableScriptJSon.append("\"Payment"		+"\":\""+		vig.getMonto_a_pagar()							+"\"");
										 
									 }
							 
									 tableScriptJSon.append("}]';");
							//FIN arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
							 
						 }
						 
						 tableVig.append(" >");
						 tableVig.append("<td class='clCenter' >"+ (vig.getNo_credito()==null?"":vig.getNo_credito())+" - " +(vig.getInd_circulo()==null?"BC":(vig.getInd_circulo().equals("1")?"CC":"BC"))+ "</td>");
//										 tableVig.append("<td class='clCenter' >"+ (vig.getInd_circulo()==null?"BÚRO":(vig.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"</td>");
						 tableVig.append("<td class='clCenter clIdentified clIdentTD"+vig.getNo_credito()+"' ");
						 
						 tableVig.append(">"+ (vig.getOtorgante()==null?"":vig.getOtorgante())+""
						 		+ "<input type='hidden' id='indentHidden"+vig.getNo_credito()+"' value='"+getStrForCreditIdentified(vig)+"' />"
						 		+ "</td>");
						 
						// MOP ACTUAL
						 	
						 tableVig.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getMop_actual()==null?"":(vig.getMop_actual()+"-")));
							
						 if(vig.getMop_actual()==null){
							 
							 tableVig.append("</td>");
							 
						 }else{
							 if(vig.getMop_actual().equals("UR")){
								 
								 tableVig.append("Cuenta sin información.</td>");
							 
							 }else if(vig.getMop_actual().equals("00")){
								 
								 tableVig.append("Muy reciente para ser informada.</td>");
							 
							 }else if(vig.getMop_actual().equals("01")){
								 
								 tableVig.append("Cuenta al corriente.</td>");
							 
							 }else if(vig.getMop_actual().equals("02")){
								 
								 tableVig.append("Atraso de 01 a 29 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("03")){
								 
								 tableVig.append("Atraso de 30 a 59 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("04")){
								 
								 tableVig.append("Atraso de 60 a 89 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("05")){
								 
								 tableVig.append("Atraso de 90 a 119 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("06")){
								 
								 tableVig.append("Atraso de 120 a 149 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("07")){
								 
								 tableVig.append("Atraso de 150 a 12 meses.</td>");
							 
							 }else if(vig.getMop_actual().equals("96")){
								 
								 tableVig.append("Atraso de 12 meses.</td>");
							 
							 }else if(vig.getMop_actual().equals("97")){
								 
								 tableVig.append("Cuenta con deuda parcial o total sin recuperar.</td>");
							 
							 }else if(vig.getMop_actual().equals("99")){
								 
								 tableVig.append("Fraude cometido por el Cliente.</td>");
							 }
						 }
						 
						 // FIN MOP ACTUAL
						 
						 tableVig.append("<td class='clCenter' >"+ (vig.getFrecuencia_pagos()==null?"":vig.getFrecuencia_pagos())+"</td>");
						 
						 ///////////////////////////////////////////////////////////////////////////////////////////////////////////
						 //Calcular el pago en el que va
						 
						 int ndias = 0;
							if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1) ){
								ndias=15;
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
								 ndias=30;
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL")  != (-1)){
								 ndias=14;
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL") != (-1) ){
								
								ndias=7;
							 }
						 
						 
						 Calendar fecT = Calendar.getInstance();
						 fecT.setTime(fm3.parse(vig.getFecha_apertura()));
						 
						 Calendar fecR = Calendar.getInstance();
						 fecR.setTime(fm3.parse( vig.getFecha_reporte() ) );
						 
						 int numCuota = (-1); 
						 if(ndias>0)
							 while(fecT.getTime().compareTo(fecR.getTime())<0){
								 if(ndias < 30)
									 fecT.add(Calendar.DATE, ndias);
								 else
									 fecT.add(Calendar.MONTH, 1);
								 numCuota++;
							 }
						 
						 
						 // oujihoihoiuhoijj;
						 
						//Fin Calcular el pago en el que va
						///////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						 String strColorN = "";
							
							if( Integer.parseInt( vig.getNumero_pagos()) < Integer.parseInt(numCuota+"") ){
								
								strColorN = "color: #FF0000;";
								
							}
							
						int numPag = 	Integer.parseInt(vig.getNumero_pagos()==null?"0":vig.getNumero_pagos());
							
						 if(numCuota!=(-1) && numPag>0 )
							 tableVig.append("<td class='clCenter' style='"+strColorN+"' >"+ numCuota +" de "+(vig.getNumero_pagos()==null?"":vig.getNumero_pagos())+"</td>");
						 else
							 tableVig.append("<td class='clCenter' style="+strColorN+" >"+(vig.getNumero_pagos()==null?"":vig.getNumero_pagos())+"</td>");
						 
						 tableVig.append("<td class='moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getMonto_a_pagar()==null?"":(dec.format(Double.parseDouble(vig.getMonto_a_pagar()))))+"</td>");
						 
						 
						 
						 //Monto del crédito
						 Double montoCred = 0D;
						 if( vig.getMonto_a_pagar()!=null && ((vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")) || 
								 ( Double.parseDouble( vig.getLimite_credito() ) > 
								 ( Double.parseDouble( vig.getMonto_a_pagar() ) * 
										 Double.parseDouble( vig.getNumero_pagos() ) ) ) ) ){
							 
							 tableVig.append("<td class='moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ dec.format(Double.parseDouble((vig.getCredito_maximo()==null?"0":vig.getCredito_maximo())))+"</td>");
							 montoCred=Double.parseDouble((vig.getCredito_maximo()==null?"0":vig.getCredito_maximo()));
							 
						 }else{
							 tableVig.append("<td class='moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ dec.format(Double.parseDouble((vig.getLimite_credito()==null?"0":vig.getLimite_credito())))+"</td>");
							 montoCred=Double.parseDouble((vig.getLimite_credito()==null?"0":vig.getLimite_credito()));
						 }
						 
						 totalMontoCred += montoCred;
						 
						 Date fechaVencimiento = null;
						 
						 if(vig.getFecha_apertura() != null){
								 int dias = 0;
								 if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1) ){
									dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 15 ;
								 }
								 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
									dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 30 ;
								 }
								 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL") != (-1) ){
									 dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 14 ;
								 }
								 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL") != (-1) ){
									dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 7 ;
								 }
										 
								 Calendar fec1 = Calendar.getInstance();
								 fec1.setTime(fm3.parse(vig.getFecha_apertura()));
								 
								 fec1.add(Calendar.DATE, dias);
								 
								 //Se obtiene la fecha de vencimiento
								 if(dias>0){
									 fechaVencimiento = fec1.getTime();
								 
								 
									 if(fechaMaxGrafica == null){
										 fechaMaxGrafica = fechaVencimiento;
									 }
									 else{
										 if(fechaMaxGrafica.before(fechaVencimiento)){
											 fechaMaxGrafica = fechaVencimiento;
										 }
										 
									 }
									 
								 }
								 
								 
								 /*
								 if(htGral.isEmpty()){
									 htGral.put("1", fechaVencimiento);
									 System.out.println("Entro una vez");
								 }else{
									 if(htGral.get("1").before(fechaVencimiento)){
										 htGral.put("1", fechaVencimiento);
										 System.out.println("Ultima fecha0"+htGral.get("1"));
									 }
								 }
								 */
								 
						 }
						 
						 
						 Double montoMasInteres = 0D;
						 
						 Double montoTotalDeuda = 0D;
						 
						 if(fechaVencimiento != null){
							 
							 Calendar f1 = Calendar.getInstance();
							 f1.setTime(dFecConsult);
							 
							 Calendar f2 = Calendar.getInstance();
							 f2.setTime(fechaVencimiento);
							 
						
							 String[] strContFecMaxRep = new String[2];
							 strContFecMaxRep[0] = "";//Se reserva el espacio para ir guardando la fecha maxima de reporte mas abajo
							 //ultimo mop. Se reserva el espacio para guardarlo mas abajo.
							 strContFecMaxRep[1] = ""; 
							 
							 
							 //vig.get
							 String[] str = new String[2];
							 str[0] = formatStr.format(fechaVencimiento);
							 str[1] = ""; // ultimo mop. Se reserva el espacio para guardarlo mas abajo.
							 //arFecVen.add(str);
							 if(vig.getNo_credito() != null){
								 htFecVen.put((Integer.parseInt(vig.getNo_credito())+""), str);
								 htFecMaxReporte.put((Integer.parseInt(vig.getNo_credito())+""), strContFecMaxRep);
							 }
							 
							 if(f1.before(f2)){
								 
								 montoMasInteres = Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()) * Double.parseDouble( vig.getNumero_pagos() == null ? "0" : vig.getNumero_pagos() );
								 
							 }else{
								 montoMasInteres = Double.parseDouble(vig.getSaldo_actual() == null ? "0" : vig.getSaldo_actual());
							 }
							 
						 }
						 
						 montoTotalDeuda = Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()) * Double.parseDouble( vig.getNumero_pagos() == null ? "0" : vig.getNumero_pagos() );
						 
						 //Tasa
						 Double tasa = 0D;
						 Double r = 0D;
						 Rate rate = new Rate();
						 boolean flagtasa=false;
						 
						 if( vig.getMonto_a_pagar()==null || vig.getMonto_a_pagar().equals("0") || vig.getNumero_pagos()==null || vig.getNumero_pagos().equals("0") ){
							 
							 tableVig.append("<td class='clCenter' style='background-color: #e8c6c5; text-align: left !important; padding-right: 10px !important; ' >Revolvente</td>");
							 
						 }else{
						 
							 if( (vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")) || ( Double.parseDouble( vig.getLimite_credito() ) > ( Double.parseDouble( vig.getMonto_a_pagar() ) * Double.parseDouble( vig.getNumero_pagos() ) ) )){
								 
								 r =   rate.getRate(Integer.parseInt((vig.getNumero_pagos()==null?"0":vig.getNumero_pagos())), ((-1) * Double.parseDouble((vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()))), Double.parseDouble( ( vig.getCredito_maximo() == null ? "0" : vig.getCredito_maximo() )) );
								 
								 flagtasa = true;
								
							 }else{
								 
								 r =  rate.getRate(Integer.parseInt((vig.getNumero_pagos()==null?"0":vig.getNumero_pagos())), ((-1) * Double.parseDouble((vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()))), Double.parseDouble( ( vig.getLimite_credito() == null ? "0" : vig.getLimite_credito() )) );
								 
								 flagtasa = false;
								 
							 }
							 
							 Double f = 0D;
							 if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1) ){
								 
								 if(flagtasa){
								
									 f=24D;
								 
								 }else{
								
									 f =  52/2.15 ;
								 
								 }
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
								
								 if(flagtasa){
									 
									 f=12D;
									 
								 }else{
								 
									 f = (double)(52/4) ;
								 
								 }
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL")  != (-1)){
								 
								 if(flagtasa){
									 
									 f=26D;
								 
								 }else{
								 
									 f = (double)(52/2) ;
									 
								 }
								 
								 
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL") != (-1) ){
								
								 if(flagtasa){
									 
									 f=52D;
								 
								 }else{
								 
									 f = 52D;
									 
								 }
							 }
							 
							 tasa = r * f;

							 //System.out.println(tasa+" = "+r+" * "+ f);
							 
							 tasa = (double)Math.round(tasa*100)/100;
							
							 tasa = tasa * 100;
							 
							 if(tasa > 0){
								 tableVig.append("<td class='clCenter' style='background-color: #e8c6c5; text-align: right !important; padding-right: 10px !important; ' >"+ (double)Math.round(tasa) +"%</td>");
							 }else{
								 tableVig.append("<td class='clCenter' style='background-color: #e8c6c5; text-align: left !important; padding-left: 10px !important; ' >No Definida</td>");
							 }
							 
						 }
						 
						 Double tasaKubo = 0D;
						 
						 if(proyecto!=null && proyecto.getRate()!=null)
						 {
							 
							 tasaKubo = proyecto.getRate();
							 
							 tableVig.append("<td class='clCenter' style='background-color: #439539; text-align: right !important; padding-right: 10px !important; ' >"+ ((double)Math.round((tasaKubo * 1.16)*100))/100 +"%</td>");
							 
						 }else{
							 
							 if(infocalkubo.getTasa_acre() != null){
								 
								 tableVig.append("<td class='clCenter' style='background-color: #439539; text-align: left !important; padding-left: 10px !important; ' >"+(double)Math.round(Double.parseDouble( infocalkubo.getTasa_acre() ))+"</td>");
								 
							 }
							 
						 }
						 
						
						 
						 //pagoKubo
						 
						 if(vig.getNumero_pagos()!=null && !vig.getNumero_pagos().equals("0")&& vig.getNumero_pagos().trim().length()>0
								 && !vig.getOtorgante().equals("KUBO FINANCIERO")
								 ){
						 
							Simulator simulator 
						    = (Simulator) FacesContext.getCurrentInstance().getApplication()
						        .getELResolver().getValue(elContext, null, "simulator");
							
							int f = 0;
							if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1) ){
								f=3;
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
								 f=4;
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL")  != (-1)){
								 f=2;
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL") != (-1) ){
								
								f=1;
							 }
							
							
							simulator.setFrequency_id(f);
							
							simulator.generaNumCuotas();
							
							simulator.setAmmount(montoCred);
							simulator.setTerm_id(Integer.parseInt( vig.getNumero_pagos() ));
							
							simulator.setTasaTotal(tasaKubo);		
							
							//simulator.getCuotaByFormula();
							
							boolean flagRang = isTerminosValiodos(simulator.getAmmount(),simulator.getFrequency_id(),simulator.getTerm_id());
							
							String alert = "";
							
							if(!flagRang){
								alert = "<span style='color: #74101E; font-weight: bold;'>(!)</span>";
							}
							
							tableVig.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: right !important; padding-left: 20px !important; ' >"+alert+dec.format( simulator.getMontoCuota() )+"</td>");
							
							Double ahorro = montoTotalDeuda - simulator.getTotalPagar();
							
							String strColor = "";
							
							if( ahorro<0 ){
								
								strColor = "color: #FF0000;";
								
							}
							
							if(tasa > 0){
								tableVig.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: rigth !important; padding-left: 20px !important; "+strColor+"' >"+alert+dec.format(ahorro)+"</td>");
							}else{
								tableVig.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: left !important; padding-left: 20px !important;'  >No Aplica</td>");
							}
						 }else{
							 
							 tableVig.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: left !important; padding-left: 20px !important; ' >No aplica</td>");
							 tableVig.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: left !important; padding-left: 20px !important; ' >No aplica</td>");
							 
						 }
							
						 //fin pagoKubo
						 
						 tableVig.append("<td class='clCenter' >"+ (vig.getTipo_contrato()==null?"":vig.getTipo_contrato())+"</td>");
						 
						 tableVig.append("<td class='moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'  >"+ (vig.getMonto_mensual()==null?"":(dec.format(Double.parseDouble(vig.getMonto_mensual()))))+"</td>");
						 
						 //Interes que paga
						 Double interesQuePaga = 0D;
						 if( vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")){
							 if( (montoMasInteres - Double.parseDouble(vig.getCredito_maximo() == null ? "0" : vig.getCredito_maximo()))<0 ){
								 interesQuePaga = 0D;
							 }else{
								 if( vig.getCredito_maximo() == null || vig.getCredito_maximo().equals("0")){
									 interesQuePaga = 0D;
								 }else{
									 interesQuePaga = montoMasInteres - Double.parseDouble(vig.getCredito_maximo() == null ? "0" : vig.getCredito_maximo());
								 }
							 }
						 }else{
							 if(montoMasInteres - Double.parseDouble(vig.getLimite_credito() == null ? "0" : vig.getLimite_credito())<0){
								 interesQuePaga = 0D;
							 }else{
								 if( vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")){
									 interesQuePaga = 0D;
								 }else{
									 interesQuePaga = montoMasInteres - Double.parseDouble(vig.getLimite_credito() == null ? "0" : vig.getLimite_credito());
								 }
							 }
						 }
						 tableVig.append("<td class='moneyClass'  style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'  >"+ (dec.format(interesQuePaga)) +"</td>");
						 
						 tableVig.append("<td class='moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;' >"+ (dec.format(montoMasInteres)) +"</td>");
						 
						 
						 //pago minimo
						 if(vig.getSaldo_actual().equals("0")){
							 tableVig.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: left !important; padding-left: 20px !important; ' >No aplica</td>");
						 }else{
							 Double PagMin = 0D;
							 
							 PagMin = Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar())/Double.parseDouble(vig.getSaldo_actual() == null ? "0" : vig.getSaldo_actual());
							 PagMin = (double)Math.round(PagMin*100)/100;
							 
							 PagMin = PagMin * 100;
							 
							 tableVig.append("<td style='background-color: #fffe9d;  text-align: right !important; padding-right: 10px !important; ' >"+ (double)Math.round(PagMin) +"%</td>");
							 
						 }
						 
						 tableVig.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;' >"+ (vig.getCredito_maximo()==null?"":(dec.format(Double.parseDouble(vig.getCredito_maximo()))))+"</td>");
						 tableVig.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getSaldo_actual()==null?"":(dec.format(Double.parseDouble(vig.getSaldo_actual()))))+"</td>");
						 tableVig.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getLimite_credito()==null?"":(dec.format(Double.parseDouble(vig.getLimite_credito()))))+"</td>");
						 tableVig.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;' >"+ (vig.getSaldo_vencido()==null?"":(dec.format(Double.parseDouble(vig.getSaldo_vencido()))))+"</td>");
						  
						 if(vig.getSaldo_vencido() != null){
							 totalSaldoVencido += Double.parseDouble(vig.getSaldo_vencido());
						 }
						 
						 tableVig.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFecha_apertura()==null?"":(fm2.format(fm3.parse(vig.getFecha_apertura()))))+"</td>");
						 tableVig.append("<td class='clCenter' >"+ (vig.getTipo_responsabilidad()==null?"":vig.getTipo_responsabilidad())+"</td>");
						 tableVig.append("<td class='clCenter' >"+ (vig.getTipo_cuenta()==null?"":vig.getTipo_cuenta())+"</td>");
						 tableVig.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFec_ultimo_pago()==null?"":(fm2.format(fm3.parse(vig.getFec_ultimo_pago()))))+"</td>");
						 tableVig.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFec_ult_compra()==null?"":(fm2.format(fm3.parse(vig.getFec_ult_compra()))))+"</td>");
						 
						 tableVig.append("<td class='clCenter'  style='background-color: #c3d69b;' >"+ ((fechaVencimiento == null)?"Revisar":(fm2.format(fechaVencimiento)) ) +"</td>");
						 
						 tableVig.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFec_cierre()==null?"":(fm2.format(fm3.parse(vig.getFec_cierre()))))+"</td>");
						 tableVig.append("<td class='clCenter' >"+ (vig.getGarantia()==null?"":vig.getGarantia())+"</td>");
						
						 tableVig.append("<td style='text-align: left !important; padding-left: 20px !important;' >"+ (vig.getFecha_reporte()==null?"":(fm2.format(fm3.parse(vig.getFecha_reporte()))))+"</td>");
						 tableVig.append("<td style='text-align: left !important; padding-left: 10px !important;'  >"+ (vig.getHistorico_pagos()==null?"":vig.getHistorico_pagos())+"</td>");
						 tableVig.append("<td style='text-align: left !important; padding-left: 10px !important;'  >"+ (vig.getHistorico_pagos_kubo()==null?"":vig.getHistorico_pagos_kubo())+"</td>");
						 tableVig.append("<td style='text-align: left !important; padding-left: 5px !important;' >"+ (vig.getDesc_observacion()==null?"":vig.getDesc_observacion())+"</td>");
						 tableVig.append("<td class='clCenter' ></td>");
						 
					 tableVig.append("</tr>");
					 
					 subMontPagar += Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar());
					 subDeudaInteres += montoMasInteres;
					 subInteresPaga += interesQuePaga;
					 subSaldoActual += Double.parseDouble(vig.getSaldo_actual() == null ? "0" : vig.getSaldo_actual());
					 subLimCred += Double.parseDouble(vig.getLimite_credito() == null ? "0" : vig.getLimite_credito() );
					 
				 }else{
					 
					 /*************************************************************/
					 
					
					 IdentifiedCredit identifiedCredit = null;
					 /*
					 if( !sesion.getArea().toString().equals("I") ){
					 
						 if(vig.getNo_credito()!=null){
							 identifiedCredit = htIdentified.get(Integer.parseInt(vig.getNo_credito()));
						 } 
						 
						 if(identifiedCredit == null){
							 identifiedCredit  = getCreditIdentified(identify,vig);
							 
							 if(identifiedCredit != null){
								 //CREDITO IDENTIFICADO
								 htIdentified.put(Integer.parseInt(vig.getNo_credito()), identifiedCredit);
							 }
							 
						 }
						 
						 if(identifiedCredit != null){
							 
							 if( identifiedCredit!=null && identifiedCredit.getActual_entity()!=null && identifiedCredit.getActual_entity().trim().length()>0 ){
								 vig.setOtorgante(identifiedCredit.getActual_entity().toUpperCase());
							 }
							 
						 }
						 
					 } */
				 
					 
					 tableVigCom.append("<tr");
					 
					 if(sesion != null && sesion.getProspectus_id()!=null && sesion.getProspectus_id() > 0){
						 n++;
						 
						 if( sesion.getArea().toString().equals("M") )
						 {
							 tableVigCom.append(" class='clRowChng' onclick='changeNameCount(keyStr"+n+",valStr"+n+")' style='cursor: pointer;' ");
						 }
						 //arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
						 tableScriptJSon.append("var keyStr"+n+"='[{");
						 
								 if(identifiedCredit != null){
									 
									 tableScriptJSon.append("\"Company_id"		+"\":\""+		identifiedCredit.getPk().getCompany_id()			+"\",");
									 tableScriptJSon.append("\"Frequency"		+"\":\""+		identifiedCredit.getPk().getFrequency()				+"\",");
									 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		identifiedCredit.getPk().getOriginal_entity()		+"\",");
									 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		identifiedCredit.getPk().getProspectus_id()			+"\",");
									 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		identifiedCredit.getPk().getTotal_payments()		+"\",");
									 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( identifiedCredit.getPk().getStart_date() )+"\"");
								 	
								 }else{
									
									 tableScriptJSon.append("\"Company_id"		+"\":\""+		((proyecto!=null)? proyecto.getProyectloanPk().getCompany_id()	:1)				+"\",");
									 tableScriptJSon.append("\"Frequency"		+"\":\""+		vig.getFrecuencia_pagos()							+"\",");
									 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		vig.getOtorgante()									+"\",");
									 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		prospectus_id			+"\",");
									 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		vig.getNumero_pagos()								+"\",");
									 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( fm3.parse( vig.getFecha_apertura() ) )	+"\"");
									 
								 }
						 
								 tableScriptJSon.append("}]';");
						//FIN arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
						 
						//arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
								 tableScriptJSon.append("var valStr"+n+"='[{");
						 
								 if(identifiedCredit != null){
									 
									 tableScriptJSon.append("\"Entity"		+"\":\""+		identifiedCredit.getActual_entity().toUpperCase()				+"\",");
									 tableScriptJSon.append("\"Frequency"	+"\":\""+		identifiedCredit.getActual_frequency()			+"\",");
									 tableScriptJSon.append("\"Ammount"		+"\":\""+		identifiedCredit.getActual_total_ammount()		+"\",");
									 tableScriptJSon.append("\"Payment"		+"\":\""+		identifiedCredit.getActual_total_payment()		+"\"");
								 	
								 }else{
									
									 tableScriptJSon.append("\"Entity"		+"\":\""+		vig.getOtorgante().toUpperCase()								+"\",");
									 tableScriptJSon.append("\"Frequency"	+"\":\""+		vig.getFrecuencia_pagos()						+"\",");
									 tableScriptJSon.append("\"Ammount"		+"\":\""+		vig.getLimite_credito()							+"\",");
									 tableScriptJSon.append("\"Payment"		+"\":\""+		vig.getMonto_a_pagar()							+"\"");
									 
								 }
						 
								 tableScriptJSon.append("}]';");
						//FIN arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
						 
					 }
					 
					 tableVigCom.append(" >");
					 
					 
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getNo_credito()==null?"":vig.getNo_credito())+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getInd_circulo()==null?"BÚRO":(vig.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"</td>");
						 tableVigCom.append("<td class='clCenter clIdentified clIdentTD"+vig.getNo_credito()+"' ");
//						 if(identifiedCredit != null){
//							 tableVigCom.append(" style='font-weight: bold;' ");
//						 }
						 tableVigCom.append(">"+ (vig.getOtorgante()==null?"":vig.getOtorgante())+""
						 		+ "<input type='hidden' id='indentHidden"+vig.getNo_credito()+"' value='"+getStrForCreditIdentified(vig)+"' />"
						 		+ "</td>");
						
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getTipo_contrato()==null?"":vig.getTipo_contrato())+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getFrecuencia_pagos()==null?"":vig.getFrecuencia_pagos())+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getNumero_pagos()==null?"":vig.getNumero_pagos())+"</td>");
						 
						// MOP ACTUAL
						 	
						 tableVigCom.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getMop_actual()==null?"":(vig.getMop_actual()+"-")));
							
						 if(vig.getMop_actual()==null){
							 
							 tableVigCom.append("</td>");
							 
						 }else{
							 if(vig.getMop_actual().equals("UR")){
								 
								 tableVigCom.append("<Cuenta sin información.</td>");
							 
							 }else if(vig.getMop_actual().equals("00")){
								 
								 tableVigCom.append("Muy reciente para ser informada.</td>");
							 
							 }else if(vig.getMop_actual().equals("01")){
								 
								 tableVigCom.append("Cuenta al corriente.</td>");
							 
							 }else if(vig.getMop_actual().equals("02")){
								 
								 tableVigCom.append("Atraso de 01 a 29 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("03")){
								 
								 tableVigCom.append("Atraso de 30 a 59 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("04")){
								 
								 tableVigCom.append("Atraso de 60 a 89 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("05")){
								 
								 tableVigCom.append("Atraso de 90 a 119 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("06")){
								 
								 tableVigCom.append("Atraso de 120 a 149 días.</td>");
							 
							 }else if(vig.getMop_actual().equals("07")){
								 
								 tableVigCom.append("Atraso de 150 a 12 meses.</td>");
							 
							 }else if(vig.getMop_actual().equals("96")){
								 
								 tableVigCom.append("Atraso de 12 meses.</td>");
							 
							 }else if(vig.getMop_actual().equals("97")){
								 
								 tableVigCom.append("Cuenta con deuda parcial o total sin recuperar.</td>");
							 
							 }else if(vig.getMop_actual().equals("99")){
								 
								 tableVigCom.append("Fraude cometido por el Cliente.</td>");
							 }
						 }
						 
						 // FIN MOP ACTUAL
						 
						 tableVigCom.append("<td class='moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getMonto_a_pagar()==null?"":(dec.format(Double.parseDouble(vig.getMonto_a_pagar()))))+"</td>");
						 
						 Date fechaVencimiento = null;
						 
						 if(vig.getFecha_apertura() != null){
								 int dias = 0;
								 if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1)){
									dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 15 ;
								 }
								 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
									dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 30 ;
								 }
								 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL") != (-1)){
									 dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 14 ;
								 }
								 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL")  != (-1) ){
									dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 7 ;
								 }
										 
								 Calendar fec1 = Calendar.getInstance();
								 fec1.setTime(fm3.parse(vig.getFecha_apertura()));
								 
								 fec1.add(Calendar.DATE, dias);
								 
								 if(dias>0)
									 fechaVencimiento = fec1.getTime();
								 
						 }
						 
						 
						 Double montoMasInteres = 0D;
						 if(fechaVencimiento != null){
							 
							 Calendar f1 = Calendar.getInstance();
							 f1.setTime(dFecConsult);
							 
							 Calendar f2 = Calendar.getInstance();
							 f2.setTime(fechaVencimiento);
							 
							 if(f1.before(f2)){
								 
								 montoMasInteres = Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()) * Double.parseDouble( vig.getNumero_pagos() == null ? "0" : vig.getNumero_pagos() );
								 
							 }else{
								 montoMasInteres = Double.parseDouble(vig.getSaldo_actual() == null ? "0" : vig.getSaldo_actual());
							 }
							 
						 }
						 tableVigCom.append("<td class='moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;' >"+ (dec.format(montoMasInteres)) +"</td>");
						 
						 //Tasa
						 Double tasa = 0D;
						 Double r = 0D;
						 Rate rate = new Rate();
						 boolean flagtasa=false;
						 
						 if( vig.getMonto_a_pagar()==null || vig.getMonto_a_pagar().equals("0") || vig.getNumero_pagos()==null || vig.getNumero_pagos().equals("0") ){
							 
							 tableVigCom.append("<td class='clCenter' style='background-color: #e8c6c5; text-align: left !important; padding-right: 10px !important; ' >Revolvente</td>");
							 
						 }else{
						 
							 if( (vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")) || ( Double.parseDouble( vig.getLimite_credito() ) > ( Double.parseDouble( vig.getMonto_a_pagar() ) * Double.parseDouble( vig.getNumero_pagos() ) ) )){
								 
								 r =   rate.getRate(Integer.parseInt((vig.getNumero_pagos()==null?"0":vig.getNumero_pagos())), ((-1) * Double.parseDouble((vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()))), Double.parseDouble( ( vig.getCredito_maximo() == null ? "0" : vig.getCredito_maximo() )) );
								 
								 flagtasa = true;
								
							 }else{
								 
								 r =  rate.getRate(Integer.parseInt((vig.getNumero_pagos()==null?"0":vig.getNumero_pagos())), ((-1) * Double.parseDouble((vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar()))), Double.parseDouble( ( vig.getLimite_credito() == null ? "0" : vig.getLimite_credito() )) );
								 
								 flagtasa = false;
								 
							 }
							 
							 Double f = 0D;
							 if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1) ){
								 
								 if(flagtasa){
								
									 f=24D;
								 
								 }else{
								
									 f =  52/2.15 ;
								 
								 }
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
								
								 if(flagtasa){
									 
									 f=12D;
									 
								 }else{
								 
									 f = (double)(52/4) ;
								 
								 }
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL")  != (-1) ){
								 
								 if(flagtasa){
									 
									 f=26D;
								 
								 }else{
								 
									 f = (double)(52/2) ;
									 
								 }
								 
								 
							 }
							 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL")  != (-1)){
								
								 if(flagtasa){
									 
									 f=52D;
								 
								 }else{
								 
									 f = 52D;
									 
								 }
							 }
							 
							 tasa = r * f;
							 
							 tasa = (double)Math.round(tasa*100)/100;
							
							 tasa = tasa * 100;
							 
							 if(tasa > 0){
								 tableVigCom.append("<td class='clCenter' style='background-color: #e8c6c5; text-align: right !important; padding-right: 10px !important; ' >"+ (double)Math.round(tasa) +"%</td>");
							 }else{
								 tableVigCom.append("<td class='clCenter' style='background-color: #e8c6c5; text-align: left !important; padding-left: 10px !important; ' >No Definida</td>");
							 }
							 
						 }
						 //Interes que paga
						 Double interesQuePaga = 0D;
						 if( vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")){
							 if( (montoMasInteres - Double.parseDouble(vig.getCredito_maximo() == null ? "0" : vig.getCredito_maximo()))<0 ){
								 interesQuePaga = 0D;
							 }else{
								 if( vig.getCredito_maximo() == null || vig.getCredito_maximo().equals("0")){
									 interesQuePaga = 0D;
								 }else{
									 interesQuePaga = montoMasInteres - Double.parseDouble(vig.getCredito_maximo() == null ? "0" : vig.getCredito_maximo());
								 }
							 }
						 }else{
							 if(montoMasInteres - Double.parseDouble(vig.getLimite_credito() == null ? "0" : vig.getLimite_credito())<0){
								 interesQuePaga = 0D;
							 }else{
								 if( vig.getLimite_credito() == null || vig.getLimite_credito().equals("0")){
									 interesQuePaga = 0D;
								 }else{
									 interesQuePaga = montoMasInteres - Double.parseDouble(vig.getLimite_credito() == null ? "0" : vig.getLimite_credito());
								 }
							 }
						 }
						 tableVigCom.append("<td class='moneyClass'  style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'  >"+ (dec.format(interesQuePaga)) +"</td>");
						 
						 //pago minimo
						 if(vig.getSaldo_actual() != null && vig.getSaldo_actual().equals("0")){
							 tableVigCom.append("<td class='clCenter' style='background-color: #fffe9d;  text-align: left !important; padding-left: 20px !important; ' >No aplica</td>");
						 }else{
							 Double PagMin = 0D;
							 
							 PagMin = Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar())/Double.parseDouble(vig.getSaldo_actual() == null ? "0" : vig.getSaldo_actual());
							 PagMin = (double)Math.round(PagMin*100)/100;
							 
							 PagMin = PagMin * 100;
							 
							 tableVigCom.append("<td style='background-color: #fffe9d;  text-align: right !important; padding-right: 10px !important; ' >"+ (double)Math.round(PagMin) +"%</td>");
							 
						 }
						 tableVigCom.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getMonto_mensual()==null?"":(dec.format(Double.parseDouble(vig.getMonto_mensual()))))+"</td>");
						 
						 tableVigCom.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;' >"+ (vig.getCredito_maximo()==null?"":(dec.format(Double.parseDouble(vig.getCredito_maximo()))))+"</td>");
						 tableVigCom.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getSaldo_actual()==null?"":(dec.format(Double.parseDouble(vig.getSaldo_actual()))))+"</td>");
						 tableVigCom.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;'  >"+ (vig.getLimite_credito()==null?"":(dec.format(Double.parseDouble(vig.getLimite_credito()))))+"</td>");
						 tableVigCom.append("<td class=' moneyClass' style=' text-align: right !important; padding-right:10px !important;' >"+ (vig.getSaldo_vencido()==null?"":(dec.format(Double.parseDouble(vig.getSaldo_vencido()))))+"</td>");
						 
						 tableVigCom.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFecha_apertura()==null?"":(fm2.format(fm3.parse(vig.getFecha_apertura()))))+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getTipo_responsabilidad()==null?"":vig.getTipo_responsabilidad())+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getTipo_cuenta()==null?"":vig.getTipo_cuenta())+"</td>");
						 tableVigCom.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFec_ultimo_pago()==null?"":(fm2.format(fm3.parse(vig.getFec_ultimo_pago()))))+"</td>");
						 tableVigCom.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFec_ult_compra()==null?"":(fm2.format(fm3.parse(vig.getFec_ult_compra()))))+"</td>");
						 
						 tableVigCom.append("<td class='clCenter'  style='background-color: #c3d69b;' >"+ ((fechaVencimiento == null)?"Revisar":(fm2.format(fechaVencimiento)) ) +"</td>");
						 
						 tableVigCom.append("<td style='text-align:left !important; padding-left: 20px !important;' >"+ (vig.getFec_cierre()==null?"":(fm2.format(fm3.parse(vig.getFec_cierre()))))+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getGarantia()==null?"":vig.getGarantia())+"</td>");
						 
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getMop_actual()==null?"":vig.getMop_actual())+"</td>");
						 
						 tableVigCom.append("<td style='text-align: left !important; padding-left: 20px !important;' >"+ (vig.getFecha_reporte()==null?"":(fm2.format(fm3.parse(vig.getFecha_reporte()))))+"</td>");
						 tableVigCom.append("<td style='text-align: left !important; padding-left: 10px !important;'  >"+ (vig.getHistorico_pagos()==null?"":vig.getHistorico_pagos())+"</td>");
						 tableVigCom.append("<td style='text-align: left !important; padding-left: 10px !important;'  >"+ (vig.getHistorico_pagos_kubo()==null?"":vig.getHistorico_pagos_kubo())+"</td>");
						 tableVigCom.append("<td class='clCenter' >"+ (vig.getDesc_observacion()==null?"":vig.getDesc_observacion())+"</td>");
						 tableVigCom.append("<th></th>" );
						 tableVigCom.append("</tr>");
						 
						 subMontPagarCom += Double.parseDouble(vig.getMonto_a_pagar() == null ? "0" : vig.getMonto_a_pagar());
						 subDeudaInteresCom += montoMasInteres;
						 subInteresPagaCom += interesQuePaga;
						 subSaldoActualCom += Double.parseDouble(vig.getSaldo_actual() == null ? "0" : vig.getSaldo_actual());
						 subLimCredCom += Double.parseDouble(vig.getLimite_credito() == null ? "0" : vig.getLimite_credito() );
				
					 
					 /*************************************************************/
					 
				 }
				 
				 
				 
				 if( vig.getMop_actual()==null || vig.getMop_actual().equals("01") || vig.getMop_actual().equals("00") || vig.getMop_actual().equals("0") ){//al corriente
					 
					 numCorriente++;
					 
					 saldoCorriente += Double.parseDouble(vig.getSaldo_actual()!=null?vig.getSaldo_actual():"0");
					 montoAPagarCorriente += Double.parseDouble(vig.getMonto_mensual()!=null?vig.getMonto_mensual():"0");
					 
					 pagoPeriodicoCorriente += Double.parseDouble(vig.getMonto_a_pagar()!=null?vig.getMonto_a_pagar():"0");
					 saldoVencidoCorriente	+= Double.parseDouble(vig.getSaldo_vencido()!=null?vig.getSaldo_vencido():"0");
					 
					 if( maxCredCorriente < Double.parseDouble(vig.getCredito_maximo()==null?"0":vig.getCredito_maximo()) ){
						 
						 maxCredCorriente = Double.parseDouble(vig.getCredito_maximo()==null?"0":vig.getCredito_maximo());
						 
					 }
					 
					 if( LimiteMaxCorriente < Double.parseDouble(vig.getLimite_credito()==null?"0":vig.getLimite_credito()) ){
						 
						 LimiteMaxCorriente = Double.parseDouble(vig.getLimite_credito()==null?"0":vig.getLimite_credito());
						 
					 }
					 
					 
				 }else{//atrasados
					 
					 numAtraso++;
					 saldoAtraso += Double.parseDouble(vig.getSaldo_actual()!=null?vig.getSaldo_actual():"0");
					 montoAPagarAtraso += Double.parseDouble(vig.getMonto_mensual()!=null?vig.getMonto_mensual():"0");
					 
					 pagoPeriodicoAtrasado += Double.parseDouble(vig.getMonto_a_pagar()!=null?vig.getMonto_a_pagar():"0");
					 saldoVencidoAtrasado	+= Double.parseDouble(vig.getSaldo_vencido()!=null?vig.getSaldo_vencido():"0");
					 
					 if( maxCredAtraso < Double.parseDouble(vig.getCredito_maximo()==null?"0":vig.getCredito_maximo()) ){
						 
						 maxCredAtraso = Double.parseDouble(vig.getCredito_maximo()==null?"0":vig.getCredito_maximo());
						 
					 }
					 
					 if( LimiteMaxAtrasado < Double.parseDouble(vig.getLimite_credito()==null?"0":vig.getLimite_credito()) ){
						 
						 LimiteMaxAtrasado = Double.parseDouble(vig.getLimite_credito()==null?"0":vig.getLimite_credito());						 
					 }					 
				 } 
			 }
			 
			 //Totales
			 
				 tableVig.append("<tr>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 
					 tableVig.append("<td></td>");
					 tableVig.append("<td class='clCenter' style='background-color: #fffe9d;'><b>Subtotal</b></td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subMontPagar))+"</td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(totalMontoCred))+"</td>");
					 
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subInteresPaga))+"</td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subDeudaInteres))+"</td>");
					 tableVig.append("<td></td>");
					 
					 tableVig.append("<td></td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subSaldoActual))+"</td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subLimCred))+"</td>");
					 tableVig.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(totalSaldoVencido))+"</td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 
					 
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
					 tableVig.append("<td></td>");
				 tableVig.append("</tr>");
			 
			 
			 tableVig.append("</tbody>");
			 
			 
			 
			 tableVigCom.append("<tr>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td class='clCenter' style='background-color: #fffe9d;'><b>Subtotal</b></td>");
			 tableVigCom.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subMontPagarCom))+"</td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subDeudaInteresCom))+"</td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subInteresPagaCom))+"</td>");
			 tableVigCom.append("<td></td>");
			 
			 tableVigCom.append("<td class='clCenter' style='background-color: #fffe9d;'><b>Subtotal</b></td>");
			 tableVigCom.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subSaldoActualCom))+"</td>");
			 tableVigCom.append("<td class=' moneyClass' style='background-color: #fffe9d; text-align: right !important; padding-right:10px !important;'>"+(dec.format(subLimCredCom))+"</td>");
			 tableVigCom.append("<td></td>");
			 
			 tableVigCom.append("<td></td>");
			 
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
			 tableVigCom.append("<td></td>");
		 tableVigCom.append("</tr>");
	 
	 
	 tableVigCom.append("</tbody>");
			 
			 
			 //System.out.println(tableVig.toString());
			 tableVigData = tableVig.toString();
			 
			 tableVigDataCom = tableVigCom.toString();
			 
			 //System.out.println(" datos de la Table:  "+tableVigData);
			 
			 
			 
			 saldoCorrienteStr = dec.format( saldoCorriente );
			 montoAPagarCorrienteStr = dec.format(montoAPagarCorriente);
			 maxCredCorrienteStr = dec.format( maxCredCorriente );
			 pagoPeriodicoCorrienteStr = dec.format( pagoPeriodicoCorriente );
			 saldoVencidoCorrienteStr = dec.format( saldoVencidoCorriente );
			
			 saldoAtrasoStr = dec.format( saldoAtraso );
			 montoAPagarAtrasoStr = dec.format( montoAPagarAtraso );
			 maxCredAtrasoStr = dec.format( maxCredAtraso );
			 pagoPeriodicoAtrasadoStr = dec.format( pagoPeriodicoAtrasado );
			 saldoVencidoAtrasadoStr = dec.format( saldoVencidoAtrasado );
			 
			 
			 limiteMaxCorrienteStr = dec.format(LimiteMaxCorriente);
			 
			 limiteMaxAtrasadoStr = dec.format(LimiteMaxAtrasado);
			 
		 }else{
			 
			 tableVigData = "";
			 
			 tableVigDataCom = "";
			 
		 }
		 
		 Calendar cCrd_V_2 = Calendar.getInstance();
		 cCrd_V_2.setTime(new Date());
		 long difCrd_V_2 = cCrd_V_2.getTimeInMillis() - cCrd_V_1.getTimeInMillis();
		 //Systemout.println("Tiempo en cargar datos de CrdVigentes: "+difCrd_V_2+" milisegundos");
		 
	}
		
	protected String getCreditIdentifiedStr(List<IdentifiedCredit> identify)
	{
		SimpleDateFormat fm3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		
		String str = "";
		
		try{
			
		 if( identify != null && identify.size()>0  ){
			 
			 for( IdentifiedCredit ident : identify ){
				 
				 str += ident.getPk().getOriginal_entity()+"::"+ident.getPk().getFrequency()+"::"+ident.getPk().getTotal_payments()+"::"+ident.getPk().getProspectus_id();
						 
				 if( ident.getPk().getStart_date() != null  ){
					 str += "::"+ fm3.format( ident.getPk().getStart_date() );
				 }else{
					 str += "::--";
				 }
				 
				 str += "::"+ident.getActual_entity()+"##";
				 
			 }
		 }
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		 
		 return str;
		
	}
	
	protected String getStrForCreditIdentified(Object credit)
	{
		
		String str = "";
		
		SimpleDateFormat fm3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		
		try{
			
			Vtbur_infocredcte_vig credit_vig = null;
			Vtbur_infocredcte_c credit_cerr = null;
			Vtbur_infocredcte_m credit_cer_mas_seis  = null;
			
			if(credit instanceof Vtbur_infocredcte_vig)
				credit_vig = (Vtbur_infocredcte_vig)credit;
			else if (credit instanceof Vtbur_infocredcte_c)
				credit_cerr = (Vtbur_infocredcte_c)credit;
			else if( credit instanceof Vtbur_infocredcte_m)
				credit_cer_mas_seis = (Vtbur_infocredcte_m)credit;
			
		
			 if(credit_vig != null){
				 str = credit_vig.getOtorgante()+"::"+credit_vig.getFrecuencia_pagos()+"::"+credit_vig.getNumero_pagos()+"::"+credit_vig.getIdprospecto();
					 
				 if( credit_vig.getFecha_apertura() != null  ){
					 
					 String tmpDate1 = fm3.format( fm3.parse( credit_vig.getFecha_apertura() ) );
					 str += "::"+tmpDate1;
					 
				 }else{
					 
					 str += "::--";
					 
				 }
				
			 }
			 
			 if(credit_cerr != null){
				 str = credit_cerr.getOtorgante()+"::"+credit_cerr.getFrecuencia_pagos()+"::"+credit_cerr.getNumero_pagos()+"::"+credit_cerr.getIdprospecto();
					 
				 if( credit_cerr.getFecha_apertura() != null  ){
					 
					 String tmpDate1 = credit_cerr.getFecha_apertura();
					 str += "::"+tmpDate1;
					 
				 }else{
					 
					 str += "::--";
					 
				 }
			 }
			 
			 if(credit_cer_mas_seis != null){
				 
				 str = credit_cer_mas_seis.getOtorgante()+"::"+credit_cer_mas_seis.getFrecuencia_pagos()+"::"+credit_cer_mas_seis.getNumero_pagos()+"::"+credit_cer_mas_seis.getIdprospecto() ;
					 
				 if( credit_cer_mas_seis.getFecha_apertura() != null  ){
					 
					 String tmpDate1 = credit_cer_mas_seis.getFecha_apertura();
					 str += "::"+tmpDate1;
					 
				 }else{
					 
					 str += "::--";
					 
				 }
			 }
				 
			
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		 
		 return str;
		
	}
	
	protected String getRecommendationLabel( int recommendation_id )
	{
		String label = "";
		
		for( RecommendationType rec : lstRecommendationType )
		{			
			if( rec.getPk().getRecommendation_type_id() == recommendation_id ){
				
				label = rec.getName();
				break;
				
			}			
		}
		
		return label;
	}
	
	private boolean isTerminosValiodos(Double monto,Integer frequency_id,Integer plazo){
		
		String area = "L";
		String loanTypeID = null;
		String partnerID = null;
		
		SimulationConfig simulationConfig = new SimulationConfig();
		
		if(proyecto != null){
			loanTypeID=proyecto.getLoan_type();
			partnerID = proyecto.getProyect().getPartner_id();
		}
		
		if(loanTypeID != null && partnerID != null && area != null){
				
			simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandPartnerIDandArea(loanTypeID, partnerID, area, sesion.getCompany_id());
			
			if( simulationConfig == null ){
				
				simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID, area, sesion.getCompany_id());
				
			}
			
			if( simulationConfig == null ){
				
				simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandArea(loanTypeID, area, sesion.getCompany_id());
				
			}
			
		}else if(loanTypeID != null && area != null){
				
			simulationConfig = simulationConfigService.getSimulationByLoanTypeIDandArea(loanTypeID, area, sesion.getCompany_id());
				
		}else if(partnerID != null && area != null){
				
			simulationConfig = simulationConfigService.getSimulationByPartnerIDandArea(partnerID, area, sesion.getCompany_id());
			
		}else if(area != null){
				
			simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
			
		}else{
			simulationConfig = simulationConfigService.getSimulationByArea("L", 1);
		}
		
		if(simulationConfig == null){
		
			area = "L";
			if( sesion == null || sesion.getCompany_id() == null)
				simulationConfig = simulationConfigService.getSimulationByArea(area, 1);
			else
				simulationConfig = simulationConfigService.getSimulationByArea(area, sesion.getCompany_id());
			
			area = null;
			
		}
	   
		Integer termMax = 0;
		
		if(simulationConfig!=null){
			
			return (Float.parseFloat( monto+"" ) <= simulationConfig.getMax_amount() &&
					 Float.parseFloat( monto+"" ) >= simulationConfig.getMin_amount() &&
					 termMax <= simulationConfig.getMax_term()) ;
		}else{
			
			return (Float.parseFloat( monto+"" ) <= 50000 &&
					 Float.parseFloat( monto+"" ) >= 5000 &&
					 termMax <= 18) ;
			
		}
		
		//return flag;
	}
	
	protected void asignar_direcciones() 
	{
		 Calendar cDom_1 = Calendar.getInstance();
		 
		 cDom_1.setTime(new Date());
		
		 
		 if(infodircte != null && infodircte.length >0 )
		 {
			 
			 StringBuilder sbTableDom =  new StringBuilder();
			 sbTableDom.append(	"<thead>" +
							 		"<tr>" +
								 		"<th>Id</th>" +
								 		"<th>Dirección Linea 1 </th>" +
								 		"<th>Dirección Linea 2 </th>" +
								 		"<th>Colonia </th>" +	
								 		"<th>Delegación/Municipio </th>" +
								 		"<th>Ciudad</th>" +
								 		"<th>Estado</th>" +
								 		"<th>C.P.</th>" +
								 		"<th>Fecha Residencia</th>" +
								 		"<th>Teléfono</th>" +
								 		"<th>Extensión</th>" +
								 	"</tr>" +
								"</thead>" );
			 for( Vtbur_infodircte dircte : infodircte ){
				 
				 
				 sbTableDom.append(	"<tr>" +
									 		"<td>"+((dircte.getNo_dir() == null) ? "":dircte.getNo_dir()) +"</td>" +
									 		"<td>"+((dircte.getDireccion_linea1() == null )? "":dircte.getDireccion_linea1())+"</td>" +
									 		"<td>"+((dircte.getDireccion_linea2() == null )? "":dircte.getDireccion_linea2())+"</td>" +
									 		"<td>"+((dircte.getColonia() == null )? "":dircte.getColonia())+"</td>" +	
									 		"<td>"+((dircte.getDel_munic() == null )? "":dircte.getDel_munic())+"</td>" +
									 		"<td>"+((dircte.getCiudad() == null )? "":dircte.getCiudad())+"</td>" +
									 		"<td>"+((dircte.getEstado() == null )? "":dircte.getEstado())+"</td>" +
									 		"<td>"+((dircte.getCp() == null )? "":dircte.getCp())+"</td>" +
									 		"<td>"+((dircte.getFecha_recidencia() == null )? "":dircte.getFecha_recidencia())+"</td>" +
									 		"<td>"+((dircte.getTelefono() == null )? "":dircte.getTelefono())+"</td>" +
									 		"<td>"+((dircte.getExtension() == null )? "":dircte.getExtension())+"</td>" +
									 	"</tr>" );
			 }
			 
			 tableDomicilios = sbTableDom.toString();
			 
		 }
		 
		 Calendar cDom_2 = Calendar.getInstance();
		 cDom_2.setTime(new Date());
		 long difDom_2 = cDom_2.getTimeInMillis() - cDom_1.getTimeInMillis();
		 //Systemout.println("Tiempo en cargar datos de Domicilios: "+difDom_2+" milisegundos");		 
	}
	
	protected void asignar_creditos_cerrados_semestre_anterior() throws ParseException 
	{

		 //  *****       creditos cerrados       *****  //
		 
		 Calendar cCCr_R_1 = Calendar.getInstance();
		 
		 StringBuilder tableCerr =  new StringBuilder();
		 
		 tableCerr.append(	"<thead>" +
							 	"<tr>" +
							 		"<th>Crédito</th>" +
							 		"<th>SIC</th>" +
							 		"<th>Otorgante</th>" +
							 		"<th>Tipo Responsabilidad</th>" +
							 		"<th>Tipo Cuenta</th>" +
							 		"<th>Tipo Contrato</th>" +
							 		"<th>Frecuencia de Pagos</th>" +
							 		"<th>Número de Pagos</th>" +
							 		"<th>Mop Actual</th>" +
							 		"<th>Monto a Pagar</th>" +
							 		"<th>Fecha de Apertura</th>" +
							 		"<th>Fecha Último Pago</th>" +
							 		"<th>Fecha Última Compra</th>" +
							 		"<th>Fecha Cierre</th>" +
							 		"<th>Garantia</th>" +
							 		"<th>Crédito Máximo</th>" +
							 		"<th>Saldo Actual</th>" +
							 		"<th>Límite de Crédito</th>" +
							 		"<th>Saldo Vencido</th>" +
							 		
//											 		"<th>Descipción de Mop Actual</th> " +
							 		"<th>Fecha de Reporte</th>" +
							 		"<th>Histórico de Pagos</th>" +
							 		"<th>Histórico de Pagos Kubo</th>" +
							 		"<th>Observación</th>" +
							 		"<th></th>"+
							 	"</tr>" +
			 				"</thead>");

		 tableCerr.append("<tbody>");
		 
		 if(infocredcte_c != null)
		 {
			 
			 for(int i = 0; i< infocredcte_c.length; i++){
				 noActivas++;
				 
				 IdentifiedCredit identifiedCredit = null;
				 /*
				 if( !sesion.getArea().toString().equals("I") ){
				 
					 if( infocredcte_c[i].getNo_credito() != null ){
						 identifiedCredit =  htIdentified.get(Integer.parseInt(infocredcte_c[i].getNo_credito()));
					 }
					 
					 if(identifiedCredit == null){
						 identifiedCredit  = getCreditIdentified(identify,infocredcte_c[i]);
						 
						 if(identifiedCredit != null){
							 //CREDITO IDENTIFICADO
							 htIdentified.put(Integer.parseInt(infocredcte_c[i].getNo_credito()), identifiedCredit);
						 }
						 
					 }
					 
					 if(identifiedCredit != null){
						 
						 if( identifiedCredit!=null && identifiedCredit.getActual_entity()!=null && identifiedCredit.getActual_entity().trim().length()>0 ){
							 infocredcte_c[i].setOtorgante(identifiedCredit.getActual_entity().toUpperCase());
						 }
						 
					 }
					 
				 }
				 */
				 
				 tableCerr.append("<tr");
				 
				 if(sesion != null && sesion.getProspectus_id()!=null && sesion.getProspectus_id() > 0){
					 n++;
					 if( sesion.getArea().toString().equals("M") )
					 {
						 tableCerr.append(" class='clRowChng'  onclick='changeNameCount(keyStr"+n+",valStr"+n+")' style='cursor: pointer;' ");
					 }
						 
					 
					 //arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
					 tableScriptJSon.append("var keyStr"+n+"='[{");
					 
							 if(identifiedCredit != null){
								 
								 tableScriptJSon.append("\"Company_id"		+"\":\""+		identifiedCredit.getPk().getCompany_id()			+"\",");
								 tableScriptJSon.append("\"Frequency"		+"\":\""+		identifiedCredit.getPk().getFrequency()				+"\",");
								 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		identifiedCredit.getPk().getOriginal_entity()		+"\",");
								 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		identifiedCredit.getPk().getProspectus_id()			+"\",");
								 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		identifiedCredit.getPk().getTotal_payments()		+"\",");
								 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( identifiedCredit.getPk().getStart_date() )+"\"");
							 	
							 }else{
								
								 tableScriptJSon.append("\"Company_id"		+"\":\""+		((proyecto!=null)? proyecto.getProyectloanPk().getCompany_id()	:1)			+"\",");
								 tableScriptJSon.append("\"Frequency"		+"\":\""+		infocredcte_c[i].getFrecuencia_pagos()							+"\",");
								 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		infocredcte_c[i].getOtorgante()									+"\",");
								 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		prospectus_id			+"\",");
								 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		infocredcte_c[i].getNumero_pagos()								+"\",");
								 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( fm3.parse( infocredcte_c[i].getFecha_apertura() ) )	+"\"");
								 
							 }
					 
							 tableScriptJSon.append("}]';");
					//FIN arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
					 
					//arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
							 tableScriptJSon.append("var valStr"+n+"='[{");
					 
							 if(identifiedCredit != null){
								 
								 tableScriptJSon.append("\"Entity"		+"\":\""+		identifiedCredit.getActual_entity().toUpperCase()				+"\",");
								 tableScriptJSon.append("\"Frequency"	+"\":\""+		identifiedCredit.getActual_frequency()			+"\",");
								 tableScriptJSon.append("\"Ammount"		+"\":\""+		identifiedCredit.getActual_total_ammount()		+"\",");
								 tableScriptJSon.append("\"Payment"		+"\":\""+		identifiedCredit.getActual_total_payment()		+"\"");
							 	
							 }else{
								
								 tableScriptJSon.append("\"Entity"		+"\":\""+		((infocredcte_c[i].getOtorgante()==null)?"":infocredcte_c[i].getOtorgante().toUpperCase() )								+"\",");
								 tableScriptJSon.append("\"Frequency"	+"\":\""+		infocredcte_c[i].getFrecuencia_pagos()						+"\",");
								 tableScriptJSon.append("\"Ammount"		+"\":\""+		infocredcte_c[i].getLimite_credito()							+"\",");
								 tableScriptJSon.append("\"Payment"		+"\":\""+		infocredcte_c[i].getMonto_a_pagar()							+"\"");
								 
							 }
					 
							 tableScriptJSon.append("}]';");
					//FIN arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
					 
				 }
				 
				 tableCerr.append(" >");
				 
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+((infocredcte_c[i].getNo_credito()==null)?"":(infocredcte_c[i].getNo_credito()))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+ (infocredcte_c[i].getInd_circulo()==null?"BÚRO":(infocredcte_c[i].getInd_circulo().equals("1")?"CIRCULO":"Buró")) +"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;' class='clIdentified clIdentTD"+infocredcte_c[i].getNo_credito()+"' ");

					 tableCerr.append(">"+ (infocredcte_c[i].getOtorgante()==null?"":infocredcte_c[i].getOtorgante())+""
						 		+ "<input type='hidden' id='indentHidden"+infocredcte_c[i].getNo_credito()+"'  value='"+getStrForCreditIdentified(infocredcte_c[i])+"' />"
						 		+ "</td>");
						 
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getTipo_responsabilidad()==null?"":infocredcte_c[i].getTipo_responsabilidad())+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getTipo_cuenta()==null?"":infocredcte_c[i].getTipo_cuenta())+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getTipo_contrato()==null?"":infocredcte_c[i].getTipo_contrato())+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getFrecuencia_pagos()==null?"":infocredcte_c[i].getFrecuencia_pagos())+"</td>");
					 tableCerr.append("<td class='clCenter'  style='text-align:center !important;' >"+(infocredcte_c[i].getNumero_pagos()+"</td>"));
					 
//									 tableCerr.append("<td style='text-align:center !important;'>"++"</td>"));
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getMop_actual()==null?"":infocredcte_c[i].getMop_actual())+"-"+(infocredcte_c[i].getDesc_mop_actual()==null?"":infocredcte_c[i].getDesc_mop_actual())+"</td>");
					 
					 tableCerr.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_c[i].getMonto_a_pagar()==null?"0":infocredcte_c[i].getMonto_a_pagar())))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getFecha_apertura()==null?"":(fm2.format(fm3.parse(infocredcte_c[i].getFecha_apertura()))))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getFec_ultimo_pago()==null?"":(fm2.format(fm3.parse(infocredcte_c[i].getFec_ultimo_pago()))))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getFec_ult_compra()==null?"":(fm2.format(fm3.parse(infocredcte_c[i].getFec_ult_compra()))))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getFec_cierre()==null?"":(fm2.format(fm3.parse(infocredcte_c[i].getFec_cierre()))))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+infocredcte_c[i].getGarantia()+"</td>");
					 tableCerr.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_c[i].getCredito_maximo()==null?"0":infocredcte_c[i].getCredito_maximo())))+"</td>");
					 tableCerr.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_c[i].getSaldo_actual()==null?"0":infocredcte_c[i].getSaldo_actual())))+"</td>");
					 tableCerr.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_c[i].getLimite_credito()==null?"0":infocredcte_c[i].getLimite_credito())))+"</td>");
					 tableCerr.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_c[i].getSaldo_vencido()==null?"0":infocredcte_c[i].getSaldo_vencido())))+"</td>");
					 
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getFecha_reporte()==null?"":(fm2.format(fm3.parse(infocredcte_c[i].getFecha_reporte()))))+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getHistorico_pagos()==null?"":infocredcte_c[i].getHistorico_pagos())+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getHistorico_pagos_kubo()==null?"":infocredcte_c[i].getHistorico_pagos_kubo())+"</td>");
					 tableCerr.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_c[i].getDesc_observacion()==null?"":infocredcte_c[i].getDesc_observacion())+"</td>");
					 tableCerr.append("<td></td>");
				 tableCerr.append("</tr>");
				 
			 }
			 
		 }
		 
		 
		 tableCerr.append("</tbody>");
		 
		 tableCredCerr = tableCerr.toString();
		 
		 Calendar cCCr_R_2 = Calendar.getInstance();
		 cCCr_R_2.setTime(new Date());
		 long difCCr_R_2 = cCCr_R_2.getTimeInMillis() - cCCr_R_1.getTimeInMillis();
		 //Systemout.println("Tiempo en cargar datos de CrdCerr_Recientemente: "+difCCr_R_2+" milisegundos");		 		 
	}
	
	protected void asignar_creditos_cerrados_semestre_posterior() throws ParseException 
	{
		 // *****     CREDITOS CERRADOS MAS DE 6 MESES     ******  //
		 
		 Calendar cCCM6_1 = Calendar.getInstance();
		 
		 cCCM6_1.setTime( new Date() );
		 
		 StringBuilder tableCerr_M_6 =  new StringBuilder();
		 
		 tableCerr_M_6.append(	"<thead>" +
							 	"<tr>" +
							 		"<th>Crédito</th>" +
							 		"<th>SIC</th>" +
							 		"<th>Otorgante</th>" +
							 		"<th>Tipo Responsabilidad</th>" +
							 		"<th>Tipo Cuenta</th>" +
							 		"<th>Tipo Contrato</th>" +
							 		"<th>Frecuencia de Pagos</th>" +
							 		"<th>Número de Pagos</th>" +
							 		"<th>Mop Actual</th>" +
							 		"<th>Monto a Pagar</th>" +
							 		"<th>Fecha de Apertura</th>" +
							 		"<th>Fecha Último Pago</th>" +
							 		"<th>Fecha Última Compra</th>" +
							 		"<th>Fecha Cierre</th>" +
							 		"<th>Garantia</th>" +
							 		"<th>Crédito Máximo</th>" +
							 		"<th>Saldo Actual</th>" +
							 		"<th>Límite de Crédito</th>" +
							 		"<th>Saldo Vencido</th>" +
							 		"<th>Fecha de Reporte</th>" +
							 		"<th>Histórico de Pagos</th>" +
							 		"<th>Histórico de Pagos Kubo</th>" +
							 		"<th>Observación</th>" +
							 		"<th></th>" +
							 	"</tr>" +
			 				"</thead>");

		 tableCerr_M_6.append("<tbody>");
		 
		 if(infocredcte_m != null ){
		 
				 for(int i = 0; i< infocredcte_m.length; i++){
					 
					 if(infocredcte_m[i] == null )
					 	 continue;
					 
					 noActivas++;
					 
					 IdentifiedCredit identifiedCredit = null;
					 /*
					 if( !sesion.getArea().toString().equals("I") ){
					 
						 if(infocredcte_m[i].getNo_credito() != null && infocredcte_m[i].getNo_credito().trim().length()>0){
							 identifiedCredit =  htIdentified.get(Integer.parseInt(infocredcte_m[i].getNo_credito()));
						 }	
						 
						 if(identifiedCredit == null){
							 identifiedCredit  = getCreditIdentified(identify,infocredcte_m[i]);
							 
							 if(identifiedCredit != null){
								 //CREDITO IDENTIFICADO
								 htIdentified.put(Integer.parseInt(infocredcte_m[i].getNo_credito()), identifiedCredit);
							 }
							 
						 }else {
							 
							 if( identifiedCredit!=null && identifiedCredit.getActual_entity()!=null && identifiedCredit.getActual_entity().trim().length()>0 ){
								 infocredcte_m[i].setOtorgante(identifiedCredit.getActual_entity().toUpperCase());
							 }
							 
						 }
					 
					 } */
					 
					 tableCerr_M_6.append("<tr");
					 
					 if(sesion != null && sesion.getProspectus_id()!=null && sesion.getProspectus_id() > 0){
						 n++;
						 
						 if( sesion.getArea().toString().equals("M") )
						 {
							 tableCerr_M_6.append(" class='clRowChng' onclick='changeNameCount(keyStr"+n+",valStr"+n+")' style='cursor: pointer;' ");
						 }
						 
						 //arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
						 tableScriptJSon.append("var keyStr"+n+"='[{");
						 
								 if(identifiedCredit != null){
									 
									 tableScriptJSon.append("\"Company_id"		+"\":\""+		identifiedCredit.getPk().getCompany_id()			+"\",");
									 tableScriptJSon.append("\"Frequency"		+"\":\""+		identifiedCredit.getPk().getFrequency()				+"\",");
									 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		identifiedCredit.getPk().getOriginal_entity()		+"\",");
									 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		identifiedCredit.getPk().getProspectus_id()			+"\",");
									 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		identifiedCredit.getPk().getTotal_payments()		+"\",");
									 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( identifiedCredit.getPk().getStart_date() )+"\"");
								 	
								 }else{
									
									 tableScriptJSon.append("\"Company_id"		+"\":\""+		((proyecto!=null)? proyecto.getProyectloanPk().getCompany_id()	:1)				+"\",");
									 tableScriptJSon.append("\"Frequency"		+"\":\""+		infocredcte_m[i].getFrecuencia_pagos()							+"\",");
									 tableScriptJSon.append("\"OriginalEntity"	+"\":\""+		infocredcte_m[i].getOtorgante()									+"\",");
									 tableScriptJSon.append("\"Prospectus_id"	+"\":\""+		prospectus_id			+"\",");
									 tableScriptJSon.append("\"Totalpayment"	+"\":\""+		infocredcte_m[i].getNumero_pagos()								+"\",");
									 tableScriptJSon.append("\"StartDate"		+"\":\""+		fm.format( fm3.parse( infocredcte_m[i].getFecha_apertura() ) )	+"\"");
									 
								 }
						 
								 tableScriptJSon.append("}]';");
						//FIN arreglo con la llave que nos va a servir para identificar el credito dentro de la tabla 'gn_identified_credit'
						 
						//arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
								 tableScriptJSon.append("var valStr"+n+"='[{");
						 
								 if(identifiedCredit != null){
									 
									 tableScriptJSon.append("\"Entity"		+"\":\""+		identifiedCredit.getActual_entity().toUpperCase()				+"\",");
									 tableScriptJSon.append("\"Frequency"	+"\":\""+		identifiedCredit.getActual_frequency()			+"\",");
									 tableScriptJSon.append("\"Ammount"		+"\":\""+		identifiedCredit.getActual_total_ammount()		+"\",");
									 tableScriptJSon.append("\"Payment"		+"\":\""+		identifiedCredit.getActual_total_payment()		+"\"");
								 }else{
									if(infocredcte_m[i]!= null ){
										 tableScriptJSon.append("\"Entity"		+"\":\""+		( ( infocredcte_m[i].getOtorgante() == null )?"":infocredcte_m[i].getOtorgante().toUpperCase()	)							+"\",");
										 tableScriptJSon.append("\"Frequency"	+"\":\""+		infocredcte_m[i].getFrecuencia_pagos()						+"\",");
										 tableScriptJSon.append("\"Ammount"		+"\":\""+		infocredcte_m[i].getLimite_credito()							+"\",");
										 tableScriptJSon.append("\"Payment"		+"\":\""+		infocredcte_m[i].getMonto_a_pagar()							+"\"");
									}
									 
								 }
						 
								 tableScriptJSon.append("}]';");
						//FIN arreglo con los campos que lleva para mostrar dentro de la pantallita de cambio de información
						 
					 }
					 
					 tableCerr_M_6.append(" >");
					 
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+infocredcte_m[i].getNo_credito()+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+ (infocredcte_m[i].getInd_circulo()==null?"BÚRO":(infocredcte_m[i].getInd_circulo().equals("1")?"CIRCULO":"Buró"))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;' class='clIdentified clIdentTD"+infocredcte_m[i].getNo_credito()+"' ");

						 tableCerr_M_6.append(">"+ (infocredcte_m[i].getOtorgante()==null?"":infocredcte_m[i].getOtorgante())+""
						 		+ "<input type='hidden' id='indentHidden"+infocredcte_m[i].getNo_credito()+"' value='"+getStrForCreditIdentified(infocredcte_m[i])+"' />"
						 		+ "</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getTipo_responsabilidad()==null?"":infocredcte_m[i].getTipo_responsabilidad())+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getTipo_cuenta()==null?"":infocredcte_m[i].getTipo_cuenta())+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getTipo_contrato()==null?"":infocredcte_m[i].getTipo_contrato())+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getFrecuencia_pagos()==null?"":infocredcte_m[i].getFrecuencia_pagos())+"</td>");
						 tableCerr_M_6.append("<td class='clCenter'  style='text-align:center !important;' >"+(infocredcte_m[i].getNumero_pagos()+"</td>"));
						 
//										 tableCerr_M_6.append("<td style='text-align:center !important;'>"+"</td>"));
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getMop_actual()==null?"":infocredcte_m[i].getMop_actual())+"-"+(infocredcte_m[i].getDesc_mop_actual()==null?"":infocredcte_m[i].getDesc_mop_actual()+"</td>"));
						 
						 
						 tableCerr_M_6.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_m[i].getMonto_a_pagar()==null?"0":infocredcte_m[i].getMonto_a_pagar())))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getFecha_apertura()==null?"":(fm2.format(fm3.parse(infocredcte_m[i].getFecha_apertura()))))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getFec_ultimo_pago()==null?"":(fm2.format(fm3.parse(infocredcte_m[i].getFec_ultimo_pago()))))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getFec_ult_compra()==null?"":(fm2.format(fm3.parse(infocredcte_m[i].getFec_ult_compra()))))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getFec_cierre()==null?"":(fm2.format(fm3.parse(infocredcte_m[i].getFec_cierre()))))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+infocredcte_m[i].getGarantia()+"</td>");
						 tableCerr_M_6.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_m[i].getCredito_maximo()==null?"0":infocredcte_m[i].getCredito_maximo())))+"</td>");
						 tableCerr_M_6.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_m[i].getSaldo_actual()==null?"0":infocredcte_m[i].getSaldo_actual())))+"</td>");
						 tableCerr_M_6.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_m[i].getLimite_credito()==null?"0":infocredcte_m[i].getLimite_credito())))+"</td>");
						 tableCerr_M_6.append("<td class=' moneyClass'  style=' text-align: right !important; padding-right:10px !important;'>"+(dec.format(Double.parseDouble(infocredcte_m[i].getSaldo_vencido()==null?"0":infocredcte_m[i].getSaldo_vencido())))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getFecha_reporte()==null?"":(fm2.format(fm3.parse(infocredcte_m[i].getFecha_reporte()))))+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getHistorico_pagos()==null?"":infocredcte_m[i].getHistorico_pagos())+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getHistorico_pagos_kubo()==null?"":infocredcte_m[i].getHistorico_pagos_kubo())+"</td>");
						 tableCerr_M_6.append("<td style='text-align:left !important; padding-left: 20px !important;'>"+(infocredcte_m[i].getDesc_observacion()==null?"":infocredcte_m[i].getDesc_observacion())+"</td>");
						 tableCerr_M_6.append("<td></td>");
					tableCerr_M_6.append("</tr>");
					 
				 }
		 
		 }
		 tableCerr_M_6.append("</tbody>");
		 
		 tableCredCerr_M_6 = tableCerr_M_6.toString();
		 
		 Calendar cCCr6_2 = Calendar.getInstance();
		 cCCr6_2.setTime(new Date());
		 long difCCr6_2 = cCCr6_2.getTimeInMillis() - cCCM6_1.getTimeInMillis();
		 //Systemout.println("Tiempo en cargar datos de CrdCerrMas6_me: "+difCCr6_2+" milisegundos");		 		 
	}
	
	protected void asignar_grafica() throws NumberFormatException, ParseException 
	{
		 //COMIENZA GRAFICA
		 
		 Calendar cGrf = Calendar.getInstance();
		 
		 cGrf.setTime( new Date() );
		 
		 if(grs != null)
		 {
			 
				Hashtable<String,Hashtable<Date,String>> htCuenta= new Hashtable<String,Hashtable<Date,String>>();
				
				SortedSet<Date> meses = new TreeSet<Date>();
				ArrayList<String> cuentas = new ArrayList<String>();
				
				Date mesMax = fm.parse("01/01/1990");
				
				Date mesMin = fm.parse("01/01/2013");
				
				StringBuffer cuentaStr = new  StringBuffer();
				cuentaStr.append(" rowData.addColumn('string', 'Mes'); \n ");
				StringBuffer mesesStr = new  StringBuffer();
				
				Hashtable<String, String> htTemp = new Hashtable<String, String>();
				//Se recorre los datos de la grafica para obtener todos sus datos(Meses a graficar...)
				for( int i = 0; i<grs.length; i++){
					
					BurGraphic gr = grs[i];
					
					
					
					//Identificar Creditos
					
					if( gr != null ){
					
					String strcount = gr.getCuenta();
					
					 IdentifiedCredit identifiedCredit = null;
					 
					 if( !sesion.getArea().toString().equals("I") ){
						 identifiedCredit = htIdentified.get(Integer.parseInt(strcount.split("-")[1]));
					 }
					 
					 if(identifiedCredit != null){
						 
						 String name = "";
						 
						 name = strcount.split("-")[0]+"-"
							 		+strcount.split("-")[1]+"-"
							 		+strcount.split("-")[2]+"-"
							 		+identifiedCredit.getActual_entity().toUpperCase();
						 
						 if((strcount.split("-")).length >4 ){
							 name += "-"+strcount.split("-")[4]+"";
						 }
						 
						 gr.setCuenta( name );
						 
					 }
					
					
					
					//Fin Identificar Creditos
					
					Hashtable<Date,String> ht = htCuenta.get(gr.getCuenta());
					
					if( ht != null ){
//										if((gr.getCuenta().indexOf("A-01-")) != -1){
//											System.out.println("Prueba de ordenamiento:"+gr.getMes()+"-"+gr.getMopMesGraficar());
//										}
						if(Float.parseFloat(gr.getMopMesGraficar()) < 1){//validacion para replicar el ultimo mop valido
							//Si el mop es menor a uno no es valido, y se toma el ultimo valor
							//valido almacenado en htTemp
							gr.setMopMesGraficar(htTemp.get(gr.getCuenta()));
						}
						//Almacena de nuevo el ultimo mop valido
						htTemp.put(gr.getCuenta(), gr.getMopMesGraficar());
						
						ht.put(fm.parse(gr.getMes()), gr.getMopMesGraficar()+"::"+ gr.getLimiteCredito()+"::"+gr.getCreditoMaximo()+"::"+gr.getSaldoactual()+"::"+(gr.getInd_circulo()==null?"BÚRO":(gr.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"::"+gr.getFrecuenciaPagos());
						
						
						//**
						String[] strCuenta = gr.getCuenta().split("-");
						String strNumCuenta = "";
						if(strCuenta != null && strCuenta.length > 1){
							strNumCuenta = strCuenta[1];//En la posicion 1 esta el numero de cuenta
							
							//Se obtiene el String para guardar la fecha maxima de reporte y el ultimo mop valido [fecReporte, Reservado]
							//Se parceo a entero por que en el nombre de la cuenta viene (02 y en htFecMaxReporte es 2)
							String[] str1FecMaxReporte = htFecMaxReporte.get(Integer.parseInt(strNumCuenta)+"");
							
							if(str1FecMaxReporte != null && str1FecMaxReporte.length > 1){
								//Se obtiene la frecuencia que se guardo temporalmentes
								
								//Se guarda el mes de reporte
								str1FecMaxReporte[0] = gr.getMes();
								//Se guarda el mop
								str1FecMaxReporte[1] = gr.getMopMesGraficar()+"::"+ gr.getLimiteCredito()+"::"+gr.getCreditoMaximo()+"::"+gr.getSaldoactual()+"::"+(gr.getInd_circulo()==null?"BÚRO":(gr.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"::"+gr.getFrecuenciaPagos();
								
								//Guardas el string pero ya con la fecha de reporte y el mop seteado.
								//Teniendo ya completo htFecMaxReporte[NoCredito,String[fecReporte, mop]]
								htFecMaxReporte.put(strNumCuenta,str1FecMaxReporte);
							}
							
						}
						
						//**
						
						
						String[] str1 = gr.getCuenta().split("-");
						String cuentaStr01 = "";
						if(str1 != null && str1.length > 1){
							cuentaStr01 = str1[1];//En la posicion 1 esta el numero de cuenta
							
							//Se obtiene el String en el que esta la fec. de vencimiento [fecVen, Reservado]
							//Se parceo a entero por que en el nombre de la cuenta viene (02 y en htFecVen es 2)
							String[] s1 = htFecVen.get(Integer.parseInt(cuentaStr01)+"");
							
							if(s1 != null && s1.length > 1){
								
								//Se guarda el valor para el mop que se reservo al momento de guardar la 
								//fec. de vencimiento el resto de valores (limCred, credMax, salActual) no se usan en este Hashtable
								s1[1] = gr.getMopMesGraficar()+"::"+ gr.getLimiteCredito()+"::"+gr.getCreditoMaximo()+"::"+gr.getSaldoactual()+"::"+(gr.getInd_circulo()==null?"BÚRO":(gr.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"::"+gr.getFrecuenciaPagos();
								//Guardas el string pero ya con el mop seteado.
								//Teniendo ya completo htFecVen[NoCredito,String[fecVencimiento, mop:limCredito:credMax]]
								htFecVen.put(cuentaStr01,s1);
							}
							
						}
						
					}else{
//										if((gr.getCuenta().indexOf("A-01-")) != -1){
//											System.out.println("Prueba de ordenamiento:"+gr.getMes()+"-"+gr.getMopMesGraficar());
//										}
						//Almacena el primer MOP que entra para tener siempre el ultimo mop valido
						//Prueba de ordenamiento:01/01/2011-9.1
						//Prueba de ordenamiento:01/02/2011-9.1
						//Prueba de ordenamiento:01/03/2011-9.1
						htTemp.put(gr.getCuenta(), gr.getMopMesGraficar());
						Hashtable<Date,String> ht2 = new  Hashtable<Date,String>();
						ht2.put(fm.parse(gr.getMes()), gr.getMopMesGraficar()+"::"+gr.getLimiteCredito()+"::"+gr.getCreditoMaximo()+"::"+gr.getSaldoactual()+"::"+(gr.getInd_circulo()==null?"BÚRO":(gr.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"::"+gr.getFrecuenciaPagos());
						htCuenta.put(gr.getCuenta(), ht2);
						
						//**
						String[] strCuenta = gr.getCuenta().split("-");
						String strNumCuenta = "";
						if(strCuenta != null && strCuenta.length > 1){
							strNumCuenta = strCuenta[1];//En la posicion 1 esta el numero de cuenta
							
							//Se obtiene el String para guardar la fecha maxima de reporte y el ultimo mop valido [fecReporte, Reservado]
							//Se parceo a entero por que en el nombre de la cuenta viene (02 y en htFecMaxReporte es 2)
							String[] str1FecMaxReporte = htFecMaxReporte.get(Integer.parseInt(strNumCuenta)+"");
							
							if(str1FecMaxReporte != null && str1FecMaxReporte.length > 1){
								//Se obtiene la frecuencia que se guardo temporalmentes
								
								//Se guarda el mes de reporte
								str1FecMaxReporte[0] = gr.getMes();
								//Se guarda el mop
								str1FecMaxReporte[1] = gr.getMopMesGraficar()+"::"+ gr.getLimiteCredito()+"::"+gr.getCreditoMaximo()+"::"+gr.getSaldoactual()+"::"+(gr.getInd_circulo()==null?"BÚRO":(gr.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"::"+gr.getFrecuenciaPagos();
								
								//Guardas el string pero ya con la fecha de reporte y el mop seteado.
								//Teniendo ya completo htFecMaxReporte[NoCredito,String[fecReporte, mop]]
								htFecMaxReporte.put(strNumCuenta,str1FecMaxReporte);
							}
							
						}
						
						//**
						
						String[] str1 = gr.getCuenta().split("-");
						String cuentaStr01 = "";
						if(str1 != null && str1.length > 1){
							cuentaStr01 = str1[1];
							
							//Se obtiene el String en el que esta la fec. de vencimiento [fecVen, Reservado]
							//Se parceo a entero por que en el nombre de la cuenta viene (02 y en htFecVen es 2)
							String[] s1 = htFecVen.get(Integer.parseInt(cuentaStr01)+"");
							
							if(s1 != null && s1.length > 1){
								//Se guarda el valor para el mop que se reservo al momento de guardar la 
								//fec. de vencimiento 
								s1[1] = gr.getMopMesGraficar()+"::"+ gr.getLimiteCredito()+"::"+gr.getCreditoMaximo()+"::"+gr.getSaldoactual()+"::"+(gr.getInd_circulo()==null?"BÚRO":(gr.getInd_circulo().equals("1")?"CIRCULO":"BÚRO"))+"::"+gr.getFrecuenciaPagos();
								//Guardas el string pero ya con el mop seteado.
								//Teniendo ya completo htFecVen[NoCredito,String[fecVencimiento, mop:limCredito:credMax]]
								htFecVen.put(cuentaStr01,s1);
							}
							
						}
						
					}
					
					
						Date tmpDate = fm.parse(gr.getMes());
						//Obtiene el mes maximo reportado de todos los creditos.
						if (tmpDate.after(mesMax)){
							mesMax = tmpDate;
						}
						
						if (tmpDate.before(mesMin)){
							mesMin = tmpDate;
						}
						
						meses.add(tmpDate);
						
						if(!cuentas.contains(gr.getCuenta())){
							cuentas.add(gr.getCuenta());
						}
						
						if (maxGridD < Double.parseDouble(gr.getMopMesGraficar())){
							maxGridD = Double.parseDouble(gr.getMopMesGraficar());
						}
						
					}//fin de valida si gr != null
						
				}
				//'MOP 2 LINEA DE CONTROL','MOP 3 LINEA DE CONTROL','MOP 4 LINEA DE CONTROL']
				if(!cuentas.contains("MOP 2 LINEA DE CONTROL")){
					cuentas.add("MOP 2 LINEA DE CONTROL");
//					hasMop2 = false;
				}else{
//					hasMop2 = true;
				}
				if(!cuentas.contains("MOP 3 LINEA DE CONTROL")){
					cuentas.add("MOP 3 LINEA DE CONTROL");
//					hasMop3 = false;
				}else{
//					hasMop3 = true;
				}
				if(!cuentas.contains("MOP 4 LINEA DE CONTROL")){
					cuentas.add("MOP 4 LINEA DE CONTROL");
//					hasMop4 = false;
				}else{
//					hasMop4 = true;
				}
				
				Collections.sort(cuentas);
				
				for(String cuenta : cuentas){
					 //cuentaStr.append(",'"+cuenta+"'");
					 cuentaStr.append(" rowData.addColumn('number', '"+cuenta+"'); \n ");
					 cuentaStr.append(" rowData.addColumn({type: 'string', role: 'tooltip', 'p': {'html': true}}); \n ");
					 cuentaStr.append("rowData.addColumn({type:'boolean', role:'certainty'}); \n ");
				}
				
				cuentaStr.append("rowData.addColumn({type:'number', role:'interval'});  \n ");
				cuentaStr.append("rowData.addColumn({type:'number', role:'interval'});   \n ");
				cuentaStr.append("rowData.addColumn({type:'string', role:'annotation'});  \n ");
				cuentaStr.append("rowData.addColumn({type:'string', role:'annotationText', 'p': {'html': true}}); \n ");
				
				//cuentaStr.append("rowData.addColumn({type:'boolean', role:'certainty'}); ");
				
				
				Collections.sort(cuentas);
				
				Date now = null;
				
				
				if(fechaMaxGrafica!=null){
					now = fechaMaxGrafica;
				}else{
					now = dFecConsult;
				}
				Calendar cal = Calendar.getInstance(); 
				cal.setTime(now);
				cal.getActualMinimum(Calendar.DAY_OF_MONTH);
				now = cal.getTime();
				
				
				//--------------------------
				
				while(mesMin.before(dFecConsult)){
					
					Calendar c1 = Calendar.getInstance(); 
					c1.setTime(mesMin);
					meses.add(c1.getTime());
					c1.add(Calendar.MONTH,1);
					mesMin = c1.getTime();
					
					//htFecMaxReporte =(noCredito, String[fechaReporte, mop])
					Set<String> setTempNoCredito =  htFecMaxReporte.keySet();
					//htCuenta = (cuenta, Hashtable(gr.getMes,String[mop::limCred::credMax]))
					Set<String> setTempCuenta =   htCuenta.keySet();
					
				
					for(String strSetTempNoCredito:setTempNoCredito){
						
						for(String strSetTempCuenta:setTempCuenta){
						
							if(strSetTempCuenta != null && strSetTempCuenta.split("-").length > 1 && Integer.parseInt(strSetTempNoCredito) == Integer.parseInt(strSetTempCuenta.split("-")[1])){
								//Se accede a la fecha maxima de reporte por credito
								Hashtable <Date, String> htDate = htCuenta.get(strSetTempCuenta);
								String [] arFec = htFecMaxReporte.get(strSetTempNoCredito);
								//fec es la fecha max de reporte de ese credito
								Date fec = fm.parse(arFec[0]);
								
								//mesMin es el mes minimo reportado de todos los creditos.
								if(mesMin.before(dFecConsult) && mesMin.after(fec)){
									//Hashtable <Date, String> htDate = new Hashtable <Date, String>();
									//en htDate se guardan las fechas a graficar, con sus respectivos mops...
									htDate.put(mesMin, arFec[1]);
									htCuenta.put(strSetTempCuenta, htDate);
								}
								
								
							}
						}
					}
					
				}
				//--------------------------------+""

				
				//Este while permite que se tome a partir de la maxima fecha reportada
				//hacia la maxima fecha de cierre para graficar las lineas puntuadas
				while(mesMax.before(now)){
					
					Calendar c1 = Calendar.getInstance(); 
					c1.setTime(mesMax);
					meses.add(c1.getTime());
					c1.add(Calendar.MONTH,1);
					mesMax = c1.getTime();
					
					
					//System.out.println("Date is : " + fm.format(c1.getTime()));
					
					
					//htFecVen =(noCredito, String[fechaVen, mop::limCred::CredMax])
					Set<String> setTempNoCredito =  htFecVen.keySet();
					//htCuenta = (cuenta, Hashtable(gr.getMes,String[mop::limCred::credMax]))
					Set<String> setTempCuenta =   htCuenta.keySet();
					
				
					for(String strSetTempNoCredito:setTempNoCredito){
						
						for(String strSetTempCuenta:setTempCuenta){
						
							if(strSetTempCuenta != null && strSetTempCuenta.split("-").length > 1 && Integer.parseInt(strSetTempNoCredito) == Integer.parseInt(strSetTempCuenta.split("-")[1])){
								//Se accede a la fecha de vencimiento de cada credito
								Hashtable <Date, String> htDate = htCuenta.get(strSetTempCuenta);
								String [] arFec = htFecVen.get(strSetTempNoCredito);
								//fec es la fecha de venciemiento
								Date fec = formatStr.parse(arFec[0]);
								
						
								
								
								//mesMax es el mes maximo que se tiene del servicio.
								if(mesMax.before(fec)){
									//Hashtable <Date, String> htDate = new Hashtable <Date, String>();
									//en htDate se guardan las fechas a graficar, con sus respectivos mops...
									htDate.put(mesMax, arFec[1]);
									htCuenta.put(strSetTempCuenta, htDate);
								}
								
								
							}
						}
					}
					
					/*
					if(now.before(htFecVen.get())){
						
					}*/
					
				}
				//meses.add(dFecConsult);
				int k = 0;
				boolean f= false;
				boolean  fK = true;
				boolean a1 = false; //flag para la primer coma
				colors = new String("");
				for(Date mes : meses){
					
					if(a1){
						mesesStr.append(",");
					}else{
						a1 = true;
					}
					mesesStr.append("['"+fm.format(mes)+"'");
					
					for(String cuenta : cuentas){
						if(fK){
							
							if(cuenta.startsWith("MOP ")  ){
								if(f){
									colors += ",";
								}else{
									f=true;
								}
								if(cuenta.startsWith("MOP 2"))
										colors += "'#9a9a9a'";
								if(cuenta.startsWith("MOP 3"))
									colors += "'#a9a9a9'";
								if(cuenta.startsWith("MOP 4"))
									colors += "'#b3b3b3'";
								
								continue;
							}else{
								if(f){
									colors += ",";
								}else{
									f=true;
								}
								colors += colores[k];
								k++;
								if(k==colores.length){
									k=0;
								}
								
							}
							
						}
						
						if(cuenta.startsWith("MOP ")  )
							continue;
						Hashtable<Date,String> ht2 = htCuenta.get(cuenta);
						if(ht2 != null){
							
							if(ht2.get(mes) != null){
								//fec;
								Date mesActual = formatStr.parse(fecConsult);
								//System.out
//													.println("IF :" + mes +"<"+ mesActual);
								
								
								
								if(mes.before(mesActual)){
									if(ht2.get(mes).split("::").length > 4){
									mesesStr.append(","	+ ht2.get(mes).split("::")[0]+","
											+"customHTMLCont('"+cuenta+"','"+  
																			dec.format(Double.parseDouble((ht2.get(mes).split("::")[1])==null?"0":
																											(ht2.get(mes).split("::")[1]).equals("null")?"0":ht2.get(mes).split("::")[1]))+"','"+  
																			dec.format(Double.parseDouble((ht2.get(mes).split("::")[2])==null?"0":
																											(ht2.get(mes).split("::")[2]).equals("null")?"0":(ht2.get(mes).split("::")[2]) ))+"','"+
																			formatStr.format(mes)+"','"+                                       
																			dec.format(Double.parseDouble(((ht2.get(mes).split("::")[3])==null?"0":
																											((ht2.get(mes).split("::")[3]).equals("null")?"0":
																														(ht2.get(mes).split("::")[3])))))+"','"+
																			(ht2.get(mes).split("::")[4])+"','"+(ht2.get(mes).split("::")[5])+"'),true");
									}
								}else{
									if(ht2.get(mes).split("::").length > 4){
										
										if((cuenta.split("-")).length>1){
												
											//Systemout.println("Fecha de Vencimiento: "+htFecVen.get(cuenta.split("-")[1]));
											
												mesesStr.append(","	+ ht2.get(mes).split("::")[0]+","
														+"customHTMLCont('"+cuenta+"','"+  
																		  dec.format(Double.parseDouble((ht2.get(mes).split("::")[1])==null?"0":(ht2.get(mes).split("::")[1]).equals("null")?"0":ht2.get(mes).split("::")[1]))+"','"+  
																		  dec.format(Double.parseDouble((ht2.get(mes).split("::")[2])==null?"0":(ht2.get(mes).split("::")[2]).equals("null")?"0":(ht2.get(mes).split("::")[2])))+"'," +
																		  "'Fecha de Vencimiento: "+
																		  			(((htFecVen.get(cuenta.split("-")[1]))==null)?"":
																		  			(htFecVen.get(cuenta.split("-")[1]))[0]==null?"":
																		  			(htFecVen.get(cuenta.split("-")[1]))[0])
																		  +"'," +
																		  "'"+dec.format(Double.parseDouble(((ht2.get(mes).split("::")[3])==null?"0":((ht2.get(mes).split("::")[3]).equals("null")?"0":(ht2.get(mes).split("::")[3])))))+"'," +
																		  "'"+(ht2.get(mes).split("::")[4])+"'," +
																		  "'"+(ht2.get(mes).split("::")[5])+"'" +
																		")," +
																		"false");
										}
												
									}
									
								}
									
							}else{
								mesesStr.append(",null,null,null");
							}
						}
					}
					fK = false;
					
					mesesStr.append( ",2.0,'ATRASO DE 1 A 30 DÍAS',true" );
				
					mesesStr.append( ",3.0,'ATRASO DE 31 A 60 DÍAS',true" );
				
					mesesStr.append( ",4.0,'ATRASO DE 61 A 90 DÍAS',true" );
					
					//mesesStr.append( "false" );
					
					String dMes = fm.format(mes);
					String year = dMes.split("/")[2];
					String month = dMes.split("/")[1];
					
					String key = month+"::"+year;
					
					List<Vtbur_infocnsltms> ltConInfoMs  = htConsultMS.get(key);
					
					List<Vtbur_infocnsltult> ltConInfoUlt  = htConsultUlt.get(key);
					
					if((ltConInfoMs==null || ltConInfoMs.size()==0) && (ltConInfoUlt==null || ltConInfoUlt.size()==0) || (Integer.parseInt(dMes.split("/")[0]) != 1) ){
						
						//mesesStr.append( ",null,null,null,null" );
						mesesStr.append( ",null,null,null,null" );
					
					}else{
						
						String strConsult = "customHTMLContConsult(new Array(";
						boolean fl = false;
						int x = 0;
						if(ltConInfoMs != null){
							
							for( Vtbur_infocnsltms Ms : ltConInfoMs  ){
								if(fl){
									strConsult += ",";
								}else{
									fl = true;
								}
								strConsult += "new Array('"+(fm2.format(fm3.parse(Ms.getFecha_de_consulta()))) +"','"+Ms.getNombre_otorgante()+"','"+(dec.format(Double.parseDouble(Ms.getMonto_solicitado())))+"')" ;
								x++;
							}
						}
						
						if(ltConInfoUlt !=null ){
							for( Vtbur_infocnsltult ult : ltConInfoUlt  ){
								
								if(fl){
									strConsult += ",";
								}else{
									fl = true;
								}
								
								strConsult += "new Array ('"+(fm2.format(fm3.parse(ult.getFecha_de_consulta()))) +"','"+ult.getNombre_otorgante()+"','"+(dec.format(Double.parseDouble(ult.getMonto_solicitado())))+"')" ;
								x++;
							}
						}
						strConsult +="))";
						mesesStr.append( ",0,##MAXGRID##,'"+x+"',"+strConsult );
						
					}
						
					mesesStr.append( "]\n");
				}
				
				String inte = (maxGridD+"");
				inte = inte.split("\\.")[0];
				
				if (maxGridD > 4D){
				
					maxGrid = Integer.parseInt(inte) + 2;
					
				}else{
				
					maxGrid = Integer.parseInt(inte) + 1;
				
				}
				
				chartModel = "<script> " +
						"rowData = null;" +
						"rowData = new google.visualization.DataTable();" +
						""+cuentaStr.toString()+" " +
						"rowData.addRows([\n"+mesesStr.toString().replaceAll("##MAXGRID##", ((maxGrid-1)+""))+"\n]);"+
						"myColors = ["+colors+"]; \n gridV="+maxGrid+" </script>";
				
		 }
		 else{
			//fec es la fecha max de reporte de ese credito
			 SortedSet<Date> meses = new TreeSet<Date>();
			 ArrayList<String> cuentas = new ArrayList<String>();
			 
			 StringBuffer cuentaStr = new  StringBuffer();
			 
			 StringBuffer mesesStr = new  StringBuffer();
			 
			 cuentaStr.append( " rowData.addColumn('string', 'Mes'); \n ");
			 
			 cuentaStr.append(" rowData.addColumn('number', 'MOP 2 LINEA DE CONTROL'); \n ");
			 cuentaStr.append(" rowData.addColumn({type: 'string', role: 'tooltip', 'p': {'html': true}}); \n ");
			 cuentaStr.append(" rowData.addColumn('number', 'MOP 3 LINEA DE CONTROL'); \n ");
			 cuentaStr.append(" rowData.addColumn({type: 'string', role: 'tooltip', 'p': {'html': true}}); \n ");
			 cuentaStr.append(" rowData.addColumn('number', 'MOP 4 LINEA DE CONTROL'); \n ");
			 cuentaStr.append(" rowData.addColumn({type: 'string', role: 'tooltip', 'p': {'html': true}}); \n ");
			 
			 //Se agregaron intervalos y anotaciones para mostrar un detalle y las lineas de las consutas a buro.
			 cuentaStr.append("rowData.addColumn({type:'number', role:'interval'});  \n ");
			 cuentaStr.append("rowData.addColumn({type:'number', role:'interval'});   \n ");
			 cuentaStr.append("rowData.addColumn({type:'string', role:'annotation'});  \n ");
			 cuentaStr.append("rowData.addColumn({type:'string', role:'annotationText', 'p': {'html': true}}); \n ");
			 //							 
			 
			 //cuentaStr.append("rowData.addColumn({type:'boolean', role:'certainty'});");
			 cuentaStr.append("rowData.addColumn({type:'boolean', role:'certainty'}); \n ");
			 
			 Collections.sort(cuentas);
			
			Date mesMax = fm.parse("01/01/2011");
			Date now = new Date();
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(now);
			cal.getActualMinimum(Calendar.DAY_OF_MONTH);
			now = cal.getTime();
//							boolean f = false;
			boolean a1 = false; //flag para la primer coma
			while(mesMax.before(now)){
				

				Calendar c1 = Calendar.getInstance(); 
				c1.setTime(mesMax);
				meses.add(c1.getTime());
				c1.add(Calendar.MONTH,1);
				mesMax = c1.getTime();
//									mesesStr.append( ",2.0,'CONTROL'" );
//									
//									mesesStr.append( ",3.0,'CONTROL'" );
//									
//									mesesStr.append( ",4.0,'CONTROL'" );
					
//									mesesStr.append( ",false]\n");
					
					
					/**/
					
			}
					
			//Se hace el siguiente ciclo para que que los clientes que no tienen creditos
			//se muestre las consultas que el cliente tiene
//									int k = 0;
//									boolean f= false;
//									boolean  fK = true;
					colors = new String("");
					for(Date mes : meses){
						
						if(a1){
							mesesStr.append(",");
						}else{
							a1 = true;
						}
						mesesStr.append("['"+fm.format(mes)+"'");
						
						//fK = false;
						
						mesesStr.append( ",2.0,'CONTROL'" );
						
						mesesStr.append( ",3.0,'CONTROL'" );
						
						mesesStr.append( ",4.0,'CONTROL'" );
						
						
						String dMes = fm.format(mes);
						String year = dMes.split("/")[2];
						String month = dMes.split("/")[1];
						
						String key = month+"::"+year;
						
						List<Vtbur_infocnsltms> ltConInfoMs  = htConsultMS.get(key);
						
						List<Vtbur_infocnsltult> ltConInfoUlt  = htConsultUlt.get(key);
						
						if((ltConInfoMs==null || ltConInfoMs.size()==0) && (ltConInfoUlt==null || ltConInfoUlt.size()==0) || (Integer.parseInt(dMes.split("/")[0]) != 1) ){
							
							mesesStr.append( ",null,null,null,null" );
						
						}else{
							
							String strConsult = "customHTMLContConsult(new Array(";
							boolean fl = false;
							int x = 0;
							if(ltConInfoMs != null){
								
								for( Vtbur_infocnsltms Ms : ltConInfoMs  ){
									if(fl){
										strConsult += ",";
									}else{
										fl = true;
									}
									strConsult += "new Array('"+(fm2.format(fm3.parse(Ms.getFecha_de_consulta()))) +"','"+Ms.getNombre_otorgante()+"','"+(dec.format(Double.parseDouble(Ms.getMonto_solicitado())))+"')" ;
									x++;
								}
							}
							
							if(ltConInfoUlt !=null ){
								for( Vtbur_infocnsltult ult : ltConInfoUlt  ){
									
									if(fl){
										strConsult += ",";
									}else{
										fl = true;
									}
									
									strConsult += "new Array ('"+(fm2.format(fm3.parse(ult.getFecha_de_consulta()))) +"','"+ult.getNombre_otorgante()+"','"+(dec.format(Double.parseDouble(ult.getMonto_solicitado())))+"')" ;
									x++;
								}
							}
							strConsult +="))";
							mesesStr.append( ",0,4,'"+x+"',"+strConsult );
						}

						mesesStr.append( ",false]\n");
					}
					
					maxGrid = 4;
					colors += "'#9a9a9a','#a9a9a9','#b3b3b3'";
					
						
					chartModel = "<script> " +
							"		 "+cuentaStr.toString()+" " +
									 "rowData.addRows([\n"+mesesStr.toString()+"\n]);"+
								 	 "myColors = ["+colors+"] " +
								 "</script>";
					 
					 /**/
			
			//System.out.println("Valor chatModel:"+chartModel);
		 }
		 
		 Calendar cGrf2 = Calendar.getInstance();
		 cGrf2.setTime(new Date());
		 long difGrf = cGrf2.getTimeInMillis() - cGrf.getTimeInMillis();
		 //Systemout.println("Tiempo en cargar datós de gráfica: "+difGrf+" milisegundos");		 
	}
	
	public void asignar_consultas_anteriores() throws RemoteException 
	{
		
		Calendar cCns1 = Calendar.getInstance();
		 cCns1.setTime(new Date());
		
		//Systemout.println( "Consultas Anteriores." );
		
		if( service_SGB_risk == null ){
			try{
				locator     = new WsSgbRiskServiceLocator();
				service_SGB_risk     = locator.getWsSgbRisk();
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
		}
		
		BurResponse consultas[] = null;
		
		if(bur != null){
		
			consultas = service_SGB_risk.getBurScores(null, null, prospectus_id+"");
		
		}else{
			consultas = service_SGB_risk.getBurScores(null, null, sesion.getUser_graphic_temp());
		}
		 
		 lstConsultas = new ArrayList<ConsultingBean> ();
		
		 
		 if( consultas != null ){
		 
			for( BurResponse resume : consultas)
			{
				
				if(resume.getBurScore() != null && !resume.getBurScore().equals("null") && !resume.getSolNum().equals(burSolNum)  ){
				
					//System.out.println( resume.getBurDate() +"   -   "+resume.getBurScore()+"   -   "+resume.getSolNum()+"   -   "+resume.getTipoConsulta());
					
					List<ProyectLoan> temporalProyect = proyectLoanService.getProyectLoansListByBurSolNum(resume.getSolNum());
					
					if( temporalProyect != null && temporalProyect.size()>0 ){
					
						for(ProyectLoan tmp : temporalProyect ){
						
							ConsultingBean consult = new ConsultingBean();
							
							consult.setBcScore(resume.getBurScore());
							consult.setBursolnum(resume.getSolNum());
							
							ConvertCalendar conv1 = new ConvertCalendar(Long.parseLong(resume.getBurDate()));
							 
							Date dFecConsult = conv1.getDate(); 
							
							String fecConsult = formatStr.format( dFecConsult );
							
							consult.setFecConsult(fecConsult);
							consult.setTipoConsulta(resume.getTipoConsulta());
							
							consult.setProyect_loan_id(tmp.getProyectloanPk().getProyect_loan_id());
							
							consult.setCredito(tmp.getSafi_credit_id()==null?"No asignado":tmp.getSafi_credit_id());
							consult.setStatus(tmp.getStatusProyect().getUrl_img());
							
							lstConsultas.add(consult);
						}
					
					}else{
						
						ConsultingBean consult = new ConsultingBean();
						
						consult.setBcScore(resume.getBurScore());
						consult.setBursolnum(resume.getSolNum());
						
						
						ConvertCalendar conv2 = new ConvertCalendar(Long.parseLong(resume.getBurDate()));
						 
						Date dFecConsult = conv2.getDate(); 
						
						String fecConsult = formatStr.format( dFecConsult );
						
						consult.setFecConsult(fecConsult);
						
						
						consult.setTipoConsulta(resume.getTipoConsulta());
						
						consult.setCredito("Sin proyecto");
						consult.setStatus("Sin proyecto");
						
						consult.setProyect_loan_id(99999999);
						
						lstConsultas.add(consult);
						
					}
					
				}
			}
			
		 }
		 
		Calendar cCns2 = Calendar.getInstance();
		cCns2.setTime(new Date());
		long difCns = cCns2.getTimeInMillis() - cCns1.getTimeInMillis();
		//Systemout.println("Tiempo en cargar las consultas: "+difCns+" milisegundos");			
	}
	
	protected void registrar_bitacora_acceso() 
	{
		Access access = new Access();
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(26);//pantalla de Reporte de Buró
		access.setPercentage(0);
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setOp_system           (sesion.getOsbrawser());
		access.setHorizontal_size     (sesion.getBrowser_width());
		access.setVertical_size       (sesion.getBrowser_height());
		access.setIpaddress           (sesion.getIP_address_client());
		access.setUser_agent          (sesion.getUser_agent());
		access.setDevice_info         (sesion.getDevice_info());
		access.setUrl_access		  (sesion.getUrl_access());
		
		access.setProspectus_id_viewed(prospectus_id);
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access,false);
	}
	
	protected boolean insertaDatosCache()
	{
		BuroCache cache = new BuroCache();
		cache.setMx_solicitud_buro(burSolNum);
		
		cache.setProspectus_id( prospectus_id +"" );
		cache.setNombre1( infocte.getNombre1()) ;
		cache.setNombre2( infocte.getNombre2()) ;
		cache.setApellido1( infocte.getApellido1()); 
		cache.setApellido2( infocte.getApellido2());
		cache.setKubo_score_letter(kubo_score_letter+"");
		cache.setKubo_score_number(kubo_score_number+"");
		
		cache.setMx_folio(folioNum);
		cache.setIdProvider(idProvider+"");
		cache.setFecConsult(dFecConsult);
		cache.setCalificacion_kubo(calificacion_kubo);
		cache.setKubo_score_class(kubo_score_class);
		cache.setNombreCompleto(nombreCompleto);
		cache.setRfc(infocte.getRfc());
		cache.setConsultas_propias(consultas_propias+"");
		cache.setConsultas_propiasMS(consultas_propiasMS+"");
		cache.setConsultas_propiasULT(consultas_propiasULT+"");
		cache.setConsultasOtras(consultasOtras+"");
		cache.setConsultasOtrasMS(consultasOtrasMS+"");
		cache.setConsultasOtrasULT(consultasOtrasULT+"");
		cache.setConsultasKubo(consultasKubo+"");
		cache.setConsultasKuboULT(consultasKuboULT+"");
		cache.setConsultasKuboMS(consultasKuboMS+"");
		cache.setTotalConsult(totalConsult+"");
		cache.setTableRulesStr(tableRulesStr);
		cache.setTableAlertas1Str(tableAlertas1Str);
		cache.setTableAlertas2Str(tableAlertas2Str);
		cache.setTableConsultUltData(tableConsultUltData);
		cache.setTableConsultMSData(tableConsultMSData);
		cache.setTableVigData(tableVigData);
		cache.setTableVigDataCom(tableVigDataCom);
		cache.setTableDomicilios(tableDomicilios);
		cache.setTableCredCerr(tableCredCerr);
		cache.setTableCredCerr_M_6(tableCredCerr_M_6);
		cache.setChartModel(chartModel);
		cache.setMaxGrid(maxGrid+"");
		cache.setScriptJSon(scriptJSon);
		cache.setScriptSaldos(scriptSaldos);
		
		cache.setScore_buro(bur.getScore_buro() );
		
		cache.setBurFol(bur.getBurFol());
		cache.setTipoConsulta(bur.getTipoConsulta());
		cache.setScoreIcc(bur.getScoreIcc());
		
		cache.setNumsols(bur.getNumsols());
		cache.setCta_cgarantia(bur.getCta_cgarantia());
		cache.setQuebrantos(bur.getQuebrantos());
		
		cache.setFraudes(bur.getFraudes()+"");
		cache.setRoboidentidad(bur.getRoboidentidad()+"");
		cache.setFraudesnoatribuible(bur.getFraudesnoatribuible()+"");
		cache.setFianzas(bur.getFianzas()+"");
		cache.setAplicogarantia(bur.getAplicogarantia()+"");
		cache.setNolocalizable(bur.getNolocalizable()+"");
		cache.setCodemandado(bur.getCodemandado()+"");
		cache.setCondonaciones(bur.getCondonaciones()+"");
		cache.setReestructura(bur.getReestructura()+"");
		
		cache.setFec_credantiguo( bur_fec_credantiguo );
		
		if(spca != null){
			cache.setStrCreditos(spca.getStrCreditos());
			cache.setLimiteagregado(spca.getLimiteagregado() +"" );
			cache.setSaldoagregado(spca.getSaldoagregado() +"");
			cache.setMontoagregado(spca.getMontoagregado()+"");
		}
		
		if( maxoto != null ){
			cache.setPrim_credito(maxoto.getPrim_credito());
			cache.setUlt_credito(maxoto.getUlt_credito());
			cache.setMax_liquidado(maxoto.getMax_liquidado() +"");
			cache.setMax_noliquidado(maxoto.getMax_noliquidado()+"");
		}
		cache.setNumCorriente(numCorriente+"");
		cache.setSaldoCorriente(saldoCorrienteStr);
		cache.setMontoAPagarCorriente( montoAPagarCorrienteStr);
		 
		cache.setPagoPeriodicoCorriente(pagoPeriodicoCorrienteStr);
		cache.setSaldoVencidoCorriente(saldoVencidoCorrienteStr);
		 
		cache.setMaxCredCorriente(maxCredCorrienteStr);
			 
		cache.setLimiteMaxCorriente(limiteMaxCorrienteStr);
		
		cache.setNumAtraso(numAtraso+"");
		cache.setSaldoAtraso( saldoAtrasoStr);
		cache.setMontoAPagarAtraso(montoAPagarAtrasoStr);
		 
		cache.setPagoPeriodicoAtrasado( pagoPeriodicoAtrasadoStr);
		cache.setLimiteMaxCorriente( limiteMaxCorrienteStr);
		 
		cache.setMaxCredAtraso( maxCredAtrasoStr);
		cache.setLimiteMaxAtrasado(limiteMaxAtrasadoStr );
		cache.setSaldoVencidoAtrasado(saldoVencidoAtrasadoStr);
			
		return burocacheservice.saveBuroCache(cache);
	}
	
	protected void init_buro_cache(BuroCache cache) 
	{
		folioNum 			= cache.getMx_folio();
		//idProvider 			= Integer.parseInt( cache.getIdProvider() );
		
		dFecConsult  		= cache.getFecConsult();
		calificacion_kubo	= cache.getCalificacion_kubo();
		kubo_score_class	= cache.getKubo_score_class();
		nombreCompleto		= cache.getNombreCompleto();
		infocte.setRfc(cache.getRfc());
		
		infocte.setIdprospecto( cache.getProspectus_id() );
	
		if( cache.getProspectus_id() != null ){
			prospectus_id = Integer.parseInt( cache.getProspectus_id() );
		}
		
		infocte.setNombre1    ( cache.getNombre1()   );
		infocte.setNombre2    ( cache.getNombre2()   );
		infocte.setApellido1  ( cache.getApellido1() ); 
		infocte.setApellido2  ( cache.getApellido2() );
		infocte.setBur_solnum ( cache.getMx_solicitud_buro());
		
		if( cache.getKubo_score_letter() != null ){
		kubo_score_letter = Character.valueOf(cache.getKubo_score_letter().charAt(0) );
		}
		if( cache.getKubo_score_number() != null ){
		kubo_score_number = Character.valueOf(cache.getKubo_score_number().charAt(0) );
		}
		if( cache.getConsultas_propias() != null ){
		consultas_propias	= Integer.parseInt(cache.getConsultas_propias());
		}
		if( cache.getConsultas_propiasMS() != null ){
		consultas_propiasMS	= Integer.parseInt(cache.getConsultas_propiasMS());
		}
		if( cache.getConsultas_propiasULT() != null ){
		consultas_propiasULT= Integer.parseInt(cache.getConsultas_propiasULT());
		}
		if( cache.getConsultasOtras() != null ){
		consultasOtras		= Integer.parseInt(cache.getConsultasOtras());
		}
		if( cache.getConsultasOtrasMS() != null ){
		consultasOtrasMS	= Integer.parseInt(cache.getConsultasOtrasMS());
		}
		if( cache.getConsultasOtrasULT() != null ){
			consultasOtrasULT	= Integer.parseInt(cache.getConsultasOtrasULT());
		}
		if( cache.getConsultasKubo() != null ){
			consultasKubo		= Integer.parseInt(cache.getConsultasKubo());
		}
		if( cache.getConsultasKuboULT() != null ){
			consultasKuboULT	= Integer.parseInt(cache.getConsultasKuboULT());
		}
		if( cache.getConsultasKuboMS() != null ){
			consultasKuboMS		= Integer.parseInt(cache.getConsultasKuboMS());
		}
		if( cache.getTotalConsult() != null ){
			totalConsult		= Integer.parseInt(cache.getTotalConsult());
		}
		
		tableRulesStr		= cache.getTableRulesStr();
		tableAlertas1Str	= cache.getTableAlertas1Str();
		tableAlertas2Str	= cache.getTableAlertas2Str();
		tableConsultUltData	= cache.getTableConsultUltData();
		tableConsultMSData	= cache.getTableConsultMSData();
		tableVigData		= cache.getTableVigData();
		tableVigDataCom		= cache.getTableVigDataCom();
		tableDomicilios		= cache.getTableDomicilios();
		tableCredCerr		= cache.getTableCredCerr();
		tableCredCerr_M_6	= cache.getTableCredCerr_M_6();
		chartModel			= cache.getChartModel();
		
		
		if( cache.getMaxGrid() != null ){
			maxGrid			= Integer.parseInt(cache.getMaxGrid());
		}
		
		scriptJSon			= cache.getScriptJSon();
		scriptSaldos		= cache.getScriptSaldos();
		
		
		fecConsult  = formatStr.format( dFecConsult );
		
		bur = new BurResume();
		
		//System.out.println( "Inicializando  bur = new BurResume()" );
		
		bur.setScore_buro(cache.getScore_buro() );
		
		bur.setBurFol(cache.getBurFol());
		bur.setTipoConsulta(cache.getTipoConsulta());
		bur.setScoreIcc(cache.getScoreIcc());
		
		bur.setNumsols(cache.getNumsols());
		bur.setCta_cgarantia(cache.getCta_cgarantia());
		bur.setQuebrantos(cache.getQuebrantos());
		
		if( cache.getProspectus_id() != null ){
			bur.setIdprospecto( cache.getProspectus_id() );
		}
		if( cache.getFraudes() != null ){
			bur.setFraudes(Integer.parseInt(cache.getFraudes()));
		}
		if( cache.getRoboidentidad() != null ){
			bur.setRoboidentidad(Integer.parseInt(cache.getRoboidentidad()));
		}
		if( cache.getFraudesnoatribuible() != null ){
			bur.setFraudesnoatribuible(Integer.parseInt(cache.getFraudesnoatribuible()));
		}
		if( cache.getFianzas() != null ){
			bur.setFianzas(Integer.parseInt(cache.getFianzas()));
		}
		if( cache.getAplicogarantia() != null ){
			bur.setAplicogarantia(Integer.parseInt(cache.getAplicogarantia()));
		}
		if( cache.getNolocalizable() != null ){
			bur.setNolocalizable(Integer.parseInt(cache.getNolocalizable()));
		}
		if( cache.getCodemandado() != null ){
			bur.setCodemandado(Integer.parseInt(cache.getCodemandado()));
		}
		if( cache.getCondonaciones() != null ){
			bur.setCondonaciones(Integer.parseInt(cache.getCondonaciones()));
		}
		if( cache.getReestructura() != null ){
			bur.setReestructura(Integer.parseInt(cache.getReestructura()));
		}
		
		bur_fec_credantiguo = cache.getFec_credantiguo();
		
		spca = new Bur_spca();
		spca.setStrCreditos(cache.getStrCreditos());
		
		if( cache.getLimiteagregado() != null ){
			spca.setLimiteagregado(Double.parseDouble( cache.getLimiteagregado() ));
		}
		if( cache.getSaldoagregado() != null ){
			spca.setSaldoagregado( Double.parseDouble( cache.getSaldoagregado()));
		}
		if( cache.getMontoagregado() != null ){
			spca.setMontoagregado( Double.parseDouble( cache.getMontoagregado()));
		}
		
	  
		maxoto= new Bur_maxoto();
		maxoto.setPrim_credito(  cache.getPrim_credito());
		maxoto.setUlt_credito(   cache.getUlt_credito());
		
		if( cache.getMax_liquidado() != null ){
			maxoto.setMax_liquidado( Double.parseDouble( cache.getMax_liquidado() ));
		}
		if( cache.getMax_noliquidado() != null ){
			maxoto.setMax_noliquidado( Double.parseDouble(cache.getMax_noliquidado()));
		}
		
		if( cache.getNumCorriente() != null ){
			numCorriente = Integer.parseInt( cache.getNumCorriente() );
		}
		if( cache.getSaldoCorriente() != null ){
			saldoCorrienteStr = cache.getSaldoCorriente();
		}
		if( cache.getMontoAPagarCorriente( ) != null ){
			montoAPagarCorrienteStr = cache.getMontoAPagarCorriente( );
		}
		if( cache.getPagoPeriodicoCorriente() != null ){
			pagoPeriodicoCorrienteStr = cache.getPagoPeriodicoCorriente();
		}
		if( cache.getSaldoVencidoCorriente() != null ){
			saldoVencidoCorrienteStr = cache.getSaldoVencidoCorriente();
		}
		if( cache.getMaxCredCorriente() != null ){
			maxCredCorrienteStr = cache.getMaxCredCorriente();
		}
		if( cache.getLimiteMaxCorriente() != null ){
			limiteMaxCorrienteStr = cache.getLimiteMaxCorriente();
		}
		if( cache.getNumAtraso() != null ){
			numAtraso = Integer.parseInt(cache.getNumAtraso() );
		}
		if( cache.getSaldoAtraso( ) != null ){
			saldoAtrasoStr = cache.getSaldoAtraso( );
		}
		if( cache.getMontoAPagarAtraso() != null ){
			montoAPagarAtrasoStr = cache.getMontoAPagarAtraso();
		}
		if( cache.getPagoPeriodicoAtrasado( ) != null ){
			pagoPeriodicoAtrasadoStr = cache.getPagoPeriodicoAtrasado( );
		}
		if( cache.getLimiteMaxCorriente( ) != null ){
			limiteMaxCorrienteStr = cache.getLimiteMaxCorriente( );
		}
		if( cache.getMaxCredAtraso( ) != null ){
			maxCredAtrasoStr = cache.getMaxCredAtraso( );
		}
		if( cache.getLimiteMaxAtrasado( ) != null ){
			limiteMaxAtrasadoStr = cache.getLimiteMaxAtrasado( );
		}
		if( cache.getSaldoVencidoAtrasado() != null ){
			saldoVencidoAtrasadoStr = cache.getSaldoVencidoAtrasado();
		}
	}
	
	protected boolean saveChangeData(String table,String field,String origValue,String newValue,String comment,int prospectus_id,int company_id)
	{		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		
		String ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    
		if(ipAddressClient == null)  	 
		{			
	    	ipAddressClient = httpServletRequest.getRemoteAddr();
		}
	    
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		
		
		Change_controlPK changeCtrlPK=new Change_controlPK();
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		Change_control changeCtrl=new Change_control();
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(ipAddressClient);			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		if(changeControlService.addChangeControl(changeCtrl,prospectus_id,company_id))
			return true;
		else
			return false;
	}
	
	protected void setFormatName()
	{
		infocte.setNombre1  (FormatoCadenas.formatoNombre(infocte.getNombre1()));
		infocte.setNombre2  (FormatoCadenas.formatoNombre(infocte.getNombre2()));
		infocte.setApellido1(FormatoCadenas.formatoNombre(infocte.getApellido1()));
		infocte.setApellido2(FormatoCadenas.formatoNombre(infocte.getApellido2()));
	}
	
	private String quitaAcentos(String str){
		
		String tmp = "";
		while(!str.equals(tmp)){
			tmp = str;
			str = str.replace("á", "a");
			str = str.replace("é", "e");
			str = str.replace("í", "i");
			str = str.replace("ó", "o");
			str = str.replace("ú", "u");
			str = str.replace("Á", "A");
			str = str.replace("É", "E");
			str = str.replace("Í", "I");
			str = str.replace("Ó", "O");
			str = str.replace("Ú", "U");
			str = str.replace("Ü", "U");
			str = str.replace("ü", "u");
		}
		
		return str;
	}
}
