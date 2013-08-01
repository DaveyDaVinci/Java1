package com.example.magee_david_java1_week3;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fighttest.JSONSaveAndRead;
import com.fullsail.dmlib.FormItems;


public class MainActivity extends Activity {

	static FormItems formLib = new FormItems();
	static Boolean connection = false;
	static String joke;
	static TextView singleText;
	static FormItems formThings;
	static EditText firstName;
	static EditText lastName;
	static JSONSaveAndRead saveData = new JSONSaveAndRead();
	Context context;
	static String fileName = "SavedJoke";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//sets theme
		setTheme(R.style.MyCustomTheme);
		setContentView(R.layout.main_view);
		context = this;
		
		
		
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);
		
		
		
		//Checks connection
		connection = ConnectionWork.getStatusOfConnection(this);
		if (connection)
		{
			Log.i("NETWORKCONNECTION", ConnectionWork.getTheConnectionType(this));
		} else
		{
			Toast toast = Toast.makeText(context, "NO CONNECTION",  Toast.LENGTH_SHORT);
			toast.show();
		}
		
		
		
		singleText = (TextView) findViewById(R.id.savedText);
		
		//Creates a random button
		
		Button randomButton = (Button) findViewById(R.id.randomButton);
		
		randomButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			//Gets joke
			public void onClick(View v) {
				getNorrisJokes();
				
			}
		});
		
		
		
		
		//Personalized Joke Button
		
		Button submitButton = (Button) findViewById(R.id.customButton);
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Checks input fields.  
				String fname = firstName.getText().toString();
				String lname = lastName.getText().toString();
				if (fname.length() <= 0 || lname.length() <= 0)
				{
					Toast toast = Toast.makeText(context, "Please Enter First And Last Name",  Toast.LENGTH_SHORT);
					toast.show();
				}
				else
				{
					
					getPersonalizedJokes(fname, lname);
				}
				
			}
		});
		
		
		//Save button
		Button saveButton = (Button) findViewById(R.id.saveButton);
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			//THIS IS USING THE JAR FILE
			public void onClick(View v) {
				//Performs save from JAR file
				JSONSaveAndRead.storeJokeString(context, fileName, singleText.getText().toString(), false);
				
			}
		});
		
		
		//Load button
		Button loadButton = (Button) findViewById(R.id.loadButton);
	
		loadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			//THIS IS USING THE JAR FILE
			public void onClick(View v) {
				//Performs load from JAR file
				String savedData = JSONSaveAndRead.readStringFromDisk(context, fileName, false);
				singleText.setText(savedData);
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Gets the data from the chuck norris joke API
	@SuppressWarnings("unused")
	private void getNorrisJokes()
	{
		String baseURL = "http://api.icndb.com/jokes/random";
		String formattedURL;
		try 
		{
			formattedURL = URLEncoder.encode(baseURL, "UTF-8");
		} catch (Exception e)
		{
			Log.e("BAD URL", "ENCODING PROBLEM");
			Toast toast = Toast.makeText(this, "Bad URL, Encoding problem",  Toast.LENGTH_SHORT);
			toast.show();
			formattedURL = "";
		}
		URL finishedURL;
		try
		{
			finishedURL = new URL(baseURL);
			JokesRequest jokesReq = new JokesRequest();
			jokesReq.execute(finishedURL);
		} catch (MalformedURLException e)
		{
			Log.e("BAD URL", "MALFORMED URL");
			Toast toast = Toast.makeText(this, "Bad URL, Malformed URL",  Toast.LENGTH_SHORT);
			toast.show();
			finishedURL = null;
		}
	}
	
	
	//Gets data from chuck norris API using first and last name
	@SuppressWarnings("unused")
	private void getPersonalizedJokes(String fname, String lname)
	{
		String baseURL = "http://api.icndb.com/jokes/random";
		String fnameFormatted = fname.replaceAll("\\s", "");
		String lnameFormatted = lname.replaceAll("\\s", "");
		String fNameFormatter = "?firstName=" + fnameFormatted;
		String lNameFormatter = "&lastName=" + lnameFormatted;
		String combinedURL = baseURL + fNameFormatter + lNameFormatter;
		
		String formattedURL;
		try 
		{
			formattedURL = URLEncoder.encode(baseURL, "UTF-8");
		} catch (Exception e)
		{
			Log.e("BAD URL", "ENCODING PROBLEM");
			Toast toast = Toast.makeText(this, "Bad URL, Encoding Problem",  Toast.LENGTH_SHORT);
			toast.show();
			formattedURL = "";
		}
		URL finishedURL;
		try
		{
			finishedURL = new URL(combinedURL);
			JokesRequest jokesReq = new JokesRequest();
			jokesReq.execute(finishedURL);
		} catch (MalformedURLException e)
		{
			Log.e("BAD URL", "MALFORMED URL");
			finishedURL = null;
		}
	}
	
	
	//This is the method that's done in hte background
	private class JokesRequest extends AsyncTask<URL, Void, String>
	{
		@Override
		protected String doInBackground(URL...urls)
		{
			String response = "";
			for (URL url: urls)
			{
				response = ConnectionWork.getURLResponse(url);
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			Log.i("URL RESPONSE", result);
			try 
			{
				JSONObject jsonResponse = new JSONObject(result);
				JSONObject jsonResults = jsonResponse.getJSONObject("value");
				joke = jsonResults.getString("joke");
				singleText.setText(joke);
				
			} catch (JSONException e) {
				Log.e("JSON", "JSON OBJECT EXCEPTION");
			}
		}
	}

}
