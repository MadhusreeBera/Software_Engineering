// Feedback class represents user feedback for a trip.
public class Feedback {
    // Private member variables to store feedback-related information.
    private String feedbackID;
    private String feedbackTxt;
    private int rate;
    private Trip trip; // Reference to the associated Trip.

    // Constructor to create a Feedback object associated with a Trip.
    public Feedback(Trip t) {
        this.trip = t;
    }

    public String getFeedbackID() {
        return this.feedbackID;
    }

    public String getFeedbackTxt() {
        return this.feedbackTxt;
    }

    public int getRate() {
        return this.rate;
    }

    // Method to edit the existing feedback.
    // Returns true if the feedback is successfully edited.
    public boolean editFeedback() {
        String newFeedback = "";
        this.feedbackTxt = newFeedback;
        return true;
    }

    // Method to give a feedBack for the trip.
    // Returns true if the rating is successfully given.
    public boolean giveFeedback() {
        String feedback = "";
        this.feedbackTxt = feedback;
        return true;
    }

    // Method to give a rating for the trip.
    // Returns true if the rating is successfully given.
    public boolean giveRating() {
        int rating = 0;
        this.rate = rating;
        return true;
    }
}
