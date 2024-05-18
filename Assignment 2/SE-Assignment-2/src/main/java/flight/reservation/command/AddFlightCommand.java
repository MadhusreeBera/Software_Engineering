package flight.reservation.command;

import java.util.Scanner;

import flight.reservation.AirportReservationSystem;
import flight.reservation.flight.Flight;

public class AddFlightCommand implements Command{
    static Scanner sc ;
    private final AirportReservationSystem airportReservationObject;

    public AddFlightCommand(AirportReservationSystem airportReservationObject){
        this.airportReservationObject = airportReservationObject;
    }
    @Override
    public boolean execute() {
        // adding flight requires to know the list of Airports
        System.out.println("List of Airports: ");
        for(int i=0; i<airportReservationObject.getAirports().size(); i++){
            System.out.println((i+1) + ". " + airportReservationObject.getAirports().get(i).getName());
        }  
        // list of available aircrafts
        System.out.println("List of Aircrafts: ");
        for(int i=0; i<airportReservationObject.getAircrafts().size(); i++){
            System.out.println((i+1) + ". " + airportReservationObject.getAircrafts().get(i).toString());
        }      

        sc = new Scanner(System.in);
        System.out.println("Enter the flight number: ");
        int number = sc.nextInt();
        System.out.println("Enter the serial number of departure airport: ");
        int departureIndex = sc.nextInt();
        System.out.println("Enter the serial number of arrival airport: ");
        int arrivalIndex = sc.nextInt();

        System.out.println("Enter the serial number of aircraft: ");
        int aircraftIndex = sc.nextInt();
        
        sc.close();
        
        if(departureIndex <= 0 || arrivalIndex <= 0 || aircraftIndex <= 0 || departureIndex > airportReservationObject.getAirports().size() || arrivalIndex > airportReservationObject.getAirports().size() || aircraftIndex > airportReservationObject.getAircrafts().size()){
            System.out.println("Invalid serial number. Please try again.");
            return false;
        }
        
        Flight flight = new Flight(number, airportReservationObject.getAirports().get(departureIndex-1), airportReservationObject.getAirports().get(arrivalIndex-1), airportReservationObject.getAircrafts().get(aircraftIndex-1));
        return airportReservationObject.addFlight(flight);
  
    }
    
}
