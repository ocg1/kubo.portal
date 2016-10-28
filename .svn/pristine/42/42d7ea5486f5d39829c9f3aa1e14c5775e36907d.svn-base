package mx.com.kubo.portal.simulador;

public class CATCalculatorIMP extends CATCalculatorAMO
implements CATCalculatorIMO
{
	public CATCalculatorIMP(double ammount, 
							int term_id, 
							int diasFreq, 
							Double montoCuota,
							Double tasaTotal,
							Double comisionApertura,
							String frequencyStr, 
							String totalRecibir)
	{
		
		super();
		
		this.ammount    = ammount;
		this.term_id    = term_id;
		this.diasFreq   = diasFreq;
		this.montoCuota = montoCuota;
		this.tasaTotal  = tasaTotal;
		this.comisionApertura = comisionApertura;
		this.frequencyStr = frequencyStr;
		this.totalRecibir = totalRecibir;
	}
	
	public void init()
	{				
		init_tabla_amortizacion();		
		init_valor_cuotas();
				
		monto = Double.parseDouble( getTotalRecibir().replace("$", "").replace(",", ""));
		
		cat = simulatorService.getCatBySafi(monto, valorCuotas, diasFreq);
		
		cat = (double) Math.round((cat) * 10) / 10;		
	}
}
