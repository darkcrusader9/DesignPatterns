package behavorial.strategy.model;

import behavorial.strategy.strategies.PayStrategy;

public class Order {
    private int totalCost;
    boolean isClosed;

    public void processOrder(PayStrategy payStrategy){
        payStrategy.collectPaymentDetails();
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }

}
