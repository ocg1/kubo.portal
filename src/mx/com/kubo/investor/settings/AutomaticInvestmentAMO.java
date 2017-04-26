package mx.com.kubo.investor.settings;

import java.util.ArrayList;
import java.util.Date;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.Purpose;

public abstract class AutomaticInvestmentAMO extends AutomaticInvestmentDMO 
{
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
	
	protected void cargaListaTienda()
	{																	    	
    	init_filter_default();
    	
    	inversion.cargaListaTienda(ultimoFiltro, prospectus_id, company_id, flagRisk+"", safi_client_id , cuentaActual);
    	
    	scriptStatus = inversion.getScriptStatus();    	
		proyectList  = inversion.getProyectList();
    	
		filter = inversion.getFilter();
		
		asignaListForInvest();					
	}
	
	private void init_filter_default() 
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

	private void asignaListForInvest()
	{		
		if( !hold_selected )
		{			
			proyectListForInvesInd = new ArrayList<ItemLoanList>(); 
			ammountFoundedInv = 0D;
			
			for( ItemLoanList item : proyectList )
			{				
				if( item.getInvestment_bite() > 0 )
				{
					proyectListForInvesInd.add(item);
				}
				
				ammountFoundedInv += item.getInvestment_bite();				
			}			
		}		
	}
	
	protected void init_filter() 
	{
    	String riskCad 			= map.get("cadena1");
    	String termCadIni 		= map.get("cadena2");
    	String flagRisk 		= map.get("flagRisk");
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
	}
}
