package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.SelectSeatMgr;
import model.Movie;


public class SelectSeatView {
	private Scanner sc;
	private SelectSeatMgr mSelectSeatMgr;
	ArrayList<Object> testingList = new ArrayList<Object>();
	ArrayList<String> showingList = new ArrayList<String>();
	ArrayList<String> movieList = new ArrayList<String>();
	ArrayList<String> cineplexList = new ArrayList<String>();
	Movie movies = new Movie();
	private int firstDecision;
	private int secondDecision;
	private int thirdDecision;
	private int showtimeDecision;
	private String bookSeat;
	private String decisionMake;
	
	
	public SelectSeatView(){
		if(sc == null)
			sc= new Scanner(System.in);	
		if(mSelectSeatMgr == null)
			mSelectSeatMgr= new SelectSeatMgr(this);	
	}
	
	/**
	 * Listing out all the cineplex and let the user to choose he/she
	 * want to list out which cineplex's movie list
	 * @param  movL  movie list
	 * @param  cplL  cineplex list
	 */
	public void listMovie(ArrayList<String> movL,ArrayList<String> cplL){
		this.movieList = movL;
		this.cineplexList = cplL;
		chooseCplorMov();
		selectSeat();
	}
	
	/**
	 * Asking for the user input (Choose by Cineplex or Movie)
	 * Pass the user input to SelectSeatMgr and get the correspond 
	 * result and print out to the user. 
	 */
	public void	chooseCplorMov(){
		
		do{
			System.out.println("Please Enter An Option (1 or 2): ");
			System.out.println("1. Choose Cineplex ");
			System.out.println("2. Choose Movie ");
			//firstDecision = sc.nextInt();
			decisionMake=sc.nextLine();
		}while(!mSelectSeatMgr.verifyInput(2, decisionMake));
		firstDecision = Integer.parseInt(decisionMake);
		showingList=mSelectSeatMgr.proceedDecision(firstDecision);
		
		do{
			System.out.println("------------------------------------------");
			for(int i=0;i<showingList.size();i++){
				System.out.println((i+1)+"."+showingList.get(i));
			}
			decisionMake=sc.nextLine();
		}while(!mSelectSeatMgr.verifyInput(showingList.size(), decisionMake));
		secondDecision = Integer.parseInt(decisionMake);
		//secondDecision=sc.nextInt();
		showingList= mSelectSeatMgr.proceedSecondDecision(secondDecision);
		do{
			System.out.println("------------------------------------------");
			for(int i=0;i<showingList.size();i++){
				System.out.println((i+1)+"."+showingList.get(i));
			}
			decisionMake=sc.nextLine();
		}while(!mSelectSeatMgr.verifyInput(showingList.size(), decisionMake));
		thirdDecision = Integer.parseInt(decisionMake);
		//thirdDecision=sc.nextInt();
		showingList=mSelectSeatMgr.proceedThirdDecision(thirdDecision);
		do{
			System.out.println("------------------------------------------");
			System.out.println("Please Select One of The Listed ShowTime: ");
			for(int i=0;i<showingList.size();i++){
				System.out.println((i+1)+"."+showingList.get(i));
			}
			decisionMake=sc.nextLine();
		}while(!mSelectSeatMgr.verifyInput(showingList.size(), decisionMake));
		showtimeDecision = Integer.parseInt(decisionMake);
		mSelectSeatMgr.proceedShowtimeDecision(showtimeDecision);
	}
	
	/**
	 * Asking for the user input (Number of seat and the seat location)
	 * Pass the user input to SelectSeatMgr to verify the seat availability. 
	 * If the seat is valid, store the seat to the bookSeat.
	 */
	public void selectSeat(){
		do{
			System.out.println("------------------------------------------");
			System.out.println("How many seat you need?: ");
			decisionMake=sc.nextLine();
		}while(!mSelectSeatMgr.verifyInput(210, decisionMake));
		int numberOfSeat = Integer.parseInt(decisionMake);

		sc = new Scanner(System.in);
		for(int i=0;i<numberOfSeat;i++){
			System.out.println("Please select your "+(i+1)+" seat: ");
			bookSeat=sc.nextLine();
			mSelectSeatMgr.verifySeat(bookSeat);
		}
		mSelectSeatMgr.doBooking();
	}
	
	/**
	 * If the SelectSeatMgr detect the user input for the seat is invalid,
	 * SelectSeatMgr will request user to give a new seat number and 
	 * verify again until the seat number is valid.
	 * @param seatNumber The new seat number chosen by user
	 */
	public void invalidSeat(String seatNumber){

		System.out.println("The seat number "+seatNumber+" is invalid.");
		System.out.println("Please Select A New Seat: ");
		bookSeat=sc.nextLine();
		mSelectSeatMgr.verifySeat(bookSeat);
	}
	

}
