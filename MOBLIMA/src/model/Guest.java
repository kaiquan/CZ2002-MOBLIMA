package model;

import java.util.ArrayList;

public class Guest {
	private String id;
	private String name;
	private String email;
	private int mobileNo;
	private ArrayList<Booking> bookings;
	private String cineplexName;
	
	public Guest(String name, String email, int mobileNo, String cineplexName){
		setName(name);
		setEmail(email);
		setMobileNo(mobileNo);
		setCineplexName(cineplexName);
		setId(email+"_"+mobileNo);
	}
	
	public Guest() {
		
	}

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}
}
