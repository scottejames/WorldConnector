package com.scottejames.connection;

public class ServerInterfaceBuilder
{
	private static ServerInterfaceBuilder _instance = null;
	
	
	 public static ServerInterfaceBuilder getInstance()
	{
		if (_instance == null)
		{
			_instance = new ServerInterfaceBuilder();
		}
		return _instance;
		
	}
	 
	 public IServerInterface build()
	 {
		 return new ServerInterface();
	 }

}
