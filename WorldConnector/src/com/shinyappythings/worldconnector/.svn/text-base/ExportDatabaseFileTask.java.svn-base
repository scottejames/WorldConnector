package com.shinyappythings.worldconnector;

import static com.shinyappythings.worldconnector.DBConstants.AUTHORITY;
import static com.shinyappythings.worldconnector.DBConstants.TABLE_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class ExportDatabaseFileTask  
{
    Context context;
    
    public ExportDatabaseFileTask(Context c)
    {
    	context = c;
    }    

    // automatically done on worker thread (separate from UI thread)
    public void Copy() {

       File dbFile =
                new File("/data/data/com.shinyappythings.worldconnector/databases/comments.db");
       
       File exportDir = new File(Environment.getExternalStorageDirectory(), "ShineyAppyThings_Databases");
       if (!exportDir.exists()) {
          exportDir.mkdirs();
       }
       File file = new File(exportDir, dbFile.getName());

       try {
          file.createNewFile();
          this.copyFile(dbFile, file);
       } catch (IOException e) {
          Log.e("Shiney Appy Talker", e.getMessage(), e);
       }
    }

    private void copyFile(File src, File dst) throws IOException {
       FileChannel inChannel = new FileInputStream(src).getChannel();
       FileChannel outChannel = new FileOutputStream(dst).getChannel();
       try {
          inChannel.transferTo(0, inChannel.size(), outChannel);
       } finally {
          if (inChannel != null)
             inChannel.close();
          if (outChannel != null)
             outChannel.close();
       }
    }

 }
