package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieDetailsMgr;
import model.Movie;


public class MovieDetailsView {
	private Scanner sc;
	private MovieDetailsMgr mMovieDetailsMgr;
	ArrayList<String> movieList = new ArrayList<String>();
	ArrayList<String> castList = new ArrayList<String>();
	ArrayList<String> reviewList = new ArrayList<String>();
	Movie movies = new Movie();
	private int movieChoice;
	
	public MovieDetailsView(){
		if(sc == null)
			sc= new Scanner(System.in);	
		if(mMovieDetailsMgr == null)
			mMovieDetailsMgr= new MovieDetailsMgr(this);	
	}
	
	public void listMovie(ArrayList<String> movL){
		this.movieList = movL;
		listMovieDetails();
	}
	
	public void listMovieDetails(){
		System.out.println("=== Movie List ===");
		for(int i=0; i<movieList.size(); i++){
			System.out.println((i+1)+". "+movieList.get(i));
		}
		System.out.print("Enter your choice of movie: ");
		movieChoice = sc.nextInt();
		this.movies=mMovieDetailsMgr.movieDetails(movieChoice);
		System.out.println("============= Movie Details =============");
		System.out.println("Title \t\t\t:"+movies.getTitle()+" "+movies.getMovieType());
		System.out.println("Director \t\t:"+movies.getDirector());
		System.out.println("Duration \t\t:"+movies.getShowDuration());
		System.out.println("Film Classification \t:"+movies.getAgeLimit());
		System.out.println("Rating \t\t\t:"+mMovieDetailsMgr.getAvgMovieRating());
		System.out.println("Movie Status \t:"+movies.getMovieStatus());
		System.out.println("Casts \t\t\t:");
		this.castList=mMovieDetailsMgr.getMovieCasts();
		for(int i=0;i<castList.size();i++){
			System.out.println("\t\t"+(i+1)+". "+castList.get(i));
		}
		System.out.println("Reviews \t:");
		this.reviewList=mMovieDetailsMgr.getMovieReviews();
		for(int i=0;i<reviewList.size();i++){
			System.out.println("\t\t"+(i+1)+". "+reviewList.get(i));
		}
	}

}
