package mx.com.kubo.managedbeans.registro.beneficiarios;

import javax.faces.event.ActionEvent;

import mx.com.kubo.services.SavingAccountService;

public interface IbeneficiariesIMO 
{
	//void setService_beneficiarios (BeneficiariesService service);
	void setAccountService        (SavingAccountService service);
	
	void addBeneficiaries(ActionEvent e);	
	void removeBenefic   (ActionEvent e);
	
	//void updateOrSaveBenefic(Benefi_ciaries beneficiario_bean);
}
