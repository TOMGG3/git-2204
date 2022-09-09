package com.bjpowernode.config;

import com.bjpowernode.pojo.Point;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * 接口1 Converter<S, T> 将任意类型转换成指定的其他的任何类型
 * 接口2 Formatter<T> 将字符串转换为指定的数据类型，也可以将指定的数据类型转换为字符串
 */
public class PointConverter implements Converter<String, Point> {

    /**
     * 格式要求：10,25
     */
    @Override
    public Point convert(String source) {
        String[] array = source.split(",");
        Integer x = Integer.parseInt(array[0]);
        Integer y = Integer.parseInt(array[1]);
        return new Point(x, y);
    }
}
