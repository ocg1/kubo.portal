package mx.com.kubo.repositories.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import mx.com.kubo.bean.AltaClienteRequest;
import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.RFCCollector;
import mx.com.kubo.repositories.NaturalPersonDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NaturalPersonDaoImp extends DAONaturalPersonDMO
implements NaturalPersonDao
{		
	@Transactional
	public void saveNaturalPerson(NaturalPerson newNaturalPerson) 
	{
//		log.info("saveNaturalPerson.NaturalPersonDaoImp");
//		log.info("############################################################################");
//		
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println(newNaturalPerson.getFirst_name()+" "+newNaturalPerson.getMiddle_name()+" "+newNaturalPerson.getFather_last_name()+" "+newNaturalPerson.getMother_last_name());
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		
//		log.info("############################################################################");
		try
		{
			em.persist(newNaturalPerson);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public String getRFC(String name,String fatherLast,String motherLast, String birthday)
	{ 
		log.info("getRFC");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			List<RFCCollector> rfc=  em.createNamedQuery("rfcQuery",RFCCollector.class)
					.setParameter("name", quitaAcentos(name))
					.setParameter("fatherlast", quitaAcentos(fatherLast))
					.setParameter("motherlast", quitaAcentos(motherLast))
					.setParameter("birthday", fm.parse(birthday))
					.getResultList();
			
			if(rfc.isEmpty())
			{
				return "";
				
			} else {
				
				return rfc.get(0).getRfc();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public String generaCURP(NaturalPerson np,String estado)
	{
		String rfc= np.getMx_rfc().substring(0, 10);
		String nombre = quitaAcentos(np.getFirst_name());
		if(np.getMiddle_name()!=null&&np.getMiddle_name().length()>0)
			nombre += " "+quitaAcentos(np.getMiddle_name());
		nombre = checkNombre(nombre);
		
		String paterno = "";
		if( np.getFather_last_name() != null ){
			paterno = quitaAcentos(np.getFather_last_name());
		}
		
		String materno = "";
		
		if( np.getMother_last_name() != null ){
			materno = quitaAcentos(np.getMother_last_name());
		}
		
		if(paterno.trim().equals("") || paterno.trim().equals(".")  || paterno.trim().equals("X")  ){
			paterno = materno;
			materno = "X";
		}
		
		paterno =  checkNombre(paterno);
		materno =  checkNombre(materno);
		String sexo= np.getGender_id()==null?"":np.getGender_id()==1?"H":np.getGender_id()==2?"M":"";
		Date d = np.getDate_of_birth();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = f.format(d);
		String anio = strDate.split("/")[2];
		String curp ="";
		String hcurp ="";
		String edo= "";
		
		if(sexo.length() > 0){
		
		curp += rfc+sexo;
		if(!estado.equals("EXTRANJERO")){
			edo= estadoCurp(estado);
		}else{
			edo = "NE";
		}
		curp += edo;
		curp += primeraConsonanteInterna(paterno.toUpperCase());
		curp += primeraConsonanteInterna(materno.toUpperCase());
		curp += primeraConsonanteInterna(nombre.toUpperCase());
		if(Integer.parseInt(anio)<2000)
			hcurp += "0";
		else
			hcurp += "A";
		int suma = calculaNumeroVeri(curp+hcurp);
		if(((suma%10)-10)<= -10)
			hcurp += "0";
		else
			hcurp += ((suma%10)-10) *(-1);
		return curp+hcurp;
		
		}else{
			return "";
		}
		
	}
	
	private String checkNombre(String nombre)
	{
		 if(nombre.indexOf(" ") !=(-1)){
			 if((nombre.substring(0,nombre.indexOf(" ")).toUpperCase().equals("MARIA")||nombre.substring(0,nombre.indexOf(" ")).toUpperCase().equals("JOSE")||nombre.substring(0,nombre.indexOf(" ")).toUpperCase().equals("MA."))&& nombre.length()>5){
			           nombre=nombre.substring(nombre.indexOf(" ")+1);
			 }
			 if(nombre.indexOf(" ") !=(-1)&&(nombre.substring(0,nombre.indexOf(" ")).toUpperCase().equals("DE")||nombre.substring(0,nombre.indexOf(" ")).toUpperCase().equals("DEL"))){
			           nombre=nombre.substring(nombre.indexOf(" ")+1);
			 }
			 if(nombre.indexOf(" ") !=(-1)&&(nombre.substring(0,nombre.indexOf(" ")).toUpperCase().toUpperCase().equals("LOS")||nombre.substring(0,nombre.indexOf(" ")).toUpperCase().equals("LA"))){
			           nombre=nombre.substring(nombre.indexOf(" ")+1);
			 }
		 }
		 return nombre;
		}
	
	private int calculaNumeroVeri(String palabra){
		String [] tabla = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		int suma =0;
		for(int x = 0; x<palabra.length();x++){
			for(int y = 0; y <tabla.length;y++){
				if((""+palabra.charAt(x)+"").equals(tabla[y])){
					suma += y*((palabra.length()-x)+1);
					//System.out.println(palabra.charAt(x)+":  "+y+"*("+((palabra.length()-x)+1)+")");
				}
			}
		}
		return suma;
	}
	
	private String primeraConsonanteInterna(String palabra)
	{
		String vocales = "AEIOUÁÉÍÓÚÜ ";
		
		if(palabra.length()>0)
		{
			for(int i =1;i<palabra.length();i++){
				if(vocales.indexOf(palabra.charAt(i))== -1){
					if(!(palabra.charAt(i)+"").equals("Ñ"))
						return ""+palabra.charAt(i)+"";
					else
						return "X";
				}
			}
		}
		
		return "X";
	}
	
	public String estadoCurp(String estado)
	{
		String res = "";
		
		estado = quitaAcentos(estado);
		
		if(estado!=null&&!estado.equals(""))
		{
			
			//System.out.println("servlet estado: "+estado);
			if(estado.equals("BAJA CALIFORNIA")){
				res = "BC";
			}else if(estado.equals("BAJA CALIFORNIA SUR")){
				res = "BS";
			}else if(estado.equals("CAMPECHE")){
				res = "CC";
			}else if(estado.equals("DISTRITO FEDERAL")){
				res = "DF";
			}else if(estado.equals("NUEVO LEON")){
				res = "NL";
			}else if(estado.equals("QUERETARO DE ARTEAGA")){
				res = "QT";
			}else if(estado.equals("QUINTANA ROO")){
				res = "QR";
			}else if(estado.equals("SAN LUIS POTOSI")){
				res = "SP";
			}else if(estado.equals("MICHOACAN DE OCAMPO")){
				res = "MN";
			}else if(estado.equals("VERACRUZ LLAVE")){
				res = "VZ";
			}else if(estado.equals("COAHUILA DE ZARAGOZA")){
				res = "CL";
			}else{
				     res = ""+estado.charAt(0);
				     //System.out.println("res: "+res);
				     char c='a';
				      String vocales="aeiouAEIOU"; 
				      for(int a=estado.length()-1;a>0;a--){ 
				            c=estado.charAt(a); 
				            //System.out.println("c: "+c);
				            if(vocales.indexOf(c)==-1) 
				                  break; 
				      } 
				   res=res+c;
				   
			}
			
			return res;
		}
			
		else{
			
			return "NOEXISTE";
		}
		
	}
	
	public String generaHomoclaveRFC(NaturalPerson np){
		String rfc= np.getMx_rfc().substring(0, 10);
		String nombre = np.getFirst_name();
		if(np.getMiddle_name()!=null&&np.getMiddle_name().length()>0)
			nombre += " "+np.getMiddle_name();
		String paterno= np.getFather_last_name();
		String materno= np.getMother_last_name();
		String nombreCompleto = paterno.trim().toUpperCase()+" "+materno.trim().toUpperCase()+" "+nombre.trim().toUpperCase();
		String[] nombreEnNumero = new String[nombreCompleto.length()+1];
		nombreEnNumero [0] = "0";
		//System.out.println("tabla 1");
		for (int i =1; i<=nombreCompleto.length();i++)
		{
			char c; 
			if(nombreCompleto.charAt(i-1)=='Á'){
				c='A';
			}
			else if(nombreCompleto.charAt(i-1)=='É'){
				c='E';
			}
			else if(nombreCompleto.charAt(i-1)=='Í'){
				c='I';
			}
			else if(nombreCompleto.charAt(i-1)=='Ó'){
				c='O';
			}
			else if(nombreCompleto.charAt(i-1)=='Ú'){
				c='U';
			}
			else if(nombreCompleto.charAt(i-1)=='Ü'){
				c='U';
			}
			else {
				c = nombreCompleto.charAt(i-1);
			}
			if (tablaRFC1.containsKey(""+c+"")){
				//System.out.println(c+": "+(String.valueOf(tablaRFC1.get(""+c+""))));
				nombreEnNumero[i] = (String.valueOf(tablaRFC1.get(""+c+"")));
			}else{
				nombreEnNumero[i] = "00";
				//System.out.println(c+": 00");
			}
		}
		int valorSuma=0;
		String numeros ="0";
		for(int y  = 0; y <nombreEnNumero.length; y++){
			numeros += nombreEnNumero[y];
		}
		/*for (int i = 0; i < nombreEnNumero.length-1; i++)
		{
			valorSuma += ((Integer.parseInt(nombreEnNumero[i]) * 10) + Integer.parseInt(nombreEnNumero[i + 1])) * Integer.parseInt(nombreEnNumero[i + 1]);
			System.out.println("(("+Integer.parseInt(nombreEnNumero[i])+" * 10"+") + "+Integer.parseInt(nombreEnNumero[i + 1])+") * "+Integer.parseInt(nombreEnNumero[i + 1])+" = "+valorSuma);
		}*/
		//System.out.println(numeros);
		for (int i = 0; i < numeros.length()-1; i++)
		{
			valorSuma += ((Integer.parseInt(""+(numeros.charAt(i))+"") * 10) + Integer.parseInt(""+(numeros.charAt(i+1))+"")) * Integer.parseInt(""+(numeros.charAt(i+1))+"");
			//System.out.println("(("+Integer.parseInt(""+(numeros.charAt(i))+"")+" * 10"+") + "+Integer.parseInt(""+(numeros.charAt(i+1))+"")+") * "+Integer.parseInt(""+(numeros.charAt(i+1))+"")+" = "+valorSuma);
		}
		
		int div = 0, mod = 0;
		div = valorSuma % 1000;
		mod = div % 34;
		div = (div - mod) / 34;

		int indice = 0;
		String hc = "";  //los dos primeros caracteres de la homoclave
//		System.out.println("tabla 2");
//		System.out.println("DIV (1): " + div );
//		System.out.println("MOD (2): "+ mod);
		while (indice <= 1)
		{
			if (tablaRFC2.containsKey((indice == 0) ? div : mod)){
				//System.out.println(String.valueOf(tablaRFC2.get(((indice == 0) ? div : mod))));
				hc += (String.valueOf(tablaRFC2.get(((indice == 0) ? div : mod))));
			}else{
				//System.out.println("Z");
				hc += "Z";
			}
			indice++;
		}
		rfc += hc;
		
		int rfcAnumeroSuma = 0 ;
		int sumaParcial = 0;
		//System.out.println("tabla 3");
		for (int i = 0; i < rfc.length(); i++)
		{
			if (tablaRFC3.containsKey(""+rfc.charAt(i)+""))
			{
				//System.out.println(String.valueOf(""+rfc.charAt(i)+": "+tablaRFC3.get(""+rfc.charAt(i)+"")));
				rfcAnumeroSuma = Integer.parseInt(String.valueOf(tablaRFC3.get(""+rfc.charAt(i)+"")));
				sumaParcial += (rfcAnumeroSuma * (14 - (i + 1)));
			}
		}

		int moduloVerificador = sumaParcial % 11;
		if (moduloVerificador == 0)
			hc += "0";
		else
		{
			sumaParcial = 11 - moduloVerificador;
			if (sumaParcial == 10)
				hc += "A";
			else
				hc += ""+sumaParcial+"";
		}
		
		return hc;
	}
	
	public NaturalPerson existNaturalPersonByRFC(String rfc)
	{
		
		String query="from NaturalPerson where mx_rfc = ? and prospectus.safi_prospectus_id is not null";
		NaturalPerson res = null;
		
		List<NaturalPerson> gnNPrfc = em.createQuery(query,NaturalPerson.class).setParameter(1, rfc).getResultList();
		
		if(gnNPrfc.size()>0){
			res = gnNPrfc.get(0);
		}
		
		return res;
		
	}
	
	private static Hashtable<String , String> tablaRFC1 = new Hashtable<String , String>();
	private static Hashtable<Integer , String> tablaRFC2 = new Hashtable<Integer , String>();
	private static Hashtable<String , Integer> tablaRFC3 = new Hashtable<String , Integer>();
	
	static{
		
		tablaRFC1.put("&", "10");
		tablaRFC1.put("Ñ", "10");
		tablaRFC1.put("A", "11");
		tablaRFC1.put("B", "12");
		tablaRFC1.put("C", "13");
		tablaRFC1.put("D", "14");
		tablaRFC1.put("E", "15");
		tablaRFC1.put("F", "16");
		tablaRFC1.put("G", "17");
		tablaRFC1.put("H", "18");
		tablaRFC1.put("I", "19");
		tablaRFC1.put("J", "21");
		tablaRFC1.put("K", "22");
		tablaRFC1.put("L", "23");
		tablaRFC1.put("M", "24");
		tablaRFC1.put("N", "25");
		tablaRFC1.put("O", "26");
		tablaRFC1.put("P", "27");
		tablaRFC1.put("Q", "28");
		tablaRFC1.put("R", "29");
		tablaRFC1.put("S", "32");
		tablaRFC1.put("T", "33");
		tablaRFC1.put("U", "34");
		tablaRFC1.put("V", "35");
		tablaRFC1.put("W", "36");
		tablaRFC1.put("X", "37");
		tablaRFC1.put("Y", "38");
		tablaRFC1.put("Z", "39");
		tablaRFC1.put("0", "00");
		tablaRFC1.put("1", "01");
		tablaRFC1.put("2", "02");
		tablaRFC1.put("3", "03");
		tablaRFC1.put("4", "04");
		tablaRFC1.put("5", "05");
		tablaRFC1.put("6", "06");
		tablaRFC1.put("7", "07");
		tablaRFC1.put("8", "08");
		tablaRFC1.put("9", "09");
		
		
		tablaRFC2.put(0, "1");
		tablaRFC2.put(1, "2");
		tablaRFC2.put(2, "3");
		tablaRFC2.put(3, "4");
		tablaRFC2.put(4, "5");
		tablaRFC2.put(5, "6");
		tablaRFC2.put(6, "7");
		tablaRFC2.put(7, "8");
		tablaRFC2.put(8, "9");
		tablaRFC2.put(9, "A");
		tablaRFC2.put(10, "B");
		tablaRFC2.put(11, "C");
		tablaRFC2.put(12, "D");
		tablaRFC2.put(13, "E");
		tablaRFC2.put(14, "F");
		tablaRFC2.put(15, "G");
		tablaRFC2.put(16, "H");
		tablaRFC2.put(17, "I");
		tablaRFC2.put(18, "J");
		tablaRFC2.put(19, "K");
		tablaRFC2.put(20, "L");
		tablaRFC2.put(21, "M");
		tablaRFC2.put(22, "N");
		tablaRFC2.put(23, "P");
		tablaRFC2.put(24, "Q");
		tablaRFC2.put(25, "R");
		tablaRFC2.put(26, "S");
		tablaRFC2.put(27, "T");
		tablaRFC2.put(28, "U");
		tablaRFC2.put(29, "V");
		tablaRFC2.put(30, "W");
		tablaRFC2.put(31, "X");
		tablaRFC2.put(32, "Y");
		
		
		tablaRFC3.put("A", 10);
		tablaRFC3.put("B", 11);
		tablaRFC3.put("C", 12);
		tablaRFC3.put("D", 13);
		tablaRFC3.put("E", 14);
		tablaRFC3.put("F", 15);
		tablaRFC3.put("G", 16);
		tablaRFC3.put("H", 17);
		tablaRFC3.put("I", 18);
		tablaRFC3.put("J", 19);
		tablaRFC3.put("K", 20);
		tablaRFC3.put("L", 21);
		tablaRFC3.put("M", 22);
		tablaRFC3.put("N", 23);
		tablaRFC3.put("O", 25);
		tablaRFC3.put("P", 26);
		tablaRFC3.put("Q", 27);
		tablaRFC3.put("R", 28);
		tablaRFC3.put("S", 29);
		tablaRFC3.put("T", 30);
		tablaRFC3.put("U", 31);
		tablaRFC3.put("V", 32);
		tablaRFC3.put("W", 33);
		tablaRFC3.put("X", 34);
		tablaRFC3.put("Y", 35);
		tablaRFC3.put("Z", 36);
		tablaRFC3.put("0", 0);
		tablaRFC3.put("1", 1);
		tablaRFC3.put("2", 2);
		tablaRFC3.put("3", 3);
		tablaRFC3.put("4", 4);
		tablaRFC3.put("5", 5);
		tablaRFC3.put("6", 6);
		tablaRFC3.put("7", 7);
		tablaRFC3.put("8", 8);
		tablaRFC3.put("9", 9);
		tablaRFC3.put("", 24);
		tablaRFC3.put(" ", 37);
	}
	
	private String quitaAcentos(String str){
		str = str.replace("á", "a");
		str = str.replace("é", "e");
		str = str.replace("í", "i");
		str = str.replace("ó", "o");
		str = str.replace("ú", "u");
		str = str.replace("Á", "A");
		str = str.replace("É", "E");
		str = str.replace("Í", "I");
		str = str.replace("Ó", "O");
		str = str.replace("Ú", "U");
		str = str.replace("Ü", "U");
		str = str.replace("ü", "u");
		
		return str;
	}

	public IdentificationCollector getIdentificationCollector(AltaClienteRequest request)
	{
		try
		{			
			IdentificationCollector identification =  em.createNamedQuery("ifeQuery",IdentificationCollector.class)
					.setParameter("clientID",   request.getClientID())
					.setParameter("tipoIdentID",request.getTipoIdentID())
					.setParameter("oficial",    request.getOficial())
					.setParameter("numIdentif", request.getNumIdentif())
					.setParameter("fecExIden",  request.getFecExIden())
					.setParameter("fecVenIden", request.getFecVenIden())
					.setParameter("empresaID",  request.getEmpresaID())
					.setParameter("audUsuario", request.getAudUsuario())
					.setParameter("audFechaActual", request.getAudFechaActual())
					.setParameter("audDireccionIP", request.getAudDireccionIP())
					.setParameter("audProgramaID",  request.getAudProgramaID())
					.setParameter("audSucursal",    request.getAudSucursal())
					.setParameter("audNumTransaccion", request.getAudNumTransaccion())
					.getSingleResult();
			
			if(identification == null)
				return null;
			else{
				
				return identification;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public IdentificationCollector getIdentificationCollector(Long clientID, Integer tipoIdentID,
			String oficial, String numIdentif, Date fecExIden, Date fecVenIden,
			Integer empresaID, Integer audUsuario, Date audFechaActual,
			String audDireccionIP, String audProgramaID, Integer audSucursal,
			Integer audNumTransaccion) 
	{
		log.info("getIdentificationCollector");
		
		try
		{
			//List<IdentificationCollector> identification=  em.createNamedQuery("IFEQuery",IdentificationCollector.class)
			IdentificationCollector identification=  em.createNamedQuery("ifeQuery",IdentificationCollector.class)
					.setParameter("clientID", clientID)
					.setParameter("tipoIdentID", tipoIdentID)
					.setParameter("oficial", oficial)
					.setParameter("numIdentif", numIdentif)
					.setParameter("fecExIden", fecExIden)
					.setParameter("fecVenIden", fecVenIden)
					.setParameter("empresaID", empresaID)
					.setParameter("audUsuario", audUsuario)
					.setParameter("audFechaActual", audFechaActual)
					.setParameter("audDireccionIP", audDireccionIP)
					.setParameter("audProgramaID", audProgramaID)
					.setParameter("audSucursal", audSucursal)
					.setParameter("audNumTransaccion", audNumTransaccion)
					.getSingleResult();
			if(identification == null)
				return null;
			else{
				
				return identification;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<NaturalPerson> getPersonListByCURP(String curp)
	{		
		try
		{			
			String query = "from NaturalPerson where mx_curp like ?";
			
			List<NaturalPerson> lista_natural_person = em.createQuery(query, NaturalPerson.class).setParameter(1, '%'+curp+'%').getResultList();
			
			return lista_natural_person;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}	
}
