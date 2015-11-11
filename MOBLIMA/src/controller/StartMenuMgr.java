package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao_module.CineplexDAO;
import dao_module.MovieDAO;
import model.Movie;
import view.AdminTopFiveMoviesView;
import view.ListMovieView;
import view.MovieDetailsView;
import view.SelectSeatView;

public class StartMenuMgr {

	
	public void showMainOptions(){
		
		
		System.out.println("Please select an option");
		System.out.println("1. List movies");
		System.out.println("2. View movie details - including reviews and ratings");
		System.out.println("3. Book and purchase tickets");
		System.out.println("4. View booking history");
		System.out.println("5. Admin Login");
		System.out.println("6. Exit");

		System.out.print("Choice: ");
	}

	public void processOptions(int choice){
		switch(choice)
		{
		case 1:
			new ListMovieView().listMovie();
			break;
		case 2:
			MovieDetailsView movieDetailsView = new MovieDetailsView();
			ArrayList<String> moviesString= new ArrayList<String>();
			ArrayList<Movie> movies =new MovieDAO().getAllMovieswithReviews(true);
			for(int i=0;i<movies.size();i++){
				moviesString.add(movies.get(i).getTitle()+"("+movies.get(i).getMovieType()+") , Showing at :"+movies.get(i).getCineplexName());
			}
			movieDetailsView.listMovie(moviesString);
			movieDetailsView.listMovieDetails();

			break;
		case 3:
			//select the movie?
			SelectSeatView selectSeatView = new SelectSeatView();
			CineplexDAO cDAO = new CineplexDAO();
			MovieDAO movDAO = new MovieDAO();
			ArrayList<String> cineplexes = new ArrayList<String>();
			ArrayList<String> movieList = new ArrayList<String>();
			
			for(int i=0; i<cDAO.getAllCineplex().size(); i++){
				cineplexes.add(cDAO.getAllCineplex().get(i).getName());
			}
			for(int i=0; i<movDAO.getAllMovieswithReviews(true).size(); i++){
				movieList.add(movDAO.getAllMovieswithReviews(true).get(i).getTitle());
			}
			selectSeatView.listMovie(movieList, cineplexes);
			break;
		case 4:
			BookingMgr bookingMgr =  new BookingMgr();
			bookingMgr.checkBookingHistory();
			break;
		case 5:
			AdminLoginMgr mAdminLoginMgr = new AdminLoginMgr();	
			try {
				mAdminLoginMgr.prepareloginForm();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}

}
