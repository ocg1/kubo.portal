package mx.com.kubo.managedbeans.mesa;

import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;

public abstract class InvestorListAMO extends InvestorListDMO 
{
	protected void init_proyect_loan() 
	{
		proyect_loan_PK = new ProyectLoanPK();
		proyect_loan_PK.setCompany_id(company_id);
		proyect_loan_PK.setProspectus_id(prospectus_id_inv);
		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id(company_id);
		membership_PK.setProspectus_id(prospectus_id_inv);
		
		membership = membershipservice.getMembershipById(membership_PK);
		
		proyect_loan = new ProyectLoan();		
		proyect_loan.setPerson( membership.getPerson() );
		proyect_loan.setProyectloanPk(proyect_loan_PK);
		
		Integer tipo_identificacion = proyect_loan.getPerson().getIdentification_type_id();
		
		acreditado_IFE = null;
		
		if(tipo_identificacion != null)
		{
			switch(tipo_identificacion)
			{
				case IFE:
					acreditado_IFE = proyect_loan.getPerson().getMx_ife_cveelector();
				break;
				
				case INE:
					acreditado_IFE = proyect_loan.getPerson().getMx_ine_cic();
				break;
			}
		}
	}
	
	protected void setPermissions(int role_id)
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		rfc = (RoleFunctionController) resolver.getValue(elContext, null, "roleFunctionController");
		
		rolefunctionlistbyrole = rfc.getFunctionByRole(role_id);
		
		for(RoleFunction rf : rolefunctionlistbyrole)
		{			
			if(rf.getPk().getFunction_id() == SOLICITUD_INVERSION)
			{ 				
				activation = true;			
			}
			
		}		
	}
	
	protected boolean ejecuta_store_en_safi( Membership membershipHijo)
	{		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id(membershipHijo.getMembershipPK().getCompany_id());
		membership_PK.setProspectus_id(thisTutor.getProspectus_id_tutor());
		
		Membership membershipTutor = membershipservice.getMembershipById(membership_PK);
		
		return savingaccountservice.executeSP_Relaciona_Menor(membershipHijo.getPerson().getSafi_client_id(), membershipTutor.getPerson().getSafi_client_id());
		
	}
	
	protected boolean Asignamos_cuenta_a_tutor ( SavingAccount saving_del_hijo )
	{		
		if( thisTutor != null )
		{			
			SavingAccount saving = savingaccountservice.getSavingAccountByProspectus(thisTutor.getProspectus_id_tutor(), thisTutor.getCompany_id());
			
			if(saving == null)
			{
				SavingAccountPK savingPK = new SavingAccountPK();
				savingPK.setCompany_id(thisTutor.getCompany_id());
				savingPK.setProspectus_id(thisTutor.getProspectus_id_tutor());
				 saving = new SavingAccount();
				saving.setDescription("Cuenta de Inversión Kubo");
				saving.setSaving_accountPk(savingPK);
				
				savingaccountservice.addSavingAccount(saving, savingPK.getProspectus_id(), savingPK.getCompany_id());
				
			}
			
			if( saving != null )
			{
				
				saving.setSafi_account_id(saving_del_hijo.getSafi_account_id());
				
				saving.setStatus( saving_del_hijo.getStatus() );
				
				savingaccountservice.updateSavingAccount(saving);
				
				return true;
				
			} else {
				
				return false;
			}
		
		} else {
			return false;
		}
	}
}
