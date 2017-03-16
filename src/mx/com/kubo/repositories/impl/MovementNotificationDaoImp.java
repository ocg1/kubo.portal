package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.MovementNotification;
import mx.com.kubo.model.SafiDepositoRefere;
import mx.com.kubo.model.ViewDepositInfo;
import mx.com.kubo.repositories.MovementNotificationDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MovementNotificationDaoImp implements MovementNotificationDao , Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;

	/**
	 * Sets the entity manager.
	 */

	@PersistenceContext
	public void setEntityManager(EntityManager em) {

		this.em = em;

	}

	@Transactional
	@Override
	public boolean saveMovementNotification( MovementNotification newMovementNotification ) {

		try {

			em.persist(newMovementNotification);
			return true;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
			
		}

	}
	
	@Transactional
	@Override
	public boolean updateMovementNotification( MovementNotification newMovementNotification ) {

		try {

			em.merge(newMovementNotification);
			return true;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
			
		}

	}
	
	@Override
	public List< MovementNotification > getMovementNotificationInStatusCeroList(){
		
		try{
		
			String query = "from MovementNotification where status_notification = 0";
			return em.createQuery(query,MovementNotification.class).getResultList();
		
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	@Override
	public MovementNotification getMaxMovementNotification(){
		
		String query = "select * from pr_movement_notification where folioCargaID = ( select  MAX( folioCargaID ) as folioCargaID from pr_movement_notification );";
		
		try{
		
			return (MovementNotification)em.createNativeQuery(query,MovementNotification.class).getSingleResult();
			
			
		}catch( Exception e ){
		
			
			
			//e.printStackTrace();
			
			System.out.println( "Sin Resultados: getMaxMovementNotification ");
			//e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Override
	public List<SafiDepositoRefere> getMovementToSafiByFolioCarga( BigInteger folioCarga ){
		
		try{
			
			String query = "select * from view_investor_deposit where cast(FolioCargaID as unsigned) > "+folioCarga+" and FolioCargaId not in ( select folioCargaID from pr_movement_notification ); ";
			
			return (List<SafiDepositoRefere>)em.createNativeQuery(query,SafiDepositoRefere.class).getResultList();
			
		}catch( Exception e ){
		
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Override
	public List<SafiDepositoRefere> getMovementToSafiByFecha( Date fecha_de_origen ){
		
		SimpleDateFormat fr = new SimpleDateFormat( "yyyy-MM-dd" );
		
		String d = fr.format(fecha_de_origen);
		
		String query = "select * from view_investor_deposit where FechaAplica > '"+d+"' and FolioCargaId not in ( select folioCargaID from pr_movement_notification ); ";
		
		try{
		
			return (List<SafiDepositoRefere>)em.createNativeQuery(query,SafiDepositoRefere.class) .getResultList();
			
		}catch( Exception e ){
		
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public void insertaMovementToSafi( String tipoOper ){
		
		try
		{			
//			String query = "select v.* from view_deposit_info v , pr_movement_notification m where m.folioCargaID = v.ClaveRastreo and m.status_notification = 0 and m.tipoMov = 'D' ;";
//			
//			return ( List< ViewDepositInfo > ) em.createNativeQuery(query,ViewDepositInfo.class).getResultList();
		
			List< ViewDepositInfo > l = ( List< ViewDepositInfo > ) em.createNamedQuery("collectorViewDepositInfo",ViewDepositInfo.class)
					.setParameter("par_INSERTA", "S")
					.setParameter("par_TIPONOTIFICA", "C")
					.setParameter("par_TipoOperacion", tipoOper)
					.setParameter("par_TipoConsulta", 0)
					.setParameter("par_Fecha", "1900-01-01")
					.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			
		}		
		
	}

}


	

