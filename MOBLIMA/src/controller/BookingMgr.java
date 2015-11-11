/**
 * A controller class which manages the logics needed to perform ticket booking.
 * @author 	Freny Nelin
 * @version	1.0
 * @since	2015-11-10
 */

package controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Booking;
import model.Cinema;
import model.DAYOFWEEK;
import model.Guest;
import model.Movie;
import model.Showtime;
import model.TICKETTYPE;
import dao_module.BookingDAO;
import dao_module.MovieDAO;
import dao_module.PriceDAO;
import dao_module.ShowTimeDAO;
import view.BookingView;

public class BookingMgr {

	/**
	 * Class that interfaces with the user (displays)
	 */
	private BookingView bookingView;
	
	/**
	 * Seats selected for booking
	 */
	private ArrayList<String> selectedSeats;
	
	/**
	 * Movie selected for booking
	 */
	private Movie selectedMovie;
	
	/**
	 * Cinema selected for booking
	 */
	private Cinema selectedCinema;
	
	/**
	 * Showtime selected for booking
	 */
	private Showtime showtime;
	
	/**
	 * Cineplex selected for booking
	 */
	private String selectedCineplexName;
	
	/**
	 * Number of senior(s) who are going to the movie
	 */
	private int numOfSenior;
	
	/**
	 * Number of student(s) who are going to the movie
	 */
	private int numOfStudent;
	
	/**
	 * Movie-goer who is booking the ticket(s)
	 */
	private Guest guest;
	
	/**
	 * Total price payable for selected ticket(s)
	 */
	private double totalPrice;
	
	/**
	 * Creates a new BookingMgr with all attributes initialized to null or empty (except Booking View)
	 */
	public BookingMgr(){
		if(this.bookingView == null){
			this.bookingView = new BookingView(this);
		}
		this.selectedSeats = new ArrayList<String>();
		this.selectedMovie = null;
		this.selectedCinema = null;
		this.showtime = null;
		this.selectedCineplexName = null;
	}
	
	/**
	 * Creates a new BookingMgr with given list of seats, movie selected, cinema selected, 
	 * showtime selected, cineplex name selected for booking purposes.
	 * @param selectedSeats		A list of seats selected for booking
	 * @param selectedMovie		Movie selected for booking
	 * @param selectedCinema	Cinema selected for booking
	 * @param showtime			Showtime selected for booking
	 * @param cineplexName		Name of the selected cineplex 
	 */
	public BookingMgr(ArrayList<String> selectedSeats, Movie selectedMovie, Cinema selectedCinema, Showtime showtime, String cineplexName){
		if(this.bookingView == null){
			this.bookingView = new BookingView(this);
		}
		this.selectedSeats = selectedSeats;
		this.selectedMovie = selectedMovie;
		this.selectedCinema = selectedCinema;
		this.showtime = showtime;
		this.selectedCineplexName = cineplexName;
	}
	
	/**
	 * Calls Booking View to show booking details with selected items (seats, movie, cinema, showtime, cineplex)
	 */
	public void displayBookingDetails(){
		this.bookingView.showBookingDetails(selectedSeats, selectedMovie.getTitle(), selectedMovie.getMovieType().toString(), selectedCinema.getCinemaCode(), showtime.getDateTime(), selectedCineplexName);
	}
	
	/**
	 * Calls Booking View to interface with user and get user's details
	 */
	public void getMovieGoerDetails(){
		this.bookingView.getMovieGoersDetails();
	}
	
	/**
	 * Set Movie Goer's details for booking purpose.
	 * @param name		Movie goer's name
	 * @param email		Movie goer's email
	 * @param mobileNo	Movie goer's mobile number
	 */
	public void setMovieGoerDetails(String name, String email, int mobileNo){
		this.guest = new Guest(name, email, mobileNo, selectedCineplexName);
	}
	
	/**
	 * Calls Booking View to interface with user and get the number of senior(s) and student(s)
	 * who will be using the ticket(s)
	 */
	public void getSeniorAndStudentCount(){
		this.bookingView.getSeniorAndStudentCount(selectedSeats.size());
	}
	
