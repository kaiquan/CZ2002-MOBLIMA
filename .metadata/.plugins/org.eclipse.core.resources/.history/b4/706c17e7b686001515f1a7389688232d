package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ListMovieMgr;
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
		for(int i=0; i<cineplexes.size(); i++)
			System.out.println((i+1)+". "+cineplexes.get(i));
		
		System.out.print("Enter cineplex choice: ");
		cineplexChoice = sc.nextInt();
		this.movies=mlistMovieMgr.getCineplexMovie(cineplexChoice);
		
		
		System.out.println("=== "+cineplexes.get(cineplexChoice-1)+" Movie List ===");
		for(int i=0; i<movies.size(); i++){
			System.out.println((i+1)+". "+movies.get(i).getTitle());
			System.out.println("Movie Type: "+movies.get(i).getMovieType());
			System.out.println("");
		}
		System.out.println("---------------------------------------------");
	}
	
	
}
