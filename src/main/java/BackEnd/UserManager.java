//make sure to state that the username needs to include a digit OR any 'special' characters and that the password should be a 4 digit number
package BackEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager {

    private ArrayList<Users> users = new ArrayList<>();

    //contructor: takes user's info from SQL and adds to ArrayList
    public UserManager() {
        try {
            String query = "SELECT * FROM menu_items_db.tblusers;";

            ResultSet rs = DB.query(query);

            //i dont want to add the userID to the ArrayList (maybe i dont have to)
            while (rs.next()) {
                String userID = rs.getString("userID");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Users user = new Users(name, username, password);
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //makes sure the username follows certain restrictions (must include letters, numbers and special characters)- username will be checked in the signup screen
    public boolean validateUsername(String username) {
        // Scanner scName = new Scanner(this.username);
        boolean hasLetters = false;
        boolean hasNumbers = false;
        boolean hasOther = false;

        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (Character.isDigit(c)) {
                hasNumbers = true;
            } else if (Character.isLetter(c)) {
                hasLetters = true;
            } else {
                hasOther = true;
            }
        }

        return hasLetters && hasNumbers && hasOther;
    }

    //validates password: makes sure it's only digits and is not longer than 4 digits
    public boolean validatePassword(String inPassword) {
        boolean hasDigit = true;
        boolean correctLength = true;

        if (inPassword.length() > 4) {
            correctLength = false;
        }

        for (int i = 0; i < inPassword.length(); i++) {
            char c = inPassword.charAt(i);

            if (Character.isLetter(c)) {
                hasDigit = false;
            } else if (!Character.isLetterOrDigit(c)) {
                hasDigit = false;
            }
        }
        return hasDigit && correctLength;
    }

    //gets the name of the waiter by using their username and password
    public String getName(String inUsername, String inPassword) {
        String name = "";
        try {
            String query = "SELECT name FROM menu_items_db.tblusers WHERE username = '" + inUsername + "' AND password = '" + inPassword + "';";
            ResultSet rs = DB.query(query);

            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    //check if the user is actually stored in the database
    public boolean checkIfUserIsRegistered(String inUserName, String inPassword) throws SQLException {
        String query = "SELECT * FROM menu_items_db.tblusers ;";
        ResultSet rs = DB.query(query);

        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");

            if (!username.equals(inUserName) && !password.equals(inPassword)) {
                return false;
            }
        }
        return true;
    }
    
    public void addUser(String inName, String inUsername, String inPassword) throws SQLException{
    //String id = items.getLast().getMenuID();
    String query = "INSERT INTO `menu_items_db`.`tblusers` (`name`, `username`, `password`) VALUES ('" + inName + "', '" + inUsername + "', '" + inPassword + "');";
    DB.update(query);
    }
}
