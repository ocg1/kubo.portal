package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.model.Stackholder_relationshipPK;

public interface StackholderRelDao {

	public List<Stackholder_relationship> getStackholderRelLst();
	
	public Stackholder_relationship getStackholderByPK( Stackholder_relationshipPK pk );
	
}
