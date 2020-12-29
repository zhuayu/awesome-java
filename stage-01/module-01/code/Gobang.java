/*
 *@ 题目要求：
    使用双重循环实现五子棋游戏棋盘的绘制

 *@ 技术点：
    1. 数组初始化
    2. 类型转换
    3. 双重循环

  *@ 实现思路：
    1. 使用十六进制编码初始化数组
    2. 打印头部信息换行
    3. 使用双循环打印内容信息
 */

public class Gobang {
    public static void main(String[] args) {
      printHeader();
      printBody();
    }

    // 打印头部
    public static void printHeader() {
      String[] arr = arrayInit();
      System.out.print("   ");
      for (int i = 0; i < arr.length; i ++) {
        System.out.print(arr[i] + " ");
      }
      System.out.println("");
    }

    // 打印主体
    public static void printBody() {
      String[] arr = arrayInit();
      for (int i = 0; i < arr.length; i ++) {
        System.out.print(arr[i] + " ");
        for (int j = 0; j < arr.length; j ++) {
          System.out.print("+  ");
        }
        System.out.println("");
      }
    }

    // 数组初始化
    public static String[] arrayInit() {
      String[] arr = new String[16];
      for (int r = 0; r < 16; r++) {
        arr[r] = Integer.toHexString(r) + " ";
      }
      return arr;
    }
}
