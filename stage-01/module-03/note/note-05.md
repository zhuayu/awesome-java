# 第15章 集合类库(下)

[toc]

## 一. 泛型机制(熟悉)

### 1. 基本概念

- 以集合中的泛型为例来说明泛型的特点

- 通常情况下集合中可以存放不同类型的对象, 是因为将所有对象都看作Object类型放入的, 因此从集合中取出元素时也是Object类型, 为了表达该元素真实的数据类型, 则需要强制类型转换, 而强制类型转换可能引发类型转换异常

- 为了避免上述错误的发生, 从Java5开始增加泛型机制,也就是在集合名称的右侧使用`<数据类型>`的方式来明确要求该集合中可以存放的元素类型, 若放入其它类型的元素则编译报错

- 泛型只在编译时期有效, 在编译成字节码文件后, 字节码文件中不会有泛型这个东西,所以在Java程序运行时期不区分是什么泛型

- 从Java7开始  声明含有泛型的对象时可以只在变量的类型上输入泛型的具体值, new后面的类名后必须写  `< >` , 但可以不写泛型的具体值.

  - ```java
    //Java7之前声明一个集合
    List<String> list = new ArrayList<String>();
    ```

  - ```java
    //Java7开始声明一个变量
    List<String> list = new ArrayList<>();
    ```

- 当声明一个具有泛型的对象却没有给这个泛型赋值时, 默认泛型的值为`Object`

  - ```java
    List list = new ArrayList();
    //如果类在声明时有泛型, 而在声明对象时没有定义泛型的值则会默认泛型的值为Object, 相当于输入的是
    //List<Object> list = new ArrayList<>();
    ```
### 2. 底层原理

- 泛型的本质就是将  类型  参数化 , 也就是让  数据类型  作为  参数  传递

- 其中集合接口名或者类名后的\<E\>  \<T\>  \<K , V\>中的这些大写字母相当于形式参数来进行==占位==(也可以是其他的大写字母, 但必须要有**\<** 和 **\>**)

  - ```java
    //例如下面的 E 就是为 形式参数, 为数据类型进行占位
    public interface List<E> extends Collection<E>{
        ...
    }
    ```

- 而在声明集合对象时, 在接口名或者类名后面的`< >`输入具体的数据类型, 这个数据类型就是实体参数, 形式参数相当于被赋值为这个数据类型 , 也就是说在声明类时的所有被这个形式参数占位的地方的值都是这种数据类型

  - ```java
    List<String> list = new ArrayList<>();
    //相当于ArrayList类中被E占位的地方变成了String这个引用数据类型
    //List类中被E占位的地方也是String这个引用数据类型 
    //List的父接口Collection被E占位的地方也变成了String这个引用数据类型
    ```
- 因为在泛型可以  在声明类或者方法时  用来代表  各种各样广泛的数据类型, 因此得名泛型

### 3. 自定义泛型接口

```java
public interface CustomInterface<T>{
    public abstract void show1(T t);
    public abstract T show2();
}
```
### 4. 自定义泛型类

- 泛型类和普通类的区别是类名后面添加了被 `<` 和 `>`  包起来的参数列表, 可以有一个类型形参, 也可以有多个类型形参, 如: `<T>`    `<K , V>`等

- 实例化泛型类时**必须指定**具体的数据类型, 并且**是**引用数据类型**而不能是**基本数据类型

- 父类有泛型, 子类可以选择保留泛型也可以选择指定泛型类型

  - ```java
    public class Father<E>{
        ...
    }
    ```
```

  - 子类可以保留父类的泛型

    - ```java
      public class Son<E> extends Father<E>{
          ...
      }
