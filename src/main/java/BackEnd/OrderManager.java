// this class is for the 'Order' tab, this class will help users to make orders and remove certain items from the order
package BackEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderManager {

    private ArrayList<OrderedItems> orders = new ArrayList<>();

    //constructor
    public OrderManager() {
        //getSelectedMenu(inCategory);
    }

    //gets the menu based on which category the user selected
    public void getSelectedMenu(String inCategory) {
        try {
            String query = "SELECT *  FROM menu_items_db.menu WHERE category = '" + inCategory + "';";

            ResultSet rs = DB.query(query);

            while (rs.next()) {
                String id = rs.getString("itemID");
//dont really think i need this:
//String category = rs.getString("category");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                OrderedItems oi = new OrderedItems(inCategory, name, description, price);
                orders.add(oi);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuItemManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //gets the description of the selected item (by using the item's id)
    public String descriptionOfSelectedItem(String inItemID) {

        String description = "";
        try {
            String query = "SELECT description  FROM menu_items_db.tblmenu WHERE itemID = '" + inItemID + "';";
            ResultSet rs = DB.query(query);
            
            while (rs.next()) {                
                description = rs.getString("description");
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return description;
    }

    //removes an unwanted item from the 'ordered' table (items are given a 'temp' itemID - so it doesnt get mixed up w/ other similar items)
    //i dont really know the difference between this method and the one below
    public void removeFromOrder(String inItemID) {
        try {
            String query = "DELETE FROM `menu_items_db`.`tblorders` WHERE (`itemID` = '" + inItemID + "');";
            DB.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //this is for the right side of the Orders screen
    public void deleteFromOrder(String inName) {
        try {
            String query = "DELETE FROM `menu_items_db`.`tblmenu` WHERE (name = '" + inName + "');";
            DB.update(query);
        } catch (SQLException ex) {
            Logger.getLogger(OrderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
