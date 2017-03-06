package mx.com.kubo.rest.tienda;

import mx.com.kubo.bean.FilterStore;

public class FilterStoreIMP extends FilterStoreAMO
implements FilterStoreIMO 
{
	@Override
	public void init() 
	{		
		init_filters();
		init_plazo();
		init_tipo_fondeo();
		init_gender();
    	init_ammount();
    	init_loan_type();
    	
		init_filter_store();	
	}
	
	private void init_filter_store() 
	{						
		filter = new FilterStore();		
		filter.setClienteID(safi_client_id);
		filter.setCuentaAhoID(safi_account_id);				    	    	    	    	        	
    	filter.setDiasPorTrans(diasPorTrans);    	    	    	
    	filter.setKuboScore(riskCad);    	            	
    	filter.setGenderID(genderCad);    	
		filter.setTipoFondeo(tipo_fondeo);    	
    	filter.setAmmount( concatAmmountCad );    	    			    	
    	filter.setLoanType(concatPrevLoanCad);    	    	
    	filter.setPurpose_id( destiny_values );
	}
}
