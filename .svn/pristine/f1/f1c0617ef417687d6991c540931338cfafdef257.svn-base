package mx.com.kubo.managedbeans.investor;

import java.util.Hashtable;
import java.util.List;

import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.model.MontoInvertido_F_G_Collector;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public class AjustesPMO extends AjustesDMO  {

	protected void initScript(){
		
		investor_param_script = "<script>";
		
		ht = null;
		
		List<InvestorParam> lst = investor_param_service.getInvestorParamByProspectus(sesion.getProspectus_id(), sesion.getCompany_id());
		
		SystemParamPK sysPK = null;
		
		SystemParam sysParam = null;
		InvestorParam p = null;
		
		if( lst != null && lst.size() > 0 ){
			
			ht = initLst( lst );
			
			
			
			p =  ht.get(ID_SUMA_MONTO_MAXIMO_E5_INV);
			
			if( p != null ){
				
				investor_param_script += "$('#param_id_"+ID_SUMA_MONTO_MAXIMO_E5_INV+"').val("+p.getValue_str()+"); ";
				
			}else{
				
				sysPK = new SystemParamPK();
				sysParam = null;
				sysPK.setCompany_id(sesion.getCompany_id());
				sysPK.setSystem_param_id(ID_SUMA_MONTO_MAXIMO_E5_SYS); // Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de riesgo ( E5 )
				sysParam = systemparamservice.loadSelectedSystemParam(sysPK);
				
				if( sysParam != null ){
					
					investor_param_script += "$('#param_id_"+ID_SUMA_MONTO_MAXIMO_E5_INV+"').val("+sysParam.getValue()+"); ";
					
				}
				
			}
			
			p= null;
			
			p =  ht.get(ID_SUMA_MONTO_MAXIMO_FG_INV);
			
			if( p != null ){
				
				investor_param_script += "$('#param_id_"+ID_SUMA_MONTO_MAXIMO_FG_INV+"').val("+p.getValue_str()+"); ";
				
			}else{
				
				sysPK = new SystemParamPK();
				sysParam = null;
				sysPK.setCompany_id(sesion.getCompany_id());
				sysPK.setSystem_param_id(ID_SUMA_MONTO_MAXIMO_FG_SYS); // Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de riesgo ( F1,G1 )
				sysParam = systemparamservice.loadSelectedSystemParam(sysPK);
				
				if( sysParam != null ){
					
					investor_param_script += "$('#param_id_"+ID_SUMA_MONTO_MAXIMO_FG_INV+"').val("+sysParam.getValue()+"); ";
					
				}
				
			}
			
		}else{
			
			sysPK = new SystemParamPK();
			sysParam = null;
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(ID_SUMA_MONTO_MAXIMO_E5_SYS); // Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de riesgo ( E5 )
			sysParam = systemparamservice.loadSelectedSystemParam(sysPK);
			
			if( sysParam != null ){
				
				investor_param_script += "$('#param_id_"+ID_SUMA_MONTO_MAXIMO_E5_INV+"').val("+sysParam.getValue()+"); ";
				
			}
			
			sysPK = new SystemParamPK();
			sysParam = null;
			sysPK.setCompany_id(sesion.getCompany_id());
			sysPK.setSystem_param_id(ID_SUMA_MONTO_MAXIMO_FG_SYS); // Porcentaje del saldo total que sirve como tope máximo de la suma de los montos que se ha invertido en proyectos de alto riesgo (F1,G1) 
			sysParam = systemparamservice.loadSelectedSystemParam(sysPK);
			
			
			if( sysParam != null ){
				
				investor_param_script += "$('#param_id_"+ID_SUMA_MONTO_MAXIMO_FG_INV+"').val("+sysParam.getValue()+"); ";
				
			}
		
		}
		
		investor_param_script += " </script>";
		
	}
	
	private Hashtable<Integer, InvestorParam>  initLst( List<InvestorParam> lst){
		
		Hashtable<Integer, InvestorParam> ht =  new Hashtable<Integer, InvestorParam>();
		
			for( InvestorParam p : lst ){
				ht.put(p.getPk().getInvestment_param_id(), p);
			}
		
		return ht;
		
	}
	
	protected void inicializaSaldos(){
		
		saldoTotalActual = investment_list.getSaldoActual();
		cuentaActual = investment_list.getCuentaActual();
		montoInvertido_E5 = 0D;
		montoInvertido_FG = 0D;
		
		List<MontoInvertido_F_G_Collector> lst =  montoInvertido_F_G_service.getMontoInvertido_F_G(cuentaActual);
		if( lst != null ){
			for( MontoInvertido_F_G_Collector coll : lst ){
				if( coll.getKubo_score().equals( "E5" ) ){
					
					montoInvertido_E5 += coll.getTotalFondeado();
					
				}
				
				if( coll.getKubo_score().equals( "F1" ) || coll.getKubo_score().equals( "G1" ) ){
					
					montoInvertido_FG += coll.getTotalFondeado();
					
				}
			}
		
		}
		
	}
	
}
