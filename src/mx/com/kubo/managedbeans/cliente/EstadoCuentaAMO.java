package mx.com.kubo.managedbeans.cliente;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.xml.rpc.ServiceException;

import mx.com.kubo.bean.CuentasAhoMovDep;
import mx.com.kubo.bean.MovsCuentaAhorro;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TicketConfig;
import mx.com.kubo.portal.reader.ParameterReaderIMP;
import mx.com.kubo.tools.Utilities;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiPagosCuota;

public class EstadoCuentaAMO extends EstadoCuentaDMO
{
	protected boolean  asignarSesion()
	{
		faces  = FacesContext.getCurrentInstance();
		
		resolver   = faces.getApplication().getELResolver();
		elContext  = faces.getELContext();					
		
		reader = new ParameterReaderIMP();
		reader.setFaces(faces);
		reader.init_sesion();
		
		if(reader.getAccess_from() == null)
		{		
			accessFromURL = false;				
			
			sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		} else {
			
			accessFromURL = true;						
			
			credit_id = reader.getCredit_id();
			sesion    = reader.getSesion();
		}
		
		if(sesion == null || sesion.getArea() == null ||  sesion.getCompany_id() == null ||  sesion.getProspectus_id() == null ){
			
			ExternalContext external   = faces.getExternalContext();
			
			try 
			{
		        external.redirect("expired.jsf");
		        return false;
		        
			} catch (IOException ex) {
			       ex.printStackTrace();
			       return false;
			}
			
		}else{
			
				System.out.println( "******* SESSION ESTADO DE CUENTA ACREDITADO 1 *******" );
				System.out.println( "******* USER AGENT: "+ sesion.getUser_agent() +" *******" );
				System.out.println( "***************************************************" );
					
		}
		
		return true;		
	}
	
	protected void asignarHeadless()
	{
		System.setProperty("java.awt.headless", "true");
	}
	
	protected boolean verificarSesion()
	{							
		if(sesion == null || (sesion.getCompany_id() == null && sesion.getProspectus_id() == null))
		{			
			external   = faces.getExternalContext();
			
			try 
			{
		        external.redirect("expired.jsf");
		        return false;
			} catch (IOException ex) {
			       ex.printStackTrace();
			       return false;
			}			
		}else{
			System.out.println( "******* SESSION ESTADO DE CUENTA ACREDITADO 2 *******" );
			System.out.println( "******* USER AGENT: "+ sesion.getUser_agent() +" *******" );
			System.out.println( "***************************************************" );
			return true;
			
		}
	}
	
 	protected void asignarFechaSOFIPO()
	{
//		syspk.setCompany_id(sesion.getCompany_id());
//		syspk.setSystem_param_id(14); //valor para que nos traiga el valor del iva;
//		
//		
//		SystemParamPK syspk = new SystemParamPK();
		
		systemParam_PK.setCompany_id(sesion.getCompany_id());
		// Fecha en que kubo se constituyó como SOFIPO
		systemParam_PK.setSystem_param_id(47);   
		
		systemparam = systemparamservice.loadSelectedSystemParam(systemParam_PK);
	}
	
	protected void asignarRazonSocial()
	{		
		try
		{			
			fechaSofipo = formatStr2.parse(systemparam.getValue());			
			recurso     = ResourceBundle.getBundle("Message.MessageResourceBundle");

			if(fechaSofipo.after(new Date()))
			{       		 
	       		 // SOFOM	       		 
	       		 setRazonSocial(recurso.getString("razon_social_kubo_sofom"));
	       		 setRfcKubo(recurso.getString( "rfc_kubo_sofom" ));	       		 
	       	 } else { // SOFIPO	       		 
	       		setRazonSocial(recurso.getString("razon_social_kubo_sofipo"));
	       		setRfcKubo(recurso.getString( "rfc_kubo_sofipo" ));	       		
	       	 }
			
			setTelefonoKubo(recurso.getString("Kubo_telefono"));
			
		} catch(Exception e) {			
			e.printStackTrace();			
		}
	}
	
	protected void asignarProspectoFromSesion()
	{
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		setFont_size("1.0");		
	}
	
