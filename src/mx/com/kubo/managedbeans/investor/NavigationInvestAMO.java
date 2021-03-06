package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.FilterStore;
import mx.com.kubo.managedbeans.MyInvestments;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.EventTokenAccess;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public abstract class NavigationInvestAMO extends NavigationInvestDMO
{
	protected void initTokenAccess( String area )
	{		
		List<EventTokenAccess> lst_token = event_token_access_service.getEventTokenAccessListByArea(area);
		
		if( lst_token != null && lst_token.size() > 0 )
		{
			
			System.out.println( "lst_token.size1 = " + lst_token.size() );
			
			for( EventTokenAccess et : lst_token )
			{			
				if( et.getPk().getEvent_token_id() == MODIFICAR_DATOS_PERSONALES && et.getIs_active().equals("1") )
				{ 
					flagTokenPersonalData = true;
				}
			
			}
			
		} else {
			
			if( lst_token == null )
			{
				System.out.println("lst_token = null");
				
			} else {
				
				System.out.println( "lst_token.size2 = " + lst_token.size() );
			}
		}	
	}
	
	protected void init_seccion(String seccionInv, SessionBean sesion, FacesContext faces, ELResolver resolver, ELContext elContext) 
	{
		boolean is_mesa_control = sesion.getArea().toString().equals("M");
		
		if(seccionInv.equals("investments"))
		{
			setCashWithdrawalDisp(false);
			setResumenDisp(true);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			setProyectListInvestDisp(false);
			//paginaActualInt="templates/myInvestments.xhtml";
						
			if(is_mesa_control)
			{
				paginaActualIntInv = "templates/myInvestments.xhtml";
				
			} else {
				
				MyInvestments myinvestment = (MyInvestments) resolver.getValue(elContext, null, "myInvestments");
				
				myinvestment.webService();
				
			}
			
			flagDispEdoCuenta = false;
			 
			menuInveSel = 1;
			
		}else if(seccionInv.equals("returnProyectListInvest")){
			
			setCashWithdrawalDisp(false);
			setResumenDisp(false);
			setListProyectDisp(true);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			setProyectListInvestDisp(false);
			//paginaActualInt="proyectList.xhtml";
			
			
			SystemParamPK sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(70);//Bandera que indica si existe un error al cargar la tienda
			SystemParam thisSystem = service_system_param.loadSelectedSystemParam(sysPk);
			
			if( thisSystem != null && thisSystem.getValue().equals("S") )
			{
				paginaActualInt="secciones/investment/investmentListError.xhtml";
			
			}else {
				
				sysPk = new SystemParamPK();
				
				sysPk.setCompany_id(sesion.getCompany_id());
				sysPk.setSystem_param_id(71);//Bandera que indica si se encuentra deshabilitada la tienda
				thisSystem = service_system_param.loadSelectedSystemParam(sysPk);
				
				if( thisSystem != null && thisSystem.getValue().equals("S") )
				{
					paginaActualInt="secciones/investment/investmentListWarning.xhtml";
				
				}else{
					
						paginaActualInt="secciones/investment/investmentList.xhtml";
						
						InvestmentList investmentlist = (InvestmentList) resolver.getValue(elContext, null, "investmentList");
						
						if(investmentlist != null)
						{
							
							if( flagFistTimeLIst ){
							
								InvestorSession investorSession = (InvestorSession) resolver.getValue(elContext, null, "investorSession");
								
								
								investmentlist.setInvestorSession(investorSession);
								investmentlist.inicializaSaldos();
								investmentlist.calculaSaldoActual();
								FilterStore cad =  investmentlist.getFilter();
								
								
								
								if(cad != null){
									investmentlist.preparaProyectList(cad);
								}else{
									investmentlist.setupList();
									
								}
								
								investmentlist.calculaInvestmentBite(  );
								
							}else{
								flagFistTimeLIst = true;
							}
							
						}
						
				}
				
			}
			
			
			
//			paginaActualInt="secciones/investment/investmentList.xhtml";
			paginaActualIntInv="";
			flagDispEdoCuenta = false;
			
			menuInveSel = 2;
			
		}
		else if(seccionInv.equals("proyectList")){
			setCashWithdrawalDisp(false);
			setResumenDisp(false);
			setListProyectDisp(true);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			setProyectListInvestDisp(false);
			//paginaActualInt="proyectList.xhtml";
			
			SystemParamPK sysPk = new SystemParamPK();
			
			sysPk.setCompany_id(sesion.getCompany_id());
			sysPk.setSystem_param_id(70);//Bandera que indica si existe un error al cargar la tienda
			SystemParam thisSystem = service_system_param.loadSelectedSystemParam(sysPk);
			
			if( thisSystem != null && thisSystem.getValue().equals("S") )
			{
				paginaActualInt="secciones/investment/investmentListError.xhtml";
			
			}else {
				
				sysPk = new SystemParamPK();
				
				sysPk.setCompany_id(sesion.getCompany_id());
				sysPk.setSystem_param_id(71);//Bandera que indica si se encuentra deshabilitada la tienda
				thisSystem = service_system_param.loadSelectedSystemParam(sysPk);
				
				if( thisSystem != null && thisSystem.getValue().equals("S") )
				{
					paginaActualInt="secciones/investment/investmentListWarning.xhtml";
				
				}else{
					
					paginaActualInt="secciones/investment/investmentList.xhtml";
					
					InvestmentList investmentlist = (InvestmentList) resolver.getValue(elContext, null, "investmentList");
					
					if(investmentlist != null)
					{
																		
						InvestorSession investorSession = (InvestorSession) resolver.getValue(elContext, null, "investorSession");
						
						
						investmentlist.setInvestorSession(investorSession);
						investmentlist.inicializaSaldos();
						investmentlist.calculaSaldoActual();
						investmentlist.calculaInvestmentBite();
						investmentlist.insertaAccessTienda();
					}
					
				}
				
			}
			
//			paginaActualInt="secciones/investment/investmentList.xhtml";
			paginaActualIntInv="";
			flagDispEdoCuenta = false;
			
			menuInveSel = 2;
			
		}
		else if(seccionInv.equals("investment_Action")){
			
//			viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
//			
//			InvestmentAction invAct = (InvestmentAction) viewMap.get("investmentAction");
//			
//			invAct.setProyectList(proyectlist_to_inv);
			
			setCashWithdrawalDisp(false);
			setResumenDisp(false);
			setListProyectDisp(true);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			setProyectListInvestDisp(false);
			//setInvestmentActionDisp(true);
			paginaActualInt="templates/investmentAction.xhtml";
			paginaActualIntInv="";
			flagDispEdoCuenta = false;
		}
		else if(seccionInv.equals("menuregistro")){
			setCashWithdrawalDisp(false);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(true);
			setSummaryDisp(false);
			setLogsDisp(false);
			setProyectListInvestDisp(false);
			//paginaActualInt="menuregistro.xhtml";
			flagDispEdoCuenta = false;
			
			faces.getExternalContext().getSessionMap().put("navigationBean", null);
			
			menuInveSel = 4;
			
		}
		else if(seccionInv.equals("summary")){
			
			SummaryRequest summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");
			
			summary.init();
			
			setCashWithdrawalDisp(false);
			setResumenDisp(false);
			setListProyectDisp(true);
			setBasicDataDisp(false);
			setSummaryDisp(true);
			setLogsDisp(false);
			setProyectListInvestDisp(false);
			flagDispEdoCuenta = true;
			paginaActualInt="templates/request.xhtml";
			
		}	
		else if(seccionInv.equals("logs")){
			setCashWithdrawalDisp(false);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(true);
			setProyectListInvestDisp(false);
			//paginaActualInt="templates/bitacorasForm.xhtml";
			flagDispEdoCuenta = false;
		}
		else if( seccionInv.equals("proyectListInvest") ){
			setCashWithdrawalDisp(false);
			setProyectListInvestDisp(true);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			paginaActualIntInv="templates/proyectListInvest.xhtml";
			flagDispEdoCuenta = false;
		}
		else if( seccionInv.equals("proyectListInvestActiveAction") ){
			setCashWithdrawalDisp(false);
			setProyectListInvestDisp(true);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			paginaActualIntInv="templates/proyectListInvestActiveAction.xhtml";
			flagDispEdoCuenta = true;
		}
		else if( seccionInv.equals("sellingProyectListAction") ){
			
			setCashWithdrawalDisp(false);
			setProyectListInvestDisp(true);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			paginaActualIntInv="templates/ventaCartera.xhtml";
			
			
		}
		else if ( seccionInv.equals("cashWithdrawal") ){
			
			setCashWithdrawalDisp(true);
			setProyectListInvestDisp(false);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			
			MovimientosInversionista movimientos = (MovimientosInversionista) resolver.getValue(elContext, null, "movimientosInversionista");
			
			movimientos.context = elContext;
			movimientos.initPaginaMovimientos();
			
			paginaActualIntInv="";
			menuInveSel = 3;
		}
		else if ( seccionInv.equals("ajustes") ){
			
			setCashWithdrawalDisp(false);
			setProyectListInvestDisp(true);
			setResumenDisp(false);
			setListProyectDisp(false);
			setBasicDataDisp(false);
			setSummaryDisp(false);
			setLogsDisp(false);
			paginaActualIntInv="templates/ajustes.xhtml";
			
		}
		
		
	}
}
