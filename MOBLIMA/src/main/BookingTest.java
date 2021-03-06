package main;

import java.util.ArrayList;

import controller.AdminTopFiveMoviesMgr;
import controller.BookingMgr;
import dao_module.CinemaDAO;
import dao_module.MovieDAO;
import dao_module.ShowTimeDAO;
import model.Cinema;
import model.Cineplex;
import model.Movie;
import model.Showtime;

public class BookingTest {

	public static void main(String[] args) {
		MovieDAO md = new MovieDAO();
		CinemaDAO cd = new CinemaDAO();
		ShowTimeDAO sd = new ShowTimeDAO();
		
		ArrayList<String> selectedSeats = new ArrayList<String>();
		selectedSeats.add("A1");
		selectedSeats.add("A2");
		selectedSeats.add("A3");
		selectedSeats.add("A4");
		Movie selectedMovie = md.getMovieByid("TCC-IRONMAN-HD-2015-07-13-24-59");
		Cinema selectedCinema = cd.getCinemaByCinemaId("TCC-T1");
		Showtime showtime = sd.getShowTimeByID("TCC-T1-201511141330");
		String cineplexName = "THE CATHAY CINEPLEX";
		
		Cineplex nc = new Cineplex("Cathay Cineplexes Pte Ltd","THE CATHAY CINEPLEX", "2 Handy Road Levels 5 & 6 The Cathay Singapore 229233", new ArrayList<Cinema>());
		
		BookingMgr bm = new BookingMgr(selectedSeats, selectedMovie, selectedCinema, showtime, cineplexName);
		
		bm.displayBookingDetails();
		bm.getSeniorAndStudentCount();
		bm.getMovieGoerDetails();
		bm.calculateAndDisplayTicketCosts();
		bm.proceedBooking();
		
		bm.checkBookingHistory();

		AdminTopFiveMoviesMgr am = new AdminTopFiveMoviesMgr(nc);
		am.displayTopFiveMoviesMenu();
		
		
	}

}
