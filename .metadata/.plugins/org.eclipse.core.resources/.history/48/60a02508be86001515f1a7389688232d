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
		
	public Movie movieDetails(int movieChoice){
		if(movDAO == null){
			movDAO = new MovieDAO();
		}
		movieList=movDAO.getAllMovieswithReviews(true);
		dMovie=movDAO.getMovieByid(movieList.get(movieChoice-1).getId());
		return dMovie;
	}
		
	public ArrayList<String> getMovieCasts(){
		for(int i=0;i<dMovie.getCasts().size();i++){
			moviesCasts.add(dMovie.getCasts().get(i));
		}
		return moviesReviews;
	}
	
	public ArrayList<String> getMovieReviews(){
		for(int i=0;i<dMovie.getReviews().size();i++){
			moviesReviews.add(dMovie.getReviews().get(i).getReview()+"   - By "+dMovie.getReviews().get(i).getGuestName());
		}
		return moviesReviews;
	}
	
	public int getAvgMovieRating(){
		rating=0;
		for(int i=0;i<dMovie.getReviews().size();i++){
			rating+=dMovie.getReviews().get(i).getRating();
		}
		return rating/dMovie.getReviews().size();
	}
}
