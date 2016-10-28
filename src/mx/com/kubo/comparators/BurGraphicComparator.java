package mx.com.kubo.comparators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import com.soa.model.businessobject.BurGraphic;

public class BurGraphicComparator implements Comparator<BurGraphic>{

	@Override
	public int compare(BurGraphic obj1, BurGraphic obj2) {
		// TODO Auto-generated method stub
		SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
		int res = -1;
		
		if(obj1 != null && obj2!=null){
		
			if( obj1.getCuenta()!= null && obj2.getCuenta()!=null ){
				res = obj1.getCuenta().compareTo(obj2.getCuenta());
				if(res != 0){
					return res;
				}
			}
			try {
				res = (format.parse(obj1.getMes()).compareTo(format.parse(obj2.getMes())));
				if(res != 0){
					return res;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

}
