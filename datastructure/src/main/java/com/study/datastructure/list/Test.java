package com.study.datastructure.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName Test
 * @Description
 * @Author za-yaowei
 * @Date 2020/8/31 11:10
 * @Version 1.0
 */
public class Test {


    public  void aa() {
        List<Map<String,Object>> lsl = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "zhangSan");
        map.put("age","18");
        lsl.add(map);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("id", "2");
        map2.put("name", "zhangSan");
        map2.put("age","18");
        lsl.add(map2);

        Map<String,Object> map3 = new HashMap<>();
        map3.put("id", "1");
        map3.put("name", "wangwu");
        map3.put("age","18");
        lsl.add(map3);

        Map<String,Object> map4 = new HashMap<>();
        map4.put("id", "2");
        map4.put("name", "zhaoliu");
//        map4.put("age","19");
        lsl.add(map4);

        Map<Object, List<Map<String, Object>>> data = lsl.stream().collect(Collectors.groupingBy(this ::customKey));

        System.out.println(data);

    }
    private  String customKey(Map<String,Object> map){


        return String.valueOf(map.get("id")) + String.valueOf(map.get("age"));
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.aa();
    }

}
