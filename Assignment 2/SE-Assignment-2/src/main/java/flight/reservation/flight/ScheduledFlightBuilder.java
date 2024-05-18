package flight.reservation.flight;

import flight.reservation.Airport;
import flight.reservation.Passenger;

import java.util.Date;
import java.util.List;

public abstract class ScheduledFlightBuilder {

    private List<Passenger> passengers ;
    private Date departureTime;
    protected double currentPrice = 100;
    private int number;
    private Airport departure;
    private Airport arrival;
    protected Object aircraft;


    public ScheduledFlightBuilder setNumber(int number) {
        this.number = number;
        return this;
    }
    public ScheduledFlightBuilder setDeparture(Airport departure) {
        this.departure = departure;
        return this;
    }
    public ScheduledFlightBuilder setArrival(Airport arrival) {
        this.arrival = arrival;
        return this;
    }
    public ScheduledFlightBuilder setAircraft(Object aircraft) {
        this.aircraft = aircraft;
        return this;
    }
    public ScheduledFlight build(){
        return new ScheduledFlight(this);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public int getNumber() {
        return number;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public Object getAircraft() {
        return aircraft;
    }
}
