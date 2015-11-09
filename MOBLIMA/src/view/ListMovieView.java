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
	private int cineplexChoice;
	
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
	
	public void listMovie(){
		System.out.print("\nSelect which cineplex you would like to query from:\n");
		for(int i=0; i<new CineplexDAO().getAllCineplex().size(); i++)
			System.out.println((i+1)+". "+new CineplexDAO().getAllCineplex().get(i).getName());
		
		System.out.print("Enter cineplex choice: ");
		cineplexChoice = sc.nextInt();
		this.movies=mlistMovieMgr.getCineplexMovie(cineplexChoice);
		
		System.out.println(this.movies.size());
		System.out.println("=== "+new CineplexDAO().getAllCineplex().get(cineplexChoice-1).getName()+"'s Movie List ===");
		for(int i=0; i<movies.size(); i++){
			System.out.println((i+1)+". "+movies.get(i).getTitle());
			System.out.println("Movie Type: "+movies.get(i).getMovieType());
			System.out.println("");
		}
		System.out.println("---------------------------------------------");
	}
	
	
}
