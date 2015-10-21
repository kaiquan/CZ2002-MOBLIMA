package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Movie {
	private String id;
	private String title;
	private MOVIESTATUS movieStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> casts;
	private int showDuration;
	private AGELIMIT ageLimit;
	private MOVIETYPE movieType;
	private ArrayList<Review> reviews;
	private String cineplexName;
	
	private Cineplex cineplex;
	
	

	public Movie(Cineplex cineplex, String title, MOVIETYPE movieType, MOVIESTATUS movieStatus, String synopsis, String director, ArrayList<String>casts, int showDuration, AGELIMIT ageLimit, ArrayList<Review>reviews){
		setTitle(title);
		setMovieStatus(movieStatus);
		setSynopsis(synopsis);
		setDirector(director);
		setCasts(casts);
		setShowDuration(showDuration);
		setReviews(reviews);
		setAgeLimit(ageLimit);
		setMovieType(movieType);
		setCineplex(cineplex);
		
		String dateString = new SimpleDateFormat("YYYY-MM-DD-hh-mm").format(new Date());
		
		StringTokenizer st = new StringTokenizer(cineplex.getName(), " /n");
		String cinemaLetters="";
	    while (st.hasMoreTokens()) {
	        String word = st.nextToken();
	        word = word.toUpperCase();
	        char firstChar = word.charAt(0);
	        cinemaLetters+=firstChar;
	    }
	   
		//id is the cinema's first letter in each word + mevietitle + movie type + the date time created 
		setId(cinemaLetters.toUpperCase()+"-"+title.toUpperCase()+"-"+movieType.toString().toUpperCase()+"-"+dateString);
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Cineplex getCineplex() {
		return cineplex;
	}

	public void setCineplex(Cineplex cineplex) {
		this.cineplex = cineplex;
	}
	
	public void addReview(Review review){
		this.reviews.add(review);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public MOVIESTATUS getMovieStatus() {
		return movieStatus;
	}
	
	public void setMovieStatus(MOVIESTATUS movieStatus) {
		this.movieStatus = movieStatus;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public ArrayList<String> getCasts() {
		return casts;
	}
	
	public void setCasts(ArrayList<String> casts) {
		this.casts = casts;
	}
	
	public int getShowDuration() {
		return showDuration;
	}
	
	public void setShowDuration(int showDuration) {
		this.showDuration = showDuration;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public void setReviews(ArrayList<Review> reviews2) {
		this.reviews = reviews2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AGELIMIT getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(AGELIMIT ageLimit) {
		this.ageLimit = ageLimit;
	}

	public MOVIETYPE getMovieType() {
		return movieType;
	}

	public void setMovieType(MOVIETYPE movieType) {
		this.movieType = movieType;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	
	
}


