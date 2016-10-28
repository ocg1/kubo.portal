package mx.com.kubo.repositories;

import java.util.Date;
import java.util.List;

import mx.com.kubo.bean.AltaClienteRequest;
import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;

public interface NaturalPersonDao 
{
	void saveNaturalPerson  (NaturalPerson newCompany);
	NaturalPerson update(NaturalPerson newCompany);
	
	String getRFC(String name,String fatherLast,String motherLast, String birthday);
	String generaCURP(NaturalPerson np,String estado);
	String generaHomoclaveRFC(NaturalPerson np);
	
	NaturalPerson loadSelectedNaturalPerson(gnNaturalPersonPK pk);
	NaturalPerson existNaturalPersonByRFC(String rfc);
	NaturalPerson getPersonBySAFI_client_id(String sAFI_client_id);		
	
	IdentificationCollector getIdentificationCollector(AltaClienteRequest request);
	IdentificationCollector getIdentificationCollector(Long clientID, Integer tipoIdentID, String oficial, String numIdentif,Date fecExIden, Date fecVenIden, Integer empresaID, Integer audUsuario, Date audFechaActual, String audDireccionIP, String audProgramaID, Integer audSucursal, Integer audNumTransaccion);
	
	List<NaturalPerson> loadNaturalPersonList();
	List<NaturalPerson> getPersonListByCURP(String curp);	
}
