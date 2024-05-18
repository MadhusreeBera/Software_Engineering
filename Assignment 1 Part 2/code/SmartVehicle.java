/*
   The code defines a system for managing smart vehicles with different types (Bike, Moped, Bicycle).
   The SmartVehicle class encapsulates common properties such as a unique identifier, registration number,
   vehicle type, availability status, rental rates, repair status, location, and an associated QR code.

   Three specific types of vehicles, Bike, Moped, and Bicycle, extend the SmartVehicle class, each having
   additional properties like charge status for Bike, charge status for Moped, and number of gears for Bicycle.

   The ParkingLot class represents a parking lot where smart vehicles are parked. Each SmartVehicle has a
   reference to the ParkingLot where it is currently located.

   Overall, the system is designed to manage the parking, availability, and booking of smart vehicles,
   providing a foundation for a transportation or rental system.
*/

enum VehicleType {
    BIKE,
    MOPED,
    BICYCLE
}

// SmartVehicle class represents a generic smart vehicle with common properties.

class SmartVehicle {
		//vehicle type, availability status, rental rates, repair status, location, and associated QR code.

    private String vehicleId;
    private String registrationNo;
    private VehicleType vehicleType;
    private boolean isAvailable;
    private float baseRate;
    private float excessRate;
    private boolean needRepair;
    private ParkingLot vehicleLocation;
    private QRCode qrCode;
    
    
    // Constructor to initialize a SmartVehicle with its properties.

    public SmartVehicle(String vehicleId, String registrationNo, VehicleType vehicleType,
                        boolean isAvailable, float baseRate, float excessRate,
                        boolean needRepair, ParkingLot vehicleLocation, QRCode qrCode) {
        this.vehicleId = vehicleId;
        this.registrationNo = registrationNo;
        this.vehicleType = vehicleType;
        this.isAvailable = isAvailable;
        this.baseRate = baseRate;
        this.excessRate = excessRate;
        this.needRepair = needRepair;
        this.vehicleLocation = vehicleLocation;
        this.qrCode = qrCode;
    }

    public void getVehicleDetails() {}

    public void setNeedRepair(boolean status) {
        this.needRepair = status;
    }

    public void bookVehicle() {
        this.isAvailable = false;
    }

    public void releaseVehicle() {
        this.isAvailable = true;
    }
}


// Bike class represents a specific type of smart vehicle - Bike.
class Bike extends SmartVehicle {
    private int chargeStatus;
		
    // Constructor to initialize a Moped with its properties.
    public Bike(String vehicleId, String registrationNo, boolean isAvailable,
                float baseRate, float excessRate, boolean needRepair,
                ParkingLot vehicleLocation, QRCode qrCode, int chargeStatus) {
        super(vehicleId, registrationNo, VehicleType.BIKE, isAvailable,
                baseRate, excessRate, needRepair, vehicleLocation, qrCode);
        this.chargeStatus = chargeStatus;
    }
}

// Moped class represents a specific type of smart vehicle - Moped.
class Moped extends SmartVehicle {
    private int chargeStatus;

    public Moped(String vehicleId, String registrationNo, boolean isAvailable,
                 float baseRate, float excessRate, boolean needRepair,
                 ParkingLot vehicleLocation, QRCode qrCode, int chargeStatus) {
        super(vehicleId, registrationNo, VehicleType.MOPED, isAvailable,
                baseRate, excessRate, needRepair, vehicleLocation, qrCode);
        this.chargeStatus = chargeStatus;
    }
}


// Bicycle class represents a specific type of smart vehicle - Bicycle.
class Bicycle extends SmartVehicle {
    private int noOfGears;

    public Bicycle(String vehicleId, String registrationNo, boolean isAvailable,
                   float baseRate, float excessRate, boolean needRepair,
                   ParkingLot vehicleLocation, QRCode qrCode, int noOfGears ) {
        super(vehicleId, registrationNo, VehicleType.BICYCLE, isAvailable,
                baseRate, excessRate, needRepair, vehicleLocation, qrCode);
        this.noOfGears = noOfGears;
    }
}

