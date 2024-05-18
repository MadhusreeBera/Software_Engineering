package flight.reservation;

import java.util.ArrayList;
import java.util.List;

public class AirportNotificationManager {
     private static List<Customer> subscriberList = new ArrayList<>();
    public static boolean subscribe (Customer dataSubs)
    {
        subscriberList.add(dataSubs);
        System.out.println(" Added customer to list of subscribed customers ");
        return true;
    }

    public void notify(String value)
    {
        for (Customer customer : subscriberList) {
            customer.update(value);
        }
    }

    public List<Customer> getSubscriberList()
    {
        return subscriberList;
    }
    
}
