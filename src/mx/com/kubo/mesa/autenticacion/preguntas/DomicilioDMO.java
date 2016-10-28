package mx.com.kubo.mesa.autenticacion.preguntas;

import java.util.List;

import mx.com.kubo.mesa.autenticacion.AutenticacionDMO;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.tools.Utilities;

public abstract class DomicilioDMO extends AutenticacionDMO
{	
	protected AddressService service_address;
	protected TownService    servive_town;
	
	protected Address address;
	protected NeighborhoodCat colonia;
	protected TownCat delegacion;
	
	protected List<TownCat> lista_delegaciones;
	
	protected String zipcode;	
	
	protected Integer town_id_ORIGINAL;
	protected Integer town_id_NEW;
	protected Integer state_id;
	
	protected boolean town_ENABLED;
	protected boolean zipcode_ENABLED;
	
	private final int PARTICULAR = 1;
	
	protected DomicilioDMO()
	{
		service_address = Utilities.findBean("addressServiceImp");
		servive_town    = Utilities.findBean("townServiceImp");
	}		
	
	public final void setNatural_person(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;
		
		company_id    = natural_person.getNatPerPK().getCompany_id();
		prospectus_id = natural_person.getNatPerPK().getProspectus_id();
		state_id      = natural_person.getState_id();
		
		address = service_address.getMaxAddressByType(prospectus_id, company_id, PARTICULAR);	
		
		lista_delegaciones = servive_town.loadTownList(state_id);
		
		zipcode = address.getZip_code();
		colonia = address.getNeighborhood();
		
		delegacion = colonia.getDelegMunicipio();
				
		town_id_ORIGINAL = delegacion.getTownCatPK().getTown_id();							
	}
	
	public final List<TownCat> getLista_delegaciones() 
	{
		return lista_delegaciones;
	}
}
