package com.shinyappythings.restfulservice;

import java.io.File;
import java.io.IOException;

import org.apache.cxf.resource.URIResolver;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class SimpleTest
{
	public static void main(String[] args) throws ClientProtocolException, IOException
	{


	    	if (args.length>0)
	    		Configuration.getInstance().setDefaultMode(args[0]);
    	Configuration.getInstance().loadProperties();
    	
		SimpleTest client = new SimpleTest();
	     String inputFile = client.getClass().getResource("message.xml").getFile();
	     URIResolver resolver = new URIResolver(inputFile);
	     File input = new File(resolver.getURI());
	     HttpPut put = new HttpPut(Configuration.getInstance().getURL() + "/messageservice/message");
	     HttpEntity entity = new FileEntity(input, "text/xml; charset=ISO-8859-1");
	
		put.setEntity(entity);
		HttpClient httpclient = new DefaultHttpClient();
		try
		{
			HttpResponse result = httpclient.execute(put);
			System.out.println("Response status code: "
					+ result.getStatusLine().getStatusCode());
			System.out.println("Response body: ");
			System.out.println(result.toString());
		}
		finally
		{
			// Release current connection to the connection pool once you are
			// done
			httpclient.getConnectionManager().shutdown();
		}
	}
}
