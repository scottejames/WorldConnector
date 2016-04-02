package com.shineyappythings.test;

import java.io.IOException;

import com.shineyappythings.restfulservice.Configuration;
import com.shineyappythings.restfulservice.Database;
import com.shineyappythings.restfulservice.Message;

public class GetLatestMessageFromDB
{

	public static void main(String [] args) throws IOException
	{
		if (args.length>0)
    		Configuration.getInstance().setDefaultMode(args[0]);
    	Configuration.getInstance().loadProperties();
    	
		Message message = Database.getInstance().getLatestMessage();
		System.out.println("Message" + message);
		
	}
}
