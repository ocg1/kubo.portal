package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class InvestmentSimulator extends InvestmentSimulatorPMO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init(){
		
		initValues();
		graficar();
		
	}
	
	public void setAmmountValue(){
		
		if(ammountStr != null && !ammountStr.equals(ammountStrTmp)){
			System.out.println(ammountStr);
			ammountStrTmp = ammountStr;
			
			setAmmount( Double.parseDouble( ammountStr.replaceAll(",", "") ) );
			
			graficar();
		}
		
	}
	
	public void graphicAction(){
		
//		setRisk();
//		setTerm();
//		setGender();
//		setDestino();
		graficar();
	}
	
	
	public void setRisk(){
		
		
		
		riskArray = new ArrayList<String>();
		
		if(riskElements != null && !riskElements.equals(riskElementsTmp)){
			System.out.println(riskElements);
			riskElementsTmp = riskElements;
			
			String riskArr[] =  riskElements.split("::");
			
			for( int i= 0 ; i < riskArr.length ; i++ ){
				
				riskArray.add( riskArr[i]);
				
			}
			
		}
		
	}
	
	public void setTerm(){
		
		termArray = new ArrayList<String>();
		
		if(termElements != null && !termElements.equals(termElementsTmp)){
			System.out.println(termElements);
			termElementsTmp = termElements;
			
			String termArr[] =  termElements.split("::");
			
			for( int i= 0 ; i < termArr.length ; i++ ){
				
				termArray.add( termArr[i]);
				
			}
			
			
		}
	}
	
	public void setGender(){
		
		genderArray = new ArrayList<String>();
		
		if(genderElements != null && !genderElements.equals(genderElementsTmp)){
			System.out.println(genderElements);
			genderElementsTmp = genderElements;
			
			String genderArr[] =  genderElements.split("::");
			
			for( int i= 0 ; i < genderArr.length ; i++ ){
				
				genderArray.add( genderArr[i]);
				
			}
			
			
		}
	}
	
	public void setDestino(){
		
		purposeArray = new ArrayList<String>();
		
		if(destinoElements != null && !destinoElements.equals(destinoElementsTmp)){
			System.out.println(destinoElements);
			destinoElementsTmp = destinoElements;
			
			String destinoArr[] =  destinoElements.split("::");
			
			for( int i= 0 ; i < destinoArr.length ; i++ ){
				
				purposeArray.add( destinoArr[i]);
				
			}
			
			
		}
	}
	
}
