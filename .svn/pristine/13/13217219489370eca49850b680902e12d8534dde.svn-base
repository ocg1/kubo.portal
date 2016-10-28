package mx.com.kubo.managedbeans.mesa;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.mesa.reportes.buro.ChartBackBeanIMP;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.PldNotification;

public abstract class ElectronicFileAMO extends ElectronicFileDMO
{
	protected void init_menu_item(int searchID) 
	{
		switch (searchID) 
		{
			case IDENTIFICACION:
				search_documentation();
								
				init_display_flags();
				
				creden_fm2_dip = true;

			break;
			
			case SOLICITUD:
				init_display_flags();
				
				summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");
				summary.mapeoDatos(searchsum.getSearchSummary());
					
				include_disp = true;
					
				pageStr = "templates/request.xhtml";
//					
//					if(!haveConsult){
//						chart = (ChartBackBeanIMP) FacesContext.getCurrentInstance()
//				                .getApplication().getELResolver()
//				                .getValue(elContext, null, "chartBackBean");
//						
//						chart.setBurSolNum(proyectLoan.getMx_solicitud_buro());
//						chart.loadChart();
//						
//						haveConsult=true;
//						
//					}
			break;
			
			case 3: break;
			
			case CAPACIDAD_PAGO:
				search_docCapacidadPago();
			break;
			
			case CONTRATOS:
				addDocsContracts();
			break;
				
			case CONSULTAS:
				init_display_flags();
				
				include_disp   = true;
				valGraphicDisp = true;
				
				pageStr = "reporteBuroExpediente.xhtml";
				
				search_docSICs();
				
				if(!haveConsult)
				{
					chart = (ChartBackBeanIMP) resolver.getValue(elContext, null, "chartBackBean");
					
					chart.setBurSolNum(proyectLoan.getMx_solicitud_buro());
					chart.loadChart();
					
					idProvider = chart.isIdProvider();
					
					haveConsult = true;					
				}
			break;
			
			case ESTADO_CUENTA:
				init_display_flags();
				
				include_disp   = true;
				valGraphicDisp = true;
				
				pageStr = "templates/contEdoCuenta.xhtml";
				titleStr   = "";
				contMsgStr = "";
			break;
			
			case BITACORA_COBRANZA:
				init_display_flags();
				
				searchsum.setTypeLog("COB");								
				
				include_disp = true;
				
				pageStr = "templates/bitacorasForm.xhtml";

				titleStr   = "";
				contMsgStr = "";
			break;
			
			case COMPROBANTE_DOMICILIO:
				search_docAddress();
			break;
			
			case GARANTIAS:	
				init_display_flags();
				
				msg_disp = true;
								
				titleStr   = "Garantías";
				contMsgStr = "Sin Garantías";
			break;
			
			case ESTADOS_FINANCIEROS:								
				init_display_flags();
				
				msg_disp = true;
				titleStr   = "Estados Financieros";
				contMsgStr = "No aplica a Personas Físicas";
			break;
			
			case PROPIETARIO_REAL:
				init_display_flags();												
				init_pld();			
				
				propietario_real_ENABLED = true;
			break;
			
			default: break;
		}
	}
	
	private void init_display_flags() 
	{
		creden_fm2_dip      = false;
		capacidad_pago_disp = false;
		doc_address_disp    = false;
		doc_contracts_disp  = false;
		include_disp        = false;
		valGraphicDisp      = false;
		dispListSIC         = false;
		msg_disp            = false;
		propietario_real_ENABLED = false;
		
		titleStr   = "";
		contMsgStr = "";
	}
	
	private void init_pld() 
	{
		lista_pld_notification = new ArrayList<PldNotification>();
		
		pld_PK = new PrevencionLDPK();
		pld_PK.setCompany_id(company_id);
		pld_PK.setProspectus_id(prospectus_id);
		
		pld = service_PLD.getSelectedPrevencionLDById(pld_PK);
		
		if(pld != null && pld.getFor_third_party() != null)
		{
			pld_notification = new PldNotification();
			pld_notification.setPld_notification_id(0);
			pld_notification.setUnusual_behavior_id(0);
			pld_notification.setCompany_id(company_id);
			pld_notification.setProspectus_id(sesion.getProspectus_id());
			pld_notification.setProspectus_id_viewed(prospectus_id);			
			pld_notification.setPerson(member.getPerson());		
			pld_notification.setNotification_date(member.getCreation_date());
					
			third_party = pld.getFor_third_party().charAt(0);
			
			switch (third_party)
			{
				case 'T':
					pld_notification.setComments(COMMENT_TERCERO);			
				break;
				
				case 'P':
					pld_notification.setComments(COMMENT_PROPIETARIO_REAL);							
				break;

				default: break;
			}			
			
			lista_pld_notification.add(pld_notification);
		}
		
		List<PldNotification> lista_pld = service_PLD.getLista_pld_notification(prospectus_id, company_id);
		
		if(lista_pld != null && lista_pld.size() > 0)
		{
			lista_pld_notification.addAll(lista_pld);
		}
	}

