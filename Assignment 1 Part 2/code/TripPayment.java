/*
   The TripPayment class represents a payment made for a trip in a transportation system.
   It contains attributes such as paymentId, paymentAmount, timestamp, and paymentStatus.

   Attributes:
   - paymentId: Unique identifier for the payment.
   - paymentAmount: The amount paid for the trip.
   - timestamp: Date and time when the payment was made.
   - paymentStatus: Indicates whether the payment is successful or not.

   Constructor:
   - Initializes a TripPayment object with the provided paymentId, timestamp, paymentAmount, and paymentStatus.

   Getters and Setters:
   - Getters provide access to the private attributes.
   - Setters allow modifying the paymentStatus and paymentAmount attributes.
*/


import java.time.LocalDateTime;

public class TripPayment {

    private String paymentId;
    private float paymentAmount;
    private LocalDateTime timestamp;
    private boolean paymentStatus;

    // Constructor
    public TripPayment(String paymentId, LocalDateTime timestamp, 
                        float paymentAmount, boolean paymentStatus) {
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.timestamp = timestamp;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters for all attributes of the Payment object
    public String getPaymentId() {
        return paymentId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public Boolean getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
