package com.shineyappythings.restfulservice;

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

	}

	public Message(String comment, String userName)
	{
		this.comment = comment;
		this.userName = userName;
	}

	public Message(String comment, String userName, Long when, Long messageID)
	{
		this.comment = comment;
		this.userName = userName;
		this.when = when;
		this.messageID = messageID;
	}

	public String toString ()
	{
		return " Comment: " + comment + " user name: " + userName ;
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

	public void setWhen(long when)
	{
		this.when = when;
	}

	public long getMessageID()
	{
		return messageID;
	}

	public void setMessageID(long messageID)
	{
		this.messageID = messageID;
	}

}