	protected void search_documentation()
	{		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		
		
		/*
		if (getListFilesP()==null) {
			//listFilesP=filesService.getListFilesByProspect(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]));
			listFilesP = filesService.getListFilesByProspect(prospectusID, companyID, proyectLoanID);
		}		
		*/
		
		
		if( searchsum.getSearchSummary().split("::").length > 2 )
		{	
			prospectus_id = Integer.parseInt(searchsum.getSearchSummary().split("::")[2]);
			company_id    = Integer.parseInt(searchsum.getSearchSummary().split("::")[3]);
			
			listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(prospectus_id, company_id);
			
		} else {
			
			prospectus_id = Integer.parseInt(searchsum.getSearchSummary().split("::")[0]);
			company_id    = Integer.parseInt(searchsum.getSearchSummary().split("::")[1]);
			
			listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(prospectus_id, company_id);			
		}
						
		if (listProyectLoan != null) 
		{
			listDocumentation = new ArrayList<DocumentationDMO>();
			
			for(ProyectLoan iterPL : listProyectLoan)
			{			
				listFilesP = filesService.getListFilesByProspect(iterPL.getProyectloanPk().getProspectus_id(), iterPL.getProyectloanPk().getCompany_id(), iterPL.getProyectloanPk().getProyect_loan_id());
			
			//listFilesP = filesService.getListFilesByProspect(prospectusID, companyID, proyectLoanID);
				boolean flagShowTitle= true;
			
				for (Files iter : listFilesP) 
				{	
					String formatDocument=iter.getLocation().substring(iter.getLocation().lastIndexOf(".")+1);
				
					switch (iter.getFileType().getFileTypePk().getFile_type_id()) 
					{					 			 
				 		case 1:
				 			listDocumentation.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(), iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));
				 			flagShowTitle = false;
				 		break;
				 		
				 		case 2:
				 			listDocumentation.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(), iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));
				 			flagShowTitle = false;
				 		break;
				 		
					 	case 42:
					 		listDocumentation.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(), iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));					 		
					 		flagShowTitle = false;
					 	break;
					 	
					 	case 43:
					 		listDocumentation.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(), iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));					 		
					 		flagShowTitle = false;
				 		break;
	
					 	default: break;																	
					}			 
				}		
			}				
		}	
	}
	
	protected void addDocsContracts()
	{				
		if(searchsum.getSearchSummary() !=null)
		{
				
			if(listProyectLoan != null)
			{
					
				listDocsContracts=fileTypeService.getListFileTypeByCategory(8);
				listAddedContracts=new ArrayList<DocumentationDMO>();
					
				listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]));
					
				for(ProyectLoan iterPL : listProyectLoan)
				{
					boolean flagShowTitle = true;
						//listFilesP=filesService.getListFilesByProspect(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]), Integer.parseInt(searchsum.getSearchSummary().split("::")[0]));
						listFilesP = filesService.getListFilesByProspect(iterPL.getProyectloanPk().getProspectus_id(), iterPL.getProyectloanPk().getCompany_id(), iterPL.getProyectloanPk().getProyect_loan_id());
						
						
						for (Files itera : listFilesP) 
						{
							String formatDocument=itera.getLocation().substring(itera.getLocation().lastIndexOf(".")+1);
							
							if(itera.getFilesPk().getFile_type_id()==44 || itera.getFilesPk().getFile_type_id()==45)
							{
								listAddedContracts.add(new DocumentationDMO(itera.getFilesPk().getProspectus_id(),itera.getFilesPk().getCompany_id(), itera.getFileType().getName(),itera.getLocation(),getRealPath()+itera.getLocation(),itera.getFilesPk().getFile_type_id(),formatDocument,itera.getFilesPk().getFile_id(),itera.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));						
								flagShowTitle = false;
							}
								
						}
					}
				}
			}			

			searchsum.setTypeLog("EVA");
			
			init_display_flags();
			
			doc_contracts_disp = true;
	}
	
	private void search_docCapacidadPago()
	{		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		/*
		if (getListFilesP()==null) {
			listFilesP=filesService.getListFilesByProspect(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]), Integer.parseInt(searchsum.getSearchSummary().split("::")[0]));
		}
		*/
		
		boolean flag = false;
		
		if(listProyectLoan != null)
		{
			listDocCapPago = new ArrayList<DocumentationDMO>();
			listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]));
			
			
			for(ProyectLoan iterPL : listProyectLoan)
			{
				listFilesP = filesService.getListFilesByProspect(iterPL.getProyectloanPk().getProspectus_id(), iterPL.getProyectloanPk().getCompany_id(), iterPL.getProyectloanPk().getProyect_loan_id());
				boolean flagShowTitle = true;
	
			for (Files iter : listFilesP) 
			{	
				String formatDocument=iter.getLocation().substring(iter.getLocation().lastIndexOf(".")+1);
				
				if(iter.getFilesPk().getFile_type_id()>=9 && iter.getFilesPk().getFile_type_id()<=14)
				{
					flag = true;
					listDocCapPago.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(), iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));
					flagShowTitle = false;
				}			 	
			}	
		}
		}
			
		
		if(flag)
		{
			capacidad_pago_disp = true;
					
			init_display_flags();
			
		} else {

			init_display_flags();
			
			msg_disp = true;

			titleStr   = "Capacidad de Pago";
			contMsgStr = "No cuenta con ningun documento que acredite su capacidad de pago.";
		}
	}
	
	private void search_docSICs()
	{		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		/*
		if (getListFilesP()==null) {
			listFilesP=filesService.getListFilesByProspect(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]), Integer.parseInt(searchsum.getSearchSummary().split("::")[0]));
		}
		*/
		boolean flag = false;
		
		if(listProyectLoan != null)
		{
			listDocSIC = (new ArrayList<DocumentationDMO>());
			listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]));
			
			for(ProyectLoan iterPL: listProyectLoan)
			{
				listFilesP = filesService.getListFilesByProspect(iterPL.getProyectloanPk().getProspectus_id(), iterPL.getProyectloanPk().getCompany_id(), iterPL.getProyectloanPk().getProyect_loan_id());
				
				boolean flagShowTitle = true;
			
				for (Files iter : listFilesP) 
				{	
					String formatDocument=iter.getLocation().substring(iter.getLocation().lastIndexOf(".")+1);
					
					if(iter.getFileType().getFile_category_id()==9 )
					{
						flag = true;
						listDocSIC.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(),iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));
						flagShowTitle = false;
					}			 	
				}	
			}
		}
		
		if(flag)
		{			 
			contMsgStr = "";
			
		} else {
			
			titleStr   = "Documentos para consulta a las Sociedades de Información Crediticia";
			contMsgStr = "No cuenta con ningun documento permita su consulta a las Sociedades de Información Crediticia";
		}
				
		dispListSIC = true;		
	}
	
	private void search_docAddress()
	{		
		searchsum = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		/*
		if (getListFilesP()==null) {
			
			listFilesP=filesService.getListFilesByProspect(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]), Integer.parseInt(searchsum.getSearchSummary().split("::")[0]));
		}
		*/
		
		if(getListaProyectLoan() != null)
		{					
			listDocAddress = new ArrayList<DocumentationDMO>();
			
			if( searchsum != null && searchsum.getSearchSummary().split("::").length>2)
			{
				listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(Integer.parseInt(searchsum.getSearchSummary().split("::")[2]), Integer.parseInt(searchsum.getSearchSummary().split("::")[3]));
						
				for(ProyectLoan iterPL : listProyectLoan)
				{
					
					listFilesP = filesService.getListFilesByProspect(iterPL.getProyectloanPk().getProspectus_id(), iterPL.getProyectloanPk().getCompany_id(), iterPL.getProyectloanPk().getProyect_loan_id());
				
					boolean flagShowTitle = true;
					
					for (Files iter : listFilesP) 
					{	
						String formatDocument=iter.getLocation().substring(iter.getLocation().lastIndexOf(".")+1);
						
						if(iter.getFilesPk().getFile_type_id()>=24 && iter.getFilesPk().getFile_type_id()<=29)
						{
							listDocAddress.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(),iter.getFilesPk().getProyect_loan_id(), iterPL.getStatusProyect().getName(), flagShowTitle));
							flagShowTitle = false;
						}			 	
					}
				}
				
			}else{
				//listProyectLoan = proyectLoanService.getProyectLoanListByProspectusOrderByProyectLoan(Integer.parseInt(), Integer.parseInt(searchsum.getSearchSummary().split("::")[1]));
				
				listFilesP = filesService.getListFilesByProspect( Integer.parseInt( searchsum.getSearchSummary().split("::")[0] ), Integer.parseInt( searchsum.getSearchSummary().split("::")[1]) , 1 );
				
				boolean flagShowTitle = true;
				
				for (Files iter : listFilesP) 
				{	
					String formatDocument=iter.getLocation().substring(iter.getLocation().lastIndexOf(".")+1);
					
					if(iter.getFilesPk().getFile_type_id()>=24 && iter.getFilesPk().getFile_type_id()<=29)
					{
						listDocAddress.add(new DocumentationDMO(iter.getFilesPk().getProspectus_id(),iter.getFilesPk().getCompany_id(), iter.getFileType().getName(),iter.getLocation(),getRealPath()+iter.getLocation(),iter.getFilesPk().getFile_type_id(),formatDocument,iter.getFilesPk().getFile_id(),iter.getFilesPk().getProyect_loan_id(), "", flagShowTitle));
						flagShowTitle = false;
					}			 	
				}
				
			}
				
		}
		
		 creden_fm2_dip = false;
		 capacidad_pago_disp = false;
		 doc_address_disp = true;
		 doc_contracts_disp = false;
		 include_disp = false;
		 valGraphicDisp = false;
		 msg_disp = false;
		 dispListSIC = false;
			titleStr = "";
			contMsgStr = "";
		 
	}
}
