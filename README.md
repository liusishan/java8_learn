# Java 8 新特性学习

> 本文中代码只有一部分，详细代码参见本代码库


## 1. Lambda 表达式

### 1.1 为什么使用 Lamdba 表达式

> Lambda 是一个 匿名函数，我们可以把 Lambda 表达式理解为是 一段可以传递的代码（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使 Java 的语言表达能力得到了提升。

回顾一下我们之前写的一些代码，比如常见的线程使用匿名内部类

```java
// 匿名内部类
Runnable r1 = new Runnable(){
    @Override
    public void run(){
        System.out.println("Hello World!");
    }
}
```

上面的代码使用 Lambda 表达式

```java
Runnable r2 = () ->  System.out.println("Hello Lambda!");
```

还有使用自定义比较器来进行排序

```java
// 原来匿名内部类
Comparator<Integer> com = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1, o2);
    }
};
TreeSet<Integer> ts = new TreeSet<>(com);
```

使用 Lambda 表达式

```java
Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
TreeSet<Integer> ts = new TreeSet<>(com);
```

​	在上面两个例子中，可以看到使用 Lambda 表达式可以使代码会更加简洁，而且可读性比较好（前提你得知道 Lambda 表达式的语法）。下面将介绍 Lambda 表达式的语法。

-------

### 1.2 Lambda 表达式语法

​	Lambda 表达式在Java 语言中引入了一个新的语法元素和操作符。这个操作符为 “**==->==**” ， 该操作符被称
为 Lambda 操作符或剪头操作符。它将 Lambda 分为两个部分：

**左侧：**指定了 Lambda 表达式需要的所有参数
**右侧：**指定了 Lambda 体，即 Lambda 表达式要执行

**注：**本文练习全部使用 junit 4，不会的同学可以看一下，为了避免篇幅过长，代码段将只保留主体代码

```java
@Test
public void test1() {……}
```

**语法格式一：无参，无返回值，Lambda 体只需要一条语句**

**example：**

```java
@Test
public void test1() {
    int num = 0;// jdk 1.7前，必须是final

    Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello World!" + num);
        }
    };

    r.run();

    System.out.println("---------------");
    Runnable r1 = () -> System.out.println("Hello Lambda!" + num);
    r1.run();
}
```

**语法格式二 ：Lambda  需要一个参数**

**example：**

```java
Consumer<String> con = (x) -> System.out.println(x);
con.accept("Java 8 Lambda 表达式学习");
```

**语法格式三 ：Lambda  只需要一个参数时，参数的小括号可以省略**

**example：**

```java
Consumer<String> con = x -> System.out.println(x);
con.accept("Java 8 Lambda 表达式学习");
```

**语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句**

**example：**

```java
Comparator<Integer> comparator = (x, y) -> {
    System.out.println("函数式接口");
    return Integer.compare(x, y);
};
```

**语法格式五：若 Lambda 体中只有==一条==语句，==return 和大括号==都可以省略不写**

**example：**

```java
Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
```

**语法格式六：Lambda 表达式的参数列表的数据/类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”**

**example：**

```java
(Integer x, Integer y) -> Integer.compare(x, y);
```

**总结： 左右遇一括号省，左侧推断类型省，能省则省**

**补充：类型推断**

Lambda 表达式中无需指定类型，程序依然可以编译，这是因为 javac 根据程序的上下文，在后台推断出了参数的类型。Lambda 表达式的类型依赖于上下文环境，是由编译器推断出来的。这就是所谓的 “**类型推断**”。

Lambda 表达式需要“**函数式接口**”的支持，下面讲一下什么是函数式接口



## 2. 函数式接口

### 2.1 什么是函数式接口？

函数式接口（function interface 也叫功能性接口，其实是同一个东西）

