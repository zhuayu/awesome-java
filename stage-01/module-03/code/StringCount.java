/*
 *@ 题目要求：
    编程统计字符串"ABCD123!@#$%ab"中大写字母、小写字母、数字、其它字符的个数并打印出来。 

 *@ 技术点：
    1. 遍历
    2. 类型转换
    3. 正则匹配

  *@ 实现思路：
    1. 遍历字符串
    2. 匹配字符串类型
    3. 统计不同类型的个数
 */

public class StringCount {
    private String str;
    private String[] strArray;

    public StringCount() {}

    public StringCount(String str) {
        this.str = str;
        this.strArray = str.split("");
    }

    public void start() {
        int upperCharCount = 0;
        int lowerCharCount = 0;
        int numberCharCount = 0 ;
        int otherCharCount = 0;

        for (String str: strArray) {
            if (str.matches("[A-Z]")){
                upperCharCount++;
            }else if(str.matches("[a-z]")){
                lowerCharCount++;
            }else if(str.matches("[0-9]")){
                numberCharCount++;
            }else{
                otherCharCount++;
            }
        }

        String inputStr = "输入字符串：" + str;
        String upperCharCountPrintStr = "大写字符出现了" + upperCharCount + "次";
        String lowerCharCountPrintStr = "小写字符出现了" + lowerCharCount + "次";
        String numberCharCountPrintStr = "数字字符出现了" + numberCharCount + "次";
        String otherCharCountPrintStr = "其它字符出现了" + otherCharCount + "次";
        System.out.println(inputStr);
        System.out.println(upperCharCountPrintStr);
        System.out.println(lowerCharCountPrintStr);
        System.out.println(numberCharCountPrintStr);
        System.out.println(otherCharCountPrintStr);
    }
}
