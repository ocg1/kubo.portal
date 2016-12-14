package mx.com.kubo.managedbeans;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mx.com.kubo.bean.ConsultingErrorBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.model.ConsultingManualPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;

public abstract class ConsultingControllerAMO extends ConsultingControllerDMO
{
	protected void crear_proyect_loan( Membership memberSel_tmp ) 
	{		
		if(consultaBuro)
		{
			try
			{
				Double amount        = 0D;
				Double min_amount    = 0D;
				Double payment       = 0D;
				Double cat           = 0D;
				Integer frequency_id = 0;
				Integer term_id      = 0;
				
				consulting_prospectus_id = memberSel_tmp.getMembershipPK().getProspectus_id();
				prospectus_id = memberSel_tmp.getMembershipPK().getProspectus_id();
				company_id    = memberSel_tmp.getMembershipPK().getCompany_id();
				
				proyect_id = proyectService.getMaxProyectID();
				
				simulador = simulatorService.getMaxSimulationProspect(prospectus_id, company_id);
				
				if(simulador != null){
				
					amount        = simulador.getAmmount();
					min_amount    = simulador.getAmmount();
					payment       = simulador.getPayment();
					cat           = simulador.getMx_cat();
					frequency_id = simulador.getFrequency_id();
					term_id      = simulador.getTerm_id();
				
				}else{
					
					
					
					simulator.simulaCred(false);
					
					amount        = simulator.getAmmount();
					min_amount    = simulator.getAmmount();
					payment       = simulator.getMontoCuota();
					cat           = simulator.getCat();
					frequency_id = simulator.getFrequency_id();
					term_id      = simulator.getTerm_id();
					
				}
												
				String bur_sol_num       = score.getMx_solicitud_buro();
				String kubo_score_A      = score.getKubo_score_a() == null ? "" : score.getKubo_score_a();
				String kubo_score_B      = score.getKubo_score_b() == null ? "" : score.getKubo_score_b();
				String cci_score         = score.getCci_score();
				
				Integer bc_score         = Integer.parseInt(score.getBc_score());
				
				Double rate              = score.getRate();
				Double rate_opening      = score.getRate();
				Double rate_investor     = score.getRate_investor();
				Double comision_apertura = score.getOpening_commission();
												
				proyect      = new Proyect();
				proyect_pk   = new ProyectPK(proyect_id, prospectus_id, company_id);
				proyect_loan = new ProyectLoan();
				
				if(simulador != null)
				{
					if( simulador.getPurpose_id() != null ){
						
						proyect.setPurpose_id(simulador.getPurpose_id());
						
					}else{
						
						proyect.setPurpose_id( 1 );
						
					}
				
				} else {
					
					//proyect.setPurpose_id(simulator.getPurpose_id());
					
					if( simulator.getPurpose_id() != 0 ){
						
						proyect.setPurpose_id(simulator.getPurpose_id());
						
					}else{
						
						proyect.setPurpose_id( 1 );
						
					}
					
				}
				
				proyect.setProyectoPk(proyect_pk);
				
				proyect_loan.setDay_published(null);
				proyect_loan.setConsulting_date(new Date());
				proyect_loan.setDays_online(15);	
				proyect_loan.setLoan_type("NVO");
				proyect_loan.setOpening_payment("D");
				proyect_loan.setFunding_type('T');
				
				proyect_loan.setRate_with_opening(rate_opening);
				proyect_loan.setOpening_commission_amount(comision_apertura);
				
				proyect_loan.setBc_score(bc_score);
				proyect_loan.setKubo_score_a(kubo_score_A);
				proyect_loan.setKubo_score_b(kubo_score_B);	
				proyect_loan.setCci_score(cci_score);
								
				proyect_loan.setAmmount(amount);
				proyect_loan.setFrequency_id(frequency_id);
				proyect_loan.setMx_cat(cat);
				
				proyect_loan.setMx_solicitud_buro(bur_sol_num);
				proyect_loan.setMin_ammount(min_amount);								
				proyect_loan.setPayment(payment);
				proyect_loan.setRate_investor(rate_investor);												
				
				is_proyect_OK = proyectService.add(proyect);
				
				if(is_proyect_OK)
				{
					proyect_loan_PK = new ProyectLoanPK();
					
					proyect = proyectService.getMaxProyect(prospectus_id, company_id);
					
					proyect_id = proyect.getProyectoPk().getProyect_id();
					
					proyect_loan_PK.setCompany_id(company_id);
					proyect_loan_PK.setProspectus_id(prospectus_id);
					proyect_loan_PK.setProyect_id(proyect_id);
					proyect_loan_PK.setProyect_loan_id(0);	
					
					proyect_loan.setProyectloanPk(proyect_loan_PK);
					
					proyect_loan.setRate(rate);
					proyect_loan.setStatus_id(0);									
					proyect_loan.setTerm_id(term_id);
					proyect_loan.setDeposit_method_id(1);									
					proyect_loan.setMx_solicitud_buro(bur_sol_num);
					
					proyectloanService.add(proyect_loan);
					
					proyect_loan_PK = proyect_loan.getProyectloanPk();
					
					proyect_loan = proyectloanService.getProyectLoanById(proyect_loan_PK);					
				}
				
				init_manual_consulting(bur_sol_num);
				
			} catch(Exception e) {
							
				writer      = new StringWriter();
				printWriter = new PrintWriter(writer);
				
				e.printStackTrace(printWriter);
				
				msg = e.getMessage();
				
				evento = Evento.ERROR_DESARROLLO;
				evento.setError("managedbeans.ConsultingController.createConsulting(282): " + msg);
								
				notificar(evento, null, msg + " <br /> " + writer.toString(), null);
				
				e.printStackTrace();
				
				ConsultingErrorBean errorbean = new ConsultingErrorBean();
				
				errorbean.setMember( memberSel_tmp );
				errorbean.setError_descript( msg );
				
				if( lstError == null )
				{				
					lstError = new ArrayList<ConsultingErrorBean>();					
				}
				
				lstError.add( errorbean );												
			}			
		}
	}
	
