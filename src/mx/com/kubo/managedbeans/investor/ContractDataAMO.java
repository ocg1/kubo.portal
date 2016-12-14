package mx.com.kubo.managedbeans.investor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.com.kubo.controller.contract.investor.CreaContrato;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Signature;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

public class ContractDataAMO extends ContractDataDMO
{
	protected boolean generaContratosMedios(){
		
		try{
			
			if( !have_contrato_medios_electronicos ){
					
					FileTypePK pk =  new FileTypePK();
					pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
					pk.setFile_type_id( FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS );
					FileType fileType = filetypeservice.getFileTypeById(pk);
					
					CreaContrato contrato = new CreaContrato( txtContratoMedios, fileType.getAbreviation() , saving.getSaving_accountPk().getCompany_id()+"" , saving.getSaving_accountPk().getProspectus_id()+"" , fileType.getFileCategory().getAbreviation() );
					
					contrato.setUrl_firma(signature_medios_location_complete);
					
					Integer py_ln_id = 0;
					
					if( isCredit() ){
						py_ln_id = proyect_loan_id;
					}else{
						py_ln_id = PROYECT_LOAN_ID_INV;
					}
					
					if( contrato.generaContrato( URL_SERVER ) ){
						
						registraContrato( FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS, contrato.getLocation(), py_ln_id );
						
						have_contrato_medios_electronicos = true;
						url_contrato_medios_electronicos = "resources"+contrato.getLocation() ;
						signed_contrato_medios_electronicos = true;
						
						contract_sign_medios.setSignature_id(signature_medios_id);
						contract_sign_medios.setContract_signed_date(new Date());
						contractsignatureservice.updateContractSignature(contract_sign_medios);
							
						
						return true;
					}else{
						
						/** TODO 
						 * 
						 * ERROR AL GENERAR EL CONTRATO
						 * 
						 * */
						return false;
						
					}
					
			}else{
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	protected boolean generaContratosCaptacion(){
		
		try{
			
			if( !have_contrato_captacion ){
					
					FileTypePK pk =  new FileTypePK();
					pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
					pk.setFile_type_id( FILE_ID_CONTRATO_CAPTACION );
					FileType fileType = filetypeservice.getFileTypeById(pk);
					
					CreaContrato contrato = new CreaContrato( txtContratoCaptacion, fileType.getAbreviation() , saving.getSaving_accountPk().getCompany_id()+"" , saving.getSaving_accountPk().getProspectus_id()+"" , fileType.getFileCategory().getAbreviation() );
					
					contrato.setUrl_firma(signature_captacion_location_complete);
					
					Integer py_ln_id = 0;
					
					if( isCredit() ){
						py_ln_id = proyect_loan_id;
					}else{
						py_ln_id = PROYECT_LOAN_ID_INV;
					}
					
					if( contrato.generaContrato( URL_SERVER ) ){
						
						registraContrato( FILE_ID_CONTRATO_CAPTACION, contrato.getLocation(), py_ln_id );
						
						have_contrato_captacion = true;
						url_contrato_captacion = "resources"+contrato.getLocation() ;
						signed_contrato_captacion = true;
						
						contract_sign_captacion.setSignature_id(signature_captacion_id);
						contract_sign_captacion.setContract_signed_date(new Date());
						contractsignatureservice.updateContractSignature(contract_sign_captacion);
							
						return true;
					}else{
						
						/** TODO 
						 * 
						 * ERROR AL GENERAR EL CONTRATO
						 * 
						 * */
						return false;
						
					}
					
			}else{
				
				return true;
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
		
	protected boolean generaContratosGarantia(){
		
		try{
			
			if( !have_contrato_garantias ){
			
					FileTypePK pk =  new FileTypePK();
					pk.setCompany_id( saving.getSaving_accountPk().getCompany_id() );
					pk.setFile_type_id( FILE_ID_CONTRATO_GARANTIAS );
					FileType fileType = filetypeservice.getFileTypeById(pk);
					
					CreaContrato contrato = new CreaContrato( txtContratoGarantia, fileType.getAbreviation() , saving.getSaving_accountPk().getCompany_id()+"" , saving.getSaving_accountPk().getProspectus_id()+"" , fileType.getFileCategory().getAbreviation() );
					
					contrato.setUrl_firma(signature_garantia_location_complete);
					
					Integer py_ln_id = 0;
					
					if( isCredit() ){
						py_ln_id = proyect_loan_id;
					}else{
						py_ln_id = PROYECT_LOAN_ID_INV;
					}
					
					if( contrato.generaContrato( URL_SERVER ) ){
						
						registraContrato( FILE_ID_CONTRATO_GARANTIAS, contrato.getLocation(), py_ln_id );
						
						have_contrato_garantias = true;
						url_contrato_garantias = "resources"+contrato.getLocation() ;
						signed_contrato_garantias = true;
						
							contract_sign_garantia.setSignature_id(signature_garantia_id);
							contract_sign_garantia.setContract_signed_date(new Date());
							contractsignatureservice.updateContractSignature(contract_sign_garantia);
							
						
						return true;
					}else{
						
						/** TODO 
						 * 
						 * ERROR AL GENERAR EL CONTRATO
						 * 
						 * */
						return false;
						
					}
					
			}else{
				
				return false;
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	protected boolean generaContratosCredito(){
		
		try{
			
			if( !have_contrato_credito ){
					
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
					
					if( contrato.generaContratoCredito( URL_SERVER, (py_ln_id + "") ) ){
						
						registraContrato( FILE_ID_CONTRATO_CREDITO, contrato.getLocation(), py_ln_id );
						registraContrato( FILE_ID_PAGARE, contrato.getLocationPagare(), py_ln_id );
						
						have_contrato_credito = true;
						url_contrato_credito = "resources"+contrato.getLocation() ;
						signed_contrato_credito = true;
						
						contract_sign_credito.setSignature_id(signature_credito_id);
						contract_sign_credito.setContract_signed_date(new Date());
						contractsignatureservice.updateContractSignature(contract_sign_credito);
							
						return true;
					}else{
						
						/** TODO 
						 * 
						 * ERROR AL GENERAR EL CONTRATO
						 * 
						 * */
						return false;
						
					}
					
			}else{
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
		
	protected boolean registraFirma( int  contract_type, String location, String proyectLoanID ){
		
		try{
			
			Signature signature = new Signature();
			
			signature.setCompany_id(saving.getSaving_accountPk().getCompany_id());
			
			signature.setFile_id(contract_type);
			signature.setIpaddress(ip_data);
			signature.setLocation(location);
			signature.setProspectus_id(saving.getSaving_accountPk().getProspectus_id());
			signature.setSignature_date(new Date());
			signature.setUser_agent(browser_data);
			signature.setProyect_loan_id( Integer.parseInt( proyectLoanID ));
			
			if( contract_type == FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS ){
				
				signature.setData_url_str(data_url_str);
				signature.setSerialize_str(serialize_str);
				
			}else if( contract_type == FILE_ID_CONTRATO_CAPTACION ){
				signature.setData_url_str(data_url_str_2);
				signature.setSerialize_str(serialize_str_2);
				
				
				
			}else if( contract_type == FILE_ID_CONTRATO_GARANTIAS ){
				signature.setData_url_str(data_url_str_3);
				signature.setSerialize_str(serialize_str_3);
				
			}else if( contract_type == FILE_ID_CONTRATO_CREDITO ){
				signature.setData_url_str(data_url_str_4);
				signature.setSerialize_str(serialize_str_4);
				
			}
			
			signatureservice.addSignature(signature);
			
			if( contract_type == FILE_ID_CONTRATO_MEDIOS_ELECTRONICOS ){
				
				signature_medios_id = signature.getSignature_id();
				
			}else if( contract_type == FILE_ID_CONTRATO_CAPTACION ){
				
				signature_captacion_id = signature.getSignature_id();
				
			}else if( contract_type == FILE_ID_CONTRATO_GARANTIAS ){
				
				signature_garantia_id = signature.getSignature_id();
				
			}else if( contract_type == FILE_ID_CONTRATO_CREDITO ){
				
				signature_credito_id = signature.getSignature_id();
				
			}
			
			
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	protected boolean registraContrato( int contract_type, String location , Integer proyect_loan_id ){
		
		boolean flag = false; 
		
		try{
			
			Files newFile = new Files();
			
			FilesPK fpk = new FilesPK();
			
			fpk.setCompany_id(saving.getSaving_accountPk().getCompany_id());
			fpk.setFile_type_id(contract_type);
			fpk.setProspectus_id(saving.getSaving_accountPk().getProspectus_id());
			
			// PARA INVERSIONISTA
			fpk.setProyect_loan_id(proyect_loan_id);
			
			newFile.setUpload_date(new Date());
			newFile.setLocation(location);
			newFile.setFilesPk(fpk);
			newFile.setApproved("0");
			
			flag = fileService.addFile(newFile, saving.getSaving_accountPk().getProspectus_id(), saving.getSaving_accountPk().getCompany_id() );
			
			
		
		}catch( Exception e ){
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
		
	}
	
	protected String generaFreq( String plazo ){
		String freq ="";
		
			if( plazo != null ){
				
				if( plazo.indexOf("Quince") != (-1) ){
					freq ="Quincenal";
				}else if ( plazo.indexOf("Catorce") != (-1) ){
					freq ="Catorcenal";
				}else if ( plazo.indexOf("Seman") != (-1) ){
					freq ="Semanal";
				}else if ( plazo.indexOf("Meses") != (-1) ){
					freq ="Mensual";
				}
				
			}
		
		return freq;
	}
	
	
	
	protected void validaMontopagoTardio( double couta ){
		
		if ( couta <= 500.00){
			pagoTardioStr = "$43.10";
		}

		if ( couta > 500.00 && couta <= 750.00){
			pagoTardioStr = "$64.66";
		}

		if ( couta > 750.00 && couta <= 1000.00){
			pagoTardioStr = "$86.20";
		}

		if ( couta > 1000.00){
			pagoTardioStr = "$129.31";
		}
		
	}
	
	protected Double redondeo( double valor ){
		
		return (Math.rint(valor * 100) / 100);
		
	}
	
}
