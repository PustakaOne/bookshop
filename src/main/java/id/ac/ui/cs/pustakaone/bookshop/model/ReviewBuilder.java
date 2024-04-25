package id.ac.ui.cs.pustakaone.bookshop.model;

import java.util.Date;

public interface ReviewBuilder {
    public void reset();
    public void setId(String id);
    public void setBook(Book book);
    public void setUser(User user);
    public void setReview(String reviewText);
    public void setRating(int rating);
    public void setCreatedAt(Date createdAt);
    public void setUpdatedAt(Date updatedAt);
    public Review build();
}
