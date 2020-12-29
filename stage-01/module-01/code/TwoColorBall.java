import java.util.Random;
import java.util.Arrays;
/*
 *@ 题目要求：
    实现双色球抽奖游戏中奖号码的生成，
    中奖号码由 6 个红球号码和 1 个蓝球号码组成。 
    其中红球号码要求随机生成 6 个 1~33 之间不重复的随机号码。 
    其中蓝球号码要求随机生成 1 个 1~16 之间的随机号码。

 *@ 技术点：
    1. 随机数生成
    2. 区间随机数生成
    3. 非重复随机数数组生成

  *@ 实现思路：
    1. 定义随机数方法
    2. 定义非重复随机数组生产方法
    3. 打印 6 个红球非重复随机数组
    4. 打印 1 个红蓝球随机数
 */

public class TwoColorBall {
  public static void main(String[] args) {
    int[] $redBalls = randomArrayNumber(1, 33, 6);
    System.out.print("红球号码: ");
    System.out.println(Arrays.toString($redBalls));
    int $blueBall = randomNumber(1, 16);
    System.out.print("蓝球号码: ");
    System.out.println($blueBall);
  }

  public static int[] randomArrayNumber(int $minNumber, int $maxNumber, int $len) {
    int[] $randomArrayNumber = new int[$len];
    for (int i = 0; i < $len; i++) {
      int $r;
      Boolean $included;
      do {
        $r = randomNumber($minNumber, $maxNumber);
        $included = Arrays.binarySearch($randomArrayNumber, $r) >= 0 ? true : false;
      } while ($included);
      $randomArrayNumber[i] = $r;
    }
    return $randomArrayNumber;
  }

  public static int randomNumber(int $minNumber, int $maxNumber) {
    Random random = new Random();
    int $range = $maxNumber - $minNumber + 1;
    int $randomNumber = random.nextInt($range) + $minNumber;
    return $randomNumber;
  }
}
