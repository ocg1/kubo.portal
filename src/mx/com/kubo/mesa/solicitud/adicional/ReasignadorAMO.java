package mx.com.kubo.mesa.solicitud.adicional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.PurposePK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.tools.Utilities;

public abstract class ReasignadorAMO extends ReasignadorDMO
implements ReasignadorIMO
{		
	protected void asignar_valores_a_copiar() 
	{
		prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
		company_id    = proyect_loan.getProyectloanPk().getCompany_id();
		
		score = scoreService.loadMaxScoringByProspectus(prospectus_id, company_id);

		frequency_id    = proyect_loan.getFrequency_id();		
		term_id         = proyect_loan.getTerm_id();
		rate            = proyect_loan.getRate();
		BC_score        = proyect_loan.getBc_score();		
		score_A         = proyect_loan.getKubo_score_a();
		score_B         = proyect_loan.getKubo_score_b();		
		pago_inicial    = proyect_loan.getOpening_payment();				
		fecha_consulta  = proyect_loan.getConsulting_date();		
		ammount         = proyect_loan.getAmmount();
		min_ammount     = proyect_loan.getMin_ammount();
		payment         = proyect_loan.getPayment();
		CAT             = proyect_loan.getMx_cat();
		rate_opening    = proyect_loan.getRate_with_opening();
		comision        = proyect_loan.getOpening_commission();
		comision_amount = proyect_loan.getOpening_commission_amount();		
		liquidez        = proyect_loan.getLiquidity();	
		cci_score       = proyect_loan.getCci_score();
	}

	protected void asignar_valores_copiados_con_consulta() 
	{
		bursolnum = null;
		
		proyect_loan_NEW.setMx_solicitud_buro(bursolnum);
		
		proyect_loan_NEW.setAmmount      (ammount);	
		proyect_loan_NEW.setPayment      (payment);
		proyect_loan_NEW.setFrequency_id (frequency_id);
		proyect_loan_NEW.setTerm_id      (term_id);
		
		proyect_loan_NEW.setMin_ammount  (min_ammount);
		proyect_loan_NEW.setRate         (rate);											
						
		proyect_loan_NEW.setKubo_score_a ("");
		proyect_loan_NEW.setKubo_score_b ("");
		proyect_loan_NEW.setCci_score    ("");
		proyect_loan_NEW.setFunding_type ('T');
		
		proyect_loan_NEW.setBc_score           (0);
		proyect_loan_NEW.setVerification_score (0);		
		proyect_loan_NEW.setDays_online        (15);
		proyect_loan_NEW.setStatus_id          (0);
		proyect_loan_NEW.setDeposit_method_id  (1);
		
		proyect_loan_NEW.setDay_published             (null);		
		proyect_loan_NEW.setOpening_payment           (null);
		proyect_loan_NEW.setOpening_commission        (null);
		proyect_loan_NEW.setOpening_commission_amount (null);
		proyect_loan_NEW.setRate_with_opening         (null);
		proyect_loan_NEW.setLiquidity                 (null);
		proyect_loan_NEW.setMx_cat                    (null);						
		
		proyect_loan_NEW.setProyectloanPk(proyect_loan_NEW_PK);	
	}

	protected void asignar_valores_copiados_sin_consulta() 
	{				
		proyect_loan_NEW.setAmmount           (ammount);
		proyect_loan_NEW.setPayment           (payment);
		proyect_loan_NEW.setFrequency_id      (frequency_id);
		proyect_loan_NEW.setTerm_id           (term_id);
		proyect_loan_NEW.setMin_ammount       (min_ammount);								
		proyect_loan_NEW.setConsulting_date   (fecha_consulta);		
		proyect_loan_NEW.setKubo_score_a      (score_A);
		proyect_loan_NEW.setKubo_score_b      (score_B);
		proyect_loan_NEW.setCci_score         (cci_score);
		proyect_loan_NEW.setBc_score          (BC_score);
		proyect_loan_NEW.setOpening_payment   (pago_inicial);	
		proyect_loan_NEW.setRate              (rate);													
		proyect_loan_NEW.setRate_with_opening (rate_opening);
		
		proyect_loan_NEW.setLiquidity         (liquidez);
		proyect_loan_NEW.setMx_cat            (CAT);
		
		proyect_loan_NEW.setOpening_commission        (comision);
		proyect_loan_NEW.setOpening_commission_amount (comision_amount);
		
		proyect_loan_NEW.setFunding_type('T');									
												
		proyect_loan_NEW.setStatus_id          (0);
		proyect_loan_NEW.setDeposit_method_id  (1);
		proyect_loan_NEW.setVerification_score (1);		
		proyect_loan_NEW.setDays_online        (15);
		
		proyect_loan_NEW.setDay_published (null);
																																		
		if( score != null )
		{					
			bursolnum = score.getMx_solicitud_buro();
			
			proyect_loan_NEW.setMx_solicitud_buro (bursolnum);		
			
			proyect_loan_NEW.setRate_investor     (score.getRate_investor());					
		}
	}
	
	protected void asignar_tipo_de_credito(int loan_type) 
	{
		proyect_loan_id_ORIGINAL = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		switch(loan_type)
		{				
			case  1:
				proyect_loan_NEW.setLoan_type("RDC");
				
				proyect_loan_NEW.setPrevious_proyect_loan_id(proyect_loan_id_ORIGINAL);
			break;
			
			case  2:			
				proyect_loan_NEW.setLoan_type("ADD");
			break;
				
			case  3:			
				proyect_loan_NEW.setLoan_type("REN");
				
				proyect_loan_NEW.setPrevious_proyect_loan_id(proyect_loan_id_ORIGINAL);
				proyect_loan_NEW.setIs_collection_solution("N");
			break;
			
			case  4:			
				proyect_loan_NEW.setLoan_type("NVO");		
			break;
		}
	}
	
	protected void asignar_proyect_NEW_PK(int company_id, int prospectus_id)
	{
		proyect_NEW = proyectService.getMaxProyect(prospectus_id, company_id);
		
		int proyect_id_NEW = proyect_NEW.getProyectoPk().getProyect_id();
		
		proyect_loan_NEW_PK = new ProyectLoanPK();
		
		proyect_loan_NEW_PK.setCompany_id(company_id);
		proyect_loan_NEW_PK.setProspectus_id(prospectus_id);
		proyect_loan_NEW_PK.setProyect_id(proyect_id_NEW);		
		proyect_loan_NEW_PK.setProyect_loan_id(0);
		
		proyect_loan_NEW.setProyectloanPk(proyect_loan_NEW_PK);
	}
	
	protected void asignar_proyect_ORIGINAL_PK(int company_id, int prospectus_id) 
	{
		proyect_loan_NEW_PK = new ProyectLoanPK();
		
		proyect_NEW = proyect_loan.getProyect();
		
		proyect_loan_NEW_PK.setCompany_id(company_id);
		proyect_loan_NEW_PK.setProspectus_id(prospectus_id);
		
		proyect_loan_NEW_PK.setProyect_id(proyect_NEW.getProyectoPk().getProyect_id());
		
		proyect_loan_NEW_PK.setProyect_loan_id(0);
		
		proyect_loan_NEW.setProyectloanPk(proyect_loan_NEW_PK);
	}
	
	protected void crear_proyect(int prospectus_id, int company_id)
	{		
		max_proyect_id = proyectService.getMaxProyectID();
		
		proyect_PK  = new ProyectPK(max_proyect_id, prospectus_id, company_id);
		proyect_NEW = new Proyect();
		
		proyect_NEW.setProyectoPk(proyect_PK);
			
		membershipPK = new MembershipPK(prospectus_id, company_id);								
		membership   = membershipservice.getMembershipById(membershipPK);
			
		if(membership.getRegistration_reason() != null)
		{
			if(membership.getRegistration_reason().getPartner_id() != null)
			{
				partner_id = membership.getRegistration_reason().getPartner_id();
				
				proyect_NEW.setPartner_id(partner_id);
				
			} else {
				
				proyect_NEW.setPartner_id(null);
			}
			
		} else {
			
			proyect_NEW.setPartner_id(null);
		}
		
		is_proyect_NEW_OK = proyectService.add(proyect_NEW);
	}
		
	protected void asignar_vigencia_del_documento(Date fecha_vigente)
	{
		Calendar today       = Calendar.getInstance();
		Calendar fecha_leida = Calendar.getInstance();
		
		fecha_leida.setTime(fecha_vigente);
		
		setDocumento_vigente_DISABLED(!today.after(fecha_leida));
	}
		
	public final void registrar_bitacora_accesso(int screen_id, int percentage, int company_id, int prospectus_id)
	{
		
		if( screen_id == 3 ){
			screen_id = 2;
		}
		access = new Access();
		realizaSimulacion(company_id, prospectus_id);
		access.setCompany_id(company_id);
		access.setProspectus_id(prospectus_id);
		access.setScreen_id(screen_id);
		access.setPercentage(percentage);

		access.setOp_system      (sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size  (sesion.getBrowser_height());
		access.setIpaddress      (sesion.getIP_address_client());
		access.setWeb_browser    (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setUser_agent     (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());
		access.setUrl_access		  (sesion.getUrl_access());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description(sesion.getVersion_description());
		
		accessService.add(access, true);
	}
	
	public void realizaSimulacion( int company_id, int prospectus_id){
		
		SimulatorBean sim = simulatorService.getMaxSimulationProspect(prospectus_id, company_id);
		
		sim.getSimulatorPK().setSimulation_id(sim.getSimulatorPK().getSimulation_id()+1);
		
		sim.setPurpose_id(null);
		
		simulatorService.add(sim);
		
	}
	
	protected String copy_proyect_photo(String file_location, int number_photo, int proyect_loan_id, int proyect_id) 
	{
		String file_format = file_location.split("\\.")[1];				
		
		String random_name = Utilities.getRandomName();
		
		StringBuilder sb = new StringBuilder();
		sb.append("/documents/cia_").append(company_id);
		sb.append("/pros_").append(prospectus_id);
		sb.append("/photo/proyectphoto").append(number_photo);
		sb.append("_").append(proyect_loan_id);
		sb.append("_").append(prospectus_id);
		sb.append("_").append(proyect_id);
		sb.append("_").append(random_name);
		sb.append(".").append(file_format);	
		
		String pathDocument = sb.toString();
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//");
		
		//Utilities.createDirectory(realPath + pathDocument);								 
		
		File f = new File (realPath + file_location);
		
		try 
		{
			if(f.exists())
			{
				InputStream inStr = new FileInputStream(realPath + file_location);
				
				Utilities.copyFile(realPath + pathDocument, inStr);								
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		return pathDocument;
	}
	
	public final boolean copiar_archivos(DocumentationDMO documento)
	{
		bandera = false;
		
		proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
		
		filessPk = new FilesPK();
		
		filessPk.setProyect_loan_id(proyect_loan_id);
		
		filessPk.setCompany_id(documento.getCompany_id());		
		filessPk.setProspectus_id(documento.getProspectus_id());
		
		filessPk.setFile_id(documento.getFile_id());																
		filessPk.setFile_type_id(documento.getTypeFile());									
		
		files = filesService.getFilebyID(filessPk);
		
		url = copiar_archivos_service.copyFile(files , proyect_loan_NEW);
		
		if(url == null)
		{
			bandera = true;			
		}
		
		proyect_loan_id = proyect_loan_NEW.getProyectloanPk().getProyect_loan_id();
		
		filessPk.setProyect_loan_id(proyect_loan_id);							
		files.setLocation(url);							
		files.setUpload_date(new Date());
		
		filesService.addFile(files, documento.getProspectus_id(), documento.getCompany_id());
			
		return bandera;
	}
	
	public final void registrarChangeControl(ChangeBean field, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id) 
	{										
		if(field.isValueChanged())
		{		
			if(saveChangeData(field, company_id, emisor_prospectus_id, acreditado_prospectus_id))
			{												
				System.out.println("AdditionalCreditAMO.registrarChangeControl(): ");	
				System.out.println("\nChangeControl." + field.getChange_control().getAfected_table() + "." + field.getChange_control().getField() + "\n");		
			}
		}
	}
	
	public boolean saveChangeData(ChangeBean field, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id) 
	{											
		changeCtrlPK = new Change_controlPK();
		changeCtrl   = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		
		changeCtrlPK.setProspectus_id(acreditado_prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
					
		changeCtrl.setChange_prospectus_id(emisor_prospectus_id);
		
		changeCtrl.setAfected_table(field.getChange_control().getAfected_table());	
		changeCtrl.setField(field.getChange_control().getField());
		
		changeCtrl.setOriginal_value(field.getOrigValue());
		changeCtrl.setNew_value(field.getNewValue());
		
		changeCtrl.setComments(field.getWhyChangeData());
		changeCtrl.setIp(field.getIp());
		
		if(changeControlService.addChangeControl(changeCtrl, acreditado_prospectus_id, company_id))
		{
			return true;	
		} else {
			return false;
		}
	}
	
	protected Proyect validaPurpose( Proyect proyect){
		
		if( proyect.getPurpose_id() != null && proyect.getType_id() != null ){
			
			return proyect;
			
		}else{
			
			if( proyect.getPurpose() != null){
				
				proyect.setType_id( proyect.getPurpose().getType_id()  );
				proyectService.update(proyect);
				
			}else{
				
				Proyect prTmp = proyectService.getMaxProyectWithPurpose( proyect.getProyectoPk().getProspectus_id(), proyect.getProyectoPk().getCompany_id() );
				
				if(prTmp != null){
				
					proyect.setPurpose_id( prTmp.getPurpose_id() );
					
					proyect.setType_id( prTmp.getType_id());
					
					proyectService.update(proyect);
					
				}else{
					
					SimulatorBean simulator = simulatorService.getMaxSimulationProspectWithPurpose ( proyect.getProyectoPk().getProspectus_id(), proyect.getProyectoPk().getCompany_id() );
					
					if( simulator != null ){
						
						proyect.setPurpose_id( simulator.getPurpose_id() );
						
						PurposePK ppk = new PurposePK();
						
						ppk.setCompany_id(1);
						ppk.setPurpose_id(simulator.getPurpose_id());
						
						Purpose purpose = purposeService.getPurposeById(ppk);
						
						proyect.setType_id( purpose.getType_id());
						
						proyectService.update(proyect);
						
					}else{
						
						proyect.setPurpose_id( 1 );
						
						proyect.setType_id( 1100);
						
						proyectService.update(proyect);
						
					}
					
				}
				
				
				
			}
			
		}
		
		proyect = proyectService.getProyectById(proyect.getProyectoPk());
		
		return proyect;
		
	}
	
}
