package com.zsf.entity;

import com.alibaba.fastjson.JSONObject;
import com.zsf.util.BaseVO;
import com.zsf.util.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2017/11/8.
 *
 * @author zhoushengfan
 */
public class EntityFieldFilter {

    /**
     * 此list只能用于过滤一种类型对象，当需要在一个EntityFieldFilter对象中过滤另一种类型对象时
     * 需要使用该类的 newList
     */
    private List<Map<String, Object>> mapList = new ArrayList<>();

    /**
     * @param obj             要过滤的实体类
     * @param filterFieldName 实体类中要过滤的字段名(字段名不能相同且不能是基本数据类型)
     * @param <T>
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public <T extends BaseEntity> void filter(T obj, List<String> filterFieldName) throws Exception {
        if (obj == null || filterFieldName == null) {
            return;
        }

        Method[] superMethods = null;
        if (obj instanceof BaseVO) {
            superMethods = obj.getClass().getSuperclass().getDeclaredMethods();
        }
        String methodPrefix = "set";
        Method[] methods = obj.getClass().getDeclaredMethods();
        methods = ArrayUtils.addAll(superMethods, methods);
        for (Method m : methods) {
            if (m.getName().startsWith(methodPrefix)) {
                String fieldName = m.getName().replace(methodPrefix, "");
                fieldName = StringUtils.toLowerCaseFirstOne(fieldName);
                if (filterFieldName.contains(fieldName)) {
                    m.invoke(obj, (Object) null);
                    filterFieldName.remove(fieldName);
                    if (filterFieldName.isEmpty()) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * @param excludeFilterFieldName 实体类中要保留的字段名(字段名不能相同且不能是基本数据类型)
     * @param <T>
     * @return 保留后的新对象(可能为null)
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> T notFilter(T obj, List<String> excludeFilterFieldName) throws Exception {
        if (obj == null || excludeFilterFieldName == null) {
            return null;
        }

        Field[] superField = null;
        if (obj instanceof BaseVO) {
            superField = obj.getClass().getSuperclass().getDeclaredFields();
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        fields = ArrayUtils.addAll(superField, fields);

        List<String> ignoreList = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!excludeFilterFieldName.contains(fieldName)) {
                ignoreList.add(fieldName);
            }
        }
        if (!ignoreList.isEmpty()) {
            T newObj = (T) obj.getClass().newInstance();
            BeanUtils.copyProperties(obj, newObj, ignoreList.toArray(new String[ignoreList.size()]));
            return newObj;
        }
        return null;
    }

    public <T extends BaseEntity> void jsonFilter(JSONObject jsonObject, T obj, List<String> fieldName, boolean filter) throws Exception {
        jsonFilter(jsonObject, obj, fieldName, filter, null, false);
    }

    public <T extends BaseEntity> void jsonFilter(JSONObject jsonObject, T obj, List<String> fieldName, String objKeyName, boolean filter) throws Exception {
        jsonFilter(jsonObject, obj, fieldName, filter, objKeyName, false);
    }


    /**
     * 针对json对象的过滤器，将obj对象中的字段按要求注入到json对象中
     *
     * @param jsonObject 封装数据的json对象
     * @param obj        包含要处理字段的对象
     * @param fieldName  obj中要处理的字段名
     * @param filter     true代表过滤fieldName中字段，false则代表保留
     * @param objKeyName 覆盖包装到JSONObject对象的某一数据字段名(默认为该字段所属类名)
     * @param wrapList   调用此方法的重载方法时，该值默认为false，当
     *                   循环调用jsonFilter时，会检测jsonObject中是否已经存在
     *                   键为obj类名的对象(就是刚刚过滤过同类型对象)，false表明如果不存在将会将过滤后对象
     *                   当成当个对象存储，如果该值设为true的话，则不论是否过滤过同类型对象
     *                   都会被存储在list中
     */
    public <T extends BaseEntity> void jsonFilter(JSONObject jsonObject, T obj, List<String> fieldName,
                                                  boolean filter, String objKeyName, boolean wrapList) throws Exception {
        if (jsonObject == null || obj == null || fieldName == null) {
            return;
        }
        Map<String, Object> objectMap = new HashMap<>();
        Method[] thisMethods = obj.getClass().getDeclaredMethods();
        //先尝试封装基类的field对象，再封装子类field对象
        if (obj instanceof BaseVO) {
            Method[] superMethods = obj.getClass().getSuperclass().getDeclaredMethods();
            fillJsonObject(objectMap, superMethods, obj, fieldName, filter);
        }
        fillJsonObject(objectMap, thisMethods, obj, fieldName, filter);
        String objKey;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(objKeyName)) {
            objKey = objKeyName;
        } else {
            objKey = StringUtils.toLowerCaseFirstOne(obj.getClass().getSimpleName());
        }

        mapList.add(objectMap);
        if (jsonObject.get(objKey) == null && !wrapList) {
            jsonObject.put(objKey, objectMap);
        } else {
            jsonObject.put(objKey, mapList);
        }
    }


    private void fillJsonObject(Map<String, Object> objectMap, Method[] methods, Object obj, List<String> fieldList, boolean filter) throws Exception {
        String methodPrefix = "get";
        for (Method m : methods) {
            if (m.getName().startsWith(methodPrefix)) {
                String fieldName = m.getName().replace(methodPrefix, "");
                fieldName = StringUtils.toLowerCaseFirstOne(fieldName);
                if (filter) {
                    if (!fieldList.contains(fieldName)) {
                        objectMap.put(fieldName, m.invoke(obj));
                    }
                } else {
                    if (fieldList.contains(fieldName)) {
                        objectMap.put(fieldName, m.invoke(obj));
                    }
                }
            }
        }
    }

    public void newList() {
        this.mapList = new ArrayList<>();
    }
}
