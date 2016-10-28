package mx.com.kubo.controller.infusion;

import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClient;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfusionSoft {

//	public static void main(String[] args) throws MalformedURLException, XmlRpcException {
//
//		// 113  Consulta Satisfactoria
//		
////		String email = "alan.lazalde@gmail.com";
////		
////		String reason = "Acepta Registro Kubo.Financiero";
////		
////		Integer contactID = altaContacto("Alan2", "Lazalde2" , email );
//////		
////		opt_in_emai( email,  reason);
//		
//		addTAgToContact( 595, 113 );
//		
//		
////		actualizaContacto( contactID, "Alan Gustavo", "Lazalde" , "gustavo.lazalde@gmail.com"  );
//		
//		System.out.println( "Finaliza InfusionSoft ..." );
//		
//	}
	
	@SuppressWarnings(value = { "all" })
	public  boolean opt_in_emai( String email, String reason){
		
		try{
			
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("https://su293.infusionsoft.com:443/api/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			// The secure encryption key
			String key = "48f94a85ba5f46640ab8c51a3f25b74d";
			/*************************************************
			 * * ADD CONTACT TO DATABASE *
			 *************************************************/
			List parameters = new ArrayList();
			parameters.add(key); // The secure key
			parameters.add(email); // The table we will be adding to
			parameters.add(reason); // The table we will be adding to
//			parameters.add(contactData); // The data to be added
			// Make the call
			Boolean addTag = (Boolean) client.execute("APIEmailService.optIn", parameters);
			System.out.println("opt_in email: " + email+ " - " + addTag);
			return addTag;
			
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	@SuppressWarnings(value = { "all" })
	public boolean addTAgToContact( Integer contactID, Integer tagId ){
		
		try{
			
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("https://su293.infusionsoft.com:443/api/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			// The secure encryption key
			String key = "48f94a85ba5f46640ab8c51a3f25b74d";
			/*************************************************
			 * * ADD CONTACT TO DATABASE *
			 *************************************************/
			List parameters = new ArrayList();
			parameters.add(key); // The secure key
			parameters.add(contactID); // The table we will be adding to
			parameters.add(tagId); // The table we will be adding to
//			parameters.add(contactData); // The data to be added
			// Make the call
			Boolean addTag = (Boolean) client.execute("ContactService.addToGroup", parameters);
			System.out.println("Tags add " + addTag);
			return addTag;
			
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@SuppressWarnings(value = { "all" })
	public boolean sendRejectMailForEFL( String email, String body ){
		
		try{
			
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("https://su293.infusionsoft.com:443/api/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			// The secure encryption key
			String key = "48f94a85ba5f46640ab8c51a3f25b74d";
			
			
			/*************************************************
			 * * ADD CONTACT TO DATABASE *
			 *************************************************/
			
			String [] contactlist = new  String [0];
			
			//contactlist[0] = email;
			
			String fromAddress="soporte@kubofinanciero.com";
			String toAddress = email;
			String ccAddresses = "";
			String bccAddresses = "";
			String contentType = "Multipart";
			String subject = "SUBJECT : MY SUBJECT";
			String htmlBody = "Hola HTML";
			String textBody  = "Hola TEXT ";
			
			List parameters = new ArrayList();
			parameters.add(key); // The secure key
			parameters.add(contactlist); // The table we will be adding to
			parameters.add(fromAddress); // The table we will be adding to
//			parameters.add(contactData); // The data to be added
			
			parameters.add(toAddress);
			parameters.add(ccAddresses);
			parameters.add(bccAddresses);
			parameters.add(contentType);
			parameters.add(subject);
			parameters.add(htmlBody);
			parameters.add(textBody);
			
			
			
			// Make the call
			Boolean addTag = (Boolean) client.execute("APIEmailService.sendEmail", parameters);
			System.out.println("SendMail TO " + email);
			return addTag;
			
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@SuppressWarnings(value = { "all" })
	public Integer altaContacto(String firstName, String lastName , String email ){
		
		try {

			// Sets up the java client, including the api url
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("https://su293.infusionsoft.com:443/api/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			// The secure encryption key
			//String key = "9a73a7meb587v9jgjkvfgjqf";
			String key = "48f94a85ba5f46640ab8c51a3f25b74d";
			/*************************************************
			 * * ADD CONTACT TO DATABASE *
			 *************************************************/
			List parameters = new ArrayList();
			Map<String,String> contactData = new HashMap();
			contactData.put("FirstName", firstName);
			contactData.put("LastName", lastName);
			contactData.put("Email", email);
			parameters.add(key); // The secure key
			parameters.add("Contact"); // The table we will be adding to
			parameters.add(contactData); // The data to be added
			// Make the call
			Integer contactId = (Integer) client.execute("DataService.add", parameters);
			System.out.println("Contact added was " + contactId);
			
			return contactId;
			
			/*************************************************
			 * * ADD CONTACT TO GROUP *
			 ************************************************* /
			int groupId = 97; // The group we will be adding to
			List parameters2 = new ArrayList();
			parameters2.add(key); // Secure key
			parameters2.add(contactId); // Id of the contact we just added
			parameters2.add(groupId); // Id of the group we want to add to
			Boolean success = (Boolean) client.execute("ContactService.addToGroup", parameters2);
			System.out.println("Added to group: " + success);
			/*************************************************
			 * * LIST ALL CONTACTS IN GROUP *
			 ************************************************* /
			List fields = new ArrayList(); // What fields we will be selecting
			fields.add("ContactGroup");
			fields.add("ContactId");
			List parameters3 = new ArrayList();
			parameters3.add(key); // Secure key
			parameters3.add("ContactGroupAssign"); // What table we are looking
													// in
			parameters3.add(new Integer(50)); // How many records to return
			parameters3.add(new Integer(0)); // Which page of results to display
			parameters3.add("GroupId"); // The field we are querying on
			parameters3.add(new Integer(groupId)); // THe data to query on
			parameters3.add(fields); // what fields to select on return
			// Make call - the result is an array of structs
			Object[] contacts = (Object[]) client.execute("DataService.findByField", parameters3);
			// Loop through results
			for (int i = 0; i < contacts.length; i++) {
				// Each item in the array is a struct
				Map contact = (Map) contacts[i];
				System.out.println(
						"Contact " + contact.get("ContactId") + " was found in group " + contact.get("ContactGroup"));
			}
			
			*/
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings(value = { "all" })
	public Integer actualizaTelefonoContacto(Integer contactId,  String phone){
		
		try {

			// Sets up the java client, including the api url
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("https://su293.infusionsoft.com:443/api/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			// The secure encryption key
			//String key = "9a73a7meb587v9jgjkvfgjqf";
			String key = "48f94a85ba5f46640ab8c51a3f25b74d";
			/*************************************************
			 * * ADD CONTACT TO DATABASE *
			 *************************************************/
			List parameters = new ArrayList();
			Map<String,String> contactData = new HashMap();
			contactData.put("Phone1", phone);
			parameters.add(key); // The secure key
			parameters.add(contactId);
			parameters.add(contactData); // The data to be added
			// Make the call
			contactId = (Integer) client.execute("ContactService.update", parameters);
			System.out.println("Contact Phone updated: " + contactId);
			
			return contactId;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings(value = { "all" })
	public Integer actualizaContacto(Integer contactId,  String firstName, String lastName , String email ){
		
		try {

			// Sets up the java client, including the api url
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("https://su293.infusionsoft.com:443/api/xmlrpc"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			// The secure encryption key
			//String key = "9a73a7meb587v9jgjkvfgjqf";
			String key = "48f94a85ba5f46640ab8c51a3f25b74d";
			/*************************************************
			 * * ADD CONTACT TO DATABASE *
			 *************************************************/
			List parameters = new ArrayList();
			Map<String,String> contactData = new HashMap();
			contactData.put("FirstName", firstName);
			contactData.put("LastName", lastName);
			contactData.put("Email", email);
			parameters.add(key); // The secure key
			parameters.add(contactId);
			parameters.add(contactData); // The data to be added
			// Make the call
			contactId = (Integer) client.execute("ContactService.update", parameters);
			System.out.println("Contact updated: " + contactId);
			
			return contactId;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
