/**
 * A View class which is responsible for interfacing with user to List Top 5 Movies Based on Ticket sales or Ratings
 * @author 	Freny Nelin
 * @version	1.0
 * @since	2015-11-10
 */

package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Movie;
import controller.AdminTopFiveMoviesMgr;

public class AdminTopFiveMoviesView {

	/**
	 * Controller object used as center logic to perform list top five movies
	 */
	private AdminTopFiveMoviesMgr topFiveMoviesMgr;
	
	/**
	 * Scanner object used for getting user inputs
	 */
	private Scanner s;

	/**
	 * Creates a new AdminTopFiveMoviesView Object which initializes the controller object (AdminTopFiveMoviesMgr)
	 * @param topFiveMoviesMgr	AdminTopFiveMoviesMgr Object
	 */
	public AdminTopFiveMoviesView(AdminTopFiveMoviesMgr topFiveMoviesMgr){
		System.out.println("\n*** Please be patient as this might take some time to load...***");
		this.topFiveMoviesMgr = topFiveMoviesMgr;
		this.s = new Scanner(System.in);
	}
	
	/**
	 * Display menu for user to choose listing of top five movies by ticket sales or reviewers' ratings
	 */
	public void displayTopFiveMovies(){
		int choice = -1;
		while(choice != 3){
			System.out.println("\n===================  TOP FIVE MOVIES ===================");
			System.out.println("Please select one of followings: ");
			System.out.println("1. List Top 5 Movies by ticket sales");
			System.out.println("2. List Top 5 Movies by reviewers' ratings");
			System.out.println("3. Back to Menu");
			System.out.print("Your choice: ");
			choice = s.nextInt();
			while(choice < 1 || choice > 3){
				System.out.print("Your choice is invalid. Please enter your choice within 1-3: ");
				choice = s.nextInt();
			}
			System.out.println();
			this.topFiveMoviesMgr.displayMoviesByChoice(choice);
		}
	}
	
	/**
	 * Display the details of sorted movie list ordered by ticket sales or reviewers' ratings in descending order
	 * @param movieArray		List of Top Five Movies (Array)
	 * @param showTicketSales	Boolean to indicate whether to show ticket sales (Only for ranking by ticket sales)
	 */
	public void displayMoviesList(Movie[] movieArray, boolean showTicketSales){
		if(showTicketSales){
			System.out.println("====================== RANKING BY TICKET SALES ========================");
		}
		else{
			System.out.println("======================== RANKING BY RATINGS ==========================");
		}
		for(int i = 0; i < movieArray.length; i++){
			if(movieArray[i] != null){
				System.out.println("Rank " + (i+1));
				System.out.println("Movie Title\t\t: " + movieArray[i].getTitle());
				System.out.println("Directed by\t\t: " + movieArray[i].getDirector());
				System.out.println("Duration\t\t: " + movieArray[i].getShowDuration() + " mins");
				System.out.println("Synopsis\t\t: " + movieArray[i].getSynopsis());
				System.out.print("Casts\t\t\t: ");
				ArrayList<String> casts = movieArray[i].getCasts();
				for(int j = 0; j < casts.size(); j++){
					System.out.print(casts.get(j));
					if(j < casts.size()-1){
						System.out.print(", ");
					}
				}
				System.out.println();
				System.out.println("Age Limit\t\t: " + movieArray[i].getAgeLimit().toString());
				System.out.println("Movie Status\t\t: " + movieArray[i].getMovieStatus());
				System.out.println("Movie Type\t\t: " + movieArray[i].getMovieType().toString());
				System.out.println("Overall Ratings\t\t: " + movieArray[i].getOverallRatings());
				if(showTicketSales){
					System.out.println("Total Ticket Sales\t: $ " + this.topFiveMoviesMgr.getTotalTicketSalesForOneMovie(movieArray[i]));
				}
				System.out.println();
			}
		}
	}
}