​	简单来说， 函数式接口是只包含一个方法的接口。比如Java标准库中的 java.lang.Runnable 和 java.util.Comparator 都是 典 型 的 函 数 式 接 口 。 j a v a 8 提 供 `@FunctionalInterface` 作为注解，用来检查接口是否是函数式接口，这个注解是非必须的，只要接口符合函数式接口的标准（即只包含一个方法的接口），虚拟机会自动判断， 但最好在接口上使用注解`@FunctionalInterface`进行声明，以免团队的其他人员错误地往接口中添加新的方法。Java中的lambda无法单独出现，它需要一个函数式接口来盛放，lambda表达式方法体其实就是函数接口的实现。

### 2.2 自定义函数式接口

**example：**

```java
// 未使用泛型
@FunctionalInterface
public interface MyNumber {
    public double getValue();
}

// 函数式接口中使用泛型
@FunctionalInterface
public interface MyFunc<T> {
    public T getValue(T t);
}
```

**作为参数传递 Lambda 表达式**

**example：**

```java
public String toUpperString(MyFunc<String> mf,String str){
    return mf.getValue(str);
}

// 作为参数传递 Lambda 表达式
String newStr = toUpperString(
		(str) -> str.toUpperCase(),"abcdef");
System.out.println(newStr);
```

**==注意：作为参数传递  Lambda 表达式：为了将  Lambda  表达式作为参数传递，接收 Lambda 表达式的参数类型必须是与该  Lambda  表达式兼容的函数式接口的类型。==**

-----

### 2.3 Java 内置四大核心函数式接口

![](images/01.png)

**Consumer\<T> ： 消费型接口**

**example：**

```java
@Test
public void test1() {
    happy(10000, (m) -> System.out.println("大保健，消费" + m + "元"));
}

public void happy(double money, Consumer<Double> consumer) {
    consumer.accept(money);
}
```

**Supplier\<T> ：供给型接口**

**example:**

```java
// Supplier<T> : 供给型接口
@Test
public void test2() {
    List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
    for (Integer integer : numList) {
        System.out.println(integer);
    }
}

// 需求：产生指定个数的整数，并放入集合中
public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < num; i++) {
        Integer n = supplier.get();
        list.add(n);
    }
    return list;
}
```

**Function<T,R> ：函数型接口 **

**example：**

```java
// Function<T,R> : 函数型接口
@Test
public void test3() {
    String s = strHandler("\t\t\t 你很厉害哦", (str) -> str.trim());
    System.out.println(s);

    String s1 = strHandler("你很厉害哦", (str) -> str.substring(2, 4));
    System.out.println(s1);
}

// 需求：用于处理字符串
public String strHandler(String str, Function<String, String> fun) {
    return fun.apply(str);
}
```

**Predicate\<T> : 断言型接口**

**example：**

```java
@Test
public void test4() {
    List<String> list = Arrays.asList("Hello", "World", "Lambda", "www", "ok");
    List<String> stringList = filterStr(list, (str) -> str.length() > 3);

    for (String s : stringList) {
        System.out.println(s);
    }
}

// 需求：将满足条件的字符串，放入集合中
public List<String> filterStr(List<String> list, Predicate<String> predicate) {
    List<String> strList = new ArrayList<>();

    for (String str : list) {
        if (predicate.test(str))
            strList.add(str);
    }
    return strList;
}
```

### 2.4 其他接口

![](images/02.png)





## 3. 方法引用与构造器引用

### 3.1 方法引用

当要传递给 **Lambda 体**的操作，已经有**实现的方法**了，可以使用方法引用！（可以理解为方法引用是Lambda 表达式的另外一种表现形式）

**方法引用：使用操作符 ”`::`“  将方法名和对象或类的名字分隔开来。**

有以下三种主要使用情况：

- **对象 :: 实例方法**
- **类 :: 静态方法**
- **类 :: 实例方法**

**example(1)：**

​	`(x) -> System.out.println(x);`

**等同于：**

​	`System.out::println;`

​	打印的这个操作 `println()` 这个方法已经实现了，所以就可以使用 方法引用。

**example(2)：**

​	`BinaryOperator<Double> bo = (x, y) -> Math.pow(x, y);`

