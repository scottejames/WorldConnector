package com.shinyappythings.restfulservice;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class ChatServer {
	
    protected ChatServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        
        sf.setResourceClasses(MessageService.class);
        sf.setResourceProvider(MessageService.class, 
            new SingletonResourceProvider(new MessageService()));
        sf.setAddress(Configuration.getInstance().getURL());

        sf.create();
    }

    public static void main(String args[]) throws Exception {
        System.out.println("Server starting...");
    	if (args.length>0)
    		Configuration.getInstance().setDefaultMode(args[0]);
    	Configuration.getInstance().loadProperties();
    	
        new ChatServer();
        System.out.println("Server ready...");
        while (1==1)
        {
        	Thread.sleep(5 * 60 * 1000);
        }
        

    }
}
