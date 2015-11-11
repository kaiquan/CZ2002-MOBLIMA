package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import dao_module.HoildayDAO;
import dao_module.MovieDAO;
import dao_module.PriceDAO;
import dao_module.ShowTimeDAO;
import model.AGELIMIT;
import model.Cinema;
import model.Cineplex;
import model.MOVIESTATUS;
import model.MOVIETYPE;
import view.AdminTopFiveMoviesView;
import view.AdminView;
import view.ListMovieView;
import model.Movie;
import model.Price;
import model.Review;
import model.Showtime;

public class AdminMgr {
	
	/**
	 * Admin username
	 */
	private String username;
	
	/**
	 * Cineplex of the admin
	 */
	private Cineplex mCineplex;
	
	/**
	 * Admin view for display
	 */
	private AdminView mAdminView;
	

	/**
	 * Movie Data access object to access stored movie information
	 */
	private MovieDAO movieDao;
	
	/**
	 * ShowTime Data access object to access stored Showtime information
	 */
	private ShowTimeDAO showtimeDAO;
	
	/**
	 * Holiday Data access object to access stored Holiday information
	 */
	private HoildayDAO holidayDAO;
	

	/**
	 * initalize the username and cineplex of the admin after admin logined
	 * @param username
	 * @param mCineplex
	 */
	public AdminMgr(String username, Cineplex mCineplex) {
		this.username = username;
		this.mCineplex = mCineplex;
		if(mAdminView==null)
			mAdminView = new AdminView(username, mCineplex.getName(), this);
	}
	
	/**
	 * initalize the username and cineplex of the admin after admin logined
	 * @param username
	 * @param mCineplex
	 * @param mAdminView
	 */
	public AdminMgr(String username, Cineplex mCineplex, AdminView mAdminView) {
		this.username = username;
		this.mCineplex = mCineplex;
		this.mAdminView = mAdminView;
	}
	
	public AdminMgr() {
	}

	/**
	 * process admin menu selection depend on user choice
	 * @param choice
	 */
	public void optionSelected(int choice) throws Exception{
		switch(choice){
		case 1:
			mAdminView.displayAddMovieForm();
			break;
		case 2:
			mAdminView.displayEditMovieMenu(mCineplex.getMovies());
			break;
		case 3:
			//edit ovie status??
			mAdminView.displayEditMovieMenu(mCineplex.getMovies());
			break;
		case 4:
			mAdminView.displayAddShowTimeForm(this.mCineplex.getMovies(), this.mCineplex.getCinemas());
			break;
		case 5:
			int c;
			do{
				System.out.println("Select 1 to add a new hoilday, 2 to remove an existing hoilday");
				c=new Scanner(System.in).nextInt();
				if(c==1)
					mAdminView.displayAddHolidayForm();
				else
					mAdminView.displayRemoveHolidayMenu();
			}while(c!=1||c!=2);
			
			break;
		case 6:
			//edit pricing
			new AdminView().displayEditPricings();
			break;
		case 7:
			AdminTopFiveMoviesView topFiveMovieView = new AdminTopFiveMoviesView(new AdminTopFiveMoviesMgr(this.mCineplex));
			topFiveMovieView.displayTopFiveMovies();
			break;
			
		}
	}

	public void prepareAdminMenu(){
		mAdminView.displayAdminOptions();
		
	}

	/**
	 * Add movie to movie json file
	 * @param movieName - name of movie
	 * @param movieType - Options: THREE_DIMENATION, TWO_DIMENATION or HD
	 * @param movieStatus - Options: NOW_SHOWING, COMING_SOON, PREVIEW or END_OF_SHOWING
	 * @param synopsis - brief description of movie
	 * @param director - director of the movie
	 * @param casts - cast of the movie
	 * @param duration - minutes
	 * @param ageLimit - Options: G, PG13, M16 or R21
	 */
	public void addMovie(String movieName, String movieType, String movieStatus, String synopsis, String director, String casts, int duration, String ageLimit) throws Exception{
		
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
	
	
	/**
	 * Change a Movie detail depend on the selected key
	 * @param movideIndex - index of the movie
	 * @param key - index of detail to change
	 * @param value - replace the detail with value
	 * @return
	 */
	public boolean changeMovieDetails(int movideIndex, int key, String value)  throws Exception{
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
		}catch(Exception e){
			e.getMessage();
			
		}finally{
				changeSuccess = true;				
		}
		
		return changeSuccess;
	
	}
	
	/**
	 * Update the movie changes in movie json file
	 * @param movideIndex - index of the movie
	 * @return
	 */
	public boolean commitUpdateMovie(int movideIndex) throws Exception{
		boolean changeSuccess = false;
		try{
			Movie selectedMovie = mCineplex.getMovies().get(movideIndex);
			movieDao = new MovieDAO();
			movieDao.updateMovie(selectedMovie);
		}catch(Exception e){
			e.getMessage();
		}finally{
			changeSuccess = true;
		}
		
		return changeSuccess;
	}

	/**
	 * Schedule a new movie showtime
	 * @param cinemaIndex - index of cinema
	 * @param movieIndex - index of movie
	 * @param showtime - date/time of showtime
	 */
	public void addShowTime(int cinemaIndex, int movieIndex, Date showtime) {
		Cinema selectedCinema = mCineplex.getCinemas().get(cinemaIndex);
		Movie selectedMovie = mCineplex.getMovies().get(movieIndex);
		
		Showtime newShowTime = new Showtime(showtime, selectedMovie, selectedCinema);

		showtimeDAO = new ShowTimeDAO();
		boolean success = showtimeDAO.createNewShowTime(newShowTime);

		if(success) mAdminView.displayShowTimeAdded();	
	}

