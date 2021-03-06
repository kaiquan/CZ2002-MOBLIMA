package controller;

import java.util.ArrayList;

import dao_module.MovieDAO;
import model.Movie;
import view.MovieDetailsView;;

public class MovieDetailsMgr {
	private MovieDetailsView mMovieDetailsView;
	private MovieDAO movDAO;
	private Movie dMovie;
	ArrayList<String> moviesReviews = new ArrayList<String>();
	ArrayList<String> moviesCasts = new ArrayList<String>();
	ArrayList<Movie> movieList = new ArrayList<Movie>();
	private int rating;
	
	public MovieDetailsMgr(MovieDetailsView movieDetailsView){
		this.mMovieDetailsView=movieDetailsView;
	}
		
	/**
	 * Get the specific movie's details that chosen by the user
	 * @param movieChoice	The movie selected by the user
	 * @return dMovie		An Movie object that contain all the details of the movie
	 */
	public Movie movieDetails(int movieChoice){
		if(movDAO == null){
			movDAO = new MovieDAO();
		}
		movieList=movDAO.getAllMovieswithReviews(true);
		dMovie=movDAO.getMovieByid(movieList.get(movieChoice-1).getId());
		System.out.println(dMovie.getCasts().get(0));
		return dMovie;
	}
		
	/**
	 * Get the casts of the selected movie
	 * @return moviesCasts	A string array that contain all the cast of the selected movie
	 */
	public ArrayList<String> getMovieCasts(){
		for(int i=0;i<dMovie.getCasts().size();i++){
			moviesCasts.add(dMovie.getCasts().get(i));
		}
		return moviesCasts;
	}
	
	/**
	 * Get the reviews of the selected movie
	 * @return movieReviews	A string array that contain all the reviews for the selected movie 
	 */
	public ArrayList<String> getMovieReviews(){
		for(int i=0;i<dMovie.getReviews().size();i++){
			moviesReviews.add(dMovie.getReviews().get(i).getReview()+"   - By "+dMovie.getReviews().get(i).getGuestName());
		}
		return moviesReviews;
	}
	
	/**
	 * Get the average of the movie rating
	 * @return	rating		The average rating of the movie
	 */
	public int getAvgMovieRating(){
		rating=0;
		for(int i=0;i<dMovie.getReviews().size();i++){
			rating+=dMovie.getReviews().get(i).getRating();
		}
		rating =rating/dMovie.getReviews().size();
		return rating;
	}
// ------------------------Verify User input-----------------------------------------//
	/**
	 * Verify the user input based on:
	 * 1. options limit (cannot give a number more than the given options and less than 1)
	 * 2. invalid input (non-meaning characters or symbol)
	 * @param 	seatNumber		The seat number that given by the user
	 */
	public boolean verifyInput(int limit, String userInput) {
		String regex = "[0-9]+";
		int input;
		if (!userInput.matches(regex)) {
			return false;
		} else {
			input = Integer.parseInt(userInput);
		}
		if (input <= 0 || input > limit) {
			return false;
		}
		return true;
	}
}
