package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.bean.AltaClienteRequest;
import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.catalogos.DependantsNumber;

public interface NaturalPersonService 
{	
	void add(NaturalPerson newNaturalPerson);
	NaturalPerson update(NaturalPerson newNaturalPerson);
	
	String getRFC(String name,String fatherLast,String motherLast, String birthday); 		
	String generaCURP(NaturalPerson np,String estado);
	String generaHomoclaveRFC(NaturalPerson np);
	
	IdentificationCollector getIdentificationCollector(AltaClienteRequest request);
	IdentificationCollector getIdentificationCollector(Long clientID, Integer tipoIdentID, String oficial, String numIdentif,Date fecExIden, Date fecVenIden, Integer empresaID, Integer audUsuario, Date audFechaActual, String audDireccionIP, String audProgramaID, Integer audSucursal, Integer audNumTransaccion);
	
	NaturalPerson getNaturalPersonById(gnNaturalPersonPK pk);
	NaturalPerson existNaturalPersonByRFC(String rfc);		
	NaturalPerson getPersonBySAFI_client_id(String SAFI_client_id);
	
	List<NaturalPerson> getNaturalPersonList();
	List<NaturalPerson> getPersonListByCURP(String curp);	
	
	List<DependantsNumber> getLista_dependants_number();
}
