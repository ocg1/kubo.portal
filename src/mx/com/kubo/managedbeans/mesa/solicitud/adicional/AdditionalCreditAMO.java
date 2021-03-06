package mx.com.kubo.managedbeans.mesa.solicitud.adicional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RestructureBean;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.tools.Utilities;

public abstract class AdditionalCreditAMO extends AdditionalCreditDMO
{					
	protected void asignar_flag_no_publish() 
	{
		lista_proyect_loan = proyectloanService.getProyectLoanByProspectDontPublish(prospectus_id , company_id);
		
		if( lista_proyect_loan == null || lista_proyect_loan.size() == 0)
		{
			setFlagnopublish(false);
			
		} else {
			
			setFlagnopublish(true);
		}	
	}
	
	protected void asignar_lista_pagos()
	{			
		try
		{																			
			pagadas    = 0;			
			vigentes   = 0;			
			inactivos  = 0;			
			atrasadas  = 0;			
			dias_atras = 0;
			/*
			locator = new WsSgbRiskServiceLocator();		
			service_SGB = locator.getWsSgbRisk();
		
			safi_lista_pagos      = service_SGB.getTSafiPagosCuota(prospectus_id  + "");			
			safi_lista_posiciones = service_SGB.getTSafiPosicionInt(prospectus_id + "");
			*/
			safi_lista_pagos      = estadocuentaservice.getTSafiPagosCuota(prospectus_id );			
			safi_lista_posiciones = estadocuentaservice.getTSafiPosicionInt(prospectus_id );
			
			
			lista_safi_pagos = new ArrayList<TSafiPagosCuota>();
			
			posicion = null;
			
			if(safi_lista_posiciones != null)
			{
				for(int i = 0 ; i < safi_lista_posiciones.size(); i++)
				{				
					safi_posicion_credito_id = safi_lista_posiciones.get(i).getPk().getCreditoId();
					
					if(proyectloan_safi_credit_id == safi_posicion_credito_id)
					{
						posicion = safi_lista_posiciones.get(i);
						break;
					}				
				}
			}
						
			for(int i = 0 ; i < safi_lista_pagos.size() ; i++)
			{				
				safi_pago_credito_id = safi_lista_pagos.get(i).getPk().getCreditoId();
				safi_pago_estatus    = safi_lista_pagos.get(i).getEstatus();
				
				if(proyectloan_safi_credit_id == safi_pago_credito_id)
				{					
					if(safi_pago_estatus.equals("I") )
					{						
						inactivos++;						
					}
					
					if(safi_pago_estatus.equals("P"))
					{						
						pagadas++;						
					}
					
					if(safi_pago_estatus.equals("V"))
					{						
						vigentes++;						
					}
					
					if(safi_pago_estatus.equals("B"))
					{						
						vencidas++;						
					}
					
					if(safi_pago_estatus.equals("A"))
					{						
						atrasadas++;
																	
						d1 =  safi_lista_pagos.get(i).getFechaExigible() ;
						d2 =  new Date() ;
						
						ld1 = d1.getTime();
						ld2 = d2.getTime();
						
						dias_de_atraso = ( (ld2 - ld1) / MILLSECS_PER_DAY );
						
						if ( dias_de_atraso > getDias_atras() )
						{
							dias_atras = dias_de_atraso.intValue();
						}
					}
					
					lista_safi_pagos.add(safi_lista_pagos.get(i));					
				}				
			}
									
//			if(getPagadas() > 0)
//			{				
//				setPorc_pag( (double) ((getPagadas() * 100) / lista_safi_pagos.size()));			
//			}
			
			if(atrasadas > 0 || vencidas > 0)
			{
				asignar_reestructura_credito(proyect_loan);		
				asignar_indice_de_pago();
			}						
									
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void asignar_SGB_risk_get_resume() 
	{
		buro_resume = getBuroResume(prospectus_id + "","0", bur_sol_num);

		if(buro_resume != null)
		{								
			asignar_fecha_consulta();				 							
		}	
	}
	
	private void asignar_reestructura_credito(ProyectLoan proyectloan) throws ParseException 
	{
		setRestructure(true);
		
		restructurebean = new RestructureBean();
		
		restructurebean.setProyect(proyectloan);
		
		if(posicion.getFechaInicio() != null)
		{					
			fec = formatStr1.format(  posicion.getFechaInicio() );
			restructurebean.setFechaInicio(fec);
		}
		
		if(posicion.getFechaVencimien() != null)
		{					
			fec = formatStr1.format(  posicion.getFechaVencimien()  );
			restructurebean.setFechaFin( fec );					
		}
		
		syspk = new SystemParamPK();
		
		syspk.setCompany_id(proyectloan.getProyectloanPk().getCompany_id());
		syspk.setSystem_param_id(14); // verifica si esta habilitada la funcion de Pago por pademovil  
									
		systemparam = getSystemparamservice().loadSelectedSystemParam(syspk);
		
		iva = Double.parseDouble(systemparam.getValue() == null ? "0.16" : systemparam.getValue());
		
		saldo_por_liquidar = 0D;
		
		saldo_capital_vigente     = ((posicion.getSaldoCapVigent()) ==  null ? 0 : posicion.getSaldoCapVigent());
		saldo_interes_provi       = ((posicion.getSaldoInterProvi()) == null ? 0 : posicion.getSaldoInterProvi());
		saldo_capital_atrasado    = ((posicion.getSaldoCapAtrasad()) == null ? 0 : posicion.getSaldoCapAtrasad());
		saldo_interes_atrasado    = ((posicion.getSaldoInterAtras()) == null ? 0 : posicion.getSaldoInterAtras());
		saldo_moratorios          = ((posicion.getSaldoMoratorios()) == null ? 0 : posicion.getSaldoMoratorios());
		saldo_comision_falta_pago = ((posicion.getSaldoComFaltaPago()) == null ? 0 : posicion.getSaldoComFaltaPago());
		
		ivaIp = (saldo_interes_provi        * iva); 
		ivaIa = (saldo_interes_atrasado     * iva);
		ivaM  = (saldo_moratorios           * iva);
		ivaC  = (saldo_comision_falta_pago  * iva);
		
		saldo_por_liquidar = saldo_capital_vigente 
						   + saldo_interes_provi 
						   + saldo_capital_atrasado 
						   + saldo_interes_atrasado 
						   + saldo_moratorios 
						   + saldo_comision_falta_pago 
						   + ivaIp + ivaIa + ivaM + ivaC + 15;
		
		
		restructurebean.setSaldoLiquidar(saldo_por_liquidar);		
	}
	
	private void asignar_indice_de_pago() 
	{
		monto_credito          = ((posicion.getMontoCredito())    == null ? 0 : posicion.getMontoCredito());
		saldo_capital_vigente  = ((posicion.getSaldoCapVigent())  == null ? 0 : posicion.getSaldoCapVigent());		
		saldo_capital_atrasado = ((posicion.getSaldoCapAtrasad()) == null ? 0 : posicion.getSaldoCapAtrasad());
		saldo_capital_vencido  = ((posicion.getSaldoCapVencido()) == null ? 0 : posicion.getSaldoCapVencido());
		saldo_capital_vencido_no_exigible = ((posicion.getSaldoCapVenNoExi()) == null ? 0 : posicion.getSaldoCapVenNoExi());
		
		saldo_capital = saldo_capital_vigente + saldo_capital_atrasado + saldo_capital_vencido + saldo_capital_vencido_no_exigible;
		
		if(saldo_capital < monto_credito)
		{
			//montopagado                  //falta_por_pagar    
			saldo_deudor = monto_credito - saldo_capital;
			
			//porcentaje_pagado      //montopagado
			indice_saldo_capital =  (saldo_deudor / monto_credito) * 100;
			
			//porcentaje_de_monto_por_pagar
			indice_saldo_deudor  = 100 - indice_saldo_capital;
			
			syspk = new SystemParamPK();
			
			syspk.setCompany_id(company_id);
			syspk.setSystem_param_id(60);   
										
			systemparam = getSystemparamservice().loadSelectedSystemParam(syspk);
			
			indice_saldo_deudor_MIN = Double.parseDouble(systemparam.getValue() == null ? "25" : systemparam.getValue());
			
			    //porcentaje_pagado
			if(indice_saldo_capital >= indice_saldo_deudor_MIN)
			{
				saldo_deudor_superior_al_MIN = true;
				
			} else {
				
				saldo_deudor_superior_al_MIN = false;
			}
			
			setPorc_pag( (double) (indice_saldo_capital));
			
		}
	}

	protected void asignar_fecha_consulta()
	{
		fecha_consulta = Long.parseLong(buro_resume.getFecConsulta());
		
		conv = new ConvertCalendar(fecha_consulta);
		
		 try
		 {					 
			 dFecConsult = conv.getDate();
			 
			 setFecConsult(formatStr.format(dFecConsult));
			 
			 objUtility = new Utilities();
			 setDiasTrans(objUtility.diasTrans(dFecConsult, new Date()));					 					
			 
		 } catch(Exception e) { 
			 e.printStackTrace();
		 }
	}
	
	protected void asignar_documentacion()
	{
		faces    = FacesContext.getCurrentInstance();
		external = faces.getExternalContext();
		
		prospectus_id   = getProyectloan().getPerson().getNatPerPK().getProspectus_id();
		company_id      = getProyectloan().getPerson().getNatPerPK().getCompany_id();		
		proyect_loan_id = getProyectloan().getProyectloanPk().getProyect_loan_id();
		
		htCategFile = new Hashtable<String, List<DocumentationDMO>>();		
		
		lista_files_loaded = filesService.getListFilesByProspectOrderByCategory(prospectus_id, company_id, proyect_loan_id);
		
		docBean = null;
		
		 for (Files file : lista_files_loaded) 
		 {			 			 
			 category_id = file.getFileType().getFile_category_id();
			 
			 if(category_id == 8)
			 {
				 continue;
			 }			 			 
			 
			 real_path      = external.getRealPath("//resources//") + file.getLocation();
			 
			 formatDocument = file.getLocation().substring(file.getLocation().lastIndexOf(".") + 1);			 
			 prospectus_id  = file.getFilesPk().getProspectus_id();
			 company_id     = file.getFilesPk().getCompany_id();			 
			 file_type      = file.getFileType().getName();
			 url_img        = file.getLocation();			 
			 file_type_id   = file.getFilesPk().getFile_type_id();			 
			 file_id        = file.getFilesPk().getFile_id();			 
			 category_name  = file.getFileType().getFileCategory().getName();
			 
			 lista_documentacion = htCategFile.get(category_name);
			 
			 if(lista_documentacion != null)
			 {				 
				 docBean   = new DocumentationDMO();
				 				 
				 docBean.setProspectus_id(prospectus_id);
				 docBean.setCompany_id(company_id);
				 
				 docBean.setDescription(file_type);
				 docBean.setUrlImg(url_img);
				 
				 docBean.setOriginalPathImg(real_path);
				 docBean.setFormatFile(formatDocument);
				 
				 docBean.setTypeFile(file_type_id);
				 docBean.setFile_id(file_id);
				 docBean.setProyect_loan_id(proyect_loan_id);
				 
				 lista_documentacion.add(docBean);
				 
				 htCategFile.put(file.getFileType().getFileCategory().getName(), lista_documentacion);
				 
			 } else {
				 
				 lista_documentacion  =  new ArrayList<DocumentationDMO>();
				 
				 docBean = new DocumentationDMO();				 				 				 				 
				 
				 docBean.setProspectus_id(prospectus_id);
				 docBean.setCompany_id(company_id);
				 
				 docBean.setDescription(file_type);
				 docBean.setUrlImg(url_img);
				 
				 docBean.setOriginalPathImg(real_path);
				 docBean.setFormatFile(formatDocument);
				 
				 docBean.setTypeFile(file_type_id);
				 docBean.setFile_id(file_id);						 
				 docBean.setProyect_loan_id(proyect_loan_id);
				 
				 lista_documentacion.add(docBean);
				 
				 htCategFile.put(file.getFileType().getFileCategory().getName(), lista_documentacion);				 
			 }
		 }
		
		 claves    = htCategFile.keySet();
		 listFiles = new ArrayList<FilesTypeCategoryBean>();
		 
		 fileCateg = null;
		 
		 for(String clave : claves)
		 {			 
			 fileCateg = new FilesTypeCategoryBean(clave, htCategFile.get(clave));
			 
			 listFiles.add(fileCateg);			
		 }		 		
	}
			
	protected void copiar_archivos() 
	{
		if(getSelectedItems() != null)
		{				
			valores = getSelectedItems().split("MM");
			
			error_al_mover = false;
			
			for(FilesTypeCategoryBean file : listFiles)
			{					
				for(DocumentationDMO doc : file.getListFiles())
				{							
					solicitado = true;
					
					for(String val : valores )
					{					
						String document_id_selected = doc.getFile_id()  + "::" 
													+ doc.getTypeFile() + "::" 
													+ doc.getProspectus_id();
						
						String log_msg = "valor: " + val + " doc: "  + document_id_selected;
						
						System.out.println(log_msg);
						
						if(val.equals(document_id_selected))
						{							
							solicitado = false;							
						}								
					}
					
					if(solicitado)
					{								
						error_al_mover = reasignador.copiar_archivos(doc);
					}						
				}
				
				if(error_al_mover)
				{
					break;
				}					
			}
			
			if(error_al_mover)
			{					
				setRenderDocuments(false);
				setRenderError(true);
				setRenderSuccess(false);
				
			} else {
				
				setRenderDocuments(false);
				setRenderError(false);
				setRenderSuccess(true);					
			}
		
		} else {
			
			System.out.println("sin datos seleccionados");
			
		}
	}
}
