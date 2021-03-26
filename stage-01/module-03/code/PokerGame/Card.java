public class Card {
    private String name;
    private int rank;
    private static Card redJoker = new Card("大王",54);
    private static Card blackJoker = new Card("小王",53);

    private Card(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public Card(CardColor cardColor, CardRank cardRank){
        name = cardColor.getColor() + cardColor.getRankName();
        rank = cardRank.getRank() + (cardRank.getRank() -1) * 4;
    }

    public static Card getBlackJoker() {
        return blackJoker;
    }

    public static Card getRedJoker() {
        return redJoker;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return name;
    }
}
