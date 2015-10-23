package dao_module;

import org.json.simple.JSONObject;

import model.DAYOFWEEK;
import model.Price;

public class PriceDAO extends JSONDAO {
	
	private JSONObject jsonObject;
	
	public PriceDAO(){
		jsonObject = readFile(pricePath);
		parseJson(this.jsonObject);
	}
	
	
	public void updatePrice(Price price){
		
	}
	
	
	public double getPrice(boolean is_senior, boolean is_student, DAYOFWEEK dayOfWeek, boolean is_After6){
		return 0.01;
	}
	
	
	public void parseJson(JSONObject jsonObject){
		
	}
}
