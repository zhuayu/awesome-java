# 第14章 集合类库(上)

[toc]

## 一. 集合的概述(重点)

### 1. 集合的由来

- 当需要在Java程序中记录单个数据内容时,则声明一个变量,

- 当需要在Java程序中记录多个类型相同的数据内容时,则声明一个一维数组

- 当需要在Java程序中记录多个类型不同的数据内容时,则创建一个对象

- 当需要在Java程序中记录多个类型相同的对象数据时, 则创建一个对象数组

- 当需要在Java程序中记录多个类型不同的对象数据时,则准备一个集合

- 集合中的元素存放的是对象的地址， 而非引用或者对象本身

  - ```java
            String str = "贝优妮塔";
            ArrayList<String> list = new ArrayList<>();
            list.add(str);
            System.out.println(list.toString());
            str = null;
            System.out.println(list.toString());
    ```



### 2. 集合的框架结构

![](%E7%AC%AC14%E7%AB%A0%20%E9%9B%86%E5%90%88%E7%B1%BB%E5%BA%93(%E4%B8%8A).assets/%E9%9B%86%E5%90%88%E6%A1%86%E6%9E%B6.png)

- Java中集合框架的顶层框架是: `java.util.Collection`集合和`java.util.Map`集合
- 其中`Collection`集合中存取元素的基本单位是: **单==个==元素**
- 其中`Map`集合中存取元素的基本单位是: **单==对==元素**

## 二. `Collection`集合(重点)

![](%E7%AC%AC14%E7%AB%A0%20%E9%9B%86%E5%90%88%E7%B1%BB%E5%BA%93(%E4%B8%8A).assets/Collection%E6%8E%A5%E5%8F%A3.png)

### 1. 基本概念

`java.util.Collection`接口是`List`接口、Queue接口以及Set接口的父接口，因此该接口里定义的方法既可用于操作List集合，也可用于操作Queue集合和Set集合

### 2. 常用的实例方法

| 功能（增）                                              | 方法                                      |
| ------------------------------------------------------- | ----------------------------------------- |
| 向集合中添加元素                                        | boolean add(E e)                          |
| 将参数指定集合c中的所有元素添加到当前集合中             | boolean addAll(Collection<? extends E> c) |
|                                                         |                                           |
| **功能（删）**                                          | **方法**                                  |
| 从集合中删除指定对象                                    | boolean remove(Object c)                  |
| 从集合中删除c集合中所包含的所有对象                     | boolean removeAll(Collectiion<?> c)       |
| 清空集合                                                | void clear()                              |
| 集合只保留同时存在于c集合中的对象，其它的对象删除       | boolean retainAll(Collection<?> c)        |
|                                                         |                                           |
| **功能（改）**                                          | **方法**                                  |
| ==暂时无==                                              |                                           |
| **功能（查）**                                          | **方法**                                  |
| 判断集合是否包含指定对象                                | boolean contains(Object 0)                |
| 判断集合是否包含c集合中的所有对象                       | boolean containsAll(Colleciton<?> c)      |
|                                                         |                                           |
| **功能**                                                | **方法**                                  |
| 获取集合的容量                                          | int capacity()                            |
| 获取集合中保存的对象的对象的个数                        | int size()                                |
| 判断集合是否为空，就是判断集合中保存的对象的个数是否为0 | boolean isEmpty()                         |
| 判断集合与obj对象是否相等                               | boolean equals(Object obj)                |
| 获取当前集合的哈希码值                                  | int hashCode()                            |
| 将集合转换为数组                                        | Object[] toArray()[^注1]                  |
| 获取当前集合的迭代器                                    | Iterator iterator()                       |

[^注1]：在`java.util.Arrays`类中有个与此功能相反的静态方法`static List<T> asList(T...a)`是将数组转换为集合。

### 3. Iterator接口(重点)

#### (1). 基本概念

