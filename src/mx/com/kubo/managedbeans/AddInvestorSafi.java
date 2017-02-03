package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.MembershipPK;

@ManagedBean @ViewScoped
public class AddInvestorSafi extends AddInvestorSafiAMO 
implements Serializable
{
	private static final long serialVersionUID = -739689243478885824L;

	@PostConstruct
	public void init() 
	{		
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		init_investor();
		init_menu();
	}

	public boolean finalizaSolicitud()
	{					
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		membership_PK = new MembershipPK();		
		membership_PK.setCompany_id(sesion.getCompany_id());
		membership_PK.setProspectus_id(sesion.getProspectus_id());
		
		membership = membershipservice.getMembershipById(membership_PK);
		
		person = membership.getPerson();
		
		creaProspect_INV_SGB();
		init_documents_review();
		notificar();
		
		if(notificacion_OK)
		{			
			investor_PK = new InvestorPK();						
			investor_PK.setCompany_id(sesion.getCompany_id());
			investor_PK.setProspectus_id(sesion.getProspectus_id());
			
			investor = investorservice.getInvestorById(investor_PK);
			
			investor.setStatus_id(1);
			investor.setDate_published(new Date());
			
			hubspot_update_person();
						
			if(investorservice.updateInvestor(investor))
			{				
				System.out.println("Actualizacion de status: 1");
				
				registraAcceso( sesion, 20, 100);
				registraAcceso( sesion, 18, 100);
				
			} else {
				
				System.out.println("Error en la Actualizacion de status: 0");				
			}
			
			msg = "Tus datos como inversionista han sido creados satisfactoriamente";
			success =true;
			error =false;
			wait =false;
			successFull = true;
			 
		} else {
			
			msg = "Error al dar de alta la cuenta del inversionista";
			success =false;
			error =true;
			wait =false;
		}
		
		return true;
	}

	public void setSuccessFullOn()
	{
		successFull = true;
	}
	
	public void cargaInfo ()
	{	
		faces = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		membership_PK = new MembershipPK();		
		membership_PK.setCompany_id(sesion.getCompany_id());
		membership_PK.setProspectus_id(getProspectus_id());
		
		membership = membershipservice.getMembershipById(membership_PK);
		
		person = membership.getPerson();
	}
}

