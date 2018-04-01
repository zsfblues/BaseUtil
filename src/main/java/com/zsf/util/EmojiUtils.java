package com.zsf.util;


import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;


/**
 * EmojiUtil
 * TODO 后续扩展尽量使用emoji-java处理(依赖包已引入)
 * @see  <a href="https://github.com/vdurmont/emoji-java" />
 *
 */
public final class EmojiUtils {

    private EmojiUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     *  该字符串是否只包含emoji字符
     */
    public static boolean isOnlyEmoji(String str){
        return EmojiManager.isOnlyEmojis(str);
    }

    public static String filterEmoji(String str){
        return EmojiParser.removeAllEmojis(str);
    }

    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i+1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50|| hs == 0x231a ) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() -1) {
                    char ls = source.charAt(i+1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return  isEmoji;
    }
}
