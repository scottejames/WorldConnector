package com.shineyappythings.test;

import java.io.IOException;

import com.shineyappythings.restfulservice.Message;
import com.shineyappythings.restfulservice.MessageBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class PostMessage
{

	public static void main (String [] args) throws IOException
	{
		
		Message message = new Message("COmment","sjames1");
		
		String result = MessageBuilder.createStringFromMessage(message);
		
	   Client client = Client.create();
	   WebResource webResource = client.resource("http://localhost:9998/messages");
	   String response = webResource.post(String.class,result);
	   
	   System.out.println("POST got: " + response);
	}
}
