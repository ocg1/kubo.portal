package mx.com.kubo.datamodels;

import java.util.List;

import javax.faces.model.ListDataModel;

import mx.com.kubo.model.ClientCarteraKiva;

import org.primefaces.model.SelectableDataModel;

public class KivaClientDataModel extends ListDataModel<ClientCarteraKiva> implements SelectableDataModel<ClientCarteraKiva> {    
	  
    public KivaClientDataModel() {  
    }  
  
    public KivaClientDataModel(List<ClientCarteraKiva> data) {  
    	super(data);
    }  

    @SuppressWarnings("unchecked")
	@Override  
    public ClientCarteraKiva getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
        try {
        	List<ClientCarteraKiva> clientesKiva = (List<ClientCarteraKiva>) getWrappedData();
        	for(ClientCarteraKiva ck : clientesKiva) {  
                if(ck.getSafi_credit_id().equals(rowKey))  
                    return ck;  
            }  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}  

        return null;  
    }  
    
	@Override
	public Object getRowKey(ClientCarteraKiva clienteKiva) {
		// TODO Auto-generated method stub
		return clienteKiva.getSafi_credit_id();
	}  
}  
