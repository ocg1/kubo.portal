package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Bank;

public interface BankDao {

	public List<Bank> searchBankList(String descripStr,boolean maxNum);
	public Bank getBankByShortName(String descripStr);
	
}