	protected boolean asignarProspectusFromAutocomplete() 
	{
		boolean bandera = false;
		
		setFont_size("0.8");
		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		
		String value = searchsum.getSearchSummary();
		
		searchsum.setPerson(false);
		
		if(value != null)
		{
			//value=proyectLoan.getProyectloanPk().getProyect_loan_id()+"::"+proyectLoan.getProyectloanPk().getProyect_id()+"::"+proyectLoan.getProyectloanPk().getProspectus_id()+"::"+proyectLoan.getProyectloanPk().getCompany_id();
			
			String valueS[] = value.split("::");
			
			if(valueS != null && valueS.length > 3)
			{				
				prospectus_id = Integer.parseInt(valueS[2]);
				company_id    = Integer.parseInt(valueS[3]);
				
				if(prospectus_id != null && company_id != null )
				{					
					bandera = true;
					
					MenuControlTableBean menu = (MenuControlTableBean) FacesContext.getCurrentInstance()
			                .getApplication().getELResolver()
			                .getValue(elContext, null, "menuControlTableBean");
					
					
					MembershipPK mpk = new  MembershipPK();
					
					mpk.setCompany_id ( Integer.parseInt( value.split("::")[3] ) );
					mpk.setProspectus_id ( Integer.parseInt( value.split("::")[2] ) );
					
					Membership member = membershipService.getMembershipById(mpk);
					
					if( member.getIs_canceled() != null && member.getIs_canceled().trim().length()>0 && !member.getIs_canceled().equals("N") ){
						
						menu.setProspectus_is_canceled( true );
						menu.setCanceledReason(member.getIs_canceled());
						
					}else{
						
						menu.setProspectus_is_canceled( false );
						menu.setCanceledReason("");
						
					}
					
				}
			}		
		}	
		
		return bandera;
	}
	
	protected void asignarFactorMora()
	{			
		systemParam_PK = new SystemParamPK();		
		systemParam_PK.setCompany_id(sesion.getCompany_id());
		
		// Veces que se multiplica la tasa, para obtener la tasa moratoria
		systemParam_PK.setSystem_param_id(48); 
		
		systemparam = systemparamservice.loadSelectedSystemParam(systemParam_PK);
		
		if(systemparam != null)
		{			
			factorMora = Integer.parseInt( systemparam.getValue());			
		}
	}
	
	protected void asignarRestructure()
	{
		systemParam_PK = new SystemParamPK();
		
		systemParam_PK.setCompany_id(sesion.getCompany_id());
		systemParam_PK.setSystem_param_id(33); // verifica si esta habilitada la funcion de Reestructuras
		
		systemparam = systemparamservice.loadSelectedSystemParam(systemParam_PK);
		
		if(systemparam != null)
		{		
			if(systemparam.getValue() != null && systemparam.getValue().equals("S"))
			{				
				setRestructure(true);				
			}
		}
	}
	
	protected void asignarPademovil()
	{
		systemParam_PK = new SystemParamPK();
		
		systemParam_PK.setCompany_id(sesion.getCompany_id());
		systemParam_PK.setSystem_param_id(32); // verifica si esta habilitada la funcion de Pago por pademovil  
		
		systemparam = systemparamservice.loadSelectedSystemParam(systemParam_PK);
		
		if(systemparam != null )
		{
			if( sesion.getArea().toString().equals("L") )
			{			
				if(systemparam.getValue()!= null && systemparam.getValue().equals("S"))
				{				
					setPademovil(true);
				} else {
					try
					{
						if(Integer.parseInt( systemparam.getValue() ) == sesion.getProspectus_id())
							setPademovil(true);
					}catch(Exception e){
						e.printStackTrace();
						//System.out.println("No es un numero");
					}
				}
			}		
		}	
	}
	
	protected void asignarFecha()
	{
		 if(mes == null)
		 {
			Calendar c1 = Calendar.getInstance();			
			SimpleDateFormat frmTmp3 = new SimpleDateFormat("MMMM", new Locale("ES"));
			
			mesStr = (frmTmp3.format(c1.getTime()));
	        mesStr = mesStr.substring(0, 1).toUpperCase() + (mesStr.substring(1, mesStr.length()).toLowerCase());
			
			setMes((c1.get(Calendar.MONTH)) + 1);			
			setYear(c1.get(Calendar.YEAR));
		 }
	}
	
	protected void asignarProspecto()
	{
		ProspectusPK pk = new ProspectusPK();
		
		pk.setCompany_id(company_id);
		pk.setProspectus_id(prospectus_id);
		
		prospectus = prospectusService.getProspectusById(pk);
	}
	
