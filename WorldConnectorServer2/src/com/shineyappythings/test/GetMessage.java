package com.shineyappythings.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class GetMessage
{
	public static void main (String [] args)
	{
	   Client client = Client.create();
	   WebResource webResource = client.resource("http://localhost:9998/messages");
	   String s = webResource.get(String.class);
	   
	   System.out.println("GET got: " + s);
	}

}
