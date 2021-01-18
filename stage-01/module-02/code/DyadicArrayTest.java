public class DyadicArrayTest extends DyadicArray {
    public static void main(String[] args) {
        DyadicArray dyadicArr = new DyadicArray(16, 1, 100);
        dyadicArr.printRowCountInDyadicArray();
        System.out.println("======");
        dyadicArr.printColCountInDyadicArray();
        System.out.println("======");
        dyadicArr.printLtToRbCountInDyadicArray();
        System.out.println("======");
        dyadicArr.printRtToLbCountInDyadicArray();
    }
}
