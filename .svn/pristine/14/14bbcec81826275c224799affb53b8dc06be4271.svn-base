package mx.com.kubo.managedbeans.buro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import com.soa.model.businessobject.Vtbur_infocredcte_c;
import com.soa.model.businessobject.Vtbur_infocredcte_m;
import com.soa.model.businessobject.Vtbur_infocredcte_vig;
import com.soa.model.businessobject.Vtbur_infocte;

public class SaldoDeudas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
	
	Hashtable<Date,  Hashtable<String, Double> > htGrp = new Hashtable<Date,  Hashtable<String, Double> >(); 
	
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	
	private String scriptStr ="";
	
	private Date fecConsul = null;
	
	private SimpleDateFormat fm1 	  		= new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat fm2 	  		= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	
	private TreeSet<String>  cuentas 		= null; 

	public SaldoDeudas(){
		
	}
	
	public String calculaGráfica( Vtbur_infocredcte_c[]   infocredcte_c,Vtbur_infocredcte_m[]   infocredcte_m, Vtbur_infocredcte_vig[] infocredcte_vig,Vtbur_infocte infocte ){
		try{
			
		
			fecConsul = fm2.parse( infocte.getFecha_consulta() );
			
			String fecCon = fm1.format(fecConsul);
			
			cuentas = new TreeSet<String>(); 
			htGrp = new Hashtable<Date, Hashtable<String, Double> >(); 
			
			Double limit 	= 0D;
			Double saldo 	= 0D;
			int cuentasRev	= 0;
			
			//System.out.println("cerrados mas de 6 meses");
			
			if( infocredcte_m != null){
			
				for ( Vtbur_infocredcte_m cerrado : infocredcte_m){
					
					//System.out.println("Cerrados en elos ultimos 6 meses: freq - "+cerrado.getIdfrecuencia_pagos());
					
					if( cerrado!=null && cerrado.getIdfrecuencia_pagos()!=null && !cerrado.getIdfrecuencia_pagos().trim().toUpperCase().equals( "Z" ) ){
						
						if(cerrado.getFec_cierre() !=null && cerrado.getFec_cierre().trim().length()>0 )
						{
							
							Calendar c1 = Calendar.getInstance();
							
							c1.setTime(fm2.parse(cerrado.getFec_cierre()));
							
							c1.add(Calendar.DATE, (-1));
							
							insertaenHt(cerrado.getFecha_apertura(),fm2.format( c1.getTime() ),cerrado.getLimite_credito(), cerrado.getCredito_maximo(), cerrado.getMonto_a_pagar(), cerrado.getNumero_pagos(),cerrado.getOtorgante() );
						}
						
					}
					
				}
			
			}
			
			//System.out.println("cerrados");
			if( infocredcte_c != null ){
				for ( Vtbur_infocredcte_c cerrado : infocredcte_c){
					
					//System.out.println("Cerrados en elos ultimos 6 meses: freq - "+cerrado.getIdfrecuencia_pagos());
					
					if( cerrado!=null &&  cerrado.getIdfrecuencia_pagos()!=null && !cerrado.getIdfrecuencia_pagos() .toUpperCase().equals( "Z" ) ){
						
						if(cerrado.getFec_cierre() !=null)
						{
							
							Calendar c1 = Calendar.getInstance();
							
							c1.setTime(fm2.parse(cerrado.getFec_cierre()));
							
							c1.add(Calendar.DATE, (-1));
							
							insertaenHt(cerrado.getFecha_apertura(),fm2.format( c1.getTime() ),cerrado.getLimite_credito(), cerrado.getCredito_maximo(), cerrado.getMonto_a_pagar(), cerrado.getNumero_pagos(),cerrado.getOtorgante() );
						}
					}
					
				}
			}
			
			
			
			//System.out.println("vigentes");
			SimpleDateFormat fm3 	  		= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			
			if( infocredcte_vig != null ){
				for ( Vtbur_infocredcte_vig vig : infocredcte_vig){
					
					//System.out.println("Cerrados en elos ultimos 6 meses: freq - "+vig.getIdfrecuencia_pagos());
					
					if( vig!=null &&  vig.getIdfrecuencia_pagos()!=null && !vig.getIdfrecuencia_pagos().toUpperCase().equals( "Z" ) ){
						
						Date d = calculaFecFin( vig );
						if(d != null){
							
							insertaenHt(vig.getFecha_apertura(), fm3.format(d), vig.getLimite_credito(), vig.getCredito_maximo(), vig.getMonto_a_pagar(), vig.getNumero_pagos(),vig.getOtorgante() );
							
						}else{
							//System.out.println( vig.getFecha_apertura()+"  -  No definida " );
						}
					}
					
					if( vig.getIdtipo_contrato().equals("CC") || vig.getIdtipo_contrato().equals("CL") ){
						
						limit += Double.parseDouble( (vig.getLimite_credito()==null)?"0":vig.getLimite_credito() ) ;
						saldo += Double.parseDouble( (vig.getSaldo_actual()==null)?"0":vig.getSaldo_actual() );
						
						cuentasRev++;
						
					}
				}
				
			}
			
			Set<Date> keys = htGrp.keySet();
			
			ArrayList<Date> lista = new ArrayList<Date>();
			
			for( Date f : keys ){
				lista.add(f);
			}
			
			Collections.sort(lista);
			SimpleDateFormat fm1 	  		= new SimpleDateFormat("dd/MM/yyyy");
			
			Hashtable<Date,Hashtable<String,Double>> htMensual = new Hashtable<Date,Hashtable<String,Double>>();
			
			int i = 0;
			
			while (i <  lista.size()) {
				
				String mes = fm1.format(lista.get(i));
				
				Hashtable<String,Double> htmax = new Hashtable<String,Double>();
				
				String mesT = "";
				
				do{
					
					Hashtable<String,Double> ht1= htGrp.get(lista.get(i));
					Set<String> keysTem = ht1.keySet();
					
					for( String s : keysTem){
						Double max = htmax.get(s);
						Double maxT = ht1.get(s);
						boolean f = false;
						if(max==null){
							max=0D;
							
						}
						
						if(maxT==null){
							maxT=0D;
							f = true;
						}
						
						if(maxT>max){
							
							max = maxT;
							htmax.put(s,max);
							
						}else if(!f && (Double.compare(maxT, 0D) == 0) ){
							//System.out.println("Entro: "+s+"  - "+lista.get(i)+"  - "+maxT);
							htmax.put(s,maxT);
						}
					}
					
					i++;
					
					if(lista.size()>i){
						
						mesT = fm1.format( lista.get(i) );
						
					}else{
						
						mesT = "    ";
						
					}
					
					
					
				}while(mesT.substring(2).equals(mes.substring(2)));
				
				Date dT = getPrimerDiaDelMes(fm1.parse(mes));
				
				htMensual.put(dT, htmax);
				
			}
			
			keys = htMensual.keySet();
			
			lista = new ArrayList<Date>();
			
			for( Date f : keys ){
				lista.add(f);
			}
			
			Collections.sort(lista);
			
			int num = 0;
			Calendar c = Calendar.getInstance();
			
			ArrayList<Date> lstTmp = new ArrayList<Date>();
			
			for(Date f : lista){
				lstTmp.add(f);
			}
			
			for(Date f : lstTmp){
				
				if( num == 0 ){
					c.setTime(f);
					num++;
					continue;
				}
				do{
					c.add(Calendar.MONTH, 1);
				
					if(f.compareTo(c.getTime())!=0){
						lista.add( c.getTime() );
					}
					
				}while(f.compareTo(c.getTime())!=0);
				
				
			}
			
			Collections.sort(lista);
			
//			scriptStr = "<script>var dataArea = google.visualization.arrayToDataTable([";
//			scriptStr += "['Fecha' ";

			scriptStr = "<script>\nvar dataArea = new google.visualization.DataTable();";
			
			scriptStr += "\ndataArea.addColumn('string', 'Mes');";
			
			
			
			for( Iterator<String> it = cuentas.iterator(); it.hasNext(); ) {
				String account = (String) it.next();
				
				scriptStr += "\ndataArea.addColumn('number', '"+account+"');";
				scriptStr += "\ndataArea.addColumn({type: 'string', role: 'tooltip', 'p': {'html': true}});";
				scriptStr += "\ndataArea.addColumn({type: 'boolean', role: 'scope'});";
				
				} 
			
			if( saldo > 0 ){
				
				scriptStr += "\ndataArea.addColumn('number', 'REVOLVENTES');";
				scriptStr += "\ndataArea.addColumn({type: 'string', role: 'tooltip', 'p': {'html': true}});";
				scriptStr += "\ndataArea.addColumn({type: 'boolean', role: 'scope'});";
				
			}
			
			scriptStr += "\ndataArea.addColumn({type:'number', role:'interval'});  \n ";
			scriptStr += "\ndataArea.addColumn({type:'number', role:'interval'});  \n ";
			
			scriptStr += "\ndataArea.addColumn({type:'string', role:'annotation'});  \n ";
			scriptStr += "\ndataArea.addColumn({type:'string', role:'annotationText', 'p': {'html': true}}); \n ";
			
			scriptStr += "\ndataArea.addRows([";
			
			scriptStr += "\n";
			boolean in = false;
			boolean flagConsul = true;
			boolean flagRev=false;
			if(lista.size()>0){
				Double maxMonto = 0D;
				for(Date d : lista){
					
					Hashtable<String,Double>  htem = htMensual.get(d);
					
					if(htem != null){
						
						if(in){
							scriptStr += ",";
							
						}else{
							in = true;
						}
						
						String fec = fm1.format(d);
						
						scriptStr += "['"+fec+"'";
						int ix = 0;
						int total= 0;
						
						int ultimo= 0;
						
						for( Iterator<String> it = cuentas.iterator(); it.hasNext(); ) {
							String account = (String) it.next();
							
							Double res = htem.get( account );
							
							if( res != null ){
							
								scriptStr += ",  "+res.intValue();
								
								total = total + res.intValue();
								
								ultimo = res.intValue();
								
								if(ix == 0){
									
									scriptStr += ", createCustomHTMLDiagCntnt('"+dec.format(res.intValue())+"','###TOTAL###')";
									
								}else{
									scriptStr += ", createCustomHTMLDiagCntnt('"+dec.format(res.intValue())+"','-')";
								}
								
								
								if(d.after(fecConsul))
									scriptStr += ", false";
								else
									scriptStr += ", true";
								ix++;
								
							}else{
								scriptStr += ",  null,null,null";
							}
							
						}
						
						
						
						if(total>maxMonto){
							maxMonto = Double.parseDouble( total+"" );
						}
						
					
						if(fec.split("/")[1].equals(fecCon.split("/")[1]) && fec.split("/")[2].equals(fecCon.split("/")[2]) && flagConsul){
							
							if( saldo > 0 ){
								scriptStr += ","+saldo+"";
								scriptStr += ",createCustomHTMLDiagCntnt('"+dec.format(saldo)+"','-')";
								scriptStr += ",true";
								total = total+saldo.intValue();
								flagRev = true;
							}
							
							int lineCons = ((Double)((maxMonto-(total-ultimo)+2000))).intValue();
							if(lineCons<0)
								lineCons = lineCons*(-1);
							
							scriptStr += ",0,"+lineCons;
							flagConsul = false;
							//System.out.println("Monto Maximo: "+(maxMonto-(total-ultimo)+2000));
							scriptStr += ",'K',customHTMLDiagCnsltCnt('"+fecCon+"')";
							
						}else{
							if( flagRev) {
								
								scriptStr += ","+saldo+"";
								scriptStr += ",createCustomHTMLDiagCntnt('"+dec.format(saldo)+"','-')";
								scriptStr += ",true";
								total = total+saldo.intValue();
								flagRev = false;
							
							}else{
								if( saldo > 0 ){
									scriptStr += ",null,null,null";
								}
							}
							scriptStr += ",null,null,null,null";
						}
						
						scriptStr = scriptStr.replace("###TOTAL###", dec.format(total ));
						
						
						scriptStr += "]";
						scriptStr += "\n";
					}else{
						
						scriptStr += ",['"+fm1.format(d)+"'";
						
						for( Iterator<String> it = cuentas.iterator(); it.hasNext(); ) {
							it.next();
							scriptStr += ",  null,null,null";
							
						}
						if( saldo > 0 ){
							scriptStr += ",null,null,null";
						}
						
						scriptStr += ",null,null,null,null";
						
						scriptStr += "]";
						scriptStr += "\n";
					}
					
					flagConsul = true;
				}
				
				scriptStr += "]);</script>";
				
			}else{
				scriptStr = "<script>dataArea=null;</script>";
			}

			System.out.println(scriptStr);
			
			return scriptStr;
			
		}catch( Exception e ){
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public Date calculaFecFin(Vtbur_infocredcte_vig vig){
		Date fechaVencimiento = null;
		
		try{
		
			SimpleDateFormat fm3 	  		= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");	
			
			 if(vig.getFecha_apertura() != null){
					 int dias = 0;
					 if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("QUINCENAL") != (-1) ){
						dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 15 ;
					 }
					 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("MENSUAL") != (-1) ){
						dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 30 ;
					 }
					 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("CATORCENAL") != (-1) ){
						 dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 14 ;
					 }
					 else if(vig.getFrecuencia_pagos()!=null && vig.getFrecuencia_pagos().toUpperCase().indexOf("SEMANAL") != (-1) ){
						dias = ( Integer.parseInt( ((vig.getNumero_pagos() == null)?"0": vig.getNumero_pagos()))) * 7 ;
					 }
							 
					 Calendar fec1 = Calendar.getInstance();
					 fec1.setTime(fm3.parse(vig.getFecha_apertura()));
					 
					 fec1.add(Calendar.DATE, (dias-1));
					 
					 //Se obtiene la fecha de vencimiento
					 if(dias>0){
					
						 fechaVencimiento = fec1.getTime();
					 					 
					 }
					 
			 }
			 
			 return fechaVencimiento;
			 
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
			 
	}
	
	public void insertaenHt(String fecIni,String fecfin,String limiteCredito, String creditoMax, String monto_A_Pagar, String numPagos,String otorgante){
		
		try{
			
			Date dI = fm2.parse(fecIni);
			
			//dI = getPrimerDiaDelMes(dI);
			
			//System.out.println("Otorgante: "+otorgante+" Fecha-Inicio: "+fecIni+" Fecha-Fin: "+fecfin+" Monto a pagar: "+monto_A_Pagar);
			
			Double monto1 = 0D;
			
			Double monto = 0D;
			
			
			if( (limiteCredito == null || limiteCredito.equals("0")) ){
				
				monto1 =  (creditoMax == null ? 0D : Double.parseDouble( creditoMax ) );
				
			}else{
				
				monto1 = Double.parseDouble( ( limiteCredito == null ? "0" : limiteCredito )) ;
				
			}
			
			Hashtable<String,Double> htGrpOtor = htGrp.get(dI);
			
			if(htGrpOtor != null){
			
				monto = htGrpOtor.get(otorgante);
				
				
				if(monto!=null){
					monto += monto1;
				}else{
					monto = monto1;
				}
			
			}else{
				
				htGrpOtor = new Hashtable<String,Double> ();
				
				monto = monto1;
			}
			
			htGrpOtor.put(otorgante, monto);
			
			htGrp.put(dI,htGrpOtor);
			
			int dias =  diasEntreFechas(dI, fm2.parse( fecfin ));
			
			Double factorDescuentoDiario = (monto1/dias);
			
			//System.out.println(credito+"  "+fm1.format(dI) + "  -  " + fecfin +" monto: "+monto1+"  acumulado: "+monto);
//			System.out.println("Inicio: "+otorgante+" -- "+fm1.format(dI) + "  -  " + fecfin +" monto: "+monto1+"  acumulado: "+monto+"  factor descuento: "+factorDescuentoDiario+" dias: "+dias);
			while( ( dI.compareTo( fm2.parse( fecfin ) ) ) < 0 ){
				
				Calendar tmp = Calendar.getInstance();
				tmp.setTime(dI);
				tmp.add(Calendar.DATE, 1);
				
				monto1 = monto1 - factorDescuentoDiario;
				
				Hashtable<String,Double> HT_tmp  = htGrp.get(tmp.getTime());
				if(HT_tmp != null){
				
					monto = HT_tmp.get(otorgante);
					
				}else{
					
					monto=null;
					HT_tmp = new Hashtable<String,Double>();
					
				}
					
				
				
				if(monto != null ){
					
					monto += monto1;
					HT_tmp.put(otorgante,monto);
					
				}else{
					
					HT_tmp.put(otorgante,monto1);
					
				}
				
				htGrp.put( tmp.getTime(),HT_tmp);
				
				dI = tmp.getTime();
				
				//System.out.println(fm1.format(dI) + "  -  " + fecfin +" monto: "+monto1+"  acumulado: "+monto);
				
			 }
			
			Calendar c2 = Calendar.getInstance();
			c2.setTime(fm2.parse( fecfin ));
			c2.add(Calendar.MONTH, 1);
			
			Date nDate = c2.getTime();
			
			Date nDate2 = getPrimerDiaDelMes( nDate );
			
			Hashtable<String,Double> HT_tmp = htGrp.get(nDate2);
			
			if(HT_tmp == null){
			
				HT_tmp = new Hashtable<String,Double>();
				
				HT_tmp.put(otorgante,0D);
				
				htGrp.put( nDate2,HT_tmp);
			
			}else{
				
				if( HT_tmp.get(otorgante) == null ){
					
					HT_tmp = new Hashtable<String,Double>();
					
					HT_tmp.put(otorgante,0D);
					
					Calendar c3 = Calendar.getInstance();
					c3.setTime(nDate2);
					c3.add(Calendar.DATE, 3);
					
					nDate2 = c3.getTime();
					
					htGrp.put( nDate2,HT_tmp);
					
					//System.out.println("Insertando: "+otorgante+ "  --  0D  --  "+ nDate2 );
					
				}
				
			}
			
//			System.out.println("Fin: "+otorgante+" -- "+fm1.format(dI) + "  -  " + nDate2 +" monto: "+monto1+"  acumulado: "+monto);
//			System.out.println("");
			
			cuentas.add(otorgante);
			
		}catch(Exception e ){
			
			e.printStackTrace();
			
		}
		
	}
	
	 public Date getPrimerDiaDelMes( Date date) {
		 
		 Calendar cal = Calendar.getInstance();
		 
		 //System.out.println("entra: "+fm1.format(date));
		 
		 cal.setTime(date);
		 
		 cal.set(cal.get(Calendar.YEAR),
		 	                cal.get(Calendar.MONTH),
		 	                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
		 	                cal.getMinimum(Calendar.HOUR_OF_DAY),
		 	                cal.getMinimum(Calendar.MINUTE),
		 	                cal.getMinimum(Calendar.SECOND));
		 
		 //System.out.println("regresa: "+fm1.format(cal.getTime()));
		 
		 	        return cal.getTime();
		 	    }

	
	public Integer diasEntreFechas( Date fecIni , Date fecFin ){
		
		Long diferenciaDias = ( fecFin.getTime() - fecIni.getTime() )/MILLSECS_PER_DAY; 
		
//		long diferenciaMes = diferenciaDias/30;
//		
//		Double l = Math.ceil( Double.parseDouble( diferenciaMes + "" ) );
		
		return diferenciaDias.intValue();
		
		
	}
	 
	public Hashtable<Date, Hashtable<String, Double> > getHtGrp() {
		return htGrp;
	}

	public void setHtGrp(Hashtable<Date, Hashtable<String, Double> > htGrp) {
		this.htGrp = htGrp;
	}

	public String getScriptStr() {
		return scriptStr;
	}
	
	

	public void setScriptStr(String scriptStr) {
		this.scriptStr = scriptStr;
	}
	
}
