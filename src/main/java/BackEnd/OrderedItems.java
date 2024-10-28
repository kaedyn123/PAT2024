package BackEnd;

public class OrderedItems {
//dont need an ID field bc its auto-incremented

    private String category;
    private String name;
    private String description;
    private double price;

    public OrderedItems(String inCategory, String inName, String inDescription, double inPrice) {

        this.category = inCategory;
        this.name = inName;
        this.price = inPrice;
        this.description = inDescription;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setCategory(String inCategory) {
        this.category = inCategory;
    }

    public void setName(String inName) {
        this.name = inName;
    }

    public void setDescription(String inDescription) {
        this.description = inDescription;
    }

    public void setPrice(double inPrice) {
        this.price = inPrice;
    }
}
