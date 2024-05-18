import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with basic details, a wallet, and functionalities like trip booking, payment, feedback, and support.
 */
public class User {
    protected String userID, username, password, email, uploadedID;
    protected int contactNo;
    float moneyDue;
    protected Wallet userWallet;
    protected List<Trip> trips;

    /**
     * Constructor to initialize a User with basic details and an associated wallet.
     */
    public User(String userID, String username, String password, String email, int contact) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.contactNo = contact;
        this.email = email;
        this.userWallet = new Wallet();
    }

    /** Uploads user ID information. */
    public boolean uploadID() {
        String id = "";
        this.uploadedID = id;
        return true;
    }

    /** Initiates a payment process for a trip. */
    public TripPayment makePayment(String mode, float amount) {
        return new TripPayment(mode, null, amount, false);
    }

    /** Initiates the booking of a trip. */
    public Trip bookTrip() {
        return TripManager.startTrip();
    }

    /** Ends an ongoing trip. */
    public void endTrip(Trip t) {
        TripManager.endTrip(t);
    }

    /** Provides feedback for a completed trip. */
    public boolean provideFeedback(Trip t) {
        Feedback f = new Feedback(t);
        f.giveFeedback();
        f.giveRating();
        return true;
    }

    /** Views support contact details and help documents. */
    public void viewSupport() {
        AppSupport s = new AppSupport();
        s.viewContactDetails();
        s.viewHelpDocuments();
    }

    /** Displays the trip history for the user. */
    public void showHistory() {
        for (Trip i : trips) {
            System.out.println(i);
        }
    }

    /** Retrieves the amount of money due. */
    public float getMoneyDue() {
        return moneyDue;
    }

    /** Sets the amount of money due. */
    public void setMoneyDue(int moneyDue) {
        this.moneyDue = moneyDue;
    }

    /** Retrieves the user's wallet. */
    public Wallet getUserWallet() {
        return this.userWallet;
    }
}

/** Represents a student, extending the basic User class. */
class Student extends User {
    private String rollNo;
    private float fees;

    /**
     * Constructor to initialize a Student with additional details.
     */
    public Student(String userID, String username, String password, String email, int contact, String rollNo,
                   float fees) {
        super(userID, username, password, email, contact);
        this.rollNo = rollNo;
        this.fees = fees;
    }

    /** Retrieves the student's fees. */
    public float getFees() {
        return this.fees;
    }

    /** Sets the student's fees. */
    public void setFees(float fees) {
        this.fees = fees;
    }

    /** Retrieves the student's roll number. */
    public String getRollNo() {
        return this.rollNo;
    }

    /** Sets the student's roll number. */
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    /** Adds an amount to the student's fees. */
    public boolean addToFees(float amount) {
        this.fees += amount;
        return true;
    }

    /** Overrides the makePayment method to handle student-specific payment scenarios. */
    @Override
    public TripPayment makePayment(String mode, float amount) {
        if ("Wallet".equals(mode)) {
            this.userWallet.deductMoney(amount);
        } else {
            this.addToFees(amount);
        }
        return new TripPayment(mode, null, amount, false);
    }
}

/** Represents a salaried user, extending the basic User class. */
class Salaried extends User {
    private String employeeID, accountNo;
    private float salary;

    /**
     * Constructor to initialize a Salaried user with additional details.
     */
    public Salaried(String userID, String username, String password, String email, int contact,
                    String employeeID, String accountNo, float salary) {
        super(userID, username, password, email, contact);
        this.employeeID = employeeID;
        this.accountNo = accountNo;
        this.salary = salary;
    }

    /** Deducts an amount from the user's salary. */
    public boolean deductFromSalary(float amount) {
        this.salary -= amount;
        return true;
    }

    /** Overrides the makePayment method to handle salaried user-specific payment scenarios. */
    @Override
    public TripPayment makePayment(String mode, float amount) {
        if ("Wallet".equals(mode)) {
            this.userWallet.deductMoney(amount);
        } else {
            this.deductFromSalary(amount);
        }
        return new TripPayment(mode, null, amount, false);
    }
}

/** Represents a staff member, extending the Salaried class. */
class Staff extends Salaried {
    private String office;

    /** Constructor to initialize a Staff member with additional details. */
    public Staff(String userID, String username, String password, String email, int contact,
                 String employeeID, String accountNo, String office, float salary) {
        super(userID, username, password, email, contact, employeeID, accountNo, salary);
        this.office = office;
    }
}

/** Represents a teacher, extending the Salaried class. */
class Teacher extends Salaried {
    private String department;

    /** Constructor to initialize a Teacher with additional details. */
    public Teacher(String userID, String username, String password, String email, int contact,
                   String employeeID, String department, String accountNo, float salary) {
        super(userID, username, password, email, contact, employeeID, accountNo, salary);
        this.department = department;
    }
}
