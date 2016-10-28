package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Bank;
import mx.com.kubo.repositories.BankDao;
import mx.com.kubo.services.BankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImp implements BankService {
	
	@Autowired
	private BankDao bankRepository;
	
	@Override
	public List<Bank> searchBankList(String descripStr,boolean maxNum){
		return bankRepository.searchBankList(descripStr,maxNum);
	}
	
	@Override
	public Bank getBankByShortName(String descripStr){
		return bankRepository.getBankByShortName(descripStr);
	}
}
