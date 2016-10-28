package mx.com.kubo.repositories;

import mx.com.kubo.model.ProyectInfo;

public interface ProyectInfoDao {

	public ProyectInfo getProyectInfoByProyectLoan( Integer proyect_loan_id );
	
}
