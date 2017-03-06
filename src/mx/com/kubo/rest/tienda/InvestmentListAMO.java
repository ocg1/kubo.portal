package mx.com.kubo.rest.tienda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.model.ProyectFunding;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public abstract class InvestmentListAMO extends InvestmentListDMO 
{
	protected void cargaListaTienda()
	{																	
		typeSearch = 2; 
		
		int i = 0;
		
		String destiny_values = "";
		
		for( Purpose p : purposelst)
		{			
			if(i != 0)
			{
				destiny_values += ",";
			}
			
			destiny_values += p.getPurposePK().getPurpose_id();
			
			i++;
		}
		
		String strRisk = "risk:'A','B','C','D','E'";
		
		if( flagInversionFG )
		{
			strRisk+=",'F','G'";
		}
		
    	String ultimoFiltro = "term:25T36_6||"+strRisk+"||gender:MH||typeSearch:"+typeSearch+"||between:||previousType:"+previousType+"||destinyValues:"+destiny_values;
    	
    	int prospectus_id = sesion.getProspectus_id();
    	int company_id    = sesion.getCompany_id(); 
    	
    	String safi_client_id = naturalPerson.getSafi_client_id();
    	
    	inversion.cargaListaTienda(ultimoFiltro, prospectus_id, company_id, flagRisk+"", safi_client_id , cuentaActual);
    	
    	scriptStatus = inversion.getScriptStatus();    	
		proyectList  = inversion.getProyectList();
    	
		filter = inversion.getFilter();
		
		asignaListForInvest();
		
		destiny_str = destiny_values;		
	}
	
	protected void asignaListForInvest()
	{		
		if( !hold_selected )
		{			
			proyectListForInvesInd = new ArrayList<ItemLoanList>(); 
			ammountFoundedInv = 0D;
			
			for( ItemLoanList item : proyectList )
			{				
				if( item.getInvestment_bite() > 0 )
				{
					proyectListForInvesInd.add(item);
				}
				
				ammountFoundedInv += item.getInvestment_bite();				
			}			
		}		
	}
	
	protected String getHtml(String type)
	{		
		String res = "";
		
		try
		{
			File archivo = null; 
			
			  FileReader fr = null;
			  BufferedReader br = null;
			  StringBuilder sb = new StringBuilder();
			  
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jsf/docs/");
			  //String path = new File("Escritorio/Desarrollo_Kubo/Kubodb/WebContent/jsf/docs").getAbsolutePath();
			  
			  if(type.equals("ticket"))
			  {			  
				  path += "/ticketInv.html";
				  
			  } 
			  
			  else if( type.equals("Garantia") )
			  {				  
				  path += "/comprobante_garantia.html";				  
			  }
			  
			  //////System.out.println(path);
			  
			  archivo = new File (path);
			  fr = new FileReader (archivo);
			  br = new BufferedReader(fr);
			
			    // Lectura del fichero
			String linea;
			
			while((linea=br.readLine())!=null)
			{				
				sb.append(linea);				
			}
		
			res= sb.toString();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return res;	
	}
	
	protected Hashtable<String,List<ProyectFunding> > generaHashFunding()
	{				
		List<ProyectFunding> lstFunding =  proyectFundingService.getListProyectFunByInvestor( sesion.getProspectus_id() , sesion.getCompany_id() );
		
		Hashtable<String,List<ProyectFunding> > ht = new Hashtable<String,List<ProyectFunding> >();
		
		ArrayList<String> solicituondeo = new ArrayList<String> (); 
		
		if(elContext == null){
			elContext = FacesContext.getCurrentInstance().getELContext();
		}
		
		List<ProyectFunding> lstInv = investorSession.getInvestmentList();
		
		for( ProyectFunding pf : lstFunding ){
			
			String key = pf.getProyectloanfundingPk().getProspectus_id()+"::"+pf.getProyectloanfundingPk().getProyect_loan_id();
			
			List<ProyectFunding> lstTmp = ht.get(key);
			
			if( lstTmp == null ){
				lstTmp = new ArrayList<ProyectFunding>();
			}
			
			lstTmp.add(pf);
			
			ht.put(key, lstTmp);
			
			if( pf.getSolicitudFondeo() != null && pf.getSolicitudFondeo().trim().length() > 0 && lstInv != null && lstInv.size() > 0 ){
				solicituondeo.add( pf.getSolicitudFondeo() );
			}
			
		}
		
		int i = 0; 
		
		ArrayList<Integer> lst_index = new ArrayList<Integer>();
		
		for ( ProyectFunding fdg : lstInv ) {
			
			for( String str : solicituondeo ){
				
				if ( fdg.getSolicitudFondeo().trim().length()>0 && fdg.getSolicitudFondeo().trim().equals(str) ){
					lst_index.add(i);
				}
				
			}
			 i++;
		}
		
		if( lst_index != null && lst_index.size()>0 ){
		
			Collections.reverse(lst_index);
			
			for( Integer in : lst_index ){
				lstInv.remove(in);
			}
		
		}
		
		for ( ProyectFunding fdg : lstInv ) {
			
			if(fdg.getSolicitudFondeo() != null && fdg.getSolicitudFondeo().trim().length() > 0){
			
				String key = fdg.getProyectloanfundingPk().getProspectus_id()+"::"+fdg.getProyectloanfundingPk().getProyect_loan_id();
				
				List<ProyectFunding> lstTmp = ht.get(key);
				
				if( lstTmp == null ){
					lstTmp = new ArrayList<ProyectFunding>();
				}
				
				lstTmp.add(fdg);
				
				ht.put(key, lstTmp);
			}
			
		}
		
		return ht;
		
	}
	
	protected void preparaListaDispSel( List<ItemLoanList> proyectListTmp )
	{		
		 for( ItemLoanList itemP : proyectList )
		 {
			 boolean f1 = false;
			 
			 for( ItemLoanList itemT : proyectListTmp  )
			 {				 
				 if( isEqualsItem( itemP ,  itemT )  )
				 {
					 f1 = true;
					 itemP = itemT;
					 continue;					 
				 }				 
			 }
			 
			 if( !f1 )
			 {
				 itemP.setInvestment_bite(0D);
			 }
		 }		
	}
	
	protected void inicializaListas()
	{		
		int maxProxect = 80;
		
		proyectList_B = new ArrayList<ItemLoanList>();
		proyectList_A = new ArrayList<ItemLoanList>();
		
		moreProyects = false;
		
		int i = 0;
		
		for(ItemLoanList a : proyectList)
		{
		  proyectList_A.add(a);
		  
		  i++;
		  
		  if( i == maxProxect )
		  {
			  break;
		  }
		}
		
		if( i > maxProxect)
		{			
			moreProyects = false;
			
		} else if( i == maxProxect && proyectList.size() > maxProxect ) {
			
			moreProyects = true;
			
		} else if( i == maxProxect && proyectList.size() == maxProxect ) {
			
			moreProyects = false;
		}		
	}
	
	protected void setMontoMaximo()
	{
		
		SystemParamPK sysPk = new SystemParamPK();
		
		sysPk.setCompany_id(sesion.getCompany_id());
		sysPk.setSystem_param_id(52);//Monto máximo a invertir por paquete
		SystemParam sys = systemparamservice.loadSelectedSystemParam(sysPk);
		
		montoMaximo = 0D;
		
		if(sys != null){
			montoMaximo = Double.parseDouble(sys.getValue());
		}
		
		displayInvButton = (saldoActual - montoMaximo) >0;
		
	}
	
	protected boolean isEqualsItem( ItemLoanList itemP , ItemLoanList itemT ){
		
		boolean flagEquals= false;
		
		if( itemP == null ){
			return flagEquals;
		}
		if( itemT == null ){
			return flagEquals;
		}
		
		if(
				itemP.getProspectus_id().intValue() 	== itemT.getProspectus_id().intValue() 		&& 
				itemP.getProyect_id().intValue()		== itemT.getProyect_id().intValue()  		&& 
				itemP.getProyect_loan_id().intValue() 	== itemT.getProyect_loan_id().intValue() 	&& 
				itemP.getCompany_id().intValue() 		== itemT.getCompany_id().intValue()
		){
			flagEquals = true;
		}
		
		return flagEquals;
		
	}
	
	protected void calculaInversionPorProyecto( boolean isSelected )
	{		
		inversion.sethTFunding(generaHashFunding() );
		
		proyectList =  inversion.getProyectList();
		
		if( !isSelected )
		{		
			proyectList = inversion.calculaInvestmentBite( sesion.getProspectus_id(),sesion.getCompany_id(),ammoutToInv, proyectList  );
		    
			inversion.setProyectList(proyectList);
			
			asignaListForInvest();
		    
		}else{
			
			List<ItemLoanList> proyectListTmp = inversion.calculaInvestmentBite( sesion.getProspectus_id(),sesion.getCompany_id(),ammoutToInv, proyectListForInvesInd  );
		    
			preparaListaDispSel( proyectListTmp );
			
			inversion.setProyectList(proyectList);
			
			asignaListForInvest();
		    
		}
		
		asignaListForInvest();
	    
	    maxPorcPryG 			= inversion.getMaxPorcPryG();
		maximoInvBySaldoG 		= inversion.getMaximoInvBySaldoG();
		maximoInvBySaldoPryE5 	= inversion.getMaximoInvBySaldoPryE5();
		maximoInvBySaldoPryE4 	= inversion.getMaximoInvBySaldoPryE4();
		
		montoMinF1G1 = inversion.getMontoMinF1_G1_G();
		montoInvertido_F_G = inversion.getMontoInvertido_F_G_temp();
		maximoInvBySaldoPryF1G1 = inversion.getMontoMaximoPorProyecto_F_G();
		porcMaxSaldoPryF1G1_G = inversion.getPorcMaxSaldoProyF1_G1();
		porcent_suma_F1_G1_G = inversion.getPorcent_suma_F1_G1_G();
		limiteMaximoInversion_F_G = inversion.getLimiteMaximoInversion_F_G();
		
		montoMinG 				= inversion.getMontoMinG();
		montoMinE4E5G 			= inversion.getMontoMinE4E5G();
		monto_a_invertir_temp 	= inversion.getMonto_a_invertir_temp();
		montoSaldoG 			= inversion.getMontoSaldoG();
		monto_a_invertir 		= inversion.getMonto_a_invertir();
		
		montoDisponibleEn_E5	= inversion.getMontoDisponibleEn_E5();
		porcMaxSUMSaldoProyE5	= inversion.getPorcMaxSUMSaldoProyE5();
		
		investmentBiteVAL 		= inversion.getInvestmentBiteVAL();
		
		porcMaxSaldoG 			= inversion.getPorcMaxSaldoG();
		porcMaxSaldoPryE5G 		= inversion.getPorcMaxSaldoPryE5G();
		porcMaxSaldoPryE4G 		= inversion.getPorcMaxSaldoPryE4G();
		
		
		flagMin_E5_E4 			= inversion.isFlagMin_E5_E4();
		flagNotRule 			= inversion.isFlagNotRule();
		
	}
	
	protected void calculaInversionPorProyectoManteniendoSeleccionados(  )
	{		
		inversion.sethTFunding(generaHashFunding() );
		
		List<ItemLoanList> proyectList_Tmp1 =  inversion.getProyectList();
		
		Iterator<ItemLoanList> itr = proyectList_Tmp1.iterator();
		
		while ( itr.hasNext()) 
		{			
			ItemLoanList itemP = itr.next();
			
			for( ItemLoanList itemT : proyectListForInvesInd )
			{		
				if( isEqualsItem( itemP , itemT ) )
				{					
				    itr.remove();				    
				}			
			}			    
		}
		
		Double ammoutToInvTmp =  ammoutToInv;
		
		ammoutToInvTmp = ammoutToInv - monto_a_invertir ;
		
		Double montoInvertido_F_GTmp =  montoInvertido_F_G;
		
		List<ItemLoanList> proyectList_Tmp2 = inversion.calculaInvestmentBite( sesion.getProspectus_id(),sesion.getCompany_id(),ammoutToInvTmp, proyectList_Tmp1  );
		
		proyectList.clear();
		
		for( ItemLoanList itemT : proyectListForInvesInd )
		{			
			proyectList.add(itemT);			
		}
		
		Double monto_eliminar = 0D;
		
		for( ItemLoanList itemT : proyectList_Tmp2 )
		{			
			monto_eliminar +=  itemT.getInvestment_bite();
			
			itemT.setInvestment_bite(0D);
			itemT.setBlnChck(false);
			
			proyectList.add(itemT);			
		}
				
		inversion.setProyectList(proyectList);		    
		inversion.setMontoInvertido_F_G_temp( 0D );
		inversion.setMonto_a_invertir_temp(0D);
		inversion.setMonto_a_invertir(0D);
		
		asignaListForInvest();
	    
	    maxPorcPryG 			= inversion.getMaxPorcPryG();
		maximoInvBySaldoG 		= inversion.getMaximoInvBySaldoG();
		maximoInvBySaldoPryE5 	= inversion.getMaximoInvBySaldoPryE5();
		maximoInvBySaldoPryE4 	= inversion.getMaximoInvBySaldoPryE4();
		
		montoMinF1G1       = inversion.getMontoMinF1_G1_G();
		montoInvertido_F_G = inversion.getMontoInvertido_F_G_temp() + montoInvertido_F_GTmp;
		maximoInvBySaldoPryF1G1   = inversion.getMontoMaximoPorProyecto_F_G();
		porcMaxSaldoPryF1G1_G     = inversion.getPorcMaxSaldoProyF1_G1();
		porcent_suma_F1_G1_G      = inversion.getPorcent_suma_F1_G1_G();
		limiteMaximoInversion_F_G = inversion.getLimiteMaximoInversion_F_G();
		
		montoMinG 				= inversion.getMontoMinG();
		montoMinE4E5G 			= inversion.getMontoMinE4E5G();
		monto_a_invertir_temp 	= inversion.getMonto_a_invertir_temp();
		montoSaldoG 			= inversion.getMontoSaldoG();
		monto_a_invertir 		= inversion.getMonto_a_invertir() + ammoutToInvTmp ;
		
		investmentBiteVAL 		= inversion.getInvestmentBiteVAL();
		
		porcMaxSaldoG 			= inversion.getPorcMaxSaldoG();
		porcMaxSaldoPryE5G 		= inversion.getPorcMaxSaldoPryE5G();
		porcMaxSaldoPryE4G 		= inversion.getPorcMaxSaldoPryE4G();
		
		
		flagMin_E5_E4 			= inversion.isFlagMin_E5_E4();
		flagNotRule 			= inversion.isFlagNotRule();
		
		montoDisponibleEn_E5	= inversion.getMontoDisponibleEn_E5();
		porcMaxSUMSaldoProyE5	= inversion.getPorcMaxSUMSaldoProyE5();		
	}
}
