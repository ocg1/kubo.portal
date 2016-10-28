package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.repositories.AddressDao;

import org.springframework.stereotype.Repository;

@Repository
public final class AddressDaoImp extends AddressDaoAMO
implements AddressDao 
{
	public int getMaxAddressId(int prospectusID, int companyID) 
	{
		log.info("MaxAddressId.AddressDaoImp");
		
		String query = "select MAX(a.address_id) from gn_address a where prospectus_id = ? and company_id = ?";
		
		Integer address_id = (Integer) em.createNativeQuery(query)
					     .setParameter(1, prospectusID)
					     .setParameter(2, companyID) 
					     .getSingleResult();
		
		if(address_id == null)
		{
			address_id = 1;
			
		} else { 
			
			address_id++;
		}
		
		return address_id;
	}
	
	public Address getMaxAddressByType(int prospectusID,int companyID,int addressType) 
	{
		log.info("MaxAddressHome.AddressDaoImp");
		
		String query = "select MAX(a.address_id) from gn_address a "
			  + "where prospectus_id = ? "
			  + "and company_id      = ? "
			  + "and address_type_id = ?";
		
		Integer address_id = (Integer) em.createNativeQuery(query)
						 .setParameter(1, prospectusID)
						 .setParameter(2, companyID)
						 .setParameter(3,addressType) 
						 .getSingleResult();
		
		if(address_id == null)
		{
			return null;
			
		} else {
			
			AddressPK address_PK =  new AddressPK();
			address_PK.setAddress_id(address_id);
			address_PK.setCompany_id(companyID);
			address_PK.setProspectus_id(prospectusID);
			
			try
			{
				Address address = em.find(Address.class, address_PK);
				
				return address;
				
			} catch (Exception e) {
				
				return null;
			}
		}
	}

	public Address getAdressByTypeByEmployment(int prospectusID, int companyID,int addressType, int employmentID) 
	{
		log.info("getAdressByTypeByEmployment.AddressDaoImp");
		
		String query = "select MAX(a.address_id) from gn_address a "
			  + "where prospectus_id = ? "
			  + "and company_id      = ? "
			  + "and address_type_id = ? "
			  + "and employment_id   = ?";
		
		Integer address_id = (Integer) em.createNativeQuery(query)
					     .setParameter(1, prospectusID)
					     .setParameter(2, companyID)
					     .setParameter(3,addressType)
					     .setParameter(4, employmentID)
					     .getSingleResult();
		
		if(address_id == null)
		{
			return null;
			
		} else {
			AddressPK address_PK = new AddressPK(address_id, prospectusID, companyID);
			
			Address address = em.find(Address.class, address_PK);
			
			return address; 			
		}
	}

	public Address getAdressByTypeByBusiness(int prospectusID, int companyID,int addressType, int businessID) 
	{
		log.info("getAdressByTypeBusiness.AddressDaoImp");
		
		String query = "select MAX(a.address_id) from gn_address a "
			  + "where prospectus_id = ? "
			  + "and company_id      = ? "
			  + "and address_type_id = ? "
			  + "and business_id     = ?";
		
		Integer address_id = (Integer) em.createNativeQuery(query)
					   .setParameter(1, prospectusID)
					   .setParameter(2, companyID)
					   .setParameter(3, addressType)
					   .setParameter(4, businessID)
					   .getSingleResult();
		
		if(address_id == null)
		{
			return null;
			
		} else  {
			
			AddressPK address_PK = new AddressPK(address_id, prospectusID, companyID);
			
			Address address = em.find(Address.class, address_PK);
			
			return address;
		}
	}
	
	public final Address getMaxAddressByBeneficiario(int prospectusID, int companyID, int beneficiarie_id) 
	{
		log.info("getAdressByTypeByEmployment.AddressDaoImp");
		
		String query = "select MAX(a.address_id) from gn_address a "
			  + "where prospectus_id = ? "
			  + "and company_id      = ? "
			  + "and address_type_id = ? "
			  + "and beneficiarie_id = ? ";
		
		Integer address_id = (Integer) em.createNativeQuery(query)
				         .setParameter(1, prospectusID)
				         .setParameter(2, companyID)
				         .setParameter(3, BENEFICIARIO)
				         .setParameter(4, beneficiarie_id)
				         .getSingleResult();
		
		if(address_id == null)
		{
			return null;
			
		} else {
			
			AddressPK address_PK = new AddressPK(address_id, prospectusID, companyID);
			
			return em.find(Address.class, address_PK);
		}
	}
	
	public final Long getCoincidencias_NUMBER(int nivel_filtro, Address address)
	{		
		TypedQuery<Long> typed_coincidencias_NUMBER = null;
		Long coincidencias_NUMBER = null;		
		
		if( address.getAddressPK() != null )
		{
			StringBuilder sb = new StringBuilder();
			sb.append("select count(*) from Address where neighborhood_id = ").append(address.getNeighborhood_id()).append(" ");
			sb.append("and street like '%").append(address.getStreet()).append("%' ");
			
			sb.append("and prospectus_id <> ").append(address.getAddressPK().getProspectus_id()).append(" ");
			
			init_filtro_NIVEL_2 (nivel_filtro, address, sb);
			init_address_type_id(nivel_filtro, address, sb);						
			
			typed_coincidencias_NUMBER = em.createQuery(sb.toString(), Long.class);		
								
			coincidencias_NUMBER = typed_coincidencias_NUMBER.getSingleResult();
			
		} else {
			
			coincidencias_NUMBER = 0L;
		}
		
		return coincidencias_NUMBER;
	}

	public final List<Address> getLista_coincidencias(int nivel_filtro, Address address)
	{		
		StringBuilder sb = new StringBuilder();
		sb.append("from Address where neighborhood_id = ").append(address.getNeighborhood_id()).append(" ");
		sb.append("and street like '%").append(address.getStreet()).append("%' ");
		sb.append("and prospectus_id <> ").append(address.getAddressPK().getProspectus_id()).append(" ");
		
		init_filtro_NIVEL_2 (nivel_filtro, address, sb);
		init_address_type_id(nivel_filtro, address, sb);	
		
		TypedQuery<Address> typed = em.createQuery(sb.toString(), Address.class);		
		
		List<Address> lista_address = typed.getResultList();
		
		return lista_address;
	}
}
