package framework.recommendation;

import java.util.HashMap;
import java.util.Map;

import shopping.model.Customer.Customer;
import shopping.model.Product.Product;

public class RateInformation {
    private Map<Integer, Customer> custmaps = new HashMap<Integer, Customer>();
    private Map<Integer, Product> procmaps = new HashMap<Integer, Product>();
    private int customerCount;
    private int procductCount;
    private double[][] rateScores;
    
    private Map<String, Integer> custRowPos = new HashMap<String, Integer>();
    private Map<String, Integer> procRowPos = new HashMap<String, Integer>();
    
    private static RateInformation instance = null;
    private boolean assigned = false;
    public boolean isAssigned() {
        return assigned;
    }
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    /**
    * Returns a reference to the single instance. Creates the instance if it
    * does not yet exist. (This is called lazy instantiation.)
    */
    public static RateInformation getRateInformation(){
        if(instance == null){
            synchronized (RateInformation.class) {
                if(instance == null){
                    instance = new RateInformation();
                }
            }
        }
        return instance;
    }
    /**
    * The Singleton Constructor. Note that it is private! No client can
    * instantiate a Singleton object directly!
    */
    private RateInformation() {}
    
    public void assignRateInformation(int customerCount, int procductCount, double scores[][]) {
        this.customerCount = customerCount;
        this.procductCount = procductCount;
        rateScores =  new double[customerCount][procductCount];
        for(int row = 0; row<customerCount; row++)
            for(int col = 0; col<procductCount; col++)
                rateScores[row][col] = scores[row][col];
        assigned = true;
    }
    
    public Map<Integer, Customer> getCustmaps() {
        return custmaps;
    }
    public void setCustmaps(Map<Integer, Customer> custmaps) {
        this.custmaps = custmaps;
    }
    public Map<Integer, Product> getProcmaps() {
        return procmaps;
    }
    public void setProcmaps(Map<Integer, Product> procmaps) {
        this.procmaps = procmaps;
    }
    public int getCustomerCount() {
        return customerCount;
    }
    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }
    public int getProcductCount() {
        return procductCount;
    }
    public void setProcductCount(int procductCount) {
        this.procductCount = procductCount;
    }
    public double[][] getRateScores() {
        return rateScores;
    }
    public void setRateScores(double[][] rateScores) {
        this.rateScores = rateScores;
    }
    public Map<String, Integer> getCustRowPos() {
        return custRowPos;
    }

    public void setCustRowPos(Map<String, Integer> custRowPos) {
        this.custRowPos = custRowPos;
    }

    public Map<String, Integer> getProcRowPos() {
        return procRowPos;
    }

    public void setProcRowPos(Map<String, Integer> procRowPos) {
        this.procRowPos = procRowPos;
    }

}
