package mx.com.kubo.managedbeans;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

@ManagedBean @ViewScoped
public class GeneraCurpRfc extends GeneraCurpRfcDMO
{		
	@PostConstruct
	public void init() 
	{
		naturalPerson = new NaturalPerson();
		stateList     = stateService.getStateList();
		countryList   = countryService.getCountryList();
		
		// Dias
		for (int i = 1; i <= 31; i++) 
		{
			days.add("" + i);
		}
		// Meses
		for (int i = 0; i < monthStr.length; i++) 
		{
			months.add("" + monthStr[i]);
		}

		// Años
		for (int i = 1994; i >= 1950; i--) 
		{
			years.add("" + i);
		}
	}
	
	public void getRFC() 
	{
		System.out.println("Obteniendo RFC");
		
		String name = "";
		
		if(naturalPerson.getFirst_name()       != null
		&& naturalPerson.getFather_last_name() != null
		&& naturalPerson.getMother_last_name() != null
		&& naturalPerson.getDate_of_birth()    != null
		&& !naturalPerson.getFirst_name().isEmpty()
		&& !naturalPerson.getFather_last_name().isEmpty()
		&& !naturalPerson.getMother_last_name().isEmpty()) 
		{
			if (naturalPerson.getMiddle_name()!=null&&!naturalPerson.getMiddle_name().isEmpty())
			{
				name = naturalPerson.getFirst_name().trim() + " " + naturalPerson.getMiddle_name().trim();
				
			} else {
				
				name = naturalPerson.getFirst_name().trim();
			}

			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

			String birthday = fm.format(naturalPerson.getDate_of_birth());

			naturalPerson.setMx_rfc(naturalPersonService.getRFC(name,
					naturalPerson.getFather_last_name().trim(), naturalPerson
							.getMother_last_name().trim(), birthday));
			
			getCURP();
			
		}
		
	}

	public void getCURP() 
	{
		System.out.println("Obteniendo CURP");
		System.out.println("fecha de nacimiento: "
				+ naturalPerson.getDate_of_birth());
		if (	naturalPerson.getFirst_name()!=null
				&& naturalPerson.getFather_last_name()!=null
				&& naturalPerson.getMother_last_name()!=null
				&& !naturalPerson.getFirst_name().isEmpty()
				&& !naturalPerson.getFather_last_name().isEmpty()
				&& !naturalPerson.getMother_last_name().isEmpty()
				&& naturalPerson.getDate_of_birth() != null) 
		{
			String estado = "";
			boolean flag = false;
			
			if (naturalPerson.getCitizenship()!=null&&naturalPerson.getCitizenship() == 1)
			{
				if (naturalPerson.getState_id() != null
						&& !naturalPerson.getState_id().equals("")
						&& !naturalPerson.getState_id().equals("0")) 
				{
					StateCatPK pk = new StateCatPK();
					pk.setCompany_id(1);
					pk.setState_id(naturalPerson.getState_id());
					estado = ((StateCat) stateService.getStateById(pk))
							.getName();
					flag = true;
				} else {
					estado = "Extranjero";
					flag = true;
				}
			} else {
				estado = "Extranjero";
				flag = true;
			}
			
			if (flag)
			{
				naturalPerson.setMx_curp(naturalPersonService.generaCURP(naturalPerson, estado.toUpperCase()));
			}
			
		}
	}
	
	public void changeNation()
	{
		if(naturalPerson.getCitizenship()==0){
			naturalPerson.setState_id(null);
		}
		if(naturalPerson.getCitizenship()==1){
			naturalPerson.setCountry_id(700);
			naturalPerson.setState_id(null);
		}
		getCURP();
		
	}

