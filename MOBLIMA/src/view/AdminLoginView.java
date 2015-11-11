package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import controller.AdminLoginMgr;

public class AdminLoginView extends Aview{

	/**
	 * AdminLoginMgr to process admin login
	 */
	private AdminLoginMgr mAdminLoginMgr;
	
	/**
	 * an arraylist to store all the cineplexes available in the system
	 */
	ArrayList<String> cineplexes = new ArrayList<String>();

	/**
	 * initalize and create a new AdminLoginMgr object
	 */
	public AdminLoginView(){
		if(mAdminLoginMgr == null)
			mAdminLoginMgr= new AdminLoginMgr(this);	
	}
	
	/**
	 * reuse the AdminLoginMgr object
	 * @param mAdmLgnMgr
	 */
	public AdminLoginView(AdminLoginMgr mAdmLgnMgr){
		this.mAdminLoginMgr = mAdmLgnMgr;
	}
	

	/**
	 * Login form
	 * @param cpl - arraylist of all the cineplexes
	 */
	public void loginForm(ArrayList<String> cpl){
		this.cineplexes = cpl;
		loginForm();
	}
	
	/**
	 * Login form
	 */
	public void loginForm(){
		
		String username;
		String password;
		int cineplexChoice;
		System.out.println();
		
		try {
		for(int i=0; i<cineplexes.size(); i++)
			System.out.println(i+". "+cineplexes.get(i));
		
		System.out.print("Enter cineplex choice: ");
		cineplexChoice = sc.nextInt();
		
		System.out.print("Enter username: ");
		username = sc.next();
	
		System.out.print("Enter password: ");
		password = sc.next();
		
		
			mAdminLoginMgr.authAdmin(username, password, cineplexes.get(cineplexChoice));
		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());			
		}
		
	}
	
	/**
	 * display a login fail message then display login form again to try login again
	 */
	public void loginFail(){
		System.out.println("Login Fail..Retry");
		loginForm();	
	}

}
