/**
 * A controller class which manages the logics needed to List Top 5 Movies Based on Ticket sales or Ratings
 * @author 	Freny Nelin
 * @version	1.0
 * @since	2015-11-10
 */

package controller;

import java.util.ArrayList;
import java.util.Arrays;

import view.AdminTopFiveMoviesView;
import model.Booking;
import model.Cineplex;
import model.Movie;
import model.TicketSalesComparable;
import dao_module.BookingDAO;
import dao_module.MovieDAO;

public class AdminTopFiveMoviesMgr {

	/**
	 * Class that interfaces with the user (displays)
	 */
	private AdminTopFiveMoviesView topFiveView;
	
	/**
	 * Cineplex where the Admin is in
	 */
	private Cineplex adminCineplex;
	
	/**
	 * Object which interacts with Json files to get booking related data
	 */
	private BookingDAO bookingDAO;
	
	/**
	 * Object which interacts with Json files to get movie related data
	 */
	private MovieDAO movieDAO;
	
	/**
	 * Creates a new AdminTopFiveMoviesMgr Object which initializes associated View (topFiveView) and the cineplex
	 * @param cineplex	Cineplex where admin is in
	 */
	public AdminTopFiveMoviesMgr(Cineplex cineplex){
		if(this.topFiveView == null){
			topFiveView = new AdminTopFiveMoviesView(this);
		}
		this.adminCineplex = cineplex;
		this.bookingDAO = new BookingDAO();
		this.movieDAO = new MovieDAO();
	}
	
	/** 
	 * Get all movies and sort them in descending order by ticket sales
	 * @return	Array of Five Movies, sorted by ticket sales
	 */
	private Movie[] getTopFiveMoviesByTicketSales(){
		Movie[] topFiveMovies;
		
		ArrayList<Movie> allMovies = movieDAO.getAllMoviesWithReviewsByCineplex(this.adminCineplex.getName(),true);
		TicketSalesComparable[] ticketSales = new TicketSalesComparable[allMovies.size()];
		
		topFiveMovies = new Movie[allMovies.size()];
		for(int i = 0; i < allMovies.size(); i++){
			double totalTicketSales = getTotalTicketSalesForOneMovie(allMovies.get(i));
			ticketSales[i] = new TicketSalesComparable(allMovies.get(i), totalTicketSales);
		}
		sortDesc(ticketSales);
		int counter = 0;
		for(int i = 0; i < ticketSales.length; i++){
			if(counter == 5){
				break;
			}
			topFiveMovies[i] = ticketSales[i].getMovie();
		}
		return Arrays.copyOfRange(topFiveMovies, 0, 5);
	}
	
	/**
	 * Calls Data access object (DAO) to get ticket sales for one movie and
	 * Calculate the total sales
	 * @param movie	Movie which needs to get the total sales
	 * @return		Total ticket sales of the movie
	 */
	public double getTotalTicketSalesForOneMovie(Movie movie){
		double totalTicketSales = 0.0;
		ArrayList<Booking> allBookingsForOneMovie = bookingDAO.getBookingsByMovieID(movie.getId());
		for(int i = 0; i < allBookingsForOneMovie.size(); i++){
			totalTicketSales += allBookingsForOneMovie.get(i).getTotalPrice();
		}
		return totalTicketSales;
	}
	
	/**
	 * Get all movies and sort them in descending order by Reviewers' ratings
	 * @return	Array of five movies sorted by ratings
	 */
	private Movie[] getTopFiveMoviesByReviewerRatings(){
		ArrayList<Movie> allMovies = movieDAO.getAllMoviesWithReviewsByCineplex(this.adminCineplex.getName(), true);
		Movie[] res = new Movie[allMovies.size()];
		
		for(int i = 0; i < allMovies.size(); i++){
			res[i] = allMovies.get(i);
		}
		sortDesc(res);
		return Arrays.copyOfRange(res, 0, 5);
	}	
	
	/**
	 * Sort Array of comparable object by descending order (using Insertion sort)
	 * @param list	Array of comparable objects
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void sortDesc(Comparable[] list){
		for(int i = 1; i < list.length; i++){
			Comparable key = list[i];
			int position = i;
			while(position > 0 && key.compareTo(list[position-1]) > 0){
				list[position] = list[position-1];
				position--;
			}
			list[position] = key;
		}
	}
	
	/**
	 * Get admin's choice to display sorted movies by ticket sales or reviewers' ratings.
	 * Calls topFiveView to display the sorted movies to the user.
	 * @param choice User's choice to display movies by ticket sales (1) or reviewers' ratings (2) or quit (3)
	 */
	public void displayMoviesByChoice(int choice){
		Movie[] movieArray;
		switch(choice){
		case 1:
			// GetMoviesByTicketSales
			movieArray = getTopFiveMoviesByTicketSales();
			this.topFiveView.displayMoviesList(movieArray, true);
			break;
		case 2:
			//GEtMoviesByReviewer'sRating
			movieArray = getTopFiveMoviesByReviewerRatings();
			this.topFiveView.displayMoviesList(movieArray, false);
			break;
		case 3:
			break;
		}
	}
	
	/**
	 * Calls topFiveView to display menu for top five movies listing
	 */
	public void displayTopFiveMoviesMenu(){
		this.topFiveView.displayTopFiveMovies();
	}
}
