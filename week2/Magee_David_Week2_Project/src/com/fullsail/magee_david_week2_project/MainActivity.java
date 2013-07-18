package com.fullsail.magee_david_week2_project;

import java.util.Random;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fullsail.dmlib.FormItems;
import com.fullsail.ships.Battle;
import com.fullsail.ships.SavedShips;
import com.fullsail.ships.Ships;

public class MainActivity extends Activity {

	LinearLayout linearLayout;
	LinearLayout.LayoutParams theParams;
	
	Ships[] ships = Ships.shipsList();
	
	String shipText;
	String opponentCaptainName;
	String opponentShipName;
	String captainName;
	
	TextView singleText;
	TextView randomResultsText;
	TextView readResults;
	TextView opponentResults;
	TextView fightText;
	
	int shipIndex;
	int captainIndex;
	int opponentShip;
	int opponentCaptain;
	int yourHP;
	int theirHP;
	
	int testHP;
	
	boolean successfulSave;
	boolean opponentChosen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		successfulSave = false;
		opponentChosen = false;
		
		
		LinearLayout linLay = new LinearLayout(this);
		linLay.setOrientation(LinearLayout.VERTICAL);
		
        //Directions text
		singleText = FormItems.singleTextView(this, "\nHit Randomize to populate a random ship and captain. Hit save to " +
				"save the randomized items, or hit load to load previously saved items");
        linLay.addView(singleText);
        
        
        //Displays randomized values
        singleText = FormItems.singleTextView(this, "\nNo Defaults");
        linLay.addView(singleText);
        
       
        //Randomize button and code
        Button singleButton = FormItems.singleButton(this, "Randomize");
        singleButton.setOnClickListener(new View.OnClickListener() {
			
        	
        	//This will grab a random ship and captain from the enum and resources respectively.  Also saves variables for later use
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Random randomGenerated = new Random();
				
				shipIndex = randomGenerated.nextInt(ships.length);
				
				Ships selectedShip = ships[shipIndex];
				
				shipText = selectedShip.shipname;
				
				String[] captainsArray = getResources().getStringArray(R.array.captainsArray);
				
				captainIndex = randomGenerated.nextInt(captainsArray.length);
				
				captainName = captainsArray[captainIndex];
				
				yourHP = selectedShip.hitpoints;
				int ap = selectedShip.attackpower;
				int hr = selectedShip.hitrating;
				
				singleText.setText("\n\n\n                  " + shipText + "    " + captainName);
				
				randomResultsText.setText("\n\nCaptain " + captainName + " is piloting the " + shipText + " which has "
						+ yourHP + " armor, " + ap + " attack power, and a hit rating of " + hr);
				
				Ships [] values = selectedShip.getDeclaringClass().getEnumConstants();
				
				System.out.println(values[0]);
				
				
				
				
			}
		});
        
        linLay.addView(singleButton);
        
        
       
        //Random results view
        randomResultsText = FormItems.singleTextView(this, "\n\n");
        linLay.addView(randomResultsText);
        
        
        //Creates save and load buttons for saving the jsond ata
        LinearLayout twoButtons = FormItems.twoButtonsAndTextField(this, "Save", "Load");
        Button testSave = (Button) twoButtons.findViewById(5);
        Button testLoad = (Button) twoButtons.findViewById(7);
        testLoad.setOnClickListener(new View.OnClickListener() {
 		
     	   @Override
     	   public void onClick(View v) {
 			// TODO Auto-generated method stub
     		   
     		   TextView updatedText = (TextView) v.getTag();
     		   
     		   if (successfulSave == true)
     		   {
     			   try {
     				   String[] savedShips = SavedShips.readJSON();
     				  updatedText.setText(savedShips[0] + " " + savedShips[1]);
    					} catch (JSONException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
     			   
     		   }
     		   else 
     		   {
     			   
     			   updatedText.setText("No saved values to load");
     			   
     		   }
 			
     	   }
        });
        //save button
        testSave.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				
 				
 				String testForValue = (String) singleText.getText();
 				
 				TextView updatedText = (TextView) v.getTag();
 				
 				if (testForValue.equals("\nNo Defaults"))
 				{
 					updatedText.setText("Nothing Saved, hit randomize first");
 				
 				} else 
 				{
 					try {
 				
 						SavedShips.buildJSON(shipIndex, captainName);
 						updatedText.setText("Saved");
 						successfulSave = true;
 				
 					} catch (JSONException e) {
 					// TODO Auto-generated catch block
 						Log.e("error", "JSONError", e);
 					}
 				
 				}
 			
 			}
 				
 			
        });
        
        
        
        linLay.addView(twoButtons);
        
       
       //Views the selected opponent
       opponentResults = new TextView(this);
       linLay.addView(opponentResults);
       
       
       //Button to create random opponents
       Button opponentButton = new Button(this);
       opponentButton.setText("Random Opponent");
       opponentButton.setOnClickListener(new View.OnClickListener() {
		
    	   @Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
    		     randomOpponent();
				
    		     
			
			}
       });
        
       linLay.addView(opponentButton);
       
       //Creates fight results text view
       fightText = new TextView(this);
       linLay.addView(fightText);
       
       //Creats a fight button that performs the battle 
       Button testFightButton = new Button(this);
       testFightButton.setText("Fight!");
       testFightButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (successfulSave == false && opponentChosen == false)
				{
					fightText.setText("\nPlease save your captain and randomize an opponent");
				}
				else if (successfulSave == true && opponentChosen == false)
				{
					fightText.setText("\nPlease randomize an opponent");
				}
				else if (successfulSave == false && opponentChosen == true)
				{
					fightText.setText("\nPlease save your captain above");
					
				}
				else if (successfulSave == true && opponentChosen == true)
				{
					String [] resultsStrings = Battle.yourFight( captainName, "ShipName", "Rawr", "Tank", yourHP, testHP, shipIndex);
					
					String resultsText = "\n" + resultsStrings[0];
				
					testHP = Integer.parseInt(resultsStrings[1]);
				
					fightText.setText(resultsText);
				}
				else 
				{
					fightText.setText("Something went wrong...");
				}
				
			
			}
       });
       
       linLay.addView(testFightButton);
       
       
       
        
        setContentView(linLay);
	}

	
	//Generates a random opponent from the enum and resources
	public void randomOpponent()
	{
		
		Random randomGenerated = new Random();
		
		opponentShip = randomGenerated.nextInt(ships.length);
		
		Ships selectedShip = ships[opponentShip];
		
		opponentShipName = selectedShip.shipname;
		
		String[] captainsArray = getResources().getStringArray(R.array.captainsArray);
		
		opponentCaptain = randomGenerated.nextInt(captainsArray.length);
		
		opponentCaptainName = captainsArray[opponentCaptain];
		
		opponentChosen = true;
		
		theirHP = selectedShip.hitpoints;
		int ap = selectedShip.attackpower;
		int hr = selectedShip.hitrating;
		
		testHP = selectedShip.hitpoints;
		
		opponentResults.setText("\nCaptain " + opponentCaptainName + " is piloting the " + opponentShipName + " which has "
				+ theirHP + " armor, " + ap + " attack power, and a hit rating of " + hr);
		
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
