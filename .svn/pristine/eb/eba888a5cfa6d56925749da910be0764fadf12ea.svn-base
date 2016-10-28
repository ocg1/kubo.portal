package mx.com.kubo.repositories.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.repositories.FilesDao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FilesDaoImp 
implements FilesDao 
{
	
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	public Files getFilebyID(FilesPK filessPk) 
	{
		return em.find(Files.class, filessPk);
	}
	
	@Transactional
	public boolean addFile(Files newFile, int prospectusID, int companyID) 
	{
		Integer idfile=0;
		idfile=(Integer) em.createNamedQuery("queryAddFile")
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.getSingleResult();
		if(idfile==null){			
			idfile=1;
		}
		else{
			idfile++;
			}
		log.info("Maximo de files= "+idfile);
		newFile.getFilesPk().setFile_id(idfile);
		try {
			
			em.persist(newFile);			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional	
	public boolean updateFile(Files file) 
	{
		log.info("updateFile.FilesDaoImp");
		try{
			em.merge(file);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public Files getFileByTypeID(int prospectusID, int companyID, int proyectLoanID, int typeFileID) {
		String query="";
		Integer id=0;
		
		if(proyectLoanID>0){
			
			query="select max(file_id) from gn_file  where prospectus_id=? and company_id=? and file_type_id=? and proyect_loan_id = ?";
			
			id=(Integer)em.createNativeQuery(query)
					.setParameter(1,prospectusID)
					.setParameter(2, companyID)
					.setParameter(3, typeFileID)
					.setParameter(4, proyectLoanID)
					.getSingleResult();
			
		}else{
			query="select max(file_id) from gn_file  where prospectus_id=? and company_id=? and file_type_id=?";
			
			id=(Integer)em.createNativeQuery(query)
					.setParameter(1,prospectusID)
					.setParameter(2, companyID)
					.setParameter(3, typeFileID)
					.getSingleResult();
			
		}
		//String query="select max(f.filesPk.file_id) from Files f  where f.filesPk.prospectus_id=? and f.filesPk.company_id=? and f.filesPk.file_type_id=? and f.filesPk.proyect_loan_id = ?";
		
		
		log.info("file id es= "+id);
		if(id==null){
			return null;
		}
		else{
			if(proyectLoanID == 0){
				
				String fileStr = "from Files where filesPk.file_id = ? and filesPk.company_id = ? and filesPk.prospectus_id = ? and filesPk.file_type_id = ? "; 
			
				List<Files> files = em.createQuery( fileStr ,Files.class)
										.setParameter(1, id)
										.setParameter(3, prospectusID)
										.setParameter(2, companyID)
										.setParameter(4, typeFileID)
										.getResultList();
				
				if( files != null && files.size() > 0 ){
					
					return files.get(0);
					
				}else{
					return null;
				}
				
			}else{
				
				FilesPK filesPk=new FilesPK(id,prospectusID,companyID, proyectLoanID, typeFileID);	
				return em.find(Files.class,filesPk);
				
			}
		}
	}

	public List<Files> getListFiles() {
		List<Files> files=em.createQuery("from Files",Files.class).getResultList();
		return files;
	}
	
	public List<Files> getListaContratos(int prospectusID, int companyID) 
	{
		String query;
		
		List<Files> files = null;
		
		TypedQuery<Files> typed;
		
		query = "from Files f "
				+ "where f.filesPk.prospectus_id   = ? "
				+ "and   f.filesPk.company_id      = ? "
				+ "and   f.fileType.fileCategory.fileCategoryPk.file_category_id = 8 "
				+ "order by f.filesPk.proyect_loan_id desc";
		
		typed = em.createQuery(query, Files.class);
		typed.setParameter(1, prospectusID);
		typed.setParameter(2, companyID);
		
		files = typed.getResultList();
		
		return files;
	}

	public List<Files> getListFilesByProspect(int prospectusID, int companyID, int proyectLoanID) 
	{
		List<Files> files = null;
		
		if(proyectLoanID>0)
		{
			 files=em.createQuery("from Files f where f.filesPk.prospectus_id=? and f.filesPk.company_id=? and f.filesPk.proyect_loan_id=?",Files.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, proyectLoanID)
				.getResultList();
		}else{
			files=em.createQuery("from Files f where f.filesPk.prospectus_id=? and f.filesPk.company_id=?",Files.class)
					.setParameter(1, prospectusID)
					.setParameter(2, companyID)
					.getResultList();
		}
		return files;
	}
	
	public List<Files> getListFilesByProspectOrderByCategory(int prospectusID, int companyID, int proyectLoanID) 
	{
		String query;
		
		query = "from Files f "
				+ "where f.filesPk.prospectus_id   = ? "
				+ "and   f.filesPk.company_id      = ? "
				+ "and   f.filesPk.proyect_loan_id = ? "
				+ "order by f.fileType.fileCategory.fileCategoryPk.file_category_id,"
				+ "f.fileType.fileTypePk.file_type_id ";
		
		List<Files> files = em.createQuery(query,Files.class)
				.setParameter(1, prospectusID)
				.setParameter(2, companyID)
				.setParameter(3, proyectLoanID)
				.getResultList();
		
		return files;
	}
	
	@Transactional
	public boolean removeFile(FilesPK filePk) 
	{
		boolean bandera=false;	
		 try{
			    log.info("Inicia eliminar");
			    Files filex = em.find(Files.class, filePk);
			    if(filex!=null)
			    	em.remove(filex); 
			    log.info("Termina eliminar");
			    bandera= true;
		 }
		 catch (Exception e) {
				log.info("Error"+e.getMessage());
				bandera=false;
			}
		 finally {
			    em.close();
			  }
		 return bandera;
	}

	@Transactional
	public boolean removeFileByProyectLoanID(int prospectusID, int companyID, int proyectLoanID, int fileTypeID) 
	{
		
		EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
		Session session = (Session) entityManager.unwrap(Session.class);
		try{
		String strQuery = "delete from Files where filesPk.prospectus_id = :prospectusID"
				+ " and filesPk.company_id = :companyID and filesPk.proyect_loan_id = :proyectLoanID"
				+ " and filesPk.file_type_id = :fileTypeID";

		Query query = session.createQuery(strQuery)
				.setParameter("prospectusID", prospectusID)
				.setParameter("companyID", companyID)
				.setParameter("proyectLoanID", proyectLoanID)
				.setParameter("fileTypeID", fileTypeID);
		query.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
