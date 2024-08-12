public class ItemToPurchase {

    //fields
    private String itemName;
    private double itemPrice;
    private int itemQuantity;

    //constructor
    public ItemToPurchase() {
        this.itemName ="none";
        this.itemPrice = 0;
        this.itemQuantity = 0;
    }
    public ItemToPurchase(String name, double price, int quantity) {
        if (name == null || price < 0 || quantity < 1)
            throw new IllegalArgumentException("invalid argument");
        this.itemName = name;
        this.itemPrice = price;
        this.itemQuantity = quantity;
    }

    //methods
    public String getName() {
        return this.itemName;
    }
    public double getPrice() {
        return this.itemPrice;
    }

    public int getQuantity() {
        return this.itemQuantity;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public void setPrice(double price) {
        itemPrice = price;
    }

    public void setQuantity(int quantity) {
        this.itemQuantity = quantity;
    }

    public String getTotalPrice() {
        String totalCost = String.valueOf(itemPrice*itemQuantity);
        return totalCost;
    }

    public String toString() {
        return String.format(this.itemName +" "+this.itemQuantity+" @ $"+this.itemPrice+" = $"+getTotalPrice());
    }
}