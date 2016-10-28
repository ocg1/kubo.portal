package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Fields;
import mx.com.kubo.model.FieldsPK;

public interface FieldsService {
	public abstract Fields getFieldsById(FieldsPK fieldPk);
	public abstract List<Fields> getListByScreenId(Integer idScreen,Integer company_id);
}
