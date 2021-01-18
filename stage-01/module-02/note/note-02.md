# 方法和封装

## 构造方法

构造方法名与类名相同，没有返回值类型（ 且不带 void ） ，主要用于在使用 new 关键字创建对象时会自动调用构造方法实现成员变量初始化工作。

## 重载
同一个类中, 若两个及两个以上的方法的方法名称相同, 参数列表不同, 那么这些方法之间构成重载关系。同样，构造方法也会有重载的情况。

## this

构造方法中的 this 代表使用该构造方法构造出来的对象，成员方法中的 this 代表正在调用该方法的对象。

## 封装

通常情况下可以在测试类给成员变量赋值一些合法但不合理的数值, 无论是编译阶段还是运行阶段都不会报错或者给出提示。

为了避免上述错误的发生, 就需要对成员变量进行密封包装处理, 来隐藏成员变量的细节以及保证成员变量数值的合理性,该机制就叫做封装。

### 2. 封装的实现流程

1. 私有化成员变量, 使用`private`关键字修饰
2. 提供公有的 get 和 set 方法,并在 set 方法体中进行合理值的判断
3. 在构造方法中调用 set 方法进行合理值的判断

```java
public class Point {
    private int x;
    private int y;

    Point() {}

    Point(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point(2,2);
        int x1 = p1.getX();
        int x2 = p2.getX();
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
    }
}

```

## JavaBean (了解)

JavaBean是一种遵循特定标准写出来的类，是一种Java语言写成的可重用组件，通过反射机制发现和操作属性。

JavaBean本质上就是符合以下标准的类：
1. 类是公共的(类名被 public 修饰)
2. 有一个无参的公共的构造器（这个构造器被 public 修饰）
3. 有属性, 且有对应的 get、set 方法