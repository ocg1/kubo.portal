package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.FromWhereCat;
import mx.com.kubo.model.FromWhereCatPK;

public interface FromWhereCatService {
	public abstract FromWhereCat getFromWhereByID(FromWhereCatPK pk);
	public abstract List<FromWhereCat> getListFromWhere();
}