- `java.util.Iterator`接口主要用于描述迭代器对象, 可以遍历`Collection`集合中的所有元素.
- `java.util.Collection`接口继承`Iterator`接口, 因此所有实现`Collection`接口的实现类都可以使用该迭代器对象

#### (2). 常用的方法

实现`Iterator`接口的类会定义一个指针`cursor`, 这个`cursor`初始化后会指向集合中的第一个元素

| 功能                                                         | 方法              |
| ------------------------------------------------------------ | ----------------- |
| 判断指针`cursor`指向的元素之后是否还有元素                   | boolean hasNext() |
| 获取指针`cursor`指向的元素, 然后将指针`cursor`指向下一个元素 | E next()          |
| 从集合中删除指针`cursor`指向的元素   的上一个元素            | void remove()     |

### 4. for each循环(重点)

#### (1). 基本概念

- Java5推出了增强型for循环语句, 可以应用于数组和集合的遍历
- 是经典迭代的"简化版"
- 可以使用`break;`和`continue;`语句
- 循环体中只能进行遍历(也就是**只能查**), 不能进行增删改

#### (2). 语法格式

```java
for(元素类型 变量名 : 数组/集合名称){
    循环体;
}
```

#### (3). 执行流程

不断地从数组/集合中取出一个元素赋值给变量名并执行循环体, 直到取完所有元素为止

### 5. List集合==(重中之重)==

![](%E7%AC%AC14%E7%AB%A0%20%E9%9B%86%E5%90%88%E7%B1%BB%E5%BA%93(%E4%B8%8A).assets/List%E6%8E%A5%E5%8F%A3.png)

#### (1). 基本概念

- `java.lang.List`集合是Collection集合的子集合
- `List`集合中允许有重复的元素, 且集合中元素的排序就是元素添加进集合的顺序
- 该集合的主要实现类有: `ArrayList`类  `LinkedList`类 `Stack`类    `Vector`类
- `java.lang.List`集合的每个元素都有下标, 支持通过下标增删改查元素
- 其中ArrayList类的底层是采用动态数组进行数据管理的,支持下标访问,增删元素不方便
- 其中LinkedList类的底层是采用双向链表进行数据管理的, 访问不方便, 增删元素方便
- 可以认为ArrayList和LinkedList的方法在逻辑上完全一样,只是在性能上有一定的差别, ArrayList更适合随机访问, 而LinkedList更适合插入和删除 ; 在性能要求不是特别苛刻的情形下可以忽略这个差别
- 其中Stack类的底层是采用动态数组进行数据管理的, 该类主要用于描述一种具有后进先出特征的数据结构, 叫做栈(last in first out---LIFO)

#### (2). 常用的方法

| 功能(增)                                                     | 方法                                                 |
| ------------------------------------------------------------ | ---------------------------------------------------- |
| 向集合中指定位置添加元素                                     | void add(int index, E element)                       |
| 从集合的指定位置开始, 把c集合中的所有元素添加到集合中        | boolean addAll(int index, Collection<? extends E> c) |
|                                                              |                                                      |
| **功能(删)**                                                 | **方法**                                             |
| 删除指定位置的元素(==连位置都删了==)                         | E remove(int index)                                  |
| **功能(改)**                                                 | **方法**                                             |
| 修改指定位置的元素为另外的指定元素                           | E set(int index, E element)                          |
| **功能(查)**                                                 | **方法**                                             |
| 获取指定位置的元素                                           | E get(int index)                                     |
| 查找指定对象在集合中第一次出现的位置                         | int indexOf(Object obj)                              |
| 从后往前找, 查找指定对象在集合中第一次出现的位置             | int lastIndexOf(Object obj)                          |
| **功能**                                                     | **方法**                                             |
| 获取List集合特有的迭代器                                     | ListIterator\<E\> listIterator()                     |
| 获取List集合特有的迭代器, 会从List集合下标为index处的元素开始迭代 | ListIterator\<E\> listIterator(int index)            |
| 获取本集合的  从下标fromeIndex到下标endIndex  的元素形成的子集合 | List subList(int fromIndex, int endIndex)[^注2]      |

