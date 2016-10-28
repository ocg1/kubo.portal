package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesPK;
import mx.com.kubo.model.gnNaturalPersonPK;

@ManagedBean(name = "imoreInfo") @ViewScoped
public class ImoreInfo extends ImoreInfoDMO
implements Serializable 
{
	private static final long serialVersionUID = 1L;	
	
	@PostConstruct
	public void init()
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		int prospectus_id = sesion.getProspectus_id();
		int company_id    = sesion.getCompany_id();
		
		naturalPerson     = naturalPersonService.getNaturalPersonById(new gnNaturalPersonPK(prospectus_id, company_id));		
		studylevellist    = studyLevelService.getStudyLevelList();
		legalstatuslist   = legalstatusService.getLegalStatusList();
		maritalstatuslist = maritalstatusService.getMaritalStatusList();
		
		ReferencesPK rfPK1 = new ReferencesPK();
		
		rfPK1.setCompany_id(sesion.getCompany_id());
		rfPK1.setProspectus_id(sesion.getProspectus_id());
		rfPK1.setReference_id(1);
		
		References ref1 = sreferencesService.loadSelectedReferece(rfPK1);
		
		if(ref1 != null)
		{
			setReference1(ref1);
			
			if(ref1.getPhone()!=null)
			{
				String rphone = ref1.getPhone();
				String[] arphone = rphone.split("\\)");
				
				if(arphone.length>1)
				{
					setPhone_lada_ref1(arphone[0].replace("(", "").trim());
					setPhone_ref1( arphone[0].replace("(", "").trim() + arphone[1]);
				}else{
					setPhone_ref1(arphone[0].replace("(", "").trim());
				}
			}
			
			setHasRef1(true);
			
		}else{
			ref1 = new References();
			ref1.setReferencePK(rfPK1);
			setReference1(ref1);
			setHasRef1(false);
		}
		
		ReferencesPK rfPK2 = new ReferencesPK();
		
		rfPK2.setCompany_id(sesion.getCompany_id());
		rfPK2.setProspectus_id(sesion.getProspectus_id());
		rfPK2.setReference_id(2);
		
		References ref2 = sreferencesService.loadSelectedReferece(rfPK2);
		
		if(ref2 != null)
		{
			setReference2(ref2);
			
			if(ref2.getPhone() != null)
			{
				String rphone = ref2.getPhone();
				String[] arphone = rphone.split("\\)");
				
				if(arphone.length>1)
				{
					setPhone_lada_ref2(arphone[0].replace("(", "").trim());
					setPhone_ref2( arphone[0].replace("(", "").trim() + arphone[1]);
				} else {
					setPhone_ref2(arphone[0].replace("(", "").trim());
				}
			}
			
			setHasRef2(true);
		} else {
			ref2 = new References();
			ref2.setReferencePK(rfPK2);
			setReference2(ref2);
			setHasRef2(false);
		}
				
		selectedComputer = new ArrayList<String>();
		
		if (naturalPerson.getHas_computer_home() != null &&  naturalPerson.getHas_computer_home() == 1)
		{
			selectedComputer.add("1");
		}
		
		if (naturalPerson.getHas_computer_employment() != null && naturalPerson.getHas_computer_employment() == 1)
		{
			selectedComputer.add("2");
		}
		
		if (naturalPerson.getHas_computer_business() != null && naturalPerson.getHas_computer_business() == 1)
		{
			selectedComputer.add("3"); 
		}

		selectedInternet = new ArrayList<String>();
		
		if (naturalPerson.getHas_internet_home() != null && naturalPerson.getHas_internet_home() == 1)
		{
			selectedInternet.add("1");
		}
		
		if (naturalPerson.getHas_internet_employment() != null && naturalPerson.getHas_internet_employment() == 1)
		{
			selectedInternet.add("2"); 
		}
		
		if (naturalPerson.getHas_internet_business() != null && naturalPerson.getHas_internet_business() == 1)
		{
			selectedInternet.add("3");
	    }		
	}
	
	public void selectlegalstatus()
	{
		
		if (getNaturalPerson().getLegal_status_id() != null && getNaturalPerson().getLegal_status_id() == 2) 
		{
			setMaritalstatusdis("block");
		} else {
			setMaritalstatusdis("none");
		}
		
		updateNaturalPerson();	
	}

	public String getMaritalstatusdis() 
	{
		if (naturalPerson.getLegal_status_id() != null && naturalPerson.getLegal_status_id() == 2) 
		{
			setMaritalstatusdis("block");
			
		} else {
			
			setMaritalstatusdis("none");
		}
		
		updateNaturalPerson();
		
		
		return maritalstatusdis;
	}

	public void saveReference1()
	{
		if(!isHasRef1())
		{
			sreferencesService.saveReference(getReference1());
			setHasRef1(true);
			
		} else {
			
			sreferencesService.updateReference(getReference1());
		}
	}
	
	public void saveReference2()
	{
		if(!isHasRef2())
		{
			sreferencesService.saveReference(getReference2());
			setHasRef2(true);
			
		} else {
			
			sreferencesService.updateReference(getReference2());
		}
		
	}
	
	public void savePhoneRef1()
	{
		getReference1().setPhone( getPhone_ref1());
		saveReference1();
	}
	
	public void savePhoneRef2()
	{
		getReference2().setPhone( getPhone_ref2());
		saveReference2();
	}
	
	public void updateNaturalPerson()
	{
		naturalPersonService.update(naturalPerson);
	}

	public void setHasComputer() {

		Integer hasCompHome = 0;
		Integer hasCompEmp = 0;
		Integer hasCompBusi = 0;

		for (String s : selectedComputer) {

			if (s.equals("1")) {
				hasCompHome = 1;
			}
			if (s.equals("2")) {
				hasCompEmp = 1;
			}
			if (s.equals("3")) {
				hasCompBusi = 1;
			}
		}
		naturalPerson.setHas_computer_business(hasCompBusi);
		naturalPerson.setHas_computer_employment(hasCompEmp);
		naturalPerson.setHas_computer_home(hasCompHome);
		updateNaturalPerson();

	}
	
	public void setHasInternet() {
		
		Integer hasIntHome = 0;
		Integer hasIntEmp = 0;
		Integer hasIntBusi = 0;

		for (String s : selectedInternet) {

			if (s.equals("1")) {
				hasIntHome = 1;
			}
			if (s.equals("2")) {
				hasIntEmp = 1;
			}
			if (s.equals("3")) {
				hasIntBusi = 1;
			}
		}
		naturalPerson.setHas_internet_business(hasIntBusi);
		naturalPerson.setHas_internet_employment(hasIntEmp);
		naturalPerson.setHas_internet_home(hasIntHome);
		updateNaturalPerson();

	}
}
