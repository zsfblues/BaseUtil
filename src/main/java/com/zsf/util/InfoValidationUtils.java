package com.zsf.util;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNumeric;


/**
 * 主要功能： 用于验证数据格式
 *
 * */
@SuppressWarnings("rawtypes")
public final class InfoValidationUtils {

	private InfoValidationUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }
	//邮箱表达式
	private final static Pattern email_pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

	//手机号表达式
	private final static Pattern phone_pattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}");
	
	//银行卡号表达式
	private final static Pattern bankNo_pattern = Pattern.compile("^[0-9]{16,19}$");
	
	//座机号码表达式
	private final static Pattern plane_pattern = Pattern.compile("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$");
	
	//非零表达式
	private final static Pattern notZero_pattern = Pattern.compile("^\\+?[1-9][0-9]*$");
	
	//数字表达式
	private final static Pattern number_pattern = Pattern.compile("^[0-9]*$");

	//浮点表达式
    private final static Pattern float_pattern = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
	
	//大写字母表达式
	private final static Pattern upChar_pattern = Pattern.compile("^[A-Z]+$");
	
	//小写字母表达式
	private final static Pattern lowChar_pattern = Pattern.compile("^[a-z]+$");

	//大小写字母表达式
	private final static Pattern letter_pattern = Pattern.compile("^[A-Za-z]+$");
	
	//中文汉字表达式
	private final static Pattern chinese_pattern = Pattern.compile("^[\u4e00-\u9fa5],{0,}$");
	
	//条形码表达式
	private final static Pattern onecode_pattern = Pattern.compile("^(([0-9])|([0-9])|([0-9]))\\d{10}$");
	
	//邮政编码表达式
	private final static Pattern postalcode_pattern = Pattern.compile("([0-9]{3})+.([0-9]{4})+"); 
	
	//IP地址表达式
	private final static Pattern ipaddress_pattern = Pattern.compile("[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"); 
	
	//URL地址表达式
	private final static Pattern url_pattern = Pattern.compile("^(https?://)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([/\\w \\.-]*)*/?$");
	
	//用户名表达式(根据具体业务修改)
	private final static Pattern username_pattern = Pattern.compile("^[A-Za-z0-9-\u4e00-\u9fa5]{2,8}$");

	// 金钱表达式
    private final static Pattern money_pattern = Pattern.compile("(^[1-9](\\d+)?(\\.\\d{1,2})?$)|(^(0){1}$)|(^\\d\\.\\d{1,2}?$)");

    // 英文句子表达式
    private final static Pattern english_sentence_pattern = Pattern.compile("(([A-Za-z]+(\\([A-Za-z]*\\))?|\"[A-Za-z]+(\\([A-Za-z]*\\))?\")\\s*[',;]?\\s*)*([A-Za-z]+(\\([A-Za-z]*\\))?\\s*[.?!])*");
	/**
	 * 验证是否为空串 (包括空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串,返回true)
	 * @param str 验证字符
	 * @return boolean   
	 */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || str.length() == 0) {
        	 return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
            return false;
            }
        }

        return true;
    }
 
    
	/**
	 * 是否不为空
	 * @param s
	 */
	public static boolean isNotEmpty(String s){
		return s != null && !"".equals(s.trim());
	}
	
	/**
	 * 验证非零正整数
	 * @param str 验证字符
	 * @return boolean 
	 */
	public static boolean isNotZero(String str) {
        if (isEmpty(str)){
            return false;
        }
		return notZero_pattern.matcher(str).matches();
	}

	
	/**
	 * 验证是数字
	 * @param str 验证字符
	 * @return boolean   
	 */
	public static boolean isNumber(String str) {
        if (isEmpty(str)){
            return false;
        }
		return number_pattern.matcher(str).matches();
	}

    /**
     * 验证是浮点数
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isFloat(String str) {
        if (isEmpty(str)){
            return false;
        }
        return float_pattern.matcher(str).matches();
    }

    /**
	 * 验证是大写字母
	 * @param str 验证字符
	 * @return boolean   
	 */
	public static boolean isUpChar(String str) {
        if (isEmpty(str)){
            return false;
        }
		return upChar_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验证是小写字母
	 * @param str 验证字符
	 * @return boolean   
	 */
	public static boolean isLowChar(String str) {
        if (isEmpty(str)){
            return false;
        }
		return lowChar_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验证是英文字母
	 * @param str 验证字符
	 * @return boolean   
	 */
	public static boolean isLetter(String str) {
        if (isEmpty(str)){
            return false;
        }
		return letter_pattern.matcher(str).matches();
	}
	
	
	/**
	 * 验证输入汉字
	 * @param str 验证字符
	 * @return boolean
	 */
	public static boolean isChinese(String str) {
        if (isEmpty(str)){
            return false;
        }
		return chinese_pattern.matcher(str).matches();
	}

	/**
	 * 验证是否是条形码
	 * @param oneCode 条形码
	 * @return boolean 
	 */
	public static boolean isOneCode(String oneCode) {
        if (isEmpty(oneCode)){
            return false;
        }
		return onecode_pattern.matcher(oneCode).matches();
	}

	public static boolean isMoney(String str){
	    if (isEmpty(str)){
	        return false;
        }
        return money_pattern.matcher(str).matches();
    }

	/**
	 * 是否含有特殊符号
	 *
	 * @param str 待验证的字符串
	 * @return 是否含有特殊符号
	 */
	public static boolean hasSpecialCharacter(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	
	/**
	 * 验证邮箱是否正确
	 * @param email  邮箱地址
	 * @return boolean   
	 */
	public static boolean isEmail(String email) {
        if (isEmpty(email)){
            return false;
        }
		return email_pattern.matcher(email).matches();
	}

	
	/**
	 * 验证手机号是否正确
	 * @param phone 手机号码
	 * @return boolean   
	 */
	public static boolean isPhone(String phone) {
        if (isEmpty(phone)){
            return false;
        }
		 return phone_pattern.matcher(phone).matches();
	}

	
	/**
	 * 验证座机号码是否正确
	 * @param plane 座机号码
	 * @return boolean   
	 */
	public static boolean isPlane(String plane) {
        if (isEmpty(plane)){
            return false;
        }
		 return plane_pattern.matcher(plane).matches();
	}
	
	
	/**
	 * 验证邮政编码是否正确
	 * @param postalcode 邮政编码
	 * @return boolean   
	 */
	public static boolean isPostalCode(String postalcode) {
        if (isEmpty(postalcode)){
            return false;
        }
		return postalcode_pattern.matcher(postalcode).matches();
	}
	

	/**
	 * 验证IP地址是否正确
	 * @param ipaddress IP地址
	 * @return boolean   
	 */
	public static boolean isIpAddress(String ipaddress){
        if (isEmpty(ipaddress)){
            return false;
        }
        return ipaddress_pattern.matcher(ipaddress).matches();
	}
	
	
	
	/**
	 * 验证URL地址是否正确
	 * @param url 地址
	 * @return boolean   
	 */
	public static boolean isURL(String url){
        if (isEmpty(url)){
            return false;
        }
		 return url_pattern.matcher(url).matches();
	}
	
	
	
    
    /**
     * 验证是否是正整数
     * @param str 验证字符
     * @return boolean
     */
	public static boolean isInteger(String str){
		try{
			Integer.valueOf(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	/**
	 * 验证是否是小数
	 * @param paramString 验证字符
	 * @return boolean   
	 */
	public static boolean isPoint(String paramString){
		if(paramString.indexOf(".") > 0){
			if(paramString.substring(paramString.indexOf(".")).length() > 3){
				return false;
			}
		}
		return true;
	}
    
	
	/**
	 * 验证是否银行卡号
	 * @param bankNo 银行卡号
	 * @return
	 */
	public static boolean isBankNo(String bankNo){
        if (isEmpty(bankNo)){
            return false;
        }
		//替换空格
		bankNo = bankNo.replaceAll(" ", "");
		//银行卡号可为12位数字
		if(12 == bankNo.length()){
			return true;
		}
		//银行卡号可为16-19位数字
		return bankNo_pattern.matcher(bankNo).matches();
	}

	/**
	 * 验证身份证号码是否正确
	 * @param IDStr 身份证号码
	 * @return boolean   
	 */
    @SuppressWarnings("unchecked")
    public static String IDCardValidate(String IDStr) throws ParseException {
        // 记录错误信息
        String errorInfo = "";
        String[] valCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (!isNumeric(Ai)) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
            errorInfo = "身份证生日无效";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围";
                return errorInfo;
            }
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误";
            return errorInfo;
        }

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = valCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (!Ai.equalsIgnoreCase(IDStr)) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }

        return "";
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        if (isEmpty(strDate)){
            return false;
        }
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }


    /**
	 * 判断是否有特殊字符
	 * @param str 验证字符
	 * @return boolean   
	 */
	public static boolean isPeculiarStr(String str){
        if (StringUtils.isBlank(str)){
            return false;
        }
		boolean flag = false;
		String regEx = "[^0-9a-zA-Z\u4e00-\u9fa5]+";
		if(str.length() != (str.replaceAll(regEx, "").length())) {
			flag = true;
		}
			return  flag;
	}
	
	
	/**
	 * 判断是否为用户名账号(规则如下：用户名可以包含数字、字母、中文组成的2-8位字符)
	 * @param username 用户名 
	 * @return boolean   
	 */
	public static boolean isUserName(String username) {
        if (isEmpty(username)){
            return false;
        }
		return username_pattern.matcher(username).matches();
	}

	/**
	 * 获取字符串中文字符的长度（每个中文算2个字符）.
	 *
	 * @param str 指定的字符串
	 * @return 中文字符的长度
	 */
	public static int chineseLength(String str) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		if (!isEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				/* 获取一个字符 */
				String temp = str.substring(i, i + 1);
				/* 判断是否为中文字符 */
				if (temp.matches(chinese)) {
					valueLength += 2;
				}
			}
		}
		return valueLength;
	}

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    // 中文字符长度为2
                    valueLength += 2;
                } else {
                    // 其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str
     *            指定的字符串
     * @param maxL
     *            要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public static int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            // 获取一个字符
            String temp = str.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * 描述：是否只是字母和数字.
     *
     * @param str
     *            指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str
     *            指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：从输入流中获得String.
     *
     * @param is
     *            输入流
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // 最后一个\n删除
            if (sb.indexOf("\n") != -1
                && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串到指定字节长度.
	 *
     * @param str
	 *            the str
	 * @param length
	 *            指定字节长度
	 * @return 截取后的字符串
	 */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str
     *            文本
     * @param length
     *            字节长度
     * @param dot
     *            省略符号
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }



    /**
     * 描述：获取字节长度.
     *
     * @param str
     *            文本
     * @param charset
     *            字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取大小的描述.
     *
     * @param size
     *            字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                // size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    // size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip
     *            the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16
            | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    /**
     * 获取UUID
     *
     * @return 32UUID小写字符串
     */
    public static String gainUUID() {
        String strUUID = UUID.randomUUID().toString();
        strUUID = strUUID.replaceAll("-", "").toLowerCase();
        return strUUID;
    }


	/**
	 * 手机号码，中间4位星号替换
	 *
	 * @param phone 手机号
	 * @return 星号替换的手机号
	 */
	public static String phoneNoHide(String phone) {
		// 括号表示组，被替换的部分$n表示第n组的内容
		// 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
		// 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
		// "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
		// 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
		return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * 银行卡号，保留最后4位，其他星号替换
	 *
	 * @param cardId 卡号
	 * @return 星号替换的银行卡号
	 */
	public static String cardIdHide(String cardId) {
		return cardId.replaceAll("\\d{15}(\\d{3})", "**** **** **** **** $1");
	}

	/**
	 * 身份证号，中间10位星号替换
	 *
	 * @param id 身份证号
	 * @return 星号替换的身份证号
	 */
	public static String idHide(String id) {
		return id.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1** **** ****$2");
	}

	/**
	 * 是否为车牌号（沪A88888）
	 *
	 * @param vehicleNo 车牌号
	 * @return 是否为车牌号
	 */

	public static boolean checkVehicleNo(String vehicleNo) {
        if (isEmpty(vehicleNo)){
            return false;
        }
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");
		return pattern.matcher(vehicleNo).find();

	}

	/**
	 * 是否是日期
	 * 20120506 共八位，前四位-年，中间两位-月，最后两位-日
	 *
	 * @param date    待验证的字符串
	 * @param yearlen yearlength
	 * @return 是否是真实的日期
	 */
	public static boolean isRealDate(String date, int yearlen) {
	    if (isEmpty(date)){
	        return false;
        }
		int len = 4 + yearlen;
		if (date == null || date.length() != len){

            return false;
        }

		if (!date.matches("[0-9]+")){
            return false;
        }

		int year = Integer.parseInt(date.substring(0, yearlen));
		int month = Integer.parseInt(date.substring(yearlen, yearlen + 2));
		int day = Integer.parseInt(date.substring(yearlen + 2, yearlen + 4));

		if (year <= 0){

            return false;
        }
		if (month <= 0 || month > 12){

            return false;
        }

		if (day <= 0 || day > 31){
            return false;
        }

		switch (month) {
			case 4:
			case 6:
			case 9:
			case 11:
				return day > 30 ? false : true;
			case 2:
				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
                    return day > 29 ? false : true;
                }
				return day > 28 ? false : true;
			default:
				return true;
		}
	}



	/** 验证是否图片 */
	public static boolean isImage(File file) {
		if (!file.exists()) {
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(file);
			if (img == null || img.getWidth(null) <= 0
					|| img.getHeight(null) <= 0) {
				return false;
			}
			int w = img.getWidth(null);
			int h = img.getHeight(null);
			long size = file.length();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}

	public static boolean isNullOrPositiveInteger(Integer integer){
        return integer == null || integer >= 0;
    }

    public static boolean isNullOrNegativeInteger(Integer integer){
        return integer == null || integer <= 0;
    }


	/**
	 * 区号+座机号码+分机号码
	 * @param telephone
	 * @return
	 */
	public static boolean isFixedPhone(String telephone) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(telephone);
		return matcher.matches();
	}


	/**
	 * 邮箱校验
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email){
		boolean flag = false;
		try{
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+[-|_|\\.]?)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	public static boolean isEnglishSentence(String str) {
	    if (isEmpty(str)) {
	        return false;
        }

        return english_sentence_pattern.matcher(str).matches();
    }
}


