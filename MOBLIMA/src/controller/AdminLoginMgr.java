package controller;


import dao_module.AdminDAO;
import model.Admin;
import model.Cineplex;
import view.AdminLoginView;;

public class AdminLoginMgr {
	
	private AdminLoginView mAdminLoginView;
	
	private AdminDAO admDao;
	private Cineplex mCineplex;
	
	public AdminLoginMgr(AdminLoginView adminLoginView){
		this.mAdminLoginView = adminLoginView;
	}
	
	
	public void authAdmin(String username, String password, String cineplexName){
		if(admDao == null)
			admDao = new AdminDAO();
		
		mCineplex = admDao.getCineplexByUserNameAndPassword(username, password, cineplexName);
		if(mCineplex == null)
			mAdminLoginView.loginFail();
		else{
			System.out.println("Login Success");
			
		}
		
		
	}
	
	

}
