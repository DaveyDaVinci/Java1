package com.fullsail.ships;

public enum Ships {
	
	//Holds ship and its hitpoints
	DESTROYER("Destroyer",175, 25, 60),
	SKYTANK("Skytank",225, 40, 40),
	MOVER("Mover", 150, 30, 55),
	SCOUTSHIP("Scoutship", 150, 25, 70),
	DART("Dart", 175, 30, 65);
	
	public final int hitpoints;
	public final int attackpower;
	public final int hitrating;
	public final String shipname;
	
	
	
	Ships(String shippy, int hp, int ap, int hr)
	{
		
		this.hitpoints = hp;
		this.attackpower = ap;
		this.hitrating = hr;
		this.shipname = shippy;
	}
	
	public static Ships[] shipsList()
	{
		Ships[] listOfShips = new Ships[]{DESTROYER, SKYTANK, MOVER, SCOUTSHIP, DART};
		
		return listOfShips;
		
		
	}
}
