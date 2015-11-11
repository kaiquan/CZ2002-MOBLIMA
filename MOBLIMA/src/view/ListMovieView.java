package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ListMovieMgr;
import dao_module.CineplexDAO;
import model.Cineplex;
import model.Movie;


public class ListMovieView {
	private Scanner sc;
	private ListMovieMgr mlistMovieMgr;
	ArrayList<String> cineplexes = new ArrayList<String>();
	ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<Cineplex> cplDAO;
	private String decisionMake;
	
	public ListMovieView(){
		if(sc == null)
			sc= new Scanner(System.in);	
		if(mlistMovieMgr == null)
			mlistMovieMgr= new ListMovieMgr(this);	
	}
	
	public void listMovie(ArrayList<String> cpl){
		this.cineplexes = cpl;
		listMovie();
	}
	
	/**
	 * Listing out all the cineplex and let the user to choose he/she
	 * want to list out which cineplex's movie list
	 */
	public void listMovie(){
		cplDAO=new CineplexDAO().getAllCineplex();		
		do{
			System.out.print("\nSelect which cineplex you would like to query from:\n");
			for(int i=0; i<cplDAO.size(); i++)
				System.out.println((i+1)+". "+new CineplexDAO().getAllCineplex().get(i).getName());
			System.out.println("------------------------------------------");
			System.out.print("Enter cineplex choice: ");
			decisionMake=sc.nextLine();
		}while(!mlistMovieMgr.verifyInput(cplDAO.size(), decisionMake));
		int cineplexChoice = Integer.parseInt(decisionMake);
		this.movies=mlistMovieMgr.getCineplexMovie(cineplexChoice);
		
		
		System.out.println("=== "+new CineplexDAO().getAllCineplex().get(cineplexChoice-1).getName()+"'s Movie List ===");
		for(int i=0; i<movies.size(); i++){
			System.out.println((i+1)+". "+movies.get(i).getTitle());
			System.out.println("Movie Type: "+movies.get(i).getMovieType());
			System.out.println("");
		}
		System.out.println("---------------------------------------------");
	}
	
	
}
