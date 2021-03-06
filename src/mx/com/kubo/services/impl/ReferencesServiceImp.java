package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesMotive;
import mx.com.kubo.model.ReferencesPK;
import mx.com.kubo.model.ReferencesScore;
import mx.com.kubo.repositories.ReferencesDao;
import mx.com.kubo.services.ReferencesService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferencesServiceImp implements ReferencesService {

	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ReferencesDao referenceRepository;	
	
	@Override
	public  References loadSelectedReferece(ReferencesPK id){
		return referenceRepository.loadSelectedReferece(id);
	}
	@Override
	public  void saveReference(References newReference){
		referenceRepository.saveReference(newReference);
	}
	@Override
	public  List<References> loadReferencesList(){
		return referenceRepository.loadReferencesList();
	}
	@Override
	public  void updateReference(References newReferences){
		referenceRepository.updateReference(newReferences);
	}
	@Override
	public  void removeReference(References newReferences){
		referenceRepository.removeReference(newReferences);
	}
	@Override
	public  List<References> loadReferencesListByProspect(int prospectus_id,int company_id){
		return referenceRepository.loadReferencesListByProspect(prospectus_id, company_id);
	}
	
	@Override
	public List<References> loadReferencesListByNumber(String phone_str,int prospectus_id ,int company_id ){
		return referenceRepository.loadReferencesListByNumber( phone_str, prospectus_id , company_id );
	}
	
	@Override
	public List<ReferencesMotive> loadReferencesMotiveLstEnabled( ){
		return referenceRepository.loadReferencesMotiveLstEnabled( );
	}
	
	
	@Override
	public List<ReferencesScore> loadReferencesScoreLstEnabled( ){
		return referenceRepository.loadReferencesScoreLstEnabled(  );
	}
}
