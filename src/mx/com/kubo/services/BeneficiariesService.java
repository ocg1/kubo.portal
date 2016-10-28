package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;


public interface BeneficiariesService 
{
	Beneficiaries getBeneficiariesByID(BeneficiariesPK beneficiariesPk);

	List<Beneficiaries> getListBeneficiariesByProspect(int prospectID,int companyID);
	List<Beneficiaries> getListBeneficByProspectByAccount(int prospectID,int companyID,int account);
	
	boolean addBeneficiaries   (Beneficiaries newBeneficiaries,int prospectusID,int companyID);
	boolean saveBeneficByID    (Beneficiaries beneficiaries);
	boolean updateBeneficiaries(Beneficiaries beneficiaries);
	boolean removeBenefic      (BeneficiariesPK beneficPk);
}
