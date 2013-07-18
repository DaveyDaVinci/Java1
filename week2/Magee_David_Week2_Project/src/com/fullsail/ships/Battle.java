package com.fullsail.ships;

import java.util.Random;


//A battle class that makes a fight based on hit rating and hit points and attack power
public class Battle {
	static int yourHP;
	static int yourAP;
	static int yourHR;
	static int yourShipIndex;
	static int opponentHP;
	static int opponentAP;
	static int opponentHR;
	static String yourCaptain;
	static String yourShip;
	static String opponentCaptain;
	static String opponentShip;
	static String battleText;
	
	static Ships[] shipsList = Ships.shipsList();
	
	public static String[] yourFight(String yourC, String yourS, String opponentC, String opponentS, 
			int yourHit, int opponentsHit, int yourIndex)
	{
		yourShipIndex = yourIndex;
		Ships yourEnumShip = shipsList[yourIndex];
		yourCaptain = yourC;
		yourShip = yourS;
		yourHP = yourHit;
		yourAP = yourEnumShip.attackpower;
		yourHR = yourEnumShip.hitrating;
		
		
		opponentCaptain = opponentC;
		opponentShip = opponentS;
		opponentHP = opponentsHit;
		opponentAP = yourAP;
		opponentHR = yourHR;
		
		Random randomHit = new Random();
		
		int hitOrMiss = randomHit.nextInt(100);
		
		if (yourHR >= hitOrMiss)
		{
			
			int pendingHP = opponentHP;
			
			opponentHP = pendingHP - 20;
			
			if (opponentHP > 0)
			{
				
				battleText = "You hit. opponent has " + opponentHP + "left";
			} else
			{
				
				battleText = "Opponent is dead";
				
			}
			
		}
		else
		{
			
			battleText = "Miss!";
		}
		
		String yourHitPointsString = String.valueOf(opponentHP);
		
		String[] hitpoints = {battleText, yourHitPointsString};
		
		return hitpoints;
	
	
	}
	
	
}
