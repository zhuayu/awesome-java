import java.util.LinkedList;
import java.util.Collections;

public class Poker{
  private LinkedList<Card> pockCards;

  public Poker() {
    pockCards = new LinkedList<>();
    reset();
    shuffle();
  }

  public void reset() {
    pockCards.clear();
    pockCards.add(Card.getRedJoker());
    pockCards.add(Card.getBlackJoker());
    CardColor[] cardColors = CardColor.values();
    CardRank[] cardRanks = CardRank.values();
    for (CardColor cardColor : cardColors) {
        for (CardRank cardRank : cardRanks) {
            pockCards.add(new Card(cardColor, cardRank));
        }
    }
  }

  public LinkedList<Card> shuffle(){
    Collections.shuffle(pockCards);
    return pockCards;
  }

  public Card poll(){
    return pockCards.poll();
  }

  public LinkedList<Card> getCards() {
    return pockCards;
  }
}




