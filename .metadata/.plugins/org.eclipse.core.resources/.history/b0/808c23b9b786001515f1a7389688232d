package controller;

import java.util.ArrayList;

import dao_module.CineplexDAO;
import dao_module.MovieDAO;
import model.Cineplex;
import model.Movie;
import view.ListMovieView;;

public class ListMovieMgr {
	private ListMovieView mListMovieView;
	private MovieDAO movDAO;
	private ArrayList<Movie> mMovie;
	private CineplexDAO cDAO;
	ArrayList<Movie> movies = new ArrayList<Movie>();
	ArrayList<Cineplex>cineplex=new ArrayList<Cineplex>();
	
	public ListMovieMgr(ListMovieView listMovieView){
		this.mListMovieView=listMovieView;
	}
	
	public ArrayList<Movie> getCineplexMovie(int cpl){
		if(movDAO == null){
			movDAO = new MovieDAO();
		}
		if(cDAO == null){
			cDAO = new CineplexDAO();
		}
		cineplex=cDAO.getAllCineplex();
		mMovie = movDAO.getAllMoviesWithReviewsByCineplex(cineplex.get(cpl-1).getName(), true);
		if(mMovie!=null){
			for(int i=0;i<mMovie.size();i++){
				movies.add(mMovie.get(i));
				
			}
		}
		return movies;
	}
}
