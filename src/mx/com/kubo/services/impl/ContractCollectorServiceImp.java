package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.ContratoRepCapBenCollector;
import mx.com.kubo.model.ContratoRepCapCollector;
import mx.com.kubo.repositories.ContractCollectorDao;
import mx.com.kubo.services.ContractCollectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ContractCollectorServiceImp implements ContractCollectorService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContractCollectorDao dao;
	
	public List<ContratoRepCapCollector> getContractInvList(String safi_account_id){
		return dao.getContractInvList(safi_account_id);
		
	}
	
	public List<ContratoRepCapBenCollector> getContractBenList(String safi_account_id) {
		return dao.getContractBenList(safi_account_id);
	}
}
