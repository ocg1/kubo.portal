package mx.com.kubo.bean;


import java.text.DecimalFormat;

public class Rate {

	/**
	* @param args
	*/
	
	private static final int FINANCIAL_MAX_ITERATIONS = 256;
	private static final double FINANCIAL_PRECISION = 1.0e-08;
	
	
	public Double getRate(int totalPagos, double cuota, double montoTotalSolicitado){
		Double d;
		//if(totalPagos > 0 && cuota >0 && montoTotalSolicitado>0 )
			d = calcRATE(totalPagos, cuota, montoTotalSolicitado, 0, 0, 0.05);
		//else
			//d = 0D;
		return d;
				
	}
	
	public static double calcRATE(int nper, double pmt, double pv, double fv, int type, double guess){
		double y, y0, y1, x0, x1 = 0, f = 0, i = 0;
	
		double rate = guess;
	
		if (Math.abs(rate) < FINANCIAL_PRECISION){
			
			y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
			
		}else
			{
				f = Math.exp(nper * Math.log(1 + rate));
				y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
			}
	
		y0 = pv + pmt * nper + fv;
		y1 = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
		
		i = x0 = 0.0;
		x1 = rate;
	
		while ((Math.abs(y0 - y1) > FINANCIAL_PRECISION) && (i < FINANCIAL_MAX_ITERATIONS))
		{
			rate = (y1 * x0 - y0 * x1) / (y1 - y0);
			x0 = x1;
			x1 = rate;
			
			if (Math.abs(rate) < FINANCIAL_PRECISION){
				
				y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
				
			}else{
				
				f = Math.exp(nper * Math.log(1 + rate));
				y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
				
			}
			//System.out.println("dentro del while: "+i);
			y0 = y1;
			y1 = y;
			++i;
		}
	
		return rate;
	} //  function RATE()
	
	
	public double calcFV(double rate, int nPer, double pps){
		double sum = pps;
		for(int i = 0; i < nPer; i++){
			sum += sum * rate;
		}
		return sum;
	}
	
	public double calcPV(double rate, int nPer, double pmt, double fv, boolean Type){
		double ann = Math.pow(1 + rate, nPer);
		return ((fv + pmt * (1 + (Type ? rate : 0)) * ((ann - 1) / rate)) / ann);
	}
	
	public String customFormat(String pattern, double value ){
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}

}