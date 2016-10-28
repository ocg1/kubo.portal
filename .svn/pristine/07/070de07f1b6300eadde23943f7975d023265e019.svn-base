package mx.com.kubo.bean;

import java.util.List;

import mx.com.kubo.model.SimulationBase;

public class SimulationInvestmentBean extends SimulationInvestmentDMO {

	
	public SimulationInvestmentBean( List<SimulationBase> lst, Double monto_a_invertir){
		
		setBaseLst(lst);
		setSize(lst.size());
		setMonto_a_invertir(monto_a_invertir);
		setCuota( calculaCuota() );
		inicializaCapital();
		
		//inicializaInteres();
		
	}
	
	private void inicializaCapital(){
		
		for( SimulationBase tmp : getBaseLst() ){
			
			if( tmp.getNo_pago() == 0 ){
				
				double factor = getFactor( tmp.getAmmount(),getCuota() );
			
				setSumcap1(  (getSumcap1() +getRealValue(tmp.getCapital_fee_1(),factor)) );
				setSumcap2(  (getSumcap2() +getRealValue(tmp.getCapital_fee_2(),factor)) );
				setSumcap3(  (getSumcap3() +getRealValue(tmp.getCapital_fee_3(),factor)) );
				setSumcap4(  (getSumcap4() +getRealValue(tmp.getCapital_fee_4(),factor)) );
				setSumcap5(  (getSumcap5() +getRealValue(tmp.getCapital_fee_5(),factor)) );
				setSumcap6(  (getSumcap6() +getRealValue(tmp.getCapital_fee_6(),factor)) );
				setSumcap7(  (getSumcap7() +getRealValue(tmp.getCapital_fee_7(),factor)) );
				setSumcap8(  (getSumcap8() +getRealValue(tmp.getCapital_fee_8(),factor)) );
				setSumcap9(  (getSumcap9()+getRealValue(tmp.getCapital_fee_9(),factor)) );
				setSumcap10( (getSumcap10()+getRealValue(tmp.getCapital_fee_10(),factor)) );
				setSumcap11( (getSumcap11()+getRealValue(tmp.getCapital_fee_11(),factor)) );
				setSumcap12( (getSumcap12()+getRealValue(tmp.getCapital_fee_12(),factor)) );
				setSumcap13( (getSumcap13()+getRealValue(tmp.getCapital_fee_13(),factor)) );
				setSumcap14( (getSumcap14()+getRealValue(tmp.getCapital_fee_14(),factor)) );
				setSumcap15( (getSumcap15()+getRealValue(tmp.getCapital_fee_15(),factor)) );
				setSumcap16( (getSumcap16()+getRealValue(tmp.getCapital_fee_16(),factor)) );
				setSumcap17( (getSumcap17()+getRealValue(tmp.getCapital_fee_17(),factor)) );
				setSumcap18( (getSumcap18()+getRealValue(tmp.getCapital_fee_18(),factor)) );
				
				
				setSumint1(  (getSumint1() + getRealValue(tmp.getInteres_fee_1(),factor)) );
				setSumint2(  (getSumint2() +getRealValue(tmp.getInteres_fee_2(),factor)) );
				setSumint3(  (getSumint3() +getRealValue(tmp.getInteres_fee_3(),factor)) );
				setSumint4(  (getSumint4() +getRealValue(tmp.getInteres_fee_4(),factor)) );
				setSumint5(  (getSumint5() +getRealValue(tmp.getInteres_fee_5(),factor)) );
				setSumint6(  (getSumint6()+getRealValue(tmp.getInteres_fee_6(),factor)) );
				setSumint7(  (getSumint7()+getRealValue(tmp.getInteres_fee_7(),factor)) );
				setSumint8(  (getSumint8()+getRealValue(tmp.getInteres_fee_8(),factor)) );
				setSumint9(  (getSumint9()+getRealValue(tmp.getInteres_fee_9(),factor)) );
				setSumint10( (getSumint10()+getRealValue(tmp.getInteres_fee_10(),factor)) );
				setSumint11( (getSumint11()+getRealValue(tmp.getInteres_fee_11(),factor)) );
				setSumint12( (getSumint12()+getRealValue(tmp.getInteres_fee_12(),factor)) );
				setSumint13( (getSumint13()+getRealValue(tmp.getInteres_fee_13(),factor)) );
				setSumint14( (getSumint14()+getRealValue(tmp.getInteres_fee_14(),factor)) );
				setSumint15( (getSumint15()+getRealValue(tmp.getInteres_fee_15(),factor)) );
				setSumint16( (getSumint16()+getRealValue(tmp.getInteres_fee_16(),factor)) );
				setSumint17( (getSumint17()+getRealValue(tmp.getInteres_fee_17(),factor)) );
				setSumint18( (getSumint18()+getRealValue(tmp.getInteres_fee_18(),factor)) );
				
			}
			
			if(tmp.getCapital_fee_1() != 0 )
				setSumVig1( getSumVig1() + 1 );
			
			if(tmp.getCapital_fee_2() != 0 )
				setSumVig2( getSumVig2() + 1 );
			
			if(tmp.getCapital_fee_3() != 0 )
				setSumVig3( getSumVig3() + 1 );
			
			if(tmp.getCapital_fee_4() != 0 )
				setSumVig4( getSumVig4() + 1 );
			
			if(tmp.getCapital_fee_5() != 0 )
				setSumVig5( getSumVig5() + 1 );
			
			if(tmp.getCapital_fee_6() != 0 )
				setSumVig6( getSumVig6() + 1 );
			
			if(tmp.getCapital_fee_7() != 0 )
				setSumVig7( getSumVig7() + 1 );
			
			if(tmp.getCapital_fee_8() != 0 )
				setSumVig8( getSumVig8() + 1 );
			
			if(tmp.getCapital_fee_9() != 0 )
				setSumVig9( getSumVig9() + 1 );
			
			if(tmp.getCapital_fee_10() != 0 )
				setSumVig10( getSumVig10() + 1 );
			
			if(tmp.getCapital_fee_11() != 0 )
				setSumVig11( getSumVig11() + 1 );
			
			if(tmp.getCapital_fee_12() != 0 )
				setSumVig12( getSumVig12() + 1 );
			
			if(tmp.getCapital_fee_13() != 0 )
				setSumVig13( getSumVig13() + 1 );
			
			if(tmp.getCapital_fee_14() != 0 )
				setSumVig14( getSumVig14() + 1 );
			
			if(tmp.getCapital_fee_15() != 0 )
				setSumVig15( getSumVig15() + 1 );
			
			if(tmp.getCapital_fee_16() != 0 )
				setSumVig16( getSumVig16() + 1 );
			
			if(tmp.getCapital_fee_17() != 0 )
				setSumVig17( getSumVig17() + 1 );
			
			if(tmp.getCapital_fee_18() != 0 )
				setSumVig18( getSumVig18() + 1 );
			
			
			switch ( tmp.getKubo_score_a().charAt(0)){
			
				case 'A':
					setSumA(( getSumA()+1 ));
				break;
				
				case 'B':
					setSumB(getSumB()+1);
				break;
				
				case 'C':
					setSumC(getSumC()+1);
				break;
				
				case 'D':
					setSumD(getSumD()+1);
				break;
				
				case 'E':
					setSumE(getSumE()+1);
				break;
			
			}
			
		}
		
		sumadeTotales();
		
	}
	
