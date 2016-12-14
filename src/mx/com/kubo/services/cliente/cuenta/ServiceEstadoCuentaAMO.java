package mx.com.kubo.services.cliente.cuenta;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mx.com.kubo.model.TSafiPagosCuota;

import mx.com.kubo.bean.TAmortizacionBean;
import mx.com.kubo.managedbeans.cliente.Credito;
import mx.com.kubo.managedbeans.cliente.CreditoEMO;
import mx.com.kubo.managedbeans.cliente.CreditoSAFI_EMO;
import mx.com.kubo.model.RestructureBean;

public abstract class ServiceEstadoCuentaAMO extends ServiceEstadoCuentaDMO
{	
	protected void init_credito_SAFI(int indice_posicion_SAFI) throws ParseException 
	{
		credito_safi = new CreditoSAFI_EMO(posicion_SAFI.get(indice_posicion_SAFI));
		
		credito_LIQUIDADO_EN_FECHA = false;
		
		estatus                         =  credito_safi.getEstatus();
		credito_id                      =  credito_safi.getCredito_id();	
		amortizacion_id                 =  credito_safi.getCredito_amortizacion_id();
		saldo_capital_vencido           =  credito_safi.getSaldo_capital_vencido();
		saldo_capital_vencido_no_exigle =  credito_safi.getCredito_saldo_capital_vencido_no_exigle();
		
		saldo_insoluto                  =  credito_safi.getSaldo_capital_vigent() 
										+  credito_safi.getSaldo_capital_atrasado();
		
		intereses_generados             =  credito_safi.getSaldo_moratorio()
									    +  credito_safi.getSaldo_interes_provisional()
									    +  credito_safi.getSaldo_interes_atrasado()						    
									    +  credito_safi.getSaldo_interes_vencido()
									    +  credito_safi.getSaldo_interes_no_contable();		
		
		iva_generado 				    = (credito_safi.getSaldo_interes_provisional() * iva) 
									    + (credito_safi.getSaldo_interes_atrasado()    * iva)
									    + (credito_safi.getSaldo_moratorio()           * iva)
									    + (credito_safi.getSaldo_com_falta_pago()      * iva)
									    + (credito_safi.getSaldo_interes_vencido()     * iva) 
									    + (credito_safi.getSaldo_interes_no_contable() * iva);
		
		if((estatus.equals("P") || estatus.equals("RP") )) {
		
			fecha_ultimo_pago = fm1.parse(credito_safi.getCredito_fecha_ultimo_pago());
		
			credito_LIQUIDADO_EN_FECHA = !ver_creditos_pagados_ENABLED && (fechaInicio.compareTo(fecha_ultimo_pago) >= 0 || fechaFin.compareTo(fecha_ultimo_pago) <= 0 );
		
		}
	}
	
	protected void init_credito(int indice_posicion_SAFI) throws ParseException, NumberFormatException, RemoteException 
	{		
		credito     = new CreditoEMO();				
		restructure = new RestructureBean();										
							
		credito.setStatus    (estatus);
		credito.setNumCredito(credito_id);
		credito.setNumCuotas (credito_safi.getAmortizacion_numero());				
		
		credito_VENCIDO = estatus != null && (estatus.equals("B") || estatus.equals("RB") );
		
		if(credito_VENCIDO)
		{						
			capital_vencido = saldo_capital_vencido + saldo_capital_vencido_no_exigle;
			
		} else {
			
			capital_vencido = saldo_capital_vencido;					
		}	
				
		init_lista_amortizacion();			
				
		if(cuotas_vencidas_ENABLED)
		{
			credito_vencido = true;	
			
		} else if(estatus.equals("RB") ) {
			
			credito_safi.setCredito_estatus("RV");
			credito.setStatus("RV");
		}
		
		credito.setTablaAmort(lista_amortizacion);
		
		init_relationship();
		ini_CAT();	
		
		asignarComisiones();			
		asignarTasas();						
		asignarProyectLoan();			
		asignarFechas();			
		
		credito.setMonto(dec.format(credito_safi.getMonto()));
		
		asignarPlazo(indice_posicion_SAFI);																																																				
		asignarComisionesGeneradas();	
		asignarSaldoLiquidar();
				
		credito.setSaldoInsoluto     (dec.format(saldo_insoluto));
		credito.setSaldoCapVen       (dec.format(capital_vencido));														
		credito.setInteresesGenerados(dec.format(intereses_generados));	
		credito.setIvaGenerado       (dec.format(iva_generado));						
		
		restructure.setSaldoLiquidar(saldoLiquidar);
		
		credito.setProyect(restructure);
		
		asignarReportePago();
		asignarColores();
		asignarscriptGraphic();										
		
		//credito.setMontoProxCuota(dec.format(credito_safi.getCredito_cuota() + 15));
		
		credito.setMontoProxCuota( dec.format( getCuota( credito.getNumCredito() ) + 15 ) );
		
    	asignarPanelMontoDetalle();	 
    	asignarCreditoTotal();
    	asignarPanelReferenciaPago();
		
    	lista_creditos.add(credito);
		
	}

