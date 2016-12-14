package mx.com.kubo.bean;

import java.util.Date;

import mx.com.kubo.model.TSafiCuentasAhoMovDep;

public class CuentasAhoMovDep implements Comparable<CuentasAhoMovDep> {
    
	
	private String cantidadMov;

    private String concepto;

    private String cuentaAhoID;

    private String deposito;

    private String descripcionMov;

    private Date fecha;

    private Date fechaActual;

    private Date fechaAplica;

    private Date fechaCarga;

    private String moneda;

    private String natMovimiento;

    private String nombreCorto;

    private String numeroMov;

    private String referenciaMov;

    private String tipoDeposito;
	

    public CuentasAhoMovDep(TSafiCuentasAhoMovDep cuenta) {
        if (cuenta == null )
            throw new NullPointerException();
        
        this.cantidadMov = cuenta.getCantidadMov() +"" ;

        this.concepto = cuenta.getConcepto();

        this.cuentaAhoID  = cuenta.getCuentaAhoId()+"";

        this.deposito  = cuenta.getDeposito()+"";

        this.descripcionMov  = cuenta.getDescripcionMov();
        
        if(cuenta.getFecha()!= null)
        	this.fecha = cuenta.getFecha();
        else
        	this.fecha = null;
        
        if(cuenta.getFechaActual()!= null)
        	this.fechaActual = cuenta.getFechaActual();
        else
        	this.fechaActual = null;
        
        if(cuenta.getFechaAplica()!= null)
        	this.fechaAplica = cuenta.getFechaAplica();
        else
        	this.fechaAplica = null;

        if(cuenta.getFechaCarga()!= null)
        	this.fechaCarga = cuenta.getFechaCarga();
        else
        	this.fechaCarga = null;

        this.moneda = cuenta.getMoneda();

        this.natMovimiento  = cuenta.getNatMovimiento();

        this.nombreCorto = cuenta.getNombreCorto();

        this.numeroMov  = cuenta.getNumeroMov()+"";

        this.referenciaMov = cuenta.getReferenciaMov();

        this.tipoDeposito = cuenta.getTipoDeposito();
    }

    public boolean equals(Object o) {
        if (!(o instanceof CuentasAhoMovDep))
            return false;
        CuentasAhoMovDep n = (CuentasAhoMovDep) o;
        return 
        		n.getNumeroMov().equals(numeroMov) && 
        		n.getFecha().compareTo(fecha)==0 &&
        		n.getCantidadMov() == cantidadMov &&
        		n.getCuentaAhoID().equals(cuentaAhoID);
    }

    public int hashCode() {
         Double d = 31*numeroMov.hashCode() + cuentaAhoID.hashCode() * (Double.parseDouble( cantidadMov )) ;
         return d.intValue();
    }

    public String toString() {
	return "";
    }

    public int compareTo(CuentasAhoMovDep n) {
        
        return this.fecha.compareTo(n.getFecha());
        
    }

	public String getCantidadMov() {
		return cantidadMov;
	}

	public void setCantidadMov(String cantidadMov) {
		this.cantidadMov = cantidadMov;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCuentaAhoID() {
		return cuentaAhoID;
	}

	public void setCuentaAhoID(String cuentaAhoID) {
		this.cuentaAhoID = cuentaAhoID;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getDescripcionMov() {
		return descripcionMov;
	}

	public void setDescripcionMov(String descripcionMov) {
		this.descripcionMov = descripcionMov;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Date getFechaAplica() {
		return fechaAplica;
	}

	public void setFechaAplica(Date fechaAplica) {
		this.fechaAplica = fechaAplica;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNatMovimiento() {
		return natMovimiento;
	}

	public void setNatMovimiento(String natMovimiento) {
		this.natMovimiento = natMovimiento;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNumeroMov() {
		return numeroMov;
	}

	public void setNumeroMov(String numeroMov) {
		this.numeroMov = numeroMov;
	}

	public String getReferenciaMov() {
		return referenciaMov;
	}

	public void setReferenciaMov(String referenciaMov) {
		this.referenciaMov = referenciaMov;
	}

	public String getTipoDeposito() {
		return tipoDeposito;
	}

	public void setTipoDeposito(String tipoDeposito) {
		this.tipoDeposito = tipoDeposito;
	}
}