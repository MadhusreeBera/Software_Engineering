package flight.reservation.flight;

import flight.reservation.Airport;
import java.util.Scanner;

import java.util.Date;

public class ScheduledFlightDirector {
    ScheduledFlightBuilder scheduledFlightBuilder;
    static Scanner sc = new Scanner(System.in);

    public ScheduledFlightDirector(ScheduledFlightBuilder scheduledFlightBuilder) {
        this.scheduledFlightBuilder = scheduledFlightBuilder;
    }

    private ScheduledFlight createScheduledFlightWithoutPrice(Airport departure, Airport arrival, Object aircraft){
        System.out.print("Enter flight number: ");
        int flight_no = sc.nextInt();
        return scheduledFlightBuilder.setNumber(flight_no).setDeparture(departure).setArrival(arrival).setAircraft(aircraft).build();
    }

    private ScheduledFlight createScheduledFlightWithPrice(Airport departure, Airport arrival, Object aircraft){
        System.out.print("Enter flight number: ");
        int flight_no = sc.nextInt();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        ScheduledFlightBuilder temp =  scheduledFlightBuilder.setNumber(flight_no).setDeparture(departure).setArrival(arrival).setAircraft(aircraft);
        return ((ScheduledFlightWithPriceBuilder)temp).setCurrentPrice(price).build();
    }

    public ScheduledFlight createScheduledFlight(Airport departure, Airport arrival, Object aircraft){
        if(scheduledFlightBuilder instanceof ScheduledFightWithoutPriceBuilder)
            return createScheduledFlightWithoutPrice( departure,  arrival,  aircraft);
        else if(scheduledFlightBuilder instanceof ScheduledFlightWithPriceBuilder)
            return createScheduledFlightWithPrice( departure,  arrival,  aircraft);
        return null;
    }
}
