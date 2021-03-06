# 可变字符串类和日期时间相关类

## 可变字符串类(重点)

由于String类描述的字符串内容是个常量不可改变, 当需要在Java代码中描述大量类似的字符串时, 只能单独申请和存储,此时会造成内存空间的浪费。

为了解决上述问题, 可以使用`java.lang.StringBuffer`和`java.lang.StringBuilder`类来描述字符序列可以改变的字符串

- `StringBuffer`类是从Java1.0开始存在, 属于线程安全的类, 因此效率比较低
- `StringBuilder`类是从Java1.5开始存在,属于**非**线程安全的类,效率比较高

**`StringBuilder`常用的构造方法**

| 功能                                                         | 方法                        |
| ------------------------------------------------------------ | --------------------------- |
| 使用无参方式构造对象, 容量为16(即最大可装一个长度是16的字符串),当装的字符串的长度超过了这个容量, 那么容量就会自动增加 | StringBuilder()             |
| 根据参数指定的容量来构造对象.容量为参数指定大小,当装的字符串的长度超过了这个容量, 那么容量就会自动增加 | StringBuilder(int capacity) |
| 根据参数指定的字符串来构造对象,容量为: 16+字符串长度,当装的字符串的长度超过了这个容量, 那么容量就会自动增加 | StringBuilder(String str)   |

**`StringBuilder`常用的实例方法**

| 功能                                                         | 方法                                                  |
| ------------------------------------------------------------ | ----------------------------------------------------- |
| 获取调用对象的容量                                           | int capacity()                                        |
| 获取装到本对象容器里的字符串的长度                           | int length()                                          |
|                                                              |                                                       |
| **功能(增)**                                                 | **方法**                                              |
| 从字符串下标为offset的位置开始插入str**(增)**                | StringBuilder insert(int offset, String str)          |
| 追加字符串**(增)**                                           | StringBuilder append(String str)                      |
|                                                              |                                                       |
| **功能(删)**                                                 | **方法**                                              |
| 删除下标为index处的字符**(删)**                              | StringBuilder deleteCharAt(int index)                 |
| 删除从下标start开始(包括)到end结束(不包括)的字符串**(删)**   | StringBuilder delete(int start, int end)              |
|                                                              |                                                       |
| **功能(改)**                                                 | **方法**                                              |
| 用str替换掉从下标start开始(包括)到end结束(不包括)的字符串**(改)** | StringBuilder replace(int start, int end, String str) |
| 修改下标为index处的字符为ch                                  | void setCharAt(int index, char ch)                    |
|                                                              |                                                       |
| **功能(查--查下标处对应的字符、字符串)**                     | **方法**                                              |
| 下标为index处的字符                                          | char charAt(int index)                                |
| 从下标start处开始到下标end(不包括)结束形成的字符串           | String subString(int start, int end)                  |
| 从下标start处开始到结束形成的字符串                          | String subString(int start)                           |
| **功能(查---查字符串对应的下标)**                            | **方法**                                              |
| 获取指定字符串在本对象中第一次被找到的位置                   | int indexOf(String str)                               |
| 从下标fromIndex处开始找, str在本对象中第一次被找到的位置     | int indexOf(String str , int fromIndex)               |
| 从后往前找, 获取str第一次被找到的位置                        | int lastIndexOf(String str)                           |
| 从下标fromIndex处开始,从后往前找, 获取str第一次被找到的位置  | int lastIndexOf(String str, int fromIndex)            |
| **功能**                                                     | **方法**                                              |
| 反转字符串                                                   | StringBuilder reverse()                               |

==注意: 作为参数传递的话, String不会改变其值, StringBuffer和StringBuilder会改变其值==

### 笔试考点

(1). 既然StringBuilder类的对象本身可以修改, 那为什么成员方法都还有返回值呢?

​   为了方便调用

(2). 如何实现StringBuilder类型与String类型间的转换

​   `StringBuilder  sb = new StringBuilder(str);`

​   `String s = sb.toString();`

(3). String   StringBuffer   StringBuilder之间的效率谁高?

​   String < StringBuffer < StringBuilder

## Java8之前的日期相关类(熟悉)

### `System`类

`java.lang.System`类中提供了一些有用的类字段和方法

**常用的静态方法**

| 功能                                                         | 方法                             |
| ------------------------------------------------------------ | -------------------------------- |
| 获取当前时间与基准时间1[^基准时间1]之间的一毫秒为单位的时间差 | static  long currentTimeMillis() |

