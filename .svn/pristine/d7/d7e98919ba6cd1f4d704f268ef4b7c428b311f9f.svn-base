package mx.com.kubo.managedbeans.registro.beneficiarios;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.Benefi_ciaries;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.registro.beneficiarios.BeneficiarioIMP;
import mx.com.kubo.registro.beneficiarios.DomicilioIMP;

@ManagedBean (name = "ibeneficiaries") @ViewScoped
public final class Ibeneficiaries extends IbeneficiariesAMO
implements Serializable, IbeneficiariesIMO
{		
	private static final long serialVersionUID = -6713269295117917310L;

	@PostConstruct
	public void init() 
	{
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		external = faces.getExternalContext();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if(isSesion_DISABLED()){
			return;
		}
		
		company_id          = sesion.getCompany_id();
		prospectus_id       = sesion.getProspectus_id();		
		prospectus_id_coach = sesion.getCoachProspectus_id();
		
		change_prospectus_id = prospectus_id_coach != null ? prospectus_id_coach : prospectus_id;
		
		person_PK = new gnNaturalPersonPK();
		person_PK.setCompany_id(company_id);
		person_PK.setProspectus_id(prospectus_id);
		
		person = service_natural_person.getNaturalPersonById(person_PK);
		
		beneficiario = new BeneficiarioIMP();
		beneficiario.setService_address(service_address);
		beneficiario.setService_beneficiario(service_beneficiarios);
		beneficiario.setSesion(sesion);
		beneficiario.setPerson(person);
		beneficiario.init();
		
		domicilio = new DomicilioIMP();
		domicilio.setService_employment(service_employment);
		domicilio.setService_address(service_address);
		domicilio.setService_change_control(service_change_control);
		domicilio.setSesion(sesion);
		domicilio.setPerson(person);
		domicilio.setAddress_type_id(address_type_BENEFICIARIO);
		domicilio.init();
	
		init_beneficiarios();		
	}
	
	public void init_beneficiarios() 
	{
		request = RequestContext.getCurrentInstance();
		
		init_cuenta();
		
		//TODO
		//init_vivienda_TOKEN();
		
		sizeBenefic = listBenefic.size();
		
		if(sizeBenefic == 0)
		{
			init_beneficiario_NEW();
			
		} else {
			
			init_lista_beneficiarios();			
		}	
		
		init_porcentaje();		
		
		if(request != null)
		{
			request.addCallbackParam("sizeBenefic", sizeBenefic);
		}
	}

	public final void removeBenefic(ActionEvent e)
	{
		is_remove_OK = false;
		int account_ID, benefic_ID;
		
		beneficiario_bean = (Benefi_ciaries) e.getComponent().getAttributes().get("beneficiario");
		
		String accountID = (String) e.getComponent().getAttributes().get("accountID").toString();
		String beneficID = (String) e.getComponent().getAttributes().get("beneficID").toString();
		
		account_ID = Integer.parseInt(accountID);
		benefic_ID = Integer.parseInt(beneficID);
		
		log.info("******************** Empieza a Eliminar cuenta= "+accountID+ " del beneficiario= "+beneficID);
		
		//TODO
		//is_remove_OK = beneficiario_bean.getEditor_vivienda().eliminar();
		
		beneficiario_PK = new BeneficiariesPK(sesion.getProspectus_id(),sesion.getCompany_id(),account_ID, benefic_ID);
		
		is_remove_OK = service_beneficiarios.removeBenefic(beneficiario_PK);
		
		if(is_remove_OK)
		{
			log.info("********************Eliminado= "+accountID+ " del beneficiario= "+beneficID);
			
			listBenefic = service_beneficiarios.getListBeneficByProspectByAccount(sesion.getProspectus_id(), sesion.getCompany_id(), getActualAccountID());
			
			listBenefi_ciaries.clear();
			init_lista_beneficiarios();	
			
		} else {
			
			log.info("***Error al eliminar");
		}
		
		sizeBenefic = listBenefic.size();		
	}
	
	public final void addBeneficiaries(ActionEvent e)
	{		
		boolean flagSave = false;
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		beneficiario_PK = new BeneficiariesPK(prospectus_id, company_id);
		beneficiario_PK.setSaving_account_id(getActualAccountID());
		beneficiarie = new Beneficiaries();
		beneficiarie.setBeneficPk(beneficiario_PK);
		beneficiarie.setPercentage( BigDecimal.valueOf(0D));
		
		flagSave = service_beneficiarios.addBeneficiaries(beneficiarie, prospectus_id, company_id);
		
		if(flagSave)
		{
			
			log.info("Nuevo Beneficiario agregado ");
			listBenefic = null;
			listBenefic = service_beneficiarios.getListBeneficByProspectByAccount(sesion.getProspectus_id(), sesion.getCompany_id(), getActualAccountID());
			
			
			listBenefi_ciaries.clear();
			init_lista_beneficiarios();	
			
		} else {
			log.info("Error al agregar el beneficiario");
		}
		
		sizeBenefic = listBenefic.size();
		
	}
	
	private boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=basicData");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		}
		
		return bandera;
	}
	
	private String getPath()
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
}
