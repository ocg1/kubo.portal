package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import mx.com.kubo.bean.DetMovsInvestmentsBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.DetalleMovsInvestments;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;

@ManagedBean @ViewScoped
public class DetMovsInvestments extends DetMovsInvestmentsDMO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	
	@PostConstruct
	public void init()
	{
		Double	saldoInicialMes;
		int mes=0,anio=0;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		SimpleDateFormat dfD = new SimpleDateFormat("'01 de' MMMM 'de' yyyy", new Locale("ES"));
		
		try{
			String fechaActual = df.format(c.getTime());
			String primerDia   = dfD.format(c.getTime());
			
			mes =  c.get(Calendar.MONTH)+1;
			anio =  c.get(Calendar.YEAR);
			
			periodoAconsultar = "PERIODO DEL "+primerDia.toUpperCase()+" AL "+fechaActual.toUpperCase();
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		init_Cuentas();
		
		try{
			Integer.parseInt(selectedAccount);
		}catch(Exception e){
			System.out.println("Cuenta de Ahorro Invalida");
			selectedAccount = "0";
		}
		
		List<DetalleMovsInvestments> detMovsInvestmentsListTemp = detMovsInvestmentsServicio.getMovimientosInvestments(0, Integer.parseInt(selectedAccount), 'M', "1900-01-01", "1900-01-01", mes, anio);
		saldoInicialMes  =  (detMovsInvestmentsListTemp.get(0).getAbonosMov());
		
		detMovsInvestmentsList = initLista(detMovsInvestmentsListTemp,saldoInicialMes);
		
	}
	
	private List<DetMovsInvestmentsBean> initLista( List<DetalleMovsInvestments> detMovsInvestmentsList, Double saldoInicialMes){
		
		saldoInicialMes=0.00;
		List<DetMovsInvestmentsBean> detMovsInvestBean = new ArrayList<DetMovsInvestmentsBean>();		
		
		for( DetalleMovsInvestments det : detMovsInvestmentsList ){
			DetMovsInvestmentsBean tmp = new DetMovsInvestmentsBean();
			
			//System.out.print("-----SALDO ---->"+String.valueOf(dec.format(det.getAbonosMov())));

			tmp.setFecha(det.getFecha());
			tmp.setDescripcion1(det.getDescripcion1());
			tmp.setDescripcion2(det.getDescripcion2());
			tmp.setDescripcion3(det.getDescripcion3());
			tmp.setAbonosMov(String.valueOf(dec.format(det.getAbonosMov())));
			tmp.setCargosMov(String.valueOf(dec.format(det.getCargosMov())));
			saldoInicialMes = saldoInicialMes +  det.getAbonosMov() - det.getCargosMov();
				
			tmp.setSaldo(String.valueOf(dec.format(saldoInicialMes)));
			
			tmp.setBancoReceptor(det.getBancoReceptor());
			detMovsInvestBean.add(tmp);
		}
		return detMovsInvestBean;
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
