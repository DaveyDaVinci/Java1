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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fullsail.dmlib.FormItems;
import com.example.fighttest.JSONSaveAndRead;


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
		
		context = this;
		
		LinearLayout linLay = new LinearLayout(this);
		linLay.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layPar = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linLay.setLayoutParams(layPar);
		
		//Edit texts for first and last name
		firstName = new EditText(this);
		firstName.setHint("First Name");
		firstName.setLayoutParams(layPar);
		
		lastName = new EditText(this);
		lastName.setHint("Last Name");
		lastName.setLayoutParams(layPar);
		
		
		//Checks connection
		connection = ConnectionWork.getStatusOfConnection(this);
		if (connection)
		{
			Log.i("NETWORKCONNECTION", ConnectionWork.getTheConnectionType(this));
		}
		
		
		singleText = FormItems.singleTextView(this, joke);
		
		//Creates a random button
		Button randomButton = new Button(this);
		layPar = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		randomButton.setText("Random Joke");
		randomButton.setLayoutParams(layPar);
		randomButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			//Gets joke
			public void onClick(View v) {
				getNorrisJokes();
				
			}
		});
		
		//Custom instructions
		TextView customText = new TextView(this);
		customText.setLayoutParams(layPar);
		customText.setText("\nOr enter a first and last name to customize the joke!");
		
		//Personalized Joke Button
		Button submitButton = new Button(this);
		submitButton.setLayoutParams(layPar);
		submitButton.setText("Get Personalized Joke");
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
		
		//New LL for buttons
		LinearLayout buttonsLayout = new LinearLayout(this);
		layPar = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);
		buttonsLayout.setLayoutParams(layPar);
		
		//Save button
		Button saveButton = new Button(this);
		layPar = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, .5f);
		saveButton.setLayoutParams(layPar);
		saveButton.setText("Save Joke");
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			//THIS IS USING THE JAR FILE
			public void onClick(View v) {
				//Performs save from JAR file
				JSONSaveAndRead.storeJokeString(context, fileName, singleText.getText().toString(), false);
				
			}
		});
		buttonsLayout.addView(saveButton);
		
		//Load button
		Button loadButton = new Button(this);
		loadButton.setLayoutParams(layPar);
		loadButton.setText("Load Saved Joke");
		loadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			//THIS IS USING THE JAR FILE
			public void onClick(View v) {
				//Performs load from JAR file
				String savedData = JSONSaveAndRead.readStringFromDisk(context, fileName, false);
				singleText.setText(savedData);
				
				
			}
		});
		buttonsLayout.addView(loadButton);
		
		
		linLay.addView(singleText);
		linLay.addView(randomButton);
		linLay.addView(customText);
		linLay.addView(firstName);
		linLay.addView(lastName);
		linLay.addView(submitButton);
		linLay.addView(buttonsLayout);
		
		setContentView(linLay);
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