	protected void init_lista_amortizacion() throws NumberFormatException, ParseException, RemoteException 
	{
		status                = true;		
		credito_vencido       = false;
		credito_mora          = false;		
		cuotas_vencidas_ENABLED = false;
		
		lista_amortizacion = new ArrayList<TAmortizacionBean>() ;								
		
		for(int indice_pago = 0; indice_pago < pagos_SAFI.size(); indice_pago++)
		{				
			
			
			//System.out.println( "credito: " + credito.getNumCredito() + " pagoSafi_credito: " + pagos_SAFI.get(indice_pago).getPk() .getCreditoId() );
			
			if(credito.getNumCredito().equals( (pagos_SAFI.get(indice_pago).getPk() .getCreditoId())+"") )
			{						
				amortizacion = new TAmortizacionBean();
				
				amortizacion.setCredit_id     (pagos_SAFI.get(indice_pago).getPk() .getCreditoId()+"");
				amortizacion.setId            (pagos_SAFI.get(indice_pago).getPk().getAmortizacionId()+"");
				amortizacion.setCapital       (pagos_SAFI.get(indice_pago).getCapital()       == null ? "" : (dec.format(pagos_SAFI.get(indice_pago).getCapital())));
				amortizacion.setCuota         (pagos_SAFI.get(indice_pago).getCuota()         == null ? "" : (dec.format(pagos_SAFI.get(indice_pago).getCuota())));
				amortizacion.setCuotaComision (pagos_SAFI.get(indice_pago).getCuotaMasCom() == null ? ""   : (dec.format(pagos_SAFI.get(indice_pago).getCuotaMasCom())));												
				amortizacion.setInteres       (pagos_SAFI.get(indice_pago).getInteres()       == null ? "" : (dec.format(pagos_SAFI.get(indice_pago).getInteres())));
				amortizacion.setIva           (pagos_SAFI.get(indice_pago).getIvaInteres()    == null ? "" : (dec.format(pagos_SAFI.get(indice_pago).getIvaInteres())));
				amortizacion.setFecExigble    (pagos_SAFI.get(indice_pago).getFechaExigible() == null ? "" : formatStr.format(  pagos_SAFI.get(indice_pago).getFechaExigible()));
				
				//System.out.println( " fecha liquida: " + pagos_SAFI.get(indice_pago).getFechaLiquida() );
				
				amortizacion.setFecPago       ( ( pagos_SAFI.get(indice_pago).getFechaLiquida()  == null || (  pagos_SAFI.get(indice_pago).getFechaLiquida() +"").equals("1900-01-01 00:00:00.0")  )  ? "" : formatStr.format(  pagos_SAFI.get(indice_pago).getFechaLiquida()));
								
				amortizacion.setFecCorte("");
				
				asignar_status(indice_pago);													
				asignar_comportamiento(indice_pago);	
								
				if( !cuotas_vencidas_ENABLED )
				{
					cuotas_vencidas_ENABLED = pagos_SAFI.get(indice_pago).getEstatus() != null && (pagos_SAFI.get(indice_pago).getEstatus().equals("B") || estatus.equals("B"));
				}
				
				if(!credito_mora)
				{
					credito_mora            = pagos_SAFI.get(indice_pago).getEstatus() != null &&  pagos_SAFI.get(indice_pago).getEstatus().equals("A") && !cuotas_vencidas_ENABLED;
				}
				
				if(pagos_SAFI.get(indice_pago).getEstatus().equals("A"))
				{
					System.out.println("ServiceEstadoCuentaAMO.init_lista_amortizacion()");
				}
				
				lista_amortizacion.add(amortizacion);
			
			
				if(amortizacion_id != null && pagos_SAFI.get(indice_pago).getPk() .getAmortizacionId() != null &&  amortizacion_id.equals( (pagos_SAFI.get(indice_pago).getPk() .getAmortizacionId()+"") ))
				{							
					capitalDes = dec.format( pagos_SAFI.get(indice_pago).getCapital()    == null ? 0D : pagos_SAFI.get(indice_pago).getCapital());
					interesDes = dec.format( pagos_SAFI.get(indice_pago).getInteres()    == null ? 0D : pagos_SAFI.get(indice_pago).getInteres());
					ivaDes     = dec.format( pagos_SAFI.get(indice_pago).getIvaInteres() == null ? 0D : pagos_SAFI.get(indice_pago).getIvaInteres());							
				}
				
				calTemp = Calendar.getInstance();
				calTemp.setTime(fechaCorte);
				calTemp.add(Calendar.DATE, 1);
				
				//System.out.println("fechaCorte: "+calTemp.getTime()+"    ---    fechaExigible: "+ fm1.parse( pagos_SAFI.get(indice_pago).getFechaexigible() ));
				if(calTemp.getTime() != null && calTemp.getTime().compareTo(  pagos_SAFI.get(indice_pago).getFechaExigible()  ) == 0 && !pagos_SAFI.get(indice_pago).getEstatus().equals("P") && !pagos_SAFI.get(indice_pago).getEstatus().equals( "RP" ) )
				{										
					if(pagos_SAFI.get(indice_pago).getCapital() == null)
					{
						tmpCapVen = Double.parseDouble("0");
						
					} else {
						
						tmpCapVen = pagos_SAFI.get(indice_pago).getCapital();	
					}	
				}					
			}
		}
	}
	
	private void asignar_status(int indice_pago) throws RemoteException 
	{
		if(!pagos_SAFI.get(indice_pago).getEstatus().equals("P") && !pagos_SAFI.get(indice_pago).getEstatus().equals( "RP" ) && status  )
		{							
			creditos_SAFI = estadocuentaservice.getTSafiCreditosMovs(pagos_SAFI.get(indice_pago).getPk().getCreditoId(), pagos_SAFI.get(indice_pago).getPk().getAmortizacionId(), (prospectus_id));
			
			if(creditos_SAFI != null)
			{
				amortizacion.setStatus("PA_" + pagos_SAFI.get(indice_pago).getEstatus());
				
			} else {
				
				amortizacion.setStatus(pagos_SAFI.get(indice_pago).getEstatus());
			}
			
			status = false;
			
		} else {
			
			amortizacion.setStatus(pagos_SAFI.get(indice_pago).getEstatus());
		}
	}

