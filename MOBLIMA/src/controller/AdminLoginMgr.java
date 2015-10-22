package controller;


import java.util.ArrayList;

import dao_module.AdminDAO;
import dao_module.CineplexDAO;
import model.Cineplex;
import view.AdminLoginView;

public class AdminLoginMgr {
	
	private AdminLoginView mAdminLoginView;
	
	CineplexDAO cDao;
	private AdminDAO admDao;
	private Cineplex mCineplex;
	
	public AdminLoginMgr(){
		this.mAdminLoginView = new AdminLoginView();
		init();
	}
	public AdminLoginMgr(AdminLoginView adminLoginView){
		this.mAdminLoginView = adminLoginView;
		init();
	}
	
	private void init(){
		if(admDao == null)
			admDao = new AdminDAO();
		if(cDao == null)
			cDao = new CineplexDAO();
	}
	
	//prompt login view
	public void prepareloginForm() {
		ArrayList<String> cineplexes = new ArrayList<String>();
		
		for(int i=0; i<cDao.getAllCineplex().size(); i++)
			cineplexes.add(cDao.getAllCineplex().get(i).getName());
		mAdminLoginView.loginForm(cineplexes);
		
	}
	
	
	public void authAdmin(String username, String password, String cineplexName){
		mCineplex = admDao.getCineplexByUserNameAndPassword(username, password, cineplexName);
		if(mCineplex == null)
			mAdminLoginView.loginFail();
		else{
			System.out.println("Login Success");
			AdminMgr aMgr = new AdminMgr();
			
		}
		
		
	}

	
	

}
