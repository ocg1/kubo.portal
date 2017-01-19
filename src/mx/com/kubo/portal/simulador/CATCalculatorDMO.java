package mx.com.kubo.portal.simulador;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import mx.com.kubo.bean.Amortization;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.TablaAmortizacion;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.tools.Utilities;

public abstract class CATCalculatorDMO 
implements CATCalculatorIMO
{
	protected SimulatorService simulatorService;
//	
//	protected SessionBean sesion;
//	
	protected TablaAmortizacion amortization;
	
	protected List<Amortization> lista_amortizacion;
	
	protected Locale locale;
	protected NumberFormat dec;
	
	protected String totalPagarTOKEN;
	protected String frequencyStr;
	protected String totalRecibir;
	protected String valorCuotas = "";
	
	protected Double montoCuota;	
	protected Double tasaTotal=52.6d;
	protected Double comisionApertura = 5D;
	
	protected Double monto;
	protected Double cat;
	protected Double interesSinIva;
	protected Double interes;
	protected Double capital;
	protected Double payment;
	protected Double total_payment;
	
	protected double ammount;
	
	protected int term_id;	
	protected int diasFreq;
	
	protected boolean isSafiSimulation;		
	
	protected CATCalculatorDMO()
	{
		simulatorService = Utilities.findBean("simulatorServiceImp");
		
		locale = new Locale("es","mx");
		
		dec = NumberFormat.getCurrencyInstance(locale);
	}
	
	public void setSafiSimulation(boolean isSafiSimulation) 
	{
		this.isSafiSimulation = isSafiSimulation;
	}	

	public void setAmortization(TablaAmortizacion amortization) 
	{
		this.amortization = amortization;
	}

	/*public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		if(sesion.getRate() != null && !sesion.getArea().toString().equals("M"))
		{
			tasaTotal = sesion.getRate();
		}
		
		if(sesion.getOpeningCommission() != null)
		{
			comisionApertura = sesion.getOpeningCommission();
		}
	} */
	
	public void setTotalPagar(Double totalPagar)
	{
		if(totalPagar != null && totalPagar > 0)
		{
			totalPagarTOKEN = formatDec(dec.format(totalPagar));
			
		} else {
			
			totalPagarTOKEN = "No disponible";
		}
	}
	
	public Double getCat() 
	{
		return cat;
	}

	protected String getMontoCuotaStr() 
	{
		if(montoCuota != null && montoCuota > 0)
		{
			return formatDec(dec.format(montoCuota));
			
		} else {
			
			return "No disponible";
		}
	}
	
	public String getTotalRecibir() 
	{		
		Double d =  ammount - (ammount * (comisionApertura / 100));
		
		d = (double) Math.round(d*100)/100;
		
		totalRecibir = dec.format(d);
		
		return totalRecibir;
	}
	
	private String formatDec(String valor)
	{
		String res;
		valor = valor.replace("$", "");
		
		String[] arrayValor = valor.split("\\.");
		
		if(arrayValor.length < 2)
		{
			//res=valor+".00";
			res = valor;
			
		} else {
			
			if(arrayValor[1].length() < 2)
			{
				arrayValor[1]= arrayValor[1] + "0";
			}
			
			res = arrayValor[0]+"."+arrayValor[1];
		}
		
		return res;
	}

	public void setTasaTotal(Double tasaTotal) {
		this.tasaTotal = tasaTotal;
	}

	public void setComisionApertura(Double comisionApertura) {
		this.comisionApertura = comisionApertura;
	}
	
}
