package dao_module;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Cineplex;
import model.Movie;
import model.Showtime;

public class ShowTimeDAO extends JSONDAO{
	private JSONObject jsonObject = null;
	private ArrayList<Showtime> showtimes = null;
	
	public ShowTimeDAO(){
		this.jsonObject = readFile(JSONDAO.showtimePath);
		parseJSON(this.jsonObject);
	}
	
	//@purpose	: create a new showtime
	//@param	: required
	//@return	: void
	@SuppressWarnings("unchecked")
	public void createNewShowTime(Showtime showTime){
		JSONArray jsonShowTimes = (JSONArray) jsonObject.get("showtimes");
		JSONObject jsonShowTime = new JSONObject();
		
		jsonShowTime.put("showtimeid", showTime.getId());
		jsonShowTime.put("cineplexname", showTime.getCineplexName());
		jsonShowTime.put("movieid", showTime.getMovieId());
		jsonShowTime.put("cinemacode", showTime.getCinemaCode());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
		jsonShowTime.put("datetime", df.format(showTime.getDateTime()));
		
		jsonShowTimes.add(jsonShowTime);
		updateFile(JSONDAO.showtimePath,this.jsonObject);
	}
	//@purpose	: update a new showtime
	//@param	: required
	//@return	: void
	@SuppressWarnings("unchecked")
	public void updateShowTime(Showtime showtime){
		JSONArray jsonShowTimes = (JSONArray) jsonObject.get("showtimes");
		JSONObject jsonShowTime = new JSONObject();
		for(int i=0;i<jsonShowTimes.size();i++){
			jsonShowTime = (JSONObject)jsonShowTimes.get(i);
			if(jsonShowTime.get("showtimeid").toString().equals(showtime.getId())){
				jsonShowTimes.remove(i);
				
				jsonShowTime = new JSONObject();
				jsonShowTime.put("showtimeid", showtime.getId());
				jsonShowTime.put("cineplexname", showtime.getCineplexName());
				jsonShowTime.put("movieid", showtime.getMovieId());
				jsonShowTime.put("cinemacode", showtime.getCinemaCode());
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
				jsonShowTime.put("datetime", df.format(showtime.getDateTime()));
				
				jsonShowTimes.add(jsonShowTime);
				break;
			}
		}
		updateFile(JSONDAO.showtimePath,this.jsonObject);
	}
	
	//@purpose	: retrieve showtimes by cineplex n movie
	//@param	: required
	//@return	: void
	public ArrayList<Showtime> getShowTimesByCineplexAndMovie(Cineplex cineplex, Movie movie){
		ArrayList<Showtime> results = null;
		if(this.showtimes!=null&&this.showtimes.size()>0){
			for(int i=0;i<this.showtimes.size();i++){
				if(this.showtimes.get(i).getMovieId().equals(movie.getId())&&this.showtimes.get(i).getCineplexName().equals(cineplex.getName())){
					if(results==null)
						results = new ArrayList<Showtime>();
					results.add(this.showtimes.get(i));
				}
			}
		}
		return results;
	}
	
	private void parseJSON(JSONObject jsonObject){
		if(this.jsonObject != null){
			JSONArray jsonShowTimes = (JSONArray) jsonObject.get("showtimes");
			JSONObject jsonShowTime;
			Showtime showtime;
			for(int i=0;i<jsonShowTimes.size();i++){
				jsonShowTime = (JSONObject)jsonShowTimes.get(i);
				showtime= new Showtime();
				
				showtime.setId(jsonShowTime.get("showtimeid").toString());
				showtime.setCineplexName(jsonShowTime.get("cineplexname").toString());
				showtime.setCinemaCode(jsonShowTime.get("cinemacode").toString());
				showtime.setMovieId(jsonShowTime.get("movieid").toString());
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd-:hh:mm", Locale.ENGLISH);
				Date date = null;
				try {
					date = format.parse(jsonShowTime.get("datetime").toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				showtime.setDateTime(date);
				showtime.setCinema(new CinemaDAO().getCinemaByCinemaId(showtime.getCinemaCode()));
				showtime.setMovie(new MovieDAO().getMovieByid(showtime.getMovieId()));
				
				if(this.showtimes==null)
					this.showtimes = new ArrayList<Showtime>();
				this.showtimes.add(showtime);
			}
		}
	}
	
}
