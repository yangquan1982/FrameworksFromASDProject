package framework.recommendation.service;

import framework.designpattern.observer.Observer;
import shopping.model.Customer.Customer;

//Customer should be something relates to the purchase
//rest service should be much better
public class RecommendationService implements Runnable, Observer<Customer> {

    @Override
    public void run() {
        
    }

    //Recommend the other product
    @Override
    public void updateInformation(Customer customer) {
        
    }
}