**等同于：**

​	`BinaryOperator<Double> bo = Math::pow;`



下面详细介绍一下使用方法引用的几种方式：

- **对象 :: 实例方法**

**example：**

```java
PrintStream ps1 = System.out;
Consumer<String> con = (x) -> ps1.println(x);
// 演化过程
PrintStream ps = System.out;
Consumer<String> con1 = ps::println;

Consumer<String> con2 = System.out::println;
con2.accept("abcdef");
```

```java
Employee emp = new Employee(10, "liu", 18, 555.00); // Employee 是我自定义的类
Supplier<String> sup = () -> emp.getName(); // 使用 Lambda 表达式 体中获取 emp 的名字
String str = sup.get();
System.out.println(str);

Supplier<Integer> sup2 = emp::getAge; // 使用对象 :: 实例方法获取年龄
Integer num = sup2.get();
System.out.println(num);
```

- **类 :: 静态方法**

**example：**

```java
Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
Comparator<Integer> com2 = Integer::compare;
```

**==注意①：== 类 :: 静态方法** 的引用有个**==前提==**，**Lambda 体中的这个方法的参数列表和返回值类型要与我函数式接口中，抽象参数列表和返回值都相同**

- **类 :: 实例方法**

**example：**

```java
BiPredicate<String, String> bp = (x, y) -> x.equals(y); // Lambda 表达式
BiPredicate<String, String> bp2 = String::equals; // 方法引用
```

**==注意②：==** 当参数列表中的第一个参数，是实例方法的**调用者**，参数列表第二个参数是 实例方法的**参数实例**（或**无参数时**）这种情况下就可以使用 `类::实例方法名`

**再强调一下要注意的点：**

```
注意:
* ① Lambda 体中调用的方法的参数列表与返回值类型，要与函数式接口中的抽象方法的参数列表和返回值保持一致！
* ② 若 Lambda 参数列表中的第一个参数，是实例方法的调用者，参数列表第二个参数是 实例方法的参数（或无参数）时，可以使用ClassName::method
```

------

### 3.2 构造器引用

**格式：==ClassName :: new==**

与函数式接口相结合，自动与函数式接口中方法兼容。可以把构造器引用赋值给定义的方法，与构造器参数
列表要与接口中抽象方法的参数列表一致！

**example：**

```java
Supplier<Employee> sup = () -> new Employee();
// 原来 Lambda 表达式
Employee employee = sup.get();

// 构造器引用方式 默认使用无参构造器（用函数式接口的参数列表匹配构造器参数）
Supplier<Employee> sup2 = Employee::new;
```

可能有同学要问，它怎么知道我要用那构造器？实际上他是**==用函数式接口的参数列表匹配构造器参数==**。看下面这个例子

```java
Function<Integer, Employee> fun = (x) -> new Employee(x);

Function<Integer, Employee> fun2 = Employee::new;
Employee employee = fun2.apply(101);
System.out.println(employee);
```

Function函数式接口是这样定义的

```java
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.将此函数应用于给定的参数。
     *
     * @param t the function argument 给定一个参数
     * @return the function result 返回以一个结果
     */
    R apply(T t);
}
```

上面使用 Function 函数式接口，抽象方法里面有一个参数 `T` 那么就会去找 Employee 里面找对应的构造器，前提是 Employee 类里有对应的构造器，如果没有语法上会报错的。

**==注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！==**

------

### 3.3 数组引用

**格式：==Type[] :: new==**

**example：**

```java
Function<Integer, String[]> fun = (x) -> new String[x]; // Lambda 表达式
String[] apply = fun.apply(10);
System.out.println(apply.length);

Function<Integer, Integer[]> fun2 = Integer[]::new; // 数组引用
Integer[] apply1 = fun2.apply(20);
System.out.println(apply1.length);
```



**总结**：不管是方法引用和构造器引用只不过是基于原来的语法进行的改进，变种成一种更简洁的语法，看起来更美观，也更方便，更利于使用。



## 4. Stream API