### `Date`类

`java.util.Date`类主要用于描述特定的瞬间,也就是年月日时分秒,可以精确到毫秒

**常用的构造方法**

| 功能                                                         | 方法            |
| ------------------------------------------------------------ | --------------- |
| 使用无参的方式构造对象,该对象封装的是构造对象时的系统时间距离基准时间1[^基准时间1]的以毫秒数为单位的时间差 | Date()          |
| 根据参数指定的毫秒数构造对象, 参数为距离基准时间1[^基准时间1]的以毫秒数为单位的时间差, 构造的对象封装的也是这个时间差 | Date(long date) |

[^基准时间1]: 1970年1月1日0时0分0秒0毫秒

**常用的实例方法**

| 功能                                                 | 方法                    |
| ---------------------------------------------------- | ----------------------- |
| 获取调用对象距离基准时间1[^基准时间1]的时间差        | long getTime()          |
| 设置调用对象距离基准时间11[^基准时间1]的时间差为time | void setTime(long time) |

### `SimpleDateFormat`

`java.text.SimpleDateFormat`主要用于实现日期和文本间的转换, 该类可以根据Date类型对象封装的日期和时间来**生成**此日期和时间的指定格式的**字符串**

**常用的构造方法**

| 功能                                                         | 方法                             |
| ------------------------------------------------------------ | -------------------------------- |
| 使用无参方式构建对象                                         | SimpleDateFormat()               |
| 根据参数指定的模式来构造对象,模式主要有: y--年  M--月  d--日  H--时  m--分  s-秒 | SimpleDateFormat(String pattern) |

**常用的实例方法**

| 功能                         | 方法                      |
| ---------------------------- | ------------------------- |
| 获取Date类型对象对应的字符串 | String format(Date date)  |
| 获取字符串对应的Date类型对象 | Date parse(String source) |

### `Calendar`类

`java.util.Calendar`类主要用于描述特定的瞬间,取代Date类中过时方法同时实现全球化。该类是个抽象类,因此不能实例化, 其具体子类针对不同国家的日历系统,其中应用最广泛的是`GregorianCalendar`(格里高利历),对应世界上绝大多数国家/地区使用的标准日历系统。

**常用的静态方法**

| 功能                   | 方法                          |
| ---------------------- | ----------------------------- |
| 获取Calendar类型的引用 | static Calendar getInstance() |

**常用的实例方法**

| 功能                       | 方法                                                         |
| -------------------------- | ------------------------------------------------------------ |
| 设置年月日时分秒信息       | void set(int year, int mouth[^注1], int date, int hourOfDay, int minute, int second) |
| 将Calendar类型转为Date类型 | Date getTime()                                               |
| 设置指定字段的数值         | void set(int field[^注2], int value)                         |
| 向指定字段增加数值         | void add(int field[^注2], int amount)                        |

[^注1]: 从0开始, 如果mouth>=12, 那输出结果就会mouth - 11 ,同时year + 1, 其他的日期时间都是此规律
[^注2]: `Calender`类有对应的常量字段, 如: `Calendar.YEAR`    `Calendar.MOUTH`    `Calendar.DATE`等等

### 代码示例

#### 输出现在的日期与时间

```java
//1. 获取当前时间与基准时间的毫秒级时间差
long currentTimeMillis = System.currentTimeMillis();
//2. 时间差封装到Date对象里
Date date = new Date(currentTimeMillis);
//3. 对Date对应的日期时间字符串进行指定的格式化
SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
String time = sdf.format(date);
System.out.println(time);
```

#### 输出指定的日期与时间

```java
//1. 获取Calendar实例,并给日历中时间点的各个字段赋值
Calendar calendar = Calendar.getInstance();
calendar.set(2020 , 8 - 1 , 25 , 15 , 35 , 55);
//2. 获取Date类型的对象
Date date = calendar.getTime();
//3. 对date对应的日期时间字符串进行指定的格式化
SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒")
String time = sdf.format(date);
System.out.println(time)
```

通过对代码的分析,可以总结出**要得到日期和时间的指定格式的字符串**总共就分三步:

  1. 获取Date对象
  2. 获取可以根据Date对象的封装的日期和时间  来生成  此日期和时间的  指定格式的字符串  的SimpleDateFormat对象
  3. 调用SimpleDateFormat对象的format(Date date)方法来获取日期和时间的字符串

## Java8日期类的概述

