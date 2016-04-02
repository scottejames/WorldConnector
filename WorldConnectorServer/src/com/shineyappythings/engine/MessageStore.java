package com.shineyappythings.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.shinyappythings.restfulservice.Message;

public class MessageStore
{
	private ArrayList<Message> _messageList = new ArrayList<Message>();
	private Database db = null;
	
	public MessageStore()
	{
		db = Database.getInstance();
		
		// Setup from database
		_messageList = db.loadAllMessages();

	}

	public void addMessage(Message message)
	{
		_messageList.add(message);
		db.createMessage(message);
	}
	
	
	/* very suboptimal, replace this with an index ASAP, not to worried as
	 * when this becomes a problem this will all be in a database anyways!
	 */
	public ArrayList<Message> getMessagesSince(long messageID)
	{
		ArrayList<Message> messageList = new ArrayList<Message>();
		
		for(Message message: _messageList)
		{
			if (message.getMessageID() > messageID)
			{
				messageList.add(message);
			}			
		}
		return messageList;
	}
}
