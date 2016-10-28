package mx.com.kubo.services.impl;

import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;
import mx.com.kubo.repositories.BeneficiariesDao;
import mx.com.kubo.services.BeneficiariesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class BeneficiariesServiceImp
implements BeneficiariesService 
{
	@Autowired
	protected BeneficiariesDao dao;
		
	public Beneficiaries getBeneficiariesByID(BeneficiariesPK beneficiariesPk) 
	{
		return dao.getBeneficiariesByID(beneficiariesPk);
	}

	public boolean addBeneficiaries(Beneficiaries newBeneficiaries,int prospectusID, int companyID) 
	{
		return dao.addBeneficiaries(newBeneficiaries, prospectusID, companyID);
	}

	public boolean updateBeneficiaries(Beneficiaries beneficiaries) 
	{
		return dao.updateBeneficiaries(beneficiaries);
	}

	public List<Beneficiaries> getListBeneficiariesByProspect(int prospectID,int companyID) 
	{
		return dao.getListBeneficiariesByProspect(prospectID, companyID);
	}

	public List<Beneficiaries> getListBeneficByProspectByAccount(int prospectID, int companyID, int account) 
	{
		return dao.getListBeneficByProspectByAccount(prospectID, companyID, account);
	}

	public boolean saveBeneficByID(Beneficiaries beneficiaries) 
	{
	 return dao.saveBeneficByID(beneficiaries);		
	}

	public boolean removeBenefic(BeneficiariesPK beneficPk) 
	{
		return dao.removeBenefic(beneficPk);
	}
}
