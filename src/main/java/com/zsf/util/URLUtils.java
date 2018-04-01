package com.zsf.util;

import com.google.common.base.Splitter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/10/31.
 *
 * @author zhoushengfan
 */
public final class URLUtils {

    private URLUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取url中特定的参数值
     * @param url
     * @param key 要取值的键
     * @return 对应的值
     */
    public static String getURLValueByKey(String url, String key){
        boolean invalid = url == null || key == null || "".equals(key.trim()) || !url.contains("?");
        if (invalid){
            return "";
        }

        String params = url.substring(url.indexOf("?") + 1, url.length());
        List<String> split = Splitter.onPattern("&").splitToList(params);
        for (String one : split){
            int inx = one.indexOf("=");
            String urlKey = one.substring(0, inx);
            if (key.equals(urlKey)){
                return one.substring(inx + 1);
            }
        }
        return "";
    }

    /**
     *
     * @param url 待处理的url
     * @param params 需要过滤的参数
     * @return 处理后的url
     */
    public static String omitParamsInURL(String url, List<String> params){
        if (url == null || "".equals(url.trim()) || params == null || params.isEmpty()){
            return url;
        }
        Iterator<String> strIter = Splitter.onPattern("\\?|&")
                .trimResults()
                .omitEmptyStrings()
                .split(url).iterator();

        int i = 0;
        String paramSeparator = "&";
        String routerSeparator = "?";
        StringBuilder newUrl = new StringBuilder();
        String route = "";
        while (strIter.hasNext()){
            String str = strIter.next();
            if (i > 0){
                if (!params.contains(str.substring(0, str.indexOf("=")))){
                    newUrl.append(str).append(paramSeparator);
                }
            }else {
                route = str + routerSeparator;
            }

            i++;
        }
        String result = newUrl.toString();
        if (!result.contains(paramSeparator)){
            return route.substring(0, route.length() - 1);
        }else {
            return route + result.substring(0, result.length() - 1);
        }
    }

    /**
     * 返回带参数的get请求url地址
     * @param url url
     * @param params 参数
     * @return 带参数的get请求url地址
     */
    public static String getURLWithParams(String url,Map<String, String> params){
        if (url.contains("?")) {
            return url + joinParams(params);
        }
        return url + "?" + joinParams(params);
    }

    /**
     * 连接参数
     * @param params 参数
     * @return 连接结果
     */
    private static StringBuffer joinParams(Map<String, String> params) {
        StringBuffer result = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            String key = param.getKey();
            String value = param.getValue();
            result.append(key).append('=').append(value);
            if (iterator.hasNext()) {
                result.append('&');
            }
        }
        return result;
    }
}
