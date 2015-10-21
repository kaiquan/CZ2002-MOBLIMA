package model;

public class Review {
	
	private String review;
	private int rating;
	private String guestId;
	private String guestName;
	private Movie movie;
	
	public Review(String review, int rating, String guestName, Movie movie){
		setReview(review);
		setRating(rating);
		setMovie(movie);
		setGuestName(guestName);
	}
	
	public Review() {
		// TODO Auto-generated constructor stub
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
}
