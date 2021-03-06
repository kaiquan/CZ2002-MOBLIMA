package dao_module;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Admin;
import model.Cineplex;
public class AdminDAO extends JSONDAO{
	private JSONObject jsonObject = null;
	private ArrayList<Admin> admins = null;

	public AdminDAO(){
		this.jsonObject = readFile(JSONDAO.adminPath);
		parseJSON(this.jsonObject);
	}
	
	/**@purpose	: To retrieve the cineplex details for admin login
	 **@param	: String username, String password, String cineplexName
	 **@return	: null if not found/exist else Cineplecx object
	 **/
	public Cineplex getCineplexByUserNameAndPassword(String username, String password, String cineplexName){
		Cineplex result = null;
		
		if(username.isEmpty()||password.isEmpty()){
			return result;
		}
		if(this.admins!=null&&this.admins.size()>0){
			Admin temp = null;
			for(int i=0;i<this.admins.size();i++){
				temp = this.admins.get(i);
				if(temp.getUsername().equals(username)&&temp.getPassword().equals(password)&&temp.getCineplexName().equalsIgnoreCase(cineplexName)){
					result= new CineplexDAO().getCineplexByName(temp.getCineplexName());
				}
			}
		}		
		return result;
	}
	
	private void parseJSON(JSONObject jsonObject){
		if(this.jsonObject != null){
			JSONArray accounts = (JSONArray) jsonObject.get("accounts");
			JSONObject jsonAdmin;
			for(int i=0;i<accounts.size();i++){
				jsonAdmin = (JSONObject) accounts.get(i);
				if(this.admins==null)
					this.admins =  new ArrayList<Admin>();
				this.admins.add(new Admin(jsonAdmin.get("username").toString(), jsonAdmin.get("password").toString(), jsonAdmin.get("cineplexname").toString()));	 
			}
		}
	}
}
