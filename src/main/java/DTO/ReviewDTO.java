package DTO;

public class ReviewDTO extends DTO{	 
    
    public ReviewDTO() {}
    
    private String review;
    
    private int rating;
    
    private OnlyIdObject user;
    
    private OnlyIdObject product;

	public OnlyIdObject getUser() {
		return user;
	}

	public void setUser(OnlyIdObject user) {
		this.user = user;
	}

	public OnlyIdObject getProduct() {
		return product;
	}

	public void setProduct(OnlyIdObject product) {
		this.product = product;
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
}
