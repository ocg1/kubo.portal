package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;
 
@ManagedBean @ViewScoped
public class DetSaldosKuboGlobalBean extends DetSaldosKuboGlobalDMO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init(){
		try{
			
				init_Cuentas();
				
				try{
				Integer.parseInt(selectedAccount);
			}catch(Exception e){
				System.out.println("Cuenta de Ahorro Invalida");
				selectedAccount = "0";
			}
			
			detSaldosKubo = detSaldosKuboGlobalService.consultaSaldoKuboGlobal(
							0, Integer.parseInt(selectedAccount), 'M', "1900-01-01", "1900-01-01", 02, 1992);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void init_Cuentas(){
		try{
			numCuentas = new ArrayList<String>();
			FacesContext faces      = FacesContext.getCurrentInstance();
			ELContext elContext = faces.getELContext();
			
			SessionBean sesion = (SessionBean) faces
					.getApplication().getELResolver()
					.getValue(elContext, null, "sessionBean");

			if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
						
				gnNaturalPersonPK key = new gnNaturalPersonPK(sesion.getProspectus_id(), sesion.getCompany_id());
			
				NaturalPerson persona = personaNaturalService.getNaturalPersonById(key);
			        
			    safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorA = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
				safisrv.ws.CuentasServicios.SAFIServicios serviceA =  locatorA.getSAFIServiciosSoap11();
			
				ConsultaCuentasPorClienteResponse replyA = serviceA.consultaCuentasPorCliente(new ConsultaCuentasPorClienteRequest(persona.getSafi_client_id()));
				
				numCuentas.add("TODAS LAS CUENTAS");
				
				if(replyA.getInfocuenta()!=null && replyA.getCodigoRespuesta()!=null && replyA.getCodigoRespuesta()[0].equals("0")){
		                String respuestas = replyA.getInfocuenta()[0];
		                //if(respuestas ==null || respuestas.isEmpty())
		                //valid=-1;
		                String[] cuentas = respuestas.split("\\&\\|\\&");
		                
		                for(int i =0; i<cuentas.length;i++)
		                {
		                    String[] vars = cuentas[i].split("\\&\\;\\&");
		                    
		                    if(vars.length==3){
		                    	numCuentas.add(vars[0]);
		                    }else{
		                        System.out.println("Cuenta inválida:" + cuentas[i]);
		                        //valid=1;
		                    }
		                }
		                if(numCuentas.size()==2){
		                	selectedAccount = numCuentas.get(1);
		                }		
			     }
			}
		}catch(Exception e){
			e.printStackTrace();		
		}
		System.out.println("-->Cuenta "+selectedAccount);
	}
}
