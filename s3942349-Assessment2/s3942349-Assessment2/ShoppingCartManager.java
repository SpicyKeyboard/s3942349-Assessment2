    //imports
import java.util.*;

public class ShoppingCartManager {

    public static void stage1(ItemToPurchase item){
        String name;
        double price;
        int quantity;

        Scanner productObj = new Scanner(System.in);
        System.out.println("Enter name of the item:");
        name = productObj.nextLine();
        item.setName(name);
        System.out.println("Enter price of "+name+":");
        price = productObj.nextDouble();
        item.setPrice(price);
        System.out.println("Enter quantity:");
        quantity = productObj.nextInt();
        item.setQuantity(quantity);

        System.out.println(item.toString());
    }

    public static void stage2(ShoppingCart cart){
        String inputName;
        String date;

        Scanner cartObj = new Scanner(System.in);
        System.out.println("Enter name of the customer:"); //uncomment the input requirements
        inputName = cartObj.nextLine();
        cart.setCustomerName(inputName);
        System.out.println("Enter the current date:");
        date = cartObj.nextLine();
        cart.setDate(date);

        cart.printTotal();
        cart.addItem();
        cart.printTotal();
    }

    public static void stage3(ShoppingCart cart){
        cart.removeItem();
        cart.modifyItem();
        cart.checkout();
    }

    public static void stage4(VIPShoppingCart cart){
        String inputName;
        String date;
        int inputPoints;

        Scanner cartObj = new Scanner(System.in);
        System.out.println("Enter name of the customer:"); //uncomment the input requirements
        inputName = cartObj.nextLine();
        cart.setCustomerName(inputName);
        System.out.println("Enter the current date:");
        date = cartObj.nextLine();
        cart.setDate(date);

        System.out.println("Enter the available points:");
        inputPoints = cartObj.nextInt();
        cart.setCustomerPoints(inputPoints);
        cart.addItem();
        cart.printTotal();
        cart.redeemPoints();
    }

    public static void main(String[] args) {
        ItemToPurchase item = new ItemToPurchase();
        ShoppingCart cart = new ShoppingCart();
        VIPShoppingCart vipCart = new VIPShoppingCart();
        System.out.println("***************Stage 1***************");
        stage1(item);
        System.out.println("***************Stage 2***************");
        stage2(cart);
        System.out.println("***************Stage 3***************");
        stage3(cart);
        System.out.println("***************Stage 4***************");
        stage4(vipCart);
    }
}