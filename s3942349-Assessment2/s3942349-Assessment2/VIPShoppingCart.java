    //imports
import java.util.Objects;
import java.util.Scanner;

public class VIPShoppingCart extends ShoppingCart {
    //fields
    private int customerPoints;
    private String discountCost;
    private boolean discountCheck;

    //constructor
    public VIPShoppingCart() {
        this.customerPoints = 0;
        this.discountCost = null;
        this. discountCheck = false;
    }

    //methods
    public int getCustomerPoints() {
        return customerPoints;
    }

    public void setCustomerPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("invalid argument");
        }
        this.customerPoints = points;
    }

    public void redeemPoints() {
        System.out.println("Redeem points? (Y/N)");
        Scanner redeemObj = new Scanner(System.in);
        String inputRedeemBoolean = redeemObj.nextLine();
        System.out.println("Enter points to be redeemed, -1 to quit:");
        while (Objects.equals(inputRedeemBoolean, "Y")) {
            int inputRedeemAmount = redeemObj.nextInt();
            if (inputRedeemAmount == -1) {
                break;
            }
            if (inputRedeemAmount < 50) {
                System.out.println("Less than 50! Please retry. Enter -1 to quit:");
            }
            else if (inputRedeemAmount > customerPoints) {
                System.out.println("Not enough points. Please retry. Enter -1 to quit:");
            }
            else {
                //get the closest, lowest multiple of 50 and then deduct that number from customerPoints, and if it has the 5% discount do that too, then generate a summary
                inputRedeemAmount = inputRedeemAmount-(inputRedeemAmount%50);
                customerPoints -= inputRedeemAmount;
                System.out.println("Redeeming " + inputRedeemAmount + " points.");
                if (discountCheck) {
                    System.out.println("Total: $" + (Double.parseDouble(discountCost) - inputRedeemAmount/50));
                }
                else {
                    System.out.println("Total: $" + (getCostOfCart() - inputRedeemAmount/50));
                }
                customerPoints -= inputRedeemAmount/50;
                System.out.println("Thank you for shopping with us. " + String.format("%.0f",getCostOfCart() - inputRedeemAmount/50) + " points added!");
                break;
            }
        }
    }

    @Override
    public void printTotal() {
        if (numItemsAdded == 0) {
            System.out.println(getCustomerName() + " - " + getDate() + "\nSHOPPING CART IS EMPTY");
        }
        else {
            System.out.println(getCustomerName() + " - " + getDate());
            System.out.println(getNumItemsInCart());
            for (int i = 0; i < numItemsAdded; i += 1) {
                System.out.println(getCartItems()[i]);
            }
            if (getCostOfCart() >= 100) {
                discountCost = String.format("%.2f", getCostOfCart() * 0.95);
                System.out.println("Total: $" + discountCost + " (after 5% discount)");
                discountCheck = true;
            }
            else {
                System.out.println("Total: $" + getCostOfCart());
                System.out.println("No discount for a total less than 100.");
            }
            customerPoints += Integer.parseInt(String.format("%.0f",getCostOfCart()));
        }
    }
}