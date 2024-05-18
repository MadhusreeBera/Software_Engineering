import java.util.List;

// The ParkingLotManager class manages a collection of ParkingLots and provides
// functionality to add new lots, remove lots, and display details. It also utilizes
// a Logger to log messages related to parking lot management.

public class ParkingLotManager {
    private List<ParkingLot> parkingLots;
    private Logger logger;

    public ParkingLotManager(Logger logger) {
        this.logger = logger;
    }
    
    public void addNewParkingLot(){}
    public void removeParkingLot(){}
    public void displayParkingLotDetails() {}
    
    public boolean log(String message) {
        logger.log(message);
        return true;
    }
}
