package dao_module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONDAO {
	public static final String adminPath="admin.json";
	public static final String guestPath="guest.json";
	public static final String cineplexPath="cineplex.json";
	public static final String bookingPath="booking.json";
	public static final String moviePath="movie.json";
	public static final String showtimePath="showtime.json";
	public static final String hoildayPath="hoilday.json";
	public static final String pricePath="price.json";

	//@Purpose	: Update .json file
	//@param	: (name) filepath required
	//			: (JSONObject) the data to write required
	public void updateFile(String name, JSONObject data){
		BufferedWriter output = null;
        try {
            File file = new File(name);
            output = new BufferedWriter(new FileWriter(file));
            output.write(data.toJSONString());
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null )
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
	}
	
	//@purpose	: read .json file
	//@param	: (name) filepath required
	//@return	: JSONObject
	public JSONObject readFile(String name){
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
            Object obj = parser.parse(new FileReader(name));
            jsonObject = (JSONObject) obj;    
          //  System.out.println(jsonObject.toJSONString());
        } 
		catch (Exception e) {
            e.printStackTrace();
        }		
		return jsonObject;
	}
}
