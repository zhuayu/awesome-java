# 常用类的概述和使用

## 常用的包(熟悉)
常用包的名称和功能

- `java.lang` --- 该包是Java语言的核心包, 并且该包中的所有内容由Java虚拟机自动导入, 如: System类 String类
- `java.util` --- 该包是Java语言的工具包, 里面提供了大量工具类以及集合类等.如`Scanner`类 `Random`类 `Arrays`类 `List`集合
- `java.io` --- 该包是Java语言中的输入输出包, 里面提供了大量读写文件相关的类, 如: `FileInputStream`类 `FileOutputStream`类
- `java.net` --- 该包是Java语言中的网络包, 里面提供了大量网络编程相关的类. 如: `Serversocket`类 `Socket`类等
- `java.sql` --- 该包是Java语言中的数据包, 里面提供了大量操作数据库的类和接口. 如: `DriverManager`类  `Connection`接口等
- ...

## Object类的概述(重点)

### 基本概念

`java.lang.Object`类是Java语言中类层次结构的根类, 也就是说任何一个类都是该类的直接或间接子类。如果定义一个Java类是没有使用`extends`声明其父类, 则编译器会自动为其加上`extends Object`,使其父类为`java.lang.Object`类。

### Object类常用的方法

| 方法声明                   | 功能介绍                                                     |
| -------------------------- | ------------------------------------------------------------ |
| boolean equals(Object obj) | 判断调用对象是否与参数对象是否相同, <br />该方法默认的是比较两个对象的地址是否相等.  <br />如果希望以对象的内容来判断两个对象是否相同,则需要重写该方法<br />若该方法被重写后, 则应该重写hashCode方法来保证结果的一致性(对象相同,则hashCode的值应该相同) |
| int hashCode()             | 用于获取调用对象的哈希码值(内存地址的编号)<br />若两个对象调用equals方法是相等,则各自调用该方法的结果必须相同<br />若两个对象调用equals方法不相等,则各自调用该方法的结果应该不相同<br />为了使该方法的结果与重写后的equals方法保持一致,必须重写该方法 |
| String toString()          | 用于获取调用对象的字符串形式<br />该方法默认返回的字符串为:包名.类名@哈希码值的十六进制 |
| Class<?> getClass()        | 返回调用该方法对象的字节码文件(.class文件)的对象(也叫实例), 最常用于反射机制中 |

### Example

```java
public class Student{
    private int id;
    private String name;
    ...
}
```

重写`equals`方法, 以`id`和`name`判断两个对象是否相同

```java
@Override
public boolean equals(Object obj){
    //1. 如果两个对象的地址值相同,那么它们就是相同的
    if(this == obj){
        return true;
    }
    //2.如果obj引用指向null或者obj的字节码文件的实例跟调用方法的对象的不同,那么这两个对象肯定是不同的
    if(null == obj || this.getClass() != obj.getClass){
        return false;
    }
    //3. 接下来才可以判断对象的成员变量是否相同了
    Student student = (Student)obj;
    if(name == null){
        if(student.getName() == null){
            return id == student.getId;
        }else{
            return false;
        }
    }
    return id == obj.getId() ? 
        name.equals(obj.getName()) : false
     //或者:Java7之后新增了Objects工具类
     //return id == student.getId() && Objects.equals(name , student.getName());
}
```

重写了equals方法就必须重写hashCode方法

```java
@Overide
public int hashcode(){
    result name == null ? 31 * id : 31 * id + name.hashCode();
}
//或者:Java7之后新增了Objects工具类
     //return Objects.hash(int,name);
```

重写toString方法

```java
@Override
public String toString(){
  return "Student{name = " + name + " , id = " + id + "}";
}
```

### Objects 工具类

> 一定要注意`Objects`工具跟`Object`根类的区分

常用方法: 

| 方法声明                                   | 功能介绍                           |
| ------------------------------------------ | ---------------------------------- |
| static boolean equals(Object a , Object b) | 以对象的内容来比较两个对象是否相同 |
| static int hash(Object...obj)              | 根据输入的参数算出哈希值           |

## 包装类

通常情况下基本数据类型的变量不是对象，负责将这些变量声明为成员变量进行对象化处理的相关类,叫做包装类。

**包装类的分类**

继承了`Number`类的包装类, 数字相关的6种基本数据类型

| 包装类                   | 对应的基本类型 |
| ----------------------- | -------------- |
| `java.lang.Byte`        | `byte`         |
| `java.lang.Short`       | `short`        |
| `java.lang.Integer`     | `int`          |
| `java.lang.Long`        | `long`         |
| `java.lang.Float`       | `float`        |
| `java.lang.Double`      | `double`       |

其他的两种基本数据类型

