package mx.com.kubo.managedbeans.buro;

import java.io.Serializable;
import java.util.Date;

import com.soa.model.businessobject.Vbur_maxoto;

public class Bur_maxoto implements Serializable{

	private static final long serialVersionUID = 1L;

	public Bur_maxoto()
	{
		super();
		init(null);
	}
	
	public Bur_maxoto(Vbur_maxoto vbur_maxoto)
	{
		super();
		init(vbur_maxoto);
	}
	
	public void init(Vbur_maxoto vbur_maxoto)
	{
		if(vbur_maxoto!=null)
		{
			if(vbur_maxoto.getMax_liquidado()!=null && !vbur_maxoto.getMax_liquidado().isEmpty())
				this.max_liquidado = new Double(vbur_maxoto.getMax_liquidado());
			if(vbur_maxoto.getMax_noliquidado()!=null && !vbur_maxoto.getMax_noliquidado().isEmpty())
				this.max_noliquidado = new Double(vbur_maxoto.getMax_noliquidado());
			if(vbur_maxoto.getPrim_credito()!=null)
				this.prim_credito = vbur_maxoto.getPrim_credito().getTime();
			else
				this.prim_credito = null;
			if(vbur_maxoto.getUlt_credito()!=null)
				this.ult_credito=vbur_maxoto.getUlt_credito().getTime();
			else
				this.ult_credito = null;
		}
		else
		{
			prim_credito = null;
			ult_credito  = null;
			otorgante 	 = null;
		}
	}
	private double max_liquidado;
	private double max_noliquidado;
	private String otorgante;
	private Date prim_credito;
	private Date ult_credito;

	public double getMax_liquidado() {
		return max_liquidado;
	}
	public void setMax_liquidado(double max_liquidado) {
		this.max_liquidado = max_liquidado;
	}
	public double getMax_noliquidado() {
		return max_noliquidado;
	}
	public void setMax_noliquidado(double max_noliquidado) {
		this.max_noliquidado = max_noliquidado;
	}
	public String getOtorgante() {
		return otorgante;
	}
	public void setOtorgante(String otorgante) {
		this.otorgante = otorgante;
	}
	public Date getPrim_credito() {
		return prim_credito;
	}
	public void setPrim_credito(Date prim_credito) {
		this.prim_credito = prim_credito;
	}
	public Date getUlt_credito() {
		return ult_credito;
	}
	public void setUlt_credito(Date ult_credito) {
		this.ult_credito = ult_credito;
	}
	
	
}
