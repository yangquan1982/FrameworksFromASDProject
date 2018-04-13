package framework.recommendation;

import shopping.model.Customer.Customer;

public class PearsonCorrelationSimilarity {
    
    private RateInformation rates;
    
    public PearsonCorrelationSimilarity(RateInformation rates) {
        this.rates = rates;
    }
    
    public double calculateSimilarity(Customer custA, Customer custB) throws Exception {
        int apos = rates.getCustRowPos().get(custA.getCustomerProfile().getId());
        int bpos = rates.getCustRowPos().get(custB.getCustomerProfile().getId());
        return simil(rates.getRateScores()[apos], rates.getRateScores()[bpos]);
    }
    
    public double simil(double[] scoresA, double[] scoresB) throws Exception {
        double similScore = 0.0;
        if(scoresA.length != scoresB.length)
            throw new Exception("illegal parameters size");
        double averageA = calAverage(scoresA);
        double averageB = calAverage(scoresB);
        int rateScoreSize = scoresA.length;
        double numeratorSum = 0.0;
        double denominatorASum = 0.0;
        double denominatorBSum = 0.0;
        for(int i=0; i<rateScoreSize; i++) {
            numeratorSum += (scoresA[i]-averageA) * (scoresB[i]-averageB);
            denominatorASum += Math.pow(scoresA[i]-averageA, 2);
            denominatorBSum += Math.pow(scoresB[i]-averageB, 2);
        }
        similScore = numeratorSum/(Math.sqrt(denominatorASum) * Math.sqrt(denominatorBSum));
        return similScore;
    }
    
    private double calAverage(double[] scores) {
        double sum = 0;
        for(double score : scores) {
            sum += score;
        }
        return sum/scores.length;
    }
    
    public RateInformation getRates() {
        return rates;
    }

    public void setRates(RateInformation rates) {
        this.rates = rates;
    }
}