| 包装类                     | 对应的基本类型   |
| ------------------------- | -------------- |
| `java.lang.Boolean`       | `boolean`      |
| `java.lang.Charactor`     | `char`         |

只有char和int类型的包装类的单词与原来的关键字不一致

### Integer 类的描述

`java.lang.Integer`类主要用于实现对int类型的包装并提供int类型到String类之间的转换等方法。Integer 对象存储对应 int 值的底层是被 final 修饰的，所以 Interger 对象对应的 int 值不可修改。

**Integer 常用的常量**

| 常量修饰符          | 类型 | 名称      | 功能                                 |
| ------------------- | ---- | --------- | ------------------------------------ |
| public static final | int  | MAX_VALUE | 表示int类型能描述的最大值,即`2^31-1` |
| public static final | int  | MIN_VALUE | 表示int类型能描述的最小值,即`-2^31`  |
| public static final | int  | SIZE      | 表示int类型的二进制补码所占的位数    |
| public static final | int  | BYTES     | 表示int类型所长的字节个数            |
| public static final | int  | TYPE      | 获取表示int类型的Class实例           |

**常用的功能**

1. 构造方法

| 修饰符             | 方法                | 功能                                                         |
| ------------------| -------------------| ------------------------------------------------------------ |
| deprecated(弃用的) | Integer(int value) | 根据参数指定的整数来构造对象,没有使用自动装箱池功能, 比较浪费内存,所以已过时, 被静态方法`valueOf(int i)`代替 |
| deprecated(弃用的) | Integer(String s)  | 根据参数指定的字符串来构造对象, 没有使用自动装箱池功能, 比较浪费内存, 所以已过时, 被静态方法`parseInt(String s)`代替 |

2. 静态方法

| 功能                                                         | 方法                                |
| ------------------------------------------------------------ | ----------------------------------- |
| 根据参数指定的int数值提供对应的Integer对象,使用到了自动装箱池功能 | static  Integer valueOf(int i);     |
| 根据参数指定的字符串提供对应的Integer对象,使用到了自动装箱池功能 | static Integer parseInt(String s)   |
| 获取指定整数参数的十进制字符串                               | static String toString(int i)       |
| 获取指定整数参数的二进制字符串                               | static String toBinaryString(int i) |
| 获取指定整数参数的八进制字符串                               | static String toOctalString(int i)  |
| 获取指定整数参数的十六进制字符串                             | static String toHexString(int i)    |

3. 常用的实例方法(Intance Method)

| 功能                               | 方法                       |
| ---------------------------------- | -------------------------- |
| 获得Integer对象的整数值            | int intValue()             |
| 比较调用对象与参数指定对象是否相等 | boolean equals(Object obj) |
| 返回描述调用对象数值的字符串       | string toString()          |

**自动装箱池**

- 装箱：将一个基本数据类型的数值封装成包装类的对象
- 拆箱：将一个包装对象对象拆封为基本数据类型

在Java5发布之前使用包装类对象进行运算时, 需要较为繁琐的"拆箱"和"装箱"操作: 即运算前先将包装类对象拆分为基本数据类型参加运算,运算后得到的值在封装成包装类对象。从Java5开始增加了自动拆箱和自动装箱的功能, 也就是说**所有的基本数据类型都有了自动拆装箱的功能**。

```java
Integer a = 999;
Integer b = 888;
Integer c = a * b;
System.out.println("c = " + c);
```

在Integer类的内部提供了自动装箱池技术, 将`-128`到`127`之间的整数已经装箱完毕, 当程序中使用该范围之间的整数的Integer对象时, 无需装箱直接取用自动装箱池中的对象即可,从而提高了效率

```java
Integer d = 999;
Integer f = 999;
System.out.println("d与f指向的是否是同一个对象: " + (d == f));//false, 超出了自动装箱池的范围,所以指向的不是同一个对象
Integer g = 127;
Integer h = 127;
System.out.println("g与h指向的是否是同一个对象: " + (g == h));//true
Integer i = Integer.valueOf(127);
Integer j = Integer.valueOf(127);
System.out.println("i与j指向的是否是同一个对象: " + (i == j));//true, 使用到了自动装箱池功能
```

自动装箱池的范围可以改变,这个功能是在Integer类的静态内部类 IntegerCache 里面定义的

### Double类的描述

`java.lang.Double`类型内部包装了一个double类型的变量作为成员变量,主要用于实现对double类型的包装并提供double类之间的转换等方法

**常用的常量**

| 常量修饰符          | 类型 | 名称  | 功能                             |
| ------------------- | ---- | ----- | -------------------------------- |
| public static final | int  | SIZE  | 表示double类型的二进制所占的位数 |
| public static final | int  | BYTES | 表示double类型所占的字节个数     |
| public static final | int  | TYPE  | 获取表示double类型的Class实例    |

**常用的功能**

1. 构造方法

