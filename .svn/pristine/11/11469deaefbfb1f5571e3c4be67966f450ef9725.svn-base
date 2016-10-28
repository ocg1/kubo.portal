package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;

public interface BeneficiariesDao {
	public Beneficiaries getBeneficiariesByID(BeneficiariesPK beneficiariesPk);
	public boolean addBeneficiaries(Beneficiaries newBeneficiaries,int prospectusID,int companyID);
	public boolean saveBeneficByID(Beneficiaries beneficiaries);
	public boolean updateBeneficiaries(Beneficiaries beneficiaries);
	public List<Beneficiaries> getListBeneficiariesByProspect(int prospectID,int companyID);
	public List<Beneficiaries> getListBeneficByProspectByAccount(int prospectID,int companyID,int account);
	public boolean removeBenefic(BeneficiariesPK beneficPk);
}
