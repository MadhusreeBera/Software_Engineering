import java.util.List;

/**
 * Manages user accounts, providing account operations and logging.
 */
public class UserAccountManager {
    private List<User> listOfUsers; // List of user accounts.
    private Logger logger;          // Logger instance.

    /**
     * Constructor to initialize UserAccountManager with a Logger.
     * @param logger The Logger used for logging.
     */
    public UserAccountManager(Logger logger) {
        this.logger = logger;
    }

    /** Creates a new user account with default values. */
    public User createAccount() {
        return new User(null, null, null, null, 0);
    }

    /** Deletes a user account identified by userID. */
    public boolean deleteAccount(String userID) {
        return true;
    }

    /** Performs user login with a username and password. */
    public boolean login(String uname, String password) {
        return true;
    }

    /** Logs out the provided user. */
    public boolean logout(User u) {
        return true;
    }

    /** Changes the password for the provided user. */
    public boolean changePassword(User u) {
        return true;
    }

    /** Logs a message using the associated Logger. */
    public boolean log(String message) {
        logger.log(message);
        return true;
    }
}
