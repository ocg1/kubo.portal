package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.PademobilePayment;
import mx.com.kubo.model.PademobilePaymentPK;
import mx.com.kubo.repositories.PademobilePaymentDao;
import mx.com.kubo.services.PademobilePaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PademobilePaymentServiceImp implements Serializable,PademobilePaymentService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PademobilePaymentDao paymentrepository;

	@Transactional
	@Override
	public boolean savePademobilePayment(PademobilePayment payment){
		return paymentrepository.savePademobilePayment(payment);
	}
	
	@Transactional
	@Override
	public boolean updatePademobilePayment(PademobilePayment payment){
		return paymentrepository.updatePademobilePayment(payment);
	}
	
	@Transactional
	@Override
	public boolean removePademobilePayment(PademobilePayment payment){
		return paymentrepository.removePademobilePayment(payment);
	}
	
	@Override
	public PademobilePayment getPademobilePaymentById(PademobilePaymentPK payment){
		return paymentrepository.getPademobilePaymentById(payment);
	}
	
	
}
