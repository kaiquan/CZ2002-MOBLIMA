package model;

import java.util.ArrayList;

public class Booking{
	private String transactionid;
	private double totalPrice;
	private int numberOfSeniors;
	private int numberOfStudents;
	private ArrayList<String> seats;
	
	private String guestId;
	private String cineplexName;
	private String cinemaId;
	private String movieTitle;
	private String movieId;
	private String showtimeId;
	
	
	private Guest guest;
	private Cinema cinema;
	private Movie movie;
	private Showtime showTime;
	
	public Booking(String tid, Movie movie, Showtime showTime, int numberOfSeniors, int numberOfStudents, ArrayList<String> seats, Guest guest, Cinema cinema, double totalPrice){
		setTransactionid(tid);
		setMovie(movie);
		setNumberOfSeniors(numberOfSeniors);
		setNumberOfStudents(numberOfStudents);
		setSeats(seats);
		setGuestId(guest.getId());
		setCineplexName(cinema.getCineplex().getName());
		setMovieTitle(movie.getTitle());
		setMovieId(movie.getId());
		setCinemaId(cinema.getCinemaCode());
		setShowtimeId(showTime.getId());
		
		setGuest(guest);
		setCinema(cinema);
		setTotalPrice(totalPrice);
	}
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}
	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public double caculateTotalPrice(){
		//TODO formula to be put here...
		return 0.0;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNumberOfSeniors() {
		return numberOfSeniors;
	}

	public void setNumberOfSeniors(int numberOfSeniors) {
		this.numberOfSeniors = numberOfSeniors;
	}
	
	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public ArrayList<String> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<String> seats) {
		this.seats = seats;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getShowtimeId() {
		return showtimeId;
	}
	public void setShowtimeId(String showtimeId) {
		this.showtimeId = showtimeId;
	}
	public Showtime getShowTime() {
		return showTime;
	}
	public void setShowTime(Showtime showTime) {
		this.showTime = showTime;
	}
	
}
