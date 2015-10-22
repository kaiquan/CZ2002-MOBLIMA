package view;

public class AdminView {
	
	private String username;

	public AdminView(String username, String cineplex) {
		this.username = username;
		System.out.println("Welcome "+username);
	}
	
	
	
	public void displayAdminOptions(){
		System.out.println("1. Add New Movie");
		System.out.println("2. Edit Movie");
		System.out.println("3. Schedule Show Time");
		System.out.println("4. Logout");	

	}

}
