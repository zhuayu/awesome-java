
/*
 使用集合实现斗地主游戏的部分功能，要求如下：

 （1）首先准备 54 张扑克牌并打乱顺序。

 （2）由三个玩家交替摸牌，每人 17 张扑克牌，最后三张留作底牌。

 （3）查看三个玩家手中的扑克牌和底牌。

 （4）其中玩家手中的扑克牌需要按照大小顺序打印，规则如下：

    手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3
 */

public class PokerGame {
    public static void main(String[] var0) {
        // 创建一副牌、洗牌
        Poker poker = new Poker();
        System.out.println("所有牌: " + poker.getCards());
        // 创建 3 个玩家交替发牌
        Player playerA = new Player();
        Player playerB = new Player();
        Player playerC = new Player();
        for (int j = 0; j < 17; j++) {
          playerA.add(poker.poll());
          playerB.add(poker.poll());
          playerC.add(poker.poll());
        }
        // 按照大小顺序列出玩家的牌
        System.out.println("玩家 A 的牌: " + playerA.getCardsBySort());
        System.out.println("玩家 B 的牌: " + playerB.getCardsBySort());
        System.out.println("玩家 C 的牌: " + playerC.getCardsBySort());
        // 列出底牌
        System.out.println("低牌: " + poker.getCards());
    }
}
