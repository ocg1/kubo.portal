package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.FromWhereCat;
import mx.com.kubo.model.FromWhereCatPK;

public interface FromWhereCatDao {
	public  FromWhereCat getFromWhereByID(FromWhereCatPK pk);
	public  List<FromWhereCat> getListFromWhere();
}
