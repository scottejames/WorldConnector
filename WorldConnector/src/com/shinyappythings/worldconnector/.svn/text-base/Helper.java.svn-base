package com.shinyappythings.worldconnector;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Helper {

	public static String GetPref(Context c, String name, String defaultValue)
	{
		return PreferenceManager.getDefaultSharedPreferences(c).getString(name, defaultValue);	
	}

	public static long GetLongPref(Context c, String name, long defaultValue)
	{
		long pref = PreferenceManager.getDefaultSharedPreferences(c).getLong(name, defaultValue);
		
		return pref;	
	}
	
	public static String GetUser(Context c)
	{
		return GetPref(c, Constants.PREFKEY_USER, c.getString(R.string.prefUserDefault));
	}
	
	public static String GetServer(Context c)
	{
		return GetPref(c, Constants.PREFKEY_SERVER, c.getString(R.string.prefServerDefault)); 
	}
	
	public static long GetPollInterval(Context c)
	{
		String def = c.getString(R.string.prefPollIntervalDefault);
		String val = GetPref(c, Constants.PREFKEY_POLL_INTERVAL, def);
		
		return Long.parseLong(val);
	}
	
	public static void SetLastCheckId(Context c, long id)
	{
		Editor editor = PreferenceManager.getDefaultSharedPreferences(c).edit(); 
		editor.putLong(GetServer(c) + "-" + Constants.PREFKEY_LAST_CHECK_ID, id);
		editor.commit();	
	}
	
	public static long GetLastCheckId(Context c)
	{
		return Helper.GetLongPref(c, GetServer(c) + "-" + Constants.PREFKEY_LAST_CHECK_ID, 0);
	}
}
