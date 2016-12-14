package mx.com.kubo.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ExpensesTypePK;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeDetailPK;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.IncomeTypePK;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_costPK;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingDetailPK;
import mx.com.kubo.model.SellingType;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.IncomeDetailService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.impl.ExpensesTypeServiceImp;
import mx.com.kubo.services.impl.IncomeTypeServiceImp;

@ManagedBean
@ViewScoped
public class AddIncomeExpenses implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	@ManagedProperty("#{employmentServiceImp}")
	private EmploymentService  employmentService;
	
	@ManagedProperty("#{businessServiceImp}")
	private BusinessService businessservice;
	
	@ManagedProperty("#{incomeServiceImp}")
	private IncomeService incomeService;
	
	@ManagedProperty("#{incomeDetailServiceImp}")
	private IncomeDetailService incomedetailservice;
	
	@ManagedProperty("#{incomeTypeServiceImp}")
	private IncomeTypeServiceImp incomeTypeService;
	
	@ManagedProperty("#{expensesServiceImp}")
	private ExpensesService expensesService;
	
	@ManagedProperty("#{expensesTypeServiceImp}")
	private ExpensesTypeServiceImp expensesTypeService;
	
	@ManagedProperty("#{proyectServiceImp}")
	private ProyectService proyectoService;
	
	@ManagedProperty("#{change_controlServiceImp}")
	private Change_controlService changeControlService;
	
	private String wagesSalaryDisp="none";
	private String businessCompanyDisp="none";
	
	private Income thisIncome;
	private Expenses thisExpenses;
	private Expenses totalExpensesObj;
	private Proyect thisProyect;
	private boolean hasProyect; 
	
	private boolean hasEmployment=false;
	private boolean hasBusiness=false;
	//variables de ingresos
	private String ammountWagesSalStr="0.00";
	private String ammountBusComStr="0.00";
	private String ammountOtherFamStr="0.00";
	private String ammountOtherStr="0.00";
	
	private double ammountWagesSal;
	private double ammountBusCom;
	private double ammountOtherFam;
	private double ammountOther;
	
	//Variables de egresos
	private String ammountFoodStr="0.00";
	private String ammountMaintenancHousStr="0.00";
	private String ammountLightStr="0.00";
	private String ammountPhoneStr="0.00";
	private String ammountIncomeStr="0.00";
	private String ammountSeriesStr="0.00";
	private String ammountCreditsStr="0.00";
	private String ammountSchoolStr="0.00";
	private String ammountMedicalStr="0.00";
	private String ammountEntertainmentStr="0.00";
	private String ammountFoodWorkStr="0.00";
	private String ammountGasolineTranspStr="0.00";
	private String ammountGasStr="0.00";
	
	private double ammountFood;
	private double ammountMaintenancHous;
	private double ammountLight;
	private double ammountPhone;
	private double ammountIncome;
	private double ammountSeries;
	private double ammountCredits;
	private double ammountSchool;
	private double ammountMedical;
	private double ammountEntertainment;
	private double ammountFoodWork;
	private double ammountGasolineTransp;
	private double ammountGas;

	
	private int typeIncomeID;
	private double totalIncome=0.00;
	private String totalIncomeStr="0.00";
	
	private int typeExpensesID;
	private String excessStr="0.00";
	private double totalExpenses=0.00;
	private String totalExpensesStr="0.00";
	
	private List<Employment> listEmployment;
	private List<Business> listBusiness;
	private List<Income> listIncomeByProspect;
	private List<Expenses> listExpensesByProspect;
	private IncomeDetail incomedetail;
	private boolean hasIncomeDetail;
	private boolean flagTotalExpense;
	private String dispSlideDetails;
	
	private String dispDaily;
	private String dispWeek;
	private String dispFortnight;
	private String dispMonthly;
	
	private String sales_freqStr;//	varchar(1)	YES
	private String times_refillStr;//	int(11)	YES
	
	private String provider_amountStr;//	"double(12	2)"	YES
	private String provider_totalStr;//	"String(12	2)"	YES
	private String detail_1Str;//	"String(12	2)"	YES
	private String detail_2Str;//	"String(12	2)"	YES;//
	private String detail_3Str;//	"String(12	2)"	YES
	private String detail_4Str;//	"String(12	2)"	YES
	private String detail_5Str;//	"String(12	2)"	YES
	private String detail_6Str;//	"String(12	2)"	YES
	private String detail_7Str;//	"String(12	2)"	YES
	private String sales_totalStr;//	"String(12	2)"	YES
	private String profit_before_costsStr;//	"String(12	2)"	YES
	private String operative_costsStr;//	"String(12	2)"	YES
	private String cost_rentStr;//	"String(12	2)"	YES
	private String cost_waterStr;//	"String(12	2)"	YES
	private String cost_electricityStr;//	"String(12	2)"	YES
	private String cost_gasStr;//	"String(12	2)"	YES
	private String cost_phoneStr;//	"String(12	2)"	YES
	private String cost_taxesStr;//	"String(12	2)"	YES
	private String cost_transportStr;//	"String(12	2)"	YES
	private String cost_maintenanceStr;//	"String(12	2)"	YES
	private String cost_accountantStr;//	"String(12	2)"	YES
	private String cost_employeesStr;//	"String(12	2)"	YES
	private String cost_otherStr;//	"String(12	2)"	YES
	private String profil_after_costsStr;//	"double(12	2)"	YES
	private String sales_totalMonthlyStr;
	private List<IncomeDetailsBean> lstSellingDetails;
	private List<IncomeDetailsBean> lstOpertCostDetails;
	private List<SellingType> lstSellingType;
	private List<Operating_cost_type> lstOperaCostType;
	private Integer income_id;
	
	private String ammountDeductionsSalStr;
	private String totalammountWagesSalStr;
	
	private Double ammountDeductionsSal;
	private Double totalammountWagesSal;
	
	private String descriptionOtherIncome;
	private String ipAddressClient;
	
	private String sales_type;
	private String provider_type;
	private String sales_freq;
	
	private Employment employment;
	
	
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	public  java.text.NumberFormat num = java.text.NumberFormat.getNumberInstance(locale);
	
	SessionBean sesion;
	
	@PostConstruct
	public void init(){
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		ELContext elContext = faces.getELContext();
		setSesion((SessionBean) faces.getApplication().getELResolver().getValue(elContext, null, "sessionBean"));		
       
		if( isSesion_DISABLED( faces.getExternalContext() ) ){
			return;
		}
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
		
	    if(ipAddressClient == null)  	   
	    	ipAddressClient = httpServletRequest.getRemoteAddr();
	    
		listEmployment=employmentService.getListEmployByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		listBusiness=businessservice.getListBusinessByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		listIncomeByProspect=incomeService.getListIncomeByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		listExpensesByProspect=expensesService.getListExpensesByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		incomedetail = incomedetailservice.loadMaxIncomeByProspectus(sesion.getProspectus_id(),sesion.getCompany_id());
		lstSellingType=incomedetailservice.getListSellingType(sesion.getCompany_id());
		lstOperaCostType=incomedetailservice.getListOperation_costType(sesion.getCompany_id());
		thisProyect=proyectoService.getMaxProyect(sesion.getProspectus_id(), sesion.getCompany_id());
		
		totalExpensesObj = null;
		flagTotalExpense = false;
		
		if(thisProyect==null){
			thisProyect=new Proyect();
			setHasProyect(false);			  
		}
		else{
			setHasProyect(true);
		}
		lstSellingDetails=new ArrayList<IncomeDetailsBean>();
		lstOpertCostDetails=new ArrayList<IncomeDetailsBean>();
		IncomeDetailsBean incomeDetaBean=null;		
		int index=0;
		if(incomedetail!=null){			
			hasIncomeDetail=true;	
			
			sales_type=incomedetail.getSales_type();
			provider_type=incomedetail.getProvider_type();
			sales_freq=incomedetail.getSales_freq()==null?"1":incomedetail.getSales_freq();
			incomedetail.setSales_freq(sales_freq);
			
			List<SellingDetail> tempSellDeta= incomedetailservice.getSellingDetailByDetailId(sesion.getProspectus_id(),sesion.getCompany_id(),incomedetail.getIncomDetailPk().getIncome_detail_id());			
			
			if(tempSellDeta!=null && tempSellDeta.size()>0){
					
				for (SellingDetail sellingDetail : tempSellDeta) 
				{
					incomeDetaBean=new IncomeDetailsBean();
					incomeDetaBean.setSave(true);
					incomeDetaBean.setIndex(index);	
					incomeDetaBean.setTypeDetail(sellingDetail.getSellingDetailPK().getSelling_type_id());
					incomeDetaBean.setName(sellingDetail.getSellingType().getName());
					incomeDetaBean.setHs_name(sellingDetail.getSellingType().getHs_name());
					incomeDetaBean.setSellingOrOperCostDetail(sellingDetail);
					incomeDetaBean.setAmmount(sellingDetail.getAmmount());
					incomeDetaBean.setAmmount_modified(num.format(sellingDetail.getAmmount()));
					lstSellingDetails.add(incomeDetaBean);
					index++;
				}
				
			}else{
				
				if(incomedetail.getSales_freq()!=null){
					int end=1;
					int start=1;			
					
					if(incomedetail.getSales_freq().equals("1")){
						start=1;
						end=1;
					}else if(incomedetail.getSales_freq().equals("2")){
						start=2;
						end=3;
					}else if(incomedetail.getSales_freq().equals("4")){
						start=4;
						end=7;
					}else if(incomedetail.getSales_freq().equals("30")){
						start=8;
						end=14;
					}			
					
					for (int i = start; i <= end; i++) 
					{	
						SellingDetail sellingDetail=new SellingDetail();
						SellingDetailPK sellingPK=new SellingDetailPK(
																		incomedetail.getIncomDetailPk().getIncome_id(), 
																		incomedetail.getIncomDetailPk().getProspectus_id(), 
																		incomedetail.getIncomDetailPk().getCompany_id(), 
																		incomedetail.getIncomDetailPk().getIncome_detail_id()
																	);				
						sellingDetail.setAmmount(0.0);
						
						incomeDetaBean=new IncomeDetailsBean();
						incomeDetaBean.setSave(false);
						incomeDetaBean.setIndex(index);	
						
						for(SellingType sellingType:lstSellingType)
						{
							if(i==sellingType.getSellingTypePk().getSelling_type_id())
							{
								incomeDetaBean.setName(sellingType.getName());
								incomeDetaBean.setHs_name(sellingType.getHs_name());
								sellingDetail.setSellingType(sellingType);
								break;
							}
						}
						
						incomeDetaBean.setTypeDetail(i);
						sellingPK.setSelling_type_id(i);
						sellingDetail.setSellingDetailPK(sellingPK);
						incomeDetaBean.setSellingOrOperCostDetail(sellingDetail);
						incomeDetaBean.setAmmount(0.0);
						incomeDetaBean.setAmmount_modified("0");
						lstSellingDetails.add(incomeDetaBean);
						index++;
						
					}	
					
				}else{
					
					incomedetail.setSales_freq("1");
					
					incomeDetaBean=new IncomeDetailsBean();
					incomeDetaBean.setSave(false);
					incomeDetaBean.setIndex(0);			
					incomeDetaBean.setTypeDetail(1);
					incomeDetaBean.setName("Ventas mensuales");								
					incomeDetaBean.setSellingOrOperCostDetail(new SellingDetail());
					incomeDetaBean.setAmmount(0.0);
					incomeDetaBean.setAmmount_modified("0");
					lstSellingDetails.add(incomeDetaBean);
				}
			}
			
			index=0;
			List<Operating_cost> tempOperaCostDetail=incomedetailservice.getOperatingCostByDetailId(sesion.getProspectus_id(),sesion.getCompany_id(),incomedetail.getIncomDetailPk().getIncome_detail_id());
			
			if(tempOperaCostDetail!=null && tempOperaCostDetail.size()>0){				
				
				for (Operating_cost_type operating_costType : lstOperaCostType) {					
					
					incomeDetaBean=new IncomeDetailsBean();
					incomeDetaBean.setSave(false);
					
					for (Operating_cost operating_cost : tempOperaCostDetail) 
					{
						if(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id()==operating_cost.getOperatingCostPK().getOperating_cost_type_id())
						{												
							incomeDetaBean.setSave(true);
							incomeDetaBean.setIndex(index);			
							incomeDetaBean.setTypeDetail(operating_cost.getOperatingCostPK().getOperating_cost_type_id());
							incomeDetaBean.setName(operating_cost.getOperaCostType().getName());
							incomeDetaBean.setHs_name(operating_cost.getOperaCostType().getHs_name());
							incomeDetaBean.setSellingOrOperCostDetail(operating_cost);
							incomeDetaBean.setAmmount(operating_cost.getAmmount());
						
							if(operating_cost.getAmmount() != null){
								incomeDetaBean.setAmmount_modified(num.format(operating_cost.getAmmount()));
							}else{
								incomeDetaBean.setAmmount_modified(num.format(0D));
							}
							
							lstOpertCostDetails.add(incomeDetaBean);
							break;
						}
					}
					
					if(!incomeDetaBean.isSave()){
						Operating_cost operaCost=new Operating_cost();
						Operating_costPK operaCostPk=new Operating_costPK(
																		incomedetail.getIncomDetailPk().getIncome_id(), 
																		incomedetail.getIncomDetailPk().getProspectus_id(), 
																		incomedetail.getIncomDetailPk().getCompany_id(), 
																		incomedetail.getIncomDetailPk().getIncome_detail_id(),
																		operating_costType.getOperatingCostTypePk().getOperating_cost_type_id()
																		);	
						incomeDetaBean.setIndex(index);			
						incomeDetaBean.setTypeDetail(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id());
						operaCost.setOperaCostType(operating_costType);
						operaCost.setOperatingCostPK(operaCostPk);
						incomeDetaBean.setName(operating_costType.getName());
						incomeDetaBean.setHs_name(operating_costType.getHs_name());
						incomeDetaBean.setSellingOrOperCostDetail(operaCost);
						incomeDetaBean.setAmmount(0.0);
						incomeDetaBean.setAmmount_modified("0");
						lstOpertCostDetails.add(incomeDetaBean);
					}
					index++;
					
				}
				
			}else{						
				
				for (Operating_cost_type operating_costType : lstOperaCostType) 
				{
					Operating_cost operaCost=new Operating_cost();
					
					Operating_costPK operaCostPk=new Operating_costPK(
																		incomedetail.getIncomDetailPk().getIncome_id(), 
																		incomedetail.getIncomDetailPk().getProspectus_id(), 
																		incomedetail.getIncomDetailPk().getCompany_id(), 
																		incomedetail.getIncomDetailPk().getIncome_detail_id()
																	);	
					
					incomeDetaBean=new IncomeDetailsBean();
					incomeDetaBean.setSave(false);
					incomeDetaBean.setIndex(index);			
					incomeDetaBean.setTypeDetail(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id());
					operaCost.setOperaCostType(operating_costType);
					operaCostPk.setOperating_cost_type_id(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id());
					operaCost.setOperatingCostPK(operaCostPk);
					incomeDetaBean.setName(operating_costType.getName());
					incomeDetaBean.setHs_name(operating_costType.getHs_name());
					incomeDetaBean.setSellingOrOperCostDetail(operaCost);
					incomeDetaBean.setAmmount(0.0);
					incomeDetaBean.setAmmount_modified("0");
					lstOpertCostDetails.add(incomeDetaBean);
					index++;
				
				}
			}
			
			
			
			
			if(incomedetail.getProvider_amount()!=null)
				provider_amountStr = num.format(incomedetail.getProvider_amount());
			
			if(incomedetail.getProvider_total()!=null)
				provider_totalStr = num.format(incomedetail.getProvider_total());
			
			if(incomedetail.getTimes_refill()!=null)
				times_refillStr = incomedetail.getTimes_refill()+"";
								
			
			if(incomedetail.getSales_total()!=null){
				
				if(incomedetail.getSales_freq().equals("30")){
					Double d = (incomedetail.getSales_total()*4);					
					sales_totalMonthlyStr= num.format(d);
					sales_totalStr= num.format(incomedetail.getSales_total());
				}else{
					sales_totalStr = num.format(incomedetail.getSales_total());
					}			
				
			}
			
			if(incomedetail.getProfit_before_costs()!=null)
				profit_before_costsStr = num.format(incomedetail.getProfit_before_costs());
			
			if(incomedetail.getOperative_costs()!=null)
				operative_costsStr = num.format(incomedetail.getOperative_costs());
			
			if(incomedetail.getProfil_after_costs()!=null)
				profil_after_costsStr=num.format(incomedetail.getProfil_after_costs());
			
		}else{
			
			incomedetail = new IncomeDetail();
			hasIncomeDetail=false;
			
			incomeDetaBean=new IncomeDetailsBean();
			incomeDetaBean.setSave(false);
			incomeDetaBean.setIndex(0);		
			incomeDetaBean.setTypeDetail(1);
			incomeDetaBean.setName("Ventas mensuales");						
			incomeDetaBean.setSellingOrOperCostDetail(new SellingDetail());
			incomeDetaBean.setAmmount(0.0);
			incomeDetaBean.setAmmount_modified("0");
			lstSellingDetails.add(incomeDetaBean);			
			
			for (Operating_cost_type operating_costType : lstOperaCostType) {
				incomeDetaBean=new IncomeDetailsBean();
				incomeDetaBean.setSave(false);
				incomeDetaBean.setIndex(index);			
				incomeDetaBean.setTypeDetail(operating_costType.getOperatingCostTypePk().getOperating_cost_type_id());
				incomeDetaBean.setName(operating_costType.getName());
				incomeDetaBean.setHs_name(operating_costType.getHs_name());
				incomeDetaBean.setSellingOrOperCostDetail(new Operating_cost());
				incomeDetaBean.setAmmount(0.0);
				incomeDetaBean.setAmmount_modified("0");
				lstOpertCostDetails.add(incomeDetaBean);
				index++;
			}
			
			//dispSlideDetails ="none";
			dispDaily="none";
			dispWeek="none";
			dispFortnight="none";
			dispMonthly="none";			
			sales_freq=incomedetail.getSales_freq()==null?"1":incomedetail.getSales_freq();
			incomedetail.setSales_freq(sales_freq);
			
		}
			dispSlideDetails ="block";
			if(incomedetail.getSales_freq()!=null){
				if(incomedetail.getSales_freq().equals("1")){
					dispDaily = "none";
					dispWeek = "none";
					dispFortnight = "none";
					dispMonthly = "block";
				}
				if(incomedetail.getSales_freq().equals("2")){
					dispDaily = "none";
					dispWeek = "none";
					dispFortnight = "block";
					dispMonthly = "none";
				}
				if(incomedetail.getSales_freq().equals("4")){
					dispDaily = "none";
					dispWeek = "block";
					dispFortnight = "none";
					dispMonthly = "none";
				}
				if(incomedetail.getSales_freq().equals("30")){
					dispDaily = "block";
					dispWeek = "none";
					dispFortnight = "none";
					dispMonthly = "none";
				}
				
			}else{
				dispDaily = "none";
				dispWeek = "none";
				dispFortnight = "none";
				dispMonthly = "none";
			}
			
			
			/*
			if(incomedetail.getCost_accountant()!=null)
				cost_accountantStr = num.format(incomedetail.getCost_accountant());
			if(incomedetail.getCost_electricity()!=null)
				cost_electricityStr = num.format(incomedetail.getCost_electricity());
			if(incomedetail.getCost_employees()!=null)
				cost_employeesStr = num.format(incomedetail.getCost_employees());
			if(incomedetail.getCost_gas()!=null)
				cost_gasStr = num.format(incomedetail.getCost_gas());
			if(incomedetail.getCost_maintenance()!=null)
				cost_maintenanceStr = num.format(incomedetail.getCost_maintenance());
			if(incomedetail.getCost_other()!=null)
				cost_otherStr = num.format(incomedetail.getCost_other());
			if(incomedetail.getCost_phone()!=null)
				cost_phoneStr = num.format(incomedetail.getCost_phone());
			if(incomedetail.getCost_rent()!=null)
				cost_rentStr = num.format(incomedetail.getCost_rent());
			if(incomedetail.getCost_taxes()!=null)
				cost_taxesStr = num.format(incomedetail.getCost_taxes());
			if(incomedetail.getCost_transport()!=null)
				cost_transportStr = num.format(incomedetail.getCost_transport());
			if(incomedetail.getCost_water()!=null)
				cost_waterStr = num.format(incomedetail.getCost_water());
			if(incomedetail.getProfil_after_costs()!=null)
				profil_after_costsStr = num.format(incomedetail.getProfil_after_costs());*/
			
			
		/*}else{
			incomedetail = new IncomeDetail();
			hasIncomeDetail=false;
			dispSlideDetails ="none";
			dispDaily="none";
			dispWeek="none";
			dispFortnight="none";
			dispMonthly="none";
		}*/
		
		for(Income incomReg: listIncomeByProspect){
			switch (incomReg.getIncome_type_id()) {
			case 1:
				ammountWagesSalStr=num.format(incomReg.getAmmount());
				totalIncome+=incomReg.getAmmount();
				break;
			case 2:
				ammountBusComStr=num.format(incomReg.getAmmount());
				totalIncome+=incomReg.getAmmount();
				income_id = incomReg.getIncomePk().getIncome_id();
				break;
			case 3:
				ammountOtherFamStr=num.format(incomReg.getAmmount());
				totalIncome+=incomReg.getAmmount();
				break;
			case 4:
				ammountOtherStr=num.format(incomReg.getAmmount());
				totalIncome+=incomReg.getAmmount();
				if(incomReg.getAmmount()>0){
					descriptionOtherIncome= incomReg.getDescription()!=null?incomReg.getDescription():"";
				}
				break;
			case 5:
				ammountDeductionsSalStr=num.format(incomReg.getAmmount());
				totalIncome= totalIncome - incomReg.getAmmount();
				break;
			case 6:
				totalammountWagesSalStr=num.format(incomReg.getAmmount());
				//totalIncome+=incomReg.getAmmount();
				break;
			default:
				break;
			}
			
		}
		totalIncomeStr=num.format(totalIncome);
		totalExpenses=0D;
		for(Expenses expReg: listExpensesByProspect){
			switch (expReg.getExpense_type_id()) {
					case 1:
						ammountFoodStr=num.format(expReg.getAmmount());
						totalExpenses+=expReg.getAmmount();
						break;
					case 2:
						ammountMaintenancHousStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 3:
						ammountLightStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 4:
						ammountPhoneStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 5:
						ammountIncomeStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 6:
						ammountSeriesStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 7:
						ammountCreditsStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 8:
						ammountSchoolStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 9:
						ammountMedicalStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 10:
						ammountEntertainmentStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 11:
						ammountFoodWorkStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 12:
						ammountGasolineTranspStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 13:
						ammountGasStr=num.format(expReg.getAmmount());	
						totalExpenses+=expReg.getAmmount();
						break;
					case 14:
						totalExpensesObj = new Expenses(); 
						
						totalExpensesObj.setAmmount( expReg.getAmmount() );
						totalExpensesObj.setAmmount_minus( expReg.getAmmount_minus() );
						totalExpensesObj.setAmmount_modified( expReg.getAmmount_modified() );
						totalExpensesObj.setDatetime_modified( expReg.getDatetime_modified() );
						totalExpensesObj.setDescription( expReg.getDescription() );
						totalExpensesObj.setExpense_type_id( expReg.getExpense_type_id() );
						totalExpensesObj.setExpensesPk( expReg.getExpensesPk() );
						totalExpensesObj.setExpensesType( expReg.getExpensesType() );
						totalExpensesObj.setProspectus_id_modified( expReg.getProspectus_id_modified() );
						flagTotalExpense=true;
						break;
					default:
						break;
			}
			
		}
		
		if( totalExpensesObj == null ){
			
			ExpensesPK expensesPK  = new ExpensesPK();
			expensesPK.setCompany_id( sesion.getCompany_id() );
			expensesPK.setExpense_id(14);
			expensesPK.setProspectus_id( sesion.getProspectus_id() );
			
			totalExpensesObj = new Expenses(); 
			totalExpensesObj.setExpensesPk(expensesPK);
			totalExpensesObj.setAmmount(totalExpenses);
			totalExpensesObj.setExpense_type_id(14);
			
			flagTotalExpense=false;
			
		}
		
		if(totalExpenses > 0){
		
		totalExpensesStr=num.format(totalExpenses);
		
		}else{
			totalExpensesStr=num.format(totalExpensesObj.getAmmount());
		}
		
		if((totalIncome-totalExpenses)<0){
			excessStr="-"+num.format((-(totalIncome-totalExpenses)));
		}
		else{
			excessStr=num.format(totalIncome-totalExpenses);
		}
		
		if(listEmployment.size() !=0){
			wagesSalaryDisp="block";
			hasEmployment=true;
		}
		
		if(listBusiness.size() !=0){
			businessCompanyDisp="block";
			hasBusiness=true;
		}
		
		if( hasEmployment || ( !hasBusiness && !hasBusiness  ) ){
			if(listEmployment != null && listEmployment.size()>0){
				
				employment = listEmployment.get(0);
				
			}else{
				
				EmploymentPK epk = new EmploymentPK();
				epk.setCompany_id(sesion.getCompany_id());
				epk.setProspectus_id(sesion.getProspectus_id());
				epk.setEmployment_id(1);
				
				employment = new Employment();
				
				employment.setEmploymentPK(epk);
				
				employmentService.add(employment);
			}
		}
		
		if(hasBusiness && income_id == null){
			checkIncome();
		}
		
	}

	public EmploymentService getEmploymentService() {
		return employmentService;
	}

	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	public BusinessService getBusinessservice() {
		return businessservice;
	}

	public void setBusinessservice(BusinessService businessservice) {
		this.businessservice = businessservice;
	}

	public String getWagesSalaryDisp() {
		return wagesSalaryDisp;
	}

	public void setWagesSalaryDisp(String wagesSalaryDisp) {
		this.wagesSalaryDisp = wagesSalaryDisp;
	}

	public String getBusinessCompanyDisp() {
		return businessCompanyDisp;
	}

	public void setBusinessCompanyDisp(String businessCompanyDisp) {
		this.businessCompanyDisp = businessCompanyDisp;
	}

	public boolean isHasEmployment() {
		return hasEmployment;
	}

	public void setHasEmployment(boolean hasEmployment) {
		this.hasEmployment = hasEmployment;
	}

	public boolean isHasBusiness() {
		return hasBusiness;
	}

	public void setHasBusiness(boolean hasBusiness) {
		this.hasBusiness = hasBusiness;
	}

	public List<Employment> getListEmployment() {
		return listEmployment;
	}

	public void setListEmployment(List<Employment> listEmployment) {
		this.listEmployment = listEmployment;
	}

	public List<Business> getListBusiness() {
		return listBusiness;
	}

	public void setListBusiness(List<Business> listBusiness) {
		this.listBusiness = listBusiness;
	}
	
	
	public IncomeService getIncomeService() {
		return incomeService;
	}

	public Income getThisIncome() {
		return thisIncome;
	}

	public String getAmmountWagesSalStr() {
		return ammountWagesSalStr;
	}

	public String getAmmountBusComStr() {
		return ammountBusComStr;
	}

	public String getAmmountOtherFamStr() {
		return ammountOtherFamStr;
	}

	public String getAmmountOtherStr() {
		return ammountOtherStr;
	}

	public double getAmmountWagesSal() {
		return ammountWagesSal;
	}

	public double getAmmountBusCom() {
		return ammountBusCom;
	}

	public double getAmmountOtherFam() {
		return ammountOtherFam;
	}

	public double getAmmountOther() {
		return ammountOther;
	}

	public int getTypeIncomeID() {
		return typeIncomeID;
	}

	public List<Income> getListIncomeByProspect() {
		return listIncomeByProspect;
	}

	public void setIncomeService(IncomeService incomeService) {
		this.incomeService = incomeService;
	}

	public void setThisIncome(Income thisIncome) {
		this.thisIncome = thisIncome;
	}

	public void setAmmountWagesSalStr(String ammountWagesSalStr) {
		this.ammountWagesSalStr = ammountWagesSalStr;
		setTypeIncomeID(1);
		setAmmountWagesSal(Float.parseFloat(ammountWagesSalStr.replaceAll(",", "")));
	}

	public void setAmmountBusComStr(String ammountBusComStr) {
		this.ammountBusComStr = ammountBusComStr;
		setTypeIncomeID(2);
		setAmmountBusCom(Float.parseFloat(ammountBusComStr.replaceAll(",", "")));
	}

	public void setAmmountOtherFamStr(String ammountOtherFamStr) {
		this.ammountOtherFamStr = ammountOtherFamStr;
		setTypeIncomeID(3);
		setAmmountOtherFam(Float.parseFloat(ammountOtherFamStr.replaceAll(",", "")));
	}

	public void setAmmountOtherStr(String ammountOtherStr) {
		this.ammountOtherStr = ammountOtherStr;
		setTypeIncomeID(4);
		setAmmountOther(Float.parseFloat(ammountOtherStr.replaceAll(",", "")));
	}

	public void setAmmountWagesSal(double ammountWagesSal) {
		this.ammountWagesSal = ammountWagesSal;
	}

	public void setAmmountBusCom(double ammountBusCom) {
		this.ammountBusCom = ammountBusCom;
	}

	public void setAmmountOtherFam(double ammountOtherFam) {
		this.ammountOtherFam = ammountOtherFam;
	}

	public void setAmmountOther(double ammountOther) {
		this.ammountOther = ammountOther;
	}

	public void setTypeIncomeID(int typeIncomeID) {
		this.typeIncomeID = typeIncomeID;
	}

	public void setListIncomeByProspect(List<Income> listIncomeByProspect) {
		this.listIncomeByProspect = listIncomeByProspect;
	}
	
	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getTotalIncomeStr() {
		return totalIncomeStr;
	}

	public void setTotalIncomeStr(String totalIncomeStr) {
		this.totalIncomeStr = totalIncomeStr;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}
	
	//--------------EXPENSES GETTER AND SETTER-------


	public ExpensesService getExpensesService() {
		return expensesService;
	}

	public Expenses getThisExpenses() {
		return thisExpenses;
	}

	public String getAmmountFoodStr() {
		return ammountFoodStr;
	}

	public String getAmmountMaintenancHousStr() {
		return ammountMaintenancHousStr;
	}

	public String getAmmountLightStr() {
		return ammountLightStr;
	}

	public String getAmmountPhoneStr() {
		return ammountPhoneStr;
	}

	public String getAmmountIncomeStr() {
		return ammountIncomeStr;
	}

	public String getAmmountSeriesStr() {
		return ammountSeriesStr;
	}

	public String getAmmountCreditsStr() {
		return ammountCreditsStr;
	}

	public String getAmmountSchoolStr() {
		return ammountSchoolStr;
	}

	public String getAmmountMedicalStr() {
		return ammountMedicalStr;
	}

	public String getAmmountEntertainmentStr() {
		return ammountEntertainmentStr;
	}

	public String getAmmountFoodWorkStr() {
		return ammountFoodWorkStr;
	}

	public String getAmmountGasolineTranspStr() {
		return ammountGasolineTranspStr;
	}
	
	public String getAmmountGasStr() {
		return ammountGasStr;
	}

	public double getAmmountFood() {
		return ammountFood;
	}

	public double getAmmountMaintenancHous() {
		return ammountMaintenancHous;
	}

	public double getAmmountLight() {
		return ammountLight;
	}

	public double getAmmountPhone() {
		return ammountPhone;
	}

	public double getAmmountIncome() {
		return ammountIncome;
	}

	public double getAmmountSeries() {
		return ammountSeries;
	}

	public double getAmmountCredits() {
		return ammountCredits;
	}

	public double getAmmountSchool() {
		return ammountSchool;
	}

	public double getAmmountMedical() {
		return ammountMedical;
	}

	public double getAmmountEntertainment() {
		return ammountEntertainment;
	}

	public double getAmmountFoodWork() {
		return ammountFoodWork;
	}

	public double getAmmountGasolineTransp() {
		return ammountGasolineTransp;
	}
    
	public double getAmmountGas() {
		return ammountGas;
	}

	public int getTypeExpensesID() {
		return typeExpensesID;
	}

	public String getExcessStr() {
		return excessStr;
	}

	public double getTotalExpenses() {
		return totalExpenses;
	}

	public String getTotalExpensesStr() {
		if( totalExpensesObj != null ){
		totalExpensesStr = num.format( totalExpensesObj.getAmmount() );
		}
		return totalExpensesStr;
	}

	public List<Expenses> getListExpensesByProspect() {
		return listExpensesByProspect;
	}

	public void setExpensesService(ExpensesService expensesService) {
		this.expensesService = expensesService;
	}

	public void setThisExpenses(Expenses thisExpenses) {
		this.thisExpenses = thisExpenses;
	}
	

	public List<IncomeDetailsBean> getLstSellingDetails() {
		return lstSellingDetails;
	}

	public void setLstSellingDetails(List<IncomeDetailsBean> lstSellingDetails) {
		this.lstSellingDetails = lstSellingDetails;
	}

	public List<IncomeDetailsBean> getLstOpertCostDetails() {
		return lstOpertCostDetails;
	}

	public void setLstOpertCostDetails(List<IncomeDetailsBean> lstOpertCostDetails) {
		this.lstOpertCostDetails = lstOpertCostDetails;
	}

	public void setAmmountFoodStr(String ammountFoodStr) {
		this.ammountFoodStr = ammountFoodStr;
		setTypeExpensesID(1);		
		setAmmountFood(Float.parseFloat(ammountFoodStr.replaceAll(",", "")));
	}

	public void setAmmountMaintenancHousStr(String ammountMaintenancHousStr) {
		this.ammountMaintenancHousStr = ammountMaintenancHousStr;
		setTypeExpensesID(2);		
		setAmmountMaintenancHous(Float.parseFloat(ammountMaintenancHousStr.replaceAll(",", "")));
	}

	public void setAmmountLightStr(String ammountLightStr) {
		this.ammountLightStr = ammountLightStr;
		setTypeExpensesID(3);		
		setAmmountLight(Float.parseFloat(ammountLightStr.replaceAll(",", "")));
	}

	public void setAmmountPhoneStr(String ammountPhoneStr) {
		this.ammountPhoneStr = ammountPhoneStr;
		setTypeExpensesID(4);		
		setAmmountPhone(Float.parseFloat(ammountPhoneStr.replaceAll(",", "")));
	}

	public void setAmmountIncomeStr(String ammountIncomeStr) {
		this.ammountIncomeStr = ammountIncomeStr;
		setTypeExpensesID(5);		
		setAmmountIncome(Float.parseFloat(ammountIncomeStr.replaceAll(",", "")));
	}

	public void setAmmountSeriesStr(String ammountSeriesStr) {
		this.ammountSeriesStr = ammountSeriesStr;
		setTypeExpensesID(6);		
		setAmmountSeries(Float.parseFloat(ammountSeriesStr.replaceAll(",", "")));
	}

	public void setAmmountCreditsStr(String ammountCreditsStr) {
		this.ammountCreditsStr = ammountCreditsStr;
		setTypeExpensesID(7);		
		setAmmountCredits(Float.parseFloat(ammountCreditsStr.replaceAll(",", "")));
	}

	public void setAmmountSchoolStr(String ammountSchoolStr) {
		this.ammountSchoolStr = ammountSchoolStr;
		setTypeExpensesID(8);		
		setAmmountSchool(Float.parseFloat(ammountSchoolStr.replaceAll(",", "")));
	}

	public void setAmmountMedicalStr(String ammountMedicalStr) {
		this.ammountMedicalStr = ammountMedicalStr;
		setTypeExpensesID(9);		
		setAmmountMedical(Float.parseFloat(ammountMedicalStr.replaceAll(",", "")));
	}

	public void setAmmountEntertainmentStr(String ammountEntertainmentStr) {
		this.ammountEntertainmentStr = ammountEntertainmentStr;
		setTypeExpensesID(10);		
		setAmmountEntertainment(Float.parseFloat(ammountEntertainmentStr.replaceAll(",", "")));
	}

	public void setAmmountFoodWorkStr(String ammountFoodWorkStr) {
		this.ammountFoodWorkStr = ammountFoodWorkStr;
		setTypeExpensesID(11);		
		setAmmountFoodWork(Float.parseFloat(ammountFoodWorkStr.replaceAll(",", "")));
	}

	public void setAmmountGasolineTranspStr(String ammountGasolineTranspStr) {
		this.ammountGasolineTranspStr = ammountGasolineTranspStr;
		setTypeExpensesID(12);		
		setAmmountGasolineTransp(Float.parseFloat(ammountGasolineTranspStr.replaceAll(",", "")));
	}

	public void setAmmountGasStr(String ammountGasStr) {
		this.ammountGasStr = ammountGasStr;
		setTypeExpensesID(13);		
		setAmmountGas(Float.parseFloat(ammountGasStr.replaceAll(",", "")));
	}

	public void setAmmountFood(double ammountFood) {
		this.ammountFood = ammountFood;
	}

	public void setAmmountMaintenancHous(double ammountMaintenancHous) {
		this.ammountMaintenancHous = ammountMaintenancHous;
	}

	public void setAmmountLight(double ammountLight) {
		this.ammountLight = ammountLight;
	}

	public void setAmmountPhone(double ammountPhone) {
		this.ammountPhone = ammountPhone;
	}

	public void setAmmountIncome(double ammountIncome) {
		this.ammountIncome = ammountIncome;
	}

	public void setAmmountSeries(double ammountSeries) {
		this.ammountSeries = ammountSeries;
	}

	public void setAmmountCredits(double ammountCredits) {
		this.ammountCredits = ammountCredits;
	}

	public void setAmmountSchool(double ammountSchool) {
		this.ammountSchool = ammountSchool;
	}

	public void setAmmountMedical(double ammountMedical) {
		this.ammountMedical = ammountMedical;
	}

	public void setAmmountEntertainment(double ammountEntertainment) {
		this.ammountEntertainment = ammountEntertainment;
	}

	public void setAmmountFoodWork(double ammountFoodWork) {
		this.ammountFoodWork = ammountFoodWork;
	}

	public void setAmmountGasolineTransp(double ammountGasolineTransp) {
		this.ammountGasolineTransp = ammountGasolineTransp;
	}

	public void setAmmountGas(double ammountGas) {
		this.ammountGas = ammountGas;
	}

	public void setTypeExpensesID(int typeExpensesID) {
		this.typeExpensesID = typeExpensesID;
	}

	public void setExcessStr(String excessStr) {
		this.excessStr = excessStr;
	}

	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public void setTotalExpensesStr(String totalExpensesStr) {
		this.totalExpensesStr = totalExpensesStr;
		
		totalExpensesObj.setAmmount( Double.parseDouble( totalExpensesStr.replaceAll(",", "") ) ); 
		
	}

	public void setListExpensesByProspect(List<Expenses> listExpensesByProspect) {
		this.listExpensesByProspect = listExpensesByProspect;
	}
	public String getDescriptionOtherIncome() {
		return descriptionOtherIncome;
	}

	public void setDescriptionOtherIncome(String descriptionOtherIncome) {
		this.descriptionOtherIncome = descriptionOtherIncome;
		setTypeIncomeID(4);
		setAmmountOther(Float.parseFloat(getAmmountOtherStr().replaceAll(",", "")));
	}
	
	public ProyectService getProyectoService() {
		return proyectoService;
	}

	public void setProyectoService(ProyectService proyectoService) {
		this.proyectoService = proyectoService;
	}
	
	public Proyect getThisProyect() {
		return thisProyect;
	}

	public void setThisProyect(Proyect thisProyect) {
		this.thisProyect = thisProyect;
	}

	public boolean isHasProyect() {
		return hasProyect;
	}

	public void setHasProyect(boolean hasProyect) {
		this.hasProyect = hasProyect;
	}

	public void updateOrSaveIncome(){
		boolean flagSave=false;
		boolean flagUpdate=false;
		Double preIncomeValue=null;
		
		setThisIncome(incomeService.getIncomeByTypeIncomeID(sesion.getProspectus_id(),sesion.getCompany_id(), getTypeIncomeID()));
		if(getThisIncome()==null){
			//insertar
			setThisIncome(new Income());
			IncomePK incomePk=new IncomePK(getSesion().getProspectus_id(),getSesion().getCompany_id());
			IncomeType incomeType=incomeTypeService.getIncomeTypeById(new IncomeTypePK(getTypeIncomeID(), sesion.getCompany_id()));
			getThisIncome().setIncomeType(incomeType);
			switch (getTypeIncomeID()) {
				case 1:	
					if(hasEmployment){
					getThisIncome().setIncomePk(incomePk);
					getThisIncome().setAmmount(getAmmountWagesSal());
					getThisIncome().setIncome_type_id(getTypeIncomeID());
					flagSave=incomeService.addIncome(getThisIncome(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_income","ammount", "", getAmmountWagesSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());						
						}
					}
					break;
				case 2:	
					if(hasBusiness){
					getThisIncome().setIncomePk(incomePk);
					getThisIncome().setAmmount(getAmmountBusCom());
					getThisIncome().setIncome_type_id(getTypeIncomeID());
					flagSave=incomeService.addIncome(getThisIncome(), getSesion().getProspectus_id(), getSesion().getCompany_id());	
					income_id = getThisIncome().getIncomePk().getIncome_id();
					}
					break;
				case 3:		
					getThisIncome().setIncomePk(incomePk);
					getThisIncome().setAmmount(getAmmountOtherFam());
					getThisIncome().setIncome_type_id(getTypeIncomeID());
					flagSave=incomeService.addIncome(getThisIncome(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_income","ammount","", getAmmountOtherFam()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());						
						}
					break;
				case 4:		
					getThisIncome().setIncomePk(incomePk);
					getThisIncome().setAmmount(getAmmountOther());
					getThisIncome().setIncome_type_id(getTypeIncomeID());					
					flagSave=incomeService.addIncome(getThisIncome(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_income","ammount","", getAmmountOther()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					break;
				case 5:		
					getThisIncome().setIncomePk(incomePk);
					getThisIncome().setAmmount(getAmmountDeductionsSal());
					getThisIncome().setIncome_type_id(getTypeIncomeID());					
					flagSave=incomeService.addIncome(getThisIncome(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_income","ammount","", getAmmountDeductionsSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					break;
				case 6:		
					getThisIncome().setIncomePk(incomePk);
					getThisIncome().setAmmount(getTotalammountWagesSal());
					getThisIncome().setIncome_type_id(getTypeIncomeID());					
					flagSave=incomeService.addIncome(getThisIncome(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_income","ammount","", getTotalammountWagesSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					break;
				default:
					break;
			}

		}
		else{
			//Actualiza
			switch (getTypeIncomeID()) {
				case 1:
					preIncomeValue=getThisIncome().getAmmount();
					getThisIncome().setAmmount(getAmmountWagesSal());
					flagUpdate=incomeService.updateIncome(getThisIncome());
					if(flagUpdate){
						if(preIncomeValue==null){
							saveChangeData("gn_income","ammount","", getAmmountWagesSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}else if(!preIncomeValue.equals(getAmmountWagesSal())){
							saveChangeData("gn_income","ammount",preIncomeValue+"", getAmmountWagesSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					}
					break;
				case 2:
					preIncomeValue=getThisIncome().getAmmount();
					getThisIncome().setAmmount(getAmmountBusCom());
					flagUpdate=incomeService.updateIncome(getThisIncome());
					income_id = getThisIncome().getIncomePk().getIncome_id();
					break;
				case 3:
					preIncomeValue=getThisIncome().getAmmount();
					getThisIncome().setAmmount(getAmmountOtherFam());
					flagUpdate=incomeService.updateIncome(getThisIncome());
					if(flagUpdate){
						if(preIncomeValue==null){
							saveChangeData("gn_income","ammount","", getAmmountOtherFam()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}else if(!preIncomeValue.equals(getAmmountOtherFam())){
							saveChangeData("gn_income","ammount",preIncomeValue+"", getAmmountOtherFam()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					}
					break;
				case 4:
					preIncomeValue=getThisIncome().getAmmount();
					getThisIncome().setAmmount(getAmmountOther());
					if(descriptionOtherIncome!=null && getThisIncome().getAmmount()>0 )
						getThisIncome().setDescription(getDescriptionOtherIncome());
					else
						setDescriptionOtherIncome(null);
					
					flagUpdate=incomeService.updateIncome(getThisIncome());
					if(flagUpdate){
						if(preIncomeValue==null){
							saveChangeData("gn_income","ammount","", getAmmountOther()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}else if(!preIncomeValue.equals(getAmmountOther())){
							saveChangeData("gn_income","ammount",preIncomeValue+"", getAmmountOther()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					}
					break;
				case 5:
					preIncomeValue=getThisIncome().getAmmount();
					getThisIncome().setAmmount(getAmmountDeductionsSal());
					flagUpdate=incomeService.updateIncome(getThisIncome());
					if(flagUpdate){
						if(preIncomeValue==null){
							saveChangeData("gn_income","ammount","", getAmmountDeductionsSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}else if(!preIncomeValue.equals(getAmmountWagesSal())){
							saveChangeData("gn_income","ammount",preIncomeValue+"", getAmmountDeductionsSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					}
					break;
				case 6:
					preIncomeValue=getThisIncome().getAmmount();
					getThisIncome().setAmmount(getTotalammountWagesSal());
					flagUpdate=incomeService.updateIncome(getThisIncome());
					if(flagUpdate){
						if(preIncomeValue==null){
							saveChangeData("gn_income","ammount","", getTotalammountWagesSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}else if(!preIncomeValue.equals(getAmmountWagesSal())){
							saveChangeData("gn_income","ammount",preIncomeValue+"", getTotalammountWagesSal()+"","El usuario modificó "+getThisIncome().getIncomeType().getName());
						}
					}
					break;
				default:
					break;
			}			
			
		}
		if(flagSave)
			log.info("====>>>> Income saved successfully");
		else
			log.info("====>>>> Income  NO saved");	
		if(flagUpdate)
			log.info("====>>>> Update Income successfully");
		else
			log.info("====>>>> Income  NO update");
		
	}
	
	public void updateOrSaveExpenses(){
		boolean flagSave=false;
		boolean flagUpdate=false;
		Double preExpenseValue=null;
		setThisExpenses(expensesService.getExpensesByTypeExpensesID(sesion.getProspectus_id(),sesion.getCompany_id(), getTypeExpensesID()));
		if(getThisExpenses()==null){
			//Save
			setThisExpenses(new Expenses());
			ExpensesPK expensesPk=new ExpensesPK(getSesion().getProspectus_id(),getSesion().getCompany_id());
			ExpensesType expensesType=expensesTypeService.getExpensesTypeById(new ExpensesTypePK(getTypeExpensesID(), sesion.getCompany_id()));
			getThisExpenses().setExpensesType(expensesType);
			
			switch (getTypeExpensesID()) {
				case 1:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountFood());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountFood()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 2:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountMaintenancHous());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());					
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountMaintenancHous()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 3:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountLight());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountLight()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 4:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountPhone());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
					if(flagSave){
						saveChangeData("gn_expense","ammount", "", getAmmountPhone()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
					}
					break;
				case 5:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountIncome());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountIncome()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 6:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountSeries());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountSeries()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 7:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountCredits());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountCredits()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 8:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountSchool());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountSchool()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 9:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountMedical());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountMedical()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 10:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountEntertainment());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountEntertainment()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 11:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountFoodWork());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountFoodWork()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 12:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountGasolineTransp());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountGasolineTransp()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				case 13:
					getThisExpenses().setExpensesPk(expensesPk);
					getThisExpenses().setAmmount(getAmmountGas());
					getThisExpenses().setExpense_type_id(getTypeExpensesID());
					flagSave=expensesService.addExpenses(getThisExpenses(), getSesion().getProspectus_id(), getSesion().getCompany_id());
						if(flagSave){
							saveChangeData("gn_expense","ammount", "", getAmmountGas()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());						
						}
					break;
				default:
					break;
				}
		}
		else{
			//Update
			switch (getTypeExpensesID()) {
				case 1:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountFood());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
						if(flagUpdate){
							if(preExpenseValue==null){
								saveChangeData("gn_expense","ammount","", getAmmountFood()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
							}else if(!preExpenseValue.equals(getAmmountFood())){
								saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountFood()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
							}
						}
					break;
				case 2:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountMaintenancHous());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountMaintenancHous()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountMaintenancHous())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountMaintenancHous()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 3:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountLight());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountLight()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountLight())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountLight()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 4:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountPhone());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountPhone()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountPhone())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountPhone()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 5:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountIncome());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountIncome()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountIncome())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountIncome()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 6:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountSeries());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountSeries()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountSeries())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountSeries()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 7:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountCredits());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountCredits()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountCredits())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountCredits()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 8:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountSchool());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountSchool()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountSchool())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountSchool()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 9:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountMedical());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountMedical()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountMedical())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountMedical()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 10:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountEntertainment());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountEntertainment()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountEntertainment())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountEntertainment()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 11:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountFoodWork());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountFoodWork()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountFoodWork())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountFoodWork()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 12:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountGasolineTransp());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountGasolineTransp()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountGasolineTransp())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountGasolineTransp()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				case 13:
					preExpenseValue=getThisExpenses().getAmmount();
					getThisExpenses().setAmmount(getAmmountGas());
					flagUpdate=expensesService.updateExpenses(getThisExpenses());
					if(flagUpdate){
						if(preExpenseValue==null){
							saveChangeData("gn_expense","ammount","", getAmmountGas()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}else if(!preExpenseValue.equals(getAmmountGas())){
							saveChangeData("gn_expense","ammount",preExpenseValue+"", getAmmountGas()+"","El usuario modificó "+getThisExpenses().getExpensesType().getName());
						}
					}
					break;
				default:
					break;
				}
		}
		

		if(flagSave)
			log.info("====>>>> Expenses saved successfully");
		else
			log.info("====>>>> Expenses NO saved");	
		if(flagUpdate)
			log.info("====>>>> Update Expenses successfully");
		else
			log.info("====>>>> Expenses  NO update");
		
		
	}

	public IncomeDetailService getIncomedetailservice() {
		return incomedetailservice;
	}

	public void setIncomedetailservice(IncomeDetailService incomedetailservice) {
		this.incomedetailservice = incomedetailservice;
	}

	public IncomeDetail getIncomedetail() {
		return incomedetail;
	}

	public void setIncomedetail(IncomeDetail incomedetail) {
		this.incomedetail = incomedetail;
	}

	public boolean isHasIncomeDetail() {
		return hasIncomeDetail;
	}

	public void setHasIncomeDetail(boolean hasIncomeDetail) {
		this.hasIncomeDetail = hasIncomeDetail;
	}	
	public String getSales_type() {
		return sales_type;
	}
	public void setSales_type(String sales_type) {
		this.sales_type = sales_type;
	}
	public String getProvider_type() {
		return provider_type;
	}
	public void setProvider_type(String provider_type) {
		this.provider_type = provider_type;
	}
	public String getSales_freq() {
		return sales_freq;
	}
	public void setSales_freq(String sales_freq) {
		this.sales_freq = sales_freq;
	}

	public void saveIncomeDetail(){
		if(!hasIncomeDetail){
			
			IncomeDetailPK inPK = new IncomeDetailPK();
			
			inPK.setCompany_id(sesion.getCompany_id());
			if( income_id != null )
				inPK.setIncome_id(income_id);
			
			inPK.setProspectus_id(sesion.getProspectus_id());
			incomedetail.setIncomDetailPk(inPK);
			
			if(getSales_type()!=null && getSales_type()!="" ){
				saveChangeData("gn_income_detail", "sales_type","", getSales_type().equals("C")?"Contado":getSales_type().equals("A")?"Abonos":"Mixto","El usuario modificó su tipo de venta" );
			}
			if(getProvider_type()!=null && getProvider_type()!="" ){
				saveChangeData("gn_income_detail", "provider_type","", getProvider_type().equals("C")?"Contado":getProvider_type().equals("A")?"Abonos":"Mixto","El usuario modificó su tipo de compra" );
			}
			if(getSales_freq()!=null && getSales_freq()!="" ){
				String result="";
				if(getSales_freq().equals("1")){
					result="Mensual";
				}else if(getSales_freq().equals("2")){
					result="Quincenal";
				}else if(getSales_freq().equals("4")){
					result="Semanal";
				}else if(getSales_freq().equals("30")){
					result="Diario";
					
				}
				saveChangeData("gn_income_detail", "sales_freq","", result,"El usuario modificó su frecuencia de venta" );
			}
			
			incomedetail.setSales_type(getSales_type()!=null && getSales_type()!=""?getSales_type():null);
			incomedetail.setProvider_type(getProvider_type()!=null && getProvider_type()!=""?getProvider_type():null);
			incomedetail.setSales_freq(getSales_freq()!=null && getSales_freq()!=""?getSales_freq():null);
			
			if(incomedetailservice.saveIncomeDeatil(incomedetail)){
				incomedetail = incomedetailservice.loadMaxIncomeByProspectus(sesion.getProspectus_id(),sesion.getCompany_id());
				hasIncomeDetail = true;
			}
		}else{
			if(getSales_type()!=null && getSales_type()!="" ){
				if(incomedetail.getSales_type()==null){
					saveChangeData("gn_income_detail", "sales_type","", getSales_type().equals("C")?"Contado":getSales_type().equals("A")?"Abonos":"Mixto","El usuario modificó su tipo de venta" );
				}else if(!getSales_type().equals(incomedetail.getSales_type())){
					saveChangeData("gn_income_detail", "sales_type",incomedetail.getSales_type()==null?"":incomedetail.getSales_type().equals("C")?"Contado":incomedetail.getSales_type().equals("A")?"Abonos":"Mixto", getSales_type().equals("C")?"Contado":getSales_type().equals("A")?"Abonos":"Mixto","El usuario modificó su tipo de venta" );
				}								
			}
			
			if(getProvider_type()!=null && getProvider_type()!="" ){
				if(incomedetail.getProvider_type()==null){
					saveChangeData("gn_income_detail", "provider_type","", getProvider_type().equals("C")?"Contado":getProvider_type().equals("A")?"Abonos":"Mixto","El usuario modificó su tipo de compra" );
				}else if(!getProvider_type().equals(incomedetail.getProvider_type())){
					saveChangeData("gn_income_detail", "provider_type",incomedetail.getProvider_type()==null?"":incomedetail.getProvider_type().equals("C")?"Contado":incomedetail.getProvider_type().equals("A")?"Abonos":"Mixto", getProvider_type().equals("C")?"Contado":getProvider_type().equals("A")?"Abonos":"Mixto","El usuario modificó su tipo de compra" );
				}
				
			}
			if(getSales_freq()!=null && getSales_freq()!="" ){
				String newVal="";
				if(getSales_freq()!=null){
					if(getSales_freq().equals("1")){
						newVal="Mensual";
					}else if(getSales_freq().equals("2")){
						newVal="Quincenal";
					}else if(getSales_freq().equals("4")){
						newVal="Semanal";
					}else if(getSales_freq().equals("30")){
						newVal="Diario";					
					}
				}
				
				String preValue="";
				
				if(incomedetail.getSales_freq()!=null){
					if(incomedetail.getSales_freq().equals("1")){
						newVal="Mensual";
					}else if(incomedetail.getSales_freq().equals("2")){
						newVal="Quincenal";
					}else if(incomedetail.getSales_freq().equals("4")){
						newVal="Semanal";
					}else if(incomedetail.getSales_freq().equals("30")){
						newVal="Diario";					
					}				
				}
				if(preValue!="" && newVal!=""){
					if(!preValue.equals(newVal))
						saveChangeData("gn_income_detail", "sales_freq",preValue, newVal,"El usuario modificó su frecuencia de venta" );
				}
					
			}
			
			incomedetail.setSales_type(getSales_type()!=null && getSales_type()!=""?getSales_type():null);
			incomedetail.setProvider_type(getProvider_type()!=null && getProvider_type()!=""?getProvider_type():null);
			incomedetail.setSales_freq(getSales_freq()!=null && getSales_freq()!=""?getSales_freq():null);
			
			incomedetailservice.updateMaxIncomeDeatil(incomedetail);
		}
	}
	
	public void saveOrUpdate(IncomeDetailsBean incomDetailBean){
		
		System.out.println(" saveOrUpdate INCOME ");
		
		Double newAmmount=0D;
		
		if(incomDetailBean.getAmmount_modified() == null || incomDetailBean.getAmmount_modified().trim().length() == 0) { 
			 newAmmount=0D;
		}else {
			 newAmmount= Double.parseDouble(incomDetailBean.getAmmount_modified().replace(",", ""));	
		}		
		
		if(incomDetailBean.getSellingOrOperCostDetail() instanceof SellingDetail){
			IncomeDetailsBean updateThis= lstSellingDetails.get(incomDetailBean.getIndex());
			SellingDetail updateSellDetail=(SellingDetail) updateThis.getSellingOrOperCostDetail();
			Double preValue=updateSellDetail.getAmmount();
			
			incomedetail.setSales_total((incomedetail.getSales_total()!=null?incomedetail.getSales_total():0.0)-(incomDetailBean.getAmmount()!=null?incomDetailBean.getAmmount():0.0));
			incomedetail.setSales_total(incomedetail.getSales_total()+newAmmount);
			
			updateThis.setAmmount(newAmmount);
			updateThis.setAmmount_modified(incomDetailBean.getAmmount_modified());			
			updateSellDetail.setAmmount(newAmmount);
			
			sales_totalStr=num.format(incomedetail.getSales_total());
			
			if(incomedetail.getSales_freq().equals("30")){
				sales_totalMonthlyStr = num.format(incomedetail.getSales_total()*4);			
				incomedetail.setProfit_before_costs((incomedetail.getSales_total()*4) - (incomedetail.getProvider_total()!=null?incomedetail.getProvider_total():0.0));
			}else{
				sales_totalMonthlyStr = num.format(incomedetail.getSales_total());					
				incomedetail.setSales_total(incomedetail.getSales_total());
				incomedetail.setProfit_before_costs(incomedetail.getSales_total() - (incomedetail.getProvider_total()!=null?incomedetail.getProvider_total():0.0));
			}
			
			profit_before_costsStr = num.format(incomedetail.getProfit_before_costs());
			
			incomedetail.setProfil_after_costs(incomedetail.getProfit_before_costs()-(incomedetail.getOperative_costs()!=null?incomedetail.getOperative_costs():0.0));
			profil_after_costsStr = num.format(incomedetail.getProfil_after_costs());
			
			if(hasIncomeDetail){
				if(updateSellDetail.getSellingDetailPK()!=null){
					if(incomDetailBean.isSave()?incomedetailservice.updateSelligOrOperatingDetail(updateSellDetail):incomedetailservice.saveSelligOrOperatingDetail(updateSellDetail)){										
						updateThis.setSave(true);	
						if(preValue==null){
							saveChangeData("gn_selling_detail","ammount", "", newAmmount+"","El usuario modificó "+updateThis.getName());							
						}else if(!preValue.equals(newAmmount)){
							saveChangeData("gn_selling_detail","ammount",preValue+"", newAmmount+"","El usuario modificó "+updateThis.getName());
						}
					}
				}else{
					SellingDetailPK sellingPK=new SellingDetailPK(
							incomedetail.getIncomDetailPk().getIncome_id(), 
							incomedetail.getIncomDetailPk().getProspectus_id(), 
							incomedetail.getIncomDetailPk().getCompany_id(), 
							incomedetail.getIncomDetailPk().getIncome_detail_id(),incomDetailBean.getTypeDetail());						
					updateSellDetail.setSellingDetailPK(sellingPK);
					if(incomedetailservice.saveSelligOrOperatingDetail(updateSellDetail)){
						updateThis.setSave(true);
						if(preValue==null){
							saveChangeData("gn_selling_detail","ammount", "", newAmmount+"","El usuario modificó "+updateThis.getName());							
						}else if(!preValue.equals(newAmmount)){
							saveChangeData("gn_selling_detail","ammount",preValue+"", newAmmount+"","El usuario modificó "+updateThis.getName());
						}
					}
				}
				saveIncomeDetail();	
			}else{					
				saveIncomeDetail();
				
				if(hasIncomeDetail && !updateThis.isSave()){
					SellingDetailPK sellingPK=new SellingDetailPK(
							incomedetail.getIncomDetailPk().getIncome_id(), 
							incomedetail.getIncomDetailPk().getProspectus_id(), 
							incomedetail.getIncomDetailPk().getCompany_id(), 
							incomedetail.getIncomDetailPk().getIncome_detail_id(),incomDetailBean.getTypeDetail());						
					updateSellDetail.setSellingDetailPK(sellingPK);
					if(incomedetailservice.saveSelligOrOperatingDetail(updateSellDetail)){
						updateThis.setSave(true);
						if(preValue==null){
							saveChangeData("gn_selling_detail","ammount", "", newAmmount+"","El usuario modificó "+updateThis.getName());							
						}else if(!preValue.equals(newAmmount)){
							saveChangeData("gn_selling_detail","ammount",preValue+"", newAmmount+"","El usuario modificó "+updateThis.getName());
						}
					}
					
				}
				
			}
			updateThis.setSellingOrOperCostDetail(updateSellDetail);
			
		}else if(incomDetailBean.getSellingOrOperCostDetail() instanceof Operating_cost){	
			IncomeDetailsBean updateThis= lstOpertCostDetails.get(incomDetailBean.getIndex());
			Operating_cost updateOperaCostDetail=(Operating_cost) updateThis.getSellingOrOperCostDetail();
			Double preValue=updateOperaCostDetail.getAmmount();
			updateOperaCostDetail.setAmmount(newAmmount);	
			
			updateThis.setAmmount(newAmmount);
			updateThis.setAmmount_modified(incomDetailBean.getAmmount_modified());			
			updateOperaCostDetail.setAmmount(newAmmount);
			
//			incomedetail.setOperative_costs((incomedetail.getOperative_costs()!=null?incomedetail.getOperative_costs():0.0)-(incomDetailBean.getAmmount()!=null?incomDetailBean.getAmmount():0.0));		
//			incomedetail.setOperative_costs((incomedetail.getOperative_costs()!=null?incomedetail.getOperative_costs():0.0)+newAmmount);			
			
			Double sumaOpCst = 0D;
			
			for( IncomeDetailsBean tmp : lstOpertCostDetails ){
				sumaOpCst += tmp.getAmmount();
			}
			
			incomedetail.setOperative_costs( sumaOpCst );
			
			operative_costsStr = num.format(incomedetail.getOperative_costs());
			
			
			incomedetail.setProfil_after_costs((incomedetail.getProfit_before_costs()==null?0:incomedetail.getProfit_before_costs())-(incomedetail.getOperative_costs()==null?0:incomedetail.getOperative_costs()));
			profil_after_costsStr = num.format(incomedetail.getProfil_after_costs());
						
			if(hasIncomeDetail){			
				if(updateOperaCostDetail.getOperatingCostPK()!=null){
					if(updateThis.isSave()?incomedetailservice.updateSelligOrOperatingDetail(updateOperaCostDetail):incomedetailservice.saveSelligOrOperatingDetail(updateOperaCostDetail)){
						updateThis.setSave(true);
						saveIncomeDetail();
						if(preValue==null){
							saveChangeData("gn_operating_cost","ammount", "", newAmmount+"","El usuario modificó "+updateThis.getName());							
						}else if(!preValue.equals(newAmmount)){
							saveChangeData("gn_operating_cost","ammount",preValue+"", newAmmount+"","El usuario modificó "+updateThis.getName());
						}
					}
				}else{
					Operating_costPK opetatinCostPk=new Operating_costPK(
							incomedetail.getIncomDetailPk().getIncome_id(), 
							incomedetail.getIncomDetailPk().getProspectus_id(), 
							incomedetail.getIncomDetailPk().getCompany_id(), 
							incomedetail.getIncomDetailPk().getIncome_detail_id(),incomDetailBean.getTypeDetail());	
					updateOperaCostDetail.setOperatingCostPK(opetatinCostPk);
					if(incomedetailservice.saveSelligOrOperatingDetail(updateOperaCostDetail)){
						updateThis.setSave(true);
						saveIncomeDetail();
						if(preValue==null){
							saveChangeData("gn_operating_cost","ammount", "", newAmmount+"","El usuario modificó "+updateThis.getName());							
						}else if(!preValue.equals(newAmmount)){
							saveChangeData("gn_operating_cost","ammount",preValue+"", newAmmount+"","El usuario modificó "+updateThis.getName());
						}
					}						
				}
				
			}else{
				saveIncomeDetail();
				if(hasIncomeDetail && !updateThis.isSave()){
					Operating_costPK opetatinCostPk=new Operating_costPK(
							incomedetail.getIncomDetailPk().getIncome_id(), 
							incomedetail.getIncomDetailPk().getProspectus_id(), 
							incomedetail.getIncomDetailPk().getCompany_id(), 
							incomedetail.getIncomDetailPk().getIncome_detail_id(),incomDetailBean.getTypeDetail());	
					updateOperaCostDetail.setOperatingCostPK(opetatinCostPk);
					if(incomedetailservice.saveSelligOrOperatingDetail(updateOperaCostDetail)){
						updateThis.setSave(true);
						if(preValue==null){
							saveChangeData("gn_operating_cost","ammount", "", newAmmount+"","El usuario modificó "+updateThis.getName());							
						}else if(!preValue.equals(newAmmount)){
							saveChangeData("gn_operating_cost","ammount",preValue+"", newAmmount+"","El usuario modificó "+updateThis.getName());
						}
					}
				}
			}	
		}
		
		
	}
	
	public void setValuesDetail(ValueChangeEvent e){		
		String field = (String) e.getComponent().getAttributes().get("field").toString();
		
		if(field.equals("times_refill")){
			Integer i =  Integer.parseInt(e.getNewValue().toString().replace("$", "").replace(",", ""));
			
			if(i!=null){
				Integer preVal=incomedetail.getTimes_refill();
				if(preVal==null){
					saveChangeData("gn_income_detail", "times_refill","", i+"","El usuario modificó veces que se surte al mes");
				}else if(!i.equals(preVal)){
					saveChangeData("gn_income_detail", "times_refill",preVal+"", i+"","El usuario modificó veces que se surte al mes");
				}
			}
			
			incomedetail.setTimes_refill(i);
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addPartialUpdateTarget("pnlProfitBeforeCosts");
			requestContext.addPartialUpdateTarget("totalOperativeCost");
			requestContext.addPartialUpdateTarget("pnlProfilAfterCostsStr");
		}
		
		if(field.equals("provider_total")){
			Double d =  Double.parseDouble(e.getNewValue().toString().replace("$", "").replace(",", ""));
			provider_totalStr = num.format(d);
			if(d!=null){
				Double preVal=incomedetail.getProvider_total();
				if(preVal==null){
					saveChangeData("gn_income_detail", "provider_total","", d+"","El usuario modificó el total de compras mensuales");
				}else if(!d.equals(preVal)){
					saveChangeData("gn_income_detail", "provider_total",preVal+"", d+"","El usuario modificó el total de compras mensuales");
				}
			}
			incomedetail.setProvider_total(d);
			
			Double sales_total = (incomedetail.getSales_total()==null?0:incomedetail.getSales_total());
			
			if ( incomedetail.getSales_freq() != null && incomedetail.getSales_freq().equals("30") ) {
			
				sales_total = sales_total * 4;
				
			}
			
			Double before = sales_total - (incomedetail.getProvider_total()==null?0:incomedetail.getProvider_total());
			incomedetail.setProfit_before_costs(before);
			profit_before_costsStr = num.format(incomedetail.getProfit_before_costs());
			
			incomedetail.setProfil_after_costs((incomedetail.getProfit_before_costs()==null?0:incomedetail.getProfit_before_costs())-(incomedetail.getOperative_costs()==null?0:incomedetail.getOperative_costs()));
			profil_after_costsStr = num.format(incomedetail.getProfil_after_costs());
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addPartialUpdateTarget("pnlProfitBeforeCosts");
			requestContext.addPartialUpdateTarget("totalOperativeCost");
			requestContext.addPartialUpdateTarget("pnlProfilAfterCostsStr");
		}		
		saveIncomeDetail();
		
	}
	
	public void checkIncome(){
		if(income_id == null){
			if( incomedetail != null && incomedetail.getIncomDetailPk() != null && incomedetail.getIncomDetailPk().getIncome_id() != 0)
				income_id = incomedetail.getIncomDetailPk().getIncome_id();
			else if (getThisIncome() != null && getThisIncome().getIncomePk() != null && getThisIncome().getIncomePk().getIncome_id() != 0 )
				income_id = getThisIncome().getIncomePk().getIncome_id();
			else{
				
				setTypeIncomeID(2);
				
				updateOrSaveIncome();
			}
			
		}
	}
	
	public void changeSalesFreq(){
		sales_totalStr=sales_totalMonthlyStr=profit_before_costsStr=profil_after_costsStr="0";
		incomedetail.setSales_total(0.0);
		incomedetail.setProfit_before_costs(0.0);			
		incomedetail.setProfil_after_costs(0.0);
		
		checkIncome();
		
		incomedetail.setSales_freq(sales_freq);
		
		int end=0;
		int start=0;
		int index=0;
		
		if(incomedetail.getSales_freq() == null) {
			incomedetail.setSales_freq("1");
		}
		
		if(incomedetail.getSales_freq().equals("1")){
			start=1;
			end=1;
		}else if(incomedetail.getSales_freq().equals("2")){
			start=2;
			end=3;
		}else if(incomedetail.getSales_freq().equals("4")){
			start=4;
			end=7;
		}else if(incomedetail.getSales_freq().equals("30")){
			start=8;
			end=14;
		}
		lstSellingDetails.clear();
		IncomeDetailsBean incoDetaBean=null;
		if(hasIncomeDetail){
			incomedetailservice.deleteSellingByDetailId(sesion.getProspectus_id(),sesion.getCompany_id(),incomedetail.getIncomDetailPk().getIncome_detail_id());			
					
			for (int i = start; i <=end; i++) {
				SellingDetail sellingDetail=new SellingDetail();
				SellingDetailPK sellingPK=new SellingDetailPK(
						incomedetail.getIncomDetailPk().getIncome_id(), 
						incomedetail.getIncomDetailPk().getProspectus_id(), 
						incomedetail.getIncomDetailPk().getCompany_id(), 
						incomedetail.getIncomDetailPk().getIncome_detail_id());	
				incoDetaBean=new IncomeDetailsBean();
				for(SellingType sellingType:lstSellingType){
					if(i==sellingType.getSellingTypePk().getSelling_type_id()){
						incoDetaBean.setName(sellingType.getName());
						incoDetaBean.setHs_name(sellingType.getHs_name());
						sellingDetail.setSellingType(sellingType);
						break;
					}
				}
				sellingPK.setSelling_type_id(i);
				sellingDetail.setAmmount(0.0);
				sellingDetail.setSellingDetailPK(sellingPK);
				incoDetaBean.setIndex(index);
				incoDetaBean.setTypeDetail(i);
				incoDetaBean.setAmmount(0.0);
				incoDetaBean.setSellingOrOperCostDetail(sellingDetail);
				incoDetaBean.setAmmount_modified("0");
				incoDetaBean.setSave(incomedetailservice.saveSelligOrOperatingDetail(sellingDetail));
				lstSellingDetails.add(incoDetaBean);
				index++;
			}
			saveIncomeDetail();
		}else{
			saveIncomeDetail();
			if(hasIncomeDetail){		
				
				for (int i = start; i <=end; i++) {
					SellingDetail sellingDetail=new SellingDetail();
					SellingDetailPK sellingPK=new SellingDetailPK(
							incomedetail.getIncomDetailPk().getIncome_id(), 
							incomedetail.getIncomDetailPk().getProspectus_id(), 
							incomedetail.getIncomDetailPk().getCompany_id(), 
							incomedetail.getIncomDetailPk().getIncome_detail_id());
					incoDetaBean=new IncomeDetailsBean();	
					for(SellingType sellingType:lstSellingType){
						if(i==sellingType.getSellingTypePk().getSelling_type_id()){
							incoDetaBean.setName(sellingType.getName());
							incoDetaBean.setHs_name(sellingType.getHs_name());
							sellingDetail.setSellingType(sellingType);
							break;
						}
					}
					sellingPK.setSelling_type_id(i);
					sellingDetail.setAmmount(0.0);
					sellingDetail.setSellingDetailPK(sellingPK);	
					incoDetaBean.setIndex(index);
					incoDetaBean.setTypeDetail(i);
					incoDetaBean.setAmmount(0.0);
					incoDetaBean.setSellingOrOperCostDetail(sellingDetail);
					incoDetaBean.setAmmount_modified("0");
					incoDetaBean.setSave(incomedetailservice.saveSelligOrOperatingDetail(sellingDetail));
					lstSellingDetails.add(incoDetaBean);					
					index++;
				}
			}
			
			
		}
		
		
		
	}
	

	public String getTimes_refillStr() {
		return times_refillStr;
	}

	public void setTimes_refillStr(String times_refillStr) {
		this.times_refillStr = times_refillStr;
	}

	public String getDispSlideDetails() {
		return dispSlideDetails;
	}

	public void setDispSlideDetails(String dispSlideDetails) {
		this.dispSlideDetails = dispSlideDetails;
	}

	public String getDispDaily() {
		return dispDaily;
	}

	public void setDispDaily(String dispDaily) {
		this.dispDaily = dispDaily;
	}

	public String getDispWeek() {
		return dispWeek;
	}

	public void setDispWeek(String dispWeek) {
		this.dispWeek = dispWeek;
	}

	public String getDispFortnight() {
		return dispFortnight;
	}

	public void setDispFortnight(String dispFortnight) {
		this.dispFortnight = dispFortnight;
	}

	public String getDispMonthly() {
		return dispMonthly;
	}

	public void setDispMonthly(String dispMonthly) {
		this.dispMonthly = dispMonthly;
	}

	public String getProvider_amountStr() {
		return provider_amountStr;
	}

	public void setProvider_amountStr(String provider_amountStr) {
		this.provider_amountStr = provider_amountStr;
	}

	public String getProvider_totalStr() {
		return provider_totalStr;
	}

	public void setProvider_totalStr(String provider_totalStr) {
		this.provider_totalStr = provider_totalStr;
	}

	public String getDetail_1Str() {
		return detail_1Str;
	}

	public void setDetail_1Str(String detail_1Str) {
		this.detail_1Str = detail_1Str;
	}

	public String getDetail_2Str() {
		return detail_2Str;
	}

	public void setDetail_2Str(String detail_2Str) {
		this.detail_2Str = detail_2Str;
	}

	public String getDetail_3Str() {
		return detail_3Str;
	}

	public void setDetail_3Str(String detail_3Str) {
		this.detail_3Str = detail_3Str;
	}

	public String getDetail_4Str() {
		return detail_4Str;
	}

	public void setDetail_4Str(String detail_4Str) {
		this.detail_4Str = detail_4Str;
	}

	public String getDetail_5Str() {
		return detail_5Str;
	}

	public void setDetail_5Str(String detail_5Str) {
		this.detail_5Str = detail_5Str;
	}

	public String getDetail_6Str() {
		return detail_6Str;
	}

	public void setDetail_6Str(String detail_6Str) {
		this.detail_6Str = detail_6Str;
	}

	public String getDetail_7Str() {
		return detail_7Str;
	}

	public void setDetail_7Str(String detail_7Str) {
		this.detail_7Str = detail_7Str;
	}

	public String getSales_totalStr() {
		return sales_totalStr;
	}

	public void setSales_totalStr(String sales_totalStr) {
		this.sales_totalStr = sales_totalStr;
	}

	public String getProfit_before_costsStr() {
		return profit_before_costsStr;
	}

	public void setProfit_before_costsStr(String profit_before_costsStr) {
		this.profit_before_costsStr = profit_before_costsStr;
	}

	public String getOperative_costsStr() {
		return operative_costsStr;
	}

	public void setOperative_costsStr(String operative_costsStr) {
		this.operative_costsStr = operative_costsStr;
	}

	public String getCost_rentStr() {
		return cost_rentStr;
	}

	public void setCost_rentStr(String cost_rentStr) {
		this.cost_rentStr = cost_rentStr;
	}

	public String getCost_waterStr() {
		return cost_waterStr;
	}

	public void setCost_waterStr(String cost_waterStr) {
		this.cost_waterStr = cost_waterStr;
	}

	public String getCost_electricityStr() {
		return cost_electricityStr;
	}

	public void setCost_electricityStr(String cost_electricityStr) {
		this.cost_electricityStr = cost_electricityStr;
	}

	public String getCost_gasStr() {
		return cost_gasStr;
	}

	public void setCost_gasStr(String cost_gasStr) {
		this.cost_gasStr = cost_gasStr;
	}

	public String getCost_phoneStr() {
		return cost_phoneStr;
	}

	public void setCost_phoneStr(String cost_phoneStr) {
		this.cost_phoneStr = cost_phoneStr;
	}

	public String getCost_taxesStr() {
		return cost_taxesStr;
	}

	public void setCost_taxesStr(String cost_taxesStr) {
		this.cost_taxesStr = cost_taxesStr;
	}

	public String getCost_transportStr() {
		return cost_transportStr;
	}

	public void setCost_transportStr(String cost_transportStr) {
		this.cost_transportStr = cost_transportStr;
	}

	public String getCost_maintenanceStr() {
		return cost_maintenanceStr;
	}

	public void setCost_maintenanceStr(String cost_maintenanceStr) {
		this.cost_maintenanceStr = cost_maintenanceStr;
	}

	public String getCost_accountantStr() {
		return cost_accountantStr;
	}

	public void setCost_accountantStr(String cost_accountantStr) {
		this.cost_accountantStr = cost_accountantStr;
	}

	public String getCost_employeesStr() {
		return cost_employeesStr;
	}

	public void setCost_employeesStr(String cost_employeesStr) {
		this.cost_employeesStr = cost_employeesStr;
	}

	public String getCost_otherStr() {
		return cost_otherStr;
	}

	public void setCost_otherStr(String cost_otherStr) {
		this.cost_otherStr = cost_otherStr;
	}

	public String getProfil_after_costsStr() {
		return profil_after_costsStr;
	}

	public void setProfil_after_costsStr(String profil_after_costsStr) {
		this.profil_after_costsStr = profil_after_costsStr;
	}

	public String getSales_freqStr() {
		return sales_freqStr;
	}

	public void setSales_freqStr(String sales_freqStr) {
		this.sales_freqStr = sales_freqStr;
	}

	
	public void updateProyect(){
		boolean flag;
		
		if(isHasProyect()){		
			log.info("Actualizando...................................");
			if(getThisProyect().getOwe_family()!=null && getThisProyect().getOwe_family().equals("N"))
				getThisProyect().setOwe_family_text(null);
			
			flag=proyectoService.update(getThisProyect());			
		}
		else{
			log.info("Insertando...................................");
			ProyectPK pk =new ProyectPK(proyectoService.getMaxProyectID(),sesion.getProspectus_id(),sesion.getCompany_id());
			thisProyect.setProyectoPk(pk);
			flag=proyectoService.add(getThisProyect());
			setHasProyect(true);
		}
		//end Save Proyect
		if(flag)
			log.info("!!!!!!!! Proyect saved,update successfully !!!!!!!!!!!!!!!");
		else
			log.info("!!!!!!!! Proyect  not saved,update !!!!!!!!!!!!!!!");			
	}

	public String getSales_totalMonthlyStr() {
		return sales_totalMonthlyStr;
	}
	public void setSales_totalMonthlyStr(String sales_totalMonthlyStr) {
		this.sales_totalMonthlyStr = sales_totalMonthlyStr;
	}	
	public Change_controlService getChangeControlService() {
		return changeControlService;
	}
	public void setChangeControlService(Change_controlService changeControlService) {
		this.changeControlService = changeControlService;
	}
	public String getIpAddressClient() {
		return ipAddressClient;
	}
	public void setIpAddressClient(String ipAddressClient) {
		this.ipAddressClient = ipAddressClient;
	}

	public IncomeTypeServiceImp getIncomeTypeService() {
		return incomeTypeService;
	}

	public void setIncomeTypeService(IncomeTypeServiceImp incomeTypeService) {
		this.incomeTypeService = incomeTypeService;
	}

	public ExpensesTypeServiceImp getExpensesTypeService() {
		return expensesTypeService;
	}

	public void setExpensesTypeService(ExpensesTypeServiceImp expensesTypeService) {
		this.expensesTypeService = expensesTypeService;
	}

	private boolean saveChangeData(String table,String field,String origValue,String newValue,String comment){
		Change_controlPK changeCtrlPK=new Change_controlPK();
		changeCtrlPK.setProspectus_id(sesion.getProspectus_id());
		changeCtrlPK.setCompany_id(sesion.getCompany_id());
		
		Change_control changeCtrl=new Change_control();
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(getIpAddressClient());			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		if(changeControlService.addChangeControl(changeCtrl,sesion.getProspectus_id(),sesion.getCompany_id()))
			return true;
		else
			return false;
	}

	public String getAmmountDeductionsSalStr() {
		return ammountDeductionsSalStr;
	}

	public void setAmmountDeductionsSalStr(String ammountDeductionsSalStr) {
		this.ammountDeductionsSalStr = ammountDeductionsSalStr;
		setTypeIncomeID(5);
		setAmmountDeductionsSal(Double.parseDouble(ammountDeductionsSalStr.replaceAll(",", "")));
		
	}

	public String getTotalammountWagesSalStr() {
		return totalammountWagesSalStr;
	}

	public void setTotalammountWagesSalStr(String totalammountWagesSalStr) {
		this.totalammountWagesSalStr = totalammountWagesSalStr;
		setTypeIncomeID(6);
		setTotalammountWagesSal(Double.parseDouble(totalammountWagesSalStr.replaceAll(",", "")));
	}

	public Double getAmmountDeductionsSal() {
		return ammountDeductionsSal;
	}

	public void setAmmountDeductionsSal(Double ammountDeductionsSal) {
		this.ammountDeductionsSal = ammountDeductionsSal;
	}

	public Double getTotalammountWagesSal() {
		return totalammountWagesSal;
	}

	public void setTotalammountWagesSal(Double totalammountWagesSal) {
		this.totalammountWagesSal = totalammountWagesSal;
	}

	public Expenses getTotalExpensesObj() {
		return totalExpensesObj;
	}

	public void setTotalExpensesObj(Expenses totalExpensesObj) {
		this.totalExpensesObj = totalExpensesObj;
	}
	
	public void setTotalExpensesFnc() {
		
		if( (getTotalExpensesObj().getAmmount() != null && (getTotalExpensesObj().getAmmount()+"").equals("NaN")) )
			getTotalExpensesObj().setAmmount( 0D );
		
		System.out.println( "Total Gastos: " + getTotalExpensesObj().getAmmount() );
		
		Double preExpenseValue=totalExpenses;
		
		if(getTotalExpensesObj().getAmmount() == null ) {
			getTotalExpensesObj().setAmmount(0D); 
		}
		
		boolean flagUpdate = false;
		
		if(flagTotalExpense){
			flagUpdate=expensesService.updateExpenses(totalExpensesObj);
			
		}else{
			flagUpdate=expensesService.addExpenses(totalExpensesObj, sesion.getProspectus_id(), sesion.getCompany_id() );
			flagTotalExpense = true;
		}
		
		if(flagUpdate){
			
			saveChangeData("gn_expense","ammount",preExpenseValue+"", getTotalExpensesObj().getAmmount()+"","El usuario modificó el total de gastos " );
			
		}
		
		totalExpenses = getTotalExpensesObj().getAmmount();
		
	}
	
	
	
	public void clearExpenses(){
		
		System.out.println( "Clear eastwood Expenses! " );
		
		listExpensesByProspect=expensesService.getListExpensesByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		
		for(Expenses expReg: listExpensesByProspect){
			switch (expReg.getExpense_type_id()) {
					case 1:
						ammountFoodStr=num.format(0.0D);
						break;
					case 2:
						ammountMaintenancHousStr=num.format(0.0D);
						break;
					case 3:
						ammountLightStr=num.format(0.0D);
						break;
					case 4:
						ammountPhoneStr=num.format(0.0D);
						break;
					case 5:
						ammountIncomeStr=num.format(0.0D);
						break;
					case 6:
						ammountSeriesStr=num.format(0.0D);
						break;
					case 7:
						ammountCreditsStr=num.format(0.0D);
						break;
					case 8:
						ammountSchoolStr=num.format(0.0D);
						break;
					case 9:
						ammountMedicalStr=num.format(0.0D);
						break;
					case 10:
						ammountEntertainmentStr=num.format(0.0D);
						break;
					case 11:
						ammountFoodWorkStr=num.format(0.0D);
						break;
					case 12:
						ammountGasolineTranspStr=num.format(0.0D);
						break;
					case 13:
						ammountGasStr=num.format(0.0D);
						
						break;
					case 14:
						totalExpensesObj = new Expenses(); 
						
						totalExpensesObj.setAmmount(0D );
						totalExpensesObj.setAmmount_minus( expReg.getAmmount_minus() );
						totalExpensesObj.setAmmount_modified( expReg.getAmmount_modified() );
						totalExpensesObj.setDatetime_modified( expReg.getDatetime_modified() );
						totalExpensesObj.setDescription( expReg.getDescription() );
						totalExpensesObj.setExpense_type_id( expReg.getExpense_type_id() );
						totalExpensesObj.setExpensesPk( expReg.getExpensesPk() );
						totalExpensesObj.setExpensesType( expReg.getExpensesType() );
						totalExpensesObj.setProspectus_id_modified( expReg.getProspectus_id_modified() );
						flagTotalExpense=true;
						break;
					default:
						break;
			}
			
			expReg.setAmmount(0D);
			expensesService.updateExpenses(expReg);
			
		}
		
		totalExpenses = 0D;
		
		
	}
	
	public void clearOperativeCost()
	{
		for ( IncomeDetailsBean tmp : lstOpertCostDetails ){
			tmp.setAmmount_modified("0");
			saveOrUpdate( tmp );
		}
	}
	
	public void setTotalOperativeCostValue(){
		
		incomedetail = incomedetailservice.loadMaxIncomeByProspectus(sesion.getProspectus_id(),sesion.getCompany_id());
		
		Double d = 0D;
		
		if( operative_costsStr != null && operative_costsStr.trim().length() > 0  ){
			
			d = Double.parseDouble( operative_costsStr.replaceAll(",", "") );
			
		}
		
		incomedetail.setOperative_costs(d);
		
		incomedetailservice.updateMaxIncomeDeatil( incomedetail );
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	
	public void updateEmployment(){
		try{
			System.out.println("Actualizando Empleo .. ");
			employmentService.updateEmployment(employment);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private boolean isSesion_DISABLED( ExternalContext external )
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath( external ) + "/Portal/sesion-expirada.xhtml?redirecFrom=AddIncomeExpenses");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				  external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		}
		
		return bandera;
	}
	
	private String getPath( ExternalContext external )
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
}
