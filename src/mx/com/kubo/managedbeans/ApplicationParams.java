package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.StackholderRelService;

@ManagedBean
@ApplicationScoped
public class ApplicationParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{stackholderRelServiceImp}")
	StackholderRelService stackholderrelservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	private List<Stackholder_relationship> stackholderRelLst;
	private List<Membership> member_investor;
	private Hashtable<String,Membership> ht_member_investor;
	
	private boolean cargaParamentosAplicacion;
	
	@PostConstruct
	public void init(){
		
		 Calendar c_m = Calendar.getInstance();
		 c_m.setTime( new Date() );
		
		 stackholderRelLst = stackholderrelservice.getStackholderRelLst();
		 
		 member_investor = membershipService.getInvestorActive();
		
		 init_HT();
		 
		 Calendar c2 = Calendar.getInstance();
			c2.setTime( new Date() );
			
			long l = c2.getTimeInMillis() - c_m.getTimeInMillis();
			
			Long seg = l/1000;
			
			long m = l%1000;
			
			Long min =  Long.valueOf((seg.intValue()))/60;
			
			seg = Long.valueOf((seg.intValue()))%60;
			
			System.out.println( "Tiempo en cargar Parametros de Aplicacion: " + min.intValue()+"m " + seg +"s " + m+"ms");
		 
	}
	
	private void init_HT(){
		
		ht_member_investor = new Hashtable<String,Membership>();
		
		for( Membership mem : member_investor ){
			
			Integer i = Integer.parseInt(mem.getPerson().getSafi_client_id());
			
			ht_member_investor.put(""+i+"", mem);
			
		}
		
	}

	public StackholderRelService getStackholderrelservice() {
		return stackholderrelservice;
	}

	public void setStackholderrelservice(StackholderRelService stackholderrelservice) {
		this.stackholderrelservice = stackholderrelservice;
	}

	public List<Stackholder_relationship> getStackholderRelLst() {
		return stackholderRelLst;
	}

	public void setStackholderRelLst(
			List<Stackholder_relationship> stackholderRelLst) {
		this.stackholderRelLst = stackholderRelLst;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public List<Membership> getMember_investor() {
		return member_investor;
	}

	public void setMember_investor(List<Membership> member_investor) {
		this.member_investor = member_investor;
	}

	public Hashtable<String, Membership> getHt_member_investor() {
		return ht_member_investor;
	}

	public void setHt_member_investor(Hashtable<String, Membership> ht_member_investor) {
		this.ht_member_investor = ht_member_investor;
	}

	public boolean isCargaParamentosAplicacion() {
		return cargaParamentosAplicacion;
	}

	public void setCargaParamentosAplicacion(boolean cargaParamentosAplicacion) {
		this.cargaParamentosAplicacion = cargaParamentosAplicacion;
	}
	
}
