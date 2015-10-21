package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Showtime {
	private String id;
	private Date dateTime;
	private String movieId;
	private String cinemaCode;
	private String cineplexName;
	
	private Movie movie;
	private Cinema cinema;

	
	public Showtime(Date dateTime, Movie movie, Cinema cinema){
		setDateTime(dateTime);
		setMovie(movie);
		setMovieId(movie.getId());
		setCinema(cinema);
		setCinemaCode(cinema.getCinemaCode());
		setCineplexName(cinema.getCineplex().getName());
	
		String dateString = new SimpleDateFormat("YYYY-MM-DD-hh-mm").format(dateTime);
		
		StringTokenizer st = new StringTokenizer(cinema.getCineplex().getName(), " /n");
		String cinemaLetters="";
	    while (st.hasMoreTokens()) {
	        String word = st.nextToken();
	        word = word.toUpperCase();
	        char firstChar = word.charAt(0);
	        cinemaLetters+=firstChar;
	        cinemaLetters.toUpperCase();
	    }
		setId(cinema.getCinemaCode()+"-"+dateString);
	}

	public Showtime() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime2) {
		this.dateTime = dateTime2;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	
}
