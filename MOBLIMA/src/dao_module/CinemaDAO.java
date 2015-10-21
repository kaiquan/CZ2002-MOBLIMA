package dao_module;

import java.util.ArrayList;
import model.Cinema;
import model.Cineplex;

public class CinemaDAO extends JSONDAO{
	
	public CinemaDAO(){
	}
	
	public Cinema getCinemaByCinemaId(String cinemaId){
		Cinema results = null;
		ArrayList<Cineplex> cineplexs = new CineplexDAO().getAllCineplex();
		for(int i=0;i<cineplexs.size();i++){
			for(int x=0;x<cineplexs.get(i).getCinemas().size();x++){
				if(cineplexs.get(i).getCinemas().get(x).getCinemaCode().equalsIgnoreCase(cinemaId)){
					results=cineplexs.get(i).getCinemas().get(x);
				}
			}
		}
		return results;
	}
	public ArrayList<Cinema> getCinemaByCineplex(Cineplex cineplex){
		ArrayList<Cinema> results = null;
		ArrayList<Cineplex> cineplexs = new CineplexDAO().getAllCineplex();
		for(int i=0;i<cineplexs.size();i++){
			for(int x=0;x<cineplexs.get(i).getCinemas().size();x++){
				if(cineplexs.get(i).getName().equalsIgnoreCase(cineplex.getName())){
					if(results==null)
						results = new ArrayList<Cinema>();
					results.add(cineplexs.get(i).getCinemas().get(x));
				}
			}
		}
		return results;
	}
}
