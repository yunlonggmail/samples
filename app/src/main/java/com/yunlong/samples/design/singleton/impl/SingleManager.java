package com.yunlong.samples.design.singleton.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shiyunlong on 2017/7/24.
 * 单例管理类
 */

public class SingleManager {

    private static Map<String, Object> instanceMap = new HashMap<>();

    public static void registerService(String key, Object instance) {
        if (!instanceMap.containsKey(key)) {
            instanceMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return instanceMap.get(key);
    }
}
