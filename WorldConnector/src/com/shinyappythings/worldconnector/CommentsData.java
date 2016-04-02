package com.shinyappythings.worldconnector;

import static android.provider.BaseColumns._ID;
import static com.shinyappythings.worldconnector.DBConstants.TABLE_NAME;
import static com.shinyappythings.worldconnector.DBConstants.WHAT;
import static com.shinyappythings.worldconnector.DBConstants.WHEN;
import static com.shinyappythings.worldconnector.DBConstants.WHO;
import static com.shinyappythings.worldconnector.DBConstants.ERROR;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CommentsData extends SQLiteOpenHelper {
	   private static final String DATABASE_NAME = "comments.db";
	   private static final int DATABASE_VERSION = 5;

	   /** Create a helper object for the Events database */
	   public CommentsData(Context ctx) {
	      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	   }
	   @Override
	   public void onCreate(SQLiteDatabase db) {
		   StringBuilder sb = new StringBuilder();
		   sb.append("CREATE TABLE ");
		   sb.append(TABLE_NAME);
		   sb.append(" (");
		   sb.append(_ID);
		   sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
		   sb.append(WHEN);
		   sb.append(" INTEGER NOT NULL, ");
		   sb.append(WHO);
		   sb.append(" TEXT NOT NULL, ");
		   sb.append(WHAT);
		   sb.append(" TEXT NOT NULL, ");
		   sb.append(ERROR);
		   sb.append(" TEXT NULL);");
		   
		   db.execSQL(sb.toString());
	   }
	   
	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	   {
		   //Change this as soon as there is a new version so we don't lose data
	      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	      onCreate(db);
	   }	  
}