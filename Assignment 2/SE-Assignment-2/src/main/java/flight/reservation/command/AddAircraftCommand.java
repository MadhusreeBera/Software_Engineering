package flight.reservation.command;

import java.util.Scanner;

import flight.reservation.AirportReservationSystem;
import flight.reservation.plane.Helicopter;

public class AddAircraftCommand implements Command {
    static Scanner sc ;
    private final AirportReservationSystem airportReservationObject;

    public AddAircraftCommand(AirportReservationSystem airportReservationObject){
        this.airportReservationObject = airportReservationObject;
    }

    @Override
    public boolean execute() {

        
        sc = new Scanner(System.in);

        System.out.println("Add aircraft type");
        String type = sc.nextLine();
        System.out.println("Enter the aircraft model: ");
        String model = sc.nextLine();
       
        sc.close();

        //create object of aircraft
        Object aircraft;
        if(type.equals("Helicopter")){
            aircraft = new Helicopter(model);
        }
        else if(type.equals("PassengerPlane")){
            aircraft = new Helicopter(model);
        }
        else if(type.equals("PassengerDrone")){
            aircraft = new Helicopter(model);
        }
        else{
            System.out.println("Invalid aircraft type");
            return false;
        }

        return airportReservationObject.addAircraft(aircraft);
    }
}
