package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.com.kubo.controller.contract.investor.CreaContrato;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.SignatureManaged;
import mx.com.kubo.managedbeans.home.InicioValuesIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.ContractSignature;
import mx.com.kubo.model.ContractSignaturePK;
import mx.com.kubo.model.ContratoRepCapCollector;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PagareCollector;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Signature;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.tools.NumberToLetterConverter;

@ManagedBean
@ViewScoped
public class ContractData extends ContractDataAMO
implements Serializable 
{
	private static final long serialVersionUID = 8064550885524889727L;

	@PostConstruct
	public void init()
	{
		System.out.println( "" );
		System.out.println( "******** INICIANDO CONTRATOS ********" );
		System.out.println( "" );
		
		Calendar tmp_Inicio = Calendar.getInstance();
		tmp_Inicio.setTime(new Date());
		
		faces = FacesContext.getCurrentInstance();	
		
		external  = faces.getExternalContext();
		elContext = faces.getELContext();	
		resolver  = faces.getApplication().getELResolver();
		
		http_request = (HttpServletRequest) external.getRequest();		
		
		ip_data  = http_request.getHeader("X-FORWARDED-FOR");
		
		String safiAcount   = (String) external.getRequestParameterMap().get("numberAcountInvestor");	
		String safiCreditId = (String) external.getRequestParameterMap().get("numberCreditId");
		
		pagoTardioStr = "";
		
		SimpleDateFormat format	= new SimpleDateFormat( "dd 'de' MMMM 'de' yyyy" );
		
		Locale locale = new Locale("es","mx");
		NumberFormat dec = NumberFormat.getCurrencyInstance(locale);
		
		saving = null;
		
		ProyectLoan proyectloan = null;
		
		sumCapital 	= 0D ;
		sumInteres 	= 0D;
		sumIva 		= 0D;
		
		System.out.println( "safiCreditId: " + safiCreditId);
		
		credit = false;
		
		if(safiCreditId != null)
		{			
			proyectloan = proyectloanservice.getProyectLoanListBySafiCreditID(safiCreditId);
			credit = true;
			
			if( safiAcount == null && proyectloan != null )
			{			
				saving  =  savingaccountservice.getSavingAccountByProspectus( proyectloan.getPerson().getNatPerPK().getProspectus_id(), proyectloan.getPerson().getNatPerPK().getCompany_id() );
				safiAcount = saving.getSafi_account_id();				
			}
		}
		
		Calendar tmp_cargaPyl = Calendar.getInstance();
		tmp_cargaPyl.setTime(new Date());
		
		long dif_PY = tmp_cargaPyl.getTimeInMillis() - tmp_Inicio.getTimeInMillis();
		
		System.out.println( "Tiempo de carga de proyect_loan = " + dif_PY +" milisegundos " );  
		
		if( safiAcount != null &&  saving == null )
		{		
			saving  =  savingaccountservice.getSavingAccountByAccount(safiAcount);			
		}
		
		Calendar tmp_sav = Calendar.getInstance();
		tmp_sav.setTime(new Date());
		
		long dif_sav = tmp_sav.getTimeInMillis() - tmp_cargaPyl.getTimeInMillis();
		
		System.out.println( "Tiempo de carga de cuenta = " + dif_sav +" milisegundos " ); 
		
		List<Files> lstFiles = null;
//		
		Files fileContractCapAnterior = fileService.getFileByTypeID(saving.getSaving_accountPk().getProspectus_id(), saving.getSaving_accountPk().getCompany_id(), 0, FILE_ID_CONTRATO_CAPTACION);
		
		Calendar tmp_file1 = Calendar.getInstance();
		tmp_file1.setTime(new Date());
		
		long dif_file1 = tmp_file1.getTimeInMillis() - tmp_sav.getTimeInMillis();
		
		System.out.println( "Tiempo en revisar si ya cuenta con Contratos de captacion = " + dif_file1 +" milisegundos " );
		
		if( !credit)
		{		
			flagRenderedContactCapt = true;
			
		} else {
			
			if( fileContractCapAnterior == null)
			{
				flagRenderedContactCapt = true;
				
			} else {
				
				if( fileContractCapAnterior.getFilesPk().getProyect_loan_id() == proyectloan.getProyectloanPk().getProyect_loan_id() )
				{
					
					flagRenderedContactCapt = true;
					
				} else {
					
					flagRenderedContactCapt = false;
				}			
			}		
		}
		
		if( !isCredit()  ){
			// INVERSIONISTA
			lstFiles = fileService.getListFilesByProspect( saving.getSaving_accountPk().getProspectus_id() , saving.getSaving_accountPk().getCompany_id(), PROYECT_LOAN_ID_INV);
		
		}else if( isCredit() ){
			// ACREDITADO
			lstFiles = fileService.getListFilesByProspect( saving.getSaving_accountPk().getProspectus_id() , saving.getSaving_accountPk().getCompany_id(), proyectloan.getProyectloanPk().getProyect_loan_id());
		}
		
		MembershipPK mpk = new MembershipPK(saving.getSaving_accountPk().getProspectus_id() , saving.getSaving_accountPk().getCompany_id());
		
		membership = membershipService.getMembershipById(mpk);
		
		Calendar tmp_files = Calendar.getInstance();
		tmp_files.setTime(new Date());
		
		long dif_files = tmp_files.getTimeInMillis() - tmp_file1.getTimeInMillis();
		
		System.out.println( "Tiempo en consultar gn_file = " + dif_files +" milisegundos " );
					
			if( proyectloan != null )
			{				
				Calendar tmp_py = Calendar.getInstance();
				tmp_py.setTime(new Date());
				
				safi_client_id = proyectloan.getPerson().getSafi_client_id();
				
				proyect_loan_id =  proyectloan.getProyectloanPk().getProyect_loan_id();
				
				contratoCredito = contratocreditocollectorservice.getContratoCreditoCollectorByCreditId(safiCreditId);
				
				Calendar tmp_datosContrato = Calendar.getInstance();
				tmp_datosContrato.setTime(new Date());
				
				long dif_contratdto = tmp_datosContrato.getTimeInMillis() - tmp_py.getTimeInMillis();
				
				System.out.println( "Tiempo en consultar datos del credito = " + dif_contratdto +" milisegundos " );
				
				pagareamortizacionlst = pagarecollectorserviceimp.getPagareCollectorList(safiCreditId);
				
				freq_name = generaFreq( contratoCredito.getPlazoCredito() );
				
				Calendar tmp_datospagare = Calendar.getInstance();
				tmp_datospagare.setTime(new Date());
				
				long dif_pagare = tmp_datospagare.getTimeInMillis() - tmp_datosContrato.getTimeInMillis();
				
				System.out.println( "Tiempo en consultar datos del pagare = " + dif_pagare +" milisegundos " );
				
				System.out.println( " NombreCliente:  " + contratoCredito.getNombreCliente() );
				System.out.println( " FechaNacimiento:  " + contratoCredito.getFechaNacimiento() );
				System.out.println( " cat:  " + contratoCredito.getCat() );
				System.out.println( " DireccionCompleta:  " + contratoCredito.getDireccionCompleta() );
				System.out.println( " RegistroRECA:  " + contratoCredito.getRegistroRECA() );
				
				monto_credito = dec.format( contratoCredito.getMontoPrima() );
				 
				setComisionAperturaStr( redondeo( proyectloan.getOpening_commission() / 1.16 ) + "" );
				
				if(pagareamortizacionlst != null && pagareamortizacionlst.size() > 0 )
				{
					
					System.out.println( " amotizaciones: " + pagareamortizacionlst.size());
					
					 PagareCollector c =  pagareamortizacionlst.get(0);
					 
					 nombre_institucion = c.getNombreInstitu();
					 Date fechaVencimiento = c.getFechaVencCred();
					 
					 String monto =  ( monto_credito.split("\\.") )[0];
					 
					 centavos =  ( monto_credito.split("\\.") )[1];
					 
					 monto_a_pagar_str =  NumberToLetterConverter.convertNumberToLetter(monto.replaceAll(",", "").replaceAll("\\$", ""));
					 
					 tasaFija  =c.getVar_TasaFija()+"";
					 
					 tasaFijaTxt = c.getTasaFijaTexto();
					 
					 if(fechaVencimiento != null ){
						
						 fechaVencimientoStr = format.format(fechaVencimiento);
						 
					 }
					 
					 Double couta = 0D;
					 
					 int i = 0;
					 
					 for( PagareCollector p :  pagareamortizacionlst ){
						 
						 sumCapital += p.getCapital() ;
						 sumInteres += p.getInteres();
						 sumIva 	+= p.getiVAInteres();
						 sumPagTot	+= p.getMontoCuota();
						 
						 if( i == 0 ){
							 couta =  p.getMontoCuota();
						 }
						 
						 i++;
						 
					 }
					 
					 validaMontopagoTardio( couta ); 
					
				}
				
				Calendar tmp_datosvariable = Calendar.getInstance();
				tmp_datosvariable.setTime(new Date());
				
				long dif_datosvariables = tmp_datosvariable.getTimeInMillis() - tmp_datospagare.getTimeInMillis();
				
				System.out.println( "Tiempo en llenar variables= " + dif_datosvariables +" milisegundos " );
				
				segrep = crecertsegrepcollectorserviceimp.getCreCertSegrep(safiCreditId);
				
				Calendar tmp_segrep = Calendar.getInstance();
				tmp_segrep.setTime(new Date());
				
				long dif_segrep = tmp_segrep.getTimeInMillis() - tmp_datosvariable.getTimeInMillis();
				
				System.out.println( "Tiempo en consultar segrep = " + dif_segrep +" milisegundos " );
				
				if( segrep != null ){
					
					System.out.println( " REFERENCIA BANORTE: " + segrep.getReferenciaBanorte());
					
				}
				
				//Bandera que indica que se trata de un Acreditado
				
			}else{
				System.out.println(safiCreditId + " Sin proyect_loan asignado ");
			}
			
			Calendar tmp_cargaCredito = Calendar.getInstance();
			tmp_cargaCredito.setTime(new Date());
			
			long dif_cargaCredito = tmp_cargaCredito.getTimeInMillis() - tmp_files.getTimeInMillis();
			
			System.out.println( "Tiempo en llenar Datos Credito = " + dif_cargaCredito +" milisegundos " );
		
		
		if( safiAcount != null )
		{			
			Calendar tmp_capt = Calendar.getInstance();
			tmp_capt.setTime(new Date());
			
			List<ContratoRepCapCollector> lst = service.getContractInvList(safiAcount);
			
			Calendar tmp_repcapt = Calendar.getInstance();
			tmp_repcapt.setTime(new Date());
			
			long dif_repcap = tmp_repcapt.getTimeInMillis() - tmp_capt.getTimeInMillis();
			
			System.out.println( "Tiempo en cargar reoCapt = " + dif_repcap +" milisegundos " );
			
			if (lst != null && lst.size() > 0 ){
				data = lst.get(0);	
			}
		 
			lstBen = service.getContractBenList(safiAcount);
			
			Calendar tmp_ben = Calendar.getInstance();
			tmp_ben.setTime(new Date());
			
			long dif_ben = tmp_ben.getTimeInMillis() - tmp_repcapt.getTimeInMillis();
			
			System.out.println( "Tiempo en cargar datosBeneficiarios = " + dif_ben +" milisegundos " );
			
			if( saving != null )
			{				
				kubo_version = systemparamservice.getVersion_description();
				
				if( lstFiles != null && lstFiles.size() > 0 )
				{					
					for( int i = 0 ; i < lstFiles.size(); i++ )
					{						
						Files f = lstFiles.get( i );
						
						if( f.getFilesPk().getFile_type_id() == FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS )
						{
							
							have_contrato_medios_electronicos = true;
							url_contrato_medios_electronicos = "resources"+f.getLocation() ;
							continue;
							
						}else if ( f.getFilesPk().getFile_type_id() == FILE_ID_CONTRATO_GARANTIAS ){
							
							have_contrato_garantias = true;
							url_contrato_garantias = "resources"+f.getLocation();
							continue;
							
						}else if ( f.getFilesPk().getFile_type_id() == FILE_ID_CONTRATO_CAPTACION ){
							
							have_contrato_captacion = true;
							url_contrato_captacion = "resources"+f.getLocation();
							continue;
							
						}else if ( f.getFilesPk().getFile_type_id() == FILE_ID_CONTRATO_CREDITO ){
							
							have_contrato_credito = true;
							url_contrato_credito = "resources"+f.getLocation();
							continue;
							
						}						
					}					
				}
				
				Calendar tmp_validaExistFile = Calendar.getInstance();
				tmp_validaExistFile.setTime(new Date());
				
				long dif_validaExistFile = tmp_validaExistFile.getTimeInMillis() - tmp_repcapt.getTimeInMillis();
				
				System.out.println( "Tiempo en valida si Existe Files Contratos = " + dif_validaExistFile +" milisegundos " );
				
				List<Signature> signatures = null;
				
				if( isCredit() )
				{				
					signatures = signatureservice.getSignatureByProspectus(  saving.getSaving_accountPk().getProspectus_id(), proyect_loan_id  );
				
				} else {
					
					signatures = signatureservice.getSignatureByProspectus(  saving.getSaving_accountPk().getProspectus_id(), PROYECT_LOAN_ID_INV  );					
				}
				
				Calendar tmp_carga_signature = Calendar.getInstance();
				tmp_carga_signature.setTime(new Date());
				
				long dif_carga_signature = tmp_carga_signature.getTimeInMillis() - tmp_validaExistFile.getTimeInMillis();
				
				System.out.println( "Tiempo en cargar gn_signature = " + dif_carga_signature +" milisegundos " );
				
				if( signatures != null && signatures.size() > 0 )
				{				
					for( Signature sign : signatures )
					{
						
						if( sign.getFile_id() == FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS  )
						{							
							signature_medios_location = sign.getLocation().substring( sign.getLocation().indexOf("resources/documents") );
							signature_medios_location_complete =  sign.getLocation() ;
							signature_medios_id = sign.getSignature_id();
							have_signature_medios_electronicos = true;
							
							setSerialize_str( sign.getSerialize_str() );
							setData_url_str( sign.getData_url_str() );
							
						}else if( sign.getFile_id() == FILE_ID_CONTRATO_CAPTACION  ){
							
							signature_captacion_location = sign.getLocation().substring( sign.getLocation().indexOf("resources/documents") );
							signature_captacion_location_complete =  sign.getLocation() ;
							signature_captacion_id = sign.getSignature_id();
							have_signature_captacion = true;
							
							setSerialize_str_2( sign.getSerialize_str() );
							setData_url_str_2( sign.getData_url_str() );
							
							
						}else if( sign.getFile_id() == FILE_ID_CONTRATO_GARANTIAS  ){
							
							signature_garantia_location = sign.getLocation().substring( sign.getLocation().indexOf("resources/documents") );
							signature_garantia_location_complete =  sign.getLocation() ;
							signature_garantia_id = sign.getSignature_id();
							have_signature_garantias = true;
							
							setSerialize_str_3( sign.getSerialize_str() );
							setData_url_str_3( sign.getData_url_str() );
							
						}else if( sign.getFile_id() == FILE_ID_CONTRATO_CREDITO  ){
							
							signature_credito_location = sign.getLocation().substring( sign.getLocation().indexOf("resources/documents") );
							signature_credito_location_complete =  sign.getLocation() ;
							signature_credito_id = sign.getSignature_id();
							have_signature_credito = true;
							
							setSerialize_str_4( sign.getSerialize_str() );
							setData_url_str_4( sign.getData_url_str() );
							
						}						
					}				
				}
				
				Calendar tmp_carga_signature_data = Calendar.getInstance();
				tmp_carga_signature_data.setTime(new Date());
				
				long dif_carga_signature_data = tmp_carga_signature_data.getTimeInMillis() - tmp_carga_signature.getTimeInMillis();
				
				System.out.println( "Tiempo en asignar datos de signature = " + dif_carga_signature_data +" milisegundos " );
				
				ContractSignaturePK cspk = new ContractSignaturePK();
				
				cspk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
				cspk.setLegal_contract_id(LEGAL_ID_CONTRATO_MEDIOS_ELECTRONICOS);
				cspk.setProspectus_id(saving.getSaving_accountPk().getProspectus_id());
				
				if( isCredit() ){
					cspk.setProyect_loan_id(proyect_loan_id);
				}else{
					cspk.setProyect_loan_id(PROYECT_LOAN_ID_INV);
				}
				
				
				System.out.println( "Obtiene contrato medios" );
				
				contract_sign_medios = contractsignatureservice.getContractSignature(cspk);
				
				System.out.println( "Regresa contrato medios: " + contract_sign_medios );
				
				Calendar tmp_carga_contrato_medios = Calendar.getInstance();
				tmp_carga_contrato_medios.setTime(new Date());
				
				long dif_carga_contrato_medios = tmp_carga_contrato_medios.getTimeInMillis() - tmp_carga_signature_data.getTimeInMillis();
				
				System.out.println( "Tiempo en cargar contrato medios = " + dif_carga_contrato_medios +" milisegundos " );
				
				if( contract_sign_medios == null ){
					
					contract_sign_medios = new ContractSignature();
					contract_sign_medios.setPk(cspk);
					contractsignatureservice.addContractSignature(contract_sign_medios);
					
					acceptedMedios = new ArrayList<String>();
					
				}else{
					
					if( contract_sign_medios.getContract_accepted_date() != null ){
						acceptedMedios = new ArrayList<String>();
						acceptedMedios.add("S");
					}
					if( contract_sign_medios.getContract_signed_date() != null ){
						signed_contrato_medios_electronicos = true;
					}
					
				}
				
				cspk = new ContractSignaturePK();
				
				cspk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
				cspk.setLegal_contract_id(LEGAL_ID_CONTRATO_CAPTACION);
				cspk.setProspectus_id(saving.getSaving_accountPk().getProspectus_id());
				
				if( isCredit() ){
					cspk.setProyect_loan_id(proyect_loan_id);
				}else{
					cspk.setProyect_loan_id(PROYECT_LOAN_ID_INV);
				}
				
				contract_sign_captacion = contractsignatureservice.getContractSignature(cspk);
				
				Calendar tmp_carga_contrato_captacion = Calendar.getInstance();
				tmp_carga_contrato_captacion.setTime(new Date());
				
				long dif_carga_contrato_captacion = tmp_carga_contrato_captacion.getTimeInMillis() - tmp_carga_contrato_medios.getTimeInMillis();
				
				System.out.println( "Tiempo en cargar contrato captacion = " + dif_carga_contrato_captacion +" milisegundos " );
				
				if( contract_sign_captacion == null ){
					
					contract_sign_captacion = new ContractSignature();
					contract_sign_captacion.setPk(cspk);
					contractsignatureservice.addContractSignature(contract_sign_captacion);
					
					acceptedCaptacion = new ArrayList<String>();
					
				}else{
					
					if( contract_sign_captacion.getContract_accepted_date() != null ){
						acceptedCaptacion = new ArrayList<String>();
						acceptedCaptacion.add("S");
					}
					if( contract_sign_captacion.getContract_signed_date() != null ){
						signed_contrato_captacion = true;
					}
					
					
				}
				
				cspk = new ContractSignaturePK();
				
				cspk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
				cspk.setLegal_contract_id(LEGAL_ID_CONTRATO_GARANTIAS);
				cspk.setProspectus_id(saving.getSaving_accountPk().getProspectus_id());
				
				if( isCredit() ){
					cspk.setProyect_loan_id(proyect_loan_id);
				}else{
					cspk.setProyect_loan_id(PROYECT_LOAN_ID_INV);
				}
				
				contract_sign_garantia = contractsignatureservice.getContractSignature(cspk);
				
				Calendar tmp_carga_contrato_garantia = Calendar.getInstance();
				tmp_carga_contrato_garantia.setTime(new Date());
				
				long dif_carga_contrato_garantia = tmp_carga_contrato_garantia.getTimeInMillis() - tmp_carga_contrato_captacion.getTimeInMillis();
				
				System.out.println( "Tiempo en cargar contrato garantia = " + dif_carga_contrato_garantia +" milisegundos " );
				
				if( contract_sign_garantia == null )
				{					
					contract_sign_garantia = new ContractSignature();
					contract_sign_garantia.setPk(cspk);
					contractsignatureservice.addContractSignature(contract_sign_garantia);
					
					acceptedGarantia = new ArrayList<String>();
					
				} else {
					
					if( contract_sign_garantia.getContract_accepted_date() != null )
					{
						acceptedGarantia = new ArrayList<String>();
						acceptedGarantia.add("S");
					}
					
					if( contract_sign_garantia.getContract_signed_date() != null ){
						signed_contrato_garantias = true;
					}				
				}				
				
				if( isCredit() )
				{				
					cspk = new ContractSignaturePK();
					
					cspk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
					cspk.setLegal_contract_id(LEGAL_ID_CONTRATO_CREDITO);
					cspk.setProspectus_id(saving.getSaving_accountPk().getProspectus_id());
					
					if( isCredit() ){
						cspk.setProyect_loan_id(proyect_loan_id);
					}else{
						cspk.setProyect_loan_id(PROYECT_LOAN_ID_INV);
					}
					
					contract_sign_credito = contractsignatureservice.getContractSignature(cspk);
					
					Calendar tmp_carga_contrato_credito = Calendar.getInstance();
					tmp_carga_contrato_credito.setTime(new Date());
					
					long dif_carga_contrato_credito = tmp_carga_contrato_credito.getTimeInMillis() - tmp_carga_contrato_garantia.getTimeInMillis();
					
					System.out.println( "Tiempo en cargar contrato credito = " + dif_carga_contrato_credito +" milisegundos " );
					
					if( contract_sign_credito == null )
					{						
						contract_sign_credito = new ContractSignature();
						contract_sign_credito.setPk(cspk);
						contractsignatureservice.addContractSignature(contract_sign_credito);
						
						acceptedCredito = new ArrayList<String>();
						
					} else {
						
						if( contract_sign_credito.getContract_accepted_date() != null )
						{
							acceptedCredito = new ArrayList<String>();
							acceptedCredito.add("S");
						}
						
						if( contract_sign_credito.getContract_signed_date() != null )
						{
							signed_contrato_credito = true;
						}					
					}				
				}							
			}						
		}
		
		SystemParamPK spk = new SystemParamPK();
		
		spk.setCompany_id(COMPANYID);
		spk.setSystem_param_id(URL_SERVER_INT);
		
		SystemParam sysParam = systemparamservice.loadSelectedSystemParam( spk );
		
		URL_SERVER = sysParam.getValue();
		
		if( isCredit() ){
			
			todosFirmados =  have_contrato_medios_electronicos && have_contrato_garantias && have_contrato_captacion && have_contrato_credito ;
			
		}else{

			todosFirmados =  have_contrato_medios_electronicos && have_contrato_garantias && have_contrato_captacion ;
			
		}
		
		Calendar tmp_Fin = Calendar.getInstance();
		tmp_Fin.setTime(new Date());
		
		long dif_Total = tmp_Fin.getTimeInMillis() - tmp_Inicio.getTimeInMillis();
		
		System.out.println( "Tiempo total de carga de Contratos = " + dif_Total +" milisegundos " );
		
		System.out.println( "" );
		System.out.println( "******** FIN CONTRATOS ********" );
		System.out.println( "" );
	}
	
	public void registraShowed(){
		
		System.out.println( ".... registraShowed .... " );
		
		if( flagRenderedContactCapt ){
		
			contract_sign_medios.setContract_showed_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_medios);
			
			contract_sign_captacion.setContract_showed_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_captacion);
			
			contract_sign_garantia.setContract_showed_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_garantia);
		
		}
		
		if(isCredit()){
		 contract_sign_credito.setContract_showed_date(new Date());
		 contractsignatureservice.updateContractSignature(contract_sign_credito);
		 System.out.println( ".... registraShowed credito .... " + contract_sign_credito.getContract_showed_date() );
		}
		
		
		
		System.out.println( ".... Fin registraShowed .... " );
		
	}
	
	public void checkContractMedios(){
		
		FacesContext faces 			= FacesContext.getCurrentInstance();	
		ExternalContext external  = faces.getExternalContext();
		
		HttpServletRequest http_request = (HttpServletRequest) external.getRequest();
		
		ip_data  = http_request.getHeader("X-FORWARDED-FOR");
	    
		if(ip_data == null)
		{
			ip_data = http_request.getRemoteAddr();
		}
		
		System.out.println( ".... registraAccepted Medios .... "+acceptedMedios.size() );
		
		if( acceptedMedios.size() > 0 ){
			contract_sign_medios.setContract_accepted_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_medios);
		}else{
			contract_sign_medios.setContract_accepted_date(null);
			contractsignatureservice.updateContractSignature(contract_sign_medios);
		}
	}
	
	public void checkContractCaptacion(){
		
		FacesContext faces 			= FacesContext.getCurrentInstance();	
		ExternalContext external  = faces.getExternalContext();
		
		HttpServletRequest http_request = (HttpServletRequest) external.getRequest();
		
		ip_data  = http_request.getHeader("X-FORWARDED-FOR");
	    
		if(ip_data == null)
		{
			ip_data = http_request.getRemoteAddr();
		}
		
		System.out.println( ".... registraAccepted Captacion .... "+acceptedCaptacion.size() );
		
		if( acceptedCaptacion.size() > 0 ){
			contract_sign_captacion.setContract_accepted_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_captacion);
		}else{
			contract_sign_captacion.setContract_accepted_date(null);
			contractsignatureservice.updateContractSignature(contract_sign_captacion);
		}
		
	}
	
	public void checkContractGarantia(){
		
		FacesContext faces 			= FacesContext.getCurrentInstance();	
		ExternalContext external  = faces.getExternalContext();
		
		HttpServletRequest http_request = (HttpServletRequest) external.getRequest();
		
		ip_data  = http_request.getHeader("X-FORWARDED-FOR");
	    
		if(ip_data == null)
		{
			ip_data = http_request.getRemoteAddr();
		}
		
		System.out.println( ".... registraAccepted Garantia .... "+acceptedGarantia.size() );
		
		
		if( acceptedGarantia.size() > 0 ){
			contract_sign_garantia.setContract_accepted_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_garantia);
		}else{
			contract_sign_garantia.setContract_accepted_date(null);
			contractsignatureservice.updateContractSignature(contract_sign_garantia);
		}
		
	}
	
	public void checkContractCredito(){
		
		FacesContext faces 			= FacesContext.getCurrentInstance();	
		ExternalContext external  = faces.getExternalContext();
		
		HttpServletRequest http_request = (HttpServletRequest) external.getRequest();
		
		ip_data  = http_request.getHeader("X-FORWARDED-FOR");
	    
		if(ip_data == null)
		{
			ip_data = http_request.getRemoteAddr();
		}
		
		System.out.println( ".... registraAccepted Credito .... "+acceptedCredito.size() );
		
		
		if( acceptedCredito.size() > 0 ){
			contract_sign_credito.setContract_accepted_date(new Date());
			contractsignatureservice.updateContractSignature(contract_sign_credito);
		}else{
			contract_sign_credito.setContract_accepted_date(null);
			contractsignatureservice.updateContractSignature(contract_sign_credito);
		}
		
	}
	
	public void registraAccess(){
		
		if ( saving != null && saving.getSaving_accountPk() != null  ){
		
			FacesContext faces 			= FacesContext.getCurrentInstance();	
			ELContext elContext			= faces.getELContext();	
			ELResolver resolver			= faces.getApplication().getELResolver();
			
			InicioValuesIMP inicio = (InicioValuesIMP) resolver.getValue(elContext, null, "inicioValues");
			
			url_access = inicio.getUrl_value();
			
			Access access = new Access();
			
			System.out.println( "UserAgent: "  );
			
			access.setAccess_from("Mensaje SMS");
			access.setCompany_id(saving.getSaving_accountPk().getCompany_id());
			access.setDevice_info(null);
			access.setHorizontal_size( Integer.parseInt( browser_width ) );
			
			access.setIpaddress(ip_data);
			access.setOp_system(browser_OS);
			access.setPercentage(0);
			access.setProspectus_id( saving.getSaving_accountPk().getProspectus_id() );
			access.setProspectus_id_coach(null);
			access.setProspectus_id_viewed( saving.getSaving_accountPk().getProspectus_id() );
			access.setScreen_id(SCREEN_SIGNATURE);
			access.setUser_agent(browser_data);
			access.setVersion_description(kubo_version);
			access.setVertical_size(Integer.parseInt( browser_height ));
			access.setWeb_browser(browser_name);
			access.setWeb_browser_version(browser_version);
			access.setUrl_access( url_access );
			
			
			
			access.setAccess_datetime(new Date());
			
			access_service.add(access,false);
			
		}
		
	}
	
	public void generaFirma(){
		
		System.out.println( "genera_Firma contrato 1" );
		actual = "medios";
		
		if( saving != null && !have_signature_medios_electronicos ){
			
			String proyectLoanID = "";
			
			if(isCredit()){
				proyectLoanID = proyect_loan_id+"";
			}else{
				proyectLoanID = PROYECT_LOAN_ID_INV+"";
			}
		
			SignatureManaged signature = new SignatureManaged( saving.getSaving_accountPk().getCompany_id() + "" ,saving.getSaving_accountPk().getProspectus_id()+"", serialize_str,  data_url_str, proyectLoanID );
			
			FileTypePK pk =  new FileTypePK();
			pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
			pk.setFile_type_id( FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS );
			FileType fileType = filetypeservice.getFileTypeById(pk);
			
			if(signature.generaImg(fileType.getAbreviation())){
				
				String url_firma = signature.getUrlFirma();
				
				registraFirma( FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS,url_firma, proyectLoanID );
				
				signature_medios_location = url_firma.substring( url_firma.indexOf("resources/documents") );
				
				signature_medios_location_complete = url_firma; 
				
				have_signature_medios_electronicos = true;
				
				if( isCredit() ){
					
					if( !flagRenderedContactCapt && have_signature_credito  ){
						enviaContratos();
					}else if( have_signature_medios_electronicos && have_signature_credito && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}else{
					
					if( have_signature_medios_electronicos && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}
				
			}else{
				
				/** TODO 
				 * 
				 * ERROR AL GENERAR LA FIRMA
				 * 
				 * */
				
			}
			
		}
	}
	
	public void generaFirma_2(){
		
		System.out.println( "genera_Firma contrato 2" );
		actual = "captacion";
		
		if( saving != null && !have_signature_captacion ){
		
			String proyectLoanID = "";
			
			if(isCredit()){
				proyectLoanID = proyect_loan_id+"";
			}else{
				proyectLoanID = PROYECT_LOAN_ID_INV+"";
			}
			
			SignatureManaged signature = new SignatureManaged( saving.getSaving_accountPk().getCompany_id() + "" ,saving.getSaving_accountPk().getProspectus_id()+"", serialize_str_2,  data_url_str_2, proyectLoanID );
			
			FileTypePK pk =  new FileTypePK();
			pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
			pk.setFile_type_id( FILE_ID_CONTRATO_CAPTACION );
			FileType fileType = filetypeservice.getFileTypeById(pk);
			
			if(signature.generaImg(fileType.getAbreviation())){
				
				have_signature_captacion = true;
				
				String url_firma = signature.getUrlFirma();
				
				registraFirma( FILE_ID_CONTRATO_CAPTACION,url_firma, proyectLoanID );
				
				signature_captacion_location = url_firma.substring( url_firma.indexOf("resources/documents") );
				
				signature_captacion_location_complete = url_firma;
				
				if( isCredit() ){
					
					if( !flagRenderedContactCapt && have_signature_credito  ){
						enviaContratos();
					}else if( have_signature_medios_electronicos && have_signature_credito && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}else{
					
					if( have_signature_medios_electronicos && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}
			
			}else{
				
				/** TODO 
				 * 
				 * ERROR AL GENERAR LA FIRMA
				 * 
				 * */
				
			}
			
		}
		
	}

	public void generaFirma_3(){
		
		System.out.println( "genera_Firma contrato 3" );
		actual = "garantias";
		
		if( saving != null && !have_signature_garantias ){
		
			String proyectLoanID = "";
			
			if(isCredit()){
				proyectLoanID = proyect_loan_id+"";
			}else{
				proyectLoanID = PROYECT_LOAN_ID_INV+"";
			}
			
			SignatureManaged signature = new SignatureManaged( saving.getSaving_accountPk().getCompany_id() + "" ,saving.getSaving_accountPk().getProspectus_id()+"", serialize_str_3,  data_url_str_3, proyectLoanID );
			
			FileTypePK pk =  new FileTypePK();
			pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
			pk.setFile_type_id( FILE_ID_CONTRATO_GARANTIAS );
			FileType fileType = filetypeservice.getFileTypeById(pk);
			
			
			if(signature.generaImg(fileType.getAbreviation())){
				
				have_signature_garantias = true;
				
				String url_firma = signature.getUrlFirma();
				
				registraFirma( FILE_ID_CONTRATO_GARANTIAS, url_firma, proyectLoanID );
				
				signature_garantia_location = url_firma.substring( url_firma.indexOf("resources/documents") );
				
				signature_garantia_location_complete = url_firma;
				
				if( isCredit() ){
					
					if( !flagRenderedContactCapt && have_signature_credito  ){
						enviaContratos();
					}else if( have_signature_medios_electronicos && have_signature_credito && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}else{
					
					if( have_signature_medios_electronicos && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}
				
			}else{
				
				/** TODO 
				 * 
				 * ERROR AL GENERAR LA FIRMA
				 * 
				 * */
				
			}
			
			
		}
	}
	
	public void generaFirma_4(){
		
		System.out.println( "genera_Firma contrato 4 Creditos" );
		actual = "credito";
		
		if( saving != null && !have_signature_credito ){
		
			String proyectLoanID = "";
			
			if(isCredit()){
				proyectLoanID = proyect_loan_id+"";
			}else{
				proyectLoanID = PROYECT_LOAN_ID_INV+"";
			}
			
			SignatureManaged signature = new SignatureManaged( saving.getSaving_accountPk().getCompany_id() + "" ,saving.getSaving_accountPk().getProspectus_id()+"", serialize_str_4,  data_url_str_4, proyectLoanID );
			
			FileTypePK pk =  new FileTypePK();
			pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
			pk.setFile_type_id( FILE_ID_CONTRATO_CREDITO );
			FileType fileType = filetypeservice.getFileTypeById(pk);
			
			
			if(signature.generaImg(fileType.getAbreviation())){
				
				have_signature_credito = true;
				
				String url_firma = signature.getUrlFirma();
				
				registraFirma( FILE_ID_CONTRATO_CREDITO, url_firma, proyectLoanID );
				
				signature_credito_location = url_firma.substring( url_firma.indexOf("resources/documents") );
				
				signature_credito_location_complete = url_firma;
				
				if( isCredit() ){
					
					if( !flagRenderedContactCapt && have_signature_credito  ){
						enviaContratos();
					}else if( have_signature_medios_electronicos && have_signature_credito && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}else{
					
					if( have_signature_medios_electronicos && have_signature_captacion && have_signature_garantias  ){
						enviaContratos();
					}
					
				}
				
			}else{
				
				/** TODO 
				 * 
				 * ERROR AL GENERAR LA FIRMA
				 * 
				 * */
				
			}
			
			
		}
	}
	
	public void enviaContratos(){
		
		try{
			
			if( flagRenderedContactCapt ){
				 generaContratosMedios();
			}
			 
			 if( credit ){
				 
				 generaContratosCredito();
				 
			 }
			 
			 if( flagRenderedContactCapt ){
			 
				 generaContratosCaptacion();
			 
			 }
			 
			 
			 if( flagRenderedContactCapt ){
				 generaContratosGarantia();
			 }
			 
			 if( credit ){
				 
				 
				 if( !flagRenderedContactCapt && have_signature_credito  ){
						
					// notificaEnvioContratos();
					 
					notificaFirma();
					 
				}else if( have_contrato_medios_electronicos && have_contrato_garantias && have_contrato_captacion && have_contrato_credito ){
				 
				 	// notificaEnvioContratos();
				 
					notificaFirma();
				}
			 }else{
				 
				 if( have_contrato_medios_electronicos && have_contrato_garantias && have_contrato_captacion ){
					 	// notificaEnvioContratos();
						notificaFirma();
					}
				 
			 }
			 
			 if( isCredit() ){
					
				 if( !flagRenderedContactCapt && have_signature_credito  ){
					 
					 todosFirmados =  have_contrato_credito;
					 
				 }else{
				 
					todosFirmados =  have_contrato_medios_electronicos && have_contrato_garantias && have_contrato_captacion && have_contrato_credito ;
					
				 }
					
				}else{

					todosFirmados =  have_contrato_medios_electronicos && have_contrato_garantias && have_contrato_captacion ;
					
				}
			
		}catch( Exception e ){
			e.printStackTrace();
		}
		
	}
	
	public void replicaFirma(){
		
		System.out.println( " replicaFirma  unsignature: "+unsignature );
		
		if( unsignature != null && unsignature.trim().length() > 0 ){
			
			String[] contratosFaltantes =  unsignature.trim().split("#");
			
			String serializeT = "";  
			String data_urlT = "";
			
			
			
			if( actual.equals("medios") ){
				
				serializeT = getSerialize_str();
				data_urlT  = getData_url_str();
				
				
			}else if( actual.equals("captacion")){
				
				serializeT = getSerialize_str_2();
				data_urlT  = getData_url_str_2();
				
				
			}else if( actual.equals("garantias")){
				
				serializeT = getSerialize_str_3();
				data_urlT  = getData_url_str_3();
				
			}else if( actual.equals("credito")){
				
				serializeT = getSerialize_str_4();
				data_urlT  = getData_url_str_4();
				
			}
			
			
			for( int i =0 ; i < contratosFaltantes.length ;  i++ ){
					
				if( contratosFaltantes[i].equals("medios") ){
					
					setSerialize_str( serializeT);
					setData_url_str(data_urlT);
					generaFirma();
					
				}else if( contratosFaltantes[i].equals("captacion")){
					
					setSerialize_str_2(serializeT );
					setData_url_str_2(data_urlT);
					generaFirma_2();
					
				}else if( contratosFaltantes[i].equals("garantia")){
					
					setSerialize_str_3(serializeT );
					setData_url_str_3(data_urlT);
					generaFirma_3();
					
				}else if( contratosFaltantes[i].equals("acreditado")){
					
					setSerialize_str_4(serializeT );
					setData_url_str_4(data_urlT);
					generaFirma_4();
					
				}
					
			}
			
		}
		
	}
	
	public boolean notificaFirma (  ){
		
		try{
			
			PublicProyectServiceLocator kubolocator = new  PublicProyectServiceLocator();
			
			PublicProyect kuboservices =  kubolocator.getPublicProyect();
			
			NotificadorConfigRequest request = new NotificadorConfigRequest();
			
			request.setCalled_FROM("ContractData.notificaFirma");
			request.setCompany_id(saving.getSaving_accountPk().getCompany_id()+"");
			
			if( isCredit() ){
				request.setEvento_id("44");
			}else{
				request.setEvento_id("42");
			}
			
			request.setProspectus_id(saving.getSaving_accountPk().getProspectus_id()+"");
			WsResponse response = kuboservices.notificar(request);
			
			response.getStatus();
			
			if( isCredit() &&  membership.getPerson().getProspectus().getHs_vid() != null ){
				
				HubSpotController hs =  new HubSpotController();
				hs.sendEvent( "000002059334", membership.getEmail() );
				
			}
			
			return true; 
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public void generaCopiaContratoCredito(){
		
		FileTypePK pk =  new FileTypePK();
		pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
		pk.setFile_type_id( FILE_ID_CONTRATO_CREDITO );
		FileType fileType = filetypeservice.getFileTypeById(pk);
		
		CreaContrato contrato = new CreaContrato( txtContratoCredito, fileType.getAbreviation() , saving.getSaving_accountPk().getCompany_id()+"" , saving.getSaving_accountPk().getProspectus_id()+"" , fileType.getFileCategory().getAbreviation() );
		
		contrato.setUrl_firma(signature_credito_location_complete);
		
		Integer py_ln_id = 0;
		
		if( isCredit() ){
			py_ln_id = proyect_loan_id;
		}else{
			py_ln_id = PROYECT_LOAN_ID_INV;
		}
		contrato.setIscopy(true);
		if( contrato.generaContratoCredito( URL_SERVER, (py_ln_id + "") ) ){
			String location_copy = contrato.getLocation();
			
//			System.out.println( "" );
//			System.out.println( "" );
//			System.out.println( "URL REDIREC --  " + location_copy);
//			System.out.println( "" );
//			System.out.println( "" );
//			
			RequestContext request = RequestContext.getCurrentInstance();
			
			request.addCallbackParam("url_copy", "resources"+ location_copy );
			
		}
		
	}
			
}




