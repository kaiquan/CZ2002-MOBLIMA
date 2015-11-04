package controller;

import java.util.ArrayList;
import java.util.Arrays;

import dao_module.MovieDAO;
import model.AGELIMIT;
import model.Cineplex;
import model.MOVIESTATUS;
import model.MOVIETYPE;
import view.AdminView;
import view.ListMovieView;
import model.Movie;
import model.Review;

public class AdminMgr {
	private String username;
	private Cineplex mCineplex;
	
	private AdminView mAdminView;
	private MovieDAO movieDao;
	
	
	
	public AdminMgr(String username, Cineplex mCineplex) {
		this.username = username;
		this.mCineplex = mCineplex;
		if(mAdminView==null)
			mAdminView = new AdminView(username, mCineplex.getName(), this);
	}
	
	public AdminMgr(String username, Cineplex mCineplex, AdminView mAdminView) {
		this.username = username;
		this.mCineplex = mCineplex;
		this.mAdminView = mAdminView;
	}
	
	public void optionSelected(int choice){
		switch(choice){
		case 1:
			mAdminView.displayAddMovieForm();
			break;
		case 2:
			mAdminView.displayEditMovieMenu(mCineplex.getMovies());
			break;
		}
	}

	public void prepareAdminForm() {
		mAdminView.displayAdminOptions();
		
	}

	public void addMovie(String movieName, String movieType, String movieStatus, String synopsis, String director, String casts, int duration, String ageLimit) {
		
		MOVIETYPE mt = MOVIETYPE.valueOf(movieType.toUpperCase());
		MOVIESTATUS ms = MOVIESTATUS.valueOf(movieStatus.toUpperCase());
		AGELIMIT al = AGELIMIT.valueOf(ageLimit.toUpperCase());
		ArrayList<String> castList = new ArrayList<String>(Arrays.asList(casts.split(", ")));		
		ArrayList<Review> review = new ArrayList<Review>();
		
		
		Movie newMovie = new Movie(mCineplex, movieName, mt, ms, synopsis, director, castList, duration, al, review);
		movieDao = new MovieDAO();
		boolean success = movieDao.createNewMovie(newMovie);
		
		if(success) mAdminView.displayMovieAdded();		
		
	}
	
	
	/*public boolean changeMovieStatus(int movideIndex, int changeStatus){
		Movie selectedMovie = null;
		boolean changeSuccess = false;
		try{
			selectedMovie = mCineplex.getMovies().get(movideIndex);
			selectedMovie.setMovieStatus(MOVIESTATUS.values()[changeStatus]);	
			movieDao = new MovieDAO();
			movieDao.updateMovie(selectedMovie);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(selectedMovie.getMovieStatus() == MOVIESTATUS.values()[changeStatus])
				changeSuccess = true;				
		}
		
		return changeSuccess;
	
	}*/
	
	public boolean changeMovieDetails(int movideIndex, int key, String value){
		Movie selectedMovie = null;
		boolean changeSuccess = false;
		try{
			selectedMovie = mCineplex.getMovies().get(movideIndex);
			switch(key){
			case 1:
				selectedMovie.setTitle(value);
				break;
			case 2:
				selectedMovie.setDirector(value);
				break;
			case 3:
				selectedMovie.setShowDuration(Integer.parseInt(value));
				break;
			case 4:
				selectedMovie.setSynopsis(value);
				break;
			case 5:
				ArrayList<String> castList = new ArrayList<String>(Arrays.asList(value.split(", ")));
				selectedMovie.setCasts(castList);
				break;
			case 6:
				selectedMovie.setAgeLimit(AGELIMIT.valueOf(value));
				break;
			case 7:
				selectedMovie.setMovieStatus(MOVIESTATUS.valueOf(value));
				break;
			case 8:
				selectedMovie.setMovieType(MOVIETYPE.valueOf(value));
				break;
			}			
			movieDao = new MovieDAO();
			movieDao.updateMovie(selectedMovie);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				changeSuccess = true;				
		}
		
		return changeSuccess;
	
	}

	

}
