package flight.reservation;

public class AirportPublisher {
    public String airportData;
    public AirportNotificationManager manager;

    public AirportPublisher()
    {
        this.manager = new AirportNotificationManager();
    }

    public void setAirportData(String data)
    {
        this.airportData  = data;
        this.manager.notify(this.airportData);

    }

}
