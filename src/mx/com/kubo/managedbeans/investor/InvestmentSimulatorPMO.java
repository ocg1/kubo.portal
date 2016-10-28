package mx.com.kubo.managedbeans.investor;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SimulationInvestmentBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.SimulationBase;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public class InvestmentSimulatorPMO extends InvestmentSimulatorDMO {
	
	protected void graficar(){
		
		
		
		List<SimulationBase> baseLst = simulationBaseServiceImp.getSimulationBaseListByElements(riskArray,genderArray,purposeArray,termArray);
		
		if(baseLst != null && baseLst.size()>0){
			
			SimulationInvestmentBean simBean = new SimulationInvestmentBean(baseLst,getAmmount());
			
			initGrophicBar( simBean );
			initGrophicArea( simBean );
			initGraphicPie( simBean );
			
			initGraphicAreaVig( simBean );
			
			setNumprojects(simBean.getSize()+"");
			
		}
		
		registraAcceso();
	}
	
	private void registraAcceso(){
		
		Access access = new Access();
		access.setCompany_id(1);
		
		access.setProspectus_id(sesion.getProspectus_id() == null?1:sesion.getProspectus_id()==0?1:sesion.getProspectus_id());
		access.setScreen_id(33); //
		access.setPercentage(0);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size  (sesion.getBrowser_height());
		access.setIpaddress      (sesion.getIP_address_client());	
		access.setUser_agent     (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());
		
		access.setUrl_access		  (sesion.getUrl_access());
		
		access.setProspectus_id_viewed(null);
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access,true);
		
	}
	
