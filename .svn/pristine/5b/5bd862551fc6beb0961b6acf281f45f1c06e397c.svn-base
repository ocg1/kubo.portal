package mx.com.kubo.mesa.solicitud.telefonos;

import java.util.List;

import mx.com.kubo.bean.PersonAndPhones;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.bean.ReferencesReview;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.services.PhoneService;

public abstract class PhoneReviewDMO 
implements PhoneReviewIMO
{	
	protected PhoneService service_telefono;
	
	protected PhoneReview view;
	
	protected NaturalPerson persona;
	
	protected List<Phone> lista_phone_prospectus;
	protected List<Phone> lstPhonesUpdate;
	
	protected List<PhoneReview>      lista_phone_view;
	protected List<PersonAndPhones>  lstPerson;
	protected List<ReferencesReview> lstReferences;	
	
	protected String phone_label;
	protected String existPhoneStr;
	protected String existPerson;
	protected String existReferen;
	protected String tipo_telefono;
	
	protected String[] srr;
	
	protected int prospectus_id;
	protected int company_id;
	protected int index;
	
	public static final int TRABAJO_FIJO        = 1;
	public static final int TRABAJO_CELULAR     = 2;
	public static final int NEGOCIO_FIJO        = 3;
	public static final int NEGOCIO_CELULAR     = 4;
	public static final int PARTICULAR_FIJO     = 5;
	public static final int PARTICULAR_CELULAR  = 6;
	
	protected boolean panel_extension_ENABLED;
	protected boolean panel_lada_ENABLED;
	
	public void setService_telefono(PhoneService service) 
	{
		service_telefono = service;
	}
	
	public final void setLista_phones(List<Phone> phones)
	{
		lista_phone_prospectus = phones;
	}
	
	public final void setPerson(NaturalPerson person)
	{
		persona = person;
		
		prospectus_id = persona.getNatPerPK().getProspectus_id();
		company_id    = persona.getNatPerPK().getCompany_id();
	}
	
	public final List<PhoneReview> getLista_phone_view()
	{
		return  lista_phone_view;
	}
	
	public boolean isPanel_lada_ENABLED() 
	{
		return panel_lada_ENABLED;
	}

	public boolean isPanel_extension_ENABLED() 
	{
		return panel_extension_ENABLED;
	}
}