	/**
	 * Set the number of senior(s) and student(s) obtained from user
	 * @param numOfSenior	Number of senior(s)
	 * @param numOfStudent 	Number of student(s)
	 */
	public void setSeniorAndStudentCount(int numOfSenior, int numOfStudent){
		this.numOfSenior = numOfSenior;
		this.numOfStudent = numOfStudent;
	}
	
	
	/**
	 * Calculate the ticket costs for the booked ticket(s) and 
	 * Calls Booking View to display the ticket costs to the user 
	 */
	public void calculateAndDisplayTicketCosts(){
		int numOfAdult = this.selectedSeats.size()-numOfSenior-numOfStudent;
		PriceDAO pd = new PriceDAO();
		
		Date date = this.showtime.getDateTime();
		int day = Integer.parseInt(new SimpleDateFormat("u").format(date)) - 1;
		boolean afterSix = Integer.parseInt(new SimpleDateFormat("H").format(date)) >= 18;
		
		double adultCost = pd.getPrice(selectedCinema.getCinemaClass(), TICKETTYPE.Regular, DAYOFWEEK.values()[day], afterSix);
		double studentCost = pd.getPrice(selectedCinema.getCinemaClass(), TICKETTYPE.Student, DAYOFWEEK.values()[day], afterSix);
		double seniorCost = pd.getPrice(selectedCinema.getCinemaClass(), TICKETTYPE.Senior_citizens, DAYOFWEEK.values()[day], afterSix);
		
		double totalCost = (numOfAdult * adultCost) + (numOfStudent * studentCost) + (numOfSenior * seniorCost);
		this.totalPrice = totalCost;
		this.bookingView.displayTotalBookingCost(selectedSeats.size(), numOfAdult, numOfSenior, numOfStudent, adultCost, seniorCost, studentCost, totalCost);
	}
	
	/**
	 * Create Transaction ID with selected cinema and current booking date
	 * @return Transaction ID of current booking
	 */
	private String createTransactionID(){
		String tid = "";
		tid += this.selectedCinema.getCinemaCode();
		tid += "-";
		tid += new SimpleDateFormat("YYYYMMDDhhmm").format(new Date());
		return tid;
	}
	
	/**
	 * Create a Booking and Calls Data Access Object (DAO) to store it in database (Json Files).
	 * After successful or unsuccessful attempt, calls Booking View to display the result to the user.
	 */
	public void proceedBooking(){
		BookingDAO bd = new BookingDAO();
		String tid = createTransactionID();
		Booking book = new Booking(tid, selectedMovie, showtime, numOfSenior, numOfStudent, selectedSeats, guest, selectedCinema, this.totalPrice);
		bd.createNewBooking(book);
		this.bookingView.displaySuccessfulOrUnsuccessfulBooking(true);
	}
	
	/**
	 * Calls Booking View to get the details of the movie goer,
	 * Calls Data Access Object to search Booking History from database (Json Files) with the user details, and
	 * Calls Booking View to display the result to the user.
	 */
	public void checkBookingHistory(){
		ArrayList<Booking> bookingHistory = new ArrayList<Booking>();
		BookingDAO bookingDAO = new BookingDAO(true);
		this.bookingView.getMovieGoersDetails();
		
		bookingHistory = bookingDAO.getBookingsByGuest(this.guest);
		this.bookingView.displayBookingHistory(bookingHistory);
	}
	
	/**
	 * Use a Movie ID to retrieve Movie Object from Data Access Object (DAO) and 
	 * Construct a String consists of the Movie title and Movie type
	 * @param movieId	Movie ID
	 * @return	Concatenated Movie Title and Movie Type as String
	 */
	public String getMovieTitlePlusType(String movieId){
		MovieDAO md = new MovieDAO();
		Movie selectedMovie;
		selectedMovie = md.getMovieByid(movieId);
		return selectedMovie.getTitle() + " " + selectedMovie.getMovieType().toString();
	}
	
	/**
	 * Use a Showtime ID to retrieve Showtime Object from Data Access Object (DAO) and
	 * Construct a String consists of user readable date time like: Sat 13:30 pm, 11-11-2015
	 * @param showtimeId	Showtime ID
	 * @return	User readable date and time string
	 */
	public String getShowtime(String showtimeId){
		ShowTimeDAO sd = new ShowTimeDAO();
		Showtime st;
		st = sd.getShowTimeByID(showtimeId);
		return new SimpleDateFormat("E hh:mm a, dd-MM-yyyy").format(st.getDateTime());
	}
	
}
