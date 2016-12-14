package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.AyudaDocumentos;

public interface AyudaDocumentosDao {

	List<AyudaDocumentos> getAyudaDocumentosList( Integer dias );
	
}
