package com.shinyappythings.server;

import javax.xml.ws.Endpoint;


public class ChatServerPublisher
{
	public static void main(String [] args)
	{
		Endpoint.publish("http://192.168.0.12:8081/chat", new ChatServer());
	}
	

}
