package mx.com.kubo.session;

import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.faces.application.ResourceHandler;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * When the session destroyed, MySessionListener will do necessary logout
 * operations. Later, at the first request of client, this filter will be fired
 * and redirect the user to the appropriate timeout page if the session is not
 * valid.
 * 
 * 
 */

public class SessionTimeoutFilter implements Filter {

	// This should be your default Home or Login page
	// "login.seam" if you use Jboss Seam otherwise "login.jsf"
	// "login.xhtml" or whatever

	// private String timeoutPage = "sessionExpired.jsf";

	// private String timeoutPage = "jsf/expired.jsf";

	private String timeoutPage = "Portal/sesion-expirada.xhtml";

	public void init(FilterConfig filterConfig) throws ServletException {

		String urls = filterConfig.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		while (token.hasMoreTokens()) {
			timeoutPage = token.nextToken();

		}
		
		timeoutPage = "Portal/index.xhtml";
		//// // System.out.println(".... Asignando pagina redirec a " + timeoutPage + "  .... ");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		//System.out.println( ".... Redirigiendo a "+ timeoutPage +" .... ");
		
		String urltoredirect = "/Portal/index.xhtml";

		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {

			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			
			String uri = httpServletRequest.getRequestURI() ;
			String qs  = httpServletRequest.getQueryString() ;
			

			// is session expire control required for this request?
			if (isSessionControlRequiredForThisResource(httpServletRequest)) {
				// is session invalid?
				try {
					if (isSessionInvalid(httpServletRequest)) {
						
						/********************************************************************************************/
						
						System.out.println( "\n");
						System.out.println( "\n");
						System.out.println( "sessionID: " +  httpServletRequest.getRequestedSessionId() );
						System.out.println( "\n");
						System.out.println( "\n");
						
						if( uri.indexOf("/Portal") != (-1) && qs != null && uri.indexOf("/registro.xhtml") == (-1) && uri.indexOf("/controltable.xhtml") == (-1) && uri.indexOf("/registroCoach.xhtml") == (-1) ){
						
							uri =  uri.substring( uri.indexOf("/Portal") ) ;
							urltoredirect = uri+"?"+qs;
							
						
						}else if( uri.indexOf("comenzar-registro.xhtml") != (-1) ){
							
							uri =  uri.substring( uri.indexOf("/Portal") ) ;
							
							if( qs != null ){
								uri = uri+"?"+qs;
							}
							
							urltoredirect = uri;
							
						}

						timeoutPage = urltoredirect;
						
						System.out.println( ".... Redirigiendo a "+ timeoutPage +" .... ");
						
						if ("partial/ajax".equals(httpServletRequest.getHeader("Faces-Request"))) {

							
							String sesId = httpServletRequest.getRequestedSessionId();
							String timeoutUrl = httpServletRequest.getContextPath() +"/"+ getTimeoutPage();
							
							if( sesId != null ){
							
								// System.out.println("IF  partial/ajax sessionId: " + sesId );
	
								HttpSession session     = httpServletRequest.getSession();
								
								if( session != null ){
									
										ServletContext servlet     = session.getServletContext();
										
										if(servlet != null ){
										
											Hashtable<String, String> ht = (Hashtable<String, String>)servlet.getAttribute( "logOutuser" );
										
											if( ht != null && ht.containsKey( sesId) ){
												
												// System.out.println( "Eliminando Session: " + sesId );
												timeoutUrl = httpServletRequest.getContextPath() +"/Portal/sesion-expirada.xhtml";
												
												ht.remove(sesId);
												
											}else{
												timeoutUrl = httpServletRequest.getContextPath() +urltoredirect;
											}
											
										}else{
											// System.out.println( "servlet is null" );
										
											timeoutUrl = httpServletRequest.getContextPath() +urltoredirect;
								
										}
											
								}else{
									// System.out.println( "httpsession is null" );
								}
									
								
							}else{
								// System.out.println("IF  partial/ajax HttpSession:session ID null " );
								timeoutUrl = httpServletRequest.getContextPath() + urltoredirect;
							}
							
							// System.out.println("IF  partial/ajax  Session is invalid! Correct redirecting to timeoutpage : " + timeoutUrl);
							
							httpServletResponse.setContentType("text/xml");
							httpServletResponse.getWriter().append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").printf(
									"<partial-response><redirect url=\"%s\"></redirect></partial-response>",
									timeoutUrl);
						} else {
								
							System.out.println( " SessionTimeout URI(else): " + httpServletRequest.getRequestURI() );
							
							System.out.println( " SessionTimeout URL(else): " + httpServletRequest.getRequestURL() );
							
							String timeoutUrl = httpServletRequest.getContextPath() + urltoredirect;
							
							if( httpServletRequest.getSession(false) == null ){
								timeoutUrl = httpServletRequest.getContextPath() + urltoredirect;
								httpServletRequest.getSession(true);
							}
							
							// System.out.println("ELSE  partial/ajax");
							// System.out.println("ELSE  partial/ajax  Session is invalid! Correct redirecting to timeoutpage : " + timeoutUrl);
							httpServletResponse.sendRedirect(timeoutUrl);
						}

						/********************************************************************************************/

						return;
					}
				} catch (Exception e) {

					 // System.out.println("Session is invalid! from catch redirecting to timeoutpage : ");
					e.printStackTrace();
				}
			}
		}
		try {

			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			
			
			
			if (!httpServletRequest.getRequestURI()
					.startsWith(httpServletRequest.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip
																												// JSF
																												// resources
																												// (CSS/JS/Images/etc)
				// System.out.println( "TRY URI: " + httpServletRequest.getRequestURI() );

				httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																										// 1.1.
				httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP
																		// 1.0.
				httpServletResponse.setDateHeader("Expires", 0); // Proxies.

			}
			
			
			filterChain.doFilter(request, response);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/*
	 * session shouldn't be checked for some pages. For example: for timeout
	 * page.. Since we're redirecting to timeout page from this filter, if we
	 * don't disable session control for it, filter will again redirect to it
	 * and this will be result with an infinite loop...
	 */

	private boolean isSessionControlRequiredForThisResource(HttpServletRequest httpServletRequest) {

//		String requestPath = httpServletRequest.getRequestURI();
//		
//		// // System.out.println( "isSessionControlRequiredForThisResource URI: " + requestPath);
//		
//		boolean controlRequired = !requestPath.contains(getTimeoutPage());
//		return controlRequired;
		
		return true;
		
	}

	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
				&& !httpServletRequest.isRequestedSessionIdValid();
		return sessionInValid;
	}

	public void destroy() {
	}

	public String getTimeoutPage() {
		return timeoutPage;
	}

	public void setTimeoutPage(String timeoutPage) {
		this.timeoutPage = timeoutPage;
	}

}