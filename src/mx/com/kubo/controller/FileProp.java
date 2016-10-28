/* ----------------------------------------------------------------------
 * Copyright (c) 2010	GoWeb
 *   Todos los Derechos Reservados.
 * 
 *   Este software contiene información totalmente confidencial propiedad de GoWeb. 
 *   Queda totalmente prohibido su uso o divulgación en forma parcial o total y solamente 
 *   podra ser utilizada de acuerdo a los términos y estatutos que determine la empresa
 *	
 *	Diseño:			Mauricio Reyes Valdez
 *					mauricio.valdez@goweb.tv
 *					Goweb
 *
 *	Construcción: 	Mauricio Reyes Valdez
 *					mauricio.valdez@goweb.tv
 *					Goweb
 *
 *	OCT 22, 2010, 09:00:00 AM
 * ---------------------------------------------------------------------*/
package mx.com.kubo.controller;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Administrator
 *
 */
public class FileProp 
	{
	private java.util.Properties 	properties;
	private java.lang.String 		path;
	
	//Variables constantes que almacenan la ubicacion de los archivos de propiedades
	private final static java.lang.String configPath = "config.properties";
	
	
	//Variables con cierta config cargada
	final public static FileProp propConfig;
	
	
	static
		{
		propConfig = new FileProp( configPath);
		}
	
	//Constructor privado
	private FileProp( java.lang.String pathFileProp )
		{
		try
			{
			properties = new Properties();
			properties.load( FileProp.class.getResourceAsStream( pathFileProp ) );
			}
		catch( IOException ioException)
			{
			ioException.printStackTrace();
			}
		}
	
	/**
	 * Funcion para obtener la propiedad deseada
	 * @param nombreProp indica la clave para la propiedad
	 * @return
	 */
	public java.lang.String getProp( java.lang.String nombreProp )
		{
		return properties.getProperty( nombreProp );
		}
	
	/**
	 * Funcion para cambiar el archivo de propiedades del cual se desea obtener la informacion
	 * @param path direccion del nuevo archivo de propiedades
	 * @return
	 */
	public Boolean reLoad( java.lang.String path )
		{
		try
			{
			this.path = path;
			properties.load( FileProp.class.getResourceAsStream( path ) );
			return true;
			}
		catch( IOException ioException)
			{
			ioException.printStackTrace();
			return false;
			}
		}
	
	/**
	 * Muestra a que archivo de propiedades esta referenciado
	 * @return
	 */
	public java.lang.String getPropertiePath( )
		{
		return path;
		}
	
	/**
	 * Regresa el objeto de propiedades del objeto
	 * @return
	 */
	public java.util.Properties getThisPropertie()
		{
		return this.properties;
		}
	}