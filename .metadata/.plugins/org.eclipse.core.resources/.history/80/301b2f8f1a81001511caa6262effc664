package controller;

import java.util.ArrayList;
import dao_module.AdminDAO;
import dao_module.CineplexDAO;
import model.Cineplex;
import view.AdminLoginView;

public class AdminLoginMgr {
	
	private AdminLoginView mAdminLoginView;

	private Cineplex mCineplex;
	
	
	public AdminLoginMgr(){
		this.mAdminLoginView = new AdminLoginView();
	}
	public AdminLoginMgr(AdminLoginView adminLoginView){
		this.mAdminLoginView = adminLoginView;
	}
		
	//prompt login view
	public void prepareloginForm() {
		ArrayList<String> cineplexes = new ArrayList<String>();	
		CineplexDAO cDao = new CineplexDAO();
		for(int i=0; i<cDao.getAllCineplex().size(); i++)
			cineplexes.add(cDao.getAllCineplex().get(i).getName());
		mAdminLoginView.loginForm(cineplexes);
		
	}
	
	
	//authenticate admin
	public void authAdmin(String username, String password, String cineplexName){

		AdminDAO admDao = new AdminDAO();
		mCineplex = admDao.getCineplexByUserNameAndPassword(username, password, cineplexName);
		if(mCineplex == null)
			mAdminLoginView.loginFail();
		else{
			AdminMgr aMgr = new AdminMgr(username, mCineplex);	
			aMgr.prepareAdminForm();
		}
	}

}
