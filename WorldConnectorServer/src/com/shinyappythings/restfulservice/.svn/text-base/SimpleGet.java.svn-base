package com.shinyappythings.restfulservice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SimpleGet
{

	public static void main(String[] args) throws Exception
	{
		// // Sent HTTP GET request to query customer info
		// System.out.println("Sent HTTP GET request to query customer info");
		// URL url = new
		// URL("http://192.168.0.12:9000/messageservice/message/99");
		// InputStream in = url.openStream();
		// System.out.println(getStringFromInputStream(in));

		//
		if (args != null)
    	if (args.length>0)
    		Configuration.getInstance().setDefaultMode(args[0]);
    	Configuration.getInstance().loadProperties();
    	
    	
		HttpGet get = new HttpGet(
				Configuration.getInstance().getURL() + "/messageservice/message/0");

		HttpClient httpclient = new DefaultHttpClient();
		try
		{
			HttpResponse result = httpclient.execute(get);
			// String data = getResponse(result.getEntity());

			// System.out.println("response: " +
			// getStringFromInputStream(result.getEntity().getContent()));
			final JAXBContext context = JAXBContext.newInstance(Message.class);
			final Unmarshaller unmarshaller = context.createUnmarshaller();

			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = db.parse(result.getEntity().getContent());

			NodeList nodes = doc.getElementsByTagName("Message");
			for (int i = 0; i < nodes.getLength(); i++)
			{
			      Element rawElement = (Element) nodes.item(i);
			      
			      NodeList name = rawElement.getElementsByTagName("userName");
			      String userName = getCharacterDataFromElement((Element) name.item(0));
			      
			      NodeList comment = rawElement.getElementsByTagName("comment");
			      String commentName = getCharacterDataFromElement((Element) comment.item(0));
			      
			      NodeList updateTime = rawElement.getElementsByTagName("when");
			      String updateTimeName = getCharacterDataFromElement((Element) updateTime.item(0));
			      
			      NodeList messageId = rawElement.getElementsByTagName("messageID");
			      String messageIdName = getCharacterDataFromElement((Element) messageId.item(0));
			      

			      Message message = new Message(commentName, userName,Long.parseLong(updateTimeName),Long.parseLong(messageIdName));
			      System.out.println(message);
			}

		}
		finally
		{
			// Release current connection to the connection pool once you are
			// done
			httpclient.getConnectionManager().shutdown();
		}

	}

	public static String getCharacterDataFromElement(Element e)
	{
		Node child = e.getFirstChild();
		if (child instanceof CharacterData)
		{
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

}
