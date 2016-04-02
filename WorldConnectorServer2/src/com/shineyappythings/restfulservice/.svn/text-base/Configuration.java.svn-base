package com.shineyappythings.restfulservice;

import java.io.IOException;
import java.util.ResourceBundle;

public class Configuration
{
	private static Configuration _instance = null;
	
	private String defaultMode = null;
	private String mode = null;
	private String databaseFile = null;
	

	private String baseURL = "http://192.168.0.12:9000/";

	private Configuration()
	{

	}

	public void loadProperties() throws IOException
	{
		ResourceBundle myResources = ResourceBundle.getBundle("default");
		System.out.println("Loading configuration ");
		
		if (defaultMode == null)
			mode = myResources.getString("configuration");
		else
			mode = defaultMode;
		
		baseURL = myResources.getString("baseURL." + mode);
		databaseFile = myResources.getString("databaseFile." + mode);
		
		System.out.println("Mode: " + mode);
		System.out.println("URL: " + baseURL);
		System.out.println("DB File: " + databaseFile);

	}

	synchronized static public Configuration getInstance()
	{
		if (_instance == null)
		{
			_instance = new Configuration();
		}
		return _instance;

	}
	public void setDefaultMode(String mode)
	{
		defaultMode = mode;
	}
	public String getURL()
	{
		return baseURL;
	}
	public String getDatabaseFile()
	{
		return databaseFile;
	}

	public static void main(String[] args) throws IOException
	{
		if (args[0]!=null &&args[0].length()>0)
    		Configuration.getInstance().setDefaultMode(args[0]);
		Configuration.getInstance().loadProperties();
	}

}
