package behavorial.strategy;

import behavorial.strategy.model.Order;
import behavorial.strategy.strategies.PayByCreditCard;
import behavorial.strategy.strategies.PayByPayPal;
import behavorial.strategy.strategies.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static Map<Integer, Integer> priceOfProducts = new HashMap<>();
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;
    static {
        priceOfProducts.put(1, 2200);
        priceOfProducts.put(2, 1850);
        priceOfProducts.put(3, 1100);
        priceOfProducts.put(4, 890);
    }
    public static void main(String[] args) throws IOException {
        while(!order.isClosed()){
            int cost;
            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");

                int choice = Integer.parseInt(reader.readLine());
                cost = priceOfProducts.get(choice);

                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());

                order.setTotalCost(cost * count);

                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
                order.processOrder(strategy);

                System.out.print("Pay " + order.getTotalCost() + " units or Continue shopping? P/C: ");
                String proceed = reader.readLine();
                if(proceed.equalsIgnoreCase("P")){
                    if(strategy.pay(order.getTotalCost())){
                        System.out.println("Payment has been successful.");
                    } else {
                        System.out.println("FAIL! Please, check your data.");
                    }
                }
                order.setClosed();
            }
        }
    }
}
