package mx.com.kubo.managedbeans.mesa.solicitud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ListInvestor;
import mx.com.kubo.managedbeans.ApplicationParams;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ViewInvestmetInProyect;

@ManagedBean
@ViewScoped
public class InvestorListByProyect extends InvestorListByProyectDMO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){
		
		Calendar c_t = Calendar.getInstance();
		c_t.setTime( new Date() );
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String proyectLoanId = (String) facesContext.getExternalContext().getRequestParameterMap().get("proyectLoan");
		
		//System.out.println("Cargando Lista de Inversionistas proyecto: " + proyectLoanId );
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		ApplicationParams applicationParam = (ApplicationParams) FacesContext.getCurrentInstance()
												.getApplication().getELResolver()
												.getValue(elContext, null, "applicationParams");
		
		
		flagAutorizado = true;
		
		if( proyectLoanId != null ){
			
			String[] valores =  proyectLoanId.split("_");
			
			if( valores.length > 0 ){
				
				Calendar c_m = Calendar.getInstance();
				c_m.setTime( new Date() );
				
				if( !valores[0].equals("null") &&  !valores[0].equals("0") && valores[0].trim().length()>0 ){
				
					listInvestors = (List<ViewInvestmetInProyect>)fundingService.getListInvestorbyProyectId( Integer.parseInt( valores[0] ) , null );
				
				}else if(  !valores[1].equals("null") && !valores[1].equals("0") && valores[1].trim().length()>0   ) {
					listInvestors = (List<ViewInvestmetInProyect>)fundingService.getListInvestorbyProyectId( null, Integer.parseInt( valores[1] ) );
				}
				
				Calendar c2 = Calendar.getInstance();
				c2.setTime( new Date() );
				
				long l = c2.getTimeInMillis() - c_m.getTimeInMillis();
				
				Long seg = l/1000;
				
				long m = l%1000;
				
				Long min =  Long.valueOf((seg.intValue()))/60;
				
				seg = Long.valueOf((seg.intValue()))%60;
				
				System.out.println( "Tiempo en cargarLista de Inversionistas: " + min.intValue()+"m " + seg +"s " + m+"ms");
				
				
				
				if( !valores[0].equals("null") && !valores[0].equals("0") && valores[0].trim().length()>0 ){
				
					actualProyect = servicioProyecto.getProyectLoanListBySafiCreditID( valores[0] );
				
				}else if( !valores[1].equals("null") && !valores[1].equals("0") && valores[1].trim().length()>0 )
					{
						actualProyect = servicioProyecto.getProyectLoanBySafiSolicitudID( valores[1] );
					}
				
				if( listInvestors != null && listInvestors.size()>0 ){
					
					
					ammountAvailable = listInvestors.get(0).getDisponibleFondeo();
					ammountFounded = 0D;
					listInvestor_lst = new ArrayList<ListInvestor>();
					
					Calendar c = Calendar.getInstance();
					c.setTime( new Date() );
					
					Hashtable<String,Membership> member_inv_HT =  applicationParam. getHt_member_investor();
					
					for( ViewInvestmetInProyect invest : listInvestors ){
						
						ListInvestor item = new ListInvestor();
						
						item.setAmmount( invest.getAmount() );
						item.setPorcentaje( invest.getPorcentajeFondeo() );
						
						Membership membership = member_inv_HT.get(invest.getProyectloanfundingPk().getInvestor());  // service_membership.getMemberShipBySafiClientID( invest.getProyectloanfundingPk().getInvestor() );
						
						if( membership == null ){
							membership = getMember();
						}
						
						item.setMembership(membership);
						
						listInvestor_lst.add(item);
						
						ammountFounded += invest.getAmount();
						
					}
					
					Calendar c2_cargaMem = Calendar.getInstance();
					c2_cargaMem.setTime( new Date() );
					
					long l_cargaMem = c2_cargaMem.getTimeInMillis() - c.getTimeInMillis();
					
					Long seg_cargarMem = l_cargaMem/1000;
					
					long m_cargaMem = l_cargaMem%1000;
					
					Long min_cargaMem =  Long.valueOf((seg_cargarMem.intValue()))/60;
					
					seg_cargarMem = Long.valueOf((seg_cargarMem.intValue()))%60;
					
					System.out.println( "Tiempo en cargar membership Inversionistas: " + min_cargaMem.intValue()+"m " + seg_cargarMem +"s " + m_cargaMem+"ms");
					
					
				}else if( actualProyect != null ) {
					ammountAvailable = actualProyect.getAmmount();
				}
				
				if( actualProyect != null ){
					
					MembershipPK pk = new MembershipPK();
					
					pk.setCompany_id( actualProyect.getProyectloanPk().getCompany_id() );
					pk.setProspectus_id( actualProyect.getProyectloanPk().getProspectus_id() );
					
					member_Solicitud = service_membership.getMembershipById(pk);
					
				}
			
			}else{
				
				flagAutorizado = false;
				
			}
			
		}
		
		Calendar c2T = Calendar.getInstance();
		c2T.setTime( new Date() );
		
		long l = c2T.getTimeInMillis() - c_t.getTimeInMillis();
		
		Long seg = l/1000;
		
		long m = l%1000;
		
		Long min =  Long.valueOf((seg.intValue()))/60;
		
		seg = Long.valueOf((seg.intValue()))%60;
		
		System.out.println( "Tiempo total cargar INIT: " + min.intValue()+"m " + seg +"s " + m+"ms");
		
	}
	
private Membership getMember(){
		
		Membership mem = new Membership();
		NaturalPerson nat = new NaturalPerson();
		Prospectus pro = new Prospectus();
		
		nat.setProspectus(pro);
		mem.setPerson(nat);
		
		mem.setNickname("No  disponible");
		
		return mem;
		
	}
	
	public Double getPorcentaje( Double ammount ){
		
		return ( ammount * 100 )/actualProyect.getAmmount();
		
	}
	
}
