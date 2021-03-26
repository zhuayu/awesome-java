public enum CardRank {

    // rank越大, 等级越高
    THREE("3", 1), 
    FOUR("4", 2),
    FIVE("5", 3), 
    SIX("6", 4),
    SERVEN("7", 5), 
    EIGHT("8", 6),
    NINE("9", 7), 
    TEN("10", 8),
    JACK("J", 9), 
    QUEEN("Q", 10),
    KING("K", 11), 
    ACE("A", 12),
    DEUCE("2", 13);

    private final String rankName;
    private final int rank;

    CardRank(String rankName, int rank) {
        this.rankName = rankName;
        this.rank = rank;
    }

    public String getRankName() {
        return rankName;
    }

    public int getRank() {
        return rank;
    }
}
