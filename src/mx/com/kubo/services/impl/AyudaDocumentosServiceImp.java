package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.AyudaDocumentos;
import mx.com.kubo.repositories.AyudaDocumentosDao;
import mx.com.kubo.services.AyudaDocumentosService;

@Service
public class AyudaDocumentosServiceImp implements Serializable, AyudaDocumentosService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	AyudaDocumentosDao dao;
	
	public List<AyudaDocumentos> getAyudaDocumentosList( Integer dias ){
	
		return dao.getAyudaDocumentosList(dias);
		
	}
	
}
