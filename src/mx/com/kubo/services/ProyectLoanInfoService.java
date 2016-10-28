package mx.com.kubo.services;

import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.ProyectLoanInfoPK;

public interface ProyectLoanInfoService {

	public boolean saveProyectLoanInfo( ProyectLoanInfo proyectloaninfo );
	public boolean updateProyectLoanInfo( ProyectLoanInfo proyectloaninfo );
	public ProyectLoanInfo getProyectLoanInfo( ProyectLoanInfoPK pk );
	
}
