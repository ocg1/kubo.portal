package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.SaldoInversionista;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.repositories.SaldoInversionistaDao;
import mx.com.kubo.services.SaldoInversionistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaldoInversionistaServiceImp implements Serializable,SaldoInversionistaService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	SaldoInversionistaDao saldoinversionistadao;
	
	public SaldoInversionista getSaldoByAccount( String account ){
		
		return saldoinversionistadao.getSaldoByAccount(account);
		
	}
	
}
