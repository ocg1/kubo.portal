package mx.com.kubo.managedbeans.buro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.soa.model.businessobject.Vtbur_spca;

public class Bur_spca implements Serializable{

	private static final long serialVersionUID = 1L;

	public Bur_spca()
	{
		super();
		spcaToList(null);
	}
	
	public Bur_spca(Vtbur_spca[] scpa)
	{
		super();
		spcaToList(scpa);
	}
	
	private void spcaToList(Vtbur_spca[] scpa)
	{
		 List<Vtbur_spca> res = new ArrayList<Vtbur_spca>();
		 double saldoagregado = 0;
		 double montoagregado = 0;
		 double limiteagregado = 0;
		 String strCreditos = "", strAuxCreditos="";
		 int countP =0;
		 
		if(scpa!=null && scpa.length>0)
		{
			for(int i =0 ; i<scpa.length;i++)
			{
				Vtbur_spca aux = scpa[i];
				if(aux.getOtorgante()!=null && aux.getOtorgante().contains("BANCO"))
				{
					res.add(aux);

					countP+= new Integer(aux.getCreditos()).intValue();
					
					if(aux.getSaldo_actual()!=null && !aux.getSaldo_actual().isEmpty())
						saldoagregado += new Double(aux.getSaldo_actual()).doubleValue();
					if(aux.getMonto_pagar()!=null && !aux.getMonto_pagar().isEmpty())
						montoagregado += new Double(aux.getMonto_pagar()).doubleValue();
					if(aux.getLimite_credito()!=null && !aux.getLimite_credito().isEmpty())
						limiteagregado += new Double(aux.getLimite_credito()).doubleValue();
			
					if(strCreditos==null || strCreditos.isEmpty())
					{
						strAuxCreditos = aux.getCreditos() + " " + aux.getTipo_contrato() + " " + aux.getTipo_cuenta();
						strAuxCreditos = formatSP(strAuxCreditos, new Integer(aux.getCreditos()));
						strCreditos = strAuxCreditos;	
					}
					else
					{
						strAuxCreditos = aux.getCreditos() + " " + aux.getTipo_contrato() + " " + aux.getTipo_cuenta();
						strAuxCreditos = formatSP(strAuxCreditos, new Integer(aux.getCreditos()));
						strCreditos += ", " +  strAuxCreditos;
					}
				}
			}
		}
		this.saldoagregado = saldoagregado;
		this.montoagregado = montoagregado;
		this.limiteagregado = limiteagregado;
		this.lstSpca = res;
		if(strCreditos!=null && !strCreditos.isEmpty())
			this.strCreditos = formatSP( strCreditos + " activa(s) y figurando como titular" , countP);

	}

	private String formatSP(String input, int quantity)
	{   // Input example:   tarjeta(s) empresarial(es)  
		// Output if quantity =1 :  tarjeta empresarial. else if quantity>1 : tarjetas empresariales
		String output="";
		
		if(quantity>1)
			output = input.replaceAll("\\(", "").replaceAll("\\)", "");
		else
		{
			
			output = input.replaceAll("\\(s\\)", "").replaceAll("\\(es\\)", "");
			
		}
		
		return output;
	}
	
	private List<Vtbur_spca> lstSpca;
	private double saldoagregado;
	private double montoagregado;
	private double limiteagregado;
	private String strCreditos;
	
	public List<Vtbur_spca> getLstSpca() {
		return lstSpca;
	}
	
	public String getStrCreditos() {
		return strCreditos;
	}

	public void setStrCreditos(String strCreditos) {
		this.strCreditos = strCreditos;
	}

	public double getLimiteagregado() {
		return limiteagregado;
	}

	public void setLimiteagregado(double limiteagregado) {
		this.limiteagregado = limiteagregado;
	}

	public void setLstSpca(List<Vtbur_spca> lstSpca) {
		this.lstSpca = lstSpca;
	}

	public double getSaldoagregado() {
		return saldoagregado;
	}

	public void setSaldoagregado(double saldoagregado) {
		this.saldoagregado = saldoagregado;
	}

	public double getMontoagregado() {
		return montoagregado;
	}

	public void setMontoagregado(double montoagregado) {
		this.montoagregado = montoagregado;
	}
	
}
