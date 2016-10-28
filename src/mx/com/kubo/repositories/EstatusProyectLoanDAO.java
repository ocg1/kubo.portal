package mx.com.kubo.repositories;

import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;

public interface EstatusProyectLoanDAO 
{
	StatusProyectCat getStatus_by_PK(StatusProyectCatPK estatus_PK);
}
