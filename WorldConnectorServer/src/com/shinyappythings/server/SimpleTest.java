package com.shinyappythings.server;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class SimpleTest
{
	public static void main (String [] args) throws Exception
	{
		URL url = new URL("http://192.168.0.12:8081/chat?wsdl");
		QName qname = new QName("http://server.shinyappythings.com/","ChatServerService");
		Service service = Service.create(url,qname);
		
		IChatServer chatServer = service.getPort(IChatServer.class);
		chatServer.say("scott", "hello world");
	}
}
