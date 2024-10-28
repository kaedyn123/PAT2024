package BackEnd;

public class MenuItem {
//does it really need the ID field if the ID is auto-incremented in the database? (i dont think so)
    String name;
    String description;
    String menuID;
    Double price;
    String category;

    public MenuItem(String inName, String inDescription, String inCategory, double inPrice/*, String inID*/) {
        this.name = inName;
        this.description = inDescription;
        this.category = inCategory;
        this.price = inPrice;
        //this.menuID = inID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMenuID() {
        return menuID;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public void setMenuID(String menuID) {
        this.menuID = menuID;
    }*/

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    

}
