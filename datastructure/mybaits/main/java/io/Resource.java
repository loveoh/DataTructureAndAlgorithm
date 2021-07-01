package io;

import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.io.Reader;

/**
 * @ClassName Resource
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/20 22:43
 * @Version 1.0
 */
public class Resource {

    /**
     *  获取配置文件的字节流
     * @param location
     * @return
     */
    public static InputStream getResourceAsStream(String location){
        return Resource.class.getClassLoader().getResourceAsStream(location);
    }

    public static Reader getResourceAsReader(String location){
        return null;
    }



}