	public void generateDate() {
		String dayStr = getDay();
		String thisMonth = getMonth();
		String yearStr = getYear();
		String thisDate = "";
		String sM = "";

		if (dayStr != null && dayStr.length() > 0 && !dayStr.equals("0")) {
			thisDate += dayStr + "/";
		} else
			return;

		if (dayStr.equals("31")) {
			if (thisMonth.equals("Febrero") || thisMonth.equals("Abril")
					|| thisMonth.equals("Junio")
					|| thisMonth.equals("Septiembre")
					|| thisMonth.equals("Noviembre")) {
				setDay("0");
				return;
			}
		}
		if (thisMonth == null)
			return;
		if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) > 29) {
			setDay("0");
			return;
		}

		if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) == 29
				&& (Integer.parseInt(yearStr) % 4 != 0)) {
			setDay("0");
			return;
		}

		if (thisMonth != null && thisMonth.length() > 0
				&& !thisMonth.equals("0")) {
			for (int i = 0; i < getMonthStr().length; i++) {
				if ((getMonthStr()[i]).equals(thisMonth)) {
					if ((i + 1) < 10)
						sM = "0" + (i + 1);
					else
						sM = (i + 1) + "";
				}
			}
			thisDate += sM + "/";
		} else
			return;

		if (yearStr != null && yearStr.length() > 0 && !yearStr.equals("0")) {
			thisDate += yearStr;
		} else
			return;

		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
		try {
			getNaturalPerson().setDate_of_birth(fm.parse(thisDate));
			getRFC();
			getCURP();
		} catch (Exception e) {
			//getNaturalPerson().setDate_of_birth(null);
			e.printStackTrace();
		}

	}
	
	public void sendMail()
	{
		
		lista_proyect_loan =  proyectloanService.getProyectLoansListByBurSolNum(bursolnum);
		
		proyect_loan = lista_proyect_loan.get(0);
		
		kubo_kubofinanciero_com = proyect_loan.getProyectloanPk().getProspectus_id();
		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id( 1 );
		membership_PK.setProspectus_id(kubo_kubofinanciero_com);
		
		membership = membershipservice.getMembershipById(membership_PK);		
				
		notificacion(Evento.CONSULTA_BC_EXITOSA, "", proyect_loan.getScoring(), proyect_loan, membership);
		
	}
	
	public void sendTest()
	{
		kubo_kubofinanciero_com = 0;
		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id( 1 );
		membership_PK.setProspectus_id(kubo_kubofinanciero_com);
		
		membership = membershipservice.getMembershipById(membership_PK);	
		
		notificacion(Evento.PRUEBA_ENVIO_CORREO, "", null, null, membership);	
	}

	private void notificacion(Evento evento, String errormsg, Scoring score, ProyectLoan  myPyLn, Membership membership)
	{
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(membership);			
			notificador.notificar(evento, score, myPyLn, errormsg);
			
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}	
	}
	/*
	public void sendMail(){
		
		//Scoring score = scoringService.loadScoringByBurSolNum(bursolnum);
		
		List<ProyectLoan> lstpl =  proyectloanService.getProyectLoansListByBurSolNum(bursolnum);
		
		ProyectLoan pl = lstpl.get(0);
		
		gnNaturalPersonPK nppk= new gnNaturalPersonPK(); 
		nppk.setCompany_id(pl.getProyectloanPk().getCompany_id());
		nppk.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
		naturalPerson = naturalPersonService.getNaturalPersonById(nppk);
		
		MembershipPK mpk = new MembershipPK(); 
		mpk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		mpk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		Membership membership = membershipservice.getMembershipById(mpk);
		
		
		SendMail sm = new SendMail();
		sm.setStrTo("rogelio@kubofinanciero.com, rodrigomb@kubofinanciero.com");
		
		
		
		sm.setProspectName(naturalPerson.NombreCompletoNPM());
		sm.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id()+"");
		
		sm.setBcScore(pl.getBc_score()+"");
		sm.setTasa(pl.getRate()+"");
		sm.setKuboScore(pl.getKubo_score_a()+pl.getKubo_score_b());
		sm.setComision(pl.getOpening_commission()+"");
		sm.setConsultaBC(true);
		sm.setBurSolNum(pl.getMx_solicitud_buro());
		String registrationReason ="";
		sm.setEmailAcred(membership.getEmail());
		sm.setLoanType(pl.getLoantype().getLoan_type_name());
//		if(membership !=null && membership.getRegistration_reason()!=null){
//			if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 6){ //Promotor
//				registrationReason = membership.getRegistration_reason().getName() ;
//				if(membership.getPromotor()!=null){
//					registrationReason += " "+membership.getPromotor().getName();
//				}
//			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 7){ //Otro
//				//registrationReason = membership.getRegistration_reason().getName() ;
//				if(membership.getOther_registration_reason()!=null){
//					registrationReason = " "+membership.getOther_registration_reason();
//				}
//			}else if(membership.getRegistration_reason() != null){
//				registrationReason = " "+membership.getRegistration_reason().getName();
//			}
//			
//		}else{
//			sm.setRegistrationReason("Sin dato");
//		}
		
		
		 registrationReason =validaRegistrationReason(membership);	
			
			sm.setRegistrationReason(registrationReason);
			
			SimpleDateFormat formatStr = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
			sm.setStrFecha(formatStr.format(new Date()));
			
			//TODO Procesar ESTATUS_DE_ENVIO
			/*
			if(sm.send())
				System.out.println("Mail Enviado Satisfactoriamente ");
			else
				System.out.println("Fallo al enviar mail  ");
			* /
			
		
	}*/
	
	public void submit() 
	{  
		 try
		 {
	        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");  
	          
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
		 } catch(Exception e) {
			 FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), e.getMessage());  
	          
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		 }
	}	
}
