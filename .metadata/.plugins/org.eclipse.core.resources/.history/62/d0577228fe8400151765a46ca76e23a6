package controller;

import java.util.ArrayList;

import dao_module.CineplexDAO;
import dao_module.MovieDAO;
import view.ListMovieView;
import view.MovieDetailsView;
import view.SelectSeatView;

public class MovieGoerMenuMgr {
	
	private ListMovieView mListMovieView;
	private MovieDetailsView mMovieDetailsView;
	private SelectSeatView mSelectSeatView;
	private CineplexDAO cDAO;
	private MovieDAO movDAO;
	ArrayList<String> cineplexes = new ArrayList<String>();
	ArrayList<String> movieList = new ArrayList<String>();
	
	public void processOptions(int choice){
		switch(choice)
		{
		// Search/List Movie
		case 1:
			if(mListMovieView == null){
				mListMovieView = new ListMovieView();
			}
			if(cDAO == null){
				cDAO = new CineplexDAO();
			}
			for(int i=0; i<cDAO.getAllCineplex().size(); i++){
				cineplexes.add(cDAO.getAllCineplex().get(i).getName());
			}
			mListMovieView.listMovie(cineplexes);
			break;
		// View Movie Details
		case 2:
			if(mMovieDetailsView == null){
				mMovieDetailsView = new MovieDetailsView();
			}
			if(movDAO == null){
				movDAO = new MovieDAO();
			}
			for(int i=0; i<movDAO.getAllMovieswithReviews(true).size(); i++){
				movieList.add(movDAO.getAllMovieswithReviews(true).get(i).getTitle());
			}
			mMovieDetailsView.listMovie(movieList);
			break;
		// Check seat availability and selection of seat/s
		case 3:
			if(mSelectSeatView==null){
				mSelectSeatView = new SelectSeatView();
			}
			if(cDAO == null){
				cDAO = new CineplexDAO();
			}
			for(int i=0; i<cDAO.getAllCineplex().size(); i++){
				cineplexes.add(cDAO.getAllCineplex().get(i).getName());
			}
			if(movDAO == null){
				movDAO = new MovieDAO();
			}
			for(int i=0; i<movDAO.getAllMovieswithReviews(true).size(); i++){
				movieList.add(movDAO.getAllMovieswithReviews(true).get(i).getTitle());
			}
			mSelectSeatView.listMovie(movieList, cineplexes);
			break;
		}
		
	}
}
