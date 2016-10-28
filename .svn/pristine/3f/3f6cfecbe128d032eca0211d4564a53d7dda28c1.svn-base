package mx.com.kubo.repositories.mesa.solicitud.notas;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesMetaInfo;

@Repository("dao_notes_meta_info")
public class DAONotesMetaInfoIMP extends DAONotasDelCasoDMO
implements DAONotesMetaInfoIMO
{
	@Transactional
	public final boolean persist(NotesMetaInfo note_meta_info) 
	{
		try 
		{
			em.persist(note_meta_info);
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional
	public final boolean update(NotesMetaInfo note_meta_info) 
	{
		try
		{
			em.merge(note_meta_info);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	public final NotesMetaInfo getMeta_info(Notes nota)
	{
		prospectus_id = nota.getNotesPk().getProspectus_id();
		company_id    = nota.getNotesPk().getCompany_id();
		note_id       = nota.getNotesPk().getNote_id();
		
		query_TOKEN =  "select n from NotesMetaInfo n "
			        + " where n.meta_info_PK.prospectus_id = ? "
			        + " and   n.meta_info_PK.company_id    = ? "
			        + " and   n.meta_info_PK.note_id       = ? ";
		
		tq_meta = em.createQuery(query_TOKEN, NotesMetaInfo.class);
		tq_meta.setParameter(1, prospectus_id);
		tq_meta.setParameter(2, company_id);
		tq_meta.setParameter(3, note_id);
		
		try
		{
			meta_info = (NotesMetaInfo) tq_meta.getSingleResult();
			
		} catch (Exception e) {
									
			meta_info = null;
		}
		
		return meta_info;
	}
}
