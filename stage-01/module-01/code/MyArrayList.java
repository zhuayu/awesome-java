/*
 *@ 题目要求：
    自定义数组扩容规则，当已存储元素数量达到总容量的 80%时，扩容 1.5 倍。 
    例如，总容量是 10，当输入第 8 个元素时，数组进行扩容，容量从 10 变 15。

 *@ 技术点：
    1. 数组初始化
 */

@SuppressWarnings("unchecked")
public class MyArrayList<T> {
  private T[] data;
  private int size;
  private int capacity;

  public static void main(String[] args) {
    MyArrayList myArrayList = new MyArrayList();
    int startCapacity = myArrayList.getCapacity();
    System.out.println("当前容量：" + startCapacity);

    for (int i = 0; i < 10; i++) {
      myArrayList.add(i);
    }

    int size = myArrayList.getSize();
    System.out.println("当前用量：" + size);

    int endCapacity = myArrayList.getCapacity();
    System.out.println("当前容量：" + endCapacity);
  }

  public MyArrayList() {
    this(10);
  }

  public MyArrayList(int i) {
    data = (T[]) new Object[i];
    size = 0;
    capacity = i;
  }

  public int getSize() {
    return size;
  }

  public int getCapacity() {
    return capacity;
  }

  public void add(T t) {
    data[size++] = t;
    System.out.println("添加" + t);
    resize((int)(data.length * 1.5));
  }

  public void resize(int length) {
    int num = (int)(data.length * 0.8); 
    if (size >= num) {
      T[] newData = (T[]) new Object[length];
      for (int i = 0; i < size; i++) {
        newData[i] = data[i];
      }
      data = newData;
      capacity = length;
      System.out.println("扩容" + length);
    }
  }
}