	protected boolean isImagenValida()
	{
		return prospectus.getImage() != null &&  prospectus.getImage().length() > 0;
	}
	
	protected void asignarImagen()
	{
		photo = "documents/cia_"
				+ prospectus.getProspectusPK().getCompany_id()
				+ "/pros_"
				+ prospectus.getProspectusPK().getProspectus_id()
				+ "/photo/" + prospectus.getImage();
		
		if(!isDirectory(photo))
		{
			photo = "img/sinimagen.jpg";
		}
	}
	
	protected void asignarMovimientos() throws RemoteException, ParseException, ServiceException
	{
		String cuentaAho = "";
		String inst      = "";
		
		Address          address;		
		MovsCuentaAhorro movimiento;
																
		List<TicketConfig>  lst;
		List<SavingAccount> cuentaAhoLst;
		
		Hashtable<String,String> htCredTotalCuotas;

		address    = direccionService.getMaxAddressByType(prospectus_id, company_id, CASA);			
		addressStr = getAddressString(address);	
		addressStr = Utilities.capilizeString(addressStr);
		
		list_proyect_loan = servicioProyecto.getProyectLoanListByProspectus(prospectus_id, company_id);
		
		
							
		//tamortizacion = web_service_SGB.getTSafiPagosCuota(prospectus_id + "");
		
		tamortizacion = estadocuentaservice.getTSafiPagosCuota(prospectus_id);
					
		lst = ticketconfigservice.getTicketConfigLst();
		
		setLstTicketConfig(lst);
		
		Calendar c = estadocuentaservice.getFechaCorte();
		
		if( c != null )
		{			
			//System.out.println(c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));			
			fechaCorte = formatStr2.parse( c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));			
		}
		
		/* Movimientos de la cuenta de ahorro */
		cuentaAhoLst = savingaccountservice.getListAccountActiveByProspect(prospectus_id, company_id);	         	        
         
         if(cuentaAhoLst != null && cuentaAhoLst.size() > 0)
         {	         
        	 cuentaAho = (cuentaAhoLst.get(0)).getSafi_account_id();	         
         }
		
         movimientos = new ArrayList<MovsCuentaAhorro>();	         
		 ahoMov      = new ArrayList<CuentasAhoMovDep>();
    	 
		 movs2 = null;
		 
