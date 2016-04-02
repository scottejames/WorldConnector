package com.shinyappythings.worldconnector;

import android.content.Context;
import android.os.AsyncTask;

public class CommunicatorTask extends AsyncTask<String, Void, String> {

	private Context context;
	
    public CommunicatorTask(Context c) {
    	context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        // params comes from the execute() call: 
    	// params[0] = Method name
    	// params[1] = When
    	// params[2] = Who
    	// params[3] = What
    	CommunicatorREST comm = new CommunicatorREST(context);
    	if (params[0] == "SUBMIT") {
    		 comm.Submit(params[1], params[2], params[3], params[4]);
    	} else if (params[0] == "GET") {
    		 comm.Get(Long.parseLong(params[1]), params[2]);
    	} 
    	
    	return "";
    }
}