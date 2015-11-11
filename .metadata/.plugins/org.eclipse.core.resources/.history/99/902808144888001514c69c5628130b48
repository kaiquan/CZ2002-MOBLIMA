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
	
	private AdminTopFiveMoviesView topFiveView;
	private Cineplex adminCineplex;
	
	public AdminTopFiveMoviesMgr(Cineplex cineplex){
		if(this.topFiveView == null){
			topFiveView = new AdminTopFiveMoviesView(this);
		}
		this.adminCineplex = cineplex;
	}
	
	private Movie[] getTopFiveMoviesByTicketSales(){
		Movie[] topFiveMovies = new Movie[5];
		MovieDAO md = new MovieDAO();
		
		ArrayList<Movie> allMovies = md.getAllMoviesWithReviewsByCineplex(this.adminCineplex.getName(),true);
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
		return topFiveMovies;
	}
	
	public double getTotalTicketSalesForOneMovie(Movie movie){
		BookingDAO bd = new BookingDAO();
		double totalTicketSales = 0.0;
		ArrayList<Booking> allBookingsForOneMovie = bd.getBookingsByMovieID(movie.getId());
		for(int i = 0; i < allBookingsForOneMovie.size(); i++){
			totalTicketSales += allBookingsForOneMovie.get(i).getTotalPrice();
		}
		return totalTicketSales;
	}
		
	private Movie[] getTopFiveMoviesByReviewerRatings(){
		MovieDAO md = new MovieDAO();
		ArrayList<Movie> allMovies = md.getAllMoviesWithReviewsByCineplex(this.adminCineplex.getName(), true);
		Movie[] res = new Movie[allMovies.size()];
		
		for(int i = 0; i < allMovies.size(); i++){
			res[i] = allMovies.get(i);
		}
		
		sortDesc(res);
		
		return Arrays.copyOfRange(res, 0, 4);
	}	
	
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
	
	public void displayTopFiveMoviesMenu(){
		this.topFiveView.displayTopFiveMovies();
	}
}
