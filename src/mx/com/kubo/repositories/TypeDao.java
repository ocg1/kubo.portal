package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.TypeCat;

public interface TypeDao {
	public List<TypeCat> loadTypeList();
	public List<TypeCat> loadTypeListBySelectable();
}
