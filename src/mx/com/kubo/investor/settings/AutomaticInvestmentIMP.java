package mx.com.kubo.investor.settings;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.InvestmentFilter;
import mx.com.kubo.model.InvestmentFilterPK;
import mx.com.kubo.rest.tienda.SAFIInvestmentIMP;
import mx.com.kubo.rest.tienda.accounts.CuentasClienteIMP;

public class AutomaticInvestmentIMP extends AutomaticInvestmentAMO 
implements AutomaticInvestmentIMO
{
	public void init()
	{
/*		
		lstStatus  = statusproyectcatservice.getListStatusProyectCat();
*/		
		
		purposelst = purposeservice.getPurposeList();
		
		automatic_investment_list = service_automatic_investment.getAutomaticInvestmentListByProspect(prospectus_id);
		
		cuenta = new CuentasClienteIMP();
		cuenta.setSesion(sesion);
		cuenta.init();
		
		saldoTotal      = cuenta.getSaldoTotal();
		listInvAccounts = cuenta.getListInvAccounts();
		
		account = listInvAccounts.get(0).getAccount();
		
		inversion = new SAFIInvestmentIMP();
		inversion.setSaldoTotal(saldoTotal);
		inversion.setListInvAccounts(listInvAccounts);
		inversion.init();
		
		inicializaSaldos();
/*		
		init_system_param();											
			
		calculaSaldoActual();														
			
		try
		{					
			servicioSafi = inversion.getServicioInvKuboSafi();				
				
		} catch(Exception e) {
						
			e.printStackTrace();
		}																															
		
		setMontoMaximo();
*/		
		
		cargaListaTienda();			
	}
	
	public void updateByFiltering(ActionEvent event)
    {    	
		external = FacesContext.getCurrentInstance().getExternalContext();
		
    	map = external.getRequestParameterMap();
    	
    	init_filter();    	     	    	
    	init_automatic_investment();
    	
    	boolean save_OK = service_automatic_investment.saveAutomaticInvestment(automatic_investment);
    	
    	if(save_OK)
    	{
    		automatic_investment_list = service_automatic_investment.getAutomaticInvestmentListByProspect(prospectus_id);
    	}
    	
    	inversion.cargaListaTienda(strQuery, prospectus_id, company_id, flagRisk+"", safi_client_id, account);
    	
    	scriptStatus = inversion.getScriptStatus();
    	
    	if( !hold_selected )
    	{	    	
/*    		
	    	calculaInversionPorProyecto(false);
*/	    	
	    	
    		proyectList = inversion.getProyectList();
		
    	} else {	    			    		
 /*  		
    		calculaInversionPorProyectoManteniendoSeleccionados();
*/
    	}
    	
    	filter = inversion.getFilter();
		
		System.out.println( "*Antes updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
/*		
		asignaListForInvest();
*/		
		
		System.out.println( "*Despues updateByFiltering* proyectListForInvesInd.size(): " + proyectListForInvesInd.size() );
		
    	filterInvestment = new InvestmentFilter();
    	
    	fpk = new InvestmentFilterPK();
    	
    	fpk.setCompany_id(company_id);
    	fpk.setProspectus_id(prospectus_id);
    	
    	filterInvestment.setFilter(strQuery);
    	filterInvestment.setFilter_date_used(new Date());
    	filterInvestment.setPk(fpk);
    	
    	investmentFilterServiceImp.addFilterUsed(filterInvestment);
    	
/*    	
    	inicializaListas();
*/    	
    }

	public Integer getNumByKuboScore(String letterScore)
	{		
		Integer min = 0;
		
		for(ItemLoanList p : proyectList)
		{			
			if(p.getKubo_score_a().equals(letterScore))
			{
				min ++;
			}			
		}		
		
		return min;		
	}
	
	public Double getPorcentByKuboScore(String letterScore)
	{
		int total = this.proyectList.size();
		
		Double min =  0.0;
		
		for(ItemLoanList p : proyectList)
		{
			if(p.getKubo_score_a().equals(letterScore))
			{
				min ++;
			}
		}	
		
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();		
		simbolo.setDecimalSeparator('.');
		
		DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
		Double valor = 0d;
		
		if(total > 0)
		{
			valor = (((100 * total) - 100 * (total - min)) / total);
		}
		
		if(valor > 100.00)
		{	
			return 100.00;
			
		} else {
			
			return Double.valueOf(formateador.format(valor));
		}
	}
}
