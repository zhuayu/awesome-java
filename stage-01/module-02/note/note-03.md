# static 关键字和继承

## static 关键字

static 关键字修饰成员变量表示静态的含义, 这种变量被称作静态变量, 此时该成员变量由对象层级提升为类层级，也就是这个类以及由该类 new 出来的所有对象只有这一份并且由大家共享。

static 关键字修饰的成员变量可以使用 **引用.** 的方式访问 , 也可以使用 **类名.** 的方式访问 , 但推荐 **类名.** 的方式. (这样可以在调用时对成员变量和静态变量加以区分, 可读性更好)

使用注意事项：

1. 在非静态成员方法中既能访问非静态的成员又能访问静态的成员, 这是因为静态成员被所有对象共享。
2. 在静态成员方法中只能访问静态成员不能访问非静态成员, 因为静态方法是随类的加载就到了方法区上，而对象是在类加载完后才能在堆内存上开辟空间, 静态方法的产生在对象的成员产生之前, 所以静态成员方法只能调用静态成员.
3. 在开发中只有隶属于类层级并被所有对象共享的内容才可以使用static关键字修饰. (不能滥用static关键字)==
4. 不可以出现同名的和参数列表相同的静态方法和普通方法, 否则会编译报错。

静态构造块方法：

构造块指在类体中直接使用`{ }`括起来的代码块，每创建一个对象都会先执行一次构造块，构造方法之前的。

静态代码块，使用 static 关键字修饰的构造块，随着类的加载执行一次。

```java
public class Point {

  {}

  static {}
}
```

## 单例模式

在某些特殊场合中, 一个类对外提供且只提供一个对象时 , 这样的类叫做单例类 , 而设计单例类的流程和思想叫做单例设计模式。

实现流程：

1. 私有化构造方法 (通过使用`private`关键字修饰构造方法来实现)
2. 声明一个本类类型的成员变量, 该成员变量的值是本类类型的对象 , 并且该成员变量要使用`private static`关键字共同修饰 , 并且
3. 提供可以通过类名调用到的get方法(也就是被`public statci`共同修饰的方法)负责将在上一步中声明的成员变量返回出去

饿汉式：在单例类被加载时就会将单例对象创建出来

```java
public class Singleton{
  //第二步
  private static Singleton s = new Singleton();
    
  //第一步
  private Singleton(){}
    
  //第三步
  public static Singleton getInstance(){
    return s;
  }
}
```

懒汉式：只有当单例类的向外提供单例对象的方法被调用时 , 才会将单例对象创建出来

```java
public class Singleton{
  //第二步
  private static Singleton s;
    
  //第一步
  private Singleton(){}
    
  //第三步
  public static Singleton getInstance(){
    if (null == s){
      s = new Singleton();
    }
    return s;
  }
}
```

> ⚠️ 注意：懒汉式在多核心的cpu中易出现同步问题, 导致单例失败 , 所以单例模式优先选择饿汉式

## 继承

当多个类之间有相同的特征和行为时, 可以将相同的内容提取出来组成一个公共类, 让多个类吸收公共类中已有特征和行为, 而这些类只需要编写自己独有特征和行为的机制。

在Java语言中使用extends(扩展)关键字来表示继承关系：

```java
public class Workers extends Person{
   类体;
}
```

其中 Person 类叫做超类、父类、基类；Worker类叫做派生类、子类、孩子类。

使用继承提高了代码的复用性，可维护性及扩展性。

继承的特点：

- 子类不能继承父类的构造方法，私有成员可以被继承但会被隐藏, 导致无法被直接直接访问到
  - 私有成员变量可以被继承但不能被直接访问, (可以通过super.set(参数列表)和super.get()来间接直接访问到)
  - 私有成员方法可以被继承但不能被直接访问, 因为被隐藏了 , 所以不能被重写
- 子类可以继承父类的静态成员, 但不论父类的静态成员是否是私有, 都会被隐藏, 所以静态成员不能被重写. 
  - 子类可以声明的跟父类同名的静态成员, 也就只是同名罢了 , 没有任何关系,通过 `子类类名.` 调用到的成员只会是自己的
- 无论使用何种方式构造子类的对象时，都会自动调用父类的无参构造方法，来初始化子类从父类中继承的成员变量，相当于在构造方法的第一行增加代码`super()`的效果
  - 当然如果子类的构造方法自己写了 super(参数列表) ，那么就不会调用父类的无参构造方法了
- 使用继承必须满足逻辑关系：子类 is a 父类， 也就是不能滥用继承
- Java语言中只支持单继承不支持多继承，也就是说一个子类只能有一个父类，但一个父类可以有多个子类

## 方法重写

从父类中继承下来的方法不满足子类的需求时，就需要在子类中重新写一个和父类一样的方法来覆盖从父类中继承下来的版本，该方式就叫做方法的重写（override) 。

方法重写的原则：

- 要求方法名相同、参数列表相同以及返回值类型相同，从Java5开始允许返回子类类型
- 要求重写的方法的访问权限不能变小，只能跟父类方法的访问权限相同或变大
- 要求方法不能抛出更大的异常(异常机制)
- 重写后的方法上面写上 注解`@override`来表明这是个重写方法, 如果写了`@override`但实际上这个方法不是重写方法, 那编译阶段就会报错

不能重写的几种方法：

1. `static`静态方法
2. `private`私有方法
3. 被 `final`修饰的方法

