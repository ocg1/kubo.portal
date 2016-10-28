package mx.com.kubo.services.impl;

import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.repositories.EstatusProyectLoanDAO;
import mx.com.kubo.services.EstatusProyectLoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "estatusProyectLoanServiceIMP")
public final class EstatusProyectLoanServiceIMP 
implements EstatusProyectLoanService
{
	@Autowired
	private EstatusProyectLoanDAO estatusDAO;
	
	@Override
	public StatusProyectCat getStatus_by_PK(StatusProyectCatPK estatus_PK) 
	{		
		return estatusDAO.getStatus_by_PK(estatus_PK);
	}

}
