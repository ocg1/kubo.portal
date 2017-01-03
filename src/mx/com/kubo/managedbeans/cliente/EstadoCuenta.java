package mx.com.kubo.managedbeans.cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.MovsCuentaAhorro;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.Ticket;
import mx.com.kubo.model.TicketPk;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.services.cliente.cuenta.ServiceEstadoCuentaIMP;
import mx.com.kubo.tools.NumberToLetterConverter;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;

@ManagedBean(name = "estadoCuenta") @ViewScoped 
public final class EstadoCuenta extends EstadoCuentaAMO
implements Serializable, EstadoCuentaIMO 
{	
	private static final long serialVersionUID = 4755236788886030893L;

	@PostConstruct
	public void init()
	{				
		if(!asignarSesion())
		{
			return;
		}
		
		setDisPnlScriptPade(false);
		asignarCalendario();		
		
		asignarHeadless();
		
		verificarSesion();
		
		
		init_bandera_renovacion();
		
		asignarFechaSOFIPO();	
		asignarRazonSocial();	
		
		if( !accessFromURL ){
		
			if(sesion.getArea() == 'L')
			{	
				asignarProspectoFromSesion();
				inicializaEdoCuenta();
				
			} else if(asignarProspectusFromAutocomplete()) { 		
				
				inicializaEdoCuenta();					
			}
		
		}else{
			
			ProyectLoan proyect_loan = proyectLoanService.getProyectLoanListBySafiCreditID(credit_id);
			
			company_id = proyect_loan.getProyectloanPk().getCompany_id();
			prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
			inicializaEdoCuenta();	
		}
	
		asignarRestructure();
		asignarFactorMora();
		registrarAcceso();
		
	}

	public final void racargaProyectos()
	{
		displayPag = true;
		
		inicializaEdoCuenta();
	}
	
	public final void inicializaEdoCuenta()
	{				 		
		try
		{	
			asignarFactorMora();		
			asignarFecha();
			
			if(prospectus == null)
			{
				asignarProspecto();
			}			
			
			if (isImagenValida())
			{
				asignarImagen();
			}
			
			asignarMovimientos();						 
			inicializaMovimientos();
			
			//lista_creditos = new ArrayList<CreditoEMO>();
			
//			posicionInt = web_service_SGB.getTSafiPosicionInt(prospectus_id + "");
			
			posicionInt = estadocuentaservice.getTSafiPosicionInt(prospectus_id );
			
			if(posicionInt != null && tamortizacion != null && posicionInt.size() > 0 && tamortizacion.size() > 0  )
			{										
				flagRenderEdoCuenta = true;				
				displayMoreProyect  = !displayPag;			
				
				nombre = (posicionInt.get(0)).getNombreCompleto();
				
				service_estado_cuenta = new ServiceEstadoCuentaIMP();
				
				service_estado_cuenta.setSesion(sesion);
				service_estado_cuenta.setProspectus_id(prospectus_id);
				service_estado_cuenta.setPosicion_SAFI(posicionInt);
				service_estado_cuenta.setPagos_SAFI(tamortizacion);
				service_estado_cuenta.setFecha_de_corte(fechaCorte);
				service_estado_cuenta.setFecha_inicio(fechaInicio);
				service_estado_cuenta.setFecha_final(fechaCorte);
				service_estado_cuenta.setComisiones(htComisiones);
				service_estado_cuenta.setVer_creditos_pagados_ENABLED(displayPag);
				
				lista_creditos               = service_estado_cuenta.getLista_creditos();			
				creditos_totales             = service_estado_cuenta.getCreditos_totales();
				contador_creditos_vigentes   = service_estado_cuenta.getContador_creditos_vigentes();
				contador_creditos_liquidados = service_estado_cuenta.getContador_creditos_liquidados();
				contador_creditos_mora       = service_estado_cuenta.getContador_creditos_mora();				
				contador_creditos_vencidos   = service_estado_cuenta.getContador_creditos_vencidos();				
			
			}	
			
			ProyectLoan proyect_loan = proyectLoanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			Long dias_transcurridos = 0L;
			
			Date d1 =   proyect_loan.getConsulting_date() ;
			Date d2 =   new Date() ;
			
			if( d1 != null ){
			
				Long ld1 = d1.getTime();
				Long ld2 = d2.getTime();
				
				dias_transcurridos = ( (ld2 - ld1) / MILLSECS_PER_DAY );
			
			}
			
			sesion.setNew_consulting(false);
			
			if( d1 == null ||  dias_transcurridos > 30 || ( dias_transcurridos < 30 && proyect_loan.getStatus_id().intValue() == 0 ) ){
			
				boolean capital_pagado_superior_al_MIN = false;
				
				flag_In_for_min_100_per = false;
				
				for( TSafiPosicionInt pos : posicionInt ){
				
					capital_pagado_superior_al_MIN = asignar_indice_de_pago( pos ) ;
					
					System.out.println( "saldo_deudor_superior_al_MIN: " + capital_pagado_superior_al_MIN);
					
					if( capital_pagado_superior_al_MIN ){
						break;
					}
				
				}
				
				if( capital_pagado_superior_al_MIN ){
					
					sesion.setNew_consulting( capital_pagado_superior_al_MIN && contador_creditos_mora == 0 && contador_creditos_vencidos == 0 );
				
				}else if( !flag_In_for_min_100_per ) {
					sesion.setNew_consulting( contador_creditos_mora == 0 && contador_creditos_vencidos == 0  && (contador_creditos_liquidados>0 || contador_creditos_vigentes > 0  ));
				}else{
					sesion.setNew_consulting( false );
				}
			
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public List<ClientViewFullName> completeinfoclient(String strSearch){
		List<ClientViewFullName> suggestions = new ArrayList<ClientViewFullName>();
		//Dependiendo del valor del checkBox sera la busqueda.
		//1:Por nombre
		if(getRadioTypeSearch() == 1){
			suggestions=clientViewFullNameService.getListClientByName(strSearch);
		}else //3:Por email
			if(getRadioTypeSearch() == 3){
			suggestions=clientViewFullNameService.getListClientByEmail(strSearch);
		}
		
		return suggestions;
		
	}
		
	public final void busca_prospectus(ActionEvent e)
	{
		FacesContext   faces;
		ELContext      elContext;	
		ELResolver     resolver;
		RequestContext request;
		
		ProspectusPK   prospectusPK;		
		SessionBean    sesion;
		ProyectLoan    proyectLoan;
		SearchSummaySession searchsum;
		
		faces   = FacesContext.getCurrentInstance();
		request = RequestContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		sesion    = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		prospectusPK = new ProspectusPK();
		prospectusPK.setProspectus_id(Integer.parseInt(getSearch()));
		prospectusPK.setCompany_id(sesion.getCompany_id());
		
		prospectus = prospectusService.getProspectusById(prospectusPK);
	
		if(prospectus != null)
		{
			prospectus_id = prospectus.getProspectusPK().getProspectus_id();
			company_id    = prospectus.getProspectusPK().getCompany_id();
			
			proyectLoan = proyectLoanService.getMaxProyectLoanByProspect(prospectus_id, company_id);
			
			if(proyectLoan != null)
			{
				String value  = proyectLoan.getProyectloanPk().getProyect_loan_id() + "::" 
							  + proyectLoan.getProyectloanPk().getProyect_id()      + "::" 
						      + proyectLoan.getProyectloanPk().getProspectus_id()   + "::" 
							  + proyectLoan.getProyectloanPk().getCompany_id();
				
				if(value != null)
				{		
					 searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
					 searchsum.setSearchSummary(value);
				}
				
				inicializaEdoCuenta();		
				
				request.addCallbackParam("isValid", true);
			} else {
				 request.addCallbackParam("isValid", false);
			}			
		} else {
			 request.addCallbackParam("isValid", false);
		}
	}
	
	public void getTicket()
	{
		
		File archivo = null; 
		
	      FileReader fr = null;
	      BufferedReader br = null;
	      StringBuilder sb = new StringBuilder();
	      
	      ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
	      
	      String detalle ="";
	     
	      
	      
//	      SystemParamPK syspk = new SystemParamPK();
//			
//			syspk.setCompany_id(sesion.getCompany_id());
//			syspk.setSystem_param_id(47); // Fecha en que kubo se constituyó como SOFIPO  
//			
//			systemparam = systemparamservice.loadSelectedSystemParam(syspk);
	      
	 
	      try 
	      {
	    	  Date fecha = formatStr2.parse("01-01-1970");
	    	  Date fechaApli = formatStr2.parse("01-01-1970");
	    	  
	    	  Hashtable<Date,Double> htDates = new Hashtable<Date,Double>();
	    	  
	    	  if(values != null){
	    	  
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	    	  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsf/docs/");
	    	  //String path = new File("Escritorio/Desarrollo_Kubo/Kubodb/WebContent/jsf/docs").getAbsolutePath();
	    	  
	    	  path += "/ticketPago.html";
	    	  
	    	  //System.out.println(path);
	    	  
	    	  archivo = new File (path);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null){
	        	sb.append(linea);
	         }
	         
	         edoHtml = sb.toString();
	         
	         gnNaturalPersonPK ntPk = new gnNaturalPersonPK();
	         
	         ntPk.setCompany_id(company_id);
			 ntPk.setProspectus_id(prospectus_id);
				
	         NaturalPerson person = gnNaturalService.getNaturalPersonById(ntPk);	         

	         List<TSafiPosicionInt> posicionInt= estadocuentaservice.getTSafiPosicionInt(prospectus_id);
					
					List<TSafiPagosCuota> tamortizacion = estadocuentaservice.getTSafiPagosCuota(prospectus_id);
		         
					TSafiPosicionInt pos  = null;
					TSafiPagosCuota amort = null;
					
					String crdt = values.split("::")[0];
					String amrt = values.split("::")[1];
					String status = values.split("::")[2];
					
					List<TSafiCreditosMovs> movs = estadocuentaservice.getTSafiCreditosMovs( Integer.parseInt( crdt ), Integer.parseInt(amrt) , prospectus_id);
					
					Hashtable<String,String> htMovs = new Hashtable<String,String>();
					
					
					
					if(movs!=null){
					
						for( TSafiCreditosMovs m : movs ){
							
							if(m.getNatMovimiento().equals("A") && m.getDecripcionOperacion().equals("PAGO DE CREDITO") ){
							
								Double d = Double.parseDouble((m.getTipoMovCreId()+"") );
								
								String res = getTitleBySafiCode( d.intValue()  );
								
								String a = htMovs.get(res);
								
								if (a==null){
									
									htMovs.put(res, m.getCantidad ()+"" );
									
								}else{
									
									Double d2 = Double.parseDouble(a)+m.getCantidad();
									
									htMovs.put(res, d2+"" );
									
								}
								
								
								 if(fecha.before( m.getFechaAplicacion() ) ){
									 
									 fecha = m.getFechaAplicacion();
									 fechaApli = m.getFechaOperacion();
									 
								 }
								 
								 
								 if( htDates.get(m.getFechaAplicacion()) == null ){
								 
									 htDates.put(m.getFechaAplicacion(),m.getCantidad());
								 
								 }else{
									 
									 Double dCantidad = htDates.get(m.getFechaAplicacion());
									 
									 dCantidad = dCantidad + m.getCantidad();
									 
									 htDates.put(m.getFechaAplicacion(),dCantidad);
									 
								 }
								 
								 
//							//System.out.println( m.getAmorticreid() +
//												"  -  " + m.getCantidad()+
//												"  -  " + m.getCreditoid()+
//												"  -  " + m.getDescripcion()+
//												"  -  " + m.getDescripcion_operacion()+
//												"  -  " + (m.getFechaactual()!=null? formatStr3.format( m.getFechaactual().getTime()):"")+
//												"  -  " + (m.getFechaaplicacion()!=null? formatStr3.format( m.getFechaaplicacion().getTime()):"") +
//												"  -  " + (m.getFechaoperacion()!=null? formatStr3.format( m.getFechaoperacion().getTime()):"") +
//												"  -  " + m.getNatmovimiento()+
//												"  -  " + m.getReferencia()+
//												"  -  " + m.getTipo_saldo()+
//												"  -  " + m.getTipomovcreid()+
//												"  -  " + m.getTransaccion()
//											);
							}
						}
						
						
						
						String[] keys =keysOrdenadas(htMovs);
						
						total=0D;
						
						for(int i = 0 ; i<keys.length;i++){
							
							Double d1 = Math.rint((Double.parseDouble(htMovs.get(keys[i])))*100)/100;
							total += d1;
							detalle += "<li><span>"+ keys[i].split("::")[1] +"</span><strong>\\$"+ (num.format(d1)) + "</strong></li> ";
							
						}
						
						
	    	  		}else{
	    	  			
	    	  			//System.out.println("El servicio regresó null");
	    	  			
	    	  		}
						
					for(TSafiPosicionInt tmpPos : posicionInt){
						
						if((tmpPos.getPk().getCreditoId()+"").equals(  crdt )){
							
							pos = tmpPos;
							break;
							
						}
						
					}
					boolean flag = false;
					int totalAmort = 0;
					for(TSafiPagosCuota tamor : tamortizacion){
						
						if((tamor.getPk().getCreditoId()+"").equals(crdt) ){
							totalAmort++;
							if((tamor.getPk().getAmortizacionId()+"").equals(amrt) && !flag ){
						
								amort = tamor;
								flag = true;;
							
							}
						}
						
					}
					
		         if(pos != null && amort !=null ){
		        	 
		        	 Ticket ticket = ticketservice.getTiketbyCreditAndAmortId( amort.getPk().getCreditoId()+"" , amort.getPk().getAmortizacionId() );
		     		
		        	 String folio = "";
		        	 
		        	 if(ticket == null){
		        		 
		        		 ticket = new Ticket();
		        		 ticket.setGeneration_date(new Date());
		        		 ticket.setPayment_number(amort.getPk().getAmortizacionId());
		        		 ticket.setSafi_credit_id(amort.getPk().getCreditoId()+"");
		        		 ticket.setStatus("0");
		        		 TicketPk ticketpk = new TicketPk();
		        		 ticketpk.setCompany_id(sesion.getCompany_id()) ;
		        		 ticket.setTicketpk(ticketpk);
		        		 
		        		 if(ticketservice.saveTicket(ticket,sesion.getProspectus_id() )){
		        			 
		        			 ticket = ticketservice.getTiketbyCreditAndAmortId( amort.getPk().getCreditoId()+"" , amort.getPk().getAmortizacionId() );
		        			 
		        			 if(ticket == null){
		        				 
		        				 folio = "NO GENERADO";
		        				 
		        			 }else{
		        				 
		        				 folio = ticket.getTicketpk().getTicket_id()+"";
		        				 
		        			 }
		        		 }else{
		        			 
		        			 folio = "NO GENERADO";
		        			 
		        		 }
		        	 }else{
		        		 
		        		 folio = ticket.getTicketpk().getTicket_id()+"";
		        		 
		        	 }
		        	 
		        	 total = Math.rint((total)*100)/100;
						
		        	 
			         edoHtml = edoHtml.replaceAll("###FOLIO###", folio);
			         
			         
			         
			         edoHtml = edoHtml.replaceAll("###CLIENTE###", (pos.getProspectoIdExt()+""));
			         
			         if(sesion.getArea().toString().equals("I") ){
			        	 
			        	 edoHtml = edoHtml.replaceAll("###NOMBRE###", "NO DISPONIBLE" );
				         edoHtml = edoHtml.replaceAll("###RFC###","NO DISPONIBLE" );
			         
			         }else{
			        	 edoHtml = edoHtml.replaceAll("###NOMBRE###", pos.getNombreCompleto());
				         edoHtml = edoHtml.replaceAll("###RFC###",person.getMx_rfc() );
			         }
			        
			         
			         
			         edoHtml = edoHtml.replaceAll("###NUMCREDITO###", (pos.getPk().getCreditoId() + "" ) );
			         edoHtml = edoHtml.replaceAll("###CUOTAS_PAGADAS###", (amort.getPk().getAmortizacionId()+""));
			         edoHtml = edoHtml.replaceAll("###TOTALCUOTAS###", totalAmort+"");
			         edoHtml = edoHtml.replaceAll("###SALDOLIQUIDAR###", "");
			         edoHtml = edoHtml.replaceAll("###OPERACION###", "PAGO DE CRÉDITO");
			         //System.out.println(detalle);
			         edoHtml = edoHtml.replaceAll("###DETALLE###",detalle);
			         String importe = dec.format(total);
			         importe = importe.replaceAll("\\$", "");
			         
			         edoHtml = edoHtml.replaceAll("###IMPORTE_PAGADO###", "\\$"+importe);
			         
			         String statusStr ="";
			         
			         if(status.indexOf("PA_")!= (-1)){
			        	 statusStr = "(PARCIAL)";
			         }
			         
			         edoHtml = edoHtml.replaceAll("###STATUS###", statusStr);
			         
			         String centavos = "00";
			         
			         String sTotal = total+"";
			         
			         String pesos = sTotal.split("\\.")[0];
			         
			         if(sTotal.split("\\.").length > 1)
			        	 centavos = sTotal.split("\\.")[1];
			         
			         if(centavos.length()<2){
			        	 centavos += "0";
			         }
			         
			         String letter = NumberToLetterConverter.convertNumberToLetter(Double.parseDouble(pesos));
			         
			         edoHtml = edoHtml.replaceAll("###IMPORTE_LETRA###", letter );
			         
			         importe = dec.format(amort.getCapital());
			         importe = importe.replaceAll("\\$", "");
			         
			         edoHtml = edoHtml.replaceAll("###CAPITAL_ORDINARIO###","\\$"+importe );
			         
			         importe = dec.format(amort.getInteres());
			         importe = importe.replaceAll("\\$", "");
			         
			         edoHtml = edoHtml.replaceAll("###INTERES_NORMAL###","\\$"+ importe );
			         edoHtml = edoHtml.replaceAll("###INTERES_PROVI###", "");
			         
			         importe = dec.format(amort.getIvaInteres());
			         importe = importe.replaceAll("\\$", "");
			         
			         edoHtml = edoHtml.replaceAll("###IVA_INTERES###","\\$"+ importe );
			         
			         importe = dec.format(total);
			         importe = importe.replaceAll("\\$", "");
			         
			         edoHtml = edoHtml.replaceAll("###TOTAL_PAGADO###","\\$"+ importe );
			         
			         String fecha21 = ( formatStr5.format( fecha ) );
			         
			         edoHtml = edoHtml.replaceAll("###FECHA_PAGO###", fecha21  );
			         
			         edoHtml = edoHtml.replaceAll("###CENTAVOS###", centavos  );
			          
			        // System.out.println(" <--------  Fechas --------> "+pos.getCreditoid());
			         
			        	
						
			         
			         
			         
			         Date[] claves = (Date[]) htDates.keySet().toArray(new Date[0]);
			         java.util.Arrays.sort(claves);
			         
			         String tranfer = "";
			         
			         
			         if( ahoMov != null && ahoMov.size()>0 ){
				         for( Date date : claves ){
				        	 
				        	 
				             //System.out.println(" Fecha: -->  "+fm1.format(date) +" , valor=  "+htDates.get(date));
				        	 
				             //service.getTSafiCuentasAhoMovDep(cuentaAhoID, fecha, natMovimiento)
				             
				             Calendar c = Calendar.getInstance();
				             
				             c.setTime(date);
				             
				             Double res = htDates.get(date);
				            
				             for( int i = 0 ; i<ahoMov.size(); i++){
									//movs2[i].getFecha(); // fecha de deposito
				            	 
				            	 if(date.compareTo(ahoMov.get(i).getFecha()) == 0 || date.after(ahoMov.get(i).getFecha()) ){
				            	 
	//								System.out.println( +"  -  "+
	//													ahoMov.get(i).getCantidadMov()+"  -  "+
	//													ahoMov.get(i).getNumeroMov()+"  -  "+
	//													ahoMov.get(i).getNombreCorto()+"  -  "+
	//													(ahoMov.get(i).getTipoDeposito().equals("E")?"DEPÓSITO EN EFECTIVO":ahoMov.get(i).getTipoDeposito().equals("T")?"SPEI":ahoMov.get(i).getTipoDeposito())
	//										
	//								);
									
									String r = (ahoMov.get(i).getTipoDeposito().equals("E")?"DEPÓSITO EN EFECTIVO":ahoMov.get(i).getTipoDeposito().equals("T")?"SPEI":ahoMov.get(i).getTipoDeposito());
									
									tranfer += "<li><span>Número de transacción:</span><strong>"+ahoMov.get(i).getNumeroMov()+"</strong></li>";
									tranfer += "<li><span>Intitución receptora:</span> <strong>"+ahoMov.get(i).getNombreCorto().toUpperCase()+" POR "+r+"</strong></li>";
									tranfer += "<li><span>Fecha de depósito: </span> <strong>"+formatStr5.format(ahoMov.get(i).getFecha())+"</strong></li>";
									
									res =  res - Double.parseDouble(ahoMov.get(i).getDeposito()) ;
									if( res <= 0 ){
				            	 		break;
				            	 	}
				            	 }
				             }
				             
								
								//System.out.println("-----------------------------------");
				             
					         
					         
				         
				         }
			         }
			         
			         edoHtml = edoHtml.replaceAll("###TRANSFERENCIA###", tranfer  );
			         
			         //System.out.println(" <------ Fin Fechas ------> ");
			         
			         String fecha2 = (formatStr3.format( fecha ));
			         
			         String fecha3 = (formatStr4.format( fechaApli ));
			         
			         edoHtml = edoHtml.replaceAll("###FECHAPAGO###", fecha2  );
			         edoHtml = edoHtml.replaceAll("###HORAPAGO###", fecha3  );
			         
			         //edoHtml = edoHtml.replaceAll("TELEFONO", recurso.getString("Kubo_telefono")  );
			         
			         if(systemparam != null ){
			        	 
			        	 
			        	 Date d2 = null;
			        	 
			        	 if( amort.getFechaLiquida() != null ){
			        		 
			        		 //d2 = fm1.parse(amort.getFechaLiquida());
			        		 d2 = amort.getFechaLiquida();
			        		 
			        	 }else{
			        		 
			        		 d2 =  fecha ;
			        		 
			        	 }
			        		 
			        	 if( fechaSofipo.after(  d2 ) ){
			        		 
			        		 // SOFOM
			        		 
			        		 edoHtml = edoHtml.replaceAll("###RAZON_SOCIAL###", recurso.getString("razon_social_kubo_sofom")  );
			        		 edoHtml = edoHtml.replaceAll("###RFC_KUBO###", recurso.getString( "rfc_kubo_sofom" )  );
			        		 
			        	 }else{ // SOFIPO
			        		 
			        		 edoHtml = edoHtml.replaceAll("###RAZON_SOCIAL###", recurso.getString("razon_social_kubo_sofipo")  );
			        		 edoHtml = edoHtml.replaceAll("###RFC_KUBO###", recurso.getString( "rfc_kubo_sofipo" )  );
			        		 
			        	 }
			        	 
			        	 edoHtml = edoHtml.replaceAll("###TELEFONO###", getTelefonoKubo());
			        	 
			         }
			         
		         }
	         }
	         
	         
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		
	}

	public void pademovilAction( ActionEvent e ){
		
		String monto = (String)e.getComponent().getAttributes().get("ammount").toString();
		
		String credit_id = (String)e.getComponent().getAttributes().get("safi_credit_id").toString();
		String proyect_id = (String)e.getComponent().getAttributes().get("proyect_id").toString();
		String proyect_loan_id = (String)e.getComponent().getAttributes().get("proyect_loan_id").toString();
		
		if( monto != null ){
			
			monto = monto.replace("$", "").replace(",", "");
			
		}else{
			
			monto = null;
			
		}
		
		String usuario_id = null;
		String descripcion = null;
		String url = null;
		String key = null;
		String urlReturn = null;
		
		SystemParamPK syspk = new SystemParamPK();
		
		syspk.setCompany_id(sesion.getCompany_id());
		
		syspk.setSystem_param_id(34); //valor para que nos traiga el valor de usuario_id;
		
		systemparam = systemparamservice.loadSelectedSystemParam(syspk);
		
		if(systemparam != null){
			usuario_id = systemparam.getValue();
		}
		
		syspk.setSystem_param_id(35); //valor para que nos traiga la descripcion
		
		systemparam = systemparamservice.loadSelectedSystemParam(syspk);
		
		if(systemparam != null){
			descripcion = systemparam.getValue();
		}
		
		syspk.setSystem_param_id(36); //valor para que nos traiga ls URL;
		
		systemparam = systemparamservice.loadSelectedSystemParam(syspk);
		
		if(systemparam != null){
			url = systemparam.getValue();
		}
		
		syspk.setSystem_param_id(37); //valor para que nos traiga la llave;
		
		systemparam = systemparamservice.loadSelectedSystemParam(syspk);
		
		if(systemparam != null){
			key = systemparam.getValue();
		}
		
		syspk.setSystem_param_id(38); //valor para que nos traiga la URL DE RETORNO;
		
		systemparam = systemparamservice.loadSelectedSystemParam(syspk);
		
		if(systemparam != null){
			urlReturn = systemparam.getValue();
		}
		
		if( usuario_id != null && descripcion != null && url != null && key != null && urlReturn != null && monto != null ){
		
			scriptPade = "<script>";
			
			scriptPade += 	"pademovilefunct();" +
							"function pademovilefunct(){" +
							"var src = 'descripcion="+descripcion+"';";// 35
					
			scriptPade += 	"	src += '&id_usuario="+usuario_id+"';"; //34
			scriptPade += 	"	src += '&prospectus_id="+sesion.getProspectus_id()+"';";
			scriptPade += 	"	src += '&url="+urlReturn+"';";
			scriptPade += 	"	src += '&pais=MX';";
			scriptPade += 	"	src += '&importe="+monto+"';";
			scriptPade += 	"	src += '&safi_credit_id="+credit_id+"';";
			scriptPade += 	"	src += '&monto="+monto+"';";
			scriptPade += 	"	src += '&proyect_id="+proyect_id+"';";
			scriptPade += 	"	src += '&proyect_loan_id="+proyect_loan_id+"';";
			
			scriptPade += 	"	var firma =   CryptoJS.HmacSHA1(src, '"+key+"');"; // 37
			
			scriptPade += 	"	var url = '"+url+"';";//36
			
			scriptPade += 	"	var myURL =  url+src+'&firma='+firma;";
			//scriptPade += 	"	alert(myURL);";
			
			scriptPade += 	" 	window.location.href=	myURL;}";
			scriptPade += "</script>";
			setDisPnlScriptPade(true);
		
		}else{
			setDisPnlScriptPade(false);
		}
		
	}
	
	private final String[] keysOrdenadas(Hashtable<String,String> ht) {
		Object[] valores = ht.keySet().toArray();
		ArrayList<String> lista = new ArrayList<String>();
		
		for (int i = 0; i < valores.length; i++) {
			lista.add((String) valores[i]);
		}
		Collections.sort(lista);
		
		Object[] valores_ordenados = lista.toArray();
		
		String[] indice = new String[valores_ordenados.length];
		for (int i = 0; i < valores_ordenados.length; i++) {
			indice[i] = (String) valores_ordenados[i];
		}
		
		return indice;
	}

	private final void inicializaMovimientos()
	{
		try
		{	
			String   mesStr;
			
			Date    compDate;
			Calendar ct1, ct2;
						
			htComisiones = new Hashtable<String,Double[]>();											
			
			if(mes < 10 )
			{
				mesStr = "0" + mes;
			} else {
				mesStr = mes + "";
			}
			
			compDate = formatStr2.parse("01-" + mesStr + "-" + getYear());
			
			fechaInicio = getPrimerDiaDelMes( compDate );			
			fechaFin    = getUltimoDiaDelMes( compDate );
			
			if(fechaCorte != null)
			{
				
				ct1 = Calendar.getInstance();
				ct2 = Calendar.getInstance();
				
				ct1.setTime(fechaCorte);
				ct2.setTime(fechaFin);
				
				if(fechaCorte.compareTo(fechaFin) <= 0)
				{
					if( ct1.get(Calendar.MONTH) == ct2.get(Calendar.MONTH) 
					&&  ct1.get(Calendar.YEAR)  == ct2.get(Calendar.YEAR))
					{
						fechaFin = fechaCorte;
					}
				}			
			}
			
			tableMovs = new ArrayList<MovsCuentaAhorro>();
			
			setComisiones(0D);
			
			for( MovsCuentaAhorro m : movimientos)
			{				
				if( m.getFecha().compareTo(fechaInicio) >= 0 && m.getFecha().compareTo( fechaFin ) <= 0 )
				{				
					tableMovs.add(m);
					
					//Comisiones
					
					//System.out.println( "Comisiones: " + m.getDescripcion().toUpperCase() );
					
					if( (m.getDescripcion().toUpperCase().indexOf("COMISI")!= (-1)) 
					&& !(m.getDescripcion().toUpperCase().indexOf("IVA ")!= (-1)))
					{						
						setComisiones(Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") ) + getComisiones() ) ;
						
						if( m.getCreditoId() != null ){
						
							Double[] dc = htComisiones.get(m.getCreditoId());
						
							if( dc != null )
							{
								if(dc[0]!=null)
								{
									dc[0] = Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") ) + dc[0];
								} else {
									dc[0] = Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") );
								}
								
								//System.out.println( "Comision Cargada if(): " + dc[0] );
								
								htComisiones.put(m.getCreditoId(), dc);
								
							} else {
								
								Double[] arrD = new Double[2];
								
								arrD[0] = Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") );
								
								//System.out.println( "Comision Cargada else: " + arrD[0] );
								
								htComisiones.put(m.getCreditoId(), arrD);							
							}	
						
						}
					}
					//Fin Comisiones
					
					//Intereses 
					
					if( (m.getDescripcion().toUpperCase().indexOf("INTERÉS")!= (-1)) 
					&& !(m.getDescripcion().toUpperCase().indexOf("IVA ")!= (-1)))
					{																		
						Double[] dc = htComisiones.get(m.getCreditoId());
						
						if( dc != null )
						{
							if(dc[1]!=null)
							{
								dc[1] = Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") ) +dc[1];
							} else {
								dc[1] = Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") );
							}
							
							htComisiones.put(m.getCreditoId(),dc);
							
						} else {
							
							Double[] arrD = new Double[2];
							
							arrD[1] = Double.parseDouble( m.getMonto().replace("$", "").replace(",", "") );
							
							htComisiones.put(m.getCreditoId(), arrD);							
						}
						
					}//Fin Intereses															
				}				
			}
			
			Collections.sort(tableMovs); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Date getPrimerDiaDelMes( Date d ) {
        
		Calendar cal = Calendar.getInstance();
        
		cal.setTime( d );
		
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        
        return cal.getTime();
        
    }
			 
	public static Date getUltimoDiaDelMes( Date d ) {
        
		Calendar cal = Calendar.getInstance();
        
		cal.setTime( d );
        
		cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        
        return cal.getTime();
        
    }
	
	public void pruebaCLick(){
		System.out.println("----------------Click----------------");
	}
		
	public final void registrar_consulta(ToggleEvent event)
	{		
		panel_OPENED = event.getVisibility().name().equals("VISIBLE");		
		
		service_referencia_pago.registrar_consulta(panel_OPENED);
		
	}
	
	public final String registrar_consulta_liquidacion(){
		
		System.out.println( " SAFI_CREDIT_ID1: " + credit_id );
		
		service_referencia_pago.registrar_consulta_liquidacion();
		
		sesion.setCredito_SAFI(credit_id);
		
		saldo_liquidar = saldo_liquidar.replace("$", "");
		saldo_liquidar = saldo_liquidar.replaceAll(",", "");
		
		
		sesion.setSaldo_liquidacion(saldo_liquidar);
		
		System.out.println( " SAFI_CREDIT_ID: " + sesion.getCredito_SAFI() + "  --  " + sesion.getSaldo_liquidacion() );
		
		return "QUIERO_LIQUIDAR_MI_PRESTAMO";
	}
	
	
	private boolean asignar_indice_de_pago( TSafiPosicionInt       posicion ) 
	{
		Double monto_credito          = ((posicion.getMontoCredito())    == null ? 0 : posicion.getMontoCredito());
		Double saldo_capital_vigente  = ((posicion.getSaldoCapVigent())  == null ? 0 : posicion.getSaldoCapVigent());		
		Double saldo_capital_atrasado = ((posicion.getSaldoCapAtrasad()) == null ? 0 : posicion.getSaldoCapAtrasad());
		Double saldo_capital_vencido  = ((posicion.getSaldoCapVencido()) == null ? 0 : posicion.getSaldoCapVencido());
		Double saldo_capital_vencido_no_exigible = ((posicion.getSaldoCapVenNoExi()) == null ? 0 : posicion.getSaldoCapVenNoExi());
		
		Double saldo_capital = saldo_capital_vigente + saldo_capital_atrasado + saldo_capital_vencido + saldo_capital_vencido_no_exigible;
		
		boolean saldo_deudor_superior_al_MIN = false;
		
		if(saldo_capital < monto_credito)
		{
			//montopagado                  //falta_por_pagar    
			Double saldo_deudor = monto_credito - saldo_capital;
			
			//porcentaje_pagado      //montopagado
			Double indice_saldo_capital_pagado =  (saldo_deudor / monto_credito) * 100;
			
			//porcentaje_de_monto_por_pagar
			Double indice_saldo_deudor  = 100 - indice_saldo_capital_pagado;
			
			
			
			System.out.println( " monto_credito: " + monto_credito + "indice_saldo_deudor_MIN:  "  + indice_saldo_deudor_MIN + " indice_saldo_capital: " + indice_saldo_capital_pagado + " indice_saldo_deudor: " + indice_saldo_deudor);
			
			if( Math.round( indice_saldo_capital_pagado ) != 100L ){
			
				flag_In_for_min_100_per = true;
				
				    //porcentaje_pagado
				if(indice_saldo_capital_pagado >= indice_saldo_deudor_MIN)
				{
					saldo_deudor_superior_al_MIN = true;
					
				} else {
					
					saldo_deudor_superior_al_MIN = false;
				}
			
			}
			
			//setPorc_pag( (double) (indice_saldo_capital));
			
		}
		
		return saldo_deudor_superior_al_MIN;
		
	}
	
	
}