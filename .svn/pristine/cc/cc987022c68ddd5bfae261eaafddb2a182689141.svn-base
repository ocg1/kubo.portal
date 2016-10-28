package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ContractSignature;
import mx.com.kubo.model.ContractSignaturePK;
import mx.com.kubo.repositories.ContractSignatureDao;
import mx.com.kubo.services.ContractSignatureService;

@Service
public class ContractSignatureServiceImp implements Serializable,ContractSignatureService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContractSignatureDao repository;
	
	@Override
	public ContractSignature getContractSignature ( ContractSignaturePK pk ){
		return repository.getContractSignature(pk);
	}
	
	@Override
	public boolean addContractSignature ( ContractSignature contractSig ){
		return repository.addContractSignature(contractSig);
	}
	
	@Override
	public boolean updateContractSignature ( ContractSignature contractSig ){
		return repository.updateContractSignature(contractSig);
	}
	
}