	private void asignar_comportamiento(int indice_pago) throws ParseException 
	{
		if(pagos_SAFI.get(indice_pago).getFechaLiquida() != null && (pagos_SAFI.get(indice_pago).getFechaLiquida()+"").length() > 0 && !((pagos_SAFI.get(indice_pago).getFechaLiquida()+"").equals("1900-01-01 00:00:00.0") ) && pagos_SAFI.get(indice_pago).getFechaExigible() != null && (pagos_SAFI.get(indice_pago).getFechaExigible()+"").length() > 0)
		{
			d1 =   pagos_SAFI.get(indice_pago).getFechaExigible() ;
			d2 =   pagos_SAFI.get(indice_pago).getFechaLiquida() ;
			
			ld1 = d1.getTime();
			ld2 = d2.getTime();
			
			dias_transcurridos = ( (ld2 - ld1) / MILLSECS_PER_DAY );
					
			if(dias_transcurridos < 0)
			{
				amortizacion.setComportamiento("1-0d");
				
			} else if( dias_transcurridos >= 0 && dias_transcurridos < 1 ) {
				
				amortizacion.setComportamiento("1-0d");
				
			} else if( dias_transcurridos >= 1 &&  dias_transcurridos < 30 ) {
				
				amortizacion.setComportamiento("2-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 30 &&  dias_transcurridos < 60 ) {
				
				amortizacion.setComportamiento("3-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 60 &&  dias_transcurridos < 90 ) {
				
				amortizacion.setComportamiento("4-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 90 &&  dias_transcurridos < 120 ) {
				
				amortizacion.setComportamiento("5-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 120 &&  dias_transcurridos < 150 ) {
				
				amortizacion.setComportamiento("6-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 150 &&  dias_transcurridos <= 365 ) {
				
				amortizacion.setComportamiento("7-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos > 365  ) {
				
				amortizacion.setComportamiento("96-"+dias_transcurridos.intValue()+"d");
			}
			
			diasT = dias_transcurridos.intValue();
			
		} else if((pagos_SAFI.get(indice_pago).getFechaLiquida() == null || (pagos_SAFI.get(indice_pago).getFechaLiquida()+"").length() > 0) && ((pagos_SAFI.get(indice_pago).getFechaExigible()!= null && (pagos_SAFI.get(indice_pago).getFechaExigible()+"").length() > 0 ) && (pagos_SAFI.get(indice_pago).getFechaExigible().getTime() < (new Date()).getTime()))) {
			
			d1 =   pagos_SAFI.get(indice_pago).getFechaExigible() ;
			d2 =  new Date() ;
			
			ld1 = d1.getTime();
			ld2 = d2.getTime();
			
			dias_transcurridos = ( (ld2 - ld1) / MILLSECS_PER_DAY );
			
			if(dias_transcurridos < 0)
			{
				amortizacion.setComportamiento("1-0d");
				
			} else if( dias_transcurridos >= 0 && dias_transcurridos < 1 ) {
				
				amortizacion.setComportamiento("1-0d");
				
			} else if( dias_transcurridos >= 1 &&  dias_transcurridos < 30 ) {
				
				amortizacion.setComportamiento("2-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 30 &&  dias_transcurridos < 60 ) {
				
				amortizacion.setComportamiento("3-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 60 &&  dias_transcurridos < 90 ) {
				
				amortizacion.setComportamiento("4-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 90 &&  dias_transcurridos < 120 ) {
				
				amortizacion.setComportamiento("5-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 120 &&  dias_transcurridos < 150 ) {
				
				amortizacion.setComportamiento("6-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos >= 150 &&  dias_transcurridos <= 365 ) {
				
				amortizacion.setComportamiento("7-"+dias_transcurridos.intValue()+"d");
				
			} else if( dias_transcurridos > 365  ) {
				
				amortizacion.setComportamiento("96-"+dias_transcurridos.intValue()+"d");
				
			}
			
			diasT = dias_transcurridos.intValue();
			
			if(dias_transcurridos.intValue() > 0 && (pagos_SAFI.get(indice_pago).getEstatus().equals("V") || pagos_SAFI.get(indice_pago).getEstatus().equals("RV") ) )
			{									
				capital_vigente_tmp =   pagos_SAFI.get(indice_pago).getCapital() == null? 0D:pagos_SAFI.get(indice_pago).getCapital() ;
				interes_vigente_tmp =   pagos_SAFI.get(indice_pago).getInteres() == null? 0D:pagos_SAFI.get(indice_pago).getInteres() ; 
				
				//System.out.println("capVigTemp: "+capVigTemp+"    intVigTemp: "+intVigTemp);									
			}
			
		} else {		
			
			amortizacion.setComportamiento("");							
		}
	}	
	
	protected void init_relationship() 
	{						
		proyectloan = service_proyect_loan.getProyectLoanListBySafiCreditID(credito_id);
		
		if(proyectloan != null)
		{									
			relationship = service_collection_relationship.getCollectionRelationshipByPLPK(proyectloan.getProyectloanPk());
			
			if(relationship != null && relationship.getIs_active().equals("S"))
			{							
				credito.setHasCollection(true);
				credito.setCollectionrelationship(relationship);
				
				list_stackholder_size = relationship.getCollectionCompany().getLstStackholder().size();
				
				System.out.println("size: " + Integer.toString(list_stackholder_size));									
			} 
		}	
	}
	
	protected void ini_CAT() 
	{		
		if(credito_safi.getValor_cat() != null)
		{
			catdl =  Double.parseDouble(credito_safi.getValor_cat());				
			catlg = (double) Math.round(catdl * 10) / 10	;
			
			credito.setCat(catlg + "");
					
		} else {		
			
			credito.setCat("No disponble");						
		}
	}
	
	protected void asignarComisiones() 
	{
		Double[] o =  (Double[]) comisiones.get(credito_safi.getCredito_id());
		
		if( o != null )
		{					
			Double[] arrayD = ((Double[]) o);
			
			if(arrayD[0] !=null)
			{
				credito.setComisiones( dec.format( Double.parseDouble( arrayD[0].toString() ) ) );
			} else {
				credito.setComisiones( "$0.00" );
			}
			
			if(arrayD[1] !=null)
			{
				credito.setInteresesCobrados( dec.format( Double.parseDouble( arrayD[1].toString() )  ) );
			} else {
				credito.setInteresesCobrados( "$0.00" );
			}
			
		} else {						
			credito.setComisiones( "$0.00" );
			credito.setInteresesCobrados( "$0.00" );
		}
	}
	
	protected void asignarTasas() 
	{
		asignarTasaFija();
		asignarTasaMora();
		asignarTasaMoraMensual();		
		asignarTasaMensual();
	}
	
	private void asignarTasaFija() 
	{
		String tasa = credito_safi.getTasa_fija();
		
		if(tasa.split("\\.").length < 2)
		{					
			tasa = tasa + ".00";						
		} else {						
			if(tasa.split("\\.")[1].length() < 2)
			{
				tasa = tasa + "0";
			}						
		}
		
		credito.setTasa(tasa);
	}
	
	private void asignarTasaMora() 
	{
		String tasaMora = num.format(getTasa_mora());
		
		if(tasaMora.split("\\.").length < 2)
		{						
			tasaMora = tasaMora+".00";						
		} else {
			if(tasaMora.split("\\.")[1].length() < 2)
			{
				tasaMora = tasaMora + "0";
			}					
		}
		
		credito.setTasaMora(tasaMora);	
	}
	
	private void asignarTasaMoraMensual() 
	{										
		Double tem       = (getTasa_mora() / 360) * 30;					
		Double tmoraMen  = (Math.rint(tem * 100)) / 100 ;

		String tmoraMenStr = num.format(tmoraMen);
		
		if(tmoraMenStr.split("\\.").length < 2)
		{						
			tmoraMenStr = tmoraMenStr + ".00";
			
		} else {
			if(tmoraMenStr.split("\\.")[1].length() < 2)
			{
				tmoraMenStr = tmoraMenStr + "0";
			}						
		}
		
		credito.setTasaMoraMensual(tmoraMenStr);		
	}
	
	private void asignarTasaMensual() 
	{
		Double temp2   = ((Double.parseDouble(credito_safi.getTasa_fija()) / 360 ) * 30);					
		Double tasaMen = (Math.rint(temp2 * 100)) / 100 ;
		
		String tasaMenStr = num.format(tasaMen);
		
		if(tasaMenStr.split("\\.").length < 2)
		{						
			tasaMenStr = tasaMenStr + ".00";						
		} else {
			if(tasaMenStr.split("\\.")[1].length() < 2)
			{
				tasaMenStr = tasaMenStr + "0";
			}						
		}
		
		credito.setTasaMensual(tasaMenStr);	
	}
	
	protected void asignarProyectLoan() 
	{
		proyect_loan = getProyectLoanByCreditId(lista_proyect_loan , credito_safi.getCredito_id());
		
		if(proyect_loan != null)
		{
			credito.setProyect_id(proyect_loan.getProyectloanPk().getProyect_id()+"");
			credito.setProyect_loan_id(proyect_loan.getProyectloanPk().getProyect_loan_id()+"");
		}
		
		restructure.setProyect(proyect_loan);		
	}
	
	protected void asignarFechas() throws ParseException 
	{
		asignarFechaInicio();								
		asignarFechaVencimiento();
		
		
			credito.setFecProxCuota( credito_safi.getFecha_prox_ven()  );
		
		
		credito.setFecProxCuotaResumen(credito_safi.getFecha_prox_ven());
	}
	
	private void asignarFechaInicio() throws ParseException 
	{				
		if(credito_safi.getFecha_inicio() != null)
		{						
			fecha_inicio = formato_ddMMyyyy.format( fm1.parse(credito_safi.getFecha_inicio()));
			restructure.setFechaInicio(fecha_inicio);
			credito.setFecInicio(fecha_inicio);					
		}		
	}

	private void asignarFechaVencimiento() throws ParseException 
	{				
		if( credito_safi.getFecha_vencimiento() != null)
		{						
			fecha_vencimiento = formato_ddMMyyyy.format(fm1.parse(credito_safi.getFecha_vencimiento()));
			restructure.setFechaFin(fecha_vencimiento);
			credito.setFecFin(fecha_vencimiento );									
		}
	}
	
	protected void asignarPlazo(int indice_posicion_SAFI) 
	{
		if( proyect_loan != null)
		{						
			plazo = proyect_loan.getTerm_id() + " ";
			
			frequency_id = proyect_loan.getFrequency_id();
			
			switch(frequency_id)
			{
				case 1:
					plazo += "Semanas";
				break;
				
				case 2:
					plazo += "Catorcenas";
				break;	
				
				case 3:
					plazo += "Quincenas";
				break;	
				
				case 4:
					plazo += "Meses";
				break;	
				
				default:
					plazo += "";
				break;
			}		
										
			credito.setPlazo(plazo);
			
		} else {
			
			String frecuencia_cap = posicion_SAFI.get(indice_posicion_SAFI).getFrecuenciaCap();
						
			if(frecuencia_cap != null &&  frecuencia_cap.equals("S")  )
			{
				plazo = "Semanal";
				
			} else if( frecuencia_cap != null &&  frecuencia_cap.equals("C") ) {
				
				plazo = "Catorcenal";
				
			} else if( frecuencia_cap != null &&  frecuencia_cap.equals("Q") ) {
				
				plazo = "Quincenal";
				
			} else if( frecuencia_cap != null &&  frecuencia_cap.equals("M") ){
				
				plazo = "Mensual";
			}
						
			credito.setPlazo(plazo);
			
		}
	}
	
	protected void asignarComisionesGeneradas() 
	{
		credito.setComisionesGenerados(dec.format(credito_safi.getSaldo_com_falta_pago()));	
	}
	
	
	protected void asignarSaldoLiquidar() 
	{
		/*
		saldoLiquidar = credito_safi.getSaldo_capital_vigent() 
					  + credito_safi.getSaldo_interes_provisional()
					  + credito_safi.getSaldo_capital_atrasado() 
					  + credito_safi.getSaldo_interes_atrasado() 
					  + credito_safi.getSaldo_moratorio() 
					  + credito_safi.getSaldo_com_falta_pago()
					  + capital_vencido			
					  + credito_safi.getSaldo_interes_vencido()			
					  + credito_safi.getSaldo_interes_no_contable()		
					  + (credito_safi.getSaldo_interes_no_contable() * iva)								   
					  + (credito_safi.getSaldo_interes_provisional() * iva) 
					  + (credito_safi.getSaldo_interes_atrasado()    * iva) 
					  + (credito_safi.getSaldo_moratorio()           * iva) 
					  + (credito_safi.getSaldo_com_falta_pago()      * iva)
					  + (credito_safi.getSaldo_interes_vencido()     * iva) 
					  + 15;
		
		if( saldoLiquidar == 15D)
		{
			saldoLiquidar = 0D;
		}
		
		*/
		
		saldoLiquidar = estadocuentadatosservice.getMontoParaLiquidar(credito.getNumCredito()) + 15 ;
		
		saldoLiquidarStr = dec.format(saldoLiquidar);
		
		credito.setSaldoLiquidar(saldoLiquidarStr);
		
		if(saldoLiquidar > 0 && !area.equals('I') && prospectus_id > 0)
		{
			credito.setSaldo_liquidar_ENABLED(true);
			
		} else {
			
			credito.setSaldo_liquidar_ENABLED(false);
		}			
	}
	
	protected void asignarReportePago() 
	{
		reportePago = "<table style='border-collapse: collapse;' >" 
				   + "<tr><td>Capital:</td><td style='text-align: right;' >"
				   + dec.format(credito_safi.getSaldo_capital_vigent())
				   + "</td></tr>";
									
		if(credito_safi.getSaldo_capital_atrasado() > 0)
		{
			reportePago +=	"<tr><td>Capital atrasado:</td><td style='text-align: right;'> "
		                + dec.format(credito_safi.getSaldo_capital_atrasado())+"</td></tr>" ;						
		}
		
		if(capital_vencido > 0)
		{
			reportePago +=	"<tr><td>Capital vencido:</td><td style='text-align: right;'> " 
					     + dec.format(capital_vencido)
					     + "</td></tr>" ;					
		}
		
		if(credito_safi.getSaldo_interes_provisional() > 0)
		{
			reportePago += "<tr><td>Interés:</td><td style='text-align: right;' >"
					    +  dec.format(credito_safi.getSaldo_interes_provisional())+"</td></tr>" ;
			reportePago +=	"<tr><td>I.V.A. Interés:</td><td style='text-align: right;' > "
					    +dec.format((credito_safi.getSaldo_interes_provisional() * iva))+"</td></tr>";
		}
		
		if(credito_safi.getSaldo_interes_atrasado() > 0)
		{
			reportePago +=	"<tr><td>Interés atrasado:</td><td style='text-align: right;' > "
					    +dec.format(credito_safi.getSaldo_interes_atrasado())+"</td></tr>";
			reportePago +=	"<tr><td>I.V.A. Interés atrasado:</td><td style='text-align: right;' > "
					    +dec.format((credito_safi.getSaldo_interes_atrasado()    * iva))+"</td></tr>";
		}
		
		if(credito_safi.getSaldo_interes_vencido() > 0)
		{
			reportePago +=	"<tr><td>Interés vencido:</td><td style='text-align: right;' > "
						+ dec.format(credito_safi.getSaldo_interes_vencido())+"</td></tr>" ;
			reportePago +=	"<tr><td>I.V.A. Interés vencido:</td><td style='text-align: right;' > "
						+dec.format((credito_safi.getSaldo_interes_vencido()     * iva))+"</td></tr>" ;
		}
		
		if(credito_safi.getSaldo_interes_no_contable() > 0)
		{
			reportePago +=	"<tr><td>Interés no contabilizado:</td><td style='text-align: right;' > "
					    + dec.format(credito_safi.getSaldo_interes_no_contable())+"</td></tr>" ;
			reportePago +=	"<tr><td>I.V.A. Interés no contabilizado:</td><td style='text-align: right;' > "
					    +dec.format((credito_safi.getSaldo_interes_no_contable() * iva))+"</td></tr>" ;
		}
		
		if(credito_safi.getSaldo_moratorio() > 0)
		{
			reportePago +=	"<tr><td>Moratorios:</td><td style='text-align: right;' > "
						+  dec.format(credito_safi.getSaldo_moratorio())+"</td></tr>" ;
			reportePago +=	"<tr><td>I.V.A. Moratorios:</td><td style='text-align: right;' > "
						+dec.format((credito_safi.getSaldo_moratorio()           * iva))+"</td></tr>";
		}
		
		if(credito_safi.getSaldo_com_falta_pago() > 0)
		{
			reportePago +=	"<tr><td>Comisión falta de pago:</td><td style='text-align: right;' > "
					    +  dec.format(+ credito_safi.getSaldo_com_falta_pago())+"</td></tr>" ;
			reportePago +=	"<tr><td>I.V.A. Comisión:</td><td style='text-align: right;' > "
					    +dec.format((credito_safi.getSaldo_com_falta_pago()      * iva))+"</td></tr>";
		}
		
		reportePago +=	//"<tr><td>I.V.A.:</td><td style='text-align: right;' >"+ivaS+"</td></tr>" +
				"<tr><td style='border-bottom: solid 2px #000000;' >Comisión pago en ventanilla:</td><td style='text-align: right; border-bottom: solid 2px #000000;' >"
				+ dec.format( 15 )+"</td></tr>" 
				+ "<tr><td>Total:</td><td style='text-align: right;' >"
				+ saldoLiquidarStr
				+ "</td></tr>" 
				+ "</table>";
		
		credito.setContDesTotal(reportePago);		
	}
	
	protected void asignarColores() 
	{
		colores = "";
		boolean flagColor = false;
		
		scriptGraphic ="<script>function drawChart_"+credito.getNumCredito()+"() {" +
				"var data = google.visualization.arrayToDataTable([['Concepto', 'Monto'],";
		
		if( getCapitales() > 0 )
		{						
			scriptGraphic += "['Capital'," + Math.round(getCapitales()) + "],";						
			colores       += "'#1C8BC6'";						
			flagColor = true;
		}
		
		if(getIntereses() > 0)
		{
			scriptGraphic += "['Interés',"+Math.round(getIntereses())+"],";
			
			if(flagColor)
			{
				colores += ",";
			}
			
			colores += "'#ECDD14'";
			
			flagColor = true;
		}
		
		if(credito_safi.getSaldo_com_falta_pago() > 0)
		{
			scriptGraphic += "['Comisiones',"+Math.round(credito_safi.getSaldo_com_falta_pago())+"],";
			
			if(flagColor)
			{
				colores += ",";
			}
			
			colores += "'#1DBEF0'";
			
			flagColor = true;
		}
		
		if(iva_generado > 0)
		{
			scriptGraphic += "['IVA'," + Math.round(iva_generado) + "],";
			
			if(flagColor)
			{
				colores += ",";
			}
			
			colores += "'#EF549B'";
			
			flagColor =true;
		}
		
		if(iva_generado > 0)
		{
			scriptGraphic += "['Capital Pagado'," + Math.round(getCapital_pagado()) + "]";
			
			if(flagColor)
			{
				colores += ",";
			}
			
			colores += "'#109618'";
			
			flagColor = true;
		}
	}
	
	protected void asignarscriptGraphic() 
	{
		scriptGraphic += "]);" +
		  		"var options = {is3D: true,chartArea: {top:0,width:'80%',height:'95%'},colors:["
				+ colores
				+ "],tooltip:{text:'percentage',textStyle:{fontSize:15} }, legend:{textStyle:{fontSize:12} },sliceVisibilityThreshold: (1/3720)};" 
				+ "var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_"
				+ credito.getNumCredito()
				+ "'));" 
				+ "chart.draw(data, options);" + " }" + ""
				+ "drawChart_"+credito.getNumCredito()+"();" + "" + "</script>";
		
		//System.out.println( scriptGraphic );
		
		credito.setScriptGraphic(scriptGraphic);	
	}
	
	protected void asignarPanelMontoDetalle() 
	{
		if(credito_vencido)
		{
			asignarMonto(Credito.VENCIDO);
							
		} else if(credito_mora) {
			
			asignarMonto(Credito.MORA);
									
		} else if(isCredito_VIGENTE()) {
			
			asignarMonto(Credito.VIGENTE);
			
		} else if(isCredito_LIQUIDADO()) {
			
			contador_creditos_liquidados++;						
		}
	}
	
	private void asignarMonto(Credito credito)
	{
		switch(credito)
		{
			case VIGENTE: 
				asignarMonto_CREDITO_VIGENTE();
			break;
			
			case MORA:
				asignarMonto_CREDITO_MORA();
			break;
			
			case VENCIDO:				
				asignarMonto_CREDITO_VENCIDO();
			break;
				
			default: break;				
		}				
	}
	
	private void asignarMonto_CREDITO_VENCIDO() 
	{
		contador_creditos_vencidos++;
									
		credito.setMontoProxCuota(dec.format(getCuota(credito.getNumCredito())));
		credito.setFecProxCuota("INMEDIATA");
		credito.setContDesMonto(getPanel(Credito.VENCIDO));														
	}

	private void asignarMonto_CREDITO_MORA() 
	{
		contador_creditos_mora++;
		credito.setStatus("A");			
								
		if(credito_safi.getSaldo_capital_vigente() != 0)
		{							
			//2103 - 12 - 10 RJML RMB
			//Correccion para evitar la doble suma del saldos vigentes de créditos atrasados con una cuota en gracia
			
			capital_vigente_tmp = 0D;
			interes_vigente_tmp = 0D;							
		}
					
		credito.setMontoProxCuota(dec.format(getCuota( credito.getNumCredito() ) + 15 ));		
		credito.setFecProxCuota("INMEDIATA");
		credito.setContDesMonto(getPanel(Credito.MORA));
	}
	
	private void asignarMonto_CREDITO_VIGENTE() 
	{
		contador_creditos_vigentes++;													
		credito.setContDesMonto(getPanel(Credito.VIGENTE));	
	}
	
	private Double getCuota(String credito)
	{
		
		/*
		 
		switch(credito)
		{
			
			case VIGENTE: 
				getCuota_CREDITO_VIGENTE();
			break;
			
			case MORA:
				getCuota_CREDITO_MORA();
			break;
			
			case VENCIDO:				
				getCuota_CREDITO_VENCIDO();
			break;
				
			default: break;				
		}
		
		*/
		// --
		cuota_pago = estadocuentadatosservice.getMontoExigible( credito );
		
		if(  cuota_pago != null && Double.compare( cuota_pago , 0D ) == 0 ){
			
			calculaProximoPago();
			
			
		}
		
		return cuota_pago;
	}
	
	private void calculaProximoPago(){
		
			for(int indice_pago = 0; indice_pago < pagos_SAFI.size(); indice_pago++)
			{				
				if(credito.getNumCredito().equals( (pagos_SAFI.get(indice_pago).getPk().getCreditoId() +"") ))
				{
					TSafiPagosCuota amortizaciontemp = pagos_SAFI.get(indice_pago);
					
					if( amortizaciontemp.getEstatus().equals("V") ){
					
						credito.setMontoProxCuota(dec.format(  amortizaciontemp.getCuotaMasCom()  ));	
						try{
							credito.setFecProxCuota( formato_ddMMyyyy.format(  amortizaciontemp.getFechaExigible() )    );
						}catch(Exception e){
							e.printStackTrace();
						}
						
						break;
					
					}
				}
				
			}
		
	}
	
	private Double getCuota_CREDITO_VIGENTE()
	{
		cuota_pago = credito_safi.getCredito_cuota_mas_comision();
		
		return cuota_pago;
	}
	
	private Double getCuota_CREDITO_VENCIDO() 
	{
		cuota_pago =  getCapital_VENCIDO()
				   +  credito_safi.getSaldo_capital_atrasado() 
				   +  credito_safi.getSaldo_capital_vigente()
				   +  credito_safi.getSaldo_moratorio() 
				   +  credito_safi.getSaldo_com_falta_pago()								   	
				   +  credito_safi.getSaldo_interes_atrasado() 
				   +  credito_safi.getSaldo_interes_provisional() 
				   +  credito_safi.getSaldo_interes_no_contable() 	
				   +  credito_safi.getSaldo_interes_vencido() 		
				   + (credito_safi.getSaldo_interes_atrasado() 
				   +  credito_safi.getSaldo_interes_provisional()  * iva)
				   + (credito_safi.getSaldo_interes_vencido()      * iva)							   
				   + (credito_safi.getSaldo_interes_no_contable()  * iva)		
				   + (credito_safi.getSaldo_moratorio()            * iva)
				   + (credito_safi.getSaldo_com_falta_pago()       * iva)						   
				   + 15;
		
		return cuota_pago;
	}
	
	private Double getCuota_CREDITO_MORA()
	{
		cuota_pago  =   capital_vigente_tmp   + credito_safi.getSaldo_capital_atrasado() + credito_safi.getSaldo_capital_vigente() 
					+   interes_vigente_tmp   + credito_safi.getSaldo_interes_atrasado() + credito_safi.getSaldo_interes_pro()
					+ ((interes_vigente_tmp   + credito_safi.getSaldo_interes_atrasado() + credito_safi.getSaldo_interes_pro()) * iva)
				    +   credito_safi.getSaldo_moratorio() 
				    +   credito_safi.getSaldo_com_falta_pago() 				    				    
				    +  (credito_safi.getSaldo_moratorio()      * iva) 
				    +  (credito_safi.getSaldo_com_falta_pago() * iva) 
				    + 15;
		
		return cuota_pago;
	}
	
	protected final Double getCapital_VENCIDO()
	{
		capital_vencido = tmpCapVen + credito_safi.getSaldo_capital_vencido();
		
		return capital_vencido;
	}
	
	private String getPanel(Credito credito)
	{
		switch(credito)
		{
			case VIGENTE: 
				getPanel_CREDITO_VIGENTE(credito);
			break;
			
			case MORA:
				getPanel_CREDITO_MORA(credito);
			break;
			
			case VENCIDO:				
				getPanel_CREDITO_VENCIDO(credito);
			break;
				
			default: break;				
		}
		
		return panel_monto_detalle;
	}
	
	private String getPanel_CREDITO_VENCIDO(Credito credito)
	{
		sb = new StringBuilder();
		
		sb.append(TABLE);
		sb.append(TITLE).append("Capital atrasado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getCapital_ATRASADO())).append(TR_FIN);
		
		sb.append(TITLE).append("Capital vencido:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getCapital_VENCIDO())).append(TR_FIN);
		
		sb.append(TITLE).append("Interés atrasado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_ATRASADO())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Interés atrasado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_ATRASADO_IVA())).append(TR_FIN);
		
		sb.append(TITLE).append("Interés vencido:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_VENCIDO())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Interés vencido:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_VENCIDO_IVA())).append(TR_FIN);
		
		sb.append(TITLE).append("Interés no contabilizado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_NO_CONTABLE())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Interés no contabilizado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_NO_CONTABLE_IVA())).append(TR_FIN);
		
		sb.append(TITLE).append("Intereses moratorios:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_MORATORIO())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Intereses moratorios:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_MORATORIO_IVA())).append(TR_FIN);
		
		sb.append(TITLE).append("Comisión falta de pago:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_FALTA_PAGO())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Comisión:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_IVA())).append(TR_FIN);
		
		sb.append(TITLE_UNDER).append("Comisión pago en ventanilla:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_PAGO_VENTANILLA())).append(TR_FIN);
		
		sb.append(TITLE).append("Total:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getCuota( credito_id ))).append(TR_FIN);
		sb.append(TABLE_FIN);
		
		panel_monto_detalle = sb.toString();
		
		return panel_monto_detalle;
	}
	
	private final Double getInteres_ATRASADO()
	{
		interes = credito_safi.getSaldo_interes_atrasado() 
				+ credito_safi.getSaldo_interes_provisional();
		
		return interes;
	}
	
	private final Double getInteres_ATRASADO_CREDITO_MORA()
	{
		interes = interes_vigente_tmp 
				+ credito_safi.getSaldo_interes_atrasado() 
				+ credito_safi.getSaldo_interes_pro();
		
		return interes;
	}
		
	private final Double getInteres_ATRASADO_IVA()
	{
		interes = (credito_safi.getSaldo_interes_atrasado() 
				+  credito_safi.getSaldo_interes_provisional() * iva);
				
		return interes;
	}
	
	private final Double getInteres_ATRASADO_IVA_CREDITO_MORA()
	{
		interes = ((interes_vigente_tmp 
				+ credito_safi.getSaldo_interes_atrasado() 
				+ credito_safi.getSaldo_interes_pro()) * iva);
		
		return interes;
	}
	
	private final Double getInteres_VENCIDO()
	{
		interes = credito_safi.getSaldo_interes_vencido();
		
		return interes;
	}
	
	private final Double getInteres_VENCIDO_IVA()
	{
		interes = credito_safi.getSaldo_interes_vencido() * iva;
		
		return interes;
	}
	
	private final Double getInteres_NO_CONTABLE()
	{
		interes = credito_safi.getSaldo_interes_no_contable();
		
		return interes;
	}
	
	private final Double getInteres_NO_CONTABLE_IVA()
	{
		interes = credito_safi.getSaldo_interes_no_contable() * iva;
		
		return interes;
	}
	
	private final Double getInteres_MORATORIO()
	{
		interes = credito_safi.getSaldo_moratorio();
		
		return interes;
	}
	
	private final Double getInteres_MORATORIO_IVA()
	{
		interes = credito_safi.getSaldo_moratorio() * iva;
		
		return interes;
	}
		
	private final Double getComision_FALTA_PAGO()
	{
		comision = credito_safi.getSaldo_com_falta_pago();
		
		return comision;
	}
	
	private final Double getComision_IVA()
	{
		comision = credito_safi.getSaldo_com_falta_pago() * iva;
		
		return comision;
	}
	
	private final Double getComision_PAGO_VENTANILLA()
	{
		comision = 15.00;
		
		return comision;
	}
	
	private String getPanel_CREDITO_MORA(Credito credito)
	{
		sb = new StringBuilder();
		
		sb.append(TABLE);
		sb.append(TITLE).append("Capital atrasado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getCapital_ATRASADO_CREDITO_MORA())).append(TR_FIN);
		
		sb.append(TITLE).append("Interés atrasado:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_ATRASADO_CREDITO_MORA())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Interés:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_ATRASADO_IVA_CREDITO_MORA())).append(TR_FIN);
				
		sb.append(TITLE).append("Intereses moratorios:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_MORATORIO())).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A. Intereses Moratorios:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getInteres_MORATORIO_IVA())).append(TR_FIN);
		
		sb.append(TITLE).append("Comisión falta de pago:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_FALTA_PAGO())).append(TR_FIN);		
		
		sb.append(TITLE).append("I.V.A. Comisión:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_IVA())).append(TR_FIN);
		
		sb.append(TITLE_UNDER).append("Comisión pago en ventanilla:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_PAGO_VENTANILLA())).append(TR_FIN);
		
		sb.append(TITLE).append("Total:").append(TD_FIN);
		sb.append(VALUE).append(dec.format( getCuota( credito_id ) +15 )).append(TR_FIN);
		sb.append(TABLE_FIN);
		
		panel_monto_detalle = sb.toString();
		
		return panel_monto_detalle;
	}
	
	private String getPanel_CREDITO_VIGENTE(Credito credito)
	{
		sb = new StringBuilder();
		
		sb.append(TABLE);
		sb.append(TITLE).append("Capital:").append(TD_FIN);
		sb.append(VALUE).append(capitalDes).append(TR_FIN);
		
		sb.append(TITLE).append("Interés:").append(TD_FIN);
		sb.append(VALUE).append(interesDes).append(TR_FIN);
		
		sb.append(TITLE).append("I.V.A.:").append(TD_FIN);
		sb.append(VALUE).append(ivaDes).append(TR_FIN);
		
		sb.append(TITLE_UNDER).append("Comisión pago en ventanilla:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getComision_PAGO_VENTANILLA())).append(TR_FIN);
		
		sb.append(TITLE).append("Total:").append(TD_FIN);
		sb.append(VALUE).append(dec.format(getCuota( credito_id ))).append(TR_FIN);
		sb.append(TABLE_FIN);
		
		panel_monto_detalle = new String( sb.toString() );
		
		return panel_monto_detalle;	
	}
	
	private final Double getCapital_ATRASADO()
	{
		capital_atrasado = credito_safi.getSaldo_capital_vigente() 
						 + credito_safi.getSaldo_capital_atrasado();
		
		return capital_atrasado;
	}
	
	private final Double getCapital_ATRASADO_CREDITO_MORA()
	{
		capital_atrasado_CREDITO_MORA = capital_vigente_tmp 
									  + credito_safi.getSaldo_capital_atrasado() 
									  + credito_safi.getSaldo_capital_vigente();
		
		return capital_atrasado_CREDITO_MORA;
	}
		
	protected void asignarCreditoTotal() 
	{
		creditos_totales = contador_creditos_vigentes
						 + contador_creditos_liquidados
						 + contador_creditos_mora
						 + contador_creditos_vencidos;	
	}
	
	protected void asignarPanelReferenciaPago() 
	{			
		if(credito.getMontoProxCuota() != null)
		{
			Double monto = Double.parseDouble(credito.getMontoProxCuota().replaceAll("\\$", "").replace(",", ""));
			monto = monto -15;
			
			cuota = monto + "";
			
		} else {
			
			cuota = "0.00";
		}
		
		if(!estatus.equals("P") && !estatus.equals("RP") && !sesion.getArea().toString().equals("I"))
		{
			service_referencia_pago =  new ServiceReferenciaPagoIMP();
			service_referencia_pago.setAcreditado(nombre);
			service_referencia_pago.setCuota(cuota);
			service_referencia_pago.setCredito_id       (credito.getNumCredito());			
			service_referencia_pago.setSaldo_liquidacion(credito.getSaldoLiquidar().replaceAll("\\$", "").replace(",", ""));
			
			referencia_pago = service_referencia_pago.getReferencia_pago();
			
			credito.setPanel_enabled(true);						
			credito.setReferenciaPagoPanel(referencia_pago);		
			
		} else {
			
			credito.setPanel_enabled(false);
		}
	}
}
