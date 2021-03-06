package dao_module;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.CINEMACLASS;
import model.Cinema;
import model.Cineplex;

public class CineplexDAO extends JSONDAO{
	private JSONObject jsonObject = null;
	private ArrayList<Cineplex> cineplexes = null;
	
	public CineplexDAO(){
		this.jsonObject = readFile(JSONDAO.cineplexPath);
		parseJSON(this.jsonObject);
	}
	
	/**
	 **@purpose	: To retrieve all cineplex
	 **@param	: void
	 **@return	: ArrayList<Cineplex>
	**/
	public ArrayList<Cineplex> getAllCineplex(){
		ArrayList<Cineplex> results = null;
		if(this.cineplexes!=null&&this.cineplexes.size()>0){
			results=this.cineplexes;
		}
		return results;
	}
	/**
	 **@purpose	: To retrieve all cineplex by name
	 **@param	: String cineplex name
	 **@return	: Cineplex
	**/
	public Cineplex getCineplexByName(String name){
		Cineplex result=null;
		if(this.cineplexes!=null&&this.cineplexes.size()>0){
			for(int i=0;i<this.cineplexes.size();i++){
				if(this.cineplexes.get(i).getName().equalsIgnoreCase(name)){
					if(result==null)
						result= new Cineplex();
					result=this.cineplexes.get(i);
				}
			}
		}
		return result;
	}
	
	/**
	 **@purpose	: To retrieve all cineplex attached with movies showing
	 **@param	: boolaean to include movies that are not showing
	 **@return	: ArrayList<Cineplex>
	**/
	public ArrayList<Cineplex> getCineplexWithMovies(boolean INCLUDE_END_OF_SHOWING_MOVIES){
		ArrayList<Cineplex> results = null;
		
		if(this.cineplexes!=null&&this.cineplexes.size()>0){
			for(int i=0;i<this.cineplexes.size();i++){
				this.cineplexes.get(i).setMovies(new MovieDAO().getAllMoviesWithReviewsByCineplex(this.cineplexes.get(i).getName(), INCLUDE_END_OF_SHOWING_MOVIES));
			}
			if(results==null)
				results= new ArrayList<Cineplex>();
			results=this.cineplexes;
		}
		return results;
	}
	
	private void parseJSON(JSONObject jsonObject){
		if(this.jsonObject != null){
			Cinema cinema;
			Cineplex cineplex;
			//loop through to get the cineplexs
			JSONArray cineplexs = (JSONArray) jsonObject.get("cineplex");
			JSONObject jsonCineplex;
			for(int i=0;i<cineplexs.size();i++){
				jsonCineplex = (JSONObject) cineplexs.get(i);
				
				//loop through to get the cinemas in the cineplex
				JSONArray jsonCinemas = (JSONArray) jsonCineplex.get("cinemas");
				ArrayList<Cinema> cinemas = new ArrayList<Cinema>(); 
				for(int x=0;x<jsonCinemas.size();x++){
					JSONObject jsonCinema = (JSONObject) jsonCinemas.get(x);
					
					//loop the seat to get the rows
					JSONArray jsonSeats = (JSONArray) jsonCinema.get("seats");
					ArrayList<ArrayList<String>> seats = new ArrayList<ArrayList<String>>();
					for(int y=0;y<jsonSeats.size();y++){
						JSONObject jsonRow = (JSONObject) jsonSeats.get(y);
						//loop the rows to get the cols
						String rowletter="row_"+(char)((y + 'A'));
						JSONArray jsonCol = (JSONArray) jsonRow.get(rowletter.toLowerCase());
						ArrayList<String> row = new ArrayList<String>();
						for(int z=0;z<jsonCol.size();z++){
							row.add(((JSONObject)jsonCol.get(z)).get("seatno").toString());
						}
						seats.add(row);
					}
					cinema = new Cinema();
					cinema.setSeats(seats);
				    cinema.setCinemaCode(jsonCinema.get("cinemacode").toString());
				    cinema.setCinemaClass(CINEMACLASS.valueOf(jsonCinema.get("cinemaclass").toString()));
				    cinemas.add(cinema);
				}
				

				cineplex= new Cineplex(jsonCineplex.get("companyname").toString(),jsonCineplex.get("cineplexname").toString(),jsonCineplex.get("address").toString(),cinemas);
				cineplex.setMovies(new MovieDAO().getAllMoviesWithReviewsByCineplex(cineplex.getName(), true));
				for(int f=0;f<cineplex.getCinemas().size();f++){
					cineplex.getCinemas().get(f).setCineplex(cineplex);
				}
				if(this.cineplexes==null)
					this.cineplexes= new ArrayList<Cineplex>();
				this.cineplexes.add(cineplex);
				
				
			}
		}
	}
	
}
