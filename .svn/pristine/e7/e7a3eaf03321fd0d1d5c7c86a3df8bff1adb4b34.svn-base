package mx.com.kubo.bean;

import java.util.Date;

public class MovsCuentaAhorro implements Comparable<MovsCuentaAhorro> {
	
	
	private Date fecha;
	private String descripcion;
	private String naturaleza;
	private String monto;
	private String moneda;
	private String institucion_receptora;
	private Integer order;
	private String creditoId;
	
	
	
	public boolean equals(Object o) {
        if (!(o instanceof MovsCuentaAhorro))
            return false;
        MovsCuentaAhorro n = (MovsCuentaAhorro) o;
        return 
        		n.getDescripcion().equals(descripcion) && 
        		n.getFecha().compareTo(fecha)==0 &&
        		n.getNaturaleza() == naturaleza &&
        		n.getMonto().equals(monto) &&
        		n.getMoneda().equals(moneda) &&
        		n.getInstitucion_receptora().equals(institucion_receptora);
    }

    public int hashCode() {
         Double d = 31*fecha.hashCode() + descripcion.hashCode() * (Double.parseDouble( monto )) ;
         return d.intValue();
    }

    public String toString() {
	return "";
    }

    public int compareTo(MovsCuentaAhorro n) {
        int res = 0;
         if(this.fecha.compareTo(n.getFecha()) == 0){
        	 if( this.naturaleza.compareTo(n.getNaturaleza()) == 0 )
        		 return this.order.compareTo(n.getOrder());
        	 else
        		 res = this.naturaleza.compareTo(n.getNaturaleza());
         }else
        	 res = this.fecha.compareTo(n.getFecha()) ;
         return res;
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getInstitucion_receptora() {
		return institucion_receptora;
	}

	public void setInstitucion_receptora(String institucion_receptora) {
		this.institucion_receptora = institucion_receptora;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getCreditoId() {
		return creditoId;
	}

	public void setCreditoId(String creditoId) {
		this.creditoId = creditoId;
	}

}
