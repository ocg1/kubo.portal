package mx.com.kubo.managedbeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.datamodels.KivaClientDataModel;
import mx.com.kubo.model.ClientCarteraKiva;
import mx.com.kubo.model.PipelineHistory;
import mx.com.kubo.model.ReportLog;
import mx.com.kubo.model.ReportLogPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.ViewPipeline;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;

import com.csvreader.CsvWriter;
import com.soa.model.businessobject.TSafiKiva;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;


@ManagedBean @ViewScoped
public class CreateReport extends CreateReportDMO
implements Serializable
{
	private static final long serialVersionUID = -4687484528541699060L;


	@PostConstruct
	public void init()
	{
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		reportTypeList = reportTypeService.getReportTypeList();
		fileExist = false;
		loadReport();
	}

	
	public void loadReport()
	{
		if(reportSelected == 1)
		{
			//Probando bandera para que se inicialice por cada que se genere un archivo a descargar
			fileExist = false;
			loadCtesKiva();
		}else if(reportSelected == 2){
			fileExist = true;
		}else if(reportSelected == 3){
			fileExist = false;
			dateHistoryPipelineList = null;
			dateHistoryPipelineList = pipelineHistoryService.getPeriodoList();
		}
		
	}
	
	public void switchTrueFileExist(){
		fileExist = true;
		System.out.println("Entroo:-"+fileExist+ "-");
	}
	
	public void loadCtesKiva(){
		kivaCarteraList = new ArrayList<ClientCarteraKiva>();
		kivaCarteraList = clientCarteraKivaService.getClientCarteraKivaListByIsKuboPropertyByIsNotReportLog(2);
		carteraKivaModel = new KivaClientDataModel(kivaCarteraList);
	}
	
	public void delegateCSVFile() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		//report_type_id 1 = Reporte kiva
		if(reportSelected == 1 && kivaCarteraSelectedList != null && kivaCarteraSelectedList.length != 0){
			try {
				fileExist = false;
				WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
				WsSgbRisk service = locator.getWsSgbRisk();

				TSafiKiva[] arKiva = null;
				//Ruta de la carpeta donde se guardaran todos los .csv
				String zipPath = realPath+"/documents/reports/cartera_kiva_temp/kiva_"+fm.format(new Date());
				//Bandera que se activa cuando se crea la carpeta donde estaran los csv y se valida para crear el zip y saber que existe la carpeta
				boolean flagCreateZip = false;
				
				ctesNotFound = new ArrayList<ClientCarteraKiva>();
				for (ClientCarteraKiva obj : kivaCarteraSelectedList) {
					
					ServiceCalling srvCall = new ServiceCalling();
					try{
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(1);
						srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.getTSafiKiva");
						srvCall.setProspectus_id(obj.getProspectus_id());
						srvCall.setStatus(1);
						
						servicecallingService.saveServiceCall(srvCall);
							
						arKiva = service.getTSafiKiva(obj.getSafi_credit_id());
						
						if(arKiva != null && arKiva.length > 0){
							srvCall = new ServiceCalling();
							srvCall.setAcces_datetime(new Date());
							srvCall.setCompany_id(sesion.getCompany_id());
							srvCall.setInfo("Regresando satisfactoriamente del servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.getTSafiKiva");
							srvCall.setProspectus_id(obj.getProspectus_id());
							srvCall.setStatus(2);
							servicecallingService.saveServiceCall(srvCall);
						}else
						{
							srvCall = new ServiceCalling();
							srvCall.setAcces_datetime(new Date());
							srvCall.setCompany_id(sesion.getCompany_id());
							srvCall.setInfo("Regresando Sin datos del servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.getTSafiKiva:"+arKiva);
							srvCall.setProspectus_id(obj.getProspectus_id());
							srvCall.setStatus(2);
							servicecallingService.saveServiceCall(srvCall);
						}
					
					}catch(Exception e){
						e.printStackTrace();
						
						srvCall = new ServiceCalling();
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(1);
						srvCall.setInfo("Error al invocar WsSgbRiskServiceLocator.getWsSgbRisk.saveServiceCall");
						srvCall.setProspectus_id(obj.getProspectus_id());
						srvCall.setStatus(3);
						srvCall.setException(e.getMessage());
						servicecallingService.saveServiceCall(srvCall);
					}
					
					if(arKiva != null && arKiva.length > 0){
						String documentPath = "/documents/cia_1/pros_" + obj.getProspectus_id() + "/reports/kiva";
						//Crea un directorio para cada cliente donde se almacenara los .csv
						Utilities.createDirectory(realPath+documentPath);
						File csvFileTemp = new File(realPath+documentPath+"/kiva_" + obj.getSafi_credit_id()+ ".csv");
						if(!csvFileTemp.exists()){
							if(generateCSVFileKiva(realPath+documentPath+"/kiva_" + obj.getSafi_credit_id()+ ".csv", arKiva)){
								ReportLogPK reportLogPK = new ReportLogPK(0, obj.getCompany_id());
								reportLogPK.setCompany_id(sesion.getCompany_id());
								ReportLog reportLog = new ReportLog(reportLogPK, reportSelected,  obj.getProyect_loan_id(), obj.getProyect_id(), obj.getProspectus_id(), obj.getSafi_credit_id(), sesion.getProspectus_id(), '1', new Date());
								reportLogService.add(reportLog);
							}else{
								ReportLogPK reportLogPK = new ReportLogPK(0, obj.getCompany_id());
								ReportLog reportLog = new ReportLog(reportLogPK, reportSelected, obj.getProyect_loan_id(), obj.getProyect_id(), obj.getProspectus_id(), obj.getSafi_credit_id(), sesion.getProspectus_id(), '3', new Date());
								reportLogService.add(reportLog);
							}
								
						}
						
						//Crea un directorio para copiar todos los archivos csv seleccionados
						if(Utilities.createDirectory(zipPath)){
							Utilities.copyFile(zipPath+"/"+csvFileTemp.getName(), new FileInputStream(csvFileTemp));
							flagCreateZip = true;
						}
						
					}else{
						ctesNotFound.add(obj);
						requestContext.addCallbackParam("isFound", "N");
					}
					
				}
				
				if(flagCreateZip){
					//Se le pasa la carpeta de los archivos, la ruta y nombre del archivo .zip a crear.
					if(Utilities.createZip(zipPath+"/", zipPath+".zip")){
						//Se manda la ruta para que se eliminen los archivos de cierto periodo
						fileDelete(realPath+"/documents/reports/cartera_kiva_temp");
						fileExist = true;
						pathStreamFile = zipPath+".zip";
						//Carga el stream a la variable de instancia para que pueda ser descargado
						fileDownloadController(pathStreamFile);
					}
				}
				
				//fileDelete(realPath+"/documents/reports/");
			} catch (Exception e) {
				e.printStackTrace();
			}
			loadCtesKiva();
		}
		//Inicia la creacion del reporte de tuberia ON LINE
		else if(reportSelected == 2){
			try {
				//fileExist = false;
				
				//Ruta de la carpeta donde se guardaran todos los .csv
				String pathCSVTempFiles = realPath+"/documents/reports/pipeline_online_temp";
				//El nombre del archivo deber tener la sig estructura (xxxXxxx_fecha). Para que sea eliminado automaticmente por el metodo fileDelete(pathCSVTempFiles); 
				String  pathCSVFile = pathCSVTempFiles+"/kuvoPipelineOnLine_" + fm.format(new Date()) + ".csv";
				
				List<ViewPipeline> viewPipelineList = viewPipelineService.getViewPipeline();
				
				if(viewPipelineList != null && viewPipelineList.size() >0){
					Utilities.createDirectory(pathCSVTempFiles);
					//Se manda la ruta para que se eliminen los archivos de cierto periodo
					fileDelete(pathCSVTempFiles);
					if(generateCSVFilePipelineOnLine(pathCSVFile, viewPipelineList)){
						fileExist = true;
						pathStreamFile = pathCSVFile;
						//Carga el stream a la variable de instancia para que pueda ser descargado
						fileDownloadController(pathStreamFile);
					}else{
						System.out.println("***** Eror al generar el archivo de tuberia on line "+ "kuvoPipelineOnLine_" + fm.format(new Date()) + ".csv *****");
					}
				}else{
					requestContext.addCallbackParam("dataIsEmpty", "S");
				}
			}catch(Exception e){
				e.printStackTrace();
				requestContext.addCallbackParam("isError", "S");
			}
			
		}
		
		//Inicia la creacion del reporte para el HISTORICO DE TUBERIA
		else if(reportSelected == 3){
			try {
				//fileExist = false;
				
				//Ruta de la carpeta donde se guardaran todos los .csv
				String pathCSVTempFiles = realPath+"/documents/reports/pipeline_history_temp";
				//El nombre del archivo deber tener la sig estructura (xxxXxxx_fecha). Para que sea eliminado automaticmente por el metodo fileDelete(pathCSVTempFiles);
				String  pathCSVFile = pathCSVTempFiles+"/kuvoPipelineHistory_" + fm.format(new Date()) + ".csv";
				
				if(datePipelineHistorySelected != null){
					List<PipelineHistory> pipelineHistoryList = pipelineHistoryService.getPipelineHistoryByPeriodo(fm1.parse(datePipelineHistorySelected));
					
					if(pipelineHistoryList != null && pipelineHistoryList.size() > 0){
						Utilities.createDirectory(pathCSVTempFiles);
						//Se manda la ruta para que se eliminen los archivos de cierto periodo
						fileDelete(pathCSVTempFiles);
						if(generateCSVFilePipelineHistory(pathCSVFile, pipelineHistoryList)){
							fileExist = true;
							pathStreamFile = pathCSVFile;
							//Carga el stream a la variable de instancia para que pueda ser descargado
							fileDownloadController(pathStreamFile);
						}else{
							System.out.println("***** Eror al generar el archivo del historico de tuberia"+ "kuvoPipelineHistory_" + fm.format(new Date()) + ".csv *****");
						}
					}else{
						requestContext.addCallbackParam("dataIsEmpty", "S");
					}
				}else{
					requestContext.addCallbackParam("dateIsEmpty", "S");
				}
			}catch(Exception e){
				requestContext.addCallbackParam("isError", "S");
				e.printStackTrace();
			}
			
		}
		
		else//Envia mensaje de alerta para seleccionar un reporte
		requestContext.addCallbackParam("isSelected", "N");
		
		
	}
	
	//Asigna el archivo a descargar a la variable de tipo stream.
	public void fileDownloadController(String pathFileToDowload) {
		InputStream stream;
		try {
			if(reportSelected == 1){
				stream = new FileInputStream(new File(pathFileToDowload));
				streamFile = new DefaultStreamedContent(stream, "zip", "ReporteKiva_"+fm3.format(new Date()));
			}else if(reportSelected == 2){
				stream = new FileInputStream(new File(pathFileToDowload));
				//Lo que importa en el constructor es el streamedContent y el nombre del archivo.
				streamFile = new DefaultStreamedContent(stream, "csv", "Kubo_Pipeline_Online"+fm3.format(new Date())+".csv");
			}else if(reportSelected == 3){
				stream = new FileInputStream(new File(pathFileToDowload));
				//Lo que importa en el constructor es el streamedContent y el nombre del archivo.
				streamFile = new DefaultStreamedContent(stream, "csv", "Kubo_Pipeline_History"+fm3.format(new Date())+".csv");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
    }
	
	//Genera el archivo csv
	 private boolean generateCSVFileKiva(String sFileName, TSafiKiva[] arSafi)
	   {
		try
		{
			CsvWriter csvOutput = new CsvWriter(new FileWriter(sFileName, true), ',');
			csvOutput.write("credito_id");
            csvOutput.write("Fecha_exigible");
            csvOutput.write("Capital");
            csvOutput.write("Interes");
            csvOutput.endRecord();
			
            for(TSafiKiva objSafi : arSafi){
            	csvOutput.write(objSafi.getCreditoId());
		    	csvOutput.write(fm2.format(objSafi.getFechaexigible().getTime()));
		    	csvOutput.write(objSafi.getCapital());
		    	csvOutput.write(objSafi.getInteres());
		    	csvOutput.endRecord();
		    }
            
            csvOutput.close();
            return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		} 
	}
	
	 //Genera reporte de tuberia ON LINE
	 private boolean generateCSVFilePipelineOnLine(String sFileName, List<ViewPipeline> viewPipelineList){
		try{
			CsvWriter csvOutput = new CsvWriter(new FileWriter(sFileName, true), ',');
			
			csvOutput.write("prospectus_id");
			csvOutput.write("proyect_loan_id");
			csvOutput.write("nombre");
			csvOutput.write("edad");
			csvOutput.write("sexo");
			csvOutput.write("medio_contacto");
			csvOutput.write("otro_medio_contacto");
			csvOutput.write("promotor");
			csvOutput.write("recomendado_por");
			csvOutput.write("nivel_estudios");
			csvOutput.write("estado_civil");
			csvOutput.write("regimen_matrimonial");
			csvOutput.write("es_comerciante");
			csvOutput.write("es_empleado");
			csvOutput.write("actividad_economica");
			csvOutput.write("colonia");
			csvOutput.write("fecha_registro");
			csvOutput.write("activo");
			csvOutput.write("fecha_activacion");
			csvOutput.write("intentos");
			csvOutput.write("ultimo_intento");
			csvOutput.write("fecha_consulta");
			csvOutput.write("bc_score");
			csvOutput.write("kubo_score");
			csvOutput.write("folio_consulta");
			csvOutput.write("icc");
			csvOutput.write("estatus");
			csvOutput.write("tipo_credito");
			csvOutput.write("dia_publicacion");
			csvOutput.write("monto_actual");
			csvOutput.write("monto_ultima_simulacion");
			csvOutput.write("producto");
			csvOutput.write("destino");
			csvOutput.write("safi_credit_id");
			csvOutput.write("monto_credito");
			csvOutput.write("fecha_inicio");
			csvOutput.write("fecha_vencimiento");
			csvOutput.write("tasa");
			csvOutput.write("frecuencia");
			csvOutput.write("no_cuotas");
			csvOutput.write("monto_cuota");
			csvOutput.write("estatus_credito");
			csvOutput.write("saldo_cap_vigente");
			csvOutput.write("saldo_cap_atrasado");
			csvOutput.write("saldo_cap_vencido");
			csvOutput.write("cat");
			csvOutput.write("dias_atraso");
			csvOutput.endRecord();
			
			for(ViewPipeline objPipeline :viewPipelineList){
				
				csvOutput.write(objPipeline.getProspectus_id()!=null?objPipeline.getProspectus_id()+"":"");
				csvOutput.write(objPipeline.getProyect_loan_id()!=null?objPipeline.getProyect_loan_id()+"":"");
				csvOutput.write(objPipeline.getNombre()!=null?objPipeline.getNombre():"");
				csvOutput.write(objPipeline.getEdad()!=null?objPipeline.getEdad()+"":"");
				csvOutput.write(objPipeline.getSexo()!=null?objPipeline.getSexo():"");
				csvOutput.write(objPipeline.getMedio_contacto()!=null?objPipeline.getMedio_contacto():"");
				csvOutput.write(objPipeline.getOtro_medio_contacto()!=null?objPipeline.getOtro_medio_contacto():"");
				csvOutput.write(objPipeline.getPromotor()!=null?objPipeline.getPromotor():"");
				csvOutput.write(objPipeline.getRecomendado_por()!=null?objPipeline.getRecomendado_por():"");
				csvOutput.write(objPipeline.getNivel_estudios()!=null?objPipeline.getNivel_estudios():"");
				csvOutput.write(objPipeline.getEstado_civil()!=null?objPipeline.getEstado_civil():"");
				csvOutput.write(objPipeline.getRegimen_matrimonial()!=null?objPipeline.getRegimen_matrimonial():"");
				csvOutput.write(objPipeline.getEs_comerciante()!=null?objPipeline.getEs_comerciante():"");
				csvOutput.write(objPipeline.getEs_empleado()!=null?objPipeline.getEs_empleado():"");
				csvOutput.write(objPipeline.getActividad_economica()!=null?objPipeline.getActividad_economica():"");
				csvOutput.write(objPipeline.getColonia()!=null?objPipeline.getColonia():"");
				csvOutput.write(objPipeline.getFecha_registro()!=null?(fm3.format(objPipeline.getFecha_registro())):"");
				csvOutput.write(objPipeline.getActivo()!=null?objPipeline.getActivo()+"":"");
				csvOutput.write(objPipeline.getFecha_activacion()!=null?(fm3.format(objPipeline.getFecha_activacion())):"");
				csvOutput.write(objPipeline.getIntentos()!=null?objPipeline.getIntentos()+"":"");
				csvOutput.write(objPipeline.getUltimo_intento()!=null?objPipeline.getUltimo_intento():"");
				csvOutput.write(objPipeline.getFecha_consulta()!=null?(fm3.format(objPipeline.getFecha_consulta())):"");
				csvOutput.write(objPipeline.getBc_score()!=null?objPipeline.getBc_score()+"":"");
				csvOutput.write(objPipeline.getKubo_score()!=null?objPipeline.getKubo_score():"");
				csvOutput.write(objPipeline.getFolio_consulta()!=null?objPipeline.getFolio_consulta():"");
				csvOutput.write(objPipeline.getIcc()!=null?objPipeline.getIcc():"");
				csvOutput.write(objPipeline.getEstatus()!=null?objPipeline.getEstatus():"");
				csvOutput.write(objPipeline.getTipo_credito()!=null?objPipeline.getTipo_credito():"");
				csvOutput.write(objPipeline.getDia_publicacion()!=null?(fm3.format(objPipeline.getDia_publicacion())):"");
				csvOutput.write(objPipeline.getMonto_actual()!=null?objPipeline.getMonto_actual()+"":"");
				csvOutput.write(objPipeline.getMonto_ultima_simulacion()!=null?objPipeline.getMonto_ultima_simulacion()+"":"");
				csvOutput.write(objPipeline.getProducto()!=null?objPipeline.getProducto():"");
				csvOutput.write(objPipeline.getDestino()!=null?objPipeline.getDestino():"");
				csvOutput.write(objPipeline.getSafi_credit_id()!=null?objPipeline.getSafi_credit_id():"");
				csvOutput.write(objPipeline.getMonto_credito()!=null?objPipeline.getMonto_credito()+"":"");
				csvOutput.write(objPipeline.getFecha_inicio()!=null?(fm3.format(objPipeline.getFecha_inicio())):"");
				csvOutput.write(objPipeline.getFecha_vencimiento()!=null?(fm3.format(objPipeline.getFecha_vencimiento())):"");
				csvOutput.write(objPipeline.getTasa()!=null?objPipeline.getTasa()+"":"");
				csvOutput.write(objPipeline.getFrecuencia()!=null?objPipeline.getFrecuencia()+"":"");
				csvOutput.write(objPipeline.getNo_cuotas()!=null?objPipeline.getNo_cuotas()+"":"");
				csvOutput.write(objPipeline.getMonto_cuota()!=null?objPipeline.getMonto_cuota()+"":"");
				csvOutput.write(objPipeline.getEstatus_credito()!=null?objPipeline.getEstatus_credito()+"":"");
				csvOutput.write(objPipeline.getSaldo_cap_vigente()!=null?objPipeline.getSaldo_cap_vigente()+"":"");
				csvOutput.write(objPipeline.getSaldo_cap_atrasado()!=null?objPipeline.getSaldo_cap_atrasado()+"":"");
				csvOutput.write(objPipeline.getSaldo_cap_vencido()!=null?objPipeline.getSaldo_cap_vencido()+"":"");
				csvOutput.write(objPipeline.getCat()!=null?objPipeline.getCat()+"":"");
				csvOutput.write(objPipeline.getDias_atraso()!=null?objPipeline.getDias_atraso()+"":"");
				csvOutput.endRecord();
			}
			
			csvOutput.close();
	        return true;
	
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	 }

	 
	 //Genera reporte del HISTORICO DE TUBERIA
	 private boolean generateCSVFilePipelineHistory(String sFileName, List<PipelineHistory> pipelineHistoryList){
		try{
			CsvWriter csvOutput = new CsvWriter(new FileWriter(sFileName, true), ',');
			
			csvOutput.write("periodo");
			csvOutput.write("prospectus_id");
			csvOutput.write("proyect_loan_id");
			csvOutput.write("nombre");
			csvOutput.write("edad");
			csvOutput.write("sexo");
			csvOutput.write("medio_contacto");
			csvOutput.write("otro_medio_contacto");
			csvOutput.write("promotor");
			csvOutput.write("recomendado_por");
			csvOutput.write("nivel_estudios");
			csvOutput.write("estado_civil");
			csvOutput.write("regimen_matrimonial");
			csvOutput.write("es_comerciante");
			csvOutput.write("es_empleado");
			csvOutput.write("actividad_economica");
			csvOutput.write("colonia");
			csvOutput.write("fecha_registro");
			csvOutput.write("activo");
			csvOutput.write("fecha_activacion");
			csvOutput.write("intentos");
			csvOutput.write("ultimo_intento");
			csvOutput.write("fecha_consulta");
			csvOutput.write("bc_score");
			csvOutput.write("kubo_score");
			csvOutput.write("folio_consulta");
			csvOutput.write("icc");
			csvOutput.write("estatus");
			csvOutput.write("tipo_credito");
			csvOutput.write("dia_publicacion");
			csvOutput.write("monto_actual");
			csvOutput.write("monto_ultima_simulacion");
			csvOutput.write("producto");
			csvOutput.write("destino");
			csvOutput.write("safi_credit_id");
			csvOutput.write("monto_credito");
			csvOutput.write("fecha_inicio");
			csvOutput.write("fecha_vencimiento");
			csvOutput.write("tasa");
			csvOutput.write("frecuencia");
			csvOutput.write("no_cuotas");
			csvOutput.write("monto_cuota");
			csvOutput.write("estatus_credito");
			csvOutput.write("saldo_cap_vigente");
			csvOutput.write("saldo_cap_atrasado");
			csvOutput.write("saldo_cap_vencido");
			csvOutput.write("cat");
			csvOutput.write("dias_atraso");
			csvOutput.endRecord();
			
			for(PipelineHistory objPipeline :pipelineHistoryList){
				
				csvOutput.write(fm1.format(new Date()));
				csvOutput.write(objPipeline.getProspectus_id()!=null?objPipeline.getProspectus_id()+"":"");
				csvOutput.write(objPipeline.getProyect_loan_id()!=null?objPipeline.getProyect_loan_id()+"":"");
				csvOutput.write(objPipeline.getNombre()!=null?objPipeline.getNombre():"");
				csvOutput.write(objPipeline.getEdad()!=null?objPipeline.getEdad()+"":"");
				csvOutput.write(objPipeline.getSexo()!=null?objPipeline.getSexo():"");
				csvOutput.write(objPipeline.getMedio_contacto()!=null?objPipeline.getMedio_contacto():"");
				csvOutput.write(objPipeline.getOtro_medio_contacto()!=null?objPipeline.getOtro_medio_contacto():"");
				csvOutput.write(objPipeline.getPromotor()!=null?objPipeline.getPromotor():"");
				csvOutput.write(objPipeline.getRecomendado_por()!=null?objPipeline.getRecomendado_por():"");
				csvOutput.write(objPipeline.getNivel_estudios()!=null?objPipeline.getNivel_estudios():"");
				csvOutput.write(objPipeline.getEstado_civil()!=null?objPipeline.getEstado_civil():"");
				csvOutput.write(objPipeline.getRegimen_matrimonial()!=null?objPipeline.getRegimen_matrimonial():"");
				csvOutput.write(objPipeline.getEs_comerciante()!=null?objPipeline.getEs_comerciante():"");
				csvOutput.write(objPipeline.getEs_empleado()!=null?objPipeline.getEs_empleado():"");
				csvOutput.write(objPipeline.getActividad_economica()!=null?objPipeline.getActividad_economica():"");
				csvOutput.write(objPipeline.getColonia()!=null?objPipeline.getColonia():"");
				csvOutput.write(objPipeline.getFecha_registro()!=null?(fm3.format(objPipeline.getFecha_registro())):"");
				csvOutput.write(objPipeline.getActivo()!=null?objPipeline.getActivo()+"":"");
				csvOutput.write(objPipeline.getFecha_activacion()!=null?(fm3.format(objPipeline.getFecha_activacion())):"");
				csvOutput.write(objPipeline.getIntentos()!=null?objPipeline.getIntentos()+"":"");
				csvOutput.write(objPipeline.getUltimo_intento()!=null?objPipeline.getUltimo_intento():"");
				csvOutput.write(objPipeline.getFecha_consulta()!=null?(fm3.format(objPipeline.getFecha_consulta())):"");
				csvOutput.write(objPipeline.getBc_score()!=null?objPipeline.getBc_score()+"":"");
				csvOutput.write(objPipeline.getKubo_score()!=null?objPipeline.getKubo_score():"");
				csvOutput.write(objPipeline.getFolio_consulta()!=null?objPipeline.getFolio_consulta():"");
				csvOutput.write(objPipeline.getIcc()!=null?objPipeline.getIcc():"");
				csvOutput.write(objPipeline.getEstatus()!=null?objPipeline.getEstatus():"");
				csvOutput.write(objPipeline.getTipo_credito()!=null?objPipeline.getTipo_credito():"");
				csvOutput.write(objPipeline.getDia_publicacion()!=null?(fm3.format(objPipeline.getDia_publicacion())):"");
				csvOutput.write(objPipeline.getMonto_actual()!=null?objPipeline.getMonto_actual()+"":"");
				csvOutput.write(objPipeline.getMonto_ultima_simulacion()!=null?objPipeline.getMonto_ultima_simulacion()+"":"");
				csvOutput.write(objPipeline.getProducto()!=null?objPipeline.getProducto():"");
				csvOutput.write(objPipeline.getDestino()!=null?objPipeline.getDestino():"");
				csvOutput.write(objPipeline.getSafi_credit_id()!=null?objPipeline.getSafi_credit_id():"");
				csvOutput.write(objPipeline.getMonto_credito()!=null?objPipeline.getMonto_credito()+"":"");
				csvOutput.write(objPipeline.getFecha_inicio()!=null?(fm3.format(objPipeline.getFecha_inicio())):"");
				csvOutput.write(objPipeline.getFecha_vencimiento()!=null?(fm3.format(objPipeline.getFecha_vencimiento())):"");
				csvOutput.write(objPipeline.getTasa()!=null?objPipeline.getTasa()+"":"");
				csvOutput.write(objPipeline.getFrecuencia()!=null?objPipeline.getFrecuencia()+"":"");
				csvOutput.write(objPipeline.getNo_cuotas()!=null?objPipeline.getNo_cuotas()+"":"");
				csvOutput.write(objPipeline.getMonto_cuota()!=null?objPipeline.getMonto_cuota()+"":"");
				csvOutput.write(objPipeline.getEstatus_credito()!=null?objPipeline.getEstatus_credito()+"":"");
				csvOutput.write(objPipeline.getSaldo_cap_vigente()!=null?objPipeline.getSaldo_cap_vigente()+"":"");
				csvOutput.write(objPipeline.getSaldo_cap_atrasado()!=null?objPipeline.getSaldo_cap_atrasado()+"":"");
				csvOutput.write(objPipeline.getSaldo_cap_vencido()!=null?objPipeline.getSaldo_cap_vencido()+"":"");
				csvOutput.write(objPipeline.getCat()!=null?objPipeline.getCat()+"":"");
				csvOutput.write(objPipeline.getDias_atraso()!=null?objPipeline.getDias_atraso()+"":"");
				csvOutput.endRecord();
			}
			
			csvOutput.close();
	        return true;
	
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	 }
	 
	 //Elimina los archivos temporales de descarga
	 public void fileDelete(String pathSourceFolder) {
		 try{
			//String pathSourceFolder = realPath+"/documents/reports/";
			//ID 45 trae los dias de validez de una consulta a buro
			SystemParamPK systemParamPK = new SystemParamPK(49,sesion.getCompany_id());
			SystemParam objSystemParam = systemParamService.loadSelectedSystemParam(systemParamPK);
			int diasValidos = Integer.parseInt(objSystemParam.getValue());
			 
			 // Creamos la referencia de la carpeta a leer
			 File f = new File(pathSourceFolder);
			 // Guarda los nombres de los archivos de la carpeta a leer
			 String files[] = f.list();
			 // Muestra el listado de archivos de la carpeta a leer
			 for (int i=0; i<files.length; i++) {
				 String strDate = files[i].substring(files[i].indexOf("_")+1, files[i].lastIndexOf(" "));
				 strDate = strDate.replace("_","-");
				
				//Con los dias validos definidos en system_param se los restamos a la fecha actual para
				//obtener la fecha limite a considerar para obtener el rango de validez de la ocnsulta.
				//fecActual-diasValidos:24-10-13-2 = 22-10-13
				Date fechaLimite = Utilities.restarFechasDias(new Date(), diasValidos);
				if(fm1.parse(strDate).before(fm1.parse(fechaLimite.toString()))){
					File tempFileToDelete = new File(pathSourceFolder+"/"+files[i]);
					if(tempFileToDelete.isDirectory()){
						Utilities.deleteDirectory(tempFileToDelete);
					}
					tempFileToDelete.delete();
					//new File(pathSourceFolder+"/"+files[i]).delete();
				}
			 }
		 }catch(Exception e){
			 System.out.println("EROR..");
			 e.printStackTrace();
		 }
	 }
}


