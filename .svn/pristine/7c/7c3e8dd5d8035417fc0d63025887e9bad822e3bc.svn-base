package mx.com.kubo.registro.consulta.historial;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.model.Bank;

public final class SearchRequestIMP extends SearchRequestDMO
implements SearchRequestIMO
{
	public List<String> autocomplete(String query) 
	{
		lista_banks = new ArrayList<Bank>();
		lista_creditcard_company = new ArrayList<String>();
		
		//lista.removeAll(results);
		
		lista_banks = service_bank.searchBankList(query, true);
		
		for (Bank bank : lista_banks) 
		{
			lista_creditcard_company.add(bank.getShort_name());
		}

		return lista_creditcard_company;
	}
}
