package model;

import java.util.ArrayList;

public class Cinema {
	private String cinemaCode;
	private CINEMACLASS cinemaClass;
	@SuppressWarnings("rawtypes")
	private ArrayList[][]  seats;
	private Cineplex cineplex;
	
	public Cinema(Cineplex cineplex, CINEMACLASS cinemaClass, @SuppressWarnings("rawtypes") ArrayList[][]seats){
		setCineplex(cineplex);
		setCinemaClass(cinemaClass);
		setSeats(seats);
		setCinemaCode(cineplex.getName().substring(0, 2)+cineplex.getCinemas().size()+1);
	}
	
	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public CINEMACLASS getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(CINEMACLASS cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList[][] getSeats() {
		return seats;
	}

	public void setSeats(@SuppressWarnings("rawtypes") ArrayList[][] seats) {
		this.seats = seats;
	}

	public Cineplex getCineplex() {
		return cineplex;
	}

	public void setCineplex(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	
}
