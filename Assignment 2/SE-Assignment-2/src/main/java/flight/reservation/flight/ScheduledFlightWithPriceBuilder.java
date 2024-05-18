package flight.reservation.flight;

public class ScheduledFlightWithPriceBuilder extends ScheduledFlightBuilder{

    public ScheduledFlightBuilder setCurrentPrice(double currentPrice){
        super.currentPrice = currentPrice;
        return this;
    }
}
