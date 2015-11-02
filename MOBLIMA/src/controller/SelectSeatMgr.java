package controller;

import java.util.ArrayList;

import dao_module.BookingDAO;
import dao_module.CineplexDAO;
import dao_module.MovieDAO;
import dao_module.ShowTimeDAO;
import model.Booking;
import model.Cineplex;
import model.Movie;
import model.Showtime;
import view.SelectSeatView;

public class SelectSeatMgr {

	private SelectSeatView mSelectSeatView;
	private MovieDAO movDAO;
	private CineplexDAO cplDAO;
	private ShowTimeDAO stDAO;
	private BookingDAO bookDAO;
	private ArrayList<Movie> mMovie;
	private ArrayList<Cineplex> mCineplex;
	private ArrayList<String> showingList;
	private ArrayList<Movie> movieSearchByCineplex=new ArrayList<Movie>();;
	private ArrayList<Cineplex> cineplexSearchByMovie=new ArrayList<Cineplex>();;
	private ArrayList<Showtime> showtimeByCineplexAndMovie= new ArrayList<Showtime>();
	private ArrayList<Booking> bookingRecord=new ArrayList<Booking>();
	private ArrayList<ArrayList<String>> seatOccupied=new ArrayList<ArrayList<String>>();
	private ArrayList<String> unavailableSeat=new ArrayList<String>();
	private ArrayList<String> newBookingSeat=new ArrayList<String>();
	private int decisionMake;
	private Cineplex cpl;
	private Movie mov;
	private Showtime st;
	private String cinemaCode;
	private int cinemaRow;
	private int cinemaCol;
	String[] rowList={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	
	public SelectSeatMgr(SelectSeatView selectSeatView){
		this.mSelectSeatView=selectSeatView;
	}
	
	// List out the option and let user to decide choose movie or cineplex first
	public ArrayList<String> proceedDecision(int choice){
		this.decisionMake=choice;
		if (decisionMake==1){
			listCineplex();
		}else if(decisionMake==2){
			listMovie();
		}
		return showingList;
	}
	
	public ArrayList<String> proceedSecondDecision(int choice){
		//Choose the Cineplex First
		if(decisionMake==1){
			getMovieByCineplex(mCineplex.get(choice-1).getName());
		}
		//Choose the Movie First
		else if (decisionMake==2){
			getCineplexByMovie(mMovie.get(choice-1).getTitle());
		}
		return showingList;
	}
	
	public ArrayList<String> proceedThirdDecision(int choice){
		//Choose the Movie Last
		if(decisionMake==1){
			getCineplexByMovie(mMovie.get(choice-1).getTitle());
		}
		//Choose the Cineplex Last
		else if (decisionMake==2){
			getMovieByCineplex(mCineplex.get(choice-1).getName());
		}
		return showingList;
	}
	
	public void proceedShowtimeDecision(int choice){
		st=showtimeByCineplexAndMovie.get(choice-1);
		getCinemaCode(choice-1);
		checkBookingSeat(cinemaCode);
		getCinemaSeatSize();
		showSeat(cinemaRow,cinemaCol);
	}
//------------------------Asking For First Decision--------------------------------//
	public void listCineplex(){
		if(cplDAO==null){
			cplDAO=new CineplexDAO();
		}
		showingList=new ArrayList<String>();
		if(mCineplex==null){
			mCineplex=cplDAO.getAllCineplex();
		}
		for(int i=0;i<mCineplex.size();i++){
			showingList.add(mCineplex.get(i).getName());
		}
		System.out.println("--------------------------");
		System.out.println("Please Select One of The Listed Cineplex: ");
	}
	
	public void listMovie(){
		if(movDAO == null){
			movDAO = new MovieDAO();
		}
		showingList=new ArrayList<String>();
		if(mMovie==null){
			mMovie=movDAO.getAllMovieswithReviews(false);
		}
		
		for(int i=0;i<mMovie.size();i++){
			showingList.add(mMovie.get(i).getTitle());
		}
		System.out.println("--------------------------");
		System.out.println("Please Select One of The Listed Movie: ");
	}
//------------------------Asking For Second and Third Decision------------------//	
	public void getMovieByCineplex(String cpl){
		showingList=new ArrayList<String>();
		this.cpl=cplDAO.getCineplexByName(cpl);
		for(int i=0;i<mMovie.size();i++){
			if(mMovie.get(i).getCineplexName().equals(cpl)){
				showingList.add(mMovie.get(i).getCineplexName());
				movieSearchByCineplex.add(mMovie.get(i));
			}
		}
		System.out.println("--------------------------");
		System.out.println("Please Select One of The Listed Movie: ");
	}
	
	public  void getCineplexByMovie(String mov){
		showingList=new ArrayList<String>();
		this.mov=movDAO.getMovieByTitle(mov);
		if(mCineplex==null){
			mCineplex=cplDAO.getAllCineplex();
		}
		for(int i=0;i<mCineplex.size();i++){
			if(mCineplex.get(i).getMovies().equals(mov)){
				for(int j=0;j<mCineplex.get(i).getMovies().size();j++){
					showingList.add(mCineplex.get(i).getMovies().get(j).getCineplexName());
				}
				cineplexSearchByMovie.add(mCineplex.get(i));
			}
		}
		System.out.println("--------------------------");
		System.out.println("Please Select One of The Listed Cineplex: ");
	}
//------------------------Asking for Showtime--------------------------------------//
	public void getShowTimeInfo(Cineplex cpl, Movie mov){
		if(stDAO==null){
			stDAO=new ShowTimeDAO();
		}
		showtimeByCineplexAndMovie=stDAO.getShowTimesByCineplexAndMovie(cpl, mov);
		for(int i=0;i<showtimeByCineplexAndMovie.size();i++){
			showingList.add(showtimeByCineplexAndMovie.get(i).getDateTime().toString());
		}
		System.out.println("--------------------------");
		System.out.println("Please Select One of The Listed ShowTime: ");
	}
//------------------------Retrieve Cinema Code and The Seat-------------------------//
	public String getCinemaCode(int choice){
		this.cinemaCode=showtimeByCineplexAndMovie.get(choice).getCinemaCode();
		return cinemaCode;
	}
	
	public void getCinemaSeatSize(){
		for(int i=0;i<cpl.getCinemas().size();i++){
			if(cpl.getCinemas().get(i).getCinemaCode()==cinemaCode){
				this.cinemaRow=cpl.getCinemas().get(i).getSeats().size();
				this.cinemaCol=cpl.getCinemas().get(i).getSeats().get(0).size();
			}
		}
	}
	
	public void checkBookingSeat(String cinemaCode){
		if(bookDAO==null){
			bookDAO=new BookingDAO();
		}
		bookingRecord=bookDAO.getBookingsByMovie(mov);
		for(int i=0;i<bookingRecord.size();i++){
			if(bookingRecord.get(i).getCinemaId().equals(cinemaCode)
					&&bookingRecord.get(i).getCineplexName().equals(cpl.getName())){
				seatOccupied.add(bookingRecord.get(i).getSeats());
				for(int j=0;j<bookingRecord.get(i).getSeats().size();j++){
					unavailableSeat.add(bookingRecord.get(i).getSeats().get(j));
				}
			}
		}
	}
		
	public void showSeat(int row,int col){
		boolean occupied=false;
		System.out.print(" ");
		for(int i=0;i<col;i++){
			System.out.print("\t"+(i+1));
		}
		System.out.println();
		for(int i=0;i<row;i++){
			System.out.print(" "+rowList[i]);
			for(int j=0;j<col;j++){
				for(int k=0;k<unavailableSeat.size();k++){
					String[] part = unavailableSeat.get(k).split("(?<=\\D)(?=\\d)");
					if(rowList[i].equals(part[0]) && part[1].equals(String.valueOf((j+1)))){
						occupied=true;
						break;
					}
				}
				if(occupied){
					System.out.print("\t[x]");
					occupied=false;
				}else{
					System.out.print("\t[ ]");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(" \t===================================================  S  C  R  E  E  N  ====================================================");
		System.out.println("[x] = Unavailable / Sold");
		System.out.println("[ ] = Available");
	}
	
	public void verifySeat(String seatNumber){
		String[] part = seatNumber.split("(?<=\\D)(?=\\d)");
		boolean add=true;
		while(true){
			if(part[0].compareTo("A")<0 ||part[0].compareTo(rowList[cinemaRow-1])>0||Integer.parseInt(part[1])<1||Integer.parseInt(part[1])>cinemaCol){
				mSelectSeatView.invalidSeat(seatNumber);
				add=false;
				break;
			}
			for(int i=0;i<unavailableSeat.size();i++){
				if(seatNumber.equals(unavailableSeat.get(i))){
					mSelectSeatView.invalidSeat(seatNumber);
					add=false;
					break;
				}
			}
		}
		if(add){
			newBookingSeat.add(seatNumber);
		}
	}
}