	/**
	 * list all show times
	 * @param movieIndex - index of movie
	 * @return an arraylist of the showtimes
	 */
	public ArrayList<Showtime> listShowTimes(int movieIndex) {
		Movie selectedMovie = mCineplex.getMovies().get(movieIndex);
		ArrayList<Showtime> showTimesSelectedMovie = new ArrayList<Showtime>();

		showtimeDAO = new ShowTimeDAO();
		showTimesSelectedMovie = showtimeDAO.getShowTimesByCineplexAndMovie(mCineplex, selectedMovie);

		return showTimesSelectedMovie;
	}

	/**
	 * Change Showtime date/time
	 * @param showtimeID - ID of showtime
	 * @param updatedShowTime - new values of showtime
	 */
	public void updateShowTime(String showtimeID, Date updatedShowTime) {
		Showtime selectedShowTime = null;

		showtimeDAO = new ShowTimeDAO();
		selectedShowTime = showtimeDAO.getShowTimeByID(showtimeID);

		selectedShowTime.setDateTime(updatedShowTime);

		boolean success = showtimeDAO.updateShowTime(selectedShowTime);

		if(success) mAdminView.displayShowTimeUpdated();
	}

	/**
	 * Remove Showtime
	 * @param showtimeID - ID of the showtime
	 */
	public void removeShowTime(String showtimeID) {
		Showtime selectedShowTime = null;

		showtimeDAO = new ShowTimeDAO();
		selectedShowTime = showtimeDAO.getShowTimeByID(showtimeID);
		boolean success = showtimeDAO.removeShowTime(selectedShowTime);

		if(success) mAdminView.displayShowTimeRemoved();
	}

	/**
	 * List the Holidays
	 * @return an Arraylist of Dates
	 */
	public ArrayList<Date> listHolidays() {
		ArrayList<Date> listPublicHolidays = new ArrayList<Date>();

		holidayDAO = new HoildayDAO();
		listPublicHolidays = holidayDAO.getAllHoildays();

		return listPublicHolidays;
	}

	/**
	 * Add a new Holiday
	 * @param newHoliday - data/time of the holiday to add
	 */
	public void addHoliday(Date newHoliday) {
		holidayDAO = new HoildayDAO();
		boolean success = holidayDAO.addHoilday(newHoliday);

		if(success) mAdminView.displayHolidayAdded();
	}

	/**
	 * remove a holiday
	 * @param selectedHoliday - date/time of date to remove
	 */
	public void removeHoliday(Date selectedHoliday) {
		holidayDAO = new HoildayDAO();
		boolean success = holidayDAO.removeHoilday(selectedHoliday);

		if(success) mAdminView.displayHolidayRemoved();
	}
	
	/**
	 * Edit the pricing of the tickets
	 * @param category
	 */
	@SuppressWarnings("resource")
	public void editPrice(int category){
		Price ticketPricing = new Price();
		ticketPricing = new PriceDAO().getTickestPrices();
		
		if(category==1){
			System.out.println("Current Platinumcinema price is :$"+ticketPricing.getPlantiumCinemaPrice());
			System.out.println("Enter the new value :");
			ticketPricing.setPlantiumCinemaPrice(new Scanner(System.in).nextDouble());
		}
		else if(category==2){
			//student
			int day=-1;
			int timing=-1;
			System.out.println("Select which day would you like to edit(1-7):");
			do{
				day=new Scanner(System.in).nextInt();
			}while(day<1&&day>7);
			System.out.println("Enter '1' to edit pricing before 6pm, '2' for after 6pm:");
			do{
				timing=new Scanner(System.in).nextInt();
			}while(timing<1&&timing>2);
			
			System.out.println("Current student price selected is :$"+ticketPricing.getPricings().get(0).get(day-1).get(timing-1));
			System.out.println("Enter the new value :");
			ticketPricing.getPricings().get(0).get(day-1).set(timing-1, new Scanner(System.in).nextDouble());
		}
		else if(category==3){
			//senior
			int day=-1;
			int timing=-1;
			System.out.println("Select which day would you like to edit(1-7):");
			do{
				day=new Scanner(System.in).nextInt();
			}while(day<1&&day>7);
			System.out.println("Enter '1' to edit pricing before 6pm, '2' for after 6pm:");
			do{
				timing=new Scanner(System.in).nextInt();
			}while(timing<1&&timing>2);
			
			System.out.println("Current Senior citizen price selected is :$"+ticketPricing.getPricings().get(1).get(day-1).get(timing-1));
			System.out.println("Enter the new value :");
			ticketPricing.getPricings().get(1).get(day-1).set(timing-1, new Scanner(System.in).nextDouble());
		}
		else if(category==4){
			//regular
			int day=-1;
			int timing=-1;
			System.out.println("Select which day would you like to edit(1-7):");
			do{
				day=new Scanner(System.in).nextInt();
			}while(day<1&&day>7);
			System.out.println("Enter '1' to edit pricing before 6pm, '2' for after 6pm:");
			do{
				timing=new Scanner(System.in).nextInt();
			}while(timing<1&&timing>2);
			
			System.out.println("Current Regular citizen price selected is :$"+ticketPricing.getPricings().get(2).get(day-1).get(timing-1));
			System.out.println("Enter the new value :");
			ticketPricing.getPricings().get(2).get(day-1).set(timing-1, new Scanner(System.in).nextDouble());
		}
		
		new PriceDAO().updatePrice(ticketPricing);
		System.out.println("Prices hae been updated successfully.");
	}

}
