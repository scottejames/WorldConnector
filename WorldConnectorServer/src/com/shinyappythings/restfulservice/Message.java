package com.shinyappythings.restfulservice;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Message")
public class Message
{
	
	private String comment;
	private String userName;
	private long when;
	private long messageID;


	public Message()
	{
		init();
	}
	public Message(String comment, String userName)
	{
		this.comment = comment;
		this.userName = userName;
		init();
	}
	public Message(String comment, String userName, long when, long messageId)
	{
		this.comment = comment;
		this.userName = userName;
		this.messageID = messageId;
		this.when = when;
	}
	
	private void init()
	{
		messageID = IDBuilder.getId();
	}


	public long getMessageID()
	{
		return messageID;
	}
	public void setMessageID(long messageID)
	{
		this.messageID = messageID;
	}
	public void setWhen(long when)
	{
		this.when = when;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public long getWhen()
	{
		return when;
	}
	
	@Override
	public String toString()
	{
		return "Id: " + messageID + " User: " + userName + " Comment: " + comment + " when: " + when;
	
	}
	
	
	
}
