package framework.recommendation;

import java.util.Set;

public class ProductPurchasePredicate {
    
    public static final double barScore = 0.5;
    
    private double[][] scores;
    private double[][] weights;
    
    public ProductPurchasePredicate(double[][] scores, double[][] weights){
        this.scores = scores;
        this.weights = weights;
    }

    public double purchasePredict(Integer customer, Integer product, Set<Integer> mostSimilCustomers) {
        double finalScore = 0.0;
        double[][] scores = RateInformation.getRateInformation().getRateScores();
        double aveScoreCustomer = calAverage(scores[customer]);
        double weight = 0.0;
        double subSum = 0.0;
        for(Integer customerPos : mostSimilCustomers) {
            double avescore = calAverage(scores[customerPos]);
            weight += weights[customer][customerPos];
            subSum += (scores[customerPos][product] - avescore)*weights[customer][customerPos];
        }
        finalScore = aveScoreCustomer + subSum/weight;
        return finalScore;
    }
    
    public boolean purchaseOrNot(double score) {
        if(score>barScore) return true;
        return false;
    }
    
    private double calAverage(double[] scores) {
        double sum = 0;
        for(double score : scores) {
            sum += score;
        }
        return sum/scores.length;
    }
    
    public double[][] getScores() {
        return scores;
    }

    public void setScores(double[][] scores) {
        this.scores = scores;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }
}
