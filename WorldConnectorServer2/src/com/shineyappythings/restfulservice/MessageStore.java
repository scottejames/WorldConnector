package com.shineyappythings.restfulservice;


public class MessageStore
{
	private static MessageStore  _instance = null;
	private Database _database = null;
	
	private MessageStore()
	{
		_database = Database.getInstance();

	}
	
	public synchronized static MessageStore getInstance()
	{
		if (_instance == null)
			_instance = new MessageStore();
		return _instance;
			
			
	}

	public void addMessage(Message message)
	{
		System.out.println("MessageStore:addMessage()");
		_database.createMessage(message);
	}

	public Message getLastMessage()
	{
		System.out.println("MessageStore:getLastMessage()");
		System.exit(-1);
		return null;
	}

	public Message[] getMessagesSince(long messageID)
	{
		System.out.println("MessageStore:getMessagesSince()");
		System.exit(-1);
		return null;
	}

}
