package framework.recommendation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimilarCustomers {
    
    private Set<Integer> custSet = new HashSet<Integer>();
    private Map<Integer, Set<Integer>> mostSimilCustomers = new HashMap<Integer, Set<Integer>>();
    private Map<Integer, Set<Integer>> allSimilCustomers = new HashMap<Integer, Set<Integer>>();
    
    private int customerNumbers;
    private double[][] relativeScores;
    private RateInformation rateInformation;
    
    private int topSimilarNumber = 2;
    
    public SimilarCustomers(RateInformation rateInformation) {
        this.rateInformation = rateInformation;
        this.customerNumbers = rateInformation.getCustomerCount();
        this.relativeScores = new double[customerNumbers][customerNumbers];
    }

    public int getTopSimilarNumber() {
        return topSimilarNumber;
    }

    public void setTopSimilarNumber(int topSimilarNumber) {
        this.topSimilarNumber = topSimilarNumber;
    }

    public Map<Integer, Set<Integer>> getMostSimilCustomers() {
        return mostSimilCustomers;
    }

    public void setMostSimilCustomers(Map<Integer, Set<Integer>> similCustomers) {
        this.mostSimilCustomers = similCustomers;
    }
    
    public void addMostSimilars() throws Exception {
        for(int i=0; i<customerNumbers; i++) {
            double[] custScores = this.getRelativeScores()[i];
            Double[] transScores = new Double[custScores.length];
            for(int j=0; j<custScores.length; j++)
                transScores[j] = custScores[j];
            Map<Integer, Double> scoresMap = SortUtil.getMostOne(transScores, 2, SortType.BIG_TO_SMALL);
            this.mostSimilCustomers.put(new Integer(i), scoresMap.keySet());
        }
    }
    
    public Map<Integer, Set<Integer>> getAllSimilCustomers() {
        return allSimilCustomers;
    }

    public void setAllSimilCustomers(Map<Integer, Set<Integer>> allSimilCustomers) {
        this.allSimilCustomers = allSimilCustomers;
    }
    
    public Set<Integer> getMostSimilCustomers(Integer custPos){
        if(this.getMostSimilCustomers().get(custPos) != null)
            return this.getMostSimilCustomers().get(custPos);
        else
            return custSet;
    }
    
    public Set<Integer> getAllSimilCustomers(Integer custPos){
        if(this.getAllSimilCustomers().get(custPos) != null)
            return this.getAllSimilCustomers().get(custPos);
        else
            return custSet;
    }
    
    public Set<Integer> getCustSet() {
        return custSet;
    }

    public void setCustSet(Set<Integer> custSet) {
        this.custSet = custSet;
    }

    public int getCustomerNumbers() {
        return customerNumbers;
    }

    public void setCustomerNumbers(int customerNumbers) {
        this.customerNumbers = customerNumbers;
    }

    public double[][] getRelativeScores() {
        return relativeScores;
    }

    public void setRelativeScores(double[][] relativeScores) {
        this.relativeScores = relativeScores;
    }
    
    public void setRelativeScore(int row, int col, double value) {
        relativeScores[row][col] = value;
    }
    
    public void calculateRelativeSimilarScores() throws Exception {
        PearsonCorrelationSimilarity similarities = new PearsonCorrelationSimilarity(rateInformation);
        for(int i=0; i<customerNumbers; i++) {
            for(int j=i+1; j<customerNumbers; j++) {
                double score = similarities.simil(similarities.getRates().getRateScores()[i], similarities.getRates().getRateScores()[j]);
                this.setRelativeScore(i, j, score);
                this.setRelativeScore(j, i, score);
            }
        }
        addMostSimilars();
    } 
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
