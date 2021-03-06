package mx.com.kubo.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.ServiciosSAFIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safisrv.ws.ClienteServicios.AltaClienteRequest;
import safisrv.ws.ClienteServicios.AltaClienteResponse;
import safisrv.ws.ClienteServicios.AltaConocimientoCteRequest;
import safisrv.ws.ClienteServicios.AltaConocimientoCteResponse;
import safisrv.ws.ClienteServicios.SAFIServicios;
import safisrv.ws.ClienteServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.CreaCreditoRequest;
import safisrv.ws.CreditosServicios.CreaCreditoResponse;
import safisrv.ws.CreditosServicios.SegurosVidaRequest;
import safisrv.ws.CreditosServicios.SegurosVidaResponse;
import safisrv.ws.CuentasServicios.AltaConocimientoCtaRequest;
import safisrv.ws.CuentasServicios.AltaConocimientoCtaResponse;
import safisrv.ws.CuentasServicios.AltaCuentaRequest;
import safisrv.ws.CuentasServicios.AltaCuentaResponse;
import safisrv.ws.CuentasServicios.AltaRelacionadoCtaRequest;
import safisrv.ws.CuentasServicios.AutorizaCuentaRequest;

@Service
public class ServiciosSAFIServiceImp implements ServiciosSAFIService {
	
	@Autowired
	private ServiceCallingDao servicecallingRepository;
	
