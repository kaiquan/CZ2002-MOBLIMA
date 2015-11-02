package view;

import controller.AdminMgr;

public class AdminView extends Aview{
	
	private String username;
	private String cineplex;
	
	private AdminMgr mAdminMgr;
	
	public AdminView(String username, String cineplex, AdminMgr mAdminMgr) {
		this.username = username;
		this.cineplex = cineplex;
		this.mAdminMgr = mAdminMgr;
		
	}
	
	
	public void displayAdminOptions(){
		System.out.println("Welcome "+username+", you are editing cineplex: "+cineplex);
		System.out.println("1. Add New Movie");
		//System.out.println("2. Edit Movie");
		//System.out.println("3. Schedule Show Time");
		//System.out.println("4. Logout");
		System.out.print("Enter Choice: ");
		mAdminMgr.optionSelected(sc.nextInt());
	}
	
	public void displayMovieAdded(){
		System.out.println("Movie Added Successfully!!!");
		displayAdminOptions();
	}


	public void displayAddMovieForm() {
		System.out.println("Please enter the following");

		System.out.print("Movie Name: ");
		String movieName = sc.next();
		
		System.out.print("Movie type (THREE_DIMENATION, TWO_DIMENATION, HD): ");
		String movieType = sc.next();
		
		
		System.out.print("Moviestatus (NOW_SHOWING, COMING_SOON, PREVIEW, END_OF_SHOWING): ");
		String movieStatus = sc.next();
		
		System.out.print("Synopsis: ");
		String synopsis = sc.next();
		
		System.out.print("Director: ");
		String director = sc.next();
		
		System.out.print("Age limit (G, PG13, M16, R21): ");
		String ageLimit = sc.next();
		
		System.out.print("casts: ");
		String casts = sc.next();
		
		System.out.print("show duration: ");
		int duration = sc.nextInt();
		
		mAdminMgr.addMovie(movieName, movieType, movieStatus, synopsis, director, casts, duration, ageLimit);
		
	}

}
