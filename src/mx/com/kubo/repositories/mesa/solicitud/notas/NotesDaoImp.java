package mx.com.kubo.repositories.mesa.solicitud.notas;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;

import org.springframework.stereotype.Repository;

@Repository
public class NotesDaoImp extends DAONotasDelCasoAMO
implements NotesDao 
{	
	public final Notes getUltima_nota(ProyectLoan proyecto)
	{
		prospectus_id = proyecto.getProyectloanPk().getProspectus_id();
		company_id    = proyecto.getProyectloanPk().getCompany_id();
		proyect_id    = proyecto.getProyectloanPk().getProyect_id();
		
		query_TOKEN =  "select n from Notes n "
				     + " where n.notesPk.prospectus_id = ? "
				     + " and   n.notesPk.company_id    = ? "
				     + " and   n.proyect_id            = ? "
				     + " order by n.change_date desc";	
		
		tq = em.createQuery(query_TOKEN, Notes.class);
		
		tq.setParameter(1, prospectus_id);
		tq.setParameter(2, company_id);
		tq.setParameter(3, proyect_id);
		
		lista_notas = tq.getResultList();
		
		return lista_notas.get(0);
	}
	
	public final Notes getLastNoteByProyect( int prospectus_id, int company_id, int proyect_id )
	{		
		try
		{			
			query_TOKEN = "select MAX(n.notesPk.note_id) from Notes n "
				  + " where n.notesPk.prospectus_id = ? "
				  + " and   n.notesPk.company_id    = ? "
				  + " and   n.proyect_id            = ?";
			
			note_id  = 0;
			
			query = em.createQuery(query_TOKEN);
			
			query.setParameter(1, prospectus_id);
			query.setParameter(2, company_id);
			query.setParameter(3, proyect_id);
			
			note_id = (Integer) query.getSingleResult();
			
			query_TOKEN = "from Notes n "
				  + " where n.notesPk.prospectus_id = ? "
				  + " and   n.notesPk.company_id    = ? "
				  + " and   n.proyect_id            = ? "
				  + " and   n.notesPk.note_id       = ?";
			
			
			tq = em.createQuery(query_TOKEN, Notes.class);
			tq.setParameter(1, prospectus_id);
			tq.setParameter(2, company_id);
			tq.setParameter(3, proyect_id);
			tq.setParameter(4, note_id);
			
			nota = (Notes) tq.getSingleResult();
			
			return nota;
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
			
			return null;			
		}		
	}
	
	public final List<Notes> getListNotesByProspect(int prospectus_id, int company_id) 
	{
		query_TOKEN = "select n from Notes n "
			  + " where n.notesPk.prospectus_id = ? "
			  + " and   n.notesPk.company_id    = ? "
			  + " and   n.note_type_id         != 5 ";
		
		tq = em.createQuery(query_TOKEN, Notes.class);
		tq.setParameter(1, prospectus_id);
		tq.setParameter(2, company_id);
		
		lista_notas = tq.getResultList();
		
		return lista_notas;
	}
		
	public final List<Notes> getListaNotasPrioridadAlta(int prospectus_id, int company_id, int proyect_id, boolean fecDesc) 
	{
		query_TOKEN = "from Notes n "
			  + "where n.notesPk.prospectus_id = ? "
			  + "and   n.notesPk.company_id    = ? "
			  + "and   n.proyect_id            = ? "
			  + "and   n.priority_type_id      = 1 "
			  + "and   n.note_type_id         != 5 ";
		
		if( fecDesc )
		{
			query_TOKEN+= " order by n.change_date desc";
		}
		
		tq = em.createQuery(query_TOKEN, Notes.class);
		
		tq.setParameter(1, prospectus_id);
		tq.setParameter(2, company_id);
		tq.setParameter(3, proyect_id);
		
		lista_notas = tq.getResultList();
		
		return lista_notas;
	}
	
	public final List<Notes> getListaNotasPrioridadMediaBaja(int prospectus_id, int company_id, int proyect_id, boolean fecDesc) 
	{
		query_TOKEN =  "select n from Notes n "
					 + " where n.notesPk.prospectus_id = ? "
					 + " and   n.notesPk.company_id    = ? "
					 + " and   n.proyect_id            = ? "
					 + " and   n.priority_type_id > 1 "
					 + " and   n.note_type_id != 5 ";
					 		
		if( fecDesc )
		{
			query_TOKEN += " order by n.change_date desc";
		}
		
		tq = em.createQuery(query_TOKEN, Notes.class);
		tq.setParameter(1, prospectus_id);
		tq.setParameter(2, company_id);
		tq.setParameter(3, proyect_id);
		
		lista_notas = tq.getResultList();
		
		return lista_notas;
	}
	
	public final List<Notes> getListaNotasRechazo(ProyectLoan proyecto)
	{
		prospectus_id = proyecto.getProyectloanPk().getProspectus_id();
		company_id    = proyecto.getProyectloanPk().getCompany_id();
		proyect_id    = proyecto.getProyectloanPk().getProyect_id();
		
		query_TOKEN =  "select n from Notes n "
				     + " where n.notesPk.prospectus_id = ? "
				     + " and   n.notesPk.company_id    = ? "
				     + " and   n.proyect_id   = ? "
				     + " and   n.note_type_id = 7 "
				     + " order by n.change_date desc";
		
		tq = em.createQuery(query_TOKEN, Notes.class);
		tq.setParameter(1, prospectus_id);
		tq.setParameter(2, company_id);
		tq.setParameter(3, proyect_id);
		
		lista_notas = tq.getResultList();
		
		return lista_notas;
	}
		
	public final List<Notes> getListNotesByProspectByType(int prospectus_id, int company_id, int type, boolean fecDesc) 
	{
		query_TOKEN = "select n from Notes n "
			  + "where n.notesPk.prospectus_id = ? "
			  + "and n.notesPk.company_id      = ? "
			  + "and n.note_type_id            = ? ";
		
		if(fecDesc)
		{
			query_TOKEN += " order by n.change_date desc";
		}
		
		tq = em.createQuery(query_TOKEN, Notes.class);
		
		tq.setParameter(1, prospectus_id);
		tq.setParameter(2, company_id);
		tq.setParameter(3, type);
		
		lista_notas = tq.getResultList();
		
		return lista_notas;
	}
	
	public final List<Notes> getLista_notas_por_tipo(int note_type_id, ProyectLoan proyecto)
	{
		if( proyecto != null ){
			
			prospectus_id = proyecto.getProyectloanPk().getProspectus_id();
			company_id    = proyecto.getProyectloanPk().getCompany_id();
			proyect_id    = proyecto.getProyectloanPk().getProyect_id();
			
			query_TOKEN =  "select n from Notes n "
					     + " where n.notesPk.prospectus_id = ? "
					     + " and   n.notesPk.company_id    = ? "
					     + " and   n.proyect_id            = ? "
					     + " and   n.note_type_id          = ? "
					     + " order by n.change_date desc";
					
			tq = em.createQuery(query_TOKEN, Notes.class);
			
			tq.setParameter(1, prospectus_id);
			tq.setParameter(2, company_id);
			tq.setParameter(3, proyect_id);
			tq.setParameter(4, note_type_id);
					
			lista_notas = tq.getResultList();
			
			return lista_notas;
			
		}else{
			
			return lista_notas = new ArrayList<Notes>();
			
		}
	}

}