**Java8日期类的由来**

JDk1.0中包含了一个`java.util.Date`类, 但是他的大多数方法已经在JDK1.1引入Calendar类之后被弃用了. 但Calendar并不比Date好多少,他们面临的问题是:

- Date类中的年份是从1900开始的, 二月份都是从0开始的
- 格式化只对Date类有用,对Calendar类则不能使用
- 非线程安全

**Java8日期类的概述**

- Java8通过发布新的Date-Time API来进一步加强对日期和时间的处理
- `java.time`包: 该包是日期/时间API的基础包
- `java.time.chrono`包: 该包提供对不同日历系统的访问
- `java.time.format`包: 该包能够格式化和解析日期时间对象
- `java.time.tempora`包: 该包包含底层框架和扩展特性
- `java.time.zone`包: 该包支持不同时区以及相关规则的类

**代替`Date`的日期类**

`Date`类被四个有各自的特定功能的时间类所代替,它们分别是: 

| 功能                                                         | 类            |
| ------------------------------------------------------------ | ------------- |
| 描述年月日信息                                               | LocaDate      |
| 描述时分秒以及纳秒信息                                       | LocalTime     |
| 描述ISO-8601日历系统中没有时区的日期时间, 可以看作是描述年月日时分秒信息**(最常用)** | LocalDateTime |
| 描述瞬间的时间点信息, 作用是来描述与基准时间[^基准时间1]的毫秒级别的时间差 | Instant       |

这四个类都实现了接口`TemporalAccessor`

### LocalDate类

`java.time.LocalDate`类主要用于描述年月日格式的日期信息,该类不表示时间和时区信息

**常用的静态方法**

| 功能                                                         | 方法                              |
| ------------------------------------------------------------ | --------------------------------- |
| 获取**封装了**默认时区[^注4]下 当前系统[^注3]的 **当前时间**的LocalDate对象 | static LocalDate now()            |
| 获取**封装了**指定时区下 当前系统[^注3]的 **当前时间**的LocalDate对象 | static LocalDate now(ZoneId zone) |

### LocalTime类

`java.time.LocalTime`类主要用于描述时分秒以及毫秒格式的日期信息,该类不表示日期和时区信息

**常用的静态方法**

| 功能                                                         | 方法                              |
| ------------------------------------------------------------ | --------------------------------- |
| 获取**封装了**默认时区[^注4]下 当前系统[^注3]的 **当前时间**的LocalTime对象 | static LocalTime now()            |
| 获取**封装了**指定时区下 当前系统[^注3]的 **当前时间**的LocalTime对象 | static LocalTime now(ZoneId zone) |

[^注3]: 当前系统是指运行这个Java程序的系统(比如:Linux系统  Win10系统  虚拟系统)等等
[^注4]: 默认时区就是当前系统的时区

### LocalDateTime类

`java.time.LocalDateTime`类主要用于描述年月日时分秒以及毫秒格式的日期信息,该类不表示日期和时区信息

**常用的静态方法**

| 功能                                                         | 方法                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 获取**封装了**默认时区[^注4]下 当前系统[^注3]的 **当前时间**的LocalDateTime对象 | static LocalDateTime now()                                   |
| 获取**封装了**指定时区下 当前系统[^注3]的 **当前时间**的LocalDateTime对象 | static LocalDateTime now(ZoneId zone)                        |
| 获取LocalDateTime对象, 根据参数指定的年月日时分秒信息来设置对象的日期和时间(最常用) | static LocalDateTime of(int year, int mouth[^注6], int dayOfMouth, int hour, int minute, int second) |

**常用的实例方法**

