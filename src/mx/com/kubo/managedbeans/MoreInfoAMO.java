package mx.com.kubo.managedbeans;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesPK;
import mx.com.kubo.model.gnNaturalPersonPK;

public abstract class MoreInfoAMO extends MoreInfoDMO
{
	protected void init_natural_person() 
	{
		faces = FacesContext.getCurrentInstance();
		context  = faces.getELContext();
		resolver = faces.getApplication().getELResolver();
		external = faces.getExternalContext();
		
		sesion  = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		if( isSesion_DISABLED(  ) ){
			return;
		}
		
		natural_person_PK = new gnNaturalPersonPK();
		natural_person_PK.setCompany_id   (sesion.getCompany_id());
		natural_person_PK.setProspectus_id(sesion.getProspectus_id());
		
		naturalPerson = naturalPersonService.getNaturalPersonById(natural_person_PK);
	}
	
	protected void initProyectLoan(){
		thisProyectLoan =  proyectLoanService. getMaxProyectLoanByProspect(natural_person_PK.getProspectus_id(), natural_person_PK.getCompany_id());
		kuboscore = thisProyectLoan.getKubo_score_a()+thisProyectLoan.getKubo_score_b();
	}
	
	protected void init_referencia_1() 
	{
		rfPK1 = new ReferencesPK();		
		rfPK1.setCompany_id(sesion.getCompany_id());
		rfPK1.setProspectus_id(sesion.getProspectus_id());
		rfPK1.setReference_id(1);
		
		ref1 = sreferencesService.loadSelectedReferece(rfPK1);
		
		if(ref1 != null)
		{
			reference1 = (ref1);
			
			if(ref1.getPhone()!=null)
			{
				rphone  = ref1.getPhone();
				arphone = rphone.split("\\)");
				
				if(arphone.length > 1)
				{
					phone_lada_ref1 = arphone[0].replace("(", "").trim();
					phone_ref1      = phone_lada_ref1 + arphone[1];
					
				} else {
					
					phone_ref1 = arphone[0].replace("(", "").trim();
				}
			}
			
			hasRef1 = (true);
			
		} else {
			
			ref1 = new References();
			ref1.setReferencePK(rfPK1);
			
			reference1 = (ref1);
			hasRef1 = (false);
		}
	}
	
	protected void init_referencia_2() 
	{
		rfPK2 = new ReferencesPK();
		rfPK2.setCompany_id(sesion.getCompany_id());
		rfPK2.setProspectus_id(sesion.getProspectus_id());
		rfPK2.setReference_id(2);
		
		ref2 = sreferencesService.loadSelectedReferece(rfPK2);
		
		if(ref2 != null)
		{
			reference2 = (ref2);
			
			if(ref2.getPhone()!=null)
			{
				rphone = ref2.getPhone();
				arphone = rphone.split("\\)");
				
				if(arphone.length>1)
				{
					phone_lada_ref2 = arphone[0].replace("(", "");
					phone_ref2      = phone_lada_ref2 + arphone[1];
					
				}else{
					
					phone_ref2 = arphone[0].replace("(", "").trim();
				}
			}
			
			hasRef2 = (true);
			
		} else {
			
			ref2 = new References();
			ref2.setReferencePK(rfPK2);
			
			reference2 = (ref2);
			hasRef2 = (false);
		}
	}
	
	protected void init_referencia_3() 
	{
		rfPK3 = new ReferencesPK();		
		rfPK3.setCompany_id(sesion.getCompany_id());
		rfPK3.setProspectus_id(sesion.getProspectus_id());
		rfPK3.setReference_id(3);
		
		ref3 = sreferencesService.loadSelectedReferece(rfPK3);
		
		if(ref3 != null)
		{
			reference3 = (ref3);
			
			if(ref3.getPhone() != null)
			{
				rphone = ref3.getPhone();
				arphone = rphone.split("\\)");
				
				if(arphone.length > 1)
				{
					phone_lada_ref3 = arphone[0].replace("(", "").trim();
					phone_ref3      = phone_lada_ref3 + arphone[1];
					
				} else {
					
					phone_ref3 = arphone[0].replace("(", "").trim();
				}
			}
			
			hasRef3 = (true);
			
		} else {
			ref3 = new References();
			ref3.setReferencePK(rfPK3);
			reference3 = (ref3);
			hasRef3 = (false);
		}
	}
	
	protected void init_referencia_4() 
	{
		rfPK4 = new ReferencesPK();		
		rfPK4.setCompany_id(sesion.getCompany_id());
		rfPK4.setProspectus_id(sesion.getProspectus_id());
		rfPK4.setReference_id(4);
		
		ref4 = sreferencesService.loadSelectedReferece(rfPK4);
		
		if(ref4 != null)
		{
			reference4 = (ref4);
			
			if(ref4.getPhone() != null)
			{
				rphone  = ref4.getPhone();
				arphone = rphone.split("\\)");
				
				if(arphone.length > 1)
				{
					phone_lada_ref4 = arphone[0].replace("(", "").trim();
					phone_ref4      = phone_lada_ref4 + arphone[1];
					
				} else {
					
					phone_ref4 = arphone[0].replace("(", "").trim();
				}
			}
			
			hasRef4 = (true);
			
		} else {
			ref4 = new References();
			ref4.setReferencePK(rfPK4);
			reference4 = (ref4);
			hasRef4 = (false);
		}
	}
	
	protected boolean isSesion_DISABLED(  )
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath(  ) + "/Portal/sesion-expirada.xhtml?redirecFrom=MyInvestments");
							
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
	
	private String getPath(  )
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
}
