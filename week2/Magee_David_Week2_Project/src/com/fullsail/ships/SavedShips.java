package com.fullsail.ships;

import org.json.JSONException;
import org.json.JSONObject;
import com.fullsail.ships.Ships;

public class SavedShips {
	
	static Ships[] ships = Ships.shipsList();
	
	static JSONObject shipGroup;
	
	//Creates a JSON object
	public static JSONObject buildJSON(int shipIndex, String captainName) throws JSONException{
		
		Ships selectedShip = ships[shipIndex];
		
		String captain = captainName;
		
		shipGroup = new JSONObject();
		
		JSONObject shipAndCaptainInfo = new JSONObject();
		
		String shipname = selectedShip.shipname;
		int hp = selectedShip.hitpoints;
		int ap = selectedShip.attackpower;
		int hr = selectedShip.hitrating;
		int shipin = shipIndex;
		
		shipAndCaptainInfo.put("captain", captain);
		shipAndCaptainInfo.put("ship", shipname);
		shipAndCaptainInfo.put("shiphp", hp);
		shipAndCaptainInfo.put("shipattack", ap);
		shipAndCaptainInfo.put("shiphitrating", hr);
		shipAndCaptainInfo.put("shipindex", shipin);
		
		shipGroup.put("savedship", shipAndCaptainInfo);
		
		System.out.println(shipGroup.getJSONObject("savedship").getString("captain"));
		
		return shipGroup;
	}
	
	public static String[] readJSON() throws JSONException
	{
		String[] result = {shipGroup.getJSONObject("savedship").getString("captain"), 
				shipGroup.getJSONObject("savedship").getString("ship")};
		
		
		
		return result;
		
	}
	
	public static int shipIndexFromJSON() throws JSONException
	{
		int index = shipGroup.getJSONObject("savedship").getInt("shipindex");
		
		return index;
	}

}