	@Override
	public AltaClienteResponse createClientSAFI(ViewClientInfo view){
		
		AltaClienteResponse resAltaClient = null;
		
		try{
			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
			SAFIServicios service = locator.getSAFIServiciosSoap11();
			
			AltaClienteRequest client = new AltaClienteRequest();
			
			client.setActividadBancoMX((view.getActividadBancoMX()==null||view.getActividadBancoMX().equals(" "))?"-":view.getActividadBancoMX());
			client.setActividadINEGI((view.getActividadINEGI()==null||view.getActividadINEGI().equals(" "))?"-":(view.getActividadINEGI()+""));
			client.setAntiguedadTra((view.getAntiguedad_trabajo()==null||view.getAntiguedad_trabajo().equals(" "))?"-":(view.getAntiguedad_trabajo()+""));
			client.setClasificacion((view.getClasificacion()==null||view.getClasificacion().equals(" "))?"-":view.getClasificacion());
			client.setCorreo((view.getCorreo()==null||view.getCorreo().equals(" "))?"-":view.getCorreo());
			client.setCURP((view.getCurp()==null||view.getCurp().equals(" "))?"-":view.getCurp());
			client.setEstadoCivil((view.getEstado_civil()==null||view.getEstado_civil().equals(" "))?"-":view.getEstado_civil());
			client.setEstadoID((view.getEstadoID()==null||view.getEstadoID().equals(" "))?"-":view.getEstadoID()+"");
			client.setFax((view.getFax()==null||view.getFax().equals(" "))?"-":view.getFax());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			String fechaNacimiento ="";
			
			if(view.getFecha_nacimiento()!=null){
				fechaNacimiento = format.format(view.getFecha_nacimiento());
			}
			
			client.setFechaNacimiento((fechaNacimiento==null||fechaNacimiento.equals(" "))?"-":fechaNacimiento);
			client.setGrupoEmpresarial((view.getGrupo_empresarial()==null||view.getGrupo_empresarial().equals(" "))?"-":view.getGrupo_empresarial());
			client.setLugarNacimiento((view.getLugar_nacimiento()==null||view.getLugar_nacimiento().equals(" "))?"-":view.getLugar_nacimiento());
			client.setLugarTrabajo((view.getLugar_trabajo()==null||view.getLugar_trabajo().equals(" "))?"-":view.getLugar_trabajo());
			client.setMotivoApertura((view.getMotivo_apertura()==null||view.getMotivo_apertura().equals(" "))?"-":view.getMotivo_apertura());
			client.setNacion((view.getNacion()==null||view.getNacion().equals(" "))?"-":view.getNacion());
			client.setNivelRiesgo((view.getNivelRiesgo()==null||view.getNivelRiesgo().equals(" "))?"-":view.getNivelRiesgo());
			client.setNombreConyuge((view.getNombre_conyuge()==null||view.getNombre_conyuge().equals(" "))?"-":view.getNombre_conyuge());
			client.setOcupacionID((view.getOcupacionID()==null||view.getOcupacionID().equals(" "))?"-":(view.getOcupacionID()+""));
			client.setPagaIDE((view.getPagaIDE()==null||view.getPagaIDE().equals(" "))?"-":view.getPagaIDE());
			client.setPagaISR((view.getPagaISR()==null||view.getPagaISR().equals(" "))?"-":view.getPagaISR());
			//client.setPagaIVA((view.getPagaIVA()==null||view.getPagaIVA().equals(" "))?"-":view.getPagaIVA());
			client.setPaisResidencia((view.getPais_de_residencia()==null||view.getPais_de_residencia().equals(" "))?"-":(view.getPais_de_residencia()+""));
			client.setPromotorActual((view.getPromotor_actual()==null||view.getPromotor_actual().equals(" "))?"-":view.getPromotor_actual());
			client.setPromotorInicial((view.getPromotor_inicial()==null||view.getPromotor_inicial().equals(" "))?"-":view.getPromotor_inicial());
			client.setProspectoID((view.getProspecto_id()==null||view.getProspecto_id().equals(" "))?"-":(view.getProspecto_id()+""));
			client.setPuesto((view.getPuesto()==null||view.getPuesto().equals(" "))?"-":view.getPuesto());
			client.setRFC_Conyuge((view.getRfc_conyuge()==null||view.getRfc_conyuge().equals(" "))?"-":view.getRfc_conyuge());
			client.setSectorEconomico((view.getSectorEconomico()==null||view.getSectorEconomico().equals(" "))?"-":(view.getSectorEconomico()+""));
			client.setSectorGeneral((view.getSector_general()==null||view.getSector_general().equals(" "))?"-":(view.getSector_general()+""));
			client.setSexo((view.getSexo()==null||view.getSexo().equals(" "))?"-":view.getSexo());
			client.setSucursalOrigen((view.getSucursalOrigen()==null||view.getSucursalOrigen().equals(" "))?"-":(view.getSucursalOrigen()+""));
			client.setTelefonoCelular((view.getTelefono_celular()==null||view.getTelefono_celular().equals(" "))?"-":view.getTelefono_celular().replaceAll(" ", ""));
			client.setTelTrabajo((view.getTelefono_trabajo()==null||view.getTelefono_trabajo().equals(" "))?"-":view.getTelefono_trabajo().replaceAll(" ", ""));
			client.setTipoSociedadID((view.getTipoSociedadID()==null||view.getTipoSociedadID().equals(" "))?"-":(view.getTipoSociedadID()+""));
			client.setTitulo((view.getTitulo()==null||view.getTitulo().equals(" "))?"-":view.getTitulo());
		
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(view.getCompany_id());
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaCliente");
			srvCall.setProspectus_id(view.getProspectus_id());
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
			
			resAltaClient = service.altaCliente(client);
			
			resAltaClient.getCodigoRespuesta();
			
			if(resAltaClient.getCodigoRespuesta().equals("0")){
				
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(view.getCompany_id());
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaCliente: "+resAltaClient.getMensajeRespuesta());
				srvCall.setProspectus_id(view.getProspectus_id());
				srvCall.setStatus(2);
				
				servicecallingRepository.saveServiceCall(srvCall);
				
				
				
			}else{
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(view.getCompany_id());
				srvCall.setException(resAltaClient.getMensajeRespuesta());
				srvCall.setProspectus_id(view.getProspectus_id());
				srvCall.setStatus(3);
				servicecallingRepository.saveServiceCall(srvCall);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resAltaClient;
	}
	
	@Override
	public AltaCuentaResponse createCuentaSAFI(AltaCuentaRequest alta,int prospectus_id, int company_id){
		AltaCuentaResponse resCuenta=null;
		try{
			safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator    locator = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			safisrv.ws.CuentasServicios.SAFIServicios service = locator.getSAFIServiciosSoap11();
			
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.saveServiceCall");
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
			
			resCuenta = service.altaCuenta(alta);
			
			resCuenta.getCodigoRespuesta();
			
			if(resCuenta.getCodigoRespuesta().equals("0")){
				
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.saveServiceCall: "+resCuenta.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(2);
				
				servicecallingRepository.saveServiceCall(srvCall);
				
				
				
			}else{
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(resCuenta.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(3);
				servicecallingRepository.saveServiceCall(srvCall);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resCuenta;
	}
	
	
	@Override
	public AltaConocimientoCtaResponse createPLDCuentaSAFI(PrevencionLD pld,String account, int prospectus_id, int company_id){
		try{
				AltaConocimientoCtaResponse res;
				AltaConocimientoCtaRequest req = new AltaConocimientoCtaRequest();
				// se ejecuta solo en caso de existir un registro en savingaccount con cuenta en safi y safi account id en 
				req.setAdmonGtosIng("N");///solo par el llenado de acreditados::  N 
				req.setConcentFondo("N");    // N
				req.setCtaInversion("N");  // N
				req.setCuentaAhoID(account);  //  cuenta de savingaccount en caso de tener
				req.setDefineUso(" ");		//  "-"
				req.setDepositoCred(pld.getMaximum_deposit()==null?"":(pld.getMaximum_deposit()+""));   // valor traido de la tabla de gn_mxpld del campo  maximun deposit
				req.setOtroUso("N"); 			// N
				req.setPagoCreditos("S");	// S  seusa para pago de credito
				req.setPagoNomina("N");		//N
				req.setProcRecursos((pld.getResource_origin()==null)?"":pld.getResource_origin());	//  procedencia de los recurso tabla PLD campo resource_origin
				
				if( pld.getFor_third_party() !=null && !pld.getFor_third_party().equals("") ){
					req.setRecursoProv(pld.getFor_third_party());   //P propios T terceros  --P
				}else{
					req.setRecursoProv("P");   //P propios T terceros  --P
				}
				req.setRetirosCargo(pld.getMaximum_withdraw()==null?"":(pld.getMaximum_withdraw()+"")); // proviene de la tabla PLD maximun_withdraw
				
				safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator    locator = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
				safisrv.ws.CuentasServicios.SAFIServicios service = locator.getSAFIServiciosSoap11();
				
				ServiceCalling srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaConocimientoCta");
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(1);
				servicecallingRepository.saveServiceCall(srvCall);
				
				res  = service.altaConocimientoCta(req);
				
				if(res.getCodigoRespuesta().equals("0")){
					
					srvCall = new ServiceCalling();
		
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(company_id);
					srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaConocimientoCta: "+res.getMensajeRespuesta());
					srvCall.setProspectus_id(prospectus_id);
					srvCall.setStatus(2);
					
					servicecallingRepository.saveServiceCall(srvCall);
					
				}else{
					srvCall = new ServiceCalling();
					
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(company_id);
					srvCall.setException(res.getMensajeRespuesta());
					srvCall.setProspectus_id(prospectus_id);
					srvCall.setStatus(3);
					servicecallingRepository.saveServiceCall(srvCall);
				}
			
				return res;
				
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public AltaConocimientoCteResponse createPLDSAFI(PrevencionLD pld,String safi_client_id, int prospectus_id, int company_id){
		
		try{
			AltaConocimientoCteResponse res;
			
			AltaConocimientoCteRequest req = new AltaConocimientoCteRequest();
			
			req.setActivos("0");   //0 persona morales
			req.setAMaternoFam(pld.getPep_mother_last_name()==null?"":pld.getPep_mother_last_name());  //apellido materno del PEP
			req.setAPaternoFam(pld.getPep_father_last_name()==null?"":pld.getPep_father_last_name());  //apellido paterno del PEP
			req.setBancoRef(" ");		  //referencias bancarias  " "
			req.setBancoRef2(" ");
			req.setCapital("0");		//0 persona moral
			req.setClienteID(safi_client_id+"");	// safi_client_Id
			req.setCober_Geograf(" ");  //" " perosna moral
			req.setDolaresExport(" ");  //" "
			req.setDolaresImport(" ");  //" "
			req.setDomicilioRef(" ");	//	"-"
			req.setDomicilioRef2(" ");	//"-"
			req.setEstados_Presen(" ");  //"-"
			req.setExporta("N");  //N
			req.setFuncionID(" "); //" "
			req.setGiro(" ");     // " "
			req.setImporta("N"); // N
			req.setImporteVta("0"); // 0
			req.setIngAproxMes(""+pld.getAprox_monthly_income()); // " "  // revisar con Alex
			req.setNacionalidad(" ");//// " "
			req.setNoCuentaRef(" ");  //  ""
			req.setNoCuentaRef2(" ");  // ""
			req.setNoEmpleados(" ");//""
			req.setNombFamiliar(pld.getPep_first_name()==null?"":pld.getPep_first_name());//// nombre del PEP
			req.setNombRefCom(" "); //""
			req.setNombRefCom2(" ");//""
			req.setNombreRef(" ");//""
			req.setNombreRef2(" ");//""
			req.setNomGrupo(" "); //""
			req.setPaisesExport(" ");//""
			req.setPaisesExport2(" ");// " "
			req.setPaisesExport3(" ");// " "
			req.setPaisesImport(" ");  // " "
			req.setPaisesImport2(" ");// " "
			req.setPaisesImport3(" ");// " "
			req.setParentescoPEP(pld.getHas_pep_relative()==null?"":pld.getHas_pep_relative());// pep_relaionshio tabla PLd
			req.setParticipacion(" ");// // " "
			req.setPasivos("0"); // " 0"
			req.setPEPs(pld.getHas_pep_relative()==null?" ":pld.getHas_pep_relative()); //has pep relative
			req.setPFuenteIng(pld.getPrincipal_income()==null?"":pld.getPrincipal_income()); //principal income PLD
			req.setRazonSocial(" "); //""
			req.setRFC(" ");//// " "
			req.setServ_Produc(" ");// ""// " "
			req.setTelefonoRef(" ");// " "
			req.setTelefonoRef2(" ");// " "
			req.setTelRefCom(" ");// " "
			req.setTelRefCom2(" ");/// " "
			
			safisrv.ws.ClienteServicios.SAFIServiciosServiceLocator    locator = new safisrv.ws.ClienteServicios.SAFIServiciosServiceLocator();
			safisrv.ws.ClienteServicios.SAFIServicios service = locator.getSAFIServiciosSoap11();
			
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaConocimientoCte");
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
			
			res = service.altaConocimientoCte(req);
			
			if(res.getCodigoRespuesta().equals("0")){
				
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaConocimientoCte: "+res.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(2);
				
				servicecallingRepository.saveServiceCall(srvCall);
				
			}else{
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(res.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(3);
				servicecallingRepository.saveServiceCall(srvCall);
			}
		
			return res;
		}catch(Exception e){
			e.printStackTrace();
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			String msg="";
			if(e.getStackTrace().toString().length()>990){
				msg = e.getStackTrace().toString().substring(0,990);
			}else{
				msg = e.getStackTrace().toString().substring(0,990);
			}
			srvCall.setException(msg);
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(3);
			servicecallingRepository.saveServiceCall(srvCall);
			return null;
		}
	}
	
	@Override
	public CreaCreditoResponse creaCreditoSAFI(CreaCreditoRequest creaCreditoRequest,int prospectus_id, int company_id){
		CreaCreditoResponse resCred = null;
		try{
			safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator    locator = new safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator();
			safisrv.ws.CreditosServicios.SAFIServicios service = locator.getSAFIServiciosSoap11();
			
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.creaCredito");
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
			
			creaCreditoRequest.setTasaMora("0");
			
			resCred = service.creaCredito(creaCreditoRequest);
			
			if(resCred.getCodigoRespuesta().equals("0")){
				
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.creaCredito: "+resCred.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(2);
				
				servicecallingRepository.saveServiceCall(srvCall);
				
				
				
			}else{
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(resCred.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(3);
				servicecallingRepository.saveServiceCall(srvCall);
			}
						
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resCred;
	}
	
	@Override
	public SegurosVidaResponse creaSeguroVidaSAFI(SegurosVidaRequest segurosVidaRequest,int prospectus_id, int company_id){
		try{
			safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator    locator = new safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator();
			safisrv.ws.CreditosServicios.SAFIServicios service = locator.getSAFIServiciosSoap11();
			
			ServiceCalling srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(company_id);
			srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.segurosVida");
			srvCall.setProspectus_id(prospectus_id);
			srvCall.setStatus(1);
			servicecallingRepository.saveServiceCall(srvCall);
			
			SegurosVidaResponse res = service.segurosVida(segurosVidaRequest);
			
			if(res.getCodigoRespuesta().equals("0")){
				
				srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setInfo("Regresando Satisfactoriamente de Servicio SAFIServiciosServiceLocator.getSAFIServiciosSoap11.segurosVida: "+res.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(2);
				
				servicecallingRepository.saveServiceCall(srvCall);
				
				
				
			}else{
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(company_id);
				srvCall.setException(res.getMensajeRespuesta());
				srvCall.setProspectus_id(prospectus_id);
				srvCall.setStatus(3);
				servicecallingRepository.saveServiceCall(srvCall);
			}
			
			return res;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