protected void initGrophicArea(  SimulationInvestmentBean simBean ){
		
		setScriptGraphicArea("");
		
		String script = "<script>";
		
		
		script +=  "var dataA  = new google.visualization.DataTable();";
		
		 script += "dataA.addColumn('string', 'Meses');";
			
		 script += "dataA.addColumn('number', 'Intereses');";
		 script += "dataA.addColumn( { type: 'string', role: 'annotation' });";
		 script += "dataA.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );";
		 script += "dataA.addRows(["+	
                 "                   ['Hoy',  0 ,'"+dec.format(0)+ "',createCustomHTMLAreaCntnt( '0')], " +
                 "                   ['6 meses', "   +simBean.getSumint6()+  " , '"+dec.format(simBean.getSumint6())+  "',createCustomHTMLAreaCntnt( '"+dec.format(simBean.getSumint6())+ "')], " +
                 "                   ['12 meses', "  +simBean.getSumint12()+ " , '"+dec.format(simBean.getSumint12())+ "',createCustomHTMLAreaCntnt( '"+dec.format(simBean.getSumint12())+ "')], " +
                 "                   ['18 meses',  " +simBean.getSumint18()+ " , '"+dec.format(simBean.getSumint18())+ "',createCustomHTMLAreaCntnt( '"+dec.format(simBean.getSumint18())+ "')] " +
                 "                 ]);" ;
		
		script += "</script>";
		
		System.out.println(script);
		
		setScriptGraphicArea(script);
	}


	protected void initGraphicAreaVig(  SimulationInvestmentBean simBean ){
		
		setScriptGraphicAreaVig("");
		
		String script = "<script>";
		
		
		script +=  "var dataAV  = new google.visualization.DataTable();";
		
		 script += "dataAV.addColumn('string', 'Meses');";
			
		 script += "dataAV.addColumn('number', 'Proyectos Activos');";
		 script += "dataAV.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );";
		 script += "dataAV.addRows(["+	
	             					 "['1', "    +simBean.getSumVig1()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig1()+"', 1)], " +
	             					 "['2', "    +simBean.getSumVig2()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig2()+"', 2)], " +
	             					 "['3', "    +simBean.getSumVig3()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig3()+"', 3)], " +
	             					 "['4', "    +simBean.getSumVig4()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig4()+"', 4)], " +
	             					 "['5', "    +simBean.getSumVig5()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig5()+"', 5)], " +
	             					 "['6', "    +simBean.getSumVig6()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig6()+"', 6)], " +
	             					 "['7', "    +simBean.getSumVig7()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig7()+"', 7)], " +
	             					 "['8', "    +simBean.getSumVig8()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig8()+"', 8)], " +
	             					 "['9', "    +simBean.getSumVig8()+   ",createCustomHTMLAreaVig('"+simBean.getSumVig9()+"', 9)], " +
	             					 "['10', "   +simBean.getSumVig10()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig10()+"',10)], " +
	             					 "['11', "   +simBean.getSumVig11()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig11()+"',11)], " +
	             					 "['12', "   +simBean.getSumVig12()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig12()+"',12)], " +
	             					 "['13', "   +simBean.getSumVig13()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig13()+"',13)], " +
	             					 "['14', "   +simBean.getSumVig14()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig14()+"',14)], " +
	             					 "['15', "   +simBean.getSumVig15()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig15()+"',15)], " +
	             					 "['16', "   +simBean.getSumVig16()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig16()+"',16)], " +
	             					 "['17', "   +simBean.getSumVig17()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig17()+"',17)], " +
	             					 "['18', "   +simBean.getSumVig18()+  ",createCustomHTMLAreaVig('"+simBean.getSumVig18()+"',18)], " +
	             "                 ]);" ;
		
		script += "</script>";
		
		System.out.println(script);
		
		setScriptGraphicAreaVig(script);
	}

	
	protected void initGrophicBar(  SimulationInvestmentBean simBean ){
		
		setScriptGraphicBar("");
		
		String script = "<script>";
		
		
		script +=  "var dataB  = new google.visualization.DataTable();";
		
		 script += "dataB.addColumn('string', 'Meses');";
		 script += "dataB.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );";
		 script += "dataB.addColumn('number', 'Capital');";
		 script += "dataB.addColumn('number', 'Intereses');";
		 script += "dataB.addRows(["+

				"  ['1', createCustomHTMLBarCntnt('1', '"+dec.format(simBean.getSumcap1())+"','"+ dec.format(simBean.getSumint1())  +"','" +dec.format(simBean.getSumint1() + simBean.getSumcap1() )+"' ),   "+simBean.getSumcap1()+",   "+simBean.getSumint1()+" ], " +
				"  ['2', createCustomHTMLBarCntnt('2', '"+dec.format(simBean.getSumcap2())+"','"+ dec.format(simBean.getSumint2())  +"','" +dec.format(simBean.getSumint2() + simBean.getSumcap2() )+"' ),  "+simBean.getSumcap2()+",    "+simBean.getSumint2()+"], " +
				"  ['3', createCustomHTMLBarCntnt('3', '"+dec.format(simBean.getSumcap3())+"','"+ dec.format(simBean.getSumint3())  +"','" +dec.format(simBean.getSumint3() + simBean.getSumcap3() )+"' ),  "+simBean.getSumcap3()+",    "+simBean.getSumint3()+"], " +
				"  ['4', createCustomHTMLBarCntnt('4', '"+dec.format(simBean.getSumcap4())+"','"+ dec.format(simBean.getSumint4())  +"','" +dec.format(simBean.getSumint4() + simBean.getSumcap4() )+"' ),  "+simBean.getSumcap4()+",    "+simBean.getSumint4()+"], " +
				"  ['5', createCustomHTMLBarCntnt('5', '"+dec.format(simBean.getSumcap5())+"','"+ dec.format(simBean.getSumint5())  +"','" +dec.format(simBean.getSumint5() + simBean.getSumcap5() )+"' ),   "+simBean.getSumcap5()+",   "+simBean.getSumint5()+"], " +
				"  ['6', createCustomHTMLBarCntnt('6', '"+dec.format(simBean.getSumcap6())+"','"+ dec.format(simBean.getSumint6())  +"','" +dec.format(simBean.getSumint6() + simBean.getSumcap6() )+"' ),  "+simBean.getSumcap6()+",    "+simBean.getSumint6()+"], " +
				"  ['7', createCustomHTMLBarCntnt('7', '"+dec.format(simBean.getSumcap7())+"','"+ dec.format(simBean.getSumint7())  +"','" +dec.format(simBean.getSumint7() + simBean.getSumcap7() )+"' ),  "+simBean.getSumcap7()+",    "+simBean.getSumint7()+"], " +
				"  ['8', createCustomHTMLBarCntnt('8', '"+dec.format(simBean.getSumcap8())+"','"+ dec.format(simBean.getSumint8())  +"','" +dec.format(simBean.getSumint8() + simBean.getSumcap8() )+"' ),  "+simBean.getSumcap8()+",    "+simBean.getSumint8()+"], " +
				"  ['9', createCustomHTMLBarCntnt('9', '"+dec.format(simBean.getSumcap9())+"','"+ dec.format(simBean.getSumint9())  +"','" +dec.format(simBean.getSumint9() + simBean.getSumcap9() )+"' ),  "+simBean.getSumcap9()+",    "+simBean.getSumint9()+"], " +
				"  ['10',createCustomHTMLBarCntnt('10', '"+dec.format(simBean.getSumcap10())+"','"+ dec.format(simBean.getSumint10())  +"','"+dec.format(simBean.getSumint10() + simBean.getSumcap10() )+"' ),  "+simBean.getSumcap10()+",   "+simBean.getSumint10()+"], " +
				"  ['11',createCustomHTMLBarCntnt('11', '"+dec.format(simBean.getSumcap11())+"','"+ dec.format(simBean.getSumint11())  +"','"+dec.format(simBean.getSumint11() + simBean.getSumcap11() )+"' ),  "+simBean.getSumcap11()+",   "+simBean.getSumint11()+"], " +
				"  ['12',createCustomHTMLBarCntnt('12', '"+dec.format(simBean.getSumcap12())+"','"+ dec.format(simBean.getSumint12())  +"','"+dec.format(simBean.getSumint12() + simBean.getSumcap12() )+"' ),  "+simBean.getSumcap12()+",   "+simBean.getSumint12()+"], " +
				"  ['13',createCustomHTMLBarCntnt('13', '"+dec.format(simBean.getSumcap13())+"','"+ dec.format(simBean.getSumint13())  +"','"+dec.format(simBean.getSumint13() + simBean.getSumcap13() )+"' ),  "+simBean.getSumcap13()+",   "+simBean.getSumint13()+"], " +
				"  ['14',createCustomHTMLBarCntnt('14', '"+dec.format(simBean.getSumcap14())+"','"+ dec.format(simBean.getSumint14())  +"','"+dec.format(simBean.getSumint14() + simBean.getSumcap14() )+"' ),  "+simBean.getSumcap14()+",   "+simBean.getSumint14()+"], " +
				"  ['15',createCustomHTMLBarCntnt('15', '"+dec.format(simBean.getSumcap15())+"','"+ dec.format(simBean.getSumint15())  +"','"+dec.format(simBean.getSumint15() + simBean.getSumcap15() )+"' ),  "+simBean.getSumcap15()+",   "+simBean.getSumint15()+"], " +
				"  ['16',createCustomHTMLBarCntnt('16', '"+dec.format(simBean.getSumcap16())+"','"+ dec.format(simBean.getSumint16())  +"','"+dec.format(simBean.getSumint16() + simBean.getSumcap16() )+"' ),  "+simBean.getSumcap16()+",   "+simBean.getSumint16()+"], " +
				"  ['17',createCustomHTMLBarCntnt('17', '"+dec.format(simBean.getSumcap17())+"','"+ dec.format(simBean.getSumint17())  +"','"+dec.format(simBean.getSumint17() + simBean.getSumcap17() )+"' ),  "+simBean.getSumcap17()+",   "+simBean.getSumint17()+"], " +
				"  ['18',createCustomHTMLBarCntnt('18', '"+dec.format(simBean.getSumcap18())+"','"+ dec.format(simBean.getSumint18())  +"','"+dec.format(simBean.getSumint17() + simBean.getSumcap18() )+"' ),  "+simBean.getSumcap18()+",   "+simBean.getSumint18()+"] " +
				"  ]); " ;
		
		script += "</script>";
		
		System.out.println(script);
		
		setScriptGraphicBar(script);
	}
	
	protected void initGraphicPie( SimulationInvestmentBean simBean ){
		
		setScriptGraphicPie("");
		
		String script = "<script>";
		
		 script += "data = new google.visualization.DataTable();";
		
		 script += "\ndata.addColumn('string', 'Riesgo');";
			
		 script += "\ndata.addColumn('number', 'Proyectos');";
		 script += "\ndata.addColumn( { type: 'string', role: 'tooltip', 'p': {'html': true} } );";
		 script += "\ndata.addRows(["+
                 		"	          ['A'	, "   +  simBean.getSumA()+",createCustomHTMLPieCntnt( 'A', '"+ simBean.getSumA() +"', "+simBean.getSize()+")], " +
                 		"	          ['B'	, "   +  simBean.getSumB()+",createCustomHTMLPieCntnt( 'B', '"+ simBean.getSumB() +"', "+simBean.getSize()+")], " +
                 		"	          ['C'	, "   +  simBean.getSumC()+",createCustomHTMLPieCntnt( 'C', '"+ simBean.getSumC() +"', "+simBean.getSize()+")], " +
                 		"	          ['D'	, "   +  simBean.getSumD()+",createCustomHTMLPieCntnt( 'D', '"+ simBean.getSumD() +"', "+simBean.getSize()+")], " +
                 		"	          ['E'	, "   +  simBean.getSumE()+",createCustomHTMLPieCntnt( 'E', '"+ simBean.getSumE() +"', "+simBean.getSize()+")] " +
                 		"        ]); " ;
		
		script += "</script>";
		
		System.out.println(script);
		
		setScriptGraphicPie(script);
		
	}
	
	protected void initValues(){
		
		FacesContext faces     = FacesContext.getCurrentInstance();
		ELContext context   = faces.getELContext();
		ELResolver resolver  = faces.getApplication().getELResolver();
		
        sesion         = (SessionBean)         resolver.getValue(context, null, "sessionBean");
		
		
		SystemParamPK sysPK = new SystemParamPK();
		 
		sysPK.setCompany_id(1);
		sysPK.setSystem_param_id(58); // monto minimo para simular inversiones
		
		SystemParam sys =  systemparamservice.loadSelectedSystemParam(sysPK);
		
		setMinAmmount( sys.getValue() );
		
		sysPK.setSystem_param_id(59); // monto máximo para simular inversiones
		
		sys =  systemparamservice.loadSelectedSystemParam(sysPK);
		
		setMaxAmmount( sys.getValue() );
		
		
		setAmmount(AMMOUNT_INIT);
		
		riskArray = new ArrayList<String>();
		
		riskArray.add("A");
		riskArray.add("B");
		riskArray.add("C");
		riskArray.add("D");
		riskArray.add("E");
		
		genderArray = new ArrayList<String>();
		
		genderArray.add("M");
		genderArray.add("F");
		
		purposeArray = new ArrayList<String>();
		
		purposeArray.add("Compra de mercancía");
		purposeArray.add("Mejoras de vivienda");
		purposeArray.add("Pagar deudas");
		purposeArray.add("Mejoras de negocio");
		purposeArray.add("otros");
		
		termArray = new ArrayList<String>();
		
		termArray.add("4T6");
		termArray.add("7T9");
		termArray.add("10T12");
		termArray.add("13T15");
		termArray.add("16T18");
		
	}
	
}
