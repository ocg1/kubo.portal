package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.FileCategory;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.FileTypePK;
import mx.com.kubo.repositories.FileTypeDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
@Repository
public class FileTypeDaoImp implements FileTypeDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public List<FileType> getListFileTypeByCategory(int category) 
	{
		List<FileType> fileType = em.createQuery("from FileType f where f.file_category_id=? order by name",FileType.class)
				.setParameter(1,category)
				.getResultList();
		return fileType;
	}

	@Override
	public List<FileCategory> getListFileCatergory() {
		List<FileCategory> listCat=em.createQuery(
			    "from FileCategory ", FileCategory.class)
			    .getResultList();
		return listCat;
	}

	@Override
	public List<FileType> getListFileType() {
		List<FileType> listType=em.createQuery("from FileType order by file_category_id,name",FileType.class).getResultList();
		return listType;
	}
	@Override
	public FileType getFileTypeById(FileTypePK pk){
		try{
			return em.find(FileType.class, pk);
		}catch(Exception e){
			return null;
		}
	}

}
