public enum CardColor {

    SPADE("黑桃", 1),
    HEART("红心", 2),
    IAMOND("方块", 3),
    CLUBS("梅花", 4);

    private final String color;
    private final int rank;
    
    private CardColor(String color,int rank){
        this.color = color;
        this.rank = rank;
    }

    public String getColor() {
        return color;
    }

    public int getRank() {
        return rank;
    }
}
