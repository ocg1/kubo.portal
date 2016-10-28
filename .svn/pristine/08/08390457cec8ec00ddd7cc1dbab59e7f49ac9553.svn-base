package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.FileCategory;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.repositories.FileTypeDao;
import mx.com.kubo.services.FileTypeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileTypeServiceImp implements FileTypeService {

	Logger log = Logger.getLogger(getClass());
	@Autowired
	private FileTypeDao fileTypeRepository;
	
	@Override
	public List<FileType> getListFileTypeByCategory(int category) {
		return fileTypeRepository.getListFileTypeByCategory(category);
	}

	@Override
	public List<FileCategory> getListFileCatergory() {		
		return fileTypeRepository.getListFileCatergory();
	}

	@Override
	public List<FileType> getListFileType() {
		return fileTypeRepository.getListFileType();
	}
	
	@Override
	public FileType getFileTypeById(FileTypePK pk){
		return fileTypeRepository.getFileTypeById(pk);
	}

}
