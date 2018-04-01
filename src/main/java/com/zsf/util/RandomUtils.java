package com.zsf.util;


import java.util.LinkedList;
import java.util.Random;

/**
 * <pre>
 * 类的描述： 随机名称生成
 * 项目名称： paraMaster
 * 类的名称： RandomName
 *
 *  </pre>
 */
public final class RandomUtils {

    private RandomUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 生成指定位数随机码
     */
    public static String getRandNum(int charCount) {
        StringBuilder charValue = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue.append(String.valueOf(c));
        }
        return charValue.toString();
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

    /** 带帐号生成随机名称 */
    public static String getFileName(long accountID)
    {
        return methodA(accountID) + methodA(getRandomNum())+ methodA(getRandomNum());
    }

    /** 4位随机码 */
    private static int getRandomNum()
    {
        int randomNum = new Random().nextInt(100000000);
        if (randomNum < 10000000){
            return getRandomNum();
        }
        return randomNum;
    }

    // 得到62进制数
    private static String methodA(long num)
    {
        LinkedList<Character> strs = new LinkedList<Character>();
        long mus = getDivideMus(num);
        long mod = getDivideMod(num);
        strs.push(get62bit(mod));
        while (mus >= 62)
        {
            long temp = mus;
            mus = getDivideMus(temp);
            mod = getDivideMod(temp);
            strs.push(get62bit(mod));
        }
        if (mus > 0)
            strs.push(get62bit(mus));
        StringBuffer temp = new StringBuffer("");
        while (!strs.isEmpty())
        {
            temp.append(strs.pop() + "");
        }
        return temp.toString();
    }

    // 得到余数
    private static long getDivideMus(long num)
    {
        long divide = 62;
        return num / divide;
    }

    // 得到模数
    private static long getDivideMod(long num)
    {
        long divide = 62;
        return num % divide;
    }

    // 0~61
    private static char get62bit(long num)
    {
        if (num < 10)
            return (char) (48 + num);
        if (num < 36)
            return (char) (87 + num);
        return (char) (29 + num);
    }
}
