package model;

import java.util.ArrayList;

public class Price {
	private double plantiumCinemaPrice;
	private ArrayList<ArrayList<ArrayList<Object>>> pricings;
	
	
	public ArrayList<ArrayList<ArrayList<Object>>> getPricings() {
		return pricings;
	}
	public void setPricings(ArrayList<ArrayList<ArrayList<Object>>> pricings) {
		this.pricings = pricings;
	}
	public double getPlantiumCinemaPrice() {
		return plantiumCinemaPrice;
	}
	public void setPlantiumCinemaPrice(double plantiumCinemaPrice) {
		this.plantiumCinemaPrice = plantiumCinemaPrice;
	}
}
