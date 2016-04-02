package com.shinyappythings.server;

import javax.jws.WebService;


@WebService(endpointInterface = "com.shinyappythings.server.IChatServer")
public class ChatServer implements IChatServer
{
	@Override
	public boolean say(String username, String message)
	{
		System.out.println("User: " + username + " Said: " + message);
		return true;
	}

}
