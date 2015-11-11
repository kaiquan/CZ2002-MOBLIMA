package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import controller.AdminMgr;
import dao_module.PriceDAO;
import model.Cinema;
import model.MOVIESTATUS;
import model.Movie;
import model.Price;
import model.Showtime;

public class AdminView extends Aview{
	
	/**
	 * keep track of the username
	 */
	private String username;
	

	/**
	 * keep track of the admin's cineplex
	 */
	private String cineplex;
	
	/**
	 * Admin Manager to process user input
	 */
	private AdminMgr mAdminMgr;
	
	
	/**
	 * initialize the username, cineplex and admin manager
	 * @param username
	 * @param cineplex
	 * @param mAdminMgr
	 */
	public AdminView(String username, String cineplex, AdminMgr mAdminMgr) {
		this.username = username;
		this.cineplex = cineplex;
		this.mAdminMgr = mAdminMgr;
	}
	
	
	public AdminView() {
	}


	/**
	 * display admin menu
	 */
	public void displayAdminOptions(){
		try {
			System.out.println("Login Successful!");
			int choice;
			do{
				System.out.println("\nPress <Enter> to continue to admin main menu...");
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("Welcome "+username+", you are editing cineplex: "+cineplex);
				System.out.println("1. Add New Movie");
				System.out.println("2. Edit Movie Details");
				System.out.println("3. Edit Movie Status");
				System.out.println("4. Schedule Show Time");
				System.out.println("5. Edit hoildays");
				System.out.println("6. Edit pricings");
				System.out.println("7. List top 5 ranking movies");
				System.out.println("8. Logout");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();
				
					mAdminMgr.optionSelected(choice);
	
			}while(choice >0&&choice!=8);
		
		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());	
		}

	}
	
	/**
	 * display Movie added successful message and display admin menu
	 */
	public void displayMovieAdded(){
		System.out.println("Movie Added Successfully!!!");
		displayAdminOptions();
	}

	/**
	 * Display add movie form
	 */
	public void displayAddMovieForm() {
		try{	
			System.out.println("Please enter the following");
	
			System.out.print("Movie Name: ");
			String movieName = sc.nextLine();
			
			System.out.print("Movie type (THREE_DIMENATION, TWO_DIMENATION, HD): ");
			String movieType = sc.nextLine();
			
			
			System.out.print("Moviestatus (NOW_SHOWING, COMING_SOON, PREVIEW, END_OF_SHOWING): ");
			String movieStatus = sc.nextLine();
			
			System.out.print("Synopsis: ");
			String synopsis = sc.nextLine();
			
			System.out.print("Director: ");
			String director = sc.nextLine();
			
			System.out.print("Age limit (G, PG13, M16, R21): ");
			String ageLimit = sc.next();
			
			System.out.print("casts: ");
			String casts = sc.nextLine();
			
			System.out.print("show duration: ");
			int duration = sc.nextInt();
			
			mAdminMgr.addMovie(movieName, movieType, movieStatus, synopsis, director, casts, duration, ageLimit);
		}catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());	
		}
		
	}

	/**
	 * Display an menu/form to edit Movie Details
	 * @param movies - arraylist of all the movies
	 */
	public void displayEditMovieMenu(ArrayList<Movie> movies){
		try{
			
			System.out.println("1. Show all movie listings except End of Showing");
			System.out.println("2. Show all movie listings including End of Showing");
			System.out.print("Choice: ");
			int choice = sc.nextInt();  
			
			if(choice == 2){
				for(int i = 0; i < movies.size(); i++){	
					if(movies.get(i) != null && movies.get(i).getMovieStatus() != MOVIESTATUS.END_OF_SHOWING){
						System.out.println("Index: " + (i+1));
						movieDetail(movies, i);
					}
				}
			}
			else{
				for(int i = 0; i < movies.size(); i++){	
					if(movies.get(i) != null){
						System.out.println("Index: " + (i+1));
						movieDetail(movies, i);
					}
				}
			}
	
			
			System.out.print("Enter index of movie to edit: ");
			int movieIndex = sc.nextInt();
			boolean continueEdit=false;
			System.out.println("You Selected Movie: "+(movieIndex));
			do{		
				movieDetail(movies, movieIndex-1);
				System.out.print("Enter index field to edit:");
				int keyIndex = sc.nextInt();
				
				if(keyIndex == 6)
					System.out.print("Change Age Limit to?(G, PG13, M16, R21): ");
				else if(keyIndex == 7)
					System.out.print("Change movie status to?(NOW_SHOWING, COMING_SOON, PREVIEW, END_OF_SHOWING): ");
				else if(keyIndex == 8)
					System.out.print("Change movie type to?(THREE_DIMENATION, TWO_DIMENATION, HD): ");
				else
					System.out.print("Change field to?: ");		
				String value = sc.nextLine();
						
				boolean editSuccessful = mAdminMgr.changeMovieDetails(movieIndex-1, keyIndex, value);
				
				if(editSuccessful){
					System.out.println("Movie Status change successfully!");
					movieDetail(movies, movieIndex-1);
				}else System.out.println("Movie Status change unsuccessfully");
				
				System.out.print("Continue editing the movie?(true or false): ");
				continueEdit = sc.nextBoolean();
				
			}while(continueEdit);
			
			boolean commitChangeSuccess = mAdminMgr.commitUpdateMovie(movieIndex-1);
			if(commitChangeSuccess){
				System.out.print("All changes made successful!");
				movieDetail(movies, movieIndex-1);
			}
			else{
				System.out.print("All changes made unsuccessful!");
			}
		}catch(Exception e){
			System.out.println("Error occured: " + e.getMessage());	
		}
				
	}
	
	/**
	 * Display the details a movie
	 * @param movies - arraylist of all the movies
	 * @param movieIndex - index of a particular movie
	 */
	private void movieDetail(ArrayList<Movie> movies, int movieIndex){
		System.out.println("1. Movie Title\t\t: " + movies.get(movieIndex).getTitle());
		System.out.println("2. Directed by\t\t: " + movies.get(movieIndex).getDirector());
		System.out.println("3. Duration\t\t: " + movies.get(movieIndex).getShowDuration() + " mins");
		System.out.println("4. Synopsis\t\t: " + movies.get(movieIndex).getSynopsis());
		System.out.print("5. Casts\t\t\t: ");
		ArrayList<String> casts = movies.get(movieIndex).getCasts();
		for(int j = 0; j < casts.size(); j++){
			System.out.print(casts.get(j));
			if(j < casts.size()-1){
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.println("6. Age Limit\t\t: " + movies.get(movieIndex).getAgeLimit().toString());
		System.out.println("7. Movie Status\t\t: " + movies.get(movieIndex).getMovieStatus());
		System.out.println("8. Movie Type\t\t: " + movies.get(movieIndex).getMovieType().toString());
		System.out.println();
	}

	/**
	 * Display show time added successful message then display admin menu
	 */
	public void displayShowTimeAdded() {
		System.out.println("Showtime Added Successfully!!!");
		displayAdminOptions();
	}

	/**
	 * Display show time update successful message then display admin menu
	 */
	public void displayShowTimeUpdated() {
		System.out.println("Showtime Updated Successfully!!!");
		displayAdminOptions();
	}

	/**
	 * Display show time remove successful message then display admin menu
	 */
	public void displayShowTimeRemoved() {
		System.out.println("Showtime Removed Successfully!!!");
		displayAdminOptions();
	}

	/**
	 * Display a form to add show time
	 * @param movies - arraylist of all the movies
	 * @param cinemas - arraylist of all the cinemas
	 */
	public void displayAddShowTimeForm(ArrayList<Movie> movies, ArrayList<Cinema> cinemas) {
		System.out.println("Cinemas");
		for(int i=0; i<cinemas.size(); i++){
			System.out.println((i+1)+". Cinema "+cinemas.get(i).getCinemaCode());
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of cinema to add showtime for: ");
		int cinemaIndex = sc.nextInt();

		System.out.println("Movies");
		for(int i=0; i<movies.size(); i++){
			System.out.println((i+1)+". "+movies.get(i).getTitle()+"("+movies.get(i).getMovieType()+")");
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of movie to add showtime for: ");
		int movieIndex = sc.nextInt();

		System.out.println("Enter a showtime (YYYY-MM-DD-HH-mm): ");
		String showtimeString = sc.next();

		SimpleDateFormat showtimeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Date newShowTime = null;
		try {
			newShowTime = showtimeFormat.parse(showtimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mAdminMgr.addShowTime(cinemaIndex-1, movieIndex-1, newShowTime);
	}

	/**
	 * Display a menu/form to update showtime
	 * @param movies - arraylist of all the movies
	 */
	public void displayUpdateShowTimeMenu(ArrayList<Movie> movies) {
		System.out.println("Movies");
		for(int i=0; i<movies.size(); i++){
			System.out.println((i+1)+". "+movies.get(i).getTitle());
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of movie to update showtime for: ");
		int movieIndex = sc.nextInt();

		ArrayList<Showtime> listShowTimes = mAdminMgr.listShowTimes(movieIndex-1);

		SimpleDateFormat showtimeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

		for(int i=0; i<listShowTimes.size(); i++){
			System.out.println((i+1)+". "+showtimeFormat.format(listShowTimes.get(i).getDateTime()));
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of showtime to update: ");
		int showtimeIndex = sc.nextInt();
		String showtimeID="";
		for(int i=0; i<listShowTimes.size(); i++){
			if(showtimeIndex-1 == i) {
				showtimeID = listShowTimes.get(i).getId();
				break;
			}
		}

		System.out.println("Enter updated showtime (YYYY-MM-DD-HH-mm): ");
		String showtimeString = sc.next();

		showtimeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Date updatedShowTime = null;
		try {
			updatedShowTime = showtimeFormat.parse(showtimeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mAdminMgr.updateShowTime(showtimeID, updatedShowTime);
	}

	/**
	 * Display a menu/form to remove a showtime
	 * @param movies - arraylist of all the movies
	 */
	public void displayRemoveShowTimeMenu(ArrayList<Movie> movies) {
		System.out.println("Movies");
		for(int i=0; i<movies.size(); i++){
			System.out.println((i+1)+". "+movies.get(i).getTitle());
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of movie to remove showtime for: ");
		int movieIndex = sc.nextInt();

		ArrayList<Showtime> listShowTimes = mAdminMgr.listShowTimes(movieIndex-1);

		SimpleDateFormat showtimeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

		System.out.println("Showtimes");
		for(int i=0; i<listShowTimes.size(); i++){
			System.out.println((i+1)+". "+showtimeFormat.format(listShowTimes.get(i).getDateTime()));
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of showtime to remove: ");
		int showtimeIndex = sc.nextInt();
		String showtimeID="";
		for(int i=0; i<listShowTimes.size(); i++){
			if(showtimeIndex-1 == i) {
				showtimeID= listShowTimes.get(i).getId();
				break;
			}
		}

		mAdminMgr.removeShowTime(showtimeID);
	}

	/**
	 * Display holiday added successful message then display admin menu
	 */
	public void displayHolidayAdded() {
		System.out.println("Public Holiday Added Successfully!!!");
		displayAdminOptions();
	}

	/**
	 * Display holiday removed successful message then display admin menu
	 */
	public void displayHolidayRemoved() {
		System.out.println("Public Holiday Removed Successfully!!!");
		displayAdminOptions();
	}

	/**
	 * Display a form to add a new holiday date
	 */
	public void displayAddHolidayForm() {
		System.out.print("Enter a new public holiday to add (yyyy-MM-dd): ");
		String newHolidayString = sc.next();

		SimpleDateFormat holidayFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date newHoliday = null;
		try {
			newHoliday = holidayFormat.parse(newHolidayString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mAdminMgr.addHoliday(newHoliday);
	}

	/**
	 * display a menu/form to remove an holiday date
	 */
	public void displayRemoveHolidayMenu() {
		ArrayList<Date> listPublicHolidays = mAdminMgr.listHolidays();
		SimpleDateFormat holidayFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("List of Public Holidays");
		for(int i=0; i<listPublicHolidays.size(); i++){
			System.out.println((i+1)+". "+holidayFormat.format(listPublicHolidays.get(i)));
			System.out.println();
		}
		System.out.println("---------------------------------------------");
		System.out.print("Enter index of public holiday to remove: ");
		int holidayIndex = sc.nextInt();
		Date selectedHoliday=null;
		for(int i=0; i<listPublicHolidays.size(); i++){
			if(holidayIndex-1 == i) {
				selectedHoliday = listPublicHolidays.get(i);
				break;
			}
		}

		mAdminMgr.removeHoliday(selectedHoliday);
	}
	
	/**
	 * display an menu/form to edit ticket pricings
	 */
	public void displayEditPricings(){
		int choice=0;
		System.out.println("Select which prices to edit.");
		System.out.println("1. Platinum cinema prices");
		System.out.println("2. Student prices");
		System.out.println("3. Senior citizens prices");
		System.out.println("4. Regular prices");
		System.out.println("5. Exit and head back");
		choice = new Scanner(System.in).nextInt();
		
		new AdminMgr().editPrice(choice);
		
	}
}