```

  - 子类可以保留父类的泛型的同时有自己的泛型

    - ```java
      public class Son<E,T> extends Father<E>{
          ...
      }
      ```

  - 子类可以在有自己的泛型的同时给父类的泛型一个值

    - ```java
      public class Son<T> extends Father<String>{
          ...
      }
      ```
    
  - 子类可以没有泛型但给父类的泛型一个值
  
    - ```java
      public class Son extends Father<String>{
          ...
      }
      ```
  
  - 子类可以没有泛型也不给父类的泛型一个值, 那么就默认父类泛型的值为`Object`
  
    - ```java
      public class Son extends Father<E>{
          ...
      }
      ```
    
    - ```java
      public class Son extends Father{
          ...
      }
      ```


### 5. 自定义泛型方法

- 泛型方法就是我们在定义方法时不清楚形参的类型, 只能以泛型来占位, 但调用该方法时就能根据实参的类型来确定泛型的值了

- 泛型方法的格式

  - ```java
    //访问权限 <泛型> 返回值类型 方法名(泛型标识 参数名称){}
    public <T> void example(T t){
        ...
    }
    ```

- 因为调用静态方法是不需要创建对象的, 而泛型的值只有在声明对象时才能确定下来, 如果调用静态方法发生在创建对象之前, 那么静态方法就不能使用声明对象时的泛型的值, 所以**静态方法如果要使用泛型, 只能自己定义, 不能使用类名后面的泛型**

  - ```java
    public static <T> void example(T t){
        ...
    }
    ```

### 6. 泛型在继承上的体现

- 继承关系是在Java程序运行阶段都一直存在的
- 而泛型在Java源代码编译完成后就被丢弃了, 泛型不会存在于字节码文件中, 运行时自然也不会有泛型了.
- 所以泛型的存在或者泛型的值有没有继承关系对类的继承关系不会有任何影响
- 例如`Class A`和`Class B` , `Class A extends B` , 处于不同包中的`Class C<A>`和`ClassC<B>`是没有继承关系的

### 7. 通配符的使用

- 有时我们希望传入的类型在一个指定的范围内, 也就是说要对泛型的形式参数的取值范围作出限制, 此时就可以使用泛型通配符了

- 所以泛型通配符是在声明类或者声明方法时使用, 在创建对象时最好不要用泛型通配符, 因为这就不是为此设计的, 强行使用不会报错, 但不会达到想要的效果

  - 比如创建集合对象时强行使用泛型通配符, 会导致无法给集合添加元素

- 泛型中有三种通配符形式
  - <?>            无限制通配符
  
  - <? extends E>      表示类型的上界是E, `? extends E`相当于一个占位符, 表示其所占位的**取值范围是**类型E以及所有E的子类以及间接子类
  
  - <? super E>          表示类型的下界是E, `? super E`相当于一个占位符, 表示其所占位的取值范围是类型E以及所有E的父类以及间接父类.
  
    - 可以用`ArrayList<E>`的两个个方法来举例
  
    - | 功能                                                         | 方法                                        |
      | ------------------------------------------------------------ | ------------------------------------------- |
      | 本集合是否包含集合c, 集合c的泛型的取值只能是E或者E的子类以及间接子类 | boolean contains(Colleciton<? extends E> c) |
      | 从本集合中移除所有c集合中含有的元素, 集合c的泛型的取值可以是任何类型 | boolean removeAll(Collection<?> c)          |
      



## 二. Set集合

![ ](%E7%AC%AC15%E7%AB%A0%20%E9%9B%86%E5%90%88%E7%B1%BB%E5%BA%93(%E4%B8%8B).assets/Set%E6%8E%A5%E5%8F%A3.png)

### 1. 基本概念

- `java.util.Set`集合是Collection集合的子集合, 与 List集合评级
- 该集合中不允许有重复的元素, 且集合中的元素的排序跟元素添加进集合的顺序无关
- 该集合的主要实现类是: `HashSet`类和`TreeSet`类, 以及继承了`HashSet`类的`LinkedHashSet`类
- 其中`HashSet`类的底层是采用哈希表进行数据管理的
  - 其中LinkedHashSet类与HashSet类的不同之处在于其内部维护了一个双向链表, 链表中记录了元素的迭代顺序, 也就是元素插入集合中的先后顺序, 一次便于迭代.==[无法理解,也不知道怎么用]==
- 其中`TreeSet`类的底层是采用**红黑树**(又名**二叉树)**进行数据管理的

### 2. 常用的方法

- 参考`Collection`集合中的方法即可

### 3. HashSet集合

`HashSet`类的底层是采用**哈希表**进行数据管理的

#### ①. 元素放入`HashSet`集合的原理(==无法理解==)

1. 调用元素的`hashCode()`方法获取对应的哈希码值, 再由某种哈希算法计算出该元素在数组中的索引位置
2. 若该位置没有元素,则将该元素直接放入即可
3. 若该位置有元素, 则使用新元素和已有元素依次比较哈希值, 若哈希值不相同, 则将该元素直接放入
4. 若新元素与已有元素的哈希值相同, 则使用新元素调用equals方法与已有元素依次比较.
5. 若相等则添加元素失败, 否则将元素直接放入即可

#### ②. 元素在`HashSet`集合中的排序规则

元素在添加进`HashSet`集合之前会调用元素的`hashCode()`方法来获得元素的哈希码值, 哈希码值越大元素在`HashSet`集合中的位置越靠后

### 4. TreeSet集合

#### ①. TreeSet集合的基本概念

`TreeSet`类的底层是采用**红黑树**(又名**二叉树)**进行数据管理的

- 二叉树主要指每个节点最多只有两个子节点的树形结构
- 第一个放入`TreeSet`集合的元素叫做根节点
- 满足以下3个特征的二叉树叫做有序二叉树
  1. 左子树中的任意节点元素都小于根节点元素值
  2. 右子树中的任意节点元素都大于根节点元素值
  3. 左子树和右子树的内部也遵守上述规则
- 由于`TreeSet`集合的底层采用二叉树进行数据的管理, 当有新元素插入到TreeSet集合时,需要使用新元素与集合中已有的元素依次比较来确定新元素的合理位置
  - 并不是需要跟所有的元素进行比较, 最多只需要与根节点以及左子树或者右子树的一半元素比较后就能决定新元素的位置了

#### ②. 元素在`TreeSet`集合中的排序规则

##### (1). 自然排序规则

- 元素类型的类必须实现`java.lang.Comparable<E>`接口, 并重写`int compareTo(E e)`方法
- 集合中元素的排序规则是根据`int compareTo(E e)`的返回值排序的
- 如果`int compareTo(E e)` 的返回值
  - 大于0    那么新元素在集合中的位置要在e的后面
  - 等于0    新元素会添加失败
  - 小于0    那么新元素在集合中的位置要在e的前面

##### (2). 比较器规则

- 创建TreeSet集合时, 必须要向TreeSet类的构造方法传进一个`java.util.Comparator<E>`类型的匿名内部类对象, 
- 这个匿名内部类对象必须重写`int compare(E e1, E e2)`, 其中`e1`代表将要新增的元素, `e2`代表已存在于集合中的元素
- 集合中元素的排序规则是根据`int compare(E e1, E e2)`的返回值排序的
  - 大于0    e1在集合中的位置会排在e2的后面
  - 等于0    新元素会添加失败
  - 小于0    e1在集合中的位置会排在e2的前面

##### (3). ==注意==

- 创建`TreeSet`集合时至少要满足自然排序规则或者比较器规则中的一个, 否则无法创建`TreeSet`集合
- 如果自然排序规则和比较器规则同时存在, 那么`TreeSet`集合中的元素会按照比较器规则来进行排序

## 三. Map集合(重点)

### 1. 基本概念

- `java.util.Mpa<K,V>`集合中存取元素的基本单位是: 单对元素, 其中类型参数如下:
  - K --- 此映射所维护的键(key)的类型, 相当于目录
  - V --- 映射值(value)的类型, 相当于内容
- 该集合中key是不允许重复的, 而且一个key只能对应一个value
- 该集合的主要实现类有: 
  - ![](%E7%AC%AC15%E7%AB%A0%20%E9%9B%86%E5%90%88%E7%B1%BB%E5%BA%93(%E4%B8%8B).assets/Map%E6%8E%A5%E5%8F%A3.png)
- 其中`HashMap`类的底层是采用哈希表进行数据管理的
  - `HashMap`集合中的key的排序规则跟`HashSet`集合的相同
  - 其中`LinkedHashMap`类与`HashMap`类的不同之处在于内部维护类一个双向链表, 链表中记录了元素的迭代顺序, 也就是元素插入集合中的先后顺序, 因此便于迭代
- 其中`TreeMap`类的底层是采用红黑树(二叉树)进行数据管理的
  - `TreeMap`集合中key的排序规则跟`TreeMap`集合的排序方式相同
- 其中`Hashtable`类是古老的Map实现类, 现被`HashMap`类代替, `Hashtable`类与`HashMap`类相比属于线程安全的类, 且不允许null作为key或这value的值
  - 其中`Properties`类是`Hashtable`类的子类, 该对象用来处理属性文件, key和value都是String类型的
- Map集合是面向查询优化的的数据结构, 在大数据量情况下有着优良的查询性能
- 经常用于根据key检索value的业务场景

### 2. Map集合常用的实例方法

| 功能(增)                                                     | 方法                                               |
| ------------------------------------------------------------ | -------------------------------------------------- |
| 将key-value对存入Map (若集合中已包含该key, 则用value替换掉key所对应的旧值, 并将这个旧值作为返回值, 若集合中没有该key, 则把key-value添加进集合中, 此时的返回值为`null`) | V put(K key, V value)                              |
| If the specified key is not already associated with a value (or is mapped to `null`) associates it with the given value and returns `null`, else returns the current value. | default V putIfAbsent(K key, V value)              |
| 把集合m中的所有key-value对添加到本集合中                     | void pubAll(Map\<? extend K, ? extends V\> m)      |
| **功能(删)**                                                 | **方法**                                           |
| 根据参数指定的key将集合中的key以及其对应的value删除掉, 返回值为key所对应的value | V remove(K key)                                    |
| Removes the entry for the specified key only if it is currently mapped to the specified value. | default boolean remove(Object key, Object value)   |
| 清除掉本集合中的所有键值对                                   | void clear()                                       |
| **功能(改)**                                                 | **方法**                                           |
| Replaces the entry for the specified key only if it is currently mapped to some value | default V replace(K key, V value)                  |
| Replaces the entry for the specified key only if currently mapped to the specified value. | default V replace(K key, V oldValue , V newValue)  |
| **功能(查)**                                                 | **方法**                                           |
| 判断集合中是否包含指定的key                                  | boolean containsKey(Object key)                    |
| 判断集合中是否包含指定的value                                | boolean containsValue(Object value)                |
| 根据参数指定的key获取集合中key对应的value值, 如果集合中没有这个key就返回null | V get(Object key)                                  |
| 根据参数指定的key获取集合中key对应的value值, 如果集合中没有这个key, 那就返回一个默认值---defaultValue | default V getOrDefault(Object key, V defaultValue) |
|                                                              |                                                    |
| **功能**                                                     | **方法**                                           |
| 获取集合中键值对的个数                                       | int size()                                         |
| 判断本集合是否是空的                                         | boolean isEmpty()                                  |
| 获取本集合中所有key的Set集合[^注1]                           | Set\<K> keySet()[^注1]                             |
| 获取本集合中所有Value的Collection集合[^注1]                  | Collection\<V\> values()[^注1]                     |
| 获取本集合中所有key-value对(即entry)的Set集合[^注1]          | Set\<Map.Entry\<K , V\> entrySet()[^注1]           |

[^注1]:The `Collection/Set` is backed by the map, so changes to the map are reflected in the collection, and vice-versa. 这里作为返回值的`Collection/Set`集合是没有自己的底层数据的, 它的元素数据是借用的Map集合的底层数据, 所以改变`Collection/Set`集合中的数据会直接改变Map集合中的底层数据 , 而改变Map集合中的数据也会影响到`Colleciotn/Set`集合借用的数据.(这里的改变是指将数据换成了其他的数据)

#### Map.Entry<K , V>

- `Entry<K,V>`是Map集合内的一个静态内部类
- `Map.Entry<K,V>`封装了两个成员变量, 一个是key, 一个是key所对应的value

##### 常用的方法

| **功能**                                                     | **方法**            |
| ------------------------------------------------------------ | ------------------- |
| 获取key                                                      | K getKey()          |
| 获取value                                                    | V getValue()        |
| Replaces the value corresponding to this entry with the specified value.(这里改了,Map集合中对应的value也会改) | V setValue(V value) |

### 3. 元素放入`HashMap`集合的原理

- 使用元素的key调用hashCode方法获取对应的哈希码值, 再由某种哈希算法计算在数组中的索引位置
- 若该位置没有元素, 则将该键值对直接放入即可
- 若该位置有元素,则使用key与已有元素依次比较哈希值,若哈希值不相同,则将该元素直接放入
- 若key与已有元素的哈希值相同, 则使用key调用equals方法与已有元素依次比较
- 若相等则将对应的Value修改, 否则将键值对直接放入即可

### 4. HashMap类中相关的常量

```java
/**
     * The next size value at which to resize (capacity * load factor).
     *
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;
    
    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    
    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     */
    static final int TREEIFY_THRESHOLD = 8;
    
    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     */
    static final int MIN_TREEIFY_CAPACITY = 64;
    
