package com.shinyappythings.restfulservice;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.shineyappythings.engine.MessageStore;

@Path("/messageservice/")
public class MessageService
{
	private MessageStore messageStore = new MessageStore();
	
    @PUT
    @Path("/message/")
    public Response postMessage(Message message) {
        System.out.println("----invoking postMessage,  " + message);
        	messageStore.addMessage(message);   
        Response r = Response.ok().build();
       
        return r;
    }
   
    
    @GET
    @Path("/message/{locationid}/{lastupdate}/")
    public ArrayList<Message> getMessage(@PathParam("locationid") String locationID,@PathParam("lastupdate") String lastupdate) {
        System.out.println("----invoking getMessage(" + lastupdate + ") from " + locationID);
        ArrayList<Message> list = messageStore.getMessagesSince(Long.parseLong(lastupdate));
        for(Message message: list)
        {
        	System.out.println(" message: " + message);
        }
        return list;
    }
    
    @GET
    @Path("/message/{lastupdate}/")
    public ArrayList<Message> getMessage(@PathParam("lastupdate") String lastupdate) {
        System.out.println("----invoking getMessage(" + lastupdate + ") from unspecified location");
        ArrayList<Message> list = messageStore.getMessagesSince(Long.parseLong(lastupdate));
        for(Message message: list)
        {
        	System.out.println(" message: " + message);
        }
        return list;
    }
}
