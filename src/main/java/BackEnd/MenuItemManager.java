//This class is for the 'Manage Menu' tab, it will allow the user to add and remove the items from the menu
package BackEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class MenuItemManager {

    private ArrayList<MenuItem> items = new ArrayList<>();

    public MenuItemManager() {
        getMenuItems();
    }

    //adds item to db and array
    public void addItem(String inCategory, String inName, String inDescription, Double inPrice) throws SQLException{
    //String id = items.getLast().getMenuID();
    String query = "INSERT INTO `menu_items_db`.`tblmenu` (`category`, `name`, `description`, `price`) VALUES ('" + inCategory + "', '" + inName +"', '" + inDescription + "', '" + inPrice + "');";
    DB.update(query);
    //call constructor bc it does the same thing youre trying to do here 
    getMenuItems();
    }
    
    //deletes item from db and array
    public void deleteItem(String inID) throws SQLException{
        String query = "DELETE FROM `menu_items_db`.`tblmenu` WHERE (`itemID` = '" + inID + "');";
        DB.update(query);
        //arrow is a symbol similar to '='
        items.removeIf(item -> item.getMenuID().equals(inID));
       
    }
    
    public void getMenuItems(){
     try {
            String query = "SELECT * FROM tblmenu;";

            ResultSet rs = DB.query(query);

            while (rs.next()) {
                //String id = rs.getString("itemID");
                String category = rs.getString("category");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                
                MenuItem mi = new MenuItem(name, description, category, price);
                items.add(mi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuItemManager.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public static DefaultTableModel orderTable() throws SQLException{
        
        String query = "select * FROM menu_items_db.tblmenu;";
        DefaultTableModel tableModel;
        ResultSet rs = DB.query(query);
        String[] columnNames = {"name", "price"};
        
        tableModel = new DefaultTableModel(columnNames, 0);
        
        while (rs.next()) {            
            String fetchedName = rs.getString(3);
            double fetchedPrice = rs.getDouble(5);
            
            Object[] row = {fetchedName, fetchedPrice};
            tableModel.addRow(row);
        }
        rs.close();
        
        return tableModel;
    }
    
    public static DefaultTableModel manageMenuTable() throws SQLException{
        
        String query = "select * FROM menu_items_db.tblmenu;";
        DefaultTableModel tableModel;
        ResultSet rs = DB.query(query);
        String[] columnNames = {"itemID", "category", "name", "description", "price"};
        
        tableModel = new DefaultTableModel(columnNames, 0);
        
        while (rs.next()) {            
            String fetchedID = rs.getString(1);
            String fetchedCategory = rs.getString(2);
            String fetchedName = rs.getString(3);
            String fetchedDescription = rs.getString(4);
            double fetchedPrice = rs.getDouble(5);
            
            Object[] row = {fetchedID, fetchedCategory, fetchedName,fetchedDescription,  fetchedPrice};
            tableModel.addRow(row);
        }
        rs.close();
        
        return tableModel;
    }
}
