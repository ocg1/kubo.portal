package mx.com.kubo.services;

import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;

public interface EstatusProyectLoanService 
{
	StatusProyectCat getStatus_by_PK(StatusProyectCatPK estatus_PK);
}
