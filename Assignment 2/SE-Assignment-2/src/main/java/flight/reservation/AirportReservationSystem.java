package flight.reservation;

import java.util.List;

import flight.reservation.flight.Flight;

public class AirportReservationSystem {
    private static List<Airport> airports;
    private static List<Object> aircrafts;
    private static List<Flight> flights;

    //getters
    public List<Airport> getAirports() {
        return airports;
    }

    public List<Object> getAircrafts() {
        return aircrafts;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    
    public boolean addAirport(Airport airport) {
        return airports.add(airport);
    }

    public boolean addAircraft(Object aircraft) {
        return aircrafts.add(aircraft);
    }

    public boolean addFlight(Flight flight) {
        return flights.add(flight);
    }

   

}