	private void sumadeTotales(){
		
		setSumcap1(  getSumcap1()  );
		setSumcap2(  getSumcap1() + getSumcap2() );
		setSumcap3(  getSumcap2() +getSumcap3());
		setSumcap4(  getSumcap3() +getSumcap4() );
		setSumcap5(  getSumcap4() +getSumcap5() );
		setSumcap6(  getSumcap5() +getSumcap6() );
		setSumcap7(  getSumcap6() +getSumcap7() );
		setSumcap8(  getSumcap7() +getSumcap8() );
		setSumcap9(  getSumcap8() +getSumcap9());
		setSumcap10( getSumcap9() +getSumcap10() );
		setSumcap11( getSumcap10() +getSumcap11() );
		setSumcap12( getSumcap11() +getSumcap12() );
		setSumcap13( getSumcap12() +getSumcap13());
		setSumcap14( getSumcap13() +getSumcap14() );
		setSumcap15( getSumcap14() +getSumcap15() );
		setSumcap16( getSumcap15() +getSumcap16() );
		setSumcap17( getSumcap16() +getSumcap17() );
		setSumcap18( getSumcap17() +getSumcap18());
		
		
		setSumint1(  getSumint1());
		setSumint2(  getSumint1() + getSumint2() );
		setSumint3(  getSumint2() + getSumint3() );
		setSumint4(  getSumint3() + getSumint4() );
		setSumint5(  getSumint4() + getSumint5() );
		setSumint6(  getSumint5() + getSumint6() );
		setSumint7(  getSumint6() + getSumint7() );
		setSumint8(  getSumint7() + getSumint8() );
		setSumint9(  getSumint8() + getSumint9() );
		setSumint10( getSumint9() + getSumint10() );
		setSumint11( getSumint10() +getSumint11() );
		setSumint12( getSumint11() +getSumint12() );
		setSumint13( getSumint12() +getSumint13() );
		setSumint14( getSumint13() +getSumint14() );
		setSumint15( getSumint14() +getSumint15() );
		setSumint16( getSumint15() +getSumint16() );
		setSumint17( getSumint16() +getSumint17() );
		setSumint18( getSumint17() +getSumint18() );
		
	}
	
	private double getRealValue(double value, double factor){
		
		double t = (factor * value)/100;
		return (double)(Math.round((t)*100))/100;
		
	}
	
	private double getFactor( double monto,double cuota ){
		
		double t =  ( cuota * 100 ) / monto;
		return (double)(Math.round((t)*100))/100;
				
	}
	
	private double calculaCuota(){
		return (getMonto_a_invertir() / getSize() );
	}
	
	
}
