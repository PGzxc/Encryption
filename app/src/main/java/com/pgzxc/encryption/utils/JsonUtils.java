package com.pgzxc.encryption.utils;

import com.alibaba.fastjson.JSONArray;
import com.pgzxc.encryption.bean.Person;
import java.util.List;

/**
 * Created by admin on 2018/3/30.
 */

public class JsonUtils {
    public static String objectToJsonForFastJson(List<Person> personList) {
        String jsonString = JSONArray.toJSONString(personList);
        return jsonString;
    }
}
