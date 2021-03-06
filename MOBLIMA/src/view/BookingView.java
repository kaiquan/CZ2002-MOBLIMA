/**
 * A View class which is responsible for interfacing with user to perform booking related actions
 * @author 	Freny Nelin
 * @version	1.0
 * @since	2015-11-10
 */

package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.Booking;
import controller.BookingMgr;

public class BookingView {

	/**
	 * Controller object used as center logic to perform booking related actions
	 */
	private BookingMgr bookingMgr;
	
	/**
	 * Scanner object used for getting user inputs
	 */
	private Scanner s;

	/**
	 * Creates a new BookingView Object which initializes the controller object (BookingMgr)
	 * @param bkgMgr	BookingMgr Object
	 */
	public BookingView(BookingMgr bkgMgr){
		this.bookingMgr = bkgMgr;
		this.s = new Scanner(System.in);
	}
	
	/**
	 * Display details of items selected for booking purpose
	 * @param selectedSeats			List of seats selected for booking
	 * @param selectedMovieName		Name of the movie selected for booking
	 * @param selectedMovieType		Type of the movie selected for booking
	 * @param selectedCinemaCode	Cinema code of the cinema for booking
	 * @param showtime				Showtime selected for booking
	 * @param selectedCineplexName	Name of the cineplex selected for booking
	 */
	public void showBookingDetails(ArrayList<String> selectedSeats, String selectedMovieName, String selectedMovieType, String selectedCinemaCode, Date showtime, String selectedCineplexName){
		String dateString = new SimpleDateFormat("E hh:mm a, dd-MM-yyyy").format(showtime);
		System.out.println("======================== YOUR BASKET ========================");
		System.out.println("Movie\t\t\t: " + selectedMovieName + " (" + selectedMovieType + ")");
		System.out.println("Showing at\t\t: " + dateString + ", " + selectedCineplexName);
		System.out.println("Cinema\t\t\t: " + selectedCinemaCode);
		System.out.print("Seat(s) selected\t: ");
		printSeats(selectedSeats);
		System.out.println("\n\n");
	}
	
	/**
	 * Ask for user inputs to get the number senior(s) and student(s) for the ticket(s) going to be booked
	 * @param selectedSeatSize	Total seats selected (equals as the total number of tickets)
	 */
	public void getSeniorAndStudentCount(int selectedSeatSize){
		int seatsBookedCount = selectedSeatSize;
		int numOfSenior = 0, numOfStudent = 0;
		String input;
		boolean isValidInput = false;
		
		System.out.println("=============== Senior & Children Privileges ===============");
		System.out.println("NOTE: SENIOR AND STUDENT TICKET(S) WILL BE VERIFIED UPON CHECKING-IN.");
		System.out.print("1. Please tell us how many senior(s) you have: ");
		
		while(!isValidInput){
			input = s.nextLine();
			boolean isNumber = false;
			boolean isSmallerThanSeatBookedCount = false;
		
			if(!input.matches("[0-9]+")){
				System.out.print("*** Error: Invalid Number. *** ");
			}
			else{
				isNumber = true;
				numOfSenior = Integer.parseInt(input);
				if(numOfSenior > seatsBookedCount){
					System.out.print("*** Impossible, you have entered more than the number of tickets you have booked! *** ");
				}
				else{
					isSmallerThanSeatBookedCount = true;
				}
			}
			
			isValidInput = isNumber && isSmallerThanSeatBookedCount;
			if(!isValidInput){
				System.out.print("Please Re-enter the number of Senior(s): ");
			}
		}
		seatsBookedCount -= numOfSenior;
		
		if(seatsBookedCount > 0){
			isValidInput = false;
			System.out.print("2. Please tell us how many student(s) you have: ");
			
			while(!isValidInput){
				input = s.nextLine();
				boolean isNumber = false;
				boolean isSmallerThanSeatBookedCount = false;
			
				if(!input.matches("[0-9]+")){
					System.out.print("*** Error: Invalid Number. *** ");
				}
				else{
					isNumber = true;
					numOfStudent = Integer.parseInt(input);
					if(numOfStudent > seatsBookedCount){
						System.out.print("*** Impossible, you have entered more than the number of tickets you have booked! *** ");
					}
					else{
						isSmallerThanSeatBookedCount = true;
					}
				}
				
				isValidInput = isNumber && isSmallerThanSeatBookedCount;
				if(!isValidInput){
					System.out.print("Please Re-enter the number of Student(s): ");
				}
			}
		}
		System.out.println("\n");
		s.reset();
		this.bookingMgr.setSeniorAndStudentCount(numOfSenior, numOfStudent);
	}
	
