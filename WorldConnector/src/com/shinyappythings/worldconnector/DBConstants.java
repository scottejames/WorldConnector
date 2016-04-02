package com.shinyappythings.worldconnector;

import android.net.Uri;
import android.provider.BaseColumns;

public interface DBConstants extends BaseColumns {
   public static final String TABLE_NAME = "Comments";
   
   public static final String AUTHORITY = "com.shinyappythings.worldconnector";
   public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

   // Columns in the Notes database
   public static final String WHEN = "whenx";
   public static final String WHAT = "what";
   public static final String WHO = "who";
   public static final String ERROR = "error";   
}
