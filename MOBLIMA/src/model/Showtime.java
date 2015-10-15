package model;

public class Showtime {
	private String id;
	private String dateTime;
	private MOVIETYPE movieType;
	private Movie movie;
	private Cinema cinema;
	
	public Showtime(String dateTime, MOVIETYPE movieType, Movie movie, Cinema cinema){
		setDateTime(dateTime);
		setMovieType(movieType);
		setMovie(movie);
		setCinema(cinema);
		setId(cinema.getCinemaCode()+dateTime);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public MOVIETYPE getMovieType() {
		return movieType;
	}

	public void setMovieType(MOVIETYPE movieType) {
		this.movieType = movieType;
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
	
}
