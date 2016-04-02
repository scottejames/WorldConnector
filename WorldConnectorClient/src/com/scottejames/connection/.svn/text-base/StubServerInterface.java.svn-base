package com.scottejames.connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.shineyappythings.restfulservice.Message;

public class StubServerInterface implements IServerInterface
{
	ArrayList<Message> store = new ArrayList<Message>();
	
	@Override
	public List<Message> getNewMessages() throws IOException
	{	
		return store;
	}

	@Override
	public boolean postMessage(Message message) throws IOException
	{
		store.add(message);
		return true;
	}

}
