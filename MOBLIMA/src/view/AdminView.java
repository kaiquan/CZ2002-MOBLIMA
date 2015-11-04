package view;

import java.io.IOException;
import java.util.ArrayList;

import controller.AdminMgr;
import model.MOVIESTATUS;
import model.Movie;

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
		System.out.println("Login Successful!");
		int choice;
		do{
			System.out.println("\nPress <Enter> to continue to admin main menu...");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Welcome "+username+", you are editing cineplex: "+cineplex);
			System.out.println("1. Add New Movie");
			System.out.println("2. Edit Movie Details");
			//System.out.println("3. Edit Movie Status");
			//System.out.println("3. Schedule Show Time");
			//System.out.println("4. Logout");
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();
			mAdminMgr.optionSelected(choice);
		}while(choice >0);

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
	
	/*public void displayMovieStatusMenu(ArrayList<Movie> movies){
		
		System.out.println("1. Show all movie listings except End of Showing");
		System.out.println("2. Show all movie listings including End of Showing");
		System.out.print("Choice: ");
		int choice = sc.nextInt();
		
		if(choice == 1){
			for(int i=0; i<movies.size(); i++){
				if(movies.get(i).getMovieStatus() != MOVIESTATUS.END_OF_SHOWING)
				{
					System.out.println((i+1)+". "+movies.get(i).getTitle());
					System.out.println("Movie Status: "+movies.get(i).getMovieStatus());
					System.out.println("");
				}

			}
			System.out.println("---------------------------------------------");
		}else if(choice ==2){
			for(int i=0; i<movies.size(); i++){
				System.out.println((i+1)+". "+movies.get(i).getTitle());
				System.out.println("Movie Status: "+movies.get(i).getMovieStatus());
				System.out.println("");
			}
			System.out.println("---------------------------------------------");
		}
		System.out.print("Enter index of movie to edit: ");
		int movieIndex = sc.nextInt();
		System.out.println("You Selected Movie: "+(movieIndex)+". "+movies.get(movieIndex-1).getTitle());
		System.out.print(" Change movie status to?(1.NOW_SHOWING, 2.COMING_SOON, 3.PREVIEW, 4.END_OF_SHOWING): ");
		int movieStatusIndex = sc.nextInt();
		
		boolean editSuccessful = mAdminMgr.changeMovieStatus(movieIndex-1, movieStatusIndex-1);
		
		if(editSuccessful){
			System.out.println("Movie Status change successfully!");
			System.out.println("Movie details below:");
			System.out.println((movieIndex)+". "+movies.get(movieIndex-1).getTitle());
			System.out.println("Movie Status: "+movies.get(movieIndex-1).getMovieStatus());	
		}else{
			System.out.println("Movie Status change unsuccessfully");
		}
			
	}*/
	
	public void displayEditMovieMenu(ArrayList<Movie> movies){
		
		for(int i = 0; i < movies.size(); i++){
			if(movies.get(i) != null){
				movieDetail(movies, i);
			}
		}
		
		System.out.print("Enter index of movie to edit: ");
		int movieIndex = sc.nextInt();
		boolean continueEdit=false;
		
		do{
			System.out.println("You Selected Movie: "+(movieIndex));
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
			String value = sc.next();
					
			boolean editSuccessful = mAdminMgr.changeMovieDetails(movieIndex-1, keyIndex, value);
			
			if(editSuccessful){
				System.out.println("Movie Status change successfully!");
				movieDetail(movies, movieIndex-1);
			}else System.out.println("Movie Status change unsuccessfully");
			
			System.out.print("Continue editing the movie?: ");
			continueEdit = sc.nextBoolean();
			
		}while(continueEdit);
	}
	
	private void movieDetail(ArrayList<Movie> movies, int movieIndex){
		System.out.println("1. Movie Title\t\t: " + movies.get(movieIndex).getTitle());
		System.out.println("2. Directed by\t\t: " + movies.get(movieIndex).getDirector());
		System.out.println("3. Duration\t\t: " + movies.get(movieIndex).getShowDuration() + " mins");
		System.out.println("4. Synopsis\t\t: " + movies.get(movieIndex).getSynopsis());
		System.out.print("5. Casts\t\t\t: ");
		ArrayList<String> casts = movies.get(movieIndex-1).getCasts();
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



}
