package view;

import java.util.ArrayList;
import controller.AdminLoginMgr;

public class AdminLoginView extends AView{

	private String username;
	private String password;
	private AdminLoginMgr mAdminLoginMgr;
	
	ArrayList<String> cineplexes = new ArrayList<String>();
	private int cineplexChoice;
	
	public AdminLoginView(){
		if(mAdminLoginMgr == null)
			mAdminLoginMgr= new AdminLoginMgr(this);	
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
		sc.nextLine();
		System.out.print("Enter username: ");
		username = sc.nextLine();
	
		System.out.print("Enter password: ");
		password = sc.nextLine();
		
		mAdminLoginMgr.authAdmin(username, password, cineplexes.get(cineplexChoice));
		
	}
	
	public void loginFail(){
		System.out.println("Login Fail..Retry");
		loginForm();	
	}

}