| 功能(查)                                              | 方法                                           |
| ----------------------------------------------------- | ---------------------------------------------- |
| 获取年份的数值                                        | int getYear()                                  |
| 获取月份的数值                                        | int getMouth()[^注6]                           |
| 获取月份中的日子的数值                                | int getDayOfMouth()                            |
| 获取一天中的小时数                                    | int getHour()                                  |
| 获取一小时中的分钟数                                  | int getMinute()                                |
| 获取一分钟中的秒数                                    | int getSecond()                                |
|                                                       |                                                |
| **功能(改---改成新的)**                               | **方法**                                       |
| 设置年份[^注5]                                        | LocalDateTime withYear(int year)               |
| 设置月份[^注5]                                        | LocalDateTime withMouth(int mouth)[^注6]       |
| 设置月份中的日子的数值[^注5]                          | LocalDateTime withDayOfMouth(int dayOfMouth)   |
| 设置小时数[^注5]                                      | LocalDateTime withHour(int hour)               |
| 设置分钟数[^注5]                                      | LocalDateTime withMinute(int minute)           |
| 设置秒数[^注5]                                        | LocalDateTime withSecond(int second)           |
|                                                       |                                                |
| **功能(增改 --- 在原来的基础上增加)**                 | **方法**                                       |
| 年份增加指定的数值[^注7]                              | LocalDateTime plusYear(long year)              |
| 月份增加指定的数值[^注7]                              | LocalDateTime plusMouth(long mouth)[^注6]      |
| 月份中的日子增加指定的数值[^注7]                      | LocalDateTime plusDayOfMouth(long dayOfMouth)  |
| 小时数增加指定的数值[^注7]                            | LocalDateTime plusHour(long hour)              |
| 分钟数增加指定的数值[^注7]                            | LocalDateTime plusMinute(long minute)          |
| 描述增加指定的数值[^注7]                              | LocalDateTime plusSecond(long second)          |
|                                                       |                                                |
| **功能(减改---在原来的基础上减少)**                   | **方法**                                       |
| 年份减少指定的数值[^注7]                              | LocalDateTime minusYear(long year)             |
| 月份减少指定的数值[^注7]                              | LocalDateTime minusMouth(long mouth)[^注6]     |
| 月份中的日子减少指定的数值[^注7]                      | LocalDateTime minusDayOfMouth(long dayOfMouth) |
| 小时数减少指定的数值[^注7]                            | LocalDateTime minusHour(long hour)             |
| 分钟数减少指定的数值[^注7]                            | LocalDateTime minusMinute(long minute)         |
| 描述减少指定的数值[^注7]                              | LocalDateTime minusSecond(long second)         |
|                                                       |                                                |
| **功能**                                              | **方法**                                       |
| 字符串转时间(只支持"2007-12-03T10:15:30"格式的字符串) | LocalDateTime parse(CharSequence text)         |
[^注5]: 作为返回值的LocalDateTime对象是个全新的对象, 这个全新的对象封装的是调用对象的时间    的对应字段    进行指定的修改后所形成的新的时间
[^注6]: 这里的月份时从 1 - 12月这样数的
[^注7]: 如果增加或减去的数值是0, 那么返回的对象就是调用对象, 如果不是0, 那么作为返回值的LocalDateTime对象是个全新的对象, 这个全新的对象封装的是调用对象的时间    的对应字段    进行指定的修改后所形成的新的时间

### Instant类

`java.lang.Instant`类主要用于描述瞬间的时间点信息, 我总结的作用是来描述与基准时间[^基准时间1]的毫秒级别的时间差

**常用的静态方法**

| 功能                                                         | 方法                                           |
| ------------------------------------------------------------ | ---------------------------------------------- |
| 从系统时钟上获取当前时间                                     | static Instant now()                           |
| 根据参数指定的毫秒数来构造对象,参数为与基准时间[^基准时间1]的毫秒数的时间差 | static Instant ofEpochMillis(long epochMillis) |

**常用的实例方法**

| 功能                                                         | 方法                                       |
| ------------------------------------------------------------ | ------------------------------------------ |
| 将此瞬间与偏移量组合以创建便宜日期时间                       | OffsetDateTime atOffset(ZoneOffset offset) |
| 获取调用对象封装的时间与基准时间[^基准时间1]的毫秒级别的时间差 | long toEpochMillis()                       |

## `DateTimeFormatter`类

java.time.format.DateTimeFormatter类主要用于格式化和解析日期，相当于Java8以前的`SimpleDateFormat`类。

**常用的静态方法**

| 功能                               | 方法                                               |
| ---------------------------------- | -------------------------------------------------- |
| 根据参数指定的字符串模式来获取对象 | static DateTimeFormatter ofPattern(String pattern) |

**常用的实例方法**

| 功能                                                 | 方法                                             |
| ---------------------------------------------------- | ------------------------------------------------ |
| 将参数指定的日期时间对象封装的时间转换为对应的字符串 | String format(TemporalAccessor[^注8] temporal)   |
| 将参数指定的字符串转换为对应的日期时间对象           | TemporalAccesssor[^注8] parse(CharSequence text) |

[^注8]: `LocalDate`    `LocalTime`   `LocalDateTime`   `Instant`这些时间类都实现了`TemporalAccessor`接口

