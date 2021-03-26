/*
 *@ 题目要求：

    准备一个 HashMap 集合，统计字符串"123,456,789,123,456"中每个数字字符串出现的次数并打印出来。 如： 

    123 出现了 2 次 
    456 出现了 2 次 
    789 出现了 1 次 

 *@ 技术点：
    1. 集合
    2. 统计

  *@ 实现思路：
    1. 遍历字符串
    2. 如果集合中存在，数量 + 1
 */

import java.util.HashMap;
import java.util.Set;

public class NumberCount {
    private String str;
    private String[] strArray;

    public NumberCount() {}

    public NumberCount(String str) {
        this.str = str;
        this.strArray = str.split(",");
    }

    public void start() {
        HashMap<String,Integer> map = new HashMap<>();
        for (String str: strArray) {
            int count = map.getOrDefault(str, 0);
            map.put(str, count + 1);
        }

        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + "出现了" + map.get(key) + "次了");
        }
    }
}
