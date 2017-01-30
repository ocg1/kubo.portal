package mx.com.kubo.managedbeans.registro.consulta;

import java.util.Date;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.registro.consulta.AutorizadorIMP;
import mx.com.kubo.registro.consulta.PersonDataIMP;
import mx.com.kubo.registro.consulta.historial.car.CarIMP;
import mx.com.kubo.registro.consulta.historial.card.CreditCardIMP;
import mx.com.kubo.registro.consulta.historial.mortage.MortageIMP;
import mx.com.kubo.registro.consulta.historial.SearchRequestIMP;

public abstract class CreditHistoryControllerAMO extends CreditHistoryControllerDMO
{
	protected boolean init_pre_aprobacion() 
	{
		is_credit_history_ENABLED = true;
		
		score = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);
		
		if(score != null)
		{
			kubo_rate      = score.getKubo_rate();
			kubo_score_A   = score.getKubo_score_a();
			kubo_score_B   = score.getKubo_score_b();
			bur_sol_num    = score.getMx_solicitud_buro();
			
			is_score_ENABLED = kubo_rate    != null 
						    && kubo_score_A != null
						    && kubo_score_B != null
						    && kubo_rate.trim().length()    > 0				 
						    && kubo_score_A.trim().length() > 0 				 
						    && kubo_score_B.trim().length() > 0;
						    
			if(is_score_ENABLED)
			{				
				lista_proyect_loan = servicioProyecto.getProyectLoansListByBurSolNum(bur_sol_num);
				
				if(lista_proyect_loan != null && lista_proyect_loan.size() > 0)
				{
					muestraPreaprobacion      = true;
					is_credit_history_ENABLED = false;
					
					updateAccess();
				}			
			}
		}
		
		return is_credit_history_ENABLED;
	}
	
	protected void init_credit_history()
	{
		if(is_credit_history_ENABLED)
		{
			credit_history_PK = new CreditHistoryPK();
			
			credit_history_PK.setCompany_id(company_id);
			credit_history_PK.setProspectus_id(prospectus_id);
									
			credithistory = service_credit_history.getCreditHistoryById(credit_history_PK);
			
			if(credithistory != null)
			{				
				has_targeta_departamental = credithistory.getDepcc_is_principal() == null ? 0 : credithistory.getDepcc_is_principal();

				hasHistory = true;
				
			} else {
				
				credithistory = new CreditHistory();
				credithistory.setCreditHistoryPK(credit_history_PK);			
				credithistory.setMortgage_is_principal(0);
				credithistory.setCar_is_principal(0);
				
				service_credit_history.add(credithistory);
				
				hasHistory = true;
			}
		}else{
			
			credit_history_PK = new CreditHistoryPK();
			
			credit_history_PK.setCompany_id(company_id);
			credit_history_PK.setProspectus_id(prospectus_id);
									
			credithistory = service_credit_history.getCreditHistoryById(credit_history_PK);
			
			if(credithistory == null)
			{
			
				credit_history_PK = new CreditHistoryPK();
				
				credit_history_PK.setCompany_id(company_id);
				credit_history_PK.setProspectus_id(prospectus_id);
				
				credithistory = new CreditHistory();
				credithistory.setCreditHistoryPK(credit_history_PK);			
				credithistory.setMortgage_is_principal(0);
				credithistory.setCar_is_principal(0);
				
				service_credit_history.add(credithistory);
				
				hasHistory = true;
				
			}
			
		}
	}
	
	protected void init_historial_crediticio() 
	{
		if(is_credit_history_ENABLED)
		{							
			if(has_targeta_departamental != null)
			{
				if(has_targeta_departamental == 0)
				{
					hidden2 = "none";
					
				} else {
					
					hidden2 = "block";
				}
			}						
		}
	}
	
	
	
	protected void init_person_data() 
	{
		phone   = phoneService.getPhoneByTypeByArea(prospectus_id, company_id, PARTICULAR_CELULAR, area);
		address = addressService.getMaxAddressByType(prospectus_id, company_id, 1);
		
		data = new PersonDataIMP();
		data.setService_access(accessService);
		data.setService_credit_history(service_credit_history);
		data.setSesion(sesion);
		data.setMembership(membership);
		data.setCredit_history(credithistory);
		data.setAddress(address);
		data.setPhone(phone);
		data.init();
		
		bank_engine = new SearchRequestIMP();
		bank_engine.setService_bank(bankService);
		
		creditcard = new CreditCardIMP();
		creditcard.setService_credit_history(service_credit_history);
		creditcard.setService_change_control(service_change_control);
		creditcard.setService_bank(bankService);
		creditcard.setCredit_history(credithistory);
		creditcard.setSesion(sesion);
		
		mortage = new MortageIMP();
		mortage.setService_credit_history(service_credit_history);
		mortage.setService_change_control(service_change_control);
		mortage.setService_bank(bankService);
		mortage.setCredit_history(credithistory);
		mortage.setSesion(sesion);
		
		car = new CarIMP();
		car.setService_credit_history(service_credit_history);
		car.setService_change_control(service_change_control);
		car.setService_bank(bankService);
		car.setCredit_history(credithistory);
		car.setSesion(sesion);
		
		consulta = new AutorizadorIMP();		
		consulta.setService_change_control(service_change_control);				
		consulta.setSesion(sesion);
	}

	protected void init_promotor_id() 
	{
		promotor_id = membership.getPromotor_id();
		
		if(promotor_id != null)
		{
			change_prospectus_id = membership.getPromotor().getMembership().getMembershipPK().getProspectus_id();
			
		} else {
			
			change_prospectus_id = prospectus_id;
		}
	}
			
	public void saveCreditHistory()
	{				
		if(hasHistory)
		{
			service_credit_history.update(credithistory);
			
		} else {
			
			service_credit_history.add(credithistory);
		}
		
		System.out.println("CreditHistoryControllerAMO.saveCreditHistory(): " + hasHistory);
	}
	
	private void updateAccess(){
		
		Access access = new Access();
		
		access.setCompany_id( sesion.getCompany_id() );
		access.setProspectus_id(prospectus_id);
		access.setScreen_id(2);
		access.setPercentage(100);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		access.setProspectus_id_coach( sesion.getCoachProspectus_id() );
		
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, true);		
	}
	
	protected void asignar_nota_coach()
	{
		nota_coach_PK = new NotesPK(prospectus_id, company_id);
		nota_coach    = new Notes();
		
		nota_coach.setNotesPk(nota_coach_PK);
		nota_coach.setChange_date(new Date());	
		nota_coach.setChange_prospectus_id(change_prospectus_id);
		nota_coach.setProyect_id(null);
		nota_coach.setNote_type_id(NOTA_DEL_COACH);
		nota_coach.setPriority_type_id(MEDIA);
		nota_coach.setNote(nota_del_coach);	
	}
}
