package dao_module;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.CINEMACLASS;
import model.DAYOFWEEK;
import model.Price;
import model.TICKETTYPE;

public class PriceDAO extends JSONDAO {
	
	private JSONObject jsonObject;
	private Price ticketPrice;
	
	public PriceDAO(){
		jsonObject = readFile(pricePath);
		parseJson(this.jsonObject);
	}
	
	/**
	 **@purpose	: update the pricing list
	 **@param	: Price price
	 **@return	: void
	**/
	@SuppressWarnings("unchecked")
	public void updatePrice(Price price){
		//update the phantium price
		JSONArray jsonPrice = (JSONArray) jsonObject.get("prices");
		JSONObject phantium = (JSONObject) jsonPrice.get(0);
		phantium.put("platinumcinema", price.getPlantiumCinemaPrice());
		
		//update the studentprice
		JSONObject jsonStudentPriceObject = (JSONObject)jsonPrice.get(1);
		JSONArray jsonStudentPriceArray = (JSONArray)jsonStudentPriceObject.get("studentprices");
		//update mon
		JSONObject studentDays = (JSONObject)jsonStudentPriceArray.get(0);
		JSONArray studentPriceTIming = (JSONArray)studentDays.get("monday");
		JSONObject priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		JSONObject priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(0).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(0).get(1));
		//update tuesday
		studentDays = (JSONObject)jsonStudentPriceArray.get(1);
		studentPriceTIming = (JSONArray)studentDays.get("tuesday");
		priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(1).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(1).get(1));
		//update wednesday
		studentDays = (JSONObject)jsonStudentPriceArray.get(2);
		studentPriceTIming = (JSONArray)studentDays.get("wednesday");
		priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(2).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(2).get(1));
		//update thursday
		studentDays = (JSONObject)jsonStudentPriceArray.get(3);
		studentPriceTIming = (JSONArray)studentDays.get("thursday");
		priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(3).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(3).get(1));
		//update friday
		studentDays = (JSONObject)jsonStudentPriceArray.get(4);
		studentPriceTIming = (JSONArray)studentDays.get("friday");
		priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(4).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(4).get(1));
		//update sat
		studentDays = (JSONObject)jsonStudentPriceArray.get(5);
		studentPriceTIming = (JSONArray)studentDays.get("saturday");
		priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(5).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(5).get(1));
		//update sun
		studentDays = (JSONObject)jsonStudentPriceArray.get(6);
		studentPriceTIming = (JSONArray)studentDays.get("sunday");
		priceBefore6 = (JSONObject)studentPriceTIming.get(0);
		priceAfter6 = (JSONObject)studentPriceTIming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(0).get(6).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(0).get(6).get(1));
		
		
		//update senior pricing
		JSONObject jsonSeniorPriceObject = (JSONObject)jsonPrice.get(2);
		JSONArray jsonSeniorPriceArray = (JSONArray)jsonSeniorPriceObject.get("seniorprices");
		
		//update mon
		JSONObject seniorDays = (JSONObject)jsonSeniorPriceArray.get(0);
		JSONArray seniorPriceTiming = (JSONArray)seniorDays.get("monday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(0).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(0).get(1));
		
		seniorDays = (JSONObject)jsonSeniorPriceArray.get(1);
		seniorPriceTiming = (JSONArray)seniorDays.get("tuesday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(1).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(1).get(1));
		
		seniorDays = (JSONObject)jsonSeniorPriceArray.get(2);
		seniorPriceTiming = (JSONArray)seniorDays.get("wednesday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(2).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(2).get(1));
		
		seniorDays = (JSONObject)jsonSeniorPriceArray.get(3);
		seniorPriceTiming = (JSONArray)seniorDays.get("thursday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(3).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(3).get(1));
		
		seniorDays = (JSONObject)jsonSeniorPriceArray.get(4);
		seniorPriceTiming = (JSONArray)seniorDays.get("friday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(4).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(4).get(1));
		
		seniorDays = (JSONObject)jsonSeniorPriceArray.get(5);
		seniorPriceTiming = (JSONArray)seniorDays.get("saturday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(5).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(5).get(1));
		
		seniorDays = (JSONObject)jsonSeniorPriceArray.get(6);
		seniorPriceTiming = (JSONArray)seniorDays.get("sunday");
		priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
		priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(1).get(6).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(1).get(6).get(1));
		
		
		//update regular pricing
		JSONObject jsonRegularPriceObject = (JSONObject)jsonPrice.get(3);
		JSONArray jsonRegularPriceArray = (JSONArray)jsonRegularPriceObject.get("regularprices");
		
		JSONObject regularDays = (JSONObject)jsonRegularPriceArray.get(0);
		JSONArray regularPriceTiming = (JSONArray)regularDays.get("monday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(0).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(0).get(1));
		
		regularDays = (JSONObject)jsonRegularPriceArray.get(1);
		regularPriceTiming = (JSONArray)regularDays.get("tuesday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(1).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(1).get(1));
		
		regularDays = (JSONObject)jsonRegularPriceArray.get(2);
		regularPriceTiming = (JSONArray)regularDays.get("wednesday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(2).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(2).get(1));
		
		regularDays = (JSONObject)jsonRegularPriceArray.get(3);
		regularPriceTiming = (JSONArray)regularDays.get("thursday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(3).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(3).get(1));
		
		regularDays = (JSONObject)jsonRegularPriceArray.get(4);
		regularPriceTiming = (JSONArray)regularDays.get("friday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(4).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(4).get(1));
		
		regularDays = (JSONObject)jsonRegularPriceArray.get(5);
		regularPriceTiming = (JSONArray)regularDays.get("saturday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(5).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(5).get(1));
		
		regularDays = (JSONObject)jsonRegularPriceArray.get(6);
		regularPriceTiming = (JSONArray)regularDays.get("sunday");
		priceBefore6 = (JSONObject)regularPriceTiming.get(0);
		priceAfter6 = (JSONObject)regularPriceTiming.get(1);
		priceBefore6.put("pricebefore6", price.getPricings().get(2).get(6).get(0));
		priceAfter6.put("priceafter6", price.getPricings().get(2).get(6).get(1));
		
		updateFile(JSONDAO.pricePath,this.jsonObject);
	}
	
	/**
	 **@purpose	: retrieve the prices
	 **@param	: void
	 **@return	: Price
	**/
	public Price getTickestPrices(){
		return this.ticketPrice;
	}
	
	/**
	 **@purpose	: caculate the price
	 **@param	: CINEMACLASS cinemaType, TICKETYPE, tickeytype, DAYOFWEEK dayofweek, boolean is_After6
	 **@return	: double
	**/
	public double getPrice(CINEMACLASS cinemaType, TICKETTYPE ticketType, DAYOFWEEK dayOfWeek, boolean is_After6){
		double price = 0;
		
		if(cinemaType==CINEMACLASS.PLANTIUM_CLASS){
			price = this.ticketPrice.getPlantiumCinemaPrice();
		}
		else{
			if(ticketType == TICKETTYPE.Regular){
				switch(dayOfWeek){
					case  MON :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(0).get(1);
						else
							price = (Double) this.ticketPrice.getPricings().get(2).get(0).get(0);
						break;
					case TUE :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(1).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(2).get(1).get(0);
						break;
					case  WED :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(2).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(2).get(2).get(0);
						break;
					case  THUR :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(3).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(2).get(3).get(0);
						break;
					case  FRI :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(4).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(2).get(4).get(0);
						break;
					case  SAT :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(5).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(2).get(5).get(0);
						break;
					case  SUN :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(2).get(6).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(2).get(6).get(0);
						break;
				}
			}
			else if(ticketType == TICKETTYPE.Senior_citizens){
				switch(dayOfWeek){
					case  MON :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(0).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(0).get(0);
						break;
					case TUE :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(1).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(1).get(0);
						break;
					case  WED :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(2).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(2).get(0);
						break;
					case  THUR :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(3).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(3).get(0);
						break;
					case  FRI :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(4).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(4).get(0);
						break;
					case  SAT :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(5).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(5).get(0);
						break;
					case  SUN :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(1).get(6).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(1).get(6).get(0);
						break;
				}
			}
			else if(ticketType == TICKETTYPE.Student){
				switch(dayOfWeek){
					case  MON :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(0).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(0).get(0);
						break;
					case TUE :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(1).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(1).get(0);
						break;
					case  WED :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(2).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(2).get(0);
						break;
					case  THUR :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(3).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(3).get(0);
						break;
					case  FRI :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(4).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(4).get(0);
						break;
					case  SAT :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(5).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(5).get(0);
						break;
					case  SUN :
						if(is_After6)
							price = (Double)this.ticketPrice.getPricings().get(0).get(6).get(1);
						else
							price = (Double)this.ticketPrice.getPricings().get(0).get(6).get(0);
						break;
				}
			}
		}
		return price;
	}
	
	
	private void parseJson(JSONObject jsonObject){
		ArrayList<ArrayList<ArrayList<Object>>> ticketPrices = new ArrayList<ArrayList<ArrayList<Object>>>();
		
		if(this.jsonObject != null){
			JSONArray jsonPrice = (JSONArray) jsonObject.get("prices");
			
			if(this.ticketPrice==null)
				this.ticketPrice = new Price();
			
			//get the phantium price
			JSONObject phantium = (JSONObject) jsonPrice.get(0);
			this.ticketPrice.setPlantiumCinemaPrice(Double.parseDouble(phantium.get("platinumcinema").toString()));
			
			//get the student price
			JSONObject jsonStudentPriceObject = (JSONObject)jsonPrice.get(1);
			JSONArray jsonStudentPriceArray = (JSONArray)jsonStudentPriceObject.get("studentprices");
			
			ArrayList<ArrayList<Object>> studentArray = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> prices = new ArrayList<Object>();
			
			//get price for mon
			JSONObject studentDays = (JSONObject)jsonStudentPriceArray.get(0);
			JSONArray studentPriceTIming = (JSONArray)studentDays.get("monday");
			JSONObject priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			JSONObject priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			//get prive for tue
			prices = new ArrayList<Object>();
			studentDays = (JSONObject)jsonStudentPriceArray.get(1);
			studentPriceTIming = (JSONArray)studentDays.get("tuesday");
			priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			//get prive for wed
			prices = new ArrayList<Object>();
			studentDays = (JSONObject)jsonStudentPriceArray.get(2);
			studentPriceTIming = (JSONArray)studentDays.get("wednesday");
			priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			//get prive for thursday
			prices = new ArrayList<Object>();
			studentDays = (JSONObject)jsonStudentPriceArray.get(3);
			studentPriceTIming = (JSONArray)studentDays.get("thursday");
			priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			//get prive for fri
			prices = new ArrayList<Object>();
			studentDays = (JSONObject)jsonStudentPriceArray.get(4);
			studentPriceTIming = (JSONArray)studentDays.get("friday");
			priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			//get prive for sat
			prices = new ArrayList<Object>();
			studentDays = (JSONObject)jsonStudentPriceArray.get(5);
			studentPriceTIming = (JSONArray)studentDays.get("saturday");
			priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			//get prive for sunday
			prices = new ArrayList<Object>();
			studentDays = (JSONObject)jsonStudentPriceArray.get(6);
			studentPriceTIming = (JSONArray)studentDays.get("sunday");
			priceBefore6 = (JSONObject)studentPriceTIming.get(0);
			priceAfter6 = (JSONObject)studentPriceTIming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			studentArray.add(prices);
			ticketPrices.add(studentArray);
			
			//get prices for seniours
			JSONObject jsonSeniorPriceObject = (JSONObject)jsonPrice.get(2);
			JSONArray jsonSeniorPriceArray = (JSONArray)jsonSeniorPriceObject.get("seniorprices");
			
			ArrayList<ArrayList<Object>> seniorArray = new ArrayList<ArrayList<Object>>();
			prices = new ArrayList<Object>();

			//get price for mon
			JSONObject seniorDays = (JSONObject)jsonSeniorPriceArray.get(0);
			
			JSONArray seniorPriceTiming = (JSONArray)seniorDays.get("monday");
			prices = new ArrayList<Object>();
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			//get price for tue
			seniorDays = (JSONObject)jsonSeniorPriceArray.get(1);
			prices = new ArrayList<Object>();
			seniorPriceTiming = (JSONArray)seniorDays.get("tuesday");
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			//get price for wednesday
			seniorDays = (JSONObject)jsonSeniorPriceArray.get(2);
			prices = new ArrayList<Object>();
			seniorPriceTiming = (JSONArray)seniorDays.get("wednesday");
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			//get price for thursday
			seniorDays = (JSONObject)jsonSeniorPriceArray.get(3);
			prices = new ArrayList<Object>();
			seniorPriceTiming = (JSONArray)seniorDays.get("thursday");
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			//get price for friday
			seniorDays = (JSONObject)jsonSeniorPriceArray.get(4);
			prices = new ArrayList<Object>();
			seniorPriceTiming = (JSONArray)seniorDays.get("friday");
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			//get price for saturday
			seniorDays = (JSONObject)jsonSeniorPriceArray.get(5);
			prices = new ArrayList<Object>();
			seniorPriceTiming = (JSONArray)seniorDays.get("saturday");
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			//get price for sunday
			seniorDays = (JSONObject)jsonSeniorPriceArray.get(6);
			prices = new ArrayList<Object>();
			seniorPriceTiming = (JSONArray)seniorDays.get("sunday");
			priceBefore6 = (JSONObject)seniorPriceTiming.get(0);
			priceAfter6 = (JSONObject)seniorPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			seniorArray.add(prices);
			ticketPrices.add(seniorArray);
			
			//get prices for regulars
			JSONObject jsonRegularPriceObject = (JSONObject)jsonPrice.get(3);
			JSONArray jsonRegularPriceArray = (JSONArray)jsonRegularPriceObject.get("regularprices");
			
			ArrayList<ArrayList<Object>> regularArray = new ArrayList<ArrayList<Object>>();
			prices = new ArrayList<Object>();
			
			//get price for mon
			JSONObject regularDays = (JSONObject)jsonRegularPriceArray.get(0);
			prices = new ArrayList<Object>();
			JSONArray regularPriceTiming = (JSONArray)regularDays.get("monday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			//get price for tuesday
			regularDays = (JSONObject)jsonRegularPriceArray.get(1);
			prices = new ArrayList<Object>();
			regularPriceTiming = (JSONArray)regularDays.get("tuesday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			//get price for wednesday
			regularDays = (JSONObject)jsonRegularPriceArray.get(2);
			prices = new ArrayList<Object>();
			regularPriceTiming = (JSONArray)regularDays.get("wednesday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			//get price for thursday
			regularDays = (JSONObject)jsonRegularPriceArray.get(3);
			prices = new ArrayList<Object>();
			regularPriceTiming = (JSONArray)regularDays.get("thursday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			//get price for friday
			regularDays = (JSONObject)jsonRegularPriceArray.get(4);
			prices = new ArrayList<Object>();
			regularPriceTiming = (JSONArray)regularDays.get("friday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			//get price for saturday
			regularDays = (JSONObject)jsonRegularPriceArray.get(5);
			prices = new ArrayList<Object>();
			regularPriceTiming = (JSONArray)regularDays.get("saturday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			//get price for sunday
			regularDays = (JSONObject)jsonRegularPriceArray.get(6);
			prices = new ArrayList<Object>();
			regularPriceTiming = (JSONArray)regularDays.get("sunday");
			priceBefore6 = (JSONObject)regularPriceTiming.get(0);
			priceAfter6 = (JSONObject)regularPriceTiming.get(1);
			prices.add(Double.parseDouble(priceBefore6.get("pricebefore6").toString()));
			prices.add(Double.parseDouble(priceAfter6.get("priceafter6").toString()));
			regularArray.add(prices);
			ticketPrices.add(regularArray);
			
			this.ticketPrice.setPricings(ticketPrices);
		}
	}
}
