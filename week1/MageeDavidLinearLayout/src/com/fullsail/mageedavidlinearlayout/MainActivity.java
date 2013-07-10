package com.fullsail.mageedavidlinearlayout;

import android.os.Bundle;
import java.util.Random;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout linearLayout;
	LinearLayout.LayoutParams theParams;
	TextView resultText;
	EditText weapon1Edit;
	TextView weapon1Title;
	TextView weapon2Title;
	EditText weapon2Edit;
	TextView weapon3Title;
	EditText weapon3Edit;
	TextView weapon4Title;
	EditText weapon4Edit;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Sets up a new linear layout for everything to show in
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        
        //Creates parameters to match the width and height of the window
        theParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(theParams);
        
        
        
        //Sets directions text
        TextView directionsText = new TextView(this);
        directionsText.setText("Help Rhea and her crew stock and fire their weapons to fight the encroaching hoard \n\n");
        linearLayout.addView(directionsText);
        
        
        
        //Sets the title for the next weapon
        weapon1Title = new TextView(this);
        weapon1Title.setText(getString(R.string.laser_blaster));
        linearLayout.addView(weapon1Title);
        
        
        //Creates a new layout for the weapon input and button
        LinearLayout weapon1InputAndButton = new LinearLayout(this);
        weapon1InputAndButton.setOrientation(LinearLayout.HORIZONTAL);
        theParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        weapon1InputAndButton.setLayoutParams(theParams);
        
        //Creates edit and button
        weapon1Edit = new EditText(this);
        weapon1Edit.setHint("Enter Number of Rounds");
        Button weapon1Button = new Button(this);
        weapon1Button.setText("Enter");
        weapon1Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numberOfBulletsEntered = Integer.parseInt(weapon1Edit.getText().toString());
				
				Random randomGenerated = new Random();
				
				
				for (int index = 1; index <= numberOfBulletsEntered; ++index)
				{
					int randomInt = randomGenerated.nextInt(numberOfBulletsEntered + 1);
					
					String gunName = (String) weapon1Title.getText();
					
					System.out.println(gunName);
					generateResults(randomInt, gunName, numberOfBulletsEntered);
					
					//resultText.setText("Ship hit " + randomInt + " times.");
					//System.out.println(randomInt);
				}
				
			}
		});
        
        
        weapon1InputAndButton.addView(weapon1Edit);
        weapon1InputAndButton.addView(weapon1Button);
        linearLayout.addView(weapon1InputAndButton);
        
       
        //Sets the title for the next weapon
        weapon2Title = new TextView(this);
        weapon2Title.setText(getString(R.string.photon_torpedo));
        linearLayout.addView(weapon2Title);
        
        
        //Creates a new ll for the weapon input and button
        LinearLayout weapon2InputAndButton = new LinearLayout(this);
        weapon2InputAndButton.setOrientation(LinearLayout.HORIZONTAL);
        weapon2InputAndButton.setLayoutParams(theParams);
        
        //Creates edit and button
        weapon2Edit = new EditText(this);
        weapon2Edit.setHint("Enter Number of Rounds");
        Button weapon2Button = new Button(this);
        weapon2Button.setText("Enter");
        weapon2Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numberOfBulletsEntered = Integer.parseInt(weapon2Edit.getText().toString());
				
				Random randomGenerated = new Random();
				
				
				for (int index = 1; index <= numberOfBulletsEntered; ++index)
				{
					int randomInt = randomGenerated.nextInt(numberOfBulletsEntered + 1);
					
					String gunName = (String) weapon2Title.getText();
					
					//System.out.println(gunName);
					generateResults(randomInt, gunName, numberOfBulletsEntered);
					
					//resultText.setText("Ship hit " + randomInt + " times.");
					//System.out.println(randomInt);
				}
				
			}
		});
        weapon2InputAndButton.addView(weapon2Edit);
        weapon2InputAndButton.addView(weapon2Button);
        linearLayout.addView(weapon2InputAndButton);
        
        
        
        //Sets the title for the next weapon
        weapon3Title = new TextView(this);
        weapon3Title.setText(getString(R.string.drone_launcher));
        linearLayout.addView(weapon3Title);
        
        
        //Creates a new ll for the weapon input and button
        LinearLayout weapon3InputAndButton = new LinearLayout(this);
        weapon3InputAndButton.setOrientation(LinearLayout.HORIZONTAL);
        weapon3InputAndButton.setLayoutParams(theParams);
        
        //Creates edit and button
        weapon3Edit = new EditText(this);
        weapon3Edit.setHint("Enter Number of Rounds");
        Button weapon3Button = new Button(this);
        weapon3Button.setText("Enter");
        weapon3Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numberOfBulletsEntered = Integer.parseInt(weapon3Edit.getText().toString());
				
				Random randomGenerated = new Random();
				
				
				for (int index = 1; index <= numberOfBulletsEntered; ++index)
				{
					int randomInt = randomGenerated.nextInt(numberOfBulletsEntered + 1);
					
					String gunName = (String) weapon3Title.getText();
					
					//System.out.println(gunName);
					generateResults(randomInt, gunName, numberOfBulletsEntered);
					
					//resultText.setText("Ship hit " + randomInt + " times.");
					//System.out.println(randomInt);
				}
				
			}
		});
        
        
        weapon3InputAndButton.addView(weapon3Edit);
        weapon3InputAndButton.addView(weapon3Button);
        linearLayout.addView(weapon3InputAndButton);
        
        
        
        
        //Sets the title for the next weapon
        weapon4Title = new TextView(this);
        weapon4Title.setText(getString(R.string.microwave_cannon));
        linearLayout.addView(weapon4Title);
        
        
        //Creates a new ll for the weapon input and button
        LinearLayout weapon4InputAndButton = new LinearLayout(this);
        weapon4InputAndButton.setOrientation(LinearLayout.HORIZONTAL);
        weapon4InputAndButton.setLayoutParams(theParams);
        
        //Creates edit and button
        weapon4Edit = new EditText(this);
        weapon4Edit.setHint("Enter Number of Rounds");
        Button weapon4Button = new Button(this);
        weapon4Button.setText("Enter");
        weapon4Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int numberOfBulletsEntered = Integer.parseInt(weapon4Edit.getText().toString());
				
				Random randomGenerated = new Random();
				
				
				for (int index = 1; index <= numberOfBulletsEntered; ++index)
				{
					int randomInt = randomGenerated.nextInt(numberOfBulletsEntered + 1);
					
					String gunName = (String) weapon4Title.getText();
					
					//System.out.println(gunName);
					generateResults(randomInt, gunName, numberOfBulletsEntered);
					
					//resultText.setText("Ship hit " + randomInt + " times.");
					//System.out.println(randomInt);
				}
				
			}
		});
        
        
        weapon4InputAndButton.addView(weapon4Edit);
        weapon4InputAndButton.addView(weapon4Button);
        linearLayout.addView(weapon4InputAndButton);
        
        resultText = new TextView(this);
        //resultText.setLayoutParams(theParams);
        //resultText.setText("Results go here...");
        linearLayout.addView(resultText);
        
        
        
        
       
        
        
        
        
        //Sets the view as the ll
        setContentView(linearLayout);
    }

    
    //Does if checks to see if the ship misses, hits or destroys the enemy ship
    public void generateResults(int randomNum, String theGunName, int maxRounds)
    {
    	//System.out.println(randomNum);
    	boolean winning = false;
    	
    	if (randomNum == 0)
    	{
    		resultText.setText("The " + theGunName + " missed the enemy ship");
    		System.out.println("The " + theGunName + " missed the enemy ship");
    		
    	} else if (randomNum < maxRounds && randomNum > 0)
    	{
    		resultText.setText("Ship hit " + randomNum + " times with the " + theGunName + ".");
    		System.out.println("Ship hit " + randomNum + " times with the " + theGunName + ".");
    	}
    	else 
    	{

    		winning = true;
    		if (winning == true)
    		{
    			resultText.setText("The " + theGunName + " destroyed the enemy ship");
        		System.out.println("The " + theGunName + " destroyed the enemy ship");
    		} else 
    		{
    			resultText.setText("Something mysterious has happened...");
        		System.out.println("Something mysterious has happened...");
    		}
    		winning = false;
    	}
    	
    	
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
