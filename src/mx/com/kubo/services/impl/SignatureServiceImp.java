package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.Signature;
import mx.com.kubo.repositories.SignatureDao;
import mx.com.kubo.services.SignatureService;

@Service
public class SignatureServiceImp implements Serializable,SignatureService {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected SignatureDao signaturedao;
	
	@Override
	public Signature getSignature(){
		return signaturedao.getSignature();
	}
	
	public List<Signature> getSignatureByProspectus( Integer prospectus_id , Integer proyect_loan_id ){
		return signaturedao.getSignatureByProspectus(prospectus_id, proyect_loan_id);
	}
	
	@Override
	public boolean addSignature( Signature signature ){
		return signaturedao.addSignature( signature );
	}
	
	@Override
	public boolean updateSignature( Signature signature ){
		return signaturedao.updateSignature( signature );
	}
	
	@Override
	public boolean deleteSignature( Signature signature ){
		return signaturedao.deleteSignature( signature );
	}
	
}