	/**
	 * Ask for the details of the person who is booking the tickets
	 */
	public void getMovieGoersDetails(){
		s = new Scanner(System.in);
		String name, email, input;
		int mobileNo;
		System.out.println("================ Personal Details ================");
		System.out.print("Enter your Name: ");
		name = s.nextLine();
		while(name == ""){
			System.out.print("\nName is required. Please enter your name: ");
			name = s.nextLine();
		}
		name = name.trim();
		System.out.print("Enter your Email: ");
		email = s.nextLine();
		while(!email.matches("[a-zA-Z0-9\\_]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,3}")){
			System.out.print("Invalid email. Please enter your Email: ");
			email = s.nextLine();
		}
		
		System.out.print("Enter your Mobile No: ");
		input = s.nextLine();
		while(!input.matches("[0-9]{8}")){
			System.out.println("Invalid Number. Please enter your Mobile No: ");
			input = s.nextLine();
		}
		mobileNo = Integer.parseInt(input);
		System.out.println();
		this.bookingMgr.setMovieGoerDetails(name, email, mobileNo);
	}
	
	/**
	 * Display whether the user has successfully or unsuccessfully booked the movie
	 * @param isSuccessful	Boolean to indicate whether the booking is successful
	 */
	public void displaySuccessfulOrUnsuccessfulBooking(boolean isSuccessful){
		if(isSuccessful){
			System.out.println("You have successfully booked the movie. Thank you and enjoy your movie!");
		}
		else{
			System.out.println("Sorry, the booking is unsuccessful. Please try again.");
		}
		System.out.println();
	}
	
	/**
	 * Display the details of the ticket costs calculated and the total price payable by the user
	 * @param selectedSeatSize	Number of total tickets booked
	 * @param numOfAdult		Number of adults (regular) movie tickets
	 * @param numOfSenior		Number of senior(s) movie tickets
	 * @param numOfStudent		Number of student(s) movie tickets
	 * @param adultCost			Price for one adult (regular) ticket
	 * @param seniorCost		Price for one senior ticket
	 * @param studentCost		Price for one student ticket
	 * @param totalCost			Total price payable for ticket(s) booked
	 */
	public void displayTotalBookingCost(int selectedSeatSize, int numOfAdult, int numOfSenior, int numOfStudent, double adultCost, double seniorCost, double studentCost, double totalCost){
		System.out.println("=================== TOTAL BOOKING COST ===================");
		System.out.println("Total Number of Tickets\t: " + selectedSeatSize);
		if(numOfAdult > 0){
			System.out.println("Ticket for adult(s)\t: " + numOfAdult + " x $" + adultCost + " = $" + (numOfAdult*adultCost));
		}
		if(numOfSenior > 0){
			System.out.println("Ticket for senior(s)\t: " + numOfSenior + " x $" + seniorCost + " = $" + (numOfSenior*seniorCost));
		}
		if(numOfStudent > 0){
			System.out.println("Ticket for student(s)\t: " + numOfStudent + " x $" + studentCost + " = $" + (numOfStudent*studentCost));
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("Subtotal (Inc. GST)\t: $" + totalCost);
		System.out.println();
	}
	
	/**
	 * Display the details of all bookings made by specific user in the past
	 * @param bookingHistory	List of bookings made by user in the past
	 */
	public void displayBookingHistory(ArrayList<Booking> bookingHistory){
		System.out.println("=================== BOOKING HISTORY ==========================");
		if(bookingHistory != null && !bookingHistory.isEmpty()){
			for(Booking b : bookingHistory){
				String transactionTime;
				try {
					transactionTime = new SimpleDateFormat("E hh:mm a, dd-MM-yyyy").format(new SimpleDateFormat("yyyyMMddhhmm").parse(b.getTransactionid().split("-")[2]));
				} catch (ParseException e) {
					transactionTime = "Unknown Date";
				}
				System.out.println("Booking Date\t\t: " + transactionTime);
				System.out.println("Movie\t\t\t: " + this.bookingMgr.getMovieTitlePlusType(b.getMovieId()));
				System.out.println("Cinema\t\t\t: " + b.getCinemaId() + "(" + b.getCinema().getCinemaClass().toString() + "), " + b.getCineplexName());
				System.out.println("Showtime\t\t: " + this.bookingMgr.getShowtime(b.getShowtimeId()));
				System.out.println("Total Tickets\t\t: " + b.getSeats().size());
				System.out.print("Booked Seats\t\t: ");
				printSeats(b.getSeats());
				System.out.println();
				System.out.println("Number of Senior(s)\t: " + b.getNumberOfSeniors());
				System.out.println("Number of Student(s)\t: " + b.getNumberOfStudents());
				System.out.println("Total Ticket Prices\t: " + b.getTotalPrice());
				System.out.println();
			}
			System.out.println("=================== END OF BOOKING HISTORY ====================");
			System.out.println();
		}
		else{
			System.out.println("Sorry, you have not booked any movie before.");
			System.out.println();
		}
	}

	/**
	 * Display an array of seats name in concatenated string
	 * @param selectedSeats	List of seat names
	 */
	private void printSeats(ArrayList<String> selectedSeats){
		for(int i = 0; i < selectedSeats.size(); i++){
			System.out.print(selectedSeats.get(i));
			if(i < selectedSeats.size()-1){
				System.out.print(", ");
			}
		}
	}
}
