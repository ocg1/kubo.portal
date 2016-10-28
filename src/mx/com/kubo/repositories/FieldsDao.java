package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Fields;
import mx.com.kubo.model.FieldsPK;

public interface FieldsDao {
	public Fields getFieldsById(FieldsPK fieldPk);
	public List<Fields> getListByScreenId(Integer idScreen,Integer company_id);
}
