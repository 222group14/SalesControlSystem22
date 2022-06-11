package src.structure;

import java.util.HashMap;

import src.user.User;

/**
 * Sales Constrol System
 */
public class SCSystem {
    /** All the companies that uses this system */
    private Company[] companies; 
    /** All the registered users (key:username value:password) */
    // private HashMap<String, String> users;  
    private HashMap<User, String> users;  

    public SCSystem () {
        //! PSEUDO CODE FOR SCSYSTEM (update if necessary)

        // the system is kept in a binary file like database
        // upload the system informations (companies and users)
    
        // ----------- MENU ----------- (runs till user exit)
            // Take user enterance (username and password)
            
            // Check if the user exist (searching and password validation)
            // if it doesn't exist then provide a registeration 

            // Determine the related company that users use (another alternative can be find like taking the company name from the user)
            // Display the user information (Company name and username at the top)

            // Display action list (add/remove/display stuff)
                // three different list for admin, branch manager and employee

        // before exit, save the current status of system
    }
}
