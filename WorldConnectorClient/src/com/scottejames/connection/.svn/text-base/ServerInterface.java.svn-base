package com.scottejames.connection;

import java.io.IOException;
import java.util.List;

import com.shineyappythings.restfulservice.Message;
import com.shineyappythings.restfulservice.MessageBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ServerInterface implements IServerInterface
{

	public List<Message> getNewMessages()
	{
		return null;
		
	}
	public boolean postMessage(Message message) throws IOException
	{
		String result = MessageBuilder.createStringFromMessage(message);
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:9998/messages");
		String response = webResource.post(String.class, result);
		
		// Should use response?
		return true;

	}
}
