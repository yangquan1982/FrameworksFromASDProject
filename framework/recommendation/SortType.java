package framework.recommendation;

public enum SortType {
    BIG_TO_SMALL(0), SMALL_TO_BIG(1);
    
    private int orderType;
    SortType(int orderType){
        this.orderType = orderType;
    }
    public int getOrderType() {
        return orderType;
    }
    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
