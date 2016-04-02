package com.shinyappythings.worldconnector;

import static android.provider.BaseColumns._ID;
import static com.shinyappythings.worldconnector.DBConstants.CONTENT_URI;
import static com.shinyappythings.worldconnector.DBConstants.WHEN;
import static com.shinyappythings.worldconnector.DBConstants.WHAT;
import static com.shinyappythings.worldconnector.DBConstants.WHO;
import static com.shinyappythings.worldconnector.DBConstants.ERROR;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shinyappythings.worldconnector.Prefs;
import com.shinyappythings.worldconnector.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private EditText etInput;
	private ListView lvList;
	private MyCount counter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Get the controls
		etInput = (EditText) findViewById(R.id.input);
		lvList = (ListView) findViewById(R.id.list);

		etInput.requestFocus();

		showComments();

		Toast.makeText(getApplicationContext(), Helper.GetServer(getApplicationContext()),1000).show();
		
		// Set listeners
		etInput.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER
						&& event.getAction() == KeyEvent.ACTION_DOWN) {

					String input = etInput.getText().toString();
					String user = Helper.GetUser(getApplicationContext());

					addComment(input, new Date().getTime(), user);

					etInput.setText("");

					showComments();

					return true;
				}
				return false;
			}
		});
	}
	
	private void SetPoller()
	{
		//Poller
		long pollInterval = Helper.GetPollInterval(getApplicationContext());
	
		counter = new MyCount(pollInterval, pollInterval);
		counter.start();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();

		SetPoller();
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		counter.cancel();
	}

	// ***********************************
	// Menus
	// ***********************************
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			startActivity(new Intent(this, Prefs.class));
			return true;
		case R.id.clearDB:
			getContentResolver().delete(CONTENT_URI, null, null);
			return true;
		case R.id.copyDB:
			ExportDatabaseFileTask t = new ExportDatabaseFileTask(getApplicationContext());
			t.Copy();
			return true;
		}
		return false;
	}

	// ****************************************
	// DB Stuff
	// ****************************************
	private static String[] FROM = { _ID, WHEN, WHO, WHAT, ERROR };
	private static int[] TO = { R.id.rowid, R.id.when, R.id.who, R.id.what, R.id.error };

	private void addComment(String what, long when, String who) {
		if (what != null && what.length() > 0)
		{
			// Insert a new record
			ContentValues values = new ContentValues();
			values.put(WHEN, when);
			values.put(WHO, who);
			values.put(WHAT, what);
			Uri uri = getContentResolver().insert(CONTENT_URI, values);
			
			CommunicatorTask ct = new CommunicatorTask(getApplicationContext());
			ct.execute("SUBMIT", Long.toString(when), who, what, uri.toString());
		}
	}

	private Cursor getComments() {
		// Perform a managed query. The Activity will handle closing
		// and re-querying the cursor when needed.
		return managedQuery(CONTENT_URI, FROM, null, null, WHEN + " Asc");
	}

	// ****************************************
	// Show Comments
	// ****************************************
	private void showComments() {
		//Get any pending comments
		long lastCheckId = Helper.GetLastCheckId(getApplicationContext());
		String user = Helper.GetUser(getApplicationContext());
		
		CommunicatorTask ct = new CommunicatorTask(getApplicationContext());
		ct.execute("GET", lastCheckId + "", user);
		
		Cursor cursor = getComments();
		
		// Set up data binding
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.item, cursor, FROM, TO);
		
		// Format date when binding to list
		adapter.setViewBinder(new ViewBinder() {
		    public boolean setViewValue(View aView, Cursor aCursor, int aColumnIndex) {

		        if (aColumnIndex == 1) {
		                long ticks = aCursor.getLong(aColumnIndex);
		                TextView textView = (TextView) aView;
		                textView.setText(GetFormattedDate(ticks));
		                return true;
		         }

		         return false;
		    }
		});
		
		lvList.setAdapter(adapter);

		lvList.setSelection(adapter.getCount() - 1);
	}
	
	// ****************************************
	// Helpers
	// ****************************************
	private String GetFormattedDate(long in)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		return dateFormat.format(new Date(in));	
	}

	// ****************************************
	// Timer
	// ****************************************
	public class MyCount extends CountDownTimer {
		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			showComments();
			
			this.start();
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	}
}