	private void init_manual_consulting(String bur_sol_num) 
	{
		consulting    = new ConsultingManual();
		consulting_PK = new ConsultingManualPK();
		
		company_id    = membership.getMembershipPK().getCompany_id();
		prospectus_id = membership.getMembershipPK().getProspectus_id();
		
		consulting_PK.setCompany_id(company_id);
		consulting_PK.setProspectus_id(prospectus_id);
		
		consulting.setPk(consulting_PK);		
		consulting.setConsulting_prospectus_id(consulting_prospectus_id);		
		consulting.setConsulting_date(new Date());				
		consulting.setMx_solicitud_buro(bur_sol_num);
		consulting.setIs_consulting_for_renovation("N");
		
		consultingmanualservice.saveConsultingManual(consulting);
	}

	protected boolean callSGBAltaUsuario( Membership membership_temp )
	{
		
		int prospectus_id_tmp 	= membership_temp.getMembershipPK().getProspectus_id();
		int company_id_tmp		= membership_temp.getMembershipPK().getCompany_id();
		
		System.out.println("+++++    LLAMANDO AL SGB    +++++ usuario: "+prospectus_id_tmp);
		
		AddressPK addressPk = new AddressPK();
		
		addressPk.setCompany_id(company_id_tmp);
		addressPk.setProspectus_id(prospectus_id_tmp);
		
		Address thisAddress = addressService .getMaxAddressByType(prospectus_id_tmp, company_id_tmp, 1);
		
		gnNaturalPersonPK natperPk = new gnNaturalPersonPK();
		
		natperPk.setCompany_id(company_id_tmp);
		natperPk.setProspectus_id(prospectus_id_tmp);
		
		NaturalPerson naturalPerson = naturalPersonService.getNaturalPersonById( natperPk );
		
		PhonePK phonePk = new PhonePK();
		
		phonePk.setCompany_id(company_id_tmp);
		phonePk.setProspectus_id(prospectus_id_tmp);
		
		Phone thisPhoneFixed = phoneService.getPhoneByTypeByArea(prospectus_id_tmp, company_id_tmp, 5, 'L');
		
		simulador = simulatorService.getMaxSimulationProspect(prospectus_id, company_id);
		
		try 
		{
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();// yyyymmdd. 19860131

			
			
			String user       = "";
			String password   = "";
			String birthday_str   = "";
			String lada       = "";
			String phone      = "";
			String colonia    = "";
			String municipio  = "";
			String thisEstado = "";
			//String msg        = "";
			String prospectId = "" + prospectus_id_tmp;

			boolean flag = true;
			
			if( thisAddress != null ){
			
				Integer colonia_id = thisAddress.getNeighborhood_id();
				Integer ciudad_id  = thisAddress.getTown_id();
				Integer estado_id  = thisAddress.getState_id();
				
				if(colonia_id != null)
				{
					NeighborhoodCatPK nPK = new NeighborhoodCatPK();
					
					nPK.setCompany_id(company_id_tmp);
					nPK.setNeighborhood_id(colonia_id);
					
					NeighborhoodCat neig = neighborhoodService.getNeighborhoodById(nPK);
					colonia = neig.getName();
					
				} else if(thisAddress.getNeighborhood_text() != null){
					
					colonia = thisAddress.getNeighborhood_text();
					
				} else {
					msg += "La colonia no fue proporcionada. ";
					flag = false;
				}
	
				if(ciudad_id != null)
				{
					TownCatPK tPK = new TownCatPK();
					tPK.setCompany_id(company_id_tmp);
					tPK.setTown_id(ciudad_id);
					TownCat town = townService.getTownById(tPK);
					municipio = town.getName();
				} else {
					msg += "Delegacion o municipio no proporcionados. ";
					flag = false;
				}
											
				if(estado_id != null)
				{
					StateCatPK sPK = new StateCatPK();
					sPK.setCompany_id(company_id_tmp);
					sPK.setState_id(estado_id);
					StateCat state = stateService.getStateById(sPK);
					thisEstado = state.getBc_key();
				} else {
					msg += "Estado no proporcionado. ";
					flag = false;
				}
				
			}else{
				
				msg += "Datos insuficientes para el domicilio ";
				flag = false;
				
			}
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			
			if(naturalPerson.getDate_of_birth() != null)
			{
				birthday_str = format.format(naturalPerson.getDate_of_birth());				
			}else{
				msg += "Fecha de nacimiento no proporcionada. ";
				flag = false;
			}
			
			if ( thisPhoneFixed != null && thisPhoneFixed.getPhone_number()!=null && (thisPhoneFixed.getPhone_number().split("\\)")).length>1 )
			{
				String[] thisPhone = thisPhoneFixed.getPhone_number().split("\\)");
				
				lada = thisPhone[0];
				lada = lada.replace("(", "");
				lada = lada.trim();
				
				phone = thisPhone[1];
				phone = phone.replace("-", "");
				phone = phone.replace(" ", "");
				phone = phone.trim();
				
				
			}else{
				msg += "Número de teléfono no proporcionado. ";
				flag = false;
			}

			if (flag) 
			{

				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				srvCall.setInfo("Invocando Servicio Web prospectAdmin:("+
							prospectId+","+naturalPerson.getFirst_name()+","+naturalPerson.getMiddle_name()+","+naturalPerson.getFather_last_name()+","+
							naturalPerson.getMother_last_name()+","+birthday_str+","+naturalPerson.getMx_rfc()+","+thisAddress.getStreet()+","+
							thisAddress.getMx_manzana()+","+thisAddress.getMx_lote()+","+thisAddress.getAddress_number()+","+thisAddress.getApt_number()+","+
							colonia+","+municipio+","+thisEstado+","+thisAddress.getZip_code()+","+lada+","+phone+","+naturalPerson.getMx_curp()+","+
							naturalPerson.getProspectus().getTracking_id()
						+")");
				srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				srvCall.setStatus(1);
				
				servicecallingService.saveServiceCall(srvCall);
				
				double monto_Solicitado = simulator.getAmmount();
				
				System.out.println("PreaprobacionAMO(): " + monto_Solicitado);

				WsSgbResponse res = service.prospectAdmin
						( user, password,
						prospectId,// prospectId,
						naturalPerson.getFirst_name(), // firstName,
						naturalPerson.getMiddle_name(), // secondName,
						naturalPerson.getFather_last_name(),// surName,
						naturalPerson.getMother_last_name(),// aditSurName,
						birthday_str,// birthday,
						naturalPerson.getMx_rfc(),// rfc,
						thisAddress.getStreet(),// street,
						thisAddress.getMx_manzana(),// mx_manzana,
						thisAddress.getMx_lote(),// mx_lote,
						thisAddress.getAddress_number(),// mx_numExterior,
						thisAddress.getApt_number(),// mx_numInterior,
						colonia,// mx_colonia,
						municipio,// mx_municipio,
						thisEstado,// mx_estado,
						thisAddress.getZip_code(),// mx_codPostal,
						lada,// mx_lada,
						phone, // phoneNumber
						naturalPerson.getMx_curp(),// curp
						naturalPerson.getProspectus().getTracking_id(),
						monto_Solicitado
						);
				
				
				if(res != null && res.getStatus().equals("0")){
				
					srvCall = new ServiceCalling();
	
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
					srvCall.setInfo("Regresando Satisfactoriamente de Servicio Web prospectAdmin: "+res.getMessage());
					srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
					srvCall.setStatus(2);
					
					servicecallingService.saveServiceCall(srvCall);
					
					System.out.println("****************************************************************************************************");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("");
					System.out.println("SGB Web Service: ");
					System.out.println("Codigo Status: " + res.getStatus());
					System.out.println("Mensaje de Respuesta: " + res.getMessage());
					System.out.println("");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("****************************************************************************************************");
					System.out.println("fin");
					
				}else{
					
					srvCall = new ServiceCalling();
					
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
					srvCall.setException((res==null)?"Respuesta Null" : res.getMessage());
					srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
					System.out.println("****************************************************************************************************");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("");
					System.out.println((res==null)?"SGB Web Service: RESPUESTA NULL " : res.getMessage());
					System.out.println("");
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println("****************************************************************************************************");
					
					msg = (res==null)?"SGB Web Service: RESPUESTA NULL " : res.getMessage();
						
					flag= false;
					
					
					
				}
			
				
			}else{
				// setErrorMsg(msg);
			}
			
			if( !flag ){
				
				ConsultingErrorBean errorbean = new ConsultingErrorBean();
				
				errorbean.setMember( membership_temp );
				errorbean.setError_descript( msg );
				
				lstError.add( errorbean );
				
			}
			
			return flag;
			
		} catch (Exception e) {
			
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String msg = e.getMessage();
			
			Evento evento = Evento.ERROR_DESARROLLO;
			evento.setError("managedbeans.Preaprobacion.creaProspectSGB(1381): " + msg);
			
			notificar(evento, null, msg + " <br /> " + writer.toString(), null);
			
			srvCall = new ServiceCalling();

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			srvCall.setInfo("Error al ejecutar Servicio Web prospectAdmin");
			srvCall.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			srvCall.setStatus(3);
			
			String exception =  e.getStackTrace().toString().length()>990?e.getStackTrace().toString().substring(0,990):e.getStackTrace().toString();
			
			srvCall.setException(exception);
			
			servicecallingService.saveServiceCall(srvCall);
			
			ConsultingErrorBean errorbean = new ConsultingErrorBean();
			
			errorbean.setMember( membership_temp );
			errorbean.setError_descript( msg );
			
			lstError.add( errorbean );
			
			return false;
		}
	}
	
	protected void notificar(Evento evento, Scoring score,String errormsg, ProyectLoan proyect_loan )
	{
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(membership);
			notificador.setAcreditado(memberSel);
			notificador.notificar(evento, score, proyect_loan, errormsg);
			
		} catch (NotificacionException e) {			
			
			e.printStackTrace();
		}					
	}
}
