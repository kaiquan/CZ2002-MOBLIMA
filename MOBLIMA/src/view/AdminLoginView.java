package view;

import java.util.ArrayList;
import java.util.Scanner;
import controller.AdminLoginMgr;

public class AdminLoginView {
	
	private Scanner sc;
	private String username;
	private String password;
	private AdminLoginMgr mAdminLoginMgr;
	
	ArrayList<String> cineplexes = new ArrayList<String>();
	private int cineplexChoice;
	
	public AdminLoginView(){
		if(sc == null)
			sc= new Scanner(System.in);	
		if(mAdminLoginMgr == null)
			mAdminLoginMgr= new AdminLoginMgr(this);	
	}
	
	
	public void loginForm(ArrayList<String> cpl){
		this.cineplexes = cpl;
		loginForm();
	}
	
	public void loginForm(){
		for(int i=0; i<cineplexes.size(); i++)
			System.out.println(i+". "+cineplexes.get(i));
		
		System.out.print("Enter cineplexe choice: ");
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
