package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Signature;

public interface SignatureService {

	public Signature getSignature();
	public boolean addSignature( Signature signature );
	public boolean updateSignature( Signature signature );
	public boolean deleteSignature( Signature signature );
	public List<Signature> getSignatureByProspectus( Integer prospectus_id, Integer proyect_loan_id );
	
}
