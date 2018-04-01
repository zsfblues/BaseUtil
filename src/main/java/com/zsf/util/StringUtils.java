package com.zsf.util;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created on 2017/10/31.
 *
 * @author zhoushengfan
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**
     *
     * 判定输入汉字
     * @param c
     * @return
     */

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;
        }
        return false;

    }

    /**
     * 将字符串的第一位转为小写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0)))
            return str;
        else {
            char[] chars = str.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return new String(chars);
        }
    }

    /**
     * 将字符串的第一位转为大写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0)))
            return str;
        else {
            char[] chars = str.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        }
    }


    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1
     *            原文本
     * @param str2
     *            指定字符
     * @param offset
     *            偏移的索引
     * @return 截取后的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (str1 == null || "".equals(str1.trim())) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 获取文件扩展名
     *
     * @param filename 文件名称
     * @return
     */
    public static String getFileExtension(String filename) {

        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return null;
        }

        return filename.substring(index);
    }

    /**
     * 生成随机的UUID字符串
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 去除字符串两边的括号
     *
     * @return
     */
    public static String eraseBracketsInTwoSide(String str){
        if (str == null || "".equals(str.trim()) || str.length() <= 1){
            return str;
        }
        int len = str.length();
        String first = str.substring(0, 1);
        String result = str;
        if (BracketsType.angleBracket.equals(first)){
            result = str.substring(1, len - 1);
        }else if (BracketsType.brace.equals(first)){
            result = str.substring(1, len - 1);
        }else if (BracketsType.parenthesis.equals(first)){
            result = str.substring(1, len - 1);
        }else if (BracketsType.squareBracket.equals(first)){
            result = str.substring(1, len - 1);
        }

        return result;
    }

    private class BracketsType{
        static final String parenthesis = "(";
        static final String squareBracket = "[";
        static final String angleBracket = "<";
        static final String brace = "{";

    }

    /**
     * 字符串转int数组
     * @param s
     * @return
     */
    public static int[] stringToInts(String s){
        String str[] = s.split(",");
        int[] n = new int[str.length];
        for(int i = 0;i<str.length;i++){
            n[i] = Integer.parseInt(str[i]);
        }
        return n;
    }

    /**
     * 字符串转BigDecimal数组
     * @param s
     * @return
     */
    public static BigDecimal[] stringToBigDecimal(String s){
        String str[] = s.split(",");
        BigDecimal[] n = new BigDecimal[str.length];
        for(int i = 0;i<str.length;i++){
            n[i] = new BigDecimal(str[i]);
        }
        return n;
    }
}
