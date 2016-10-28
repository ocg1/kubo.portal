package mx.com.kubo.services;

import mx.com.kubo.model.ContractSignature;
import mx.com.kubo.model.ContractSignaturePK;

public interface ContractSignatureService {

	public ContractSignature getContractSignature ( ContractSignaturePK pk );
	public boolean addContractSignature ( ContractSignature contractSig );
	public boolean updateContractSignature ( ContractSignature contractSig );
}
