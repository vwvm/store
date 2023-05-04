package org.vwvm.paperManagement.commons.utils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <h3>store</h3>
 * <p>对类进行操作</p>
 *
 * @author : BlackBox
 * @date : 2023-04-21 21:46
 **/
public class MyObjectUtils {

    record PoDepartment(Integer id, String departmentName){};

    public static <E> List<E> listCopyProperties(List<E> list, Class<?> clazz) {

        Stream<Field> clazzDeclaredFields = Stream.of(clazz.getDeclaredFields());
        list.forEach(e -> {
            Class<?> eClass = e.getClass();
            clazzDeclaredFields.forEach(  field -> {

            });

        });
        String name = clazz.getName();
        Field[] declaredFields = clazz.getDeclaredFields();
        Stream.of(declaredFields).forEach(field -> {
            System.out.println(field.getType());
            System.out.println(field.getName());
        });

        List<Object> objects = new ArrayList<>();
        System.out.println(clazz.getName());
        list.forEach(x -> {

        });

        return list;
    }


    public static void main(String[] args) throws NoSuchFieldException {
        List<PoDepartment> list = List.of(new PoDepartment(1,"2"));
        listCopyProperties(List.of("1"), PoDepartment.class );

    }

    class A {
        public List<? extends String> list = new ArrayList<>();
        public List<Integer> list1 = new ArrayList<>();
    }

}
