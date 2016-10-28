package mx.com.kubo.mesa.solicitud.investor;

import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ServiceCallingService;

public interface ActivadorIMO 
{
	void setService_PLD            (PrevencionLDService service);
	void setService_saving_account(SavingAccountService service);
	void setService_investor           (InvestorService service);
	void setService_calling      (ServiceCallingService service);
	void setService_natural_person(NaturalPersonService service);
	void setService_membership       (MembershipService service);
}
