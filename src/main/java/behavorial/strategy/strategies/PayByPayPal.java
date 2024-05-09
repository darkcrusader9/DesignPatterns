package behavorial.strategy.strategies;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PayByPayPal implements PayStrategy{
    private static final Map<String, String> DATABASE = new HashMap<>();
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private boolean signedIn;
    private String email;
    private String password;

    static {
        DATABASE.put("amanda1985", "amanda@ya.com");
        DATABASE.put("qwerty", "john@amazon.eu");
    }
    @Override
    public boolean pay(int paymentAmount) {
        if(signedIn){
            System.out.println("Paying" + paymentAmount + "via Paypal");
            return true;
        } else
            return false;
    }

    @Override
    public void collectPaymentDetails(){
        try{
            while(!signedIn){
                System.out.print("Enter the user's email: ");
                email = READER.readLine();
                System.out.print("Enter the password: ");
                password = READER.readLine();
                if(verifyDetails()){
                    System.out.println("Data verification has been successful.");
                } else {
                    System.out.println("Wrong email or password!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyDetails() {
        boolean check = email.equals(DATABASE.get(password));
        this.signedIn = check;
        return check;
    }
}
