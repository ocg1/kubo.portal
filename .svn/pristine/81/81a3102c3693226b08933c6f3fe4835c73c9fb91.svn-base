package mx.com.kubo.repositories.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ActualizaTiendaCollector;
import mx.com.kubo.model.SafiTiendaCollector;
import mx.com.kubo.model.TiendaCreditos;
import mx.com.kubo.repositories.TiendaCreditosDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TiendaCreditosDaoImp implements TiendaCreditosDao {
	
	private EntityManager em = null;
	
	  /**
	   * Sets the entity manager.
	   */
		
	  @PersistenceContext
	  public void setEntityManager(EntityManager em) {
	      this.em = em;
	  }
	  
	  @Override
	  public List< TiendaCreditos> loadTiendaCreditosItems(){
		  
		  List<TiendaCreditos> tiendacreditoslist=em.createQuery("from TiendaCreditos",TiendaCreditos.class).getResultList();
			return tiendacreditoslist;
		  
	  }
	  
	  @Override
	  @Transactional
	  public boolean actualizaTienda(){
		  
		  try{
			  
			  String solicitudes_a_eliminar = "";
			  
			  List<SafiTiendaCollector> tiendaSafi=  em.createNamedQuery("safiTiendaSP",SafiTiendaCollector.class)
							.setParameter("par_EmpresaID",1)
							.setParameter("aud_Usuario",1)
							.setParameter("aud_FechaActual" ,new Date())
							.setParameter("aud_DireccionIP","127.0.0.1")
							.setParameter("aud_ProgramaID" ,"Portal Kubo")
							.setParameter("aud_Sucursal" ,1)
							.setParameter("aud_NumTransaccion",1)
						.getResultList();
				

			 List<TiendaCreditos> tiendaKubo = loadTiendaCreditosItems();
			  
			  if(tiendaSafi != null && tiendaSafi.size()>0 && tiendaKubo != null && tiendaKubo.size()>0 ){
				  for( TiendaCreditos tmpKubo : tiendaKubo ){
					  boolean existe = false;
					  for( SafiTiendaCollector tmpSafi : tiendaSafi ){
						 
						  if(existeEnSafi(tmpSafi,tmpKubo)){
							//TODO actualizar saldo Disponible
							  
							  existe = true;
							  TiendaCreditos tmp = em.createQuery("from TiendaCreditos where safi_mx_solicitud_id =  ? ",TiendaCreditos.class).setParameter(1, tmpKubo.getSafi_mx_solicitud_id() ).getSingleResult();
							  tmp.setSaldoCredito( tmpSafi.getSaldoCredito()  );
							  tmp.setDisponibleFondeo( tmpSafi.getDisponibleFondeo()+""  );
							  em.merge(tmp);
							  
							  if( tmpSafi.getDisponibleFondeo() <= 0D  ){
								  existe = false;
							  }
							  
							  break;
							  
						  }
						  
					  }
					  
					  if( !existe ){
						  //LLenamos la cadena para enviar las solicitudes que no se encuentran siempre debe terminar con (,)
						  solicitudes_a_eliminar += tmpKubo.getSafi_mx_solicitud_id()+",";
					  }
					  
				  }
				  
				  //TODO llamar Procedimiento Actualiza Tienda
				  
				  List<ActualizaTiendaCollector> res =  em.createNamedQuery("actualizaTienda",ActualizaTiendaCollector.class)
							.setParameter("solicitudes_a_eliminar",solicitudes_a_eliminar)
						.getResultList();  
				  
			  }
			  
			  
			  
			  
		  }catch( Exception e ){
			  e.printStackTrace();
		  }
		  
		  return true;
	  }
	  
	  @Override
	  public TiendaCreditos getTiendaCreditosItemBySolOrCred( Integer safi_solicitud_id , String safi_credit_id ){
		  
		  List<TiendaCreditos> tiendacreditoslist = null;
		  try{
			  if( safi_credit_id != null ){
				  
				  Integer i = Integer.parseInt( safi_credit_id );
				  
				  tiendacreditoslist=em.createQuery("from TiendaCreditos where safi_credit_id = ? ",TiendaCreditos.class)
						  .setParameter( 1, (i+"") )
						  .getResultList();
				  
				  if(tiendacreditoslist==null || tiendacreditoslist.size()==0){
					  tiendacreditoslist=em.createQuery("from TiendaCreditos where safi_mx_solicitud_id = ? ",TiendaCreditos.class)
							  .setParameter(1, safi_solicitud_id)
							  .getResultList();
				  }
				  
			  }
			  else if( safi_solicitud_id != null ){
				  //System.out.println("safi_solicitud_id: "+safi_solicitud_id);
				  tiendacreditoslist=em.createQuery("from TiendaCreditos where safi_mx_solicitud_id = ? ",TiendaCreditos.class)
						  .setParameter(1, safi_solicitud_id)
						  .getResultList();
			  }
		  }catch(Exception e){
			  
			  e.printStackTrace();
			  
		  }
		  
		  if (tiendacreditoslist != null && tiendacreditoslist.size()>0 ){
			  
			  return tiendacreditoslist.get(0);
			  
		  }else{
			
			  return null;
			  
		  }
		  
	  }
	
	  private boolean existeEnSafi( SafiTiendaCollector tmpSafi,TiendaCreditos tmpKubo){
		  
		  if( tmpKubo.getSafi_mx_solicitud_id() !=null )
		  {
			  
					  Integer SafiSol = Integer.parseInt(tmpSafi.getSolicitudCreditoID()+"");
					  Integer KuboSol = Integer.parseInt(tmpKubo.getSafi_mx_solicitud_id()+"");
					  
					 //System.out.println("Comparando = "+SafiSol.toString().equals(KuboSol.toString())+"  -- > "+SafiSol+" -- " + KuboSol);
					  
					 return  SafiSol.toString().equals(KuboSol.toString());
		  }else{
			  
			  return false;
			  
		  }
	  }
	  
	  @Override
	  @Transactional
	  public boolean update(TiendaCreditos tiendaRegistro){
		  try{
		  TiendaCreditos tmp = em.createQuery("from TiendaCreditos where safi_mx_solicitud_id =  ? ",TiendaCreditos.class).setParameter(1, tiendaRegistro.getSafi_mx_solicitud_id() ).getSingleResult();
		  tmp.setSaldoCredito( tiendaRegistro.getSaldoCredito()  );
		  tmp.setDisponibleFondeo( tiendaRegistro.getDisponibleFondeo() +"" );
		  em.merge(tmp);
		  return true;
		  }catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	  }
	  
}