[^注2]: SubList类是List集合的一个内部类, 它的底层没有封装属于自己的集合数据; 它只是通过成员变量root调用到了外部类对象的集合数据, 然后对外部类对象的集合数据进行增删改查操作 ; 而SubList对象能从外部类对象的集合数据中调用到的只有下标fromIndex到endIndex的数据, 对外展示的也只有这些数据;  所以通过`List subList(int fromIndex, int endIndex)`方法获取到的集合在进行增删改查的操作时也会引起外部类对象的集合数据的改变

##### ListIterator的实例方法

- ListIterator接口继承了Iterator接口, 所以有Iterator的三件套方法

| **功能(Iterator三件套)** | 方法 |
| ------------------------ | ---- |
| 判断指针`cursor`指向的元素  之后的位置  是否还有元素            | boolean hasNext() |
| 获取指针`cursor`指向的元素, 然后将指针`cursor`指向下一个元素 | E next()          |
| 从集合中删除指针`cursor`指向的元素   的上一个元素            | void remove()     |

###### ListIterator的特有方法

| 功能(增)                                                     | 方法                  |
| ------------------------------------------------------------ | --------------------- |
| 在`cursor`指向的元素的  前一个位置  添加一个元素             | void add(E e)         |
| **功能(删)**                                                 | **方法**              |
| 没有特有的删除功能                                           | 没有特有的删除功能    |
| **功能(改)**                                                 | **方法**              |
| 将调用本方法之前的  通过next()方法或者previous()方法获取到的元素   改成元素e[^注3] | void set(E e)[^注3]   |
| **功能(查)**                                                 | **方法**              |
| 判断`cursor`指向的元素  之前的位置  是否还有元素             | boolean hasPrevious() |
| 获取指针`cursor`指向的元素,并将指针cursor指向上一个元素      | E previous()          |
| 获取指针`cursor`指向的元素的  之前的位置的  下标             | int previousIndex()   |
| 获取指针`cursor`指向的元素的下标                             | int nextIndex()       |

[^注3]: 如果调用本方法之前,` ListIterator`对象从未调用过`next()`或者`previous()`方法, 会报出非法状态异常(IllegalStateException)

#### (3). ArrayList集合的数据结构

- ArrayList类的底层是采用动态数组进行数据管理的,支持下标访问,增删元素不方便

- 所谓的动态数组本质上就是如果数组中的元素装满了就换个大点儿(==新数组的长度是原数组的1.5倍==)的数组来装, 先把旧数组中的元素装到新数组里, 然后再装要装的元素, 旧数组就弃用了, 等着被垃圾回收器回收
- 增删不方便是因为底层使用的是数组结构(==这里的增删不是对原来位置上的元素做了修改, 而是连位置都进行了增删操作, 而位置进行了增删操作之后,这个位置之后的所有位置的编号都需要改变)==
  - 数组在某个位置增加一个元素时, 需要创建一个比原数组长度大1的新数组, 先把原数组中这个位置之前的所有元素一个一个地添加到新数组中, 然后在新数组的这个位置上保存要增加的元素, 接下来再将原数组这个位置到结尾的所有元素一个一个地保存到新数组中, 最后用新数组替换掉原数组
  - 数组删除掉某个位置的元素时, 需要创建一个比原数组长度小1的新数组, 先把原数组中这个位置之前的所有元素一个一个地添加到新数组中, 再将原数组这个位置之后的所有元素一个一个地保存到新数组中, 最后用新数组替换掉原数组

#### (4). LinkedList集合的数据结构

- LinkedList类的底层是采用双向链表进行数据管理的, 访问不方便, 增删元素方便
- LinkedList集合的存储数据的基本单位是Node对象(Node的意思是节点), Node类是LinkedList类的私有的静态内部类, Node有3个Field(即成员变量)
  - Node\<E\> prev  ---  集合中排在本Node对象之前的Node对象地址
  - Node\<E\> next  ---  集合中排在本Node对象之后的Node对象地址
  - E item --- 需要存储到集合中的对象地址
