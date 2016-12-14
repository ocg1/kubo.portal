package mx.com.kubo.mesa.solicitud.adicional;

import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.DocumentsReviewRequest;
import com.soa.webServices.responses.WsSgbResponse;
import com.soa.webServices.util.InputParam;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.mesa.solicitud.adicional.TipoCreditoAdicional;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ServiceCalling;

public abstract class ReasignadorPMO extends ReasignadorAMO
{
	protected void crear_proyect_loan_NEW(TipoCreditoAdicional tipo_credito_adicional, int loan_type)
	{		
		asignar_valores_a_copiar();			
						
		proyect_loan_NEW = new ProyectLoan();
		
		switch(tipo_credito_adicional)
		{			
		
			case RENOVACION_APROBACION_AUTOMATICA:								
				/*
				SimulatorBean simulation = simulatorService.getMaxSimulationProspect(prospectus_id, company_id);
				
				allocater = new ScoreAllocIMP();
				allocater.setProyect_loan(proyect_loan);
				allocater.setScore(score);
				allocater.setSimulation(simulation);				
				allocater.init();
				
				proyect_loan_NEW = allocater.getProyect_loan();
				
				asignar_tipo_de_credito(loan_type);
				asignar_proyect_NEW_PK(company_id, prospectus_id);
*/				
			break;
		
			case NUEVA_CONSULTA_ENABLED:			
				asignar_valores_copiados_con_consulta();
				asignar_tipo_de_credito(loan_type);
				
				asignar_proyect_NEW_PK(company_id, prospectus_id);
				
				scoreService.removeScoring(prospectus_id, company_id);
				
				registrar_bitacora_accesso(8, 0,   company_id, prospectus_id);
				registrar_bitacora_accesso(4, 0,   company_id, prospectus_id);
				
				realizaSimulacion(company_id, prospectus_id);
				
				registrar_bitacora_accesso(2, 90, company_id, prospectus_id);																									
			break;
						
			case NUEVA_CONSULTA_DISABLED:									
				asignar_valores_copiados_sin_consulta();
				asignar_tipo_de_credito(loan_type);
				
				asignar_proyect_NEW_PK(company_id, prospectus_id);
				realizaSimulacion(company_id, prospectus_id);
				
				registrar_bitacora_accesso(8, 0, company_id, prospectus_id);
				registrar_bitacora_accesso(4, 0, company_id, prospectus_id);
				registrar_bitacora_accesso(2, 90, company_id, prospectus_id);
			break;
			
			case RENOVACION:
				asignar_valores_copiados_sin_consulta();
				asignar_tipo_de_credito(loan_type);
				
				proyect_loan_NEW.setOpening_commission        (0D);
				proyect_loan_NEW.setOpening_commission_amount (0D);
				proyect_loan_NEW.setIs_collection_solution("S");
				
				asignar_proyect_ORIGINAL_PK(company_id, prospectus_id);
				
				registrar_bitacora_accesso(8, 0, company_id, prospectus_id);
				registrar_bitacora_accesso(4, 0, company_id, prospectus_id);								
			break;
		}
		
		if(bursolnum == null)
		{
			bursolnum = "";
		}
		
		System.out.println("AddiotionalCreditaMO.asignarProyect_loan():");
		System.out.println("solicitud_buro = " + bursolnum);
		
		proyectloanService.add(proyect_loan_NEW);
	}

	protected void registrar_change_control(ChangeBean change_control_bean, int emisor_prospectus_id) 
	{
		switch(change_control_bean.getChange_control())
		{
			case CREDITO_ADICIONAL_CON_CONSULTA:								
				change_control_bean.setWhyChangeData("CREDITO_ADICIONAL_CON_CONSULTA - " + proyect_loan_NEW.getLoan_type());
			break;
			
			case CREDITO_ADICIONAL_SIN_CONSULTA:								
				change_control_bean.setWhyChangeData("CREDITO_ADICIONAL_SIN_CONSULTA - " + proyect_loan_NEW.getLoan_type());								
			break;
			
			case RENOVACION_CREDITO:
				change_control_bean.setWhyChangeData("RENOVACION_CREDITO - " + proyect_loan_NEW.getLoan_type());
			break;
			
			default: break;
		}
		
		change_control_bean.setNewValue(Integer.toString(proyect_loan_NEW.getProyectloanPk().getProyect_loan_id()));
		change_control_bean.setOrigValue(Integer.toString(proyect_loan_id_ORIGINAL));	
							
		registrarChangeControl(change_control_bean, company_id, prospectus_id, emisor_prospectus_id);
	}
	
