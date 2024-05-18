package flight.reservation.command;

import java.util.Scanner;

import flight.reservation.Airport;
import flight.reservation.AirportReservationSystem;

public class AddAirportCommand implements Command{
    static Scanner sc ;
    private final AirportReservationSystem airportReservationObject;

    public AddAirportCommand(AirportReservationSystem airportReservationObject){
        this.airportReservationObject = airportReservationObject;
    }

    @Override
    public boolean execute() {
        sc = new Scanner(System.in);
        System.out.println("Enter the airport name: ");
        String name = sc.nextLine();
        System.out.println("Enter the airport code: ");
        String code = sc.nextLine();
        System.out.println("Enter the airport location: ");
        String location = sc.nextLine();
        sc.close();

        Airport airport = new Airport(name, code, location);
        return airportReservationObject.addAirport(airport);
    }
    
}
