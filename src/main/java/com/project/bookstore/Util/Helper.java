package com.project.bookstore.Util;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

public class Helper {

    private static ModelMapper m = new ModelMapper();

    public static Object map(Object src, Type dest) {
        try {
            return m.map(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
