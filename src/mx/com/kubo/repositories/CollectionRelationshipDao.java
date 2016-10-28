package mx.com.kubo.repositories;

import mx.com.kubo.model.CollectionRelationship;
import mx.com.kubo.model.ProyectLoanPK;

public interface CollectionRelationshipDao {

	public abstract CollectionRelationship getCollectionRelationshipByPLPK( ProyectLoanPK pylnpk );
	
}
