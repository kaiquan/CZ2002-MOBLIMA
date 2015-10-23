package controller;

import java.util.ArrayList;
import java.util.Arrays;

import dao_module.MovieDAO;
import model.AGELIMIT;
import model.Cineplex;
import model.MOVIESTATUS;
import model.MOVIETYPE;
import view.AdminView;
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

}
