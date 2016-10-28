package mx.com.kubo.referencia_pago_panel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.com.kubo.referencia_pago.Nota;
import mx.com.kubo.referencia_pago.Paso;
import mx.com.kubo.referencia_pago.ReferenciaPagoEMO;
import mx.com.kubo.referencia_pago.ReferenciaPagoEntity;

public abstract class PanelDMO 
{		
	protected Nota nota;
		
	protected Paso paso_1; 	
	protected Paso paso_2; 
	protected Paso paso_3; 
	protected Paso paso_4;
	
	protected ReferenciaPagoEntity entity;
	
	protected ReferenciaPagoEMO banorte; 
	protected ReferenciaPagoEMO banorte_SPEI;
	protected ReferenciaPagoEMO banamex; 
	protected ReferenciaPagoEMO seven_eleven;
	protected ReferenciaPagoEMO telecomm;
	
	protected List<ReferenciaPagoEMO>     lista_referencias;
	protected List<ReferenciaPagoEMO>    lista_referencias_SPEI;		
	protected List<ReferenciaPagoEntity> lista_referencias_tmp;
	
	protected NumberFormat currency;
	
	protected String acreditado;
	protected String cuota;
	
	protected Double pago;
	
	protected Integer safi_credit_id;	
	
	protected PanelDMO()
	{
		banorte      = new ReferenciaPagoEMO();
		banorte_SPEI = new ReferenciaPagoEMO();
		banamex		 = new ReferenciaPagoEMO();
		seven_eleven = new ReferenciaPagoEMO();	
		telecomm     = new ReferenciaPagoEMO();

		lista_referencias      = new ArrayList<ReferenciaPagoEMO>();
		lista_referencias_SPEI = new ArrayList<ReferenciaPagoEMO>();
		
		currency = NumberFormat.getCurrencyInstance(new Locale("es","mx"));
	}
	
	public final void setLista_referencias_tmp(List<ReferenciaPagoEntity> lista) 
	{
		lista_referencias_tmp = lista;
	}

	public final Integer getSafi_credit_id() 
	{
		return safi_credit_id;
	}

	public final void setSafi_credit_id(String id) 
	{
		safi_credit_id = Integer.valueOf(id);
	}
	
	public final void setAcreditado(String acreditado)
	{
		this.acreditado = acreditado;
	}

	public final String getAcreditado() 
	{
		return acreditado;
	}
	
	public final Double getPago() 
	{
		return pago;
	}

	public final void setCuota(String cuota) 
	{
		this.cuota = cuota;
	}

	public final List<ReferenciaPagoEMO> getLista_referencias() 
	{
		return lista_referencias;
	}

	public final List<ReferenciaPagoEMO> getLista_referencias_SPEI() 
	{
		return lista_referencias_SPEI;
	}
}