| 修饰符             | 方法                 | 功能                                                         |
| ------------------| -------------------- | ------------------------------------------------------------ |
| deprecated(弃用的) | Double(double d) | 根据参数指定的浮点数来构造对象已过时, 被静态方法`valueOf(double d)`代替 |
| deprecated(弃用的) | Double(String s) | 根据参数指定的字符串来构造对象, 已过时, 被静态方法`parseDouble(String s)`代替 |

2. 静态方法

| 功能                                                         | 方法                                 |
| ------------------------------------------------------------ | ------------------------------------ |
| 根据参数指定的double数值提供对应的Double对象,使用到了自动装箱池功能 | static  Double valueOf(double d);    |
| 根据参数指定的字符串提供对应的Double对象,使用到了自动装箱池功能 | static Doubble parseDouble(String s) |
| 获取指定整数参数的十进制字符串                               | static String toString(double d)     |
| 获取指定整数参数的十六进制字符串                             | static String toHexString(double d)  |

3. 常用的实例方法(Intance Method)

| 功能                                                 | 方法                       |
| ---------------------------------------------------- | -------------------------- |
| 获得调用对象的封装的基本数据类型的值                 | double doubleValue()       |
| 比较调用对象与参数指定对象是否相等                   | boolean equals(Object obj) |
| 返回描述调用对象数值的字符串                         | string toString()          |
| 判断调用对象的数值是否为数字,比如`0/0.0`就不是个数字 | isNaN()                    |
| 获得调用对象封装的数值的整数部分                     | intValue()                 |

### Boolean类的描述

`java.lang.Boolean`类型内部包含了一个boolean类型的变量作为成员变量, 主要用于实现对boolean类型的包装并提供boolean类型到String类之间的转换等方法

**常用的常量**

| 功能                             |                                   |
| -------------------------------- | --------------------------------- |
| 获得对应基值为false的Boolean对象 | public static final Boolean FALSE |
| 获得对应基值为true的Boolean对象  | public static final Boolean TRUE  |
| 获取表示boolean类型的Class实例   | public static final Class TYPE    |

**常用的构造参数**

| 功能                                                         | 方法                             |
| ------------------------------------------------------------ | -------------------------------- |
| 通过参数指定的boolean类型的数值来构造对象                    | ~~Boolean(boolean value)~~[^注1] |
| 通过参数指定的字符串来构造对象,如果字符串既不符合"true",又不符合"false",那就创建一个基值为false的Boolean对象 | ~~Boolean(String s)~~[^注2]      |

[^注1]: 已过时,被Boolean.valueOf(boolean value)代理
[^注2]: 已过时,被Boolean.valueOf(String s)代替

**常用的静态方法**

| 功能                                                         | 修饰符 | 方法                           |
| ------------------------------------------------------------ | ------ | ------------------------------ |
| 根据参数指定的boolean数值获得对应的对象                      | static | Boolean valueOf(boolean value) |
| 根据参数指定的字符串获得对应的对象, 如果字符串既不符合"true",那就提供一个基值为false的Boolean对象 | static | Boolean valueOf(String s)      |
| 把字符串转为对应的boolean类型的值并返回,如果字符串既不符合"true", 那返回的boolean类型的数值就是false | static | boolean parseBoolean(String s) |

**常用的实例方法**

| 功能                                 | 方法                       |
| ------------------------------------ | -------------------------- |
| 获得本对象封装的boolean类型的值      | boolean booleanValue()     |
| 比较调用对象与参数指定的对象是否相等 | boolean equals(Object obj) |
| 获得描述调用对象封装的值的字符串     | String toString()          |

### Character类的描述

`java.lang.Character`类内部包装了一个char类型的变量作为成员变量,主要用于实现对char类型的包装并提供字符类别的判断和转换等方法

**常用的常量**

| 功能                       | 常量                           |
| -------------------------- | ------------------------------ |
| 表示char类型所占的位数     | public static final int SIZE   |
| 表示char类型所占的字节个数 | public static final int BYTES  |
| 表示char类型的Class实例    | public static final Class TYPE |

**常用的构造方法**

| 功能                     | 方法                            |
| ------------------------ | ------------------------------- |
| 根据给定的char值创建对象 | ~~Character(char value)~~[^注3] |

[^注3]: 已过时,可以被静态方法`static Character valueOf(char value)`代替

**常用的静态方法**

| 功能                                    | 方法                                 |
| --------------------------------------- | ------------------------------------ |
| 根据参数指定的字符数据提供Character对象 | static Character valueOf(char value) |
| 判断字符参数是否为小写                  | static boolean isLowerCase(char c)   |
| 判断字符参数是否为大写                  | static boolean isUpperCase(char c)   |
| 判断字符参数是否为数字                  | static boolean isDigital(char c)     |
| 将字符参数转换为大写字符                | static char toUpperCase(char c)      |
| 将字符参数转换为小写字符                | static char toLowerCase(char c)      |