	protected void init_documents_review(Proyect proyect) throws RemoteException, ServiceException 
	{
		locator = new WsSgbRiskServiceLocator();
		service = locator.getWsSgbRisk();
		
		srvCall = new ServiceCalling();
		res     =  new WsSgbResponse();
		
		int company_id      = proyect_loan.getProyectloanPk().getCompany_id();
		int prospectus_id   = proyect_loan.getProyectloanPk().getProspectus_id();
		int proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		NaturalPerson person = proyect_loan.getPerson();
		
		lista_archivos = filesService.getListFilesByProspect(prospectus_id, company_id, proyect_loan_id);
		
		proyect = validaPurpose(proyect);
		
		if(lista_archivos.size() > 0)
		{
			com.soa.webServices.util.Files[] files = null ;
			
			if( person.getIdentification_type_id() != null && (person.getIdentification_type_id().intValue() == 3 || person.getIdentification_type_id().intValue() == 4 || person.getIdentification_type_id().intValue() == 5 || person.getIdentification_type_id().intValue() == 6 ) ){
			
				files = new com.soa.webServices.util.Files[lista_archivos.size() + 1];
			
			}else{
				files = new com.soa.webServices.util.Files[lista_archivos.size()];
			}
			
			int i = 0;
			
			for(Files file : lista_archivos )
			{
				params = null;
				
				 if(file.getFilesPk().getFile_type_id() == 1 || ( ( person.getIdentification_type_id() != null && (person.getIdentification_type_id().intValue() == 3 || person.getIdentification_type_id().intValue() == 4 || person.getIdentification_type_id().intValue() == 5 || person.getIdentification_type_id().intValue() == 6 )) && ( file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 133 || file.getFilesPk().getFile_type_id().intValue() == 76 ) ) )
				 {
					 params = new InputParam[5];
/*
				     params[0] = new InputParam("1", "6", "Clave de elector", person.getMx_ife_cveelector());
				     params[1] = new InputParam("1", "7", "Emisión",          person.getMx_ife_numemision() +"");
				     params[2] = new InputParam("1", "10", "ocr",             person.getMx_ife_numvertical() );
				     params[3] = new InputParam("1", "11", "seccion",         person.getMx_ife_seccion() );  
*/
					 String name = "";
					 
					if (person.getMiddle_name()!=null&&!person.getMiddle_name().isEmpty())
					{
						name = person.getFirst_name().trim() + " " + person.getMiddle_name().trim();
						
					} else {
						
						name = person.getFirst_name().trim();
					}
					
					if(person.getFather_last_name()!=null&&!person.getFather_last_name().isEmpty())
					{
						name = name + " " + person.getFather_last_name();
					}
					
					if(person.getMother_last_name()!=null&&!person.getMother_last_name().isEmpty())
					{
						name = name + " " + person.getMother_last_name();
					}
				     
					 params[0] = new InputParam("1", null, "1", name);
					 params[1] = new InputParam("1", null, "2", person.getMx_curp());
					 params[2] = new InputParam("1", "6",  "3", person.getMx_ife_cveelector());
					 params[3] = new InputParam("1", "7",  "4", person.getMx_ife_numemision() +"");
					 params[4] = new InputParam("1", "11", "11",person.getMx_ife_seccion() );
				     
				 } else if(file.getFilesPk().getFile_type_id() == 42) {
					 
					 params    = new InputParam[1];
				     params[0] = new InputParam("1", "10", "9", person.getMx_ife_numvertical());
				 }
				 
				 if(( person.getIdentification_type_id() != null && (person.getIdentification_type_id().intValue() == 3 || person.getIdentification_type_id().intValue() == 4 || person.getIdentification_type_id().intValue() == 5 || person.getIdentification_type_id().intValue() == 6 )) && ( file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 132 || file.getFilesPk().getFile_type_id().intValue() == 133 || file.getFilesPk().getFile_type_id().intValue() == 76 ) ){
				 					
					 files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), "1", file.getLocation(), params, null, proyect.getProyectoPk().getProspectus_id()+"");
					 i++;
				
					 files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), "42", file.getLocation(), params, null, proyect.getProyectoPk().getProspectus_id()+"");
					 i++;
				
				}else{
				
					files[i] =  new com.soa.webServices.util.Files(null, null, file.getFileType().getName(), file.getFilesPk().getFile_type_id()+"", file.getLocation(), params, null, proyect.getProyectoPk().getProspectus_id()+"");
					i++;	
				
				}
			}
			
			DocumentsReviewRequest docs = new DocumentsReviewRequest(files,proyect_loan.getProyectloanPk().getProyect_loan_id()+"");

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.documentsReview");
			srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(1);
			
			service_calling.saveServiceCall(srvCall);
			
			res = service.documentsReview(docs );
			
			if(res.getStatus().equals("0"))
			{					
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.documentsReview: "+((res.getMessage().length()>120)?res.getMessage().substring(0,120):res.getMessage()));
				srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
				srvCall.setStatus(2);
				
				service_calling.saveServiceCall(srvCall);
									
			} else {
				
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
				srvCall.setException(((res.getMessage().length()>120)?res.getMessage().substring(0,120):res.getMessage()));
				srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
				srvCall.setStatus(3);
				
				service_calling.saveServiceCall(srvCall);
				//return false;
			}			
		}
	}
	
