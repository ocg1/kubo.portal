package mx.com.kubo.services.impl;

import java.util.Date;
import java.util.List;

import mx.com.kubo.bean.AltaClienteRequest;
import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.catalogos.DependantsNumber;
import mx.com.kubo.repositories.NaturalPersonDao;
import mx.com.kubo.repositories.catalogos.DAODependantsNumber;
import mx.com.kubo.services.NaturalPersonService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ServiceNaturalPersonDMO 
implements NaturalPersonService 
{
	protected Logger log = Logger.getLogger(getClass());
	
	@Autowired
	protected NaturalPersonDao dao_natural_person;
	
	@Autowired @Qualifier("dao_dependants_number")
	protected DAODependantsNumber dao_dependants_number;

	public final NaturalPerson getPersonBySAFI_client_id(String SAFI_client_id)
	{
		return dao_natural_person.getPersonBySAFI_client_id(SAFI_client_id);
	}
	
	public NaturalPerson getNaturalPersonById(gnNaturalPersonPK pk) 
	{
		return dao_natural_person.loadSelectedNaturalPerson(pk);
	}
	
	public List<NaturalPerson> getNaturalPersonList() 
	{
		return dao_natural_person.loadNaturalPersonList();
	}
	
	public String getRFC(String name,String fatherLast,String motherLast, String birthday)
	{ 
		return dao_natural_person.getRFC(name, fatherLast, motherLast, birthday);
	}
	
	public String generaCURP(NaturalPerson np,String estado)
	{
		return dao_natural_person.generaCURP(np, estado);
	}
	
	public String generaHomoclaveRFC(NaturalPerson np)
	{
		return dao_natural_person.generaHomoclaveRFC(np);
	}
	
	public NaturalPerson existNaturalPersonByRFC(String rfc)
	{
		return dao_natural_person.existNaturalPersonByRFC(rfc);
	}
	
	public List<NaturalPerson> getPersonListByCURP(String curp)
	{
		return dao_natural_person.getPersonListByCURP(curp);
	}
	
	public IdentificationCollector getIdentificationCollector(AltaClienteRequest request)
	{
		return dao_natural_person.getIdentificationCollector(request);		
	}
	
	public IdentificationCollector getIdentificationCollector(
			Long clientID, 
			Integer tipoIdentID,
			String oficial, 
			String numIdentif,
			Date fecExIden, 
			Date fecVenIden,
			Integer empresaID, 
			Integer audUsuario, 
			Date audFechaActual,
			String audDireccionIP, 
			String audProgramaID, 
			Integer audSucursal,
			Integer audNumTransaccion) 
	{
		return dao_natural_person.getIdentificationCollector(clientID, tipoIdentID, 
				oficial, numIdentif, fecExIden, fecVenIden, empresaID, audUsuario, 
				audFechaActual, audDireccionIP, audProgramaID, audSucursal, audNumTransaccion);
	}
	
	public List<DependantsNumber> getLista_dependants_number()
	{
		return dao_dependants_number.getLista_dependants_number();
	}
}
