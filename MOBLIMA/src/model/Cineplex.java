package model;

import java.util.ArrayList;

public class Cineplex {
	private String companyName;
	private String name;
	private String address;
	private ArrayList<Cinema> cinemas;
	
	private ArrayList<Movie> movies;
	
	public Cineplex(String companyName,String name, String address, ArrayList<Cinema> cinemas){
		setName(name);
		setCompanyName(companyName);
		setAddress(address);
		setCinemas(cinemas);
	}
	
	public Cineplex() {
		// TODO Auto-generated constructor stub
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void addCinema(Cinema cinema){
		this.cinemas.add(cinema);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	
}
