package com.shineyappythings.restfulservice;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class MessageBuilder
{

	
	public static Message createMessageFromString(String messageString)
			throws IOException
	{
		Message result;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(messageString, Message.class);
		}
		catch (JsonGenerationException e)
		{
			e.printStackTrace();
			throw new IOException(e);
		}
		catch (JsonParseException e)
		{
			e.printStackTrace();
			throw new IOException(e);

		}
		return result;

	}

	public static String createStringFromMessage(Message message)
			throws IOException
	{
		String result;

		try
		{
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(message);
		}
		catch (JsonGenerationException e)
		{
			e.printStackTrace();
			throw new IOException(e);
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
			throw new IOException(e);

		}
		return result;
	}
}
