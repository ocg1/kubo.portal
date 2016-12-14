package mx.com.kubo.managedbeans.home;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;

@ManagedBean (name = "inicioValues") @SessionScoped
public class InicioValuesIMP extends InicioValuesAMO
implements Serializable 
{		
	private static final long serialVersionUID = 8112488787001164841L;

	@PostConstruct
	public void init()
	{					
		try
		{	
			System.out.println("InicioValuesIMP.init  ---  ok ");
			
			faces    = FacesContext.getCurrentInstance();
			
			external = faces.getExternalContext();
			resolver = faces.getApplication().getELResolver();
			
			context  = faces.getELContext();
			
			sesion = null;
			
			try{
			
			sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
			
			}catch(IllegalStateException i ){
				
				faces    = FacesContext.getCurrentInstance();
				
				external = faces.getExternalContext();
				resolver = faces.getApplication().getELResolver();
				
				context  = faces.getELContext();
				
				
				sesion =  (SessionBean) resolver.getValue(context, null, "sessionBean");
			}
			
			init_sesion();
			
			init_external_params();	
			
			init_medium();
			
			init_origin_campaign();														
			
			init_partner();		
			
			statistics = service_access.getHomeStatistics();
		
			
		} catch(Exception e) {
			e.printStackTrace();
			area = "L";
		}		
	}
	
	public void decodeURL(){
		try{
			
			if( url_value != null && url_value.trim().length() > 0 ){
			
				URL url = new URL(url_value) ;
				
				Map<String, List<String>> map = splitQuery( url);
				
				List<String> this_utm_partner_id = map.get("utm_source");
				
				if( this_utm_partner_id != null && this_utm_partner_id.size() > 0){
				
					utm_partner_id = this_utm_partner_id.get(0);
					
					if( this_utm_partner_id.get(0).equalsIgnoreCase("INVITEDBY") || this_utm_partner_id.get(0).equalsIgnoreCase("REC")   ){
						
						List<String> refe = map.get("inf_field_FirstName");
						
						referred = refe.get(0);
						
						initReferred();
					}
				
				}
			
			}
		
		}catch(Exception e){
			
		}
	}
	
	private  Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException {
		  final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		  final String[] pairs = url.getQuery().split("&");
		  for (String pair : pairs) {
		    final int idx = pair.indexOf("=");
		    final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
		    if (!query_pairs.containsKey(key)) {
		      query_pairs.put(key, new LinkedList<String>());
		    }
		    final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
		    query_pairs.get(key).add(value);
		  }
		  return query_pairs;
		}
		
	public void validaSession()
	{
		faces     = FacesContext.getCurrentInstance();	
		external  = faces.getExternalContext();
		
		sessionUsed = (HttpSession) external.getSession(false);
				 		
		//System.out.println("Validando session: " + sessionUsed.getId() + "  "+ formatter.format(new Date()));	
		
		RequestContext requestContext   = RequestContext.getCurrentInstance();
		
		
		requestContext.addCallbackParam("validSession", "válida");
		
	}
}
