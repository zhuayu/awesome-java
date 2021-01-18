import java.util.Random;
import java.util.Arrays;

public class DyadicArray {
    private int len;
    private int minNumber = 1;
    private int maxNumber = 10;
    private int[][] array;

    public DyadicArray() {}

    public DyadicArray(int len, int minNumber, int maxNumber) {
        this.len = len;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.array = initRandomDyadicArray(len);
    }

    public void printRtToLbCountInDyadicArray() {
        int[][] $array = this.array;
        int $sum = 0;
        int[] $tmpArray = new int[$array.length];
        for (int i = 0; i < $array.length; i ++){
            int $idx = $array.length - i - 1;
            $sum += $array[$idx][i];
            $tmpArray[i] = $array[$idx][i];
        }
        String $arrayStr = Arrays.toString($tmpArray);
        System.out.println("右上到左下元素 " + $arrayStr + " 和为: " + $sum);
    }

    public void printLtToRbCountInDyadicArray() {
        int[][] $array = this.array;
        int $sum = 0;
        int[] $tmpArray = new int[$array.length];
        for (int i = 0; i < $array.length; i ++){
            $sum += $array[i][i];
            $tmpArray[i] = $array[i][i];
        }
        String $arrayStr = Arrays.toString($tmpArray);
        System.out.println("左上到右下元素 " + $arrayStr + " 和为: " + $sum);
    }

    public void printRowCountInDyadicArray() {
        int[][] $array = this.array;
        for (int i = 0; i < $array.length; i ++){
            int $sum = 0;
            for (int j = 0; j < $array.length; j ++){
                $sum += $array[i][j];
            }
            String $arrayStr = Arrays.toString($array[i]);
            System.out.println("第 " + (i + 1) + " 行元素 " + $arrayStr + " 和为: " + $sum);
        }
    }

    public void printColCountInDyadicArray() {
        int[][] $array = this.array;
        for (int i = 0; i < $array.length; i ++){ // 列
            int[] $tmpArray = new int[$array.length];
            int $sum = 0;
            for (int j = 0; j < $array.length; j ++){
                $sum += $array[j][i];
                $tmpArray[j] = $array[j][i];
            }
            String $arrayStr = Arrays.toString($tmpArray);
            System.out.println("第 " + (i + 1) + " 列元素 " + $arrayStr + " 和为: " + $sum);
        }
    }

    private int[][] initRandomDyadicArray(int $len) {
        int[][] $initArr = new int[$len][$len];
        for (int i = 0; i < $len ; i ++){
            for (int j = 0; j < $len ; j++){
                $initArr[i][j] = DyadicArray.randomNumber(this.minNumber, this.maxNumber);
            }
        }
        return $initArr;
    }

    public static int randomNumber(int $minNumber, int $maxNumber) {
        Random random = new Random();
        int $range = $maxNumber - $minNumber + 1;
        return random.nextInt($range) + $minNumber;
    }
}
