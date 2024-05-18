import java.util.List;


// The ParkingLot class represents a parking facility for SmartVehicles.
// It maintains a list of SmartVehicles, has a specified capacity, and provides methods
// to add and remove vehicles from the list. Additionally, it can check whether there
// is available space for parking.

// Parking lot class has list of vehicle so it is connected to vehcile class and managed by Parkinglotmanager
public class ParkingLot {
    private String parkingLotID, address;
    private List<SmartVehicle> vehicleList;
    private int capacity;

    public ParkingLot(String parkingLotID, String address) {
        this.parkingLotID = parkingLotID;
        this.address = address;
    }

    public void addToList(SmartVehicle v) {}
    public void removeFromList(SmartVehicle v) {}

    public boolean isSpaceAvailable() {
        if (vehicleList.size() < capacity) {
            return true;
        }
        return false;
    }
}
