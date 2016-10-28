package mx.com.kubo.repositories;

import mx.com.kubo.model.PademobilePayment;
import mx.com.kubo.model.PademobilePaymentPK;

public interface PademobilePaymentDao {
	
	public boolean savePademobilePayment(PademobilePayment payment);
	public boolean updatePademobilePayment(PademobilePayment payment);
	public boolean removePademobilePayment(PademobilePayment payment);
	public PademobilePayment getPademobilePaymentById(PademobilePaymentPK payment);
	
}
