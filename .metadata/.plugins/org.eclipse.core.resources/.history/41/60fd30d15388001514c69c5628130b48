package view;

import java.util.ArrayList;
import controller.AdminLoginMgr;

public class AdminLoginView extends Aview{

	private String username;
	private String password;
	private AdminLoginMgr mAdminLoginMgr;
	
	ArrayList<String> cineplexes = new ArrayList<String>();
	private int cineplexChoice;
	
	public AdminLoginView(){
		if(mAdminLoginMgr == null)
			mAdminLoginMgr= new AdminLoginMgr(this);	
	}
	
	public AdminLoginView(AdminLoginMgr mAdmLgnMgr){
		this.mAdminLoginMgr = mAdmLgnMgr;
	}
	
	public void loginForm(ArrayList<String> cpl){
		this.cineplexes = cpl;
		loginForm();
	}
	
	public void loginForm(){
		System.out.println();
		for(int i=0; i<cineplexes.size(); i++)
			System.out.println(i+". "+cineplexes.get(i));
		
		System.out.print("Enter cineplex choice: ");
		cineplexChoice = sc.nextInt();
		
		System.out.print("Enter username: ");
		username = sc.next();
	
		System.out.print("Enter password: ");
		password = sc.next();
		
		mAdminLoginMgr.authAdmin(username, password, cineplexes.get(cineplexChoice));
		
	}
	
	public void loginFail(){
		System.out.println("Login Fail..Retry");
		loginForm();	
	}

}
