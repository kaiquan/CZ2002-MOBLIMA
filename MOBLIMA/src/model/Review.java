package model;

public class Review {
	
	private String review;
	private int rating;
	private String guestId;
	
	public Review(String review, int rating, Guest guest){
		setReview(review);
		setRating(rating);
		setGuestId(guest.getId());
	}
	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
}
