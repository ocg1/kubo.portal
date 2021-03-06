package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ContratoRepCapBenCollector;
import mx.com.kubo.model.ContratoRepCapCollector;

public interface ContractCollectorDao {
	public List<ContratoRepCapCollector> getContractInvList(String safi_account_id);
	public List<ContratoRepCapBenCollector> getContractBenList(String safi_account_id);
}
