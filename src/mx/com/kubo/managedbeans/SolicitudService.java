//package mx.com.kubo.managedbeans;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.el.ELContext;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//
//import com.soa.webServices.WsSgbRisk;
//import com.soa.webServices.WsSgbRiskServiceLocator;
//import com.soa.webServices.request.DocumentsReviewRequest;
//import com.soa.webServices.responses.WsSgbResponse;
//
//import mx.com.kubo.model.Files;
//import mx.com.kubo.model.Proyect;
//import mx.com.kubo.model.ProyectLoan;
//
//import mx.com.kubo.model.Scoring;
//import mx.com.kubo.model.ServiceCalling;
//import mx.com.kubo.model.ViewClientInfo;
//import mx.com.kubo.services.ProyectLoanService;
//import mx.com.kubo.services.ProyectService;
//import mx.com.kubo.services.ScoringService;
//import mx.com.kubo.services.ServiceCallingService;
//import mx.com.kubo.services.ViewClientInfoService;
//import mx.com.kubo.services.impl.FilesServiceImp;
//
//import safisrv.ws.schemas.AltaClienteRequest;
//import safisrv.ws.schemas.SAFIServicios;
//import safisrv.ws.schemas.SAFIServiciosServiceLocator;
//import safisrv.ws.schemas.SolicitudCreditoRequest;
//import safisrv.ws.schemas.SolicitudCreditoResponse;
//
//@ManagedBean
//@RequestScoped
//public class SolicitudService {
//	
//	@ManagedProperty("#{proyectLoanServiceImp}")
//	private ProyectLoanService proyectloanservice;
//	
//	@ManagedProperty("#{proyectServiceImp}")
//	private ProyectService proyectservice;
//	
//	@ManagedProperty("#{scoringServiceImp}")
//	private ScoringService scoringservice;
//	
//	@ManagedProperty("#{serviceCallingServiceImp}")
//	private ServiceCallingService servicecallingService;
//	
//	@ManagedProperty("#{viewClientInfoServiceImp}")
//	private ViewClientInfoService viewclientinfoservice;
//	
//	@ManagedProperty("#{filesServiceImp}")
//	private FilesServiceImp filesservice;
//
//	public void creaSolicitud(){
//		try{
//			
//			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//			SessionBean sesion 
//			    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
//			        .getELResolver().getValue(elContext, null, "sessionBean");
//			
//			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
//			SAFIServicios service = locator.getSAFIServiciosSoap11();
//			
//			Scoring score = scoringservice.loadScoringByProspectus(sesion.getProspectus_id(),sesion.getCompany_id());
//			
//			SolicitudCreditoRequest sol = new SolicitudCreditoRequest();
//			
//			ProyectLoan pl = proyectloanservice.getMaxProyectLoanByProspectDontPublish(sesion.getProspectus_id(),sesion.getCompany_id());	
//			
//			/*ProyectPK pyPK = new ProyectPK();
//			pyPK.setCompany_id(company_id);
//			pyPK.setProspectus_id(prospectus_id);
//			pyPK.setProyect_id(pl.getProyect())
//			Proyect py = proyectservice.getProyectById(pk);*/
//			
//			if(pl == null)
//				return;
//			
//			Proyect py = pl.getProyect();
//			
//			sol.setCalificacion(score.getKubo_score_a()+score.getKubo_score_b());
//			sol.setClienteID("");
//			sol.setCuentaClabe("");
//				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//				String day = f.format(new Date());
//			sol.setFechaRegistro(day);
//			sol.setMontoSolici(pl.getAmmount()+"");
//			String freq = "";
//			
//			switch (pl.getFrequency_id()){
//				case 1:
//					freq = "S";
//					break;
//				case 2:
//					freq = "C";
//					break;
//				case 3:
//					freq = "Q";
//					break;
//				case 4:
//					freq = "M";
//					break;
//			}
//			
//			sol.setPeriodicidad(freq);
//			sol.setPlazo(pl.getTerm_id()+"");
//			sol.setProductoCreditoID(py.getType_id()+"");
//			sol.setProspectoID(pl.getProyectloanPk().getProspectus_id()+"");
//			sol.setTasaActiva(score.getRate()+"");
//			sol.setTipoDispersion("S");
//			
//			ServiceCalling srvCall = new ServiceCalling();
//
//			srvCall.setAcces_datetime(new Date());
//			srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.solicitudCredito");
//			srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//			srvCall.setStatus(1);
//			
//			servicecallingService.saveServiceCall(srvCall);
//			
//			SolicitudCreditoResponse res = service.solicitudCredito(sol);
//			
//			if(res.getCodigoRespuesta().equals("0")){
//				
//				srvCall = new ServiceCalling();
//
//				srvCall.setAcces_datetime(new Date());
//				srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//				srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.getProspectIDProvider: "+res.getMensajeRespuesta());
//				srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//				srvCall.setStatus(2);
//				
//				servicecallingService.saveServiceCall(srvCall);
//				
//				System.out.println("regresando call WebService: solicitud: "+res.getSolicitudCreditoID());
//				
//				pl.setSafi_mx_solicitud_id(Integer.parseInt(res.getSolicitudCreditoID()));
//				
//				pl.setStatus_id(1);
//				
//				proyectloanservice.update(pl);
//				
//				
//			}else{
//				srvCall = new ServiceCalling();
//				
//				srvCall.setAcces_datetime(new Date());
//				srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//				srvCall.setException(res.getMensajeRespuesta());
//				srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//				srvCall.setStatus(3);
//				servicecallingService.saveServiceCall(srvCall);
//			}
//			
//			callSGB(py,pl);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	private void callSGB(Proyect py, ProyectLoan pl){
//		try{
//			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
//			WsSgbRisk service = locator.getWsSgbRisk();
//			ServiceCalling srvCall = new ServiceCalling();
//			WsSgbResponse res =  new WsSgbResponse();
//			
//			List<Files> f = filesservice.getListFilesByProspect(py.getProyectoPk().getProspectus_id(), py.getProyectoPk().getCompany_id());
//			
//			if(f.size()>0){
//				com.soa.webServices.util.Files[] files = new com.soa.webServices.util.Files[f.size()];
//				int i = 0;
//				for(Files file : f ){
//					files[i] = new  com.soa.webServices.util.Files(file.getFile_type_id()+"",file.getLocation(),file.getFilesPk().getProspectus_id()+"");
//				}
//				
//				DocumentsReviewRequest docs = new DocumentsReviewRequest(files);
//				
//				
//				
//
//				srvCall.setAcces_datetime(new Date());
//				srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//				srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.saveServiceCall");
//				srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//				srvCall.setStatus(1);
//				
//				servicecallingService.saveServiceCall(srvCall);
//				
//				res = service.documentsReview(docs );
//				
//				if(res.getStatus().equals("0")){
//					
//					srvCall = new ServiceCalling();
//
//					srvCall.setAcces_datetime(new Date());
//					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//					srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.saveServiceCall: "+res.getMessage());
//					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//					srvCall.setStatus(2);
//					servicecallingService.saveServiceCall(srvCall);
//					
//					
//				}else{
//					srvCall = new ServiceCalling();
//					
//					srvCall.setAcces_datetime(new Date());
//					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//					srvCall.setException(res.getMessage());
//					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//					srvCall.setStatus(3);
//					servicecallingService.saveServiceCall(srvCall);
//				}
//				
//			}
//				
//				srvCall = new ServiceCalling();
//
//				srvCall.setAcces_datetime(new Date());
//				srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//				srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.newProject");
//				srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//				srvCall.setStatus(1);
//				servicecallingService.saveServiceCall(srvCall);
//				
//				 res = service.newProject((py.getProyectoPk().getProspectus_id()+""), (py.getProyectoPk().getProyect_id()+""), (py.getType_id()+""), (pl.getAmmount()+""));
//				
//				if(res.getStatus().equals("0")){
//					
//					srvCall = new ServiceCalling();
//
//					srvCall.setAcces_datetime(new Date());
//					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//					srvCall.setInfo("Regresando Satisfactoriamente de Servicio WsSgbRiskServiceLocator.getWsSgbRisk.newProject: "+res.getMessage());
//					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//					srvCall.setStatus(2);
//
//					servicecallingService.saveServiceCall(srvCall);
//					
//					pl.setStatus_id(1);
//					
//					proyectloanservice.update(pl);
//					
//					
//				}else{
//					srvCall = new ServiceCalling();
//					
//					srvCall.setAcces_datetime(new Date());
//					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
//					srvCall.setException(res.getMessage());
//					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
//					srvCall.setStatus(3);
//					servicecallingService.saveServiceCall(srvCall);
//				}
//				
//				
//		
//		}catch(Exception e){
//			
//		}
//	}
//
//	public ProyectLoanService getProyectloanservice() {
//		return proyectloanservice;
//	}
//
//	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
//		this.proyectloanservice = proyectloanservice;
//	}
//
//	public ScoringService getScoringservice() {
//		return scoringservice;
//	}
//
//	public void setScoringservice(ScoringService scoringservice) {
//		this.scoringservice = scoringservice;
//	}
//
//	public ServiceCallingService getServicecallingService() {
//		return servicecallingService;
//	}
//
//	public void setServicecallingService(ServiceCallingService servicecallingService) {
//		this.servicecallingService = servicecallingService;
//	}
//
//	public ProyectService getProyectservice() {
//		return proyectservice;
//	}
//
//	public void setProyectservice(ProyectService proyectservice) {
//		this.proyectservice = proyectservice;
//	}
//	
//	
//	public void creaCliente(){
//		try{
//			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//			SessionBean sesion 
//			    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
//			        .getELResolver().getValue(elContext, null, "sessionBean");
//			
//			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
//			SAFIServicios service = locator.getSAFIServiciosSoap11();
//			
//			AltaClienteRequest client = new AltaClienteRequest();
//			
//			ViewClientInfo view =  viewclientinfoservice.getClientInfo(sesion.getProspectus_id(),sesion.getCompany_id());
//		
//			client.setActividadBancoMX((view.getActividadBancoMX()==null||view.getActividadBancoMX().equals(" "))?"":view.getActividadBancoMX());
//			client.setActividadINEGI((view.getActividadINEGI()==null||view.getActividadINEGI().equals(" "))?"":(view.getActividadINEGI()+""));
//			client.setAntiguedadTra((view.getAntiguedad_trabajo()==null||view.getAntiguedad_trabajo().equals(" "))?"":(view.getAntiguedad_trabajo()+""));
//			client.setClasificacion((view.getClasificacion()==null||view.getClasificacion().equals(" "))?"":view.getClasificacion());
//			client.setCorreo((view.getCorreo()==null||view.getCorreo().equals(" "))?"":view.getCorreo());
//			client.setCURP((view.getCurp()==null||view.getCurp().equals(" "))?"":view.getCurp());
//			client.setEstadoCivil((view.getEstado_civil()==null||view.getEstado_civil().equals(" "))?"":view.getEstado_civil());
//			client.setEstadoID((view.getEstadoID()==null||view.getActividadBancoMX().equals(" "))?"":view.getActividadBancoMX());
//			client.setFax((view.getFax()==null||view.getFax().equals(" "))?"":view.getFax());
//			
//			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
//			
//			String fechaNacimiento ="";
//			
//			if(view.getFecha_nacimiento()!=null){
//				format.format(view.getFecha_nacimiento());
//			}
//			
//			
//			client.setFechaNacimiento((fechaNacimiento==null||fechaNacimiento.equals(" "))?"":fechaNacimiento);
//			client.setGrupoEmpresarial((view.getGrupo_empresarial()==null||view.getGrupo_empresarial().equals(" "))?"":view.getGrupo_empresarial());
//			client.setLugarNacimiento((view.getLugar_nacimiento()==null||view.getLugar_nacimiento().equals(" "))?"":view.getLugar_nacimiento());
//			client.setLugarTrabajo((view.getLugar_trabajo()==null||view.getLugar_trabajo().equals(" "))?"":view.getLugar_trabajo());
//			client.setMotivoApertura((view.getMotivo_apertura()==null||view.getMotivo_apertura().equals(" "))?"":view.getMotivo_apertura());
//			client.setNacion((view.getNacion()==null||view.getNacion().equals(" "))?"":view.getNacion());
//			client.setNivelRiesgo((view.getNivelRiesgo()==null||view.getNivelRiesgo().equals(" "))?"":view.getNivelRiesgo());
//			client.setNombreConyuge((view.getNombre_conyuge()==null||view.getNombre_conyuge().equals(" "))?"":view.getNombre_conyuge());
//			client.setOcupacionID((view.getOcupacionID()==null||view.getOcupacionID().equals(" "))?"":(view.getOcupacionID()+""));
//			client.setPagaIDE((view.getPagaIDE()==null||view.getPagaIDE().equals(" "))?"":view.getPagaIDE());
//			client.setPagaISR((view.getPagaISR()==null||view.getPagaISR().equals(" "))?"":view.getPagaISR());
//			client.setPagaIVA((view.getPagaIVA()==null||view.getPagaIVA().equals(" "))?"":view.getPagaIVA());
//			client.setPaisResidencia((view.getPais_de_residencia()==null||view.getPais_de_residencia().equals(" "))?"":(view.getPais_de_residencia()+""));
//			client.setPromotorActual((view.getPromotor_actual()==null||view.getPromotor_actual().equals(" "))?"":view.getPromotor_actual());
//			client.setPromotorInicial((view.getPromotor_inicial()==null||view.getPromotor_inicial().equals(" "))?"":view.getPromotor_inicial());
//			client.setProspectoID((view.getProspecto_id()==null||view.getProspecto_id().equals(" "))?"":(view.getProspecto_id()+""));
//			client.setPuesto((view.getPuesto()==null||view.getPuesto().equals(" "))?"":view.getPuesto());
//			client.setRFC_Conyuge((view.getRfc_conyuge()==null||view.getRfc_conyuge().equals(" "))?"":view.getRfc_conyuge());
//			client.setSectorEconomico((view.getSectorEconomico()==null||view.getSectorEconomico().equals(" "))?"":(view.getSectorEconomico()+""));
//			client.setSectorGeneral((view.getSector_general()==null||view.getSector_general().equals(" "))?"":(view.getSector_general()+""));
//			client.setSexo((view.getSexo()==null||view.getSexo().equals(" "))?"":view.getSexo());
//			client.setSucursalOrigen((view.getSucursalOrigen()==null||view.getSucursalOrigen().equals(" "))?"":(view.getSucursalOrigen()+""));
//			client.setTelefonoCelular((view.getTelefono_celular()==null||view.getTelefono_celular().equals(" "))?"":view.getTelefono_celular());
//			client.setTelTrabajo((view.getTelefono_trabajo()==null||view.getTelefono_trabajo().equals(" "))?"":view.getTelefono_trabajo());
//			client.setTipoSociedadID((view.getTipoSociedadID()==null||view.getTipoSociedadID().equals(" "))?"":(view.getTipoSociedadID()+""));
//			client.setTitulo((view.getTitulo()==null||view.getTitulo().equals(" "))?"":view.getTitulo());
//		
//			//service.altaCliente(client);
//	
//		
//		}catch(Exception e){
//			
//		}
//	}
//
//	public ViewClientInfoService getViewclientinfoservice() {
//		return viewclientinfoservice;
//	}
//
//	public void setViewclientinfoservice(ViewClientInfoService viewclientinfoservice) {
//		this.viewclientinfoservice = viewclientinfoservice;
//	}
//
//	public FilesServiceImp getFilesservice() {
//		return filesservice;
//	}
//
//	public void setFilesservice(FilesServiceImp filesservice) {
//		this.filesservice = filesservice;
//	}
//	
//}
