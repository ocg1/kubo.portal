package mx.com.kubo.services.cliente.cuenta;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.soa.model.businessobject.TSafiPagosCuota;
import com.soa.model.businessobject.TSafiPosicionInt;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.cliente.CreditoEMO;

public interface ServiceEstadoCuentaIMO 
{
	List<CreditoEMO> getLista_creditos();
	
	Integer getContador_creditos_vigentes();
	Integer getContador_creditos_liquidados();
	Integer getContador_creditos_vencidos();
	Integer getContador_creditos_mora();
	Integer getCreditos_totales();
	
	void setVer_creditos_pagados_ENABLED(boolean bandera);
	
	void setSesion(SessionBean sesion);
	void setProspectus_id(Integer prospectus_id);
	
	void setFecha_de_corte(Date fecha);
	void setFecha_inicio  (Date fecha);
	void setFecha_final   (Date fecha);

	void setPosicion_SAFI(TSafiPosicionInt [] posicionInt);
	void setPagos_SAFI   (TSafiPagosCuota  []  pagos);
	
	void setComisiones(Hashtable<String, Double[]> comisiones);
}
