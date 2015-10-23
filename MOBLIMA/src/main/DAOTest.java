package main;

import java.util.ArrayList;
import java.util.Date;

import dao_module.AdminDAO;
import dao_module.CinemaDAO;
import dao_module.CineplexDAO;
import dao_module.GuestDAO;
import dao_module.HoildayDAO;
import dao_module.MovieDAO;
import model.AGELIMIT;
import model.Cineplex;
import model.Guest;
import model.MOVIESTATUS;
import model.MOVIETYPE;
import model.Movie;

public class DAOTest {
	public static void main(String a[]){
		//TestAdminDAO();
		//TestGuestDAO();
		//TestHoildayDAO();
		//TestCineplexDAO();
		//TestCinemaDAO();
		//TestMovieDAO();
		//TestShowTimeDAO();
		//TestBookingDAO();
		
	}
	
	public static void TestAdminDAO(){
		System.out.println("================================");
		System.out.println("START OF ADMIN DAO FUNCTION TEST");
		
		//testing the method getCineplexByUserNameAndPassword//
		Cineplex cineplex = new AdminDAO().getCineplexByUserNameAndPassword("admin1", "password", "THE CATHAY CINEPLEX");
		System.out.println("\nMethod : \n\ngetCineplexByUserNameAndPassword : returns cineplex object");
		System.out.println("Input  : 'admin1','password','THE CATHAY CINEPLEX'");
		System.out.println("\nOutput : \n\ncompanyName\t:"+cineplex.getCompanyName()+", \naddress\t\t:"+cineplex.getAddress()+", \nname\t\t:"+cineplex.getName());
		System.out.print("Cinema\t\t:");
		for(int i=0;i<cineplex.getCinemas().size();i++){
			System.out.print("|"+cineplex.getCinemas().get(i).getCinemaCode()+"|");
		}
		System.out.print("\nMovies\t\t:");
		for(int i=0;i<cineplex.getMovies().size();i++){
			System.out.print("|"+cineplex.getMovies().get(i).getTitle()+cineplex.getMovies().get(i).getMovieType()+"|");
		}
		
		System.out.println("\n\nEND OF ADMIN DAO FUNCTION TEST");
		System.out.println("==================================");
		
	}
	public static void TestGuestDAO(){
		System.out.println("================================");
		System.out.println("START OF GUEST DAO FUNCTION TEST");
		
		//testing method getGuestByEmailAndMobileNoANDCineplexName
		Guest guest = new GuestDAO().getGuestByEmailAndMobileNoANDCineplexName("john@gmail.com", 86886234, "CATHAY CINEPLEX DOWNTOWN EAST");
		System.out.println("\nMethod : \n\ngetGuestByEmailAndMobileNoANDCineplexName : returns Guest object");
		System.out.println("\nInput  : 'john@gmail.com',86886234,'CATHAY CINEPLEX DOWNTOWN EAST'");
		System.out.println("\nOutput : (DOES NOT INCLUDE BOOKINGS IN RESULT)\n\nemail\t\t:"+guest.getEmail()+"\nname\t\t:"+guest.getName()+"\nid\t\t:"+guest.getId()+"\nmobileno\t:"+guest.getMobileNo()+"\ncineplex\t:"+guest.getCineplexName());
		
		
		//testig method createNewGuest
		Guest newGuest= new Guest("kaiquan","kaiquan@gmail.com",86886234,"CATHAY CINEPLEX DOWNTOWN EAST");
		new GuestDAO().createNewGuest(newGuest, "CATHAY CINEPLEX DOWNTOWN EAST");
		System.out.println("\nMethod : \n\ncreateNewGuest : returns void");
		System.out.println("\nInput  : 'kaiquan','kaiquan88@gmail.com',86886234,'CATHAY CINEPLEX DOWNTOWN EAST'");
		
		
		//testig method getGuestByIdANDCineplexName
		guest = new GuestDAO().getGuestByIdANDCineplexName(newGuest.getId(), "CATHAY CINEPLEX DOWNTOWN EAST");
		System.out.println("\nMethod : \n\ngetGuestByIdANDCineplexName : returns Guest object");
		System.out.println("\nInput  : created guest id,'CATHAY CINEPLEX DOWNTOWN EAST'");
		System.out.println("\nOutput : (DOES NOT INCLUDE BOOKINGS IN RESULT)\n\nemail\t\t:"+guest.getEmail()+"\nname\t\t:"+guest.getName()+"\nid\t\t:"+guest.getId()+"\nmobileno\t:"+guest.getMobileNo()+"\ncineplex\t:"+guest.getCineplexName());
		
		System.out.println("\n\nEND OF GUEST DAO FUNCTION TEST");
		System.out.println("==================================");
	}
	public static void TestHoildayDAO(){
		System.out.println("===================================");
		System.out.println("START OF HOILDAY DAO FUNCTION TEST");
		
		//testing the method getAllHoildays
		ArrayList<Date> hoildays = new HoildayDAO().getAllHoildays();
		System.out.println("\nMethod : \n\ngetAllHoildays : returns ArrayList<Date> object");
		System.out.println("\nOutput : \n");
		for(int i=0;i<hoildays.size();i++){
			System.out.print("|"+hoildays.get(i).toString()+"|");
		}
		
		//testing the method addHoilday
		new HoildayDAO().addHoilday(new Date());
		hoildays = new HoildayDAO().getAllHoildays();
		System.out.println("\nMethod : \n\naddHoilday : returns void");
		System.out.println("\nOutput : \n");
		for(int i=0;i<hoildays.size();i++){
			System.out.print("|"+hoildays.get(i).toString()+"|");
		}
		
		//testing the method removeHoilday
		new HoildayDAO().removeHoilday(new Date());
		hoildays = new HoildayDAO().getAllHoildays();
		System.out.println("\nMethod : \n\nremoveHoilday : returns void");
		System.out.println("\nOutput : \n");
		for(int i=0;i<hoildays.size();i++){
			System.out.print("|"+hoildays.get(i).toString()+"|");
		}
		
		System.out.println("\n\nEND OF HOILDAY DAO FUNCTION TEST");
		System.out.println("====================================");
	}
	public static void TestCineplexDAO(){
		System.out.println("===================================");
		System.out.println("START OF CINEPLEX DAO FUNCTION TEST");
		
		//testing the method getAllCineplex
		ArrayList<Cineplex> cineplexs = new CineplexDAO().getAllCineplex();
		System.out.println("\nMethod : \n\ngetAllCineplex : returns ArrayList<Cineplex>");
		System.out.println("\nOutput : \n");
		for(int i=0;i<cineplexs.size();i++){
			
			for(int x=0;x<cineplexs.get(i).getCinemas().size();x++){
				
			}
		}
		
		//testing the method getCineplexByName
		Cineplex cineplex = new CineplexDAO().getCineplexByName("THE CATHAY CINEPLEX");
		System.out.println("\nMethod : \n\ngetCineplexByName : returns Cineplex object");
		System.out.println("\nInput  : 'THE CATHAY CINEPLEX'");
		System.out.println("\nOutput : \n");
		
		
		
		//testing the method getCineplexWithMovies
		System.out.println("\nMethod : \n\ngetCineplexWithMovies : returns ArrayList<Cineplex>");
		System.out.println("\nInput  : false");
		System.out.println("\nOutput : \n");
		cineplexs = new CineplexDAO().getCineplexWithMovies(false);
		for(int i=0;i<cineplexs.size();i++){
			
			
			for(int x=0;x<cineplexs.get(i).getCinemas().size();x++){
				
			}
			if(cineplexs.get(i).getMovies()!=null){
				for(int y=0;y<cineplexs.get(i).getMovies().size();y++){
					System.out.println(cineplexs.get(i).getMovies().get(y).getTitle()+"("+cineplexs.get(i).getMovies().get(y).getMovieType().toString()+")");
				}
			}
			
		}
		
		System.out.println("\n\nEND OF CINEPLEX DAO FUNCTION TEST");
		System.out.println("====================================");
	}
	public static void TestCinemaDAO(){
		
	}
	public static void TestMovieDAO(){
		Cineplex cineplex =new CineplexDAO().getCineplexByName("THE CATHAY CINEPLEX");
		ArrayList<String> cast = new ArrayList<String>();
		cast.add("mickey mouse");
		cast.add("mini mouse");
		Movie superman= new Movie(cineplex,"SUPERMAN",MOVIETYPE.THREE_DIMENATION,MOVIESTATUS.NOW_SHOWING,"superman the movie..why is his uncerware red","donnal trump",cast,90,AGELIMIT.G,null);
		
		new MovieDAO().createNewMovie(superman);
		
		new MovieDAO().getMovieByid("TCC-SUPERMAN-THREE_DIMENATION-2015-10-296-06-45");
	}
	public static void TestShowTimeDAO(){
		
	}
	public static void TestBookingDAO(){
		
	}
}
