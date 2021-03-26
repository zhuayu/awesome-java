import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Player{
  private LinkedList<Card> pockCards;

  public Player() {
    pockCards = new LinkedList<>();
  }

  public void add(Card card){
    pockCards.add(card);
  }

  public LinkedList<Card> getCardsBySort() {
    Collections.sort(pockCards, new Comparator<Card>() {
      @Override
      public int compare(Card o1, Card o2) {
        return o2.getRank() - o1.getRank();
      }
    });
    return pockCards;
  }
}