**常用的实例方法**

| 功能                             | 方法                       |
| -------------------------------- | -------------------------- |
| 获得本对象封装的char值           | char charValue()           |
| 获得描述调用对象数值的字符串形式 | String toString()          |
| 比较调用对象与参数对象是否相等   | boolean equals(Object obj) |

### 包装类(`wrapper`)的使用总结

- 基本数据类型转换为对应包装类的方式
  - 每个包装类都应用了自动拆装箱技术
  - 对应的包装类类型  引用名 = 要包装的基本数据类型的变量或值
  - 或者使用对应包装类的静态方法`valueOf(参数列表)`
- 获取包装类对象中基本数据类型的数值的方式
  - 调用包装类中的方法`xxxValue()`
- 字符串转换为基本数据类型的方法
  - 调用对应包装类中的静态方法parseXxx(String s)方法

## 数学处理类(熟悉)

### Math类

`java.lang.Math`类主要用于提供执行数学运算的方法, 如: 对数,平方根

**常用的静态方法**

| 功能                                     | 方法                                  |
| ---------------------------------------- | ------------------------------------- |
| 获得两个参数中的最大值                   | static int max(int a, int b)[^Math1]  |
| 获得两个参数中的最小值                   | static int min(int a, int b)[^Math1]  |
| 获得x的y次幂(参数及返回值都是double类型) | static double pow(double x, double y) |
| 获得参数的绝对值                         | static int abs(int i)[^Math1]         |
| 获得double类型的参数的四舍五入的long结果 | static long round(double d)           |
| 获得float类型的参数的四舍五入的int结果   | static int round(float f)             |
| 获得double类型的参数的平方根             | static double sqrt(double d)          |
| 获得0.0到1.0的随机数(小数点后16位)       | static double random()                |

[^Math1]: int long  float double类型的参数都可以

### BigDecimal类

由于float类型和double类型在运算时可能会有误差,若希望实现精确运算则借助`java.math.BigDecimal`类型加以描述

**常用的构造方法**

| 功能                                         | 方法                     |
| -------------------------------------------- | ------------------------ |
| 获得封装了指定浮点数的BigDecimal对象         | BigDecimal(double value) |
| 获得封装了字符串表示的浮点数的BigDecimal对象 | BigDecimal(String s)     |

**常用的静态方法**

| 功能                                 | 方法                                         |
| ------------------------------------ | -------------------------------------------- |
| 获得封装了指定浮点数的BigDecimal对象 | static BigDecimal valueOf(double vale)[^Big] |

[^Big]: 这个方法没有String类型的参数的重载方法

**常用的实例方法**

| 功能                     | 方法                                                         |
| ------------------------ | ------------------------------------------------------------ |
| 对小数进行精确的加法计算 | BigDecimal add(BigDecimal augend)                            |
| 对小数进行精确的减法计算 | BigDecimal subtract(BigDecimal subtrahend)                   |
| 对小数进行精确的乘法计算 | BigDecimal multply(BigDecimal multiplicand)                  |
| 对小数进行精确的除法计算 | BigDecimal divide(BigDecimal divisor)                        |
| 设置保留小数点后几位     | setSclae(int newScale, RoundingMode[^BigDecimal] roundingMode) |

[^BigDecimal]: 常用的RoundingMode的枚举值HALF_UP(四舍五入),  HALF_DOWN(五舍六入), CELLING(天花板)和FLOOR(地板)-----y轴为参考系,  UP(小数部分不为0则入,为0则舍), DOWN(舍去小数部分)

### Integer类

若希望表示比long类型范围还大的整数数据,则需要借助`java.lang.Integer`类型描述

**常用的构造方法**

| 功能                           | 方法                      |
| ------------------------------ | ------------------------- |
| 根据参数指定的字符串来构造对象 | BigIntegerJ(String value) |

**常用的静态方法**

| 功能                                 | 方法                                                 |
| ------------------------------------ | ---------------------------------------------------- |
| 根据参数指定的long类型数值来构造对象 | static BigInteger  valueOf(long value)[^BigInteger1] |

[^BigInteger1]: 这个方法没有String类型的参数的重载方法

**常用的实例方法**

| 功能         | 方法                                              |
| ------------ | ------------------------------------------------- |
| 加           | BigInteger add(BigInteger augend)                 |
| 减           | BigInteger subtract(BigInteger subtrahend)        |
| 乘           | BigInteger multiply(BigInteger multipicand)       |
| 除           | BigInteger divide(BigInteger divisor)             |
| 取余         | BigInteger remainder(BigInteger divisor)          |
| 获取商与余数 | BigInteger divideAndRemainder(BigInteger divisor) |

