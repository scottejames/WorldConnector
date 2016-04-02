package com.shinyappythings.worldconnector;

import static com.shinyappythings.worldconnector.DBConstants.CONTENT_URI;
import static com.shinyappythings.worldconnector.DBConstants.ERROR;
import static com.shinyappythings.worldconnector.DBConstants.WHAT;
import static com.shinyappythings.worldconnector.DBConstants.WHEN;
import static com.shinyappythings.worldconnector.DBConstants.WHO;

import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.preference.PreferenceManager;

public class CommunicatorREST {
	
	private Context context;
	
	public CommunicatorREST(Context c)
	{
		context = c;
		URL = Helper.GetServer(context) + "/messageservice/message";
	}
	
	private String URL = "";
	
	public String Submit(String When, String Who, String What, String contentURI) {
	    try { 
	    	URI uri = new URI(URL);
	    	HttpClient client = new DefaultHttpClient();
	    	HttpPut put = new HttpPut(uri);

	    	//message
	    	//<Message><comment>Says Hello</comment><userName>Scott</userName></Message> 

	    	String message = "<Message><comment>" + What + "</comment><userName>" + Who + "</userName><when>" + When + "</when></Message>";
	    	StringEntity se = new StringEntity(message,HTTP.UTF_8);

	    	se.setContentType("text/xml");  
	    	put.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
	    	put.setEntity(se);  
	    	
	    	HttpResponse response = client.execute(put);
	    	
	        return response.getStatusLine().toString();

        } catch (Exception e) {
        	// Update when an error occurs 
    		ContentValues values = new ContentValues();
    		values.put(ERROR, "Error Occured ( " + e.getMessage() + ")");
    		
    		//Write the error
    		context.getContentResolver().update(Uri.parse(contentURI), values, null, null);
    		
        	return e.toString();
        }
    }

	public void Get(long lastCheckId, String who) {
    try { 
    	//append last updated number
    	URI uri = new URI(URL + "/" + who + "/" + lastCheckId + "/");
		HttpGet get = new HttpGet(uri);

		HttpClient httpclient = new DefaultHttpClient();
		try
		{
			HttpResponse result = httpclient.execute(get);
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
				long updateTimeLong = Long.parseLong(updateTimeName);
				  
				NodeList messageId = rawElement.getElementsByTagName("messageID");
			    String messageIdName = getCharacterDataFromElement((Element) messageId.item(0));
			    
			    //set lastcheckid
			    if (lastCheckId < Long.parseLong(messageIdName)) {
			    	lastCheckId = Long.parseLong(messageIdName);
			    }
			    
				// Update or Insert local records to ensure the local database is up to date.
			    context.getContentResolver().delete(CONTENT_URI, WHEN + " = " + updateTimeLong + " and " + WHO + " = '" + userName + "'", null);
			    
				ContentValues values = new ContentValues();
				values.put(WHEN, updateTimeLong);
				values.put(WHO, userName);
				values.put(WHAT, commentName);
				context.getContentResolver().insert(CONTENT_URI, values);
			}
			
			//If a last check Id has been performed, set the next one to use, ensure it is always the 
			//highest value, so only the most recent messages for that device are retrieved.
			if (lastCheckId != 0) {
				Helper.SetLastCheckId(context, lastCheckId);
			}
		}
		finally
		{
			// Release current connection to the connection pool once you are
			// done
			httpclient.getConnectionManager().shutdown();
		}

		
        } catch (Exception e) {
        	System.err.println(e.toString());
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