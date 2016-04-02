package com.shinyappythings.worldconnector;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    private EditTextPreference mUser;
    private ListPreference mPollInterval;
    private ListPreference mServer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		//Get reference to the pref for updating of summaries
		mUser = (EditTextPreference)getPreferenceScreen().findPreference(Constants.PREFKEY_USER);    
		mPollInterval = (ListPreference)getPreferenceScreen().findPreference(Constants.PREFKEY_POLL_INTERVAL);    
		mServer = (ListPreference)getPreferenceScreen().findPreference(Constants.PREFKEY_SERVER);    
	}

	@Override    
	protected void onResume() 
	{        
		super.onResume();        
		
		// Setup the initial values        
		mUser.setSummary(Helper.GetPref(getApplicationContext(), Constants.PREFKEY_USER, getString(R.string.prefUserDefault)));        
		mPollInterval.setSummary(Helper.GetPref(getApplicationContext(), Constants.PREFKEY_POLL_INTERVAL, getString(R.string.prefPollIntervalDefault)) + "ms");        
		mServer.setSummary(Helper.GetPref(getApplicationContext(), Constants.PREFKEY_SERVER, getString(R.string.prefServerDefault)));        
		
		// Set up a listener whenever a key changes                    
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);    
	}
    
	@Override    
    protected void onPause() 
    {        
    	super.onPause();        
    	
    	// Unregister the listener whenever a key changes                    
    	getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);        
	}
	
	//Get changes to preferences
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) 
    {   
    	if (key.equals(Constants.PREFKEY_USER)) 
    	{          
    		mUser.setSummary(sharedPreferences.getString(key, getString(R.string.prefUserDefault)));        
    	}
    	else if (key.equals(Constants.PREFKEY_POLL_INTERVAL))
    	{
    		mPollInterval.setSummary(sharedPreferences.getString(key, getString(R.string.prefPollIntervalDefault)) + "ms");            		
    	}
    	else if (key.equals(Constants.PREFKEY_SERVER))
    	{
    		mServer.setSummary(sharedPreferences.getString(key, getString(R.string.prefServerDefault)));            		
    	}
    }
}