		 if(cuentaAho != null && cuentaAho.trim().length() > 0)
		 {								 
			 movs2 = estadocuentaservice.getTSafiCuentasAhoMovDep( Integer.parseInt( cuentaAho ) );
			 
			 if(movs2 != null)
			 {						
				for( int i = 0 ; i< movs2.size(); i++)
				{														
					if( movs2.get(i) != null )
					{	
						//System.out.println( "  MOVIMIENTO: " + movs2[i].getNatMovimiento() + " - " + movs2[i].getDescripcionMov() );
						
						if( movs2.get(i).getNatMovimiento().equals("A") )
						{								
							ahoMov.add( new CuentasAhoMovDep( movs2.get(i) ) );								
						}
							
						if( 
									movs2.get(i).getDescripcionMov() != null 
								&&  movs2.get(i).getNatMovimiento()  != null 
								&& (
										( 
													movs2.get(i).getNatMovimiento().equals("A") 
												&&  movs2.get(i).getDescripcionMov().equals("DEPOSITO A CREDITO") 
										) 
										||  
										( 
													movs2.get(i).getNatMovimiento().equals("C") 
												&& 	(movs2.get(i).getDescripcionMov().indexOf("COMISI") != (-1) )
										) 
								)
								
						)
						{	
							//System.out.println( " ASIGNANDO MOVIMIENTO: " + movs2[i].getDescripcionMov() );
							
							movimiento = new MovsCuentaAhorro();								
							movimiento.setDescripcion(movs2.get(i).getDescripcionMov());
							movimiento.setFecha( movs2.get(i).getFecha());
														
							if( movs2.get(i).getNombreCorto() != null )
							{									
								inst = movs2.get(i).getNombreCorto().toUpperCase() 
									 + " POR " + movs2.get(i).getTipoDeposito() == null ? "" : movs2.get(i).getTipoDeposito().equals("E") ? "DEPÓSITO EN EFECTIVO" : movs2.get(i).getTipoDeposito().equals("T")?"SPEI" : "";										
							} else {									
								inst = "";
							}
								
							movimiento.setInstitucion_receptora(inst);
							movimiento.setMoneda(movs2.get(i).getMoneda());
							movimiento.setMonto(dec.format(movs2.get(i).getCantidadMov() ));
							movimiento.setNaturaleza(movs2.get(i).getNatMovimiento());
							movimiento.setOrder(1);
							  
							
							if( movs2.get(i).getDescripcionMov() != null ){
								
								String[] descMov =  movs2.get(i).getDescripcionMov().split(" - ");
								
								if( descMov != null && descMov.length>1 ){
									
									String cr = descMov[1];
									
									if( cr != null && cr.trim().length() > 0 ){
										movimiento.setCreditoId( cr );
									}
									
								}
								
								
							}
							
							
							movimientos.add(movimiento);	
							
							
							}							
						}							
					}
					
					movs = estadocuentaservice.getTSafiCreditosMovs( null, null, (prospectus_id ) );
					
					htCredTotalCuotas = new Hashtable<String,String>();
					
					if( movs != null )
					{
						for( TSafiCreditosMovs m : movs)
						{								
							if(m != null && m.getNatMovimiento()!=null && m.getNatMovimiento().equals("A") && m.getDescripcion() != null && m.getDescripcion().equals("PAGO DE CREDITO") )
							{								
								movimiento = new MovsCuentaAhorro();
								
								String r = getTitleBySafiCode(m.getTipoMovCreId());
								
								String numCuotas = htCredTotalCuotas.get(m.getCreditoId());
								
								if( numCuotas == null )
								{
									numCuotas = getTotalCuotas((m.getCreditoId()+""),htCredTotalCuotas,tamortizacion);
								}
								
								String desc = "PAGO DE " + r.split("::")[1]
											+ " <br /> (Cuota " + m.getAmorticreId()
											+ " de " + numCuotas
											+ " Crédito " + m.getCreditoId()+ ")"; 
								
								movimiento.setDescripcion( desc );									
								movimiento.setOrder( Integer.parseInt( r.split("::")[0] ));									
								movimiento.setFecha( m.getFechaAplicacion());									
								movimiento.setInstitucion_receptora("");
								movimiento.setMoneda( m.getDescripcion());
								movimiento.setMonto(dec.format(m.getCantidad()));
								movimiento.setNaturaleza("C");
								movimiento.setCreditoId( m.getCreditoId()+"");
								
								movimientos.add(movimiento);
							}
						}						
					}
					
					Collections.sort(ahoMov,Collections.reverseOrder());						
				}				 
		 }	         
         /* fin de Movimientos de la cuenta de ahorro */
	}
	
	protected void registrarAcceso()
	{
		access = new Access();
		
		access.setScreen_id(21);
		access.setPercentage(0);
		access.setCompany_id          (sesion.getCompany_id());
		access.setProspectus_id       (sesion.getProspectus_id());
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setOp_system           (sesion.getOsbrawser());
		access.setVertical_size       (sesion.getBrowser_width());
		access.setHorizontal_size     (sesion.getBrowser_height());
		access.setIpaddress           (sesion.getIP_address_client());		
		access.setUser_agent          (sesion.getUser_agent());
		access.setDevice_info         (sesion.getDevice_info());	
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access.add(access, true);
	}
	
	protected void asignarCalendario() 
	{
		Calendar calendar;
		calendar = Calendar.getInstance();
		
		for (int i = 0; i < monthStr.length; i++) 
		{
			months.add("" + monthStr[i]);
		}

		years.add(calendar.get(Calendar.YEAR) + "");
		
		calendar.add(Calendar.YEAR, (-1));
		
		years.add(calendar.get(Calendar.YEAR) + "");
	}
	
	private String getAddressString(Address direccion)
	{		
		String addressStr = "";
		
		if(direccion.getStreet()!=null&&direccion.getStreet().length()>0)
		{
			addressStr += " "+direccion.getStreet();
		}
		
		if( direccion.getAddress_number() != null
		&&  direccion.getAddress_number().length() > 0 
		&& !direccion.getAddress_number().equals("0") )
		{
			addressStr += " "+direccion.getAddress_number();
		}
		
		if( direccion.getApt_number() != null
		&&  direccion.getApt_number().length() > 0 
	    && !direccion.getApt_number().equals("0"))
		{
			addressStr += " número interior "+direccion.getApt_number();
		}
		
		if( direccion.getMx_manzana()!=null
		&&  direccion.getMx_manzana().length() > 0 
		&& !direccion.getMx_manzana().equals("0"))
		{
			addressStr += " manzana "+direccion.getMx_manzana();
		}
		
		if( direccion.getMx_lote()!=null
		&&  direccion.getMx_lote().length() > 0 
		&& !direccion.getMx_lote().equals("0"))
		{
			addressStr += " lote "+direccion.getMx_lote();
		}
		
		if(direccion.getNeighborhood()!=null)
		{
			addressStr += "<br /> "+direccion.getNeighborhood().getName()+"<br />";
		}
		
		if(direccion.getZip_code() != null
		&& direccion.getZip_code().length() > 0)
		{
			addressStr += " "+direccion.getZip_code()+"";
		}
		
		if(direccion.getTownCat() != null)
		{
			if(direccion.getStateCat()!=null
			&& direccion.getStateCat().getStateCatPK().getState_id() == 9)
			{
				addressStr += " "+direccion.getTownCat().getName();
			} else {
				addressStr += " "+direccion.getTownCat().getName();
			}
		}
		
		if(direccion.getStateCat()!=null)
		{
			addressStr += ", "+direccion.getStateCat().getName();
		}
					
		return addressStr;	
	}
	
	protected String getTitleBySafiCode(Integer saficode)
	{		
		String res = "";
		
		if(saficode != null && thisList !=null )
		{		
			for(TicketConfig t : thisList)
			{			
				if(t.getPk().getSafi_code() == saficode )
				{					
					res = t.getTickettitle().getOrder_title()
						+ "::"
						+ t.getTickettitle().getName();
					break;					
				}				
			}			
		}
		
		return res;	
	}
	
	public final String getTotalCuotas(String creditoid, Hashtable<String,String> htCredTotalCuotas,List<TSafiPagosCuota> posicionInt)
	{					
		String numCuotas = "";
		int totalAmort = 0;
		
		for(TSafiPagosCuota amort : posicionInt)
		{			
			if((amort.getPk().getCreditoId()+"").equals(creditoid))
			{
				totalAmort++;
			}			
		}
		
		htCredTotalCuotas.put(creditoid, (totalAmort + ""));
		
		return numCuotas;		
	}
	
	protected void init_bandera_renovacion(){
		
		systemParam_PK = new SystemParamPK();
		
		systemParam_PK.setCompany_id(1);
		systemParam_PK.setSystem_param_id(97); 
		
		systemparam = systemparamservice.loadSelectedSystemParam(systemParam_PK);
		
		indice_saldo_deudor_MIN = Double.parseDouble(systemparam.getValue() == null ? "75" : systemparam.getValue());
		
		
		systemParam_PK = new SystemParamPK();
		
		systemParam_PK.setCompany_id(1);
		systemParam_PK.setSystem_param_id(98); 
		
		systemparam = systemparamservice.loadSelectedSystemParam(systemParam_PK);
		
		boolean ren_4_click_enabled =  systemparam.getValue().equals( "S" );
		
		sesion.setEnabled_Ren_4_Click(ren_4_click_enabled) ;
		
		
	}
	
	private boolean isDirectory(String other)
	{
		String hacia = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/resources/");
		String path= hacia+"/"+other;
		File file = new File(path);
				
		if(file.exists())
		{
			Hashtable<String, Integer> ht = getDimImg(file);
		
			setImgWidth((Integer)ht.get("Width") +"");
			setImgHeight((Integer)ht.get("Height") +"");
		} else {
			setImgWidth("150");
			setImgHeight("150");
		}
				
		return  file.exists();						
	}
	
	private Hashtable<String, Integer> getDimImg(File fOrigen) 
	{
		try 
		{
			BufferedImage img = ImageIO.read(fOrigen);
			int xac = (int) img.getWidth();
			int yac = (int) img.getHeight();

			int x = 150;
			int y = 150;
			double resx = 0;
			double resy = 0;
			//double resxy = 0;
			if (xac > x) {
				resx = x / (double) xac;
				double yy = resx * (double) yac;
				y = (int) yy;
				
				//resxy = resx;
			} 
			
			if (yac > y) {
				resy = y / (double) yac;
				double xx = resy * (double) xac;
				x = (int) xx;
				//resxy = resy;
			}
			Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
			ht.put("Height", y);
			ht.put("Width", x);
			return ht;
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
	}
		
}
