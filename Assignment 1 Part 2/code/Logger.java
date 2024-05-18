import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Interface defining the contract for loggers which will give log to implement
//this logger class will be used all of the component whoever want to log 

interface Logger {
    void log(String message);
}

// Database logger implementation that logs messages to the database user his own logic.
class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[Database Logger :] " + getCurrentTimestamp() + " " + message);
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}

// App logger implementation that logs messages to the appmemory in device.
class AppLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[App Logger :] " + getCurrentTimestamp() + " " + message);
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}

// Composite logger that contains a list of loggers 
//and provide a commong log function to log into all loggers
class CompositeLogger implements Logger {
    private List<Logger> loggers = new ArrayList<>();

    public void addLogger(Logger logger) {
        loggers.add(logger);
    }

    @Override
    public void log(String message) {
        for (Logger logger : loggers) {
            logger.log(message);
        }
    }
}

