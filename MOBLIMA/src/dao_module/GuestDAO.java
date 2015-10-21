package dao_module;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.Guest;

public class GuestDAO extends JSONDAO{
	private JSONObject jsonObject = null;
	private ArrayList<Guest> guests;
	
	public GuestDAO(){
		this.jsonObject = readFile(JSONDAO.guestPath);
		parseJSON(this.jsonObject,false);
	}
	
	
	//@purpose	: create a new guest data
	//@param		: required
	@SuppressWarnings("unchecked")
	public void createNewGuest(Guest guest,String cineplexName){
		JSONArray jsonGuests = (JSONArray) jsonObject.get("guests");
		boolean exists=  false;
		for(int i=0;i<this.guests.size();i++){
			if(this.guests.get(i).getEmail().equals(guest.getEmail())&&this.guests.get(i).getMobileNo()==guest.getMobileNo()&&this.guests.get(i).getCineplexName().equals(cineplexName)){
				//System.out.println("Account already exists");
				exists=true;
				break;
			}
		}
		if(!exists){
			JSONObject jsonGuest = new JSONObject();
			jsonGuest.put("id", guest.getId());
			jsonGuest.put("name", guest.getName());
			jsonGuest.put("mobileno", guest.getMobileNo());
			jsonGuest.put("email", guest.getEmail());
			jsonGuest.put("cineplexname", cineplexName);
			
			jsonGuests.add(jsonGuest);
		}
		updateFile(JSONDAO.guestPath, this.jsonObject);
	}
	
	//@purpose	: get Guest details by email & mobileNo
	//@param	: required
	//@return	: return null if not found/exist
	public Guest getGuestByEmailAndMobileNoANDCineplexName(String email, int mobileNo,String cineplexName){
		Guest result = null;
		if(this.guests!=null&&this.guests.size()>0){
			for(int i=0;i<this.guests.size();i++){
				if(this.guests.get(i).getEmail().equals(email)&&this.guests.get(i).getMobileNo()==mobileNo&&this.guests.get(i).getCineplexName().equals(cineplexName)){
					result = this.guests.get(i);
				}
			}
		}
		return result;
	}
	
	//@purpose	: get Guest details by id
	//@param	: required
	//@return	: return null if not found/exist
	public Guest getGuestByIdANDCineplexName(String id,String cineplexName){
		Guest result = null;
		if(this.guests!=null&&this.guests.size()>0){
			for(int i=0;i<this.guests.size();i++){
				if(this.guests.get(i).getId().equals(id)&&this.guests.get(i).getCineplexName().equals(cineplexName)){
					result = this.guests.get(i);
				}
			}
		}
		return result;
	}
	
	private void parseJSON(JSONObject jsonObject, boolean INCLUDE_BOOKINGS){
		if(this.jsonObject != null){
			JSONArray jsonGuests = (JSONArray) jsonObject.get("guests");
			JSONObject jsonGuest;
			Guest guest;
			for(int i=0;i<jsonGuests.size();i++){
				jsonGuest = (JSONObject)jsonGuests.get(i);
				if(this.guests==null)
					this.guests = new ArrayList<Guest>();
				guest = new Guest();
				guest.setId(jsonGuest.get("id").toString());
				guest.setName(jsonGuest.get("name").toString());
				guest.setEmail(jsonGuest.get("email").toString());
				guest.setCineplexName(jsonGuest.get("cineplexname").toString());
				guest.setMobileNo(Integer.parseInt(jsonGuest.get("mobileno").toString()));
				
				if(INCLUDE_BOOKINGS)
					guest.setBookings(new BookingDAO().getBookingsByGuest(guest));
				
				this.guests.add(guest);
			}
		}
	}
}
