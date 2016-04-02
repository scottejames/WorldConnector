package com.shineyappythings.restfulservice;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/messages/")
public class MessageResource
{	
	public  MessageResource()
	{
		System.out.println("Created Message Resource Object");
	}
	@GET
	@Produces("application/json")
	public String getMessage() throws IOException
	{
		System.out.println("<------- GET message()");
		Message message = MessageStore.getInstance().getLastMessage();
		if (message == null)
		{
			System.out.println("Empty string returned");
			return "";
		}
		else
			return MessageBuilder.createStringFromMessage(message);
	}
	
	@POST
	@Produces("application/json")
	public Response postMessage(String postData) throws IOException
	{
		System.out.println(">------- POST message( " + postData + ")");
		Message message = MessageBuilder.createMessageFromString(postData);
		MessageStore.getInstance().addMessage(message);
		
		return javax.ws.rs.core.Response.ok().build();
	}
	
}
