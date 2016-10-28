package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.services.ProyectLoanService;

import org.apache.log4j.Logger;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import safisrv.ws.CuentasServicios.SAFIServicios;
import safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator;

@ManagedBean
@SessionScoped
public class SAFIAccounts implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService servicioProyecto;
	
	private SessionBean sesion;
	private ProyectLoanService SAFIAccount;
	
	
	ArrayList<String> numCuentas = new ArrayList<String>();
	
	public void init() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        sesion = (SessionBean) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "sessionBean");
        }
	
	public void consultaCte(){
        @SuppressWarnings("unused")
		int valid=0;
        numCuentas.clear();
        numCuentas.add("Todas las cuentas");
        try {
            SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
            SAFIServicios service =  locator.getSAFIServiciosSoap11();
            ConsultaCuentasPorClienteResponse res = service.consultaCuentasPorCliente(new ConsultaCuentasPorClienteRequest("0000000002"));
//            ConsultaCuentasPorClienteResponse res = service.consultaCuentasPorCliente(new ConsultaCuentasPorClienteRequest(persona.getSafi_client_id()));
            
            if(res.getInfocuenta()!=null && res.getCodigoRespuesta()!=null && res.getCodigoRespuesta()[0].equals("0"))
            {
                String respuestas = res.getInfocuenta()[0];
                if(respuestas ==null || respuestas.isEmpty())
                    valid=-1;
                   
                String[] cuentas = respuestas.split("\\&\\|\\&");
                for(int i =0; i<cuentas.length;i++)
                {
                    String[] vars = cuentas[i].split("\\&\\;\\&");
                    if(vars.length==3)
                    		numCuentas.add(vars[0]);
                    else
                    {
                        System.out.println("Cuenta inválida:" + cuentas[i]);
                        valid=1;
                    }
                }
            }
            else
            {
                valid=-1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public ArrayList<String> getNumCuentas() {
		return numCuentas;
	}
	public void setNumCuentas(ArrayList<String> numCuentas) {
		this.numCuentas = numCuentas;
	}
	public SessionBean getSesion() {
		return sesion;
	}
	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public ProyectLoanService getSAFIAccount() {
		return SAFIAccount;
	}

	public void setSAFIAccount(ProyectLoanService sAFIAccount) {
		SAFIAccount = sAFIAccount;
	}

	public ProyectLoanService getServicioProyecto() {
		return servicioProyecto;
	}

	public void setServicioProyecto(ProyectLoanService servicioProyecto) {
		this.servicioProyecto = servicioProyecto;
	}
}