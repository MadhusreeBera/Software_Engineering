/**
 * The Trip class represents a journey made by a SmartVehicle from a source ParkingLot to a destination ParkingLot.
 * It includes details such as trip ID, source and destination ParkingLots, the SmartVehicle used, the User making the trip,
 * total distance traveled, total amount charged, payment details, timestamps for trip start and end, information about whether
 * the trip is inside the campus, and user feedback.
 */


import java.time.LocalDateTime;

public class Trip {
    private String tripId;
    private ParkingLot source, destination;
    private SmartVehicle tripVehicle;
    private User tripUser;
    private float totalDistance, totalAmount;
    private TripPayment tripPayment;
    private LocalDateTime startTimestamp, endTimestamp;
    private Boolean isInsideCampus;
    private Feedback feedback;

    public Trip(String tripId, ParkingLot source, ParkingLot destination,
                SmartVehicle tripVehicle, User tripUser,
                Float totalDistance, Float totalAmount,
                TripPayment tripPayment, LocalDateTime startTimestamp, 
                LocalDateTime endTimestamp, Boolean isInsideCampus) {
        this.tripId = tripId;
        this.source = source;
        this.tripUser = tripUser;
        this.startTimestamp = startTimestamp;
    }
    
    public String getTripId() {
        return tripId;
    }

    public ParkingLot getSource() {
        return source;
    }

    public ParkingLot getDestination() {
        return destination;
    }
    public void setDestination(ParkingLot destination) {
        this.destination = destination;
    }

    public SmartVehicle getTripVehicle() {
        return tripVehicle;
    }
    public void setTripVehicle(SmartVehicle tripVehicle) {
        this.tripVehicle = tripVehicle;
    }

    public User getTripUser() {
        return tripUser;
    }
    
    public float getTotalDistance() {
        return totalDistance;
    }
    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TripPayment getTripPayment() {
        return tripPayment;
    }
    public void setTripPayment(TripPayment tripPayment) {
        this.tripPayment = tripPayment;
    }

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }
    
    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }
    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Boolean getIsInsideCampus() {
        return isInsideCampus;
    }
    public void setIsInsideCampus(Boolean isInsideCampus) {
        this.isInsideCampus = isInsideCampus;
    }
}
