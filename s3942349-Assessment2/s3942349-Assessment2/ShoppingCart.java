    //imports
import java.util.Objects;
import java.util.Scanner;

public class ShoppingCart extends ItemToPurchase {

    //fields
    private String customerName;
    private String currentDate;
    private static final int CAPACITY = 10;
    private ItemToPurchase[] cartItems;
    private int itemCount;
    int numItemsAdded = 0;

    //constructors
    public ShoppingCart() {
        this.customerName = "Unknown";
        this.currentDate = "1 September 2022";
        this.cartItems = new ItemToPurchase[CAPACITY];
        this.itemCount = 0;
    }

    public ShoppingCart(String name, String date) {
        this.customerName = name;
        this.currentDate = date;
        this.cartItems = new ItemToPurchase[CAPACITY];
        this.itemCount = 0;
    }

    //methods
    public String getCustomerName() {
        return this.customerName;
    }

    public String getDate() {
        return this.currentDate;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public void setDate(String date) {
        this.currentDate = date;
    }

    public boolean addItem() {
        String name;
        double price;
        int quantity;
        String addMore = "Y";
        while (Objects.equals(addMore, "Y")) {
            boolean alreadyExists = false; // this has to be in the while loop because users may try to add an existing item more than once
            if (numItemsAdded == CAPACITY) {
                System.out.println("SHOPPING CART IS FULL");
                return false;
            }
                Scanner productObj = new Scanner(System.in);
                System.out.println("Enter name of the item:");
                name = productObj.nextLine();
                setName(name);
            if (numItemsAdded > 0) { //this if statement exists so that there is no unnecessary code run
                String currentItemName = getName();
                for (int i = 0; i < numItemsAdded; i += 1) {
                    String cartNameArray = String.valueOf(cartItems[i]);
                    String[] cartNameString = cartNameArray.split(" ");
                    String cartName = cartNameString[0];
                    if (Objects.equals(cartName, currentItemName)) {
                        System.out.println("ITEM ALREADY EXISTS");
                        alreadyExists = true;
                    }
                }
                if (alreadyExists) {
                    System.out.println("Add More? (Y/N)");
                    addMore = productObj.nextLine();
                    if (Objects.equals(addMore, "Y")) {
                        continue;
                    }
                    else {
                        break;
                    }
                }
            }
            System.out.println("Enter price of " + name + ":");
            price = productObj.nextDouble();
            setPrice(price);
            System.out.println("Enter quantity:");
            quantity = productObj.nextInt();
            setQuantity(quantity);
            productObj.nextLine();
            System.out.println("Add More? (Y/N)");
            addMore = productObj.nextLine();
            ItemToPurchase item = new ItemToPurchase(getName(), getPrice(), getQuantity());
            cartItems[numItemsAdded] = item;
            numItemsAdded += 1;
            }
        return true;
        }

        public String getNumItemsInCart() {
            int count = 0;
            String toSplit;
            itemCount = 0;
            while (count < numItemsAdded) {
                toSplit = String.valueOf(cartItems[count]);
                String[] amount = toSplit.split(" ");
                itemCount += Integer.parseInt(amount[1]);
                count += 1;
            }
            return ("Number of items: " + itemCount);
        }

        public double getCostOfCart() {
            int count = 0;
            String toSplit;
            String toDelete;
            double totalCost = 0;
            while (count < numItemsAdded) {
                toSplit = String.valueOf(cartItems[count]);
                String[] splitItem = toSplit.split(" ");
                toDelete = splitItem[5];
                toDelete = toDelete.replace("$", "");
                totalCost += Double.parseDouble(toDelete);
                count += 1;
            }
            return (totalCost);
        }

        public void removeItem() {
            boolean itemFound = false;
            String cartName;
                //get name of the item that is to be removed and match it with an item in cartItems
                System.out.println("Do you want to remove an item? Y/N");
                Scanner productObj = new Scanner(System.in);
                String inputDeleteBoolean = productObj.nextLine();
                while (Objects.equals(inputDeleteBoolean, "Y")) {
                    if (Objects.equals(inputDeleteBoolean, "Y")) {
                        System.out.println("Enter name of the item:");
                        String inputItemDelete = productObj.nextLine();
                        for (int i = 0; i < numItemsAdded; i += 1) {
                            String cartNameArray = String.valueOf(cartItems[i]);
                            String[] cartNameString = cartNameArray.split(" ");
                            cartName = cartNameString[0];
                            //if item found delete it from cartItems, set all items further along the array -1 back, -1 from numItemsInCart || else println("itemName not found")
                            if (Objects.equals(cartName, inputItemDelete)) {
                                itemFound = true;
                                for (i = i; i < numItemsAdded; i += 1) {
                                    cartItems[i] = cartItems[i + 1];
                                }
                                numItemsAdded -= 1;
                                break;
                            }
                        }
                        if (itemFound) {
                            System.out.println("[" + inputItemDelete + "] is removed from your shopping cart.");
                            itemFound = false;
                        }
                        else {
                            System.out.println("[" + inputItemDelete + "] not found in cart.");
                        }
                        printTotal();
                        System.out.println("Do you want to remove an item? Y/N");
                        inputDeleteBoolean = productObj.nextLine();
                    }
                    printTotal();
                    }
            }

        public void modifyItem() {
            boolean itemFound = false;
            //get name of the item that is to be modified and match it with an item in cartItems
            System.out.println("Do you want to modify an item? Y/N");
            Scanner productObj = new Scanner(System.in);
            String inputModifyBoolean = productObj.nextLine();
            while (Objects.equals(inputModifyBoolean, "Y")) {
                if (Objects.equals(inputModifyBoolean, "Y")) {
                    System.out.println("Enter name of the item:");
                    String inputItemModify = productObj.nextLine();
                    for (int i = 0; i < numItemsAdded; i += 1) {
                        String cartNameArray = String.valueOf(cartItems[i]);
                        String[] cartItemString = cartNameArray.split(" ");
                        String cartName = cartItemString[0];
                        //if item found delete it from cartItems and ask for new parameters
                        if (Objects.equals(cartName, inputItemModify)) {
                            itemFound = true;
                            //ask for the new quantity from the user input item and then apply it to the item
                            System.out.println("Please enter the new quantity:");
                            int inputModifyQuantity = Integer.parseInt(productObj.nextLine());
                            //break up cartItems[i] to get the quantity part
                            String inputModifyName = cartItemString[0];
                            String toDelete = cartItemString[3];
                            toDelete = toDelete.replace("$", "");
                            double inputModifyPrice = Double.parseDouble(toDelete);
                            ItemToPurchase itemModified = new ItemToPurchase(inputModifyName, inputModifyPrice, inputModifyQuantity);
                            cartItems[i] = itemModified;
                            break;
                        }
                    }
                    if (itemFound) {
                        itemFound = false;
                    }
                    else {
                        System.out.println("[" + inputItemModify + "] not found in cart.");
                    }
                    printTotal();
                    System.out.println("Do you want to modify an item? Y/N");
                    inputModifyBoolean = productObj.nextLine();
                }
            }
        }

        public void checkout() {
            System.out.println("Do you want to checkout? Y/N");
            Scanner productObj = new Scanner(System.in);
            String inputCheckoutBoolean = productObj.nextLine();
        if (Objects.equals(inputCheckoutBoolean, "Y")) {
            int i;
            if (numItemsAdded == 0) {
                System.out.println("SHOPPING CART IS EMPTY");
            }
            else {
                printTotal();
            }
            for (i = 0; i < numItemsAdded; i += 1) {
                cartItems[i] = null;
            }
            numItemsAdded = 0;
        }
        }

    public ItemToPurchase[] getCartItems() {
        return this.cartItems;
    }

    public void printTotal() {
            if (numItemsAdded == 0) {
                System.out.println(customerName + " - " + currentDate + "\nSHOPPING CART IS EMPTY");
            }
            else {
                System.out.println(customerName + " - " + currentDate);
                System.out.println(getNumItemsInCart());
                for (int i = 0; i < numItemsAdded; i += 1) {
                    System.out.println(cartItems[i]);
                }
                System.out.println("Total: $" + getCostOfCart());
            }
        }
}