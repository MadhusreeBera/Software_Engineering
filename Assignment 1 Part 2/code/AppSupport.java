
// AppSupport class provides support-related information for an application.
public class AppSupport {
    // Private member variables to store help documentation and contact details.
    private String helpDoc;
    private String contactDetails;

    // Setter method to set the help documentation.
    public void setHelpDoc(String helpDoc) {
        this.helpDoc = helpDoc;
    }
    // Getter method to view help documents.
    // Returns the help documentation stored in the class.
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    // Getter method to view contact details.
    // Returns the contact details stored in the class.
    public String viewHelpDocuments(){return this.helpDoc;}
    public String viewContactDetails(){return this.contactDetails;}
    
}