package mx.com.kubo.bean;

import mx.com.kubo.model.FormAnalytics;
import mx.com.kubo.services.FormAnalyticsService;
import mx.com.kubo.tools.Utilities;

public class InteractorBean {

	
	public boolean saveFormAnalytics(FormAnalytics frm){
		try{
			
			FormAnalyticsService forman_alytics_service = Utilities.findBean("formAnalyticsServiceImp");
			
			return forman_alytics_service.addFormAnalytics( frm );
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
}