```

## 四. Collections工具类

### 1. 基本概念

- `java.util.Collections`类主要提供了对集合进行操作或者返回集合的静态方法
- 像这种不是用来创建对象, 而是向外提供具有各种各样的功能的静态方法的类被叫做==工具类==

### 2. 常用的方法(都是静态方法)

| 功能                                                         | 方法                                                         | 返回值                | 静态方法的泛型                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ | --------------------- | --------------------------------------------------- |
| ==获取`Collection`集合中的最大值最小值==                     |                                                              |                       |                                                     |
| 按照自然排序的规则  (即元素实现的`Comparable`接口的排序规则)  获取`Collection`集合中的最大的元素 | max(Collection<? extends T> coll)                            | T                     | \<T extends Object & Comparable<? super T\>\>[^注2] |
| 按照比较器`comp`排序的规则获取`Collection`集合中的最大的元素 | max(Collection\<? extends T\> coll , Comparator\<? super T\> comp) | T                     | \<T\>                                               |
| 按照自然排序的规则  (即元素实现的`Comparable`接口的排序规则)  获取`Collection`集合中的最小的元素 | min(Collection<? extends T> coll)                            | T                     | \<T extends Object & Comparable<? super T\>\>[^注2] |
| 按照比较器`comp`排序的规则获取`Collection`集合中的最小的元素 | min(Collection\<? extends T\> coll , Comparator\<? super T\> comp) | T                     | \<T\>                                               |
| ==对`List`集合中的元素进行排序==                             |                                                              |                       |                                                     |
| 以自然排序的规则对`List`集合进行排序[^注3]                   | sort(List\<T\> list)                                         | void                  | \<T extends Comparable\<? super T\>\>               |
| 以比较器排序的规则对`List`集合进行排序[^注3]                 | sort(List\<T\> list, Comparator\<? surper T\> comp)          | void                  | \<T\>                                               |
| ==对`List`集合中的元素顺序进行洗牌==                         |                                                              |                       |                                                     |
| 对`List`集合中的所有元素的排列顺序进行洗牌---即打乱原来的排列顺序(以默认的洗牌规则洗牌) | shuffle(List<?> list)                                        | void                  |                                                     |
| 对`List`集合中的所有元素的排列顺序进行洗牌---即打乱原来的排列顺序(以指定的洗牌规则random洗牌) | shuffle(List<?> list , Random random)                        | void                  |                                                     |
| ==交换`List`集合两个位置上的元素==                           |                                                              |                       |                                                     |
| 交换`List`集合中下标为i和下标为j的位置上的元素               | swap(List<?> list , int i , int j)                           | void                  |                                                     |
| ==反转`List`集合==                                           |                                                              |                       |                                                     |
| 反转`List`集合list中的所有元素                               | reverse(List\<?\> list)                                      | void                  |                                                     |
| ==创建一个`List`集合的副本==                                 |                                                              |                       |                                                     |
| 把`List`集合src中的所有元素装到空的`List`集合dest中          | copyList(List\<? super T\> dest, List\<? extends T\> src)    | void                  | \<T\>                                               |
| ==将线程不安全的集合转换成线程安全的集合==                   |                                                              |                       |                                                     |
| 将线程不安全的集合collection转换为线程安全的Collection集合[^注4] | `synchronizedCollection(Collection<T> collection)`           | `Collection<T>`[^注4] | `<T>`jian                                           |
| 将线程不安全的集合list转换为线程安全的List集合[^注5]         | `synchronizedList(List<T> list)`                             | `List<T>`[^注5]       | `<T>`                                               |
| 将线程不安全的集合set转换为线程安全的Set集合[^注6]           | `synchronizedSet(Set<T> set)`                                | `Set<T>`[^注6]        | `<T>`                                               |
| 将线程不安全的集合map转换为线程安全的Map集合[^注7]           | `sychronizedMap(Map<K,V> map)`                               | `Map<K,V>`[^注7]      | `<K,V>`                                             |

[^注2]: 集合中元素的类型必须继承Object类且实现Comparable接口
[^注3]: 这种排序允许有重复的元素

[^注4]: 返回的是一个新的Collection集合, 实际类型是Collections.SynchronizedCollection<T>
[^注5]:返回的是一个新的List集合, 实际类型是Collections.SynchronizedList<T>
[^注6]: 返回的是一个新的Set集合, 实际类型是Collections.SynchronizedSet<T>

[^注7]: 返回的是一个新的Map集合, 实际类型是Collecitions.SynchronizedMap<K,V>