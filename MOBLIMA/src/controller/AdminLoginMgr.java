package controller;

import java.util.ArrayList;
import dao_module.AdminDAO;
import dao_module.CineplexDAO;
import model.Cineplex;
import view.AdminLoginView;

public class AdminLoginMgr {
	
	/**
	 * Admin View that contains the login view
	 */
	private AdminLoginView mAdminLoginView;

	/**
	 * cineplex of the admin
	 */
	private Cineplex mCineplex;
	
	/**
	 * Create a new adminLoginView object
	 */
	public AdminLoginMgr(){
		this.mAdminLoginView = new AdminLoginView();
	}
	
	/**
	 * Reuse the adminLoginView
	 * @param adminLoginView
	 */
	public AdminLoginMgr(AdminLoginView adminLoginView){
		this.mAdminLoginView = adminLoginView;
	}
		
	/**
	 * prompt login form
	 */
	public void prepareloginForm() throws Exception{
		ArrayList<String> cineplexes = new ArrayList<String>();	
		CineplexDAO cDao = new CineplexDAO();
		for(int i=0; i<cDao.getAllCineplex().size(); i++)
			cineplexes.add(cDao.getAllCineplex().get(i).getName());
		mAdminLoginView.loginForm(cineplexes);
		
	}
	
	/**
	 * Authenticate admin
	 * @param username
	 * @param password
	 * @param cineplexName
	 */
	public void authAdmin(String username, String password, String cineplexName) throws Exception{

		AdminDAO admDao = new AdminDAO();
		mCineplex = admDao.getCineplexByUserNameAndPassword(username, password, cineplexName);
		if(mCineplex == null)
			mAdminLoginView.loginFail();
		else{
			AdminMgr aMgr = new AdminMgr(username, mCineplex);	
			aMgr.prepareAdminMenu();
		}
	}

}
