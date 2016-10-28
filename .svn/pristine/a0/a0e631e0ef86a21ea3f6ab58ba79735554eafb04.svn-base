package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesMotive;
import mx.com.kubo.model.ReferencesPK;
import mx.com.kubo.model.ReferencesScore;


public interface ReferencesDao  {

		public abstract References loadSelectedReferece(ReferencesPK id);
		public abstract void saveReference(References newReference);
		public abstract List<References> loadReferencesList();
		public abstract void updateReference(References newReferences);
		public abstract void removeReference(References newReferences);
		public abstract List<References> loadReferencesListByProspect(int prospectus_id,int company_id);
		
		public List<References> loadReferencesListByNumber(String phone_str,int prospectus_id ,int company_id );
		
		public List<ReferencesMotive> loadReferencesMotiveLstEnabled( );
		
		public List<ReferencesScore> loadReferencesScoreLstEnabled( );
		
}
