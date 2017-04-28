package mx.com.kubo.investor.settings;

import java.util.Date;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.InvestmentFilter;
import mx.com.kubo.model.InvestmentFilterPK;
import mx.com.kubo.model.Purpose;

public abstract class AutomaticInvestmentAMO extends AutomaticInvestmentDMO 
{
	protected void init_access(int screen_id) 
	{
		access = new Access();
		access.setPercentage(0);
		access.setCompany_id(company_id);
		access.setProspectus_id(prospectus_id);
		access.setScreen_id(screen_id);		
		access.setUser_agent(user_agent);
		access.setDevice_info(device_info);
		access.setUrl_access( sesion.getUrl_access() );
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		access.setAccess_datetime(new Date());
		access.setIpaddress(IP_address_client);
		
		service_access.add(access, false);
	}
	
	public void inicializaSaldos()
	{				
		flagInversionFG = false;
		
		if( listInvAccounts!=null && listInvAccounts.size()==1 )
		{
			InvestorsAccounts account = listInvAccounts.get(0);
			
			flagInversionFG =   inversion.puedeInvertirEn_F_G(prospectus_id, company_id, account.getAccount());
		}						
		
		if(listInvAccounts != null)
		{				
			if(listInvAccounts.size() > 0)
			{					
				if(listInvAccounts.size() == 1) 
				{						 
					InvestorsAccounts account = listInvAccounts.get(0);
					
					 tagAccount   = account.getAccount();						 
					 cuentaActual = account.getAccount();
					  saldoActual = account.getSaldo();						 
					 ammoutToInv  = account.getSaldo();
					 
				} else {
					
					saldoActual = getTotalCreditOnAccounts();
					
					tagAccount = "Ninguna cuenta";
				}					
			}
		
		} else {
		
			tagAccount = "No hay cuenta";			
		}			
	}
	
	private Double getTotalCreditOnAccounts()
	{
		Double suma = 0.00;
		
		try 
		{			
			for (InvestorsAccounts account : listInvAccounts) 
			{
				suma = suma + account.getSaldo();
			}
			
		} catch (Exception e) {
			
			System.out.println("Se genero un error al hacer la suma del saldo de cuentas");
		}
		
		return suma;
	}
		
	protected void init_filter_default() 
	{
		typeSearch = 2; 
		
		int i = 0;
		
		String destiny_values = "";
		
		for( Purpose p : purposelst)
		{			
			if(i != 0)
			{
				destiny_values += ",";
			}
			
			destiny_values += p.getPurposePK().getPurpose_id();
			
			i++;
		}
		
		destiny_str = destiny_values;
		
		String strRisk = "risk:'A','B','C','D','E'";
		
		if( flagInversionFG )
		{
			strRisk+=",'F','G'";
		}
		
		sb = new StringBuilder();
		sb.append("term:25T36_6||").append(strRisk);
		sb.append("||gender:MH||typeSearch:").append(typeSearch);
		sb.append("||between:||previousType:").append(previousType);
		sb.append("||destinyValues:").append(destiny_values);
		
    	ultimoFiltro = sb.toString();
    	
    	System.out.println("ultimoFiltro = " + ultimoFiltro);
	}
	
	protected void init_filter() 
	{
    	String riskCad 			= map.get("cadena1");
    	String termCadIni 		= map.get("cadena2");
    	//String flagRisk 		= map.get("flagRisk");
    	String destiny_values 	= map.get("destiny_str");
    	String genderCad 		= map.get("cadenaGender");
    	String ammountCadFrom 	= map.get("inputFromBtn");
    	String ammountCadTo 	= map.get("inputToBtn");    	    	
    	
    	destiny_str = destiny_values;    	
 	       risk_str = riskCad;
    	  genderStr = new String("");
    	
    	if(genderCad != null && genderCad.trim().length() > 0)
    	{    		
    		if(genderCad.indexOf("2") != (-1))
    		{	    			
    			genderStr += "M";
    		}
    		
    		if(genderCad.indexOf("1") != (-1))
    		{
    			genderStr += "H";
    		}
		}    	
    	
    	sb = new StringBuilder();
    	sb.append("term:").append(termCadIni);
    	sb.append("||risk:").append(riskCad);
    	sb.append("||gender:").append(genderStr);
    	sb.append("||typeSearch:").append(typeSearch);
    	sb.append("||between:").append(ammountCadFrom).append("_").append(ammountCadTo);
    	sb.append("||previousType:").append(previousType);
    	sb.append("||destinyValues:").append(destiny_values);    	    	    	
    	
    	lastFilter = strQuery = sb.toString();
	}
	
	protected void init_automatic_investment() 
	{
    	automatic_investment = new AutomaticInvestment();
    	automatic_investment.setCompany_id(company_id);
    	automatic_investment.setProspectus_id(prospectus_id);
    	automatic_investment.setSafi_account_id(account);
    	automatic_investment.setSafi_client_id(safi_client_id);
    	automatic_investment.setFilter_label(label);
    	automatic_investment.setFilter(strQuery);
    	automatic_investment.setIs_active(is_active);
    	automatic_investment.setFrequency(frequency);
    	automatic_investment.setInscription_date(frm.format(new Date()));       	    	
    	automatic_investment.setFinished_date(finisehd_date);
    	automatic_investment.setNext_investment(new Date());
    	automatic_investment.setNext_investment_apply(new Date()); 
    	
    	save_OK = service_automatic_investment.saveAutomaticInvestment(automatic_investment);
	}
	
	protected void init_user_filter() 
	{
    	filterInvestment = new InvestmentFilter();
    	
    	fpk = new InvestmentFilterPK();
    	
    	fpk.setCompany_id(company_id);
    	fpk.setProspectus_id(prospectus_id);
    	
    	filterInvestment.setFilter(strQuery);
    	filterInvestment.setFilter_date_used(new Date());
    	filterInvestment.setPk(fpk);
    	
    	save_OK = investmentFilterServiceImp.addFilterUsed(filterInvestment); 
	}
}
