package model;

public class Guest {
	private String id;
	private String name;
	private String email;
	private int mobileNo;
	
	public Guest(String name, String email, int mobileNo){
		setName(name);
		setEmail(email);
		setMobileNo(mobileNo);
		setId(email+"_"+mobileNo);
	}
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
}
