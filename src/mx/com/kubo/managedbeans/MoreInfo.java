package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MoreInfo extends MoreInfoAMO
implements Serializable 
{		
	@PostConstruct
	public void init()
	{			
		init_natural_person();	
		
		initProyectLoan();
		
		sesion  = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		if( isSesion_DISABLED(  ) ){
			return;
		}
		
		init_referencia_1();
		init_referencia_2();
		init_referencia_3();
		init_referencia_4();						
	}
	
	public void saveReference1()
	{
		if(!isHasRef1())
		{
			sreferencesService.saveReference(getReference1());
			
			hasRef1 = (true);
			
		} else {
			
			sreferencesService.updateReference(getReference1());
		}
	}
	
	public void saveReference2()
	{
		if(!isHasRef2())
		{
			sreferencesService.saveReference(reference2);
			hasRef2 = (true);
			
		} else {
			
			sreferencesService.updateReference(reference2);
		}		
	}
	
	public void saveReference3()
	{
		if(!isHasRef3())
		{
			sreferencesService.saveReference(reference3);
			hasRef3 = (true);
			
		} else {
			sreferencesService.updateReference(reference3);
		}
	}
	
	public void saveReference4()
	{
		if(!isHasRef4())
		{
			sreferencesService.saveReference(getReference4());
			hasRef4 = (true);
			
		} else {
			sreferencesService.updateReference(getReference4());
		}
	}
	
	public void savePhoneRef1()
	{
		getReference1().setPhone(phone_ref1);
		saveReference1();
	}
	
	public void savePhoneRef2()
	{	
		reference2.setPhone(phone_ref2);
		
		saveReference2();
	}
	
	public void savePhoneRef3()
	{
		reference3.setPhone(phone_ref3);
		saveReference3();
	}
	
	public void savePhoneRef4()
	{
		getReference4().setPhone( phone_ref4);
		saveReference4();
	}
}