/*	
	protected boolean init_new_project(Proyect proyect, ProyectLoan proyect_loan) throws RemoteException 
	{
		
		srvCall = new ServiceCalling();

		srvCall.setAcces_datetime(new Date());
		srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
		srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.newProject");
		srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
		srvCall.setStatus(1);
			
		service_calling.saveServiceCall(srvCall);
		
		prospectId = Integer.toString(proyect.getProyectoPk().getProspectus_id());
		projectId  = Integer.toString(proyect_loan.getProyectloanPk().getProyect_loan_id());
		productId  = Integer.toString(proyect.getType_id());			
		companyId  = Integer.toString(proyect_loan.getProyectloanPk().getCompany_id());
		mxFrec     = Integer.toString(proyect_loan.getFrequency_id());
		mxNumPagos = Integer.toString(proyect_loan.getTerm_id());
		amount     = Double.toString(proyect_loan.getAmmount());
		mxTasa     = Double.toString(proyect_loan.getRate());
		mxComisionApertura = Double.toString(proyect_loan.getOpening_commission());
		
		if( proyect_loan.getIs_prospector_score() != null && proyect_loan.getIs_prospector_score().equals("S") ){
			bursolnum = "PROSPECTOR_INVALID";
		}else{
			bursolnum = proyect_loan.getMx_solicitud_buro();
		}
		loan_type = proyect_loan.getLoan_type();
		is_collection_solution = proyect_loan.getIs_collection_solution();
		
				
		res = service.newProject(prospectId, projectId, productId, amount, companyId, mxTasa, mxFrec, mxNumPagos, mxComisionApertura, bursolnum, loan_type, is_collection_solution);
		
		if(res.getStatus().equals("0"))
		{
			
			srvCall = new ServiceCalling();

			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.newProject: "+((res.getMessage().length()>120)?res.getMessage().substring(0,120):res.getMessage()));
			srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(2);

			service_calling.saveServiceCall(srvCall);
			
//			proyect_loan.setStatus_id(1);
//			proyect_loan.setDay_published(new Date());
//			
//			proyectloanService.update(proyect_loan);
			
			if( membership == null ){
			
				MembershipPK mpk = new MembershipPK();
				
				mpk.setCompany_id(company_id);
				mpk.setProspectus_id(prospectus_id);
				
				membership =  membershipservice.getMembershipById(mpk);
				
			}
			
				if( membership.getFile_creation_date() == null )
				{
					membership.setFile_creation_date(new Date());
					membershipservice.update(membership);
				}
			
			
			
			if( proyect_loan.getStatus_id() != 11 ){
			
				return proyectloanService.cambioStatus(proyect_loan, 1, new Date());
			
			}else{
				
				return proyectloanService.cambioStatus(proyect_loan, 11, new Date());
				
			}
			
			
		} else {
			
			srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(proyect_loan.getProyectloanPk().getCompany_id());
			srvCall.setException(((res.getMessage().length()>120)?res.getMessage().substring(0,120):res.getMessage()));
			srvCall.setProspectus_id(proyect_loan.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(3);
			
			service_calling.saveServiceCall(srvCall);
			
			return false;
		}
	}
*/
}