- LinkedList集合有两个Node\<E\>类型的Field, 分别是first和last
  - Node\<E\> first --- LinkedList集合中排在第一位的Node对象
  - Node\<E\> last ---- LinkedList集合中排在最后一位的Node对象
- 给LinkedList集合中添加对象其实就是声明了一个由final修饰的Node类型的局部变量, 这个Node对象由于被final修饰所以会被保存到常量池中
- 在LinkedList中查找某个对象时, 会先根据Field中的first或者last找到Linked集合中的第一个或者最后一个Node对象, 然后根据Node对象中的prev或者next记录的Node对象的地址一个一个地找下去, 直到Node对象中的item与要查找的对象匹配为止
- LinkedList集合中所有元素的  编号/下标  都是从Linked集合中的第一个或最后一个Node对象开始, 在对Node对象进行遍历查找的过程中通过计数器生成的, 每查找一次就生成生成一次  编号/下标  ,   编号/下标  在LinkedList中是个局部变量, 所以LinkedList支持下标访问, 但效率很低

#### (5). Vector集合

- 底层是采用动态数组进行数据管理的, 与ArrayList相比属于线程安全,但效率较低,以后开发中基本不用
- ArrayList就是用来代替Vector的

#### (6). Stack集合

- Stack类继承了Vector类, 所以其底层是采用动态数组进行数据管理的
- Stack类主要用于描述一种具有后进先出特征的数据结构,叫做栈(last in first out LIFO)
  - Stack集合的后进先出的特征主要是通过其特有的方法`E pop()`实现的,其功能是获取集合中的最后一个元素, 并将其从集合中删除

##### 特有的方法

| 功能(增)                                        | 方法                   |
| ----------------------------------------------- | ---------------------- |
| 给栈添加一个元素(添加到了最后)                  | E push(E item)         |
| **功能(删)**                                    | **方法**               |
| 将栈中的最后一个元素提取出来,并将之从集合中删除 | E pop()                |
| **功能(改)**                                    | **方法**               |
| 无                                              | 无                     |
| **功能(查)**                                    | **方法**               |
| 这个栈是空的                                    | boolean empty()        |
| 获取栈中的最后一个元素,但不删除                 | E peek()               |
| 查找对象obj在栈中的位置(从1开始数)              | int search(Object obj) |

### 6. Queue集合(重点)

#### (1). 基本概念

- `java.util.Queue`接口是Collection集合的子集合, 与List集合属于平级关系
- 该集合主要用于描述有先进先出特征的数据结构, 叫做队列(first in first out FIFO).
- 该集合先进先出的功能主要通过`E poll()`方法实现, 获取集合中的第一个元素, 并将之从集合中删除
- 该集合的主要实现类是`LinkedList`类, 因为该类在增删方面比较有优势

#### (2). 常用的特有方法

| 功能(增)                                                  | 方法               |
| --------------------------------------------------------- | ------------------ |
| 给队列添加一个元素(添加到了队列的最后)                    | boolean add(E e)   |
| 给队列添加一个元素(添加到了队列的最后)                    | boolean offer(E e) |
|                                                           |                    |
| **功能(删)**                                              | **方法**           |
| 获取队首(也就是集合的第一个元素)的元素,并将之从集合中删除 | E poll()           |
| 获取队首(也就是集合的第一个元素)的元素,并将之从集合中删除 | E remove()[^注1]   |
|                                                           |                    |
| **功能(改)**                                              | **方法**           |
| 无                                                        | 无                 |
| **功能(查)**                                              | **方法**           |
| 获取队首的元素但不删除                                    | E peek()           |
| 获取队首的元素但不删除                                    | E element()        |

[^注1]: `LinkedList`类实现了`Queue`接口, 所以`LinkedList`集合有个`E remove()`方法


