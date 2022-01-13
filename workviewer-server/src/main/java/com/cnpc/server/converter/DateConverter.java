package com.cnpc.server.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换
 *
 * @Author: yangg19
 * @version: 1.0.0
 * @Date: 2022年01月04日 10:33:00
 */
public class DateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {

        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
