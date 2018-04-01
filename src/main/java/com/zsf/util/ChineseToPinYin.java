package com.zsf.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public final class ChineseToPinYin {

    private ChineseToPinYin() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 汉字转拼音的方法
     * 全拼
     * @param name 汉字
     * @return 拼音
     */
    public static String cnToPinyin(String name) {
        String pinyinName = "";
        char[] nameChar = name.toCharArray();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        pinyinName += pinyinArray[0];
                    } else {
                        // do nothing
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pinyinName;
    }

    /**
     * 转化出汉字的大写首字母
     * @param c
     * @return
     */
    public static String cnToUpCaseFirstChar(char c) {
        String pinyinFirst = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (c > 128) {
            try {
                pinyinFirst = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0].substring(0, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pinyinFirst;
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

    public static void main(String[] args) {
        System.out.println(cnToPinyin("鱼坚强"));
    }
}