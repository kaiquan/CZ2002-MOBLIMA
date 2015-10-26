package dao_module;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Booking;
import model.Guest;
import model.Movie;

public class BookingDAO extends JSONDAO {
	private JSONObject jsonObject = null;
	private ArrayList<Booking> bookings = null;
	
	public BookingDAO(){
		this.jsonObject = readFile(JSONDAO.bookingPath);
		parseJSON(this.jsonObject,false);
	}
	
	//@purpose 	: To create a new booking
	//@Param 	: required
	@SuppressWarnings("unchecked")
	public void createNewBooking(Booking booking){
		
		if(booking.getGuest() !=null){
			new GuestDAO().createNewGuest(booking.getGuest(),booking.getCineplexName());
		}
		//TODO add the booking object to the jsonObject
		JSONObject jsonBooking = new JSONObject();
		jsonBooking.put("transactionid", booking.getTransactionid());
		jsonBooking.put("movietitle", booking.getMovieTitle());
		jsonBooking.put("movieid", booking.getMovieId());
		jsonBooking.put("cineplexname", booking.getCineplexName());
		jsonBooking.put("totalprice", booking.getTotalPrice());
		jsonBooking.put("numberofseniors",booking.getNumberOfSeniors());
		jsonBooking.put("cinemaid", booking.getCinemaId());
		jsonBooking.put("guestid", booking.getGuestId());
		
		JSONArray jsonSeats = new JSONArray();
		JSONObject jsonSeat;
		for(int i=0;i<booking.getSeats().size();i++){
			jsonSeat = new JSONObject();
			jsonSeat.put("seatno", booking.getSeats().get(i));
			jsonSeats.add(jsonSeat);
		}
		jsonBooking.put("seats", jsonSeats);
		
		updateFile(JSONDAO.bookingPath,this.jsonObject);
	}	
	
	//@purpose 	: To calculate the total ticket sales for a movie by the controller
	//@Param	: (Movie) required
	//@return	: null if no bookings
	@SuppressWarnings("null")
	public ArrayList<Booking> getBookingsByMovie(Movie movie){
		ArrayList<Booking> results = null;
		
		if(this.bookings!=null&&this.bookings.size()>0){
			for(int i=0;i<this.bookings.size();i++){
				if(this.bookings.get(i).getMovie().equals(movie)){
					results.add(this.bookings.get(i));
				}
			}
		}
		return results;
	}
	
	//@purpose	: To retrieve all bookings by a guest
	//@param	: required
	//@return	: null if bookings are empty/not found
	@SuppressWarnings("null")
	public ArrayList<Booking> getBookingsByGuest(Guest guest){
		ArrayList<Booking> results = null;
		parseJSON(this.jsonObject,true);
		if(this.bookings!=null&&this.bookings.size()>0){
			for(int i=0;i<this.bookings.size();i++){
				if(this.bookings.get(i).getGuest().equals(guest)){
					results.add(this.bookings.get(i));
				}
			}
		}
		
		return results;
	}
	
	
	private void parseJSON(JSONObject jsonObject,boolean INCLUDE_GUEST){
		if(this.jsonObject != null){
			JSONArray jsonBookings = (JSONArray) jsonObject.get("bookings");
			JSONObject jsonBooking;
			Booking booking;
			for(int i=0;i<jsonBookings.size();i++){
				jsonBooking = (JSONObject)jsonBookings.get(i);
				booking = new Booking();
				booking.setTransactionid(jsonBooking.get("transactionid").toString());
				booking.setShowtimeId(jsonBooking.get("showtimeid").toString());
				booking.setMovieTitle(jsonBooking.get("movietitle").toString());
				booking.setMovieId(jsonBooking.get("moiveid").toString());
				booking.setCineplexName(jsonBooking.get("cineplexname").toString());
				booking.setTotalPrice((double) jsonBooking.get("totalprice"));
				booking.setNumberOfSeniors(Integer.parseInt(jsonBooking.get("numberofseniors").toString()));
				booking.setGuestId(jsonBooking.get("guestid").toString());
				booking.setCinemaId(jsonBooking.get("cinemaid").toString());
				
				booking.setShowTime(new ShowTimeDAO().getShowTimeByID(booking.getShowtimeId()));
				
				//loop to get the booked seats
				JSONArray jsonSeats = (JSONArray) jsonBooking.get("seats");
				JSONObject jsonSeat;
				ArrayList<String> seats=null;
				for(int x=0;x<jsonSeats.size();x++){
					jsonSeat = (JSONObject)jsonSeats.get(x);
					if(seats==null)
						seats = new ArrayList<String>();
					seats.add(jsonSeat.get("seatno").toString());
				}
				booking.setSeats(seats);
				
				booking.setCinema(new CinemaDAO().getCinemaByCinemaId(booking.getCinemaId()));
				booking.setMovie(new MovieDAO().getMovieByid(booking.getMovieId()));
				if(INCLUDE_GUEST)
					booking.setGuest(new GuestDAO().getGuestByIdANDCineplexName(booking.getGuestId(), booking.getCineplexName()));
				
				if(this.bookings==null)
					this.bookings = new ArrayList<Booking>();
				this.bookings.add(booking);
			}
		}
	}
}
